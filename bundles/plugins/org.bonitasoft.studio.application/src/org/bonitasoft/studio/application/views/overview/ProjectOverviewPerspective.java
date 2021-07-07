/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.views.overview;

import org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

public class ProjectOverviewPerspective extends AbstractPerspectiveFactory {

    public static final String EXTENSION_PERSPECTIVE_ID = "org.bonitasoft.studio.application.extension.perspective";

    @Override
    public void createInitialLayout(final IPageLayout layout) {
        String editorArea = layout.getEditorArea();

        IFolderLayout leftFolder = layout.createFolder(
                "left", IPageLayout.LEFT, getExplorerViewRatio(), editorArea);
        leftFolder.addView("org.bonitasoft.studio.application.project.explorer");
        IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, 0.7f, "left");
        bottomLeft.addView(PROBLEM_VIEW_ID);
    }

    @Override
    public boolean isRelevantFor(IEditorPart part) {
        return part instanceof ProjectOverviewEditorPart;
    }

    @Override
    public String getID() {
        return EXTENSION_PERSPECTIVE_ID;
    }

}
