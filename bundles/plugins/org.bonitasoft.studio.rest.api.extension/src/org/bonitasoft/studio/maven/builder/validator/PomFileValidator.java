/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.builder.validator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.maven.project.MavenProject;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.ICallable;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.eclipse.m2e.core.internal.MavenPluginActivator;
import org.eclipse.m2e.core.internal.embedder.MavenExecutionContext;
import org.eclipse.m2e.core.internal.markers.IMavenMarkerManager;
import org.eclipse.m2e.core.internal.project.registry.ProjectRegistryManager;
import org.eclipse.m2e.core.project.IMavenProjectFacade;
import org.eclipse.m2e.core.project.IProjectConfigurationManager;
import org.eclipse.m2e.core.project.ResolverConfiguration;

public class PomFileValidator extends AbstractCustomPageValidator {

    public static final String VERSION_MIN_PROPERTY = "versionMin";
    public static final String TAG_PROPERTY = "tagToUseForQuickfix";

    private static final String POM_VALIDATION_MARKER = "org.bonitasoft.studio.rest.api.extension.validation.pomMarker";

    private ProjectRegistryManager projectManager = MavenPluginActivator.getDefault().getMavenProjectManagerImpl();
    private IProjectConfigurationManager configurationManager = MavenPlugin.getProjectConfigurationManager();
    private IMavenMarkerManager markerManager = MavenPluginActivator.getDefault().getMavenMarkerManager();

    private IProgressMonitor monitor;
    private List<IDependencyValidator> dependencyValidators = new ArrayList<>();

    public PomFileValidator(IProject project, IProgressMonitor monitor) {
        super(project);
        this.monitor = monitor;
    }

    public PomFileValidator addDependencyValidator(IDependencyValidator validator) {
        dependencyValidators.add(validator);
        return this;
    }

    @Override
    public boolean accept(IFile candidate) {
        RepositoryManager repositoryManager = RepositoryManager.getInstance();
        return repositoryManager.hasActiveRepository()
                && candidate.getLocation() != null
                && repositoryManager.getCurrentRepository().getProject().getLocation() != null
                && repositoryManager.getCurrentRepository().getProject().getLocation()
                        .isPrefixOf(candidate.getLocation())
                && candidate.getProjectRelativePath().equals(Path.fromOSString(IMavenConstants.POM_FILE_NAME));
    }

    @Override
    public void validate(IFile pomFile) throws CoreException {
        markerManager.deleteMarkers(pomFile, POM_VALIDATION_MARKER);
        ResolverConfiguration resolverConfiguration = configurationManager.getResolverConfiguration(project);
        if (resolverConfiguration != null) {
            MavenExecutionContext context = projectManager.createExecutionContext(pomFile, resolverConfiguration);
            ICallable<IProject> callable = (ctx, mntr) -> {
                IMavenProjectFacade projectFacade = projectManager.getProject(project);
                if (projectFacade == null || projectFacade.isStale()) {
                    projectManager.refresh(Collections.singleton(pomFile), monitor);
                    projectFacade = projectManager.getProject(project);
                    if (projectFacade == null) {
                        return null;
                    }
                }
                MavenProject mavenProject = projectFacade.getMavenProject(monitor);
                for (IDependencyValidator validator : dependencyValidators) {
                    for (DependencyToUpdate dependencyToUpdate : validator
                            .findDependenciesToUpdate(mavenProject)) {
                        createMarker(dependencyToUpdate, pomFile);
                    }
                }
                return null;
            };
            context.execute(callable, monitor);
        }
    }

    private void createMarker(DependencyToUpdate dependencyToUpdate, IFile pomFile)
            throws CoreException {
        Location location = dependencyToUpdate.getLocation();
        IMarker marker = markerManager.addMarker(pomFile, POM_VALIDATION_MARKER, dependencyToUpdate.getMessage(),
                location.getLineNumber() + 1, toMarkerSeverity(dependencyToUpdate.getSeverity()));
        marker.setAttribute(VERSION_MIN_PROPERTY, dependencyToUpdate.getMinVersion());
        marker.setAttribute(TAG_PROPERTY, dependencyToUpdate.getTag());
        try {
            marker.setAttribute(IMarker.CHAR_START, location.getOffset());
            marker.setAttribute(IMarker.CHAR_END, location.getOffset() + location.getLength());
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }
    }

    private int toMarkerSeverity(int severity) {
        switch (severity) {
            case IStatus.ERROR:
                return IMarker.SEVERITY_ERROR;
            case IStatus.WARNING:
                return IMarker.SEVERITY_WARNING;
            default:
                return IMarker.SEVERITY_INFO;
        }
    }

    @Override
    public void acceptAndValidate() throws CoreException {
        IFile candidate = project.getFile(IMavenConstants.POM_FILE_NAME);
        if (candidate != null) {
            acceptAndValidate(candidate);
        }
    }

    @Override
    public String getMarkerType() {
        return POM_VALIDATION_MARKER;
    }

    @Override
    protected IFolder getParentFolder() {
        return RepositoryManager.getInstance().getRepositoryStore(RestAPIExtensionRepositoryStore.class).getResource();
    }

}
