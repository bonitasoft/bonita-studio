/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.repository.core.maven.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginExecution;

public class MavenPlugin {
    
    private final String groupId;
    private final String artifactId;
    private final String version;
    private final List<PluginExecution> executions = new ArrayList<>();

    public MavenPlugin(String groupId, String artifactId, String version) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
    }
    
    public Plugin toManagedPlugin() {
        Plugin plugin = new Plugin();
        plugin.setGroupId(groupId);
        plugin.setArtifactId(artifactId);
        plugin.setVersion(getVersionPropertyVariable());
        return plugin;
    }
    
    public boolean hasExecutions() {
        return !executions.isEmpty();
    }
    
    public Plugin toPlugin() {
        Plugin plugin = new Plugin();
        plugin.setGroupId(groupId);
        plugin.setArtifactId(artifactId);
        executions.stream().forEach(plugin::addExecution);
        return plugin;
    }
    
    public void addExecution(PluginExecution execution) {
        executions.add(execution);
    }

    public String getVersion() {
        return version;
    }

    public String getVersionPropertyVariable() {
        return String.format("${%s.version}", artifactId);
    }
    
    public String getVersionPropertyName() {
        return String.format("%s.version", artifactId);
    }

}
