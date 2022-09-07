/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.connector.wizard.sapjco3.pages.dialogs;

import org.bonitasoft.studio.connector.wizard.sapjco3.providers.TreeContentProvider;
import org.bonitasoft.studio.connector.wizard.sapjco3.tooling.SapTool;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Maxence Raoux
 * 
 */
public class SapNewOutputDialog extends AbstractInputOutputDialog {

	public SapNewOutputDialog(Shell parentShell, SapTool sapTool) {
		super(parentShell, sapTool);
	}

	@Override
	protected void makeRadioButton(Button button, String name,
			final Boolean single, final Boolean table, final Boolean structure) {
		button.setText(name);
		button.setSelection(false);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				treeElement = sapTool.getFunctionFieldsOutputFiltered(sapTool.selectedFunction, single, table,structure);
				checkboxTreeViewer.setContentProvider(new TreeContentProvider(	treeElement));
			}
		});
	}

	@Override
	protected void initializeTreeElement() {
		treeElement = sapTool.getFunctionFieldsOutputFiltered(sapTool.selectedFunction,	true, false, false);
		if (checkboxTreeViewer != null) {
			checkboxTreeViewer.setContentProvider(new TreeContentProvider(treeElement));
		}
	}
}
