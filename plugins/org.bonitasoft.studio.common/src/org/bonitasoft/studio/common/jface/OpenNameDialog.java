/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.jface;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.NamingUtils;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * 
 * @author Baptiste Mesta
 *
 */
public class OpenNameDialog extends Dialog {

	private String srcName;
	private String newName;

	/**
	 * @param name
	 * @param version
	 */
	public OpenNameDialog(Shell parentShell, String name) {
		super(parentShell);
		this.srcName = name;
		this.newName = name;
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Messages.openNameAndVersionDialogTitle);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite res = new Composite(parent, SWT.FILL);
		res.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		//GridData labelLayoutData = new GridData(SWT.DEFAULT, SWT.CENTER, false, false);
		GridData textLayoutData = new GridData(SWT.FILL, SWT.NONE, true, false);
		
		res.setLayout(new GridLayout(2, false));
		Label nameLabel = new Label(res, SWT.NONE);
		nameLabel.setText(Messages.name);
		//nameLabel.setLayoutData(labelLayoutData);
		final Text nameText = new Text(res, SWT.BORDER);
		nameText.setText(srcName);
		nameText.setLayoutData(textLayoutData);

		nameText.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				newName = nameText.getText();
				updateOkButton();
			}
		});
		return res;
	}

	protected void updateOkButton() {
		Button button = getButton(OK);
		button.setEnabled(
				newName != null && newName.length() > 0 &&
			! (NamingUtils.convertToId(newName).equalsIgnoreCase(NamingUtils.convertToId(srcName))));

		
	}
	
	@Override
	public void create() {
		super.create();
		updateOkButton();
	}

	public String getNewName() {
		return newName;
	}

	/**
	 * @return the srcName
	 */
	public String getSrcName() {
		return srcName;
	}
	
}
