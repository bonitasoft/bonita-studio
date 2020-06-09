/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.businessobject.editor;

import org.bonitasoft.studio.businessobject.editor.editor.BusinessDataModelEditor;
import org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

public class BdmPerspectiveFactory extends AbstractPerspectiveFactory {

    public static final String BDM_PERSPECTIVE_ID = "org.bonitasoft.studio.businessobject.perspective";

    @Override
    public boolean isRelevantFor(final IEditorPart part) {
        return part instanceof BusinessDataModelEditor;
    }

    @Override
    public String getID() {
        return BDM_PERSPECTIVE_ID;
    }

    @Override
    public void createInitialLayout(IPageLayout layout) {
        String editorArea = layout.getEditorArea();
        final IFolderLayout leftFolder = layout.createFolder(
                "leftFolder", IPageLayout.LEFT, 0.2f, editorArea);
        leftFolder.addView("org.bonitasoft.studio.application.project.explorer");
        if (RepositoryManager.getInstance().getCurrentRepository().isShared("org.eclipse.egit.core.GitProvider")) {
            leftFolder.addView("org.eclipse.egit.ui.StagingView");
            leftFolder.addPlaceholder("org.eclipse.team.ui.GenericHistoryView");
        }
    }

}
