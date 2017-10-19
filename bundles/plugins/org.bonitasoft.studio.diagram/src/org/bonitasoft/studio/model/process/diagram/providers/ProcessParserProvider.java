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

import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ANDGatewayLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ANDGatewayLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ActivityName2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ActivityNameEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.BoundaryMessageEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.BoundaryMessageEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.BoundarySignalEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.BoundarySignalEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.BoundaryTimerEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.BoundaryTimerEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CallActivityName2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CallActivityNameEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CatchLinkEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.CatchLinkEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndErrorEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndErrorEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndMessageEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndMessageEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndSignalEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndSignalEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndTerminatedEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.EndTerminatedEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.InclusiveGatewayLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.InclusiveGatewayLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchMessageEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchMessageEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchSignalEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchSignalEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchTimerEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateCatchTimerEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEventLabel3EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEventLabel4EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEventLabel5EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEventLabel6EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateErrorCatchEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateThrowMessageEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateThrowMessageEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateThrowSignalEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.IntermediateThrowSignalEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneNameEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MessageFlowLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.NonInterruptingBoundaryTimerEventName2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.NonInterruptingBoundaryTimerEventNameEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolNameEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ReceiveTaskLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ReceiveTaskLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ScriptTaskLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ScriptTaskLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SendTaskLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SendTaskLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SequenceFlowNameEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ServiceTaskLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ServiceTaskLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartErrorEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartErrorEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartMessageEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartMessageEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartSignalEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartSignalEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartTimerEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.StartTimerEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TaskName2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TaskNameEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TextAnnotationText2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.TextAnnotationTextEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ThrowLinkEventLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.ThrowLinkEventLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.XORGatewayLabel2EditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.XORGatewayLabelEditPart;
import org.bonitasoft.studio.model.process.diagram.parsers.MessageFormatParser;
import org.bonitasoft.studio.model.process.diagram.part.ProcessVisualIDRegistry;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class ProcessParserProvider extends AbstractProvider implements IParserProvider {

	/**
	* @generated
	*/
	private IParser aNDGatewayName_5019Parser;

	/**
	* @generated
	*/
	private IParser getANDGatewayName_5019Parser() {
		if (aNDGatewayName_5019Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			aNDGatewayName_5019Parser = parser;
		}
		return aNDGatewayName_5019Parser;
	}

	/**
	* @generated
	*/
	private IParser startEventName_5021Parser;

	/**
	* @generated
	*/
	private IParser getStartEventName_5021Parser() {
		if (startEventName_5021Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			startEventName_5021Parser = parser;
		}
		return startEventName_5021Parser;
	}

	/**
	* @generated
	*/
	private IParser endEventName_5022Parser;

	/**
	* @generated
	*/
	private IParser getEndEventName_5022Parser() {
		if (endEventName_5022Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			endEventName_5022Parser = parser;
		}
		return endEventName_5022Parser;
	}

	/**
	* @generated
	*/
	private IParser taskName_5001Parser;

	/**
	* @generated
	*/
	private IParser getTaskName_5001Parser() {
		if (taskName_5001Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			taskName_5001Parser = parser;
		}
		return taskName_5001Parser;
	}

	/**
	* @generated
	*/
	private IParser callActivityName_5092Parser;

	/**
	* @generated
	*/
	private IParser getCallActivityName_5092Parser() {
		if (callActivityName_5092Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			callActivityName_5092Parser = parser;
		}
		return callActivityName_5092Parser;
	}

	/**
	* @generated
	*/
	private IParser subProcessEventName_5081Parser;

	/**
	* @generated
	*/
	private IParser getSubProcessEventName_5081Parser() {
		if (subProcessEventName_5081Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			subProcessEventName_5081Parser = parser;
		}
		return subProcessEventName_5081Parser;
	}

	/**
	* @generated
	*/
	private IParser receiveTaskName_5013Parser;

	/**
	* @generated
	*/
	private IParser getReceiveTaskName_5013Parser() {
		if (receiveTaskName_5013Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			receiveTaskName_5013Parser = parser;
		}
		return receiveTaskName_5013Parser;
	}

	/**
	* @generated
	*/
	private IParser sendTaskName_5014Parser;

	/**
	* @generated
	*/
	private IParser getSendTaskName_5014Parser() {
		if (sendTaskName_5014Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			sendTaskName_5014Parser = parser;
		}
		return sendTaskName_5014Parser;
	}

	/**
	* @generated
	*/
	private IParser serviceTaskName_5015Parser;

	/**
	* @generated
	*/
	private IParser getServiceTaskName_5015Parser() {
		if (serviceTaskName_5015Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			serviceTaskName_5015Parser = parser;
		}
		return serviceTaskName_5015Parser;
	}

	/**
	* @generated
	*/
	private IParser scriptTaskName_5016Parser;

	/**
	* @generated
	*/
	private IParser getScriptTaskName_5016Parser() {
		if (scriptTaskName_5016Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			scriptTaskName_5016Parser = parser;
		}
		return scriptTaskName_5016Parser;
	}

	/**
	* @generated
	*/
	private IParser xORGatewayName_5023Parser;

	/**
	* @generated
	*/
	private IParser getXORGatewayName_5023Parser() {
		if (xORGatewayName_5023Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			xORGatewayName_5023Parser = parser;
		}
		return xORGatewayName_5023Parser;
	}

	/**
	* @generated
	*/
	private IParser inclusiveGatewayName_5074Parser;

	/**
	* @generated
	*/
	private IParser getInclusiveGatewayName_5074Parser() {
		if (inclusiveGatewayName_5074Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			inclusiveGatewayName_5074Parser = parser;
		}
		return inclusiveGatewayName_5074Parser;
	}

	/**
	* @generated
	*/
	private IParser activityName_5003Parser;

	/**
	* @generated
	*/
	private IParser getActivityName_5003Parser() {
		if (activityName_5003Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			activityName_5003Parser = parser;
		}
		return activityName_5003Parser;
	}

	/**
	* @generated
	*/
	private IParser poolName_5008Parser;

	/**
	* @generated
	*/
	private IParser getPoolName_5008Parser() {
		if (poolName_5008Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			poolName_5008Parser = parser;
		}
		return poolName_5008Parser;
	}

	/**
	* @generated
	*/
	private IParser startMessageEventName_5039Parser;

	/**
	* @generated
	*/
	private IParser getStartMessageEventName_5039Parser() {
		if (startMessageEventName_5039Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			startMessageEventName_5039Parser = parser;
		}
		return startMessageEventName_5039Parser;
	}

	/**
	* @generated
	*/
	private IParser endMessageEventName_5040Parser;

	/**
	* @generated
	*/
	private IParser getEndMessageEventName_5040Parser() {
		if (endMessageEventName_5040Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			endMessageEventName_5040Parser = parser;
		}
		return endMessageEventName_5040Parser;
	}

	/**
	* @generated
	*/
	private IParser intermediateCatchMessageEventName_5041Parser;

	/**
	* @generated
	*/
	private IParser getIntermediateCatchMessageEventName_5041Parser() {
		if (intermediateCatchMessageEventName_5041Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			intermediateCatchMessageEventName_5041Parser = parser;
		}
		return intermediateCatchMessageEventName_5041Parser;
	}

	/**
	* @generated
	*/
	private IParser intermediateThrowMessageEventName_5042Parser;

	/**
	* @generated
	*/
	private IParser getIntermediateThrowMessageEventName_5042Parser() {
		if (intermediateThrowMessageEventName_5042Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			intermediateThrowMessageEventName_5042Parser = parser;
		}
		return intermediateThrowMessageEventName_5042Parser;
	}

	/**
	* @generated
	*/
	private IParser textAnnotationText_5009Parser;

	/**
	* @generated
	*/
	private IParser getTextAnnotationText_5009Parser() {
		if (textAnnotationText_5009Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getTextAnnotation_Text() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getTextAnnotation_Text() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			textAnnotationText_5009Parser = parser;
		}
		return textAnnotationText_5009Parser;
	}

	/**
	* @generated
	*/
	private IParser intermediateCatchTimerEventName_5043Parser;

	/**
	* @generated
	*/
	private IParser getIntermediateCatchTimerEventName_5043Parser() {
		if (intermediateCatchTimerEventName_5043Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			intermediateCatchTimerEventName_5043Parser = parser;
		}
		return intermediateCatchTimerEventName_5043Parser;
	}

	/**
	* @generated
	*/
	private IParser startTimerEventName_5044Parser;

	/**
	* @generated
	*/
	private IParser getStartTimerEventName_5044Parser() {
		if (startTimerEventName_5044Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			startTimerEventName_5044Parser = parser;
		}
		return startTimerEventName_5044Parser;
	}

	/**
	* @generated
	*/
	private IParser catchLinkEventName_5045Parser;

	/**
	* @generated
	*/
	private IParser getCatchLinkEventName_5045Parser() {
		if (catchLinkEventName_5045Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			catchLinkEventName_5045Parser = parser;
		}
		return catchLinkEventName_5045Parser;
	}

	/**
	* @generated
	*/
	private IParser throwLinkEventName_5046Parser;

	/**
	* @generated
	*/
	private IParser getThrowLinkEventName_5046Parser() {
		if (throwLinkEventName_5046Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			throwLinkEventName_5046Parser = parser;
		}
		return throwLinkEventName_5046Parser;
	}

	/**
	* @generated
	*/
	private IParser intermediateThrowSignalEventName_5047Parser;

	/**
	* @generated
	*/
	private IParser getIntermediateThrowSignalEventName_5047Parser() {
		if (intermediateThrowSignalEventName_5047Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			intermediateThrowSignalEventName_5047Parser = parser;
		}
		return intermediateThrowSignalEventName_5047Parser;
	}

	/**
	* @generated
	*/
	private IParser intermediateCatchSignalEventName_5048Parser;

	/**
	* @generated
	*/
	private IParser getIntermediateCatchSignalEventName_5048Parser() {
		if (intermediateCatchSignalEventName_5048Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			intermediateCatchSignalEventName_5048Parser = parser;
		}
		return intermediateCatchSignalEventName_5048Parser;
	}

	/**
	* @generated
	*/
	private IParser startSignalEventName_5049Parser;

	/**
	* @generated
	*/
	private IParser getStartSignalEventName_5049Parser() {
		if (startSignalEventName_5049Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			startSignalEventName_5049Parser = parser;
		}
		return startSignalEventName_5049Parser;
	}

	/**
	* @generated
	*/
	private IParser endSignalEventName_5050Parser;

	/**
	* @generated
	*/
	private IParser getEndSignalEventName_5050Parser() {
		if (endSignalEventName_5050Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			endSignalEventName_5050Parser = parser;
		}
		return endSignalEventName_5050Parser;
	}

	/**
	* @generated
	*/
	private IParser endErrorEventName_5051Parser;

	/**
	* @generated
	*/
	private IParser getEndErrorEventName_5051Parser() {
		if (endErrorEventName_5051Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			endErrorEventName_5051Parser = parser;
		}
		return endErrorEventName_5051Parser;
	}

	/**
	* @generated
	*/
	private IParser startErrorEventName_5087Parser;

	/**
	* @generated
	*/
	private IParser getStartErrorEventName_5087Parser() {
		if (startErrorEventName_5087Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			startErrorEventName_5087Parser = parser;
		}
		return startErrorEventName_5087Parser;
	}

	/**
	* @generated
	*/
	private IParser endTerminatedEventName_5091Parser;

	/**
	* @generated
	*/
	private IParser getEndTerminatedEventName_5091Parser() {
		if (endTerminatedEventName_5091Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			endTerminatedEventName_5091Parser = parser;
		}
		return endTerminatedEventName_5091Parser;
	}

	/**
	* @generated
	*/
	private IParser intermediateErrorCatchEventName_5053Parser;

	/**
	* @generated
	*/
	private IParser getIntermediateErrorCatchEventName_5053Parser() {
		if (intermediateErrorCatchEventName_5053Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			intermediateErrorCatchEventName_5053Parser = parser;
		}
		return intermediateErrorCatchEventName_5053Parser;
	}

	/**
	* @generated
	*/
	private IParser boundaryMessageEventName_5054Parser;

	/**
	* @generated
	*/
	private IParser getBoundaryMessageEventName_5054Parser() {
		if (boundaryMessageEventName_5054Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			boundaryMessageEventName_5054Parser = parser;
		}
		return boundaryMessageEventName_5054Parser;
	}

	/**
	* @generated
	*/
	private IParser nonInterruptingBoundaryTimerEventName_5094Parser;

	/**
	* @generated
	*/
	private IParser getNonInterruptingBoundaryTimerEventName_5094Parser() {
		if (nonInterruptingBoundaryTimerEventName_5094Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			nonInterruptingBoundaryTimerEventName_5094Parser = parser;
		}
		return nonInterruptingBoundaryTimerEventName_5094Parser;
	}

	/**
	* @generated
	*/
	private IParser boundaryTimerEventName_5055Parser;

	/**
	* @generated
	*/
	private IParser getBoundaryTimerEventName_5055Parser() {
		if (boundaryTimerEventName_5055Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			boundaryTimerEventName_5055Parser = parser;
		}
		return boundaryTimerEventName_5055Parser;
	}

	/**
	* @generated
	*/
	private IParser boundarySignalEventName_5076Parser;

	/**
	* @generated
	*/
	private IParser getBoundarySignalEventName_5076Parser() {
		if (boundarySignalEventName_5076Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			boundarySignalEventName_5076Parser = parser;
		}
		return boundarySignalEventName_5076Parser;
	}

	/**
	* @generated
	*/
	private IParser intermediateErrorCatchEventName_5056Parser;

	/**
	* @generated
	*/
	private IParser getIntermediateErrorCatchEventName_5056Parser() {
		if (intermediateErrorCatchEventName_5056Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			intermediateErrorCatchEventName_5056Parser = parser;
		}
		return intermediateErrorCatchEventName_5056Parser;
	}

	/**
	* @generated
	*/
	private IParser boundaryMessageEventName_5057Parser;

	/**
	* @generated
	*/
	private IParser getBoundaryMessageEventName_5057Parser() {
		if (boundaryMessageEventName_5057Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			boundaryMessageEventName_5057Parser = parser;
		}
		return boundaryMessageEventName_5057Parser;
	}

	/**
	* @generated
	*/
	private IParser nonInterruptingBoundaryTimerEventName_5095Parser;

	/**
	* @generated
	*/
	private IParser getNonInterruptingBoundaryTimerEventName_5095Parser() {
		if (nonInterruptingBoundaryTimerEventName_5095Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			nonInterruptingBoundaryTimerEventName_5095Parser = parser;
		}
		return nonInterruptingBoundaryTimerEventName_5095Parser;
	}

	/**
	* @generated
	*/
	private IParser boundaryTimerEventName_5058Parser;

	/**
	* @generated
	*/
	private IParser getBoundaryTimerEventName_5058Parser() {
		if (boundaryTimerEventName_5058Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			boundaryTimerEventName_5058Parser = parser;
		}
		return boundaryTimerEventName_5058Parser;
	}

	/**
	* @generated
	*/
	private IParser boundarySignalEventName_5077Parser;

	/**
	* @generated
	*/
	private IParser getBoundarySignalEventName_5077Parser() {
		if (boundarySignalEventName_5077Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			boundarySignalEventName_5077Parser = parser;
		}
		return boundarySignalEventName_5077Parser;
	}

	/**
	* @generated
	*/
	private IParser aNDGatewayName_5020Parser;

	/**
	* @generated
	*/
	private IParser getANDGatewayName_5020Parser() {
		if (aNDGatewayName_5020Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			aNDGatewayName_5020Parser = parser;
		}
		return aNDGatewayName_5020Parser;
	}

	/**
	* @generated
	*/
	private IParser endEventName_5025Parser;

	/**
	* @generated
	*/
	private IParser getEndEventName_5025Parser() {
		if (endEventName_5025Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			endEventName_5025Parser = parser;
		}
		return endEventName_5025Parser;
	}

	/**
	* @generated
	*/
	private IParser callActivityName_5093Parser;

	/**
	* @generated
	*/
	private IParser getCallActivityName_5093Parser() {
		if (callActivityName_5093Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			callActivityName_5093Parser = parser;
		}
		return callActivityName_5093Parser;
	}

	/**
	* @generated
	*/
	private IParser taskName_5005Parser;

	/**
	* @generated
	*/
	private IParser getTaskName_5005Parser() {
		if (taskName_5005Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			taskName_5005Parser = parser;
		}
		return taskName_5005Parser;
	}

	/**
	* @generated
	*/
	private IParser receiveTaskName_5012Parser;

	/**
	* @generated
	*/
	private IParser getReceiveTaskName_5012Parser() {
		if (receiveTaskName_5012Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			receiveTaskName_5012Parser = parser;
		}
		return receiveTaskName_5012Parser;
	}

	/**
	* @generated
	*/
	private IParser intermediateErrorCatchEventName_5059Parser;

	/**
	* @generated
	*/
	private IParser getIntermediateErrorCatchEventName_5059Parser() {
		if (intermediateErrorCatchEventName_5059Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			intermediateErrorCatchEventName_5059Parser = parser;
		}
		return intermediateErrorCatchEventName_5059Parser;
	}

	/**
	* @generated
	*/
	private IParser sendTaskName_5011Parser;

	/**
	* @generated
	*/
	private IParser getSendTaskName_5011Parser() {
		if (sendTaskName_5011Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			sendTaskName_5011Parser = parser;
		}
		return sendTaskName_5011Parser;
	}

	/**
	* @generated
	*/
	private IParser serviceTaskName_5017Parser;

	/**
	* @generated
	*/
	private IParser getServiceTaskName_5017Parser() {
		if (serviceTaskName_5017Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			serviceTaskName_5017Parser = parser;
		}
		return serviceTaskName_5017Parser;
	}

	/**
	* @generated
	*/
	private IParser intermediateErrorCatchEventName_5062Parser;

	/**
	* @generated
	*/
	private IParser getIntermediateErrorCatchEventName_5062Parser() {
		if (intermediateErrorCatchEventName_5062Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			intermediateErrorCatchEventName_5062Parser = parser;
		}
		return intermediateErrorCatchEventName_5062Parser;
	}

	/**
	* @generated
	*/
	private IParser scriptTaskName_5018Parser;

	/**
	* @generated
	*/
	private IParser getScriptTaskName_5018Parser() {
		if (scriptTaskName_5018Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			scriptTaskName_5018Parser = parser;
		}
		return scriptTaskName_5018Parser;
	}

	/**
	* @generated
	*/
	private IParser intermediateErrorCatchEventName_5065Parser;

	/**
	* @generated
	*/
	private IParser getIntermediateErrorCatchEventName_5065Parser() {
		if (intermediateErrorCatchEventName_5065Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			intermediateErrorCatchEventName_5065Parser = parser;
		}
		return intermediateErrorCatchEventName_5065Parser;
	}

	/**
	* @generated
	*/
	private IParser xORGatewayName_5026Parser;

	/**
	* @generated
	*/
	private IParser getXORGatewayName_5026Parser() {
		if (xORGatewayName_5026Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			xORGatewayName_5026Parser = parser;
		}
		return xORGatewayName_5026Parser;
	}

	/**
	* @generated
	*/
	private IParser activityName_5006Parser;

	/**
	* @generated
	*/
	private IParser getActivityName_5006Parser() {
		if (activityName_5006Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			activityName_5006Parser = parser;
		}
		return activityName_5006Parser;
	}

	/**
	* @generated
	*/
	private IParser intermediateErrorCatchEventName_5068Parser;

	/**
	* @generated
	*/
	private IParser getIntermediateErrorCatchEventName_5068Parser() {
		if (intermediateErrorCatchEventName_5068Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			intermediateErrorCatchEventName_5068Parser = parser;
		}
		return intermediateErrorCatchEventName_5068Parser;
	}

	/**
	* @generated
	*/
	private IParser intermediateCatchMessageEventName_5027Parser;

	/**
	* @generated
	*/
	private IParser getIntermediateCatchMessageEventName_5027Parser() {
		if (intermediateCatchMessageEventName_5027Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			intermediateCatchMessageEventName_5027Parser = parser;
		}
		return intermediateCatchMessageEventName_5027Parser;
	}

	/**
	* @generated
	*/
	private IParser startMessageEventName_5029Parser;

	/**
	* @generated
	*/
	private IParser getStartMessageEventName_5029Parser() {
		if (startMessageEventName_5029Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			startMessageEventName_5029Parser = parser;
		}
		return startMessageEventName_5029Parser;
	}

	/**
	* @generated
	*/
	private IParser endMessageEventName_5028Parser;

	/**
	* @generated
	*/
	private IParser getEndMessageEventName_5028Parser() {
		if (endMessageEventName_5028Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			endMessageEventName_5028Parser = parser;
		}
		return endMessageEventName_5028Parser;
	}

	/**
	* @generated
	*/
	private IParser intermediateThrowMessageEventName_5030Parser;

	/**
	* @generated
	*/
	private IParser getIntermediateThrowMessageEventName_5030Parser() {
		if (intermediateThrowMessageEventName_5030Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			intermediateThrowMessageEventName_5030Parser = parser;
		}
		return intermediateThrowMessageEventName_5030Parser;
	}

	/**
	* @generated
	*/
	private IParser textAnnotationText_5010Parser;

	/**
	* @generated
	*/
	private IParser getTextAnnotationText_5010Parser() {
		if (textAnnotationText_5010Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getTextAnnotation_Text() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getTextAnnotation_Text() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			textAnnotationText_5010Parser = parser;
		}
		return textAnnotationText_5010Parser;
	}

	/**
	* @generated
	*/
	private IParser startTimerEventName_5031Parser;

	/**
	* @generated
	*/
	private IParser getStartTimerEventName_5031Parser() {
		if (startTimerEventName_5031Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			startTimerEventName_5031Parser = parser;
		}
		return startTimerEventName_5031Parser;
	}

	/**
	* @generated
	*/
	private IParser intermediateCatchTimerEventName_5032Parser;

	/**
	* @generated
	*/
	private IParser getIntermediateCatchTimerEventName_5032Parser() {
		if (intermediateCatchTimerEventName_5032Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			intermediateCatchTimerEventName_5032Parser = parser;
		}
		return intermediateCatchTimerEventName_5032Parser;
	}

	/**
	* @generated
	*/
	private IParser startSignalEventName_5038Parser;

	/**
	* @generated
	*/
	private IParser getStartSignalEventName_5038Parser() {
		if (startSignalEventName_5038Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			startSignalEventName_5038Parser = parser;
		}
		return startSignalEventName_5038Parser;
	}

	/**
	* @generated
	*/
	private IParser intermediateThrowSignalEventName_5037Parser;

	/**
	* @generated
	*/
	private IParser getIntermediateThrowSignalEventName_5037Parser() {
		if (intermediateThrowSignalEventName_5037Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			intermediateThrowSignalEventName_5037Parser = parser;
		}
		return intermediateThrowSignalEventName_5037Parser;
	}

	/**
	* @generated
	*/
	private IParser intermediateCatchSignalEventName_5036Parser;

	/**
	* @generated
	*/
	private IParser getIntermediateCatchSignalEventName_5036Parser() {
		if (intermediateCatchSignalEventName_5036Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			intermediateCatchSignalEventName_5036Parser = parser;
		}
		return intermediateCatchSignalEventName_5036Parser;
	}

	/**
	* @generated
	*/
	private IParser endSignalEventName_5035Parser;

	/**
	* @generated
	*/
	private IParser getEndSignalEventName_5035Parser() {
		if (endSignalEventName_5035Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			endSignalEventName_5035Parser = parser;
		}
		return endSignalEventName_5035Parser;
	}

	/**
	* @generated
	*/
	private IParser endErrorEventName_5052Parser;

	/**
	* @generated
	*/
	private IParser getEndErrorEventName_5052Parser() {
		if (endErrorEventName_5052Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			endErrorEventName_5052Parser = parser;
		}
		return endErrorEventName_5052Parser;
	}

	/**
	* @generated
	*/
	private IParser endTerminatedEventName_5090Parser;

	/**
	* @generated
	*/
	private IParser getEndTerminatedEventName_5090Parser() {
		if (endTerminatedEventName_5090Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			endTerminatedEventName_5090Parser = parser;
		}
		return endTerminatedEventName_5090Parser;
	}

	/**
	* @generated
	*/
	private IParser startErrorEventName_5086Parser;

	/**
	* @generated
	*/
	private IParser getStartErrorEventName_5086Parser() {
		if (startErrorEventName_5086Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			startErrorEventName_5086Parser = parser;
		}
		return startErrorEventName_5086Parser;
	}

	/**
	* @generated
	*/
	private IParser inclusiveGatewayName_5075Parser;

	/**
	* @generated
	*/
	private IParser getInclusiveGatewayName_5075Parser() {
		if (inclusiveGatewayName_5075Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			inclusiveGatewayName_5075Parser = parser;
		}
		return inclusiveGatewayName_5075Parser;
	}

	/**
	* @generated
	*/
	private IParser laneName_5007Parser;

	/**
	* @generated
	*/
	private IParser getLaneName_5007Parser() {
		if (laneName_5007Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			laneName_5007Parser = parser;
		}
		return laneName_5007Parser;
	}

	/**
	* @generated
	*/
	private IParser startEventName_5024Parser;

	/**
	* @generated
	*/
	private IParser getStartEventName_5024Parser() {
		if (startEventName_5024Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			startEventName_5024Parser = parser;
		}
		return startEventName_5024Parser;
	}

	/**
	* @generated
	*/
	private IParser subProcessEventName_5083Parser;

	/**
	* @generated
	*/
	private IParser getSubProcessEventName_5083Parser() {
		if (subProcessEventName_5083Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			subProcessEventName_5083Parser = parser;
		}
		return subProcessEventName_5083Parser;
	}

	/**
	* @generated
	*/
	private IParser throwLinkEventName_5033Parser;

	/**
	* @generated
	*/
	private IParser getThrowLinkEventName_5033Parser() {
		if (throwLinkEventName_5033Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			throwLinkEventName_5033Parser = parser;
		}
		return throwLinkEventName_5033Parser;
	}

	/**
	* @generated
	*/
	private IParser catchLinkEventName_5034Parser;

	/**
	* @generated
	*/
	private IParser getCatchLinkEventName_5034Parser() {
		if (catchLinkEventName_5034Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			catchLinkEventName_5034Parser = parser;
		}
		return catchLinkEventName_5034Parser;
	}

	/**
	* @generated
	*/
	private IParser sequenceFlowName_6001Parser;

	/**
	* @generated
	*/
	private IParser getSequenceFlowName_6001Parser() {
		if (sequenceFlowName_6001Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			sequenceFlowName_6001Parser = parser;
		}
		return sequenceFlowName_6001Parser;
	}

	/**
	* @generated
	*/
	private IParser messageFlowName_6003Parser;

	/**
	* @generated
	*/
	private IParser getMessageFlowName_6003Parser() {
		if (messageFlowName_6003Parser == null) {
			EAttribute[] features = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			EAttribute[] editableFeatures = new EAttribute[] { ProcessPackage.eINSTANCE.getElement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features, editableFeatures);
			messageFlowName_6003Parser = parser;
		}
		return messageFlowName_6003Parser;
	}

	/**
	* @generated
	*/
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case ANDGatewayLabelEditPart.VISUAL_ID:
			return getANDGatewayName_5019Parser();
		case StartEventLabelEditPart.VISUAL_ID:
			return getStartEventName_5021Parser();
		case EndEventLabelEditPart.VISUAL_ID:
			return getEndEventName_5022Parser();
		case TaskNameEditPart.VISUAL_ID:
			return getTaskName_5001Parser();
		case CallActivityNameEditPart.VISUAL_ID:
			return getCallActivityName_5092Parser();
		case SubProcessEventLabelEditPart.VISUAL_ID:
			return getSubProcessEventName_5081Parser();
		case ReceiveTaskLabelEditPart.VISUAL_ID:
			return getReceiveTaskName_5013Parser();
		case SendTaskLabelEditPart.VISUAL_ID:
			return getSendTaskName_5014Parser();
		case ServiceTaskLabelEditPart.VISUAL_ID:
			return getServiceTaskName_5015Parser();
		case ScriptTaskLabelEditPart.VISUAL_ID:
			return getScriptTaskName_5016Parser();
		case XORGatewayLabelEditPart.VISUAL_ID:
			return getXORGatewayName_5023Parser();
		case InclusiveGatewayLabelEditPart.VISUAL_ID:
			return getInclusiveGatewayName_5074Parser();
		case ActivityNameEditPart.VISUAL_ID:
			return getActivityName_5003Parser();
		case PoolNameEditPart.VISUAL_ID:
			return getPoolName_5008Parser();
		case StartMessageEventLabelEditPart.VISUAL_ID:
			return getStartMessageEventName_5039Parser();
		case EndMessageEventLabelEditPart.VISUAL_ID:
			return getEndMessageEventName_5040Parser();
		case IntermediateCatchMessageEventLabelEditPart.VISUAL_ID:
			return getIntermediateCatchMessageEventName_5041Parser();
		case IntermediateThrowMessageEventLabelEditPart.VISUAL_ID:
			return getIntermediateThrowMessageEventName_5042Parser();
		case TextAnnotationTextEditPart.VISUAL_ID:
			return getTextAnnotationText_5009Parser();
		case IntermediateCatchTimerEventLabelEditPart.VISUAL_ID:
			return getIntermediateCatchTimerEventName_5043Parser();
		case StartTimerEventLabelEditPart.VISUAL_ID:
			return getStartTimerEventName_5044Parser();
		case CatchLinkEventLabelEditPart.VISUAL_ID:
			return getCatchLinkEventName_5045Parser();
		case ThrowLinkEventLabelEditPart.VISUAL_ID:
			return getThrowLinkEventName_5046Parser();
		case IntermediateThrowSignalEventLabelEditPart.VISUAL_ID:
			return getIntermediateThrowSignalEventName_5047Parser();
		case IntermediateCatchSignalEventLabelEditPart.VISUAL_ID:
			return getIntermediateCatchSignalEventName_5048Parser();
		case StartSignalEventLabelEditPart.VISUAL_ID:
			return getStartSignalEventName_5049Parser();
		case EndSignalEventLabelEditPart.VISUAL_ID:
			return getEndSignalEventName_5050Parser();
		case EndErrorEventLabelEditPart.VISUAL_ID:
			return getEndErrorEventName_5051Parser();
		case StartErrorEventLabelEditPart.VISUAL_ID:
			return getStartErrorEventName_5087Parser();
		case EndTerminatedEventLabelEditPart.VISUAL_ID:
			return getEndTerminatedEventName_5091Parser();
		case IntermediateErrorCatchEventLabelEditPart.VISUAL_ID:
			return getIntermediateErrorCatchEventName_5053Parser();
		case BoundaryMessageEventLabelEditPart.VISUAL_ID:
			return getBoundaryMessageEventName_5054Parser();
		case NonInterruptingBoundaryTimerEventNameEditPart.VISUAL_ID:
			return getNonInterruptingBoundaryTimerEventName_5094Parser();
		case BoundaryTimerEventLabelEditPart.VISUAL_ID:
			return getBoundaryTimerEventName_5055Parser();
		case BoundarySignalEventLabelEditPart.VISUAL_ID:
			return getBoundarySignalEventName_5076Parser();
		case IntermediateErrorCatchEventLabel2EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEventName_5056Parser();
		case BoundaryMessageEventLabel2EditPart.VISUAL_ID:
			return getBoundaryMessageEventName_5057Parser();
		case NonInterruptingBoundaryTimerEventName2EditPart.VISUAL_ID:
			return getNonInterruptingBoundaryTimerEventName_5095Parser();
		case BoundaryTimerEventLabel2EditPart.VISUAL_ID:
			return getBoundaryTimerEventName_5058Parser();
		case BoundarySignalEventLabel2EditPart.VISUAL_ID:
			return getBoundarySignalEventName_5077Parser();
		case ANDGatewayLabel2EditPart.VISUAL_ID:
			return getANDGatewayName_5020Parser();
		case EndEventLabel2EditPart.VISUAL_ID:
			return getEndEventName_5025Parser();
		case CallActivityName2EditPart.VISUAL_ID:
			return getCallActivityName_5093Parser();
		case TaskName2EditPart.VISUAL_ID:
			return getTaskName_5005Parser();
		case ReceiveTaskLabel2EditPart.VISUAL_ID:
			return getReceiveTaskName_5012Parser();
		case IntermediateErrorCatchEventLabel3EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEventName_5059Parser();
		case SendTaskLabel2EditPart.VISUAL_ID:
			return getSendTaskName_5011Parser();
		case ServiceTaskLabel2EditPart.VISUAL_ID:
			return getServiceTaskName_5017Parser();
		case IntermediateErrorCatchEventLabel4EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEventName_5062Parser();
		case ScriptTaskLabel2EditPart.VISUAL_ID:
			return getScriptTaskName_5018Parser();
		case IntermediateErrorCatchEventLabel5EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEventName_5065Parser();
		case XORGatewayLabel2EditPart.VISUAL_ID:
			return getXORGatewayName_5026Parser();
		case ActivityName2EditPart.VISUAL_ID:
			return getActivityName_5006Parser();
		case IntermediateErrorCatchEventLabel6EditPart.VISUAL_ID:
			return getIntermediateErrorCatchEventName_5068Parser();
		case IntermediateCatchMessageEventLabel2EditPart.VISUAL_ID:
			return getIntermediateCatchMessageEventName_5027Parser();
		case StartMessageEventLabel2EditPart.VISUAL_ID:
			return getStartMessageEventName_5029Parser();
		case EndMessageEventLabel2EditPart.VISUAL_ID:
			return getEndMessageEventName_5028Parser();
		case IntermediateThrowMessageEventLabel2EditPart.VISUAL_ID:
			return getIntermediateThrowMessageEventName_5030Parser();
		case TextAnnotationText2EditPart.VISUAL_ID:
			return getTextAnnotationText_5010Parser();
		case StartTimerEventLabel2EditPart.VISUAL_ID:
			return getStartTimerEventName_5031Parser();
		case IntermediateCatchTimerEventLabel2EditPart.VISUAL_ID:
			return getIntermediateCatchTimerEventName_5032Parser();
		case StartSignalEventLabel2EditPart.VISUAL_ID:
			return getStartSignalEventName_5038Parser();
		case IntermediateThrowSignalEventLabel2EditPart.VISUAL_ID:
			return getIntermediateThrowSignalEventName_5037Parser();
		case IntermediateCatchSignalEventLabel2EditPart.VISUAL_ID:
			return getIntermediateCatchSignalEventName_5036Parser();
		case EndSignalEventLabel2EditPart.VISUAL_ID:
			return getEndSignalEventName_5035Parser();
		case EndErrorEventLabel2EditPart.VISUAL_ID:
			return getEndErrorEventName_5052Parser();
		case EndTerminatedEventLabel2EditPart.VISUAL_ID:
			return getEndTerminatedEventName_5090Parser();
		case StartErrorEventLabel2EditPart.VISUAL_ID:
			return getStartErrorEventName_5086Parser();
		case InclusiveGatewayLabel2EditPart.VISUAL_ID:
			return getInclusiveGatewayName_5075Parser();
		case LaneNameEditPart.VISUAL_ID:
			return getLaneName_5007Parser();
		case StartEventLabel2EditPart.VISUAL_ID:
			return getStartEventName_5024Parser();
		case SubProcessEventLabel2EditPart.VISUAL_ID:
			return getSubProcessEventName_5083Parser();
		case ThrowLinkEventLabel2EditPart.VISUAL_ID:
			return getThrowLinkEventName_5033Parser();
		case CatchLinkEventLabel2EditPart.VISUAL_ID:
			return getCatchLinkEventName_5034Parser();
		case SequenceFlowNameEditPart.VISUAL_ID:
			return getSequenceFlowName_6001Parser();
		case MessageFlowLabelEditPart.VISUAL_ID:
			return getMessageFlowName_6003Parser();
		}
		return null;
	}

	/**
	* Utility method that consults ParserService
	* @generated
	*/
	public static IParser getParser(IElementType type, EObject object, String parserHint) {
		return ParserService.getInstance().getParser(new HintAdapter(type, object, parserHint));
	}

	/**
	* @generated
	*/
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(ProcessVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(ProcessVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	* @generated
	*/
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (ProcessElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	* @generated
	*/
	private static class HintAdapter extends ParserHintAdapter {

		/**
		* @generated
		*/
		private final IElementType elementType;

		/**
		* @generated
		*/
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		* @generated
		*/
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}
