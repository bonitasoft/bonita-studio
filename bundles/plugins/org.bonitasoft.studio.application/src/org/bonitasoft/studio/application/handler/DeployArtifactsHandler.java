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

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Named;

import org.bonitasoft.engine.api.result.StatusCode;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.platform.LoginException;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.operation.DeployProjectOperation;
import org.bonitasoft.studio.application.operation.DeployTenantResourcesOperation;
import org.bonitasoft.studio.application.operation.ValidateProjectOperation;
import org.bonitasoft.studio.application.ui.control.DeploySuccessDialog;
import org.bonitasoft.studio.application.ui.control.DeployedAppContentProvider;
import org.bonitasoft.studio.application.ui.control.RepositoryModelBuilder;
import org.bonitasoft.studio.application.ui.control.SelectArtifactToDeployPage;
import org.bonitasoft.studio.application.ui.control.model.Artifact;
import org.bonitasoft.studio.application.ui.control.model.FileStoreArtifact;
import org.bonitasoft.studio.application.ui.control.model.RepositoryModel;
import org.bonitasoft.studio.application.ui.control.model.RepositoryStore;
import org.bonitasoft.studio.application.ui.control.model.TenantArtifact;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.ActiveOrganizationProvider;
import org.bonitasoft.studio.common.repository.model.DeployOptions;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.configuration.EnvironmentProviderFactory;
import org.bonitasoft.studio.designer.core.operation.IndexingUIDOperation;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.operation.GetApiSessionOperation;
import org.bonitasoft.studio.preferences.browser.OpenBrowserOperation;
import org.bonitasoft.studio.ui.dialog.MultiStatusDialog;
import org.bonitasoft.studio.ui.dialog.SaveBeforeDeployDialog;
import org.bonitasoft.studio.ui.util.ProcessValidationStatus;
import org.bonitasoft.studio.ui.wizard.WizardBuilder;
import org.bonitasoft.studio.validation.ModelFileCompatibilityValidator;
import org.bonitasoft.studio.validation.common.operation.RunProcessesValidationOperation;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.progress.IProgressService;
import org.eclipse.ui.statushandlers.StatusManager;

public class DeployArtifactsHandler {

    private RepositoryModel repositoryModel;
    private List<IRepositoryFileStore<?>> defaultSelection;
    private static final List<String> REPO_STORE_DEPLOY_ORDER = new ArrayList<>();

    static {
        REPO_STORE_DEPLOY_ORDER.add("organizations");
        REPO_STORE_DEPLOY_ORDER.add("profiles");
        REPO_STORE_DEPLOY_ORDER.add("bdm");
        REPO_STORE_DEPLOY_ORDER.add("diagrams");
        REPO_STORE_DEPLOY_ORDER.add("restAPIExtensions");
        REPO_STORE_DEPLOY_ORDER.add("web_page");
        REPO_STORE_DEPLOY_ORDER.add("themes");
        REPO_STORE_DEPLOY_ORDER.add("applications");
    }

    @Execute
    public void deploy(@Named(IServiceConstants.ACTIVE_SHELL) Shell activeShell,
            RepositoryAccessor repositoryAccessor, IProgressService progressService)
            throws InvocationTargetException, InterruptedException {
        ModelFileCompatibilityValidator validator = new ModelFileCompatibilityValidator(
                repositoryAccessor.getCurrentRepository());
        validator.addResourceMarkers();
        progressService.busyCursorWhile(validator::run);
        boolean shouldUpdateModels = Stream.of(validator.getStatus().getChildren()).anyMatch(s -> s.matches(IStatus.WARNING));
        if (validator.getStatus().matches(IStatus.ERROR) || shouldUpdateModels) {
            int result = new MultiStatusDialog(activeShell, Messages.incompatibleModelFoundTitle, Messages.incompatibleModelFoundMsg,
                    shouldUpdateModels ? new String[] { org.bonitasoft.studio.common.repository.Messages.updateAllModels, IDialogConstants.ABORT_LABEL} :  new String[] {IDialogConstants.PROCEED_LABEL}, validator.getStatus())
                            .setLevel(IStatus.WARNING)
                            .open();
            if(result == 1) {
                return;
            }
            if (Stream.of(validator.getStatus().getChildren()).anyMatch(s -> s.matches(IStatus.WARNING))) {
                progressService.busyCursorWhile(monitor -> {
                    try {
                        repositoryAccessor.getCurrentRepository().migrate(monitor);
                        validator.run(monitor);
                    } catch (CoreException | MigrationException e) {
                        BonitaStudioLog.error(e);
                    }
                });
            }
        }
        DiagramRepositoryStore diagramStore = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class);
        progressService.busyCursorWhile(monitor -> {
                diagramStore.computeProcesses(monitor);
                repositoryModel = new RepositoryModelBuilder().create(repositoryAccessor);}
        );
        SelectArtifactToDeployPage page = new SelectArtifactToDeployPage(repositoryModel,
                new EnvironmentProviderFactory().getEnvironmentProvider());
        if (defaultSelection != null) {
            page.setDefaultSelectedElements(defaultSelection.stream()
                    .map(this::asArtifact)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet()));
        }
        Optional<IStatus> result = createWizard(newWizard(), page,
                repositoryAccessor,
                Messages.selectArtifactToDeployTitle,
                Messages.selectArtifactToDeploy)
                        .open(activeShell, Messages.deploy);
        if (result.isPresent()) {
            openStatusDialog(activeShell, result.get(), repositoryAccessor);
        }
        diagramStore.resetComputedProcesses();
    }

    private Artifact asArtifact(IRepositoryFileStore<?> fStore) {
        return repositoryModel.getArtifacts().stream()
                .filter(FileStoreArtifact.class::isInstance)
                .map(FileStoreArtifact.class::cast)
                .filter(artifact -> Objects.equals(artifact.getFileStore().getResource(), fStore.getResource()))
                .findFirst()
                .orElse(null);
    }

    public void setDefaultSelection(List<IRepositoryFileStore<?>> defaultSelection) {
        this.defaultSelection = defaultSelection;
    }

    protected IStatus deployArtifacts(RepositoryAccessor repositoryAccessor,
            Collection<Artifact> artifactsToDeploy,
            Map<String, Object> deployOptions,
            IWizardContainer container) {
        MultiStatus status = new MultiStatus(ApplicationPlugin.PLUGIN_ID, 0, null, null);
        if (!checkDirtyState(container)) {
            return null;
        }
        try {
            container.run(true, true,
                    performFinish(repositoryAccessor, artifactsToDeploy, deployOptions, status));
        } catch (InvocationTargetException | InterruptedException e) {
            BonitaStudioLog.error(e);
            return new Status(IStatus.ERROR, ApplicationPlugin.PLUGIN_ID, "Deploy failed",
                    e.getCause() != null ? e.getCause() : e);
        }
        if (status.getSeverity() == IStatus.CANCEL) {
            openAbortDialog(Display.getDefault().getActiveShell());
        }
        return status.getSeverity() == IStatus.CANCEL ? null : status;
    }

    private boolean checkDirtyState(IWizardContainer container) {
        if (PlatformUI.isWorkbenchRunning()
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null) {
            List<IEditorPart> dirtyEditors = Arrays
                    .asList(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences())
                    .stream()
                    .map(ref -> ref.getEditor(false))
                    .filter(editorPart -> editorPart != null)
                    .filter(IEditorPart::isDirty)
                    .collect(Collectors.toList());
            if (!dirtyEditors.isEmpty()) {
                SaveStrategy strategy = saveDirtyEditors();
                if (Objects.equals(strategy, SaveStrategy.CANCEL)) {
                    return false;
                } else if (Objects.equals(strategy, SaveStrategy.SAVE)) {
                    try {
                        container.run(false, false, monitor -> {
                            monitor.beginTask(Messages.savingEditors, dirtyEditors.size());
                            dirtyEditors.forEach(editor -> {
                                editor.doSave(monitor);
                                monitor.worked(1);
                            });
                            monitor.done();
                        });
                    } catch (InvocationTargetException | InterruptedException e) {
                        throw new RuntimeException("An error occured while saving editors", e);
                    }
                }
            }
        }
        return true;
    }

    private SaveStrategy saveDirtyEditors() {
        int choice = SaveBeforeDeployDialog.open(Messages.saveOpenedEditorsTitle,
                Messages.saveOpenedEditors,
                IDialogConstants.CANCEL_LABEL, IDialogConstants.NO_LABEL, IDialogConstants.YES_LABEL);
        switch (choice) {
            case 0:
                return SaveStrategy.CANCEL;
            case 1:
                return SaveStrategy.DONT_SAVE;
            default:
                return SaveStrategy.SAVE;
        }
    }

    enum SaveStrategy {
        SAVE, DONT_SAVE, CANCEL
    }

    private IRunnableWithProgress performFinish(RepositoryAccessor repositoryAccessor,
            Collection<Artifact> artifactsToDeploy,
            Map<String, Object> deployOptions,
            MultiStatus status) {
        return monitor -> {
            new IndexingUIDOperation().run(monitor);
            GetApiSessionOperation apiSessionOperation = new GetApiSessionOperation();
            apiSessionOperation.run(monitor);
            try {
                APISession session = apiSessionOperation.getSession();
                DeployTenantResourcesOperation deployTenantResourcesOperation = new DeployTenantResourcesOperation(
                        artifactsToDeploy.stream().filter(TenantArtifact.class::isInstance)
                                .map(TenantArtifact.class::cast).collect(Collectors.toList()),
                        session, deployOptions);
                monitor.beginTask(Messages.deploy, artifactsToDeploy.size());
                deployTenantResourcesOperation.run(monitor);
                addToMultiStatus(deployTenantResourcesOperation.getStatus(), status);
                if ((boolean) deployOptions.get(DeployOptions.RUN_VALIDATION)) {
                    ValidateProjectOperation operation = new ValidateProjectOperation(artifactsToDeploy);
                    operation.run(monitor);
                    status.add(operation.getStatus());
                }
                if (status.getSeverity() != IStatus.ERROR) {
                    addToMultiStatus(deploy(artifactsToDeploy, session, monitor), status);
                }
            } finally {
                monitor.done();
                apiSessionOperation.logout();
            }
            if (monitor.isCanceled()) {
                status.add(ValidationStatus.cancel(Messages.abort));
            }
        };
    }

    private void addToMultiStatus(IStatus status, MultiStatus multiStatus) {
        if (status instanceof MultiStatus) {
            multiStatus.addAll(status);
        } else {
            multiStatus.add(status);
        }
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
        HashMap<String, Object> options = new HashMap<>();
        options.put(DeployOptions.CLEAN_BDM, page.isCleanBDM());
        options.put(DeployOptions.DEFAULT_USERNAME, page.getDefaultUsername());
        options.put(DeployOptions.RUN_VALIDATION, page.isValidate());
        return options;
    }

    private void openStatusDialog(Shell activeShell, IStatus status, RepositoryAccessor repositoryAccessor) {
        if (status instanceof MultiStatus) {
            if (status.getSeverity() == IStatus.ERROR || status.getSeverity() == IStatus.WARNING) {
                String[] buttonLabels = new String[] { IDialogConstants.CLOSE_LABEL };
                List<ProcessValidationStatus> processValidationStatuses = new ArrayList<>();
                findProcessValidationStaus(status, processValidationStatuses);
                if (!processValidationStatuses.isEmpty()) {
                    buttonLabels = new String[] { org.bonitasoft.studio.ui.i18n.Messages.seeDetails,
                            IDialogConstants.CLOSE_LABEL };
                }
                MultiStatusDialog multiStatusDialog = new MultiStatusDialog(activeShell, Messages.deployStatus,
                        errorMessageFromStatus(status),
                        buttonLabels,
                        (MultiStatus) status);
                multiStatusDialog.setLevel(IStatus.WARNING);
                if (multiStatusDialog.open() == MultiStatusDialog.SEE_DETAILS_ID) {
                    openDiagrams(processValidationStatuses, repositoryAccessor);
                }
            } else {
                try {
                    openSuccessDialog(activeShell, status);
                } catch (LoginException | BonitaHomeNotSetException | ServerAPIException | UnknownAPITypeException e) {
                    BonitaStudioLog.error(e);
                    new BonitaErrorDialog(activeShell, Messages.deployErrorTitle, e.getMessage(), e).open();
                }
            }
        } else if (status.getSeverity() == IStatus.CANCEL) {
            MessageDialog.openInformation(activeShell, IDialogConstants.CANCEL_LABEL, status.getMessage());
        } else {
            StatusManager.getManager().handle(status, StatusManager.SHOW);
        }
    }

    private void openAbortDialog(Shell activeShell) {
        MessageDialog.openInformation(activeShell, Messages.abort, Messages.deployAborted);
    }

    private void openDiagrams(List<ProcessValidationStatus> processValidationStatuses,
            RepositoryAccessor repositoryAccessor) {
        DiagramRepositoryStore diagramStore = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class);
        processValidationStatuses.stream()
                .map(s -> s.getProcess().eResource())
                .map(resource -> diagramStore.getChild(URI.decode(resource.getURI().lastSegment()), false))
                .filter(Objects::nonNull)
                .distinct()
                .filter(fStore -> !fStore.isOpened())
                .forEach(DiagramFileStore::open);

        RunProcessesValidationOperation.showValidationPart();
    }

    private void findProcessValidationStaus(IStatus status, List<ProcessValidationStatus> result) {
        if (status instanceof ProcessValidationStatus && status.getSeverity() == IStatus.ERROR) {
            result.add((ProcessValidationStatus) status);
        }
        for (IStatus child : status.getChildren()) {
            findProcessValidationStaus(child, result);
        }
    }

    private String errorMessageFromStatus(IStatus status) {
        if (Stream.of(status.getChildren()).anyMatch(
                s -> Objects.equals(s.getCode(), StatusCode.PROCESS_DEPLOYMENT_IMPOSSIBLE_UNRESOLVED.ordinal()))) {
            return Messages.deployStatusWithUnresolvedProcessesMessage;
        }
        return Messages.deployStatusMessage;
    }

    private void openSuccessDialog(Shell activeShell, IStatus status)
            throws LoginException, BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException {
        APISession session = BOSEngineManager.getInstance().loginDefaultTenant(AbstractRepository.NULL_PROGRESS_MONITOR);
        DeployedAppContentProvider contentProvider = new DeployedAppContentProvider(status,
                BOSEngineManager.getInstance().getApplicationAPI(session),
                BOSEngineManager.getInstance().getProfileAPI(session),
                BOSEngineManager.getInstance().getIdentityAPI(session));
        if (contentProvider.getItems().length > 0) {
            if (IDialogConstants.OPEN_ID == DeploySuccessDialog.open(activeShell, contentProvider,
                    WorkbenchPlugin.getDefault().getDialogSettings())) {
                try {
                    new OpenBrowserOperation(contentProvider.getSelectedURL()).execute();
                } catch (MalformedURLException | UnsupportedEncodingException | URISyntaxException e) {
                    BonitaStudioLog.error(e);
                }
            }
        } else {
            MessageDialog.openWarning(activeShell, Messages.deployStatus, String.format(
                    Messages.deploySuccessButNoAppToOpenMsg, new ActiveOrganizationProvider().getDefaultUser()));
        }
        if (session != null) {
            BOSEngineManager.getInstance().logoutDefaultTenant(session);
        }
    }

    private IStatus deploy(Collection<Artifact> artifactsToDeploy, APISession session, IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException {
        DeployProjectOperation operation = new DeployProjectOperation(session, artifactsToDeploy.stream()
                .filter(notTenantArtifact())
                .filter(FileStoreArtifact.class::isInstance)
                .map(FileStoreArtifact.class::cast)
                .sorted(artifactComparator())
                .collect(Collectors.toList()));
        operation.run(monitor);
        return operation.getStatus();
    }

    private Comparator<? super FileStoreArtifact> artifactComparator() {
        return (a1, a2) -> Integer.compare(REPO_STORE_DEPLOY_ORDER.indexOf(getStoreName(a1)),
                REPO_STORE_DEPLOY_ORDER.indexOf(getStoreName(a2)));
    }

    private String getStoreName(FileStoreArtifact artifact) {
        Object parent = artifact.getParent();
        while (parent != null && !(parent instanceof RepositoryStore)) {
            if (parent instanceof Artifact) {
                parent = ((Artifact) parent).getParent();
            } else {
                parent = null;
            }
        }
        return parent instanceof RepositoryStore ? ((RepositoryStore) parent).getResource().getName() : null;
    }

    private Predicate<? super Artifact> notTenantArtifact() {
        return artifact -> !(artifact instanceof TenantArtifact);
    }

}
