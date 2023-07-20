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
package org.bonitasoft.studio.common.repository.core.maven.plugin;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.assertions.StatusAssert.assertThat;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateBdmModulePluginTest {

    private String projectId;
    private BonitaProject project;

    @BeforeEach
    void createProject() throws CoreException {
        projectId = "test-project";
        project = BonitaProject.create(projectId);
        var repository = RepositoryManager.getInstance().newRepository(project.getAppProject().getName());
        RepositoryManager.getInstance().setCurrentRepository(repository);
        project = BonitaProject.create(projectId);
        project.delete(new NullProgressMonitor());
        var metadata = new ProjectMetadata();
        metadata.setArtifactId("test-project");
        metadata.setName("Test Project");
        metadata.setGroupId("org.bonita.test");
        metadata.setBonitaRuntimeVersion(ProductVersion.BONITA_RUNTIME_VERSION);
        metadata.setVersion("1.0.0-SNAPSHOT");
        metadata.setUseSnapshotRepository(true);
        repository.create(metadata, new NullProgressMonitor());
    }

    @Test
    void executeCreateBdmModulePlugin() throws Exception {
        var projectPath = project.getParentProject().getLocation().toFile().toPath();
        var plugin = new CreateBdmModulePlugin(projectPath, projectId);
      
        var status =plugin.execute(new NullProgressMonitor());

        assertThat(status).isOK();
        assertThat(projectPath.resolve("bdm").resolve("pom.xml")).exists();
        assertThat(projectPath.resolve("bdm").resolve("bom.xml")).exists();
        assertThat(projectPath.resolve("bdm").resolve("model").resolve("pom.xml")).exists();
        assertThat(projectPath.resolve("bdm").resolve("dao-client").resolve("pom.xml")).exists();
    }

}
