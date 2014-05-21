/**
 * Copyright (C) 2010 BonitaSoft S.A.
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

import org.bonitasoft.studio.model.form.Validable;
import org.bonitasoft.studio.validators.ui.property.section.ValidatorsPropertySection;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * @author Aurelien Pupier
 *
 */
public class RemoveValidatorCommand extends AbstractTransactionalCommand {

	private Validable validable;
	private Object[] selection;
	private ValidatorsPropertySection callingSection;

	public RemoveValidatorCommand(TransactionalEditingDomain domain, Validable validable, Object[] selection, ValidatorsPropertySection callingSection) {
		super(domain, "Remove validator", getWorkspaceFiles(validable));
		this.validable = validable;
		this.selection = selection;
		this.callingSection = callingSection;
	}

	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		for (Object validator : selection) {
			validable.getValidators().remove(validator);
		}
		callingSection.refresh();
		return CommandResult.newOKCommandResult(validable);
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
			callingSection.refresh();
		}
	}
}
