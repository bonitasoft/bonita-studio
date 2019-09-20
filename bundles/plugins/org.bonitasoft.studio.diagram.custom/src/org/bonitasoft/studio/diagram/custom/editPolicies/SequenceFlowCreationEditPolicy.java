/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SequenceFlowEditPart;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class SequenceFlowCreationEditPolicy extends CreationEditPolicy {


	private static final Color FEEDBACK_COLOR = Display.getDefault().getSystemColor(SWT.COLOR_BLUE);
	private Color originalColor;
	private EditPart containerEp;

	@Override
	public Command getCommand(Request request) {
		if (understandsRequest(request)) {
			if (request instanceof CreateUnspecifiedTypeRequest) {
				return getUnspecifiedTypeCreateCommand((CreateUnspecifiedTypeRequest) request);
			} 
			return super.getCommand(request);
		}
		return null;
	}
	
	@Override
	public boolean understandsRequest(Request request) {
		if (request instanceof CreateUnspecifiedTypeRequest) {
			final List elementTypes = ((CreateUnspecifiedTypeRequest) request).getElementTypes();
			if(elementTypes.contains(ProcessElementTypes.TextAnnotation_3015)
					|| elementTypes.contains(ProcessElementTypes.TextAnnotation_2015)
					|| elementTypes.contains(ProcessElementTypes.Lane_3007)
					|| elementTypes.contains(ProcessElementTypes.Pool_2007)
					|| elementTypes.contains(ProcessElementTypes.StartErrorEvent_2033)
					|| elementTypes.contains(ProcessElementTypes.StartErrorEvent_3060)
					|| elementTypes.contains(ProcessElementTypes.StartEvent_2002)
					|| elementTypes.contains(ProcessElementTypes.StartEvent_3002)
					|| elementTypes.contains(ProcessElementTypes.StartMessageEvent_2010)
					|| elementTypes.contains(ProcessElementTypes.StartMessageEvent_3012)
					|| elementTypes.contains(ProcessElementTypes.StartSignalEvent_2022)
					|| elementTypes.contains(ProcessElementTypes.StartSignalEvent_3023)
					|| elementTypes.contains(ProcessElementTypes.StartTimerEvent_2016)
					|| elementTypes.contains(ProcessElementTypes.StartTimerEvent_3016)
					|| elementTypes.contains(ProcessElementTypes.EndErrorEvent_2029)
					|| elementTypes.contains(ProcessElementTypes.EndErrorEvent_3050)
					|| elementTypes.contains(ProcessElementTypes.EndEvent_2003)
					|| elementTypes.contains(ProcessElementTypes.EndEvent_3003)
					|| elementTypes.contains(ProcessElementTypes.EndMessageEvent_2011)
					|| elementTypes.contains(ProcessElementTypes.EndMessageEvent_3011)
					|| elementTypes.contains(ProcessElementTypes.EndSignalEvent_2023)
					|| elementTypes.contains(ProcessElementTypes.EndSignalEvent_3020)
					|| elementTypes.contains(ProcessElementTypes.EndTerminatedEvent_2035)
					|| elementTypes.contains(ProcessElementTypes.EndTerminatedEvent_3062)
					|| elementTypes.contains(ProcessElementTypes.CatchLinkEvent_2018)
					|| elementTypes.contains(ProcessElementTypes.CatchLinkEvent_3019)
					|| elementTypes.contains(ProcessElementTypes.ThrowLinkEvent_2019)
					|| elementTypes.contains(ProcessElementTypes.ThrowLinkEvent_3018)
					|| elementTypes.contains(ProcessElementTypes.SubProcessEvent_2031)
					|| elementTypes.contains(ProcessElementTypes.SubProcessEvent_3058)){
					return false;
			}
		}
		return super.understandsRequest(request);
	}


	@Override
	protected Command getCreateElementAndViewCommand(
			CreateViewAndElementRequest request) {
		return containerEp.getCommand(request);
	}

	@Override
	public void eraseTargetFeedback(Request request) {
		if(understandsRequest(request) && originalColor != null){
			ConnectionEditPart part =  (ConnectionEditPart) getHost();
			part.getFigure().setForegroundColor(originalColor);
		}else{
			super.showTargetFeedback(request);
		}
	}

	@Override
	public void showTargetFeedback(Request request) {
		if(understandsRequest(request)){
			ConnectionEditPart part =  (ConnectionEditPart) getHost();
			if(originalColor == null){
				originalColor = part.getFigure().getForegroundColor();
			}
			part.getFigure().setForegroundColor(FEEDBACK_COLOR);
		}else{
			super.showTargetFeedback(request);
		}
	}

	/**
	 * Gets the command to create a new view (and optionally element) for an
	 * unspecified type request. This command includes a command to popup a menu
	 * to prompt the user for the type to be created.
	 * 
	 * @param request
	 *            the unspecified type request
	 * @return the command
	 */
	private Command getUnspecifiedTypeCreateCommand(
			final CreateUnspecifiedTypeRequest request) {

		final Map createCmds = new HashMap();
		List validTypes = new ArrayList();
		for (Iterator iter = request.getElementTypes().iterator(); iter
				.hasNext();) {
			IElementType elementType = (IElementType) iter.next();
			Request createRequest = request.getRequestForType(elementType);
			if (createRequest != null) {
				SequenceFlowEditPart part  = (SequenceFlowEditPart) getHost();
				SequenceFlow flow=  (SequenceFlow) part.resolveSemanticElement();
				EObject hostElement = ((SequenceFlow) flow).getSource().eContainer();
				while (!(hostElement instanceof Container)) {
					hostElement = hostElement.eContainer();
				}
				EditPart ep = GMFTools.findEditPart((EditPart) getHost().getViewer().getRootEditPart().getChildren().get(0), hostElement);
				EditPart target = getCompartmentEditPart(ep);
				if ( target == null )  {
					continue;
				}
				setContainerEditPart(target);
				CompoundCommand	individualCmd = (CompoundCommand) getHost().getCommand(createRequest);
				if (individualCmd != null && individualCmd.canExecute()) {
					createCmds.put(elementType, individualCmd);
					validTypes.add(elementType);
				}
			}
		}

		if (createCmds.size() == 1) {
			return (Command) createCmds.values().toArray()[0];
		}
		return null;
	}

	private void setContainerEditPart(EditPart target) {
		this.containerEp = target;
	}

	private EditPart getCompartmentEditPart(EditPart target) {
		if(target instanceof ShapeCompartmentEditPart){
			return target;
		}else{
			for(Object c : target.getChildren()){
				if(c instanceof ShapeCompartmentEditPart){
					return (EditPart) c;
				}
			}
		}
		return null;
	}
}
