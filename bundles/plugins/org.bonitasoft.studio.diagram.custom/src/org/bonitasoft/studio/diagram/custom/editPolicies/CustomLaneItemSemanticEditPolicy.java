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

package org.bonitasoft.studio.diagram.custom.editPolicies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bonitasoft.studio.diagram.custom.parts.CustomLaneCompartmentEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomLaneEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomPoolCompartmentEditPart;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.diagram.edit.policies.LaneItemSemanticEditPolicy;
import org.bonitasoft.studio.model.process.diagram.part.ValidateAction;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.core.commands.DeleteCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.CommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.requests.ArrangeRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.EditElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 *
 */
public class CustomLaneItemSemanticEditPolicy extends LaneItemSemanticEditPolicy {


	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.process.diagram.edit.policies.Lane2ItemSemanticEditPolicy#getDestroyElementCommand(org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest)
	 */
	@Override
	protected Command getDestroyElementCommand(final DestroyElementRequest req) {

		final CustomPoolCompartmentEditPart poolCompartment = (CustomPoolCompartmentEditPart) getHost().getParent() ;
		GraphicalEditPart compartment = poolCompartment ;

		CustomLaneCompartmentEditPart laneCompartment = null ;
		final List<CustomLaneEditPart> lanes = new ArrayList<CustomLaneEditPart>() ;

		for(final Object o : poolCompartment.getChildren()){
			if(o instanceof CustomLaneEditPart){
				lanes.add((CustomLaneEditPart) o);
			}
		}

		for(final Object o : getHost().getChildren()){
			if(o instanceof CustomLaneCompartmentEditPart){
				laneCompartment = (CustomLaneCompartmentEditPart) o ;
			}
		}


		//Set the target compartment
		if( lanes.size()>1){
			for(int i = 0 ; i< lanes.size() ; i++){
				if(lanes.get(i).equals(getHost())){
					if(i != lanes.size()-1){
						compartment = lanes.get(i+1).getCompartment();
					}else{
						compartment = lanes.get(i-1).getCompartment();
					}
				}
			}
		}else if(lanes.size() == 1){
			compartment = poolCompartment ;
		}


		final List<GraphicalEditPart> newList = new ArrayList<GraphicalEditPart>();
		for(final Object e : laneCompartment.getChildren()){
			if(e instanceof GraphicalEditPart) {
                newList.add(((GraphicalEditPart) e));
            }
		}

		final ChangeBoundsRequest request = new ChangeBoundsRequest();
		final HashMap<String,Object> extended = new HashMap<String, Object>() ;
		extended.put("DELETE_FROM_LANE", true) ;
		request.setExtendedData(extended ) ;
		request.setType(RequestConstants.REQ_ADD);
		request.setEditParts(newList);
		CreationEditPolicy cep = null;
		if(compartment.getEditPolicy(EditPolicyRoles.CREATION_ROLE) == null){
			compartment.installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CreationEditPolicy());
		}

		cep = (CreationEditPolicy) compartment.getEditPolicy(EditPolicyRoles.CREATION_ROLE);

		CommandProxy moveCmd = null ;

		if(newList.size() > 0 && cep.getCommand(request) != null){
			moveCmd = new CommandProxy(cep.getCommand(request));
		}






		final View view = (View) getHost().getModel();
		final CompositeTransactionalCommand cmd = new CompositeTransactionalCommand(
				getEditingDomain(), null);
		cmd.setTransactionNestingEnabled(true);


		final Lane lane = (Lane)((IGraphicalEditPart)getHost()).resolveSemanticElement();
		if(lane != null){
			for(final Element element : lane.getElements()){
				if(element instanceof Task) {
					final EditElementCommand eec = new SetValueCommand(new SetRequest(element, ProcessPackage.Literals.TASK__OVERRIDE_ACTORS_OF_THE_LANE, true)){

						/* (non-Javadoc)
						 * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doUndo(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
						 */
						@Override
						protected IStatus doUndo(final IProgressMonitor monitor,
								final IAdaptable info) throws ExecutionException {
							final IStatus status = super.doUndo(monitor, info);
							final DiagramEditor editor = (DiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
							ValidateAction.runValidation(editor.getDiagram());
							return status;
						}
					};

					cmd.add(eec);
				}
			}

		}


		final EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
		if (annotation == null) {
			// there are indirectly referenced children, need extra commands: false
			if(newList.size() > 0 && moveCmd != null){
				cmd.add(moveCmd);
			}
			cmd.add(new DestroyElementCommand(req));
		} else {
			if(newList.size() > 0){
				cmd.add(moveCmd);
			}
			cmd.add(new DeleteCommand(getEditingDomain(), view));
		}
		return getGEFWrapper(cmd.reduce());

	}


	public static CommandProxy arrangeAll(final EditPart ep, final List<GraphicalEditPart> newList) {
		final ArrangeRequest request = new ArrangeRequest(RequestConstants.REQ_ARRANGE_DEFERRED);
		request.setPartsToArrange(newList);
		request.setViewAdaptersToArrange(newList);
		return new CommandProxy(ep.getCommand(request));
	}

}
