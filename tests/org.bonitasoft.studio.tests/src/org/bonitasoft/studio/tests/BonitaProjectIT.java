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
package org.bonitasoft.studio.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.Assert.assertNotSame;

import org.apache.maven.model.Model;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.engine.connector.AbstractConnector;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.BonitaProjectNature;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.groovy.repository.ProvidedGroovyRepositoryStore;
import org.bonitasoft.studio.identity.organization.repository.OrganizationRepositoryStore;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.eclipse.m2e.core.project.IMavenProjectFacade;
import org.junit.Test;

public class BonitaProjectIT {

    @Test
    public void should_create_a_bonita_project() throws Exception {
        TeamRepositoryUtil.createNewLocalRepository("New Test Project", AbstractRepository.NULL_PROGRESS_MONITOR);

        AbstractRepository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
        // Validate the default maven model
        IProject project = currentRepository.getProject();
        IMarker[] markers = project.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
        for (IMarker m : markers) {
            BonitaStudioLog.warning(m.toString(), "org.bonitasoft.studio.tests");
        }
        assertThat(markers).isEmpty();
        IJavaProject javaProject = currentRepository.getJavaProject();
        IMavenProjectFacade mavenProjectFacade = MavenPlugin.getMavenProjectRegistry().getProject(project);
        assertThat(mavenProjectFacade).isNotNull();

        MavenProject mavenProject = mavenProjectFacade.getMavenProject();
        Model model = mavenProject.getModel();
        assertThat(model.getGroupId()).isEqualTo("com.company");
        assertThat(model.getArtifactId()).isEqualTo("new-test-project");
        assertThat(model.getVersion()).isEqualTo("1.0.0-SNAPSHOT");
        assertThat(model.getName()).isEqualTo("New Test Project");
        assertThat(model.getProperties()).contains(entry("bonita.version", ProductVersion.mavenVersion()));

        // Validate the project natures and builders
        assertThat(project.getDescription().getNatureIds()).containsOnly(BonitaProjectNature.NATURE_ID,
                JavaCore.NATURE_ID,
                "org.eclipse.jdt.groovy.core.groovyNature",
                IMavenConstants.NATURE_ID);

        assertThat(project.getDescription().getBuildSpec()).extracting(ICommand::getBuilderName)
                .containsOnly(IMavenConstants.BUILDER_ID,
                        "org.eclipse.jdt.core.javabuilder",
                        "org.eclipse.wst.validation.validationbuilder");

        // Check default organization 
        OrganizationRepositoryStore orgaStore = currentRepository
                .getRepositoryStore(OrganizationRepositoryStore.class);
        assertNotSame(0, orgaStore.getChildren().size());

        // Check provided scripts 
        ProvidedGroovyRepositoryStore providedScriptStore = currentRepository
                .getRepositoryStore(ProvidedGroovyRepositoryStore.class);
        assertNotSame(0, providedScriptStore.getChildren().size());

        assertThat(javaProject.findType("BonitaUsers")).isNotNull(); // provided script are compiling
        assertThat(javaProject.findType(AbstractConnector.class.getName())).isNotNull(); // classes in dependencies are in classpath
    }

}
