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

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.versioning.ComparableVersion;
import org.apache.maven.model.Build;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginManagement;
import org.bonitasoft.studio.rest.api.extension.core.repository.MavenModelMigration;
import org.codehaus.plexus.util.xml.Xpp3Dom;

public class Groovy3MigrationStep implements MavenModelMigration {

    private static final ComparableVersion _3_0_0 = new ComparableVersion("3.0.0");

    @Override
    public void migrate(Model model) {
        Properties properties = model.getProperties();
        properties.setProperty("groovy-all.version", "3.0.7");
        properties.setProperty("maven-compiler-plugin.version", "3.8.1");
        properties.setProperty("maven-surefire-plugin.version", "3.0.0-M5");
        properties.setProperty("java.version", "11");
        properties.setProperty("groovy-eclipse-compiler.version", "3.7.0");
        properties.setProperty("groovy-eclipse-batch.version", "3.0.7-03");
        properties.setProperty("spock.version", "2.0-M4-groovy-3.0");

        properties.remove("groovy.version");

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
                    dependency.setVersion("${spock.version}");
                });

        Build build = model.getBuild();
        if (build != null) {
            findPlugin(model.getBuild().getPlugins(), "org.apache.maven.plugins", "maven-compiler-plugin")
                    .ifPresent(p -> {
                        p.setVersion("${maven-compiler-plugin.version}");
                        Xpp3Dom configuration = (Xpp3Dom) p.getConfiguration();
                        Xpp3Dom source = configuration.getChild("source");
                        if (source != null) {
                            source.setValue("${java.version}");
                        }
                        Xpp3Dom target = configuration.getChild("target");
                        if (target != null) {
                            target.setValue("${java.version}");
                        }
                        findDependency(p.getDependencies(), "org.codehaus.groovy", "groovy-eclipse-compiler")
                                .ifPresent(d -> d.setVersion("${groovy-eclipse-compiler.version}"));
                        findDependency(p.getDependencies(), "org.codehaus.groovy", "groovy-eclipse-batch")
                                .ifPresent(d -> d.setVersion("${groovy-eclipse-batch.version}"));
                    });

            PluginManagement pluginManagement = Optional.ofNullable(build.getPluginManagement())
                    .orElseGet(PluginManagement::new);
            findPlugin(pluginManagement.getPlugins(), "org.apache.maven.plugins",
                    "maven-surefire-plugin")
                            .ifPresentOrElse(
                                    surefirePlugin -> surefirePlugin.setVersion("${maven-surefire-plugin.version}"),
                                    () -> {
                                        pluginManagement.addPlugin(newPlugin("org.apache.maven.plugins",
                                                "maven-surefire-plugin", "${maven-surefire-plugin.version}"));
                                    });
            build.setPluginManagement(pluginManagement);
        }

        model.getPluginRepositories().removeIf(repository -> "bintray".equals(repository.getId()));
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
            String groovyVersion = model.getProperties().getProperty("groovy.version");
            // Already using Groovy 3
            if (groovyVersion != null && !groovyVersion.isBlank()
                    && _3_0_0.compareTo(new ComparableVersion(groovyVersion)) < 0) {
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
