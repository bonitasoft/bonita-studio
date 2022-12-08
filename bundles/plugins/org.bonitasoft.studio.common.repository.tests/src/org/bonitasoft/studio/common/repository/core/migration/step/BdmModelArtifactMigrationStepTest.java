/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
import static org.assertj.core.api.Assertions.tuple;

import java.io.IOException;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.m2e.core.MavenPlugin;
import org.junit.jupiter.api.Test;

class BdmModelArtifactMigrationStepTest {

    private ProjectMetadata metadata = ProjectMetadata.defaultMetadata();

    @Test
    void shouldRemoveOldBdmArtifactsAndAddNewModelArtifact() throws Exception {
        var step = new BdmModelArtifactMigrationStep();
        var model = load("/pom.xml.test");
        
        var report = step.migrate(model, metadata);
        
        assertThat(report.additions()).containsOnly(String.format("`com.company:%s-bdm-model:jar:0.0.1` dependency has been added.", metadata.getProjectId()));
        assertThat(report.removals())
            .containsOnly("`com.company.example.model:bdm-client:jar:1.0.0` has been removed.",
                    "`com.company.example.model:bdm-dao:jar:1.0.0` has been removed.");
        assertThat(model.getDependencies())
            .extracting("groupId", "artifactId", "version")
            .doesNotContain(tuple("com.company.example.model", "bdm-client", "1.0.0"),
                            tuple("com.company.example.model", "bdm-dao", "1.0.0"))
            .contains(tuple("com.company", String.format("%s-bdm-model", metadata.getProjectId()), "0.0.1"));
    }
    
    @Test
    void shouldAppliesToProjectWithOldBdmArtifacts() throws Exception {
        var step = new BdmModelArtifactMigrationStep();
        var model = load("/pom.xml.nobdm.test");
        
        assertThat(step.appliesTo(model, metadata)).isFalse();
    }

    private Model load(String resourcePath) throws IOException, CoreException {
        try (var is = BdmModelArtifactMigrationStepTest.class.getResourceAsStream(resourcePath)) {
            return MavenPlugin.getMaven().readModel(is);
        }
    }

}
