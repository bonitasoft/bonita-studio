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
package org.bonitasoft.studio.common.repository.core;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

import org.bonitasoft.studio.common.repository.BonitaProjectNature;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateBonitaProjectOperationTest {

    @AfterEach
    @BeforeEach
    void cleanWorkspace() throws Exception {
        Stream.of(ResourcesPlugin.getWorkspace().getRoot().getProjects()).forEach(p -> {
            try {
                p.delete(true, new NullProgressMonitor());
            } catch (CoreException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Test
    void createBonitaProject() throws Exception {
        var metadata = new ProjectMetadata();
        metadata.setName("procurement");
        metadata.setArtifactId("procurement");
        metadata.setGroupId("com.company");
        metadata.setVersion("1.0.0-SNAPSHOT");
        metadata.setBonitaRuntimeVersion("7.15.0");
        var workspace = ResourcesPlugin.getWorkspace();
        var operation = new CreateBonitaProjectOperation(workspace, metadata);

        operation.run(new NullProgressMonitor());

        var project = workspace.getRoot().getProject("procurement");
        assertThat(project.exists()).isTrue();
        assertThat(project.getLocation()).isEqualTo(workspace.getRoot().getLocation().append("procurement"));
        assertThat(project.isNatureEnabled(IMavenConstants.NATURE_ID)).isTrue();
       
        assertThat(project.isNatureEnabled(BonitaProjectNature.NATURE_ID)).isFalse();
        assertThat(project.isNatureEnabled(JavaCore.NATURE_ID)).isFalse();

        var mavenProjectFacade = MavenPlugin.getMavenProjectRegistry().getProject(project);
        assertThat(mavenProjectFacade).isNotNull();
        var mavenProject = mavenProjectFacade.getMavenProject(new NullProgressMonitor());
        assertThat(mavenProject.getArtifactId()).isEqualTo("procurement-parent");
        assertThat(mavenProject.getVersion()).isEqualTo("1.0.0-SNAPSHOT");
        assertThat(mavenProject.getGroupId()).isEqualTo("com.company");
        
        var appProject = workspace.getRoot().getProject("procurement-app");
        assertThat(appProject.getLocation()).isEqualTo(workspace.getRoot().getLocation().append("procurement").append("app"));
        assertThat(appProject.isNatureEnabled(IMavenConstants.NATURE_ID)).isTrue();
        assertThat(appProject.isNatureEnabled(BonitaProjectNature.NATURE_ID)).isTrue();
        assertThat(appProject.isNatureEnabled(JavaCore.NATURE_ID)).isTrue();
        assertThat(appProject.isNatureEnabled("org.eclipse.jdt.groovy.core.groovyNature")).isTrue();
        var mavenAppProjectFacade = MavenPlugin.getMavenProjectRegistry().getProject(appProject);
        assertThat(mavenAppProjectFacade).isNotNull();
        var mavenappProject = mavenAppProjectFacade.getMavenProject(new NullProgressMonitor());
        assertThat(mavenappProject.getArtifactId()).isEqualTo("procurement");
        assertThat(mavenappProject.getVersion()).isEqualTo("1.0.0-SNAPSHOT");
        assertThat(mavenappProject.getGroupId()).isEqualTo("com.company");
    }

}
