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

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.application.handler.ImportExtensionHandler;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.extension.UpdateExtensionOperationDecoratorFactory;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependency;
import org.bonitasoft.studio.application.ui.control.model.dependency.BonitaArtifactDependencyVersion;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.UpdateDependencyVersionOperation;
import org.bonitasoft.studio.common.repository.extension.update.DependencyUpdate;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;

@Creatable
public class UpdateExtensionListener {

    private RepositoryAccessor repositoryAccessor;
    private UpdateExtensionOperationDecoratorFactory updateExtensionOperationDecoratorFactory;
    private CommandExecutor commandExecutor;
    private ExceptionDialogHandler errorHandler;

    @Inject
    public UpdateExtensionListener(RepositoryAccessor repositoryAccessor,
            UpdateExtensionOperationDecoratorFactory updateExtensionOperationDecoratorFactory,
            CommandExecutor commandExecutor,
            ExceptionDialogHandler errorHandler) {
        this.repositoryAccessor = repositoryAccessor;
        this.updateExtensionOperationDecoratorFactory = updateExtensionOperationDecoratorFactory;
        this.commandExecutor = commandExecutor;
        this.errorHandler = errorHandler;
    }

    public void updateExtension(BonitaArtifactDependency artifact, Dependency dep) {
        if (artifact != null && artifact.isFromMarketplace()) {
            updateMarketplaceExtension(artifact, dep);
        } else {
            updateOtherExtension(artifact, dep);
        }
    }

    public void updateGav(BonitaArtifactDependency artifact, Dependency dep) {
        var parameters = new HashMap<String, Object>();
        parameters.put("groupId", dep.getGroupId());
        parameters.put("artifactId", dep.getArtifactId());
        parameters.put("version", dep.getVersion());
        parameters.put("type", dep.getType());
        parameters.put("classifier", dep.getClassifier());
        parameters.put(ImportExtensionHandler.EXTENSION_TYPE_PARAMETER, artifact.getArtifactType().name());
        commandExecutor.executeCommand(ProjectExtensionEditorPart.UPDATE_GAV_COMMAND, parameters);
    }

    private void updateMarketplaceExtension(BonitaArtifactDependency bonitaDep, Dependency dep) {
        String latestVersion = bonitaDep.getLatestCompatibleVersion()
                .map(BonitaArtifactDependencyVersion::getVersion)
                .orElseThrow(() -> new IllegalArgumentException("No update available"));
        if (MessageDialog.openQuestion(Display.getDefault().getActiveShell(), Messages.updateExtensionConfirmationTitle,
                String.format(Messages.updateExtensionConfirmation, bonitaDep.getName(), dep.getVersion(),
                        latestVersion))) {
            var updateExtensionDecorator = updateExtensionOperationDecoratorFactory
                    .create(List.of(new DependencyUpdate(dep, latestVersion)));
            IProgressService progressService = PlatformUI.getWorkbench().getProgressService();
            try {
                progressService.run(true, false, monitor -> {
                    monitor.beginTask(Messages.upgradeExtension, IProgressMonitor.UNKNOWN);
                    updateExtensionDecorator.preUpdate(monitor);
                    try {
                        new UpdateDependencyVersionOperation(bonitaDep.getGroupId(),
                                bonitaDep.getArtifactId(),
                                latestVersion)
                                        .run(AbstractRepository.NULL_PROGRESS_MONITOR);
                        updateExtensionDecorator.postUpdate(monitor);
                    } catch (CoreException e) {
                        throw new InvocationTargetException(e);
                    }
                });
                if(updateExtensionDecorator.shouldValidateProject()) {
                    updateExtensionDecorator.validateDependenciesConstraints();
                 }
            } catch (InvocationTargetException | InterruptedException e) {
                errorHandler.openErrorDialog(Display.getDefault().getActiveShell(), e.getMessage(), e);
            }
        }
    }

    private void updateOtherExtension(BonitaArtifactDependency artifact, Dependency dep) {
        boolean localExtension = artifact.isLocalDependency();
        var parameters = new HashMap<String, Object>();
        parameters.put("groupId", dep.getGroupId());
        parameters.put("artifactId", dep.getArtifactId());
        parameters.put("version", dep.getVersion());
        parameters.put("type", dep.getType());
        parameters.put("classifier", dep.getClassifier());
        parameters.put("isLocal", String.valueOf(localExtension));
        parameters.put(ImportExtensionHandler.EXTENSION_TYPE_PARAMETER, artifact.getArtifactType().name());
        commandExecutor.executeCommand(ProjectExtensionEditorPart.IMPORT_EXTENSION_COMMAND, parameters);
    }

}
