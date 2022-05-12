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
package org.bonitasoft.studio.model.process.diagram.providers.assistants;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.bonitasoft.studio.model.process.diagram.edit.parts.ANDGateway2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ANDGatewayEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.Activity2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ActivityEditPart;
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
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateThrowMessageEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateThrowMessageEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateThrowSignalEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateThrowSignalEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ReceiveTask2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ReceiveTaskEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ScriptTask2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ScriptTaskEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SendTask2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SendTaskEditPart;
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
import org.bonitasoft.studio.model.process.diagram.edit.parts.Task2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TaskEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ThrowLinkEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ThrowLinkEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.XORGateway2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.XORGatewayEditPart;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessModelingAssistantProvider;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @generated
 */
public class ProcessModelingAssistantProviderOfIntermediateCatchTimerEventEditPart
		extends ProcessModelingAssistantProvider {

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getRelTypesOnSource(IAdaptable source) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSource((IntermediateCatchTimerEventEditPart) sourceEditPart);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetRelTypesOnSource(IntermediateCatchTimerEventEditPart source) {
		List<IElementType> types = new ArrayList<IElementType>(1);
		types.add(ProcessElementTypes.SequenceFlow_4001);
		return types;
	}

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getRelTypesOnSourceAndTarget(IAdaptable source, IAdaptable target) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnSourceAndTarget((IntermediateCatchTimerEventEditPart) sourceEditPart, targetEditPart);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetRelTypesOnSourceAndTarget(IntermediateCatchTimerEventEditPart source,
			IGraphicalEditPart targetEditPart) {
		List<IElementType> types = new LinkedList<IElementType>();
		if (targetEditPart instanceof ANDGatewayEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof StartEventEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof EndEventEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof TaskEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof CallActivityEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof ReceiveTaskEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof SendTaskEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof ServiceTaskEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof ScriptTaskEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof XORGatewayEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof InclusiveGatewayEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof ActivityEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof StartMessageEventEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof EndMessageEventEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof IntermediateCatchMessageEventEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof IntermediateThrowMessageEventEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof IntermediateCatchTimerEventEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof StartTimerEventEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof CatchLinkEventEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof ThrowLinkEventEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof IntermediateThrowSignalEventEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof IntermediateCatchSignalEventEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof StartSignalEventEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof EndSignalEventEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof EventEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof EndErrorEventEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof StartErrorEventEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof EndTerminatedEventEditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof ANDGateway2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof EndEvent2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof CallActivity2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof Task2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof ReceiveTask2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof SendTask2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof ServiceTask2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof ScriptTask2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof XORGateway2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof Activity2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof IntermediateCatchMessageEvent2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof StartMessageEvent2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof EndMessageEvent2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof IntermediateThrowMessageEvent2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof StartTimerEvent2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof IntermediateCatchTimerEvent2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof StartSignalEvent2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof IntermediateThrowSignalEvent2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof IntermediateCatchSignalEvent2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof EndSignalEvent2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof EndErrorEvent2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof EndTerminatedEvent2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof StartErrorEvent2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof Event2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof InclusiveGateway2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof StartEvent2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof ThrowLinkEvent2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		if (targetEditPart instanceof CatchLinkEvent2EditPart) {
			types.add(ProcessElementTypes.SequenceFlow_4001);
		}
		return types;
	}

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getTypesForTarget(IAdaptable source, IElementType relationshipType) {
		IGraphicalEditPart sourceEditPart = (IGraphicalEditPart) source.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForTarget((IntermediateCatchTimerEventEditPart) sourceEditPart, relationshipType);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetTypesForTarget(IntermediateCatchTimerEventEditPart source,
			IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == ProcessElementTypes.SequenceFlow_4001) {
			types.add(ProcessElementTypes.ANDGateway_2009);
			types.add(ProcessElementTypes.StartEvent_2002);
			types.add(ProcessElementTypes.EndEvent_2003);
			types.add(ProcessElementTypes.Task_2004);
			types.add(ProcessElementTypes.CallActivity_2036);
			types.add(ProcessElementTypes.ReceiveTask_2025);
			types.add(ProcessElementTypes.SendTask_2026);
			types.add(ProcessElementTypes.ServiceTask_2027);
			types.add(ProcessElementTypes.ScriptTask_2028);
			types.add(ProcessElementTypes.XORGateway_2008);
			types.add(ProcessElementTypes.InclusiveGateway_2030);
			types.add(ProcessElementTypes.Activity_2006);
			types.add(ProcessElementTypes.StartMessageEvent_2010);
			types.add(ProcessElementTypes.EndMessageEvent_2011);
			types.add(ProcessElementTypes.IntermediateCatchMessageEvent_2013);
			types.add(ProcessElementTypes.IntermediateThrowMessageEvent_2014);
			types.add(ProcessElementTypes.IntermediateCatchTimerEvent_2017);
			types.add(ProcessElementTypes.StartTimerEvent_2016);
			types.add(ProcessElementTypes.CatchLinkEvent_2018);
			types.add(ProcessElementTypes.ThrowLinkEvent_2019);
			types.add(ProcessElementTypes.IntermediateThrowSignalEvent_2020);
			types.add(ProcessElementTypes.IntermediateCatchSignalEvent_2021);
			types.add(ProcessElementTypes.StartSignalEvent_2022);
			types.add(ProcessElementTypes.EndSignalEvent_2023);
			types.add(ProcessElementTypes.Event_2024);
			types.add(ProcessElementTypes.EndErrorEvent_2029);
			types.add(ProcessElementTypes.StartErrorEvent_2033);
			types.add(ProcessElementTypes.EndTerminatedEvent_2035);
			types.add(ProcessElementTypes.ANDGateway_3009);
			types.add(ProcessElementTypes.EndEvent_3003);
			types.add(ProcessElementTypes.CallActivity_3063);
			types.add(ProcessElementTypes.Task_3005);
			types.add(ProcessElementTypes.ReceiveTask_3026);
			types.add(ProcessElementTypes.SendTask_3025);
			types.add(ProcessElementTypes.ServiceTask_3027);
			types.add(ProcessElementTypes.ScriptTask_3028);
			types.add(ProcessElementTypes.XORGateway_3008);
			types.add(ProcessElementTypes.Activity_3006);
			types.add(ProcessElementTypes.IntermediateCatchMessageEvent_3013);
			types.add(ProcessElementTypes.StartMessageEvent_3012);
			types.add(ProcessElementTypes.EndMessageEvent_3011);
			types.add(ProcessElementTypes.IntermediateThrowMessageEvent_3014);
			types.add(ProcessElementTypes.StartTimerEvent_3016);
			types.add(ProcessElementTypes.IntermediateCatchTimerEvent_3017);
			types.add(ProcessElementTypes.StartSignalEvent_3023);
			types.add(ProcessElementTypes.IntermediateThrowSignalEvent_3022);
			types.add(ProcessElementTypes.IntermediateCatchSignalEvent_3021);
			types.add(ProcessElementTypes.EndSignalEvent_3020);
			types.add(ProcessElementTypes.EndErrorEvent_3050);
			types.add(ProcessElementTypes.EndTerminatedEvent_3062);
			types.add(ProcessElementTypes.StartErrorEvent_3060);
			types.add(ProcessElementTypes.Event_3024);
			types.add(ProcessElementTypes.InclusiveGateway_3051);
			types.add(ProcessElementTypes.StartEvent_3002);
			types.add(ProcessElementTypes.ThrowLinkEvent_3018);
			types.add(ProcessElementTypes.CatchLinkEvent_3019);
		}
		return types;
	}

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getRelTypesOnTarget(IAdaptable target) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetRelTypesOnTarget((IntermediateCatchTimerEventEditPart) targetEditPart);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetRelTypesOnTarget(IntermediateCatchTimerEventEditPart target) {
		List<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.SequenceFlow_4001);
		types.add(ProcessElementTypes.TextAnnotationAttachment_4003);
		return types;
	}

	/**
	* @generated
	*/
	@Override

	public List<IElementType> getTypesForSource(IAdaptable target, IElementType relationshipType) {
		IGraphicalEditPart targetEditPart = (IGraphicalEditPart) target.getAdapter(IGraphicalEditPart.class);
		return doGetTypesForSource((IntermediateCatchTimerEventEditPart) targetEditPart, relationshipType);
	}

	/**
	* @generated
	*/
	public List<IElementType> doGetTypesForSource(IntermediateCatchTimerEventEditPart target,
			IElementType relationshipType) {
		List<IElementType> types = new ArrayList<IElementType>();
		if (relationshipType == ProcessElementTypes.SequenceFlow_4001) {
			types.add(ProcessElementTypes.ANDGateway_2009);
			types.add(ProcessElementTypes.StartEvent_2002);
			types.add(ProcessElementTypes.EndEvent_2003);
			types.add(ProcessElementTypes.Task_2004);
			types.add(ProcessElementTypes.CallActivity_2036);
			types.add(ProcessElementTypes.ReceiveTask_2025);
			types.add(ProcessElementTypes.SendTask_2026);
			types.add(ProcessElementTypes.ServiceTask_2027);
			types.add(ProcessElementTypes.ScriptTask_2028);
			types.add(ProcessElementTypes.XORGateway_2008);
			types.add(ProcessElementTypes.InclusiveGateway_2030);
			types.add(ProcessElementTypes.Activity_2006);
			types.add(ProcessElementTypes.StartMessageEvent_2010);
			types.add(ProcessElementTypes.EndMessageEvent_2011);
			types.add(ProcessElementTypes.IntermediateCatchMessageEvent_2013);
			types.add(ProcessElementTypes.IntermediateThrowMessageEvent_2014);
			types.add(ProcessElementTypes.IntermediateCatchTimerEvent_2017);
			types.add(ProcessElementTypes.StartTimerEvent_2016);
			types.add(ProcessElementTypes.CatchLinkEvent_2018);
			types.add(ProcessElementTypes.ThrowLinkEvent_2019);
			types.add(ProcessElementTypes.IntermediateThrowSignalEvent_2020);
			types.add(ProcessElementTypes.IntermediateCatchSignalEvent_2021);
			types.add(ProcessElementTypes.StartSignalEvent_2022);
			types.add(ProcessElementTypes.EndSignalEvent_2023);
			types.add(ProcessElementTypes.Event_2024);
			types.add(ProcessElementTypes.EndErrorEvent_2029);
			types.add(ProcessElementTypes.StartErrorEvent_2033);
			types.add(ProcessElementTypes.EndTerminatedEvent_2035);
			types.add(ProcessElementTypes.IntermediateErrorCatchEvent_3029);
			types.add(ProcessElementTypes.BoundaryMessageEvent_3035);
			types.add(ProcessElementTypes.NonInterruptingBoundaryTimerEvent_3064);
			types.add(ProcessElementTypes.BoundaryTimerEvent_3043);
			types.add(ProcessElementTypes.BoundarySignalEvent_3052);
			types.add(ProcessElementTypes.IntermediateErrorCatchEvent_3030);
			types.add(ProcessElementTypes.BoundaryMessageEvent_3036);
			types.add(ProcessElementTypes.NonInterruptingBoundaryTimerEvent_3065);
			types.add(ProcessElementTypes.BoundaryTimerEvent_3044);
			types.add(ProcessElementTypes.BoundarySignalEvent_3053);
			types.add(ProcessElementTypes.ANDGateway_3009);
			types.add(ProcessElementTypes.EndEvent_3003);
			types.add(ProcessElementTypes.CallActivity_3063);
			types.add(ProcessElementTypes.Task_3005);
			types.add(ProcessElementTypes.ReceiveTask_3026);
			types.add(ProcessElementTypes.IntermediateErrorCatchEvent_3031);
			types.add(ProcessElementTypes.SendTask_3025);
			types.add(ProcessElementTypes.ServiceTask_3027);
			types.add(ProcessElementTypes.IntermediateErrorCatchEvent_3032);
			types.add(ProcessElementTypes.ScriptTask_3028);
			types.add(ProcessElementTypes.IntermediateErrorCatchEvent_3033);
			types.add(ProcessElementTypes.XORGateway_3008);
			types.add(ProcessElementTypes.Activity_3006);
			types.add(ProcessElementTypes.IntermediateErrorCatchEvent_3034);
			types.add(ProcessElementTypes.IntermediateCatchMessageEvent_3013);
			types.add(ProcessElementTypes.StartMessageEvent_3012);
			types.add(ProcessElementTypes.EndMessageEvent_3011);
			types.add(ProcessElementTypes.IntermediateThrowMessageEvent_3014);
			types.add(ProcessElementTypes.StartTimerEvent_3016);
			types.add(ProcessElementTypes.IntermediateCatchTimerEvent_3017);
			types.add(ProcessElementTypes.StartSignalEvent_3023);
			types.add(ProcessElementTypes.IntermediateThrowSignalEvent_3022);
			types.add(ProcessElementTypes.IntermediateCatchSignalEvent_3021);
			types.add(ProcessElementTypes.EndSignalEvent_3020);
			types.add(ProcessElementTypes.EndErrorEvent_3050);
			types.add(ProcessElementTypes.EndTerminatedEvent_3062);
			types.add(ProcessElementTypes.StartErrorEvent_3060);
			types.add(ProcessElementTypes.Event_3024);
			types.add(ProcessElementTypes.InclusiveGateway_3051);
			types.add(ProcessElementTypes.StartEvent_3002);
			types.add(ProcessElementTypes.ThrowLinkEvent_3018);
			types.add(ProcessElementTypes.CatchLinkEvent_3019);
		} else if (relationshipType == ProcessElementTypes.TextAnnotationAttachment_4003) {
			types.add(ProcessElementTypes.TextAnnotation_2015);
			types.add(ProcessElementTypes.TextAnnotation_3015);
		}
		return types;
	}

}
