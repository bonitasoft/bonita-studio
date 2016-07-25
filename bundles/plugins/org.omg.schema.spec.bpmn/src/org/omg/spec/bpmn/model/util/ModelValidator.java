/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model.util;

import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.ecore.xml.type.util.XMLTypeValidator;

import org.omg.spec.bpmn.model.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.omg.spec.bpmn.model.ModelPackage
 * @generated
 */
public class ModelValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final ModelValidator INSTANCE = new ModelValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.omg.spec.bpmn.model";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * The cached base package validator.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected XMLTypeValidator xmlTypeValidator;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelValidator() {
		super();
		xmlTypeValidator = XMLTypeValidator.INSTANCE;
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return ModelPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case ModelPackage.DOCUMENT_ROOT:
				return validateDocumentRoot((DocumentRoot)value, diagnostics, context);
			case ModelPackage.TACTIVITY:
				return validateTActivity((TActivity)value, diagnostics, context);
			case ModelPackage.TAD_HOC_SUB_PROCESS:
				return validateTAdHocSubProcess((TAdHocSubProcess)value, diagnostics, context);
			case ModelPackage.TARTIFACT:
				return validateTArtifact((TArtifact)value, diagnostics, context);
			case ModelPackage.TASSIGNMENT:
				return validateTAssignment((TAssignment)value, diagnostics, context);
			case ModelPackage.TASSOCIATION:
				return validateTAssociation((TAssociation)value, diagnostics, context);
			case ModelPackage.TAUDITING:
				return validateTAuditing((TAuditing)value, diagnostics, context);
			case ModelPackage.TBASE_ELEMENT:
				return validateTBaseElement((TBaseElement)value, diagnostics, context);
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT:
				return validateTBaseElementWithMixedContent((TBaseElementWithMixedContent)value, diagnostics, context);
			case ModelPackage.TBOUNDARY_EVENT:
				return validateTBoundaryEvent((TBoundaryEvent)value, diagnostics, context);
			case ModelPackage.TBUSINESS_RULE_TASK:
				return validateTBusinessRuleTask((TBusinessRuleTask)value, diagnostics, context);
			case ModelPackage.TCALLABLE_ELEMENT:
				return validateTCallableElement((TCallableElement)value, diagnostics, context);
			case ModelPackage.TCALL_ACTIVITY:
				return validateTCallActivity((TCallActivity)value, diagnostics, context);
			case ModelPackage.TCALL_CHOREOGRAPHY:
				return validateTCallChoreography((TCallChoreography)value, diagnostics, context);
			case ModelPackage.TCALL_CONVERSATION:
				return validateTCallConversation((TCallConversation)value, diagnostics, context);
			case ModelPackage.TCANCEL_EVENT_DEFINITION:
				return validateTCancelEventDefinition((TCancelEventDefinition)value, diagnostics, context);
			case ModelPackage.TCATCH_EVENT:
				return validateTCatchEvent((TCatchEvent)value, diagnostics, context);
			case ModelPackage.TCATEGORY:
				return validateTCategory((TCategory)value, diagnostics, context);
			case ModelPackage.TCATEGORY_VALUE:
				return validateTCategoryValue((TCategoryValue)value, diagnostics, context);
			case ModelPackage.TCHOREOGRAPHY:
				return validateTChoreography((TChoreography)value, diagnostics, context);
			case ModelPackage.TCHOREOGRAPHY_ACTIVITY:
				return validateTChoreographyActivity((TChoreographyActivity)value, diagnostics, context);
			case ModelPackage.TCHOREOGRAPHY_TASK:
				return validateTChoreographyTask((TChoreographyTask)value, diagnostics, context);
			case ModelPackage.TCOLLABORATION:
				return validateTCollaboration((TCollaboration)value, diagnostics, context);
			case ModelPackage.TCOMPENSATE_EVENT_DEFINITION:
				return validateTCompensateEventDefinition((TCompensateEventDefinition)value, diagnostics, context);
			case ModelPackage.TCOMPLEX_BEHAVIOR_DEFINITION:
				return validateTComplexBehaviorDefinition((TComplexBehaviorDefinition)value, diagnostics, context);
			case ModelPackage.TCOMPLEX_GATEWAY:
				return validateTComplexGateway((TComplexGateway)value, diagnostics, context);
			case ModelPackage.TCONDITIONAL_EVENT_DEFINITION:
				return validateTConditionalEventDefinition((TConditionalEventDefinition)value, diagnostics, context);
			case ModelPackage.TCONVERSATION:
				return validateTConversation((TConversation)value, diagnostics, context);
			case ModelPackage.TCONVERSATION_ASSOCIATION:
				return validateTConversationAssociation((TConversationAssociation)value, diagnostics, context);
			case ModelPackage.TCONVERSATION_LINK:
				return validateTConversationLink((TConversationLink)value, diagnostics, context);
			case ModelPackage.TCONVERSATION_NODE:
				return validateTConversationNode((TConversationNode)value, diagnostics, context);
			case ModelPackage.TCORRELATION_KEY:
				return validateTCorrelationKey((TCorrelationKey)value, diagnostics, context);
			case ModelPackage.TCORRELATION_PROPERTY:
				return validateTCorrelationProperty((TCorrelationProperty)value, diagnostics, context);
			case ModelPackage.TCORRELATION_PROPERTY_BINDING:
				return validateTCorrelationPropertyBinding((TCorrelationPropertyBinding)value, diagnostics, context);
			case ModelPackage.TCORRELATION_PROPERTY_RETRIEVAL_EXPRESSION:
				return validateTCorrelationPropertyRetrievalExpression((TCorrelationPropertyRetrievalExpression)value, diagnostics, context);
			case ModelPackage.TCORRELATION_SUBSCRIPTION:
				return validateTCorrelationSubscription((TCorrelationSubscription)value, diagnostics, context);
			case ModelPackage.TDATA_ASSOCIATION:
				return validateTDataAssociation((TDataAssociation)value, diagnostics, context);
			case ModelPackage.TDATA_INPUT:
				return validateTDataInput((TDataInput)value, diagnostics, context);
			case ModelPackage.TDATA_INPUT_ASSOCIATION:
				return validateTDataInputAssociation((TDataInputAssociation)value, diagnostics, context);
			case ModelPackage.TDATA_OBJECT:
				return validateTDataObject((TDataObject)value, diagnostics, context);
			case ModelPackage.TDATA_OBJECT_REFERENCE:
				return validateTDataObjectReference((TDataObjectReference)value, diagnostics, context);
			case ModelPackage.TDATA_OUTPUT:
				return validateTDataOutput((TDataOutput)value, diagnostics, context);
			case ModelPackage.TDATA_OUTPUT_ASSOCIATION:
				return validateTDataOutputAssociation((TDataOutputAssociation)value, diagnostics, context);
			case ModelPackage.TDATA_STATE:
				return validateTDataState((TDataState)value, diagnostics, context);
			case ModelPackage.TDATA_STORE:
				return validateTDataStore((TDataStore)value, diagnostics, context);
			case ModelPackage.TDATA_STORE_REFERENCE:
				return validateTDataStoreReference((TDataStoreReference)value, diagnostics, context);
			case ModelPackage.TDEFINITIONS:
				return validateTDefinitions((TDefinitions)value, diagnostics, context);
			case ModelPackage.TDOCUMENTATION:
				return validateTDocumentation((TDocumentation)value, diagnostics, context);
			case ModelPackage.TEND_EVENT:
				return validateTEndEvent((TEndEvent)value, diagnostics, context);
			case ModelPackage.TEND_POINT:
				return validateTEndPoint((TEndPoint)value, diagnostics, context);
			case ModelPackage.TERROR:
				return validateTError((TError)value, diagnostics, context);
			case ModelPackage.TERROR_EVENT_DEFINITION:
				return validateTErrorEventDefinition((TErrorEventDefinition)value, diagnostics, context);
			case ModelPackage.TESCALATION:
				return validateTEscalation((TEscalation)value, diagnostics, context);
			case ModelPackage.TESCALATION_EVENT_DEFINITION:
				return validateTEscalationEventDefinition((TEscalationEventDefinition)value, diagnostics, context);
			case ModelPackage.TEVENT:
				return validateTEvent((TEvent)value, diagnostics, context);
			case ModelPackage.TEVENT_BASED_GATEWAY:
				return validateTEventBasedGateway((TEventBasedGateway)value, diagnostics, context);
			case ModelPackage.TEVENT_DEFINITION:
				return validateTEventDefinition((TEventDefinition)value, diagnostics, context);
			case ModelPackage.TEXCLUSIVE_GATEWAY:
				return validateTExclusiveGateway((TExclusiveGateway)value, diagnostics, context);
			case ModelPackage.TEXPRESSION:
				return validateTExpression((TExpression)value, diagnostics, context);
			case ModelPackage.TEXTENSION:
				return validateTExtension((TExtension)value, diagnostics, context);
			case ModelPackage.TEXTENSION_ELEMENTS:
				return validateTExtensionElements((TExtensionElements)value, diagnostics, context);
			case ModelPackage.TFLOW_ELEMENT:
				return validateTFlowElement((TFlowElement)value, diagnostics, context);
			case ModelPackage.TFLOW_NODE:
				return validateTFlowNode((TFlowNode)value, diagnostics, context);
			case ModelPackage.TFORMAL_EXPRESSION:
				return validateTFormalExpression((TFormalExpression)value, diagnostics, context);
			case ModelPackage.TGATEWAY:
				return validateTGateway((TGateway)value, diagnostics, context);
			case ModelPackage.TGLOBAL_BUSINESS_RULE_TASK:
				return validateTGlobalBusinessRuleTask((TGlobalBusinessRuleTask)value, diagnostics, context);
			case ModelPackage.TGLOBAL_CHOREOGRAPHY_TASK:
				return validateTGlobalChoreographyTask((TGlobalChoreographyTask)value, diagnostics, context);
			case ModelPackage.TGLOBAL_CONVERSATION:
				return validateTGlobalConversation((TGlobalConversation)value, diagnostics, context);
			case ModelPackage.TGLOBAL_MANUAL_TASK:
				return validateTGlobalManualTask((TGlobalManualTask)value, diagnostics, context);
			case ModelPackage.TGLOBAL_SCRIPT_TASK:
				return validateTGlobalScriptTask((TGlobalScriptTask)value, diagnostics, context);
			case ModelPackage.TGLOBAL_TASK:
				return validateTGlobalTask((TGlobalTask)value, diagnostics, context);
			case ModelPackage.TGLOBAL_USER_TASK:
				return validateTGlobalUserTask((TGlobalUserTask)value, diagnostics, context);
			case ModelPackage.TGROUP:
				return validateTGroup((TGroup)value, diagnostics, context);
			case ModelPackage.THUMAN_PERFORMER:
				return validateTHumanPerformer((THumanPerformer)value, diagnostics, context);
			case ModelPackage.TIMPLICIT_THROW_EVENT:
				return validateTImplicitThrowEvent((TImplicitThrowEvent)value, diagnostics, context);
			case ModelPackage.TIMPORT:
				return validateTImport((TImport)value, diagnostics, context);
			case ModelPackage.TINCLUSIVE_GATEWAY:
				return validateTInclusiveGateway((TInclusiveGateway)value, diagnostics, context);
			case ModelPackage.TINPUT_OUTPUT_BINDING:
				return validateTInputOutputBinding((TInputOutputBinding)value, diagnostics, context);
			case ModelPackage.TINPUT_OUTPUT_SPECIFICATION:
				return validateTInputOutputSpecification((TInputOutputSpecification)value, diagnostics, context);
			case ModelPackage.TINPUT_SET:
				return validateTInputSet((TInputSet)value, diagnostics, context);
			case ModelPackage.TINTERFACE:
				return validateTInterface((TInterface)value, diagnostics, context);
			case ModelPackage.TINTERMEDIATE_CATCH_EVENT:
				return validateTIntermediateCatchEvent((TIntermediateCatchEvent)value, diagnostics, context);
			case ModelPackage.TINTERMEDIATE_THROW_EVENT:
				return validateTIntermediateThrowEvent((TIntermediateThrowEvent)value, diagnostics, context);
			case ModelPackage.TITEM_DEFINITION:
				return validateTItemDefinition((TItemDefinition)value, diagnostics, context);
			case ModelPackage.TLANE:
				return validateTLane((TLane)value, diagnostics, context);
			case ModelPackage.TLANE_SET:
				return validateTLaneSet((TLaneSet)value, diagnostics, context);
			case ModelPackage.TLINK_EVENT_DEFINITION:
				return validateTLinkEventDefinition((TLinkEventDefinition)value, diagnostics, context);
			case ModelPackage.TLOOP_CHARACTERISTICS:
				return validateTLoopCharacteristics((TLoopCharacteristics)value, diagnostics, context);
			case ModelPackage.TMANUAL_TASK:
				return validateTManualTask((TManualTask)value, diagnostics, context);
			case ModelPackage.TMESSAGE:
				return validateTMessage((TMessage)value, diagnostics, context);
			case ModelPackage.TMESSAGE_EVENT_DEFINITION:
				return validateTMessageEventDefinition((TMessageEventDefinition)value, diagnostics, context);
			case ModelPackage.TMESSAGE_FLOW:
				return validateTMessageFlow((TMessageFlow)value, diagnostics, context);
			case ModelPackage.TMESSAGE_FLOW_ASSOCIATION:
				return validateTMessageFlowAssociation((TMessageFlowAssociation)value, diagnostics, context);
			case ModelPackage.TMONITORING:
				return validateTMonitoring((TMonitoring)value, diagnostics, context);
			case ModelPackage.TMULTI_INSTANCE_LOOP_CHARACTERISTICS:
				return validateTMultiInstanceLoopCharacteristics((TMultiInstanceLoopCharacteristics)value, diagnostics, context);
			case ModelPackage.TOPERATION:
				return validateTOperation((TOperation)value, diagnostics, context);
			case ModelPackage.TOUTPUT_SET:
				return validateTOutputSet((TOutputSet)value, diagnostics, context);
			case ModelPackage.TPARALLEL_GATEWAY:
				return validateTParallelGateway((TParallelGateway)value, diagnostics, context);
			case ModelPackage.TPARTICIPANT:
				return validateTParticipant((TParticipant)value, diagnostics, context);
			case ModelPackage.TPARTICIPANT_ASSOCIATION:
				return validateTParticipantAssociation((TParticipantAssociation)value, diagnostics, context);
			case ModelPackage.TPARTICIPANT_MULTIPLICITY:
				return validateTParticipantMultiplicity((TParticipantMultiplicity)value, diagnostics, context);
			case ModelPackage.TPARTNER_ENTITY:
				return validateTPartnerEntity((TPartnerEntity)value, diagnostics, context);
			case ModelPackage.TPARTNER_ROLE:
				return validateTPartnerRole((TPartnerRole)value, diagnostics, context);
			case ModelPackage.TPERFORMER:
				return validateTPerformer((TPerformer)value, diagnostics, context);
			case ModelPackage.TPOTENTIAL_OWNER:
				return validateTPotentialOwner((TPotentialOwner)value, diagnostics, context);
			case ModelPackage.TPROCESS:
				return validateTProcess((TProcess)value, diagnostics, context);
			case ModelPackage.TPROPERTY:
				return validateTProperty((TProperty)value, diagnostics, context);
			case ModelPackage.TRECEIVE_TASK:
				return validateTReceiveTask((TReceiveTask)value, diagnostics, context);
			case ModelPackage.TRELATIONSHIP:
				return validateTRelationship((TRelationship)value, diagnostics, context);
			case ModelPackage.TRENDERING:
				return validateTRendering((TRendering)value, diagnostics, context);
			case ModelPackage.TRESOURCE:
				return validateTResource((TResource)value, diagnostics, context);
			case ModelPackage.TRESOURCE_ASSIGNMENT_EXPRESSION:
				return validateTResourceAssignmentExpression((TResourceAssignmentExpression)value, diagnostics, context);
			case ModelPackage.TRESOURCE_PARAMETER:
				return validateTResourceParameter((TResourceParameter)value, diagnostics, context);
			case ModelPackage.TRESOURCE_PARAMETER_BINDING:
				return validateTResourceParameterBinding((TResourceParameterBinding)value, diagnostics, context);
			case ModelPackage.TRESOURCE_ROLE:
				return validateTResourceRole((TResourceRole)value, diagnostics, context);
			case ModelPackage.TROOT_ELEMENT:
				return validateTRootElement((TRootElement)value, diagnostics, context);
			case ModelPackage.TSCRIPT:
				return validateTScript((TScript)value, diagnostics, context);
			case ModelPackage.TSCRIPT_TASK:
				return validateTScriptTask((TScriptTask)value, diagnostics, context);
			case ModelPackage.TSEND_TASK:
				return validateTSendTask((TSendTask)value, diagnostics, context);
			case ModelPackage.TSEQUENCE_FLOW:
				return validateTSequenceFlow((TSequenceFlow)value, diagnostics, context);
			case ModelPackage.TSERVICE_TASK:
				return validateTServiceTask((TServiceTask)value, diagnostics, context);
			case ModelPackage.TSIGNAL:
				return validateTSignal((TSignal)value, diagnostics, context);
			case ModelPackage.TSIGNAL_EVENT_DEFINITION:
				return validateTSignalEventDefinition((TSignalEventDefinition)value, diagnostics, context);
			case ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS:
				return validateTStandardLoopCharacteristics((TStandardLoopCharacteristics)value, diagnostics, context);
			case ModelPackage.TSTART_EVENT:
				return validateTStartEvent((TStartEvent)value, diagnostics, context);
			case ModelPackage.TSUB_CHOREOGRAPHY:
				return validateTSubChoreography((TSubChoreography)value, diagnostics, context);
			case ModelPackage.TSUB_CONVERSATION:
				return validateTSubConversation((TSubConversation)value, diagnostics, context);
			case ModelPackage.TSUB_PROCESS:
				return validateTSubProcess((TSubProcess)value, diagnostics, context);
			case ModelPackage.TTASK:
				return validateTTask((TTask)value, diagnostics, context);
			case ModelPackage.TTERMINATE_EVENT_DEFINITION:
				return validateTTerminateEventDefinition((TTerminateEventDefinition)value, diagnostics, context);
			case ModelPackage.TTEXT:
				return validateTText((TText)value, diagnostics, context);
			case ModelPackage.TTEXT_ANNOTATION:
				return validateTTextAnnotation((TTextAnnotation)value, diagnostics, context);
			case ModelPackage.TTHROW_EVENT:
				return validateTThrowEvent((TThrowEvent)value, diagnostics, context);
			case ModelPackage.TTIMER_EVENT_DEFINITION:
				return validateTTimerEventDefinition((TTimerEventDefinition)value, diagnostics, context);
			case ModelPackage.TTRANSACTION:
				return validateTTransaction((TTransaction)value, diagnostics, context);
			case ModelPackage.TUSER_TASK:
				return validateTUserTask((TUserTask)value, diagnostics, context);
			case ModelPackage.TAD_HOC_ORDERING:
				return validateTAdHocOrdering((TAdHocOrdering)value, diagnostics, context);
			case ModelPackage.TASSOCIATION_DIRECTION:
				return validateTAssociationDirection((TAssociationDirection)value, diagnostics, context);
			case ModelPackage.TCHOREOGRAPHY_LOOP_TYPE:
				return validateTChoreographyLoopType((TChoreographyLoopType)value, diagnostics, context);
			case ModelPackage.TEVENT_BASED_GATEWAY_TYPE:
				return validateTEventBasedGatewayType((TEventBasedGatewayType)value, diagnostics, context);
			case ModelPackage.TGATEWAY_DIRECTION:
				return validateTGatewayDirection((TGatewayDirection)value, diagnostics, context);
			case ModelPackage.TIMPLEMENTATION_MEMBER1:
				return validateTImplementationMember1((TImplementationMember1)value, diagnostics, context);
			case ModelPackage.TITEM_KIND:
				return validateTItemKind((TItemKind)value, diagnostics, context);
			case ModelPackage.TMULTI_INSTANCE_FLOW_CONDITION:
				return validateTMultiInstanceFlowCondition((TMultiInstanceFlowCondition)value, diagnostics, context);
			case ModelPackage.TPROCESS_TYPE:
				return validateTProcessType((TProcessType)value, diagnostics, context);
			case ModelPackage.TRELATIONSHIP_DIRECTION:
				return validateTRelationshipDirection((TRelationshipDirection)value, diagnostics, context);
			case ModelPackage.TTRANSACTION_METHOD_MEMBER1:
				return validateTTransactionMethodMember1((TTransactionMethodMember1)value, diagnostics, context);
			case ModelPackage.TAD_HOC_ORDERING_OBJECT:
				return validateTAdHocOrderingObject((TAdHocOrdering)value, diagnostics, context);
			case ModelPackage.TASSOCIATION_DIRECTION_OBJECT:
				return validateTAssociationDirectionObject((TAssociationDirection)value, diagnostics, context);
			case ModelPackage.TCHOREOGRAPHY_LOOP_TYPE_OBJECT:
				return validateTChoreographyLoopTypeObject((TChoreographyLoopType)value, diagnostics, context);
			case ModelPackage.TEVENT_BASED_GATEWAY_TYPE_OBJECT:
				return validateTEventBasedGatewayTypeObject((TEventBasedGatewayType)value, diagnostics, context);
			case ModelPackage.TGATEWAY_DIRECTION_OBJECT:
				return validateTGatewayDirectionObject((TGatewayDirection)value, diagnostics, context);
			case ModelPackage.TIMPLEMENTATION:
				return validateTImplementation(value, diagnostics, context);
			case ModelPackage.TIMPLEMENTATION_MEMBER1_OBJECT:
				return validateTImplementationMember1Object((TImplementationMember1)value, diagnostics, context);
			case ModelPackage.TITEM_KIND_OBJECT:
				return validateTItemKindObject((TItemKind)value, diagnostics, context);
			case ModelPackage.TMULTI_INSTANCE_FLOW_CONDITION_OBJECT:
				return validateTMultiInstanceFlowConditionObject((TMultiInstanceFlowCondition)value, diagnostics, context);
			case ModelPackage.TPROCESS_TYPE_OBJECT:
				return validateTProcessTypeObject((TProcessType)value, diagnostics, context);
			case ModelPackage.TRELATIONSHIP_DIRECTION_OBJECT:
				return validateTRelationshipDirectionObject((TRelationshipDirection)value, diagnostics, context);
			case ModelPackage.TTRANSACTION_METHOD:
				return validateTTransactionMethod(value, diagnostics, context);
			case ModelPackage.TTRANSACTION_METHOD_MEMBER1_OBJECT:
				return validateTTransactionMethodMember1Object((TTransactionMethodMember1)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDocumentRoot(DocumentRoot documentRoot, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(documentRoot, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTActivity(TActivity tActivity, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tActivity, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTAdHocSubProcess(TAdHocSubProcess tAdHocSubProcess, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tAdHocSubProcess, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTArtifact(TArtifact tArtifact, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tArtifact, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTAssignment(TAssignment tAssignment, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tAssignment, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTAssociation(TAssociation tAssociation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tAssociation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTAuditing(TAuditing tAuditing, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tAuditing, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTBaseElement(TBaseElement tBaseElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tBaseElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTBaseElementWithMixedContent(TBaseElementWithMixedContent tBaseElementWithMixedContent, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tBaseElementWithMixedContent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTBoundaryEvent(TBoundaryEvent tBoundaryEvent, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tBoundaryEvent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTBusinessRuleTask(TBusinessRuleTask tBusinessRuleTask, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tBusinessRuleTask, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTCallableElement(TCallableElement tCallableElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tCallableElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTCallActivity(TCallActivity tCallActivity, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tCallActivity, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTCallChoreography(TCallChoreography tCallChoreography, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tCallChoreography, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTCallConversation(TCallConversation tCallConversation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tCallConversation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTCancelEventDefinition(TCancelEventDefinition tCancelEventDefinition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tCancelEventDefinition, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTCatchEvent(TCatchEvent tCatchEvent, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tCatchEvent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTCategory(TCategory tCategory, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tCategory, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTCategoryValue(TCategoryValue tCategoryValue, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tCategoryValue, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTChoreography(TChoreography tChoreography, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tChoreography, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTChoreographyActivity(TChoreographyActivity tChoreographyActivity, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tChoreographyActivity, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTChoreographyTask(TChoreographyTask tChoreographyTask, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tChoreographyTask, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTCollaboration(TCollaboration tCollaboration, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tCollaboration, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTCompensateEventDefinition(TCompensateEventDefinition tCompensateEventDefinition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tCompensateEventDefinition, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTComplexBehaviorDefinition(TComplexBehaviorDefinition tComplexBehaviorDefinition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tComplexBehaviorDefinition, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTComplexGateway(TComplexGateway tComplexGateway, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tComplexGateway, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTConditionalEventDefinition(TConditionalEventDefinition tConditionalEventDefinition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tConditionalEventDefinition, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTConversation(TConversation tConversation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tConversation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTConversationAssociation(TConversationAssociation tConversationAssociation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tConversationAssociation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTConversationLink(TConversationLink tConversationLink, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tConversationLink, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTConversationNode(TConversationNode tConversationNode, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tConversationNode, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTCorrelationKey(TCorrelationKey tCorrelationKey, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tCorrelationKey, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTCorrelationProperty(TCorrelationProperty tCorrelationProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tCorrelationProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTCorrelationPropertyBinding(TCorrelationPropertyBinding tCorrelationPropertyBinding, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tCorrelationPropertyBinding, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTCorrelationPropertyRetrievalExpression(TCorrelationPropertyRetrievalExpression tCorrelationPropertyRetrievalExpression, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tCorrelationPropertyRetrievalExpression, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTCorrelationSubscription(TCorrelationSubscription tCorrelationSubscription, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tCorrelationSubscription, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTDataAssociation(TDataAssociation tDataAssociation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tDataAssociation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTDataInput(TDataInput tDataInput, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tDataInput, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTDataInputAssociation(TDataInputAssociation tDataInputAssociation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tDataInputAssociation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTDataObject(TDataObject tDataObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tDataObject, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTDataObjectReference(TDataObjectReference tDataObjectReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tDataObjectReference, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTDataOutput(TDataOutput tDataOutput, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tDataOutput, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTDataOutputAssociation(TDataOutputAssociation tDataOutputAssociation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tDataOutputAssociation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTDataState(TDataState tDataState, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tDataState, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTDataStore(TDataStore tDataStore, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tDataStore, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTDataStoreReference(TDataStoreReference tDataStoreReference, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tDataStoreReference, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTDefinitions(TDefinitions tDefinitions, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tDefinitions, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTDocumentation(TDocumentation tDocumentation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tDocumentation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTEndEvent(TEndEvent tEndEvent, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tEndEvent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTEndPoint(TEndPoint tEndPoint, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tEndPoint, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTError(TError tError, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tError, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTErrorEventDefinition(TErrorEventDefinition tErrorEventDefinition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tErrorEventDefinition, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTEscalation(TEscalation tEscalation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tEscalation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTEscalationEventDefinition(TEscalationEventDefinition tEscalationEventDefinition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tEscalationEventDefinition, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTEvent(TEvent tEvent, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tEvent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTEventBasedGateway(TEventBasedGateway tEventBasedGateway, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tEventBasedGateway, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTEventDefinition(TEventDefinition tEventDefinition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tEventDefinition, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTExclusiveGateway(TExclusiveGateway tExclusiveGateway, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tExclusiveGateway, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTExpression(TExpression tExpression, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tExpression, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTExtension(TExtension tExtension, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tExtension, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTExtensionElements(TExtensionElements tExtensionElements, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tExtensionElements, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTFlowElement(TFlowElement tFlowElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tFlowElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTFlowNode(TFlowNode tFlowNode, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tFlowNode, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTFormalExpression(TFormalExpression tFormalExpression, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tFormalExpression, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTGateway(TGateway tGateway, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tGateway, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTGlobalBusinessRuleTask(TGlobalBusinessRuleTask tGlobalBusinessRuleTask, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tGlobalBusinessRuleTask, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTGlobalChoreographyTask(TGlobalChoreographyTask tGlobalChoreographyTask, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tGlobalChoreographyTask, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTGlobalConversation(TGlobalConversation tGlobalConversation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tGlobalConversation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTGlobalManualTask(TGlobalManualTask tGlobalManualTask, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tGlobalManualTask, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTGlobalScriptTask(TGlobalScriptTask tGlobalScriptTask, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tGlobalScriptTask, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTGlobalTask(TGlobalTask tGlobalTask, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tGlobalTask, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTGlobalUserTask(TGlobalUserTask tGlobalUserTask, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tGlobalUserTask, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTGroup(TGroup tGroup, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tGroup, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTHumanPerformer(THumanPerformer tHumanPerformer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tHumanPerformer, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTImplicitThrowEvent(TImplicitThrowEvent tImplicitThrowEvent, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tImplicitThrowEvent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTImport(TImport tImport, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tImport, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTInclusiveGateway(TInclusiveGateway tInclusiveGateway, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tInclusiveGateway, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTInputOutputBinding(TInputOutputBinding tInputOutputBinding, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tInputOutputBinding, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTInputOutputSpecification(TInputOutputSpecification tInputOutputSpecification, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tInputOutputSpecification, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTInputSet(TInputSet tInputSet, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tInputSet, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTInterface(TInterface tInterface, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tInterface, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTIntermediateCatchEvent(TIntermediateCatchEvent tIntermediateCatchEvent, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tIntermediateCatchEvent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTIntermediateThrowEvent(TIntermediateThrowEvent tIntermediateThrowEvent, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tIntermediateThrowEvent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTItemDefinition(TItemDefinition tItemDefinition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tItemDefinition, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTLane(TLane tLane, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tLane, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTLaneSet(TLaneSet tLaneSet, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tLaneSet, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTLinkEventDefinition(TLinkEventDefinition tLinkEventDefinition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tLinkEventDefinition, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTLoopCharacteristics(TLoopCharacteristics tLoopCharacteristics, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tLoopCharacteristics, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTManualTask(TManualTask tManualTask, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tManualTask, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTMessage(TMessage tMessage, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tMessage, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTMessageEventDefinition(TMessageEventDefinition tMessageEventDefinition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tMessageEventDefinition, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTMessageFlow(TMessageFlow tMessageFlow, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tMessageFlow, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTMessageFlowAssociation(TMessageFlowAssociation tMessageFlowAssociation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tMessageFlowAssociation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTMonitoring(TMonitoring tMonitoring, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tMonitoring, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTMultiInstanceLoopCharacteristics(TMultiInstanceLoopCharacteristics tMultiInstanceLoopCharacteristics, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tMultiInstanceLoopCharacteristics, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTOperation(TOperation tOperation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tOperation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTOutputSet(TOutputSet tOutputSet, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tOutputSet, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTParallelGateway(TParallelGateway tParallelGateway, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tParallelGateway, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTParticipant(TParticipant tParticipant, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tParticipant, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTParticipantAssociation(TParticipantAssociation tParticipantAssociation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tParticipantAssociation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTParticipantMultiplicity(TParticipantMultiplicity tParticipantMultiplicity, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tParticipantMultiplicity, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTPartnerEntity(TPartnerEntity tPartnerEntity, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tPartnerEntity, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTPartnerRole(TPartnerRole tPartnerRole, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tPartnerRole, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTPerformer(TPerformer tPerformer, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tPerformer, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTPotentialOwner(TPotentialOwner tPotentialOwner, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tPotentialOwner, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTProcess(TProcess tProcess, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tProcess, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTProperty(TProperty tProperty, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tProperty, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTReceiveTask(TReceiveTask tReceiveTask, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tReceiveTask, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTRelationship(TRelationship tRelationship, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tRelationship, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTRendering(TRendering tRendering, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tRendering, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTResource(TResource tResource, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tResource, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTResourceAssignmentExpression(TResourceAssignmentExpression tResourceAssignmentExpression, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tResourceAssignmentExpression, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTResourceParameter(TResourceParameter tResourceParameter, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tResourceParameter, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTResourceParameterBinding(TResourceParameterBinding tResourceParameterBinding, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tResourceParameterBinding, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTResourceRole(TResourceRole tResourceRole, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tResourceRole, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTRootElement(TRootElement tRootElement, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tRootElement, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTScript(TScript tScript, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tScript, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTScriptTask(TScriptTask tScriptTask, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tScriptTask, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTSendTask(TSendTask tSendTask, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tSendTask, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTSequenceFlow(TSequenceFlow tSequenceFlow, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tSequenceFlow, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTServiceTask(TServiceTask tServiceTask, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tServiceTask, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTSignal(TSignal tSignal, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tSignal, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTSignalEventDefinition(TSignalEventDefinition tSignalEventDefinition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tSignalEventDefinition, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTStandardLoopCharacteristics(TStandardLoopCharacteristics tStandardLoopCharacteristics, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tStandardLoopCharacteristics, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTStartEvent(TStartEvent tStartEvent, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tStartEvent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTSubChoreography(TSubChoreography tSubChoreography, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tSubChoreography, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTSubConversation(TSubConversation tSubConversation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tSubConversation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTSubProcess(TSubProcess tSubProcess, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tSubProcess, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTTask(TTask tTask, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tTask, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTTerminateEventDefinition(TTerminateEventDefinition tTerminateEventDefinition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tTerminateEventDefinition, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTText(TText tText, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tText, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTTextAnnotation(TTextAnnotation tTextAnnotation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tTextAnnotation, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTThrowEvent(TThrowEvent tThrowEvent, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tThrowEvent, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTTimerEventDefinition(TTimerEventDefinition tTimerEventDefinition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tTimerEventDefinition, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTTransaction(TTransaction tTransaction, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tTransaction, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTUserTask(TUserTask tUserTask, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(tUserTask, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTAdHocOrdering(TAdHocOrdering tAdHocOrdering, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTAssociationDirection(TAssociationDirection tAssociationDirection, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTChoreographyLoopType(TChoreographyLoopType tChoreographyLoopType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTEventBasedGatewayType(TEventBasedGatewayType tEventBasedGatewayType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTGatewayDirection(TGatewayDirection tGatewayDirection, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTImplementationMember1(TImplementationMember1 tImplementationMember1, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTItemKind(TItemKind tItemKind, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTMultiInstanceFlowCondition(TMultiInstanceFlowCondition tMultiInstanceFlowCondition, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTProcessType(TProcessType tProcessType, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTRelationshipDirection(TRelationshipDirection tRelationshipDirection, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTTransactionMethodMember1(TTransactionMethodMember1 tTransactionMethodMember1, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTAdHocOrderingObject(TAdHocOrdering tAdHocOrderingObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTAssociationDirectionObject(TAssociationDirection tAssociationDirectionObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTChoreographyLoopTypeObject(TChoreographyLoopType tChoreographyLoopTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTEventBasedGatewayTypeObject(TEventBasedGatewayType tEventBasedGatewayTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTGatewayDirectionObject(TGatewayDirection tGatewayDirectionObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTImplementation(Object tImplementation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateTImplementation_MemberTypes(tImplementation, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MemberTypes constraint of '<em>TImplementation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTImplementation_MemberTypes(Object tImplementation, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (diagnostics != null) {
			BasicDiagnostic tempDiagnostics = new BasicDiagnostic();
			if (XMLTypePackage.Literals.ANY_URI.isInstance(tImplementation)) {
				if (xmlTypeValidator.validateAnyURI((String)tImplementation, tempDiagnostics, context)) return true;
			}
			if (ModelPackage.Literals.TIMPLEMENTATION_MEMBER1.isInstance(tImplementation)) {
				if (validateTImplementationMember1((TImplementationMember1)tImplementation, tempDiagnostics, context)) return true;
			}
			for (Diagnostic diagnostic : tempDiagnostics.getChildren()) {
				diagnostics.add(diagnostic);
			}
		}
		else {
			if (XMLTypePackage.Literals.ANY_URI.isInstance(tImplementation)) {
				if (xmlTypeValidator.validateAnyURI((String)tImplementation, null, context)) return true;
			}
			if (ModelPackage.Literals.TIMPLEMENTATION_MEMBER1.isInstance(tImplementation)) {
				if (validateTImplementationMember1((TImplementationMember1)tImplementation, null, context)) return true;
			}
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTImplementationMember1Object(TImplementationMember1 tImplementationMember1Object, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTItemKindObject(TItemKind tItemKindObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTMultiInstanceFlowConditionObject(TMultiInstanceFlowCondition tMultiInstanceFlowConditionObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTProcessTypeObject(TProcessType tProcessTypeObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTRelationshipDirectionObject(TRelationshipDirection tRelationshipDirectionObject, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTTransactionMethod(Object tTransactionMethod, DiagnosticChain diagnostics, Map<Object, Object> context) {
		boolean result = validateTTransactionMethod_MemberTypes(tTransactionMethod, diagnostics, context);
		return result;
	}

	/**
	 * Validates the MemberTypes constraint of '<em>TTransaction Method</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTTransactionMethod_MemberTypes(Object tTransactionMethod, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (diagnostics != null) {
			BasicDiagnostic tempDiagnostics = new BasicDiagnostic();
			if (XMLTypePackage.Literals.ANY_URI.isInstance(tTransactionMethod)) {
				if (xmlTypeValidator.validateAnyURI((String)tTransactionMethod, tempDiagnostics, context)) return true;
			}
			if (ModelPackage.Literals.TTRANSACTION_METHOD_MEMBER1.isInstance(tTransactionMethod)) {
				if (validateTTransactionMethodMember1((TTransactionMethodMember1)tTransactionMethod, tempDiagnostics, context)) return true;
			}
			for (Diagnostic diagnostic : tempDiagnostics.getChildren()) {
				diagnostics.add(diagnostic);
			}
		}
		else {
			if (XMLTypePackage.Literals.ANY_URI.isInstance(tTransactionMethod)) {
				if (xmlTypeValidator.validateAnyURI((String)tTransactionMethod, null, context)) return true;
			}
			if (ModelPackage.Literals.TTRANSACTION_METHOD_MEMBER1.isInstance(tTransactionMethod)) {
				if (validateTTransactionMethodMember1((TTransactionMethodMember1)tTransactionMethod, null, context)) return true;
			}
		}
		return false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTTransactionMethodMember1Object(TTransactionMethodMember1 tTransactionMethodMember1Object, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //ModelValidator
