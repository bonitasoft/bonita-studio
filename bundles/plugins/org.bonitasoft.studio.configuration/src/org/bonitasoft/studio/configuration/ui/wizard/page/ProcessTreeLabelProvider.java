/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.configuration.ui.wizard.page;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public class ProcessTreeLabelProvider extends LabelProvider {
	
	private ComposedAdapterFactory adapterFactory;
	private AdapterFactoryLabelProvider labelProvider;

	public ProcessTreeLabelProvider(){
		this.adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		this.labelProvider = new AdapterFactoryLabelProvider(adapterFactory);
	}
	
	@Override
	public Image getImage(Object element) {
		if(element instanceof EObject){
			return labelProvider.getImage(element) ;
		}
		return super.getImage(element);
	}

	
	@Override
	public String getText(Object element) {
		if(element instanceof EObject){
			return labelProvider.getText(element) ;
		}
		return super.getText(element);
	}
}
