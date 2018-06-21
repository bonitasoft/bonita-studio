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
import org.bonitasoft.studio.diagram.custom.views.BPMNPaletteView;
import org.bonitasoft.studio.migration.model.report.Report;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

/**
 * @author Aurelien Pupier
 */
public class PerspectiveProcessFactory extends AbstractPerspectiveFactory {

    public static String PROCESS_PERSPECTIVE_ID = "org.bonitasoft.studio.perspective.process";

    @Override
    public void createInitialLayout(final IPageLayout layout) {
        // Editors are placed for free.
        final String editorArea = layout.getEditorArea();
        // Bottom left.
        final IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, (float) 0.65, //$NON-NLS-1$
                editorArea);//$NON-NLS-1$
        bottomLeft.addView("org.bonitasoft.studio.views.overview.tree");
        bottomLeft.addView("org.bonitasoft.studio.views.overview");

        // Bottom right.
        final IFolderLayout bottomRight = layout.createFolder("bottomRight", IPageLayout.RIGHT, (float) 0.3, //$NON-NLS-1$
                "bottomLeft");
        bottomRight.addView("org.bonitasoft.studio.views.properties.process.general");
        bottomRight.addView("org.bonitasoft.studio.views.properties.process.data");
        bottomRight.addView("org.bonitasoft.studio.views.properties.process.execution");
        bottomRight.addView("org.bonitasoft.studio.views.properties.process.appearance");

        for (final String viewId : BonitaPerspectivesUtils.getContributedPropertiesViews(PROCESS_PERSPECTIVE_ID)) {
            bottomRight.addView(viewId);
        }
        bottomRight.addView("org.bonitasoft.studio.validation.view");
        if (RepositoryManager.getInstance().getCurrentRepository().isShared("org.eclipse.egit.core.GitProvider")) {
            bottomRight.addView("org.eclipse.egit.ui.StagingView");
            bottomRight.addPlaceholder("org.eclipse.team.ui.GenericHistoryView");
        }

        createLeftViewFolder(layout, editorArea);
    }

    protected void configureIntroView(final IPageLayout layout) {
        layout.getViewLayout("org.eclipse.ui.internal.introview").setCloseable(false);
        layout.getViewLayout("org.eclipse.ui.internal.introview").setMoveable(false);
    }

    protected void createLeftViewFolder(final IPageLayout layout, final String editorArea) {
        final IFolderLayout left = layout.createFolder(
                "left",
                IPageLayout.LEFT,
                (float) 0.1,
                editorArea);
        left.addView(BPMNPaletteView.ID);
        layout.getViewLayout(BPMNPaletteView.ID).setCloseable(false);
        layout.getViewLayout(BPMNPaletteView.ID).setMoveable(false);
        left.addPlaceholder("org.bonitasoft.studio.migration.view");
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory#isRelevantFor(org.eclipse.ui.IEditorPart)
     */
    @Override
    public boolean isRelevantFor(final IEditorPart part) {
        if (part instanceof ProcessDiagramEditor) {
            return !isOngoingMigration((ProcessDiagramEditor) part);
        }
        return false;
    }

    protected boolean isOngoingMigration(final ProcessDiagramEditor processDiagramEditor) {
        final EObject element = processDiagramEditor.getDiagramEditPart().resolveSemanticElement();
        if (element != null) {
            final Resource resource = element.eResource();
            if (resource != null) {
                for (final EObject root : resource.getContents()) {
                    if (root instanceof Report) {
                        return true;
                    }
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
        return PROCESS_PERSPECTIVE_ID;
    }

}
