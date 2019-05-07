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
import org.bonitasoft.studio.designer.ui.property.section.control.WebPageNameResourceChangeListener;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

public class CustomProcessDiagramEditor extends ProcessDiagramEditor {

    public static final String ID = "org.bonitasoft.studio.model.process.diagram.part.CustomProcessDiagramEditorID";

    private RepositoryAccessor repositoryAccessor;
    private WebPageNameResourceChangeListener webPageNameResourceChangeListener;

    public CustomProcessDiagramEditor() {
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        webPageNameResourceChangeListener = new WebPageNameResourceChangeListener(repositoryAccessor);
    }

    @Override
    public void init(IEditorSite site, IEditorInput input) throws PartInitException {
        super.init(site, input);
        DiagramFileStore store = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class)
                .getChild(input.getName());
        if (store != null) {
            webPageNameResourceChangeListener.setMainProcess(store.getContent());
            repositoryAccessor.getWorkspace().addResourceChangeListener(webPageNameResourceChangeListener);
        }
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
