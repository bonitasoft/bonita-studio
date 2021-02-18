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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.maven.model.Build;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginExecution;
import org.apache.maven.model.PluginManagement;
import org.apache.maven.model.Repository;
import org.apache.maven.model.RepositoryPolicy;
import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.codehaus.plexus.util.xml.Xpp3Dom;

public class MavenProjectModelBuilder {

    public static final String BONITA_PROJECT_MAVEN_PLUGIN_ARTIFACT_ID = "bonita-project-maven-plugin";
    public static final String BONITA_PROJECT_MAVEN_PLUGIN_GROUP_ID = "org.bonitasoft.maven";
    public static final String BONITA_PROJECT_MAVEN_PLUGIN_DEFAULT_VERSION = "0.0.1-SNAPSHOT";

    private String artifactId;
    private String groupId;
    private String version;
    private String displayName;
    private String description;
    private String bonitaVersion;
    private List<Dependency> dependencies = new ArrayList<>();

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

    public List<Dependency> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<Dependency> dependencies) {
        this.dependencies = dependencies;
    }

    public Model toMavenModel() {
        Model model = new Model();
        model.setModelVersion("4.0.0");
        model.setName(getDisplayName());
        model.setArtifactId(getArtifactId());
        model.setGroupId(getGroupId());
        model.setVersion(getVersion());
        model.setDescription(getDescription());

        model.addProperty("bonita.version", getBonitaVersion());
        model.addProperty("groovy.version", "2.4.21");
        model.addProperty("slf4j-api.version", "1.7.30");
        model.addProperty("maven.compiler.source", "11");
        model.addProperty("maven.compiler.target", "11");
        model.addProperty("project.build.sourceEncoding", "UTF-8");
        model.addProperty("project.reporting.outputEncoding", "UTF-8");
        model.addProperty("build-helper-maven-plugin.version", "3.2.0");
        model.addProperty("maven-install-plugin.version", "3.0.0-M1");
        model.addProperty("bonita-project-maven-plugin.version", BONITA_PROJECT_MAVEN_PLUGIN_DEFAULT_VERSION);

        if (PlatformUtil.isACommunityBonitaProduct()) {
            model.addDependency(providedDependency("org.bonitasoft.engine", "bonita-common", "${bonita.version}"));
        } else {
            model.addDependency(providedDependency("com.bonitasoft.engine", "bonita-common-sp", "${bonita.version}"));
        }
        model.addDependency(providedDependency("org.codehaus.groovy", "groovy-all", "${groovy.version}"));
        model.addDependency(providedDependency("org.slf4j", "slf4j-api", "${slf4j-api.version}"));

        dependencies.stream().forEach(model::addDependency);

        Build build = new Build();
        Plugin helperPlugin = plugin("org.codehaus.mojo", "build-helper-maven-plugin",
                "${build-helper-maven-plugin.version}");
        helperPlugin.addExecution(pluginExecution("generate-sources",
                Collections.singletonList("add-source"),
                createBuilderHelperMavenPluginConfiguration()));
        build.addPlugin(helperPlugin);

        PluginManagement pluginManagement = new PluginManagement();
        pluginManagement.addPlugin(plugin("org.apache.maven.plugins", "maven-install-plugin",
                "${maven-install-plugin.version}"));
        pluginManagement.addPlugin(
                plugin(BONITA_PROJECT_MAVEN_PLUGIN_GROUP_ID, BONITA_PROJECT_MAVEN_PLUGIN_ARTIFACT_ID,
                        "${bonita-project-maven-plugin.version}"));

        build.setPluginManagement(pluginManagement);
        model.setBuild(build);

        model.addPluginRepository(repository("ossrh-snapshot", "ossrh-snapshot",
                "https://oss.sonatype.org/content/repositories/snapshots/", false, true));

        return model;
    }

    private Repository repository(String id, String name, String url, boolean release, boolean snapshot) {
        Repository repository = new Repository();
        repository.setId(id);
        repository.setName(name);
        repository.setUrl(url);
        RepositoryPolicy releaseRepositoryPolicy = new RepositoryPolicy();
        releaseRepositoryPolicy.setEnabled(release);
        repository.setReleases(releaseRepositoryPolicy);
        RepositoryPolicy snapshotRepositoryPolicy = new RepositoryPolicy();
        snapshotRepositoryPolicy.setEnabled(snapshot);
        repository.setSnapshots(snapshotRepositoryPolicy);
        return repository;
    }

    private PluginExecution pluginExecution(String phase, List<String> goals, Object configuration) {
        PluginExecution execution = new PluginExecution();
        execution.setPhase(phase);
        execution.setGoals(goals);
        execution.setConfiguration(configuration);
        return execution;
    }

    private Plugin plugin(String groupId, String artifactId, String version) {
        Plugin plugin = new Plugin();
        plugin.setGroupId(groupId);
        plugin.setArtifactId(artifactId);
        plugin.setVersion(version);
        return plugin;
    }

    private Dependency providedDependency(String groupId, String artifactId, String version) {
        Dependency dependency = new Dependency();
        dependency.setScope("provided");
        dependency.setGroupId(groupId);
        dependency.setArtifactId(artifactId);
        dependency.setVersion(version);
        return dependency;
    }

    private Xpp3Dom createBuilderHelperMavenPluginConfiguration() {
        Xpp3Dom pluginConfiguration = new Xpp3Dom("configuration");
        Xpp3Dom sources = new Xpp3Dom("sources");
        Xpp3Dom srcConnectors = new Xpp3Dom("source");
        srcConnectors.setValue("src-connectors");
        Xpp3Dom srcFilters = new Xpp3Dom("source");
        srcFilters.setValue("src-filters");
        Xpp3Dom srcGroovy = new Xpp3Dom("source");
        srcGroovy.setValue("src-groovy");
        Xpp3Dom providedGroovySrc = new Xpp3Dom("source");
        providedGroovySrc.setValue("src-providedGroovy");
        sources.addChild(srcConnectors);
        sources.addChild(srcFilters);
        sources.addChild(srcGroovy);
        sources.addChild(providedGroovySrc);
        pluginConfiguration.addChild(sources);
        return pluginConfiguration;
    }

}
