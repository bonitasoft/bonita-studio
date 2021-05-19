/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.diagram.custom.parts;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.designer.ui.property.section.control.WebPageNameResourceChangeListener;
import org.bonitasoft.studio.diagram.custom.Activator;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;

public class CustomProcessDiagramEditor extends ProcessDiagramEditor {

    public static final String ID = "org.bonitasoft.studio.model.process.diagram.part.CustomProcessDiagramEditorID";

    private RepositoryAccessor repositoryAccessor;
    private WebPageNameResourceChangeListener webPageNameResourceChangeListener;

    public CustomProcessDiagramEditor() {
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        webPageNameResourceChangeListener = new WebPageNameResourceChangeListener(repositoryAccessor);
    }

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        DiagramFileStore fileStore = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class)
                .getChild(input.getName(), true);
        if (fileStore != null) {
            DiagramEditor openedEditor = fileStore.getOpenedEditor();
            if(openedEditor != null) { // Close other editors already opened for this input
                fileStore.close();
            }
            try {
                if (fileStore.getEMFResource().isLoaded()) {
                    fileStore.getEMFResource().unload();
                }
                fileStore.getContent();
            } catch (ReadFileStoreException e) {
                throw new PartInitException(
                        new Status(IStatus.ERROR, Activator.PLUGIN_ID, e.getMessage(), e.getCause()));
            }
        }
        super.init(site, input);
        repositoryAccessor.getWorkspace().addResourceChangeListener(webPageNameResourceChangeListener);
    }

    @Override
    protected void initializeGraphicalViewerContents() {
        super.initializeGraphicalViewerContents();
        EObject mainProcess = getDiagramEditPart().resolveSemanticElement();
        if (mainProcess instanceof MainProcess) {
            webPageNameResourceChangeListener.setMainProcess((MainProcess) mainProcess);
        }
    }

    @Override
    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        if (getSite() == null || getSite().getPage() == null) {
            //invalid editor
            return;
        }
        super.selectionChanged(part, selection);
    }


    @Override
    public void dispose() {
        repositoryAccessor.getWorkspace().removeResourceChangeListener(webPageNameResourceChangeListener);
        super.dispose();
    }

    public static String getId() {
        return ID;
    }

}
