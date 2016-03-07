/*
 * Copyright 2016 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.thoughtworks.go.server.messaging.elasticagents;

import com.thoughtworks.go.config.JobAgentConfig;
import com.thoughtworks.go.server.messaging.GoMessage;

import java.util.List;
import java.util.Map;

public class CreateAgentMessage implements GoMessage {
    private final String autoregisterKey;
    private final List<String> resources;
    private final String environment;
    private final JobAgentConfig jobAgentConfig;

    public CreateAgentMessage(String autoregisterKey, List<String> resources, String environment, JobAgentConfig jobAgentConfig) {
        this.autoregisterKey = autoregisterKey;
        this.resources = resources;
        this.environment = environment;
        this.jobAgentConfig = jobAgentConfig;
    }

    public String autoregisterKey() {
        return autoregisterKey;
    }

    public List<String> resources() {
        return resources;
    }

    public String environment() {
        return environment;
    }

    @Override
    public String toString() {
        return "CreateAgentMessage{" +
                "autoregisterKey='" + autoregisterKey + '\'' +
                ", environment='" + environment + '\'' +
                ", jobAgentConfig=" + jobAgentConfig +
                '}';
    }

    public String getPluginId() {
        return jobAgentConfig.getPluginId();
    }

    public Map<String, String> getConfiguration() {
        return jobAgentConfig.getConfigurationAsMap(true);
    }
}
