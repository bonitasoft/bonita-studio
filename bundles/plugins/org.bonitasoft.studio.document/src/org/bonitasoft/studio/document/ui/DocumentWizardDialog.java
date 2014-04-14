package org.bonitasoft.studio.document.ui;

import org.bonitasoft.studio.document.i18n.Messages;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;

public class DocumentWizardDialog extends WizardDialog {

	public DocumentWizardDialog(Shell parentShell, IWizard newWizard) {
		super(parentShell, newWizard);
		this.setTitle(Messages.newDocument);
	}

}
