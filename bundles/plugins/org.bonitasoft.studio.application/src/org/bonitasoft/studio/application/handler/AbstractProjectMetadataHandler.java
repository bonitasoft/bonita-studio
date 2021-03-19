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

import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import javax.inject.Named;

import org.bonitasoft.studio.application.operation.SetProjectMetadataOperation;
import org.bonitasoft.studio.application.ui.control.ProjectMetadataPage;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.widgets.Shell;

public abstract class AbstractProjectMetadataHandler {

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell,
            RepositoryAccessor repositoryAccessor,
            MavenProjectHelper mavenProjectHelper,
            ExceptionDialogHandler exceptionDialogHandler) {
        AbstractRepository currentRepository = repositoryAccessor.getCurrentRepository();
        ProjectMetadata metadata = initialMetadata(currentRepository);
        ProjectMetadataPage page = new ProjectMetadataPage(metadata, isNewProject());

        WizardBuilder.<IStatus> newWizard()
                .withTitle(getWizardTitle())
                .needProgress()
                .havingPage(newPage()
                        .withTitle(getWizardTitle())
                        .withDescription(getWizardDescription())
                        .withControl(page))
                .onFinish(container -> performFinish(container, page, repositoryAccessor, mavenProjectHelper,
                        exceptionDialogHandler))
                .open(activeShell, getFinishLabel());
    }

    public String getFinishLabel() {
        return IDialogConstants.FINISH_LABEL;
    }

    protected abstract ProjectMetadata initialMetadata(AbstractRepository currentRepository);

    protected abstract boolean isNewProject();

    protected abstract String getWizardDescription();

    protected abstract String getWizardTitle();

    private Optional<IStatus> performFinish(IWizardContainer container,
            ProjectMetadataPage page,
            RepositoryAccessor repositoryAccessor,
            MavenProjectHelper mavenProjectHelper,
            ExceptionDialogHandler exceptionDialogHandler) {
        SetProjectMetadataOperation operation = createOperation(mavenProjectHelper, page, repositoryAccessor);
        try {
            container.run(true, false, operation);
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
            exceptionDialogHandler.openErrorDialog(container.getShell(), "Failed to set project metadata", e);
            return Optional.empty();
        }
        return Optional.of(operation.getStatus());

    }

    protected abstract SetProjectMetadataOperation createOperation(MavenProjectHelper mavenProjectHelper,
            ProjectMetadataPage page, RepositoryAccessor repositoryAccessor);

}
