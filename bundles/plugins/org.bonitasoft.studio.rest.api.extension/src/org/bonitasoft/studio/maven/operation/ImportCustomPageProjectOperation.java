/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.operation;

import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.maven.CustomPageProjectFileStore;
import org.bonitasoft.studio.maven.builder.validator.PomFileValidator;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.bonitasoft.studio.rest.api.extension.core.validation.RestAPIDependencyVersionToUpdateFinder;
import org.bonitasoft.studio.theme.builder.ThemeDependencyVersionToUpdateFinder;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.eclipse.m2e.core.project.IMavenProjectImportResult;
import org.eclipse.m2e.core.project.IProjectConfigurationManager;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.eclipse.osgi.util.NLS;

public class ImportCustomPageProjectOperation extends AbstractMavenProjectUpdateOperation {

    private final IProjectConfigurationManager projectConfigurationManager;
    private final CustomPageProjectFileStore fileStore;
    private final ProjectImportConfiguration projectImportConfiguration;

    public ImportCustomPageProjectOperation(
            final CustomPageProjectFileStore fileStore,
            final IProjectConfigurationManager projectConfigurationManager,
            final ProjectImportConfiguration projectImportConfiguration) {
        super(false);
        this.fileStore = fileStore;
        this.projectConfigurationManager = projectConfigurationManager;
        this.projectImportConfiguration = projectImportConfiguration;
    }

    @Override
    protected IProject doRun(final IProgressMonitor monitor) {
        final String name = fileStore.getResource().getName();
        monitor.beginTask(NLS.bind(Messages.importingRestAPIExtensionProject, name), IProgressMonitor.UNKNOWN);
        List<IMavenProjectImportResult> results;
        try {
            results = projectConfigurationManager.importProjects(
                    Collections.singletonList(fileStore.getMavenProjectInfo()), projectImportConfiguration, monitor);
            validatePomFile(fileStore.getProject(), monitor);
            BonitaStudioLog.debug(String.format("%s project has been imported in workspace.", name),
                    RestAPIExtensionActivator.PLUGIN_ID);
            return results.get(0).getProject();
        } catch (final CoreException e) {
            status = new Status(IStatus.ERROR, RestAPIExtensionActivator.PLUGIN_ID, IStatus.OK,
                    e.getMessage(), e);
        }
        return null;
    }

    private void validatePomFile(IProject project, IProgressMonitor monitor) throws CoreException {
        new PomFileValidator(project, monitor)
                .addDependencyValidator(new RestAPIDependencyVersionToUpdateFinder())
                .addDependencyValidator(new ThemeDependencyVersionToUpdateFinder())
                .acceptAndValidate((IFile) project.findMember(IMavenConstants.POM_FILE_NAME));
    }

}
