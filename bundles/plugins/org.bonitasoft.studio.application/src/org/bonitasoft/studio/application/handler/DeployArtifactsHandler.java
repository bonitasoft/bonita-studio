/**
 * Copyright (C) 2019 Bonitasoft S.A.
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

import static org.bonitasoft.studio.ui.wizard.WizardBuilder.newWizard;
import static org.bonitasoft.studio.ui.wizard.WizardPageBuilder.newPage;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Named;

import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.BuildProjectOperation;
import org.bonitasoft.studio.application.operation.DeployProjectOperation;
import org.bonitasoft.studio.application.operation.DeployTenantResourcesOperation;
import org.bonitasoft.studio.application.operation.ValidateProjectOperation;
import org.bonitasoft.studio.application.ui.control.RepositoryModelBuilder;
import org.bonitasoft.studio.application.ui.control.SelectArtifactToDeployPage;
import org.bonitasoft.studio.application.ui.control.model.Artifact;
import org.bonitasoft.studio.application.ui.control.model.BuildableArtifact;
import org.bonitasoft.studio.application.ui.control.model.RepositoryModel;
import org.bonitasoft.studio.application.ui.control.model.TenantArtifact;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.DeployOptions;
import org.bonitasoft.studio.configuration.EnvironmentProviderFactory;
import org.bonitasoft.studio.engine.operation.GetApiSessionOperation;
import org.bonitasoft.studio.ui.dialog.MultiStatusDialog;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.ui.statushandlers.StatusManager;

public class DeployArtifactsHandler {

    private BuildProjectOperation buildOperation;
    private RepositoryModel repositoryModel;

    @Execute
    public void deploy(@Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell,
            RepositoryAccessor repositoryAccessor, IProgressService progressService)
            throws InvocationTargetException, InterruptedException {

        progressService.busyCursorWhile( monitor -> {
            repositoryModel = new RepositoryModelBuilder().create(repositoryAccessor);
        });

        SelectArtifactToDeployPage page = new SelectArtifactToDeployPage(repositoryModel,
                new EnvironmentProviderFactory().getEnvironmentProvider());
        Optional<IStatus> result = createWizard(newWizard(), page,
                repositoryAccessor,
                Messages.selectArtifactToDeployTitle,
                Messages.selectArtifactToDeploy)
                        .open(activeShell, Messages.deploy);
        if (result.isPresent()) {
            openStatusDialog(activeShell, result.get());
        }
    }

    protected IStatus deployArtifacts(RepositoryAccessor repositoryAccessor,
            Collection<Artifact> artifactsToDeploy,
            Map<String, Object> deployOptions,
            IWizardContainer container) {
        MultiStatus status = new MultiStatus(ApplicationPlugin.PLUGIN_ID, 0, null, null);
        try {
            container.run(true, false,
                    performFinish(repositoryAccessor,artifactsToDeploy, deployOptions, status));
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
            return new Status(IStatus.ERROR, ApplicationPlugin.PLUGIN_ID, "Deploy failed",
                    e.getCause() != null ? e.getCause() : e);
        }
        return status;
    }

    private IRunnableWithProgress performFinish(RepositoryAccessor repositoryAccessor,
            Collection<Artifact> artifactsToDeploy,
            Map<String, Object> deployOptions,
            MultiStatus status) {
        return monitor -> {
            GetApiSessionOperation apiSessionOperation = new GetApiSessionOperation();
            apiSessionOperation.run(monitor);
            try {
                APISession session = apiSessionOperation.getSession();
                DeployTenantResourcesOperation deployTenantResourcesOperation = new DeployTenantResourcesOperation(
                        artifactsToDeploy.stream().filter(TenantArtifact.class::isInstance).map(TenantArtifact.class::cast).collect(Collectors.toList()), session, deployOptions);
                deployTenantResourcesOperation.run(monitor);
                status.addAll(deployTenantResourcesOperation.getStatus());
                if (shouldValidate()) {
                    IStatus validationStatus = performArtifactsValidation(artifactsToDeploy, monitor);
                    if (!validationStatus.isOK()) {
                        status.addAll(validationStatus);
                        return;
                    }
                }
                IStatus buildStatus = performBuild(repositoryAccessor, artifactsToDeploy, monitor);
                if (!buildStatus.isOK()) {
                    if (buildStatus instanceof MultiStatus) {
                        status.addAll(buildStatus);
                    } else {
                        status.add(buildStatus);
                    }
                    return;
                }
                IStatus deployStatus = performDeploy(session, monitor);
                status.addAll(deployStatus);
            } finally {
                apiSessionOperation.logout();
            }
        };
    }

    private boolean shouldValidate() {
        return true;
    }

    private WizardBuilder<IStatus> createWizard(
            WizardBuilder<IStatus> builder,
            SelectArtifactToDeployPage page,
            RepositoryAccessor repositoryAccessor,
            String title,
            String description) {
        return builder
                .withTitle(title)
                .withSize(1000, 700)
                .needProgress()
                .havingPage(newPage()
                        .withTitle(title)
                        .withDescription(description)
                        .withControl(page))
                .onFinish(container -> Optional
                        .ofNullable(deployArtifacts(repositoryAccessor,
                                page.getSelectedArtifacts(),
                                buildOptions(page),
                                container)));
    }

    private Map<String, Object> buildOptions(SelectArtifactToDeployPage page) {
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put(DeployOptions.CLEAN_BDM, page.isCleanBDM());
        options.put(DeployOptions.DEFAULT_USERNAME, page.getDefaultUsername());
        return options;
    }

    private void openStatusDialog(Shell activeShell, IStatus status) {
        if (status instanceof MultiStatus) {
            new MultiStatusDialog(activeShell, Messages.deployStatus, Messages.deployStatusMessage,
                    new String[] { IDialogConstants.OK_LABEL }, (MultiStatus) status).open();
        } else {
            StatusManager.getManager().handle(status, StatusManager.SHOW);
        }
    }

    private IStatus performBuild(RepositoryAccessor repositoryAccessor,
            Collection<Artifact> artifactsToDeploy, IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException {
        buildOperation = new BuildProjectOperation(repositoryAccessor, artifactsToDeploy.stream()
                .filter(BuildableArtifact.class::isInstance)
                .map(BuildableArtifact.class::cast)
                .collect(Collectors.toList()));
        buildOperation.run(monitor);
        return buildOperation.getStatus();
    }

    private IStatus performDeploy(APISession session, IProgressMonitor monitor)
            throws InterruptedException, InvocationTargetException {
        DeployProjectOperation operation = new DeployProjectOperation(session, buildOperation.getArchiveFilePath());
        operation.run(monitor);
        return operation.getStatus();
    }

    private IStatus performArtifactsValidation(Collection<Artifact> artifactsToDeploy,
            IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException {
        ValidateProjectOperation operation = new ValidateProjectOperation(artifactsToDeploy);
        operation.run(monitor);
        return operation.getStatus();
    }

}
