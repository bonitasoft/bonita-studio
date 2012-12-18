/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.groovy.ui.widgets.multiple;

import org.bonitasoft.studio.common.BonitaConstants;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.bonitasoft.studio.groovy.ui.widgets.GroovyComboProvider;
import org.bonitasoft.studio.groovy.ui.widgets.TextOrData;
import org.bonitasoft.studio.model.process.Data;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;

/**
 * @author Mickael Istria
 *
 */
public class SelectWriteToMultipleDataDialog extends Dialog {


	private Data data;
	private TextOrData textOrData;
	private String script;
	
	
	/**
	 * @param textOrData.getControl().getShell()
	 * @param data 
	 */
	protected SelectWriteToMultipleDataDialog(TextOrData textOrData, Data data) {
		super(textOrData.getControl().getShell());
		this.textOrData = textOrData;
		this.data = data;
		this.script = data.getName();
	}
	
	@Override
	public Composite createDialogArea(Composite parent) {
		Composite res = new Composite(parent, SWT.NONE);
		res.setLayout(new RowLayout(SWT.VERTICAL));
		Label selectWriteModeLabel = new Label(res, SWT.NONE);
		selectWriteModeLabel.setText(Messages.selectWriteToMultipleData_label);
		Button appendButton = new Button(res, SWT.RADIO);
		appendButton.setText(Messages.writeToMultipleData_append);
		appendButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				script = data.getName() + BonitaConstants.JAVA_VAR_SEPARATOR + data.getName() + BonitaConstants.JAVA_VAR_SEPARATOR + "add";
			}
		});
		
		Button replaceButton = new Button(res, SWT.RADIO);
		replaceButton.setSelection(true);
		replaceButton.setText(Messages.writeToMultipleData_replace);
		replaceButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				script = data.getName();
			}
		});
		
		Link link = new Link(res, SWT.NONE);
		link.setText("<A>" + Messages.openExpressionEditor + "</A>");
		link.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				okPressed();
				if (textOrData != null) {
					script = new GroovyComboProvider().openGroovyEditor(textOrData, data.getName());
				}
			}
		});
		
		return res;
	}

	/**
	 * @return
	 */
	public String getScript() {
		return script;
	}

}
