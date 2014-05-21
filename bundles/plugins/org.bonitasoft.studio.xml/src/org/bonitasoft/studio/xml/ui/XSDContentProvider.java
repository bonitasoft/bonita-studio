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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeGroupContent;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDParticleContent;
import org.eclipse.xsd.XSDSimpleTypeDefinition;

/**
 * @author Mickael Istria
 *
 */
public class XSDContentProvider implements ITreeContentProvider {

	public static final class Append {
		private static final Map<Object, Append> REGISTRY = new HashMap<Object, Append>();
		
		private Append() {}
		
		/**
		 * @param parentElement
		 * @return
		 */
		public static Object newInstance(Object parentElement) {
			if (parentElement == null) {
				return new Append();
			} else {
				if (!REGISTRY.containsKey(parentElement)) {
					REGISTRY.put(parentElement, new Append());
				}
				return REGISTRY.get(parentElement);
			}
		}
	}
	
	public static final Object WHOLE_XML = new Object();
	private XSDElementDeclaration inputElement;
	private boolean browseOnly;

	/**
	 * @param browseOnly  Whether the dialog should show a tree only for browsing, without "Append" nodes
	 */
	public XSDContentProvider(boolean browseOnly) {
		this.browseOnly = browseOnly;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {
		return new Object[] {WHOLE_XML};
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement == WHOLE_XML) {
			if(inputElement != null){
				return new Object[] {inputElement};
			} else {
				return new Object[]{} ;
			}
		}
		if (parentElement instanceof List<?>) {
			return ((List<?>)parentElement).toArray();
		}
		if (parentElement instanceof XSDAttributeUse) {
			XSDAttributeUse attribute = (XSDAttributeUse)parentElement;
			return new Object[] {attribute.getAttributeDeclaration()};
		}
		if (parentElement instanceof XSDElementDeclaration) {
			XSDElementDeclaration decl = ((XSDElementDeclaration)parentElement);
			return getChildren(decl.getType());
		}
		if (parentElement instanceof XSDParticle) {
			XSDParticleContent content = ((XSDParticle) parentElement).getContent();
			if (content instanceof XSDAttributeDeclaration) {
				return new Object[] { content };
			} else if (content instanceof XSDElementDeclaration) {
				XSDElementDeclaration decl = (XSDElementDeclaration)content;
				if (decl.getType() != null) {
					return new Object[] {content};
				} else {
					return new Object[] {decl.getResolvedElementDeclaration()};
				}
			} else if (content instanceof XSDModelGroup) {
				return getChildren(content);
			}
		}
		if (parentElement instanceof XSDComplexTypeDefinition) {
			XSDComplexTypeDefinition def = (XSDComplexTypeDefinition)parentElement;
			List<Object> res = new ArrayList<Object>();
			for (XSDAttributeGroupContent attribute : def.getAttributeContents()) {
				for (Object item : getChildren(attribute)) {
					res.add(item);
				}
			}
			Object[] children = getChildren(def.getContentType());
			for (Object item : children) {
				res.add(item);
			}
			if (!browseOnly) {
				res.add(Append.newInstance(parentElement));
			}
			return res.toArray();
		} else if (parentElement instanceof XSDModelGroup) {
			XSDModelGroup group = (XSDModelGroup)parentElement;
			List<Object> res = new ArrayList<Object>();
			for (XSDParticle particle : group.getContents()) {
				for (Object item : getChildren(particle)) {
					res.add(item);
				}
			}
			return res.toArray();
		} else {
			return new Object[] {};
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
	 */
	public Object getParent(Object element) {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
	 */
	public boolean hasChildren(Object element) {
		return ! (
			element instanceof Append ||
			element instanceof XSDSimpleTypeDefinition ||
			element instanceof XSDAttributeUse ||
			element instanceof XSDAttributeDeclaration ||
			(element instanceof XSDElementDeclaration && ((XSDElementDeclaration)element).getType() instanceof XSDSimpleTypeDefinition));
	}
	
	public void setElement(XSDElementDeclaration element) {
		this.inputElement = element;
	}

	public XSDElementDeclaration getElement() {
		return this.inputElement;
	}
}
