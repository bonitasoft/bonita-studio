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

import java.util.List;

import org.apache.maven.model.License;
import org.apache.maven.model.Model;
import org.apache.maven.model.Parent;
import org.apache.maven.model.Repository;
import org.apache.maven.model.RepositoryPolicy;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.core.maven.model.DefaultPluginVersions;

public class MavenParentProjectModelBuilder implements MavenModelBuilder{

    private static final String APP_MODULE_NAME = "app";
    private String artifactId;
    private String groupId;
    private String version;
    private String displayName;
    private String description;
    private String bonitaVersion;
    private boolean useSnapshotRepository;
    private boolean includeAdminApp;
    
    public MavenParentProjectModelBuilder() {
        this(false);
    }

    public MavenParentProjectModelBuilder(boolean useSnapshotRepository) {
      this.useSnapshotRepository = useSnapshotRepository;
    }

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
        model.setArtifactId(getArtifactId()+"-parent");
        model.setGroupId(getGroupId());
        model.setVersion(getVersion());
        model.setPackaging("pom");

        var bonitaRuntimeVersion = bonitaVersion == null ? ProductVersion.BONITA_RUNTIME_VERSION : bonitaVersion;

        var bonitaProjectParent = new Parent();
        bonitaProjectParent.setGroupId(DefaultPluginVersions.BONITA_PROJECT_GROUP_ID);
        bonitaProjectParent.setArtifactId(DefaultPluginVersions.BONITA_PROJECT_ARTIFACT_ID);
        bonitaProjectParent.setVersion(bonitaRuntimeVersion);
        model.setParent(bonitaProjectParent); 
        
        // Set an empty license to avoid inheriting the parent GPLv2 license.
        model.setLicenses(List.of(new License()));
        
        model.getModules().add(APP_MODULE_NAME);

        if(useSnapshotRepository) {
            var pluginRepository = new Repository();
            pluginRepository.setId("ossrh-snapshots");
            pluginRepository.setUrl("https://oss.sonatype.org/content/repositories/snapshots");
            var disableRelease = new RepositoryPolicy();
            disableRelease.setEnabled(false);
            pluginRepository.setReleases(disableRelease);
            var enableSnapshot = new RepositoryPolicy();
            enableSnapshot.setEnabled(true);
            pluginRepository.setSnapshots(enableSnapshot);
            model.setPluginRepositories(List.of(pluginRepository));
        }
        return model;
    }


}
