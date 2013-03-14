/**
 * Copyright (C) 2010-2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.application.views.form;

import org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory;
import org.bonitasoft.studio.common.perspectives.BonitaPerspectivesUtils;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.eclipse.gef.ui.views.palette.PaletteView;
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
        
        IFolderLayout left = layout.createFolder(
        		"left",
        		IPageLayout.LEFT,
        		(float) 0.15,
        		editorArea);
        left.addView(PaletteView.ID);
        //left.addPlaceholder("ID of the migration view");
        
        // Bottom left.
		IFolderLayout bottomLeft = layout.createFolder(
                "bottomLeft", IPageLayout.BOTTOM, (float) (400. / 650.),//$NON-NLS-1$
                editorArea);//$NON-NLS-1$
        bottomLeft.addView("org.bonitasoft.studio.views.overview");
        bottomLeft.setProperty(VIEW_KIND, BONITA_OVERVIEW);
        
        // Bottom right.
		IFolderLayout bottomRight = layout.createFolder(
                "bottomRight", IPageLayout.RIGHT, (float) 0.33,//$NON-NLS-1$
                "bottomLeft");
		bottomRight.addView("org.bonitasoft.studio.views.properties.form.general");
		bottomRight.addView("org.bonitasoft.studio.views.properties.form.appearance");

		for (String viewId : BonitaPerspectivesUtils.getContributedPropertiesViews(FORM_PERSPECTIVE_ID)) {
			bottomRight.addView(viewId);
		}
		bottomRight.setProperty(VIEW_KIND, BONITA_TABS);
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory#isRelevantFor()
	 */
	@Override
	public boolean isRelevantFor(IEditorPart part) {
		return part instanceof FormDiagramEditor;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory#getID()
	 */
	@Override
	public String getID() {
		return FORM_PERSPECTIVE_ID;
	}
	
	protected void configureIntroView(IPageLayout layout) {
		layout.getViewLayout("org.eclipse.ui.internal.introview").setCloseable(false);
		layout.getViewLayout("org.eclipse.ui.internal.introview").setMoveable(false);
	}

}
