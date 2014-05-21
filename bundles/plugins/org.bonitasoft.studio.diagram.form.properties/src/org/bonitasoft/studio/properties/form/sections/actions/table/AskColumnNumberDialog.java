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
package org.bonitasoft.studio.properties.form.sections.actions.table;

import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.PlatformUI;

/**
 * @author Mickael Istria
 *
 */
public class AskColumnNumberDialog extends Dialog {

	private int columnMax;
	private int selectedColumn;
	private Spinner spinner;

	/**
	 * @param nbColumns
	 */
	public AskColumnNumberDialog(int nbColumns) {
		super(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
		columnMax = nbColumns;
	}

	/**
	 * @return
	 */
	public int getSelectedColumn() {
		return this.selectedColumn;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		super.createDialogArea(parent);
		getShell().setText(Messages.removeColumnButton);
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		Label label = new Label(composite, SWT.NONE);
		label.setText(Messages.bind(Messages.selectColumnNumber,columnMax));
		spinner = new Spinner(composite, SWT.BORDER);
		spinner.setLayoutData(GridDataFactory.swtDefaults().align(SWT.CENTER, SWT.CENTER).hint(40,SWT.DEFAULT).create());
		spinner.setSelection(1);
		selectedColumn = 1;
		spinner.setMinimum(1);
		spinner.setMaximum(columnMax);
		
		return composite;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	@Override
	protected void okPressed() {
		selectedColumn = spinner.getSelection();
		super.okPressed();
	}

}
