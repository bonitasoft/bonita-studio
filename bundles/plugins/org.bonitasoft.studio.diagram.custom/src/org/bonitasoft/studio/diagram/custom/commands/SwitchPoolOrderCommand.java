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

import java.util.List;

import org.bonitasoft.studio.diagram.custom.editPolicies.SwitchPoolSelectionEditPolicy;
import org.bonitasoft.studio.diagram.custom.parts.CustomLaneEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomMainProcessEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomPoolCompartmentEditPart;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.commands.core.commands.RepositionEObjectCommand;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Romain Bioteau
 *
 */
public class SwitchPoolOrderCommand extends AbstractTransactionalCommand {

	private String typeChangeSpan;
	private IGraphicalEditPart targetEp;

	public SwitchPoolOrderCommand(IGraphicalEditPart target, String typeChangeSpan) {
		super(target.getEditingDomain(), typeChangeSpan, getWorkspaceFiles(target.resolveSemanticElement()));
		this.typeChangeSpan = typeChangeSpan ;
		this.targetEp = target ;
	}
	
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor,
			IAdaptable info) throws ExecutionException {
		Command cc = null ;
		
		List<?> children = ((CustomMainProcessEditPart)targetEp.getParent()).getChildren() ;
		ShapeNodeEditPart nextEp = null ;
	
		if( typeChangeSpan.equals(SwitchPoolSelectionEditPolicy.ADD_BOTTOM)){
			for(int i = 0 ; i< children.size(); i++){
				if(children.get(i).equals(targetEp)){
					nextEp = (ShapeNodeEditPart) children.get(i+1);
					break;
				}
			}
		
			
			cc = createMoveChildCommand(targetEp,nextEp);
		}else if(typeChangeSpan.equals(SwitchPoolSelectionEditPolicy.ADD_TOP)){
			for(int i = 0 ; i< children.size(); i++){
				if(children.get(i).equals(targetEp)){
					nextEp = (ShapeNodeEditPart) children.get(i-1);
					break;
				}
			}
			cc = createMoveChildCommand(targetEp,nextEp);
		}
		
		targetEp.getDiagramEditDomain().getDiagramCommandStack().execute(cc);

	
		for(Object child : targetEp.getChildren()){
			if(child instanceof CustomPoolCompartmentEditPart){
				for(Object child1 : ((CustomPoolCompartmentEditPart) child).getChildren()){
					if(child1 instanceof CustomLaneEditPart){
						((CustomLaneEditPart) child1).refresh();
					}
				}
			}
			
		}
		
		return CommandResult.newOKCommandResult();
	}

	protected Command createMoveChildCommand(EditPart child, EditPart after) {	

		int newIndex;
		int displacement;

		List children = getHost().getChildren();
		int childIndex = children.indexOf(child);
		int afterIndex = children.indexOf(after);	

		if(afterIndex == -1) {
			newIndex = children.size()-1;			
			displacement = newIndex - childIndex;
		} else {		
			newIndex = afterIndex;
			displacement = afterIndex - childIndex;
//			if (childIndex <= afterIndex) {
//				newIndex--;
//				displacement--;			
//			}
		}

		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();

		RepositionEObjectCommand command = new CompartmentRepositionEObjectCommand(child, editingDomain, "", 
				(EList<?>)((View)child.getParent().getModel()).getElement().eGet(ProcessPackage.Literals.CONTAINER__ELEMENTS), 
				((View)child.getModel()).getElement(), 
				displacement, newIndex);	

		return new ICommandProxy(command);
	}
	private AbstractEditPart getHost() {
		return (AbstractEditPart) targetEp.getParent();
	}

	/**
	 * @return
	 */
	private String getOppositeDirection() {
		if(typeChangeSpan.equals(SwitchPoolSelectionEditPolicy.ADD_LEFT)){		
			return SwitchPoolSelectionEditPolicy.REMOVE_LEFT;
		} else if( typeChangeSpan.equals(SwitchPoolSelectionEditPolicy.ADD_RIGHT)){
			return SwitchPoolSelectionEditPolicy.REMOVE_RIGHT;
		} else if(typeChangeSpan.equals(SwitchPoolSelectionEditPolicy.ADD_BOTTOM)){
			return SwitchPoolSelectionEditPolicy.REMOVE_BOTTOM;
		}else if(typeChangeSpan.equals(SwitchPoolSelectionEditPolicy.ADD_TOP)){
			return SwitchPoolSelectionEditPolicy.REMOVE_TOP;
		}else if(typeChangeSpan.equals(SwitchPoolSelectionEditPolicy.REMOVE_LEFT)){
			return SwitchPoolSelectionEditPolicy.ADD_LEFT;
		}else if(typeChangeSpan.equals(SwitchPoolSelectionEditPolicy.REMOVE_RIGHT)){
			return SwitchPoolSelectionEditPolicy.ADD_RIGHT;
		}else if(typeChangeSpan.equals(SwitchPoolSelectionEditPolicy.REMOVE_BOTTOM)){
			return SwitchPoolSelectionEditPolicy.ADD_BOTTOM;
		}else if(typeChangeSpan.equals(SwitchPoolSelectionEditPolicy.REMOVE_TOP)){
			return SwitchPoolSelectionEditPolicy.ADD_TOP;
		}
		return ""; //$NON-NLS-1$
	}
	

}
