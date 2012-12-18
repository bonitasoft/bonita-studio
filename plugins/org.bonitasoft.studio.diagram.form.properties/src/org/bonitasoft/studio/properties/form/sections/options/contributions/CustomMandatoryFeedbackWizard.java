/**
 * Copyright (C) 2010-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.properties.form.sections.options.contributions;

import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.MandatoryFieldsCustomization;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.wizard.Wizard;

/**
 * @author Mickael Istria
 *
 */
public class CustomMandatoryFeedbackWizard extends Wizard {

	private MandatoryFieldsCustomization mandatoryCusto;
	private TransactionalEditingDomain editingDomain;
	private MandatoryStyleWizardPage page;

	/**
	 * @param mandatoryCusto
	 * @param editingDomain 
	 */
	public CustomMandatoryFeedbackWizard(MandatoryFieldsCustomization mandatoryCusto, TransactionalEditingDomain editingDomain) {
		this.mandatoryCusto = mandatoryCusto;
		this.editingDomain = editingDomain;
	}

	@Override
	public void addPages() {
		page = new MandatoryStyleWizardPage(EcoreUtil.copy(mandatoryCusto));
		this.addPage(page);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		CompoundCommand cc = new CompoundCommand();
		cc.append(new SetCommand(editingDomain, mandatoryCusto, FormPackage.Literals.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_SYMBOL, page.getMandatorySymbol()));
		cc.append(new SetCommand(editingDomain, mandatoryCusto, FormPackage.Literals.MANDATORY_FIELDS_CUSTOMIZATION__MANDATORY_LABEL, page.getMandatoryLabel()));
		editingDomain.getCommandStack().execute(cc);
		return true;
	}

}
