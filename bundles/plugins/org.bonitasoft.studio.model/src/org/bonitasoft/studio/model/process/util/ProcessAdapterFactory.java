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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.model.process.ProcessPackage
 * @generated
 */
public class ProcessAdapterFactory extends AdapterFactoryImpl {
	/**
     * The cached model package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static ProcessPackage modelPackage;

	/**
     * Creates an instance of the adapter factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ProcessAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = ProcessPackage.eINSTANCE;
        }
    }

	/**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
	@Override
	public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject)object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

	/**
     * The switch that delegates to the <code>createXXX</code> methods.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ProcessSwitch<Adapter> modelSwitch =
		new ProcessSwitch<Adapter>() {
            @Override
            public Adapter caseAbstractCatchMessageEvent(AbstractCatchMessageEvent object) {
                return createAbstractCatchMessageEventAdapter();
            }
            @Override
            public Adapter caseAbstractPageFlow(AbstractPageFlow object) {
                return createAbstractPageFlowAdapter();
            }
            @Override
            public Adapter caseAbstractProcess(AbstractProcess object) {
                return createAbstractProcessAdapter();
            }
            @Override
            public Adapter caseAbstractTimerEvent(AbstractTimerEvent object) {
                return createAbstractTimerEventAdapter();
            }
            @Override
            public Adapter caseActivity(Activity object) {
                return createActivityAdapter();
            }
            @Override
            public Adapter caseActor(Actor object) {
                return createActorAdapter();
            }
            @Override
            public Adapter caseActorFilter(ActorFilter object) {
                return createActorFilterAdapter();
            }
            @Override
            public Adapter caseANDGateway(ANDGateway object) {
                return createANDGatewayAdapter();
            }
            @Override
            public Adapter caseAssignable(Assignable object) {
                return createAssignableAdapter();
            }
            @Override
            public Adapter caseAssociation(Association object) {
                return createAssociationAdapter();
            }
            @Override
            public Adapter caseBooleanType(BooleanType object) {
                return createBooleanTypeAdapter();
            }
            @Override
            public Adapter caseBoundaryEvent(BoundaryEvent object) {
                return createBoundaryEventAdapter();
            }
            @Override
            public Adapter caseBoundaryMessageEvent(BoundaryMessageEvent object) {
                return createBoundaryMessageEventAdapter();
            }
            @Override
            public Adapter caseBoundarySignalEvent(BoundarySignalEvent object) {
                return createBoundarySignalEventAdapter();
            }
            @Override
            public Adapter caseBoundaryTimerEvent(BoundaryTimerEvent object) {
                return createBoundaryTimerEventAdapter();
            }
            @Override
            public Adapter caseBusinessObjectData(BusinessObjectData object) {
                return createBusinessObjectDataAdapter();
            }
            @Override
            public Adapter caseBusinessObjectType(BusinessObjectType object) {
                return createBusinessObjectTypeAdapter();
            }
            @Override
            public Adapter caseCallActivity(CallActivity object) {
                return createCallActivityAdapter();
            }
            @Override
            public Adapter caseCatchLinkEvent(CatchLinkEvent object) {
                return createCatchLinkEventAdapter();
            }
            @Override
            public Adapter caseCatchMessageEvent(CatchMessageEvent object) {
                return createCatchMessageEventAdapter();
            }
            @Override
            public Adapter caseCatchSignalEvent(CatchSignalEvent object) {
                return createCatchSignalEventAdapter();
            }
            @Override
            public Adapter caseConnectableElement(ConnectableElement object) {
                return createConnectableElementAdapter();
            }
            @Override
            public Adapter caseConnector(Connector object) {
                return createConnectorAdapter();
            }
            @Override
            public Adapter caseContainer(Container object) {
                return createContainerAdapter();
            }
            @Override
            public Adapter caseContract(Contract object) {
                return createContractAdapter();
            }
            @Override
            public Adapter caseContractInputMapping(ContractInputMapping object) {
                return createContractInputMappingAdapter();
            }
            @Override
            public Adapter caseContractInput(ContractInput object) {
                return createContractInputAdapter();
            }
            @Override
            public Adapter caseContractConstraint(ContractConstraint object) {
                return createContractConstraintAdapter();
            }
            @Override
            public Adapter caseContractContainer(ContractContainer object) {
                return createContractContainerAdapter();
            }
            @Override
            public Adapter caseConnection(Connection object) {
                return createConnectionAdapter();
            }
            @Override
            public Adapter caseCorrelation(Correlation object) {
                return createCorrelationAdapter();
            }
            @Override
            public Adapter caseCorrelationAssociation(CorrelationAssociation object) {
                return createCorrelationAssociationAdapter();
            }
            @Override
            public Adapter caseData(Data object) {
                return createDataAdapter();
            }
            @Override
            public Adapter caseDataAware(DataAware object) {
                return createDataAwareAdapter();
            }
            @Override
            public Adapter caseDateType(DateType object) {
                return createDateTypeAdapter();
            }
            @Override
            public Adapter caseDataType(DataType object) {
                return createDataTypeAdapter();
            }
            @Override
            public Adapter caseDocument(Document object) {
                return createDocumentAdapter();
            }
            @Override
            public Adapter caseDoubleType(DoubleType object) {
                return createDoubleTypeAdapter();
            }
            @Override
            public Adapter caseElement(Element object) {
                return createElementAdapter();
            }
            @Override
            public Adapter caseEvent(Event object) {
                return createEventAdapter();
            }
            @Override
            public Adapter caseEndErrorEvent(EndErrorEvent object) {
                return createEndErrorEventAdapter();
            }
            @Override
            public Adapter caseEndEvent(EndEvent object) {
                return createEndEventAdapter();
            }
            @Override
            public Adapter caseEndMessageEvent(EndMessageEvent object) {
                return createEndMessageEventAdapter();
            }
            @Override
            public Adapter caseEndSignalEvent(EndSignalEvent object) {
                return createEndSignalEventAdapter();
            }
            @Override
            public Adapter caseEndTerminatedEvent(EndTerminatedEvent object) {
                return createEndTerminatedEventAdapter();
            }
            @Override
            public Adapter caseErrorEvent(ErrorEvent object) {
                return createErrorEventAdapter();
            }
            @Override
            public Adapter caseEnumType(EnumType object) {
                return createEnumTypeAdapter();
            }
            @Override
            public Adapter caseFloatType(FloatType object) {
                return createFloatTypeAdapter();
            }
            @Override
            public Adapter caseFlowElement(FlowElement object) {
                return createFlowElementAdapter();
            }
            @Override
            public Adapter caseFormMapping(FormMapping object) {
                return createFormMappingAdapter();
            }
            @Override
            public Adapter caseGateway(Gateway object) {
                return createGatewayAdapter();
            }
            @Override
            public Adapter caseInclusiveGateway(InclusiveGateway object) {
                return createInclusiveGatewayAdapter();
            }
            @Override
            public Adapter caseInputMapping(InputMapping object) {
                return createInputMappingAdapter();
            }
            @Override
            public Adapter caseIntegerType(IntegerType object) {
                return createIntegerTypeAdapter();
            }
            @Override
            public Adapter caseIntermediateErrorCatchEvent(IntermediateErrorCatchEvent object) {
                return createIntermediateErrorCatchEventAdapter();
            }
            @Override
            public Adapter caseIntermediateCatchSignalEvent(IntermediateCatchSignalEvent object) {
                return createIntermediateCatchSignalEventAdapter();
            }
            @Override
            public Adapter caseIntermediateThrowSignalEvent(IntermediateThrowSignalEvent object) {
                return createIntermediateThrowSignalEventAdapter();
            }
            @Override
            public Adapter caseIntermediateCatchMessageEvent(IntermediateCatchMessageEvent object) {
                return createIntermediateCatchMessageEventAdapter();
            }
            @Override
            public Adapter caseIntermediateThrowMessageEvent(IntermediateThrowMessageEvent object) {
                return createIntermediateThrowMessageEventAdapter();
            }
            @Override
            public Adapter caseIntermediateCatchTimerEvent(IntermediateCatchTimerEvent object) {
                return createIntermediateCatchTimerEventAdapter();
            }
            @Override
            public Adapter caseJavaObjectData(JavaObjectData object) {
                return createJavaObjectDataAdapter();
            }
            @Override
            public Adapter caseJavaType(JavaType object) {
                return createJavaTypeAdapter();
            }
            @Override
            public Adapter caseLane(Lane object) {
                return createLaneAdapter();
            }
            @Override
            public Adapter caseLinkEvent(LinkEvent object) {
                return createLinkEventAdapter();
            }
            @Override
            public Adapter caseLongType(LongType object) {
                return createLongTypeAdapter();
            }
            @Override
            public Adapter caseMainProcess(MainProcess object) {
                return createMainProcessAdapter();
            }
            @Override
            public Adapter caseMessage(Message object) {
                return createMessageAdapter();
            }
            @Override
            public Adapter caseMessageFlow(MessageFlow object) {
                return createMessageFlowAdapter();
            }
            @Override
            public Adapter caseMessageEvent(MessageEvent object) {
                return createMessageEventAdapter();
            }
            @Override
            public Adapter caseMultiInstantiable(MultiInstantiable object) {
                return createMultiInstantiableAdapter();
            }
            @Override
            public Adapter caseNonInterruptingBoundaryTimerEvent(NonInterruptingBoundaryTimerEvent object) {
                return createNonInterruptingBoundaryTimerEventAdapter();
            }
            @Override
            public Adapter caseOperationContainer(OperationContainer object) {
                return createOperationContainerAdapter();
            }
            @Override
            public Adapter caseOutputMapping(OutputMapping object) {
                return createOutputMappingAdapter();
            }
            @Override
            public Adapter casePageFlow(PageFlow object) {
                return createPageFlowAdapter();
            }
            @Override
            public Adapter casePool(Pool object) {
                return createPoolAdapter();
            }
            @Override
            public Adapter caseRecapFlow(RecapFlow object) {
                return createRecapFlowAdapter();
            }
            @Override
            public Adapter caseReceiveTask(ReceiveTask object) {
                return createReceiveTaskAdapter();
            }
            @Override
            public Adapter caseSequenceFlow(SequenceFlow object) {
                return createSequenceFlowAdapter();
            }
            @Override
            public Adapter caseSignalEvent(SignalEvent object) {
                return createSignalEventAdapter();
            }
            @Override
            public Adapter caseSourceElement(SourceElement object) {
                return createSourceElementAdapter();
            }
            @Override
            public Adapter caseStringType(StringType object) {
                return createStringTypeAdapter();
            }
            @Override
            public Adapter caseScriptTask(ScriptTask object) {
                return createScriptTaskAdapter();
            }
            @Override
            public Adapter caseSearchIndex(SearchIndex object) {
                return createSearchIndexAdapter();
            }
            @Override
            public Adapter caseSendTask(SendTask object) {
                return createSendTaskAdapter();
            }
            @Override
            public Adapter caseServiceTask(ServiceTask object) {
                return createServiceTaskAdapter();
            }
            @Override
            public Adapter caseStartErrorEvent(StartErrorEvent object) {
                return createStartErrorEventAdapter();
            }
            @Override
            public Adapter caseStartEvent(StartEvent object) {
                return createStartEventAdapter();
            }
            @Override
            public Adapter caseStartMessageEvent(StartMessageEvent object) {
                return createStartMessageEventAdapter();
            }
            @Override
            public Adapter caseStartSignalEvent(StartSignalEvent object) {
                return createStartSignalEventAdapter();
            }
            @Override
            public Adapter caseStartTimerEvent(StartTimerEvent object) {
                return createStartTimerEventAdapter();
            }
            @Override
            public Adapter caseSubProcessEvent(SubProcessEvent object) {
                return createSubProcessEventAdapter();
            }
            @Override
            public Adapter caseTask(Task object) {
                return createTaskAdapter();
            }
            @Override
            public Adapter caseTargetElement(TargetElement object) {
                return createTargetElementAdapter();
            }
            @Override
            public Adapter caseTextAnnotation(TextAnnotation object) {
                return createTextAnnotationAdapter();
            }
            @Override
            public Adapter caseTextAnnotationAttachment(TextAnnotationAttachment object) {
                return createTextAnnotationAttachmentAdapter();
            }
            @Override
            public Adapter caseThrowSignalEvent(ThrowSignalEvent object) {
                return createThrowSignalEventAdapter();
            }
            @Override
            public Adapter caseThrowLinkEvent(ThrowLinkEvent object) {
                return createThrowLinkEventAdapter();
            }
            @Override
            public Adapter caseThrowMessageEvent(ThrowMessageEvent object) {
                return createThrowMessageEventAdapter();
            }
            @Override
            public Adapter caseTimerEvent(TimerEvent object) {
                return createTimerEventAdapter();
            }
            @Override
            public Adapter caseXMLData(XMLData object) {
                return createXMLDataAdapter();
            }
            @Override
            public Adapter caseXMLType(XMLType object) {
                return createXMLTypeAdapter();
            }
            @Override
            public Adapter caseXORGateway(XORGateway object) {
                return createXORGatewayAdapter();
            }
            @Override
            public Adapter defaultCase(EObject object) {
                return createEObjectAdapter();
            }
        };

	/**
     * Creates an adapter for the <code>target</code>.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param target the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
	@Override
	public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject)target);
    }


	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.AbstractCatchMessageEvent <em>Abstract Catch Message Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.AbstractCatchMessageEvent
     * @generated
     */
	public Adapter createAbstractCatchMessageEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.AbstractPageFlow <em>Abstract Page Flow</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.AbstractPageFlow
     * @generated
     */
	public Adapter createAbstractPageFlowAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.AbstractProcess <em>Abstract Process</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.AbstractProcess
     * @generated
     */
	public Adapter createAbstractProcessAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.AbstractTimerEvent <em>Abstract Timer Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.AbstractTimerEvent
     * @generated
     */
	public Adapter createAbstractTimerEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.Activity <em>Activity</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.Activity
     * @generated
     */
	public Adapter createActivityAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.Actor <em>Actor</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.Actor
     * @generated
     */
	public Adapter createActorAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.ActorFilter <em>Actor Filter</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.ActorFilter
     * @generated
     */
	public Adapter createActorFilterAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.ANDGateway <em>AND Gateway</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.ANDGateway
     * @generated
     */
	public Adapter createANDGatewayAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.Assignable <em>Assignable</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.Assignable
     * @generated
     */
	public Adapter createAssignableAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.Association <em>Association</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.Association
     * @generated
     */
	public Adapter createAssociationAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.BooleanType <em>Boolean Type</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.BooleanType
     * @generated
     */
	public Adapter createBooleanTypeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.BoundaryEvent <em>Boundary Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.BoundaryEvent
     * @generated
     */
	public Adapter createBoundaryEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.BoundaryMessageEvent <em>Boundary Message Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.BoundaryMessageEvent
     * @generated
     */
	public Adapter createBoundaryMessageEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.BoundarySignalEvent <em>Boundary Signal Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.BoundarySignalEvent
     * @generated
     */
	public Adapter createBoundarySignalEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.BoundaryTimerEvent <em>Boundary Timer Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.BoundaryTimerEvent
     * @generated
     */
	public Adapter createBoundaryTimerEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.BusinessObjectData <em>Business Object Data</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.BusinessObjectData
     * @generated
     */
	public Adapter createBusinessObjectDataAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.BusinessObjectType <em>Business Object Type</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.BusinessObjectType
     * @generated
     */
	public Adapter createBusinessObjectTypeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.CallActivity <em>Call Activity</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.CallActivity
     * @generated
     */
	public Adapter createCallActivityAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.CatchLinkEvent <em>Catch Link Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.CatchLinkEvent
     * @generated
     */
	public Adapter createCatchLinkEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.CatchMessageEvent <em>Catch Message Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.CatchMessageEvent
     * @generated
     */
	public Adapter createCatchMessageEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.CatchSignalEvent <em>Catch Signal Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.CatchSignalEvent
     * @generated
     */
	public Adapter createCatchSignalEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.ConnectableElement <em>Connectable Element</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.ConnectableElement
     * @generated
     */
	public Adapter createConnectableElementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.Connector <em>Connector</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.Connector
     * @generated
     */
	public Adapter createConnectorAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.Container <em>Container</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.Container
     * @generated
     */
	public Adapter createContainerAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.Contract <em>Contract</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.Contract
     * @generated
     */
	public Adapter createContractAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.ContractInputMapping <em>Contract Input Mapping</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.ContractInputMapping
     * @generated
     */
	public Adapter createContractInputMappingAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.ContractInput <em>Contract Input</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.ContractInput
     * @generated
     */
	public Adapter createContractInputAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.ContractConstraint <em>Contract Constraint</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.ContractConstraint
     * @generated
     */
	public Adapter createContractConstraintAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.ContractContainer <em>Contract Container</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.ContractContainer
     * @generated
     */
	public Adapter createContractContainerAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.Connection <em>Connection</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.Connection
     * @generated
     */
	public Adapter createConnectionAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.Correlation <em>Correlation</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.Correlation
     * @generated
     */
	public Adapter createCorrelationAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.CorrelationAssociation <em>Correlation Association</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.CorrelationAssociation
     * @generated
     */
	public Adapter createCorrelationAssociationAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.Data <em>Data</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.Data
     * @generated
     */
	public Adapter createDataAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.DataAware <em>Data Aware</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.DataAware
     * @generated
     */
	public Adapter createDataAwareAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.DateType <em>Date Type</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.DateType
     * @generated
     */
	public Adapter createDateTypeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.DataType <em>Data Type</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.DataType
     * @generated
     */
	public Adapter createDataTypeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.Document <em>Document</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.Document
     * @generated
     */
	public Adapter createDocumentAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.DoubleType <em>Double Type</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.DoubleType
     * @generated
     */
	public Adapter createDoubleTypeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.Element <em>Element</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.Element
     * @generated
     */
	public Adapter createElementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.Event <em>Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.Event
     * @generated
     */
	public Adapter createEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.EndErrorEvent <em>End Error Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.EndErrorEvent
     * @generated
     */
	public Adapter createEndErrorEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.EndEvent <em>End Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.EndEvent
     * @generated
     */
	public Adapter createEndEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.EndMessageEvent <em>End Message Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.EndMessageEvent
     * @generated
     */
	public Adapter createEndMessageEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.EndSignalEvent <em>End Signal Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.EndSignalEvent
     * @generated
     */
	public Adapter createEndSignalEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.EndTerminatedEvent <em>End Terminated Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.EndTerminatedEvent
     * @generated
     */
	public Adapter createEndTerminatedEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.ErrorEvent <em>Error Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.ErrorEvent
     * @generated
     */
	public Adapter createErrorEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.EnumType <em>Enum Type</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.EnumType
     * @generated
     */
	public Adapter createEnumTypeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.FloatType <em>Float Type</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.FloatType
     * @generated
     */
	public Adapter createFloatTypeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.FlowElement <em>Flow Element</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.FlowElement
     * @generated
     */
	public Adapter createFlowElementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.FormMapping <em>Form Mapping</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.FormMapping
     * @generated
     */
	public Adapter createFormMappingAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.Gateway <em>Gateway</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.Gateway
     * @generated
     */
	public Adapter createGatewayAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.InclusiveGateway <em>Inclusive Gateway</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.InclusiveGateway
     * @generated
     */
	public Adapter createInclusiveGatewayAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.InputMapping <em>Input Mapping</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.InputMapping
     * @generated
     */
	public Adapter createInputMappingAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.IntegerType <em>Integer Type</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.IntegerType
     * @generated
     */
	public Adapter createIntegerTypeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.IntermediateErrorCatchEvent <em>Intermediate Error Catch Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.IntermediateErrorCatchEvent
     * @generated
     */
	public Adapter createIntermediateErrorCatchEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.IntermediateCatchSignalEvent <em>Intermediate Catch Signal Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.IntermediateCatchSignalEvent
     * @generated
     */
	public Adapter createIntermediateCatchSignalEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.IntermediateThrowSignalEvent <em>Intermediate Throw Signal Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.IntermediateThrowSignalEvent
     * @generated
     */
	public Adapter createIntermediateThrowSignalEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.IntermediateCatchMessageEvent <em>Intermediate Catch Message Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.IntermediateCatchMessageEvent
     * @generated
     */
	public Adapter createIntermediateCatchMessageEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.IntermediateThrowMessageEvent <em>Intermediate Throw Message Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.IntermediateThrowMessageEvent
     * @generated
     */
	public Adapter createIntermediateThrowMessageEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.IntermediateCatchTimerEvent <em>Intermediate Catch Timer Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.IntermediateCatchTimerEvent
     * @generated
     */
	public Adapter createIntermediateCatchTimerEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.JavaObjectData <em>Java Object Data</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.JavaObjectData
     * @generated
     */
	public Adapter createJavaObjectDataAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.JavaType <em>Java Type</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.JavaType
     * @generated
     */
	public Adapter createJavaTypeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.Lane <em>Lane</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.Lane
     * @generated
     */
	public Adapter createLaneAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.LinkEvent <em>Link Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.LinkEvent
     * @generated
     */
	public Adapter createLinkEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.LongType <em>Long Type</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.LongType
     * @generated
     */
	public Adapter createLongTypeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.MainProcess <em>Main Process</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.MainProcess
     * @generated
     */
	public Adapter createMainProcessAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.Message <em>Message</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.Message
     * @generated
     */
	public Adapter createMessageAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.MessageFlow <em>Message Flow</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.MessageFlow
     * @generated
     */
	public Adapter createMessageFlowAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.MessageEvent <em>Message Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.MessageEvent
     * @generated
     */
	public Adapter createMessageEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.MultiInstantiable <em>Multi Instantiable</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.MultiInstantiable
     * @generated
     */
	public Adapter createMultiInstantiableAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.NonInterruptingBoundaryTimerEvent <em>Non Interrupting Boundary Timer Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.NonInterruptingBoundaryTimerEvent
     * @generated
     */
	public Adapter createNonInterruptingBoundaryTimerEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.OperationContainer <em>Operation Container</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.OperationContainer
     * @generated
     */
	public Adapter createOperationContainerAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.OutputMapping <em>Output Mapping</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.OutputMapping
     * @generated
     */
	public Adapter createOutputMappingAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.PageFlow <em>Page Flow</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.PageFlow
     * @generated
     */
	public Adapter createPageFlowAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.Pool <em>Pool</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.Pool
     * @generated
     */
	public Adapter createPoolAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.RecapFlow <em>Recap Flow</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.RecapFlow
     * @generated
     */
	public Adapter createRecapFlowAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.ReceiveTask <em>Receive Task</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.ReceiveTask
     * @generated
     */
	public Adapter createReceiveTaskAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.SequenceFlow <em>Sequence Flow</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.SequenceFlow
     * @generated
     */
	public Adapter createSequenceFlowAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.SignalEvent <em>Signal Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.SignalEvent
     * @generated
     */
	public Adapter createSignalEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.SourceElement <em>Source Element</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.SourceElement
     * @generated
     */
	public Adapter createSourceElementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.StringType <em>String Type</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.StringType
     * @generated
     */
	public Adapter createStringTypeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.ScriptTask <em>Script Task</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.ScriptTask
     * @generated
     */
	public Adapter createScriptTaskAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.SearchIndex <em>Search Index</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.SearchIndex
     * @generated
     */
	public Adapter createSearchIndexAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.SendTask <em>Send Task</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.SendTask
     * @generated
     */
	public Adapter createSendTaskAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.ServiceTask <em>Service Task</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.ServiceTask
     * @generated
     */
	public Adapter createServiceTaskAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.StartErrorEvent <em>Start Error Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.StartErrorEvent
     * @generated
     */
	public Adapter createStartErrorEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.StartEvent <em>Start Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.StartEvent
     * @generated
     */
	public Adapter createStartEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.StartMessageEvent <em>Start Message Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.StartMessageEvent
     * @generated
     */
	public Adapter createStartMessageEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.StartSignalEvent <em>Start Signal Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.StartSignalEvent
     * @generated
     */
	public Adapter createStartSignalEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.StartTimerEvent <em>Start Timer Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.StartTimerEvent
     * @generated
     */
	public Adapter createStartTimerEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.SubProcessEvent <em>Sub Process Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.SubProcessEvent
     * @generated
     */
	public Adapter createSubProcessEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.Task <em>Task</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.Task
     * @generated
     */
	public Adapter createTaskAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.TargetElement <em>Target Element</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.TargetElement
     * @generated
     */
	public Adapter createTargetElementAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.TextAnnotation <em>Text Annotation</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.TextAnnotation
     * @generated
     */
	public Adapter createTextAnnotationAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.TextAnnotationAttachment <em>Text Annotation Attachment</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.TextAnnotationAttachment
     * @generated
     */
	public Adapter createTextAnnotationAttachmentAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.ThrowSignalEvent <em>Throw Signal Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.ThrowSignalEvent
     * @generated
     */
	public Adapter createThrowSignalEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.ThrowLinkEvent <em>Throw Link Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.ThrowLinkEvent
     * @generated
     */
	public Adapter createThrowLinkEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.ThrowMessageEvent <em>Throw Message Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.ThrowMessageEvent
     * @generated
     */
	public Adapter createThrowMessageEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.TimerEvent <em>Timer Event</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.TimerEvent
     * @generated
     */
	public Adapter createTimerEventAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.XMLData <em>XML Data</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.XMLData
     * @generated
     */
	public Adapter createXMLDataAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.XMLType <em>XML Type</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.XMLType
     * @generated
     */
	public Adapter createXMLTypeAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.model.process.XORGateway <em>XOR Gateway</em>}'.
     * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @see org.bonitasoft.studio.model.process.XORGateway
     * @generated
     */
	public Adapter createXORGatewayAdapter() {
        return null;
    }

	/**
     * Creates a new adapter for the default case.
     * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
     * @return the new adapter.
     * @generated
     */
	public Adapter createEObjectAdapter() {
        return null;
    }

} //ProcessAdapterFactory
