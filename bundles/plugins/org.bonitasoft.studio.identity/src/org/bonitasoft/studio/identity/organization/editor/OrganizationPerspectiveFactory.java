/**
 * Copyright (C) 2020 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.identity.organization.editor;

import org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

public class OrganizationPerspectiveFactory extends AbstractPerspectiveFactory {

    public static final String ORGANIZATION_PERSPECTIVE_ID = "org.bonitasoft.studio.identity.organization.perspective";

    @Override
    public void createInitialLayout(final IPageLayout layout) {
        final String editorArea = layout.getEditorArea();

        final IFolderLayout leftFolder = layout.createFolder(
                "left", IPageLayout.LEFT, getExplorerViewRatio(), editorArea);
        leftFolder.addView("org.bonitasoft.studio.application.project.explorer");
        leftFolder.addView(IPageLayout.ID_OUTLINE);
        IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, 0.7f, "left");
        bottomLeft.addView(PROBLEM_VIEW_ID);

        final IFolderLayout bottomfolder = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.7f, editorArea); //$NON-NLS-1$
        if (RepositoryManager.getInstance().getCurrentRepository().isShared("org.eclipse.egit.core.GitProvider")) {
            bottomfolder.addView("org.eclipse.egit.ui.StagingView");
            bottomfolder.addPlaceholder("org.eclipse.team.ui.GenericHistoryView");
        }
    }

    @Override
    public boolean isRelevantFor(final IEditorPart part) {
        return part instanceof OrganizationEditor;
    }

    @Override
    public String getID() {
        return ORGANIZATION_PERSPECTIVE_ID;
    }

}
