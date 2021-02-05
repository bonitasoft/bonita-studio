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

import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.ExtendProjectPage;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.maven.AddDependencyOperation;
import org.bonitasoft.studio.common.repository.core.maven.UpdateDependencyVersionOperation;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.widgets.Shell;

public class ExtendProjectHandler {

    @Execute
    public void execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell) {

        ExtendProjectPage extendProjectPage = new ExtendProjectPage();

        WizardBuilder.<Boolean> newWizard()
                .withTitle(Messages.extendProjectTitle)
                .needProgress()
                .havingPage(newPage()
                        .withTitle(Messages.extendProjectTitle)
                        .withDescription(Messages.extendProjectDescription)
                        .withControl(extendProjectPage))
                .onFinish(container -> performFinish(container, extendProjectPage))
                .withSize(800, 800)
                .withFixedInitialSize()
                .open(activeShell, Messages.install);
    }

    private Optional<Boolean> performFinish(IWizardContainer container, ExtendProjectPage extendProjectPage) {
        try {
            container.run(true, false, monitor -> {
                monitor.beginTask(Messages.installingExtensions, IProgressMonitor.UNKNOWN);
                for (BonitaArtifactDependency dep : extendProjectPage.getDependenciesToUpdate()) {
                    updateDependency(dep, monitor);
                }
                for (BonitaArtifactDependency dep : extendProjectPage.getDependenciesToAdd()) {
                    addDependency(dep, monitor);
                }
            });
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
            MessageDialog.openError(container.getShell(), Messages.addDependenciesError, e.getMessage());
        }
        return Optional.of(true);
    }

    private void updateDependency(BonitaArtifactDependency dep, IProgressMonitor monitor) throws InvocationTargetException {
        try {
            UpdateDependencyVersionOperation operation = new UpdateDependencyVersionOperation(dep.getGroupId(),
                    dep.getArtifactId(), dep.getVersion());
            operation.run(monitor);
        } catch (CoreException e) {
            throw new InvocationTargetException(e);
        }
    }

    private void addDependency(BonitaArtifactDependency dep, IProgressMonitor monitor) throws InvocationTargetException {
        try {
            AddDependencyOperation operation = new AddDependencyOperation(dep.getGroupId(),
                    dep.getArtifactId(), dep.getVersion());
            operation.run(monitor);
        } catch (CoreException e) {
            throw new InvocationTargetException(e);
        }
    }

}
