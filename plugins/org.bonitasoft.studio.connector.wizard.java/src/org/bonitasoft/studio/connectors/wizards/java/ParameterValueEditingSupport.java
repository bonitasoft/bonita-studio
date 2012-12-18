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
package org.bonitasoft.studio.connectors.wizards.java;

import java.io.Serializable;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

/**
 * @author Mickael Istria
 *
 */
public class ParameterValueEditingSupport extends EditingSupport {

	private TextCellEditor propertyCellEditor;
	private JavaConnectorWizardPage javaConnectorWizardPage;

	/**
	 * @param javaConnectorWizardPage 
	 * @param viewer
	 */
	public ParameterValueEditingSupport(JavaConnectorWizardPage javaConnectorWizardPage, ColumnViewer viewer) {
		super(viewer);
		this.javaConnectorWizardPage = javaConnectorWizardPage; 
		propertyCellEditor = new TextCellEditor((Composite)viewer.getControl());
		propertyCellEditor.getControl().setBackground(ColorConstants.white);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#canEdit(java.lang.Object)
	 */
	@Override
	public boolean canEdit(Object element) {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#getCellEditor(java.lang.Object)
	 */
	@Override
	protected CellEditor getCellEditor(Object element) {
		if (element instanceof ArgDescription) {
			return propertyCellEditor;
		} else if (element instanceof MethodEntry) {
			return new RemoveMethodCellEditor(javaConnectorWizardPage, (MethodEntry)element);
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#getValue(java.lang.Object)
	 */
	@Override
	protected Object getValue(Object element) {
		if (element instanceof ArgDescription) {
			Object res = ((ArgDescription)element).value;
			if (res == null) {
				res = "";
			}
			return res;
		} else {
			return "";
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.EditingSupport#setValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	protected void setValue(Object element, Object value) {
		if (element instanceof ArgDescription) {
			((ArgDescription)element).value = (Serializable) value;
			javaConnectorWizardPage.refreshMethodCalls();
		}
		getViewer().getControl().notifyListeners(SWT.Selection, new Event()) ;
	}

}
