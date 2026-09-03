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
import java.util.List;

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
import org.codehaus.plexus.util.xml.Xpp3Dom;
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
    public void should_not_apply_to_an_extension_already_on_the_apache_coordinates() throws Exception {
        // the pom the merge window is about: its Groovy coordinates are migrated, so this step - which is what
        // removes them - never visits it again and the Spock pin it would have corrected stays as it is. The
        // javadoc on appliesTo says so; asserted here so that changing the gate is a decision, not a side effect
        var spock = newDependency("org.spockframework", "spock-core", "2.0-groovy-3.0", Artifact.SCOPE_TEST);
        Model model = modelWithDependencies(
                newDependency(DefaultPluginVersions.APACHE_GROOVY_GROUPID, "groovy", null, Artifact.SCOPE_PROVIDED),
                spock);
        var metadata = metadataForRuntime(GROOVY_4_RUNTIME_VERSION);

        var appliesTo = migrationStep.appliesTo(model, metadata);
        if (appliesTo) {
            migrationStep.migrate(model, metadata);
        }

        assertThat(appliesTo).isFalse();
        assertThat(spock.getVersion()).isEqualTo("2.0-groovy-3.0");
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
                        tuple("org.spockframework", "spock-core", "2.4-groovy-4.0", Artifact.SCOPE_TEST),
                        tuple("net.bytebuddy", "byte-buddy", "${byte-buddy.version}", Artifact.SCOPE_TEST),
                        tuple("ch.qos.logback", "logback-classic", "${logback-classic.version}",
                                Artifact.SCOPE_TEST));
        // this pom's Spock pin is 2.0-M4-groovy-3.0, written by the archetype that generated it - the same
        // property name carries 1.3-groovy-2.4 in pom_from_1_3_1.xml - so it is one of the values Bonita
        // wrote, and correcting it leaves nothing for the user to do
        assertThat(model.getProperties()).doesNotContainKey("spock-core.version");
        assertThat(report.updates()).noneMatch(message -> message.startsWith("Action required:"));
        // and nothing in removals either: this pom drops no module, so that list stays empty
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
        // Groovy3MigrationStep pins spock.version to 2.0-groovy-3.0 in the same run, so this step corrects
        // spock-core rather than handing the user a fix for a value the migration itself produced - on the
        // coordinate, since that property is shared with spock-reports
        assertThat(model.getDependencies())
                .filteredOn(dependency -> "spock-core".equals(dependency.getArtifactId()))
                .extracting(Dependency::getVersion)
                .containsExactly("2.4-groovy-4.0");
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
    public void should_correct_the_spock_pin_the_groovy_3_migration_wrote() throws Exception {
        // written by Groovy3MigrationStep in the same run: the version is a property reference in every
        // generated pom, so the qualifier is never on the dependency itself. The correction lands on the
        // coordinate rather than the property, which spock-reports also reads
        var spock = newDependency("org.spockframework", "spock-core", "${spock.version}", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);
        model.getProperties().setProperty("spock.version", "2.0-groovy-3.0");

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(spock.getVersion()).isEqualTo("2.4-groovy-4.0");
        // the corrected version never goes into the shared property: spock-reports reads it too and
        // is versioned on its own stream. Whether the property then survives at all is
        // should_remove_the_spock_property_the_correction_left_dead's business
        assertThat(model.getProperties()).doesNotContainEntry("spock.version", "2.4-groovy-4.0");
        assertThat(report.updates()).noneMatch(message -> message.startsWith("Action required:"));
    }

    @Test
    public void should_report_a_spock_core_pin_bonita_did_not_write() throws Exception {
        // 1.3-groovy-2.5 is in none of the archetypes and is not what Groovy3MigrationStep writes, so it was
        // set by hand. A hand-set version is a decision, and this step reports it rather than overruling it -
        // unlike 1.3-groovy-2.4, one character away, which archetype 1.3.1 wrote and which is corrected
        var spock = newDependency("org.spockframework", "spock-core", "1.3-groovy-2.5", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(spock.getVersion()).isEqualTo("1.3-groovy-2.5");
        assertThat(report.updates())
                .anyMatch(message -> message.startsWith("Action required:")
                        && message.contains("1.3-groovy-2.5")
                        && message.contains("-groovy-4.0")
                        // Spock 1 published nothing past -groovy-2.5, so the move this user faces crosses the
                        // major version. The corrected path says so; the reported path owes the same warning,
                        // and the hand-set pin is the likelier of the two to be load-bearing
                        && message.contains("migration_guide"));
    }

    @Test
    public void should_correct_the_spock_pin_the_oldest_archetype_wrote() throws Exception {
        // bonita-rest-api-extension-archetype 1.3.1 wrote this under its own spock-core.version property,
        // which is the shape pom_from_1_3_1.xml carries
        var spock = newDependency("org.spockframework", "spock-core", "1.3-groovy-2.4", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(spock.getVersion()).isEqualTo("2.4-groovy-4.0");
        assertThat(report.updates()).noneMatch(message -> message.startsWith("Action required:"));
    }

    @Test
    public void should_correct_the_spock_pin_the_groovy_3_archetype_wrote() throws Exception {
        // the milestone pin pom_groovy3.xml carries. Groovy3MigrationStep never normalises that pom - its
        // groovy.version is already 3.0.7, so appliesTo declines it - which is how this value reaches this
        // step untouched, and why matching only what Groovy3MigrationStep writes missed it
        var spock = newDependency("org.spockframework", "spock-core", "2.0-M4-groovy-3.0", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(spock.getVersion()).isEqualTo("2.4-groovy-4.0");
        assertThat(report.updates()).noneMatch(message -> message.startsWith("Action required:"));
    }

    @Test
    public void should_correct_the_spock_pin_the_connector_archetype_wrote() throws Exception {
        // bonita-connector-archetype writes this under spock-core.version for every 7.13+ runtime, and its
        // Groovy variant is what a connector module carries. That pin is one Bonita wrote, so it belongs with
        // the others here rather than in an Action required line asking the user to fix our own value - it
        // reached this step only once connectors joined the shared list
        var spock = newDependency("org.spockframework", "spock-core", "2.3-groovy-3.0", Artifact.SCOPE_TEST);
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(spock.getVersion()).isEqualTo("2.4-groovy-4.0");
        assertThat(report.updates()).noneMatch(message -> message.startsWith("Action required:"));
    }

    @Test
    public void should_see_a_spock_variant_no_allowlist_would_have_named() throws Exception {
        // -groovy-2.0 shipped with Spock 1.0 and was in neither of the two qualifiers first checked here.
        // Reported on spock-spring, whose matching Groovy 4 release this step has no table for
        var spock = newDependency("org.spockframework", "spock-spring", "1.0-groovy-2.0", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .anyMatch(message -> message.startsWith("Action required:") && message.contains("1.0-groovy-2.0"));
    }

    @Test
    public void should_stay_quiet_about_a_spock_variant_built_for_groovy_4() throws Exception {
        var spock = newDependency("org.spockframework", "spock-core", "2.3-groovy-4.0", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates()).noneMatch(message -> message.contains("spock"));
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
    public void should_point_a_profile_scoped_spock_1_pin_at_the_migration_guide() throws Exception {
        // the profile path told a Spock 1 pin to move to the -groovy-4.0 release, which Spock 1 never
        // published - the same unfollowable advice both root paths were corrected for. Only the Spock 2 note
        // belongs here: the provenance one would claim this migration set the value, and Groovy3MigrationStep
        // rewrites model.getDependencies() only, never a profile's, so on a profile it wrote nothing
        var profile = new Profile();
        profile.setId("bonita-10");
        profile.getDependencies().add(newDependency("org.spockframework", "spock-core", "1.3-groovy-2.4", "test"));
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE));
        model.getProfiles().add(profile);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .anyMatch(message -> message.startsWith("Action required:")
                        && message.contains("bonita-10")
                        && message.contains("migration_guide"))
                .noneMatch(message -> message.contains("this migration set that version itself"));
    }

    @Test
    public void should_report_rather_than_rewrite_a_profile_scoped_spock() throws Exception {
        // read and write have to stay in the same scope: resolving a profile property and writing the
        // corrected value to the root would add a property the active profile then overrides - a no-op the
        // report would call a success
        var profile = new Profile();
        profile.setId("bonita-10");
        profile.getDependencies().add(newDependency("org.spockframework", "spock-core", "${spock.version}", "test"));
        profile.getProperties().setProperty("spock.version", "2.0-groovy-3.0");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE));
        model.getProfiles().add(profile);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getProperties()).doesNotContainKey("spock.version");
        assertThat(profile.getProperties()).containsEntry("spock.version", "2.0-groovy-3.0");
        assertThat(report.updates())
                .anyMatch(message -> message.startsWith("Action required:")
                        && message.contains("bonita-10")
                        && message.contains("2.0-groovy-3.0")
                        && message.contains("-groovy-4.0"));
    }

    @Test
    public void should_not_rewrite_a_spock_pinned_inside_a_profile() throws Exception {
        // a literal pin inside a profile is the same policy: profiles are reported, never rewritten
        var profile = new Profile();
        profile.setId("bonita-10");
        var spock = newDependency("org.spockframework", "spock-core", "2.0-groovy-3.0", "test");
        profile.getDependencies().add(spock);
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE));
        model.getProfiles().add(profile);

        migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(spock.getVersion()).isEqualTo("2.0-groovy-3.0");
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
                .map(dependency -> dependency.getGroupId() + ":" + dependency.getArtifactId() + ":"
                        + dependency.getVersion())
                .toList();

        var appliesAgain = migrationStep.appliesTo(model, metadata);
        migrationStep.migrate(model, metadata);

        assertThat(appliesAgain).isFalse();
        assertThat(model.getDependencies())
                .extracting(dependency -> dependency.getGroupId() + ":" + dependency.getArtifactId() + ":"
                        + dependency.getVersion())
                .containsExactlyElementsOf(afterFirst);
    }

    @Test
    public void should_report_a_root_spock_whose_property_only_a_profile_defines() throws Exception {
        // a common way to keep a pre-12 pin: the dependency is at root, the property lives in a profile.
        // It cannot be corrected - writing at root would be overridden by the active profile - but going
        // silent is what dropping the profile fallback from the resolver did, and that is worse
        var profile = new Profile();
        profile.setId("bonita-10");
        profile.getProperties().setProperty("spock.version", "2.0-groovy-3.0");
        Model model = modelWithDependencies(
                groovyModule("groovy", GROOVY_3_VERSION_REFERENCE),
                newDependency("org.spockframework", "spock-core", "${spock.version}", "test"));
        model.getProfiles().add(profile);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getProperties()).doesNotContainKey("spock.version");
        assertThat(report.updates())
                .anyMatch(message -> message.startsWith("Action required:")
                        && message.contains("spock-core")
                        && message.contains("2.0-groovy-3.0"));
    }

    @Test
    public void should_stay_quiet_about_a_profile_already_on_the_groovy_4_spock() throws Exception {
        // reporting every profile that mentions Spock would fire on one that is already correct
        var profile = new Profile();
        profile.setId("bonita-12");
        profile.getDependencies().add(newDependency("org.spockframework", "spock-core", "2.3-groovy-4.0", "test"));
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE));
        model.getProfiles().add(profile);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates()).noneMatch(message -> message.contains("spock"));
    }

    @Test
    public void should_report_a_profile_sharing_a_property_the_root_correction_no_longer_moves() throws Exception {
        // the correction lands on the coordinate, not on the shared property - spock-reports reads that
        // property too - so a profile dependency reading it really is still on the Groovy 3 variant
        var profile = new Profile();
        profile.setId("tests");
        profile.getDependencies().add(newDependency("org.spockframework", "spock-core", "${spock.version}", "test"));
        Model model = modelWithDependencies(
                groovyModule("groovy", GROOVY_3_VERSION_REFERENCE),
                newDependency("org.spockframework", "spock-core", "${spock.version}", "test"));
        model.getProperties().setProperty("spock.version", "2.0-groovy-3.0");
        model.getProfiles().add(profile);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getProperties()).containsEntry("spock.version", "2.0-groovy-3.0");
        assertThat(report.updates())
                .anyMatch(message -> message.startsWith("Action required:") && message.contains("tests"));
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
    public void should_survive_a_root_spock_declared_without_a_version() throws Exception {
        // the version comes from dependencyManagement or an imported BOM: an ordinary pom shape, and the
        // null reached isPropertyReference unguarded, aborting the whole migration
        Model model = modelWithDependencies(
                groovyModule("groovy", GROOVY_3_VERSION_REFERENCE),
                newDependency("org.spockframework", "spock-core", null, "test"));

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates()).noneMatch(message -> message.contains("spock"));
    }

    @Test
    public void should_survive_a_profile_spock_declared_without_a_version() throws Exception {
        // a profile re-declaring spock-core to add an exclusion or change scope, leaving the version to root
        var profile = new Profile();
        profile.setId("tests");
        profile.getDependencies().add(newDependency("org.spockframework", "spock-core", null, "test"));
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE));
        model.getProfiles().add(profile);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates()).noneMatch(message -> message.contains("spock"));
    }

    @Test
    public void should_report_the_profile_that_holds_the_stale_variant_not_the_first_one() throws Exception {
        // filtering has to precede findFirst: a correct value in an earlier profile would otherwise mask a
        // stale one declared later
        var current = new Profile();
        current.setId("bonita-12");
        current.getProperties().setProperty("spock.version", "2.3-groovy-4.0");
        var legacy = new Profile();
        legacy.setId("bonita-10");
        legacy.getProperties().setProperty("spock.version", "2.0-groovy-3.0");
        Model model = modelWithDependencies(
                groovyModule("groovy", GROOVY_3_VERSION_REFERENCE),
                newDependency("org.spockframework", "spock-core", "${spock.version}", "test"));
        model.getProfiles().add(current);
        model.getProfiles().add(legacy);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .anyMatch(message -> message.startsWith("Action required:") && message.contains("2.0-groovy-3.0"));
    }

    @Test
    public void should_report_a_profile_that_only_overrides_the_spock_version_property() throws Exception {
        // the "keep a pre-12 pin" shape: root is correct, the profile overrides the property and declares no
        // dependency of its own, so building with -Pbonita-10 resolves the Groovy 3 variant
        var legacy = new Profile();
        legacy.setId("bonita-10");
        legacy.getProperties().setProperty("spock.version", "2.0-groovy-3.0");
        Model model = modelWithDependencies(
                groovyModule("groovy", GROOVY_3_VERSION_REFERENCE),
                newDependency("org.spockframework", "spock-core", "${spock.version}", "test"));
        model.getProperties().setProperty("spock.version", "2.3-groovy-4.0");
        model.getProfiles().add(legacy);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .anyMatch(message -> message.startsWith("Action required:")
                        && message.contains("bonita-10")
                        && message.contains("2.0-groovy-3.0"));
    }

    @Test
    public void should_report_once_a_profile_that_declares_spock_and_overrides_its_version() throws Exception {
        // the natural shape of a legacy profile trips both profile nets: the one walking its dependencies
        // and the one watching for overrides of a property the root reads. Two differently worded lines for
        // one problem, so the report's own dedup cannot collapse them
        var legacy = new Profile();
        legacy.setId("bonita-10");
        legacy.getDependencies().add(newDependency("org.spockframework", "spock-core", "${spock.version}", "test"));
        legacy.getProperties().setProperty("spock.version", "2.0-groovy-3.0");
        Model model = modelWithDependencies(
                groovyModule("groovy", GROOVY_3_VERSION_REFERENCE),
                newDependency("org.spockframework", "spock-core", "${spock.version}", "test"));
        model.getProperties().setProperty("spock.version", "2.3-groovy-4.0");
        model.getProfiles().add(legacy);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .filteredOn(message -> message.contains("bonita-10"))
                .hasSize(1);
    }

    @Test
    public void should_not_retarget_spock_reports_through_the_shared_version_property() throws Exception {
        // Groovy3MigrationStep points com.athaydes:spock-reports at ${spock.version} too, and the two
        // artifacts do not share a version stream - spock-reports is versioned on its own stream - so
        // writing the corrected spock-core version into that property makes it unresolvable
        var spockCore = newDependency("org.spockframework", "spock-core", "${spock.version}", "test");
        var spockReports = newDependency("com.athaydes", "spock-reports", "${spock.version}", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spockCore,
                spockReports);
        model.getProperties().setProperty("spock.version", "2.0-groovy-3.0");

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        // the property that spock-reports also reads is left alone; the correction lands on the coordinate
        assertThat(model.getProperties()).containsEntry("spock.version", "2.0-groovy-3.0");
        assertThat(spockCore.getVersion()).isEqualTo("2.4-groovy-4.0");
        assertThat(spockReports.getVersion()).isEqualTo("${spock.version}");
        // and spock-reports is named rather than silently left on a Groovy 3 variant
        assertThat(report.updates())
                .anyMatch(message -> message.startsWith("Action required:") && message.contains("spock-reports"));
    }

    @Test
    public void should_report_any_artifact_pinned_to_a_groovy_3_spock_variant() throws Exception {
        // spock-spring and spock-junit4 are versioned in lockstep with spock-core; a groupId filter would
        // leave them silently on a variant that cannot run
        var spockSpring = newDependency("org.spockframework", "spock-spring", "2.0-groovy-3.0", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spockSpring);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(spockSpring.getVersion()).isEqualTo("2.0-groovy-3.0");
        assertThat(report.updates())
                .anyMatch(message -> message.startsWith("Action required:") && message.contains("spock-spring"))
                // 2.0-groovy-3.0 is a pin Bonita wrote, but only ever onto spock-core and spock-reports.
                // Saying the migration set this one would be false of a coordinate nothing in the chain writes
                .noneMatch(message -> message.contains("this migration set that version itself"));
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

    @Test
    public void should_name_a_reported_artifact_by_its_own_group() throws Exception {
        // detection matches com.athaydes as well as org.spockframework, so a message built from a constant
        // groupId pointed the user at org.spockframework:spock-reports - a coordinate that does not exist
        var legacy = new Profile();
        legacy.setId("bonita-10");
        legacy.getDependencies().add(newDependency("com.athaydes", "spock-reports", "2.3.2-groovy-3.0", "test"));
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE));
        model.getProfiles().add(legacy);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .anyMatch(message -> message.contains("com.athaydes:spock-reports"))
                .noneMatch(message -> message.contains("org.spockframework:spock-reports"));
    }

    @Test
    public void should_report_a_root_declaration_a_profile_resolves_only_once() throws Exception {
        // the root pass and the override pass both owned this shape - a root dependency whose property only a
        // profile defines - and worded it differently, so the report's dedup could not collapse the two lines
        var legacy = new Profile();
        legacy.setId("bonita-10");
        legacy.getProperties().setProperty("spock.version", "2.0-groovy-3.0");
        Model model = modelWithDependencies(
                groovyModule("groovy", GROOVY_3_VERSION_REFERENCE),
                newDependency("org.spockframework", "spock-core", "${spock.version}", "test"));
        model.getProfiles().add(legacy);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        // filtered on the version, not the coordinate: the duplicate line this guards against named the
        // profile and the stale value but no artifact at all, so filtering on the coordinate matched one
        // message either way and the test passed against the very shape it was written for
        assertThat(report.updates())
                .filteredOn(message -> message.contains("2.0-groovy-3.0"))
                .hasSize(1);
    }

    @Test
    public void should_report_a_stale_override_a_profile_declaring_correct_spock_hides() throws Exception {
        // the profile's own spock-core is already on the Groovy 4 variant, so walking its dependencies finds
        // nothing wrong - while the property it overrides still sends the root spock-reports to a Groovy 3
        // one. Skipping the override check for any profile that declares Spock went silent here
        var legacy = new Profile();
        legacy.setId("bonita-10");
        legacy.getDependencies().add(newDependency("org.spockframework", "spock-core", "2.3-groovy-4.0", "test"));
        legacy.getProperties().setProperty("spock.version", "2.3.2-groovy-3.0");
        Model model = modelWithDependencies(
                groovyModule("groovy", GROOVY_3_VERSION_REFERENCE),
                newDependency("com.athaydes", "spock-reports", "${spock.version}", "test"));
        model.getProperties().setProperty("spock.version", "2.4.0-groovy-4.0");
        model.getProfiles().add(legacy);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .anyMatch(message -> message.contains("bonita-10")
                        && message.contains("com.athaydes:spock-reports")
                        && message.contains("2.3.2-groovy-3.0"));
    }

    @Test
    public void should_ignore_an_unrelated_artifact_sharing_the_spock_reports_group() throws Exception {
        // com.athaydes publishes more than spock-reports, and matching the whole groupId made any of it a
        // Spock declaration - enough to suppress the override report for a profile that mentioned one
        var unrelated = newDependency("com.athaydes", "easy-jsm", "1.0-groovy-3.0", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), unrelated);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(unrelated.getVersion()).isEqualTo("1.0-groovy-3.0");
        assertThat(report.updates()).noneMatch(message -> message.contains("easy-jsm"));
    }

    @Test
    public void should_say_when_a_correction_detached_a_property_a_profile_overrides() throws Exception {
        // the correction writes the version onto the coordinate, so a profile overriding the property it was
        // declared with stops affecting it. That is the right outcome - the profile's own variant does not run
        // on Groovy 4 either - but going quiet about it leaves a profile that no longer does what its name says
        var legacy = new Profile();
        legacy.setId("bonita-10");
        legacy.getProperties().setProperty("spock.version", "2.0-groovy-2.5");
        var spock = newDependency("org.spockframework", "spock-core", "${spock.version}", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);
        model.getProperties().setProperty("spock.version", "2.0-groovy-3.0");
        model.getProfiles().add(legacy);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(spock.getVersion()).isEqualTo("2.4-groovy-4.0");
        assertThat(report.updates())
                .anyMatch(message -> message.contains("no longer takes its version from")
                        && message.contains("bonita-10"));
    }

    @Test
    public void should_stay_quiet_about_a_property_no_profile_overrides() throws Exception {
        // the detachment line is about a profile losing its effect, so a pom without profiles gets none
        var spock = newDependency("org.spockframework", "spock-core", "${spock.version}", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);
        model.getProperties().setProperty("spock.version", "2.0-groovy-3.0");

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates()).noneMatch(message -> message.contains("no longer takes its version from"));
    }

    @Test
    public void should_report_one_line_for_a_coordinate_declared_and_managed_at_the_same_version()
            throws Exception {
        // one artifact at one version is one thing to read about, however many places declare it. Both slots
        // carry the same pin on purpose: with different pins they are two things to fix, which is the case below
        var managed = newDependency("org.spockframework", "spock-core", "2.0-groovy-3.0", "test");
        var management = new DependencyManagement();
        management.getDependencies().add(managed);
        Model model = modelWithDependencies(
                groovyModule("groovy", GROOVY_3_VERSION_REFERENCE),
                newDependency("org.spockframework", "spock-core", "2.0-groovy-3.0", "test"));
        model.setDependencyManagement(management);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .filteredOn(message -> message.contains("spock-core"))
                .hasSize(1);
        // deduplicating the message never deduplicates the correction: dependencyManagement is what a
        // transitive spock-core resolves through, so leaving it behind would strand it on a Groovy 4 runtime
        assertThat(managed.getVersion()).isEqualTo("2.4-groovy-4.0");
    }

    @Test
    public void should_report_a_managed_pin_the_declared_one_does_not_share() throws Exception {
        // two declarations of one coordinate at different versions are two things for the user to fix. The
        // managed pin here was set by hand, so this step leaves the version alone - but silence about it is
        // what the dedup used to produce, on the declaration that governs transitive resolution
        var managed = newDependency("org.spockframework", "spock-core", "1.3-groovy-2.5", "test");
        var management = new DependencyManagement();
        management.getDependencies().add(managed);
        Model model = modelWithDependencies(
                groovyModule("groovy", GROOVY_3_VERSION_REFERENCE),
                newDependency("org.spockframework", "spock-core", "2.0-groovy-3.0", "test"));
        model.setDependencyManagement(management);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(managed.getVersion()).isEqualTo("1.3-groovy-2.5");
        assertThat(report.updates())
                .anyMatch(message -> message.contains("spock-core") && message.contains("2.0-groovy-3.0")
                        && message.contains("2.4-groovy-4.0"))
                .anyMatch(message -> message.startsWith("Action required:") && message.contains("spock-core")
                        && message.contains("1.3-groovy-2.5"));
    }

    @Test
    public void should_report_correcting_a_managed_pin_when_the_declared_one_is_hand_set() throws Exception {
        // the reverse shape: the managed pin is one Bonita wrote and gets corrected, while the declared one is
        // the user's and does not. The correction has to be reported - this class changes nothing silently
        var managed = newDependency("org.spockframework", "spock-core", "2.0-groovy-3.0", "test");
        var management = new DependencyManagement();
        management.getDependencies().add(managed);
        Model model = modelWithDependencies(
                groovyModule("groovy", GROOVY_3_VERSION_REFERENCE),
                newDependency("org.spockframework", "spock-core", "1.3-groovy-2.5", "test"));
        model.setDependencyManagement(management);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(managed.getVersion()).isEqualTo("2.4-groovy-4.0");
        assertThat(report.updates())
                .anyMatch(message -> message.contains("spock-core") && message.contains("2.0-groovy-3.0")
                        && message.contains("2.4-groovy-4.0"));
    }

    @Test
    public void should_remove_the_spock_property_the_correction_left_dead() throws Exception {
        // Groovy3MigrationStep writes spock.version whether the pom declares Spock or not, and the correction
        // moves the version onto the coordinate, so nothing reads the property afterwards
        var spock = newDependency("org.spockframework", "spock-core", "${spock.version}", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);
        model.getProperties().setProperty("spock.version", "2.0-groovy-3.0");

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getProperties()).doesNotContainKey("spock.version");
        assertThat(report.removals()).anyMatch(message -> message.contains("spock.version"));
    }

    @Test
    public void should_keep_the_spock_property_spock_reports_still_reads() throws Exception {
        // the property is shared, and spock-reports is not rewritten: removing it would leave that
        // dependency resolving to nothing, which is worse than a stale value it at least still resolves
        var spockCore = newDependency("org.spockframework", "spock-core", "${spock.version}", "test");
        var spockReports = newDependency("com.athaydes", "spock-reports", "${spock.version}", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spockCore,
                spockReports);
        model.getProperties().setProperty("spock.version", "2.0-groovy-3.0");

        migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getProperties()).containsEntry("spock.version", "2.0-groovy-3.0");
    }

    @Test
    public void should_report_a_spock_version_no_scope_resolves() throws Exception {
        // a version built from two references is not a single property name, so the lookup finds nothing.
        // Silence there is how an unresolvable pom gets a clean report
        var spock = newDependency("org.spockframework", "spock-core", "${spock.base}-${spock.qualifier}", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .anyMatch(message -> message.startsWith("Action required:")
                        && message.contains("${spock.base}-${spock.qualifier}")
                        && message.contains("cannot resolve for the default build"));
    }

    @Test
    public void should_correct_the_spock_pin_of_every_archetype_fixture() throws Exception {
        // asserted against the fixtures themselves, which are the record of what the archetypes wrote, so a
        // pin Bonita shipped cannot go missing from the allowlist and be merely reported. The oldest fixtures
        // carry the version inline on the dependency rather than in a property, which is easy to overlook
        for (String fixture : List.of("pom_from_1_0_5.xml", "pom_from_1_1_1.xml", "pom_from_1_3_1.xml",
                "pom_from_1_5_0.xml", "pom_groovy3.xml")) {
            Model model = loadModel(fixture);

            var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

            assertThat(PomDeclarations.all(model)
                    .filter(dependency -> "spock-core".equals(dependency.getArtifactId()))
                    .map(Dependency::getVersion))
                            .as("spock-core version in %s", fixture)
                            .containsOnly("2.4-groovy-4.0");
            assertThat(report.updates())
                    .as("action required lines about Spock in %s", fixture)
                    .noneMatch(message -> message.startsWith("Action required:") && message.contains("spock"));
        }
    }

    @Test
    public void should_correct_a_managed_declaration_as_well_as_the_plain_one() throws Exception {
        // deduplicating per coordinate skipped the second declaration outright rather than only its message,
        // leaving dependencyManagement stale - which is what a transitive spock-core resolves through
        var managed = newDependency("org.spockframework", "spock-core", "2.0-groovy-3.0", "test");
        var management = new DependencyManagement();
        management.getDependencies().add(managed);
        var declared = newDependency("org.spockframework", "spock-core", "2.0-groovy-3.0", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), declared);
        model.setDependencyManagement(management);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(declared.getVersion()).isEqualTo("2.4-groovy-4.0");
        assertThat(managed.getVersion()).isEqualTo("2.4-groovy-4.0");
        // still one line: two declarations, one thing for the user to read
        assertThat(report.updates()).filteredOn(message -> message.contains("spock-core")).hasSize(1);
    }

    @Test
    public void should_keep_a_spock_version_property_a_plugin_configuration_still_names() throws Exception {
        // the Spock properties are deleted on the strength of PomDeclarations.namesProperty, which walks
        // configuration DOMs precisely because a version can be named there and nowhere else. The Groovy side
        // has this test; deleting a property a plugin still reads corrupts the pom rather than adding noise
        var spock = newDependency("org.spockframework", "spock-core", "${spock.version}", Artifact.SCOPE_TEST);
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);
        model.getProperties().setProperty("spock.version", "2.0-groovy-3.0");
        var variable = new Xpp3Dom("spockVersion");
        variable.setValue("${spock.version}");
        var variables = new Xpp3Dom("systemPropertyVariables");
        variables.addChild(variable);
        var configuration = new Xpp3Dom("configuration");
        configuration.addChild(variables);
        var surefire = new Plugin();
        surefire.setGroupId(DefaultPluginVersions.APACHE_MAVEN_PLUGIN_GROUP_ID);
        surefire.setArtifactId(DefaultPluginVersions.MAVEN_SUREFIRE_PLUGIN);
        surefire.setConfiguration(configuration);
        var build = new Build();
        build.getPlugins().add(surefire);
        model.setBuild(build);

        migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        // corrected on the coordinate, so nothing reads the property through a dependency any more
        assertThat(spock.getVersion()).isEqualTo("2.4-groovy-4.0");
        assertThat(model.getProperties()).containsEntry("spock.version", "2.0-groovy-3.0");
    }

    @Test
    public void should_stay_quiet_about_a_profile_override_one_hop_below_the_declaration() throws Exception {
        // the detachment map keys on the property a declaration names directly, so an override of the property
        // that one names is not reported. The correction is still right and no archetype writes that chain -
        // asserted so the silence is a decision rather than a property of where the map is keyed
        var spock = newDependency("org.spockframework", "spock-core", "${spock.version}", Artifact.SCOPE_TEST);
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);
        model.getProperties().setProperty("spock.version", "${spock-core.version}");
        model.getProperties().setProperty("spock-core.version", "2.0-groovy-3.0");
        var profile = new Profile();
        profile.setId("bonita-10");
        profile.getProperties().setProperty("spock-core.version", "1.3-groovy-2.5");
        model.getProfiles().add(profile);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        // the chain resolved and the correction fired, which is what makes the silence below meaningful
        assertThat(spock.getVersion()).isEqualTo("2.4-groovy-4.0");
        assertThat(report.updates()).noneMatch(message -> message.contains("no longer takes its version from"));
    }

    @Test
    public void should_not_credit_the_migration_for_a_pin_an_archetype_wrote() throws Exception {
        // the provenance sentence names the Groovy 3 step as the source, and that step writes one value; the
        // rest of the allowlist comes from archetypes. Attaching it to those tells the user this migration set
        // a version it never touched - the connector archetype's pin is the newest way in
        var reports = newDependency("com.athaydes", "spock-reports", "2.3-groovy-3.0", Artifact.SCOPE_TEST);
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), reports);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .anyMatch(message -> message.startsWith("Action required:") && message.contains("spock-reports"))
                .noneMatch(message -> message.contains("this migration set that version itself"));
    }

    @Test
    public void should_resolve_a_chain_that_needs_every_hop() throws Exception {
        // the bound counts lookups, and the result is tested once more after the last one - so a chain needing
        // all of them still resolves. It read as unresolvable before that post-loop test was added
        var spock = newDependency("org.spockframework", "spock-core", "${spock.hop1}", Artifact.SCOPE_TEST);
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);
        for (int hop = 1; hop < 5; hop++) {
            model.getProperties().setProperty("spock.hop" + hop, "${spock.hop" + (hop + 1) + "}");
        }
        model.getProperties().setProperty("spock.hop5", "2.0-groovy-3.0");

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(spock.getVersion()).isEqualTo("2.4-groovy-4.0");
        assertThat(report.updates()).noneMatch(message -> message.startsWith("Action required:"));
    }

    @Test
    public void should_give_up_on_a_chain_one_hop_past_the_limit() throws Exception {
        // and the hop after it is where the bound bites: reported as unresolvable rather than followed forever
        var spock = newDependency("org.spockframework", "spock-core", "${spock.hop1}", Artifact.SCOPE_TEST);
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);
        for (int hop = 1; hop < 6; hop++) {
            model.getProperties().setProperty("spock.hop" + hop, "${spock.hop" + (hop + 1) + "}");
        }
        model.getProperties().setProperty("spock.hop6", "2.0-groovy-3.0");

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(spock.getVersion()).isEqualTo("${spock.hop1}");
        assertThat(report.updates())
                .anyMatch(message -> message.startsWith("Action required:")
                        && message.contains("cannot resolve for the default build"));
    }

    @Test
    public void should_resolve_a_property_that_names_another_property() throws Exception {
        // these poms carry both spock.version and spock-core.version and one can name the other. Resolving a
        // single level returned the inner reference, which matches no variant qualifier, so a stale pin passed
        // without a correction and without a word
        var spock = newDependency("org.spockframework", "spock-core", "${spock.version}", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);
        model.getProperties().setProperty("spock.version", "${spock-core.version}");
        model.getProperties().setProperty("spock-core.version", "2.0-groovy-3.0");

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(spock.getVersion()).isEqualTo("2.4-groovy-4.0");
        // a 2.0 pin is the variant swap the message describes, so it gets no Spock 2 upgrade note
        assertThat(report.updates()).noneMatch(message -> message.contains("migration_guide"));
    }

    @Test
    public void should_survive_properties_that_name_each_other() throws Exception {
        // an unresolvable pom, not a reason to spin
        var spock = newDependency("org.spockframework", "spock-core", "${spock.version}", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);
        model.getProperties().setProperty("spock.version", "${spock-core.version}");
        model.getProperties().setProperty("spock-core.version", "${spock.version}");

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(spock.getVersion()).isEqualTo("${spock.version}");
        // the cycle has a checkable outcome: resolved() gives up, so the version cannot be read and the step
        // says so instead of reporting a clean pom. Also guards its bound from becoming "return the last
        // reference"
        assertThat(report.updates())
                .anyMatch(message -> message.startsWith("Action required:")
                        && message.contains("cannot resolve for the default build"));
    }

    @Test
    public void should_stay_quiet_about_a_variant_built_for_a_newer_groovy() throws Exception {
        // -groovy-4.1 is not an older variant, and telling the user to move it to -groovy-4.0 asks for a
        // downgrade. Anything from 4.0 up runs on the Groovy the runtime ships
        var spock = newDependency("org.spockframework", "spock-core", "2.4-groovy-4.1", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(spock.getVersion()).isEqualTo("2.4-groovy-4.1");
        assertThat(report.updates()).noneMatch(message -> message.contains("spock"));
    }

    @Test
    public void should_not_claim_no_scope_defines_a_composite_a_profile_contributes_to() throws Exception {
        // a composite of two references is not one property name, and slicing it as one produced a key no pom
        // defines - which was then reported as "no profile and no property defines it", about a property a
        // profile does define
        var legacy = new Profile();
        legacy.setId("bonita-10");
        legacy.getProperties().setProperty("spock.base", "2.0");
        var spock = newDependency("org.spockframework", "spock-core", "${spock.base}-${spock.qualifier}", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);
        model.getProfiles().add(legacy);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        // reported, and reported accurately: a composite has no single property to name as missing
        assertThat(report.updates())
                .filteredOn(message -> message.contains("${spock.base}-${spock.qualifier}"))
                .singleElement().asString()
                .contains("cannot resolve for the default build");
    }

    @Test
    public void should_not_call_a_detached_profile_override_intentional_when_it_is_newer() throws Exception {
        // the root pin is one Bonita wrote, so spock-core becomes a literal and the profile's override stops
        // applying. This is the shape where the user loses something - building with -Pbonita-12 now resolves
        // 2.4 rather than the 2.5 that was pinned - so it is the one that most needs saying. What it must not
        // say is that the detachment was intentional "since those variants do not run on Groovy 4 either",
        // which is false of an override that is itself a Groovy 4 variant
        var spock = newDependency("org.spockframework", "spock-core", "${spock.version}", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);
        model.getProperties().setProperty("spock.version", "2.0-groovy-3.0");
        var profile = new Profile();
        profile.setId("bonita-12");
        // 2.5-groovy-4.0 is not published as this is written; the step reads the qualifier and never resolves
        // the artifact, and the point needs a variant newer than the 2.4 the correction now writes
        profile.getProperties().setProperty("spock.version", "2.5-groovy-4.0");
        model.getProfiles().add(profile);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(spock.getVersion()).isEqualTo("2.4-groovy-4.0");
        assertThat(report.updates())
                .anyMatch(message -> message.contains("no longer takes its version from")
                        && message.contains("bonita-12"))
                .noneMatch(message -> message.contains("intentionally"));
    }

    @Test
    public void should_not_call_a_detached_override_intentional_when_it_is_newer_through_a_property() throws Exception {
        // the same loss as above, spelled through one property hop, so the resolution has to happen before
        // the value can be judged - reading the raw ${spock4.version} matches no variant qualifier at all
        var spock = newDependency("org.spockframework", "spock-core", "${spock.version}", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);
        model.getProperties().setProperty("spock.version", "2.0-groovy-3.0");
        var profile = new Profile();
        profile.setId("bonita-12");
        profile.getProperties().setProperty("spock.version", "${spock4.version}");
        profile.getProperties().setProperty("spock4.version", "2.5-groovy-4.0");
        model.getProfiles().add(profile);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(spock.getVersion()).isEqualTo("2.4-groovy-4.0");
        assertThat(report.updates())
                .anyMatch(message -> message.contains("no longer takes its version from")
                        && message.contains("bonita-12"))
                .noneMatch(message -> message.contains("intentionally"));
    }

    @Test
    public void should_say_when_the_version_it_reports_is_one_the_migration_wrote() throws Exception {
        // Groovy3MigrationStep sets spock.version and points com.athaydes:spock-reports at it, two steps
        // before this one runs in the same chain. So the value the report asks the user to change is one the
        // migration produced over whatever they had, and reading as though they had pinned it is unfair -
        // the same rule that justifies correcting the spock-core pin rather than reporting it
        var reports = newDependency("com.athaydes", "spock-reports", "${spock.version}", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), reports);
        model.getProperties().setProperty("spock.version", "2.0-groovy-3.0");

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(reports.getVersion()).isEqualTo("${spock.version}");
        assertThat(report.updates())
                .anyMatch(message -> message.startsWith("Action required:")
                        && message.contains("spock-reports")
                        && message.contains("this migration set that version itself"));
    }

    @Test
    public void should_stay_quiet_about_a_profile_override_that_resolves_to_the_correction() throws Exception {
        // a profile already pinning what the correction writes loses nothing: building with it resolved
        // 2.4-groovy-4.0 before and resolves 2.4-groovy-4.0 after. Reporting it asks for a review of a no-op,
        // on the profile id a Bonita 12 project is likeliest to carry. Stale and newer overrides still report
        var spock = newDependency("org.spockframework", "spock-core", "${spock.version}", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);
        model.getProperties().setProperty("spock.version", "2.0-groovy-3.0");
        var profile = new Profile();
        profile.setId("bonita-12");
        profile.getProperties().setProperty("spock.version", "2.4-groovy-4.0");
        model.getProfiles().add(profile);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(spock.getVersion()).isEqualTo("2.4-groovy-4.0");
        assertThat(report.updates()).noneMatch(message -> message.contains("no longer takes its version from"));
    }

    @Test
    public void should_still_flag_a_detached_profile_override_that_is_stale() throws Exception {
        // the counterpart: this profile's own pin does not run on Groovy 4, so losing it costs nothing. Both
        // shapes are reported, and the line says which is which rather than asserting one of them
        var spock = newDependency("org.spockframework", "spock-core", "${spock.version}", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);
        model.getProperties().setProperty("spock.version", "2.0-groovy-3.0");
        var profile = new Profile();
        profile.setId("bonita-10");
        profile.getProperties().setProperty("spock.version", "1.3-groovy-2.5");
        model.getProfiles().add(profile);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .anyMatch(message -> message.contains("no longer takes its version from")
                        && message.contains("bonita-10 (1.3-groovy-2.5)"));
    }

    @Test
    public void should_report_a_detached_override_when_the_literal_declaration_is_seen_first() throws Exception {
        // PomDeclarations.of yields <dependencies> before <dependencyManagement>, and both resolve to the
        // same version, so they share one correction line. The detachment line is per declaration, not per
        // coordinate: only the managed entry read the property, so only it can report losing it
        var literal = newDependency("org.spockframework", "spock-core", "2.0-groovy-3.0", "test");
        var managed = newDependency("org.spockframework", "spock-core", "${spock.version}", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), literal);
        var management = new DependencyManagement();
        management.getDependencies().add(managed);
        model.setDependencyManagement(management);
        model.getProperties().setProperty("spock.version", "2.0-groovy-3.0");
        var profile = new Profile();
        profile.setId("bonita-10");
        profile.getProperties().setProperty("spock.version", "1.3-groovy-2.5");
        model.getProfiles().add(profile);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(managed.getVersion()).isEqualTo("2.4-groovy-4.0");
        assertThat(report.updates())
                .anyMatch(message -> message.contains("no longer takes its version from")
                        && message.contains("bonita-10"));
    }

    @Test
    public void should_say_so_when_only_a_profile_can_resolve_the_spock_version() throws Exception {
        // the hand-off deferred to the profile pass for any property a profile defines, but that pass speaks
        // only for a stale value. A profile pinning the Groovy 4 variant therefore silenced both passes, and
        // a pom whose default scope cannot resolve spock-core at all got a clean report
        var spock = newDependency("org.spockframework", "spock-core", "${spock.version}", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);
        var profile = new Profile();
        profile.setId("bonita-12");
        profile.getProperties().setProperty("spock.version", "2.3-groovy-4.0");
        model.getProfiles().add(profile);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .anyMatch(message -> message.startsWith("Action required:")
                        && message.contains("cannot resolve for the default build"));
    }

    @Test
    public void should_point_a_spock_1_pin_at_the_spock_2_migration_guide() throws Exception {
        // 1.3-groovy-2.4 to 2.4-groovy-4.0 is a major Spock upgrade, not the variant swap the message
        // described: Spock 2 runs on the JUnit 5 platform and a Spock 1 specification need not run unchanged.
        // Groovy3MigrationStep links the same guide for the same jump, and three fixtures carry a Spock 1
        // pin, so the correction really does move those poms across a major version
        var spock = newDependency("org.spockframework", "spock-core", "1.3-groovy-2.4", "test");
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE), spock);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(spock.getVersion()).isEqualTo("2.4-groovy-4.0");
        assertThat(report.updates())
                .anyMatch(message -> message.contains("spock-core") && message.contains("migration_guide"));
    }

    @Test
    public void should_report_a_profile_pin_the_managed_one_does_not_share() throws Exception {
        // two pins in one profile are two things for the user to change, as they are at the root, so the
        // profile pass keys its dedup on the version as well as the coordinate
        var profile = new Profile();
        profile.setId("bonita-10");
        profile.getDependencies().add(newDependency("org.spockframework", "spock-core", "2.0-groovy-3.0", "test"));
        var management = new DependencyManagement();
        management.getDependencies().add(
                newDependency("org.spockframework", "spock-core", "1.3-groovy-2.5", "test"));
        profile.setDependencyManagement(management);
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE));
        model.getProfiles().add(profile);

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates())
                .filteredOn(message -> message.startsWith("Action required:") && message.contains("spock-core"))
                .hasSize(2);
    }

    @Test
    public void should_survive_a_spock_declaration_without_an_artifact_id() throws Exception {
        // the Groovy half of this class guards every membership test against a missing artifactId; the Spock
        // half named the coordinate `org.spockframework:null` in the report instead
        Model model = modelWithDependencies(groovyModule("groovy", GROOVY_3_VERSION_REFERENCE),
                newDependency("org.spockframework", null, "2.0-groovy-3.0", "test"));

        var report = migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(report.updates()).noneMatch(message -> message.contains(":null"));
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
