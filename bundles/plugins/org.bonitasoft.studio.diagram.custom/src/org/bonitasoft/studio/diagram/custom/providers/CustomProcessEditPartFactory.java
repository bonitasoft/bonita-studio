/*
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.diagram.custom.providers;

import org.bonitasoft.studio.diagram.custom.parts.CustomANDGateway2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomActivity2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomActivityName2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomAndGatewayLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomBoundaryMessageEventLabelEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomBoundarySignalEventLabelEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomBoundaryTimerEventLabelEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomCallActivity2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomCallActivityName2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomCatchLinkEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomCatchLinkEventLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomEndErrorEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomEndErrorEventLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomEndEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomEndEventLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomEndMessageEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomEndMessageEventLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomEndSignalEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomEndSignalEventLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomEndTerminatedEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomEndTerminatedEventLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomInclusiveGateway2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomInclusiveGatewayLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomIntermediateCatchMessageEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomIntermediateCatchMessageEventLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomIntermediateCatchSignalEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomIntermediateCatchSignalEventLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomIntermediateCatchTimerEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomIntermediateCatchTimerEventLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomIntermediateErrorCatchEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomIntermediateErrorCatchEventLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomIntermediateErrorCatchEventLabel3EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomIntermediateErrorCatchEventLabel4EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomIntermediateErrorCatchEventLabel5EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomIntermediateErrorCatchEventLabel6EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomIntermediateErrorCatchEventLabelEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomIntermediateThrowMessageEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomIntermediateThrowMessageEventLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomIntermediateThrowSignalEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomIntermediateThrowSignalEventLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomLaneCompartmentEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomLaneEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomLaneNameEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomMainProcessEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomMessageFlowEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomMessageFlowLabelEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomNonInterruptingBoundaryTimerEventNameEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomPoolCompartmentEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomPoolEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomPoolNameEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomReceiveTask2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomReceiveTaskLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomScriptTask2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomScriptTaskLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomSendTask2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomSendTaskLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomSequenceFlowEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomSequenceFlowNameEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomServiceTask2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomServiceTaskLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomStartErrorEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomStartErrorEventLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomStartEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomStartEventLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomStartMessageEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomStartMessageEventLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomStartSignalEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomStartSignalEventLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomStartTimerEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomStartTimerEventLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomSubProcessEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomSubProcessEventLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomSubprocessEventCompartmentEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomTask2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomTaskName2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomTextAnnotation2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomTextAnnotationText2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomThrowLinkEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomThrowLinkEventLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomXORGateway2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomXORGatewayLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ANDGateway2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ANDGatewayLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.Activity2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ActivityName2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.BoundaryMessageEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.BoundarySignalEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.BoundaryTimerEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CallActivity2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CallActivityName2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CatchLinkEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CatchLinkEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndErrorEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndErrorEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndMessageEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndMessageEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndSignalEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndSignalEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndTerminatedEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndTerminatedEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.InclusiveGateway2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.InclusiveGatewayLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchMessageEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchMessageEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchSignalEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchSignalEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchTimerEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchTimerEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEventLabel3EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEventLabel4EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEventLabel5EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEventLabel6EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateThrowMessageEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateThrowSignalEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateThrowSignalEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneLaneCompartmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneNameEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MessageFlowEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MessageFlowLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.NonInterruptingBoundaryTimerEventNameEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolNameEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolPoolCompartmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ProcessEditPartFactory;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ReceiveTask2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ReceiveTaskLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ScriptTask2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ScriptTaskLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SendTask2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SendTaskLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SequenceFlowEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SequenceFlowNameEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ServiceTask2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ServiceTaskLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartErrorEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartErrorEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartMessageEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartMessageEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartSignalEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartSignalEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartTimerEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartTimerEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEventSubProcessCompartment2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.Task2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TaskName2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TextAnnotation2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TextAnnotationText2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ThrowLinkEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ThrowLinkEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.XORGateway2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.XORGatewayLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.part.ProcessVisualIDRegistry;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Romain Bioteau
 */
public class CustomProcessEditPartFactory extends ProcessEditPartFactory {

    @Override
    public EditPart createEditPart(EditPart context, Object model) {
        if (model instanceof View) {
            View view = (View) model;
            switch (ProcessVisualIDRegistry.getVisualID(view)) {

                case MainProcessEditPart.VISUAL_ID:
                    return new CustomMainProcessEditPart(view);

                case LaneEditPart.VISUAL_ID:
                    return new CustomLaneEditPart(view);

                case LaneLaneCompartmentEditPart.VISUAL_ID:
                    return new CustomLaneCompartmentEditPart(view);

                case PoolEditPart.VISUAL_ID:
                    return new CustomPoolEditPart(view);

                case PoolPoolCompartmentEditPart.VISUAL_ID:
                    return new CustomPoolCompartmentEditPart(view);

                case ActivityName2EditPart.VISUAL_ID:
                    return new CustomActivityName2EditPart(view);

                case TaskName2EditPart.VISUAL_ID:
                    return new CustomTaskName2EditPart(view);

                case CallActivityName2EditPart.VISUAL_ID:
                    return new CustomCallActivityName2EditPart(view);

                case SubProcessEventLabel2EditPart.VISUAL_ID:
                    return new CustomSubProcessEventLabel2EditPart(view);

                case PoolNameEditPart.VISUAL_ID:
                    return new CustomPoolNameEditPart(view);

                case LaneNameEditPart.VISUAL_ID:
                    return new CustomLaneNameEditPart(view);

                case EndEvent2EditPart.VISUAL_ID:
                    return new CustomEndEvent2EditPart(view);

                case EndErrorEvent2EditPart.VISUAL_ID:
                    return new CustomEndErrorEvent2EditPart(view);

                case EndMessageEvent2EditPart.VISUAL_ID:
                    return new CustomEndMessageEvent2EditPart(view);

                case EndSignalEvent2EditPart.VISUAL_ID:
                    return new CustomEndSignalEvent2EditPart(view);

                case EndTerminatedEvent2EditPart.VISUAL_ID:
                    return new CustomEndTerminatedEvent2EditPart(view);

                case StartEvent2EditPart.VISUAL_ID:
                    return new CustomStartEvent2EditPart(view);

                case StartErrorEvent2EditPart.VISUAL_ID:
                    return new CustomStartErrorEvent2EditPart(view);

                case StartMessageEvent2EditPart.VISUAL_ID:
                    return new CustomStartMessageEvent2EditPart(view);

                case StartSignalEvent2EditPart.VISUAL_ID:
                    return new CustomStartSignalEvent2EditPart(view);

                case StartTimerEvent2EditPart.VISUAL_ID:
                    return new CustomStartTimerEvent2EditPart(view);

                case IntermediateCatchMessageEvent2EditPart.VISUAL_ID:
                    return new CustomIntermediateCatchMessageEvent2EditPart(view);

                case IntermediateCatchSignalEvent2EditPart.VISUAL_ID:
                    return new CustomIntermediateCatchSignalEvent2EditPart(view);

                case IntermediateCatchTimerEvent2EditPart.VISUAL_ID:
                    return new CustomIntermediateCatchTimerEvent2EditPart(view);

                case IntermediateErrorCatchEvent2EditPart.VISUAL_ID:
                    return new CustomIntermediateErrorCatchEvent2EditPart(view);

                case IntermediateThrowMessageEvent2EditPart.VISUAL_ID:
                    return new CustomIntermediateThrowMessageEvent2EditPart(view);

                case IntermediateThrowSignalEvent2EditPart.VISUAL_ID:
                    return new CustomIntermediateThrowSignalEvent2EditPart(view);

                case ANDGateway2EditPart.VISUAL_ID:
                    return new CustomANDGateway2EditPart(view);

                case XORGateway2EditPart.VISUAL_ID:
                    return new CustomXORGateway2EditPart(view);

                case InclusiveGateway2EditPart.VISUAL_ID:
                    return new CustomInclusiveGateway2EditPart(view);

                case Activity2EditPart.VISUAL_ID:
                    return new CustomActivity2EditPart(view);

                case Task2EditPart.VISUAL_ID:
                    return new CustomTask2EditPart(view);

                case CallActivity2EditPart.VISUAL_ID:
                    return new CustomCallActivity2EditPart(view);
                case SubProcessEvent2EditPart.VISUAL_ID:
                    return new CustomSubProcessEvent2EditPart(view);

                case SequenceFlowEditPart.VISUAL_ID:
                    return new CustomSequenceFlowEditPart(view);

                case SequenceFlowNameEditPart.VISUAL_ID:
                    return new CustomSequenceFlowNameEditPart(view);

                case MessageFlowEditPart.VISUAL_ID:
                    return new CustomMessageFlowEditPart(view);

                case TextAnnotation2EditPart.VISUAL_ID:
                    return new CustomTextAnnotation2EditPart(view);

                case TextAnnotationText2EditPart.VISUAL_ID:
                    return new CustomTextAnnotationText2EditPart(view);

                case MessageFlowLabelEditPart.VISUAL_ID:
                    return new CustomMessageFlowLabelEditPart(view);

                case CatchLinkEvent2EditPart.VISUAL_ID:
                    return new CustomCatchLinkEvent2EditPart(view);

                case ThrowLinkEvent2EditPart.VISUAL_ID:
                    return new CustomThrowLinkEvent2EditPart(view);

                case SendTask2EditPart.VISUAL_ID:
                    return new CustomSendTask2EditPart(view);

                case ReceiveTask2EditPart.VISUAL_ID:
                    return new CustomReceiveTask2EditPart(view);

                case SendTaskLabel2EditPart.VISUAL_ID:
                    return new CustomSendTaskLabel2EditPart(view);

                case ReceiveTaskLabel2EditPart.VISUAL_ID:
                    return new CustomReceiveTaskLabel2EditPart(view);

                case ScriptTask2EditPart.VISUAL_ID:
                    return new CustomScriptTask2EditPart(view);

                case ServiceTask2EditPart.VISUAL_ID:
                    return new CustomServiceTask2EditPart(view);

                case ScriptTaskLabel2EditPart.VISUAL_ID:
                    return new CustomScriptTaskLabel2EditPart(view);

                case ServiceTaskLabel2EditPart.VISUAL_ID:
                    return new CustomServiceTaskLabel2EditPart(view);

                case SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID:
                    return new CustomSubprocessEventCompartmentEditPart(view);

                case ANDGatewayLabel2EditPart.VISUAL_ID:
                    return new CustomAndGatewayLabel2EditPart(view);

                case InclusiveGatewayLabel2EditPart.VISUAL_ID:
                    return new CustomInclusiveGatewayLabel2EditPart(view);

                case XORGatewayLabel2EditPart.VISUAL_ID:
                    return new CustomXORGatewayLabel2EditPart(view);

                case StartEventLabel2EditPart.VISUAL_ID:
                    return new CustomStartEventLabel2EditPart(view);

                case StartErrorEventLabel2EditPart.VISUAL_ID:
                    return new CustomStartErrorEventLabel2EditPart(view);

                case StartMessageEventLabel2EditPart.VISUAL_ID:
                    return new CustomStartMessageEventLabel2EditPart(view);

                case StartSignalEventLabel2EditPart.VISUAL_ID:
                    return new CustomStartSignalEventLabel2EditPart(view);

                case StartTimerEventLabel2EditPart.VISUAL_ID:
                    return new CustomStartTimerEventLabel2EditPart(view);

                case BoundaryMessageEventLabelEditPart.VISUAL_ID:
                    return new CustomBoundaryMessageEventLabelEditPart(view);

                case IntermediateCatchMessageEventLabel2EditPart.VISUAL_ID:
                    return new CustomIntermediateCatchMessageEventLabel2EditPart(view);

                case CustomIntermediateThrowMessageEventLabel2EditPart.VISUAL_ID:
                    return new CustomIntermediateThrowMessageEventLabel2EditPart(view);

                case BoundaryTimerEventLabelEditPart.VISUAL_ID:
                    return new CustomBoundaryTimerEventLabelEditPart(view);

                case IntermediateCatchTimerEventLabel2EditPart.VISUAL_ID:
                    return new CustomIntermediateCatchTimerEventLabel2EditPart(view);

                case NonInterruptingBoundaryTimerEventNameEditPart.VISUAL_ID:
                    return new CustomNonInterruptingBoundaryTimerEventNameEditPart(view);

                case IntermediateCatchSignalEventLabel2EditPart.VISUAL_ID:
                    return new CustomIntermediateCatchSignalEventLabel2EditPart(view);

                case BoundarySignalEventLabelEditPart.VISUAL_ID:
                    return new CustomBoundarySignalEventLabelEditPart(view);

                case IntermediateThrowSignalEventLabel2EditPart.VISUAL_ID:
                    return new CustomIntermediateThrowSignalEventLabel2EditPart(view);

                case IntermediateErrorCatchEventLabelEditPart.VISUAL_ID:
                    return new CustomIntermediateErrorCatchEventLabelEditPart(view);

                case IntermediateErrorCatchEventLabel2EditPart.VISUAL_ID:
                    return new CustomIntermediateErrorCatchEventLabel2EditPart(view);

                case IntermediateErrorCatchEventLabel3EditPart.VISUAL_ID:
                    return new CustomIntermediateErrorCatchEventLabel3EditPart(view);

                case IntermediateErrorCatchEventLabel4EditPart.VISUAL_ID:
                    return new CustomIntermediateErrorCatchEventLabel4EditPart(view);

                case IntermediateErrorCatchEventLabel5EditPart.VISUAL_ID:
                    return new CustomIntermediateErrorCatchEventLabel5EditPart(view);

                case IntermediateErrorCatchEventLabel6EditPart.VISUAL_ID:
                    return new CustomIntermediateErrorCatchEventLabel6EditPart(view);

                case EndErrorEventLabel2EditPart.VISUAL_ID:
                    return new CustomEndErrorEventLabel2EditPart(view);

                case EndEventLabel2EditPart.VISUAL_ID:
                    return new CustomEndEventLabel2EditPart(view);

                case EndMessageEventLabel2EditPart.VISUAL_ID:
                    return new CustomEndMessageEventLabel2EditPart(view);

                case EndSignalEventLabel2EditPart.VISUAL_ID:
                    return new CustomEndSignalEventLabel2EditPart(view);

                case EndTerminatedEventLabel2EditPart.VISUAL_ID:
                    return new CustomEndTerminatedEventLabel2EditPart(view);

                case CatchLinkEventLabel2EditPart.VISUAL_ID:
                    return new CustomCatchLinkEventLabel2EditPart(view);

                case ThrowLinkEventLabel2EditPart.VISUAL_ID:
                    return new CustomThrowLinkEventLabel2EditPart(view);

            }
        }
        return super.createEditPart(context, model);
    }

}
