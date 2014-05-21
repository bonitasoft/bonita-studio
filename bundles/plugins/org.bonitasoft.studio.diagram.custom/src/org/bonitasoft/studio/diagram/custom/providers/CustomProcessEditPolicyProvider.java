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
package org.bonitasoft.studio.diagram.custom.providers;

import org.bonitasoft.studio.diagram.custom.editPolicies.AbstractSwitchLaneSelectionEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.ActivitySwitchEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.BoundaryEventSwitchEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.BoundaryEventToolEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.CustomCreationEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.CustomDragDropEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.CustomFeedbackXYLayoutPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.NextElementEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.OpenDetailsOrGoToSubProcessEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.SelectionFeedbackEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.SwitchLaneSelectionEditpolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.SwitchPoolSelectionEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.UpdateSizeLaneSelectionEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.UpdateSizePoolSelectionEditPolicy;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.BoundaryTimerEvent;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Event;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.Gateway;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.SendTask;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.model.process.TextAnnotation;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.CreateEditPoliciesOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider;

/**
 * @author Mickael Istria
 *
 */
public class CustomProcessEditPolicyProvider implements IEditPolicyProvider {

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider#createEditPolicies(org.eclipse.gef.EditPart)
	 */
	public void createEditPolicies(EditPart editPart) {
		
		EObject resolveSemanticElement = ((GraphicalEditPart)editPart).resolveSemanticElement();
		if(!(editPart instanceof ITextAwareEditPart)){//DO NOT INSTALL EDIT POLICIES ON LABELS
			if ((resolveSemanticElement instanceof FlowElement
					|| resolveSemanticElement instanceof BoundaryEvent
					|| resolveSemanticElement instanceof SubProcessEvent) 
					&& !(resolveSemanticElement instanceof Pool)) {
				editPart.installEditPolicy(SelectionFeedbackEditPolicy.BONITA_SELECTION_FEEDBACK_ROLE, new SelectionFeedbackEditPolicy(((IGraphicalEditPart)editPart).resolveSemanticElement().eClass()));
			}
			
			if ((resolveSemanticElement instanceof FlowElement
					 ||resolveSemanticElement instanceof BoundaryEvent 
					 || resolveSemanticElement instanceof TextAnnotation) 
					&& !(resolveSemanticElement instanceof Pool)
					&& !(resolveSemanticElement instanceof SubProcessEvent)) {

				editPart.installEditPolicy(NextElementEditPolicy.NEXT_ELEMENT_ROLE, new NextElementEditPolicy());
			}
		
			if (resolveSemanticElement instanceof Activity
					&& !(resolveSemanticElement instanceof SubProcessEvent)) {
				editPart.installEditPolicy(ActivitySwitchEditPolicy.SWITCH_TYPE_ROLE, new ActivitySwitchEditPolicy());
			}

			if (resolveSemanticElement instanceof Activity
					&& !(resolveSemanticElement instanceof SendTask)
					&& !(resolveSemanticElement instanceof SubProcessEvent)) {
				editPart.installEditPolicy(BoundaryEventToolEditPolicy.BOUNDARY_TOOL_ROLE, new BoundaryEventToolEditPolicy());
			}

			if (resolveSemanticElement instanceof Event) {
				editPart.installEditPolicy(ActivitySwitchEditPolicy.SWITCH_TYPE_ROLE, new ActivitySwitchEditPolicy());
			}
			
			if (resolveSemanticElement instanceof Gateway) {
				editPart.installEditPolicy(ActivitySwitchEditPolicy.SWITCH_TYPE_ROLE, new ActivitySwitchEditPolicy());
			}
			
			if (resolveSemanticElement instanceof BoundaryTimerEvent) {
				editPart.installEditPolicy(ActivitySwitchEditPolicy.SWITCH_TYPE_ROLE, new BoundaryEventSwitchEditPolicy());
			}

		}
		
		// Override the container LAYOUT_ROLE instead of the edit part PRIMARY_DRAG_ROLE
		// since the edit policy is created on edit part by container and there is no
		// way to override it
		EditPolicy layoutEditPolicy = editPart.getEditPolicy(EditPolicy.LAYOUT_ROLE);
		if (layoutEditPolicy != null && layoutEditPolicy instanceof XYLayoutEditPolicy) {
			editPart.removeEditPolicy(EditPolicy.LAYOUT_ROLE);
			editPart.installEditPolicy(EditPolicy.LAYOUT_ROLE, new CustomFeedbackXYLayoutPolicy());
		}
		
		if ( resolveSemanticElement instanceof Pool) {
			editPart.installEditPolicy(SwitchPoolSelectionEditPolicy.SWITCH_POOL_SELECTION_FEEDBACK_ROLE, new SwitchPoolSelectionEditPolicy());
			editPart.installEditPolicy(UpdateSizePoolSelectionEditPolicy.UPDATE_POOL_SIZE_SELECTION_FEEDBACK_ROLE, new UpdateSizePoolSelectionEditPolicy());
		}
		
		if (resolveSemanticElement instanceof Lane) {
			if(editPart instanceof LaneEditPart){
				editPart.installEditPolicy(AbstractSwitchLaneSelectionEditPolicy.SWITCH_LANE_SELECTION_FEEDBACK_ROLE, new SwitchLaneSelectionEditpolicy());
				editPart.installEditPolicy(UpdateSizeLaneSelectionEditPolicy.UPDATE_LANE_SIZE_SELECTION_FEEDBACK_ROLE, new UpdateSizeLaneSelectionEditPolicy());
			} 
		}

			
		editPart.removeEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE);
		editPart.installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new CustomDragDropEditPolicy());
		
		
		if (!(resolveSemanticElement instanceof MainProcess)) {
			editPart.removeEditPolicy(EditPolicyRoles.CREATION_ROLE);
			editPart.installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CustomCreationEditPolicy());
		}
		
		editPart.removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
		editPart.removeEditPolicy(EditPolicyRoles.POPUPBAR_ROLE);
		editPart.installEditPolicy(EditPolicyRoles.OPEN_ROLE, new OpenDetailsOrGoToSubProcessEditPolicy());
	}

	
	public void addProviderChangeListener(IProviderChangeListener listener) {
	}

	public boolean provides(IOperation operation) {
		if (operation instanceof CreateEditPoliciesOperation && ((CreateEditPoliciesOperation)operation).getEditPart() instanceof GraphicalEditPart) {
			GraphicalEditPart editPart = (GraphicalEditPart) ((CreateEditPoliciesOperation)operation).getEditPart();
			if(editPart.getRoot().getContents() instanceof MainProcessEditPart)
				return editPart.resolveSemanticElement() instanceof Element;
		}
		return false;
	}

	public void removeProviderChangeListener(IProviderChangeListener listener) {
	}

}
