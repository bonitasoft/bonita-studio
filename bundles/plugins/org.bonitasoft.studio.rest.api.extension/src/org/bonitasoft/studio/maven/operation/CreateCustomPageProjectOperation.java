/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.operation;

import java.util.Objects;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.maven.AddDependencyOperation;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.bonitasoft.studio.maven.model.CustomPageArchetypeConfiguration;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.m2e.core.project.IArchetype;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;

/**
 * Operation to create an extension project with a custom page.properties file (Theme of REST API).
 * The new project must be created from a maven archetype.
 */
public abstract class CreateCustomPageProjectOperation extends CreateExtensionProjectOperation {

    private static final String UTF_8 = "UTF-8";

    protected CreateCustomPageProjectOperation(
            final ExtensionRepositoryStore repositoryStore,
            final ProjectImportConfiguration projectImportConfiguration,
            final CustomPageArchetypeConfiguration archetypeConfiguration) {
        super(repositoryStore, projectImportConfiguration, archetypeConfiguration);
    }

    protected CustomPageArchetypeConfiguration getArchetypeConfiguration() {
        return (CustomPageArchetypeConfiguration) super.getArchetypeConfiguration();
    }

    protected abstract IArchetype getArchetype();

    @Override
    protected void addDependencyToAppProject() throws CoreException {
        var bonitaProject = BonitaProject.create(repositoryStore.getRepository().getProjectId());
        var appModel = MavenProjectHelper.getMavenModel(bonitaProject.getAppProject());
        var extensionDependency = new Dependency();
        var existingDependency = appModel.getDependencies()
                .stream()
                .filter(dep -> Objects.equals(dep.getGroupId(), getArchetypeConfiguration().getGroupId()))
                .filter(dep -> Objects.equals(dep.getArtifactId(), getArchetypeConfiguration().getProjectName()))
                .filter(dep -> Objects.equals(dep.getType(), "zip"))
                .findFirst();
        if (existingDependency.isEmpty()) {
            extensionDependency.setGroupId("${project.groupId}");
            extensionDependency.setArtifactId(getArchetypeConfiguration().getProjectName());
            extensionDependency.setVersion("${project.version}");
            extensionDependency.setType("zip");
            appModel.getDependencies().add(extensionDependency);
            new AddDependencyOperation(extensionDependency).run(new NullProgressMonitor());
        }
    }

    protected void configure(final IProject project, final IProgressMonitor monitor) throws CoreException {
        project.getFile(getPagePropertyPath()).setCharset(UTF_8, monitor);
        super.configure(project, monitor);
    }

    /**
     * Get the path to the page's .properties file.
     * 
     * @return relative path from project root to .properties file
     */
    abstract protected String getPagePropertyPath();

}
