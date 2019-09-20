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
 
package org.bonitasoft.studio.diagram.custom.commands;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.emf.commands.core.commands.RepositionEObjectCommand;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Romain Bioteau
 *
 */
public class CompartmentRepositionEObjectCommand extends RepositionEObjectCommand {

	EditPart childToMove = null;
	int newIndex = 0;
	
	public CompartmentRepositionEObjectCommand(
			TransactionalEditingDomain editingDomain, String label,
			EList<?> elements, EObject element, int displacement) {
		super(editingDomain, label, elements, element, displacement);
	}
	
	public CompartmentRepositionEObjectCommand(EditPart childToMove,
			TransactionalEditingDomain editingDomain, String label,
			EList<?> elements, EObject element, int displacement, int newIndex) {
		super(editingDomain, label, elements, element, displacement);
		
		this.childToMove = childToMove;
		this.newIndex = newIndex;
	}
	
	public CommandResult doExecuteWithResult(
			IProgressMonitor progressMonitor, IAdaptable info)
			throws ExecutionException {
		CommandResult rs = super.doExecuteWithResult(progressMonitor, info);

		EditPart compartment = childToMove.getParent(); 
		
		ViewUtil.repositionChildAt((View)compartment.getModel(), (View)childToMove.getModel(), newIndex);
		compartment.refresh();		
		
		return rs;
	}
}
