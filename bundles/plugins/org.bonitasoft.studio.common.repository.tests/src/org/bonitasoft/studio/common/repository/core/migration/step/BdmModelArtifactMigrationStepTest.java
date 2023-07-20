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
import java.util.stream.Stream;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.m2e.core.MavenPlugin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class BdmModelArtifactMigrationStepTest {

    private static ProjectMetadata metadata = ProjectMetadata.defaultMetadata();

    @ParameterizedTest
    @MethodSource("provideModelAndReportMessage")
    void shouldRemoveOldBdmArtifactsAndAddNewModelArtifact(Model model, String expectedMigrationMessge) throws Exception {
        var step = new BdmModelArtifactMigrationStep();

        var report = step.migrate(model, metadata);

        assertThat(report.updates()).containsOnly(expectedMigrationMessge);
        assertThat(model.getDependencies())
                .extracting("groupId", "artifactId", "version")
                .doesNotContain(tuple("com.company.example.model", "bdm-client", "1.0.0"),
                        tuple("com.company.example.model", "bdm-dao", "1.0.0"))
                .contains(tuple("com.company", String.format("%s-bdm-model", metadata.getProjectId()), "0.0.1"));
    }

    @Test
    void shouldDoNothing() throws Exception {
        var step = new BdmModelArtifactMigrationStep();
        var model = remove(load("/pom.xml.test"), "bdm-client", "bdm-dao");
       
        var report = step.migrate(model, metadata);

        assertThat(report.updates()).isEmpty();
        assertThat(model.getDependencies())
                .extracting("groupId", "artifactId", "version")
                .doesNotContain(tuple("com.company.example.model", "bdm-client", "1.0.0"),
                        tuple("com.company.example.model", "bdm-dao", "1.0.0"))
                .doesNotContain(tuple("com.company", String.format("%s-bdm-model", metadata.getProjectId()), "0.0.1"));
    }

    private static String withDaoHint(String message) {
        message = message + "  \n\n" +
                "[NOTE]\n"
                + "====\n"
                + "If you were referencing `DAOImpl` directly in your code you may need to update the following usage:  \n"
                + "[source,java]\n"
                + "----\n"
                + "// When a direct access to DAOImpl class is used\n"
                + "BusinessObjectDAOImpl dao = new BusinessObjectDAOImpl(context.getApiSession())\n\n"
                + "// Use the DAO interfaces and the DAO factory from the ApiClient instead\n"
                + "BusinessObjectDAO dao = context.getApiClient().getDAO(BusinessObjectDAO.class)\n"
                + "----\n"
                + "====\n";
        return message;
    }
    
    private static Stream<Arguments> provideModelAndReportMessage() throws IOException, CoreException {
        var model = load("/pom.xml.test");
        var modelVersionProperty = load("/pom.xml.version.property.test");
        return Stream.of(
                Arguments.of(model.clone(), withDaoHint(String.format(
                        "`com.company.example.model:bdm-client:jar:1.0.0` and `com.company.example.model:bdm-dao:jar:1.0.0` dependencies have been replaced with `com.company:%s-bdm-model:jar:0.0.1`.",
                        metadata.getProjectId()))),
                Arguments.of(modelVersionProperty.clone(), withDaoHint(String.format(
                        "`com.company.example.model:bdm-client:jar:${my.bdm.version}` and `com.company.example.model:bdm-dao:jar:${my.bdm.version}` dependencies have been replaced with `com.company:%s-bdm-model:jar:0.0.1`.",
                        metadata.getProjectId()))),
                Arguments.of(remove(model.clone(), "bdm-dao"), String.format(
                        "`com.company.example.model:bdm-client:jar:1.0.0` dependency has been replaced with `com.company:%s-bdm-model:jar:0.0.1`.",
                        metadata.getProjectId())),
                Arguments.of(remove(model.clone(), "bdm-client"), withDaoHint(String.format(
                        "`com.company.example.model:bdm-dao:jar:1.0.0` dependency has been replaced with `com.company:%s-bdm-model:jar:0.0.1`.",
                        metadata.getProjectId())))
              );
    }

    private static Model remove(Model model, String... artifactIds) {
        for(var artifactId : artifactIds) {
            model.getDependencies().removeIf(dep -> dep.getArtifactId().equals(artifactId));
        }
        return model;
    }

    @Test
    void shouldAppliesToProjectWithOldBdmArtifacts() throws Exception {
        var step = new BdmModelArtifactMigrationStep();
        var model = load("/pom.xml.nobdm.test");

        assertThat(step.appliesTo(model, metadata)).isFalse();
    }

    private static Model load(String resourcePath) throws IOException, CoreException {
        try (var is = BdmModelArtifactMigrationStepTest.class.getResourceAsStream(resourcePath)) {
            return MavenPlugin.getMaven().readModel(is);
        }
    }

}
