/**
 * Copyright (C) 2026 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.migration.step;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.common.repository.core.maven.model.DefaultPluginVersions;
import org.bonitasoft.studio.common.repository.core.migration.BonitaProjectMigrator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class GroovyCoordinatesMigrationStepTest {

    private static final String POM_TEMPLATE = """
            <?xml version="1.0" encoding="UTF-8"?>
            <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
              <modelVersion>4.0.0</modelVersion>
              <groupId>com.acme</groupId>
              <artifactId>legacy-project</artifactId>
              <version>1.0.0-SNAPSHOT</version>
              <dependencies>
            %s  </dependencies>
            </project>
            """;

    @TempDir
    Path project;

    private GroovyCoordinatesMigrationStep step;

    @BeforeEach
    void createStep() {
        step = new GroovyCoordinatesMigrationStep();
    }

    @Test
    void migrateTheLegacyGroovyAllDependency() throws Exception {
        writePom(codehausDependency("groovy-all", "pom"));

        assertThat(step.appliesToProject(project)).isTrue();
        var report = step.run(project, new NullProgressMonitor());

        assertMigratedToApacheGroovyModules();
        // the monolith brought modules transitively that the provided list does not include
        assertThat(report.updates())
                .anyMatch(message -> message.contains("groovy-all dependency has been replaced")
                        && message.contains("groovy-servlet"));
    }

    @Test
    void migrateAVersionPinnedGroovyAllDependency() throws Exception {
        // unlike a pinned module, a pinned monolith cannot mean "keep me": groovy-all cannot run
        // on the Groovy 4 runtime at all
        writePom("""
                    <dependency>
                      <groupId>org.codehaus.groovy</groupId>
                      <artifactId>groovy-all</artifactId>
                      <version>3.0.8</version>
                      <type>pom</type>
                    </dependency>
                """);

        assertThat(step.appliesToProject(project)).isTrue();
        step.run(project, new NullProgressMonitor());

        assertMigratedToApacheGroovyModules();
    }

    @Test
    void migrateTheLegacyGroovyModuleDependencies() throws Exception {
        writePom(codehausDependency("groovy", "jar")
                + codehausDependency("groovy-json", "jar")
                + codehausDependency("groovy-servlet", "jar"));

        assertThat(step.appliesToProject(project)).isTrue();
        var report = step.run(project, new NullProgressMonitor());

        assertMigratedToApacheGroovyModules();
        assertThat(report.updates())
                .anyMatch(message -> message.contains("org.apache.groovy"));
        assertThat(report.removals())
                .anyMatch(message -> message.contains("groovy-servlet")
                        && message.contains("no longer provided by the Bonita runtime"));
    }

    @Test
    void preserveUserAddedCodehausGroovyArtifacts() throws Exception {
        writePom(codehausDependency("groovy-all", "pom")
                + versionedCodehausDependency("groovy-swing", "3.0.8"));

        assertThat(step.appliesToProject(project)).isTrue();
        var report = step.run(project, new NullProgressMonitor());

        var model = step.loadMavenModel(project);
        assertThat(model.getDependencies())
                .anyMatch(dependency -> "org.codehaus.groovy".equals(dependency.getGroupId())
                        && "groovy-swing".equals(dependency.getArtifactId())
                        && "3.0.8".equals(dependency.getVersion()));
        assertThat(model.getDependencies())
                .filteredOn(dependency -> "org.apache.groovy".equals(dependency.getGroupId()))
                .isNotEmpty();
        assertThat(report.updates())
                .anyMatch(message -> message.contains("org.codehaus.groovy:groovy-swing")
                        && message.contains("review it manually"));
        // the preserved user artifact keeps the step applicable, for reporting only
        assertThat(step.appliesToProject(project)).isTrue();
    }

    @Test
    void preserveAVersionPinnedLegacyModule() throws Exception {
        writePom(codehausDependency("groovy-all", "pom")
                + versionedCodehausDependency("groovy-sql", "3.0.9"));

        var report = step.run(project, new NullProgressMonitor());

        var model = step.loadMavenModel(project);
        assertThat(model.getDependencies())
                .anyMatch(dependency -> "org.codehaus.groovy".equals(dependency.getGroupId())
                        && "groovy-sql".equals(dependency.getArtifactId())
                        && "3.0.9".equals(dependency.getVersion()));
        assertThat(report.updates())
                .anyMatch(message -> message.contains("org.codehaus.groovy:groovy-sql")
                        && message.contains("review it manually")
                        && message.contains("may conflict with the org.apache.groovy module"));
    }

    @Test
    void preserveACompileScopedLegacyModule() throws Exception {
        writePom(codehausDependency("groovy-all", "pom")
                + scopedCodehausDependency("groovy-templates", "compile"));

        var report = step.run(project, new NullProgressMonitor());

        var model = step.loadMavenModel(project);
        assertThat(model.getDependencies())
                .anyMatch(dependency -> "org.codehaus.groovy".equals(dependency.getGroupId())
                        && "groovy-templates".equals(dependency.getArtifactId())
                        && "compile".equals(dependency.getScope()));
        assertThat(report.updates())
                .anyMatch(message -> message.contains("org.codehaus.groovy:groovy-templates")
                        && message.contains("review it manually"));
    }

    @Test
    void doNotTouchAPomWithoutStudioWrittenLegacyGroovyDependencies() throws Exception {
        writePom(versionedCodehausDependency("groovy-eclipse-batch", "3.0.8"));
        var pomBeforeRun = Files.readString(project.resolve("pom.xml"));

        assertThat(step.appliesToProject(project)).isTrue();
        var report = step.run(project, new NullProgressMonitor());

        assertThat(Files.readString(project.resolve("pom.xml"))).isEqualTo(pomBeforeRun);
        assertThat(report.updates())
                .anyMatch(message -> message.contains("org.codehaus.groovy:groovy-eclipse-batch")
                        && message.contains("review it manually"));
        assertThat(report.hasRemovals()).isFalse();
    }

    @Test
    void runningTheMigrationTwiceIsIdempotent() throws Exception {
        writePom(codehausDependency("groovy-all", "pom"));

        step.run(project, new NullProgressMonitor());
        var pomAfterFirstRun = Files.readString(project.resolve("pom.xml"));

        var secondReport = step.run(project, new NullProgressMonitor());

        assertThat(Files.readString(project.resolve("pom.xml"))).isEqualTo(pomAfterFirstRun);
        assertThat(secondReport.isEmpty()).isTrue();
    }

    @Test
    void migrateAMixedGroovyAllAndModulesDependencyList() throws Exception {
        writePom(codehausDependency("groovy-all", "pom")
                + codehausDependency("groovy-xml", "jar"));

        assertThat(step.appliesToProject(project)).isTrue();
        step.run(project, new NullProgressMonitor());

        assertMigratedToApacheGroovyModules();
    }

    @Test
    void migrateTheAppModulePomOfAMultiModuleProject() throws Exception {
        // 8.0.0 and later projects are multi-module: the dependency list lives in app/pom.xml
        writeMultiModuleRootPom();
        writeAppPom(codehausDependency("groovy", "jar")
                + codehausDependency("groovy-json", "jar")
                + codehausDependency("groovy-servlet", "jar"));

        assertThat(step.appliesToProject(project)).isTrue();
        var report = step.run(project, new NullProgressMonitor());

        var appModel = step.loadMavenModel(project.resolve("app"));
        assertThat(appModel.getDependencies())
                .noneMatch(dependency -> "org.codehaus.groovy".equals(dependency.getGroupId()));
        assertThat(appModel.getDependencies())
                .filteredOn(dependency -> "org.apache.groovy".equals(dependency.getGroupId()))
                .isNotEmpty()
                .allMatch(dependency -> dependency.getVersion() == null)
                .noneMatch(dependency -> "groovy-servlet".equals(dependency.getArtifactId()));
        // the root pom of a multi-module project has no dependencies and stays untouched
        assertThat(step.loadMavenModel(project).getDependencies()).isEmpty();
        assertThat(report.hasRemovals()).isTrue();
        assertThat(step.appliesToProject(project)).isFalse();
    }

    @Test
    void removeLegacyEntriesFromAnAggregatorRootPomWithoutAddingTheProvidedModules() throws Exception {
        // an aggregator root pom generated by Studio declares no dependencies: the provided
        // modules belong to the app module, every other module would inherit them otherwise
        writeMultiModuleRootPom(codehausDependency("groovy-all", "pom"));
        writeAppPom(codehausDependency("groovy", "jar")
                + codehausDependency("groovy-json", "jar"));

        step.run(project, new NullProgressMonitor());

        var rootModel = step.loadMavenModel(project);
        assertThat(rootModel.getDependencies())
                .noneMatch(dependency -> "org.codehaus.groovy".equals(dependency.getGroupId()))
                .noneMatch(dependency -> "org.apache.groovy".equals(dependency.getGroupId()));
        var appModel = step.loadMavenModel(project.resolve("app"));
        assertThat(appModel.getDependencies())
                .noneMatch(dependency -> "org.codehaus.groovy".equals(dependency.getGroupId()));
        assertThat(appModel.getDependencies())
                .filteredOn(dependency -> "org.apache.groovy".equals(dependency.getGroupId()))
                .isNotEmpty();
        assertThat(step.appliesToProject(project)).isFalse();
    }

    @Test
    void declareTheProvidedModulesInTheAppPomWhenTheRootHoistedThem() throws Exception {
        // a user may have hoisted the studio-written list into the aggregator root, relying on
        // inheritance: the remove-only root treatment must not leave the project without groovy
        writeMultiModuleRootPom(codehausDependency("groovy", "jar")
                + codehausDependency("groovy-json", "jar"));
        writeAppPom("");

        var report = step.run(project, new NullProgressMonitor());

        assertThat(step.loadMavenModel(project).getDependencies()).isEmpty();
        assertThat(step.loadMavenModel(project.resolve("app")).getDependencies())
                .filteredOn(dependency -> "org.apache.groovy".equals(dependency.getGroupId()))
                .isNotEmpty();
        assertThat(report.updates())
                .anyMatch(message -> message.contains("have been declared in `app/pom.xml`"));
        assertThat(step.appliesToProject(project)).isFalse();
    }

    @Test
    void normalizeThePomsGeneratedByIntermediateBuilds() throws Exception {
        // interim 12.0 templates pinned the apache modules and declared a provided groovy-ant
        writePom("""
                    <dependency>
                      <groupId>org.apache.groovy</groupId>
                      <artifactId>groovy</artifactId>
                      <version>4.0.32</version>
                      <scope>provided</scope>
                    </dependency>
                    <dependency>
                      <groupId>org.apache.groovy</groupId>
                      <artifactId>groovy-ant</artifactId>
                      <scope>provided</scope>
                    </dependency>
                    <dependency>
                      <groupId>org.apache.groovy</groupId>
                      <artifactId>groovy-json</artifactId>
                      <version>4.0.32</version>
                      <scope>provided</scope>
                    </dependency>
                """);

        assertThat(step.appliesToProject(project)).isTrue();
        var report = step.run(project, new NullProgressMonitor());

        var model = step.loadMavenModel(project);
        assertThat(model.getDependencies())
                .noneMatch(dependency -> "groovy-ant".equals(dependency.getArtifactId()));
        // the pass also completes the interim-build list: the end state is the full provided
        // set, exactly as on the legacy-coordinates path
        assertThat(model.getDependencies())
                .filteredOn(dependency -> "org.apache.groovy".equals(dependency.getGroupId()))
                .extracting(Dependency::getArtifactId)
                .containsExactlyInAnyOrderElementsOf(DefaultPluginVersions.GROOVY_MODULES);
        assertThat(model.getDependencies())
                .filteredOn(dependency -> "org.apache.groovy".equals(dependency.getGroupId()))
                .allMatch(dependency -> dependency.getVersion() == null);
        assertThat(report.removals())
                .anyMatch(message -> message.contains("org.apache.groovy:groovy-ant"));
        assertThat(report.updates())
                .anyMatch(message -> message.contains("version pins on the provided org.apache.groovy modules"));
        // a second run has nothing left to do
        assertThat(step.appliesToProject(project)).isFalse();
    }

    @Test
    void keepAUserCompileScopedApacheGroovyAntDependency() throws Exception {
        // a compile dependency on Maven Central's groovy-ant is user intent, not an interim
        // template leftover
        writePom(codehausDependency("groovy-all", "pom") + """
                    <dependency>
                      <groupId>org.apache.groovy</groupId>
                      <artifactId>groovy-ant</artifactId>
                      <version>4.0.32</version>
                      <scope>compile</scope>
                    </dependency>
                """);

        step.run(project, new NullProgressMonitor());

        assertThat(step.loadMavenModel(project).getDependencies())
                .anyMatch(dependency -> "groovy-ant".equals(dependency.getArtifactId())
                        && "compile".equals(dependency.getScope())
                        && "4.0.32".equals(dependency.getVersion()));
    }

    @Test
    void warnAboutExtensionModulePoms() throws Exception {
        // extension poms have their own store migration, which still targets Groovy 3
        writePom(codehausDependency("groovy-all", "pom"));
        writeExtensionsAggregator("<modules><module>my-rest-api</module></modules>");

        var report = step.run(project, new NullProgressMonitor());

        assertThat(report.updates())
                .anyMatch(message -> message.contains("Action required")
                        && message.contains("extension module poms are migrated separately"));
    }

    @Test
    void warnAboutExtensionModulePomsEvenWhenNothingIsRewritten() throws Exception {
        // a fully version-pinned project is report-only, yet it is exactly the mixed-state shape
        // the extension warning is about
        writePom(versionedCodehausDependency("groovy", "3.0.8"));
        writeExtensionsAggregator("<modules><module>my-rest-api</module></modules>");

        var report = step.run(project, new NullProgressMonitor());

        assertThat(report.updates())
                .anyMatch(message -> message.contains("extension module poms are migrated separately"));
    }

    @Test
    void doNotWarnAboutAnEmptyExtensionsAggregator() throws Exception {
        // the 8.0.0 layout creates extensions/pom.xml unconditionally: a project that never had
        // an extension must not get a permanently-on 'Action required' line in every report
        writePom(codehausDependency("groovy-all", "pom"));
        writeExtensionsAggregator("");

        var report = step.run(project, new NullProgressMonitor());

        assertThat(report.updates())
                .noneMatch(message -> message.contains("extension module poms are migrated separately"));
    }

    private void writeExtensionsAggregator(String modules) throws Exception {
        Files.createDirectories(project.resolve("extensions"));
        Files.writeString(project.resolve("extensions").resolve("pom.xml"), "<project>" + modules + "</project>");
    }

    @Test
    void warnAboutLegacyExtensionProjectsAtTheProjectRoot() throws Exception {
        // a 7.x project carries its extensions as store folders at the root: the extensions
        // aggregator does not exist yet at this point of the migration (this step runs before
        // the multi-module split and the extensions-module step), and the 7.13-era population
        // is the one whose extension code is oldest
        writePom(codehausDependency("groovy-all", "pom"));
        Files.createDirectories(project.resolve("restAPIExtensions").resolve("my-rest-api"));
        Files.writeString(
                project.resolve("restAPIExtensions").resolve("my-rest-api").resolve("pom.xml"), "<project/>");

        var report = step.run(project, new NullProgressMonitor());

        assertThat(report.updates())
                .anyMatch(message -> message.contains("extension module poms are migrated separately"));
    }

    @Test
    void warnAboutLegacyExtensionProjectsUnderTheAppModule() throws Exception {
        // an 8.x project that predates the extensions module keeps them under app/
        writeMultiModuleRootPom(codehausDependency("groovy", "jar"));
        writeAppPom("");
        Files.createDirectories(project.resolve("app").resolve("themes").resolve("my-theme"));
        Files.writeString(
                project.resolve("app").resolve("themes").resolve("my-theme").resolve("pom.xml"), "<project/>");

        var report = step.run(project, new NullProgressMonitor());

        assertThat(report.updates())
                .anyMatch(message -> message.contains("extension module poms are migrated separately"));
    }

    @Test
    void surviveAnUnreadableExtensionsAggregator() throws Exception {
        // the probe is purely advisory and runs after the poms were rewritten on disk: an
        // unparsable aggregator must not fail the step and discard the whole report
        writePom(codehausDependency("groovy-all", "pom"));
        Files.createDirectories(project.resolve("extensions"));
        Files.writeString(project.resolve("extensions").resolve("pom.xml"), "<project><modul");

        var report = step.run(project, new NullProgressMonitor());

        assertMigratedToApacheGroovyModules();
        assertThat(report.updates()).isNotEmpty();
        assertThat(report.updates())
                .noneMatch(message -> message.contains("extension module poms are migrated separately"));
    }

    @Test
    void reportAProvidedApacheModuleDeclaredInAProfile() throws Exception {
        // profile dependencies reach the classpath whenever the profile is active: the
        // not-shipped-by-the-runtime net must see them like plain dependencies
        Files.writeString(project.resolve("pom.xml"), """
                <?xml version="1.0" encoding="UTF-8"?>
                <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
                  <modelVersion>4.0.0</modelVersion>
                  <groupId>com.acme</groupId>
                  <artifactId>legacy-project</artifactId>
                  <version>1.0.0-SNAPSHOT</version>
                  <dependencies>
                    <dependency>
                      <groupId>org.codehaus.groovy</groupId>
                      <artifactId>groovy-all</artifactId>
                      <version>3.0.8</version>
                      <type>pom</type>
                    </dependency>
                  </dependencies>
                  <profiles>
                    <profile>
                      <id>custom</id>
                      <dependencies>
                        <dependency>
                          <groupId>org.apache.groovy</groupId>
                          <artifactId>groovy-swing</artifactId>
                          <scope>provided</scope>
                        </dependency>
                      </dependencies>
                    </profile>
                  </profiles>
                </project>
                """);

        var report = step.run(project, new NullProgressMonitor());

        assertThat(report.updates())
                .anyMatch(message -> message.contains("org.apache.groovy:groovy-swing")
                        && message.contains("not provided by the Bonita runtime"));
    }

    @Test
    void reportAProvidedApacheModuleTheRuntimeDoesNotShip() throws Exception {
        // the apache-side counterpart of the codehaus manual-review net: whenever a module
        // leaves GROOVY_MODULES, a provided entry on it compiles and then fails with
        // NoClassDefFoundError at execution - kept (it may be user intent), but reported
        writePom(codehausDependency("groovy-all", "pom") + """
                    <dependency>
                      <groupId>org.apache.groovy</groupId>
                      <artifactId>groovy-swing</artifactId>
                      <scope>provided</scope>
                    </dependency>
                """);

        var report = step.run(project, new NullProgressMonitor());

        assertThat(step.loadMavenModel(project).getDependencies())
                .filteredOn(dependency -> "groovy-swing".equals(dependency.getArtifactId()))
                .isNotEmpty();
        assertThat(report.updates())
                .anyMatch(message -> message.contains("Action required")
                        && message.contains("org.apache.groovy:groovy-swing")
                        && message.contains("not provided by the Bonita runtime"));
    }

    @Test
    void runBeforeTheMultiModuleSplit() {
        // a pre-split 7.13 root pom must be rewritten in place BEFORE MultiModuleMigrationStep
        // copies its dependency list into app/pom.xml: reordering silently ships the legacy
        // coordinates into the app module
        var stepOrder = BonitaProjectMigrator.getAllSteps()
                .map(migrationStep -> migrationStep.getClass().getSimpleName())
                .toList();
        assertThat(stepOrder.indexOf("GroovyCoordinatesMigrationStep"))
                .isNotNegative()
                .isLessThan(stepOrder.indexOf("MultiModuleMigrationStep"));
    }

    @Test
    void declareTheProvidedModulesDespiteAForeignApacheGroovyEntry() throws Exception {
        // an unrelated org.apache.groovy artifact in the app pom must not suppress the fallback:
        // only a runtime-provided module counts as 'declared'
        writeMultiModuleRootPom(codehausDependency("groovy", "jar"));
        writeAppPom("""
                    <dependency>
                      <groupId>org.apache.groovy</groupId>
                      <artifactId>groovy-ant</artifactId>
                      <version>4.0.32</version>
                      <scope>compile</scope>
                    </dependency>
                """);

        var report = step.run(project, new NullProgressMonitor());

        assertThat(step.loadMavenModel(project.resolve("app")).getDependencies())
                .filteredOn(dependency -> "org.apache.groovy".equals(dependency.getGroupId())
                        && "groovy".equals(dependency.getArtifactId()))
                .isNotEmpty();
        assertThat(report.updates())
                .anyMatch(message -> message.contains("have been declared in `app/pom.xml`"));
    }

    @Test
    void completeAPartiallyDeclaredProvidedModuleList() throws Exception {
        // one declared module must not mark a whole hoisted root list as covered: the modules it
        // would hide are removed from the root and re-declared nowhere. The fallback reconciles
        // per artifact, adds only the missing modules and keeps the user entry untouched
        writeMultiModuleRootPom(codehausDependency("groovy", "jar")
                + codehausDependency("groovy-json", "jar"));
        writeAppPom("""
                    <dependency>
                      <groupId>org.apache.groovy</groupId>
                      <artifactId>groovy</artifactId>
                      <scope>provided</scope>
                    </dependency>
                """);

        var report = step.run(project, new NullProgressMonitor());

        assertThat(step.loadMavenModel(project.resolve("app")).getDependencies())
                .filteredOn(dependency -> "org.apache.groovy".equals(dependency.getGroupId()))
                .extracting(Dependency::getArtifactId)
                .doesNotHaveDuplicates()
                .containsExactlyInAnyOrderElementsOf(DefaultPluginVersions.GROOVY_MODULES);
        assertThat(report.updates())
                .anyMatch(message -> message.contains("have been declared in `app/pom.xml`")
                        && message.contains("groovy-json"));
        assertThat(step.appliesToProject(project)).isFalse();
    }

    @Test
    void neverDeclareTheProvidedModulesInAnAggregatorPom() throws Exception {
        // multi-module project whose modules do not include an app pom: the fallback must not
        // inject the provided list into the aggregator, where every sub-module would inherit it
        writeMultiModuleRootPom(codehausDependency("groovy", "jar"));

        var report = step.run(project, new NullProgressMonitor());

        assertThat(step.loadMavenModel(project).getDependencies())
                .noneMatch(dependency -> "org.apache.groovy".equals(dependency.getGroupId()))
                .noneMatch(dependency -> "org.codehaus.groovy".equals(dependency.getGroupId()));
        assertThat(report.updates())
                .anyMatch(message -> message.contains("Action required")
                        && message.contains("declare them in the module that needs them"));
    }

    @Test
    void reportTheSameEntryOnlyOnceAcrossRootAndAppPoms() throws Exception {
        // same pinned entry in the root dependencyManagement and the app dependencies: one notice
        Files.writeString(project.resolve("pom.xml"), """
                <?xml version="1.0" encoding="UTF-8"?>
                <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
                  <modelVersion>4.0.0</modelVersion>
                  <groupId>com.acme</groupId>
                  <artifactId>legacy-project</artifactId>
                  <version>1.0.0-SNAPSHOT</version>
                  <packaging>pom</packaging>
                  <modules>
                    <module>app</module>
                  </modules>
                  <dependencyManagement>
                    <dependencies>
                      <dependency>
                        <groupId>org.codehaus.groovy</groupId>
                        <artifactId>groovy</artifactId>
                        <version>3.0.8</version>
                      </dependency>
                    </dependencies>
                  </dependencyManagement>
                </project>
                """);
        writeAppPom(codehausDependency("groovy-json", "jar")
                + versionedCodehausDependency("groovy", "3.0.8"));

        var report = step.run(project, new NullProgressMonitor());

        // the app module is migrated, and the pinned entry is reported exactly once
        assertThat(step.loadMavenModel(project.resolve("app")).getDependencies())
                .filteredOn(dependency -> "org.apache.groovy".equals(dependency.getGroupId()))
                .isNotEmpty();
        assertThat(report.updates())
                .filteredOn(message -> message.contains("org.codehaus.groovy:groovy dependency was left unchanged"))
                .hasSize(1);
    }

    @Test
    void reportALegacyEntryDeclaredInAProfile() throws Exception {
        Files.writeString(project.resolve("pom.xml"), """
                <?xml version="1.0" encoding="UTF-8"?>
                <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
                  <modelVersion>4.0.0</modelVersion>
                  <groupId>com.acme</groupId>
                  <artifactId>legacy-project</artifactId>
                  <version>1.0.0-SNAPSHOT</version>
                  <profiles>
                    <profile>
                      <id>legacy</id>
                      <dependencies>
                        <dependency>
                          <groupId>org.codehaus.groovy</groupId>
                          <artifactId>groovy-sql</artifactId>
                          <version>3.0.8</version>
                        </dependency>
                      </dependencies>
                    </profile>
                  </profiles>
                </project>
                """);
        var pomBeforeRun = Files.readString(project.resolve("pom.xml"));

        assertThat(step.appliesToProject(project)).isTrue();
        var report = step.run(project, new NullProgressMonitor());

        assertThat(Files.readString(project.resolve("pom.xml"))).isEqualTo(pomBeforeRun);
        assertThat(report.updates())
                .anyMatch(message -> message.contains("Action required")
                        && message.contains("org.codehaus.groovy:groovy-sql"));
    }

    @Test
    void reportLegacyEntriesInDependencyManagementWithoutTouchingThem() throws Exception {
        Files.writeString(project.resolve("pom.xml"), """
                <?xml version="1.0" encoding="UTF-8"?>
                <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
                  <modelVersion>4.0.0</modelVersion>
                  <groupId>com.acme</groupId>
                  <artifactId>legacy-project</artifactId>
                  <version>1.0.0-SNAPSHOT</version>
                  <dependencyManagement>
                    <dependencies>
                      <dependency>
                        <groupId>org.codehaus.groovy</groupId>
                        <artifactId>groovy</artifactId>
                        <version>3.0.8</version>
                      </dependency>
                    </dependencies>
                  </dependencyManagement>
                </project>
                """);
        var pomBeforeRun = Files.readString(project.resolve("pom.xml"));

        assertThat(step.appliesToProject(project)).isTrue();
        var report = step.run(project, new NullProgressMonitor());

        assertThat(Files.readString(project.resolve("pom.xml"))).isEqualTo(pomBeforeRun);
        assertThat(report.updates())
                .anyMatch(message -> message.contains("Action required")
                        && message.contains("org.codehaus.groovy:groovy"));
    }

    @Test
    void reportAFullyVersionPinnedLegacyListWithoutTouchingThePom() throws Exception {
        // pinned versions resolve at build time but silently run Groovy 3 against the Groovy 4 runtime
        writePom(versionedCodehausDependency("groovy", "3.0.8")
                + versionedCodehausDependency("groovy-json", "3.0.8"));
        var pomBeforeRun = Files.readString(project.resolve("pom.xml"));

        assertThat(step.appliesToProject(project)).isTrue();
        var report = step.run(project, new NullProgressMonitor());

        assertThat(Files.readString(project.resolve("pom.xml"))).isEqualTo(pomBeforeRun);
        assertThat(report.updates())
                .anyMatch(message -> message.contains("org.codehaus.groovy:groovy-json")
                        && message.contains("review it manually"));
    }

    @Test
    void applyOnlyFromTheModularGroovyBaseline() {
        assertThat(step.appliesToVersion("7.12.0")).isFalse();
        assertThat(step.appliesToVersion("7.13.0")).isTrue();
    }

    private void assertMigratedToApacheGroovyModules() throws Exception {
        var model = step.loadMavenModel(project);
        assertThat(model.getDependencies())
                .noneMatch(dependency -> "org.codehaus.groovy".equals(dependency.getGroupId()));
        assertThat(model.getDependencies())
                .filteredOn(dependency -> "org.apache.groovy".equals(dependency.getGroupId()))
                .isNotEmpty()
                .allMatch(dependency -> dependency.getVersion() == null)
                .allMatch(dependency -> "provided".equals(dependency.getScope()))
                .anyMatch(dependency -> "groovy-json".equals(dependency.getArtifactId()))
                .noneMatch(dependency -> "groovy-ant".equals(dependency.getArtifactId()))
                .noneMatch(dependency -> "groovy-servlet".equals(dependency.getArtifactId()));
        // only the apache groovy slice of the provided dependencies is added: bonita-common must
        // not appear in a pom that did not declare it
        assertThat(model.getDependencies())
                .noneMatch(dependency -> "bonita-common".equals(dependency.getArtifactId())
                        || "bonita-common-sp".equals(dependency.getArtifactId()));
        // no duplicate module entries
        assertThat(model.getDependencies())
                .extracting(dependency -> dependency.getGroupId() + ":" + dependency.getArtifactId())
                .doesNotHaveDuplicates();

        assertThat(step.appliesToProject(project)).isFalse();
    }

    private void writePom(String dependencies) throws Exception {
        Files.writeString(project.resolve("pom.xml"), POM_TEMPLATE.formatted(dependencies));
    }

    private void writeMultiModuleRootPom() throws Exception {
        writeMultiModuleRootPom("");
    }

    private void writeMultiModuleRootPom(String dependencies) throws Exception {
        Files.writeString(project.resolve("pom.xml"), """
                <?xml version="1.0" encoding="UTF-8"?>
                <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
                  <modelVersion>4.0.0</modelVersion>
                  <groupId>com.acme</groupId>
                  <artifactId>legacy-project</artifactId>
                  <version>1.0.0-SNAPSHOT</version>
                  <packaging>pom</packaging>
                  <modules>
                    <module>app</module>
                  </modules>
                  <dependencies>
                %s  </dependencies>
                </project>
                """.formatted(dependencies));
    }

    private void writeAppPom(String dependencies) throws Exception {
        Files.createDirectories(project.resolve("app"));
        Files.writeString(project.resolve("app").resolve("pom.xml"), POM_TEMPLATE.formatted(dependencies));
    }

    private String codehausDependency(String artifactId, String type) {
        return """
                    <dependency>
                      <groupId>org.codehaus.groovy</groupId>
                      <artifactId>%s</artifactId>
                      <type>%s</type>
                      <scope>provided</scope>
                    </dependency>
                """.formatted(artifactId, type);
    }

    private String scopedCodehausDependency(String artifactId, String scope) {
        return """
                    <dependency>
                      <groupId>org.codehaus.groovy</groupId>
                      <artifactId>%s</artifactId>
                      <scope>%s</scope>
                    </dependency>
                """.formatted(artifactId, scope);
    }

    private String versionedCodehausDependency(String artifactId, String version) {
        return """
                    <dependency>
                      <groupId>org.codehaus.groovy</groupId>
                      <artifactId>%s</artifactId>
                      <version>%s</version>
                    </dependency>
                """.formatted(artifactId, version);
    }
}
