package org.bonitasoft.studio.document.ui;

import org.bonitasoft.studio.expression.editor.provider.IProposalListener;
import org.bonitasoft.studio.model.process.Document;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

public class DocumentProposalListener implements IProposalListener {

	@Override
	public String handleEvent(EObject context, String fixedReturnType) {
		Assert.isNotNull(context);
		final DocumentWizard documentWizard = new DocumentWizard(context);
		final DocumentWizardDialog documentWizardDialog = new DocumentWizardDialog(Display.getCurrent().getActiveShell().getParent().getShell(),documentWizard);
		if (documentWizardDialog.open()==Dialog.OK){
			final Document document=documentWizard.getDocument();
			if (document!=null){
				return document.getName();
			}
		}
		return null;
	}

}
