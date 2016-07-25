package org.bonitasoft.studio.groovy.ui.dialog;


import org.bonitasoft.studio.groovy.ui.Messages;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

public class GroovyScriptNameDialog extends InputDialog {

	public GroovyScriptNameDialog(Shell parentShell,String title,String message,String value,IInputValidator validator) {
		super(parentShell, title, message, value, validator);
		
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Control control =  super.createDialogArea(parent);
		 ((GridData)control.getLayoutData()).horizontalIndent = 6;
		  final ControlDecoration nameHelp = new ControlDecoration(getText(),SWT.LEFT);
	        nameHelp.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK));
	        nameHelp.setDescriptionText(Messages.nameHelp);
	       
	       
	        
	       
		return control;
	}
}
