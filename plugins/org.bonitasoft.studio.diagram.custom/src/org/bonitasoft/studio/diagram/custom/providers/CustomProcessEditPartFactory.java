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
package org.bonitasoft.studio.diagram.custom.providers;

import org.bonitasoft.studio.diagram.custom.parts.CustomANDGateway2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomActivity2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomActivityName2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomCallActivity2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomCallActivityName2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomCatchLinkEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomEventSubProcessPoolEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomEventSubProcessPoolLabelEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomEventSubProcessPoolPoolCompartmentEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomInclusiveGateway2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomLaneCompartmentEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomLaneEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomLaneNameEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomMainProcessEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomMessageFlowEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomMessageFlowLabelEditPart;
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
import org.bonitasoft.studio.diagram.custom.parts.CustomSubProcessEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomSubProcessEventLabel2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomSubprocessEventCompartmentEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomTask2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomTaskName2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomTextAnnotation2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomTextAnnotationText2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomXORGateway2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ANDGateway2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.Activity2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ActivityName2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CallActivity2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CallActivityName2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CatchLinkEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EventSubProcessPoolEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EventSubProcessPoolLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EventSubProcessPoolPoolCompartmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.InclusiveGateway2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneLaneCompartmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneNameEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MessageFlowEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MessageFlowLabelEditPart;
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
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEvent2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEventSubProcessCompartment2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.Task2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TaskName2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TextAnnotation2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TextAnnotationText2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.XORGateway2EditPart;
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

                case EventSubProcessPoolEditPart.VISUAL_ID:
                    return new CustomEventSubProcessPoolEditPart(view);

                case EventSubProcessPoolPoolCompartmentEditPart.VISUAL_ID:
                    return new CustomEventSubProcessPoolPoolCompartmentEditPart(view);

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
                case EventSubProcessPoolLabelEditPart.VISUAL_ID:
                    return new CustomEventSubProcessPoolLabelEditPart(view);

                case LaneNameEditPart.VISUAL_ID:
                    return new CustomLaneNameEditPart(view);



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

                case SubProcessEventSubProcessCompartment2EditPart.VISUAL_ID :
                    return new CustomSubprocessEventCompartmentEditPart(view) ;
                    /*
			case BoundaryTimerEventLabelEditPart.VISUAL_ID :
				return new CustomBoundaryTimerLabelEditPart(view) ;

			case BoundaryTimerEventLabel2EditPart.VISUAL_ID :
				return new CustomBoundaryTimer2LabelEditPart(view) ;

			case IntermediateCatchTimerEventLabel2EditPart.VISUAL_ID :
				return new CustomIntermediateTimer2LabelEditPart(view) ;

			case StartTimerEventLabel2EditPart.VISUAL_ID :
				return new CustomStartTimer2LabelEditPart(view) ;
                     */
            }
        }
        return super.createEditPart(context, model);
    }

}
