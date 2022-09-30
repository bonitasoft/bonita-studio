/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.perspective;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.ui.perspectives.AbstractPerspectiveFactory;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.internal.browser.WebBrowserEditor;


public class TextEditorPerspectiveFactory extends AbstractPerspectiveFactory {

    public static final String PERSPECTIVE_ID = "org.bonitasoft.studio.perspective.textEditor";

    @Override
    public void createInitialLayout(final IPageLayout layout) {
        final String editorArea = layout.getEditorArea();

        final IFolderLayout leftView = layout.createFolder(
                "left", IPageLayout.LEFT, getExplorerViewRatio(), editorArea);
        leftView.addView("org.bonitasoft.studio.application.project.explorer");
        IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, 0.75f, "left");
        bottomLeft.addView(PROBLEM_VIEW_ID);
        
        final IFolderLayout bottomfolder = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.75f, editorArea); //$NON-NLS-1$
        RepositoryManager.getInstance().getCurrentRepository()
        .filter(repo -> repo.isShared("org.eclipse.egit.core.GitProvider"))
        .ifPresent(repo -> {
            bottomfolder.addView("org.eclipse.egit.ui.StagingView");
            bottomfolder.addPlaceholder("org.eclipse.team.ui.GenericHistoryView");
        });
    }

    @Override
    public boolean isRelevantFor(final IEditorPart part) {
        return (part instanceof TextEditor || part instanceof WebBrowserEditor)
                && !isInsideprojectWithREStApiExtensionNature(part);
    }


    @Override
    public String getID() {
        return PERSPECTIVE_ID;
    }

}
