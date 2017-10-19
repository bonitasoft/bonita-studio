/*
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
package org.bonitasoft.studio.model.process.diagram.edit.parts;

import org.bonitasoft.studio.model.process.diagram.edit.policies.PoolPoolCompartmentCanonicalEditPolicy;
import org.bonitasoft.studio.model.process.diagram.edit.policies.PoolPoolCompartmentItemSemanticEditPolicy;
import org.bonitasoft.studio.model.process.diagram.part.Messages;
import org.bonitasoft.studio.model.process.diagram.part.ProcessVisualIDRegistry;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DragDropEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeConnectionRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.edit.policies.reparent.CreationEditPolicyWithCustomReparent;

/**
 * @generated
 */
public class PoolPoolCompartmentEditPart extends ShapeCompartmentEditPart {

	/**
	* @generated
	*/
	public static final int VISUAL_ID = 7001;

	/**
	* @generated
	*/
	public PoolPoolCompartmentEditPart(View view) {
		super(view);
	}

	/**
	* @generated
	*/
	public String getCompartmentName() {
		return Messages.PoolPoolCompartmentEditPart_title;
	}

	/**
	* @generated
	*/
	public IFigure createFigure() {
		ResizableCompartmentFigure result = (ResizableCompartmentFigure) super.createFigure();
		result.setTitleVisibility(false);
		return result;
	}

	/**
	* @generated
	*/
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE, new PoolPoolCompartmentItemSemanticEditPolicy());
		installEditPolicy(EditPolicyRoles.CREATION_ROLE,
				new CreationEditPolicyWithCustomReparent(ProcessVisualIDRegistry.TYPED_INSTANCE));
		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new DragDropEditPolicy());
		installEditPolicy(EditPolicyRoles.CANONICAL_ROLE, new PoolPoolCompartmentCanonicalEditPolicy());
	}

	/**
	* @generated
	*/
	protected void setRatio(Double ratio) {
		if (getFigure().getParent().getLayoutManager() instanceof ConstrainedToolbarLayout) {
			super.setRatio(ratio);
		}
	}

	/**
	* @generated
	*/
	public EditPart getTargetEditPart(Request request) {
		if (request instanceof CreateViewAndElementRequest) {
			CreateElementRequestAdapter adapter = ((CreateViewAndElementRequest) request).getViewAndElementDescriptor()
					.getCreateElementRequestAdapter();
			IElementType type = (IElementType) adapter.getAdapter(IElementType.class);
			if (type == ProcessElementTypes.Lane_3007) {
				return this;
			}
			if (type == ProcessElementTypes.ANDGateway_3009) {
				return this;
			}
			if (type == ProcessElementTypes.StartEvent_3002) {
				return this;
			}
			if (type == ProcessElementTypes.EndEvent_3003) {
				return this;
			}
			if (type == ProcessElementTypes.CallActivity_3063) {
				return this;
			}
			if (type == ProcessElementTypes.SubProcessEvent_3058) {
				return this;
			}
			if (type == ProcessElementTypes.Task_3005) {
				return this;
			}
			if (type == ProcessElementTypes.ReceiveTask_3026) {
				return this;
			}
			if (type == ProcessElementTypes.SendTask_3025) {
				return this;
			}
			if (type == ProcessElementTypes.ServiceTask_3027) {
				return this;
			}
			if (type == ProcessElementTypes.ScriptTask_3028) {
				return this;
			}
			if (type == ProcessElementTypes.XORGateway_3008) {
				return this;
			}
			if (type == ProcessElementTypes.Activity_3006) {
				return this;
			}
			if (type == ProcessElementTypes.IntermediateCatchMessageEvent_3013) {
				return this;
			}
			if (type == ProcessElementTypes.StartMessageEvent_3012) {
				return this;
			}
			if (type == ProcessElementTypes.EndMessageEvent_3011) {
				return this;
			}
			if (type == ProcessElementTypes.IntermediateThrowMessageEvent_3014) {
				return this;
			}
			if (type == ProcessElementTypes.TextAnnotation_3015) {
				return this;
			}
			if (type == ProcessElementTypes.StartTimerEvent_3016) {
				return this;
			}
			if (type == ProcessElementTypes.IntermediateCatchTimerEvent_3017) {
				return this;
			}
			if (type == ProcessElementTypes.CatchLinkEvent_3019) {
				return this;
			}
			if (type == ProcessElementTypes.ThrowLinkEvent_3018) {
				return this;
			}
			if (type == ProcessElementTypes.StartSignalEvent_3023) {
				return this;
			}
			if (type == ProcessElementTypes.IntermediateThrowSignalEvent_3022) {
				return this;
			}
			if (type == ProcessElementTypes.IntermediateCatchSignalEvent_3021) {
				return this;
			}
			if (type == ProcessElementTypes.EndSignalEvent_3020) {
				return this;
			}
			if (type == ProcessElementTypes.EndErrorEvent_3050) {
				return this;
			}
			if (type == ProcessElementTypes.EndTerminatedEvent_3062) {
				return this;
			}
			if (type == ProcessElementTypes.Event_3024) {
				return this;
			}
			if (type == ProcessElementTypes.InclusiveGateway_3051) {
				return this;
			}
			return getParent().getTargetEditPart(request);
		}
		if (request instanceof CreateUnspecifiedTypeConnectionRequest) {
			if (RequestConstants.REQ_CONNECTION_END.equals(request.getType())) {
				for (Object type : ((CreateUnspecifiedTypeConnectionRequest) request).getElementTypes()) {
					if (type instanceof IElementType) {
						IElementType elementType = (IElementType) type;
						if (elementType.equals(ProcessElementTypes.SequenceFlow_4001)
								|| elementType.equals(ProcessElementTypes.MessageFlow_4002)
								|| elementType.equals(ProcessElementTypes.TextAnnotationAttachment_4003))
							return super.getTargetEditPart(request);
					}
				}
			}
			return getParent().getTargetEditPart(request);
		}
		return super.getTargetEditPart(request);
	}

}
