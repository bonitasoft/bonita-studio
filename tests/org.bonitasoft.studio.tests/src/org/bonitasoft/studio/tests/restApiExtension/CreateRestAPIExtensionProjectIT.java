/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.tests.restApiExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetypeConfiguration;
import org.bonitasoft.studio.rest.api.extension.core.maven.CreateRestAPIExtensionProjectOperation;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.eclipse.swt.SWT;
import org.junit.After;
import org.junit.Test;

public class CreateRestAPIExtensionProjectIT {

    @After
    public void tearDown() throws Exception {
        final IFolder createdFolder = RepositoryManager.getInstance().getRepositoryStore(RestAPIExtensionRepositoryStore.class).getResource()
                .getFolder("my-rest-api");
        if (createdFolder.exists()) {
            createdFolder.delete(true, AbstractRepository.NULL_PROGRESS_MONITOR);
        }
    }

    @Test
    public void should_create_a_rest_api_extension_project_in_workspace() throws Exception {
        RestAPIExtensionArchetypeConfiguration defaultArchetypeConfiguration = RestAPIExtensionArchetypeConfiguration.defaultArchetypeConfiguration();
        defaultArchetypeConfiguration.setBonitaVersion(ProductVersion.mavenVersion());
        final CreateRestAPIExtensionProjectOperation operation = new CreateRestAPIExtensionProjectOperation(
                RepositoryManager.getInstance().getRepositoryStore(RestAPIExtensionRepositoryStore.class),
                MavenPlugin.getProjectConfigurationManager(),
                new ProjectImportConfiguration(),
                defaultArchetypeConfiguration);

        operation.run(AbstractRepository.NULL_PROGRESS_MONITOR);

        Job.getJobManager().join(CreateRestAPIExtensionProjectOperation.class, AbstractRepository.NULL_PROGRESS_MONITOR);

        StatusAssert.assertThat(operation.getStatus()).isOK();
        assertThat(operation.getProjects()).hasSize(1);

        final IProject newProject = operation.getProjects().get(0);
        final IProjectDescription description = newProject.getDescription();
        assertThat(description.getNatureIds()).contains(JavaCore.NATURE_ID,
                "org.eclipse.jdt.groovy.core.groovyNature",
                "org.eclipse.m2e.core.maven2Nature");
        assertThat(description.getBuildSpec()).extracting("name").contains(
                "org.eclipse.m2e.core.maven2Builder",
                "org.eclipse.jdt.core.javabuilder");

        assertThat(newProject.getName()).isEqualTo("resourceNameRestAPI");
        assertThat(newProject.getFile("pom.xml").exists()).isTrue();
        final Model mavenModel = new MavenProjectHelper().getMavenModel(newProject);
        assertThat(mavenModel.getArtifactId()).isEqualTo("resourceNameRestAPI");
        assertThat(mavenModel.getGroupId()).isEqualTo("com.company.rest.api");
        assertThat(mavenModel.getVersion()).isEqualTo("1.0.0-SNAPSHOT");
        assertThat(mavenModel.getProperties()).containsKey("bonita-runtime.version");

        //Check that there is no problems on eclipse project
        newProject.build(IncrementalProjectBuilder.FULL_BUILD, AbstractRepository.NULL_PROGRESS_MONITOR);
        final StringBuilder sb = new StringBuilder();
        for (final IMarker marker : newProject.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE)) {
            final int severity = (int) marker.getAttribute(IMarker.SEVERITY);
            if (severity == IMarker.SEVERITY_ERROR) {
                sb.append((String) marker.getAttribute(IMarker.MESSAGE));
                sb.append(SWT.CR);
            }
        }
        if (sb.length() > 0) {
            fail(sb.toString());
        }
    }

}
