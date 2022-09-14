/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.rest.api.extension.core.repository.migration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Optional;

import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.Xpp3Dom;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.junit.Before;
import org.junit.Test;

public class Java11MigrationStepTest {
    
    private MavenXpp3Reader modelReader = new MavenXpp3Reader();
    private Java11MigrationStep migrationStep;

    @Before
    public void createFixture() throws Exception {
        migrationStep = new Java11MigrationStep();
    }
    
    @Test
    public void should_add_java_version_property() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");

        // When
        migrationStep.migrate(model);

        // Then
        assertThat(model.getProperties()).contains(entry("java.version", "11"));
    }
    
    @Test
    public void should_update_maven_compiler_plugin_configuration() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");
        assertThat(findPluginConfiguration(model, "org.apache.maven.plugins", "maven-compiler-plugin"))
                .isPresent()
                .hasValueSatisfying(c -> {
                    assertThat(c.getChild("source").getValue()).isEqualTo("1.8");
                    assertThat(c.getChild("target").getValue()).isEqualTo("1.8");
                });

        // When
        migrationStep.migrate(model);

        // Then
        assertThat(findPluginConfiguration(model, "org.apache.maven.plugins", "maven-compiler-plugin"))
                .isPresent()
                .hasValueSatisfying(c -> {
                    assertThat(c.getChild("source")).isNull();
                    assertThat(c.getChild("target")).isNull();
                });
        
        assertThat(model.getProperties().get("maven.compiler.source")).isEqualTo("${java.version}");
        assertThat(model.getProperties().get("maven.compiler.target")).isEqualTo("${java.version}");
    }
    
    private Optional<Xpp3Dom> findPluginConfiguration(Model model, String groupId, String artifactId) {
        if (model.getBuild() == null) {
            return Optional.empty();
        }
        return model.getBuild().getPlugins().stream()
                .filter(p -> Objects.equals(groupId, p.getGroupId()))
                .filter(p -> Objects.equals(artifactId, p.getArtifactId()))
                .map(Plugin::getConfiguration)
                .map(Xpp3Dom.class::cast)
                .findFirst();
    }
    
    
    private Model loadModel(String resourceName) throws IOException, XmlPullParserException {
        try (InputStream is = Groovy3MigrationStepTest.class.getResourceAsStream("/migration/" + resourceName)) {
            return modelReader.read(is);
        }
    }

}
