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
import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.Model;
import org.apache.maven.model.PluginExecution;
import org.codehaus.plexus.util.xml.Xpp3Dom;

public class ProjectDefaultConfiguration implements DefaultPluginVersions {

    public static final String BONITA_RUNTIME_VERSION = "bonita.runtime.version";
    private static final String ENCODING_CHARSET = "UTF-8";
    private static final String JAVA_VERSION = "11";

    public static final List<MavenDependency> PROVIDED_DEPENDENCIES = List.of(
            new BonitaCommonDependency(),
            new MavenDependency(CODEHAUS_GROOVY_GROUPID, "groovy", null),
            new MavenDependency(CODEHAUS_GROOVY_GROUPID, "groovy-json", null),
            new MavenDependency(CODEHAUS_GROOVY_GROUPID, "groovy-xml", null),
            new MavenDependency(CODEHAUS_GROOVY_GROUPID, "groovy-nio", null),
            new MavenDependency(CODEHAUS_GROOVY_GROUPID, "groovy-datetime", null),
            new MavenDependency(CODEHAUS_GROOVY_GROUPID, "groovy-dateutil", null),
            new MavenDependency(CODEHAUS_GROOVY_GROUPID, "groovy-sql", null),
            new MavenDependency(CODEHAUS_GROOVY_GROUPID, "groovy-templates", null),
            new MavenDependency(CODEHAUS_GROOVY_GROUPID, "groovy-jsr223", null),
            new MavenDependency(CODEHAUS_GROOVY_GROUPID, "groovy-jmx", null),
            new MavenDependency(CODEHAUS_GROOVY_GROUPID, "groovy-yaml", null),
            new MavenDependency(CODEHAUS_GROOVY_GROUPID, "groovy-servlet", null));
    

    private Properties properties = new Properties();
    private List<MavenPlugin> plugins = new ArrayList<>();
    private List<MavenDependency> dependencies = new ArrayList<>();

    public ProjectDefaultConfiguration(String bonitaRuntimeVersion) {
        var installPlugin = new MavenPlugin(APACHE_MAVEN_PLUGIN_GROUP_ID, MAVEN_INSTALL_PLUGIN, MAVEN_INSTALL_PLUGIN_VERSION);
        installPlugin.setConfiguration(installPluginConfiguration());
        addPlugin(installPlugin);
        addPlugin(new MavenPlugin(APACHE_MAVEN_PLUGIN_GROUP_ID, MAVEN_SOURCE_PLUGIN, MAVEN_SOURCE_PLUGIN_VERSION));
        addPlugin(new MavenPlugin(APACHE_MAVEN_PLUGIN_GROUP_ID, MAVEN_COMPILER_PLUGIN, MAVEN_COMPILER_PLUGIN_VERSION));
        addPlugin(new MavenPlugin(APACHE_MAVEN_PLUGIN_GROUP_ID, MAVEN_CLEAN_PLUGIN, MAVEN_CLEAN_PLUGIN_VERSION));
        addPlugin(new MavenPlugin(APACHE_MAVEN_PLUGIN_GROUP_ID, MAVEN_JAVADOC_PLUGIN, MAVEN_JAVADOC_PLUGIN_VERSION));
        addPlugin(new MavenPlugin(APACHE_MAVEN_PLUGIN_GROUP_ID, MAVEN_ASSEMBLY_PLUGIN, MAVEN_ASSEMBLY_PLUGIN_VERSION));
        addPlugin(new MavenPlugin(APACHE_MAVEN_PLUGIN_GROUP_ID, MAVEN_SUREFIRE_PLUGIN, MAVEN_SUREFIRE_PLUGIN_VERSION));
        addPlugin(new MavenPlugin(APACHE_MAVEN_PLUGIN_GROUP_ID, MAVEN_FAILSAFE_PLUGIN, MAVEN_FAILSAFE_PLUGIN_VERSION));
        addPlugin(new MavenPlugin(APACHE_MAVEN_PLUGIN_GROUP_ID, MAVEN_DEPENDENCY_PLUGIN, MAVEN_DEPENDENCY_PLUGIN_VERSION));
        var jarPlugin = new MavenPlugin(APACHE_MAVEN_PLUGIN_GROUP_ID, MAVEN_JAR_PLUGIN, MAVEN_JAR_PLUGIN_VERSION);
        jarPlugin.setConfiguration(jarPluginConfiguration());
        addPlugin(jarPlugin);
        addPlugin(new MavenPlugin(APACHE_MAVEN_PLUGIN_GROUP_ID, MAVEN_RESOURCES_PLUGIN, MAVEN_RESOURCES_PLUGIN_VERSION));
        var deployPlugin = new MavenPlugin(APACHE_MAVEN_PLUGIN_GROUP_ID, MAVEN_DEPLOY_PLUGIN, MAVEN_DEPLOY_PLUGIN_VERSION);
        deployPlugin.setConfiguration(deployPluginConfiguration());
        addPlugin(deployPlugin);
        addPlugin(new MavenPlugin(APACHE_MAVEN_PLUGIN_GROUP_ID, MAVEN_SHADE_PLUGIN, MAVEN_SHADE_PLUGIN_VERSION));
        addPlugin(new MavenPlugin(CODEHAUS_PLUGIN_GROUP_ID, FLATTEN_MAVEN_PLUGIN, FLATTEN_MAVEN_PLUGIN_VERSION));
        MavenPlugin bonitProjectPlugin = new MavenPlugin(BONITA_PROJECT_MAVEN_PLUGIN_GROUP_ID, BONITA_PROJECT_MAVEN_PLUGIN_ARTIFACT_ID,
                BONITA_PROJECT_MAVEN_PLUGIN_DEFAULT_VERSION);
        bonitProjectPlugin.addDependency(bonitaBusinessDataGeneratorDependency());
        addPlugin(bonitProjectPlugin);
        MavenPlugin buildHelperPlugin = new MavenPlugin(CODEHAUS_PLUGIN_GROUP_ID, BUILD_HELPER_MAVEN_PLUGIN,
                BUILD_HELPER_MAVEN_PLUGIN_VERSION);
        buildHelperPlugin.addExecution(pluginExecution("generate-sources", List.of("add-source"),
                createBuilderHelperMavenPluginConfiguration()));
        addPlugin(buildHelperPlugin);

        PROVIDED_DEPENDENCIES.stream().forEach(this::addDependency);

        properties.setProperty(BONITA_RUNTIME_VERSION, bonitaRuntimeVersion);
        properties.setProperty("maven.compiler.release", JAVA_VERSION);
        properties.setProperty("project.build.sourceEncoding", ENCODING_CHARSET);
        properties.setProperty("project.reporting.outputEncoding", ENCODING_CHARSET);
        plugins.stream()
                .forEach(plugin -> properties.setProperty(plugin.getVersionPropertyName(), plugin.getVersion()));
    }

    private static Dependency bonitaBusinessDataGeneratorDependency() {
        var dep = new Dependency();
        dep.setGroupId("org.bonitasoft.engine.data");
        dep.setArtifactId("bonita-business-data-generator");
        dep.setVersion(String.format("${%s}", BONITA_RUNTIME_VERSION));
        return dep;
    }

    private Object jarPluginConfiguration() {
        Xpp3Dom pluginConfiguration = new Xpp3Dom("configuration");
        Xpp3Dom skipIfEmpty = new Xpp3Dom("skipIfEmpty");
        skipIfEmpty.setValue(Boolean.TRUE.toString());
        pluginConfiguration.addChild(skipIfEmpty);
        return pluginConfiguration;
    }
    
    private Object deployPluginConfiguration() {
        Xpp3Dom pluginConfiguration = new Xpp3Dom("configuration");
        Xpp3Dom skipIfEmpty = new Xpp3Dom("skip");
        skipIfEmpty.setValue(Boolean.TRUE.toString());
        pluginConfiguration.addChild(skipIfEmpty);
        return pluginConfiguration;
    }
    
    private Object installPluginConfiguration() {
        Xpp3Dom pluginConfiguration = new Xpp3Dom("configuration");
        Xpp3Dom skipIfEmpty = new Xpp3Dom("skip");
        skipIfEmpty.setValue(Boolean.TRUE.toString());
        pluginConfiguration.addChild(skipIfEmpty);
        return pluginConfiguration;
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
    
    public DependencyManagement getDependencyManagement() {
        var dependencyManagement = new DependencyManagement();
        dependencyManagement.addDependency(runtimeBOMImportDependency());
        return dependencyManagement;
    }

    public static Dependency runtimeBOMImportDependency() {
        var runtimeBOM = bonitaBusinessDataGeneratorDependency();
        runtimeBOM.setGroupId(RUNTIME_BOM_GROUP_ID);
        runtimeBOM.setArtifactId(RUNTIME_BOM_ARTIFACT_ID);
        runtimeBOM.setVersion(String.format("${%s}", BONITA_RUNTIME_VERSION));
        runtimeBOM.setType("pom");
        runtimeBOM.setScope("import");
        return runtimeBOM;
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
        return PROVIDED_DEPENDENCIES.stream()
                .map(MavenDependency::toGAV)
                .anyMatch(new GAV(dependency)::isSameAs);
    }

    public static String getBonitaRuntimeVersion(Model model) {
        if (model.getProperties().containsKey(BONITA_RUNTIME_VERSION)) {
            return model.getProperties().getProperty(BONITA_RUNTIME_VERSION);
        }
        return null;
    }
}
