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
import java.util.Objects;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.BuildBase;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginExecution;
import org.apache.maven.model.Profile;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.codehaus.plexus.util.xml.Xpp3Dom;

public class AppProjectConfiguration implements DefaultPluginVersions {

    private static final String EXECUTE_GOAL = "execute";
    private static final String CONFIGURATION_TAG_NAME = "configuration";
    public static final String BONITA_RUNTIME_VERSION = "bonita.runtime.version";
    private static final String BUNDLE_ID = "bundle";
    private static final String DOCKER_ID = "docker";
    private static final String ENTERPRISE_BASE_IMAGE_NAME = "bonitasoft.jfrog.io/docker/bonita-subscription";
    private static final String GENERATED_GROOVY_SOURCES = "generated-groovy-sources";
    public static final String GENERATED_GROOVY_SOURCES_FODLER = "target/" + GENERATED_GROOVY_SOURCES;
   

    public static final List<MavenDependency> PROVIDED_DEPENDENCIES = List.of(new BonitaCommonDependency(),
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


    private List<MavenPlugin> plugins = new ArrayList<>();
    private List<MavenDependency> dependencies = new ArrayList<>();
    private List<Profile> profiles = new ArrayList<>();

    public AppProjectConfiguration(boolean includeAdminApp) {
        var groovyMavenPlugin = new MavenPlugin(GROOVY_MAVEN_PLUGIN_GROUP_ID,
                GROOVY_MAVEN_PLUGIN_ARTIFACT_ID, GROOVY_MAVEN_PLUGIN_VERSION);
        groovyMavenPlugin.addExecution(pluginExecution("bonita-project-properties", null, List.of(EXECUTE_GOAL), null));
        groovyMavenPlugin
                .addExecution(pluginExecution("generate-application-properties", null, List.of(EXECUTE_GOAL), null));
        groovyMavenPlugin
                .addExecution(pluginExecution("generate-classpath-assembly", null, List.of(EXECUTE_GOAL), null));
        addPlugin(groovyMavenPlugin);
        var bonitProjectPlugin = new MavenPlugin(BONITA_PROJECT_MAVEN_PLUGIN_GROUP_ID,
                BONITA_PROJECT_MAVEN_PLUGIN_ARTIFACT_ID, BONITA_PROJECT_MAVEN_PLUGIN_DEFAULT_VERSION);
        bonitProjectPlugin.addExecution(
                pluginExecution("process-bonita-artifacts", null,
                        List.of("copy-provided-pages", "business-archive", "uid-page", "merge-configuration"), null));
        addPlugin(bonitProjectPlugin);
        var buildHelperPlugin = new MavenPlugin(CODEHAUS_PLUGIN_GROUP_ID, BUILD_HELPER_MAVEN_PLUGIN,
                BUILD_HELPER_MAVEN_PLUGIN_VERSION);
        buildHelperPlugin.addExecution(pluginExecution("add-source-folder", null, List.of("add-source"),
                createBuilderHelperMavenPluginSourceConfiguration()));
        addPlugin(buildHelperPlugin);
        var assemblyPlugin = new MavenPlugin(APACHE_MAVEN_PLUGIN_GROUP_ID, MAVEN_ASSEMBLY_PLUGIN,
                MAVEN_ASSEMBLY_PLUGIN_VERSION);
        assemblyPlugin.addExecution(pluginExecution("application-archive", null, List.of("single"), null));
        assemblyPlugin.addExecution(pluginExecution("application-classpath", null, List.of("single"), null));
        addPlugin(assemblyPlugin);

        PROVIDED_DEPENDENCIES.stream().forEach(this::addDependency);
        if(includeAdminApp) {
            addDependency(new BonitaAdminAppDependency());
        }

        profiles.add(createBundleProfile());
        profiles.add(createDockerProfile());
    }

    private Profile createBundleProfile() {
        var bundleProfile = new Profile();
        bundleProfile.setId(BUNDLE_ID);
        var buildBase = new BuildBase();
        buildBase.addPlugin(unpackBundleDependency());
        buildBase.addPlugin(configureBundle());
        buildBase.addPlugin(bundleAssembly());
        bundleProfile.setBuild(buildBase);
        return bundleProfile;
    }
    

    private Plugin unpackBundleDependency() {
        var dependencyPlugin = new Plugin();
        dependencyPlugin.setArtifactId(MAVEN_DEPENDENCY_PLUGIN);
        var bundleExecution = new PluginExecution();
        bundleExecution
                .setId(PlatformUtil.isACommunityBonitaProduct() ? "prepare-bundle" : "prepare-bundle-enterprise");
        bundleExecution.addGoal("unpack");
        dependencyPlugin.addExecution(bundleExecution);
        return dependencyPlugin;
    }
    
    private Plugin configureBundle() {
        var groovyPlugin = new Plugin();
        groovyPlugin.setGroupId(GROOVY_MAVEN_PLUGIN_GROUP_ID);
        groovyPlugin.setArtifactId(GROOVY_MAVEN_PLUGIN_ARTIFACT_ID);
        var configureBundleExecution = new PluginExecution();
        configureBundleExecution
                .setId("configure-bundle");
        configureBundleExecution.addGoal(EXECUTE_GOAL);
        groovyPlugin.addExecution(configureBundleExecution);
        return groovyPlugin;
    }

    private Profile createDockerProfile() {
        var bundleProfile = new Profile();
        bundleProfile.setId(DOCKER_ID);
        if (!PlatformUtil.isACommunityBonitaProduct()) {
            bundleProfile.addProperty("docker.baseImageRepository", ENTERPRISE_BASE_IMAGE_NAME);
        }
        var buildBase = new BuildBase();
        buildBase.addPlugin(groovyMavenPlugin("generate-docker-resources"));
        buildBase.addPlugin(dockerExecPlugin());
        bundleProfile.setBuild(buildBase);
        return bundleProfile;
    }

    private Plugin groovyMavenPlugin(String id) {
        var groovyMavenPlugin = new Plugin();
        groovyMavenPlugin.setGroupId(GROOVY_MAVEN_PLUGIN_GROUP_ID);
        groovyMavenPlugin.setArtifactId(GROOVY_MAVEN_PLUGIN_ARTIFACT_ID);
        var execution = new PluginExecution();
        execution.setId(id);
        execution.addGoal(EXECUTE_GOAL);
        groovyMavenPlugin.addExecution(execution);
        return groovyMavenPlugin;
    }

    private Plugin bundleAssembly() {
        var assemblyPlugin = new Plugin();
        assemblyPlugin.setArtifactId(MAVEN_ASSEMBLY_PLUGIN);
        var bundleExecution = new PluginExecution();
        bundleExecution
                .setId(PlatformUtil.isACommunityBonitaProduct() ? "bundle-archive" : "bundle-archive-enterprise");
        bundleExecution.addGoal("single");
        assemblyPlugin.addExecution(bundleExecution);
        return assemblyPlugin;
    }

    private Plugin dockerExecPlugin() {
        var execPlugin = new Plugin();
        execPlugin.setGroupId(EXEC_MAVEN_PLUGIN_GROUP_ID);
        execPlugin.setArtifactId(EXEC_MAVEN_PLUGIN_ARTIFACT_ID);
        var dockerPlugin = new PluginExecution();
        dockerPlugin.setId("build-image");
        dockerPlugin.addGoal("exec");
        execPlugin.addExecution(dockerPlugin);
        return execPlugin;
    }

    private void addDependency(MavenDependency mavenDependency) {
        dependencies.add(mavenDependency);
    }

    private void addPlugin(MavenPlugin plugin) {
        plugins.add(plugin);
    }

    public List<MavenPlugin> getPlugins() {
        return plugins;
    }

    public List<MavenDependency> getDependencies() {
        return dependencies;
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    private Xpp3Dom createBuilderHelperMavenPluginSourceConfiguration() {
        Xpp3Dom pluginConfiguration = new Xpp3Dom(CONFIGURATION_TAG_NAME);
        Xpp3Dom sources = new Xpp3Dom("sources");
        Xpp3Dom srcGroovy = new Xpp3Dom("source");
        srcGroovy.setValue("src-groovy");
        sources.addChild(srcGroovy);
        Xpp3Dom generatedSrcGroovy = new Xpp3Dom("source");
        generatedSrcGroovy.setValue("${project.build.directory}/" + GENERATED_GROOVY_SOURCES);
        sources.addChild(generatedSrcGroovy);
        pluginConfiguration.addChild(sources);
        return pluginConfiguration;
    }

    private PluginExecution pluginExecution(String id, String phase, List<String> goals, Object configuration) {
        PluginExecution execution = new PluginExecution();
        execution.setId(id);
        if (phase != null) {
            execution.setPhase(phase);
        }
        execution.setGoals(goals);
        if (configuration != null) {
            execution.setConfiguration(configuration);
        }
        return execution;
    }

    public static boolean isInternalDependency(Dependency dependency) {
        var internalDependencies = new ArrayList<>(PROVIDED_DEPENDENCIES);
        // legacy internal dependency 
        internalDependencies.add(new MavenDependency("com.bonitasoft.engine", "bonita-common-sp", null, "jar", null, Artifact.SCOPE_PROVIDED));
        internalDependencies.add(new MavenDependency("org.bonitasoft.engine", "bonita-common", null, "jar", null, Artifact.SCOPE_PROVIDED));
        internalDependencies.add(new MavenDependency(CODEHAUS_GROOVY_GROUPID, "groovy-all", null, "pom", null, Artifact.SCOPE_PROVIDED));
        return internalDependencies.stream().map(MavenDependency::toGAV).anyMatch(new GAV(dependency)::isSameAs);
    }

    public static String getBonitaRuntimeVersion(Model model) {
        if (model.getProperties().containsKey(BONITA_RUNTIME_VERSION)) {
            return model.getProperties().getProperty(BONITA_RUNTIME_VERSION);
        }
        return null;
    }

    public static boolean isBdmDependency(Dependency dep) {
        return isLegacyBdmDependency(dep) || (Objects.equals(dep.getGroupId(), "${project.groupId}")
                && Objects.equals(dep.getVersion(), "${project.version}")
                && dep.getArtifactId().endsWith("-bdm-model"));
    }

    private static boolean isLegacyBdmDependency(Dependency dep) {
        return Objects.equals(Artifact.SCOPE_PROVIDED, dep.getScope())
                && Objects.equals(dep.getArtifactId(), "bdm-client")
                && Objects.equals(dep.getVersion(), "1.0.0");
    }
}
