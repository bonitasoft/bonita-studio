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
package org.bonitasoft.studio.rest.api.extension.core.repository.migration;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.versioning.ComparableVersion;
import org.apache.maven.model.Build;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginManagement;
import org.bonitasoft.studio.common.repository.core.migration.MavenModelMigration;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.codehaus.plexus.util.xml.Xpp3Dom;

public class Groovy3MigrationStep implements MavenModelMigration {

    private static final String JAVA_VERSION_PROPERTY = "java.version";
    private static final String GROOVY_VERSION_PROPERTY = "groovy.version";
    private static final String GROOVY_ALL_VERSION_PROPERTY = "groovy-all.version";;
    private static final String MAVEN_SUREFIRE_PLUGIN_VERSION = "3.0.0-M5";
    private static final String GROOVY_VERSION = "3.0.8";
    private static final String SPOCK_VERSION = "2.0-groovy-3.0";
    private static final String JAVA_VERSION = "11";
    
    private static final ComparableVersion _3_0_0 = new ComparableVersion("3.0.0");

    @Override
    public MigrationReport migrate(Model model) {
        MigrationReport report = new MigrationReport();
        Properties properties = model.getProperties();

        properties.setProperty(GROOVY_ALL_VERSION_PROPERTY, GROOVY_VERSION);
        properties.setProperty("maven-compiler-plugin.version", "3.8.1");
        properties.setProperty("maven-surefire-plugin.version", MAVEN_SUREFIRE_PLUGIN_VERSION);
        
        AtomicReference<String> existingJavaVersion = new AtomicReference<>("1.8");
        if(properties.containsKey(JAVA_VERSION_PROPERTY)) {
            existingJavaVersion.set(properties.getProperty(JAVA_VERSION_PROPERTY));
        }
        properties.setProperty(JAVA_VERSION_PROPERTY, JAVA_VERSION);
        
        if(properties.containsKey("maven.compiler.target")) {
            existingJavaVersion.set(properties.getProperty("maven.compiler.target"));
        }
        properties.setProperty("maven.compiler.target", "${java.version}");
        properties.setProperty("maven.compiler.source", "${java.version}");
        properties.setProperty("groovy-eclipse-compiler.version", "3.7.0");
        properties.setProperty("groovy-eclipse-batch.version", "3.0.8-01");
        properties.setProperty("spock.version", SPOCK_VERSION);

        var existingGroovyVersion = properties.getOrDefault(GROOVY_VERSION_PROPERTY, "2.4.x");
        properties.remove(GROOVY_VERSION_PROPERTY);
        
        report.updated(String.format("Groovy version has been updated from `%s` to `%s`. Check the https://groovy-lang.org/releasenotes/groovy-3.0.html[release note] for more information about the breaking changes.", existingGroovyVersion, GROOVY_VERSION));
                
        findDependency(model.getDependencies(), "org.codehaus.groovy", "groovy-all")
                .ifPresent(dependency -> {
                    dependency.setVersion("${groovy-all.version}");
                    dependency.setType("pom");
                    dependency.setScope(Artifact.SCOPE_PROVIDED);
                });

        model.getDependencies().add(newDependency("org.codehaus.groovy",
                "groovy-dateutil",
                "${groovy-all.version}",
                Artifact.SCOPE_PROVIDED));

        findDependency(model.getDependencies(), "org.spockframework", "spock-core")
                .ifPresent(dependency -> {
                    var existingSpockVersion = dependency.getVersion();
                    dependency.setVersion("${spock.version}");
                    report.updated(String.format("`spock-core` version has been updated from `%s` to `%s`. For more information check the https://spockframework.org/spock/docs/2.0/migration_guide.html#_migration_guide_2_0[migration guide].", existingSpockVersion, SPOCK_VERSION));
                });
        
        

        findDependency(model.getDependencies(), "com.athaydes", "spock-reports")
                .ifPresent(dependency -> {
                    var existingSpockReportsVersion = dependency.getVersion();
                    dependency.setVersion("${spock.version}");
                    report.updated(String.format("`spock-reports` version has been updated from `%s` to `%s`.", existingSpockReportsVersion, SPOCK_VERSION));
                });

        Build build = model.getBuild();
        if (build != null) {
            findPlugin(model.getBuild().getPlugins(), "org.apache.maven.plugins", "maven-compiler-plugin")
                    .ifPresent(p -> {
                        p.setVersion("${maven-compiler-plugin.version}");
                        Xpp3Dom configuration = (Xpp3Dom) p.getConfiguration();
                        removeNode("source", configuration);
                        var targetNode = configuration.getChild("target");
                        if(targetNode != null) {
                            existingJavaVersion.set(targetNode.getValue());
                        }
                        removeNode("target", configuration);
                        findDependency(p.getDependencies(), "org.codehaus.groovy", "groovy-eclipse-compiler")
                                .ifPresent(d -> d.setVersion("${groovy-eclipse-compiler.version}"));
                        findDependency(p.getDependencies(), "org.codehaus.groovy", "groovy-eclipse-batch")
                                .ifPresent(d -> d.setVersion("${groovy-eclipse-batch.version}"));
                    });
            
            if(!JAVA_VERSION.equals(existingJavaVersion.get())) {
                report.updated(String.format("Java version has been updated from `%s` to `%s`", existingJavaVersion.get(), JAVA_VERSION));
            }

            PluginManagement pluginManagement = Optional.ofNullable(build.getPluginManagement())
                    .orElseGet(PluginManagement::new);
            findPlugin(pluginManagement.getPlugins(), "org.apache.maven.plugins",
                    "maven-surefire-plugin")
                            .ifPresentOrElse(
                                    surefirePlugin -> {
                                        var existingVersion = surefirePlugin.getVersion();
                                        surefirePlugin.setVersion("${maven-surefire-plugin.version}");
                                        if(!Objects.equals(existingVersion, MAVEN_SUREFIRE_PLUGIN_VERSION)) {
                                            report.updated(String.format("`maven-surefire-plugin` plugin has been updated from %s to %s", existingVersion, MAVEN_SUREFIRE_PLUGIN_VERSION));
                                        }
                                    },
                                    () -> {
                                        pluginManagement.addPlugin(newPlugin("org.apache.maven.plugins",
                                                "maven-surefire-plugin", "${maven-surefire-plugin.version}"));
                                        report.added("`maven-surefire-plugin` plugin has been added");
                                    });
            build.setPluginManagement(pluginManagement);
        }

        if (model.getPluginRepositories().removeIf(repository -> "bintray".equals(repository.getId()))) {
            report.removed("`bintray` plugin repository has been removed. All required plugins are now available on maven central.");
        }

        return report;
    }

    private void removeNode(String nodeName, Xpp3Dom configuration) {
        int index = -1;
        for(var i = 0; i < configuration.getChildCount(); i ++) {
           var node = configuration.getChild(i);
           if(nodeName.equals(node.getName())) {
               index = i;
               break;
           }
        }
        if(index != -1) {
            configuration.removeChild(index);
        }
    }

    private Dependency newDependency(String groupId, String artifactId, String version, String scope) {
        Dependency dependency = new Dependency();
        dependency.setGroupId(groupId);
        dependency.setArtifactId(artifactId);
        dependency.setVersion(version);
        dependency.setScope(scope);
        return dependency;
    }

    private Plugin newPlugin(String groupId, String artifactId, String version) {
        Plugin plugin = new Plugin();
        plugin.setGroupId(groupId);
        plugin.setArtifactId(artifactId);
        plugin.setVersion(version);
        return plugin;
    }

    private Optional<Dependency> findDependency(Collection<Dependency> dependencies, String groupId,
            String artifactId) {
        return dependencies.stream()
                .filter(d -> Objects.equals(groupId, d.getGroupId()))
                .filter(d -> Objects.equals(artifactId, d.getArtifactId()))
                .findFirst();
    }

    private Optional<Plugin> findPlugin(Collection<Plugin> plugins, String groupId, String artifactId) {
        return plugins.stream()
                .filter(p -> Objects.equals(groupId, p.getGroupId()))
                .filter(p -> Objects.equals(artifactId, p.getArtifactId()))
                .findFirst();
    }

    @Override
    public boolean appliesTo(Model model) {
        if (model.getBuild() != null) {
            String groovyVersion = model.getProperties().getProperty(GROOVY_VERSION_PROPERTY);
            String groovyAllVersion = model.getProperties().getProperty(GROOVY_ALL_VERSION_PROPERTY);
            // Already using Groovy 3
            if (groovyVersion != null && !groovyVersion.isBlank()
                    && _3_0_0.compareTo(new ComparableVersion(groovyVersion)) < 0) {
                return false;
            }
            if (groovyAllVersion != null && !groovyAllVersion.isBlank()
                    && _3_0_0.compareTo(new ComparableVersion(groovyAllVersion)) < 0) {
                return false;
            }

            // The project must use the groovy eclipse compiler
            return findPlugin(model.getBuild().getPlugins(), "org.apache.maven.plugins", "maven-compiler-plugin")
                    .map(Plugin::getConfiguration)
                    .map(Xpp3Dom.class::cast)
                    .map(configuration -> configuration.getChild("compilerId"))
                    .filter(Objects::nonNull)
                    .map(Xpp3Dom::getValue)
                    .map("groovy-eclipse-compiler"::equals)
                    .orElse(false);
        }
        return false;
    }

}
