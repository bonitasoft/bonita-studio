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

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * @author Mickael Istria
 *
 */
public class RemoveMethodCellEditor extends CellEditor {

	private JavaConnectorWizardPage javaConnectorWizardPage;
	private MethodEntry methodEntry;

	/**
	 * @param javaConnectorWizardPage
	 * @param element
	 */
	public RemoveMethodCellEditor(JavaConnectorWizardPage javaConnectorWizardPage, MethodEntry element) {
		this.javaConnectorWizardPage = javaConnectorWizardPage;
		this.methodEntry = element;
	}

	@Override
	protected Control createControl(Composite parent) {
		Button deleteButton = new Button(parent, SWT.FLAT);
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
		deleteButton.setImage(sharedImages.getImage(ISharedImages.IMG_TOOL_DELETE));
		deleteButton.addListener(SWT.Selection, new Listener() {
			
			public void handleEvent(Event event) {
				javaConnectorWizardPage.getMethodEntries().remove(methodEntry);
				javaConnectorWizardPage.refreshMethodCalls();
			}
		});
		return deleteButton;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellEditor#doGetValue()
	 */
	@Override
	protected Object doGetValue() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellEditor#doSetFocus()
	 */
	@Override
	protected void doSetFocus() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.CellEditor#doSetValue(java.lang.Object)
	 */
	@Override
	protected void doSetValue(Object value) {
		// TODO Auto-generated method stub

	}

}
