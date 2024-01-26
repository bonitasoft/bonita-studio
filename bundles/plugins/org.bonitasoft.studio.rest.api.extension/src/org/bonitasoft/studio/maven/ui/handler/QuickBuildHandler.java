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

import jakarta.inject.Named;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.FileStoreFinder;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.maven.ExtensionProjectFileStore;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.bonitasoft.studio.maven.operation.BuildCustomPageOperation;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.bonitasoft.studio.rest.api.extension.ui.view.MavenConsoleView;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
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
        final ExtensionProjectFileStore selectedExtension = getMavenProjectFileStore(selection,
                repositoryAccessor);
        if (selectedExtension != null) {
            build(selectedExtension);
        }
    }

    protected IStatus build(ExtensionProjectFileStore selectedExtension) {
        try {
            ModalContext.run(monitor -> {
                try {
                    selectedExtension.getProject().build(IncrementalProjectBuilder.INCREMENTAL_BUILD, monitor);
                } catch (CoreException e) {
                  throw new InvocationTargetException(e);
                }
            }, false, new NullProgressMonitor(), Display.getDefault());
            final BuildCustomPageOperation operation = selectedExtension.newBuildOperation();
            ModalContext.run(operation.asWorkspaceModifyOperation(), true, new NullProgressMonitor(),
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

    private ExtensionProjectFileStore getMavenProjectFileStore(final ISelection selection,
            RepositoryAccessor repositoryAccessor) {
        if (selection instanceof IStructuredSelection) {
            final Object firstSelectedElement = ((IStructuredSelection) selection).getFirstElement();
            if (firstSelectedElement instanceof IAdaptable) {
                IRepositoryFileStore fStore = new FileStoreFinder()
                        .findFileStore(((IAdaptable) firstSelectedElement).getAdapter(IResource.class).getProject(),
                                repositoryAccessor.getCurrentRepository().orElseThrow())
                        .orElse(null);
                if (fStore instanceof ExtensionProjectFileStore) {
                    return (ExtensionProjectFileStore) fStore;
                }
            }
        }
        return null;
    }

    protected ExtensionRepositoryStore getRestAPIExtensionRepositoryStore() {
        return RepositoryManager.getInstance().getRepositoryStore(ExtensionRepositoryStore.class);
    }

    @CanExecute
    public boolean canExecute(@Named(IServiceConstants.ACTIVE_SELECTION) @Optional ISelection selection) {
        return selection != null;
    }

}
