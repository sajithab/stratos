/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.stratos.mock.iaas.statistics.publisher;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.stratos.common.statistics.publisher.HealthStatisticsPublisher;
import org.apache.stratos.common.statistics.publisher.HealthStatisticsPublisherFactory;
import org.apache.stratos.common.statistics.publisher.StatisticsPublisherType;
import org.apache.stratos.mock.iaas.domain.MockInstanceContext;
import org.apache.stratos.mock.iaas.exceptions.NoStatisticsFoundException;
import org.apache.stratos.mock.iaas.services.impl.MockAutoscalingFactor;
import org.apache.stratos.mock.iaas.statistics.MockHealthStatistics;

/**
 * Health statistics notifier thread for publishing statistics periodically to CEP.
 */
public class MockHealthStatisticsNotifier implements Runnable {
    private static final Log log = LogFactory.getLog(MockHealthStatisticsNotifier.class);

    public static final String MEMORY_CONSUMPTION = "memory_consumption";
    public static final String LOAD_AVERAGE = "load_average";

    private final MockInstanceContext mockMemberContext;
    private final HealthStatisticsPublisher statsPublisher;

    public MockHealthStatisticsNotifier(MockInstanceContext mockMemberContext) {
        this.mockMemberContext = mockMemberContext;
        this.statsPublisher = HealthStatisticsPublisherFactory.createHealthStatisticsPublisher(
                StatisticsPublisherType.WSO2CEP);
        this.statsPublisher.setEnabled(true);
    }

    @Override
    public void run() {
        if (!statsPublisher.isEnabled()) {
            if (log.isWarnEnabled()) {
                log.warn("Statistics publisher is disabled");
            }
            return;
        }

        try {
            double memoryConsumption = MockHealthStatistics.getInstance().getStatistics(
                    mockMemberContext.getServiceName(), MockAutoscalingFactor.MemoryConsumption);

            if (log.isDebugEnabled()) {
                log.debug(String.format("Publishing memory consumption: [member-id] %s [value] %f",
                        mockMemberContext.getMemberId(), memoryConsumption));
            }
            statsPublisher.publish(
                    mockMemberContext.getClusterId(),
                    mockMemberContext.getClusterInstanceId(),
                    mockMemberContext.getNetworkPartitionId(),
                    mockMemberContext.getMemberId(),
                    mockMemberContext.getPartitionId(),
                    MEMORY_CONSUMPTION,
                    memoryConsumption
            );
        } catch (NoStatisticsFoundException ignore) {
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("Could not publish health statistics", e);
            }
        }


        try {
            double loadAvereage = MockHealthStatistics.getInstance().getStatistics(
                    mockMemberContext.getServiceName(), MockAutoscalingFactor.LoadAverage);
            if (log.isDebugEnabled()) {
                log.debug(String.format("Publishing load average: [member-id] %s [value] %f",
                        mockMemberContext.getMemberId(), loadAvereage));
            }
            statsPublisher.publish(
                    mockMemberContext.getClusterId(),
                    mockMemberContext.getClusterInstanceId(),
                    mockMemberContext.getNetworkPartitionId(),
                    mockMemberContext.getMemberId(),
                    mockMemberContext.getPartitionId(),
                    LOAD_AVERAGE,
                    loadAvereage
            );
        } catch (NoStatisticsFoundException ignore) {
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("Could not publish health statistics", e);
            }
        }
    }
}
