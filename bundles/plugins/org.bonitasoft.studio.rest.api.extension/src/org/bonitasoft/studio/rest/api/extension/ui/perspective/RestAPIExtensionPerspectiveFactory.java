/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.ui.perspective;

import org.bonitasoft.studio.application.views.BonitaProjectExplorer;
import org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.rest.api.extension.ui.view.MavenConsoleView;
import org.bonitasoft.studio.rest.api.extension.ui.view.OnlySelectedElementProblemView;
import org.eclipse.jdt.internal.junit.ui.TestRunnerViewPart;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

public class RestAPIExtensionPerspectiveFactory extends AbstractPerspectiveFactory {

    public static final String REST_API_EXTENSION_PERSPECTIVE_ID = "org.bonitasoft.studio.rest.api.extension.perspective";

    @Override
    public void createInitialLayout(final IPageLayout layout) {
        final String editorArea = layout.getEditorArea();

        final IFolderLayout leftFolder = layout.createFolder("left", IPageLayout.LEFT, getExplorerViewRatio(), editorArea); //$NON-NLS-1$
        leftFolder.addView(BonitaProjectExplorer.ID);
        IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, 0.75f, "left");
        bottomLeft.addView(PROBLEM_VIEW_ID);

        final IFolderLayout bottomfolder = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.75f, editorArea); //$NON-NLS-1$
        bottomfolder.addView(MavenConsoleView.VIEW_ID);
        if (RepositoryManager.getInstance().getCurrentRepository().isShared("org.eclipse.egit.core.GitProvider")) {
            bottomfolder.addView("org.eclipse.egit.ui.StagingView");
            bottomfolder.addPlaceholder("org.eclipse.team.ui.GenericHistoryView");
        }

        final IFolderLayout rightFolder = layout.createFolder("right", IPageLayout.RIGHT, 0.75f, editorArea); //$NON-NLS-1$
        rightFolder.addView(IPageLayout.ID_OUTLINE);
        rightFolder.addView(TestRunnerViewPart.NAME);
    }

    @Override
    public boolean isRelevantFor(final IEditorPart part) {
        return isInsideprojectWithREStApiExtensionNature(part);
    }

    @Override
    public String getID() {
        return REST_API_EXTENSION_PERSPECTIVE_ID;
    }

}
