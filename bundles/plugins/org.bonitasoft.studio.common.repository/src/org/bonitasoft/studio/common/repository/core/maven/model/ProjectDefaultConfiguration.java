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
package org.bonitasoft.studio.common.repository.core.maven.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.PluginExecution;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.GAV;
import org.codehaus.plexus.util.xml.Xpp3Dom;

public class ProjectDefaultConfiguration implements DefaultPluginVersions {

    private static final String ENCODING_CHARSET = "UTF-8";
    private static final String JAVA_VERSION = "11";
    private static final String SLF4J_VERSION = "1.7.30";
    private static final String GROOVY_VERSION = "2.4.21";

  
  
    private static final List<MavenDependency> PROVIDED_DEPENDENCIES = List.of(
            new BonitaCommonDependency(),
            new MavenDependency("org.codehaus.groovy", "groovy-all", "${groovy.version}"),
            new MavenDependency("org.slf4j", "slf4j-api", "${slf4j-api.version}"));

    private Properties properties = new Properties();
    private List<MavenPlugin> plugins = new ArrayList<>();
    private List<MavenDependency> dependencies = new ArrayList<>();

    public ProjectDefaultConfiguration(String bonitaVersion) {

        addPlugin(new MavenPlugin(APACHE_MAVEN_PLUGIN_GROUP_ID, MAVEN_INSTALL_PLUGIN, MAVEN_INSTALL_PLUGIN_VERSION));
        addPlugin(new MavenPlugin(BONITA_PROJECT_MAVEN_PLUGIN_GROUP_ID, BONITA_PROJECT_MAVEN_PLUGIN_ARTIFACT_ID,
                BONITA_PROJECT_MAVEN_PLUGIN_DEFAULT_VERSION));
        MavenPlugin buildHelperPlugin = new MavenPlugin(CODEHAUS_PLUGIN_GROUP_ID, BUILD_HELPER_MAVEN_PLUGIN,
                BUILD_HELPER_MAVEN_PLUGIN_VERSION);
        buildHelperPlugin.addExecution(pluginExecution("generate-sources", List.of("add-source"),
                createBuilderHelperMavenPluginConfiguration()));
        addPlugin(buildHelperPlugin);
        
        PROVIDED_DEPENDENCIES.stream().forEach(this::addDependency);

        properties.setProperty("bonita.version", bonitaVersion);
        properties.setProperty("groovy.version", GROOVY_VERSION);
        properties.setProperty("slf4j-api.version", SLF4J_VERSION);
        properties.setProperty("maven.compiler.source", JAVA_VERSION);
        properties.setProperty("maven.compiler.target", JAVA_VERSION);
        properties.setProperty("project.build.sourceEncoding", ENCODING_CHARSET);
        properties.setProperty("project.reporting.outputEncoding", ENCODING_CHARSET);
        plugins.stream().forEach(plugin -> properties.setProperty(plugin.getVersionPropertyName(), plugin.getVersion()));
    }

    private void addDependency(MavenDependency mavenDependency) {
       dependencies.add(mavenDependency);
    }

    private void addPlugin(MavenPlugin plugin) {
        plugins.add(plugin);
    }

    public Properties getProperties() {
        return properties;
    }

    public List<MavenPlugin> getPlugins() {
        return plugins;
    }
    
    public List<MavenDependency> getDependencies() {
        return dependencies;
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

    private PluginExecution pluginExecution(String phase, List<String> goals, Object configuration) {
        PluginExecution execution = new PluginExecution();
        execution.setPhase(phase);
        execution.setGoals(goals);
        execution.setConfiguration(configuration);
        return execution;
    }
    
    public static boolean isInternalDependency(Dependency dependency) {
        return PROVIDED_DEPENDENCIES.stream().map(MavenDependency::toGAV).anyMatch(new GAV(dependency)::equals);
    }

}
