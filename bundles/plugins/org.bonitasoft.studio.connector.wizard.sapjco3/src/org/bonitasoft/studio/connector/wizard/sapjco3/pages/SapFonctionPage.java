/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.connector.wizard.sapjco3.pages;

import org.bonitasoft.studio.connector.model.definition.wizard.PageComponentSwitch;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Maxence Raoux
 * 
 */
public class SapFonctionPage extends AbstractPage {

	public SapFonctionPage() {
		super();
	}

	@Override
	protected PageComponentSwitch getPageComponentSwitch(
			EMFDataBindingContext context, Composite pageComposite) {

		if (this.libraryLoaded && sapTool != null && sapTool.userWantToUseIt && sapTool.connectionOK) {
			return new SapFonctionPageComponentSwitch(getContainer(),
					pageComposite, getElementContainer(), 
					getDefinition(),
					getConfiguration(), context,
					getExpressionTypeFilter(),
					sapTool);
		} else {
			return new PageComponentSwitch(getContainer(), pageComposite,
					getElementContainer(), getDefinition(), getConfiguration(),
					context, getExpressionTypeFilter());
		}
	}

	@Override
	public boolean canFlipToNextPage() {
		if (this.libraryLoaded && sapTool != null && sapTool.userWantToUseIt && sapTool.connectionOK) {
			final Expression exp = (Expression) this.getConnectorParameter(getInput("functionName")).getExpression();
			if (sapTool.isFunction(exp.getContent())) {
				return super.canFlipToNextPage();
			} else {
				return false;
			}
		} else {
			return super.canFlipToNextPage();
		}
	}

	@Override
	public boolean isPageComplete() {
		return super.isPageComplete();
	}

	@Override
	public void setErrorMessage(String newMessage) {
		super.setErrorMessage(newMessage);
	}

	@Override
	public IWizardPage getNextPage() {
		final IWizardPage nextPage = super.getNextPage();
		if (nextPage instanceof SapInputPage && sapTool!=null) {
			final Expression exp = (Expression) this.getConnectorParameter(getInput("functionName")).getExpression();
			sapTool.selectedFunction = exp.getContent();
			((SapInputPage) nextPage).sapTool = sapTool;
			((SapInputPage) nextPage).updateRef();
		}
		return nextPage;
	}
}
