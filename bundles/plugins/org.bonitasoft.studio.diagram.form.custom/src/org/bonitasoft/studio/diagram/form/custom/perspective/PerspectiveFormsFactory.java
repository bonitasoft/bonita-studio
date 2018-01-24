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
package org.bonitasoft.studio.diagram.form.custom.perspective;

import org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory;
import org.bonitasoft.studio.common.perspectives.BonitaPerspectivesUtils;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.form.custom.views.FormPaletteView;
import org.bonitasoft.studio.migration.model.report.Report;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

/**
 * @author Aurelien Pupier
 */
public class PerspectiveFormsFactory extends AbstractPerspectiveFactory {

    protected static String FORM_PERSPECTIVE_ID = "org.bonitasoft.studio.common.perspective.form";

    public void createInitialLayout(IPageLayout layout) {
        // Editors are placed for free.
        String editorArea = layout.getEditorArea();

        // Bottom left.
        IFolderLayout bottomLeft = layout.createFolder(
                "bottomLeft", IPageLayout.BOTTOM, (float) 0.650, //$NON-NLS-1$
                editorArea);//$NON-NLS-1$
        bottomLeft.addView("org.bonitasoft.studio.views.overview.tree");
        bottomLeft.addView("org.bonitasoft.studio.views.overview");
        // Bottom right.
        IFolderLayout bottomRight = layout.createFolder(
                "bottomRight", IPageLayout.RIGHT, (float) 0.3, //$NON-NLS-1$
                "bottomLeft");
        bottomRight.addView("org.bonitasoft.studio.views.properties.form.general");
        bottomRight.addView("org.bonitasoft.studio.views.properties.form.appearance");
        bottomRight.addView("org.bonitasoft.studio.validation.view");
        bottomRight.addView("org.bonitasoft.studio.views.properties.form.preview");

        for (String viewId : BonitaPerspectivesUtils.getContributedPropertiesViews(FORM_PERSPECTIVE_ID)) {
            bottomRight.addView(viewId);
        }
        if (RepositoryManager.getInstance().getCurrentRepository().isShared("org.eclipse.egit.core.GitProvider")) {
            bottomRight.addView("org.eclipse.egit.ui.StagingView");
        }

        createLeftViewFolder(layout, editorArea);
    }

    protected void createLeftViewFolder(IPageLayout layout, String editorArea) {
        IFolderLayout left = layout.createFolder(
                "left",
                IPageLayout.LEFT,
                (float) 0.1,
                editorArea);
        left.addView(FormPaletteView.ID);
        layout.getViewLayout(FormPaletteView.ID).setCloseable(false);
        layout.getViewLayout(FormPaletteView.ID).setMoveable(false);
        left.addPlaceholder("org.bonitasoft.studio.migration.view");
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory#isRelevantFor()
     */
    @Override
    public boolean isRelevantFor(IEditorPart part) {
        if (part instanceof FormDiagramEditor) {
            return !isOngoingMigration((FormDiagramEditor) part);
        }
        return false;
    }

    protected boolean isOngoingMigration(FormDiagramEditor formDiagramEditor) {
        final Resource resource = formDiagramEditor.getDiagramEditPart().resolveSemanticElement().eResource();
        if (resource != null) {
            for (EObject root : resource.getContents()) {
                if (root instanceof Report) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory#getID()
     */
    @Override
    public String getID() {
        return FORM_PERSPECTIVE_ID;
    }

}
