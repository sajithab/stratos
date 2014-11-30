/**
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at

 *  http://www.apache.org/licenses/LICENSE-2.0

 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.stratos.cli.beans.autoscaler.policy.deployment;

import org.apache.stratos.cli.beans.autoscaler.partition.NetworkPartition;
import org.apache.stratos.cli.beans.autoscaler.partition.Partition;

import java.util.List;

public class DeploymentPolicy {
    private String id;
    
    private String description;
    
    private boolean isPublic;

     //partition groups
     private List<NetworkPartition> networkPartition;

    //partitions
    private List<Partition> partition;  

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<NetworkPartition> getNetworkPartition() {
        return networkPartition;
    }

    public void setNetworkPartition(List<NetworkPartition> networkPartition) {
        this.networkPartition = networkPartition;
    }

    public List<Partition> getPartition() {
        return partition;
    }

    public void setPartition(List<Partition> partition) {
        this.partition = partition;
    }
    
    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
