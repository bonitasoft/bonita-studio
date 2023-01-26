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

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.model.process.ANDGateway;
import org.bonitasoft.studio.model.process.AbstractCatchMessageEvent;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.BoundaryMessageEvent;
import org.bonitasoft.studio.model.process.BoundarySignalEvent;
import org.bonitasoft.studio.model.process.BoundaryTimerEvent;
import org.bonitasoft.studio.model.process.CallActivity;
import org.bonitasoft.studio.model.process.CatchLinkEvent;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.EndErrorEvent;
import org.bonitasoft.studio.model.process.EndEvent;
import org.bonitasoft.studio.model.process.EndMessageEvent;
import org.bonitasoft.studio.model.process.EndSignalEvent;
import org.bonitasoft.studio.model.process.EndTerminatedEvent;
import org.bonitasoft.studio.model.process.Event;
import org.bonitasoft.studio.model.process.InclusiveGateway;
import org.bonitasoft.studio.model.process.IntermediateCatchMessageEvent;
import org.bonitasoft.studio.model.process.IntermediateCatchSignalEvent;
import org.bonitasoft.studio.model.process.IntermediateCatchTimerEvent;
import org.bonitasoft.studio.model.process.IntermediateErrorCatchEvent;
import org.bonitasoft.studio.model.process.IntermediateThrowMessageEvent;
import org.bonitasoft.studio.model.process.IntermediateThrowSignalEvent;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.MessageFlow;
import org.bonitasoft.studio.model.process.NonInterruptingBoundaryTimerEvent;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.ReceiveTask;
import org.bonitasoft.studio.model.process.ScriptTask;
import org.bonitasoft.studio.model.process.SendTask;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.ServiceTask;
import org.bonitasoft.studio.model.process.SourceElement;
import org.bonitasoft.studio.model.process.StartErrorEvent;
import org.bonitasoft.studio.model.process.StartEvent;
import org.bonitasoft.studio.model.process.StartMessageEvent;
import org.bonitasoft.studio.model.process.StartSignalEvent;
import org.bonitasoft.studio.model.process.StartTimerEvent;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.model.process.TargetElement;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.TextAnnotation;
import org.bonitasoft.studio.model.process.TextAnnotationAttachment;
import org.bonitasoft.studio.model.process.ThrowLinkEvent;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;
import org.bonitasoft.studio.model.process.XORGateway;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ANDGateway2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ANDGatewayEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.Activity2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ActivityEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.BoundaryMessageEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.BoundaryMessageEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.BoundarySignalEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.BoundarySignalEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.BoundaryTimerEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.BoundaryTimerEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CallActivity2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CallActivityEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CatchLinkEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CatchLinkEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndErrorEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndErrorEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndMessageEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndMessageEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndSignalEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndSignalEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndTerminatedEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndTerminatedEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.Event2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.InclusiveGateway2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.InclusiveGatewayEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchMessageEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchMessageEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchSignalEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchSignalEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchTimerEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchTimerEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEvent3EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEvent4EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEvent5EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEvent6EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateThrowMessageEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateThrowMessageEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateThrowSignalEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateThrowSignalEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneLaneCompartmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MessageFlowEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.NonInterruptingBoundaryTimerEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.NonInterruptingBoundaryTimerEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolPoolCompartmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ReceiveTask2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ReceiveTaskEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ScriptTask2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ScriptTaskEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SendTask2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SendTaskEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SequenceFlowEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ServiceTask2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ServiceTaskEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartErrorEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartErrorEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartMessageEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartMessageEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartSignalEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartSignalEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartTimerEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartTimerEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEventSubProcessCompartment2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEventSubProcessCompartmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.Task2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TaskEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TextAnnotation2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TextAnnotationAttachmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TextAnnotationEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ThrowLinkEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ThrowLinkEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.XORGateway2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.XORGatewayEditPart;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.gmf.tooling.runtime.update.DiagramUpdater;

/**
 * @generated
 */
public class ProcessDiagramUpdater {

	/**
	* @generated
	*/
	public static List<ProcessNodeDescriptor> getSemanticChildren(View view) {
		switch (ProcessVisualIDRegistry.getVisualID(view)) {
		case MainProcessEditPart.VISUAL_ID:
			return getMainProcess_1000SemanticChildren(view);
		case TaskEditPart.VISUAL_ID:
			return getTask_2004SemanticChildren(view);
		case CallActivityEditPart.VISUAL_ID:
			return getCallActivity_2036SemanticChildren(view);
		case ReceiveTaskEditPart.VISUAL_ID:
			return getReceiveTask_2025SemanticChildren(view);
		case ServiceTaskEditPart.VISUAL_ID:
			return getServiceTask_2027SemanticChildren(view);
		case ScriptTaskEditPart.VISUAL_ID:
			return getScriptTask_2028SemanticChildren(view);
		case ActivityEditPart.VISUAL_ID:
			return getActivity_2006SemanticChildren(view);
		case CallActivity2EditPart.VISUAL_ID:
			return getCallActivity_3063SemanticChildren(view);
		case Task2EditPart.VISUAL_ID:
			return getTask_3005SemanticChildren(view);
		case ReceiveTask2EditPart.VISUAL_ID:
			return getReceiveTask_3026SemanticChildren(view);
		case ServiceTask2EditPart.VISUAL_ID:
			return getServiceTask_3027SemanticChildren(view);
		case ScriptTask2EditPart.VISUAL_ID:
			return getScriptTask_3028SemanticChildren(view);
		case Activity2EditPart.VISUAL_ID:
			return getActivity_3006SemanticChildren(view);
		case SubProcessEventSubProcessCompartmentEditPart.VISUAL_ID:
			return getSubProcessEventEventSubProcessCompartment_7005SemanticChildren(view);
		case PoolPoolCompartmentEditPart.VISUAL_ID:
			return getPoolPoolCompartment_7001SemanticChildren(view);
		case LaneLaneCompartmentEditPart.VISUAL_ID:
			return getLaneLaneCompartment_7002SemanticChildren(view);
		case SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID:
			return getSubProcessEventEventSubProcessCompartment_7006SemanticChildren(view);
		}
		return Collections.emptyList();
	}

	/**
	* @generated
	*/
	public static List<ProcessNodeDescriptor> getMainProcess_1000SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		MainProcess modelElement = (MainProcess) view.getElement();
		LinkedList<ProcessNodeDescriptor> result = new LinkedList<ProcessNodeDescriptor>();
		for (Iterator<?> it = modelElement.getElements().iterator(); it.hasNext();) {
			Element childElement = (Element) it.next();
			int visualID = ProcessVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == ANDGatewayEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == StartEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == TaskEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == CallActivityEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == SubProcessEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ReceiveTaskEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == SendTaskEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ServiceTaskEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ScriptTaskEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == XORGatewayEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == InclusiveGatewayEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ActivityEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == PoolEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == StartMessageEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndMessageEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateCatchMessageEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateThrowMessageEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == TextAnnotationEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateCatchTimerEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == StartTimerEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == CatchLinkEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ThrowLinkEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateThrowSignalEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateCatchSignalEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == StartSignalEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndSignalEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndErrorEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == StartErrorEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndTerminatedEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<ProcessNodeDescriptor> getTask_2004SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Task modelElement = (Task) view.getElement();
		LinkedList<ProcessNodeDescriptor> result = new LinkedList<ProcessNodeDescriptor>();
		for (Iterator<?> it = modelElement.getBoundaryIntermediateEvents().iterator(); it.hasNext();) {
			BoundaryEvent childElement = (BoundaryEvent) it.next();
			int visualID = ProcessVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == IntermediateErrorCatchEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BoundaryMessageEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == NonInterruptingBoundaryTimerEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BoundaryTimerEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BoundarySignalEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<ProcessNodeDescriptor> getCallActivity_2036SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		CallActivity modelElement = (CallActivity) view.getElement();
		LinkedList<ProcessNodeDescriptor> result = new LinkedList<ProcessNodeDescriptor>();
		for (Iterator<?> it = modelElement.getBoundaryIntermediateEvents().iterator(); it.hasNext();) {
			BoundaryEvent childElement = (BoundaryEvent) it.next();
			int visualID = ProcessVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == IntermediateErrorCatchEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BoundaryMessageEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == NonInterruptingBoundaryTimerEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BoundaryTimerEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BoundarySignalEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<ProcessNodeDescriptor> getReceiveTask_2025SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		ReceiveTask modelElement = (ReceiveTask) view.getElement();
		LinkedList<ProcessNodeDescriptor> result = new LinkedList<ProcessNodeDescriptor>();
		for (Iterator<?> it = modelElement.getBoundaryIntermediateEvents().iterator(); it.hasNext();) {
			BoundaryEvent childElement = (BoundaryEvent) it.next();
			int visualID = ProcessVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == IntermediateErrorCatchEvent3EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<ProcessNodeDescriptor> getServiceTask_2027SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		ServiceTask modelElement = (ServiceTask) view.getElement();
		LinkedList<ProcessNodeDescriptor> result = new LinkedList<ProcessNodeDescriptor>();
		for (Iterator<?> it = modelElement.getBoundaryIntermediateEvents().iterator(); it.hasNext();) {
			BoundaryEvent childElement = (BoundaryEvent) it.next();
			int visualID = ProcessVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == IntermediateErrorCatchEvent4EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<ProcessNodeDescriptor> getScriptTask_2028SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		ScriptTask modelElement = (ScriptTask) view.getElement();
		LinkedList<ProcessNodeDescriptor> result = new LinkedList<ProcessNodeDescriptor>();
		for (Iterator<?> it = modelElement.getBoundaryIntermediateEvents().iterator(); it.hasNext();) {
			BoundaryEvent childElement = (BoundaryEvent) it.next();
			int visualID = ProcessVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == IntermediateErrorCatchEvent5EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<ProcessNodeDescriptor> getActivity_2006SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Activity modelElement = (Activity) view.getElement();
		LinkedList<ProcessNodeDescriptor> result = new LinkedList<ProcessNodeDescriptor>();
		for (Iterator<?> it = modelElement.getBoundaryIntermediateEvents().iterator(); it.hasNext();) {
			BoundaryEvent childElement = (BoundaryEvent) it.next();
			int visualID = ProcessVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == IntermediateErrorCatchEvent6EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<ProcessNodeDescriptor> getCallActivity_3063SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		CallActivity modelElement = (CallActivity) view.getElement();
		LinkedList<ProcessNodeDescriptor> result = new LinkedList<ProcessNodeDescriptor>();
		for (Iterator<?> it = modelElement.getBoundaryIntermediateEvents().iterator(); it.hasNext();) {
			BoundaryEvent childElement = (BoundaryEvent) it.next();
			int visualID = ProcessVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == IntermediateErrorCatchEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BoundaryMessageEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == NonInterruptingBoundaryTimerEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BoundaryTimerEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BoundarySignalEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<ProcessNodeDescriptor> getTask_3005SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Task modelElement = (Task) view.getElement();
		LinkedList<ProcessNodeDescriptor> result = new LinkedList<ProcessNodeDescriptor>();
		for (Iterator<?> it = modelElement.getBoundaryIntermediateEvents().iterator(); it.hasNext();) {
			BoundaryEvent childElement = (BoundaryEvent) it.next();
			int visualID = ProcessVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == IntermediateErrorCatchEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BoundaryMessageEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == NonInterruptingBoundaryTimerEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BoundaryTimerEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == BoundarySignalEventEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<ProcessNodeDescriptor> getReceiveTask_3026SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		ReceiveTask modelElement = (ReceiveTask) view.getElement();
		LinkedList<ProcessNodeDescriptor> result = new LinkedList<ProcessNodeDescriptor>();
		for (Iterator<?> it = modelElement.getBoundaryIntermediateEvents().iterator(); it.hasNext();) {
			BoundaryEvent childElement = (BoundaryEvent) it.next();
			int visualID = ProcessVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == IntermediateErrorCatchEvent3EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<ProcessNodeDescriptor> getServiceTask_3027SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		ServiceTask modelElement = (ServiceTask) view.getElement();
		LinkedList<ProcessNodeDescriptor> result = new LinkedList<ProcessNodeDescriptor>();
		for (Iterator<?> it = modelElement.getBoundaryIntermediateEvents().iterator(); it.hasNext();) {
			BoundaryEvent childElement = (BoundaryEvent) it.next();
			int visualID = ProcessVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == IntermediateErrorCatchEvent4EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<ProcessNodeDescriptor> getScriptTask_3028SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		ScriptTask modelElement = (ScriptTask) view.getElement();
		LinkedList<ProcessNodeDescriptor> result = new LinkedList<ProcessNodeDescriptor>();
		for (Iterator<?> it = modelElement.getBoundaryIntermediateEvents().iterator(); it.hasNext();) {
			BoundaryEvent childElement = (BoundaryEvent) it.next();
			int visualID = ProcessVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == IntermediateErrorCatchEvent5EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<ProcessNodeDescriptor> getActivity_3006SemanticChildren(View view) {
		if (!view.isSetElement()) {
			return Collections.emptyList();
		}
		Activity modelElement = (Activity) view.getElement();
		LinkedList<ProcessNodeDescriptor> result = new LinkedList<ProcessNodeDescriptor>();
		for (Iterator<?> it = modelElement.getBoundaryIntermediateEvents().iterator(); it.hasNext();) {
			BoundaryEvent childElement = (BoundaryEvent) it.next();
			int visualID = ProcessVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == IntermediateErrorCatchEvent6EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<ProcessNodeDescriptor> getSubProcessEventEventSubProcessCompartment_7005SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		SubProcessEvent modelElement = (SubProcessEvent) containerView.getElement();
		LinkedList<ProcessNodeDescriptor> result = new LinkedList<ProcessNodeDescriptor>();
		for (Iterator<?> it = modelElement.getElements().iterator(); it.hasNext();) {
			Element childElement = (Element) it.next();
			int visualID = ProcessVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == ANDGateway2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == CallActivity2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Task2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ReceiveTask2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == SendTask2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ServiceTask2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ScriptTask2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == XORGateway2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Activity2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateCatchMessageEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == StartMessageEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndMessageEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateThrowMessageEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == TextAnnotation2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == StartTimerEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateCatchTimerEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == StartSignalEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateThrowSignalEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateCatchSignalEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndSignalEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndErrorEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndTerminatedEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == StartErrorEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Event2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == InclusiveGateway2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<ProcessNodeDescriptor> getPoolPoolCompartment_7001SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		Pool modelElement = (Pool) containerView.getElement();
		LinkedList<ProcessNodeDescriptor> result = new LinkedList<ProcessNodeDescriptor>();
		for (Iterator<?> it = modelElement.getElements().iterator(); it.hasNext();) {
			Element childElement = (Element) it.next();
			int visualID = ProcessVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == LaneEditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ANDGateway2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == StartEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == CallActivity2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == SubProcessEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Task2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ReceiveTask2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == SendTask2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ServiceTask2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ScriptTask2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == XORGateway2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Activity2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateCatchMessageEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == StartMessageEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndMessageEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateThrowMessageEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == TextAnnotation2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == StartTimerEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateCatchTimerEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == CatchLinkEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ThrowLinkEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == StartSignalEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateThrowSignalEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateCatchSignalEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndSignalEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndErrorEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndTerminatedEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Event2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == InclusiveGateway2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<ProcessNodeDescriptor> getLaneLaneCompartment_7002SemanticChildren(View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		Lane modelElement = (Lane) containerView.getElement();
		LinkedList<ProcessNodeDescriptor> result = new LinkedList<ProcessNodeDescriptor>();
		for (Iterator<?> it = modelElement.getElements().iterator(); it.hasNext();) {
			Element childElement = (Element) it.next();
			int visualID = ProcessVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == Task2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ANDGateway2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == StartEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == CallActivity2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == SubProcessEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == XORGateway2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == SendTask2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ReceiveTask2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ServiceTask2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ScriptTask2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Activity2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateCatchMessageEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndMessageEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == StartMessageEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateThrowMessageEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == TextAnnotation2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == StartTimerEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateCatchTimerEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ThrowLinkEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == CatchLinkEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndSignalEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateCatchSignalEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateThrowSignalEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == StartSignalEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndErrorEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndTerminatedEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Event2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == InclusiveGateway2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<ProcessNodeDescriptor> getSubProcessEventEventSubProcessCompartment_7006SemanticChildren(
			View view) {
		if (false == view.eContainer() instanceof View) {
			return Collections.emptyList();
		}
		View containerView = (View) view.eContainer();
		if (!containerView.isSetElement()) {
			return Collections.emptyList();
		}
		SubProcessEvent modelElement = (SubProcessEvent) containerView.getElement();
		LinkedList<ProcessNodeDescriptor> result = new LinkedList<ProcessNodeDescriptor>();
		for (Iterator<?> it = modelElement.getElements().iterator(); it.hasNext();) {
			Element childElement = (Element) it.next();
			int visualID = ProcessVisualIDRegistry.getNodeVisualID(view, childElement);
			if (visualID == ANDGateway2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == CallActivity2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Task2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ReceiveTask2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == SendTask2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ServiceTask2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == ScriptTask2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == XORGateway2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Activity2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateCatchMessageEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == StartMessageEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndMessageEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateThrowMessageEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == TextAnnotation2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == StartTimerEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateCatchTimerEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == StartSignalEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateThrowSignalEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == IntermediateCatchSignalEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndSignalEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndErrorEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == EndTerminatedEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == StartErrorEvent2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == Event2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
			if (visualID == InclusiveGateway2EditPart.VISUAL_ID) {
				result.add(new ProcessNodeDescriptor(childElement, visualID));
				continue;
			}
		}
		return result;
	}

	/**
	* @generated
	*/
	public static List<ProcessLinkDescriptor> getContainedLinks(View view) {
		switch (ProcessVisualIDRegistry.getVisualID(view)) {
		case MainProcessEditPart.VISUAL_ID:
			return getMainProcess_1000ContainedLinks(view);
		case ANDGatewayEditPart.VISUAL_ID:
			return getANDGateway_2009ContainedLinks(view);
		case StartEventEditPart.VISUAL_ID:
			return getStartEvent_2002ContainedLinks(view);
		case EndEventEditPart.VISUAL_ID:
			return getEndEvent_2003ContainedLinks(view);
		case TaskEditPart.VISUAL_ID:
			return getTask_2004ContainedLinks(view);
		case CallActivityEditPart.VISUAL_ID:
			return getCallActivity_2036ContainedLinks(view);
		case SubProcessEventEditPart.VISUAL_ID:
			return getSubProcessEvent_2031ContainedLinks(view);
		case ReceiveTaskEditPart.VISUAL_ID:
			return getReceiveTask_2025ContainedLinks(view);
		case SendTaskEditPart.VISUAL_ID:
			return getSendTask_2026ContainedLinks(view);
		case ServiceTaskEditPart.VISUAL_ID:
			return getServiceTask_2027ContainedLinks(view);
		case ScriptTaskEditPart.VISUAL_ID:
			return getScriptTask_2028ContainedLinks(view);
		case XORGatewayEditPart.VISUAL_ID:
			return getXORGateway_2008ContainedLinks(view);
		case InclusiveGatewayEditPart.VISUAL_ID:
			return getInclusiveGateway_2030ContainedLinks(view);
		case ActivityEditPart.VISUAL_ID:
			return getActivity_2006ContainedLinks(view);
		case PoolEditPart.VISUAL_ID:
			return getPool_2007ContainedLinks(view);
		case StartMessageEventEditPart.VISUAL_ID:
			return getStartMessageEvent_2010ContainedLinks(view);
		case EndMessageEventEditPart.VISUAL_ID:
			return getEndMessageEvent_2011ContainedLinks(view);
		case IntermediateCatchMessageEventEditPart.VISUAL_ID:
			return getIntermediateCatchMessageEvent_2013ContainedLinks(view);
		case IntermediateThrowMessageEventEditPart.VISUAL_ID:
			return getIntermediateThrowMessageEvent_2014ContainedLinks(view);
		case TextAnnotationEditPart.VISUAL_ID:
			return getTextAnnotation_2015ContainedLinks(view);
		case IntermediateCatchTimerEventEditPart.VISUAL_ID:
			return getIntermediateCatchTimerEvent_2017ContainedLinks(view);
		case StartTimerEventEditPart.VISUAL_ID:
			return getStartTimerEvent_2016ContainedLinks(view);
		case CatchLinkEventEditPart.VISUAL_ID:
			return getCatchLinkEvent_2018ContainedLinks(view);
		case ThrowLinkEventEditPart.VISUAL_ID:
			return getThrowLinkEvent_2019ContainedLinks(view);
		case IntermediateThrowSignalEventEditPart.VISUAL_ID:
			return getIntermediateThrowSignalEvent_2020ContainedLinks(view);
		case IntermediateCatchSignalEventEditPart.VISUAL_ID:
			return getIntermediateCatchSignalEvent_2021ContainedLinks(view);
		case StartSignalEventEditPart.VISUAL_ID:
			return getStartSignalEvent_2022ContainedLinks(view);
		case EndSignalEventEditPart.VISUAL_ID:
			return getEndSignalEvent_2023ContainedLinks(view);
		case EventEditPart.VISUAL_ID:
			return getEvent_2024ContainedLinks(view);
		case EndErrorEventEditPart.VISUAL_ID:
			return getEndErrorEvent_2029ContainedLinks(view);
		case StartErrorEventEditPart.VISUAL_ID:
			return getStartErrorEvent_2033ContainedLinks(view);
		case EndTerminatedEventEditPart.VISUAL_ID:
			return getEndTerminatedEvent_2035ContainedLinks(view);
		case IntermediateErrorCatchEvent2EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3029ContainedLinks(view);
		case BoundaryMessageEventEditPart.VISUAL_ID:
			return getBoundaryMessageEvent_3035ContainedLinks(view);
		case NonInterruptingBoundaryTimerEventEditPart.VISUAL_ID:
			return getNonInterruptingBoundaryTimerEvent_3064ContainedLinks(view);
		case BoundaryTimerEventEditPart.VISUAL_ID:
			return getBoundaryTimerEvent_3043ContainedLinks(view);
		case BoundarySignalEventEditPart.VISUAL_ID:
			return getBoundarySignalEvent_3052ContainedLinks(view);
		case IntermediateErrorCatchEventEditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3030ContainedLinks(view);
		case BoundaryMessageEvent2EditPart.VISUAL_ID:
			return getBoundaryMessageEvent_3036ContainedLinks(view);
		case NonInterruptingBoundaryTimerEvent2EditPart.VISUAL_ID:
			return getNonInterruptingBoundaryTimerEvent_3065ContainedLinks(view);
		case BoundaryTimerEvent2EditPart.VISUAL_ID:
			return getBoundaryTimerEvent_3044ContainedLinks(view);
		case BoundarySignalEvent2EditPart.VISUAL_ID:
			return getBoundarySignalEvent_3053ContainedLinks(view);
		case ANDGateway2EditPart.VISUAL_ID:
			return getANDGateway_3009ContainedLinks(view);
		case EndEvent2EditPart.VISUAL_ID:
			return getEndEvent_3003ContainedLinks(view);
		case CallActivity2EditPart.VISUAL_ID:
			return getCallActivity_3063ContainedLinks(view);
		case Task2EditPart.VISUAL_ID:
			return getTask_3005ContainedLinks(view);
		case ReceiveTask2EditPart.VISUAL_ID:
			return getReceiveTask_3026ContainedLinks(view);
		case IntermediateErrorCatchEvent3EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3031ContainedLinks(view);
		case SendTask2EditPart.VISUAL_ID:
			return getSendTask_3025ContainedLinks(view);
		case ServiceTask2EditPart.VISUAL_ID:
			return getServiceTask_3027ContainedLinks(view);
		case IntermediateErrorCatchEvent4EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3032ContainedLinks(view);
		case ScriptTask2EditPart.VISUAL_ID:
			return getScriptTask_3028ContainedLinks(view);
		case IntermediateErrorCatchEvent5EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3033ContainedLinks(view);
		case XORGateway2EditPart.VISUAL_ID:
			return getXORGateway_3008ContainedLinks(view);
		case Activity2EditPart.VISUAL_ID:
			return getActivity_3006ContainedLinks(view);
		case IntermediateErrorCatchEvent6EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3034ContainedLinks(view);
		case IntermediateCatchMessageEvent2EditPart.VISUAL_ID:
			return getIntermediateCatchMessageEvent_3013ContainedLinks(view);
		case StartMessageEvent2EditPart.VISUAL_ID:
			return getStartMessageEvent_3012ContainedLinks(view);
		case EndMessageEvent2EditPart.VISUAL_ID:
			return getEndMessageEvent_3011ContainedLinks(view);
		case IntermediateThrowMessageEvent2EditPart.VISUAL_ID:
			return getIntermediateThrowMessageEvent_3014ContainedLinks(view);
		case TextAnnotation2EditPart.VISUAL_ID:
			return getTextAnnotation_3015ContainedLinks(view);
		case StartTimerEvent2EditPart.VISUAL_ID:
			return getStartTimerEvent_3016ContainedLinks(view);
		case IntermediateCatchTimerEvent2EditPart.VISUAL_ID:
			return getIntermediateCatchTimerEvent_3017ContainedLinks(view);
		case StartSignalEvent2EditPart.VISUAL_ID:
			return getStartSignalEvent_3023ContainedLinks(view);
		case IntermediateThrowSignalEvent2EditPart.VISUAL_ID:
			return getIntermediateThrowSignalEvent_3022ContainedLinks(view);
		case IntermediateCatchSignalEvent2EditPart.VISUAL_ID:
			return getIntermediateCatchSignalEvent_3021ContainedLinks(view);
		case EndSignalEvent2EditPart.VISUAL_ID:
			return getEndSignalEvent_3020ContainedLinks(view);
		case EndErrorEvent2EditPart.VISUAL_ID:
			return getEndErrorEvent_3050ContainedLinks(view);
		case EndTerminatedEvent2EditPart.VISUAL_ID:
			return getEndTerminatedEvent_3062ContainedLinks(view);
		case StartErrorEvent2EditPart.VISUAL_ID:
			return getStartErrorEvent_3060ContainedLinks(view);
		case Event2EditPart.VISUAL_ID:
			return getEvent_3024ContainedLinks(view);
		case InclusiveGateway2EditPart.VISUAL_ID:
			return getInclusiveGateway_3051ContainedLinks(view);
		case LaneEditPart.VISUAL_ID:
			return getLane_3007ContainedLinks(view);
		case StartEvent2EditPart.VISUAL_ID:
			return getStartEvent_3002ContainedLinks(view);
		case SubProcessEvent2EditPart.VISUAL_ID:
			return getSubProcessEvent_3058ContainedLinks(view);
		case ThrowLinkEvent2EditPart.VISUAL_ID:
			return getThrowLinkEvent_3018ContainedLinks(view);
		case CatchLinkEvent2EditPart.VISUAL_ID:
			return getCatchLinkEvent_3019ContainedLinks(view);
		case SequenceFlowEditPart.VISUAL_ID:
			return getSequenceFlow_4001ContainedLinks(view);
		case MessageFlowEditPart.VISUAL_ID:
			return getMessageFlow_4002ContainedLinks(view);
		case TextAnnotationAttachmentEditPart.VISUAL_ID:
			return getTextAnnotationAttachment_4003ContainedLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	* @generated
	*/
	public static List<ProcessLinkDescriptor> getIncomingLinks(View view) {
		switch (ProcessVisualIDRegistry.getVisualID(view)) {
		case ANDGatewayEditPart.VISUAL_ID:
			return getANDGateway_2009IncomingLinks(view);
		case StartEventEditPart.VISUAL_ID:
			return getStartEvent_2002IncomingLinks(view);
		case EndEventEditPart.VISUAL_ID:
			return getEndEvent_2003IncomingLinks(view);
		case TaskEditPart.VISUAL_ID:
			return getTask_2004IncomingLinks(view);
		case CallActivityEditPart.VISUAL_ID:
			return getCallActivity_2036IncomingLinks(view);
		case SubProcessEventEditPart.VISUAL_ID:
			return getSubProcessEvent_2031IncomingLinks(view);
		case ReceiveTaskEditPart.VISUAL_ID:
			return getReceiveTask_2025IncomingLinks(view);
		case SendTaskEditPart.VISUAL_ID:
			return getSendTask_2026IncomingLinks(view);
		case ServiceTaskEditPart.VISUAL_ID:
			return getServiceTask_2027IncomingLinks(view);
		case ScriptTaskEditPart.VISUAL_ID:
			return getScriptTask_2028IncomingLinks(view);
		case XORGatewayEditPart.VISUAL_ID:
			return getXORGateway_2008IncomingLinks(view);
		case InclusiveGatewayEditPart.VISUAL_ID:
			return getInclusiveGateway_2030IncomingLinks(view);
		case ActivityEditPart.VISUAL_ID:
			return getActivity_2006IncomingLinks(view);
		case PoolEditPart.VISUAL_ID:
			return getPool_2007IncomingLinks(view);
		case StartMessageEventEditPart.VISUAL_ID:
			return getStartMessageEvent_2010IncomingLinks(view);
		case EndMessageEventEditPart.VISUAL_ID:
			return getEndMessageEvent_2011IncomingLinks(view);
		case IntermediateCatchMessageEventEditPart.VISUAL_ID:
			return getIntermediateCatchMessageEvent_2013IncomingLinks(view);
		case IntermediateThrowMessageEventEditPart.VISUAL_ID:
			return getIntermediateThrowMessageEvent_2014IncomingLinks(view);
		case TextAnnotationEditPart.VISUAL_ID:
			return getTextAnnotation_2015IncomingLinks(view);
		case IntermediateCatchTimerEventEditPart.VISUAL_ID:
			return getIntermediateCatchTimerEvent_2017IncomingLinks(view);
		case StartTimerEventEditPart.VISUAL_ID:
			return getStartTimerEvent_2016IncomingLinks(view);
		case CatchLinkEventEditPart.VISUAL_ID:
			return getCatchLinkEvent_2018IncomingLinks(view);
		case ThrowLinkEventEditPart.VISUAL_ID:
			return getThrowLinkEvent_2019IncomingLinks(view);
		case IntermediateThrowSignalEventEditPart.VISUAL_ID:
			return getIntermediateThrowSignalEvent_2020IncomingLinks(view);
		case IntermediateCatchSignalEventEditPart.VISUAL_ID:
			return getIntermediateCatchSignalEvent_2021IncomingLinks(view);
		case StartSignalEventEditPart.VISUAL_ID:
			return getStartSignalEvent_2022IncomingLinks(view);
		case EndSignalEventEditPart.VISUAL_ID:
			return getEndSignalEvent_2023IncomingLinks(view);
		case EventEditPart.VISUAL_ID:
			return getEvent_2024IncomingLinks(view);
		case EndErrorEventEditPart.VISUAL_ID:
			return getEndErrorEvent_2029IncomingLinks(view);
		case StartErrorEventEditPart.VISUAL_ID:
			return getStartErrorEvent_2033IncomingLinks(view);
		case EndTerminatedEventEditPart.VISUAL_ID:
			return getEndTerminatedEvent_2035IncomingLinks(view);
		case IntermediateErrorCatchEvent2EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3029IncomingLinks(view);
		case BoundaryMessageEventEditPart.VISUAL_ID:
			return getBoundaryMessageEvent_3035IncomingLinks(view);
		case NonInterruptingBoundaryTimerEventEditPart.VISUAL_ID:
			return getNonInterruptingBoundaryTimerEvent_3064IncomingLinks(view);
		case BoundaryTimerEventEditPart.VISUAL_ID:
			return getBoundaryTimerEvent_3043IncomingLinks(view);
		case BoundarySignalEventEditPart.VISUAL_ID:
			return getBoundarySignalEvent_3052IncomingLinks(view);
		case IntermediateErrorCatchEventEditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3030IncomingLinks(view);
		case BoundaryMessageEvent2EditPart.VISUAL_ID:
			return getBoundaryMessageEvent_3036IncomingLinks(view);
		case NonInterruptingBoundaryTimerEvent2EditPart.VISUAL_ID:
			return getNonInterruptingBoundaryTimerEvent_3065IncomingLinks(view);
		case BoundaryTimerEvent2EditPart.VISUAL_ID:
			return getBoundaryTimerEvent_3044IncomingLinks(view);
		case BoundarySignalEvent2EditPart.VISUAL_ID:
			return getBoundarySignalEvent_3053IncomingLinks(view);
		case ANDGateway2EditPart.VISUAL_ID:
			return getANDGateway_3009IncomingLinks(view);
		case EndEvent2EditPart.VISUAL_ID:
			return getEndEvent_3003IncomingLinks(view);
		case CallActivity2EditPart.VISUAL_ID:
			return getCallActivity_3063IncomingLinks(view);
		case Task2EditPart.VISUAL_ID:
			return getTask_3005IncomingLinks(view);
		case ReceiveTask2EditPart.VISUAL_ID:
			return getReceiveTask_3026IncomingLinks(view);
		case IntermediateErrorCatchEvent3EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3031IncomingLinks(view);
		case SendTask2EditPart.VISUAL_ID:
			return getSendTask_3025IncomingLinks(view);
		case ServiceTask2EditPart.VISUAL_ID:
			return getServiceTask_3027IncomingLinks(view);
		case IntermediateErrorCatchEvent4EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3032IncomingLinks(view);
		case ScriptTask2EditPart.VISUAL_ID:
			return getScriptTask_3028IncomingLinks(view);
		case IntermediateErrorCatchEvent5EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3033IncomingLinks(view);
		case XORGateway2EditPart.VISUAL_ID:
			return getXORGateway_3008IncomingLinks(view);
		case Activity2EditPart.VISUAL_ID:
			return getActivity_3006IncomingLinks(view);
		case IntermediateErrorCatchEvent6EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3034IncomingLinks(view);
		case IntermediateCatchMessageEvent2EditPart.VISUAL_ID:
			return getIntermediateCatchMessageEvent_3013IncomingLinks(view);
		case StartMessageEvent2EditPart.VISUAL_ID:
			return getStartMessageEvent_3012IncomingLinks(view);
		case EndMessageEvent2EditPart.VISUAL_ID:
			return getEndMessageEvent_3011IncomingLinks(view);
		case IntermediateThrowMessageEvent2EditPart.VISUAL_ID:
			return getIntermediateThrowMessageEvent_3014IncomingLinks(view);
		case TextAnnotation2EditPart.VISUAL_ID:
			return getTextAnnotation_3015IncomingLinks(view);
		case StartTimerEvent2EditPart.VISUAL_ID:
			return getStartTimerEvent_3016IncomingLinks(view);
		case IntermediateCatchTimerEvent2EditPart.VISUAL_ID:
			return getIntermediateCatchTimerEvent_3017IncomingLinks(view);
		case StartSignalEvent2EditPart.VISUAL_ID:
			return getStartSignalEvent_3023IncomingLinks(view);
		case IntermediateThrowSignalEvent2EditPart.VISUAL_ID:
			return getIntermediateThrowSignalEvent_3022IncomingLinks(view);
		case IntermediateCatchSignalEvent2EditPart.VISUAL_ID:
			return getIntermediateCatchSignalEvent_3021IncomingLinks(view);
		case EndSignalEvent2EditPart.VISUAL_ID:
			return getEndSignalEvent_3020IncomingLinks(view);
		case EndErrorEvent2EditPart.VISUAL_ID:
			return getEndErrorEvent_3050IncomingLinks(view);
		case EndTerminatedEvent2EditPart.VISUAL_ID:
			return getEndTerminatedEvent_3062IncomingLinks(view);
		case StartErrorEvent2EditPart.VISUAL_ID:
			return getStartErrorEvent_3060IncomingLinks(view);
		case Event2EditPart.VISUAL_ID:
			return getEvent_3024IncomingLinks(view);
		case InclusiveGateway2EditPart.VISUAL_ID:
			return getInclusiveGateway_3051IncomingLinks(view);
		case LaneEditPart.VISUAL_ID:
			return getLane_3007IncomingLinks(view);
		case StartEvent2EditPart.VISUAL_ID:
			return getStartEvent_3002IncomingLinks(view);
		case SubProcessEvent2EditPart.VISUAL_ID:
			return getSubProcessEvent_3058IncomingLinks(view);
		case ThrowLinkEvent2EditPart.VISUAL_ID:
			return getThrowLinkEvent_3018IncomingLinks(view);
		case CatchLinkEvent2EditPart.VISUAL_ID:
			return getCatchLinkEvent_3019IncomingLinks(view);
		case SequenceFlowEditPart.VISUAL_ID:
			return getSequenceFlow_4001IncomingLinks(view);
		case MessageFlowEditPart.VISUAL_ID:
			return getMessageFlow_4002IncomingLinks(view);
		case TextAnnotationAttachmentEditPart.VISUAL_ID:
			return getTextAnnotationAttachment_4003IncomingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	* @generated
	*/
	public static List<ProcessLinkDescriptor> getOutgoingLinks(View view) {
		switch (ProcessVisualIDRegistry.getVisualID(view)) {
		case ANDGatewayEditPart.VISUAL_ID:
			return getANDGateway_2009OutgoingLinks(view);
		case StartEventEditPart.VISUAL_ID:
			return getStartEvent_2002OutgoingLinks(view);
		case EndEventEditPart.VISUAL_ID:
			return getEndEvent_2003OutgoingLinks(view);
		case TaskEditPart.VISUAL_ID:
			return getTask_2004OutgoingLinks(view);
		case CallActivityEditPart.VISUAL_ID:
			return getCallActivity_2036OutgoingLinks(view);
		case SubProcessEventEditPart.VISUAL_ID:
			return getSubProcessEvent_2031OutgoingLinks(view);
		case ReceiveTaskEditPart.VISUAL_ID:
			return getReceiveTask_2025OutgoingLinks(view);
		case SendTaskEditPart.VISUAL_ID:
			return getSendTask_2026OutgoingLinks(view);
		case ServiceTaskEditPart.VISUAL_ID:
			return getServiceTask_2027OutgoingLinks(view);
		case ScriptTaskEditPart.VISUAL_ID:
			return getScriptTask_2028OutgoingLinks(view);
		case XORGatewayEditPart.VISUAL_ID:
			return getXORGateway_2008OutgoingLinks(view);
		case InclusiveGatewayEditPart.VISUAL_ID:
			return getInclusiveGateway_2030OutgoingLinks(view);
		case ActivityEditPart.VISUAL_ID:
			return getActivity_2006OutgoingLinks(view);
		case PoolEditPart.VISUAL_ID:
			return getPool_2007OutgoingLinks(view);
		case StartMessageEventEditPart.VISUAL_ID:
			return getStartMessageEvent_2010OutgoingLinks(view);
		case EndMessageEventEditPart.VISUAL_ID:
			return getEndMessageEvent_2011OutgoingLinks(view);
		case IntermediateCatchMessageEventEditPart.VISUAL_ID:
			return getIntermediateCatchMessageEvent_2013OutgoingLinks(view);
		case IntermediateThrowMessageEventEditPart.VISUAL_ID:
			return getIntermediateThrowMessageEvent_2014OutgoingLinks(view);
		case TextAnnotationEditPart.VISUAL_ID:
			return getTextAnnotation_2015OutgoingLinks(view);
		case IntermediateCatchTimerEventEditPart.VISUAL_ID:
			return getIntermediateCatchTimerEvent_2017OutgoingLinks(view);
		case StartTimerEventEditPart.VISUAL_ID:
			return getStartTimerEvent_2016OutgoingLinks(view);
		case CatchLinkEventEditPart.VISUAL_ID:
			return getCatchLinkEvent_2018OutgoingLinks(view);
		case ThrowLinkEventEditPart.VISUAL_ID:
			return getThrowLinkEvent_2019OutgoingLinks(view);
		case IntermediateThrowSignalEventEditPart.VISUAL_ID:
			return getIntermediateThrowSignalEvent_2020OutgoingLinks(view);
		case IntermediateCatchSignalEventEditPart.VISUAL_ID:
			return getIntermediateCatchSignalEvent_2021OutgoingLinks(view);
		case StartSignalEventEditPart.VISUAL_ID:
			return getStartSignalEvent_2022OutgoingLinks(view);
		case EndSignalEventEditPart.VISUAL_ID:
			return getEndSignalEvent_2023OutgoingLinks(view);
		case EventEditPart.VISUAL_ID:
			return getEvent_2024OutgoingLinks(view);
		case EndErrorEventEditPart.VISUAL_ID:
			return getEndErrorEvent_2029OutgoingLinks(view);
		case StartErrorEventEditPart.VISUAL_ID:
			return getStartErrorEvent_2033OutgoingLinks(view);
		case EndTerminatedEventEditPart.VISUAL_ID:
			return getEndTerminatedEvent_2035OutgoingLinks(view);
		case IntermediateErrorCatchEvent2EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3029OutgoingLinks(view);
		case BoundaryMessageEventEditPart.VISUAL_ID:
			return getBoundaryMessageEvent_3035OutgoingLinks(view);
		case NonInterruptingBoundaryTimerEventEditPart.VISUAL_ID:
			return getNonInterruptingBoundaryTimerEvent_3064OutgoingLinks(view);
		case BoundaryTimerEventEditPart.VISUAL_ID:
			return getBoundaryTimerEvent_3043OutgoingLinks(view);
		case BoundarySignalEventEditPart.VISUAL_ID:
			return getBoundarySignalEvent_3052OutgoingLinks(view);
		case IntermediateErrorCatchEventEditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3030OutgoingLinks(view);
		case BoundaryMessageEvent2EditPart.VISUAL_ID:
			return getBoundaryMessageEvent_3036OutgoingLinks(view);
		case NonInterruptingBoundaryTimerEvent2EditPart.VISUAL_ID:
			return getNonInterruptingBoundaryTimerEvent_3065OutgoingLinks(view);
		case BoundaryTimerEvent2EditPart.VISUAL_ID:
			return getBoundaryTimerEvent_3044OutgoingLinks(view);
		case BoundarySignalEvent2EditPart.VISUAL_ID:
			return getBoundarySignalEvent_3053OutgoingLinks(view);
		case ANDGateway2EditPart.VISUAL_ID:
			return getANDGateway_3009OutgoingLinks(view);
		case EndEvent2EditPart.VISUAL_ID:
			return getEndEvent_3003OutgoingLinks(view);
		case CallActivity2EditPart.VISUAL_ID:
			return getCallActivity_3063OutgoingLinks(view);
		case Task2EditPart.VISUAL_ID:
			return getTask_3005OutgoingLinks(view);
		case ReceiveTask2EditPart.VISUAL_ID:
			return getReceiveTask_3026OutgoingLinks(view);
		case IntermediateErrorCatchEvent3EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3031OutgoingLinks(view);
		case SendTask2EditPart.VISUAL_ID:
			return getSendTask_3025OutgoingLinks(view);
		case ServiceTask2EditPart.VISUAL_ID:
			return getServiceTask_3027OutgoingLinks(view);
		case IntermediateErrorCatchEvent4EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3032OutgoingLinks(view);
		case ScriptTask2EditPart.VISUAL_ID:
			return getScriptTask_3028OutgoingLinks(view);
		case IntermediateErrorCatchEvent5EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3033OutgoingLinks(view);
		case XORGateway2EditPart.VISUAL_ID:
			return getXORGateway_3008OutgoingLinks(view);
		case Activity2EditPart.VISUAL_ID:
			return getActivity_3006OutgoingLinks(view);
		case IntermediateErrorCatchEvent6EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3034OutgoingLinks(view);
		case IntermediateCatchMessageEvent2EditPart.VISUAL_ID:
			return getIntermediateCatchMessageEvent_3013OutgoingLinks(view);
		case StartMessageEvent2EditPart.VISUAL_ID:
			return getStartMessageEvent_3012OutgoingLinks(view);
		case EndMessageEvent2EditPart.VISUAL_ID:
			return getEndMessageEvent_3011OutgoingLinks(view);
		case IntermediateThrowMessageEvent2EditPart.VISUAL_ID:
			return getIntermediateThrowMessageEvent_3014OutgoingLinks(view);
		case TextAnnotation2EditPart.VISUAL_ID:
			return getTextAnnotation_3015OutgoingLinks(view);
		case StartTimerEvent2EditPart.VISUAL_ID:
			return getStartTimerEvent_3016OutgoingLinks(view);
		case IntermediateCatchTimerEvent2EditPart.VISUAL_ID:
			return getIntermediateCatchTimerEvent_3017OutgoingLinks(view);
		case StartSignalEvent2EditPart.VISUAL_ID:
			return getStartSignalEvent_3023OutgoingLinks(view);
		case IntermediateThrowSignalEvent2EditPart.VISUAL_ID:
			return getIntermediateThrowSignalEvent_3022OutgoingLinks(view);
		case IntermediateCatchSignalEvent2EditPart.VISUAL_ID:
			return getIntermediateCatchSignalEvent_3021OutgoingLinks(view);
		case EndSignalEvent2EditPart.VISUAL_ID:
			return getEndSignalEvent_3020OutgoingLinks(view);
		case EndErrorEvent2EditPart.VISUAL_ID:
			return getEndErrorEvent_3050OutgoingLinks(view);
		case EndTerminatedEvent2EditPart.VISUAL_ID:
			return getEndTerminatedEvent_3062OutgoingLinks(view);
		case StartErrorEvent2EditPart.VISUAL_ID:
			return getStartErrorEvent_3060OutgoingLinks(view);
		case Event2EditPart.VISUAL_ID:
			return getEvent_3024OutgoingLinks(view);
		case InclusiveGateway2EditPart.VISUAL_ID:
			return getInclusiveGateway_3051OutgoingLinks(view);
		case LaneEditPart.VISUAL_ID:
			return getLane_3007OutgoingLinks(view);
		case StartEvent2EditPart.VISUAL_ID:
			return getStartEvent_3002OutgoingLinks(view);
		case SubProcessEvent2EditPart.VISUAL_ID:
			return getSubProcessEvent_3058OutgoingLinks(view);
		case ThrowLinkEvent2EditPart.VISUAL_ID:
			return getThrowLinkEvent_3018OutgoingLinks(view);
		case CatchLinkEvent2EditPart.VISUAL_ID:
			return getCatchLinkEvent_3019OutgoingLinks(view);
		case SequenceFlowEditPart.VISUAL_ID:
			return getSequenceFlow_4001OutgoingLinks(view);
		case MessageFlowEditPart.VISUAL_ID:
			return getMessageFlow_4002OutgoingLinks(view);
		case TextAnnotationAttachmentEditPart.VISUAL_ID:
			return getTextAnnotationAttachment_4003OutgoingLinks(view);
		}
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getMainProcess_1000ContainedLinks(View view) {
		MainProcess modelElement = (MainProcess) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_MessageFlow_4002(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getANDGateway_2009ContainedLinks(View view) {
		ANDGateway modelElement = (ANDGateway) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartEvent_2002ContainedLinks(View view) {
		StartEvent modelElement = (StartEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndEvent_2003ContainedLinks(View view) {
		EndEvent modelElement = (EndEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTask_2004ContainedLinks(View view) {
		Task modelElement = (Task) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCallActivity_2036ContainedLinks(View view) {
		CallActivity modelElement = (CallActivity) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSubProcessEvent_2031ContainedLinks(View view) {
		SubProcessEvent modelElement = (SubProcessEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getReceiveTask_2025ContainedLinks(View view) {
		ReceiveTask modelElement = (ReceiveTask) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSendTask_2026ContainedLinks(View view) {
		SendTask modelElement = (SendTask) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getServiceTask_2027ContainedLinks(View view) {
		ServiceTask modelElement = (ServiceTask) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getScriptTask_2028ContainedLinks(View view) {
		ScriptTask modelElement = (ScriptTask) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getXORGateway_2008ContainedLinks(View view) {
		XORGateway modelElement = (XORGateway) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getInclusiveGateway_2030ContainedLinks(View view) {
		InclusiveGateway modelElement = (InclusiveGateway) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getActivity_2006ContainedLinks(View view) {
		Activity modelElement = (Activity) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getPool_2007ContainedLinks(View view) {
		Pool modelElement = (Pool) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartMessageEvent_2010ContainedLinks(View view) {
		StartMessageEvent modelElement = (StartMessageEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndMessageEvent_2011ContainedLinks(View view) {
		EndMessageEvent modelElement = (EndMessageEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateCatchMessageEvent_2013ContainedLinks(View view) {
		IntermediateCatchMessageEvent modelElement = (IntermediateCatchMessageEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateThrowMessageEvent_2014ContainedLinks(View view) {
		IntermediateThrowMessageEvent modelElement = (IntermediateThrowMessageEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextAnnotation_2015ContainedLinks(View view) {
		TextAnnotation modelElement = (TextAnnotation) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateCatchTimerEvent_2017ContainedLinks(View view) {
		IntermediateCatchTimerEvent modelElement = (IntermediateCatchTimerEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartTimerEvent_2016ContainedLinks(View view) {
		StartTimerEvent modelElement = (StartTimerEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCatchLinkEvent_2018ContainedLinks(View view) {
		CatchLinkEvent modelElement = (CatchLinkEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getThrowLinkEvent_2019ContainedLinks(View view) {
		ThrowLinkEvent modelElement = (ThrowLinkEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateThrowSignalEvent_2020ContainedLinks(View view) {
		IntermediateThrowSignalEvent modelElement = (IntermediateThrowSignalEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateCatchSignalEvent_2021ContainedLinks(View view) {
		IntermediateCatchSignalEvent modelElement = (IntermediateCatchSignalEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartSignalEvent_2022ContainedLinks(View view) {
		StartSignalEvent modelElement = (StartSignalEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndSignalEvent_2023ContainedLinks(View view) {
		EndSignalEvent modelElement = (EndSignalEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEvent_2024ContainedLinks(View view) {
		Event modelElement = (Event) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndErrorEvent_2029ContainedLinks(View view) {
		EndErrorEvent modelElement = (EndErrorEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartErrorEvent_2033ContainedLinks(View view) {
		StartErrorEvent modelElement = (StartErrorEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndTerminatedEvent_2035ContainedLinks(View view) {
		EndTerminatedEvent modelElement = (EndTerminatedEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateErrorCatchEvent_3029ContainedLinks(View view) {
		IntermediateErrorCatchEvent modelElement = (IntermediateErrorCatchEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getBoundaryMessageEvent_3035ContainedLinks(View view) {
		BoundaryMessageEvent modelElement = (BoundaryMessageEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getNonInterruptingBoundaryTimerEvent_3064ContainedLinks(View view) {
		NonInterruptingBoundaryTimerEvent modelElement = (NonInterruptingBoundaryTimerEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getBoundaryTimerEvent_3043ContainedLinks(View view) {
		BoundaryTimerEvent modelElement = (BoundaryTimerEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getBoundarySignalEvent_3052ContainedLinks(View view) {
		BoundarySignalEvent modelElement = (BoundarySignalEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateErrorCatchEvent_3030ContainedLinks(View view) {
		IntermediateErrorCatchEvent modelElement = (IntermediateErrorCatchEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getBoundaryMessageEvent_3036ContainedLinks(View view) {
		BoundaryMessageEvent modelElement = (BoundaryMessageEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getNonInterruptingBoundaryTimerEvent_3065ContainedLinks(View view) {
		NonInterruptingBoundaryTimerEvent modelElement = (NonInterruptingBoundaryTimerEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getBoundaryTimerEvent_3044ContainedLinks(View view) {
		BoundaryTimerEvent modelElement = (BoundaryTimerEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getBoundarySignalEvent_3053ContainedLinks(View view) {
		BoundarySignalEvent modelElement = (BoundarySignalEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getANDGateway_3009ContainedLinks(View view) {
		ANDGateway modelElement = (ANDGateway) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndEvent_3003ContainedLinks(View view) {
		EndEvent modelElement = (EndEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCallActivity_3063ContainedLinks(View view) {
		CallActivity modelElement = (CallActivity) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTask_3005ContainedLinks(View view) {
		Task modelElement = (Task) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getReceiveTask_3026ContainedLinks(View view) {
		ReceiveTask modelElement = (ReceiveTask) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateErrorCatchEvent_3031ContainedLinks(View view) {
		IntermediateErrorCatchEvent modelElement = (IntermediateErrorCatchEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSendTask_3025ContainedLinks(View view) {
		SendTask modelElement = (SendTask) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getServiceTask_3027ContainedLinks(View view) {
		ServiceTask modelElement = (ServiceTask) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateErrorCatchEvent_3032ContainedLinks(View view) {
		IntermediateErrorCatchEvent modelElement = (IntermediateErrorCatchEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getScriptTask_3028ContainedLinks(View view) {
		ScriptTask modelElement = (ScriptTask) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateErrorCatchEvent_3033ContainedLinks(View view) {
		IntermediateErrorCatchEvent modelElement = (IntermediateErrorCatchEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getXORGateway_3008ContainedLinks(View view) {
		XORGateway modelElement = (XORGateway) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getActivity_3006ContainedLinks(View view) {
		Activity modelElement = (Activity) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateErrorCatchEvent_3034ContainedLinks(View view) {
		IntermediateErrorCatchEvent modelElement = (IntermediateErrorCatchEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateCatchMessageEvent_3013ContainedLinks(View view) {
		IntermediateCatchMessageEvent modelElement = (IntermediateCatchMessageEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartMessageEvent_3012ContainedLinks(View view) {
		StartMessageEvent modelElement = (StartMessageEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndMessageEvent_3011ContainedLinks(View view) {
		EndMessageEvent modelElement = (EndMessageEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateThrowMessageEvent_3014ContainedLinks(View view) {
		IntermediateThrowMessageEvent modelElement = (IntermediateThrowMessageEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextAnnotation_3015ContainedLinks(View view) {
		TextAnnotation modelElement = (TextAnnotation) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartTimerEvent_3016ContainedLinks(View view) {
		StartTimerEvent modelElement = (StartTimerEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateCatchTimerEvent_3017ContainedLinks(View view) {
		IntermediateCatchTimerEvent modelElement = (IntermediateCatchTimerEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartSignalEvent_3023ContainedLinks(View view) {
		StartSignalEvent modelElement = (StartSignalEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateThrowSignalEvent_3022ContainedLinks(View view) {
		IntermediateThrowSignalEvent modelElement = (IntermediateThrowSignalEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateCatchSignalEvent_3021ContainedLinks(View view) {
		IntermediateCatchSignalEvent modelElement = (IntermediateCatchSignalEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndSignalEvent_3020ContainedLinks(View view) {
		EndSignalEvent modelElement = (EndSignalEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndErrorEvent_3050ContainedLinks(View view) {
		EndErrorEvent modelElement = (EndErrorEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndTerminatedEvent_3062ContainedLinks(View view) {
		EndTerminatedEvent modelElement = (EndTerminatedEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartErrorEvent_3060ContainedLinks(View view) {
		StartErrorEvent modelElement = (StartErrorEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEvent_3024ContainedLinks(View view) {
		Event modelElement = (Event) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getInclusiveGateway_3051ContainedLinks(View view) {
		InclusiveGateway modelElement = (InclusiveGateway) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getLane_3007ContainedLinks(View view) {
		Lane modelElement = (Lane) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartEvent_3002ContainedLinks(View view) {
		StartEvent modelElement = (StartEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSubProcessEvent_3058ContainedLinks(View view) {
		SubProcessEvent modelElement = (SubProcessEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getThrowLinkEvent_3018ContainedLinks(View view) {
		ThrowLinkEvent modelElement = (ThrowLinkEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCatchLinkEvent_3019ContainedLinks(View view) {
		CatchLinkEvent modelElement = (CatchLinkEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSequenceFlow_4001ContainedLinks(View view) {
		SequenceFlow modelElement = (SequenceFlow) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getMessageFlow_4002ContainedLinks(View view) {
		MessageFlow modelElement = (MessageFlow) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextAnnotationAttachment_4003ContainedLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getANDGateway_2009IncomingLinks(View view) {
		ANDGateway modelElement = (ANDGateway) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartEvent_2002IncomingLinks(View view) {
		StartEvent modelElement = (StartEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndEvent_2003IncomingLinks(View view) {
		EndEvent modelElement = (EndEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTask_2004IncomingLinks(View view) {
		Task modelElement = (Task) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCallActivity_2036IncomingLinks(View view) {
		CallActivity modelElement = (CallActivity) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSubProcessEvent_2031IncomingLinks(View view) {
		SubProcessEvent modelElement = (SubProcessEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getReceiveTask_2025IncomingLinks(View view) {
		ReceiveTask modelElement = (ReceiveTask) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_MessageFlow_4002(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSendTask_2026IncomingLinks(View view) {
		SendTask modelElement = (SendTask) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getServiceTask_2027IncomingLinks(View view) {
		ServiceTask modelElement = (ServiceTask) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getScriptTask_2028IncomingLinks(View view) {
		ScriptTask modelElement = (ScriptTask) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getXORGateway_2008IncomingLinks(View view) {
		XORGateway modelElement = (XORGateway) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getInclusiveGateway_2030IncomingLinks(View view) {
		InclusiveGateway modelElement = (InclusiveGateway) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getActivity_2006IncomingLinks(View view) {
		Activity modelElement = (Activity) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getPool_2007IncomingLinks(View view) {
		Pool modelElement = (Pool) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartMessageEvent_2010IncomingLinks(View view) {
		StartMessageEvent modelElement = (StartMessageEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_MessageFlow_4002(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndMessageEvent_2011IncomingLinks(View view) {
		EndMessageEvent modelElement = (EndMessageEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateCatchMessageEvent_2013IncomingLinks(View view) {
		IntermediateCatchMessageEvent modelElement = (IntermediateCatchMessageEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_MessageFlow_4002(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateThrowMessageEvent_2014IncomingLinks(View view) {
		IntermediateThrowMessageEvent modelElement = (IntermediateThrowMessageEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextAnnotation_2015IncomingLinks(View view) {
		TextAnnotation modelElement = (TextAnnotation) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateCatchTimerEvent_2017IncomingLinks(View view) {
		IntermediateCatchTimerEvent modelElement = (IntermediateCatchTimerEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartTimerEvent_2016IncomingLinks(View view) {
		StartTimerEvent modelElement = (StartTimerEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCatchLinkEvent_2018IncomingLinks(View view) {
		CatchLinkEvent modelElement = (CatchLinkEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getThrowLinkEvent_2019IncomingLinks(View view) {
		ThrowLinkEvent modelElement = (ThrowLinkEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateThrowSignalEvent_2020IncomingLinks(View view) {
		IntermediateThrowSignalEvent modelElement = (IntermediateThrowSignalEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateCatchSignalEvent_2021IncomingLinks(View view) {
		IntermediateCatchSignalEvent modelElement = (IntermediateCatchSignalEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartSignalEvent_2022IncomingLinks(View view) {
		StartSignalEvent modelElement = (StartSignalEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndSignalEvent_2023IncomingLinks(View view) {
		EndSignalEvent modelElement = (EndSignalEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEvent_2024IncomingLinks(View view) {
		Event modelElement = (Event) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndErrorEvent_2029IncomingLinks(View view) {
		EndErrorEvent modelElement = (EndErrorEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartErrorEvent_2033IncomingLinks(View view) {
		StartErrorEvent modelElement = (StartErrorEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndTerminatedEvent_2035IncomingLinks(View view) {
		EndTerminatedEvent modelElement = (EndTerminatedEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateErrorCatchEvent_3029IncomingLinks(View view) {
		IntermediateErrorCatchEvent modelElement = (IntermediateErrorCatchEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getBoundaryMessageEvent_3035IncomingLinks(View view) {
		BoundaryMessageEvent modelElement = (BoundaryMessageEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_MessageFlow_4002(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getNonInterruptingBoundaryTimerEvent_3064IncomingLinks(View view) {
		NonInterruptingBoundaryTimerEvent modelElement = (NonInterruptingBoundaryTimerEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getBoundaryTimerEvent_3043IncomingLinks(View view) {
		BoundaryTimerEvent modelElement = (BoundaryTimerEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getBoundarySignalEvent_3052IncomingLinks(View view) {
		BoundarySignalEvent modelElement = (BoundarySignalEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateErrorCatchEvent_3030IncomingLinks(View view) {
		IntermediateErrorCatchEvent modelElement = (IntermediateErrorCatchEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getBoundaryMessageEvent_3036IncomingLinks(View view) {
		BoundaryMessageEvent modelElement = (BoundaryMessageEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_MessageFlow_4002(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getNonInterruptingBoundaryTimerEvent_3065IncomingLinks(View view) {
		NonInterruptingBoundaryTimerEvent modelElement = (NonInterruptingBoundaryTimerEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getBoundaryTimerEvent_3044IncomingLinks(View view) {
		BoundaryTimerEvent modelElement = (BoundaryTimerEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getBoundarySignalEvent_3053IncomingLinks(View view) {
		BoundarySignalEvent modelElement = (BoundarySignalEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getANDGateway_3009IncomingLinks(View view) {
		ANDGateway modelElement = (ANDGateway) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndEvent_3003IncomingLinks(View view) {
		EndEvent modelElement = (EndEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCallActivity_3063IncomingLinks(View view) {
		CallActivity modelElement = (CallActivity) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTask_3005IncomingLinks(View view) {
		Task modelElement = (Task) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getReceiveTask_3026IncomingLinks(View view) {
		ReceiveTask modelElement = (ReceiveTask) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_MessageFlow_4002(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateErrorCatchEvent_3031IncomingLinks(View view) {
		IntermediateErrorCatchEvent modelElement = (IntermediateErrorCatchEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSendTask_3025IncomingLinks(View view) {
		SendTask modelElement = (SendTask) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getServiceTask_3027IncomingLinks(View view) {
		ServiceTask modelElement = (ServiceTask) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateErrorCatchEvent_3032IncomingLinks(View view) {
		IntermediateErrorCatchEvent modelElement = (IntermediateErrorCatchEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getScriptTask_3028IncomingLinks(View view) {
		ScriptTask modelElement = (ScriptTask) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateErrorCatchEvent_3033IncomingLinks(View view) {
		IntermediateErrorCatchEvent modelElement = (IntermediateErrorCatchEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getXORGateway_3008IncomingLinks(View view) {
		XORGateway modelElement = (XORGateway) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getActivity_3006IncomingLinks(View view) {
		Activity modelElement = (Activity) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateErrorCatchEvent_3034IncomingLinks(View view) {
		IntermediateErrorCatchEvent modelElement = (IntermediateErrorCatchEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateCatchMessageEvent_3013IncomingLinks(View view) {
		IntermediateCatchMessageEvent modelElement = (IntermediateCatchMessageEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_MessageFlow_4002(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartMessageEvent_3012IncomingLinks(View view) {
		StartMessageEvent modelElement = (StartMessageEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_MessageFlow_4002(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndMessageEvent_3011IncomingLinks(View view) {
		EndMessageEvent modelElement = (EndMessageEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateThrowMessageEvent_3014IncomingLinks(View view) {
		IntermediateThrowMessageEvent modelElement = (IntermediateThrowMessageEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextAnnotation_3015IncomingLinks(View view) {
		TextAnnotation modelElement = (TextAnnotation) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartTimerEvent_3016IncomingLinks(View view) {
		StartTimerEvent modelElement = (StartTimerEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateCatchTimerEvent_3017IncomingLinks(View view) {
		IntermediateCatchTimerEvent modelElement = (IntermediateCatchTimerEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartSignalEvent_3023IncomingLinks(View view) {
		StartSignalEvent modelElement = (StartSignalEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateThrowSignalEvent_3022IncomingLinks(View view) {
		IntermediateThrowSignalEvent modelElement = (IntermediateThrowSignalEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateCatchSignalEvent_3021IncomingLinks(View view) {
		IntermediateCatchSignalEvent modelElement = (IntermediateCatchSignalEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndSignalEvent_3020IncomingLinks(View view) {
		EndSignalEvent modelElement = (EndSignalEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndErrorEvent_3050IncomingLinks(View view) {
		EndErrorEvent modelElement = (EndErrorEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndTerminatedEvent_3062IncomingLinks(View view) {
		EndTerminatedEvent modelElement = (EndTerminatedEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartErrorEvent_3060IncomingLinks(View view) {
		StartErrorEvent modelElement = (StartErrorEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEvent_3024IncomingLinks(View view) {
		Event modelElement = (Event) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getInclusiveGateway_3051IncomingLinks(View view) {
		InclusiveGateway modelElement = (InclusiveGateway) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getLane_3007IncomingLinks(View view) {
		Lane modelElement = (Lane) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartEvent_3002IncomingLinks(View view) {
		StartEvent modelElement = (StartEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSubProcessEvent_3058IncomingLinks(View view) {
		SubProcessEvent modelElement = (SubProcessEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getThrowLinkEvent_3018IncomingLinks(View view) {
		ThrowLinkEvent modelElement = (ThrowLinkEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCatchLinkEvent_3019IncomingLinks(View view) {
		CatchLinkEvent modelElement = (CatchLinkEvent) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_SequenceFlow_4001(modelElement, crossReferences));
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSequenceFlow_4001IncomingLinks(View view) {
		SequenceFlow modelElement = (SequenceFlow) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getMessageFlow_4002IncomingLinks(View view) {
		MessageFlow modelElement = (MessageFlow) view.getElement();
		Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer
				.find(view.eResource().getResourceSet().getResources());
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement, crossReferences));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextAnnotationAttachment_4003IncomingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getANDGateway_2009OutgoingLinks(View view) {
		ANDGateway modelElement = (ANDGateway) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartEvent_2002OutgoingLinks(View view) {
		StartEvent modelElement = (StartEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndEvent_2003OutgoingLinks(View view) {
		EndEvent modelElement = (EndEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTask_2004OutgoingLinks(View view) {
		Task modelElement = (Task) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCallActivity_2036OutgoingLinks(View view) {
		CallActivity modelElement = (CallActivity) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSubProcessEvent_2031OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getReceiveTask_2025OutgoingLinks(View view) {
		ReceiveTask modelElement = (ReceiveTask) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSendTask_2026OutgoingLinks(View view) {
		SendTask modelElement = (SendTask) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_MessageFlow_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getServiceTask_2027OutgoingLinks(View view) {
		ServiceTask modelElement = (ServiceTask) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getScriptTask_2028OutgoingLinks(View view) {
		ScriptTask modelElement = (ScriptTask) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getXORGateway_2008OutgoingLinks(View view) {
		XORGateway modelElement = (XORGateway) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getInclusiveGateway_2030OutgoingLinks(View view) {
		InclusiveGateway modelElement = (InclusiveGateway) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getActivity_2006OutgoingLinks(View view) {
		Activity modelElement = (Activity) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getPool_2007OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartMessageEvent_2010OutgoingLinks(View view) {
		StartMessageEvent modelElement = (StartMessageEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndMessageEvent_2011OutgoingLinks(View view) {
		EndMessageEvent modelElement = (EndMessageEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_MessageFlow_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateCatchMessageEvent_2013OutgoingLinks(View view) {
		IntermediateCatchMessageEvent modelElement = (IntermediateCatchMessageEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateThrowMessageEvent_2014OutgoingLinks(View view) {
		IntermediateThrowMessageEvent modelElement = (IntermediateThrowMessageEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_MessageFlow_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextAnnotation_2015OutgoingLinks(View view) {
		TextAnnotation modelElement = (TextAnnotation) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateCatchTimerEvent_2017OutgoingLinks(View view) {
		IntermediateCatchTimerEvent modelElement = (IntermediateCatchTimerEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartTimerEvent_2016OutgoingLinks(View view) {
		StartTimerEvent modelElement = (StartTimerEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCatchLinkEvent_2018OutgoingLinks(View view) {
		CatchLinkEvent modelElement = (CatchLinkEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getThrowLinkEvent_2019OutgoingLinks(View view) {
		ThrowLinkEvent modelElement = (ThrowLinkEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateThrowSignalEvent_2020OutgoingLinks(View view) {
		IntermediateThrowSignalEvent modelElement = (IntermediateThrowSignalEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateCatchSignalEvent_2021OutgoingLinks(View view) {
		IntermediateCatchSignalEvent modelElement = (IntermediateCatchSignalEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartSignalEvent_2022OutgoingLinks(View view) {
		StartSignalEvent modelElement = (StartSignalEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndSignalEvent_2023OutgoingLinks(View view) {
		EndSignalEvent modelElement = (EndSignalEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEvent_2024OutgoingLinks(View view) {
		Event modelElement = (Event) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndErrorEvent_2029OutgoingLinks(View view) {
		EndErrorEvent modelElement = (EndErrorEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartErrorEvent_2033OutgoingLinks(View view) {
		StartErrorEvent modelElement = (StartErrorEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndTerminatedEvent_2035OutgoingLinks(View view) {
		EndTerminatedEvent modelElement = (EndTerminatedEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateErrorCatchEvent_3029OutgoingLinks(View view) {
		IntermediateErrorCatchEvent modelElement = (IntermediateErrorCatchEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getBoundaryMessageEvent_3035OutgoingLinks(View view) {
		BoundaryMessageEvent modelElement = (BoundaryMessageEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getNonInterruptingBoundaryTimerEvent_3064OutgoingLinks(View view) {
		NonInterruptingBoundaryTimerEvent modelElement = (NonInterruptingBoundaryTimerEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getBoundaryTimerEvent_3043OutgoingLinks(View view) {
		BoundaryTimerEvent modelElement = (BoundaryTimerEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getBoundarySignalEvent_3052OutgoingLinks(View view) {
		BoundarySignalEvent modelElement = (BoundarySignalEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateErrorCatchEvent_3030OutgoingLinks(View view) {
		IntermediateErrorCatchEvent modelElement = (IntermediateErrorCatchEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getBoundaryMessageEvent_3036OutgoingLinks(View view) {
		BoundaryMessageEvent modelElement = (BoundaryMessageEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getNonInterruptingBoundaryTimerEvent_3065OutgoingLinks(View view) {
		NonInterruptingBoundaryTimerEvent modelElement = (NonInterruptingBoundaryTimerEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getBoundaryTimerEvent_3044OutgoingLinks(View view) {
		BoundaryTimerEvent modelElement = (BoundaryTimerEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getBoundarySignalEvent_3053OutgoingLinks(View view) {
		BoundarySignalEvent modelElement = (BoundarySignalEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getANDGateway_3009OutgoingLinks(View view) {
		ANDGateway modelElement = (ANDGateway) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndEvent_3003OutgoingLinks(View view) {
		EndEvent modelElement = (EndEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCallActivity_3063OutgoingLinks(View view) {
		CallActivity modelElement = (CallActivity) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTask_3005OutgoingLinks(View view) {
		Task modelElement = (Task) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getReceiveTask_3026OutgoingLinks(View view) {
		ReceiveTask modelElement = (ReceiveTask) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateErrorCatchEvent_3031OutgoingLinks(View view) {
		IntermediateErrorCatchEvent modelElement = (IntermediateErrorCatchEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSendTask_3025OutgoingLinks(View view) {
		SendTask modelElement = (SendTask) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_MessageFlow_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getServiceTask_3027OutgoingLinks(View view) {
		ServiceTask modelElement = (ServiceTask) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateErrorCatchEvent_3032OutgoingLinks(View view) {
		IntermediateErrorCatchEvent modelElement = (IntermediateErrorCatchEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getScriptTask_3028OutgoingLinks(View view) {
		ScriptTask modelElement = (ScriptTask) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateErrorCatchEvent_3033OutgoingLinks(View view) {
		IntermediateErrorCatchEvent modelElement = (IntermediateErrorCatchEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getXORGateway_3008OutgoingLinks(View view) {
		XORGateway modelElement = (XORGateway) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getActivity_3006OutgoingLinks(View view) {
		Activity modelElement = (Activity) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateErrorCatchEvent_3034OutgoingLinks(View view) {
		IntermediateErrorCatchEvent modelElement = (IntermediateErrorCatchEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateCatchMessageEvent_3013OutgoingLinks(View view) {
		IntermediateCatchMessageEvent modelElement = (IntermediateCatchMessageEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartMessageEvent_3012OutgoingLinks(View view) {
		StartMessageEvent modelElement = (StartMessageEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndMessageEvent_3011OutgoingLinks(View view) {
		EndMessageEvent modelElement = (EndMessageEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_MessageFlow_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateThrowMessageEvent_3014OutgoingLinks(View view) {
		IntermediateThrowMessageEvent modelElement = (IntermediateThrowMessageEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		result.addAll(getOutgoingTypeModelFacetLinks_MessageFlow_4002(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextAnnotation_3015OutgoingLinks(View view) {
		TextAnnotation modelElement = (TextAnnotation) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_TextAnnotationAttachment_4003(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartTimerEvent_3016OutgoingLinks(View view) {
		StartTimerEvent modelElement = (StartTimerEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateCatchTimerEvent_3017OutgoingLinks(View view) {
		IntermediateCatchTimerEvent modelElement = (IntermediateCatchTimerEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartSignalEvent_3023OutgoingLinks(View view) {
		StartSignalEvent modelElement = (StartSignalEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateThrowSignalEvent_3022OutgoingLinks(View view) {
		IntermediateThrowSignalEvent modelElement = (IntermediateThrowSignalEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getIntermediateCatchSignalEvent_3021OutgoingLinks(View view) {
		IntermediateCatchSignalEvent modelElement = (IntermediateCatchSignalEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndSignalEvent_3020OutgoingLinks(View view) {
		EndSignalEvent modelElement = (EndSignalEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndErrorEvent_3050OutgoingLinks(View view) {
		EndErrorEvent modelElement = (EndErrorEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEndTerminatedEvent_3062OutgoingLinks(View view) {
		EndTerminatedEvent modelElement = (EndTerminatedEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartErrorEvent_3060OutgoingLinks(View view) {
		StartErrorEvent modelElement = (StartErrorEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getEvent_3024OutgoingLinks(View view) {
		Event modelElement = (Event) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getInclusiveGateway_3051OutgoingLinks(View view) {
		InclusiveGateway modelElement = (InclusiveGateway) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getLane_3007OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getStartEvent_3002OutgoingLinks(View view) {
		StartEvent modelElement = (StartEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSubProcessEvent_3058OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getThrowLinkEvent_3018OutgoingLinks(View view) {
		ThrowLinkEvent modelElement = (ThrowLinkEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getCatchLinkEvent_3019OutgoingLinks(View view) {
		CatchLinkEvent modelElement = (CatchLinkEvent) view.getElement();
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		result.addAll(getOutgoingTypeModelFacetLinks_SequenceFlow_4001(modelElement));
		return result;
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getSequenceFlow_4001OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getMessageFlow_4002OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	 * @generated
	 */
	public static List<ProcessLinkDescriptor> getTextAnnotationAttachment_4003OutgoingLinks(View view) {
		return Collections.emptyList();
	}

	/**
	* @generated
	*/
	private static Collection<ProcessLinkDescriptor> getContainedTypeModelFacetLinks_SequenceFlow_4001(
			AbstractProcess container) {
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		for (Iterator<?> links = container.getConnections().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof SequenceFlow) {
				continue;
			}
			SequenceFlow link = (SequenceFlow) linkObject;
			if (SequenceFlowEditPart.VISUAL_ID != ProcessVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			TargetElement dst = link.getTarget();
			SourceElement src = link.getSource();
			result.add(new ProcessLinkDescriptor(src, dst, link, ProcessElementTypes.SequenceFlow_4001,
					SequenceFlowEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	* @generated
	*/
	private static Collection<ProcessLinkDescriptor> getContainedTypeModelFacetLinks_MessageFlow_4002(
			MainProcess container) {
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		for (Iterator<?> links = container.getMessageConnections().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof MessageFlow) {
				continue;
			}
			MessageFlow link = (MessageFlow) linkObject;
			if (MessageFlowEditPart.VISUAL_ID != ProcessVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			AbstractCatchMessageEvent dst = link.getTarget();
			ThrowMessageEvent src = link.getSource();
			result.add(new ProcessLinkDescriptor(src, dst, link, ProcessElementTypes.MessageFlow_4002,
					MessageFlowEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	* @generated
	*/
	private static Collection<ProcessLinkDescriptor> getContainedTypeModelFacetLinks_TextAnnotationAttachment_4003(
			Element container) {
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		for (Iterator<?> links = container.getTextAnnotationAttachment().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof TextAnnotationAttachment) {
				continue;
			}
			TextAnnotationAttachment link = (TextAnnotationAttachment) linkObject;
			if (TextAnnotationAttachmentEditPart.VISUAL_ID != ProcessVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getTarget();
			TextAnnotation src = link.getSource();
			result.add(new ProcessLinkDescriptor(src, dst, link, ProcessElementTypes.TextAnnotationAttachment_4003,
					TextAnnotationAttachmentEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ProcessLinkDescriptor> getIncomingTypeModelFacetLinks_SequenceFlow_4001(
			TargetElement target, Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != ProcessPackage.eINSTANCE.getConnection_Target()
					|| false == setting.getEObject() instanceof SequenceFlow) {
				continue;
			}
			SequenceFlow link = (SequenceFlow) setting.getEObject();
			if (SequenceFlowEditPart.VISUAL_ID != ProcessVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			SourceElement src = link.getSource();
			result.add(new ProcessLinkDescriptor(src, target, link, ProcessElementTypes.SequenceFlow_4001,
					SequenceFlowEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ProcessLinkDescriptor> getIncomingTypeModelFacetLinks_MessageFlow_4002(
			AbstractCatchMessageEvent target, Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != ProcessPackage.eINSTANCE.getMessageFlow_Target()
					|| false == setting.getEObject() instanceof MessageFlow) {
				continue;
			}
			MessageFlow link = (MessageFlow) setting.getEObject();
			if (MessageFlowEditPart.VISUAL_ID != ProcessVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			ThrowMessageEvent src = link.getSource();
			result.add(new ProcessLinkDescriptor(src, target, link, ProcessElementTypes.MessageFlow_4002,
					MessageFlowEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	 * @generated
	 */
	private static Collection<ProcessLinkDescriptor> getIncomingTypeModelFacetLinks_TextAnnotationAttachment_4003(
			Element target, Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		Collection<EStructuralFeature.Setting> settings = crossReferences.get(target);
		for (EStructuralFeature.Setting setting : settings) {
			if (setting.getEStructuralFeature() != ProcessPackage.eINSTANCE.getTextAnnotationAttachment_Target()
					|| false == setting.getEObject() instanceof TextAnnotationAttachment) {
				continue;
			}
			TextAnnotationAttachment link = (TextAnnotationAttachment) setting.getEObject();
			if (TextAnnotationAttachmentEditPart.VISUAL_ID != ProcessVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			TextAnnotation src = link.getSource();
			result.add(new ProcessLinkDescriptor(src, target, link, ProcessElementTypes.TextAnnotationAttachment_4003,
					TextAnnotationAttachmentEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	* @generated
	*/
	private static Collection<ProcessLinkDescriptor> getOutgoingTypeModelFacetLinks_SequenceFlow_4001(
			SourceElement source) {
		AbstractProcess container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof AbstractProcess) {
				container = (AbstractProcess) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		for (Iterator<?> links = container.getConnections().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof SequenceFlow) {
				continue;
			}
			SequenceFlow link = (SequenceFlow) linkObject;
			if (SequenceFlowEditPart.VISUAL_ID != ProcessVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			TargetElement dst = link.getTarget();
			SourceElement src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new ProcessLinkDescriptor(src, dst, link, ProcessElementTypes.SequenceFlow_4001,
					SequenceFlowEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	* @generated
	*/
	private static Collection<ProcessLinkDescriptor> getOutgoingTypeModelFacetLinks_MessageFlow_4002(
			ThrowMessageEvent source) {
		MainProcess container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof MainProcess) {
				container = (MainProcess) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		for (Iterator<?> links = container.getMessageConnections().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof MessageFlow) {
				continue;
			}
			MessageFlow link = (MessageFlow) linkObject;
			if (MessageFlowEditPart.VISUAL_ID != ProcessVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			AbstractCatchMessageEvent dst = link.getTarget();
			ThrowMessageEvent src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new ProcessLinkDescriptor(src, dst, link, ProcessElementTypes.MessageFlow_4002,
					MessageFlowEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	* @generated
	*/
	private static Collection<ProcessLinkDescriptor> getOutgoingTypeModelFacetLinks_TextAnnotationAttachment_4003(
			TextAnnotation source) {
		Element container = null;
		// Find container element for the link.
		// Climb up by containment hierarchy starting from the source
		// and return the first element that is instance of the container class.
		for (EObject element = source; element != null && container == null; element = element.eContainer()) {
			if (element instanceof Element) {
				container = (Element) element;
			}
		}
		if (container == null) {
			return Collections.emptyList();
		}
		LinkedList<ProcessLinkDescriptor> result = new LinkedList<ProcessLinkDescriptor>();
		for (Iterator<?> links = container.getTextAnnotationAttachment().iterator(); links.hasNext();) {
			EObject linkObject = (EObject) links.next();
			if (false == linkObject instanceof TextAnnotationAttachment) {
				continue;
			}
			TextAnnotationAttachment link = (TextAnnotationAttachment) linkObject;
			if (TextAnnotationAttachmentEditPart.VISUAL_ID != ProcessVisualIDRegistry.getLinkWithClassVisualID(link)) {
				continue;
			}
			Element dst = link.getTarget();
			TextAnnotation src = link.getSource();
			if (src != source) {
				continue;
			}
			result.add(new ProcessLinkDescriptor(src, dst, link, ProcessElementTypes.TextAnnotationAttachment_4003,
					TextAnnotationAttachmentEditPart.VISUAL_ID));
		}
		return result;
	}

	/**
	* @generated
	*/
	public static final DiagramUpdater TYPED_INSTANCE = new DiagramUpdater() {
		/**
		* @generated
		*/
		@Override

		public List<ProcessNodeDescriptor> getSemanticChildren(View view) {
			return ProcessDiagramUpdater.getSemanticChildren(view);
		}

		/**
		* @generated
		*/
		@Override

		public List<ProcessLinkDescriptor> getContainedLinks(View view) {
			return ProcessDiagramUpdater.getContainedLinks(view);
		}

		/**
		* @generated
		*/
		@Override

		public List<ProcessLinkDescriptor> getIncomingLinks(View view) {
			return ProcessDiagramUpdater.getIncomingLinks(view);
		}

		/**
		* @generated
		*/
		@Override

		public List<ProcessLinkDescriptor> getOutgoingLinks(View view) {
			return ProcessDiagramUpdater.getOutgoingLinks(view);
		}
	};

}
