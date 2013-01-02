/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.perspectives;

import org.eclipse.gef.ui.views.palette.PaletteView;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

/**
 * @author Aurelien Pupier
 */
public class PerspectiveProcessFactory extends AbstractPerspectiveFactory {
	
	public static String PROCESS_PERSPECTIVE_ID = "org.bonitasoft.studio.perspective.process";

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
        final float ratioForBottomPart = (float) (400. / 650.);
		IFolderLayout bottomLeft = layout.createFolder(
                "bottomLeft", IPageLayout.BOTTOM, ratioForBottomPart,//$NON-NLS-1$
                "left");//$NON-NLS-1$
        bottomLeft.addView("org.bonitasoft.studio.views.overview");
        bottomLeft.setProperty(VIEW_KIND, BONITA_OVERVIEW);
        
        // Bottom right.
		IFolderLayout bottomRight = layout.createFolder(
                "bottomRight", IPageLayout.BOTTOM, ratioForBottomPart,//$NON-NLS-1$
                editorArea);
		bottomRight.addView("org.bonitasoft.studio.views.properties.process.general");
		bottomRight.addView("org.bonitasoft.studio.views.properties.application");
		bottomRight.addView("org.bonitasoft.studio.views.properties.process.appearance");
	
		for (String viewId : BonitaPerspectivesUtils.getContributedPropertiesViews(PROCESS_PERSPECTIVE_ID)) {
			bottomRight.addView(viewId);
		}
		
		//bottomRight.setProperty("viewProperty", "true");
		bottomRight.setProperty(VIEW_KIND, BONITA_TABS);
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory#isRelevantFor(org.eclipse.ui.IEditorPart)
	 */
	@Override
	public boolean isRelevantFor(IEditorPart part) {
		return part.getClass().getSimpleName().startsWith("ProcessDiagram");
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory#getID()
	 */
	@Override
	public String getID() {
		return PROCESS_PERSPECTIVE_ID;
	}


}
