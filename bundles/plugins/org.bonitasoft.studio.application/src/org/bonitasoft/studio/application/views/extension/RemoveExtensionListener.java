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
package org.bonitasoft.studio.application.views.extension;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.inject.Inject;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.extension.UpdateExtensionOperationDecoratorFactory;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.RemoveDependencyOperation;
import org.bonitasoft.studio.common.repository.extension.update.DependencyUpdate;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

@Creatable
public class RemoveExtensionListener {

    private UpdateExtensionOperationDecoratorFactory updateExtensionOperationDecoratorFactory;
    private ExceptionDialogHandler errorHandler;
    private RepositoryAccessor repositoryAccessor;

    @Inject
    public RemoveExtensionListener(RepositoryAccessor repositoryAccessor,
            UpdateExtensionOperationDecoratorFactory updateExtensionOperationDecoratorFactory,
            ExceptionDialogHandler errorHandler) {
        this.repositoryAccessor = repositoryAccessor;
        this.updateExtensionOperationDecoratorFactory = updateExtensionOperationDecoratorFactory;
        this.errorHandler = errorHandler;
    }

    public void removeExtension(Dependency dep) {
        String depText = String.format("%s:%s:%s", dep.getGroupId(), dep.getArtifactId(), dep.getVersion());
        if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(),
                Messages.removeExtensionConfirmationTitle,
                String.format(Messages.removeExtensionConfirmation, depText))) {
            var progressService = PlatformUI.getWorkbench().getProgressService();
            RemoveDependencyOperation removeDependencyOperation = new RemoveDependencyOperation(dep);
            var updateExtensionDecorator = updateExtensionOperationDecoratorFactory
                    .create(List.of(new DependencyUpdate(dep, "")));
            try {
                progressService.run(true, false, monitor -> {
                    try {
                        updateExtensionDecorator.preUpdate(monitor);
                        repositoryAccessor.getCurrentRepository().getLocalDependencyStore().backup(dep);
                        removeDependencyOperation.run(monitor);
                        updateExtensionDecorator.postUpdate(monitor);
                    } catch (CoreException | IOException e) {
                        throw new InvocationTargetException(e);
                    }
                });
                if (updateExtensionDecorator.shouldValidateProject()) {
                    updateExtensionDecorator.validateDependenciesConstraints();
                }
            } catch (InvocationTargetException | InterruptedException e) {
                errorHandler.openErrorDialog(Display.getDefault().getActiveShell(), e.getMessage(), e);
            }
        }
    }

}
