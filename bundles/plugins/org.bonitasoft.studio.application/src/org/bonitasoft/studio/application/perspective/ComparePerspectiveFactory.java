/*******************************************************************************
 * Copyright (C) 2018 Bonitasoft S.A.
 * Bonitasoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.application.perspective;

import org.bonitasoft.studio.application.views.BonitaProjectExplorer;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.ui.perspectives.AbstractPerspectiveFactory;
import org.eclipse.compare.internal.CompareEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

public class ComparePerspectiveFactory extends AbstractPerspectiveFactory {

    private static final String COMPARE_PERSPECTIVE_ID = "org.bonitasoft.studio.perspective.compare";

    @Override
    public void createInitialLayout(IPageLayout layout) {
        final String editorArea = layout.getEditorArea();
        final IFolderLayout leftFolder = layout.createFolder(
                "left", IPageLayout.LEFT, getExplorerViewRatio(), editorArea);
        leftFolder.addView(BonitaProjectExplorer.ID);
        IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, 0.75f, "left");
        bottomLeft.addView(PROBLEM_VIEW_ID);
        RepositoryManager.getInstance().getCurrentRepository()
        .filter(repo -> repo.isShared("org.eclipse.egit.core.GitProvider"))
        .ifPresent(repo -> {
            final IFolderLayout bottomfolder = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.75f, editorArea); //$NON-NLS-1$
            bottomfolder.addView("org.eclipse.egit.ui.StagingView");
            bottomfolder.addPlaceholder("org.eclipse.team.ui.GenericHistoryView");
        });
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.ui.perspectives.AbstractPerspectiveFactory#isRelevantFor(org.eclipse.ui.IEditorPart)
     */
    @Override
    public boolean isRelevantFor(IEditorPart part) {
        return part instanceof CompareEditor && !isInsideprojectWithREStApiExtensionNature(part);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.ui.perspectives.AbstractPerspectiveFactory#getID()
     */
    @Override
    public String getID() {
        return COMPARE_PERSPECTIVE_ID;
    }

}
