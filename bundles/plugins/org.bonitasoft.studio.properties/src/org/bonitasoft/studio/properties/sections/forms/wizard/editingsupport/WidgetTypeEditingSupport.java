/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.properties.sections.forms.wizard.editingsupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.diagram.form.custom.model.WidgetMapping;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.Widget;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class WidgetTypeEditingSupport extends EditingSupport {

	private HashMap<String, EClass> widgetTypesByNames;

	/**
	 * @param viewer
	 */
	public WidgetTypeEditingSupport(ColumnViewer viewer) {
		super(viewer);
		widgetTypesByNames = new HashMap<String,EClass>();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
	 */
	@Override
	protected CellEditor getCellEditor(Object element) {
		return new ComboBoxCellEditor((Composite) getViewer().getControl(), getItemsFor(element), SWT.READ_ONLY);
	}


	protected String[] getItemsFor(Object element) {
		if(element instanceof WidgetMapping){
			WidgetMapping mapping = (WidgetMapping) element;
			List<String> widgetNames = new ArrayList<String>();
			for(EClass widgetType : mapping.getCompatibleWidgetTypes()){
				String text = getText(widgetType,mapping);
				widgetTypesByNames.put(text,widgetType);
				widgetNames.add(text);
			}
			return widgetNames.toArray(new String[widgetNames.size()]);
		}
		return new String[0];
	}

	public String getText(EClass widgetType, WidgetMapping mapping) {
		return NamingUtils.getFormPaletteText(false, widgetType);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
	 */
	@Override
	protected boolean canEdit(Object element) {
		if(element instanceof WidgetMapping){
			return getItemsFor(element).length > 1;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
	 */
	@Override
	protected Object getValue(Object element) {
		return Arrays.asList(getItemsFor(element)).indexOf(NamingUtils.getFormPaletteText(false,((WidgetMapping)element).getWidgetType().eClass()));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void setValue(Object element, Object value) {
		EClass widgetEClass = getEClassByWidgetName(getItemsFor(element)[(Integer)value]);
		((WidgetMapping)element).setWidgetType(createWidgetFor(widgetEClass));
		Display.getDefault().asyncExec(new Runnable() {

			@Override
			public void run() {
				getViewer().refresh();		
			}
		}) ;
	}


	protected EClass getEClassByWidgetName(String widgetName) {
		return widgetTypesByNames.get(widgetName);
	}

	protected Widget createWidgetFor(EClass widgetEClass) {
		return (Widget) FormFactory.eINSTANCE.create(widgetEClass);
	}

}
