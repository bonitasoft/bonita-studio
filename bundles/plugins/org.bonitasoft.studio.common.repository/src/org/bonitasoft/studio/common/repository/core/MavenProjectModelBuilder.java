/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.repository.core;

import org.apache.maven.model.Build;
import org.apache.maven.model.Model;
import org.apache.maven.model.PluginManagement;
import org.bonitasoft.studio.common.repository.core.maven.model.MavenDependency;
import org.bonitasoft.studio.common.repository.core.maven.model.MavenPlugin;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectDefaultConfiguration;

public class MavenProjectModelBuilder {

    private String artifactId;
    private String groupId;
    private String version;
    private String displayName;
    private String description;
    private String bonitaVersion;

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBonitaVersion() {
        return bonitaVersion;
    }

    public void setBonitaVersion(String bonitaVersion) {
        this.bonitaVersion = bonitaVersion;
    }

    public Model toMavenModel() {
        Model model = new Model();
        model.setModelVersion("4.0.0");
        model.setName(getDisplayName());
        model.setArtifactId(getArtifactId());
        model.setGroupId(getGroupId());
        model.setVersion(getVersion());
        model.setDescription(getDescription());

        ProjectDefaultConfiguration defaultConfiguration = new ProjectDefaultConfiguration(bonitaVersion);
        defaultConfiguration.getProperties()
                .forEach((key, value) -> model.addProperty(key.toString(), value.toString()));

        defaultConfiguration.getDependencies().stream()
                .map(MavenDependency::toProvidedDependency)
                .forEach(model::addDependency);

        Build build = new Build();
        defaultConfiguration.getPlugins().stream()
                .filter(MavenPlugin::hasExecutions)
                .map(MavenPlugin::toPlugin)
                .forEach(build::addPlugin);

        PluginManagement pluginManagement = new PluginManagement();
        defaultConfiguration.getPlugins().stream()
                .map(MavenPlugin::toManagedPlugin)
                .forEach(pluginManagement::addPlugin);

        build.setPluginManagement(pluginManagement);
        model.setBuild(build);
        return model;
    }

}
