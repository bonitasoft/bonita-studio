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

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.diagram.custom.editPolicies.SwitchPoolSelectionEditPolicy;
import org.bonitasoft.studio.diagram.custom.parts.CustomLaneEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomPoolCompartmentEditPart;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Lane;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.commands.core.commands.RepositionEObjectCommand;

/**
 * @author Romain Bioteau
 *
 */
public class SwitchLaneCommand<T extends IGraphicalEditPart> extends AbstractTransactionalCommand {

	private String typeChangeSpan;
	protected Lane lane;
	protected AbstractProcess pool;
	protected T laneEp;
	protected CompositeCommand cc;

	/**
	 * @param target
	 * @param typeChangeSpan
	 */
	public SwitchLaneCommand(IGraphicalEditPart target, String typeChangeSpan) {
		super(target.getEditingDomain(), typeChangeSpan, getWorkspaceFiles(target.resolveSemanticElement()));
		this.typeChangeSpan = typeChangeSpan;
		this.laneEp = (T)target ;
		this.lane = (Lane) laneEp.resolveSemanticElement() ;
		this.pool = ModelHelper.getParentProcess(lane);
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
		CompositeCommand cmd = new CompositeCommand("Update position"); //$NON-NLS-1$
		if( typeChangeSpan1.equals(SwitchPoolSelectionEditPolicy.ADD_TOP)){
			List<IGraphicalEditPart> lanes = (List<IGraphicalEditPart>)getPoolLanes() ;

			IGraphicalEditPart l = lanes.get(lanes.indexOf(laneEp)-1);
			lanes.set(lanes.indexOf(laneEp)-1, laneEp);
			lanes.set(lanes.indexOf(laneEp)+1, l);
	
			for(IGraphicalEditPart lan : lanes){
				lan.refresh();
			}
			cmd.add(new RepositionEObjectCommand(laneEp.getEditingDomain(), "Reposition Lane", pool.getElements(),lane, -1)); //$NON-NLS-1$
			
		}else if(typeChangeSpan1.equals(SwitchPoolSelectionEditPolicy.REMOVE_TOP)){
			List<IGraphicalEditPart> lanes = (List<IGraphicalEditPart>) getPoolLanes() ;

			IGraphicalEditPart l = lanes.get(lanes.indexOf(laneEp)+1);
			lanes.set(lanes.indexOf(laneEp)+1, laneEp);
			lanes.set(lanes.indexOf(laneEp), l);
	
			for(IGraphicalEditPart lan : lanes){
				lan.refresh();
			}
			cmd.add(new RepositionEObjectCommand(laneEp.getEditingDomain(), "Reposition Lane", pool.getElements(),lane, -1)); //$NON-NLS-1$
		}else if(typeChangeSpan1.equals(SwitchPoolSelectionEditPolicy.ADD_BOTTOM)){
			List<IGraphicalEditPart> lanes = (List<IGraphicalEditPart>)getPoolLanes() ;

			IGraphicalEditPart l = lanes.get(lanes.indexOf(laneEp)+1);
			lanes.set(lanes.indexOf(laneEp)+1, laneEp);
			lanes.set(lanes.indexOf(laneEp), l);
	
			for(IGraphicalEditPart lan : lanes){
				lan.refresh();
			}
			cmd.add(new RepositionEObjectCommand(laneEp.getEditingDomain(), "Reposition Lane", pool.getElements(),lane, 1)); //$NON-NLS-1$
		}else if(typeChangeSpan1.equals(SwitchPoolSelectionEditPolicy.REMOVE_BOTTOM)){
			List<IGraphicalEditPart> lanes = (List<IGraphicalEditPart>)getPoolLanes() ;

			IGraphicalEditPart l = lanes.get(lanes.indexOf(laneEp)+1);
			lanes.set(lanes.indexOf(laneEp)+1, laneEp);
			lanes.set(lanes.indexOf(laneEp), l);
	
			for(IGraphicalEditPart lan : lanes){
				lan.refresh();
			}
			cmd.add(new RepositionEObjectCommand(laneEp.getEditingDomain(), "Reposition Lane", pool.getElements(),lane, 1)); //$NON-NLS-1$
		}

		if(cmd != null){
			OperationHistoryFactory.getOperationHistory().execute(cmd, monitor, info);
		}

	}

	/**
	 * @return lanes in normal or in Evenemential subprocess pool
	 */
	protected List<? extends IGraphicalEditPart> getPoolLanes() {
		if(laneEp instanceof CustomLaneEditPart){
			CustomPoolCompartmentEditPart poolCompartment = ((CustomLaneEditPart)laneEp).getPoolCompartment();
			return poolCompartment.getPoolLanes();
		}
		return new ArrayList<IGraphicalEditPart>();
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
