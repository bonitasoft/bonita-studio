package org.bonitasoft.studio.document.ui;

import org.bonitasoft.studio.expression.editor.provider.IProposalListener;
import org.bonitasoft.studio.model.process.Document;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

public class DocumentProposalListener implements IProposalListener {

	private boolean isPageFlowContext=false;

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

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#isPageFlowContext()
	 */
	@Override
	public boolean isPageFlowContext() {
		return isPageFlowContext;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsPageFlowContext(boolean)
	 */
	@Override
	public void setIsPageFlowContext(boolean isPageFlowContext) {
		this.isPageFlowContext=isPageFlowContext;
		
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.expression.editor.provider.IProposalListener#setEStructuralFeature(org.eclipse.emf.ecore.EStructuralFeature)
	 */
	@Override
	public void setEStructuralFeature(EStructuralFeature feature) {
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#isOverViewContext()
	 */
	@Override
	public boolean isOverViewContext() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#setIsOverviewContext(boolean)
	 */
	@Override
	public void setIsOverviewContext(boolean isOverviewContext) {
	}

}
