/**
 * Copyright (C) 2010-2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.diagram.custom.perspective;

import org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory;
import org.bonitasoft.studio.common.perspectives.BonitaPerspectivesUtils;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;


public class PerspectiveProcessFactory extends AbstractPerspectiveFactory {

    public static final String PROCESS_PERSPECTIVE_ID = "org.bonitasoft.studio.perspective.process";

    @Override
    public void createInitialLayout(final IPageLayout layout) {
        // Editors are placed for free.
        final String editorArea = layout.getEditorArea();

        final IFolderLayout left = layout.createFolder(
                "left",
                IPageLayout.LEFT,
                getExplorerViewRatio(),
                editorArea);
        left.addView("org.bonitasoft.studio.application.project.explorer");
        left.addView("org.bonitasoft.studio.views.overview.tree");
        
        IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, 0.7f, "left");
        bottomLeft.addView(PROBLEM_VIEW_ID);

        layout.createFolder("right", IPageLayout.RIGHT, 100, editorArea);
        // Bottom right.
        final IFolderLayout bottomRight = layout.createFolder("bottom", IPageLayout.BOTTOM, //$NON-NLS-1$
                0.7f, editorArea);
        bottomRight.addView("org.bonitasoft.studio.views.properties.process.general");
        bottomRight.addView("org.bonitasoft.studio.views.properties.process.data");
        bottomRight.addView("org.bonitasoft.studio.views.properties.process.execution");
        bottomRight.addView("org.bonitasoft.studio.views.properties.process.appearance");

        for (final String viewId : BonitaPerspectivesUtils.getContributedPropertiesViews(PROCESS_PERSPECTIVE_ID)) {
            bottomRight.addView(viewId);
        }
        bottomRight.addView("org.bonitasoft.studio.validation.view");
        bottomRight.addView("org.bonitasoft.studio.views.overview");
        if (RepositoryManager.getInstance().getCurrentRepository().isShared("org.eclipse.egit.core.GitProvider")) {
            bottomRight.addView("org.eclipse.egit.ui.StagingView");
            bottomRight.addPlaceholder("org.eclipse.team.ui.GenericHistoryView");
        }

    }

    @Override
    public boolean isRelevantFor(final IEditorPart part) {
        return part instanceof ProcessDiagramEditor;
    }

    @Override
    public String getID() {
        return PROCESS_PERSPECTIVE_ID;
    }

}
