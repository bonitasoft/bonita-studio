/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
 * @author Florine Boudin
 *
 */
public class ValidationDialog extends MessageDialog{
	
	public static final int OK = IDialogConstants.OK_ID;
	public static final int NO = IDialogConstants.NO_ID;
	public static final int YES = IDialogConstants.YES_ID;
	public static final int SEE_DETAILS = IDialogConstants.DETAILS_ID;
	

	public static final int YES_NO_SEEDETAILS = 0;
	public static final int OK_SEEDETAILS = 1;
	public static final int OK_ONLY = 2;
	public static final int YES_NO = 3;
	
	private int messageType = 0;
	
	String seeDetails = Messages.seeDetailsButtonLabel;
	
	/**
	 * 
	 * @param parentShell
	 * @param dialogTitle
	 * @param dialogTitleImage
	 * @param dialogMessage
	 * @param dialogImageType
	 * @param dialogButtonLabels
	 * @param defaultIndex
	 */
	public ValidationDialog(Shell parentShell,String title,String message, int messageType) {
		super(parentShell,title,null,message,QUESTION,new String[]{},SWT.NONE);
		this.messageType = messageType;
		// TODO Auto-generated constructor stub
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
		buttonbar.setLayout(new GridLayout(3,false));
		buttonbar.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, false));
		if(messageType == YES_NO_SEEDETAILS || messageType == YES_NO){
			super.createButton(buttonbar, YES, IDialogConstants.YES_LABEL, false);	
			super.createButton(buttonbar, NO, IDialogConstants.NO_LABEL, true);
		}
		if(messageType == OK_SEEDETAILS || messageType == OK_ONLY){
			super.createButton(buttonbar, OK, IDialogConstants.OK_LABEL, true);
		}
		if(messageType == YES_NO_SEEDETAILS || messageType == OK_SEEDETAILS ){
			super.createButton(buttonbar, SEE_DETAILS, seeDetails, false);
		}
		return buttonBar;
	}
}
