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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.assertj.core.api.Assertions.tuple;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.junit.Before;
import org.junit.Test;

public class Groovy3MigrationStepTest {

    private MavenXpp3Reader modelReader = new MavenXpp3Reader();
    private Groovy3MigrationStep migrationStep;

    @Before
    public void createFixture() throws Exception {
        migrationStep = new Groovy3MigrationStep();
    }

    @Test
    public void should_update_groovy_dependency() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");
        assertThat(model.getDependencies()).extracting("groupId",
                "artifactId",
                "version",
                "type",
                "scope")
                .contains(tuple("org.codehaus.groovy",
                        "groovy-all",
                        "2.4.4",
                        "jar",
                        "provided"));

        // When
        migrationStep.migrate(model);

        // Then
        assertThat(model.getDependencies()).extracting("groupId",
                "artifactId",
                "version",
                "type",
                "scope")
                .contains(tuple("org.codehaus.groovy",
                        "groovy-all",
                        "${groovy-all.version}",
                        "pom",
                        "provided"))
                .contains(tuple("org.codehaus.groovy",
                        "groovy-dateutil",
                        "${groovy-all.version}",
                        "jar",
                        "provided"));
    }

    @Test
    public void should_update_spock_dependency() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");
        assertThat(model.getDependencies()).extracting("groupId",
                "artifactId",
                "version",
                "type",
                "scope")
                .contains(tuple("org.spockframework",
                        "spock-core",
                        "1.0-groovy-2.4",
                        "jar",
                        "test"));

        // When
        migrationStep.migrate(model);

        // Then
        assertThat(model.getDependencies()).extracting("groupId",
                "artifactId",
                "version",
                "type",
                "scope")
                .contains(tuple("org.spockframework",
                        "spock-core",
                        "${spock.version}",
                        "jar",
                        "test"));
    }

    @Test
    public void should_add_groovy_version_property() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");

        // When
        migrationStep.migrate(model);

        // Then
        assertThat(model.getProperties()).contains(entry("groovy-all.version", "3.0.8"));
    }

    @Test
    public void should_add_spock_version_property() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");

        // When
        migrationStep.migrate(model);

        // Then
        assertThat(model.getProperties()).contains(entry("spock.version", "2.0-groovy-3.0"));
    }

    @Test
    public void should_remove_groovy_version_property() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");
        assertThat(model.getProperties()).containsKey("groovy.version");

        // When
        migrationStep.migrate(model);

        // Then
        assertThat(model.getProperties()).doesNotContainKey("groovy.version");
    }

    @Test
    public void should_update_maven_compiler_plugin_version() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");
        assertThat(model.getBuild().getPlugins()).extracting("groupId",
                "artifactId",
                "version")
                .contains(tuple("org.apache.maven.plugins", "maven-compiler-plugin", "3.6.0"));

        // When
        migrationStep.migrate(model);

        // Then
        assertThat(model.getBuild().getPlugins()).extracting("groupId",
                "artifactId",
                "version")
                .contains(
                        tuple("org.apache.maven.plugins", "maven-compiler-plugin", "${maven-compiler-plugin.version}"));
    }

    @Test
    public void should_update_maven_compiler_plugin_dependencies() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");
        assertThat(findPluginDependencies(model, "org.apache.maven.plugins", "maven-compiler-plugin"))
                .extracting("groupId",
                        "artifactId",
                        "version")
                .contains(tuple("org.codehaus.groovy",
                        "groovy-eclipse-compiler",
                        "2.9.2-01"))
                .contains(tuple("org.codehaus.groovy",
                        "groovy-eclipse-batch",
                        "${groovy.version}"));

        // When
        migrationStep.migrate(model);

        // Then
        assertThat(findPluginDependencies(model, "org.apache.maven.plugins", "maven-compiler-plugin"))
                .extracting("groupId",
                        "artifactId",
                        "version")
                .contains(tuple("org.codehaus.groovy",
                        "groovy-eclipse-compiler",
                        "${groovy-eclipse-compiler.version}"))
                .contains(tuple("org.codehaus.groovy",
                        "groovy-eclipse-batch",
                        "${groovy-eclipse-batch.version}"));
    }

    @Test
    public void should_add_groovy_eclipse_compiler_version_property() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");

        // When
        migrationStep.migrate(model);

        // Then
        assertThat(model.getProperties()).contains(entry("groovy-eclipse-compiler.version", "3.7.0"));
    }

    @Test
    public void should_add_groovy_eclipse_batch_version_property() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");

        // When
        migrationStep.migrate(model);

        // Then
        assertThat(model.getProperties()).contains(entry("groovy-eclipse-batch.version", "3.0.8-01"));
    }

    @Test
    public void should_add_maven_compiler_plugin_version_property() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");

        // When
        migrationStep.migrate(model);

        // Then
        assertThat(model.getProperties()).contains(entry("maven-compiler-plugin.version", "3.8.1"));
    }

    @Test
    public void should_remove_bintray_plugin_repository() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_1_1.xml");
        assertThat(model.getPluginRepositories())
                .extracting("id", "url")
                .containsOnly(tuple("bintray", "https://dl.bintray.com/groovy/maven"));

        // When
        migrationStep.migrate(model);

        // Then
        assertThat(model.getPluginRepositories()).isEmpty();
    }

    @Test
    public void should_add_surefire_plugin_in_plugin_management() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_1_1.xml");

        // When
        migrationStep.migrate(model);

        // Then
        assertThat(model.getBuild().getPluginManagement().getPlugins())
                .extracting("artifactId", "version")
                .contains(tuple("maven-surefire-plugin", "${maven-surefire-plugin.version}"));
    }

    @Test
    public void should_add_maven_surefire_plugin_version_property() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");

        // When
        migrationStep.migrate(model);

        // Then
        assertThat(model.getProperties()).contains(entry("maven-surefire-plugin.version", "2.22.2"));
    }

    @Test
    public void should_migration_step_applies_on_groovy_project() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");
        Model model2 = loadModel("pom_from_1_1_1.xml");
        Model model3 = loadModel("pom_from_1_3_1.xml");

        // Expect
        assertThat(migrationStep.appliesTo(model)).isTrue();
        assertThat(migrationStep.appliesTo(model2)).isTrue();
        assertThat(migrationStep.appliesTo(model3)).isTrue();
    }

    @Test
    public void should_migration_step_not_applies_on_java_project() throws Exception {
        // Given
        Model model = loadModel("pom_java.xml");

        // Expect
        assertThat(migrationStep.appliesTo(model)).isFalse();
    }

    @Test
    public void should_migration_step_not_applies_on_groovy3_project() throws Exception {
        // Given
        Model model = loadModel("pom_groovy3.xml");

        // Expect
        assertThat(migrationStep.appliesTo(model)).isFalse();
    }

    private Model loadModel(String resourceName) throws IOException, XmlPullParserException {
        try (InputStream is = Groovy3MigrationStepTest.class.getResourceAsStream("/migration/" + resourceName)) {
            return modelReader.read(is);
        }
    }

    private List<Dependency> findPluginDependencies(Model model, String groupId, String artifactId) {
        return model.getBuild().getPlugins().stream()
                .filter(p -> Objects.equals(groupId, p.getGroupId()))
                .filter(p -> Objects.equals(artifactId, p.getArtifactId()))
                .map(Plugin::getDependencies)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

}
