/**
 * Copyright (C) 2023 BonitaSoft S.A.
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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.repository.core.maven.model.DefaultPluginVersions;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.m2e.core.MavenPlugin;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateMavenPluginVersionInPomStepTest {

    private Path tmpDir;

    @BeforeEach
    void setup() throws Exception {
        tmpDir = Files.createTempDirectory(UpdateMavenPluginVersionInPomStepTest.class.getSimpleName());
        Files.copy(UpdateMavenPluginVersionInPomStepTest.class.getResourceAsStream("/versions-pom.xml.test"),
                tmpDir.resolve("pom.xml"));
    }

    @AfterEach
    void cleaup() throws Exception {
        FileUtil.deleteDir(tmpDir);
    }

    @Test
    void shouldUpdatePluginsVersion() throws Exception {
        var step = new UpdateMavenPluginVersionInPomStep();
        
        var beforeStepModel = load("/versions-pom.xml.test");
        assertThat(beforeStepModel.getProperties()).containsEntry("maven-compiler-plugin.version", "3.10.1");
        
        var report = step.run(tmpDir, new NullProgressMonitor());
        
        assertThat(report.hasUpdates()).isTrue();
        var afterStepModel = load(tmpDir.resolve("pom.xml"));
        assertThat(afterStepModel.getProperties()).containsEntry("maven-compiler-plugin.version", DefaultPluginVersions.MAVEN_COMPILER_PLUGIN_VERSION);
    }
    
    @Test
    void shouldNotUpdatePluginsWithGreaterVersionsThanDefault() throws Exception {
        var step = new UpdateMavenPluginVersionInPomStep();
        
        var beforeStepModel = load("/versions-pom.xml.test");
        assertThat(beforeStepModel.getProperties()).containsEntry("maven-deploy-plugin.version", "99.0.0");
        
        step.run(tmpDir, new NullProgressMonitor());
        
        var afterStepModel = load(tmpDir.resolve("pom.xml"));
        assertThat(afterStepModel.getProperties()).containsEntry("maven-deploy-plugin.version", "99.0.0");
    }
    
    @Test
    void shouldAddMissingManagedPlugins() throws Exception {
        var step = new UpdateMavenPluginVersionInPomStep();
        
        var beforeStepModel = load("/versions-pom.xml.test");
        assertThat(beforeStepModel.getProperties()).doesNotContainKey("maven-assembly-plugin.version");
        
        step.run(tmpDir, new NullProgressMonitor());
        
        var afterStepModel = load(tmpDir.resolve("pom.xml"));
        assertThat(afterStepModel.getProperties()).containsEntry("maven-assembly-plugin.version", DefaultPluginVersions.MAVEN_ASSEMBLY_PLUGIN_VERSION);
        assertThat(afterStepModel.getBuild().getPluginManagement().getPluginsAsMap()).satisfies( pluginsAsMap -> {
            assertThat(pluginsAsMap).containsKey("org.apache.maven.plugins:maven-assembly-plugin");
            var plugin = pluginsAsMap.get("org.apache.maven.plugins:maven-assembly-plugin");
            assertThat(plugin.getArtifactId()).isEqualTo("maven-assembly-plugin");
            assertThat(plugin.getVersion()).isEqualTo("${maven-assembly-plugin.version}");
        });
    }
    
    @Test
    void shouldAppliesTo80AndAbove() throws Exception {
        var step = new UpdateMavenPluginVersionInPomStep();
        
        assertThat(step.appliesTo("8.0.0")).isTrue();
        assertThat(step.appliesTo("9.0.0")).isTrue();
        assertThat(step.appliesTo("7.15.0")).isFalse();
    }
    
    private static Model load(String resourcePath) throws IOException, CoreException {
        try (var is = BdmModelArtifactMigrationStepTest.class.getResourceAsStream(resourcePath)) {
            return MavenPlugin.getMaven().readModel(is);
        }
    }
    
    
    private static Model load(Path resourcePath) throws IOException, CoreException {
        try (var is = Files.newInputStream(resourcePath)) {
            return MavenPlugin.getMaven().readModel(is);
        }
    }

}
