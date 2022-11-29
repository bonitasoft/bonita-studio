/**
 * Copyright (C) 2021 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.handler;

import java.util.Objects;
import java.util.Optional;

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.SetProjectMetadataOperation;
import org.bonitasoft.studio.common.RedirectURLBuilder;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.ui.jface.MessageDialogWithLink;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.widgets.Display;

public class EditProjectMetadataHandler extends AbstractProjectMetadataHandler {

    protected String getWizardDescription() {
        return Messages.editProjectMetadataDescription;
    }

    protected String getWizardTitle() {
        return Messages.editProjectMetadata;
    }

    @Override
    public String getFinishLabel() {
        return Messages.modify;
    }

    protected SetProjectMetadataOperation createOperation(MavenProjectHelper mavenProjectHelper,
            ProjectMetadata metadata, RepositoryAccessor repositoryAccessor) {
        return new SetProjectMetadataOperation(metadata, repositoryAccessor, mavenProjectHelper);
    }

    @Override
    protected boolean isNewProject() {
        return false;
    }

    @Override
    protected ProjectMetadata initialMetadata(RepositoryAccessor repositoryAccessor) throws CoreException {
        var currentRepository = repositoryAccessor.getCurrentRepository().orElseThrow();
        return ProjectMetadata.read(currentRepository.getProject(), new NullProgressMonitor());
    }

    @Override
    protected Optional<IStatus> performFinish(IWizardContainer container, ProjectMetadata metadata,
            RepositoryAccessor repositoryAccessor, MavenProjectHelper mavenProjectHelper,
            ExceptionDialogHandler exceptionDialogHandler) {
        if (targetRuntimeVersionChanged(metadata, repositoryAccessor.getCurrentProject().orElseThrow())
                && new MessageDialogWithLink(Display.getDefault().getActiveShell(),
                        Messages.updateTargetRuntimeVersionConfirmTitle,
                        null,
                        Messages.updateTargetRuntimeVersionConfirmMsg,
                        MessageDialog.CONFIRM,
                        new String[] { String.format(Messages.updateTo, metadata.getBonitaRuntimeVersion()),
                                IDialogConstants.CANCEL_LABEL },
                        0,
                        RedirectURLBuilder.createURI("735")).open() == 1) {
            return Optional.empty();
        }
        return super.performFinish(container, metadata, repositoryAccessor, mavenProjectHelper, exceptionDialogHandler);
    }

    private boolean targetRuntimeVersionChanged(ProjectMetadata metadata, BonitaProject project) {
        try {
            var currentMetadata = project.getProjectMetadata(new NullProgressMonitor());
            return !Objects.equals(
                    currentMetadata.getBonitaRuntimeVersion(),
                    metadata.getBonitaRuntimeVersion());
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
            return false;
        }
       
    }

}
