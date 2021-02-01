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

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.BonitaProjectNature;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.team.TeamRepositoryUtil;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.eclipse.swt.widgets.Display;
import org.junit.Test;

public class BonitaProjectIT {

    private MavenXpp3Reader reader = new MavenXpp3Reader();
    
    @Test
    public void should_create_a_bonita_project() throws Exception {

        // Create a new project
        new ProgressMonitorDialog(Display.getDefault().getActiveShell())
                .run(true, false,
                        monitor -> TeamRepositoryUtil.createNewLocalRepository("Bonita Test Project", monitor));


        // Validate the default maven model
        IProject project = RepositoryManager.getInstance().getCurrentRepository().getProject();
        assertThat(project.getFile("pom.xml").exists()).isTrue();
        
        Model model = reader.read(project.getFile("pom.xml").getContents());
        assertThat(model.getGroupId()).isEqualTo("com.company");
        assertThat(model.getArtifactId()).isEqualTo("bonita-test-project");
        assertThat(model.getVersion()).isEqualTo("1.0.0-SNAPSHOT");
        assertThat(model.getName()).isEqualTo("Bonita Test Project");
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
    }

}
