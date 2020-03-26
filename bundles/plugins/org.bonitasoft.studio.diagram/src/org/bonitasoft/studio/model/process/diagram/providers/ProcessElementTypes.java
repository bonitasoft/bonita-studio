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
package org.bonitasoft.studio.model.process.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.model.process.ProcessPackage;
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
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MessageFlowEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.NonInterruptingBoundaryTimerEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.NonInterruptingBoundaryTimerEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolEditPart;
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
import org.bonitasoft.studio.model.process.diagram.edit.parts.Task2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TaskEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TextAnnotation2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TextAnnotationAttachmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TextAnnotationEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ThrowLinkEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ThrowLinkEventEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.XORGateway2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.XORGatewayEditPart;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypeImages;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypes;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

/**
 * @generated
 */
public class ProcessElementTypes {

	/**
	* @generated
	*/
	private ProcessElementTypes() {
	}

	/**
	* @generated
	*/
	private static Map<IElementType, ENamedElement> elements;

	/**
	* @generated
	*/
	private static DiagramElementTypeImages elementTypeImages = new DiagramElementTypeImages(
			ProcessDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory());

	/**
	* @generated
	*/
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	* @generated
	*/
	public static final IElementType MainProcess_1000 = getElementType(
			"org.bonitasoft.studio.diagram.MainProcess_1000"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType ANDGateway_2009 = getElementType("org.bonitasoft.studio.diagram.ANDGateway_2009"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType StartEvent_2002 = getElementType("org.bonitasoft.studio.diagram.StartEvent_2002"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType EndEvent_2003 = getElementType("org.bonitasoft.studio.diagram.EndEvent_2003"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Task_2004 = getElementType("org.bonitasoft.studio.diagram.Task_2004"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType CallActivity_2036 = getElementType(
			"org.bonitasoft.studio.diagram.CallActivity_2036"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType SubProcessEvent_2031 = getElementType(
			"org.bonitasoft.studio.diagram.SubProcessEvent_2031"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType ReceiveTask_2025 = getElementType(
			"org.bonitasoft.studio.diagram.ReceiveTask_2025"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType SendTask_2026 = getElementType("org.bonitasoft.studio.diagram.SendTask_2026"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType ServiceTask_2027 = getElementType(
			"org.bonitasoft.studio.diagram.ServiceTask_2027"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType ScriptTask_2028 = getElementType("org.bonitasoft.studio.diagram.ScriptTask_2028"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType XORGateway_2008 = getElementType("org.bonitasoft.studio.diagram.XORGateway_2008"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType InclusiveGateway_2030 = getElementType(
			"org.bonitasoft.studio.diagram.InclusiveGateway_2030"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Activity_2006 = getElementType("org.bonitasoft.studio.diagram.Activity_2006"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Pool_2007 = getElementType("org.bonitasoft.studio.diagram.Pool_2007"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType StartMessageEvent_2010 = getElementType(
			"org.bonitasoft.studio.diagram.StartMessageEvent_2010"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType EndMessageEvent_2011 = getElementType(
			"org.bonitasoft.studio.diagram.EndMessageEvent_2011"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType IntermediateCatchMessageEvent_2013 = getElementType(
			"org.bonitasoft.studio.diagram.IntermediateCatchMessageEvent_2013"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType IntermediateThrowMessageEvent_2014 = getElementType(
			"org.bonitasoft.studio.diagram.IntermediateThrowMessageEvent_2014"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType TextAnnotation_2015 = getElementType(
			"org.bonitasoft.studio.diagram.TextAnnotation_2015"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType IntermediateCatchTimerEvent_2017 = getElementType(
			"org.bonitasoft.studio.diagram.IntermediateCatchTimerEvent_2017"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType StartTimerEvent_2016 = getElementType(
			"org.bonitasoft.studio.diagram.StartTimerEvent_2016"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType CatchLinkEvent_2018 = getElementType(
			"org.bonitasoft.studio.diagram.CatchLinkEvent_2018"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType ThrowLinkEvent_2019 = getElementType(
			"org.bonitasoft.studio.diagram.ThrowLinkEvent_2019"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType IntermediateThrowSignalEvent_2020 = getElementType(
			"org.bonitasoft.studio.diagram.IntermediateThrowSignalEvent_2020"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType IntermediateCatchSignalEvent_2021 = getElementType(
			"org.bonitasoft.studio.diagram.IntermediateCatchSignalEvent_2021"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType StartSignalEvent_2022 = getElementType(
			"org.bonitasoft.studio.diagram.StartSignalEvent_2022"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType EndSignalEvent_2023 = getElementType(
			"org.bonitasoft.studio.diagram.EndSignalEvent_2023"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Event_2024 = getElementType("org.bonitasoft.studio.diagram.Event_2024"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType EndErrorEvent_2029 = getElementType(
			"org.bonitasoft.studio.diagram.EndErrorEvent_2029"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType StartErrorEvent_2033 = getElementType(
			"org.bonitasoft.studio.diagram.StartErrorEvent_2033"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType EndTerminatedEvent_2035 = getElementType(
			"org.bonitasoft.studio.diagram.EndTerminatedEvent_2035"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType IntermediateErrorCatchEvent_3029 = getElementType(
			"org.bonitasoft.studio.diagram.IntermediateErrorCatchEvent_3029"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType BoundaryMessageEvent_3035 = getElementType(
			"org.bonitasoft.studio.diagram.BoundaryMessageEvent_3035"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType NonInterruptingBoundaryTimerEvent_3064 = getElementType(
			"org.bonitasoft.studio.diagram.NonInterruptingBoundaryTimerEvent_3064"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType BoundaryTimerEvent_3043 = getElementType(
			"org.bonitasoft.studio.diagram.BoundaryTimerEvent_3043"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType BoundarySignalEvent_3052 = getElementType(
			"org.bonitasoft.studio.diagram.BoundarySignalEvent_3052"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType IntermediateErrorCatchEvent_3030 = getElementType(
			"org.bonitasoft.studio.diagram.IntermediateErrorCatchEvent_3030"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType BoundaryMessageEvent_3036 = getElementType(
			"org.bonitasoft.studio.diagram.BoundaryMessageEvent_3036"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType NonInterruptingBoundaryTimerEvent_3065 = getElementType(
			"org.bonitasoft.studio.diagram.NonInterruptingBoundaryTimerEvent_3065"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType BoundaryTimerEvent_3044 = getElementType(
			"org.bonitasoft.studio.diagram.BoundaryTimerEvent_3044"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType BoundarySignalEvent_3053 = getElementType(
			"org.bonitasoft.studio.diagram.BoundarySignalEvent_3053"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType ANDGateway_3009 = getElementType("org.bonitasoft.studio.diagram.ANDGateway_3009"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType EndEvent_3003 = getElementType("org.bonitasoft.studio.diagram.EndEvent_3003"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType CallActivity_3063 = getElementType(
			"org.bonitasoft.studio.diagram.CallActivity_3063"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Task_3005 = getElementType("org.bonitasoft.studio.diagram.Task_3005"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType ReceiveTask_3026 = getElementType(
			"org.bonitasoft.studio.diagram.ReceiveTask_3026"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType IntermediateErrorCatchEvent_3031 = getElementType(
			"org.bonitasoft.studio.diagram.IntermediateErrorCatchEvent_3031"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType SendTask_3025 = getElementType("org.bonitasoft.studio.diagram.SendTask_3025"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType ServiceTask_3027 = getElementType(
			"org.bonitasoft.studio.diagram.ServiceTask_3027"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType IntermediateErrorCatchEvent_3032 = getElementType(
			"org.bonitasoft.studio.diagram.IntermediateErrorCatchEvent_3032"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType ScriptTask_3028 = getElementType("org.bonitasoft.studio.diagram.ScriptTask_3028"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType IntermediateErrorCatchEvent_3033 = getElementType(
			"org.bonitasoft.studio.diagram.IntermediateErrorCatchEvent_3033"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType XORGateway_3008 = getElementType("org.bonitasoft.studio.diagram.XORGateway_3008"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Activity_3006 = getElementType("org.bonitasoft.studio.diagram.Activity_3006"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType IntermediateErrorCatchEvent_3034 = getElementType(
			"org.bonitasoft.studio.diagram.IntermediateErrorCatchEvent_3034"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType IntermediateCatchMessageEvent_3013 = getElementType(
			"org.bonitasoft.studio.diagram.IntermediateCatchMessageEvent_3013"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType StartMessageEvent_3012 = getElementType(
			"org.bonitasoft.studio.diagram.StartMessageEvent_3012"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType EndMessageEvent_3011 = getElementType(
			"org.bonitasoft.studio.diagram.EndMessageEvent_3011"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType IntermediateThrowMessageEvent_3014 = getElementType(
			"org.bonitasoft.studio.diagram.IntermediateThrowMessageEvent_3014"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType TextAnnotation_3015 = getElementType(
			"org.bonitasoft.studio.diagram.TextAnnotation_3015"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType StartTimerEvent_3016 = getElementType(
			"org.bonitasoft.studio.diagram.StartTimerEvent_3016"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType IntermediateCatchTimerEvent_3017 = getElementType(
			"org.bonitasoft.studio.diagram.IntermediateCatchTimerEvent_3017"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType StartSignalEvent_3023 = getElementType(
			"org.bonitasoft.studio.diagram.StartSignalEvent_3023"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType IntermediateThrowSignalEvent_3022 = getElementType(
			"org.bonitasoft.studio.diagram.IntermediateThrowSignalEvent_3022"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType IntermediateCatchSignalEvent_3021 = getElementType(
			"org.bonitasoft.studio.diagram.IntermediateCatchSignalEvent_3021"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType EndSignalEvent_3020 = getElementType(
			"org.bonitasoft.studio.diagram.EndSignalEvent_3020"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType EndErrorEvent_3050 = getElementType(
			"org.bonitasoft.studio.diagram.EndErrorEvent_3050"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType EndTerminatedEvent_3062 = getElementType(
			"org.bonitasoft.studio.diagram.EndTerminatedEvent_3062"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType StartErrorEvent_3060 = getElementType(
			"org.bonitasoft.studio.diagram.StartErrorEvent_3060"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Event_3024 = getElementType("org.bonitasoft.studio.diagram.Event_3024"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType InclusiveGateway_3051 = getElementType(
			"org.bonitasoft.studio.diagram.InclusiveGateway_3051"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Lane_3007 = getElementType("org.bonitasoft.studio.diagram.Lane_3007"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType StartEvent_3002 = getElementType("org.bonitasoft.studio.diagram.StartEvent_3002"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType SubProcessEvent_3058 = getElementType(
			"org.bonitasoft.studio.diagram.SubProcessEvent_3058"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType ThrowLinkEvent_3018 = getElementType(
			"org.bonitasoft.studio.diagram.ThrowLinkEvent_3018"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType CatchLinkEvent_3019 = getElementType(
			"org.bonitasoft.studio.diagram.CatchLinkEvent_3019"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType SequenceFlow_4001 = getElementType(
			"org.bonitasoft.studio.diagram.SequenceFlow_4001"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType MessageFlow_4002 = getElementType(
			"org.bonitasoft.studio.diagram.MessageFlow_4002"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType TextAnnotationAttachment_4003 = getElementType(
			"org.bonitasoft.studio.diagram.TextAnnotationAttachment_4003"); //$NON-NLS-1$

	/**
	* @generated
	*/
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		return elementTypeImages.getImageDescriptor(element);
	}

	/**
	* @generated
	*/
	public static Image getImage(ENamedElement element) {
		return elementTypeImages.getImage(element);
	}

	/**
	* @generated
	*/
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		return getImageDescriptor(getElement(hint));
	}

	/**
	* @generated
	*/
	public static Image getImage(IAdaptable hint) {
		return getImage(getElement(hint));
	}

	/**
	* Returns 'type' of the ecore object associated with the hint.
	* 
	* @generated
	*/
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			initElements();
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	* @generated
	*/
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	* @generated
	*/
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			initKnownElementTypes();
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	* @generated
	*/
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case MainProcessEditPart.VISUAL_ID:
			return MainProcess_1000;
		case ANDGatewayEditPart.VISUAL_ID:
			return ANDGateway_2009;
		case StartEventEditPart.VISUAL_ID:
			return StartEvent_2002;
		case EndEventEditPart.VISUAL_ID:
			return EndEvent_2003;
		case TaskEditPart.VISUAL_ID:
			return Task_2004;
		case CallActivityEditPart.VISUAL_ID:
			return CallActivity_2036;
		case SubProcessEventEditPart.VISUAL_ID:
			return SubProcessEvent_2031;
		case ReceiveTaskEditPart.VISUAL_ID:
			return ReceiveTask_2025;
		case SendTaskEditPart.VISUAL_ID:
			return SendTask_2026;
		case ServiceTaskEditPart.VISUAL_ID:
			return ServiceTask_2027;
		case ScriptTaskEditPart.VISUAL_ID:
			return ScriptTask_2028;
		case XORGatewayEditPart.VISUAL_ID:
			return XORGateway_2008;
		case InclusiveGatewayEditPart.VISUAL_ID:
			return InclusiveGateway_2030;
		case ActivityEditPart.VISUAL_ID:
			return Activity_2006;
		case PoolEditPart.VISUAL_ID:
			return Pool_2007;
		case StartMessageEventEditPart.VISUAL_ID:
			return StartMessageEvent_2010;
		case EndMessageEventEditPart.VISUAL_ID:
			return EndMessageEvent_2011;
		case IntermediateCatchMessageEventEditPart.VISUAL_ID:
			return IntermediateCatchMessageEvent_2013;
		case IntermediateThrowMessageEventEditPart.VISUAL_ID:
			return IntermediateThrowMessageEvent_2014;
		case TextAnnotationEditPart.VISUAL_ID:
			return TextAnnotation_2015;
		case IntermediateCatchTimerEventEditPart.VISUAL_ID:
			return IntermediateCatchTimerEvent_2017;
		case StartTimerEventEditPart.VISUAL_ID:
			return StartTimerEvent_2016;
		case CatchLinkEventEditPart.VISUAL_ID:
			return CatchLinkEvent_2018;
		case ThrowLinkEventEditPart.VISUAL_ID:
			return ThrowLinkEvent_2019;
		case IntermediateThrowSignalEventEditPart.VISUAL_ID:
			return IntermediateThrowSignalEvent_2020;
		case IntermediateCatchSignalEventEditPart.VISUAL_ID:
			return IntermediateCatchSignalEvent_2021;
		case StartSignalEventEditPart.VISUAL_ID:
			return StartSignalEvent_2022;
		case EndSignalEventEditPart.VISUAL_ID:
			return EndSignalEvent_2023;
		case EventEditPart.VISUAL_ID:
			return Event_2024;
		case EndErrorEventEditPart.VISUAL_ID:
			return EndErrorEvent_2029;
		case StartErrorEventEditPart.VISUAL_ID:
			return StartErrorEvent_2033;
		case EndTerminatedEventEditPart.VISUAL_ID:
			return EndTerminatedEvent_2035;
		case IntermediateErrorCatchEvent2EditPart.VISUAL_ID:
			return IntermediateErrorCatchEvent_3029;
		case BoundaryMessageEventEditPart.VISUAL_ID:
			return BoundaryMessageEvent_3035;
		case NonInterruptingBoundaryTimerEventEditPart.VISUAL_ID:
			return NonInterruptingBoundaryTimerEvent_3064;
		case BoundaryTimerEventEditPart.VISUAL_ID:
			return BoundaryTimerEvent_3043;
		case BoundarySignalEventEditPart.VISUAL_ID:
			return BoundarySignalEvent_3052;
		case IntermediateErrorCatchEventEditPart.VISUAL_ID:
			return IntermediateErrorCatchEvent_3030;
		case BoundaryMessageEvent2EditPart.VISUAL_ID:
			return BoundaryMessageEvent_3036;
		case NonInterruptingBoundaryTimerEvent2EditPart.VISUAL_ID:
			return NonInterruptingBoundaryTimerEvent_3065;
		case BoundaryTimerEvent2EditPart.VISUAL_ID:
			return BoundaryTimerEvent_3044;
		case BoundarySignalEvent2EditPart.VISUAL_ID:
			return BoundarySignalEvent_3053;
		case ANDGateway2EditPart.VISUAL_ID:
			return ANDGateway_3009;
		case EndEvent2EditPart.VISUAL_ID:
			return EndEvent_3003;
		case CallActivity2EditPart.VISUAL_ID:
			return CallActivity_3063;
		case Task2EditPart.VISUAL_ID:
			return Task_3005;
		case ReceiveTask2EditPart.VISUAL_ID:
			return ReceiveTask_3026;
		case IntermediateErrorCatchEvent3EditPart.VISUAL_ID:
			return IntermediateErrorCatchEvent_3031;
		case SendTask2EditPart.VISUAL_ID:
			return SendTask_3025;
		case ServiceTask2EditPart.VISUAL_ID:
			return ServiceTask_3027;
		case IntermediateErrorCatchEvent4EditPart.VISUAL_ID:
			return IntermediateErrorCatchEvent_3032;
		case ScriptTask2EditPart.VISUAL_ID:
			return ScriptTask_3028;
		case IntermediateErrorCatchEvent5EditPart.VISUAL_ID:
			return IntermediateErrorCatchEvent_3033;
		case XORGateway2EditPart.VISUAL_ID:
			return XORGateway_3008;
		case Activity2EditPart.VISUAL_ID:
			return Activity_3006;
		case IntermediateErrorCatchEvent6EditPart.VISUAL_ID:
			return IntermediateErrorCatchEvent_3034;
		case IntermediateCatchMessageEvent2EditPart.VISUAL_ID:
			return IntermediateCatchMessageEvent_3013;
		case StartMessageEvent2EditPart.VISUAL_ID:
			return StartMessageEvent_3012;
		case EndMessageEvent2EditPart.VISUAL_ID:
			return EndMessageEvent_3011;
		case IntermediateThrowMessageEvent2EditPart.VISUAL_ID:
			return IntermediateThrowMessageEvent_3014;
		case TextAnnotation2EditPart.VISUAL_ID:
			return TextAnnotation_3015;
		case StartTimerEvent2EditPart.VISUAL_ID:
			return StartTimerEvent_3016;
		case IntermediateCatchTimerEvent2EditPart.VISUAL_ID:
			return IntermediateCatchTimerEvent_3017;
		case StartSignalEvent2EditPart.VISUAL_ID:
			return StartSignalEvent_3023;
		case IntermediateThrowSignalEvent2EditPart.VISUAL_ID:
			return IntermediateThrowSignalEvent_3022;
		case IntermediateCatchSignalEvent2EditPart.VISUAL_ID:
			return IntermediateCatchSignalEvent_3021;
		case EndSignalEvent2EditPart.VISUAL_ID:
			return EndSignalEvent_3020;
		case EndErrorEvent2EditPart.VISUAL_ID:
			return EndErrorEvent_3050;
		case EndTerminatedEvent2EditPart.VISUAL_ID:
			return EndTerminatedEvent_3062;
		case StartErrorEvent2EditPart.VISUAL_ID:
			return StartErrorEvent_3060;
		case Event2EditPart.VISUAL_ID:
			return Event_3024;
		case InclusiveGateway2EditPart.VISUAL_ID:
			return InclusiveGateway_3051;
		case LaneEditPart.VISUAL_ID:
			return Lane_3007;
		case StartEvent2EditPart.VISUAL_ID:
			return StartEvent_3002;
		case SubProcessEvent2EditPart.VISUAL_ID:
			return SubProcessEvent_3058;
		case ThrowLinkEvent2EditPart.VISUAL_ID:
			return ThrowLinkEvent_3018;
		case CatchLinkEvent2EditPart.VISUAL_ID:
			return CatchLinkEvent_3019;
		case SequenceFlowEditPart.VISUAL_ID:
			return SequenceFlow_4001;
		case MessageFlowEditPart.VISUAL_ID:
			return MessageFlow_4002;
		case TextAnnotationAttachmentEditPart.VISUAL_ID:
			return TextAnnotationAttachment_4003;
		}
		return null;
	}

	/**
	* @generated
	*/
	public static final DiagramElementTypes TYPED_INSTANCE = new DiagramElementTypes(elementTypeImages) {

		/**
		* @generated
		*/
		@Override

		public boolean isKnownElementType(IElementType elementType) {
			return org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes
					.isKnownElementType(elementType);
		}

		/**
		* @generated
		*/
		@Override

		public IElementType getElementTypeForVisualId(int visualID) {
			return org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes.getElementType(visualID);
		}

		/**
		* @generated
		*/
		@Override

		public ENamedElement getDefiningNamedElement(IAdaptable elementTypeAdapter) {
			return org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes
					.getElement(elementTypeAdapter);
		}
	};

	/**
	* @generated
	*/
	private synchronized static void initKnownElementTypes() {
		KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
		KNOWN_ELEMENT_TYPES.add(MainProcess_1000);
		KNOWN_ELEMENT_TYPES.add(ANDGateway_2009);
		KNOWN_ELEMENT_TYPES.add(StartEvent_2002);
		KNOWN_ELEMENT_TYPES.add(EndEvent_2003);
		KNOWN_ELEMENT_TYPES.add(Task_2004);
		KNOWN_ELEMENT_TYPES.add(CallActivity_2036);
		KNOWN_ELEMENT_TYPES.add(SubProcessEvent_2031);
		KNOWN_ELEMENT_TYPES.add(ReceiveTask_2025);
		KNOWN_ELEMENT_TYPES.add(SendTask_2026);
		KNOWN_ELEMENT_TYPES.add(ServiceTask_2027);
		KNOWN_ELEMENT_TYPES.add(ScriptTask_2028);
		KNOWN_ELEMENT_TYPES.add(XORGateway_2008);
		KNOWN_ELEMENT_TYPES.add(InclusiveGateway_2030);
		KNOWN_ELEMENT_TYPES.add(Activity_2006);
		KNOWN_ELEMENT_TYPES.add(Pool_2007);
		KNOWN_ELEMENT_TYPES.add(StartMessageEvent_2010);
		KNOWN_ELEMENT_TYPES.add(EndMessageEvent_2011);
		KNOWN_ELEMENT_TYPES.add(IntermediateCatchMessageEvent_2013);
		KNOWN_ELEMENT_TYPES.add(IntermediateThrowMessageEvent_2014);
		KNOWN_ELEMENT_TYPES.add(TextAnnotation_2015);
		KNOWN_ELEMENT_TYPES.add(IntermediateCatchTimerEvent_2017);
		KNOWN_ELEMENT_TYPES.add(StartTimerEvent_2016);
		KNOWN_ELEMENT_TYPES.add(CatchLinkEvent_2018);
		KNOWN_ELEMENT_TYPES.add(ThrowLinkEvent_2019);
		KNOWN_ELEMENT_TYPES.add(IntermediateThrowSignalEvent_2020);
		KNOWN_ELEMENT_TYPES.add(IntermediateCatchSignalEvent_2021);
		KNOWN_ELEMENT_TYPES.add(StartSignalEvent_2022);
		KNOWN_ELEMENT_TYPES.add(EndSignalEvent_2023);
		KNOWN_ELEMENT_TYPES.add(Event_2024);
		KNOWN_ELEMENT_TYPES.add(EndErrorEvent_2029);
		KNOWN_ELEMENT_TYPES.add(StartErrorEvent_2033);
		KNOWN_ELEMENT_TYPES.add(EndTerminatedEvent_2035);
		KNOWN_ELEMENT_TYPES.add(IntermediateErrorCatchEvent_3029);
		KNOWN_ELEMENT_TYPES.add(BoundaryMessageEvent_3035);
		KNOWN_ELEMENT_TYPES.add(NonInterruptingBoundaryTimerEvent_3064);
		KNOWN_ELEMENT_TYPES.add(BoundaryTimerEvent_3043);
		KNOWN_ELEMENT_TYPES.add(BoundarySignalEvent_3052);
		KNOWN_ELEMENT_TYPES.add(IntermediateErrorCatchEvent_3030);
		KNOWN_ELEMENT_TYPES.add(BoundaryMessageEvent_3036);
		KNOWN_ELEMENT_TYPES.add(NonInterruptingBoundaryTimerEvent_3065);
		KNOWN_ELEMENT_TYPES.add(BoundaryTimerEvent_3044);
		KNOWN_ELEMENT_TYPES.add(BoundarySignalEvent_3053);
		KNOWN_ELEMENT_TYPES.add(ANDGateway_3009);
		KNOWN_ELEMENT_TYPES.add(EndEvent_3003);
		KNOWN_ELEMENT_TYPES.add(CallActivity_3063);
		KNOWN_ELEMENT_TYPES.add(Task_3005);
		KNOWN_ELEMENT_TYPES.add(ReceiveTask_3026);
		KNOWN_ELEMENT_TYPES.add(IntermediateErrorCatchEvent_3031);
		KNOWN_ELEMENT_TYPES.add(SendTask_3025);
		KNOWN_ELEMENT_TYPES.add(ServiceTask_3027);
		KNOWN_ELEMENT_TYPES.add(IntermediateErrorCatchEvent_3032);
		KNOWN_ELEMENT_TYPES.add(ScriptTask_3028);
		KNOWN_ELEMENT_TYPES.add(IntermediateErrorCatchEvent_3033);
		KNOWN_ELEMENT_TYPES.add(XORGateway_3008);
		KNOWN_ELEMENT_TYPES.add(Activity_3006);
		KNOWN_ELEMENT_TYPES.add(IntermediateErrorCatchEvent_3034);
		KNOWN_ELEMENT_TYPES.add(IntermediateCatchMessageEvent_3013);
		KNOWN_ELEMENT_TYPES.add(StartMessageEvent_3012);
		KNOWN_ELEMENT_TYPES.add(EndMessageEvent_3011);
		KNOWN_ELEMENT_TYPES.add(IntermediateThrowMessageEvent_3014);
		KNOWN_ELEMENT_TYPES.add(TextAnnotation_3015);
		KNOWN_ELEMENT_TYPES.add(StartTimerEvent_3016);
		KNOWN_ELEMENT_TYPES.add(IntermediateCatchTimerEvent_3017);
		KNOWN_ELEMENT_TYPES.add(StartSignalEvent_3023);
		KNOWN_ELEMENT_TYPES.add(IntermediateThrowSignalEvent_3022);
		KNOWN_ELEMENT_TYPES.add(IntermediateCatchSignalEvent_3021);
		KNOWN_ELEMENT_TYPES.add(EndSignalEvent_3020);
		KNOWN_ELEMENT_TYPES.add(EndErrorEvent_3050);
		KNOWN_ELEMENT_TYPES.add(EndTerminatedEvent_3062);
		KNOWN_ELEMENT_TYPES.add(StartErrorEvent_3060);
		KNOWN_ELEMENT_TYPES.add(Event_3024);
		KNOWN_ELEMENT_TYPES.add(InclusiveGateway_3051);
		KNOWN_ELEMENT_TYPES.add(Lane_3007);
		KNOWN_ELEMENT_TYPES.add(StartEvent_3002);
		KNOWN_ELEMENT_TYPES.add(SubProcessEvent_3058);
		KNOWN_ELEMENT_TYPES.add(ThrowLinkEvent_3018);
		KNOWN_ELEMENT_TYPES.add(CatchLinkEvent_3019);
		KNOWN_ELEMENT_TYPES.add(SequenceFlow_4001);
		KNOWN_ELEMENT_TYPES.add(MessageFlow_4002);
		KNOWN_ELEMENT_TYPES.add(TextAnnotationAttachment_4003);
	}

	/**
	* @generated
	*/
	private synchronized static void initElements() {
		elements = new IdentityHashMap<IElementType, ENamedElement>();

		elements.put(MainProcess_1000, ProcessPackage.eINSTANCE.getMainProcess());

		elements.put(ANDGateway_2009, ProcessPackage.eINSTANCE.getANDGateway());

		elements.put(StartEvent_2002, ProcessPackage.eINSTANCE.getStartEvent());

		elements.put(EndEvent_2003, ProcessPackage.eINSTANCE.getEndEvent());

		elements.put(Task_2004, ProcessPackage.eINSTANCE.getTask());

		elements.put(CallActivity_2036, ProcessPackage.eINSTANCE.getCallActivity());

		elements.put(SubProcessEvent_2031, ProcessPackage.eINSTANCE.getSubProcessEvent());

		elements.put(ReceiveTask_2025, ProcessPackage.eINSTANCE.getReceiveTask());

		elements.put(SendTask_2026, ProcessPackage.eINSTANCE.getSendTask());

		elements.put(ServiceTask_2027, ProcessPackage.eINSTANCE.getServiceTask());

		elements.put(ScriptTask_2028, ProcessPackage.eINSTANCE.getScriptTask());

		elements.put(XORGateway_2008, ProcessPackage.eINSTANCE.getXORGateway());

		elements.put(InclusiveGateway_2030, ProcessPackage.eINSTANCE.getInclusiveGateway());

		elements.put(Activity_2006, ProcessPackage.eINSTANCE.getActivity());

		elements.put(Pool_2007, ProcessPackage.eINSTANCE.getPool());

		elements.put(StartMessageEvent_2010, ProcessPackage.eINSTANCE.getStartMessageEvent());

		elements.put(EndMessageEvent_2011, ProcessPackage.eINSTANCE.getEndMessageEvent());

		elements.put(IntermediateCatchMessageEvent_2013, ProcessPackage.eINSTANCE.getIntermediateCatchMessageEvent());

		elements.put(IntermediateThrowMessageEvent_2014, ProcessPackage.eINSTANCE.getIntermediateThrowMessageEvent());

		elements.put(TextAnnotation_2015, ProcessPackage.eINSTANCE.getTextAnnotation());

		elements.put(IntermediateCatchTimerEvent_2017, ProcessPackage.eINSTANCE.getIntermediateCatchTimerEvent());

		elements.put(StartTimerEvent_2016, ProcessPackage.eINSTANCE.getStartTimerEvent());

		elements.put(CatchLinkEvent_2018, ProcessPackage.eINSTANCE.getCatchLinkEvent());

		elements.put(ThrowLinkEvent_2019, ProcessPackage.eINSTANCE.getThrowLinkEvent());

		elements.put(IntermediateThrowSignalEvent_2020, ProcessPackage.eINSTANCE.getIntermediateThrowSignalEvent());

		elements.put(IntermediateCatchSignalEvent_2021, ProcessPackage.eINSTANCE.getIntermediateCatchSignalEvent());

		elements.put(StartSignalEvent_2022, ProcessPackage.eINSTANCE.getStartSignalEvent());

		elements.put(EndSignalEvent_2023, ProcessPackage.eINSTANCE.getEndSignalEvent());

		elements.put(Event_2024, ProcessPackage.eINSTANCE.getEvent());

		elements.put(EndErrorEvent_2029, ProcessPackage.eINSTANCE.getEndErrorEvent());

		elements.put(StartErrorEvent_2033, ProcessPackage.eINSTANCE.getStartErrorEvent());

		elements.put(EndTerminatedEvent_2035, ProcessPackage.eINSTANCE.getEndTerminatedEvent());

		elements.put(IntermediateErrorCatchEvent_3029, ProcessPackage.eINSTANCE.getIntermediateErrorCatchEvent());

		elements.put(BoundaryMessageEvent_3035, ProcessPackage.eINSTANCE.getBoundaryMessageEvent());

		elements.put(NonInterruptingBoundaryTimerEvent_3064,
				ProcessPackage.eINSTANCE.getNonInterruptingBoundaryTimerEvent());

		elements.put(BoundaryTimerEvent_3043, ProcessPackage.eINSTANCE.getBoundaryTimerEvent());

		elements.put(BoundarySignalEvent_3052, ProcessPackage.eINSTANCE.getBoundarySignalEvent());

		elements.put(IntermediateErrorCatchEvent_3030, ProcessPackage.eINSTANCE.getIntermediateErrorCatchEvent());

		elements.put(BoundaryMessageEvent_3036, ProcessPackage.eINSTANCE.getBoundaryMessageEvent());

		elements.put(NonInterruptingBoundaryTimerEvent_3065,
				ProcessPackage.eINSTANCE.getNonInterruptingBoundaryTimerEvent());

		elements.put(BoundaryTimerEvent_3044, ProcessPackage.eINSTANCE.getBoundaryTimerEvent());

		elements.put(BoundarySignalEvent_3053, ProcessPackage.eINSTANCE.getBoundarySignalEvent());

		elements.put(ANDGateway_3009, ProcessPackage.eINSTANCE.getANDGateway());

		elements.put(EndEvent_3003, ProcessPackage.eINSTANCE.getEndEvent());

		elements.put(CallActivity_3063, ProcessPackage.eINSTANCE.getCallActivity());

		elements.put(Task_3005, ProcessPackage.eINSTANCE.getTask());

		elements.put(ReceiveTask_3026, ProcessPackage.eINSTANCE.getReceiveTask());

		elements.put(IntermediateErrorCatchEvent_3031, ProcessPackage.eINSTANCE.getIntermediateErrorCatchEvent());

		elements.put(SendTask_3025, ProcessPackage.eINSTANCE.getSendTask());

		elements.put(ServiceTask_3027, ProcessPackage.eINSTANCE.getServiceTask());

		elements.put(IntermediateErrorCatchEvent_3032, ProcessPackage.eINSTANCE.getIntermediateErrorCatchEvent());

		elements.put(ScriptTask_3028, ProcessPackage.eINSTANCE.getScriptTask());

		elements.put(IntermediateErrorCatchEvent_3033, ProcessPackage.eINSTANCE.getIntermediateErrorCatchEvent());

		elements.put(XORGateway_3008, ProcessPackage.eINSTANCE.getXORGateway());

		elements.put(Activity_3006, ProcessPackage.eINSTANCE.getActivity());

		elements.put(IntermediateErrorCatchEvent_3034, ProcessPackage.eINSTANCE.getIntermediateErrorCatchEvent());

		elements.put(IntermediateCatchMessageEvent_3013, ProcessPackage.eINSTANCE.getIntermediateCatchMessageEvent());

		elements.put(StartMessageEvent_3012, ProcessPackage.eINSTANCE.getStartMessageEvent());

		elements.put(EndMessageEvent_3011, ProcessPackage.eINSTANCE.getEndMessageEvent());

		elements.put(IntermediateThrowMessageEvent_3014, ProcessPackage.eINSTANCE.getIntermediateThrowMessageEvent());

		elements.put(TextAnnotation_3015, ProcessPackage.eINSTANCE.getTextAnnotation());

		elements.put(StartTimerEvent_3016, ProcessPackage.eINSTANCE.getStartTimerEvent());

		elements.put(IntermediateCatchTimerEvent_3017, ProcessPackage.eINSTANCE.getIntermediateCatchTimerEvent());

		elements.put(StartSignalEvent_3023, ProcessPackage.eINSTANCE.getStartSignalEvent());

		elements.put(IntermediateThrowSignalEvent_3022, ProcessPackage.eINSTANCE.getIntermediateThrowSignalEvent());

		elements.put(IntermediateCatchSignalEvent_3021, ProcessPackage.eINSTANCE.getIntermediateCatchSignalEvent());

		elements.put(EndSignalEvent_3020, ProcessPackage.eINSTANCE.getEndSignalEvent());

		elements.put(EndErrorEvent_3050, ProcessPackage.eINSTANCE.getEndErrorEvent());

		elements.put(EndTerminatedEvent_3062, ProcessPackage.eINSTANCE.getEndTerminatedEvent());

		elements.put(StartErrorEvent_3060, ProcessPackage.eINSTANCE.getStartErrorEvent());

		elements.put(Event_3024, ProcessPackage.eINSTANCE.getEvent());

		elements.put(InclusiveGateway_3051, ProcessPackage.eINSTANCE.getInclusiveGateway());

		elements.put(Lane_3007, ProcessPackage.eINSTANCE.getLane());

		elements.put(StartEvent_3002, ProcessPackage.eINSTANCE.getStartEvent());

		elements.put(SubProcessEvent_3058, ProcessPackage.eINSTANCE.getSubProcessEvent());

		elements.put(ThrowLinkEvent_3018, ProcessPackage.eINSTANCE.getThrowLinkEvent());

		elements.put(CatchLinkEvent_3019, ProcessPackage.eINSTANCE.getCatchLinkEvent());

		elements.put(SequenceFlow_4001, ProcessPackage.eINSTANCE.getSequenceFlow());

		elements.put(MessageFlow_4002, ProcessPackage.eINSTANCE.getMessageFlow());

		elements.put(TextAnnotationAttachment_4003, ProcessPackage.eINSTANCE.getTextAnnotationAttachment());
	}

}
