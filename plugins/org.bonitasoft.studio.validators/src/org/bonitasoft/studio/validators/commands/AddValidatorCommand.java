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
package org.bonitasoft.studio.validators.commands;

import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.Validable;
import org.bonitasoft.studio.model.form.Validator;
import org.bonitasoft.studio.validators.ui.property.section.ValidatorsPropertySection;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

public class AddValidatorCommand extends AbstractTransactionalCommand {
	
	private String name;
	private Validable validable;
	private ValidatorsPropertySection callingSection;
	private String validatorClass;
	protected Validator createdValidator;

	public AddValidatorCommand(TransactionalEditingDomain domain, Validable validable,String name,ValidatorsPropertySection callingSection) {
		super(domain, "Add validator", getWorkspaceFiles(validable));
		this.name = name;
		this.validable = validable;
		this.callingSection = callingSection;
	}

	public AddValidatorCommand(TransactionalEditingDomain domain, Validable validable, String name, String validatorClass, ValidatorsPropertySection callingSection) {
		super(domain, "Add validator", getWorkspaceFiles(validable));
		this.name = name;
		this.validable = validable;
		this.callingSection = callingSection;
		this.validatorClass = validatorClass;
	}

	
	public void dispose() {
		super.dispose();
		validable = null;
		callingSection = null;
	}
	
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		createdValidator = FormFactory.eINSTANCE.createValidator();
		createdValidator.setName(name);
		if(validatorClass != null){
			createdValidator.setValidatorClass(validatorClass);
			callingSection.refresh() ;
		}

		validable.getValidators().add(createdValidator);
		callingSection.select(createdValidator);
		return CommandResult.newOKCommandResult(createdValidator);
	}

	@Override
	protected void didUndo(Transaction tx) {
		super.didUndo(tx);
		if(callingSection != null){
			callingSection.refresh();
		}
	}
	
	@Override
	protected void didRedo(Transaction tx) {
		super.didRedo(tx);
		if(callingSection != null){
			callingSection.select(createdValidator);
		}
	}
	
}
