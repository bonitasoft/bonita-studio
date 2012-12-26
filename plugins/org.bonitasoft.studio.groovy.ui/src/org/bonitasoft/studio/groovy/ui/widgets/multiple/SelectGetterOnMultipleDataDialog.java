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
package org.bonitasoft.studio.groovy.ui.widgets.multiple;

import java.util.List;

import org.bonitasoft.studio.groovy.GroovyUtil;
import org.bonitasoft.studio.groovy.ui.Messages;
import org.bonitasoft.studio.groovy.ui.widgets.pojo.BrowseJavaDialog;
import org.bonitasoft.studio.groovy.ui.widgets.GroovyComboProvider;
import org.bonitasoft.studio.groovy.ui.widgets.TextOrData;
import org.bonitasoft.studio.model.process.Data;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;


/**
 * @author Mickael Istria
 *
 */
public class SelectGetterOnMultipleDataDialog extends Dialog {

	private TextOrData textOrData;
	private Data data;
	private String script;

	/**
	 * @param textOrData
	 * @param data
	 */
	public SelectGetterOnMultipleDataDialog(TextOrData textOrData, Data data) {
		super(textOrData.getControl().getShell());
		this.textOrData = textOrData;
		this.data = data;
		this.script = data.getName();
	}
	
	@Override
	public Composite createDialogArea(Composite parent) {
		Composite res = new Composite(parent, SWT.NONE);
		res.setLayout(new GridLayout(2, false));
		Label selectReadModeLabel = new Label(res, SWT.NONE);
		selectReadModeLabel.setText(Messages.readMultipleData_label);
		selectReadModeLabel.setLayoutData(new GridData(SWT.FILL, SWT.DEFAULT, false, false, 2, 1));
		Button wholeListButton = new Button(res, SWT.RADIO);
		wholeListButton.setSelection(true);
		wholeListButton.setText(Messages.readMultipleData_wholeList);
		wholeListButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				script = data.getName();
			}
		});
		new Composite(res, SWT.NONE);
		final Button indexButton = new Button(res, SWT.RADIO);
		indexButton.setText(Messages.readMultipleData_index);
		final TextOrData indexText = new TextOrData(res, textOrData.getElement());
		indexText.setExepectedIsGroovy(false);
		indexText.setText("0");
		indexText.addValueChangedListener(new Listener() {
			public void handleEvent(Event event) {
				if (indexButton.getSelection()) {
					script = data.getName() + ".get(" + indexText.getText() + ")";
				}
			}
		});
		indexButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				script = data.getName() + ".get(" + indexText.getText() + ")";
			}
		});
		
		final Button sizeButton = new Button(res, SWT.RADIO);
		sizeButton.setText(Messages.readMultipleData_size);
		sizeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				script = data.getName() + ".size()";
			};
		});
		new Composite(res, SWT.NONE);
		
		Link browseLink = new Link(res, SWT.NONE);
		browseLink.setText("<A>" + Messages.browseJava + "</A>");
		browseLink.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				okPressed();
				if (textOrData != null) {
					final BrowseJavaDialog browseJavaDialog = new BrowseJavaDialog(Display.getDefault().getActiveShell(), script, data.getName(), List.class.getName(), textOrData);
					if (browseJavaDialog.open() == OK) {
						script = browseJavaDialog.getScript();
					}
				}
			}
		});
		browseLink.setLayoutData(new GridData(SWT.DEFAULT, SWT.DEFAULT, false, false, 2, 1));
		Link groovyLink = new Link(res, SWT.NONE);
		groovyLink.setText("<A>" + Messages.openExpressionEditor + "</A>");
		groovyLink.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				okPressed();
				if (textOrData != null) {
					script = new GroovyComboProvider().openGroovyEditor(textOrData, data.getName());
					script = script.substring(GroovyUtil.GROOVY_PREFIX.length(), script.length() - 1);
				}
			}
		});
		groovyLink.setLayoutData(new GridData(SWT.DEFAULT, SWT.DEFAULT, false, false, 2, 1));
		
		return res;
	}

	/**
	 * @return
	 */
	public String getScript() {
		return GroovyUtil.GROOVY_PREFIX + script + GroovyUtil.GROOVY_SUFFIX;
	}

}
