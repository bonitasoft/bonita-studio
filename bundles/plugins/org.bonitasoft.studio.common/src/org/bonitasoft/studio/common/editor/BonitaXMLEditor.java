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
 
package org.bonitasoft.studio.common.editor;

import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.wst.xml.ui.internal.tabletree.XMLMultiPageEditorPart;

/**
 * @author Romain Bioteau
 *
 */
@SuppressWarnings("restriction")
public class BonitaXMLEditor extends XMLMultiPageEditorPart {

	public final static String ID = "org.bonitasoft.studio.editor.xml"; //$NON-NLS-1$
	

	@Override
	public Object getAdapter(Class key) {

		if(IPropertySheetPage.class.equals(key)) {
			return null;
		}else if(IContentOutlinePage.class.equals(key)){
			return null;
		}
		return super.getAdapter(key);
	}

}
