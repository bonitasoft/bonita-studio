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
package org.bonitasoft.studio.maven;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.apache.maven.model.Build;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Parent;
import org.apache.maven.model.Plugin;
import org.bonitasoft.studio.common.repository.core.maven.model.DefaultPluginVersions;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.migration.MavenModelMigration;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionFileStore;
import org.bonitasoft.studio.rest.api.extension.core.repository.migration.Groovy3MigrationStep;
import org.bonitasoft.studio.rest.api.extension.core.repository.migration.Groovy4MigrationStep;
import org.bonitasoft.studio.rest.api.extension.core.repository.migration.RuntimeBOMMigrationStep;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.junit.Test;

/**
 * Which steps a pom is put through, and in which order.
 * <p>
 * Mostly tested at the list level: what matters is a property of the ordering rather than of any one step, and
 * three steps depend on it where a comment was the only thing saying so. The steps themselves take a model and a
 * metadata, so a whole pipeline can also be run over one in memory - which is the only place an interaction
 * between two steps is visible at all.
 */
public class ExtensionRepositoryStoreMigrationStepsTest {

    /** Named the way the parent pom names it: one property per managed plugin, keyed on its artifactId. */
    private static final String SUREFIRE_VERSION_PROPERTY = DefaultPluginVersions.MAVEN_SUREFIRE_PLUGIN + ".version";
    private static final String GROOVY_4_RUNTIME_VERSION = "12.0.0";
    private static final String GROOVY_3_VERSION = "3.0.19";

    /** What Groovy3MigrationStep normalizes a pom's Groovy version to. */
    private static final String GROOVY_3_MIGRATED_VERSION = "3.0.8";

    @Test
    public void should_leave_a_non_rest_extension_to_the_shared_steps_only() {
        // this branch migrates REST API extension poms only. Widening it to every extension type is another
        // change's business, and this assertion is what that change has to flip on purpose, not by accident
        var connector = mock(ExtensionProjectFileStore.class);

        var steps = ExtensionRepositoryStore.migrationStepsFor(connector);

        assertThat(steps).doesNotHaveAnyElementsOfTypes(Groovy4MigrationStep.class);
    }

    @Test
    public void should_normalize_a_pom_to_the_groovy_3_shape_before_reading_it() {
        // Groovy4MigrationStep reads the shape Groovy3MigrationStep produces out of a 7.x pom, so it cannot run
        // first. This holds only because the REST API list is drained before the shared one
        var restApi = mock(RestAPIExtensionFileStore.class);

        var steps = ExtensionRepositoryStore.migrationStepsFor(restApi);

        assertThat(indexOf(steps, Groovy3MigrationStep.class))
                .isLessThan(indexOf(steps, Groovy4MigrationStep.class));
    }

    @Test
    public void should_run_the_groovy_4_step_before_the_runtime_bom_step() {
        // RuntimeBOMMigrationStep would otherwise keep managing the legacy coordinates this step is moving.
        // Reversing the two is survivable - their runtime gates are mutually exclusive - but only deliberately,
        // and a comment was the only thing saying so
        var restApi = mock(RestAPIExtensionFileStore.class);

        var steps = ExtensionRepositoryStore.migrationStepsFor(restApi);

        assertThat(indexOf(steps, Groovy4MigrationStep.class))
                .isLessThan(indexOf(steps, RuntimeBOMMigrationStep.class));
    }

    @Test
    public void should_run_every_shared_step_on_a_rest_api_extension_too() {
        // a REST API extension gets both lists, so nothing added to the shared one can skip it
        var restApi = mock(RestAPIExtensionFileStore.class);
        var connector = mock(ExtensionProjectFileStore.class);

        var restApiSteps = ExtensionRepositoryStore.migrationStepsFor(restApi);

        assertThat(stepNames(restApiSteps)).containsAll(
                stepNames(ExtensionRepositoryStore.migrationStepsFor(connector)));
    }

    @Test
    public void should_not_run_a_step_twice_on_a_rest_api_extension() {
        // a step present in both lists would migrate the same pom twice and report it twice
        var restApi = mock(RestAPIExtensionFileStore.class);

        var steps = ExtensionRepositoryStore.migrationStepsFor(restApi);

        assertThat(stepNames(steps)).doesNotHaveDuplicates();
    }

    private List<String> stepNames(List<MavenModelMigration> steps) {
        return steps.stream().map(step -> step.getClass().getName()).toList();
    }

    @Test
    public void should_keep_a_groovy_version_property_a_dependency_still_names() {
        // the one interaction no single-step test can see. Groovy4MigrationStep leaves a codehaus module it does
        // not own alone, reports it, and keeps groovy.version alive because that module still names it -
        // then ParentMigrationStep removes groovy.version whatever names it. Nothing supplies the property once
        // the parent is attached: the generated parent does not declare it, and a BOM brought in with import
        // scope contributes dependencyManagement, never properties. So the pom would ship with a version
        // reference that resolves to nothing, which is a build failure rather than the dead property the
        // unconditional removal assumes
        var model = extensionPom();
        model.getProperties().setProperty("groovy.version", GROOVY_3_VERSION);
        model.getProperties().setProperty("groovy-all.version", GROOVY_3_VERSION);
        var userPinned = new Dependency();
        userPinned.setGroupId("org.codehaus.groovy");
        userPinned.setArtifactId("groovy-testng");
        userPinned.setVersion("${groovy.version}");
        userPinned.setScope("provided");
        model.getDependencies().add(userPinned);

        runPipeline(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(userPinned.getVersion()).isEqualTo("${groovy.version}");
        assertThat(model.getProperties()).containsEntry("groovy.version", GROOVY_3_VERSION);
    }

    @Test
    public void should_still_drop_a_groovy_version_property_nothing_names() {
        // the other half, so the guard is not read as "keep it always": with no declaration naming it, the
        // property goes, which is what attaching the parent is for
        var model = extensionPom();
        model.getProperties().setProperty("groovy.version", GROOVY_3_VERSION);

        runPipeline(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getProperties()).doesNotContainKey("groovy.version");
    }

    @Test
    public void should_keep_a_groovy_version_property_the_first_step_would_delete() {
        // the pipeline case the sibling test cannot reach. Its fixture pins groovy.version to 3.0.19, which
        // makes Groovy3MigrationStep.appliesTo return false, so the step that deletes the property never runs
        // and the guard is asserted in isolation. A 7.13-era pom - a 2.x groovy.version and the groovy-eclipse
        // compilerId - is the shape this PR exists to migrate, and there the first step of the pipeline removes
        // groovy.version before anything can decide whether something still names it
        var model = groovyEclipseExtensionPom("2.4.21");
        var userPinned = new Dependency();
        userPinned.setGroupId("org.codehaus.groovy");
        userPinned.setArtifactId("groovy-testng");
        userPinned.setVersion("${groovy.version}");
        userPinned.setScope("provided");
        model.getDependencies().add(userPinned);

        runPipeline(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        // groovy-testng is not a module the runtime provides, so no step rewrites it: the reference has to
        // keep resolving, or the extension ships with a version that resolves to nothing
        assertThat(userPinned.getVersion()).isEqualTo("${groovy.version}");
        // and at the version this run announces: kept at the value it was found with, the reference resolves
        // Groovy 2.4.21 under a report line saying the pom moved to Groovy 3
        assertThat(model.getProperties()).containsEntry("groovy.version", GROOVY_3_MIGRATED_VERSION);
    }

    /** A 7.13-era extension pom: Groovy 3 has not been applied yet, and the groovy-eclipse compiler is in use. */
    @Test
    public void should_leave_a_migrated_extension_taking_surefire_from_its_parent() {
        // Groovy3MigrationStep pins a surefire of its own, and Groovy4MigrationStep moves spock-core onto a
        // variant built against a far newer JUnit Platform - a pairing that pin could not run. What saves it is
        // ParentMigrationStep, last in this list: the parent pom manages that plugin, so the pin and its
        // property both go and the module takes the parent's. Nothing asserted the pairing the chain produces,
        // and the step that resolves it is in the other list, where a search for surefire does not reach
        var metadata = metadataForRuntime(GROOVY_4_RUNTIME_VERSION);
        var midChain = spockExtensionPom();
        new Groovy3MigrationStep().migrate(midChain, metadata);
        assertThat(midChain.getProperties()).containsKey(SUREFIRE_VERSION_PROPERTY);

        var model = spockExtensionPom();
        runPipeline(model, metadata);

        assertThat(model.getProperties()).doesNotContainKey(SUREFIRE_VERSION_PROPERTY);
        assertThat(managedPlugins(model))
                .noneMatch(plugin -> DefaultPluginVersions.MAVEN_SUREFIRE_PLUGIN.equals(plugin.getArtifactId()));
        assertThat(model.getDependencies())
                .filteredOn(dependency -> "spock-core".equals(dependency.getArtifactId()))
                .extracting(Dependency::getVersion)
                .containsExactly("2.4-groovy-4.0");
    }

    @Test
    public void should_leave_a_parented_extension_the_surefire_it_was_given() {
        // the other side of the test above, and the one the version this step writes is chosen for:
        // ParentMigrationStep declines a module that already declares a parent, so the pin it would otherwise
        // remove stays in the pom. It has to be a surefire that can run the Spock the Groovy 4 step writes,
        // which is why the value comes from the same place the parent takes it
        var model = spockExtensionPom();
        var parent = new Parent();
        parent.setGroupId("com.company");
        parent.setArtifactId("my-project-extensions");
        parent.setVersion("1.0.0");
        model.setParent(parent);

        runPipeline(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getParent()).isSameAs(parent);
        assertThat(model.getProperties())
                .containsEntry(SUREFIRE_VERSION_PROPERTY, DefaultPluginVersions.MAVEN_SUREFIRE_PLUGIN_VERSION);
        assertThat(managedPlugins(model))
                .anyMatch(plugin -> DefaultPluginVersions.MAVEN_SUREFIRE_PLUGIN.equals(plugin.getArtifactId()));
    }

    /** The 7.x shape both Spock steps read: Groovy-Eclipse selected, a Groovy 2 pin, and a Spock 1 pin. */
    private Model spockExtensionPom() {
        var model = groovyEclipseExtensionPom("2.4.3-01");
        var groovyAll = new Dependency();
        groovyAll.setGroupId("org.codehaus.groovy");
        groovyAll.setArtifactId("groovy-all");
        groovyAll.setVersion("${groovy.version}");
        groovyAll.setScope("provided");
        model.getDependencies().add(groovyAll);
        var spock = new Dependency();
        spock.setGroupId("org.spockframework");
        spock.setArtifactId("spock-core");
        spock.setVersion("1.0-groovy-2.4");
        spock.setScope("test");
        model.getDependencies().add(spock);
        return model;
    }

    private List<Plugin> managedPlugins(Model model) {
        if (model.getBuild() == null || model.getBuild().getPluginManagement() == null) {
            return List.of();
        }
        return model.getBuild().getPluginManagement().getPlugins();
    }

    private Model groovyEclipseExtensionPom(String groovyVersion) {
        var model = extensionPom();
        model.getProperties().setProperty("groovy.version", groovyVersion);
        var compilerPlugin = new Plugin();
        compilerPlugin.setGroupId("org.apache.maven.plugins");
        compilerPlugin.setArtifactId("maven-compiler-plugin");
        var compilerId = new Xpp3Dom("compilerId");
        compilerId.setValue("groovy-eclipse-compiler");
        var configuration = new Xpp3Dom("configuration");
        configuration.addChild(compilerId);
        compilerPlugin.setConfiguration(configuration);
        model.getBuild().addPlugin(compilerPlugin);
        return model;
    }

    /**
     * A pom shaped like the ones these steps meet: {@code <build>} present, because several steps read it without
     * checking - {@code Java11MigrationStep.appliesTo} dereferences it directly where {@code Groovy3MigrationStep}
     * guards. Not this branch's to fix, but a model without one never reaches the pipeline in practice.
     */
    private Model extensionPom() {
        var model = new Model();
        model.setBuild(new Build());
        return model;
    }

    private void runPipeline(Model model, ProjectMetadata metadata) {
        for (MavenModelMigration step : ExtensionRepositoryStore
                .migrationStepsFor(mock(RestAPIExtensionFileStore.class))) {
            if (step.appliesTo(model, metadata)) {
                step.migrate(model, metadata);
            }
        }
    }

    private ProjectMetadata metadataForRuntime(String bonitaRuntimeVersion) {
        var metadata = ProjectMetadata.defaultMetadata();
        metadata.setBonitaRuntimeVersion(bonitaRuntimeVersion);
        return metadata;
    }

    private int indexOf(List<MavenModelMigration> steps, Class<? extends MavenModelMigration> stepType) {
        for (int index = 0; index < steps.size(); index++) {
            if (stepType.isInstance(steps.get(index))) {
                return index;
            }
        }
        throw new AssertionError(String.format("%s is not in the migration steps", stepType.getSimpleName()));
    }
}
