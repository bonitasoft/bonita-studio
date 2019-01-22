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
package org.bonitasoft.studio.model.process.impl;

import org.bonitasoft.studio.model.process.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ProcessFactoryImpl extends EFactoryImpl implements ProcessFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ProcessFactory init() {
		try {
			ProcessFactory theProcessFactory = (ProcessFactory)EPackage.Registry.INSTANCE.getEFactory(ProcessPackage.eNS_URI);
			if (theProcessFactory != null) {
				return theProcessFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ProcessFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ProcessPackage.ACTIVITY: return createActivity();
			case ProcessPackage.ACTOR: return createActor();
			case ProcessPackage.ACTOR_FILTER: return createActorFilter();
			case ProcessPackage.AND_GATEWAY: return createANDGateway();
			case ProcessPackage.ASSOCIATION: return createAssociation();
			case ProcessPackage.BOOLEAN_TYPE: return createBooleanType();
			case ProcessPackage.BOUNDARY_EVENT: return createBoundaryEvent();
			case ProcessPackage.BOUNDARY_MESSAGE_EVENT: return createBoundaryMessageEvent();
			case ProcessPackage.BOUNDARY_SIGNAL_EVENT: return createBoundarySignalEvent();
			case ProcessPackage.BOUNDARY_TIMER_EVENT: return createBoundaryTimerEvent();
			case ProcessPackage.BUSINESS_OBJECT_DATA: return createBusinessObjectData();
			case ProcessPackage.BUSINESS_OBJECT_TYPE: return createBusinessObjectType();
			case ProcessPackage.CALL_ACTIVITY: return createCallActivity();
			case ProcessPackage.CATCH_LINK_EVENT: return createCatchLinkEvent();
			case ProcessPackage.CONNECTABLE_ELEMENT: return createConnectableElement();
			case ProcessPackage.CONNECTOR: return createConnector();
			case ProcessPackage.CONTAINER: return createContainer();
			case ProcessPackage.CONTRACT: return createContract();
			case ProcessPackage.CONTRACT_INPUT_MAPPING: return createContractInputMapping();
			case ProcessPackage.CONTRACT_INPUT: return createContractInput();
			case ProcessPackage.CONTRACT_CONSTRAINT: return createContractConstraint();
			case ProcessPackage.CONNECTION: return createConnection();
			case ProcessPackage.CORRELATION: return createCorrelation();
			case ProcessPackage.CORRELATION_ASSOCIATION: return createCorrelationAssociation();
			case ProcessPackage.DATA: return createData();
			case ProcessPackage.DATA_AWARE: return createDataAware();
			case ProcessPackage.DATE_TYPE: return createDateType();
			case ProcessPackage.DOCUMENT: return createDocument();
			case ProcessPackage.DOUBLE_TYPE: return createDoubleType();
			case ProcessPackage.EVENT: return createEvent();
			case ProcessPackage.END_ERROR_EVENT: return createEndErrorEvent();
			case ProcessPackage.END_EVENT: return createEndEvent();
			case ProcessPackage.END_MESSAGE_EVENT: return createEndMessageEvent();
			case ProcessPackage.END_SIGNAL_EVENT: return createEndSignalEvent();
			case ProcessPackage.END_TERMINATED_EVENT: return createEndTerminatedEvent();
			case ProcessPackage.ENUM_TYPE: return createEnumType();
			case ProcessPackage.FLOAT_TYPE: return createFloatType();
			case ProcessPackage.FLOW_ELEMENT: return createFlowElement();
			case ProcessPackage.FORM_MAPPING: return createFormMapping();
			case ProcessPackage.GATEWAY: return createGateway();
			case ProcessPackage.INCLUSIVE_GATEWAY: return createInclusiveGateway();
			case ProcessPackage.INPUT_MAPPING: return createInputMapping();
			case ProcessPackage.INTEGER_TYPE: return createIntegerType();
			case ProcessPackage.INTERMEDIATE_ERROR_CATCH_EVENT: return createIntermediateErrorCatchEvent();
			case ProcessPackage.INTERMEDIATE_CATCH_SIGNAL_EVENT: return createIntermediateCatchSignalEvent();
			case ProcessPackage.INTERMEDIATE_THROW_SIGNAL_EVENT: return createIntermediateThrowSignalEvent();
			case ProcessPackage.INTERMEDIATE_CATCH_MESSAGE_EVENT: return createIntermediateCatchMessageEvent();
			case ProcessPackage.INTERMEDIATE_THROW_MESSAGE_EVENT: return createIntermediateThrowMessageEvent();
			case ProcessPackage.INTERMEDIATE_CATCH_TIMER_EVENT: return createIntermediateCatchTimerEvent();
			case ProcessPackage.JAVA_OBJECT_DATA: return createJavaObjectData();
			case ProcessPackage.JAVA_TYPE: return createJavaType();
			case ProcessPackage.LANE: return createLane();
			case ProcessPackage.LINK_EVENT: return createLinkEvent();
			case ProcessPackage.LONG_TYPE: return createLongType();
			case ProcessPackage.MAIN_PROCESS: return createMainProcess();
			case ProcessPackage.MESSAGE: return createMessage();
			case ProcessPackage.MESSAGE_FLOW: return createMessageFlow();
			case ProcessPackage.MESSAGE_EVENT: return createMessageEvent();
			case ProcessPackage.NON_INTERRUPTING_BOUNDARY_TIMER_EVENT: return createNonInterruptingBoundaryTimerEvent();
			case ProcessPackage.OPERATION_CONTAINER: return createOperationContainer();
			case ProcessPackage.OUTPUT_MAPPING: return createOutputMapping();
			case ProcessPackage.PAGE_FLOW: return createPageFlow();
			case ProcessPackage.POOL: return createPool();
			case ProcessPackage.RECEIVE_TASK: return createReceiveTask();
			case ProcessPackage.SEQUENCE_FLOW: return createSequenceFlow();
			case ProcessPackage.STRING_TYPE: return createStringType();
			case ProcessPackage.SCRIPT_TASK: return createScriptTask();
			case ProcessPackage.SEARCH_INDEX: return createSearchIndex();
			case ProcessPackage.SEND_TASK: return createSendTask();
			case ProcessPackage.SERVICE_TASK: return createServiceTask();
			case ProcessPackage.START_ERROR_EVENT: return createStartErrorEvent();
			case ProcessPackage.START_EVENT: return createStartEvent();
			case ProcessPackage.START_MESSAGE_EVENT: return createStartMessageEvent();
			case ProcessPackage.START_SIGNAL_EVENT: return createStartSignalEvent();
			case ProcessPackage.START_TIMER_EVENT: return createStartTimerEvent();
			case ProcessPackage.SUB_PROCESS_EVENT: return createSubProcessEvent();
			case ProcessPackage.TASK: return createTask();
			case ProcessPackage.TEXT_ANNOTATION: return createTextAnnotation();
			case ProcessPackage.TEXT_ANNOTATION_ATTACHMENT: return createTextAnnotationAttachment();
			case ProcessPackage.THROW_LINK_EVENT: return createThrowLinkEvent();
			case ProcessPackage.THROW_MESSAGE_EVENT: return createThrowMessageEvent();
			case ProcessPackage.TIMER_EVENT: return createTimerEvent();
			case ProcessPackage.XML_DATA: return createXMLData();
			case ProcessPackage.XML_TYPE: return createXMLType();
			case ProcessPackage.XOR_GATEWAY: return createXORGateway();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ProcessPackage.CORRELATION_TYPE_ACTIVE:
				return createCorrelationTypeActiveFromString(eDataType, initialValue);
			case ProcessPackage.CONTRACT_INPUT_TYPE:
				return createContractInputTypeFromString(eDataType, initialValue);
			case ProcessPackage.DOCUMENT_TYPE:
				return createDocumentTypeFromString(eDataType, initialValue);
			case ProcessPackage.FORM_MAPPING_TYPE:
				return createFormMappingTypeFromString(eDataType, initialValue);
			case ProcessPackage.GATEWAY_TYPE:
				return createGatewayTypeFromString(eDataType, initialValue);
			case ProcessPackage.INPUT_MAPPING_ASSIGNATION_TYPE:
				return createInputMappingAssignationTypeFromString(eDataType, initialValue);
			case ProcessPackage.MULTI_INSTANCE_TYPE:
				return createMultiInstanceTypeFromString(eDataType, initialValue);
			case ProcessPackage.SEQUENCE_FLOW_CONDITION_TYPE:
				return createSequenceFlowConditionTypeFromString(eDataType, initialValue);
			case ProcessPackage.START_TIMER_SCRIPT_TYPE:
				return createStartTimerScriptTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ProcessPackage.CORRELATION_TYPE_ACTIVE:
				return convertCorrelationTypeActiveToString(eDataType, instanceValue);
			case ProcessPackage.CONTRACT_INPUT_TYPE:
				return convertContractInputTypeToString(eDataType, instanceValue);
			case ProcessPackage.DOCUMENT_TYPE:
				return convertDocumentTypeToString(eDataType, instanceValue);
			case ProcessPackage.FORM_MAPPING_TYPE:
				return convertFormMappingTypeToString(eDataType, instanceValue);
			case ProcessPackage.GATEWAY_TYPE:
				return convertGatewayTypeToString(eDataType, instanceValue);
			case ProcessPackage.INPUT_MAPPING_ASSIGNATION_TYPE:
				return convertInputMappingAssignationTypeToString(eDataType, instanceValue);
			case ProcessPackage.MULTI_INSTANCE_TYPE:
				return convertMultiInstanceTypeToString(eDataType, instanceValue);
			case ProcessPackage.SEQUENCE_FLOW_CONDITION_TYPE:
				return convertSequenceFlowConditionTypeToString(eDataType, instanceValue);
			case ProcessPackage.START_TIMER_SCRIPT_TYPE:
				return convertStartTimerScriptTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Activity createActivity() {
		ActivityImpl activity = new ActivityImpl();
		return activity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Actor createActor() {
		ActorImpl actor = new ActorImpl();
		return actor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ActorFilter createActorFilter() {
		ActorFilterImpl actorFilter = new ActorFilterImpl();
		return actorFilter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ANDGateway createANDGateway() {
		ANDGatewayImpl andGateway = new ANDGatewayImpl();
		return andGateway;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Association createAssociation() {
		AssociationImpl association = new AssociationImpl();
		return association;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BooleanType createBooleanType() {
		BooleanTypeImpl booleanType = new BooleanTypeImpl();
		return booleanType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BoundaryEvent createBoundaryEvent() {
		BoundaryEventImpl boundaryEvent = new BoundaryEventImpl();
		return boundaryEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BoundaryMessageEvent createBoundaryMessageEvent() {
		BoundaryMessageEventImpl boundaryMessageEvent = new BoundaryMessageEventImpl();
		return boundaryMessageEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BoundarySignalEvent createBoundarySignalEvent() {
		BoundarySignalEventImpl boundarySignalEvent = new BoundarySignalEventImpl();
		return boundarySignalEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BoundaryTimerEvent createBoundaryTimerEvent() {
		BoundaryTimerEventImpl boundaryTimerEvent = new BoundaryTimerEventImpl();
		return boundaryTimerEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BusinessObjectData createBusinessObjectData() {
		BusinessObjectDataImpl businessObjectData = new BusinessObjectDataImpl();
		return businessObjectData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BusinessObjectType createBusinessObjectType() {
		BusinessObjectTypeImpl businessObjectType = new BusinessObjectTypeImpl();
		return businessObjectType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CallActivity createCallActivity() {
		CallActivityImpl callActivity = new CallActivityImpl();
		return callActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CatchLinkEvent createCatchLinkEvent() {
		CatchLinkEventImpl catchLinkEvent = new CatchLinkEventImpl();
		return catchLinkEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ConnectableElement createConnectableElement() {
		ConnectableElementImpl connectableElement = new ConnectableElementImpl();
		return connectableElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Connector createConnector() {
		ConnectorImpl connector = new ConnectorImpl();
		return connector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.bonitasoft.studio.model.process.Container createContainer() {
		ContainerImpl container = new ContainerImpl();
		return container;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Contract createContract() {
		ContractImpl contract = new ContractImpl();
		return contract;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContractInputMapping createContractInputMapping() {
		ContractInputMappingImpl contractInputMapping = new ContractInputMappingImpl();
		return contractInputMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContractInput createContractInput() {
		ContractInputImpl contractInput = new ContractInputImpl();
		return contractInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContractConstraint createContractConstraint() {
		ContractConstraintImpl contractConstraint = new ContractConstraintImpl();
		return contractConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Connection createConnection() {
		ConnectionImpl connection = new ConnectionImpl();
		return connection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Correlation createCorrelation() {
		CorrelationImpl correlation = new CorrelationImpl();
		return correlation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CorrelationAssociation createCorrelationAssociation() {
		CorrelationAssociationImpl correlationAssociation = new CorrelationAssociationImpl();
		return correlationAssociation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Data createData() {
		DataImpl data = new DataImpl();
		return data;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DataAware createDataAware() {
		DataAwareImpl dataAware = new DataAwareImpl();
		return dataAware;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DateType createDateType() {
		DateTypeImpl dateType = new DateTypeImpl();
		return dateType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Document createDocument() {
		DocumentImpl document = new DocumentImpl();
		return document;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DoubleType createDoubleType() {
		DoubleTypeImpl doubleType = new DoubleTypeImpl();
		return doubleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Event createEvent() {
		EventImpl event = new EventImpl();
		return event;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EndErrorEvent createEndErrorEvent() {
		EndErrorEventImpl endErrorEvent = new EndErrorEventImpl();
		return endErrorEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EndEvent createEndEvent() {
		EndEventImpl endEvent = new EndEventImpl();
		return endEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EndMessageEvent createEndMessageEvent() {
		EndMessageEventImpl endMessageEvent = new EndMessageEventImpl();
		return endMessageEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EndSignalEvent createEndSignalEvent() {
		EndSignalEventImpl endSignalEvent = new EndSignalEventImpl();
		return endSignalEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EndTerminatedEvent createEndTerminatedEvent() {
		EndTerminatedEventImpl endTerminatedEvent = new EndTerminatedEventImpl();
		return endTerminatedEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EnumType createEnumType() {
		EnumTypeImpl enumType = new EnumTypeImpl();
		return enumType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FloatType createFloatType() {
		FloatTypeImpl floatType = new FloatTypeImpl();
		return floatType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FlowElement createFlowElement() {
		FlowElementImpl flowElement = new FlowElementImpl();
		return flowElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FormMapping createFormMapping() {
		FormMappingImpl formMapping = new FormMappingImpl();
		return formMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Gateway createGateway() {
		GatewayImpl gateway = new GatewayImpl();
		return gateway;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InclusiveGateway createInclusiveGateway() {
		InclusiveGatewayImpl inclusiveGateway = new InclusiveGatewayImpl();
		return inclusiveGateway;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InputMapping createInputMapping() {
		InputMappingImpl inputMapping = new InputMappingImpl();
		return inputMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IntegerType createIntegerType() {
		IntegerTypeImpl integerType = new IntegerTypeImpl();
		return integerType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IntermediateErrorCatchEvent createIntermediateErrorCatchEvent() {
		IntermediateErrorCatchEventImpl intermediateErrorCatchEvent = new IntermediateErrorCatchEventImpl();
		return intermediateErrorCatchEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IntermediateCatchSignalEvent createIntermediateCatchSignalEvent() {
		IntermediateCatchSignalEventImpl intermediateCatchSignalEvent = new IntermediateCatchSignalEventImpl();
		return intermediateCatchSignalEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IntermediateThrowSignalEvent createIntermediateThrowSignalEvent() {
		IntermediateThrowSignalEventImpl intermediateThrowSignalEvent = new IntermediateThrowSignalEventImpl();
		return intermediateThrowSignalEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IntermediateCatchMessageEvent createIntermediateCatchMessageEvent() {
		IntermediateCatchMessageEventImpl intermediateCatchMessageEvent = new IntermediateCatchMessageEventImpl();
		return intermediateCatchMessageEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IntermediateThrowMessageEvent createIntermediateThrowMessageEvent() {
		IntermediateThrowMessageEventImpl intermediateThrowMessageEvent = new IntermediateThrowMessageEventImpl();
		return intermediateThrowMessageEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IntermediateCatchTimerEvent createIntermediateCatchTimerEvent() {
		IntermediateCatchTimerEventImpl intermediateCatchTimerEvent = new IntermediateCatchTimerEventImpl();
		return intermediateCatchTimerEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public JavaObjectData createJavaObjectData() {
		JavaObjectDataImpl javaObjectData = new JavaObjectDataImpl();
		return javaObjectData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public JavaType createJavaType() {
		JavaTypeImpl javaType = new JavaTypeImpl();
		return javaType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Lane createLane() {
		LaneImpl lane = new LaneImpl();
		return lane;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LinkEvent createLinkEvent() {
		LinkEventImpl linkEvent = new LinkEventImpl();
		return linkEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LongType createLongType() {
		LongTypeImpl longType = new LongTypeImpl();
		return longType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MainProcess createMainProcess() {
		MainProcessImpl mainProcess = new MainProcessImpl();
		return mainProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Message createMessage() {
		MessageImpl message = new MessageImpl();
		return message;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MessageFlow createMessageFlow() {
		MessageFlowImpl messageFlow = new MessageFlowImpl();
		return messageFlow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MessageEvent createMessageEvent() {
		MessageEventImpl messageEvent = new MessageEventImpl();
		return messageEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NonInterruptingBoundaryTimerEvent createNonInterruptingBoundaryTimerEvent() {
		NonInterruptingBoundaryTimerEventImpl nonInterruptingBoundaryTimerEvent = new NonInterruptingBoundaryTimerEventImpl();
		return nonInterruptingBoundaryTimerEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OperationContainer createOperationContainer() {
		OperationContainerImpl operationContainer = new OperationContainerImpl();
		return operationContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OutputMapping createOutputMapping() {
		OutputMappingImpl outputMapping = new OutputMappingImpl();
		return outputMapping;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PageFlow createPageFlow() {
		PageFlowImpl pageFlow = new PageFlowImpl();
		return pageFlow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Pool createPool() {
		PoolImpl pool = new PoolImpl();
		return pool;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ReceiveTask createReceiveTask() {
		ReceiveTaskImpl receiveTask = new ReceiveTaskImpl();
		return receiveTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SequenceFlow createSequenceFlow() {
		SequenceFlowImpl sequenceFlow = new SequenceFlowImpl();
		return sequenceFlow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StringType createStringType() {
		StringTypeImpl stringType = new StringTypeImpl();
		return stringType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScriptTask createScriptTask() {
		ScriptTaskImpl scriptTask = new ScriptTaskImpl();
		return scriptTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SearchIndex createSearchIndex() {
		SearchIndexImpl searchIndex = new SearchIndexImpl();
		return searchIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SendTask createSendTask() {
		SendTaskImpl sendTask = new SendTaskImpl();
		return sendTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ServiceTask createServiceTask() {
		ServiceTaskImpl serviceTask = new ServiceTaskImpl();
		return serviceTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StartErrorEvent createStartErrorEvent() {
		StartErrorEventImpl startErrorEvent = new StartErrorEventImpl();
		return startErrorEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StartEvent createStartEvent() {
		StartEventImpl startEvent = new StartEventImpl();
		return startEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StartMessageEvent createStartMessageEvent() {
		StartMessageEventImpl startMessageEvent = new StartMessageEventImpl();
		return startMessageEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StartSignalEvent createStartSignalEvent() {
		StartSignalEventImpl startSignalEvent = new StartSignalEventImpl();
		return startSignalEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StartTimerEvent createStartTimerEvent() {
		StartTimerEventImpl startTimerEvent = new StartTimerEventImpl();
		return startTimerEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SubProcessEvent createSubProcessEvent() {
		SubProcessEventImpl subProcessEvent = new SubProcessEventImpl();
		return subProcessEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Task createTask() {
		TaskImpl task = new TaskImpl();
		return task;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TextAnnotation createTextAnnotation() {
		TextAnnotationImpl textAnnotation = new TextAnnotationImpl();
		return textAnnotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TextAnnotationAttachment createTextAnnotationAttachment() {
		TextAnnotationAttachmentImpl textAnnotationAttachment = new TextAnnotationAttachmentImpl();
		return textAnnotationAttachment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ThrowLinkEvent createThrowLinkEvent() {
		ThrowLinkEventImpl throwLinkEvent = new ThrowLinkEventImpl();
		return throwLinkEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ThrowMessageEvent createThrowMessageEvent() {
		ThrowMessageEventImpl throwMessageEvent = new ThrowMessageEventImpl();
		return throwMessageEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TimerEvent createTimerEvent() {
		TimerEventImpl timerEvent = new TimerEventImpl();
		return timerEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public XMLData createXMLData() {
		XMLDataImpl xmlData = new XMLDataImpl();
		return xmlData;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public XMLType createXMLType() {
		XMLTypeImpl xmlType = new XMLTypeImpl();
		return xmlType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public XORGateway createXORGateway() {
		XORGatewayImpl xorGateway = new XORGatewayImpl();
		return xorGateway;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CorrelationTypeActive createCorrelationTypeActiveFromString(EDataType eDataType, String initialValue) {
		CorrelationTypeActive result = CorrelationTypeActive.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCorrelationTypeActiveToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContractInputType createContractInputTypeFromString(EDataType eDataType, String initialValue) {
		ContractInputType result = ContractInputType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertContractInputTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentType createDocumentTypeFromString(EDataType eDataType, String initialValue) {
		DocumentType result = DocumentType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDocumentTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormMappingType createFormMappingTypeFromString(EDataType eDataType, String initialValue) {
		FormMappingType result = FormMappingType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertFormMappingTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public GatewayType createGatewayTypeFromString(EDataType eDataType, String initialValue) {
		GatewayType result = GatewayType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertGatewayTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputMappingAssignationType createInputMappingAssignationTypeFromString(EDataType eDataType, String initialValue) {
		InputMappingAssignationType result = InputMappingAssignationType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertInputMappingAssignationTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultiInstanceType createMultiInstanceTypeFromString(EDataType eDataType, String initialValue) {
		MultiInstanceType result = MultiInstanceType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMultiInstanceTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SequenceFlowConditionType createSequenceFlowConditionTypeFromString(EDataType eDataType, String initialValue) {
		SequenceFlowConditionType result = SequenceFlowConditionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSequenceFlowConditionTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartTimerScriptType createStartTimerScriptTypeFromString(EDataType eDataType, String initialValue) {
		StartTimerScriptType result = StartTimerScriptType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertStartTimerScriptTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ProcessPackage getProcessPackage() {
		return (ProcessPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ProcessPackage getPackage() {
		return ProcessPackage.eINSTANCE;
	}

} //ProcessFactoryImpl
