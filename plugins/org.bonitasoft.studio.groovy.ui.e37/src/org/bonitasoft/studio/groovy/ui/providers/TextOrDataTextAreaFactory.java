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
package org.bonitasoft.studio.groovy.ui.providers;

import java.util.Collection;

import org.bonitasoft.studio.groovy.ui.widgets.TextOrData;
import org.bonitasoft.studio.groovy.ui.widgets.TextOrDataForCellEditor;
import org.bonitasoft.studio.groovy.ui.widgets.TextOrDataTextArea;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Element;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 *
 */
public class TextOrDataTextAreaFactory implements ITextOrDataFactory {

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.groovy.providers.ITextOrDataFactory#createTextOrData(org.eclipse.swt.widgets.Composite, org.bonitasoft.studio.model.process.Element, org.eclipse.emf.transaction.TransactionalEditingDomain, org.eclipse.emf.ecore.EStructuralFeature)
	 */
	@SuppressWarnings("unchecked")
	public TextOrData createTextOrData(Composite composite, Element element,EStructuralFeature eFeature) {
		TextOrDataTextArea textOrData = new TextOrDataTextArea(composite, true, element); 
		if(element != null && element.eGet(eFeature) instanceof Collection<?>){
			textOrData.addConnectors((Collection<Connector>)element.eGet(eFeature)) ;
		}
		textOrData.setConnectorsFeaure(eFeature) ;
		return textOrData ;
	}


	public TextOrData createTextOrData(Composite composite, Element element,Collection<EClass> eClasses) {
		return new TextOrDataTextArea(composite, element,eClasses);
	}

	public TextOrData createTextOrData(Composite composite, Element element) {
		return new TextOrDataTextArea(composite, element);
	}
	

	public TextOrData createTextOrData(Composite composite, Element element,
			boolean isPassword, boolean usePassword) {
		return new TextOrDataTextArea(composite, element,isPassword,usePassword);
	}
	
	public TextOrData createTextOrData(Composite parent, Element element,FocusListener focusListener,boolean isPassword, boolean usePassword) {
		return new TextOrDataForCellEditor(parent, element, focusListener,isPassword,true);
	}
	
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.groovy.providers.ITextOrDataFactory#appliesTo(java.lang.Object)
	 */
	public boolean appliesTo(Object object) {
		return TextOrDataTextArea.class.equals(object);
	}

}
