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

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.junit.Before;
import org.junit.Test;

public class RuntimeBOMMigrationStepTest {

    private MavenXpp3Reader modelReader = new MavenXpp3Reader();
    private RuntimeBOMMigrationStep migrationStep;

    @Before
    public void createFixture() throws Exception {
        migrationStep = new RuntimeBOMMigrationStep();
    }

    @Test
    public void should_add_runtime_bom_import() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");

        // When
        migrationStep.migrate(model);

        // Then
        assertThat(model.getDependencyManagement().getDependencies()).extracting("groupId",
                "artifactId",
                "version",
                "type",
                "scope")
                .contains(tuple("org.bonitasoft.runtime",
                        "bonita-runtime-bom",
                        "${bonita.version}",
                        "pom",
                        "import"));
    }

    @Test
    public void should_remove_version_from_dependencies_in_bom() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");

        // When
        migrationStep.migrate(model);

        // Then
        assertThat(model.getDependencies()).extracting("groupId",
                "artifactId",
                "version",
                "scope")
                .contains(tuple("com.bonitasoft.web",
                        "bonita-web-extensions-sp",
                        null,
                        "provided"),
                        tuple("org.codehaus.groovy",
                                "groovy-all",
                                null,
                                "provided"));
    }

    @Test
    public void should_add_javax_servlet_api_dependency() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");

        // When
        migrationStep.migrate(model);

        // Then
        assertThat(model.getDependencies()).extracting("groupId",
                "artifactId",
                "version",
                "scope")
                .contains(tuple("javax.servlet",
                        "javax.servlet-api",
                        null,
                        "provided"));
    }

    private Model loadModel(String resourceName) throws IOException, XmlPullParserException {
        try (InputStream is = Groovy3MigrationStepTest.class.getResourceAsStream("/migration/" + resourceName)) {
            return modelReader.read(is);
        }
    }

}
