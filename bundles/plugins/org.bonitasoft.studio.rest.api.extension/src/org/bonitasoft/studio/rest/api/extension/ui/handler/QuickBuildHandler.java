/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.ui.handler;

import java.lang.reflect.InvocationTargetException;

import javax.inject.Named;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.bonitasoft.studio.common.repository.model.IDeployable;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.maven.CustomPageProjectFileStore;
import org.bonitasoft.studio.maven.operation.BuildCustomPageOperation;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.bonitasoft.studio.rest.api.extension.ui.view.MavenConsoleView;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.operation.ModalContext;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class QuickBuildHandler {

    @Execute
    public void execute(RepositoryAccessor repositoryAccessor,
            @Named(IServiceConstants.ACTIVE_SELECTION) @Optional ISelection selection) {
        showConsoleView();
        final CustomPageProjectFileStore selectedRestApiExtension = getMavenProjectFileStore(selection,
                repositoryAccessor);
        if (selectedRestApiExtension != null) {
            build(selectedRestApiExtension);
        }
    }

    protected IStatus build(CustomPageProjectFileStore selectedRestApiExtension) {
        try {
            final BuildCustomPageOperation operation = selectedRestApiExtension.newBuildOperation();
            operation.setGoals("clean install");
            ModalContext.run(operation.asWorkspaceModifyOperation(), true, AbstractRepository.NULL_PROGRESS_MONITOR,
                    Display.getDefault());
            return operation.getStatus();
        } catch (InvocationTargetException | InterruptedException | ReadFileStoreException e) {
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

    private CustomPageProjectFileStore getMavenProjectFileStore(final ISelection selection,
            RepositoryAccessor repositoryAccessor) {
        if (selection instanceof IStructuredSelection) {
            final Object firstSelectedElement = ((IStructuredSelection) selection).getFirstElement();
            if (firstSelectedElement instanceof IAdaptable) {
                IRepositoryFileStore fStore = new FileStoreFinder()
                        .findFileStore(((IAdaptable) firstSelectedElement).getAdapter(IResource.class).getProject(),
                                repositoryAccessor.getCurrentRepository())
                        .orElse(null);
                if (fStore instanceof CustomPageProjectFileStore) {
                    return (CustomPageProjectFileStore) fStore;
                }
            }
        }
        return null;
    }

    protected RestAPIExtensionRepositoryStore getRestAPIExtensionRepositoryStore() {
        return RepositoryManager.getInstance().getRepositoryStore(RestAPIExtensionRepositoryStore.class);
    }

    @CanExecute
    public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) @Optional ISelection selection) {
        return selection != null;
    }

}
