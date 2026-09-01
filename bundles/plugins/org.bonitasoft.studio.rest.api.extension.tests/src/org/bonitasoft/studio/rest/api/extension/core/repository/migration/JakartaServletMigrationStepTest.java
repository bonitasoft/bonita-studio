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
import org.apache.maven.model.Dependency;
import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.Model;
import org.apache.maven.model.Profile;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.junit.Before;
import org.junit.Test;

public class JakartaServletMigrationStepTest {

    private static final String JAKARTA_RUNTIME_VERSION = "12.0.0";
    private static final String PRE_JAKARTA_RUNTIME_VERSION = "10.2.0";

    private MavenXpp3Reader modelReader = new MavenXpp3Reader();
    private JakartaServletMigrationStep migrationStep;

    @Before
    public void createFixture() throws Exception {
        migrationStep = new JakartaServletMigrationStep();
    }

    @Test
    public void should_apply_to_an_extension_declaring_the_javax_servlet_api() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_5_0.xml");

        // When
        boolean appliesTo = migrationStep.appliesTo(model, metadataForRuntime(JAKARTA_RUNTIME_VERSION));

        // Then
        assertThat(appliesTo).isTrue();
    }

    @Test
    public void should_not_apply_to_an_extension_targeting_a_pre_jakarta_runtime() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_5_0.xml");

        // When
        boolean appliesTo = migrationStep.appliesTo(model, metadataForRuntime(PRE_JAKARTA_RUNTIME_VERSION));

        // Then
        assertThat(appliesTo).isFalse();
    }

    @Test
    public void should_apply_to_an_extension_declaring_the_javax_servlet_api_in_a_profile() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");
        model.getProfiles().add(profileDeclaringJavaxServletApi("legacy"));

        // When
        boolean appliesTo = migrationStep.appliesTo(model, metadataForRuntime(JAKARTA_RUNTIME_VERSION));

        // Then
        assertThat(appliesTo).isTrue();
    }

    @Test
    public void should_not_apply_to_an_extension_without_any_servlet_api_dependency() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");

        // When
        boolean appliesTo = migrationStep.appliesTo(model, metadataForRuntime(JAKARTA_RUNTIME_VERSION));

        // Then
        assertThat(appliesTo).isFalse();
    }

    @Test
    public void should_replace_the_javax_servlet_api_dependency_by_the_jakarta_one() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_5_0.xml");

        // When
        var report = migrationStep.migrate(model, metadataForRuntime(JAKARTA_RUNTIME_VERSION));

        // Then
        assertThat(model.getDependencies()).extracting("groupId", "artifactId", "version", "scope")
                .contains(tuple("jakarta.servlet", "jakarta.servlet-api", null, "provided"))
                .doesNotContain(tuple("javax.servlet", "javax.servlet-api", null, "provided"));
        assertThat(report.updates())
                .anyMatch(message -> message.contains("jakarta.servlet:jakarta.servlet-api"))
                .anyMatch(message -> message.startsWith("Action required:"));
    }

    @Test
    public void should_keep_the_dependency_position_and_scope_when_replacing_it() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_5_0.xml");
        int javaxServletApiIndex = model.getDependencies().indexOf(javaxServletApiOf(model));

        // When
        migrationStep.migrate(model, metadataForRuntime(JAKARTA_RUNTIME_VERSION));

        // Then
        Dependency migrated = model.getDependencies().get(javaxServletApiIndex);
        assertThat(migrated.getArtifactId()).isEqualTo("jakarta.servlet-api");
        assertThat(migrated.getScope()).isEqualTo(Artifact.SCOPE_PROVIDED);
    }

    @Test
    public void should_remove_the_javax_servlet_api_version_managed_by_the_extension() throws Exception {
        // Given a pom generated by an archetype pinning the servlet API version itself
        Model model = loadModel("pom_from_1_5_0.xml");
        managedDependencies(model).add(newDependency("javax.servlet", "javax.servlet-api", "3.1.0", null));

        // When
        var report = migrationStep.migrate(model, metadataForRuntime(JAKARTA_RUNTIME_VERSION));

        // Then
        assertThat(managedDependencies(model)).extracting("groupId", "artifactId")
                .doesNotContain(tuple("javax.servlet", "javax.servlet-api"));
        assertThat(report.removals()).anyMatch(message -> message.contains("javax.servlet:javax.servlet-api"));
    }

    @Test
    public void should_drop_the_duplicate_declaration_when_the_jakarta_servlet_api_is_already_declared()
            throws Exception {
        // Given
        Model model = loadModel("pom_from_1_5_0.xml");
        model.getDependencies()
                .add(newDependency("jakarta.servlet", "jakarta.servlet-api", null, Artifact.SCOPE_PROVIDED));

        // When
        var report = migrationStep.migrate(model, metadataForRuntime(JAKARTA_RUNTIME_VERSION));

        // Then
        assertThat(model.getDependencies()).extracting("groupId", "artifactId", "version", "scope")
                .containsOnlyOnce(tuple("jakarta.servlet", "jakarta.servlet-api", null, Artifact.SCOPE_PROVIDED));
        assertThat(model.getDependencies()).extracting("groupId", "artifactId")
                .doesNotContain(tuple("javax.servlet", "javax.servlet-api"));
        assertThat(report.removals()).anyMatch(message -> message.contains("javax.servlet:javax.servlet-api"));
    }

    @Test
    public void should_normalize_a_jakarta_servlet_api_dependency_the_extension_pinned_itself() throws Exception {
        // Given a customer hand-patch working around the jakarta move: pinned, at the default compile scope, and
        // declared before the javax dependency Studio wrote
        Model model = loadModel("pom_from_1_5_0.xml");
        model.getDependencies().add(0, newDependency("jakarta.servlet", "jakarta.servlet-api", "5.0.0", null));

        // When
        var report = migrationStep.migrate(model, metadataForRuntime(JAKARTA_RUNTIME_VERSION));

        // Then
        assertThat(model.getDependencies()).extracting("groupId", "artifactId", "version", "scope")
                .containsOnlyOnce(tuple("jakarta.servlet", "jakarta.servlet-api", null, Artifact.SCOPE_PROVIDED));
        assertThat(model.getDependencies()).extracting("groupId", "artifactId")
                .doesNotContain(tuple("javax.servlet", "javax.servlet-api"));
        assertThat(report.updates())
                .anyMatch(message -> message.contains("provided scope"))
                .anyMatch(message -> message.startsWith("Action required:"));
    }

    @Test
    public void should_drop_every_extra_javax_servlet_api_declaration() throws Exception {
        // Given a pom declaring the servlet API twice
        Model model = loadModel("pom_from_1_5_0.xml");
        model.getDependencies()
                .add(newDependency("javax.servlet", "javax.servlet-api", "3.1.0", Artifact.SCOPE_PROVIDED));

        // When
        migrationStep.migrate(model, metadataForRuntime(JAKARTA_RUNTIME_VERSION));

        // Then
        assertThat(model.getDependencies()).extracting("groupId", "artifactId")
                .containsOnlyOnce(tuple("jakarta.servlet", "jakarta.servlet-api"))
                .doesNotContain(tuple("javax.servlet", "javax.servlet-api"));
    }

    @Test
    public void should_report_without_rewriting_the_javax_servlet_api_dependency_declared_by_a_profile()
            throws Exception {
        // Given a profile holding the configuration of an older runtime, which must not be rewritten
        Model model = loadModel("pom_from_1_0_5.xml");
        var profile = profileDeclaringJavaxServletApi("bonita-11");
        model.getProfiles().add(profile);

        // When
        var report = migrationStep.migrate(model, metadataForRuntime(JAKARTA_RUNTIME_VERSION));

        // Then
        assertThat(profile.getDependencies()).extracting("groupId", "artifactId")
                .containsOnly(tuple("javax.servlet", "javax.servlet-api"));
        assertThat(report.updates()).anyMatch(message -> message.startsWith("Action required:")
                && message.contains("`bonita-11` profile"));
    }

    @Test
    public void should_report_the_previous_version_when_the_extension_pinned_one() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_5_0.xml");
        javaxServletApiOf(model).setVersion("3.1.0");

        // When
        var report = migrationStep.migrate(model, metadataForRuntime(JAKARTA_RUNTIME_VERSION));

        // Then
        assertThat(report.updates()).anyMatch(message -> message.contains("javax.servlet:javax.servlet-api:3.1.0"));
    }

    private Profile profileDeclaringJavaxServletApi(String id) {
        var profile = new Profile();
        profile.setId(id);
        profile.addDependency(newDependency("javax.servlet", "javax.servlet-api", null, Artifact.SCOPE_PROVIDED));
        return profile;
    }

    private Dependency javaxServletApiOf(Model model) {
        return model.getDependencies().stream()
                .filter(ServletApi.JAVAX::matches)
                .findFirst()
                .orElseThrow(() -> new AssertionError("The fixture must declare javax.servlet:javax.servlet-api"));
    }

    private List<Dependency> managedDependencies(Model model) {
        if (model.getDependencyManagement() == null) {
            model.setDependencyManagement(new DependencyManagement());
        }
        return model.getDependencyManagement().getDependencies();
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
        try (InputStream is = JakartaServletMigrationStepTest.class.getResourceAsStream("/migration/" + resourceName)) {
            return modelReader.read(is);
        }
    }

}
