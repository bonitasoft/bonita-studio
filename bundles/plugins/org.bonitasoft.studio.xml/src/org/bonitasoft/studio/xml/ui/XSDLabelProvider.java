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
package org.bonitasoft.studio.xml.ui;

import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.bonitasoft.studio.xml.Messages;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;

/**
 * @author Mickael Istria
 *
 */
public class XSDLabelProvider extends ColumnLabelProvider implements ILabelDecorator {

	private final boolean useQualifiedName;

	/**
	 * 
	 */
	public XSDLabelProvider(boolean useQualifiedName) {
		super();
		this.useQualifiedName = useQualifiedName;
	}
	
	/**
	 * 
	 */
	public XSDLabelProvider() {
		this(false);
	}
	
	@Override
	public String getText(Object item) {
		if (item == XSDContentProvider.WHOLE_XML) {
			return Messages.wholeXML;
		} else if (item instanceof XSDContentProvider.Append) {
			return Messages.appendChild;
		} else if (item instanceof XSDNamedComponent) {
			XSDNamedComponent xsdNamedComponent = (XSDNamedComponent)item;
			if(useQualifiedName){
				return xsdNamedComponent.getQName();
			}else{
				return xsdNamedComponent.getName();
			}
		} else {
			return super.getText(item);
		}
	}
	
	@Override
	public Image getImage(Object item) {
		if (item == XSDContentProvider.WHOLE_XML) {
			return Pics.getImage(PicsConstants.xml);
		} else if (item instanceof XSDContentProvider.Append) {
			return Pics.getImage(PicsConstants.xml3dots);
		} else if (item instanceof XSDAttributeUse || item instanceof XSDAttributeDeclaration) {
			return Pics.getImage(PicsConstants.xmlAttribute);
		} else if (item instanceof XSDElementDeclaration) {
			return Pics.getImage(PicsConstants.xmlElement);
		} else {
			return super.getImage(item);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILabelDecorator#decorateImage(org.eclipse.swt.graphics.Image, java.lang.Object)
	 */
	public Image decorateImage(Image image, Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILabelDecorator#decorateText(java.lang.String, java.lang.Object)
	 */
	public String decorateText(String text, Object element) {
		if (element instanceof XSDElementDeclaration) {
			XSDElementDeclaration decl = (XSDElementDeclaration)element;
			XSDTypeDefinition declaredType = decl.getType();
			if (declaredType != null) {
				XSDSimpleTypeDefinition type = declaredType.getSimpleType();
				if (type != null) {
					return text + " (/text() : " + type.getName() + ")";
				}
			}
		}
		return null;
	}
}
