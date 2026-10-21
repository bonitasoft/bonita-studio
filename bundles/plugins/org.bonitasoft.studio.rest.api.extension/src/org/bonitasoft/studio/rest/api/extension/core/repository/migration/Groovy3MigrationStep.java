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
import org.apache.maven.model.Exclusion;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginManagement;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.maven.model.DefaultPluginVersions;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.migration.MavenModelMigration;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.codehaus.plexus.util.xml.Xpp3Dom;

public class Groovy3MigrationStep implements MavenModelMigration {

    /** Derived, so the reference this step writes cannot drift from the property another step deletes. */
    private static final String GROOVY_ALL_VERSION_REFERENCE = "${" + DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY
            + "}";

    private static final String GROOVY_COMPILER_VERSION = "groovy-eclipse-batch.version";
    /**
     * Named the way the parent pom names it: one property per managed plugin, keyed on its artifactId. The value
     * is the parent's too, since a module that already has a parent keeps this pin - ParentMigrationStep only
     * drops it where it attaches one - and a surefire below 3 cannot run the Spock variant Groovy4MigrationStep
     * writes.
     */
    private static final String MAVEN_SUREFIRE_PLUGIN_VERSION_PROPERTY = DefaultPluginVersions.MAVEN_SUREFIRE_PLUGIN
            + ".version";
    private static final String MAVEN_SUREFIRE_PLUGIN_VERSION_REFERENCE = "${" + MAVEN_SUREFIRE_PLUGIN_VERSION_PROPERTY
            + "}";
    private static final String GROOVY_VERSION = "3.0.8";
    /**
     * The Spock variant built for Groovy 3, which is what the pom this step produces needs - and what a Groovy 4
     * runtime cannot run. Spock publishes one artifact per Groovy major, and the {@code -groovy-3.0} one pulls
     * Groovy 3 onto the test classpath transitively, so moving the Groovy coordinates without moving this pin
     * leaves a pom that compiles against Groovy 4 and fails its specifications at execution - a shape no build
     * log shows.
     * <p>
     * Package-private on purpose: Groovy4MigrationStep lists this among the pins it corrects, and a bump here
     * that did not reach that list would turn its correction into an Action required line asking the user to
     * fix a value this step had just written. The property it is stored under is shared for the same reason.
     */
    static final String SPOCK_VERSION = "2.0-groovy-3.0";

    /** The property this step writes {@link #SPOCK_VERSION} under, and Groovy4MigrationStep later removes. */
    static final String SPOCK_VERSION_PROPERTY = "spock.version";
    /** Derived like {@link #GROOVY_ALL_VERSION_REFERENCE}, for the same reason. */
    private static final String SPOCK_VERSION_REFERENCE = "${" + SPOCK_VERSION_PROPERTY + "}";
    /**
     * The coordinates this step pins, shared with Groovy4MigrationStep for the reason {@link #SPOCK_VERSION} gives.
     */
    static final String SPOCK_GROUP_ID = "org.spockframework";
    static final String SPOCK_CORE_ARTIFACT_ID = "spock-core";
    static final String SPOCK_REPORTS_GROUP_ID = "com.athaydes";
    static final String SPOCK_REPORTS_ARTIFACT_ID = "spock-reports";

    private static final ComparableVersion _3_0_0 = new ComparableVersion("3.0.0");

    @Override
    public MigrationReport migrate(Model model, ProjectMetadata metadata) {
        MigrationReport report = new MigrationReport();
        BonitaStudioLog.info(String.format("Starting %s on %s...", Groovy3MigrationStep.class.getName(), metadata.getArtifactId()));
        Properties properties = model.getProperties();

        properties.setProperty(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY, GROOVY_VERSION);
        properties.setProperty("maven-compiler-plugin.version", "3.8.1");
        properties.setProperty(MAVEN_SUREFIRE_PLUGIN_VERSION_PROPERTY,
                DefaultPluginVersions.MAVEN_SUREFIRE_PLUGIN_VERSION);
        properties.setProperty("groovy-eclipse-compiler.version", "3.7.0");
        properties.setProperty(GROOVY_COMPILER_VERSION, "3.0.8-01");
        properties.setProperty(SPOCK_VERSION_PROPERTY, SPOCK_VERSION);

        // read now, because the end of this method either removes this property or rewrites it: the
        // decision has to see the model this step leaves behind rather than the one it was given
        var existingGroovyVersion = properties.getOrDefault(DefaultPluginVersions.GROOVY_VERSION_PROPERTY, "2.4.x");

        report.updated(String.format(
                "Groovy version has been updated from `%s` to `%s`. Check the https://groovy-lang.org/releasenotes/groovy-3.0.html[release note] for more information about the breaking changes.",
                existingGroovyVersion, GROOVY_VERSION));

        findDependency(model.getDependencies(), DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID,
                DefaultPluginVersions.LEGACY_GROOVY_ALL_ARTIFACT_ID)
                .ifPresent(dependency -> {
                    dependency.setVersion(GROOVY_ALL_VERSION_REFERENCE);
                    dependency.setType("pom");
                    dependency.setScope(Artifact.SCOPE_PROVIDED);
                    var testNGExclusion = new Exclusion();
                    testNGExclusion.setGroupId(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID);
                    testNGExclusion.setArtifactId("groovy-testng");
                    dependency.getExclusions().add(testNGExclusion);
                });

        model.getDependencies().add(newDependency(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID,
                DefaultPluginVersions.GROOVY_DATEUTIL_ARTIFACT_ID,
                GROOVY_ALL_VERSION_REFERENCE,
                Artifact.SCOPE_PROVIDED));

        findDependency(model.getDependencies(), SPOCK_GROUP_ID, SPOCK_CORE_ARTIFACT_ID)
                .ifPresent(dependency -> {
                    var existingSpockVersion = dependency.getVersion();
                    dependency.setVersion(SPOCK_VERSION_REFERENCE);
                    report.updated(String.format(
                            "`spock-core` version has been updated from `%s` to `%s`. For more information check the https://spockframework.org/spock/docs/2.0/migration_guide.html#_migration_guide_2_0[migration guide].",
                            existingSpockVersion, SPOCK_VERSION));
                });

        findDependency(model.getDependencies(), SPOCK_REPORTS_GROUP_ID, SPOCK_REPORTS_ARTIFACT_ID)
                .ifPresent(dependency -> {
                    var existingSpockReportsVersion = dependency.getVersion();
                    dependency.setVersion(SPOCK_VERSION_REFERENCE);
                    report.updated(String.format("`spock-reports` version has been updated from `%s` to `%s`.",
                            existingSpockReportsVersion, SPOCK_VERSION));
                });

        Build build = model.getBuild();
        if (build != null) {
            findPlugin(model.getBuild().getPlugins(), "org.apache.maven.plugins", "maven-compiler-plugin")
                    .ifPresent(p -> {
                        p.setVersion("${maven-compiler-plugin.version}");
                        findDependency(p.getDependencies(), DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID,
                                DefaultPluginVersions.GROOVY_ECLIPSE_COMPILER_ARTIFACT_ID)
                                .ifPresent(d -> d.setVersion("${groovy-eclipse-compiler.version}"));
                        findDependency(p.getDependencies(), DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID,
                                DefaultPluginVersions.GROOVY_ECLIPSE_BATCH_ARTIFACT_ID)
                                .ifPresent(d -> d.setVersion("${groovy-eclipse-batch.version}"));
                    });

            PluginManagement pluginManagement = Optional.ofNullable(build.getPluginManagement())
                    .orElseGet(PluginManagement::new);
            findPlugin(pluginManagement.getPlugins(), DefaultPluginVersions.APACHE_MAVEN_PLUGIN_GROUP_ID,
                    DefaultPluginVersions.MAVEN_SUREFIRE_PLUGIN)
                            .ifPresentOrElse(
                                    surefirePlugin -> {
                                        var existingVersion = surefirePlugin.getVersion();
                                        surefirePlugin.setVersion(MAVEN_SUREFIRE_PLUGIN_VERSION_REFERENCE);
                                        if (!Objects.equals(existingVersion,
                                                DefaultPluginVersions.MAVEN_SUREFIRE_PLUGIN_VERSION)) {
                                            report.updated(String.format(
                                                    "`maven-surefire-plugin` plugin has been updated from %s to %s",
                                                    existingVersion,
                                                    DefaultPluginVersions.MAVEN_SUREFIRE_PLUGIN_VERSION));
                                        }
                                    },
                                    () -> {
                                        pluginManagement.addPlugin(
                                                newPlugin(DefaultPluginVersions.APACHE_MAVEN_PLUGIN_GROUP_ID,
                                                        DefaultPluginVersions.MAVEN_SUREFIRE_PLUGIN,
                                                        MAVEN_SUREFIRE_PLUGIN_VERSION_REFERENCE));
                                        report.updated(String.format(
                                                "`maven-surefire-plugin` plugin version has been fixed to `%s`",
                                                DefaultPluginVersions.MAVEN_SUREFIRE_PLUGIN_VERSION));
                                    });

            build.setPluginManagement(pluginManagement);
        }

        if (model.getPluginRepositories().removeIf(repository -> "bintray".equals(repository.getId()))) {
            report.removed(
                    "`bintray` plugin repository has been removed. All required plugins are now available on maven central.");
        }
        
        // last, and only when nothing still names it. This step rewrites some ${groovy.version} references
        // itself - groovy-eclipse-batch moves onto ${groovy-eclipse-batch.version} above - so a check made
        // before those rewrites would keep a property that is dead by the time the step returns. What can
        // survive is a reference this step does not own: a user pin such as org.codehaus.groovy:groovy-testng,
        // which Groovy4MigrationStep also declines to rewrite because the runtime does not provide it.
        // Removing the property under one of those leaves a version that resolves to nothing, which fails the
        // build rather than merely leaving a dead property behind
        if (!PomDeclarations.namesProperty(model, DefaultPluginVersions.GROOVY_VERSION_PROPERTY)) {
            properties.remove(DefaultPluginVersions.GROOVY_VERSION_PROPERTY);
        } else if (!PomDeclarations.namesPropertyOutsideDependencyVersions(model,
                DefaultPluginVersions.GROOVY_VERSION_PROPERTY)) {
            // every surviving reference is a dependency version, so the property holds a Groovy version and is
            // out of date - left as found it resolves the Groovy this pom was written against, under the line
            // reported above saying otherwise. replace rather than setProperty: a pom that only reads the
            // property takes its value from elsewhere, and defining one here would shadow that
            properties.replace(DefaultPluginVersions.GROOVY_VERSION_PROPERTY, GROOVY_VERSION);
        }

        BonitaStudioLog.info(String.format("%s completed for %s.", Groovy3MigrationStep.class.getName(), metadata.getArtifactId()));
        return report;
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
    public boolean appliesTo(Model model, ProjectMetadata metadata) {
        if (model.getBuild() != null) {
            String groovyVersion = model.getProperties().getProperty(DefaultPluginVersions.GROOVY_VERSION_PROPERTY);
            String groovyAllVersion = model.getProperties()
                    .getProperty(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY);
            String groovyCompilerVersion = model.getProperties().getProperty(GROOVY_COMPILER_VERSION);

            // Already using Groovy 3
            if (groovyVersion != null && !groovyVersion.isBlank()
                    && _3_0_0.compareTo(new ComparableVersion(groovyVersion)) < 0) {
                return false;
            }
            if (groovyAllVersion != null && !groovyAllVersion.isBlank()
                    && _3_0_0.compareTo(new ComparableVersion(groovyAllVersion)) < 0) {
                return false;
            }
            if (groovyCompilerVersion != null && !groovyCompilerVersion.isBlank()
                    && _3_0_0.compareTo(new ComparableVersion(groovyCompilerVersion)) < 0) {
                return false;
            }

            // The project must use the groovy eclipse compiler
            return findPlugin(model.getBuild().getPlugins(), "org.apache.maven.plugins", "maven-compiler-plugin")
                    .map(Plugin::getConfiguration)
                    .map(Xpp3Dom.class::cast)
                    .map(configuration -> configuration.getChild("compilerId"))
                    .filter(Objects::nonNull)
                    .map(Xpp3Dom::getValue)
                    .map(DefaultPluginVersions.GROOVY_ECLIPSE_COMPILER_ARTIFACT_ID::equals)
                    .orElse(false);
        }
        return false;
    }

}
