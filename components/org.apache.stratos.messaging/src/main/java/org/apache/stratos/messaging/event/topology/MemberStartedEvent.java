/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.stratos.messaging.event.topology;

import org.apache.stratos.messaging.domain.topology.Member;

import java.io.Serializable;

/**
 * This event is fired by Cartridge Agent when a member instance is started.
 */
public class MemberStartedEvent extends TopologyEvent implements Serializable {
    private String serviceDomainName;
    private String clusterDomainName;
    private String hostName;

    public String getServiceDomainName() {
        return serviceDomainName;
    }

    public void setServiceDomainName(String serviceDomainName) {
        this.serviceDomainName = serviceDomainName;
    }

    public String getClusterDomainName() {
        return clusterDomainName;
    }

    public void setClusterDomainName(String clusterDomainName) {
        this.clusterDomainName = clusterDomainName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}
