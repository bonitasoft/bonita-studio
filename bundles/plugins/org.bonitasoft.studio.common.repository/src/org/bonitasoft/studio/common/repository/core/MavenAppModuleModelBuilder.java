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
import org.apache.maven.model.Parent;
import org.bonitasoft.studio.common.repository.core.maven.model.AppProjectConfiguration;
import org.bonitasoft.studio.common.repository.core.maven.model.MavenDependency;
import org.bonitasoft.studio.common.repository.core.maven.model.MavenPlugin;

public class MavenAppModuleModelBuilder implements MavenModelBuilder {

    private String artifactId;
    private String groupId;
    private String version;
    private String displayName;
    private String description;
    private String bonitaVersion;
    private boolean includeAdminApp;

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
    
    @Override
    public void setIncludeAdminApp(boolean includeAdminApp) {
       this.includeAdminApp = includeAdminApp;
    }

    @Override
    public Model toMavenModel() {
        Model model = new Model();
        model.setModelVersion("4.0.0");
        model.setArtifactId(artifactId);
        model.setName(displayName);
        model.setDescription(description);
        
        var parent = new Parent();
        parent.setGroupId(groupId);
        parent.setArtifactId(artifactId+"-parent");
        parent.setVersion(version);
        model.setParent(parent);

        var appProjectConfiguration = new AppProjectConfiguration(includeAdminApp);

        appProjectConfiguration.getDependencies().stream()
                .map(MavenDependency::toDependency)
                .forEach(model::addDependency);

        Build build = new Build();
        appProjectConfiguration.getPlugins().stream()
                .filter(plugin -> plugin.hasExecutions() || plugin.hasConfiguration())
                .map(MavenPlugin::toPlugin)
                .forEach(build::addPlugin);

        model.setBuild(build);
        appProjectConfiguration.getProfiles().forEach(model::addProfile);
        return model;
    }

}
