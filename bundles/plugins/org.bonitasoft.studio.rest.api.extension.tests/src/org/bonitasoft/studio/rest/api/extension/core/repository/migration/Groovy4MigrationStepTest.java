/**
 * Copyright (C) 2026 Bonitasoft S.A.
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.io.IOException;
import java.io.InputStream;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Build;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.Exclusion;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.Profile;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.bonitasoft.studio.common.repository.core.maven.model.DefaultPluginVersions;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.junit.Before;
import org.junit.Test;

public class Groovy4MigrationStepTest {

    private static final String GROOVY_4_RUNTIME_VERSION = "12.0.0";
    private static final String PRE_GROOVY_4_RUNTIME_VERSION = "10.2.0";
    /** The version reference Groovy3MigrationStep writes, which is what this step meets in practice. */
    private static final String GROOVY_3_VERSION_REFERENCE = "${groovy-all.version}";
    private static final String GROOVY_3_VERSION = "3.0.8";

    private MavenXpp3Reader modelReader = new MavenXpp3Reader();
    private Groovy4MigrationStep migrationStep;

    @Before
    public void createFixture() throws Exception {
        migrationStep = new Groovy4MigrationStep();
    }

    @Test
    public void should_apply_to_an_extension_declaring_the_legacy_groovy_coordinates() throws Exception {
        Model model = loadModel("pom_groovy3.xml");

        boolean appliesTo = migrationStep.appliesTo(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(appliesTo).isTrue();
    }

    @Test
    public void should_not_apply_to_an_extension_targeting_a_pre_groovy_4_runtime() throws Exception {
        // the same pom is correct against a 10.2 runtime, which still ships Groovy 3
        Model model = loadModel("pom_groovy3.xml");

        boolean appliesTo = migrationStep.appliesTo(model, metadataForRuntime(PRE_GROOVY_4_RUNTIME_VERSION));

        assertThat(appliesTo).isFalse();
    }

    @Test
    public void should_not_apply_to_an_extension_without_any_groovy_dependency() throws Exception {
        Model model = loadModel("pom_java.xml");

        boolean appliesTo = migrationStep.appliesTo(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(appliesTo).isFalse();
    }

    @Test
    public void should_move_the_groovy_modules_to_the_apache_coordinates() throws Exception {
        Model model = modelWithDependencies(
                groovyModule("groovy", GROOVY_3_VERSION_REFERENCE),
                groovyModule("groovy-json", GROOVY_3_VERSION_REFERENCE));

        migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        // no version: the runtime BOM manages the Groovy modules it provides, and a pin left behind
        // would silently override it
        assertThat(model.getDependencies())
                .extracting(Dependency::getGroupId, Dependency::getArtifactId, Dependency::getVersion,
                        Dependency::getScope)
                .containsExactly(
                        tuple(DefaultPluginVersions.APACHE_GROOVY_GROUPID, "groovy", null, Artifact.SCOPE_PROVIDED),
                        tuple(DefaultPluginVersions.APACHE_GROOVY_GROUPID, "groovy-json", null,
                                Artifact.SCOPE_PROVIDED));
    }

    @Test
    public void should_keep_the_declaration_order_when_rewriting() throws Exception {
        // the pom is a file a human reads: rewriting in place keeps the diff to the coordinates
        Model model = modelWithDependencies(
                newDependency("org.slf4j", "slf4j-api", null, Artifact.SCOPE_PROVIDED),
                groovyModule("groovy", GROOVY_3_VERSION_REFERENCE),
                newDependency("org.bonitasoft.web", "bonita-web-extensions", null, Artifact.SCOPE_PROVIDED));

        migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getDependencies())
                .extracting(Dependency::getGroupId, Dependency::getArtifactId)
                .containsExactly(
                        tuple("org.slf4j", "slf4j-api"),
                        tuple(DefaultPluginVersions.APACHE_GROOVY_GROUPID, "groovy"),
                        tuple("org.bonitasoft.web", "bonita-web-extensions"));
    }

    @Test
    public void should_drop_a_module_the_runtime_no_longer_provides() throws Exception {
        // groovy-servlet left the runtime with Groovy 4: kept, it resolves at build time from the BOM
        // and then is absent at execution
        Model model = modelWithDependencies(
                groovyModule("groovy", GROOVY_3_VERSION_REFERENCE),
                groovyModule("groovy-servlet", GROOVY_3_VERSION_REFERENCE));

        migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getDependencies())
                .extracting(Dependency::getArtifactId)
                .containsExactly("groovy");
    }

    @Test
    public void should_migrate_a_module_declared_without_a_version() throws Exception {
        // the shape the 1.7.x archetypes generated: versionless and provided, inherited from the BOM
        Model model = modelWithDependencies(groovyModule("groovy-json", null));

        migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getDependencies())
                .extracting(Dependency::getGroupId, Dependency::getArtifactId)
                .containsExactly(tuple(DefaultPluginVersions.APACHE_GROOVY_GROUPID, "groovy-json"));
    }

    @Test
    public void should_replace_groovy_all_with_the_modules_the_runtime_provides() throws Exception {
        // groovy-all put every module on the compile classpath, so narrowing it to the two the current
        // archetype generates would stop an extension using groovy-xml or groovy-sql from compiling
        Model model = modelWithDependencies(groovyAll());

        migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getDependencies())
                .extracting(Dependency::getGroupId, Dependency::getArtifactId, Dependency::getVersion,
                        Dependency::getScope)
                .containsExactlyElementsOf(DefaultPluginVersions.GROOVY_MODULES.stream()
                        .map(module -> tuple(DefaultPluginVersions.APACHE_GROOVY_GROUPID, module, null,
                                Artifact.SCOPE_PROVIDED))
                        .toList());
    }

    @Test
    public void should_not_declare_a_module_twice_when_replacing_groovy_all() throws Exception {
        // a pom can carry groovy-all next to a module Groovy3MigrationStep added
        Model model = modelWithDependencies(
                groovyAll(),
                groovyModule("groovy-dateutil", GROOVY_3_VERSION_REFERENCE));

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getDependencies())
                .extracting(Dependency::getGroupId, Dependency::getArtifactId)
                .doesNotHaveDuplicates()
                .contains(tuple(DefaultPluginVersions.APACHE_GROOVY_GROUPID, "groovy-dateutil"))
                .doesNotContain(tuple(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, "groovy-dateutil"));
        // and the sentence that claims to list what the extension now has must name it. The module is still
        // on the codehaus coordinates while groovy-all is replaced and only moves later in the same run, so
        // a list built at replacement time silently omits exactly the module this pom is here to exercise
        assertThat(report.updates())
                .filteredOn(message -> message.contains("declares the Groovy modules"))
                .singleElement().asString()
                .contains("groovy-dateutil");
    }

    @Test
    public void should_report_the_modules_groovy_all_no_longer_brings() throws Exception {
        Model model = modelWithDependencies(groovyAll());

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates()).anyMatch(message -> message.contains("groovy-servlet"));
    }

    @Test
    public void should_report_without_rewriting_a_module_pinned_to_a_literal_version() throws Exception {
        // a literal version is user intent: rewriting it would drop a pin the user chose on purpose
        Model model = modelWithDependencies(groovyModule("groovy-json", "3.0.19"));

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getDependencies())
                .extracting(Dependency::getGroupId, Dependency::getVersion)
                .containsExactly(tuple(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, "3.0.19"));
        assertThat(report.updates()).anyMatch(message -> message.startsWith("Action required:")
                && message.contains("`<dependencies>`"));
    }

    @Test
    public void should_report_without_rewriting_a_module_declared_with_the_compile_scope() throws Exception {
        Model model = modelWithDependencies(
                newDependency(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, "groovy", null, null));
        model.getDependencies().get(0).setScope("compile");

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getDependencies())
                .extracting(Dependency::getGroupId)
                .containsExactly(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID);
        assertThat(report.updates()).anyMatch(message -> message.startsWith("Action required:"));
    }

    @Test
    public void should_not_tell_a_managed_entry_the_build_cannot_resolve_it() throws Exception {
        // a dependencyManagement entry reaches no classpath by itself, so there is nothing for it to fail to
        // resolve: the versionless sentence belongs to a declaration. A managed entry also needs a version of
        // its own whatever the runtime manages, so blaming the 12.0 BOM names the wrong cause
        Model model = new Model();
        var dependencyManagement = new DependencyManagement();
        dependencyManagement.addDependency(groovyModule("groovy", null));
        model.setDependencyManagement(dependencyManagement);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .anyMatch(message -> message.startsWith("Action required:")
                        && message.contains("Review it manually."))
                .noneMatch(message -> message.contains("the build cannot resolve it"));
    }

    @Test
    public void should_report_without_rewriting_a_managed_legacy_dependency() throws Exception {
        Model model = new Model();
        var dependencyManagement = new DependencyManagement();
        dependencyManagement.addDependency(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE));
        model.setDependencyManagement(dependencyManagement);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getDependencyManagement().getDependencies())
                .extracting(Dependency::getGroupId)
                .containsExactly(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID);
        assertThat(report.updates()).anyMatch(message -> message.startsWith("Action required:"));
    }

    @Test
    public void should_report_without_rewriting_a_legacy_dependency_declared_by_a_profile() throws Exception {
        // a profile can hold the deliberate variant of an extension still built against an older runtime
        Model model = new Model();
        var profile = new Profile();
        profile.setId("bonita-10");
        profile.getDependencies().add(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE));
        model.getProfiles().add(profile);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getProfiles().get(0).getDependencies())
                .extracting(Dependency::getGroupId)
                .containsExactly(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID);
        assertThat(report.updates()).anyMatch(message -> message.startsWith("Action required:"));
    }

    @Test
    public void should_migrate_a_real_groovy_3_extension_pom() throws Exception {
        // every other test builds its model by hand: this one goes through a pom as a customer has it
        Model model = loadModel("pom_groovy3.xml");

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getDependencies())
                .extracting(Dependency::getGroupId, Dependency::getArtifactId, Dependency::getVersion,
                        Dependency::getScope)
                .containsExactly(
                        tuple("org.bonitasoft.web", "bonita-web-extensions", "${bonita.version}",
                                Artifact.SCOPE_PROVIDED),
                        tuple(DefaultPluginVersions.APACHE_GROOVY_GROUPID, "groovy", null, Artifact.SCOPE_PROVIDED),
                        tuple(DefaultPluginVersions.APACHE_GROOVY_GROUPID, "groovy-json", null,
                                Artifact.SCOPE_PROVIDED),
                        tuple("org.slf4j", "slf4j-api", "${slf4j-api.version}", Artifact.SCOPE_PROVIDED),
                        tuple("org.spockframework", "spock-core", "${spock-core.version}", Artifact.SCOPE_TEST),
                        tuple("net.bytebuddy", "byte-buddy", "${byte-buddy.version}", Artifact.SCOPE_TEST),
                        tuple("ch.qos.logback", "logback-classic", "${logback-classic.version}",
                                Artifact.SCOPE_TEST));
        // a migration that leaves nothing behind must not ask the user to review anything. Read as a
        // statement about the Groovy coordinates only: Groovy3MigrationStep pins spock-core to a Groovy 3
        // variant in the same run and nothing here touches it, because picking the matching Groovy 4 release
        // is a per-artifact question this step has no table for.
        // Both lists: the marker can land in either, and updates() alone cannot see a removal that asks for
        // something
        assertThat(report.updates()).noneMatch(message -> message.startsWith("Action required:"));
        assertThat(report.removals()).noneMatch(message -> message.startsWith("Action required:"));
    }

    @Test
    public void should_leave_the_groovy_compiler_plugin_dependencies_to_the_compiler_step() throws Exception {
        // groovy-eclipse-compiler and groovy-eclipse-batch are declared under build/plugins, not among the
        // model dependencies, and GroovyCompilerPluginStep drops the whole maven-compiler-plugin later in
        // the pipeline. Rewriting them here would be work undone a moment later
        Model model = loadModel("pom_groovy3.xml");

        migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getBuild().getPlugins())
                .filteredOn(plugin -> "maven-compiler-plugin".equals(plugin.getArtifactId()))
                .flatExtracting(Plugin::getDependencies)
                .extracting(Dependency::getGroupId, Dependency::getArtifactId)
                .containsExactly(
                        tuple(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, "groovy-eclipse-compiler"),
                        tuple(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, "groovy-eclipse-batch"));
    }

    @Test
    public void should_drop_the_legacy_module_when_the_apache_one_is_already_declared() throws Exception {
        // hand-patched for Groovy 4 with the generated entry left next to the new one: rewriting it would
        // declare the same module twice, which Maven warns about and m2e marks on the pom
        Model model = modelWithDependencies(
                newDependency(DefaultPluginVersions.APACHE_GROOVY_GROUPID, "groovy-json", null,
                        Artifact.SCOPE_PROVIDED),
                groovyModule("groovy-json", GROOVY_3_VERSION_REFERENCE));

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getDependencies())
                .extracting(Dependency::getGroupId, Dependency::getArtifactId)
                .containsExactly(tuple(DefaultPluginVersions.APACHE_GROOVY_GROUPID, "groovy-json"));
        assertThat(report.removals()).anyMatch(message -> message.contains("already declares"));
    }

    @Test
    public void should_point_at_the_apache_coordinates_when_reporting_a_dropped_module() throws Exception {
        // a user following this instruction with the legacy groupId would put a Groovy 3 artifact back on
        // a Groovy 4 runtime - the mixture this step exists to prevent
        Model model = modelWithDependencies(groovyModule("groovy-servlet", GROOVY_3_VERSION_REFERENCE));

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        // the split this step now keeps: what happened is a removal, what to do about it is an update, so a
        // reader scanning either section sees the half that section is for
        assertThat(report.updates())
                .anyMatch(message -> message.contains("Declare `" + DefaultPluginVersions.APACHE_GROOVY_GROUPID
                        + ":groovy-servlet` explicitly"));
        assertThat(report.removals())
                .anyMatch(message -> message.contains("groovy-servlet` dependency has been removed")
                        && !message.contains("Action required"));
    }

    @Test
    public void should_keep_the_scope_groovy_all_carried_when_replacing_it() throws Exception {
        // a groovy-all declared for the Spock tests must not put Groovy on the main compile classpath.
        // A generated module is present so the mixed case is actually exercised: the rewrite loop forces
        // provided on that one, so the pom ends with the two scopes it already had - the split is the
        // user's, preserved, not one this step invents. containsOnly on a groovy-all-only model would pass
        // without ever reaching that
        var groovyAll = groovyAll();
        groovyAll.setScope(Artifact.SCOPE_TEST);
        Model model = modelWithDependencies(groovyAll, groovyModule("groovy-json", GROOVY_3_VERSION_REFERENCE));

        migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getDependencies())
                .filteredOn(dependency -> !"groovy-json".equals(dependency.getArtifactId()))
                .extracting(Dependency::getScope)
                .containsOnly(Artifact.SCOPE_TEST);
        assertThat(model.getDependencies())
                .filteredOn(dependency -> "groovy-json".equals(dependency.getArtifactId()))
                .singleElement()
                .extracting(Dependency::getScope)
                .isEqualTo(Artifact.SCOPE_PROVIDED);
    }

    @Test
    public void should_not_apply_to_a_pom_whose_only_legacy_coordinate_is_groovy_tooling() throws Exception {
        // groovy-eclipse-compiler and groovy-eclipse-batch keep the org.codehaus.groovy groupId on
        // purpose - that project never moved coordinates - so there is nothing to migrate and nothing
        // to tell the user about
        Model model = modelWithDependencies(
                groovyModule("groovy-eclipse-compiler", "3.9.0"),
                groovyModule("groovy-eclipse-batch", "3.0.19-01"));

        boolean appliesTo = migrationStep.appliesTo(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(appliesTo).isFalse();
    }

    @Test
    public void should_not_ask_for_a_review_of_groovy_tooling_coordinates() throws Exception {
        // migrating alongside a real legacy module: the tooling artifact must stay out of the report,
        // since advising a move to org.apache.groovy points at something that is not published there
        Model model = modelWithDependencies(
                groovyModule("groovy", GROOVY_3_VERSION_REFERENCE),
                groovyModule("groovy-eclipse-batch", "3.0.19-01"));

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates()).noneMatch(message -> message.contains("groovy-eclipse-batch"));
        assertThat(model.getDependencies())
                .extracting(Dependency::getGroupId, Dependency::getArtifactId)
                .containsExactly(
                        tuple(DefaultPluginVersions.APACHE_GROOVY_GROUPID, "groovy"),
                        tuple(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, "groovy-eclipse-batch"));
    }

    @Test
    public void should_not_rewrite_a_module_carrying_a_classifier() throws Exception {
        // the old indy variant: org.apache.groovy publishes no such classifier, so rewriting the
        // coordinates would produce a dependency that cannot resolve at all
        var indy = groovyModule("groovy", GROOVY_3_VERSION_REFERENCE);
        indy.setClassifier("indy");
        Model model = modelWithDependencies(indy);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getDependencies())
                .extracting(Dependency::getGroupId, Dependency::getClassifier)
                .containsExactly(tuple(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, "indy"));
        assertThat(report.updates()).anyMatch(message -> message.startsWith("Action required:"));
    }

    @Test
    public void should_report_a_module_that_moved_but_the_runtime_does_not_provide() throws Exception {
        // Groovy3MigrationStep excludes groovy-testng from groovy-all, so anyone who needed it declared it
        // by hand. It moved to org.apache.groovy like every other module, so staying silent would leave a
        // Groovy 3 artifact on a Groovy 4 runtime under a report claiming the extension was migrated
        Model model = modelWithDependencies(groovyModule("groovy-testng", GROOVY_3_VERSION_REFERENCE));

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .anyMatch(message -> message.startsWith("Action required:") && message.contains("groovy-testng"));
    }

    @Test
    public void should_apply_to_a_module_that_moved_but_the_runtime_does_not_provide() throws Exception {
        Model model = modelWithDependencies(groovyModule("groovy-swing", GROOVY_3_VERSION_REFERENCE));

        boolean appliesTo = migrationStep.appliesTo(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(appliesTo).isTrue();
    }

    @Test
    public void should_replace_groovy_all_where_the_pom_declared_it() throws Exception {
        // every other groovy-all test puts it first, so the position arithmetic in replaceGroovyAll -
        // index before removal, then addAll(index, ...) - is only ever exercised at zero. An off-by-one
        // there is silent: the modules simply land in the wrong place
        Model model = loadModel("pom_from_1_3_1.xml");

        migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        var artifactIds = model.getDependencies().stream().map(Dependency::getArtifactId).toList();
        assertThat(artifactIds).startsWith("bonita-web-extensions");
        assertThat(artifactIds).endsWith("slf4j-api", "spock-core", "byte-buddy", "logback-classic");
        assertThat(artifactIds.subList(1, artifactIds.size() - 4))
                .containsExactlyElementsOf(DefaultPluginVersions.GROOVY_MODULES);
    }

    @Test
    public void should_remove_every_groovy_all_declaration() throws Exception {
        // removeIf runs over the whole list, not just the one indexOfDeclaration found
        var second = groovyAll();
        second.setScope(Artifact.SCOPE_PROVIDED);
        Model model = modelWithDependencies(groovyAll(), second);

        migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getDependencies())
                .extracting(Dependency::getGroupId, Dependency::getArtifactId)
                .doesNotContain(tuple(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID,
                        DefaultPluginVersions.LEGACY_GROOVY_ALL_ARTIFACT_ID))
                .containsExactlyElementsOf(DefaultPluginVersions.GROOVY_MODULES.stream()
                        .map(module -> tuple(DefaultPluginVersions.APACHE_GROOVY_GROUPID, module))
                        .toList());
    }

    @Test
    public void should_migrate_the_shape_groovy_3_migration_hands_over() throws Exception {
        // the one shape production always produces and no test covered: Groovy3MigrationStep first, then
        // this step. It hands over three non-obvious things - type=pom on groovy-all, a groovy-testng
        // exclusion, and an unconditionally appended groovy-dateutil
        Model model = loadModel("pom_groovy3.xml");
        var metadata = metadataForRuntime(GROOVY_4_RUNTIME_VERSION);
        new Groovy3MigrationStep().migrate(model, metadata);

        var report = migrationStep.migrate(model, metadata);

        assertThat(model.getDependencies())
                .filteredOn(dependency -> dependency.getArtifactId().startsWith("groovy"))
                .extracting(Dependency::getGroupId, Dependency::getVersion)
                .containsOnly(tuple(DefaultPluginVersions.APACHE_GROOVY_GROUPID, null));
        // about the Groovy coordinates, not the whole pom: Groovy3MigrationStep also pins spock-core to a
        // Groovy 3 variant in the same run, and this step leaves it alone - which release of Spock matches
        // Groovy 4 is a per-artifact question, not a coordinate move. Both lists, as above
        assertThat(report.updates()).noneMatch(message -> message.startsWith("Action required:"));
        assertThat(report.removals()).noneMatch(message -> message.startsWith("Action required:"));
    }

    @Test
    public void should_point_at_a_groovy_version_property_nothing_references_any_more() throws Exception {
        // the property fed the version reference this step just removed: left behind, a Groovy 3 number
        // sits in a pom whose notes say the runtime ships Groovy 4
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE));
        model.getProperties().setProperty(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY, GROOVY_3_VERSION);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        // removed, not reported: referencedAnywhere covers dependencies, plugins and other properties, so
        // this is a fact rather than a guess
        assertThat(report.removals()).anyMatch(message -> message.contains(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY));
        assertThat(model.getProperties()).doesNotContainKey(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY);
    }

    @Test
    public void should_stay_quiet_about_a_groovy_version_property_still_in_use() throws Exception {
        // a dependency this step declined to rewrite still reads it, so the property is not orphaned
        var pinned = groovyModule("groovy-testng", "${groovy-all.version}");
        pinned.setScope("test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), pinned);
        model.getProperties().setProperty(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY, GROOVY_3_VERSION);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getProperties()).containsEntry(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY, GROOVY_3_VERSION);
        assertThat(report.removals()).noneMatch(message -> message.contains(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY));
    }

    @Test
    public void should_flag_the_scope_when_groovy_all_was_not_provided() throws Exception {
        // preserving the scope is right, but on compile it is now carried by eleven modules that the
        // assembly packages beside the runtime's own Groovy
        var groovyAll = groovyAll();
        groovyAll.setScope("compile");
        Model model = modelWithDependencies(groovyAll);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .anyMatch(message -> message.contains("carry the `compile` scope")
                        && message.contains("consider the provided scope"));
    }

    @Test
    public void should_point_at_a_groovy_version_property_orphaned_by_replacing_groovy_all() throws Exception {
        // the monolith was the property's only reader, and it is gone
        Model model = modelWithDependencies(groovyAll());
        model.getProperties().setProperty(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY, GROOVY_3_VERSION);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getProperties()).doesNotContainKey(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY);
        // the name says "point at", so the report line is half of what this test is for
        assertThat(report.removals()).anyMatch(message -> message.contains(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY));
    }

    @Test
    public void should_remove_the_orphaned_property_when_the_only_module_was_removed() throws Exception {
        // the cleanup must not depend on the rewrite path having produced anything: a pom whose only Groovy
        // entry is removed rather than rewritten would otherwise keep a dead property, which is the outcome
        // the cleanup exists to prevent
        Model model = modelWithDependencies(groovyModule("groovy-servlet", GROOVY_3_VERSION_REFERENCE));
        model.getProperties().setProperty(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY, GROOVY_3_VERSION);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getDependencies()).isEmpty();
        assertThat(model.getProperties()).doesNotContainKey(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY);
        assertThat(report.removals()).anyMatch(message -> message.contains(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY));
    }

    @Test
    public void should_remove_the_orphaned_property_when_the_apache_module_was_already_declared()
            throws Exception {
        // the other removal path: the generated entry is dropped as a duplicate rather than rewritten, and
        // it was the only thing naming the property
        Model model = modelWithDependencies(
                groovyModule("groovy", GROOVY_3_VERSION_REFERENCE),
                newDependency(DefaultPluginVersions.APACHE_GROOVY_GROUPID, "groovy", null,
                        Artifact.SCOPE_PROVIDED));
        model.getProperties().setProperty(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY, GROOVY_3_VERSION);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getProperties()).doesNotContainKey(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY);
        assertThat(report.removals()).anyMatch(message -> message.contains(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY));
    }

    @Test
    public void should_name_modules_groovy_all_actually_brought() throws Exception {
        // what the monolith pulled in transitively, which is a fact about how Groovy packaged groovy-all. The
        // modules Studio wrote into poms are a different set - it holds one name, groovy-servlet, and reads as
        // an enumeration of the monolith's contents only because a hedge follows it
        Model model = modelWithDependencies(groovyAll());

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .anyMatch(message -> message.contains("groovy-servlet") && message.contains("groovy-ant")
                        && message.contains("groovy-swing") && message.contains("groovy-console"));
    }

    @Test
    public void should_not_name_a_provided_module_among_the_ones_groovy_all_brought() throws Exception {
        // the enumeration is a fixed list rather than a derived one, so it has to be filtered against
        // GROOVY_MODULES: without that it would name a module the runtime provides the day the runtime bundle
        // gains one of them. Not a pre-existing regression guard - it reads the message this change introduces
        Model model = modelWithDependencies(groovyAll());

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        var advisory = report.updates().stream()
                .filter(message -> message.contains("brought other Groovy modules"))
                .findFirst()
                .orElseThrow();
        // "groovy" excluded because every module name contains it, so a substring test says nothing there
        assertThat(DefaultPluginVersions.GROOVY_MODULES)
                .filteredOn(module -> !"groovy".equals(module))
                .noneMatch(advisory::contains);
    }

    @Test
    public void should_keep_a_groovy_version_property_a_plugin_still_names() throws Exception {
        // the reference check has to cover build/plugins too, since the property is now removed on the
        // strength of its answer rather than merely reported
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE));
        model.getProperties().setProperty(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY, GROOVY_3_VERSION);
        var build = new Build();
        var plugin = new Plugin();
        plugin.setGroupId(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID);
        plugin.setArtifactId("groovy-eclipse-compiler");
        plugin.setVersion("${groovy-all.version}");
        build.getPlugins().add(plugin);
        model.setBuild(build);

        migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getProperties()).containsEntry(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY, GROOVY_3_VERSION);
    }

    @Test
    public void should_keep_a_property_a_composite_version_still_names() throws Exception {
        // ${groovy.version}-01 is not a bare reference, so the loop leaves the module alone - and matching
        // the property by equality rather than containment would then delete what that module still reads
        Model model = modelWithDependencies(
                groovyModule("groovy", "${groovy.version}"),
                groovyModule("groovy-sql", "${groovy.version}-01"));
        model.getProperties().setProperty(DefaultPluginVersions.GROOVY_VERSION_PROPERTY, "3.0.19");

        migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getProperties()).containsEntry(DefaultPluginVersions.GROOVY_VERSION_PROPERTY, "3.0.19");
    }

    @Test
    public void should_not_add_a_module_whose_name_a_codehaus_pin_already_holds() throws Exception {
        // the tradeoff this step takes deliberately: a literal pin is user intent, so the apache module of
        // the same name is withheld rather than declared beside it - two jars exporting groovy.sql would
        // leave which class wins to resolution order. The pin is reported for the user to remove
        Model model = modelWithDependencies(
                groovyAll(),
                groovyModule("groovy-sql", "3.0.19"));

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getDependencies())
                .extracting(Dependency::getGroupId, Dependency::getArtifactId)
                .contains(tuple(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, "groovy-sql"))
                .doesNotContain(tuple(DefaultPluginVersions.APACHE_GROOVY_GROUPID, "groovy-sql"));
        assertThat(report.updates())
                .anyMatch(message -> message.startsWith("Action required:") && message.contains("groovy-sql"));
        // and the replacement line must not claim the runtime provides the module it just declined to add:
        // one sentence saying groovy-sql is provided and another saying it was left for review contradict
        assertThat(report.updates())
                .filteredOn(message -> message.contains("declares the Groovy modules"))
                .singleElement().asString()
                .doesNotContain("groovy-sql");
    }

    @Test
    public void should_leave_a_groovy_all_declared_only_in_dependency_management_to_the_report() throws Exception {
        // replaceGroovyAll scans the dependencies, so a managed-only monolith falls through to the net
        Model model = new Model();
        var dependencyManagement = new DependencyManagement();
        dependencyManagement.addDependency(groovyAll());
        model.setDependencyManagement(dependencyManagement);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getDependencyManagement().getDependencies())
                .extracting(Dependency::getGroupId)
                .containsExactly(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID);
        assertThat(report.updates()).anyMatch(message -> message.startsWith("Action required:"));
    }

    @Test
    public void should_be_idempotent_over_a_second_run() throws Exception {
        // a migration has to survive being re-run: the second pass must find nothing left to do
        Model model = loadModel("pom_groovy3.xml");
        var metadata = metadataForRuntime(GROOVY_4_RUNTIME_VERSION);
        migrationStep.migrate(model, metadata);
        var afterFirst = model.getDependencies().stream()
                .map(dependency -> dependency.getGroupId() + ":" + dependency.getArtifactId())
                .toList();

        var appliesAgain = migrationStep.appliesTo(model, metadata);
        migrationStep.migrate(model, metadata);

        assertThat(appliesAgain).isFalse();
        assertThat(model.getDependencies())
                .extracting(dependency -> dependency.getGroupId() + ":" + dependency.getArtifactId())
                .containsExactlyElementsOf(afterFirst);
    }

    @Test
    public void should_not_claim_a_scope_was_carried_when_nothing_was_added() throws Exception {
        // every module already declared, so nothing was added: there are no modules to say it about
        var groovyAll = groovyAll();
        groovyAll.setScope("test");
        Model model = new Model();
        model.getDependencies().add(groovyAll);
        DefaultPluginVersions.GROOVY_MODULES
                .forEach(module -> model.getDependencies().add(groovyModule(module, GROOVY_3_VERSION_REFERENCE)));

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates()).noneMatch(message -> message.contains("kept the `test` scope"));
    }

    @Test
    public void should_remove_a_property_named_only_by_another_property_it_also_removes() throws Exception {
        // groovy.version names groovy-all.version: a single pass keeps the second alive on the strength of
        // a value it is about to delete, and then reports a cleanup it only half did
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE));
        model.getProperties().setProperty(DefaultPluginVersions.GROOVY_VERSION_PROPERTY, GROOVY_3_VERSION_REFERENCE);
        model.getProperties().setProperty(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY, GROOVY_3_VERSION);

        migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getProperties()).doesNotContainKeys(DefaultPluginVersions.GROOVY_VERSION_PROPERTY, DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY);
    }

    @Test
    public void should_say_the_runtime_module_is_absent_when_a_pin_kept_it_out() throws Exception {
        // the costly path: the apache module is withheld because the pin holds its name, so telling the user
        // it "may conflict with the module provided by the runtime" names a dependency that is not in the pom.
        // What they actually have is one Groovy 3 jar and no runtime core - it resolves, then fails at execution
        Model model = modelWithDependencies(
                groovyAll(),
                groovyModule("groovy", "3.0.19"));

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getDependencies())
                .extracting(Dependency::getGroupId, Dependency::getArtifactId)
                .doesNotContain(tuple(DefaultPluginVersions.APACHE_GROOVY_GROUPID, "groovy"));
        assertThat(report.updates())
                .filteredOn(message -> message.startsWith("Action required:") && message.contains(":groovy`"))
                .singleElement().asString()
                .contains("was not declared alongside it")
                .contains("runs Groovy 3 against a Groovy 4 runtime")
                .doesNotContain("may conflict");
    }

    @Test
    public void should_say_it_may_conflict_when_both_coordinates_are_declared() throws Exception {
        // the other outcome: the pom declares the apache module itself, so there really are two of them
        Model model = modelWithDependencies(
                groovyModule("groovy", "3.0.19"),
                newDependency(DefaultPluginVersions.APACHE_GROOVY_GROUPID, "groovy", null,
                        Artifact.SCOPE_PROVIDED));

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .filteredOn(message -> message.startsWith("Action required:") && message.contains(":groovy`"))
                .singleElement().asString()
                .contains("may conflict");
    }

    @Test
    public void should_not_run_at_all_for_a_pom_whose_only_legacy_entry_has_no_artifact_id() throws Exception {
        // the boundary of the nameless-entry advisory: appliesTo needs a legacy coordinate it could migrate,
        // and a nameless one is not that, so the step never runs and never mentions it. The advisory reaches a
        // user only on a pom that has other legacy coordinates too - pinned so the limit is not read as a bug
        Model model = modelWithDependencies(
                newDependency(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, null, GROOVY_3_VERSION,
                        Artifact.SCOPE_PROVIDED));

        assertThat(migrationStep.appliesTo(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION))).isFalse();
    }

    @Test
    public void should_report_a_module_pinned_through_a_property_the_generators_never_wrote() throws Exception {
        // the class contract is that a version reference marks a generated entry because the generators wrote
        // exactly two of them. A property the user declared themselves is their own pin, no different in intent
        // from a literal version - which this step reports rather than rewrites - so it takes the same path
        Model model = modelWithDependencies(
                groovyModule("groovy", "${company.groovy.version}"));
        model.getProperties().setProperty("company.groovy.version", GROOVY_3_VERSION);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getDependencies())
                .extracting(Dependency::getGroupId, Dependency::getArtifactId, Dependency::getVersion)
                .containsExactly(tuple(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, "groovy",
                        "${company.groovy.version}"));
        assertThat(report.updates())
                .anyMatch(message -> message.startsWith("Action required:") && message.contains("groovy"));
        assertThat(model.getProperties()).containsEntry("company.groovy.version", GROOVY_3_VERSION);
    }

    @Test
    public void should_migrate_a_module_pinned_through_either_generated_property() throws Exception {
        // both references the generators actually wrote still count as generated
        Model model = modelWithDependencies(
                groovyModule("groovy", GROOVY_3_VERSION_REFERENCE),
                groovyModule("groovy-xml", "${" + DefaultPluginVersions.GROOVY_VERSION_PROPERTY + "}"));

        migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getDependencies())
                .extracting(Dependency::getGroupId, Dependency::getVersion)
                .containsOnly(tuple(DefaultPluginVersions.APACHE_GROOVY_GROUPID, null));
    }

    @Test
    public void should_survive_an_apache_dependency_without_an_artifact_id() throws Exception {
        // the apache half of the same hazard: replacing groovy-all makes the step read back every apache
        // Groovy module the pom declares, and that membership test throws on a null artifactId exactly like
        // the codehaus one. Needs a groovy-all present, since the read-back only runs once something replaced it
        var nameless = newDependency(DefaultPluginVersions.APACHE_GROOVY_GROUPID, null, null,
                Artifact.SCOPE_PROVIDED);
        Model model = modelWithDependencies(groovyAll(), nameless);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .anyMatch(message -> message.contains("declares the Groovy modules"));
        assertThat(nameless.getArtifactId()).isNull();
    }

    @Test
    public void should_survive_a_codehaus_dependency_without_an_artifact_id() throws Exception {
        // invalid Maven, but the model is read raw and never validated, so a hand-edited pom reached the
        // List.of membership tests with a null - and those throw rather than answering false, turning one bad
        // line into a MigrationException that sinks the whole extension migration
        var nameless = newDependency(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, null, GROOVY_3_VERSION,
                Artifact.SCOPE_PROVIDED);
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), nameless);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates()).isNotEmpty();
        assertThat(nameless.getGroupId()).isEqualTo(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID);
    }

    @Test
    public void should_name_every_resulting_module_when_one_was_already_declared() throws Exception {
        // the sentence reads as the list of modules the extension ends up with, so a module the pom already
        // declared belongs in it - listing only what was added drops it from an inventory
        Model model = modelWithDependencies(
                groovyAll(),
                newDependency(DefaultPluginVersions.APACHE_GROOVY_GROUPID, "groovy-dateutil", null,
                        Artifact.SCOPE_PROVIDED));

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .filteredOn(message -> message.contains("declares the Groovy modules"))
                .singleElement().asString()
                .contains("groovy-dateutil");
    }

    @Test
    public void should_say_a_versionless_legacy_module_no_longer_resolves() throws Exception {
        // groovy-macro is outside the frozen generated-module list, so it is reported rather than rewritten -
        // but it carries no version of its own, having resolved from the pre-12 BOM. On 12.0 that BOM manages
        // org.apache.groovy alone, so this is not a stale-but-building pom: the build stops before any test
        // runs, and "review it manually" is not what the user needs to read
        Model model = modelWithDependencies(
                groovyModule("groovy", GROOVY_3_VERSION_REFERENCE),
                newDependency(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, "groovy-macro", null,
                        Artifact.SCOPE_PROVIDED));

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .filteredOn(message -> message.contains("groovy-macro"))
                .singleElement().asString()
                .contains("the build cannot resolve it");
    }

    @Test
    public void should_not_claim_a_pinned_legacy_module_fails_to_resolve() throws Exception {
        // the counterpart: a literal version still resolves, so the message must not say the build stops
        Model model = modelWithDependencies(
                groovyModule("groovy", GROOVY_3_VERSION_REFERENCE),
                newDependency(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, "groovy-macro", "3.0.8",
                        Artifact.SCOPE_PROVIDED));

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .filteredOn(message -> message.contains("groovy-macro"))
                .singleElement().asString()
                .doesNotContain("the build cannot resolve it");
    }

    @Test
    public void should_keep_reporting_a_pom_it_can_only_report_on() throws Exception {
        // the other half of idempotency, and the more interesting one: a pom whose only legacy coordinate is
        // in dependencyManagement is never rewritten, so appliesTo stays true for ever and every subsequent
        // migration re-emits the same line. Asserted rather than left to chance - the manual action really is
        // still outstanding, so repeating it is the intent
        var managed = newDependency(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, "groovy", "3.0.19", null);
        var management = new DependencyManagement();
        management.getDependencies().add(managed);
        Model model = new Model();
        model.setDependencyManagement(management);
        var metadata = metadataForRuntime(GROOVY_4_RUNTIME_VERSION);

        var first = migrationStep.migrate(model, metadata);
        var stillApplies = migrationStep.appliesTo(model, metadata);
        var second = migrationStep.migrate(model, metadata);

        assertThat(stillApplies).isTrue();
        assertThat(managed.getGroupId()).isEqualTo(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID);
        assertThat(second.updates()).isEqualTo(first.updates());
    }

    @Test
    public void should_leave_a_stale_property_alone_on_a_pom_it_does_not_apply_to() throws Exception {
        // moving the groovy-all.version removal into RuntimeBOMMigrationStep's pre-12 branch means that on a
        // 12.0 target only this step removes it, and only when it has a legacy coordinate to migrate. A pom
        // already on the apache coordinates keeps the stale property, where it used to be dropped - a dead
        // property rather than a broken build, and pinned here so the change is deliberate
        Model model = modelWithDependencies(
                newDependency(DefaultPluginVersions.APACHE_GROOVY_GROUPID, "groovy", null,
                        Artifact.SCOPE_PROVIDED));
        model.getProperties().setProperty(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY, GROOVY_3_VERSION);
        var metadata = metadataForRuntime(GROOVY_4_RUNTIME_VERSION);

        assertThat(migrationStep.appliesTo(model, metadata)).isFalse();
        assertThat(model.getProperties()).containsEntry(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY, GROOVY_3_VERSION);
    }

    @Test
    public void should_not_advise_about_a_scope_no_module_carries() throws Exception {
        // a test-scoped groovy-all whose modules are all already declared: the advisory names "the modules
        // added in place of groovy-all", and nothing was added, so emitting it would point at modules that do
        // not exist. The rewrite loop has already set the declared ones to provided
        var groovyAll = groovyAll();
        groovyAll.setScope(Artifact.SCOPE_TEST);
        Model model = modelWithDependencies(groovyAll);
        DefaultPluginVersions.GROOVY_MODULES
                .forEach(module -> model.getDependencies().add(newDependency(
                        DefaultPluginVersions.APACHE_GROOVY_GROUPID, module, null, Artifact.SCOPE_PROVIDED)));

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates()).noneMatch(message -> message.contains("carry the `test` scope"));
        assertThat(report.removals()).anyMatch(message -> message.contains("groovy-all"));
    }

    @Test
    public void should_report_a_legacy_coordinate_declared_in_two_places_twice() throws Exception {
        // MigrationReport.updated drops a message it already holds, so an unqualified line collapses the two
        // occurrences into one: the user fixes the one they find and reasonably concludes they are done
        Model model = new Model();
        var managed = new DependencyManagement();
        managed.addDependency(groovyModule("groovy", "3.0.19"));
        model.setDependencyManagement(managed);
        var profile = new Profile();
        profile.setId("bonita-10");
        profile.getDependencies().add(groovyModule("groovy", "3.0.19"));
        model.getProfiles().add(profile);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        // filtered on the coordinate too: the step emits Action required from a second net for a nameless
        // artifactId, and counting every advisory would couple this to advice it is not about
        assertThat(report.updates())
                .filteredOn(message -> message.startsWith("Action required:")
                        && message.contains(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID + ":groovy`"))
                .hasSize(2);
    }

    @Test
    public void should_name_the_profile_a_legacy_coordinate_is_declared_by() throws Exception {
        // the shape JakartaServletMigrationStep already reports this way: a profile can hold the deliberate
        // variant of an extension built against an older runtime, so the user needs to know which one
        Model model = new Model();
        var profile = new Profile();
        profile.setId("bonita-10");
        profile.getDependencies().add(groovyModule("groovy-json", "3.0.19"));
        model.getProfiles().add(profile);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates()).anyMatch(message -> message.startsWith("Action required:")
                && message.contains("bonita-10"));
    }

    @Test
    public void should_name_dependency_management_as_the_place_a_legacy_coordinate_is_managed() throws Exception {
        Model model = new Model();
        var managed = new DependencyManagement();
        managed.addDependency(groovyModule("groovy", "3.0.19"));
        model.setDependencyManagement(managed);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates()).anyMatch(message -> message.startsWith("Action required:")
                && message.contains("dependencyManagement"));
    }

    @Test
    public void should_name_both_the_profile_and_dependency_management_of_a_managed_legacy_coordinate()
            throws Exception {
        // the fourth place a pom can declare one, and the one a location-aware traversal is most likely to
        // mislabel: it belongs to a profile AND to dependencyManagement, so naming only one loses the other
        Model model = new Model();
        var profile = new Profile();
        profile.setId("bonita-10");
        var managed = new DependencyManagement();
        managed.addDependency(groovyModule("groovy", "3.0.19"));
        profile.setDependencyManagement(managed);
        model.getProfiles().add(profile);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates()).anyMatch(message -> message.startsWith("Action required:")
                && message.contains("bonita-10")
                && message.contains("dependencyManagement"));
    }

    @Test
    public void should_keep_the_exclusions_of_a_rewritten_module() throws Exception {
        // noted rather than coded: an exclusion names what the dependency must not bring, and this step has no
        // basis for deciding that moving the groupId changes that intent. A codehaus exclusion left on an apache
        // module excludes nothing, which is inert - dropping one the user meant would not be
        var module = groovyModule("groovy", GROOVY_3_VERSION_REFERENCE);
        var exclusion = new Exclusion();
        exclusion.setGroupId(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID);
        exclusion.setArtifactId("groovy-testng");
        module.getExclusions().add(exclusion);
        Model model = modelWithDependencies(module);

        migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getDependencies()).singleElement()
                .extracting(Dependency::getGroupId, Dependency::getArtifactId)
                .containsExactly(DefaultPluginVersions.APACHE_GROOVY_GROUPID, "groovy");
        assertThat(model.getDependencies().get(0).getExclusions())
                .extracting(Exclusion::getGroupId, Exclusion::getArtifactId)
                .containsExactly(tuple(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, "groovy-testng"));
    }

    @Test
    public void should_not_claim_a_managed_coordinate_is_on_the_classpath() throws Exception {
        // a dependencyManagement entry contributes nothing to a classpath by itself, so neither branch of the
        // consequence clause can be stated about it: it is not the only copy of anything, and it conflicts
        // with nothing. Saying otherwise tells the user they ship a Groovy 3 jar they do not ship
        Model model = new Model();
        var managed = new DependencyManagement();
        managed.addDependency(groovyModule("groovy", "3.0.19"));
        model.setDependencyManagement(managed);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .filteredOn(message -> message.startsWith("Action required:"))
                .allSatisfy(message -> assertThat(message).doesNotContain("on the classpath")
                        .doesNotContain("may conflict"));
    }

    @Test
    public void should_see_the_apache_module_a_profile_declares_beside_the_legacy_one() throws Exception {
        // both live in the same profile, so when it is active both reach the classpath: this is the conflict
        // case, and resolving it against the root dependencies alone reports it as the only-copy case
        Model model = new Model();
        var profile = new Profile();
        profile.setId("bonita-10");
        profile.getDependencies().add(groovyModule("groovy", "3.0.19"));
        profile.getDependencies().add(newDependency(DefaultPluginVersions.APACHE_GROOVY_GROUPID, "groovy", null,
                Artifact.SCOPE_PROVIDED));
        model.getProfiles().add(profile);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates()).anyMatch(message -> message.startsWith("Action required:")
                && message.contains("may conflict"));
    }

    @Test
    public void should_see_the_apache_module_the_root_declares_beside_a_profile_legacy_one() throws Exception {
        // the root declaration reaches the classpath whichever profile is active, so it conflicts with a
        // legacy coordinate a profile declares - scoping the question to the profile alone would miss it
        Model model = modelWithDependencies(newDependency(DefaultPluginVersions.APACHE_GROOVY_GROUPID, "groovy",
                null, Artifact.SCOPE_PROVIDED));
        var profile = new Profile();
        profile.setId("bonita-10");
        profile.getDependencies().add(groovyModule("groovy", "3.0.19"));
        model.getProfiles().add(profile);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates()).anyMatch(message -> message.startsWith("Action required:")
                && message.contains("may conflict"));
    }

    @Test
    public void should_report_each_nameless_codehaus_declaration_separately() throws Exception {
        // the location is what keeps these messages apart: MigrationReport de-duplicates by string, so any
        // constant message collapses every nameless declaration to a single line. There is no coordinate to
        // search for either, which leaves the location as the user's only handle. A real legacy coordinate is
        // present because appliesTo needs one - a nameless entry is not something this step could migrate
        Model model = modelWithDependencies(
                groovyModule("groovy", GROOVY_3_VERSION_REFERENCE),
                newDependency(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, null, GROOVY_3_VERSION,
                        Artifact.SCOPE_PROVIDED));
        var profile = new Profile();
        profile.setId("bonita-10");
        profile.getDependencies().add(newDependency(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, null,
                GROOVY_3_VERSION, Artifact.SCOPE_PROVIDED));
        model.getProfiles().add(profile);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .filteredOn(message -> message.contains("declares no `artifactId`"))
                .hasSize(2);
    }

    @Test
    public void should_advise_without_asking_when_replacing_groovy_all_end_to_end() throws Exception {
        // the shape neither other end-to-end fixture carries. The advisory cannot know whether the user's code
        // imports any of the modules groovy-all brought, so it informs rather than asking: a permanently-on
        // Action required line that ends "otherwise there is nothing to do" trains readers to skip the section
        Model model = loadModel("pom_groovy3.xml");
        model.getDependencies().add(groovyAll());

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates()).anyMatch(message -> message.contains("groovy-servlet"));
        assertThat(report.updates()).noneMatch(message -> message.startsWith("Action required:"));
        assertThat(report.removals()).noneMatch(message -> message.startsWith("Action required:"));
    }

    private Dependency groovyAll() {
        var groovyAll = groovyModule(DefaultPluginVersions.LEGACY_GROOVY_ALL_ARTIFACT_ID,
                GROOVY_3_VERSION_REFERENCE);
        groovyAll.setType("pom");
        return groovyAll;
    }

    private Dependency groovyModule(String artifactId, String version) {
        return newDependency(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, artifactId, version,
                Artifact.SCOPE_PROVIDED);
    }

    private Model modelWithDependencies(Dependency... dependencies) {
        var model = new Model();
        for (Dependency dependency : dependencies) {
            model.getDependencies().add(dependency);
        }
        return model;
    }

    private Dependency newDependency(String groupId, String artifactId, String version, String scope) {
        var dependency = new Dependency();
        dependency.setGroupId(groupId);
        dependency.setArtifactId(artifactId);
        dependency.setVersion(version);
        dependency.setScope(scope);
        return dependency;
    }

    private ProjectMetadata metadataForRuntime(String bonitaRuntimeVersion) {
        var metadata = ProjectMetadata.defaultMetadata();
        metadata.setBonitaRuntimeVersion(bonitaRuntimeVersion);
        return metadata;
    }

    private Model loadModel(String resourceName) throws IOException, XmlPullParserException {
        try (InputStream is = Groovy4MigrationStepTest.class.getResourceAsStream("/migration/" + resourceName)) {
            return modelReader.read(is);
        }
    }
}
