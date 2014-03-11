
package org.bonitasoft.studio.data.ui.wizard;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.expression.editor.provider.IProposalListener;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/

/**
 * @author Maxence Raoux
 * 
 */
public class ProposalListener implements IProposalListener {

	private boolean isPageFlowContext=false;
	private EStructuralFeature feature;

	public ProposalListener() {
	}

	@Override
	public String handleEvent(EObject context, String fixedReturnType) {
		Assert.isNotNull(context);
		while (!(context instanceof AbstractProcess || context instanceof Activity))  {
			context = context.eContainer();
		}
		if (feature == null){
			feature = ProcessPackage.Literals.DATA_AWARE__DATA;
		} 
		Set<EStructuralFeature> res = new HashSet<EStructuralFeature>();
		res.add(feature);
		final DataWizard newWizard = new DataWizard(context, feature, res, true, fixedReturnType);
		newWizard.setIsPageFlowContext(isPageFlowContext);
		final DataWizardDialog wizardDialog = new DataWizardDialog(Display
				.getCurrent().getActiveShell().getParent().getShell(),
				newWizard, null);
		if(wizardDialog.open() == Dialog.OK){
			EObject obj = newWizard.getWorkingCopy();
			if(obj instanceof Data){
				final Data d = (Data)obj;
				if(d!=null){
					return d.getName();
				}
			}
		}
	
		return null;

	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.common.IBonitaVariableContext#isPageFlowContext()
	 */
	@Override
	public boolean isPageFlowContext() {
		return true;
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
		this.feature = feature;
		
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

