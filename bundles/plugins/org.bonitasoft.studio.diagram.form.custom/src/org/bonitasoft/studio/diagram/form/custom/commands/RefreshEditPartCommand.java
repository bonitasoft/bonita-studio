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
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * @author Aurelien Pupier
 * Command to refresh edit part corresponding to the model element.
 * Typically, use for reparent command because the new editpart is not known at the creation of the command.
 */
public class RefreshEditPartCommand extends AbstractTransactionalCommand {
	protected EObject eObject = null;
	protected IGraphicalEditPart topEditPart =null;

	public RefreshEditPartCommand(IGraphicalEditPart part) {
		super(part.getEditingDomain(), "Refresh EditPart Command", null);
		
		eObject = part.resolveSemanticElement();
		topEditPart = (IGraphicalEditPart) part.getRoot().getContents();
	}

	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		EditPart part = topEditPart.findEditPart(topEditPart, eObject);
		if(part != null){
			refreshChildren(topEditPart);
			topEditPart.refresh();
			part.refresh();
		}
		return CommandResult.newOKCommandResult();
	}

	private void refreshChildren(IGraphicalEditPart topEditPart2) {
		topEditPart2.refresh();
		for(Object part : topEditPart2.getChildren()){
			if(part instanceof IGraphicalEditPart)
				refreshChildren((IGraphicalEditPart) part);
		}
		
	}

}
