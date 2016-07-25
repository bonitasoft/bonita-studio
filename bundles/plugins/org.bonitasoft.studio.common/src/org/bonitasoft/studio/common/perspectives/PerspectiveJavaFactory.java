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

import org.eclipse.jdt.internal.ui.javaeditor.JavaEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;

/**
 * @author Aurelien Pupier
 */
public class PerspectiveJavaFactory extends AbstractPerspectiveFactory {

	public static String JAVA_PERSPECTIVE_ID = "org.bonitasoft.studio.perspective.java";

	protected void configureIntroView(final IPageLayout layout) {
		layout.getViewLayout("org.eclipse.ui.internal.introview").setCloseable(false);
		layout.getViewLayout("org.eclipse.ui.internal.introview").setMoveable(false);
	}

	@Override
    public void createInitialLayout(final IPageLayout layout) {
       final String editorArea = layout.getEditorArea();

       // Bottom left.
       final IFolderLayout rightView = layout.createFolder(
               "rightView", IPageLayout.RIGHT, 0.75f,//$NON-NLS-1$
               editorArea);//$NON-NLS-1$
       rightView.addView(IPageLayout.ID_OUTLINE);
       layout.setFixed(true);
	}



	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory#isRelevantFor(org.eclipse.ui.IEditorPart)
	 */
	@Override
	public boolean isRelevantFor(final IEditorPart part) {
        return part instanceof JavaEditor && !isInsideprojectWithREStApiExtensionNature(part);
	}


	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.perspectives.AbstractPerspectiveFactory#getID()
	 */
	@Override
	public String getID() {
		return JAVA_PERSPECTIVE_ID;
	}

}
