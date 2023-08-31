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
import static org.assertj.core.api.Assertions.tuple;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.DefaultModelReader;
import org.apache.maven.model.io.ModelReader;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.junit.Before;
import org.junit.Test;

public class ParentMigrationStepTest {

    private ModelReader modelReader = new DefaultModelReader();
    private ParentMigrationStep migrationStep;
    private ProjectMetadata metadata = ProjectMetadata.defaultMetadata();

    @Before
    public void createFixture() throws Exception {
        migrationStep = new ParentMigrationStep();
    }

    @Test
    public void should_add_parent_artifact() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");

        // When
        migrationStep.migrate(model, metadata);

        // Then
        assertThat(model.getParent()).isNotNull();
        assertThat(model.getParent()).extracting("groupId", "artifactId", "version")
            .containsExactly(metadata.getGroupId(), metadata.getArtifactId() + "-extensions", metadata.getVersion());
    }

    @Test
    public void should_remove_groupId_and_version() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");

        // When
        migrationStep.migrate(model, metadata);

        // Then
        assertThat(model.getGroupId()).isNull();
        assertThat(model.getVersion()).isNull();
    }
    
    @Test
    public void should_remove_inherited_properties() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");

        // When
        migrationStep.migrate(model, metadata);

        // Then
        assertThat(model.getProperties()).isEmpty();
    }
    
    @Test
    public void should_remove_obsolete_properties() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_5_0.xml");

        // When
        migrationStep.migrate(model, metadata);

        // Then
        assertThat(model.getProperties())
            .containsOnlyKeys("spock.version", "groovy-eclipse-compiler.version", "groovy-eclipse-batch.version");
    }

    @Test
    public void should_remove_inherited_plugin_versions() throws Exception {
        // Given
        Model model = loadModel("pom_java.xml");

        // When
        migrationStep.migrate(model, metadata);

        // Then
        assertThat(model.getBuild().getPlugins()).extracting("version").allMatch(Objects::isNull);
    }
    
    
    @Test
    public void should_remove_pluginManagement_section() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_5_0.xml");
        metadata.setArtifactId("serenity");

        // When
        migrationStep.migrate(model, metadata);

        // Then
        assertThat(model.getBuild().getPluginManagement()).isNull();
    }
    
    @Test
    public void should_update_bdm_model_groupId_and_version() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_5_0.xml");
        metadata.setArtifactId("serenity");

        // When
        migrationStep.migrate(model, metadata);

        // Then
        assertThat(model.getDependencies()).extracting("groupId","artifactId","version")
            .contains(tuple("${project.groupId}","serenity-bdm-model","${project.version}"));
    }
    
    @Test
    public void should_remove_runtime_bom_import() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_5_0.xml");

        // When
        migrationStep.migrate(model, metadata);

        // Then
        assertThat(model.getDependencyManagement()).isNull();
    }
    
    @Test
    public void should_apply_when_no_parent_defined() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_5_0.xml");

        assertThat(migrationStep.appliesTo(model, metadata)).isTrue();
        
        migrationStep.migrate(model, metadata);
        
        assertThat(migrationStep.appliesTo(model, metadata)).isFalse();
    }
    
    private Model loadModel(String resourceName) throws IOException, XmlPullParserException {
        try (InputStream is = Groovy3MigrationStepTest.class.getResourceAsStream("/migration/" + resourceName)) {
            return modelReader.read(is, Map.of());
        }
    }

}
