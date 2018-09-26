
package org.bonitasoft.studio.application.handler;

import org.bonitasoft.studio.application.views.provider.UIDArtifactFilters;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.PlatformUI;

public class OpenHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        ISelectionService service = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
        ISelection selection = service.getSelection();
        IResource resource = (IResource) ((IStructuredSelection) selection).getFirstElement();
        IRepositoryFileStore fileStore = RepositoryManager.getInstance().getCurrentRepository()
                .getFileStore(resource);
        if (fileStore != null) {
            fileStore.open();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.core.commands.AbstractHandler#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        ISelectionService service = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();
        if (service == null) {
            return false;
        }
        ISelection selection = service.getSelection();

        if (selection instanceof IStructuredSelection
                && (((IStructuredSelection) selection).size() == 1
                && (((IStructuredSelection) selection).getFirstElement() instanceof IFile
                                || UIDArtifactFilters
                                        .isUIDArtifact((((IStructuredSelection) selection).getFirstElement()))))) {
            IRepositoryFileStore fileStore = RepositoryManager.getInstance().getCurrentRepository()
                    .getFileStore((IResource) (((IStructuredSelection) selection).getFirstElement()));
            return fileStore != null && !fileStore.getName().endsWith(".jar");
        }
        return false;
    }

}