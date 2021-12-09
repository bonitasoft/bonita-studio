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
package org.bonitasoft.studio.model.process.diagram.navigator;

import org.bonitasoft.studio.model.process.Event;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.edit.parts.*;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditorPlugin;
import org.bonitasoft.studio.model.process.diagram.part.ProcessVisualIDRegistry;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserOptions;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ITreePathLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonLabelProvider;

/**
 * @generated
 */
public class ProcessNavigatorLabelProvider extends LabelProvider
		implements ICommonLabelProvider, ITreePathLabelProvider {

	/**
	* @generated
	*/
	static {
		ProcessDiagramEditorPlugin.getInstance().getImageRegistry().put("Navigator?UnknownElement", //$NON-NLS-1$
				ImageDescriptor.getMissingImageDescriptor());
		ProcessDiagramEditorPlugin.getInstance().getImageRegistry().put("Navigator?ImageNotFound", //$NON-NLS-1$
				ImageDescriptor.getMissingImageDescriptor());
	}

	/**
	* @generated
	*/
	public void updateLabel(ViewerLabel label, TreePath elementPath) {
		Object element = elementPath.getLastSegment();
		if (element instanceof ProcessNavigatorItem && !isOwnView(((ProcessNavigatorItem) element).getView())) {
			return;
		}
		label.setText(getText(element));
		label.setImage(getImage(element));
	}

	/**
	* @generated
	*/
	public Image getImage(Object element) {
		if (element instanceof ProcessNavigatorGroup) {
			ProcessNavigatorGroup group = (ProcessNavigatorGroup) element;
			return ProcessDiagramEditorPlugin.getInstance().getBundledImage(group.getIcon());
		}

		if (element instanceof ProcessNavigatorItem) {
			ProcessNavigatorItem navigatorItem = (ProcessNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return super.getImage(element);
			}
			return getImage(navigatorItem.getView());
		}

		return super.getImage(element);
	}

	/**
	* @generated
	*/
	public Image getImage(View view) {
		switch (ProcessVisualIDRegistry.getVisualID(view)) {
		case MainProcessEditPart.VISUAL_ID:
			return getImage("Navigator?Diagram?http://www.bonitasoft.org/ns/studio/process?MainProcess", //$NON-NLS-1$
					ProcessElementTypes.MainProcess_1000);
		case StartEventEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?StartEvent", //$NON-NLS-1$
					ProcessElementTypes.StartEvent_2002);
		case EndEventEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?EndEvent", //$NON-NLS-1$
					ProcessElementTypes.EndEvent_2003);
		case TaskEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?Task", //$NON-NLS-1$
					ProcessElementTypes.Task_2004);
		case ActivityEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?Activity", //$NON-NLS-1$
					ProcessElementTypes.Activity_2006);
		case PoolEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?Pool", //$NON-NLS-1$
					ProcessElementTypes.Pool_2007);
		case XORGatewayEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?XORGateway", //$NON-NLS-1$
					ProcessElementTypes.XORGateway_2008);
		case ANDGatewayEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?ANDGateway", //$NON-NLS-1$
					ProcessElementTypes.ANDGateway_2009);
		case StartMessageEventEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?StartMessageEvent", //$NON-NLS-1$
					ProcessElementTypes.StartMessageEvent_2010);
		case EndMessageEventEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?EndMessageEvent", //$NON-NLS-1$
					ProcessElementTypes.EndMessageEvent_2011);
		case IntermediateCatchMessageEventEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?IntermediateCatchMessageEvent", //$NON-NLS-1$
					ProcessElementTypes.IntermediateCatchMessageEvent_2013);
		case IntermediateThrowMessageEventEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?IntermediateThrowMessageEvent", //$NON-NLS-1$
					ProcessElementTypes.IntermediateThrowMessageEvent_2014);
		case TextAnnotationEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?TextAnnotation", //$NON-NLS-1$
					ProcessElementTypes.TextAnnotation_2015);
		case StartTimerEventEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?StartTimerEvent", //$NON-NLS-1$
					ProcessElementTypes.StartTimerEvent_2016);
		case IntermediateCatchTimerEventEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?IntermediateCatchTimerEvent", //$NON-NLS-1$
					ProcessElementTypes.IntermediateCatchTimerEvent_2017);
		case CatchLinkEventEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?CatchLinkEvent", //$NON-NLS-1$
					ProcessElementTypes.CatchLinkEvent_2018);
		case ThrowLinkEventEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?ThrowLinkEvent", //$NON-NLS-1$
					ProcessElementTypes.ThrowLinkEvent_2019);
		case IntermediateThrowSignalEventEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?IntermediateThrowSignalEvent", //$NON-NLS-1$
					ProcessElementTypes.IntermediateThrowSignalEvent_2020);
		case IntermediateCatchSignalEventEditPart.VISUAL_ID:
			return getImage(
					"Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?IntermediateCatchSignalEvent", //$NON-NLS-1$
					ProcessElementTypes.IntermediateCatchSignalEvent_2021);
		case StartSignalEventEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?StartSignalEvent", //$NON-NLS-1$
					ProcessElementTypes.StartSignalEvent_2022);
		case EndSignalEventEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?EndSignalEvent", //$NON-NLS-1$
					ProcessElementTypes.EndSignalEvent_2023);
		case EventEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?Event", //$NON-NLS-1$
					ProcessElementTypes.Event_2024);
		case ReceiveTaskEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?ReceiveTask", //$NON-NLS-1$
					ProcessElementTypes.ReceiveTask_2025);
		case SendTaskEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?SendTask", //$NON-NLS-1$
					ProcessElementTypes.SendTask_2026);
		case ServiceTaskEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?ServiceTask", //$NON-NLS-1$
					ProcessElementTypes.ServiceTask_2027);
		case ScriptTaskEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?ScriptTask", //$NON-NLS-1$
					ProcessElementTypes.ScriptTask_2028);
		case EndErrorEventEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?EndErrorEvent", //$NON-NLS-1$
					ProcessElementTypes.EndErrorEvent_2029);
		case InclusiveGatewayEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?InclusiveGateway", //$NON-NLS-1$
					ProcessElementTypes.InclusiveGateway_2030);
		case SubProcessEventEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?SubProcessEvent", //$NON-NLS-1$
					ProcessElementTypes.SubProcessEvent_2031);
		case StartErrorEventEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?StartErrorEvent", //$NON-NLS-1$
					ProcessElementTypes.StartErrorEvent_2033);
		case EndTerminatedEventEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?EndTerminatedEvent", //$NON-NLS-1$
					ProcessElementTypes.EndTerminatedEvent_2035);
		case CallActivityEditPart.VISUAL_ID:
			return getImage("Navigator?TopLevelNode?http://www.bonitasoft.org/ns/studio/process?CallActivity", //$NON-NLS-1$
					ProcessElementTypes.CallActivity_2036);
		case StartEvent2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?StartEvent", //$NON-NLS-1$
					ProcessElementTypes.StartEvent_3002);
		case EndEvent2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?EndEvent", //$NON-NLS-1$
					ProcessElementTypes.EndEvent_3003);
		case Task2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?Task", //$NON-NLS-1$
					ProcessElementTypes.Task_3005);
		case Activity2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?Activity", //$NON-NLS-1$
					ProcessElementTypes.Activity_3006);
		case LaneEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?Lane", //$NON-NLS-1$
					ProcessElementTypes.Lane_3007);
		case XORGateway2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?XORGateway", //$NON-NLS-1$
					ProcessElementTypes.XORGateway_3008);
		case ANDGateway2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?ANDGateway", //$NON-NLS-1$
					ProcessElementTypes.ANDGateway_3009);
		case EndMessageEvent2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?EndMessageEvent", //$NON-NLS-1$
					ProcessElementTypes.EndMessageEvent_3011);
		case StartMessageEvent2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?StartMessageEvent", //$NON-NLS-1$
					ProcessElementTypes.StartMessageEvent_3012);
		case IntermediateCatchMessageEvent2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?IntermediateCatchMessageEvent", //$NON-NLS-1$
					ProcessElementTypes.IntermediateCatchMessageEvent_3013);
		case IntermediateThrowMessageEvent2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?IntermediateThrowMessageEvent", //$NON-NLS-1$
					ProcessElementTypes.IntermediateThrowMessageEvent_3014);
		case TextAnnotation2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?TextAnnotation", //$NON-NLS-1$
					ProcessElementTypes.TextAnnotation_3015);
		case StartTimerEvent2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?StartTimerEvent", //$NON-NLS-1$
					ProcessElementTypes.StartTimerEvent_3016);
		case IntermediateCatchTimerEvent2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?IntermediateCatchTimerEvent", //$NON-NLS-1$
					ProcessElementTypes.IntermediateCatchTimerEvent_3017);
		case ThrowLinkEvent2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?ThrowLinkEvent", //$NON-NLS-1$
					ProcessElementTypes.ThrowLinkEvent_3018);
		case CatchLinkEvent2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?CatchLinkEvent", //$NON-NLS-1$
					ProcessElementTypes.CatchLinkEvent_3019);
		case EndSignalEvent2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?EndSignalEvent", //$NON-NLS-1$
					ProcessElementTypes.EndSignalEvent_3020);
		case IntermediateCatchSignalEvent2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?IntermediateCatchSignalEvent", //$NON-NLS-1$
					ProcessElementTypes.IntermediateCatchSignalEvent_3021);
		case IntermediateThrowSignalEvent2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?IntermediateThrowSignalEvent", //$NON-NLS-1$
					ProcessElementTypes.IntermediateThrowSignalEvent_3022);
		case StartSignalEvent2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?StartSignalEvent", //$NON-NLS-1$
					ProcessElementTypes.StartSignalEvent_3023);
		case Event2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?Event", //$NON-NLS-1$
					ProcessElementTypes.Event_3024);
		case SendTask2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?SendTask", //$NON-NLS-1$
					ProcessElementTypes.SendTask_3025);
		case ReceiveTask2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?ReceiveTask", //$NON-NLS-1$
					ProcessElementTypes.ReceiveTask_3026);
		case ServiceTask2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?ServiceTask", //$NON-NLS-1$
					ProcessElementTypes.ServiceTask_3027);
		case ScriptTask2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?ScriptTask", //$NON-NLS-1$
					ProcessElementTypes.ScriptTask_3028);
		case IntermediateErrorCatchEvent2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?IntermediateErrorCatchEvent", //$NON-NLS-1$
					ProcessElementTypes.IntermediateErrorCatchEvent_3029);
		case IntermediateErrorCatchEventEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?IntermediateErrorCatchEvent", //$NON-NLS-1$
					ProcessElementTypes.IntermediateErrorCatchEvent_3030);
		case IntermediateErrorCatchEvent3EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?IntermediateErrorCatchEvent", //$NON-NLS-1$
					ProcessElementTypes.IntermediateErrorCatchEvent_3031);
		case IntermediateErrorCatchEvent4EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?IntermediateErrorCatchEvent", //$NON-NLS-1$
					ProcessElementTypes.IntermediateErrorCatchEvent_3032);
		case IntermediateErrorCatchEvent5EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?IntermediateErrorCatchEvent", //$NON-NLS-1$
					ProcessElementTypes.IntermediateErrorCatchEvent_3033);
		case IntermediateErrorCatchEvent6EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?IntermediateErrorCatchEvent", //$NON-NLS-1$
					ProcessElementTypes.IntermediateErrorCatchEvent_3034);
		case BoundaryMessageEventEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?BoundaryMessageEvent", //$NON-NLS-1$
					ProcessElementTypes.BoundaryMessageEvent_3035);
		case BoundaryMessageEvent2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?BoundaryMessageEvent", //$NON-NLS-1$
					ProcessElementTypes.BoundaryMessageEvent_3036);
		case BoundaryTimerEventEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?BoundaryTimerEvent", //$NON-NLS-1$
					ProcessElementTypes.BoundaryTimerEvent_3043);
		case BoundaryTimerEvent2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?BoundaryTimerEvent", //$NON-NLS-1$
					ProcessElementTypes.BoundaryTimerEvent_3044);
		case EndErrorEvent2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?EndErrorEvent", //$NON-NLS-1$
					ProcessElementTypes.EndErrorEvent_3050);
		case InclusiveGateway2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?InclusiveGateway", //$NON-NLS-1$
					ProcessElementTypes.InclusiveGateway_3051);
		case BoundarySignalEventEditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?BoundarySignalEvent", //$NON-NLS-1$
					ProcessElementTypes.BoundarySignalEvent_3052);
		case BoundarySignalEvent2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?BoundarySignalEvent", //$NON-NLS-1$
					ProcessElementTypes.BoundarySignalEvent_3053);
		case SubProcessEvent2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?SubProcessEvent", //$NON-NLS-1$
					ProcessElementTypes.SubProcessEvent_3058);
		case StartErrorEvent2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?StartErrorEvent", //$NON-NLS-1$
					ProcessElementTypes.StartErrorEvent_3060);
		case EndTerminatedEvent2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?EndTerminatedEvent", //$NON-NLS-1$
					ProcessElementTypes.EndTerminatedEvent_3062);
		case CallActivity2EditPart.VISUAL_ID:
			return getImage("Navigator?Node?http://www.bonitasoft.org/ns/studio/process?CallActivity", //$NON-NLS-1$
					ProcessElementTypes.CallActivity_3063);
		case NonInterruptingBoundaryTimerEventEditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.bonitasoft.org/ns/studio/process?NonInterruptingBoundaryTimerEvent", //$NON-NLS-1$
					ProcessElementTypes.NonInterruptingBoundaryTimerEvent_3064);
		case NonInterruptingBoundaryTimerEvent2EditPart.VISUAL_ID:
			return getImage(
					"Navigator?Node?http://www.bonitasoft.org/ns/studio/process?NonInterruptingBoundaryTimerEvent", //$NON-NLS-1$
					ProcessElementTypes.NonInterruptingBoundaryTimerEvent_3065);
		case SequenceFlowEditPart.VISUAL_ID:
			return getImage("Navigator?Link?http://www.bonitasoft.org/ns/studio/process?SequenceFlow", //$NON-NLS-1$
					ProcessElementTypes.SequenceFlow_4001);
		case MessageFlowEditPart.VISUAL_ID:
			return getImage("Navigator?Link?http://www.bonitasoft.org/ns/studio/process?MessageFlow", //$NON-NLS-1$
					ProcessElementTypes.MessageFlow_4002);
		case TextAnnotationAttachmentEditPart.VISUAL_ID:
			return getImage("Navigator?Link?http://www.bonitasoft.org/ns/studio/process?TextAnnotationAttachment", //$NON-NLS-1$
					ProcessElementTypes.TextAnnotationAttachment_4003);
		}
		return getImage("Navigator?UnknownElement", null); //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private Image getImage(String key, IElementType elementType) {
		ImageRegistry imageRegistry = ProcessDiagramEditorPlugin.getInstance().getImageRegistry();
		Image image = imageRegistry.get(key);
		if (image == null && elementType != null && ProcessElementTypes.isKnownElementType(elementType)) {
			image = ProcessElementTypes.getImage(elementType);
			imageRegistry.put(key, image);
		}

		if (image == null) {
			image = imageRegistry.get("Navigator?ImageNotFound"); //$NON-NLS-1$
			imageRegistry.put(key, image);
		}
		return image;
	}

	/**
	* @generated
	*/
	public String getText(Object element) {
		if (element instanceof ProcessNavigatorGroup) {
			ProcessNavigatorGroup group = (ProcessNavigatorGroup) element;
			return group.getGroupName();
		}

		if (element instanceof ProcessNavigatorItem) {
			ProcessNavigatorItem navigatorItem = (ProcessNavigatorItem) element;
			if (!isOwnView(navigatorItem.getView())) {
				return null;
			}
			return getText(navigatorItem.getView());
		}

		return super.getText(element);
	}

	/**
	* @generated
	*/
	public String getText(View view) {
		if (view.getElement() != null && view.getElement().eIsProxy()) {
			return getUnresolvedDomainElementProxyText(view);
		}
		switch (ProcessVisualIDRegistry.getVisualID(view)) {
		case MainProcessEditPart.VISUAL_ID:
			return getMainProcess_1000Text(view);
		case StartEventEditPart.VISUAL_ID:
			return getStartEvent_2002Text(view);
		case EndEventEditPart.VISUAL_ID:
			return getEndEvent_2003Text(view);
		case TaskEditPart.VISUAL_ID:
			return getTask_2004Text(view);
		case ActivityEditPart.VISUAL_ID:
			return getActivity_2006Text(view);
		case PoolEditPart.VISUAL_ID:
			return getPool_2007Text(view);
		case XORGatewayEditPart.VISUAL_ID:
			return getXORGateway_2008Text(view);
		case ANDGatewayEditPart.VISUAL_ID:
			return getANDGateway_2009Text(view);
		case StartMessageEventEditPart.VISUAL_ID:
			return getStartMessageEvent_2010Text(view);
		case EndMessageEventEditPart.VISUAL_ID:
			return getEndMessageEvent_2011Text(view);
		case IntermediateCatchMessageEventEditPart.VISUAL_ID:
			return getIntermediateCatchMessageEvent_2013Text(view);
		case IntermediateThrowMessageEventEditPart.VISUAL_ID:
			return getIntermediateThrowMessageEvent_2014Text(view);
		case TextAnnotationEditPart.VISUAL_ID:
			return getTextAnnotation_2015Text(view);
		case StartTimerEventEditPart.VISUAL_ID:
			return getStartTimerEvent_2016Text(view);
		case IntermediateCatchTimerEventEditPart.VISUAL_ID:
			return getIntermediateCatchTimerEvent_2017Text(view);
		case CatchLinkEventEditPart.VISUAL_ID:
			return getCatchLinkEvent_2018Text(view);
		case ThrowLinkEventEditPart.VISUAL_ID:
			return getThrowLinkEvent_2019Text(view);
		case IntermediateThrowSignalEventEditPart.VISUAL_ID:
			return getIntermediateThrowSignalEvent_2020Text(view);
		case IntermediateCatchSignalEventEditPart.VISUAL_ID:
			return getIntermediateCatchSignalEvent_2021Text(view);
		case StartSignalEventEditPart.VISUAL_ID:
			return getStartSignalEvent_2022Text(view);
		case EndSignalEventEditPart.VISUAL_ID:
			return getEndSignalEvent_2023Text(view);
		case EventEditPart.VISUAL_ID:
			return getEvent_2024Text(view);
		case ReceiveTaskEditPart.VISUAL_ID:
			return getReceiveTask_2025Text(view);
		case SendTaskEditPart.VISUAL_ID:
			return getSendTask_2026Text(view);
		case ServiceTaskEditPart.VISUAL_ID:
			return getServiceTask_2027Text(view);
		case ScriptTaskEditPart.VISUAL_ID:
			return getScriptTask_2028Text(view);
		case EndErrorEventEditPart.VISUAL_ID:
			return getEndErrorEvent_2029Text(view);
		case InclusiveGatewayEditPart.VISUAL_ID:
			return getInclusiveGateway_2030Text(view);
		case SubProcessEventEditPart.VISUAL_ID:
			return getSubProcessEvent_2031Text(view);
		case StartErrorEventEditPart.VISUAL_ID:
			return getStartErrorEvent_2033Text(view);
		case EndTerminatedEventEditPart.VISUAL_ID:
			return getEndTerminatedEvent_2035Text(view);
		case CallActivityEditPart.VISUAL_ID:
			return getCallActivity_2036Text(view);
		case StartEvent2EditPart.VISUAL_ID:
			return getStartEvent_3002Text(view);
		case EndEvent2EditPart.VISUAL_ID:
			return getEndEvent_3003Text(view);
		case Task2EditPart.VISUAL_ID:
			return getTask_3005Text(view);
		case Activity2EditPart.VISUAL_ID:
			return getActivity_3006Text(view);
		case LaneEditPart.VISUAL_ID:
			return getLane_3007Text(view);
		case XORGateway2EditPart.VISUAL_ID:
			return getXORGateway_3008Text(view);
		case ANDGateway2EditPart.VISUAL_ID:
			return getANDGateway_3009Text(view);
		case EndMessageEvent2EditPart.VISUAL_ID:
			return getEndMessageEvent_3011Text(view);
		case StartMessageEvent2EditPart.VISUAL_ID:
			return getStartMessageEvent_3012Text(view);
		case IntermediateCatchMessageEvent2EditPart.VISUAL_ID:
			return getIntermediateCatchMessageEvent_3013Text(view);
		case IntermediateThrowMessageEvent2EditPart.VISUAL_ID:
			return getIntermediateThrowMessageEvent_3014Text(view);
		case TextAnnotation2EditPart.VISUAL_ID:
			return getTextAnnotation_3015Text(view);
		case StartTimerEvent2EditPart.VISUAL_ID:
			return getStartTimerEvent_3016Text(view);
		case IntermediateCatchTimerEvent2EditPart.VISUAL_ID:
			return getIntermediateCatchTimerEvent_3017Text(view);
		case ThrowLinkEvent2EditPart.VISUAL_ID:
			return getThrowLinkEvent_3018Text(view);
		case CatchLinkEvent2EditPart.VISUAL_ID:
			return getCatchLinkEvent_3019Text(view);
		case EndSignalEvent2EditPart.VISUAL_ID:
			return getEndSignalEvent_3020Text(view);
		case IntermediateCatchSignalEvent2EditPart.VISUAL_ID:
			return getIntermediateCatchSignalEvent_3021Text(view);
		case IntermediateThrowSignalEvent2EditPart.VISUAL_ID:
			return getIntermediateThrowSignalEvent_3022Text(view);
		case StartSignalEvent2EditPart.VISUAL_ID:
			return getStartSignalEvent_3023Text(view);
		case Event2EditPart.VISUAL_ID:
			return getEvent_3024Text(view);
		case SendTask2EditPart.VISUAL_ID:
			return getSendTask_3025Text(view);
		case ReceiveTask2EditPart.VISUAL_ID:
			return getReceiveTask_3026Text(view);
		case ServiceTask2EditPart.VISUAL_ID:
			return getServiceTask_3027Text(view);
		case ScriptTask2EditPart.VISUAL_ID:
			return getScriptTask_3028Text(view);
		case IntermediateErrorCatchEvent2EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3029Text(view);
		case IntermediateErrorCatchEventEditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3030Text(view);
		case IntermediateErrorCatchEvent3EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3031Text(view);
		case IntermediateErrorCatchEvent4EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3032Text(view);
		case IntermediateErrorCatchEvent5EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3033Text(view);
		case IntermediateErrorCatchEvent6EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEvent_3034Text(view);
		case BoundaryMessageEventEditPart.VISUAL_ID:
			return getBoundaryMessageEvent_3035Text(view);
		case BoundaryMessageEvent2EditPart.VISUAL_ID:
			return getBoundaryMessageEvent_3036Text(view);
		case BoundaryTimerEventEditPart.VISUAL_ID:
			return getBoundaryTimerEvent_3043Text(view);
		case BoundaryTimerEvent2EditPart.VISUAL_ID:
			return getBoundaryTimerEvent_3044Text(view);
		case EndErrorEvent2EditPart.VISUAL_ID:
			return getEndErrorEvent_3050Text(view);
		case InclusiveGateway2EditPart.VISUAL_ID:
			return getInclusiveGateway_3051Text(view);
		case BoundarySignalEventEditPart.VISUAL_ID:
			return getBoundarySignalEvent_3052Text(view);
		case BoundarySignalEvent2EditPart.VISUAL_ID:
			return getBoundarySignalEvent_3053Text(view);
		case SubProcessEvent2EditPart.VISUAL_ID:
			return getSubProcessEvent_3058Text(view);
		case StartErrorEvent2EditPart.VISUAL_ID:
			return getStartErrorEvent_3060Text(view);
		case EndTerminatedEvent2EditPart.VISUAL_ID:
			return getEndTerminatedEvent_3062Text(view);
		case CallActivity2EditPart.VISUAL_ID:
			return getCallActivity_3063Text(view);
		case NonInterruptingBoundaryTimerEventEditPart.VISUAL_ID:
			return getNonInterruptingBoundaryTimerEvent_3064Text(view);
		case NonInterruptingBoundaryTimerEvent2EditPart.VISUAL_ID:
			return getNonInterruptingBoundaryTimerEvent_3065Text(view);
		case SequenceFlowEditPart.VISUAL_ID:
			return getSequenceFlow_4001Text(view);
		case MessageFlowEditPart.VISUAL_ID:
			return getMessageFlow_4002Text(view);
		case TextAnnotationAttachmentEditPart.VISUAL_ID:
			return getTextAnnotationAttachment_4003Text(view);
		}
		return getUnknownElementText(view);
	}

	/**
	* @generated
	*/
	private String getMainProcess_1000Text(View view) {
		MainProcess domainModelElement = (MainProcess) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 1000); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getStartEvent_2002Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.StartEvent_2002,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(StartEventLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5021); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getEndEvent_2003Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.EndEvent_2003,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(EndEventLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5022); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getTask_2004Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.Task_2004,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(TaskNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getActivity_2006Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.Activity_2006,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(ActivityNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5003); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getPool_2007Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.Pool_2007,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(PoolNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5008); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getXORGateway_2008Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.XORGateway_2008,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(XORGatewayLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5023); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getANDGateway_2009Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.ANDGateway_2009,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(ANDGatewayLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5019); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getStartMessageEvent_2010Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.StartMessageEvent_2010,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(StartMessageEventLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5039); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getEndMessageEvent_2011Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.EndMessageEvent_2011,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(EndMessageEventLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5040); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getIntermediateCatchMessageEvent_2013Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.IntermediateCatchMessageEvent_2013,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(IntermediateCatchMessageEventLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5041); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getIntermediateThrowMessageEvent_2014Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.IntermediateThrowMessageEvent_2014,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(IntermediateThrowMessageEventLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5042); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getTextAnnotation_2015Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.TextAnnotation_2015,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(TextAnnotationTextEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5009); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getStartTimerEvent_2016Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.StartTimerEvent_2016,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(StartTimerEventLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5044); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getIntermediateCatchTimerEvent_2017Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.IntermediateCatchTimerEvent_2017,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(IntermediateCatchTimerEventLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5043); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getCatchLinkEvent_2018Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.CatchLinkEvent_2018,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(CatchLinkEventLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5045); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getThrowLinkEvent_2019Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.ThrowLinkEvent_2019,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(ThrowLinkEventLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5046); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getIntermediateThrowSignalEvent_2020Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.IntermediateThrowSignalEvent_2020,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(IntermediateThrowSignalEventLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5047); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getIntermediateCatchSignalEvent_2021Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.IntermediateCatchSignalEvent_2021,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(IntermediateCatchSignalEventLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5048); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getStartSignalEvent_2022Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.StartSignalEvent_2022,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(StartSignalEventLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5049); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getEndSignalEvent_2023Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.EndSignalEvent_2023,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(EndSignalEventLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5050); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getEvent_2024Text(View view) {
		Event domainModelElement = (Event) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 2024); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getReceiveTask_2025Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.ReceiveTask_2025,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(ReceiveTaskLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5013); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getSendTask_2026Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.SendTask_2026,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(SendTaskLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5014); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getServiceTask_2027Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.ServiceTask_2027,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(ServiceTaskLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5015); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getScriptTask_2028Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.ScriptTask_2028,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(ScriptTaskLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5016); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getEndErrorEvent_2029Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.EndErrorEvent_2029,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(EndErrorEventLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5051); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getInclusiveGateway_2030Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.InclusiveGateway_2030,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(InclusiveGatewayLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5074); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getSubProcessEvent_2031Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.SubProcessEvent_2031,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(SubProcessEventLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5081); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getStartErrorEvent_2033Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.StartErrorEvent_2033,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(StartErrorEventLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5087); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getEndTerminatedEvent_2035Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.EndTerminatedEvent_2035,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(EndTerminatedEventLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5091); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getCallActivity_2036Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.CallActivity_2036,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(CallActivityNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5092); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getStartEvent_3002Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.StartEvent_3002,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(StartEventLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5024); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getEndEvent_3003Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.EndEvent_3003,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(EndEventLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5025); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getTask_3005Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.Task_3005,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(TaskName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5005); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getActivity_3006Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.Activity_3006,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(ActivityName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5006); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getLane_3007Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.Lane_3007,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(LaneNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5007); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getXORGateway_3008Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.XORGateway_3008,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(XORGatewayLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5026); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getANDGateway_3009Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.ANDGateway_3009,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(ANDGatewayLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5020); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getEndMessageEvent_3011Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.EndMessageEvent_3011,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(EndMessageEventLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5028); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getStartMessageEvent_3012Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.StartMessageEvent_3012,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(StartMessageEventLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5029); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getIntermediateCatchMessageEvent_3013Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.IntermediateCatchMessageEvent_3013,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(IntermediateCatchMessageEventLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5027); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getIntermediateThrowMessageEvent_3014Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.IntermediateThrowMessageEvent_3014,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(IntermediateThrowMessageEventLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5030); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getTextAnnotation_3015Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.TextAnnotation_3015,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(TextAnnotationText2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5010); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getStartTimerEvent_3016Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.StartTimerEvent_3016,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(StartTimerEventLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5031); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getIntermediateCatchTimerEvent_3017Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.IntermediateCatchTimerEvent_3017,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(IntermediateCatchTimerEventLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5032); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getThrowLinkEvent_3018Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.ThrowLinkEvent_3018,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(ThrowLinkEventLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5033); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getCatchLinkEvent_3019Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.CatchLinkEvent_3019,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(CatchLinkEventLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5034); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getEndSignalEvent_3020Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.EndSignalEvent_3020,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(EndSignalEventLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5035); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getIntermediateCatchSignalEvent_3021Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.IntermediateCatchSignalEvent_3021,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(IntermediateCatchSignalEventLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5036); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getIntermediateThrowSignalEvent_3022Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.IntermediateThrowSignalEvent_3022,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(IntermediateThrowSignalEventLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5037); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getStartSignalEvent_3023Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.StartSignalEvent_3023,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(StartSignalEventLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5038); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getEvent_3024Text(View view) {
		Event domainModelElement = (Event) view.getElement();
		if (domainModelElement != null) {
			return domainModelElement.getName();
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("No domain element for view with visualID = " + 3024); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getSendTask_3025Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.SendTask_3025,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(SendTaskLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5011); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getReceiveTask_3026Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.ReceiveTask_3026,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(ReceiveTaskLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5012); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getServiceTask_3027Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.ServiceTask_3027,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(ServiceTaskLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5017); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getScriptTask_3028Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.ScriptTask_3028,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(ScriptTaskLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5018); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getIntermediateErrorCatchEvent_3029Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.IntermediateErrorCatchEvent_3029,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(IntermediateErrorCatchEventLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5053); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getIntermediateErrorCatchEvent_3030Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.IntermediateErrorCatchEvent_3030,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(IntermediateErrorCatchEventLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5056); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getIntermediateErrorCatchEvent_3031Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.IntermediateErrorCatchEvent_3031,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(IntermediateErrorCatchEventLabel3EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5059); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getIntermediateErrorCatchEvent_3032Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.IntermediateErrorCatchEvent_3032,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(IntermediateErrorCatchEventLabel4EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5062); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getIntermediateErrorCatchEvent_3033Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.IntermediateErrorCatchEvent_3033,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(IntermediateErrorCatchEventLabel5EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5065); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getIntermediateErrorCatchEvent_3034Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.IntermediateErrorCatchEvent_3034,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(IntermediateErrorCatchEventLabel6EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5068); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getBoundaryMessageEvent_3035Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.BoundaryMessageEvent_3035,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(BoundaryMessageEventLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5054); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getBoundaryMessageEvent_3036Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.BoundaryMessageEvent_3036,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(BoundaryMessageEventLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5057); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getBoundaryTimerEvent_3043Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.BoundaryTimerEvent_3043,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(BoundaryTimerEventLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5055); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getBoundaryTimerEvent_3044Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.BoundaryTimerEvent_3044,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(BoundaryTimerEventLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5058); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getEndErrorEvent_3050Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.EndErrorEvent_3050,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(EndErrorEventLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5052); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getInclusiveGateway_3051Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.InclusiveGateway_3051,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(InclusiveGatewayLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5075); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getBoundarySignalEvent_3052Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.BoundarySignalEvent_3052,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(BoundarySignalEventLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5076); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getBoundarySignalEvent_3053Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.BoundarySignalEvent_3053,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(BoundarySignalEventLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5077); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getSubProcessEvent_3058Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.SubProcessEvent_3058,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(SubProcessEventLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5083); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getStartErrorEvent_3060Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.StartErrorEvent_3060,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(StartErrorEventLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5086); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getEndTerminatedEvent_3062Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.EndTerminatedEvent_3062,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(EndTerminatedEventLabel2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5090); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getCallActivity_3063Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.CallActivity_3063,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(CallActivityName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5093); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getNonInterruptingBoundaryTimerEvent_3064Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.NonInterruptingBoundaryTimerEvent_3064,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(NonInterruptingBoundaryTimerEventNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5094); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getNonInterruptingBoundaryTimerEvent_3065Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.NonInterruptingBoundaryTimerEvent_3065,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(NonInterruptingBoundaryTimerEventName2EditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 5095); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getSequenceFlow_4001Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.SequenceFlow_4001,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(SequenceFlowNameEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 6001); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getMessageFlow_4002Text(View view) {
		IParser parser = ProcessParserProvider.getParser(ProcessElementTypes.MessageFlow_4002,
				view.getElement() != null ? view.getElement() : view,
				ProcessVisualIDRegistry.getType(MessageFlowLabelEditPart.VISUAL_ID));
		if (parser != null) {
			return parser.getPrintString(new EObjectAdapter(view.getElement() != null ? view.getElement() : view),
					ParserOptions.NONE.intValue());
		} else {
			ProcessDiagramEditorPlugin.getInstance().logError("Parser was not found for label " + 6003); //$NON-NLS-1$
			return ""; //$NON-NLS-1$
		}
	}

	/**
	* @generated
	*/
	private String getTextAnnotationAttachment_4003Text(View view) {
		return ""; //$NON-NLS-1$
	}

	/**
	* @generated
	*/
	private String getUnknownElementText(View view) {
		return "<UnknownElement Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	* @generated
	*/
	private String getUnresolvedDomainElementProxyText(View view) {
		return "<Unresolved domain element Visual_ID = " + view.getType() + ">"; //$NON-NLS-1$  //$NON-NLS-2$
	}

	/**
	* @generated
	*/
	public void init(ICommonContentExtensionSite aConfig) {
	}

	/**
	* @generated
	*/
	public void restoreState(IMemento aMemento) {
	}

	/**
	* @generated
	*/
	public void saveState(IMemento aMemento) {
	}

	/**
	* @generated
	*/
	public String getDescription(Object anElement) {
		return null;
	}

	/**
	* @generated
	*/
	private boolean isOwnView(View view) {
		return MainProcessEditPart.MODEL_ID.equals(ProcessVisualIDRegistry.getModelID(view));
	}

}
