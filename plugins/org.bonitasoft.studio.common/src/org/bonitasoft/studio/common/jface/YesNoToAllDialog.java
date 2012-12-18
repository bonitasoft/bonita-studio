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
package org.bonitasoft.studio.common.jface;

import org.bonitasoft.studio.common.Messages;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Baptiste Mesta
 * 
 */
public class YesNoToAllDialog extends MessageDialog {


	public static final int NO_TO_ALL = IDialogConstants.NO_TO_ALL_ID;
	public static final int NO = IDialogConstants.NO_ID;
	public static final int YES = IDialogConstants.YES_ID;
	public static final int YES_TO_ALL = IDialogConstants.YES_TO_ALL_ID;

	/**
	 * @param parentShell
	 */
	public YesNoToAllDialog(Shell parentShell,String title,String message) {
		super(parentShell,title,null,message,QUESTION,new String[]{},SWT.NONE);

	}
	


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.Dialog#createButtonBar(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	protected Control createButtonBar(Composite parent) {
		Composite blank = new Composite(parent, SWT.NONE);
		blank.setLayoutData(new GridData(1,1));
		Composite buttonbar = new Composite(parent, SWT.None);
		buttonbar.setLayout(new GridLayout(4,false));
		buttonbar.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, false));
		super.createButton(buttonbar, NO_TO_ALL, Messages.noToAll, false);
		super.createButton(buttonbar, NO, IDialogConstants.NO_LABEL, true);
		super.createButton(buttonbar, YES, IDialogConstants.YES_LABEL, false);
		super.createButton(buttonbar, YES_TO_ALL, Messages.yesToAll, false);
		return buttonBar;
	}


	
	

}
