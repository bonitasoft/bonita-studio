/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.util.Diagnostician;

import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.omg.spec.bpmn.model.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelFactoryImpl extends EFactoryImpl implements ModelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ModelFactory init() {
		try {
			ModelFactory theModelFactory = (ModelFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.omg.org/spec/BPMN/20100524/MODEL"); 
			if (theModelFactory != null) {
				return theModelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ModelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelFactoryImpl() {
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
			case ModelPackage.DOCUMENT_ROOT: return createDocumentRoot();
			case ModelPackage.TAD_HOC_SUB_PROCESS: return createTAdHocSubProcess();
			case ModelPackage.TASSIGNMENT: return createTAssignment();
			case ModelPackage.TASSOCIATION: return createTAssociation();
			case ModelPackage.TAUDITING: return createTAuditing();
			case ModelPackage.TBOUNDARY_EVENT: return createTBoundaryEvent();
			case ModelPackage.TBUSINESS_RULE_TASK: return createTBusinessRuleTask();
			case ModelPackage.TCALLABLE_ELEMENT: return createTCallableElement();
			case ModelPackage.TCALL_ACTIVITY: return createTCallActivity();
			case ModelPackage.TCALL_CHOREOGRAPHY: return createTCallChoreography();
			case ModelPackage.TCALL_CONVERSATION: return createTCallConversation();
			case ModelPackage.TCANCEL_EVENT_DEFINITION: return createTCancelEventDefinition();
			case ModelPackage.TCATEGORY: return createTCategory();
			case ModelPackage.TCATEGORY_VALUE: return createTCategoryValue();
			case ModelPackage.TCHOREOGRAPHY: return createTChoreography();
			case ModelPackage.TCHOREOGRAPHY_TASK: return createTChoreographyTask();
			case ModelPackage.TCOLLABORATION: return createTCollaboration();
			case ModelPackage.TCOMPENSATE_EVENT_DEFINITION: return createTCompensateEventDefinition();
			case ModelPackage.TCOMPLEX_BEHAVIOR_DEFINITION: return createTComplexBehaviorDefinition();
			case ModelPackage.TCOMPLEX_GATEWAY: return createTComplexGateway();
			case ModelPackage.TCONDITIONAL_EVENT_DEFINITION: return createTConditionalEventDefinition();
			case ModelPackage.TCONVERSATION: return createTConversation();
			case ModelPackage.TCONVERSATION_ASSOCIATION: return createTConversationAssociation();
			case ModelPackage.TCONVERSATION_LINK: return createTConversationLink();
			case ModelPackage.TCORRELATION_KEY: return createTCorrelationKey();
			case ModelPackage.TCORRELATION_PROPERTY: return createTCorrelationProperty();
			case ModelPackage.TCORRELATION_PROPERTY_BINDING: return createTCorrelationPropertyBinding();
			case ModelPackage.TCORRELATION_PROPERTY_RETRIEVAL_EXPRESSION: return createTCorrelationPropertyRetrievalExpression();
			case ModelPackage.TCORRELATION_SUBSCRIPTION: return createTCorrelationSubscription();
			case ModelPackage.TDATA_ASSOCIATION: return createTDataAssociation();
			case ModelPackage.TDATA_INPUT: return createTDataInput();
			case ModelPackage.TDATA_INPUT_ASSOCIATION: return createTDataInputAssociation();
			case ModelPackage.TDATA_OBJECT: return createTDataObject();
			case ModelPackage.TDATA_OBJECT_REFERENCE: return createTDataObjectReference();
			case ModelPackage.TDATA_OUTPUT: return createTDataOutput();
			case ModelPackage.TDATA_OUTPUT_ASSOCIATION: return createTDataOutputAssociation();
			case ModelPackage.TDATA_STATE: return createTDataState();
			case ModelPackage.TDATA_STORE: return createTDataStore();
			case ModelPackage.TDATA_STORE_REFERENCE: return createTDataStoreReference();
			case ModelPackage.TDEFINITIONS: return createTDefinitions();
			case ModelPackage.TDOCUMENTATION: return createTDocumentation();
			case ModelPackage.TEND_EVENT: return createTEndEvent();
			case ModelPackage.TEND_POINT: return createTEndPoint();
			case ModelPackage.TERROR: return createTError();
			case ModelPackage.TERROR_EVENT_DEFINITION: return createTErrorEventDefinition();
			case ModelPackage.TESCALATION: return createTEscalation();
			case ModelPackage.TESCALATION_EVENT_DEFINITION: return createTEscalationEventDefinition();
			case ModelPackage.TEVENT_BASED_GATEWAY: return createTEventBasedGateway();
			case ModelPackage.TEXCLUSIVE_GATEWAY: return createTExclusiveGateway();
			case ModelPackage.TEXPRESSION: return createTExpression();
			case ModelPackage.TEXTENSION: return createTExtension();
			case ModelPackage.TEXTENSION_ELEMENTS: return createTExtensionElements();
			case ModelPackage.TFORMAL_EXPRESSION: return createTFormalExpression();
			case ModelPackage.TGATEWAY: return createTGateway();
			case ModelPackage.TGLOBAL_BUSINESS_RULE_TASK: return createTGlobalBusinessRuleTask();
			case ModelPackage.TGLOBAL_CHOREOGRAPHY_TASK: return createTGlobalChoreographyTask();
			case ModelPackage.TGLOBAL_CONVERSATION: return createTGlobalConversation();
			case ModelPackage.TGLOBAL_MANUAL_TASK: return createTGlobalManualTask();
			case ModelPackage.TGLOBAL_SCRIPT_TASK: return createTGlobalScriptTask();
			case ModelPackage.TGLOBAL_TASK: return createTGlobalTask();
			case ModelPackage.TGLOBAL_USER_TASK: return createTGlobalUserTask();
			case ModelPackage.TGROUP: return createTGroup();
			case ModelPackage.THUMAN_PERFORMER: return createTHumanPerformer();
			case ModelPackage.TIMPLICIT_THROW_EVENT: return createTImplicitThrowEvent();
			case ModelPackage.TIMPORT: return createTImport();
			case ModelPackage.TINCLUSIVE_GATEWAY: return createTInclusiveGateway();
			case ModelPackage.TINPUT_OUTPUT_BINDING: return createTInputOutputBinding();
			case ModelPackage.TINPUT_OUTPUT_SPECIFICATION: return createTInputOutputSpecification();
			case ModelPackage.TINPUT_SET: return createTInputSet();
			case ModelPackage.TINTERFACE: return createTInterface();
			case ModelPackage.TINTERMEDIATE_CATCH_EVENT: return createTIntermediateCatchEvent();
			case ModelPackage.TINTERMEDIATE_THROW_EVENT: return createTIntermediateThrowEvent();
			case ModelPackage.TITEM_DEFINITION: return createTItemDefinition();
			case ModelPackage.TLANE: return createTLane();
			case ModelPackage.TLANE_SET: return createTLaneSet();
			case ModelPackage.TLINK_EVENT_DEFINITION: return createTLinkEventDefinition();
			case ModelPackage.TMANUAL_TASK: return createTManualTask();
			case ModelPackage.TMESSAGE: return createTMessage();
			case ModelPackage.TMESSAGE_EVENT_DEFINITION: return createTMessageEventDefinition();
			case ModelPackage.TMESSAGE_FLOW: return createTMessageFlow();
			case ModelPackage.TMESSAGE_FLOW_ASSOCIATION: return createTMessageFlowAssociation();
			case ModelPackage.TMONITORING: return createTMonitoring();
			case ModelPackage.TMULTI_INSTANCE_LOOP_CHARACTERISTICS: return createTMultiInstanceLoopCharacteristics();
			case ModelPackage.TOPERATION: return createTOperation();
			case ModelPackage.TOUTPUT_SET: return createTOutputSet();
			case ModelPackage.TPARALLEL_GATEWAY: return createTParallelGateway();
			case ModelPackage.TPARTICIPANT: return createTParticipant();
			case ModelPackage.TPARTICIPANT_ASSOCIATION: return createTParticipantAssociation();
			case ModelPackage.TPARTICIPANT_MULTIPLICITY: return createTParticipantMultiplicity();
			case ModelPackage.TPARTNER_ENTITY: return createTPartnerEntity();
			case ModelPackage.TPARTNER_ROLE: return createTPartnerRole();
			case ModelPackage.TPERFORMER: return createTPerformer();
			case ModelPackage.TPOTENTIAL_OWNER: return createTPotentialOwner();
			case ModelPackage.TPROCESS: return createTProcess();
			case ModelPackage.TPROPERTY: return createTProperty();
			case ModelPackage.TRECEIVE_TASK: return createTReceiveTask();
			case ModelPackage.TRELATIONSHIP: return createTRelationship();
			case ModelPackage.TRENDERING: return createTRendering();
			case ModelPackage.TRESOURCE: return createTResource();
			case ModelPackage.TRESOURCE_ASSIGNMENT_EXPRESSION: return createTResourceAssignmentExpression();
			case ModelPackage.TRESOURCE_PARAMETER: return createTResourceParameter();
			case ModelPackage.TRESOURCE_PARAMETER_BINDING: return createTResourceParameterBinding();
			case ModelPackage.TRESOURCE_ROLE: return createTResourceRole();
			case ModelPackage.TSCRIPT: return createTScript();
			case ModelPackage.TSCRIPT_TASK: return createTScriptTask();
			case ModelPackage.TSEND_TASK: return createTSendTask();
			case ModelPackage.TSEQUENCE_FLOW: return createTSequenceFlow();
			case ModelPackage.TSERVICE_TASK: return createTServiceTask();
			case ModelPackage.TSIGNAL: return createTSignal();
			case ModelPackage.TSIGNAL_EVENT_DEFINITION: return createTSignalEventDefinition();
			case ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS: return createTStandardLoopCharacteristics();
			case ModelPackage.TSTART_EVENT: return createTStartEvent();
			case ModelPackage.TSUB_CHOREOGRAPHY: return createTSubChoreography();
			case ModelPackage.TSUB_CONVERSATION: return createTSubConversation();
			case ModelPackage.TSUB_PROCESS: return createTSubProcess();
			case ModelPackage.TTASK: return createTTask();
			case ModelPackage.TTERMINATE_EVENT_DEFINITION: return createTTerminateEventDefinition();
			case ModelPackage.TTEXT: return createTText();
			case ModelPackage.TTEXT_ANNOTATION: return createTTextAnnotation();
			case ModelPackage.TTIMER_EVENT_DEFINITION: return createTTimerEventDefinition();
			case ModelPackage.TTRANSACTION: return createTTransaction();
			case ModelPackage.TUSER_TASK: return createTUserTask();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
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
			case ModelPackage.TAD_HOC_ORDERING:
				return createTAdHocOrderingFromString(eDataType, initialValue);
			case ModelPackage.TASSOCIATION_DIRECTION:
				return createTAssociationDirectionFromString(eDataType, initialValue);
			case ModelPackage.TCHOREOGRAPHY_LOOP_TYPE:
				return createTChoreographyLoopTypeFromString(eDataType, initialValue);
			case ModelPackage.TEVENT_BASED_GATEWAY_TYPE:
				return createTEventBasedGatewayTypeFromString(eDataType, initialValue);
			case ModelPackage.TGATEWAY_DIRECTION:
				return createTGatewayDirectionFromString(eDataType, initialValue);
			case ModelPackage.TIMPLEMENTATION_MEMBER1:
				return createTImplementationMember1FromString(eDataType, initialValue);
			case ModelPackage.TITEM_KIND:
				return createTItemKindFromString(eDataType, initialValue);
			case ModelPackage.TMULTI_INSTANCE_FLOW_CONDITION:
				return createTMultiInstanceFlowConditionFromString(eDataType, initialValue);
			case ModelPackage.TPROCESS_TYPE:
				return createTProcessTypeFromString(eDataType, initialValue);
			case ModelPackage.TRELATIONSHIP_DIRECTION:
				return createTRelationshipDirectionFromString(eDataType, initialValue);
			case ModelPackage.TTRANSACTION_METHOD_MEMBER1:
				return createTTransactionMethodMember1FromString(eDataType, initialValue);
			case ModelPackage.TAD_HOC_ORDERING_OBJECT:
				return createTAdHocOrderingObjectFromString(eDataType, initialValue);
			case ModelPackage.TASSOCIATION_DIRECTION_OBJECT:
				return createTAssociationDirectionObjectFromString(eDataType, initialValue);
			case ModelPackage.TCHOREOGRAPHY_LOOP_TYPE_OBJECT:
				return createTChoreographyLoopTypeObjectFromString(eDataType, initialValue);
			case ModelPackage.TEVENT_BASED_GATEWAY_TYPE_OBJECT:
				return createTEventBasedGatewayTypeObjectFromString(eDataType, initialValue);
			case ModelPackage.TGATEWAY_DIRECTION_OBJECT:
				return createTGatewayDirectionObjectFromString(eDataType, initialValue);
			case ModelPackage.TIMPLEMENTATION:
				return createTImplementationFromString(eDataType, initialValue);
			case ModelPackage.TIMPLEMENTATION_MEMBER1_OBJECT:
				return createTImplementationMember1ObjectFromString(eDataType, initialValue);
			case ModelPackage.TITEM_KIND_OBJECT:
				return createTItemKindObjectFromString(eDataType, initialValue);
			case ModelPackage.TMULTI_INSTANCE_FLOW_CONDITION_OBJECT:
				return createTMultiInstanceFlowConditionObjectFromString(eDataType, initialValue);
			case ModelPackage.TPROCESS_TYPE_OBJECT:
				return createTProcessTypeObjectFromString(eDataType, initialValue);
			case ModelPackage.TRELATIONSHIP_DIRECTION_OBJECT:
				return createTRelationshipDirectionObjectFromString(eDataType, initialValue);
			case ModelPackage.TTRANSACTION_METHOD:
				return createTTransactionMethodFromString(eDataType, initialValue);
			case ModelPackage.TTRANSACTION_METHOD_MEMBER1_OBJECT:
				return createTTransactionMethodMember1ObjectFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
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
			case ModelPackage.TAD_HOC_ORDERING:
				return convertTAdHocOrderingToString(eDataType, instanceValue);
			case ModelPackage.TASSOCIATION_DIRECTION:
				return convertTAssociationDirectionToString(eDataType, instanceValue);
			case ModelPackage.TCHOREOGRAPHY_LOOP_TYPE:
				return convertTChoreographyLoopTypeToString(eDataType, instanceValue);
			case ModelPackage.TEVENT_BASED_GATEWAY_TYPE:
				return convertTEventBasedGatewayTypeToString(eDataType, instanceValue);
			case ModelPackage.TGATEWAY_DIRECTION:
				return convertTGatewayDirectionToString(eDataType, instanceValue);
			case ModelPackage.TIMPLEMENTATION_MEMBER1:
				return convertTImplementationMember1ToString(eDataType, instanceValue);
			case ModelPackage.TITEM_KIND:
				return convertTItemKindToString(eDataType, instanceValue);
			case ModelPackage.TMULTI_INSTANCE_FLOW_CONDITION:
				return convertTMultiInstanceFlowConditionToString(eDataType, instanceValue);
			case ModelPackage.TPROCESS_TYPE:
				return convertTProcessTypeToString(eDataType, instanceValue);
			case ModelPackage.TRELATIONSHIP_DIRECTION:
				return convertTRelationshipDirectionToString(eDataType, instanceValue);
			case ModelPackage.TTRANSACTION_METHOD_MEMBER1:
				return convertTTransactionMethodMember1ToString(eDataType, instanceValue);
			case ModelPackage.TAD_HOC_ORDERING_OBJECT:
				return convertTAdHocOrderingObjectToString(eDataType, instanceValue);
			case ModelPackage.TASSOCIATION_DIRECTION_OBJECT:
				return convertTAssociationDirectionObjectToString(eDataType, instanceValue);
			case ModelPackage.TCHOREOGRAPHY_LOOP_TYPE_OBJECT:
				return convertTChoreographyLoopTypeObjectToString(eDataType, instanceValue);
			case ModelPackage.TEVENT_BASED_GATEWAY_TYPE_OBJECT:
				return convertTEventBasedGatewayTypeObjectToString(eDataType, instanceValue);
			case ModelPackage.TGATEWAY_DIRECTION_OBJECT:
				return convertTGatewayDirectionObjectToString(eDataType, instanceValue);
			case ModelPackage.TIMPLEMENTATION:
				return convertTImplementationToString(eDataType, instanceValue);
			case ModelPackage.TIMPLEMENTATION_MEMBER1_OBJECT:
				return convertTImplementationMember1ObjectToString(eDataType, instanceValue);
			case ModelPackage.TITEM_KIND_OBJECT:
				return convertTItemKindObjectToString(eDataType, instanceValue);
			case ModelPackage.TMULTI_INSTANCE_FLOW_CONDITION_OBJECT:
				return convertTMultiInstanceFlowConditionObjectToString(eDataType, instanceValue);
			case ModelPackage.TPROCESS_TYPE_OBJECT:
				return convertTProcessTypeObjectToString(eDataType, instanceValue);
			case ModelPackage.TRELATIONSHIP_DIRECTION_OBJECT:
				return convertTRelationshipDirectionObjectToString(eDataType, instanceValue);
			case ModelPackage.TTRANSACTION_METHOD:
				return convertTTransactionMethodToString(eDataType, instanceValue);
			case ModelPackage.TTRANSACTION_METHOD_MEMBER1_OBJECT:
				return convertTTransactionMethodMember1ObjectToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TAdHocSubProcess createTAdHocSubProcess() {
		TAdHocSubProcessImpl tAdHocSubProcess = new TAdHocSubProcessImpl();
		return tAdHocSubProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TAssignment createTAssignment() {
		TAssignmentImpl tAssignment = new TAssignmentImpl();
		return tAssignment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TAssociation createTAssociation() {
		TAssociationImpl tAssociation = new TAssociationImpl();
		return tAssociation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TAuditing createTAuditing() {
		TAuditingImpl tAuditing = new TAuditingImpl();
		return tAuditing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TBoundaryEvent createTBoundaryEvent() {
		TBoundaryEventImpl tBoundaryEvent = new TBoundaryEventImpl();
		return tBoundaryEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TBusinessRuleTask createTBusinessRuleTask() {
		TBusinessRuleTaskImpl tBusinessRuleTask = new TBusinessRuleTaskImpl();
		return tBusinessRuleTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCallableElement createTCallableElement() {
		TCallableElementImpl tCallableElement = new TCallableElementImpl();
		return tCallableElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCallActivity createTCallActivity() {
		TCallActivityImpl tCallActivity = new TCallActivityImpl();
		return tCallActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCallChoreography createTCallChoreography() {
		TCallChoreographyImpl tCallChoreography = new TCallChoreographyImpl();
		return tCallChoreography;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCallConversation createTCallConversation() {
		TCallConversationImpl tCallConversation = new TCallConversationImpl();
		return tCallConversation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCancelEventDefinition createTCancelEventDefinition() {
		TCancelEventDefinitionImpl tCancelEventDefinition = new TCancelEventDefinitionImpl();
		return tCancelEventDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCategory createTCategory() {
		TCategoryImpl tCategory = new TCategoryImpl();
		return tCategory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCategoryValue createTCategoryValue() {
		TCategoryValueImpl tCategoryValue = new TCategoryValueImpl();
		return tCategoryValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TChoreography createTChoreography() {
		TChoreographyImpl tChoreography = new TChoreographyImpl();
		return tChoreography;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TChoreographyTask createTChoreographyTask() {
		TChoreographyTaskImpl tChoreographyTask = new TChoreographyTaskImpl();
		return tChoreographyTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCollaboration createTCollaboration() {
		TCollaborationImpl tCollaboration = new TCollaborationImpl();
		return tCollaboration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCompensateEventDefinition createTCompensateEventDefinition() {
		TCompensateEventDefinitionImpl tCompensateEventDefinition = new TCompensateEventDefinitionImpl();
		return tCompensateEventDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TComplexBehaviorDefinition createTComplexBehaviorDefinition() {
		TComplexBehaviorDefinitionImpl tComplexBehaviorDefinition = new TComplexBehaviorDefinitionImpl();
		return tComplexBehaviorDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TComplexGateway createTComplexGateway() {
		TComplexGatewayImpl tComplexGateway = new TComplexGatewayImpl();
		return tComplexGateway;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TConditionalEventDefinition createTConditionalEventDefinition() {
		TConditionalEventDefinitionImpl tConditionalEventDefinition = new TConditionalEventDefinitionImpl();
		return tConditionalEventDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TConversation createTConversation() {
		TConversationImpl tConversation = new TConversationImpl();
		return tConversation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TConversationAssociation createTConversationAssociation() {
		TConversationAssociationImpl tConversationAssociation = new TConversationAssociationImpl();
		return tConversationAssociation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TConversationLink createTConversationLink() {
		TConversationLinkImpl tConversationLink = new TConversationLinkImpl();
		return tConversationLink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCorrelationKey createTCorrelationKey() {
		TCorrelationKeyImpl tCorrelationKey = new TCorrelationKeyImpl();
		return tCorrelationKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCorrelationProperty createTCorrelationProperty() {
		TCorrelationPropertyImpl tCorrelationProperty = new TCorrelationPropertyImpl();
		return tCorrelationProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCorrelationPropertyBinding createTCorrelationPropertyBinding() {
		TCorrelationPropertyBindingImpl tCorrelationPropertyBinding = new TCorrelationPropertyBindingImpl();
		return tCorrelationPropertyBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCorrelationPropertyRetrievalExpression createTCorrelationPropertyRetrievalExpression() {
		TCorrelationPropertyRetrievalExpressionImpl tCorrelationPropertyRetrievalExpression = new TCorrelationPropertyRetrievalExpressionImpl();
		return tCorrelationPropertyRetrievalExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TCorrelationSubscription createTCorrelationSubscription() {
		TCorrelationSubscriptionImpl tCorrelationSubscription = new TCorrelationSubscriptionImpl();
		return tCorrelationSubscription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDataAssociation createTDataAssociation() {
		TDataAssociationImpl tDataAssociation = new TDataAssociationImpl();
		return tDataAssociation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDataInput createTDataInput() {
		TDataInputImpl tDataInput = new TDataInputImpl();
		return tDataInput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDataInputAssociation createTDataInputAssociation() {
		TDataInputAssociationImpl tDataInputAssociation = new TDataInputAssociationImpl();
		return tDataInputAssociation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDataObject createTDataObject() {
		TDataObjectImpl tDataObject = new TDataObjectImpl();
		return tDataObject;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDataObjectReference createTDataObjectReference() {
		TDataObjectReferenceImpl tDataObjectReference = new TDataObjectReferenceImpl();
		return tDataObjectReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDataOutput createTDataOutput() {
		TDataOutputImpl tDataOutput = new TDataOutputImpl();
		return tDataOutput;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDataOutputAssociation createTDataOutputAssociation() {
		TDataOutputAssociationImpl tDataOutputAssociation = new TDataOutputAssociationImpl();
		return tDataOutputAssociation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDataState createTDataState() {
		TDataStateImpl tDataState = new TDataStateImpl();
		return tDataState;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDataStore createTDataStore() {
		TDataStoreImpl tDataStore = new TDataStoreImpl();
		return tDataStore;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDataStoreReference createTDataStoreReference() {
		TDataStoreReferenceImpl tDataStoreReference = new TDataStoreReferenceImpl();
		return tDataStoreReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDefinitions createTDefinitions() {
		TDefinitionsImpl tDefinitions = new TDefinitionsImpl();
		return tDefinitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TDocumentation createTDocumentation() {
		TDocumentationImpl tDocumentation = new TDocumentationImpl();
		return tDocumentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TEndEvent createTEndEvent() {
		TEndEventImpl tEndEvent = new TEndEventImpl();
		return tEndEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TEndPoint createTEndPoint() {
		TEndPointImpl tEndPoint = new TEndPointImpl();
		return tEndPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TError createTError() {
		TErrorImpl tError = new TErrorImpl();
		return tError;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TErrorEventDefinition createTErrorEventDefinition() {
		TErrorEventDefinitionImpl tErrorEventDefinition = new TErrorEventDefinitionImpl();
		return tErrorEventDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TEscalation createTEscalation() {
		TEscalationImpl tEscalation = new TEscalationImpl();
		return tEscalation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TEscalationEventDefinition createTEscalationEventDefinition() {
		TEscalationEventDefinitionImpl tEscalationEventDefinition = new TEscalationEventDefinitionImpl();
		return tEscalationEventDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TEventBasedGateway createTEventBasedGateway() {
		TEventBasedGatewayImpl tEventBasedGateway = new TEventBasedGatewayImpl();
		return tEventBasedGateway;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TExclusiveGateway createTExclusiveGateway() {
		TExclusiveGatewayImpl tExclusiveGateway = new TExclusiveGatewayImpl();
		return tExclusiveGateway;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TExpression createTExpression() {
		TExpressionImpl tExpression = new TExpressionImpl();
		return tExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TExtension createTExtension() {
		TExtensionImpl tExtension = new TExtensionImpl();
		return tExtension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TExtensionElements createTExtensionElements() {
		TExtensionElementsImpl tExtensionElements = new TExtensionElementsImpl();
		return tExtensionElements;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFormalExpression createTFormalExpression() {
		TFormalExpressionImpl tFormalExpression = new TFormalExpressionImpl();
		return tFormalExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TGateway createTGateway() {
		TGatewayImpl tGateway = new TGatewayImpl();
		return tGateway;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TGlobalBusinessRuleTask createTGlobalBusinessRuleTask() {
		TGlobalBusinessRuleTaskImpl tGlobalBusinessRuleTask = new TGlobalBusinessRuleTaskImpl();
		return tGlobalBusinessRuleTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TGlobalChoreographyTask createTGlobalChoreographyTask() {
		TGlobalChoreographyTaskImpl tGlobalChoreographyTask = new TGlobalChoreographyTaskImpl();
		return tGlobalChoreographyTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TGlobalConversation createTGlobalConversation() {
		TGlobalConversationImpl tGlobalConversation = new TGlobalConversationImpl();
		return tGlobalConversation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TGlobalManualTask createTGlobalManualTask() {
		TGlobalManualTaskImpl tGlobalManualTask = new TGlobalManualTaskImpl();
		return tGlobalManualTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TGlobalScriptTask createTGlobalScriptTask() {
		TGlobalScriptTaskImpl tGlobalScriptTask = new TGlobalScriptTaskImpl();
		return tGlobalScriptTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TGlobalTask createTGlobalTask() {
		TGlobalTaskImpl tGlobalTask = new TGlobalTaskImpl();
		return tGlobalTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TGlobalUserTask createTGlobalUserTask() {
		TGlobalUserTaskImpl tGlobalUserTask = new TGlobalUserTaskImpl();
		return tGlobalUserTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TGroup createTGroup() {
		TGroupImpl tGroup = new TGroupImpl();
		return tGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public THumanPerformer createTHumanPerformer() {
		THumanPerformerImpl tHumanPerformer = new THumanPerformerImpl();
		return tHumanPerformer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TImplicitThrowEvent createTImplicitThrowEvent() {
		TImplicitThrowEventImpl tImplicitThrowEvent = new TImplicitThrowEventImpl();
		return tImplicitThrowEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TImport createTImport() {
		TImportImpl tImport = new TImportImpl();
		return tImport;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TInclusiveGateway createTInclusiveGateway() {
		TInclusiveGatewayImpl tInclusiveGateway = new TInclusiveGatewayImpl();
		return tInclusiveGateway;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TInputOutputBinding createTInputOutputBinding() {
		TInputOutputBindingImpl tInputOutputBinding = new TInputOutputBindingImpl();
		return tInputOutputBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TInputOutputSpecification createTInputOutputSpecification() {
		TInputOutputSpecificationImpl tInputOutputSpecification = new TInputOutputSpecificationImpl();
		return tInputOutputSpecification;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TInputSet createTInputSet() {
		TInputSetImpl tInputSet = new TInputSetImpl();
		return tInputSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TInterface createTInterface() {
		TInterfaceImpl tInterface = new TInterfaceImpl();
		return tInterface;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TIntermediateCatchEvent createTIntermediateCatchEvent() {
		TIntermediateCatchEventImpl tIntermediateCatchEvent = new TIntermediateCatchEventImpl();
		return tIntermediateCatchEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TIntermediateThrowEvent createTIntermediateThrowEvent() {
		TIntermediateThrowEventImpl tIntermediateThrowEvent = new TIntermediateThrowEventImpl();
		return tIntermediateThrowEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TItemDefinition createTItemDefinition() {
		TItemDefinitionImpl tItemDefinition = new TItemDefinitionImpl();
		return tItemDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TLane createTLane() {
		TLaneImpl tLane = new TLaneImpl();
		return tLane;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TLaneSet createTLaneSet() {
		TLaneSetImpl tLaneSet = new TLaneSetImpl();
		return tLaneSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TLinkEventDefinition createTLinkEventDefinition() {
		TLinkEventDefinitionImpl tLinkEventDefinition = new TLinkEventDefinitionImpl();
		return tLinkEventDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TManualTask createTManualTask() {
		TManualTaskImpl tManualTask = new TManualTaskImpl();
		return tManualTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMessage createTMessage() {
		TMessageImpl tMessage = new TMessageImpl();
		return tMessage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMessageEventDefinition createTMessageEventDefinition() {
		TMessageEventDefinitionImpl tMessageEventDefinition = new TMessageEventDefinitionImpl();
		return tMessageEventDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMessageFlow createTMessageFlow() {
		TMessageFlowImpl tMessageFlow = new TMessageFlowImpl();
		return tMessageFlow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMessageFlowAssociation createTMessageFlowAssociation() {
		TMessageFlowAssociationImpl tMessageFlowAssociation = new TMessageFlowAssociationImpl();
		return tMessageFlowAssociation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMonitoring createTMonitoring() {
		TMonitoringImpl tMonitoring = new TMonitoringImpl();
		return tMonitoring;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMultiInstanceLoopCharacteristics createTMultiInstanceLoopCharacteristics() {
		TMultiInstanceLoopCharacteristicsImpl tMultiInstanceLoopCharacteristics = new TMultiInstanceLoopCharacteristicsImpl();
		return tMultiInstanceLoopCharacteristics;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TOperation createTOperation() {
		TOperationImpl tOperation = new TOperationImpl();
		return tOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TOutputSet createTOutputSet() {
		TOutputSetImpl tOutputSet = new TOutputSetImpl();
		return tOutputSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TParallelGateway createTParallelGateway() {
		TParallelGatewayImpl tParallelGateway = new TParallelGatewayImpl();
		return tParallelGateway;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TParticipant createTParticipant() {
		TParticipantImpl tParticipant = new TParticipantImpl();
		return tParticipant;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TParticipantAssociation createTParticipantAssociation() {
		TParticipantAssociationImpl tParticipantAssociation = new TParticipantAssociationImpl();
		return tParticipantAssociation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TParticipantMultiplicity createTParticipantMultiplicity() {
		TParticipantMultiplicityImpl tParticipantMultiplicity = new TParticipantMultiplicityImpl();
		return tParticipantMultiplicity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TPartnerEntity createTPartnerEntity() {
		TPartnerEntityImpl tPartnerEntity = new TPartnerEntityImpl();
		return tPartnerEntity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TPartnerRole createTPartnerRole() {
		TPartnerRoleImpl tPartnerRole = new TPartnerRoleImpl();
		return tPartnerRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TPerformer createTPerformer() {
		TPerformerImpl tPerformer = new TPerformerImpl();
		return tPerformer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TPotentialOwner createTPotentialOwner() {
		TPotentialOwnerImpl tPotentialOwner = new TPotentialOwnerImpl();
		return tPotentialOwner;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TProcess createTProcess() {
		TProcessImpl tProcess = new TProcessImpl();
		return tProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TProperty createTProperty() {
		TPropertyImpl tProperty = new TPropertyImpl();
		return tProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TReceiveTask createTReceiveTask() {
		TReceiveTaskImpl tReceiveTask = new TReceiveTaskImpl();
		return tReceiveTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TRelationship createTRelationship() {
		TRelationshipImpl tRelationship = new TRelationshipImpl();
		return tRelationship;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TRendering createTRendering() {
		TRenderingImpl tRendering = new TRenderingImpl();
		return tRendering;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TResource createTResource() {
		TResourceImpl tResource = new TResourceImpl();
		return tResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TResourceAssignmentExpression createTResourceAssignmentExpression() {
		TResourceAssignmentExpressionImpl tResourceAssignmentExpression = new TResourceAssignmentExpressionImpl();
		return tResourceAssignmentExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TResourceParameter createTResourceParameter() {
		TResourceParameterImpl tResourceParameter = new TResourceParameterImpl();
		return tResourceParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TResourceParameterBinding createTResourceParameterBinding() {
		TResourceParameterBindingImpl tResourceParameterBinding = new TResourceParameterBindingImpl();
		return tResourceParameterBinding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TResourceRole createTResourceRole() {
		TResourceRoleImpl tResourceRole = new TResourceRoleImpl();
		return tResourceRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TScript createTScript() {
		TScriptImpl tScript = new TScriptImpl();
		return tScript;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TScriptTask createTScriptTask() {
		TScriptTaskImpl tScriptTask = new TScriptTaskImpl();
		return tScriptTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSendTask createTSendTask() {
		TSendTaskImpl tSendTask = new TSendTaskImpl();
		return tSendTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSequenceFlow createTSequenceFlow() {
		TSequenceFlowImpl tSequenceFlow = new TSequenceFlowImpl();
		return tSequenceFlow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TServiceTask createTServiceTask() {
		TServiceTaskImpl tServiceTask = new TServiceTaskImpl();
		return tServiceTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSignal createTSignal() {
		TSignalImpl tSignal = new TSignalImpl();
		return tSignal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSignalEventDefinition createTSignalEventDefinition() {
		TSignalEventDefinitionImpl tSignalEventDefinition = new TSignalEventDefinitionImpl();
		return tSignalEventDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TStandardLoopCharacteristics createTStandardLoopCharacteristics() {
		TStandardLoopCharacteristicsImpl tStandardLoopCharacteristics = new TStandardLoopCharacteristicsImpl();
		return tStandardLoopCharacteristics;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TStartEvent createTStartEvent() {
		TStartEventImpl tStartEvent = new TStartEventImpl();
		return tStartEvent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSubChoreography createTSubChoreography() {
		TSubChoreographyImpl tSubChoreography = new TSubChoreographyImpl();
		return tSubChoreography;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSubConversation createTSubConversation() {
		TSubConversationImpl tSubConversation = new TSubConversationImpl();
		return tSubConversation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TSubProcess createTSubProcess() {
		TSubProcessImpl tSubProcess = new TSubProcessImpl();
		return tSubProcess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TTask createTTask() {
		TTaskImpl tTask = new TTaskImpl();
		return tTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TTerminateEventDefinition createTTerminateEventDefinition() {
		TTerminateEventDefinitionImpl tTerminateEventDefinition = new TTerminateEventDefinitionImpl();
		return tTerminateEventDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TText createTText() {
		TTextImpl tText = new TTextImpl();
		return tText;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TTextAnnotation createTTextAnnotation() {
		TTextAnnotationImpl tTextAnnotation = new TTextAnnotationImpl();
		return tTextAnnotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TTimerEventDefinition createTTimerEventDefinition() {
		TTimerEventDefinitionImpl tTimerEventDefinition = new TTimerEventDefinitionImpl();
		return tTimerEventDefinition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TTransaction createTTransaction() {
		TTransactionImpl tTransaction = new TTransactionImpl();
		return tTransaction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TUserTask createTUserTask() {
		TUserTaskImpl tUserTask = new TUserTaskImpl();
		return tUserTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TAdHocOrdering createTAdHocOrderingFromString(EDataType eDataType, String initialValue) {
		TAdHocOrdering result = TAdHocOrdering.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTAdHocOrderingToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TAssociationDirection createTAssociationDirectionFromString(EDataType eDataType, String initialValue) {
		TAssociationDirection result = TAssociationDirection.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTAssociationDirectionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TChoreographyLoopType createTChoreographyLoopTypeFromString(EDataType eDataType, String initialValue) {
		TChoreographyLoopType result = TChoreographyLoopType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTChoreographyLoopTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TEventBasedGatewayType createTEventBasedGatewayTypeFromString(EDataType eDataType, String initialValue) {
		TEventBasedGatewayType result = TEventBasedGatewayType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTEventBasedGatewayTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TGatewayDirection createTGatewayDirectionFromString(EDataType eDataType, String initialValue) {
		TGatewayDirection result = TGatewayDirection.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTGatewayDirectionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TImplementationMember1 createTImplementationMember1FromString(EDataType eDataType, String initialValue) {
		TImplementationMember1 result = TImplementationMember1.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTImplementationMember1ToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TItemKind createTItemKindFromString(EDataType eDataType, String initialValue) {
		TItemKind result = TItemKind.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTItemKindToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMultiInstanceFlowCondition createTMultiInstanceFlowConditionFromString(EDataType eDataType, String initialValue) {
		TMultiInstanceFlowCondition result = TMultiInstanceFlowCondition.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTMultiInstanceFlowConditionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TProcessType createTProcessTypeFromString(EDataType eDataType, String initialValue) {
		TProcessType result = TProcessType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTProcessTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TRelationshipDirection createTRelationshipDirectionFromString(EDataType eDataType, String initialValue) {
		TRelationshipDirection result = TRelationshipDirection.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTRelationshipDirectionToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TTransactionMethodMember1 createTTransactionMethodMember1FromString(EDataType eDataType, String initialValue) {
		TTransactionMethodMember1 result = TTransactionMethodMember1.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTTransactionMethodMember1ToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TAdHocOrdering createTAdHocOrderingObjectFromString(EDataType eDataType, String initialValue) {
		return createTAdHocOrderingFromString(ModelPackage.Literals.TAD_HOC_ORDERING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTAdHocOrderingObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTAdHocOrderingToString(ModelPackage.Literals.TAD_HOC_ORDERING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TAssociationDirection createTAssociationDirectionObjectFromString(EDataType eDataType, String initialValue) {
		return createTAssociationDirectionFromString(ModelPackage.Literals.TASSOCIATION_DIRECTION, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTAssociationDirectionObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTAssociationDirectionToString(ModelPackage.Literals.TASSOCIATION_DIRECTION, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TChoreographyLoopType createTChoreographyLoopTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createTChoreographyLoopTypeFromString(ModelPackage.Literals.TCHOREOGRAPHY_LOOP_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTChoreographyLoopTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTChoreographyLoopTypeToString(ModelPackage.Literals.TCHOREOGRAPHY_LOOP_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TEventBasedGatewayType createTEventBasedGatewayTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createTEventBasedGatewayTypeFromString(ModelPackage.Literals.TEVENT_BASED_GATEWAY_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTEventBasedGatewayTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTEventBasedGatewayTypeToString(ModelPackage.Literals.TEVENT_BASED_GATEWAY_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TGatewayDirection createTGatewayDirectionObjectFromString(EDataType eDataType, String initialValue) {
		return createTGatewayDirectionFromString(ModelPackage.Literals.TGATEWAY_DIRECTION, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTGatewayDirectionObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTGatewayDirectionToString(ModelPackage.Literals.TGATEWAY_DIRECTION, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createTImplementationFromString(EDataType eDataType, String initialValue) {
		if (initialValue == null) return null;
		Object result = null;
		RuntimeException exception = null;
		try {
			result = XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.ANY_URI, initialValue);
			if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null)) {
				return result;
			}
		}
		catch (RuntimeException e) {
			exception = e;
		}
		try {
			result = createTImplementationMember1FromString(ModelPackage.Literals.TIMPLEMENTATION_MEMBER1, initialValue);
			if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null)) {
				return result;
			}
		}
		catch (RuntimeException e) {
			exception = e;
		}
		if (result != null || exception == null) return result;
    
		throw exception;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTImplementationToString(EDataType eDataType, Object instanceValue) {
		if (instanceValue == null) return null;
		if (XMLTypePackage.Literals.ANY_URI.isInstance(instanceValue)) {
			try {
				String value = XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.ANY_URI, instanceValue);
				if (value != null) return value;
			}
			catch (Exception e) {
				// Keep trying other member types until all have failed.
			}
		}
		if (ModelPackage.Literals.TIMPLEMENTATION_MEMBER1.isInstance(instanceValue)) {
			try {
				String value = convertTImplementationMember1ToString(ModelPackage.Literals.TIMPLEMENTATION_MEMBER1, instanceValue);
				if (value != null) return value;
			}
			catch (Exception e) {
				// Keep trying other member types until all have failed.
			}
		}
		throw new IllegalArgumentException("Invalid value: '"+instanceValue+"' for datatype :"+eDataType.getName());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TImplementationMember1 createTImplementationMember1ObjectFromString(EDataType eDataType, String initialValue) {
		return createTImplementationMember1FromString(ModelPackage.Literals.TIMPLEMENTATION_MEMBER1, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTImplementationMember1ObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTImplementationMember1ToString(ModelPackage.Literals.TIMPLEMENTATION_MEMBER1, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TItemKind createTItemKindObjectFromString(EDataType eDataType, String initialValue) {
		return createTItemKindFromString(ModelPackage.Literals.TITEM_KIND, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTItemKindObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTItemKindToString(ModelPackage.Literals.TITEM_KIND, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMultiInstanceFlowCondition createTMultiInstanceFlowConditionObjectFromString(EDataType eDataType, String initialValue) {
		return createTMultiInstanceFlowConditionFromString(ModelPackage.Literals.TMULTI_INSTANCE_FLOW_CONDITION, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTMultiInstanceFlowConditionObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTMultiInstanceFlowConditionToString(ModelPackage.Literals.TMULTI_INSTANCE_FLOW_CONDITION, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TProcessType createTProcessTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createTProcessTypeFromString(ModelPackage.Literals.TPROCESS_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTProcessTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTProcessTypeToString(ModelPackage.Literals.TPROCESS_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TRelationshipDirection createTRelationshipDirectionObjectFromString(EDataType eDataType, String initialValue) {
		return createTRelationshipDirectionFromString(ModelPackage.Literals.TRELATIONSHIP_DIRECTION, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTRelationshipDirectionObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTRelationshipDirectionToString(ModelPackage.Literals.TRELATIONSHIP_DIRECTION, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createTTransactionMethodFromString(EDataType eDataType, String initialValue) {
		if (initialValue == null) return null;
		Object result = null;
		RuntimeException exception = null;
		try {
			result = XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.ANY_URI, initialValue);
			if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null)) {
				return result;
			}
		}
		catch (RuntimeException e) {
			exception = e;
		}
		try {
			result = createTTransactionMethodMember1FromString(ModelPackage.Literals.TTRANSACTION_METHOD_MEMBER1, initialValue);
			if (result != null && Diagnostician.INSTANCE.validate(eDataType, result, null, null)) {
				return result;
			}
		}
		catch (RuntimeException e) {
			exception = e;
		}
		if (result != null || exception == null) return result;
    
		throw exception;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTTransactionMethodToString(EDataType eDataType, Object instanceValue) {
		if (instanceValue == null) return null;
		if (XMLTypePackage.Literals.ANY_URI.isInstance(instanceValue)) {
			try {
				String value = XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.ANY_URI, instanceValue);
				if (value != null) return value;
			}
			catch (Exception e) {
				// Keep trying other member types until all have failed.
			}
		}
		if (ModelPackage.Literals.TTRANSACTION_METHOD_MEMBER1.isInstance(instanceValue)) {
			try {
				String value = convertTTransactionMethodMember1ToString(ModelPackage.Literals.TTRANSACTION_METHOD_MEMBER1, instanceValue);
				if (value != null) return value;
			}
			catch (Exception e) {
				// Keep trying other member types until all have failed.
			}
		}
		throw new IllegalArgumentException("Invalid value: '"+instanceValue+"' for datatype :"+eDataType.getName());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TTransactionMethodMember1 createTTransactionMethodMember1ObjectFromString(EDataType eDataType, String initialValue) {
		return createTTransactionMethodMember1FromString(ModelPackage.Literals.TTRANSACTION_METHOD_MEMBER1, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTTransactionMethodMember1ObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTTransactionMethodMember1ToString(ModelPackage.Literals.TTRANSACTION_METHOD_MEMBER1, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelPackage getModelPackage() {
		return (ModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ModelPackage getPackage() {
		return ModelPackage.eINSTANCE;
	}

} //ModelFactoryImpl
