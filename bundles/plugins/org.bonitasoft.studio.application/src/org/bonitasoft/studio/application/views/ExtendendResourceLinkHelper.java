package org.bonitasoft.studio.application.views;


import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorPlugin;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDiagramDocument;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.internal.navigator.resources.workbench.ResourceLinkHelper;


public class ExtendendResourceLinkHelper extends ResourceLinkHelper {


    @Override
    public IStructuredSelection findSelection(IEditorInput anInput) {
        IDiagramDocument document = ProcessDiagramEditorPlugin.getInstance().getDocumentProvider()
                .getDiagramDocument(anInput);
        if (document == null) {
            return super.findSelection(anInput);
        }
        Diagram diagram = document.getDiagram();
        if (diagram == null || diagram.eResource() == null) {
            return StructuredSelection.EMPTY;
        }
        IFile file = WorkspaceSynchronizer.getFile(diagram.eResource());
        if (file != null) {
            return new StructuredSelection(file);
        }
        return StructuredSelection.EMPTY;
    }

    @Override
    public void activateEditor(IWorkbenchPage aPage, IStructuredSelection aSelection) {
        if (aSelection == null || aSelection.isEmpty())
            return;
        if (aSelection.getFirstElement() instanceof IFile) {
            DiagramRepositoryStore store = RepositoryManager.getInstance().getCurrentRepository()
                    .getRepositoryStore(DiagramRepositoryStore.class);
            IFile file = (IFile) aSelection.getFirstElement();
            DiagramFileStore fStore = store.getChild(file.getName(), true);
            if (fStore != null) {
                DiagramEditor openedEditor = fStore.getOpenedEditor();
                if (openedEditor != null) {
                    aPage.bringToTop(openedEditor);
                }
            } else {
                super.activateEditor(aPage, aSelection);
            }
        } else {
            super.activateEditor(aPage, aSelection);
        }
    }

}
