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
package org.bonitasoft.studio.diagram.custom.commands;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.diagram.custom.editPolicies.SwitchPoolSelectionEditPolicy;
import org.bonitasoft.studio.diagram.custom.parts.CustomLaneEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomPoolCompartmentEditPart;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;

/**
 * @author Romain Bioteau
 *
 */
public class UpdatePoolSizeCommand extends AbstractTransactionalCommand {

	private String typeChangeSpan;
	protected IGraphicalEditPart gep;
	protected CompositeCommand cc;

	/**
	 * @param target
	 * @param typeChangeSpan
	 */
	public UpdatePoolSizeCommand(IGraphicalEditPart target, String typeChangeSpan) {
		super(target.getEditingDomain(), typeChangeSpan, getWorkspaceFiles(target.resolveSemanticElement()));
		this.typeChangeSpan = typeChangeSpan;
		this.gep = target ;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		executeChangingSpanFor(typeChangeSpan, monitor, info);
		return CommandResult.newOKCommandResult();
	}
	
	/**
	 * @param info 
	 * @param monitor 
	 * @param typeChangeSpan2
	 * @throws ExecutionException 
	 */
	private void executeChangingSpanFor(String typeChangeSpan1, IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		if( typeChangeSpan1.equals(SwitchPoolSelectionEditPolicy.ADD_RIGHT)){
			increaseWidth();
		}else if(typeChangeSpan1.equals(SwitchPoolSelectionEditPolicy.REMOVE_RIGHT)){
			decreaseWidth();
		}else if(typeChangeSpan1.equals(SwitchPoolSelectionEditPolicy.ADD_BOTTOM)){
			increaseHeight();
		}else if(typeChangeSpan1.equals(SwitchPoolSelectionEditPolicy.REMOVE_BOTTOM)){
			decreaseHeight();
		}

	}

	private void decreaseHeight() {
		ChangeBoundsRequest setRequest1 = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE) ; 
		List<EditPart> epToMove = new ArrayList<EditPart>();
		epToMove.add(gep);
		setRequest1.setEditParts(epToMove);
		setRequest1.setResizeDirection(PositionConstants.NORTH);
		setRequest1.setSizeDelta(new Dimension(0,-150));
		gep.getDiagramEditDomain().getDiagramCommandStack().execute(gep.getCommand(setRequest1));
		for(Object o : gep.getChildren()){
			if(o instanceof CustomPoolCompartmentEditPart){
				for(CustomLaneEditPart lane : ((CustomPoolCompartmentEditPart)o).getPoolLanes()){
					lane.refresh();
				}
			}
			
		}
		
	}

	private void increaseHeight() {
		ChangeBoundsRequest setRequest1 = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE) ; 
		List<EditPart> epToMove = new ArrayList<EditPart>();
		epToMove.add(gep);
		setRequest1.setEditParts(epToMove);
		setRequest1.setResizeDirection(PositionConstants.SOUTH);
		setRequest1.setSizeDelta(new Dimension(0,150));
		gep.getDiagramEditDomain().getDiagramCommandStack().execute(gep.getCommand(setRequest1));
		for(Object o : gep.getChildren()){
			if(o instanceof CustomPoolCompartmentEditPart){
				for(CustomLaneEditPart lane : ((CustomPoolCompartmentEditPart)o).getPoolLanes()){
					lane.refresh();
				}
			}
			
		}
	}

	private void decreaseWidth() {
		ChangeBoundsRequest setRequest1 = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE) ; 
		List<EditPart> epToMove = new ArrayList<EditPart>();
		epToMove.add(gep);
		for(Object o : gep.getChildren()){
			if(o instanceof CustomPoolCompartmentEditPart){
				for(CustomLaneEditPart lane : ((CustomPoolCompartmentEditPart)o).getPoolLanes()){
					epToMove.add(lane);
				}
			}
		}
		setRequest1.setEditParts(epToMove);
		setRequest1.setResizeDirection(PositionConstants.EAST);
		setRequest1.setSizeDelta(new Dimension(-150,0));
		if(gep.getFigure().getSize().width - 150 > gep.getFigure().getMinimumSize().width ){
			gep.getDiagramEditDomain().getDiagramCommandStack().execute(gep.getCommand(setRequest1));	
			for(Object o : gep.getChildren()){
				if(o instanceof CustomPoolCompartmentEditPart){
					for(CustomLaneEditPart lane : ((CustomPoolCompartmentEditPart)o).getPoolLanes()){
						lane.refresh();
					}
				}
				
			}
		}
	}

	private void increaseWidth() {
		ChangeBoundsRequest setRequest1 = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE) ; 
		List<EditPart> epToMove = new ArrayList<EditPart>();
		epToMove.add(gep);
		for(Object o : gep.getChildren()){
			if(o instanceof CustomPoolCompartmentEditPart){
				for(CustomLaneEditPart lane : ((CustomPoolCompartmentEditPart)o).getPoolLanes()){
					epToMove.add(lane);
				}
			}
			
		}
		setRequest1.setEditParts(epToMove);
		setRequest1.setResizeDirection(PositionConstants.EAST);
		setRequest1.setSizeDelta(new Dimension(150,0));
		gep.getDiagramEditDomain().getDiagramCommandStack().execute(gep.getCommand(setRequest1));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doUndo(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	@Override
	protected IStatus doUndo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {	
		executeChangingSpanFor(getOppositeDirection(),monitor,info);
		return Status.OK_STATUS;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doRedo(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	@Override
	protected IStatus doRedo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		executeChangingSpanFor(typeChangeSpan,monitor,info);
		return Status.OK_STATUS;
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
