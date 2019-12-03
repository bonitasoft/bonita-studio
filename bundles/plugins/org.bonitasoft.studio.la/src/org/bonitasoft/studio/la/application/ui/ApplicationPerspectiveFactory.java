/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.ui;

import org.bonitasoft.studio.application.views.BonitaProjectExplorer;
import org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.wst.xml.ui.internal.tabletree.XMLMultiPageEditorPart;

public class ApplicationPerspectiveFactory extends AbstractPerspectiveFactory {

    public static final String APPLICATION_PERSPECTIVE_ID = "org.bonitasoft.studio.la.perspective";

    @Override
    public void createInitialLayout(final IPageLayout layout) {
        final String editorArea = layout.getEditorArea();

        final IFolderLayout leftFolder = layout.createFolder(
                "leftFolder", IPageLayout.LEFT, getExplorerViewRatio(), editorArea);
        leftFolder.addView(BonitaProjectExplorer.ID);
        leftFolder.addView(IPageLayout.ID_OUTLINE);
        if (RepositoryManager.getInstance().getCurrentRepository().isShared("org.eclipse.egit.core.GitProvider")) {
            leftFolder.addView("org.eclipse.egit.ui.StagingView");
            leftFolder.addPlaceholder("org.eclipse.team.ui.GenericHistoryView");
        }
    }

    @Override
    public boolean isRelevantFor(final IEditorPart part) {
        return part instanceof XMLMultiPageEditorPart;
    }

    @Override
    public String getID() {
        return APPLICATION_PERSPECTIVE_ID;
    }

}
