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

package org.bonitasoft.studio.common.repository.operation;

import java.util.ArrayList;

import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Maxence Raoux
 * 
 */
public class WarningDuplicateProcessDialog extends Dialog {

	private ArrayList<AbstractProcess> duplicateProcess;

	public WarningDuplicateProcessDialog(Shell parentShell, ArrayList<AbstractProcess> duplicateProcess) {
		super(parentShell);
		this.duplicateProcess = duplicateProcess;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Messages.warningDuplicateDialogTitle);
	}
	
	protected Control createDialogArea(Composite parent) {
		parent.setLayout(GridLayoutFactory.fillDefaults().numColumns(1).margins(10, 10).create());
		
		Label errorMessage = new Label(parent, SWT.NONE);
		errorMessage.setText(Messages.poolAlreadyExistWarningMessage);
		
		Composite pools = new Composite(parent, SWT.NONE);
		pools.setLayout(GridLayoutFactory.fillDefaults().numColumns(2).margins(10, 10).create());
		
		for (AbstractProcess p : duplicateProcess) {
			Label processName = new Label(pools, SWT.NONE);
			processName.setText(p.getName());
			Label processVersion = new Label(pools, SWT.NONE);
			processVersion.setText("("+p.getVersion()+")");
		}
		return parent;
	}
}
