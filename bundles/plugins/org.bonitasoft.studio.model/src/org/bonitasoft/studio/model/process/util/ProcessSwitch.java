/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.model.process.util;

import org.bonitasoft.studio.model.process.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.model.process.ProcessPackage
 * @generated
 */
public class ProcessSwitch<T> extends Switch<T> {
	/**
     * The cached model package
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static ProcessPackage modelPackage;

	/**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ProcessSwitch() {
        if (modelPackage == null) {
            modelPackage = ProcessPackage.eINSTANCE;
        }
    }

	/**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

	/**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case ProcessPackage.ABSTRACT_CATCH_MESSAGE_EVENT: {
                AbstractCatchMessageEvent abstractCatchMessageEvent = (AbstractCatchMessageEvent)theEObject;
                T result = caseAbstractCatchMessageEvent(abstractCatchMessageEvent);
                if (result == null) result = caseElement(abstractCatchMessageEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.ABSTRACT_PAGE_FLOW: {
                AbstractPageFlow abstractPageFlow = (AbstractPageFlow)theEObject;
                T result = caseAbstractPageFlow(abstractPageFlow);
                if (result == null) result = caseElement(abstractPageFlow);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.ABSTRACT_PROCESS: {
                AbstractProcess abstractProcess = (AbstractProcess)theEObject;
                T result = caseAbstractProcess(abstractProcess);
                if (result == null) result = caseContainer(abstractProcess);
                if (result == null) result = casePageFlow(abstractProcess);
                if (result == null) result = caseRecapFlow(abstractProcess);
                if (result == null) result = caseConnectableElement(abstractProcess);
                if (result == null) result = caseAbstractPageFlow(abstractProcess);
                if (result == null) result = caseElement(abstractProcess);
                if (result == null) result = caseDataAware(abstractProcess);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.ABSTRACT_TIMER_EVENT: {
                AbstractTimerEvent abstractTimerEvent = (AbstractTimerEvent)theEObject;
                T result = caseAbstractTimerEvent(abstractTimerEvent);
                if (result == null) result = caseElement(abstractTimerEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.ACTIVITY: {
                Activity activity = (Activity)theEObject;
                T result = caseActivity(activity);
                if (result == null) result = caseFlowElement(activity);
                if (result == null) result = caseConnectableElement(activity);
                if (result == null) result = caseOperationContainer(activity);
                if (result == null) result = caseMultiInstantiable(activity);
                if (result == null) result = caseSourceElement(activity);
                if (result == null) result = caseTargetElement(activity);
                if (result == null) result = caseDataAware(activity);
                if (result == null) result = caseElement(activity);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.ACTOR: {
                Actor actor = (Actor)theEObject;
                T result = caseActor(actor);
                if (result == null) result = caseElement(actor);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.ACTOR_FILTER: {
                ActorFilter actorFilter = (ActorFilter)theEObject;
                T result = caseActorFilter(actorFilter);
                if (result == null) result = caseConnector(actorFilter);
                if (result == null) result = caseElement(actorFilter);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.AND_GATEWAY: {
                ANDGateway andGateway = (ANDGateway)theEObject;
                T result = caseANDGateway(andGateway);
                if (result == null) result = caseGateway(andGateway);
                if (result == null) result = caseFlowElement(andGateway);
                if (result == null) result = caseSourceElement(andGateway);
                if (result == null) result = caseTargetElement(andGateway);
                if (result == null) result = caseElement(andGateway);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.ASSIGNABLE: {
                Assignable assignable = (Assignable)theEObject;
                T result = caseAssignable(assignable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.ASSOCIATION: {
                Association association = (Association)theEObject;
                T result = caseAssociation(association);
                if (result == null) result = caseConnection(association);
                if (result == null) result = caseElement(association);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.BOOLEAN_TYPE: {
                BooleanType booleanType = (BooleanType)theEObject;
                T result = caseBooleanType(booleanType);
                if (result == null) result = caseDataType(booleanType);
                if (result == null) result = caseElement(booleanType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.BOUNDARY_EVENT: {
                BoundaryEvent boundaryEvent = (BoundaryEvent)theEObject;
                T result = caseBoundaryEvent(boundaryEvent);
                if (result == null) result = caseSourceElement(boundaryEvent);
                if (result == null) result = caseElement(boundaryEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.BOUNDARY_MESSAGE_EVENT: {
                BoundaryMessageEvent boundaryMessageEvent = (BoundaryMessageEvent)theEObject;
                T result = caseBoundaryMessageEvent(boundaryMessageEvent);
                if (result == null) result = caseBoundaryEvent(boundaryMessageEvent);
                if (result == null) result = caseAbstractCatchMessageEvent(boundaryMessageEvent);
                if (result == null) result = caseSourceElement(boundaryMessageEvent);
                if (result == null) result = caseElement(boundaryMessageEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.BOUNDARY_SIGNAL_EVENT: {
                BoundarySignalEvent boundarySignalEvent = (BoundarySignalEvent)theEObject;
                T result = caseBoundarySignalEvent(boundarySignalEvent);
                if (result == null) result = caseBoundaryEvent(boundarySignalEvent);
                if (result == null) result = caseSignalEvent(boundarySignalEvent);
                if (result == null) result = caseSourceElement(boundarySignalEvent);
                if (result == null) result = caseElement(boundarySignalEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.BOUNDARY_TIMER_EVENT: {
                BoundaryTimerEvent boundaryTimerEvent = (BoundaryTimerEvent)theEObject;
                T result = caseBoundaryTimerEvent(boundaryTimerEvent);
                if (result == null) result = caseBoundaryEvent(boundaryTimerEvent);
                if (result == null) result = caseAbstractTimerEvent(boundaryTimerEvent);
                if (result == null) result = caseSourceElement(boundaryTimerEvent);
                if (result == null) result = caseElement(boundaryTimerEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.BUSINESS_OBJECT_DATA: {
                BusinessObjectData businessObjectData = (BusinessObjectData)theEObject;
                T result = caseBusinessObjectData(businessObjectData);
                if (result == null) result = caseJavaObjectData(businessObjectData);
                if (result == null) result = caseData(businessObjectData);
                if (result == null) result = caseElement(businessObjectData);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.BUSINESS_OBJECT_TYPE: {
                BusinessObjectType businessObjectType = (BusinessObjectType)theEObject;
                T result = caseBusinessObjectType(businessObjectType);
                if (result == null) result = caseDataType(businessObjectType);
                if (result == null) result = caseElement(businessObjectType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.CALL_ACTIVITY: {
                CallActivity callActivity = (CallActivity)theEObject;
                T result = caseCallActivity(callActivity);
                if (result == null) result = caseActivity(callActivity);
                if (result == null) result = caseFlowElement(callActivity);
                if (result == null) result = caseConnectableElement(callActivity);
                if (result == null) result = caseOperationContainer(callActivity);
                if (result == null) result = caseMultiInstantiable(callActivity);
                if (result == null) result = caseSourceElement(callActivity);
                if (result == null) result = caseTargetElement(callActivity);
                if (result == null) result = caseDataAware(callActivity);
                if (result == null) result = caseElement(callActivity);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.CATCH_LINK_EVENT: {
                CatchLinkEvent catchLinkEvent = (CatchLinkEvent)theEObject;
                T result = caseCatchLinkEvent(catchLinkEvent);
                if (result == null) result = caseLinkEvent(catchLinkEvent);
                if (result == null) result = caseEvent(catchLinkEvent);
                if (result == null) result = caseFlowElement(catchLinkEvent);
                if (result == null) result = caseSourceElement(catchLinkEvent);
                if (result == null) result = caseTargetElement(catchLinkEvent);
                if (result == null) result = caseElement(catchLinkEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.CATCH_MESSAGE_EVENT: {
                CatchMessageEvent catchMessageEvent = (CatchMessageEvent)theEObject;
                T result = caseCatchMessageEvent(catchMessageEvent);
                if (result == null) result = caseMessageEvent(catchMessageEvent);
                if (result == null) result = caseAbstractCatchMessageEvent(catchMessageEvent);
                if (result == null) result = caseDataAware(catchMessageEvent);
                if (result == null) result = caseEvent(catchMessageEvent);
                if (result == null) result = caseFlowElement(catchMessageEvent);
                if (result == null) result = caseSourceElement(catchMessageEvent);
                if (result == null) result = caseTargetElement(catchMessageEvent);
                if (result == null) result = caseElement(catchMessageEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.CATCH_SIGNAL_EVENT: {
                CatchSignalEvent catchSignalEvent = (CatchSignalEvent)theEObject;
                T result = caseCatchSignalEvent(catchSignalEvent);
                if (result == null) result = caseSignalEvent(catchSignalEvent);
                if (result == null) result = caseElement(catchSignalEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.CONNECTABLE_ELEMENT: {
                ConnectableElement connectableElement = (ConnectableElement)theEObject;
                T result = caseConnectableElement(connectableElement);
                if (result == null) result = caseElement(connectableElement);
                if (result == null) result = caseDataAware(connectableElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.CONNECTOR: {
                Connector connector = (Connector)theEObject;
                T result = caseConnector(connector);
                if (result == null) result = caseElement(connector);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.CONTAINER: {
                Container container = (Container)theEObject;
                T result = caseContainer(container);
                if (result == null) result = caseElement(container);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.CONTRACT: {
                Contract contract = (Contract)theEObject;
                T result = caseContract(contract);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.CONTRACT_INPUT_MAPPING: {
                ContractInputMapping contractInputMapping = (ContractInputMapping)theEObject;
                T result = caseContractInputMapping(contractInputMapping);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.CONTRACT_INPUT: {
                ContractInput contractInput = (ContractInput)theEObject;
                T result = caseContractInput(contractInput);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.CONTRACT_CONSTRAINT: {
                ContractConstraint contractConstraint = (ContractConstraint)theEObject;
                T result = caseContractConstraint(contractConstraint);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.CONTRACT_CONTAINER: {
                ContractContainer contractContainer = (ContractContainer)theEObject;
                T result = caseContractContainer(contractContainer);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.CONNECTION: {
                Connection connection = (Connection)theEObject;
                T result = caseConnection(connection);
                if (result == null) result = caseElement(connection);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.CORRELATION: {
                Correlation correlation = (Correlation)theEObject;
                T result = caseCorrelation(correlation);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.CORRELATION_ASSOCIATION: {
                CorrelationAssociation correlationAssociation = (CorrelationAssociation)theEObject;
                T result = caseCorrelationAssociation(correlationAssociation);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.DATA: {
                Data data = (Data)theEObject;
                T result = caseData(data);
                if (result == null) result = caseElement(data);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.DATA_AWARE: {
                DataAware dataAware = (DataAware)theEObject;
                T result = caseDataAware(dataAware);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.DATE_TYPE: {
                DateType dateType = (DateType)theEObject;
                T result = caseDateType(dateType);
                if (result == null) result = caseStringType(dateType);
                if (result == null) result = caseDataType(dateType);
                if (result == null) result = caseElement(dateType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.DATA_TYPE: {
                DataType dataType = (DataType)theEObject;
                T result = caseDataType(dataType);
                if (result == null) result = caseElement(dataType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.DOCUMENT: {
                Document document = (Document)theEObject;
                T result = caseDocument(document);
                if (result == null) result = caseElement(document);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.DOUBLE_TYPE: {
                DoubleType doubleType = (DoubleType)theEObject;
                T result = caseDoubleType(doubleType);
                if (result == null) result = caseDataType(doubleType);
                if (result == null) result = caseElement(doubleType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.ELEMENT: {
                Element element = (Element)theEObject;
                T result = caseElement(element);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.EVENT: {
                Event event = (Event)theEObject;
                T result = caseEvent(event);
                if (result == null) result = caseFlowElement(event);
                if (result == null) result = caseSourceElement(event);
                if (result == null) result = caseTargetElement(event);
                if (result == null) result = caseElement(event);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.END_ERROR_EVENT: {
                EndErrorEvent endErrorEvent = (EndErrorEvent)theEObject;
                T result = caseEndErrorEvent(endErrorEvent);
                if (result == null) result = caseEvent(endErrorEvent);
                if (result == null) result = caseErrorEvent(endErrorEvent);
                if (result == null) result = caseFlowElement(endErrorEvent);
                if (result == null) result = caseSourceElement(endErrorEvent);
                if (result == null) result = caseTargetElement(endErrorEvent);
                if (result == null) result = caseElement(endErrorEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.END_EVENT: {
                EndEvent endEvent = (EndEvent)theEObject;
                T result = caseEndEvent(endEvent);
                if (result == null) result = caseEvent(endEvent);
                if (result == null) result = caseFlowElement(endEvent);
                if (result == null) result = caseSourceElement(endEvent);
                if (result == null) result = caseTargetElement(endEvent);
                if (result == null) result = caseElement(endEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.END_MESSAGE_EVENT: {
                EndMessageEvent endMessageEvent = (EndMessageEvent)theEObject;
                T result = caseEndMessageEvent(endMessageEvent);
                if (result == null) result = caseThrowMessageEvent(endMessageEvent);
                if (result == null) result = caseMessageEvent(endMessageEvent);
                if (result == null) result = caseEvent(endMessageEvent);
                if (result == null) result = caseFlowElement(endMessageEvent);
                if (result == null) result = caseSourceElement(endMessageEvent);
                if (result == null) result = caseTargetElement(endMessageEvent);
                if (result == null) result = caseElement(endMessageEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.END_SIGNAL_EVENT: {
                EndSignalEvent endSignalEvent = (EndSignalEvent)theEObject;
                T result = caseEndSignalEvent(endSignalEvent);
                if (result == null) result = caseThrowSignalEvent(endSignalEvent);
                if (result == null) result = caseEvent(endSignalEvent);
                if (result == null) result = caseSignalEvent(endSignalEvent);
                if (result == null) result = caseFlowElement(endSignalEvent);
                if (result == null) result = caseSourceElement(endSignalEvent);
                if (result == null) result = caseTargetElement(endSignalEvent);
                if (result == null) result = caseElement(endSignalEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.END_TERMINATED_EVENT: {
                EndTerminatedEvent endTerminatedEvent = (EndTerminatedEvent)theEObject;
                T result = caseEndTerminatedEvent(endTerminatedEvent);
                if (result == null) result = caseEvent(endTerminatedEvent);
                if (result == null) result = caseFlowElement(endTerminatedEvent);
                if (result == null) result = caseSourceElement(endTerminatedEvent);
                if (result == null) result = caseTargetElement(endTerminatedEvent);
                if (result == null) result = caseElement(endTerminatedEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.ERROR_EVENT: {
                ErrorEvent errorEvent = (ErrorEvent)theEObject;
                T result = caseErrorEvent(errorEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.ENUM_TYPE: {
                EnumType enumType = (EnumType)theEObject;
                T result = caseEnumType(enumType);
                if (result == null) result = caseDataType(enumType);
                if (result == null) result = caseElement(enumType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.FLOAT_TYPE: {
                FloatType floatType = (FloatType)theEObject;
                T result = caseFloatType(floatType);
                if (result == null) result = caseDataType(floatType);
                if (result == null) result = caseElement(floatType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.FLOW_ELEMENT: {
                FlowElement flowElement = (FlowElement)theEObject;
                T result = caseFlowElement(flowElement);
                if (result == null) result = caseSourceElement(flowElement);
                if (result == null) result = caseTargetElement(flowElement);
                if (result == null) result = caseElement(flowElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.FORM_MAPPING: {
                FormMapping formMapping = (FormMapping)theEObject;
                T result = caseFormMapping(formMapping);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.GATEWAY: {
                Gateway gateway = (Gateway)theEObject;
                T result = caseGateway(gateway);
                if (result == null) result = caseFlowElement(gateway);
                if (result == null) result = caseSourceElement(gateway);
                if (result == null) result = caseTargetElement(gateway);
                if (result == null) result = caseElement(gateway);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.INCLUSIVE_GATEWAY: {
                InclusiveGateway inclusiveGateway = (InclusiveGateway)theEObject;
                T result = caseInclusiveGateway(inclusiveGateway);
                if (result == null) result = caseGateway(inclusiveGateway);
                if (result == null) result = caseFlowElement(inclusiveGateway);
                if (result == null) result = caseSourceElement(inclusiveGateway);
                if (result == null) result = caseTargetElement(inclusiveGateway);
                if (result == null) result = caseElement(inclusiveGateway);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.INPUT_MAPPING: {
                InputMapping inputMapping = (InputMapping)theEObject;
                T result = caseInputMapping(inputMapping);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.INTEGER_TYPE: {
                IntegerType integerType = (IntegerType)theEObject;
                T result = caseIntegerType(integerType);
                if (result == null) result = caseDataType(integerType);
                if (result == null) result = caseElement(integerType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.INTERMEDIATE_ERROR_CATCH_EVENT: {
                IntermediateErrorCatchEvent intermediateErrorCatchEvent = (IntermediateErrorCatchEvent)theEObject;
                T result = caseIntermediateErrorCatchEvent(intermediateErrorCatchEvent);
                if (result == null) result = caseBoundaryEvent(intermediateErrorCatchEvent);
                if (result == null) result = caseErrorEvent(intermediateErrorCatchEvent);
                if (result == null) result = caseSourceElement(intermediateErrorCatchEvent);
                if (result == null) result = caseElement(intermediateErrorCatchEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT: {
                IntermediateCatchSignalEvent intermediateCatchSignalEvent = (IntermediateCatchSignalEvent)theEObject;
                T result = caseIntermediateCatchSignalEvent(intermediateCatchSignalEvent);
                if (result == null) result = caseCatchSignalEvent(intermediateCatchSignalEvent);
                if (result == null) result = caseEvent(intermediateCatchSignalEvent);
                if (result == null) result = caseDataAware(intermediateCatchSignalEvent);
                if (result == null) result = caseSignalEvent(intermediateCatchSignalEvent);
                if (result == null) result = caseFlowElement(intermediateCatchSignalEvent);
                if (result == null) result = caseSourceElement(intermediateCatchSignalEvent);
                if (result == null) result = caseTargetElement(intermediateCatchSignalEvent);
                if (result == null) result = caseElement(intermediateCatchSignalEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT: {
                IntermediateThrowSignalEvent intermediateThrowSignalEvent = (IntermediateThrowSignalEvent)theEObject;
                T result = caseIntermediateThrowSignalEvent(intermediateThrowSignalEvent);
                if (result == null) result = caseThrowSignalEvent(intermediateThrowSignalEvent);
                if (result == null) result = caseEvent(intermediateThrowSignalEvent);
                if (result == null) result = caseSignalEvent(intermediateThrowSignalEvent);
                if (result == null) result = caseFlowElement(intermediateThrowSignalEvent);
                if (result == null) result = caseSourceElement(intermediateThrowSignalEvent);
                if (result == null) result = caseTargetElement(intermediateThrowSignalEvent);
                if (result == null) result = caseElement(intermediateThrowSignalEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT: {
                IntermediateCatchMessageEvent intermediateCatchMessageEvent = (IntermediateCatchMessageEvent)theEObject;
                T result = caseIntermediateCatchMessageEvent(intermediateCatchMessageEvent);
                if (result == null) result = caseCatchMessageEvent(intermediateCatchMessageEvent);
                if (result == null) result = caseMessageEvent(intermediateCatchMessageEvent);
                if (result == null) result = caseAbstractCatchMessageEvent(intermediateCatchMessageEvent);
                if (result == null) result = caseDataAware(intermediateCatchMessageEvent);
                if (result == null) result = caseEvent(intermediateCatchMessageEvent);
                if (result == null) result = caseFlowElement(intermediateCatchMessageEvent);
                if (result == null) result = caseSourceElement(intermediateCatchMessageEvent);
                if (result == null) result = caseTargetElement(intermediateCatchMessageEvent);
                if (result == null) result = caseElement(intermediateCatchMessageEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.INTERMEDIATE_THROW_MESSAGE_EVENT: {
                IntermediateThrowMessageEvent intermediateThrowMessageEvent = (IntermediateThrowMessageEvent)theEObject;
                T result = caseIntermediateThrowMessageEvent(intermediateThrowMessageEvent);
                if (result == null) result = caseThrowMessageEvent(intermediateThrowMessageEvent);
                if (result == null) result = caseMessageEvent(intermediateThrowMessageEvent);
                if (result == null) result = caseEvent(intermediateThrowMessageEvent);
                if (result == null) result = caseFlowElement(intermediateThrowMessageEvent);
                if (result == null) result = caseSourceElement(intermediateThrowMessageEvent);
                if (result == null) result = caseTargetElement(intermediateThrowMessageEvent);
                if (result == null) result = caseElement(intermediateThrowMessageEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.INTERMEDIATE_CATCH_TIMER_EVENT: {
                IntermediateCatchTimerEvent intermediateCatchTimerEvent = (IntermediateCatchTimerEvent)theEObject;
                T result = caseIntermediateCatchTimerEvent(intermediateCatchTimerEvent);
                if (result == null) result = caseTimerEvent(intermediateCatchTimerEvent);
                if (result == null) result = caseEvent(intermediateCatchTimerEvent);
                if (result == null) result = caseAbstractTimerEvent(intermediateCatchTimerEvent);
                if (result == null) result = caseDataAware(intermediateCatchTimerEvent);
                if (result == null) result = caseFlowElement(intermediateCatchTimerEvent);
                if (result == null) result = caseSourceElement(intermediateCatchTimerEvent);
                if (result == null) result = caseTargetElement(intermediateCatchTimerEvent);
                if (result == null) result = caseElement(intermediateCatchTimerEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.JAVA_OBJECT_DATA: {
                JavaObjectData javaObjectData = (JavaObjectData)theEObject;
                T result = caseJavaObjectData(javaObjectData);
                if (result == null) result = caseData(javaObjectData);
                if (result == null) result = caseElement(javaObjectData);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.JAVA_TYPE: {
                JavaType javaType = (JavaType)theEObject;
                T result = caseJavaType(javaType);
                if (result == null) result = caseDataType(javaType);
                if (result == null) result = caseElement(javaType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.LANE: {
                Lane lane = (Lane)theEObject;
                T result = caseLane(lane);
                if (result == null) result = caseContainer(lane);
                if (result == null) result = caseAssignable(lane);
                if (result == null) result = caseElement(lane);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.LINK_EVENT: {
                LinkEvent linkEvent = (LinkEvent)theEObject;
                T result = caseLinkEvent(linkEvent);
                if (result == null) result = caseEvent(linkEvent);
                if (result == null) result = caseFlowElement(linkEvent);
                if (result == null) result = caseSourceElement(linkEvent);
                if (result == null) result = caseTargetElement(linkEvent);
                if (result == null) result = caseElement(linkEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.LONG_TYPE: {
                LongType longType = (LongType)theEObject;
                T result = caseLongType(longType);
                if (result == null) result = caseDataType(longType);
                if (result == null) result = caseElement(longType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.MAIN_PROCESS: {
                MainProcess mainProcess = (MainProcess)theEObject;
                T result = caseMainProcess(mainProcess);
                if (result == null) result = caseAbstractProcess(mainProcess);
                if (result == null) result = caseContainer(mainProcess);
                if (result == null) result = casePageFlow(mainProcess);
                if (result == null) result = caseRecapFlow(mainProcess);
                if (result == null) result = caseConnectableElement(mainProcess);
                if (result == null) result = caseAbstractPageFlow(mainProcess);
                if (result == null) result = caseElement(mainProcess);
                if (result == null) result = caseDataAware(mainProcess);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.MESSAGE: {
                Message message = (Message)theEObject;
                T result = caseMessage(message);
                if (result == null) result = caseElement(message);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.MESSAGE_FLOW: {
                MessageFlow messageFlow = (MessageFlow)theEObject;
                T result = caseMessageFlow(messageFlow);
                if (result == null) result = caseElement(messageFlow);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.MESSAGE_EVENT: {
                MessageEvent messageEvent = (MessageEvent)theEObject;
                T result = caseMessageEvent(messageEvent);
                if (result == null) result = caseEvent(messageEvent);
                if (result == null) result = caseFlowElement(messageEvent);
                if (result == null) result = caseSourceElement(messageEvent);
                if (result == null) result = caseTargetElement(messageEvent);
                if (result == null) result = caseElement(messageEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.MULTI_INSTANTIABLE: {
                MultiInstantiable multiInstantiable = (MultiInstantiable)theEObject;
                T result = caseMultiInstantiable(multiInstantiable);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.NON_INTERRUPTING_BOUNDARY_TIMER_EVENT: {
                NonInterruptingBoundaryTimerEvent nonInterruptingBoundaryTimerEvent = (NonInterruptingBoundaryTimerEvent)theEObject;
                T result = caseNonInterruptingBoundaryTimerEvent(nonInterruptingBoundaryTimerEvent);
                if (result == null) result = caseBoundaryTimerEvent(nonInterruptingBoundaryTimerEvent);
                if (result == null) result = caseBoundaryEvent(nonInterruptingBoundaryTimerEvent);
                if (result == null) result = caseAbstractTimerEvent(nonInterruptingBoundaryTimerEvent);
                if (result == null) result = caseSourceElement(nonInterruptingBoundaryTimerEvent);
                if (result == null) result = caseElement(nonInterruptingBoundaryTimerEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.OPERATION_CONTAINER: {
                OperationContainer operationContainer = (OperationContainer)theEObject;
                T result = caseOperationContainer(operationContainer);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.OUTPUT_MAPPING: {
                OutputMapping outputMapping = (OutputMapping)theEObject;
                T result = caseOutputMapping(outputMapping);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.PAGE_FLOW: {
                PageFlow pageFlow = (PageFlow)theEObject;
                T result = casePageFlow(pageFlow);
                if (result == null) result = caseConnectableElement(pageFlow);
                if (result == null) result = caseAbstractPageFlow(pageFlow);
                if (result == null) result = caseElement(pageFlow);
                if (result == null) result = caseDataAware(pageFlow);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.POOL: {
                Pool pool = (Pool)theEObject;
                T result = casePool(pool);
                if (result == null) result = caseAbstractProcess(pool);
                if (result == null) result = caseContractContainer(pool);
                if (result == null) result = caseContainer(pool);
                if (result == null) result = casePageFlow(pool);
                if (result == null) result = caseRecapFlow(pool);
                if (result == null) result = caseConnectableElement(pool);
                if (result == null) result = caseAbstractPageFlow(pool);
                if (result == null) result = caseElement(pool);
                if (result == null) result = caseDataAware(pool);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.RECAP_FLOW: {
                RecapFlow recapFlow = (RecapFlow)theEObject;
                T result = caseRecapFlow(recapFlow);
                if (result == null) result = caseAbstractPageFlow(recapFlow);
                if (result == null) result = caseElement(recapFlow);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.RECEIVE_TASK: {
                ReceiveTask receiveTask = (ReceiveTask)theEObject;
                T result = caseReceiveTask(receiveTask);
                if (result == null) result = caseActivity(receiveTask);
                if (result == null) result = caseCatchMessageEvent(receiveTask);
                if (result == null) result = caseConnectableElement(receiveTask);
                if (result == null) result = caseOperationContainer(receiveTask);
                if (result == null) result = caseMultiInstantiable(receiveTask);
                if (result == null) result = caseMessageEvent(receiveTask);
                if (result == null) result = caseAbstractCatchMessageEvent(receiveTask);
                if (result == null) result = caseSourceElement(receiveTask);
                if (result == null) result = caseTargetElement(receiveTask);
                if (result == null) result = caseDataAware(receiveTask);
                if (result == null) result = caseEvent(receiveTask);
                if (result == null) result = caseFlowElement(receiveTask);
                if (result == null) result = caseElement(receiveTask);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.SEQUENCE_FLOW: {
                SequenceFlow sequenceFlow = (SequenceFlow)theEObject;
                T result = caseSequenceFlow(sequenceFlow);
                if (result == null) result = caseConnection(sequenceFlow);
                if (result == null) result = caseElement(sequenceFlow);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.SIGNAL_EVENT: {
                SignalEvent signalEvent = (SignalEvent)theEObject;
                T result = caseSignalEvent(signalEvent);
                if (result == null) result = caseElement(signalEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.SOURCE_ELEMENT: {
                SourceElement sourceElement = (SourceElement)theEObject;
                T result = caseSourceElement(sourceElement);
                if (result == null) result = caseElement(sourceElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.STRING_TYPE: {
                StringType stringType = (StringType)theEObject;
                T result = caseStringType(stringType);
                if (result == null) result = caseDataType(stringType);
                if (result == null) result = caseElement(stringType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.SCRIPT_TASK: {
                ScriptTask scriptTask = (ScriptTask)theEObject;
                T result = caseScriptTask(scriptTask);
                if (result == null) result = caseActivity(scriptTask);
                if (result == null) result = caseFlowElement(scriptTask);
                if (result == null) result = caseConnectableElement(scriptTask);
                if (result == null) result = caseOperationContainer(scriptTask);
                if (result == null) result = caseMultiInstantiable(scriptTask);
                if (result == null) result = caseSourceElement(scriptTask);
                if (result == null) result = caseTargetElement(scriptTask);
                if (result == null) result = caseDataAware(scriptTask);
                if (result == null) result = caseElement(scriptTask);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.SEARCH_INDEX: {
                SearchIndex searchIndex = (SearchIndex)theEObject;
                T result = caseSearchIndex(searchIndex);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.SEND_TASK: {
                SendTask sendTask = (SendTask)theEObject;
                T result = caseSendTask(sendTask);
                if (result == null) result = caseActivity(sendTask);
                if (result == null) result = caseThrowMessageEvent(sendTask);
                if (result == null) result = caseConnectableElement(sendTask);
                if (result == null) result = caseOperationContainer(sendTask);
                if (result == null) result = caseMultiInstantiable(sendTask);
                if (result == null) result = caseMessageEvent(sendTask);
                if (result == null) result = caseSourceElement(sendTask);
                if (result == null) result = caseTargetElement(sendTask);
                if (result == null) result = caseDataAware(sendTask);
                if (result == null) result = caseEvent(sendTask);
                if (result == null) result = caseFlowElement(sendTask);
                if (result == null) result = caseElement(sendTask);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.SERVICE_TASK: {
                ServiceTask serviceTask = (ServiceTask)theEObject;
                T result = caseServiceTask(serviceTask);
                if (result == null) result = caseActivity(serviceTask);
                if (result == null) result = caseFlowElement(serviceTask);
                if (result == null) result = caseConnectableElement(serviceTask);
                if (result == null) result = caseOperationContainer(serviceTask);
                if (result == null) result = caseMultiInstantiable(serviceTask);
                if (result == null) result = caseSourceElement(serviceTask);
                if (result == null) result = caseTargetElement(serviceTask);
                if (result == null) result = caseDataAware(serviceTask);
                if (result == null) result = caseElement(serviceTask);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.START_ERROR_EVENT: {
                StartErrorEvent startErrorEvent = (StartErrorEvent)theEObject;
                T result = caseStartErrorEvent(startErrorEvent);
                if (result == null) result = caseErrorEvent(startErrorEvent);
                if (result == null) result = caseEvent(startErrorEvent);
                if (result == null) result = caseFlowElement(startErrorEvent);
                if (result == null) result = caseSourceElement(startErrorEvent);
                if (result == null) result = caseTargetElement(startErrorEvent);
                if (result == null) result = caseElement(startErrorEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.START_EVENT: {
                StartEvent startEvent = (StartEvent)theEObject;
                T result = caseStartEvent(startEvent);
                if (result == null) result = caseEvent(startEvent);
                if (result == null) result = caseFlowElement(startEvent);
                if (result == null) result = caseSourceElement(startEvent);
                if (result == null) result = caseTargetElement(startEvent);
                if (result == null) result = caseElement(startEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.START_MESSAGE_EVENT: {
                StartMessageEvent startMessageEvent = (StartMessageEvent)theEObject;
                T result = caseStartMessageEvent(startMessageEvent);
                if (result == null) result = caseCatchMessageEvent(startMessageEvent);
                if (result == null) result = caseMessageEvent(startMessageEvent);
                if (result == null) result = caseAbstractCatchMessageEvent(startMessageEvent);
                if (result == null) result = caseDataAware(startMessageEvent);
                if (result == null) result = caseEvent(startMessageEvent);
                if (result == null) result = caseFlowElement(startMessageEvent);
                if (result == null) result = caseSourceElement(startMessageEvent);
                if (result == null) result = caseTargetElement(startMessageEvent);
                if (result == null) result = caseElement(startMessageEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.START_SIGNAL_EVENT: {
                StartSignalEvent startSignalEvent = (StartSignalEvent)theEObject;
                T result = caseStartSignalEvent(startSignalEvent);
                if (result == null) result = caseCatchSignalEvent(startSignalEvent);
                if (result == null) result = caseEvent(startSignalEvent);
                if (result == null) result = caseDataAware(startSignalEvent);
                if (result == null) result = caseSignalEvent(startSignalEvent);
                if (result == null) result = caseFlowElement(startSignalEvent);
                if (result == null) result = caseSourceElement(startSignalEvent);
                if (result == null) result = caseTargetElement(startSignalEvent);
                if (result == null) result = caseElement(startSignalEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.START_TIMER_EVENT: {
                StartTimerEvent startTimerEvent = (StartTimerEvent)theEObject;
                T result = caseStartTimerEvent(startTimerEvent);
                if (result == null) result = caseTimerEvent(startTimerEvent);
                if (result == null) result = caseEvent(startTimerEvent);
                if (result == null) result = caseAbstractTimerEvent(startTimerEvent);
                if (result == null) result = caseDataAware(startTimerEvent);
                if (result == null) result = caseFlowElement(startTimerEvent);
                if (result == null) result = caseSourceElement(startTimerEvent);
                if (result == null) result = caseTargetElement(startTimerEvent);
                if (result == null) result = caseElement(startTimerEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.SUB_PROCESS_EVENT: {
                SubProcessEvent subProcessEvent = (SubProcessEvent)theEObject;
                T result = caseSubProcessEvent(subProcessEvent);
                if (result == null) result = caseContainer(subProcessEvent);
                if (result == null) result = caseElement(subProcessEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.TASK: {
                Task task = (Task)theEObject;
                T result = caseTask(task);
                if (result == null) result = caseActivity(task);
                if (result == null) result = casePageFlow(task);
                if (result == null) result = caseAssignable(task);
                if (result == null) result = caseContractContainer(task);
                if (result == null) result = caseFlowElement(task);
                if (result == null) result = caseConnectableElement(task);
                if (result == null) result = caseOperationContainer(task);
                if (result == null) result = caseMultiInstantiable(task);
                if (result == null) result = caseAbstractPageFlow(task);
                if (result == null) result = caseSourceElement(task);
                if (result == null) result = caseTargetElement(task);
                if (result == null) result = caseDataAware(task);
                if (result == null) result = caseElement(task);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.TARGET_ELEMENT: {
                TargetElement targetElement = (TargetElement)theEObject;
                T result = caseTargetElement(targetElement);
                if (result == null) result = caseElement(targetElement);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.TEXT_ANNOTATION: {
                TextAnnotation textAnnotation = (TextAnnotation)theEObject;
                T result = caseTextAnnotation(textAnnotation);
                if (result == null) result = caseElement(textAnnotation);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.TEXT_ANNOTATION_ATTACHMENT: {
                TextAnnotationAttachment textAnnotationAttachment = (TextAnnotationAttachment)theEObject;
                T result = caseTextAnnotationAttachment(textAnnotationAttachment);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.THROW_SIGNAL_EVENT: {
                ThrowSignalEvent throwSignalEvent = (ThrowSignalEvent)theEObject;
                T result = caseThrowSignalEvent(throwSignalEvent);
                if (result == null) result = caseSignalEvent(throwSignalEvent);
                if (result == null) result = caseElement(throwSignalEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.THROW_LINK_EVENT: {
                ThrowLinkEvent throwLinkEvent = (ThrowLinkEvent)theEObject;
                T result = caseThrowLinkEvent(throwLinkEvent);
                if (result == null) result = caseLinkEvent(throwLinkEvent);
                if (result == null) result = caseEvent(throwLinkEvent);
                if (result == null) result = caseFlowElement(throwLinkEvent);
                if (result == null) result = caseSourceElement(throwLinkEvent);
                if (result == null) result = caseTargetElement(throwLinkEvent);
                if (result == null) result = caseElement(throwLinkEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.THROW_MESSAGE_EVENT: {
                ThrowMessageEvent throwMessageEvent = (ThrowMessageEvent)theEObject;
                T result = caseThrowMessageEvent(throwMessageEvent);
                if (result == null) result = caseMessageEvent(throwMessageEvent);
                if (result == null) result = caseEvent(throwMessageEvent);
                if (result == null) result = caseFlowElement(throwMessageEvent);
                if (result == null) result = caseSourceElement(throwMessageEvent);
                if (result == null) result = caseTargetElement(throwMessageEvent);
                if (result == null) result = caseElement(throwMessageEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.TIMER_EVENT: {
                TimerEvent timerEvent = (TimerEvent)theEObject;
                T result = caseTimerEvent(timerEvent);
                if (result == null) result = caseEvent(timerEvent);
                if (result == null) result = caseAbstractTimerEvent(timerEvent);
                if (result == null) result = caseDataAware(timerEvent);
                if (result == null) result = caseFlowElement(timerEvent);
                if (result == null) result = caseSourceElement(timerEvent);
                if (result == null) result = caseTargetElement(timerEvent);
                if (result == null) result = caseElement(timerEvent);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.XML_DATA: {
                XMLData xmlData = (XMLData)theEObject;
                T result = caseXMLData(xmlData);
                if (result == null) result = caseData(xmlData);
                if (result == null) result = caseElement(xmlData);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.XML_TYPE: {
                XMLType xmlType = (XMLType)theEObject;
                T result = caseXMLType(xmlType);
                if (result == null) result = caseDataType(xmlType);
                if (result == null) result = caseElement(xmlType);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.XOR_GATEWAY: {
                XORGateway xorGateway = (XORGateway)theEObject;
                T result = caseXORGateway(xorGateway);
                if (result == null) result = caseGateway(xorGateway);
                if (result == null) result = caseFlowElement(xorGateway);
                if (result == null) result = caseSourceElement(xorGateway);
                if (result == null) result = caseTargetElement(xorGateway);
                if (result == null) result = caseElement(xorGateway);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case ProcessPackage.ADDITIONAL_RESOURCE: {
                AdditionalResource additionalResource = (AdditionalResource)theEObject;
                T result = caseAdditionalResource(additionalResource);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Catch Message Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Catch Message Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseAbstractCatchMessageEvent(AbstractCatchMessageEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Page Flow</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Page Flow</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseAbstractPageFlow(AbstractPageFlow object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Process</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Process</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseAbstractProcess(AbstractProcess object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Abstract Timer Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract Timer Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseAbstractTimerEvent(AbstractTimerEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Activity</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Activity</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseActivity(Activity object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Actor</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Actor</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseActor(Actor object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Actor Filter</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Actor Filter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseActorFilter(ActorFilter object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>AND Gateway</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>AND Gateway</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseANDGateway(ANDGateway object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Assignable</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Assignable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseAssignable(Assignable object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Association</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Association</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseAssociation(Association object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Boolean Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Boolean Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseBooleanType(BooleanType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Boundary Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Boundary Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseBoundaryEvent(BoundaryEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Boundary Message Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Boundary Message Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseBoundaryMessageEvent(BoundaryMessageEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Boundary Signal Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Boundary Signal Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseBoundarySignalEvent(BoundarySignalEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Boundary Timer Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Boundary Timer Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseBoundaryTimerEvent(BoundaryTimerEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Business Object Data</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Business Object Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseBusinessObjectData(BusinessObjectData object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Business Object Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Business Object Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseBusinessObjectType(BusinessObjectType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Call Activity</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Call Activity</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseCallActivity(CallActivity object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Catch Link Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Catch Link Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseCatchLinkEvent(CatchLinkEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Catch Message Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Catch Message Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseCatchMessageEvent(CatchMessageEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Catch Signal Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Catch Signal Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseCatchSignalEvent(CatchSignalEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Connectable Element</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Connectable Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseConnectableElement(ConnectableElement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Connector</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Connector</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseConnector(Connector object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Container</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseContainer(Container object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Contract</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Contract</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseContract(Contract object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Contract Input Mapping</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Contract Input Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseContractInputMapping(ContractInputMapping object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Contract Input</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Contract Input</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseContractInput(ContractInput object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Contract Constraint</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Contract Constraint</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseContractConstraint(ContractConstraint object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Contract Container</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Contract Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseContractContainer(ContractContainer object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Connection</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Connection</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseConnection(Connection object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Correlation</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Correlation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseCorrelation(Correlation object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Correlation Association</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Correlation Association</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseCorrelationAssociation(CorrelationAssociation object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Data</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseData(Data object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Data Aware</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Data Aware</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseDataAware(DataAware object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Date Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Date Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseDateType(DateType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Data Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Data Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseDataType(DataType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Document</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Document</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseDocument(Document object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Double Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Double Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseDoubleType(DoubleType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseElement(Element object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseEvent(Event object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>End Error Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>End Error Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseEndErrorEvent(EndErrorEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>End Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>End Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseEndEvent(EndEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>End Message Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>End Message Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseEndMessageEvent(EndMessageEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>End Signal Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>End Signal Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseEndSignalEvent(EndSignalEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>End Terminated Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>End Terminated Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseEndTerminatedEvent(EndTerminatedEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Error Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Error Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseErrorEvent(ErrorEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Enum Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Enum Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseEnumType(EnumType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Float Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Float Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseFloatType(FloatType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Flow Element</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Flow Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseFlowElement(FlowElement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Form Mapping</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Form Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseFormMapping(FormMapping object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Gateway</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Gateway</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseGateway(Gateway object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Inclusive Gateway</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Inclusive Gateway</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseInclusiveGateway(InclusiveGateway object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Input Mapping</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Input Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseInputMapping(InputMapping object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Integer Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Integer Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseIntegerType(IntegerType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Intermediate Error Catch Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Intermediate Error Catch Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseIntermediateErrorCatchEvent(IntermediateErrorCatchEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Intermediate Catch Signal Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Intermediate Catch Signal Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseIntermediateCatchSignalEvent(IntermediateCatchSignalEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Intermediate Throw Signal Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Intermediate Throw Signal Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseIntermediateThrowSignalEvent(IntermediateThrowSignalEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Intermediate Catch Message Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Intermediate Catch Message Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseIntermediateCatchMessageEvent(IntermediateCatchMessageEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Intermediate Throw Message Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Intermediate Throw Message Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseIntermediateThrowMessageEvent(IntermediateThrowMessageEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Intermediate Catch Timer Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Intermediate Catch Timer Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseIntermediateCatchTimerEvent(IntermediateCatchTimerEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Java Object Data</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Java Object Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseJavaObjectData(JavaObjectData object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Java Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Java Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseJavaType(JavaType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Lane</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Lane</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseLane(Lane object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Link Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Link Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseLinkEvent(LinkEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Long Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Long Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseLongType(LongType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Main Process</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Main Process</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseMainProcess(MainProcess object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Message</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Message</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseMessage(Message object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Message Flow</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Message Flow</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseMessageFlow(MessageFlow object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Message Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Message Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseMessageEvent(MessageEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Multi Instantiable</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Multi Instantiable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseMultiInstantiable(MultiInstantiable object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Non Interrupting Boundary Timer Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Non Interrupting Boundary Timer Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseNonInterruptingBoundaryTimerEvent(NonInterruptingBoundaryTimerEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Operation Container</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Operation Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseOperationContainer(OperationContainer object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Output Mapping</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Output Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseOutputMapping(OutputMapping object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Page Flow</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Page Flow</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T casePageFlow(PageFlow object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Pool</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Pool</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T casePool(Pool object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Recap Flow</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Recap Flow</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseRecapFlow(RecapFlow object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Receive Task</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Receive Task</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseReceiveTask(ReceiveTask object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Sequence Flow</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sequence Flow</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSequenceFlow(SequenceFlow object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Signal Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Signal Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSignalEvent(SignalEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Source Element</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Source Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSourceElement(SourceElement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>String Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>String Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseStringType(StringType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Script Task</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Script Task</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseScriptTask(ScriptTask object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Search Index</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Search Index</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSearchIndex(SearchIndex object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Send Task</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Send Task</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSendTask(SendTask object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Service Task</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Service Task</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseServiceTask(ServiceTask object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Start Error Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Start Error Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseStartErrorEvent(StartErrorEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Start Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Start Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseStartEvent(StartEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Start Message Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Start Message Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseStartMessageEvent(StartMessageEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Start Signal Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Start Signal Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseStartSignalEvent(StartSignalEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Start Timer Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Start Timer Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseStartTimerEvent(StartTimerEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Sub Process Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Sub Process Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseSubProcessEvent(SubProcessEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Task</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Task</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseTask(Task object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Target Element</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Target Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseTargetElement(TargetElement object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Text Annotation</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Text Annotation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseTextAnnotation(TextAnnotation object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Text Annotation Attachment</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Text Annotation Attachment</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseTextAnnotationAttachment(TextAnnotationAttachment object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Throw Signal Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Throw Signal Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseThrowSignalEvent(ThrowSignalEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Throw Link Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Throw Link Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseThrowLinkEvent(ThrowLinkEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Throw Message Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Throw Message Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseThrowMessageEvent(ThrowMessageEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Timer Event</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Timer Event</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseTimerEvent(TimerEvent object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Data</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseXMLData(XMLData object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XML Type</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XML Type</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseXMLType(XMLType object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>XOR Gateway</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>XOR Gateway</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseXORGateway(XORGateway object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Additional Resource</em>'.
     * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Additional Resource</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAdditionalResource(AdditionalResource object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
	@Override
	public T defaultCase(EObject object) {
        return null;
    }

} //ProcessSwitch
