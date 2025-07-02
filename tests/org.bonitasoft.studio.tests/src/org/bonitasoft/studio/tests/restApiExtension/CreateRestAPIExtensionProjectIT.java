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
import static org.assertj.core.api.Assertions.tuple;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.assertions.StatusAssert;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.businessobject.ui.handler.DefineBusinessDataModelHandler;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetypeConfiguration;
import org.bonitasoft.studio.rest.api.extension.core.maven.CreateRestAPIExtensionProjectOperation;
import org.bonitasoft.studio.tests.util.InitialProjectRule;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.eclipse.swt.SWT;
import org.eclipse.ui.PlatformUI;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;

public class CreateRestAPIExtensionProjectIT {

    @Rule
    public InitialProjectRule projectRule = InitialProjectRule.INSTANCE;
    
    @After
    public void tearDown() throws Exception {
        final IFolder createdFolder = RepositoryManager.getInstance().getRepositoryStore(ExtensionRepositoryStore.class).getResource()
                .getFolder("resourceNameRestAPI");
        if (createdFolder.exists()) {
            createdFolder.delete(true, AbstractRepository.NULL_PROGRESS_MONITOR);
        }
        var bdmFileStore = RepositoryManager.getInstance().getRepositoryStore(BusinessObjectModelRepositoryStore.class).getChild("bom.xml", false);
        bdmFileStore.delete();
    }

    @Test
    public void should_create_a_rest_api_extension_project_in_workspace() throws Exception {
    	var repositoryAccessor = RepositoryManager.getInstance().getAccessor();
    	new DefineBusinessDataModelHandler().defineBusinessDataModel(repositoryAccessor, PlatformUI.getWorkbench().getProgressService());
    	var bonitaProject = RepositoryManager.getInstance().getCurrentProject().orElseThrow();
        var metadata = bonitaProject.getProjectMetadata(new NullProgressMonitor());
        RestAPIExtensionArchetypeConfiguration defaultArchetypeConfiguration = RestAPIExtensionArchetypeConfiguration.defaultArchetypeConfiguration(metadata);
        defaultArchetypeConfiguration.setBonitaVersion(ProductVersion.BONITA_RUNTIME_VERSION);
        defaultArchetypeConfiguration.setEnableBDMDependencies(true);
        final CreateRestAPIExtensionProjectOperation operation = new CreateRestAPIExtensionProjectOperation(
                RepositoryManager.getInstance().getRepositoryStore(ExtensionRepositoryStore.class),
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
        final Model mavenModel =  MavenProjectHelper.getMavenModel(newProject);
        assertThat(mavenModel.getArtifactId()).isEqualTo("resourceNameRestAPI");
        assertThat(mavenModel.getGroupId()).isNull();
        assertThat(mavenModel.getVersion()).isNull();
        assertThat(mavenModel.getParent().getGroupId()).isEqualTo("com.company");
        assertThat(mavenModel.getParent().getVersion()).isEqualTo("0.0.1");
        assertThat(mavenModel.getProperties()).doesNotContainKey("bonita-runtime.version");
        assertThat(mavenModel.getDependencies()).extracting("groupId", "artifactId", "version")
        	.contains(tuple("${project.groupId}", String.format("%s-bdm-model", metadata.getProjectId()),"${project.version}"));
        
        var appModel = MavenProjectHelper.getMavenModel(bonitaProject.getAppProject());
        assertThat(appModel.getDependencies())
        		.as("Extension zip dependency has been added to the application module dependencies.")
        		.extracting("groupId", "artifactId", "version", "type")
    			.contains(tuple("${project.groupId}", "resourceNameRestAPI","${project.version}", "zip"));

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
        
        var apiStore = RepositoryManager.getInstance().getRepositoryStore(ExtensionRepositoryStore.class).getChild("resourceNameRestAPI", false);
        apiStore.delete();
        appModel = MavenProjectHelper.getMavenModel(bonitaProject.getAppProject());
        assertThat(appModel.getDependencies())
        		.as("Extension zip dependency has been removed from the application module dependencies.")
        		.extracting("groupId", "artifactId", "version", "type")
    			.doesNotContain(tuple("${project.groupId}", "resourceNameRestAPI","${project.version}", "zip"));
    }

}
