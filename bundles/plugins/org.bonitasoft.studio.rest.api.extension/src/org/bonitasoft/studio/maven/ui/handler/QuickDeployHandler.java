/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.ui.handler;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;

import jakarta.inject.Named;

import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.ui.IDisplayable;
import org.bonitasoft.studio.common.ui.jface.BonitaErrorDialog;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.http.HttpClientFactory;
import org.bonitasoft.studio.engine.operation.GetApiSessionOperation;
import org.bonitasoft.studio.maven.ExtensionProjectFileStore;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.maven.operation.BuildCustomPageOperation;
import org.bonitasoft.studio.maven.operation.DeployCustomPageProjectOperation;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.bonitasoft.studio.rest.api.extension.ui.view.MavenConsoleView;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.ModalContext;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class QuickDeployHandler {

    private boolean showInUI;

    @Execute
    public IStatus execute(RepositoryAccessor repositoryAccessor,
            @Named(IServiceConstants.ACTIVE_SELECTION) @Optional ISelection selection,
            @Named("projectPath") @Optional String projectPath,
            @Named("disablePopup") @Optional String disablePopup,
            HttpClientFactory httpClientFactory) {
        showInUI = disablePopup == null ? true : !Boolean.valueOf(disablePopup);
        if (showInUI) {
            showConsoleView();
        }
        var apiSessionOperation = new GetApiSessionOperation();
        try {
            ExtensionProjectFileStore customPageFilseStore = null;
            if (projectPath != null) {
                IRepositoryFileStore fStore = repositoryAccessor.getCurrentRepository()
                        .orElseThrow()
                        .getRepositoryStore(ExtensionRepositoryStore.class)
                        .getChild(Paths.get(projectPath).getFileName().toString(), false);

                if (fStore instanceof ExtensionProjectFileStore) {
                    customPageFilseStore = (ExtensionProjectFileStore) fStore;
                }
            } else {
                customPageFilseStore = getMavenProjectFileStore(selection, repositoryAccessor);
            }

            if (customPageFilseStore != null) {
                String displayName = IDisplayable.toDisplayName(customPageFilseStore).orElse("");
                if (!customPageFilseStore.isReadOnly()) {
                    IStatus buildStatus = build(customPageFilseStore);
                    if (!buildStatus.isOK()) {
                        return ValidationStatus
                                .error(String.format(Messages.buildHasFailed, displayName));
                    }
                }
                var session = apiSessionOperation.execute();
                var deployRestAPIExtensionOperation = new DeployCustomPageProjectOperation(
                        BOSEngineManager.getInstance().getPageAPI(session),
                        httpClientFactory,
                        customPageFilseStore);
                if (showInUI) {
                    new ProgressMonitorDialog(Display.getDefault().getActiveShell()).run(true, false,
                            deployRestAPIExtensionOperation::run);
                    final IStatus status = deployRestAPIExtensionOperation.getStatus();
                    if (status.isOK()) {
                        openDeploySuccessDialog(displayName);
                    } else {
                        showDeployErrorDialog(status);
                    }
                } else {
                    deployRestAPIExtensionOperation.run(AbstractRepository.NULL_PROGRESS_MONITOR);
                    return deployRestAPIExtensionOperation.getStatus();
                }
            }
        } catch (InvocationTargetException | InterruptedException | BonitaHomeNotSetException | ServerAPIException
                | UnknownAPITypeException e) {
            BonitaStudioLog.error(e);
            return new Status(IStatus.ERROR, RestAPIExtensionActivator.PLUGIN_ID, e.getMessage(), e);
        } finally {
            apiSessionOperation.logout();
        }
        return ValidationStatus.ok();
    }

    protected IStatus build(ExtensionProjectFileStore selectedRestApiExtension) {
        try {
            final BuildCustomPageOperation operation = selectedRestApiExtension.newBuildOperation();
            if (showInUI) {
                ModalContext.run(operation.asWorkspaceModifyOperation(), true, AbstractRepository.NULL_PROGRESS_MONITOR,
                        Display.getDefault());
            } else {
                operation.run(AbstractRepository.NULL_PROGRESS_MONITOR);
            }
            return operation.getStatus();
        } catch (InvocationTargetException | InterruptedException | ReadFileStoreException | CoreException e) {
            BonitaStudioLog.error(e);
            return new Status(IStatus.ERROR, RestAPIExtensionActivator.PLUGIN_ID, e.getMessage(), e);
        }
    }

    protected void showConsoleView() {
        try {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(MavenConsoleView.VIEW_ID);
        } catch (final PartInitException e) {
            BonitaStudioLog.error(e);
        }
    }

    protected void openDeploySuccessDialog(final String restApiName) {
        MessageDialog.openInformation(Display.getDefault().getActiveShell(),
                Messages.deploySuccessTitle,
                NLS.bind(Messages.deploySuccessMessage, restApiName));
    }

    protected void showDeployErrorDialog(final IStatus status) {
        BonitaStudioLog.error("Failed to deploy rest api extension.", status.getException(),
                RestAPIExtensionActivator.PLUGIN_ID);
        new BonitaErrorDialog(Display.getDefault().getActiveShell(),
                Messages.errorTitle,
                status.getMessage(),
                status,
                IStatus.ERROR).open();
    }

    private ExtensionProjectFileStore getMavenProjectFileStore(final ISelection selection,
            RepositoryAccessor repositoryAccessor) {
        if (selection instanceof IStructuredSelection) {
            final Object firstSelectedElement = ((IStructuredSelection) selection).getFirstElement();
            if (firstSelectedElement instanceof IAdaptable) {
                IDeployable deployable = new FileStoreFinder()
                        .findElementToDeploy(
                                ((IAdaptable) firstSelectedElement).getAdapter(IResource.class).getProject(),
                                repositoryAccessor.getCurrentRepository().orElseThrow())
                        .orElse(null);
                if (deployable instanceof ExtensionProjectFileStore) {
                    return (ExtensionProjectFileStore) deployable;
                }
            }
        }
        return null;
    }

    @CanExecute
    public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) @Optional ISelection selection) {
        return selection != null;
    }

}
