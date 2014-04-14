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
package org.bonitasoft.studio.diagram.form.custom.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.ui.commands.CreateCommand;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Aurelien Pupier
 *
 */
public abstract class AbstractGridLayoutCreateCommand extends CreateCommand {
	
	protected int line;
	protected int column;
	protected View createdView;
	
	public AbstractGridLayoutCreateCommand(
			TransactionalEditingDomain editingDomain,
			ViewDescriptor viewDescriptor, View containerView, int line,
			int column) {
		super(editingDomain, viewDescriptor, containerView);
		this.line = line;
		this.column = column;
	}
	
	
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		createdView = ViewService.getInstance().createView(
				viewDescriptor.getViewKind(),
				viewDescriptor.getElementAdapter(),
				containerView,
				viewDescriptor.getSemanticHint(),
				viewDescriptor.getIndex(),
				viewDescriptor.isPersisted(),
				viewDescriptor.getPreferencesHint());
		Assert.isNotNull(createdView, "failed to create a view"); //$NON-NLS-1$
		viewDescriptor.setView(createdView);
		
		createAtTheRigthPlace(monitor, info);
		
		
		return CommandResult.newOKCommandResult(viewDescriptor);
	}


	/**
	 * Do it what you need to create the element at the right position
	 * 
	 * @param monitor
	 * @param info
	 * @throws ExecutionException
	 */
	protected abstract void createAtTheRigthPlace(IProgressMonitor monitor, IAdaptable info) throws ExecutionException;
	

	/**
	 * @return the createdView
	 */
	public View getCreatedView() {
		return createdView;
	}
	
	@Override
	public boolean canUndo() {
		
		return true;
	}
	
	@Override
	public boolean canRedo() {
		
		return true;
	}
}
