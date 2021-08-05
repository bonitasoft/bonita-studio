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
package org.bonitasoft.studio.model.process.diagram.part;

import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.edit.parts.*;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.structure.DiagramStructure;

/**
 * This registry is used to determine which type of visual object should be
 * created for the corresponding Diagram, Node, ChildNode or Link represented
 * by a domain model object.
 * 
 * @generated
 */
public class ProcessVisualIDRegistry {

	/**
	* @generated
	*/
	private static final String DEBUG_KEY = "org.bonitasoft.studio.diagram/debug/visualID"; //$NON-NLS-1$

	/**
	* @generated
	*/
	public static int getVisualID(View view) {
		if (view instanceof Diagram) {
			if (MainProcessEditPart.MODEL_ID.equals(view.getType())) {
				return MainProcessEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		return org.bonitasoft.studio.model.process.diagram.part.ProcessVisualIDRegistry.getVisualID(view.getType());
	}

	/**
	* @generated
	*/
	public static String getModelID(View view) {
		View diagram = view.getDiagram();
		while (view != diagram) {
			EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
			if (annotation != null) {
				return (String) annotation.getDetails().get("modelID"); //$NON-NLS-1$
			}
			view = (View) view.eContainer();
		}
		return diagram != null ? diagram.getType() : null;
	}

	/**
	* @generated
	*/
	public static int getVisualID(String type) {
		try {
			return Integer.parseInt(type);
		} catch (NumberFormatException e) {
			if (Boolean.TRUE.toString().equalsIgnoreCase(Platform.getDebugOption(DEBUG_KEY))) {
				ProcessDiagramEditorPlugin.getInstance()
						.logError("Unable to parse view type as a visualID number: " + type);
			}
		}
		return -1;
	}

	/**
	* @generated
	*/
	public static String getType(int visualID) {
		return Integer.toString(visualID);
	}

	/**
	* @generated
	*/
	public static int getDiagramVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (ProcessPackage.eINSTANCE.getMainProcess().isSuperTypeOf(domainElement.eClass())
				&& isDiagram((MainProcess) domainElement)) {
			return MainProcessEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	* @generated
	*/
	public static int getNodeVisualID(View containerView, EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		String containerModelID = org.bonitasoft.studio.model.process.diagram.part.ProcessVisualIDRegistry
				.getModelID(containerView);
		if (!MainProcessEditPart.MODEL_ID.equals(containerModelID)) {
			return -1;
		}
		int containerVisualID;
		if (MainProcessEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.bonitasoft.studio.model.process.diagram.part.ProcessVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = MainProcessEditPart.VISUAL_ID;
			} else {
				return -1;
			}
		}
		switch (containerVisualID) {
		case MainProcessEditPart.VISUAL_ID:
			if (ProcessPackage.eINSTANCE.getANDGateway().isSuperTypeOf(domainElement.eClass())) {
				return ANDGatewayEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getStartEvent().isSuperTypeOf(domainElement.eClass())) {
				return StartEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getTask().isSuperTypeOf(domainElement.eClass())) {
				return TaskEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getCallActivity().isSuperTypeOf(domainElement.eClass())) {
				return CallActivityEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getSubProcessEvent().isSuperTypeOf(domainElement.eClass())) {
				return SubProcessEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getReceiveTask().isSuperTypeOf(domainElement.eClass())) {
				return ReceiveTaskEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getSendTask().isSuperTypeOf(domainElement.eClass())) {
				return SendTaskEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getServiceTask().isSuperTypeOf(domainElement.eClass())) {
				return ServiceTaskEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getScriptTask().isSuperTypeOf(domainElement.eClass())) {
				return ScriptTaskEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getXORGateway().isSuperTypeOf(domainElement.eClass())) {
				return XORGatewayEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getInclusiveGateway().isSuperTypeOf(domainElement.eClass())) {
				return InclusiveGatewayEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getActivity().isSuperTypeOf(domainElement.eClass())) {
				return ActivityEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getPool().isSuperTypeOf(domainElement.eClass())) {
				return PoolEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getStartMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return StartMessageEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndMessageEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateCatchMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateCatchMessageEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateThrowMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateThrowMessageEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getTextAnnotation().isSuperTypeOf(domainElement.eClass())) {
				return TextAnnotationEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateCatchTimerEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateCatchTimerEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getStartTimerEvent().isSuperTypeOf(domainElement.eClass())) {
				return StartTimerEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getCatchLinkEvent().isSuperTypeOf(domainElement.eClass())) {
				return CatchLinkEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getThrowLinkEvent().isSuperTypeOf(domainElement.eClass())) {
				return ThrowLinkEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateThrowSignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateThrowSignalEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateCatchSignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateCatchSignalEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getStartSignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return StartSignalEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndSignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndSignalEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEvent().isSuperTypeOf(domainElement.eClass())) {
				return EventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndErrorEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndErrorEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getStartErrorEvent().isSuperTypeOf(domainElement.eClass())) {
				return StartErrorEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndTerminatedEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndTerminatedEventEditPart.VISUAL_ID;
			}
			break;
		case TaskEditPart.VISUAL_ID:
			if (ProcessPackage.eINSTANCE.getIntermediateErrorCatchEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateErrorCatchEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getBoundaryMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return BoundaryMessageEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getNonInterruptingBoundaryTimerEvent().isSuperTypeOf(domainElement.eClass())) {
				return NonInterruptingBoundaryTimerEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getBoundaryTimerEvent().isSuperTypeOf(domainElement.eClass())) {
				return BoundaryTimerEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getBoundarySignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return BoundarySignalEventEditPart.VISUAL_ID;
			}
			break;
		case CallActivityEditPart.VISUAL_ID:
			if (ProcessPackage.eINSTANCE.getIntermediateErrorCatchEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateErrorCatchEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getBoundaryMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return BoundaryMessageEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getNonInterruptingBoundaryTimerEvent().isSuperTypeOf(domainElement.eClass())) {
				return NonInterruptingBoundaryTimerEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getBoundaryTimerEvent().isSuperTypeOf(domainElement.eClass())) {
				return BoundaryTimerEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getBoundarySignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return BoundarySignalEvent2EditPart.VISUAL_ID;
			}
			break;
		case ReceiveTaskEditPart.VISUAL_ID:
			if (ProcessPackage.eINSTANCE.getIntermediateErrorCatchEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateErrorCatchEvent3EditPart.VISUAL_ID;
			}
			break;
		case ServiceTaskEditPart.VISUAL_ID:
			if (ProcessPackage.eINSTANCE.getIntermediateErrorCatchEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateErrorCatchEvent4EditPart.VISUAL_ID;
			}
			break;
		case ScriptTaskEditPart.VISUAL_ID:
			if (ProcessPackage.eINSTANCE.getIntermediateErrorCatchEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateErrorCatchEvent5EditPart.VISUAL_ID;
			}
			break;
		case ActivityEditPart.VISUAL_ID:
			if (ProcessPackage.eINSTANCE.getIntermediateErrorCatchEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateErrorCatchEvent6EditPart.VISUAL_ID;
			}
			break;
		case CallActivity2EditPart.VISUAL_ID:
			if (ProcessPackage.eINSTANCE.getIntermediateErrorCatchEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateErrorCatchEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getBoundaryMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return BoundaryMessageEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getNonInterruptingBoundaryTimerEvent().isSuperTypeOf(domainElement.eClass())) {
				return NonInterruptingBoundaryTimerEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getBoundaryTimerEvent().isSuperTypeOf(domainElement.eClass())) {
				return BoundaryTimerEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getBoundarySignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return BoundarySignalEvent2EditPart.VISUAL_ID;
			}
			break;
		case Task2EditPart.VISUAL_ID:
			if (ProcessPackage.eINSTANCE.getIntermediateErrorCatchEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateErrorCatchEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getBoundaryMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return BoundaryMessageEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getNonInterruptingBoundaryTimerEvent().isSuperTypeOf(domainElement.eClass())) {
				return NonInterruptingBoundaryTimerEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getBoundaryTimerEvent().isSuperTypeOf(domainElement.eClass())) {
				return BoundaryTimerEventEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getBoundarySignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return BoundarySignalEventEditPart.VISUAL_ID;
			}
			break;
		case ReceiveTask2EditPart.VISUAL_ID:
			if (ProcessPackage.eINSTANCE.getIntermediateErrorCatchEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateErrorCatchEvent3EditPart.VISUAL_ID;
			}
			break;
		case ServiceTask2EditPart.VISUAL_ID:
			if (ProcessPackage.eINSTANCE.getIntermediateErrorCatchEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateErrorCatchEvent4EditPart.VISUAL_ID;
			}
			break;
		case ScriptTask2EditPart.VISUAL_ID:
			if (ProcessPackage.eINSTANCE.getIntermediateErrorCatchEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateErrorCatchEvent5EditPart.VISUAL_ID;
			}
			break;
		case Activity2EditPart.VISUAL_ID:
			if (ProcessPackage.eINSTANCE.getIntermediateErrorCatchEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateErrorCatchEvent6EditPart.VISUAL_ID;
			}
			break;
		case SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID:
			if (ProcessPackage.eINSTANCE.getANDGateway().isSuperTypeOf(domainElement.eClass())) {
				return ANDGateway2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getCallActivity().isSuperTypeOf(domainElement.eClass())) {
				return CallActivity2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getTask().isSuperTypeOf(domainElement.eClass())) {
				return Task2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getReceiveTask().isSuperTypeOf(domainElement.eClass())) {
				return ReceiveTask2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getSendTask().isSuperTypeOf(domainElement.eClass())) {
				return SendTask2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getServiceTask().isSuperTypeOf(domainElement.eClass())) {
				return ServiceTask2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getScriptTask().isSuperTypeOf(domainElement.eClass())) {
				return ScriptTask2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getXORGateway().isSuperTypeOf(domainElement.eClass())) {
				return XORGateway2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getActivity().isSuperTypeOf(domainElement.eClass())) {
				return Activity2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateCatchMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateCatchMessageEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getStartMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return StartMessageEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndMessageEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateThrowMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateThrowMessageEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getTextAnnotation().isSuperTypeOf(domainElement.eClass())) {
				return TextAnnotation2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getStartTimerEvent().isSuperTypeOf(domainElement.eClass())) {
				return StartTimerEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateCatchTimerEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateCatchTimerEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getStartSignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return StartSignalEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateThrowSignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateThrowSignalEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateCatchSignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateCatchSignalEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndSignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndSignalEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndErrorEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndErrorEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndTerminatedEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndTerminatedEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getStartErrorEvent().isSuperTypeOf(domainElement.eClass())) {
				return StartErrorEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEvent().isSuperTypeOf(domainElement.eClass())) {
				return Event2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getInclusiveGateway().isSuperTypeOf(domainElement.eClass())) {
				return InclusiveGateway2EditPart.VISUAL_ID;
			}
			break;
		case PoolPoolCompartmentEditPart.VISUAL_ID:
			if (ProcessPackage.eINSTANCE.getLane().isSuperTypeOf(domainElement.eClass())) {
				return LaneEditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getANDGateway().isSuperTypeOf(domainElement.eClass())) {
				return ANDGateway2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getStartEvent().isSuperTypeOf(domainElement.eClass())) {
				return StartEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getCallActivity().isSuperTypeOf(domainElement.eClass())) {
				return CallActivity2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getSubProcessEvent().isSuperTypeOf(domainElement.eClass())) {
				return SubProcessEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getTask().isSuperTypeOf(domainElement.eClass())) {
				return Task2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getReceiveTask().isSuperTypeOf(domainElement.eClass())) {
				return ReceiveTask2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getSendTask().isSuperTypeOf(domainElement.eClass())) {
				return SendTask2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getServiceTask().isSuperTypeOf(domainElement.eClass())) {
				return ServiceTask2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getScriptTask().isSuperTypeOf(domainElement.eClass())) {
				return ScriptTask2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getXORGateway().isSuperTypeOf(domainElement.eClass())) {
				return XORGateway2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getActivity().isSuperTypeOf(domainElement.eClass())) {
				return Activity2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateCatchMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateCatchMessageEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getStartMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return StartMessageEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndMessageEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateThrowMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateThrowMessageEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getTextAnnotation().isSuperTypeOf(domainElement.eClass())) {
				return TextAnnotation2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getStartTimerEvent().isSuperTypeOf(domainElement.eClass())) {
				return StartTimerEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateCatchTimerEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateCatchTimerEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getCatchLinkEvent().isSuperTypeOf(domainElement.eClass())) {
				return CatchLinkEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getThrowLinkEvent().isSuperTypeOf(domainElement.eClass())) {
				return ThrowLinkEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getStartSignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return StartSignalEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateThrowSignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateThrowSignalEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateCatchSignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateCatchSignalEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndSignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndSignalEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndErrorEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndErrorEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndTerminatedEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndTerminatedEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEvent().isSuperTypeOf(domainElement.eClass())) {
				return Event2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getInclusiveGateway().isSuperTypeOf(domainElement.eClass())) {
				return InclusiveGateway2EditPart.VISUAL_ID;
			}
			break;
		case LaneLaneCompartmentEditPart.VISUAL_ID:
			if (ProcessPackage.eINSTANCE.getTask().isSuperTypeOf(domainElement.eClass())) {
				return Task2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getANDGateway().isSuperTypeOf(domainElement.eClass())) {
				return ANDGateway2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getStartEvent().isSuperTypeOf(domainElement.eClass())) {
				return StartEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getCallActivity().isSuperTypeOf(domainElement.eClass())) {
				return CallActivity2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getSubProcessEvent().isSuperTypeOf(domainElement.eClass())) {
				return SubProcessEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getXORGateway().isSuperTypeOf(domainElement.eClass())) {
				return XORGateway2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getSendTask().isSuperTypeOf(domainElement.eClass())) {
				return SendTask2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getReceiveTask().isSuperTypeOf(domainElement.eClass())) {
				return ReceiveTask2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getServiceTask().isSuperTypeOf(domainElement.eClass())) {
				return ServiceTask2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getScriptTask().isSuperTypeOf(domainElement.eClass())) {
				return ScriptTask2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getActivity().isSuperTypeOf(domainElement.eClass())) {
				return Activity2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateCatchMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateCatchMessageEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndMessageEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getStartMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return StartMessageEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateThrowMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateThrowMessageEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getTextAnnotation().isSuperTypeOf(domainElement.eClass())) {
				return TextAnnotation2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getStartTimerEvent().isSuperTypeOf(domainElement.eClass())) {
				return StartTimerEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateCatchTimerEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateCatchTimerEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getThrowLinkEvent().isSuperTypeOf(domainElement.eClass())) {
				return ThrowLinkEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getCatchLinkEvent().isSuperTypeOf(domainElement.eClass())) {
				return CatchLinkEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndSignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndSignalEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateCatchSignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateCatchSignalEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateThrowSignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateThrowSignalEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getStartSignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return StartSignalEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndErrorEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndErrorEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndTerminatedEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndTerminatedEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEvent().isSuperTypeOf(domainElement.eClass())) {
				return Event2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getInclusiveGateway().isSuperTypeOf(domainElement.eClass())) {
				return InclusiveGateway2EditPart.VISUAL_ID;
			}
			break;
		case SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID:
			if (ProcessPackage.eINSTANCE.getANDGateway().isSuperTypeOf(domainElement.eClass())) {
				return ANDGateway2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getCallActivity().isSuperTypeOf(domainElement.eClass())) {
				return CallActivity2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getTask().isSuperTypeOf(domainElement.eClass())) {
				return Task2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getReceiveTask().isSuperTypeOf(domainElement.eClass())) {
				return ReceiveTask2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getSendTask().isSuperTypeOf(domainElement.eClass())) {
				return SendTask2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getServiceTask().isSuperTypeOf(domainElement.eClass())) {
				return ServiceTask2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getScriptTask().isSuperTypeOf(domainElement.eClass())) {
				return ScriptTask2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getXORGateway().isSuperTypeOf(domainElement.eClass())) {
				return XORGateway2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getActivity().isSuperTypeOf(domainElement.eClass())) {
				return Activity2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateCatchMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateCatchMessageEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getStartMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return StartMessageEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndMessageEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateThrowMessageEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateThrowMessageEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getTextAnnotation().isSuperTypeOf(domainElement.eClass())) {
				return TextAnnotation2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getStartTimerEvent().isSuperTypeOf(domainElement.eClass())) {
				return StartTimerEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateCatchTimerEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateCatchTimerEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getStartSignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return StartSignalEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateThrowSignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateThrowSignalEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getIntermediateCatchSignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return IntermediateCatchSignalEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndSignalEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndSignalEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndErrorEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndErrorEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEndTerminatedEvent().isSuperTypeOf(domainElement.eClass())) {
				return EndTerminatedEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getStartErrorEvent().isSuperTypeOf(domainElement.eClass())) {
				return StartErrorEvent2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getEvent().isSuperTypeOf(domainElement.eClass())) {
				return Event2EditPart.VISUAL_ID;
			}
			if (ProcessPackage.eINSTANCE.getInclusiveGateway().isSuperTypeOf(domainElement.eClass())) {
				return InclusiveGateway2EditPart.VISUAL_ID;
			}
			break;
		}
		return -1;
	}

	/**
	* @generated
	*/
	public static boolean canCreateNode(View containerView, int nodeVisualID) {
		String containerModelID = org.bonitasoft.studio.model.process.diagram.part.ProcessVisualIDRegistry
				.getModelID(containerView);
		if (!MainProcessEditPart.MODEL_ID.equals(containerModelID)) {
			return false;
		}
		int containerVisualID;
		if (MainProcessEditPart.MODEL_ID.equals(containerModelID)) {
			containerVisualID = org.bonitasoft.studio.model.process.diagram.part.ProcessVisualIDRegistry
					.getVisualID(containerView);
		} else {
			if (containerView instanceof Diagram) {
				containerVisualID = MainProcessEditPart.VISUAL_ID;
			} else {
				return false;
			}
		}
		switch (containerVisualID) {
		case MainProcessEditPart.VISUAL_ID:
			if (ANDGatewayEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TaskEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (CallActivityEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SubProcessEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ReceiveTaskEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SendTaskEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ServiceTaskEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ScriptTaskEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (XORGatewayEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (InclusiveGatewayEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ActivityEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PoolEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartMessageEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndMessageEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateCatchMessageEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateThrowMessageEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TextAnnotationEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateCatchTimerEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartTimerEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (CatchLinkEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ThrowLinkEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateThrowSignalEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateCatchSignalEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartSignalEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndSignalEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndErrorEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartErrorEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndTerminatedEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ANDGatewayEditPart.VISUAL_ID:
			if (ANDGatewayLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case StartEventEditPart.VISUAL_ID:
			if (StartEventLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EndEventEditPart.VISUAL_ID:
			if (EndEventLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TaskEditPart.VISUAL_ID:
			if (TaskNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateErrorCatchEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BoundaryMessageEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (NonInterruptingBoundaryTimerEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BoundaryTimerEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BoundarySignalEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case CallActivityEditPart.VISUAL_ID:
			if (CallActivityNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateErrorCatchEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BoundaryMessageEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (NonInterruptingBoundaryTimerEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BoundaryTimerEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BoundarySignalEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SubProcessEventEditPart.VISUAL_ID:
			if (SubProcessEventLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ReceiveTaskEditPart.VISUAL_ID:
			if (ReceiveTaskLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateErrorCatchEvent3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SendTaskEditPart.VISUAL_ID:
			if (SendTaskLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ServiceTaskEditPart.VISUAL_ID:
			if (ServiceTaskLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateErrorCatchEvent4EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ScriptTaskEditPart.VISUAL_ID:
			if (ScriptTaskLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateErrorCatchEvent5EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case XORGatewayEditPart.VISUAL_ID:
			if (XORGatewayLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case InclusiveGatewayEditPart.VISUAL_ID:
			if (InclusiveGatewayLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ActivityEditPart.VISUAL_ID:
			if (ActivityNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateErrorCatchEvent6EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case PoolEditPart.VISUAL_ID:
			if (PoolNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (PoolPoolCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case StartMessageEventEditPart.VISUAL_ID:
			if (StartMessageEventLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EndMessageEventEditPart.VISUAL_ID:
			if (EndMessageEventLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case IntermediateCatchMessageEventEditPart.VISUAL_ID:
			if (IntermediateCatchMessageEventLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case IntermediateThrowMessageEventEditPart.VISUAL_ID:
			if (IntermediateThrowMessageEventLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TextAnnotationEditPart.VISUAL_ID:
			if (TextAnnotationTextEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case IntermediateCatchTimerEventEditPart.VISUAL_ID:
			if (IntermediateCatchTimerEventLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case StartTimerEventEditPart.VISUAL_ID:
			if (StartTimerEventLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case CatchLinkEventEditPart.VISUAL_ID:
			if (CatchLinkEventLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ThrowLinkEventEditPart.VISUAL_ID:
			if (ThrowLinkEventLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case IntermediateThrowSignalEventEditPart.VISUAL_ID:
			if (IntermediateThrowSignalEventLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case IntermediateCatchSignalEventEditPart.VISUAL_ID:
			if (IntermediateCatchSignalEventLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case StartSignalEventEditPart.VISUAL_ID:
			if (StartSignalEventLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EndSignalEventEditPart.VISUAL_ID:
			if (EndSignalEventLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EndErrorEventEditPart.VISUAL_ID:
			if (EndErrorEventLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case StartErrorEventEditPart.VISUAL_ID:
			if (StartErrorEventLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EndTerminatedEventEditPart.VISUAL_ID:
			if (EndTerminatedEventLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case IntermediateErrorCatchEvent2EditPart.VISUAL_ID:
			if (IntermediateErrorCatchEventLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BoundaryMessageEventEditPart.VISUAL_ID:
			if (BoundaryMessageEventLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case NonInterruptingBoundaryTimerEventEditPart.VISUAL_ID:
			if (NonInterruptingBoundaryTimerEventNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BoundaryTimerEventEditPart.VISUAL_ID:
			if (BoundaryTimerEventLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BoundarySignalEventEditPart.VISUAL_ID:
			if (BoundarySignalEventLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case IntermediateErrorCatchEventEditPart.VISUAL_ID:
			if (IntermediateErrorCatchEventLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BoundaryMessageEvent2EditPart.VISUAL_ID:
			if (BoundaryMessageEventLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case NonInterruptingBoundaryTimerEvent2EditPart.VISUAL_ID:
			if (NonInterruptingBoundaryTimerEventName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BoundaryTimerEvent2EditPart.VISUAL_ID:
			if (BoundaryTimerEventLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case BoundarySignalEvent2EditPart.VISUAL_ID:
			if (BoundarySignalEventLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ANDGateway2EditPart.VISUAL_ID:
			if (ANDGatewayLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EndEvent2EditPart.VISUAL_ID:
			if (EndEventLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case CallActivity2EditPart.VISUAL_ID:
			if (CallActivityName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateErrorCatchEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BoundaryMessageEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (NonInterruptingBoundaryTimerEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BoundaryTimerEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BoundarySignalEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Task2EditPart.VISUAL_ID:
			if (TaskName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateErrorCatchEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BoundaryMessageEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (NonInterruptingBoundaryTimerEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BoundaryTimerEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (BoundarySignalEventEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ReceiveTask2EditPart.VISUAL_ID:
			if (ReceiveTaskLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateErrorCatchEvent3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case IntermediateErrorCatchEvent3EditPart.VISUAL_ID:
			if (IntermediateErrorCatchEventLabel3EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SendTask2EditPart.VISUAL_ID:
			if (SendTaskLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ServiceTask2EditPart.VISUAL_ID:
			if (ServiceTaskLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateErrorCatchEvent4EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case IntermediateErrorCatchEvent4EditPart.VISUAL_ID:
			if (IntermediateErrorCatchEventLabel4EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ScriptTask2EditPart.VISUAL_ID:
			if (ScriptTaskLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateErrorCatchEvent5EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case IntermediateErrorCatchEvent5EditPart.VISUAL_ID:
			if (IntermediateErrorCatchEventLabel5EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case XORGateway2EditPart.VISUAL_ID:
			if (XORGatewayLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case Activity2EditPart.VISUAL_ID:
			if (ActivityName2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateErrorCatchEvent6EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case IntermediateErrorCatchEvent6EditPart.VISUAL_ID:
			if (IntermediateErrorCatchEventLabel6EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case IntermediateCatchMessageEvent2EditPart.VISUAL_ID:
			if (IntermediateCatchMessageEventLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case StartMessageEvent2EditPart.VISUAL_ID:
			if (StartMessageEventLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EndMessageEvent2EditPart.VISUAL_ID:
			if (EndMessageEventLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case IntermediateThrowMessageEvent2EditPart.VISUAL_ID:
			if (IntermediateThrowMessageEventLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case TextAnnotation2EditPart.VISUAL_ID:
			if (TextAnnotationText2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case StartTimerEvent2EditPart.VISUAL_ID:
			if (StartTimerEventLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case IntermediateCatchTimerEvent2EditPart.VISUAL_ID:
			if (IntermediateCatchTimerEventLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case StartSignalEvent2EditPart.VISUAL_ID:
			if (StartSignalEventLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case IntermediateThrowSignalEvent2EditPart.VISUAL_ID:
			if (IntermediateThrowSignalEventLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case IntermediateCatchSignalEvent2EditPart.VISUAL_ID:
			if (IntermediateCatchSignalEventLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EndSignalEvent2EditPart.VISUAL_ID:
			if (EndSignalEventLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EndErrorEvent2EditPart.VISUAL_ID:
			if (EndErrorEventLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case EndTerminatedEvent2EditPart.VISUAL_ID:
			if (EndTerminatedEventLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case StartErrorEvent2EditPart.VISUAL_ID:
			if (StartErrorEventLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case InclusiveGateway2EditPart.VISUAL_ID:
			if (InclusiveGatewayLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case LaneEditPart.VISUAL_ID:
			if (LaneNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (LaneLaneCompartmentEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case StartEvent2EditPart.VISUAL_ID:
			if (StartEventLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SubProcessEvent2EditPart.VISUAL_ID:
			if (SubProcessEventLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case ThrowLinkEvent2EditPart.VISUAL_ID:
			if (ThrowLinkEventLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case CatchLinkEvent2EditPart.VISUAL_ID:
			if (CatchLinkEventLabel2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID:
			if (ANDGateway2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (CallActivity2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Task2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ReceiveTask2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SendTask2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ServiceTask2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ScriptTask2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (XORGateway2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Activity2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateCatchMessageEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartMessageEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndMessageEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateThrowMessageEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TextAnnotation2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartTimerEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateCatchTimerEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartSignalEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateThrowSignalEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateCatchSignalEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndSignalEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndErrorEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndTerminatedEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartErrorEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Event2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (InclusiveGateway2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case PoolPoolCompartmentEditPart.VISUAL_ID:
			if (LaneEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ANDGateway2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (CallActivity2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SubProcessEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Task2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ReceiveTask2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SendTask2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ServiceTask2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ScriptTask2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (XORGateway2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Activity2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateCatchMessageEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartMessageEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndMessageEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateThrowMessageEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TextAnnotation2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartTimerEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateCatchTimerEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (CatchLinkEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ThrowLinkEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartSignalEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateThrowSignalEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateCatchSignalEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndSignalEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndErrorEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndTerminatedEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Event2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (InclusiveGateway2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case LaneLaneCompartmentEditPart.VISUAL_ID:
			if (Task2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ANDGateway2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (CallActivity2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SubProcessEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (XORGateway2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SendTask2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ReceiveTask2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ServiceTask2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ScriptTask2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Activity2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateCatchMessageEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndMessageEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartMessageEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateThrowMessageEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TextAnnotation2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartTimerEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateCatchTimerEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ThrowLinkEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (CatchLinkEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndSignalEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateCatchSignalEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateThrowSignalEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartSignalEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndErrorEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndTerminatedEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Event2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (InclusiveGateway2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID:
			if (ANDGateway2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (CallActivity2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Task2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ReceiveTask2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (SendTask2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ServiceTask2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (ScriptTask2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (XORGateway2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Activity2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateCatchMessageEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartMessageEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndMessageEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateThrowMessageEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (TextAnnotation2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartTimerEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateCatchTimerEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartSignalEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateThrowSignalEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (IntermediateCatchSignalEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndSignalEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndErrorEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (EndTerminatedEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (StartErrorEvent2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (Event2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			if (InclusiveGateway2EditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case SequenceFlowEditPart.VISUAL_ID:
			if (SequenceFlowNameEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		case MessageFlowEditPart.VISUAL_ID:
			if (MessageFlowLabelEditPart.VISUAL_ID == nodeVisualID) {
				return true;
			}
			break;
		}
		return false;
	}

	/**
	* @generated
	*/
	public static int getLinkWithClassVisualID(EObject domainElement) {
		if (domainElement == null) {
			return -1;
		}
		if (ProcessPackage.eINSTANCE.getSequenceFlow().isSuperTypeOf(domainElement.eClass())) {
			return SequenceFlowEditPart.VISUAL_ID;
		}
		if (ProcessPackage.eINSTANCE.getMessageFlow().isSuperTypeOf(domainElement.eClass())) {
			return MessageFlowEditPart.VISUAL_ID;
		}
		if (ProcessPackage.eINSTANCE.getTextAnnotationAttachment().isSuperTypeOf(domainElement.eClass())) {
			return TextAnnotationAttachmentEditPart.VISUAL_ID;
		}
		return -1;
	}

	/**
	* User can change implementation of this method to handle some specific
	* situations not covered by default logic.
	* 
	* @generated
	*/
	private static boolean isDiagram(MainProcess element) {
		return true;
	}

	/**
	* @generated
	*/
	public static boolean checkNodeVisualID(View containerView, EObject domainElement, int candidate) {
		if (candidate == -1) {
			//unrecognized id is always bad
			return false;
		}
		int basic = getNodeVisualID(containerView, domainElement);
		return basic == candidate;
	}

	/**
	* @generated
	*/
	public static boolean isCompartmentVisualID(int visualID) {
		switch (visualID) {
		case SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID:
		case PoolPoolCompartmentEditPart.VISUAL_ID:
		case LaneLaneCompartmentEditPart.VISUAL_ID:
		case SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID:
			return true;
		default:
			break;
		}
		return false;
	}

	/**
	* @generated
	*/
	public static boolean isSemanticLeafVisualID(int visualID) {
		switch (visualID) {
		case MainProcessEditPart.VISUAL_ID:
			return false;
		case StartEventEditPart.VISUAL_ID:
		case EndEventEditPart.VISUAL_ID:
		case XORGatewayEditPart.VISUAL_ID:
		case ANDGatewayEditPart.VISUAL_ID:
		case StartMessageEventEditPart.VISUAL_ID:
		case EndMessageEventEditPart.VISUAL_ID:
		case IntermediateCatchMessageEventEditPart.VISUAL_ID:
		case IntermediateThrowMessageEventEditPart.VISUAL_ID:
		case TextAnnotationEditPart.VISUAL_ID:
		case StartTimerEventEditPart.VISUAL_ID:
		case IntermediateCatchTimerEventEditPart.VISUAL_ID:
		case CatchLinkEventEditPart.VISUAL_ID:
		case ThrowLinkEventEditPart.VISUAL_ID:
		case IntermediateThrowSignalEventEditPart.VISUAL_ID:
		case IntermediateCatchSignalEventEditPart.VISUAL_ID:
		case StartSignalEventEditPart.VISUAL_ID:
		case EndSignalEventEditPart.VISUAL_ID:
		case EventEditPart.VISUAL_ID:
		case SendTaskEditPart.VISUAL_ID:
		case EndErrorEventEditPart.VISUAL_ID:
		case InclusiveGatewayEditPart.VISUAL_ID:
		case StartErrorEventEditPart.VISUAL_ID:
		case EndTerminatedEventEditPart.VISUAL_ID:
		case StartEvent2EditPart.VISUAL_ID:
		case EndEvent2EditPart.VISUAL_ID:
		case XORGateway2EditPart.VISUAL_ID:
		case ANDGateway2EditPart.VISUAL_ID:
		case EndMessageEvent2EditPart.VISUAL_ID:
		case StartMessageEvent2EditPart.VISUAL_ID:
		case IntermediateCatchMessageEvent2EditPart.VISUAL_ID:
		case IntermediateThrowMessageEvent2EditPart.VISUAL_ID:
		case TextAnnotation2EditPart.VISUAL_ID:
		case StartTimerEvent2EditPart.VISUAL_ID:
		case IntermediateCatchTimerEvent2EditPart.VISUAL_ID:
		case ThrowLinkEvent2EditPart.VISUAL_ID:
		case CatchLinkEvent2EditPart.VISUAL_ID:
		case EndSignalEvent2EditPart.VISUAL_ID:
		case IntermediateCatchSignalEvent2EditPart.VISUAL_ID:
		case IntermediateThrowSignalEvent2EditPart.VISUAL_ID:
		case StartSignalEvent2EditPart.VISUAL_ID:
		case Event2EditPart.VISUAL_ID:
		case SendTask2EditPart.VISUAL_ID:
		case IntermediateErrorCatchEvent2EditPart.VISUAL_ID:
		case IntermediateErrorCatchEventEditPart.VISUAL_ID:
		case IntermediateErrorCatchEvent3EditPart.VISUAL_ID:
		case IntermediateErrorCatchEvent4EditPart.VISUAL_ID:
		case IntermediateErrorCatchEvent5EditPart.VISUAL_ID:
		case IntermediateErrorCatchEvent6EditPart.VISUAL_ID:
		case BoundaryMessageEventEditPart.VISUAL_ID:
		case BoundaryMessageEvent2EditPart.VISUAL_ID:
		case BoundaryTimerEventEditPart.VISUAL_ID:
		case BoundaryTimerEvent2EditPart.VISUAL_ID:
		case EndErrorEvent2EditPart.VISUAL_ID:
		case InclusiveGateway2EditPart.VISUAL_ID:
		case BoundarySignalEventEditPart.VISUAL_ID:
		case BoundarySignalEvent2EditPart.VISUAL_ID:
		case StartErrorEvent2EditPart.VISUAL_ID:
		case EndTerminatedEvent2EditPart.VISUAL_ID:
		case NonInterruptingBoundaryTimerEventEditPart.VISUAL_ID:
		case NonInterruptingBoundaryTimerEvent2EditPart.VISUAL_ID:
			return true;
		default:
			break;
		}
		return false;
	}

	/**
	* @generated
	*/
	public static final DiagramStructure TYPED_INSTANCE = new DiagramStructure() {
		/**
		* @generated
		*/
		@Override

		public int getVisualID(View view) {
			return org.bonitasoft.studio.model.process.diagram.part.ProcessVisualIDRegistry.getVisualID(view);
		}

		/**
		* @generated
		*/
		@Override

		public String getModelID(View view) {
			return org.bonitasoft.studio.model.process.diagram.part.ProcessVisualIDRegistry.getModelID(view);
		}

		/**
		* @generated
		*/
		@Override

		public int getNodeVisualID(View containerView, EObject domainElement) {
			return org.bonitasoft.studio.model.process.diagram.part.ProcessVisualIDRegistry
					.getNodeVisualID(containerView, domainElement);
		}

		/**
		* @generated
		*/
		@Override

		public boolean checkNodeVisualID(View containerView, EObject domainElement, int candidate) {
			return org.bonitasoft.studio.model.process.diagram.part.ProcessVisualIDRegistry
					.checkNodeVisualID(containerView, domainElement, candidate);
		}

		/**
		* @generated
		*/
		@Override

		public boolean isCompartmentVisualID(int visualID) {
			return org.bonitasoft.studio.model.process.diagram.part.ProcessVisualIDRegistry
					.isCompartmentVisualID(visualID);
		}

		/**
		* @generated
		*/
		@Override

		public boolean isSemanticLeafVisualID(int visualID) {
			return org.bonitasoft.studio.model.process.diagram.part.ProcessVisualIDRegistry
					.isSemanticLeafVisualID(visualID);
		}
	};

}
