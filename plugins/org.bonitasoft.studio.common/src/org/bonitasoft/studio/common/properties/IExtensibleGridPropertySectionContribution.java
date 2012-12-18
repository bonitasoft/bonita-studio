/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.properties;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * This interface represents a contribution to the {@link ExtensibleGridPropertySection}.
 * @author Mickael Istria
 *
 */
public interface IExtensibleGridPropertySectionContribution {

	/**
	 * Return whether this contribution is to be displayed for specified eObject
	 * @param eObject
	 * @return
	 */
	public boolean isRelevantFor(EObject eObject);
	
	/**
	 * Refreshes the control. Called by {@link ExtensibleGridPropertySection#refresh()}
	 */
	void refresh();

	/**
	 * @return The label for this property
	 */
	String getLabel();

	/**
	 * @param composite The composite that will host the control. The layout of this
	 * composite must be set by client. Default is NONE. Clients are also free to set
	 * the {@link GridData} of the composite.
	 * @param widgetFactory a widget factory to use
	 * @param extensibleGridPropertySection 
	 */
	void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection extensibleGridPropertySection);

	/**
	 * Sets the context eObject.
	 * @param object set the eObject currently selected. This is called by {@link ExtensibleGridPropertySection#setEObject(EObject)}
	 * This method must not perform UI refresh, which must be done in {@link #refresh()}.
	 */
	void setEObject(EObject object);

	/**
	 * Update the currently selected elements. Useful when some diagram logic is required
	 * @param selection The current selection on diagram
	 */
	public void setSelection(ISelection selection);
	
	/**
	 * @param editingDomain
	 */
	void setEditingDomain(TransactionalEditingDomain editingDomain);
	
	void dispose();
	
	
	
}
