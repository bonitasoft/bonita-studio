/**
 * Copyright (C) 2009-2011 BonitaSoft S.A.
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
 
package org.bonitasoft.studio.common.editor;

import org.eclipse.jdt.internal.ui.javaeditor.CompilationUnitEditor;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaJavaEditor extends CompilationUnitEditor {

	public static final String ID = "org.bonitasoft.studio.editor.java" ; //$NON-NLS-1$
	
	
	public BonitaJavaEditor() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jdt.internal.ui.javaeditor.CompilationUnitEditor#getAdapter(java.lang.Class)
	 */
	@SuppressWarnings("restriction")
	@Override
	public Object getAdapter(Class required) {
		if(required.equals(IContentOutlinePage.class)){
			if (fOutlinePage == null)
				fOutlinePage= createOutlinePage();
			return fOutlinePage;
		}
		return super.getAdapter(required);
	}
	
	/**
	 * Creates the outline page used with this editor.
	 *
	 * @return the created Java outline page
	 */
	@SuppressWarnings("restriction")
	protected BonitaJavaOutlinePage createOutlinePage() {
		BonitaJavaOutlinePage page= new BonitaJavaOutlinePage(fOutlinerContextMenuId, this);
		setOutlinePageInput(page, getEditorInput());
		return page;
	}
	
	
	/**
	 * @generated BonitaSoft
	 * Switch the perspective to have the correct properties view when arriving on the editor
	 **/
	@Override
	@SuppressWarnings("restriction")
	public void setFocus() {
		super.setFocus();
	}
}
