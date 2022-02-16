/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.omg.spec.bpmn.model.ModelPackage
 * @generated
 */
public interface ModelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelFactory eINSTANCE = org.omg.spec.bpmn.model.impl.ModelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Document Root</em>'.
	 * @generated
	 */
	DocumentRoot createDocumentRoot();

	/**
	 * Returns a new object of class '<em>TAd Hoc Sub Process</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TAd Hoc Sub Process</em>'.
	 * @generated
	 */
	TAdHocSubProcess createTAdHocSubProcess();

	/**
	 * Returns a new object of class '<em>TAssignment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TAssignment</em>'.
	 * @generated
	 */
	TAssignment createTAssignment();

	/**
	 * Returns a new object of class '<em>TAssociation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TAssociation</em>'.
	 * @generated
	 */
	TAssociation createTAssociation();

	/**
	 * Returns a new object of class '<em>TAuditing</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TAuditing</em>'.
	 * @generated
	 */
	TAuditing createTAuditing();

	/**
	 * Returns a new object of class '<em>TBoundary Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TBoundary Event</em>'.
	 * @generated
	 */
	TBoundaryEvent createTBoundaryEvent();

	/**
	 * Returns a new object of class '<em>TBusiness Rule Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TBusiness Rule Task</em>'.
	 * @generated
	 */
	TBusinessRuleTask createTBusinessRuleTask();

	/**
	 * Returns a new object of class '<em>TCallable Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TCallable Element</em>'.
	 * @generated
	 */
	TCallableElement createTCallableElement();

	/**
	 * Returns a new object of class '<em>TCall Activity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TCall Activity</em>'.
	 * @generated
	 */
	TCallActivity createTCallActivity();

	/**
	 * Returns a new object of class '<em>TCall Choreography</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TCall Choreography</em>'.
	 * @generated
	 */
	TCallChoreography createTCallChoreography();

	/**
	 * Returns a new object of class '<em>TCall Conversation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TCall Conversation</em>'.
	 * @generated
	 */
	TCallConversation createTCallConversation();

	/**
	 * Returns a new object of class '<em>TCancel Event Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TCancel Event Definition</em>'.
	 * @generated
	 */
	TCancelEventDefinition createTCancelEventDefinition();

	/**
	 * Returns a new object of class '<em>TCategory</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TCategory</em>'.
	 * @generated
	 */
	TCategory createTCategory();

	/**
	 * Returns a new object of class '<em>TCategory Value</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TCategory Value</em>'.
	 * @generated
	 */
	TCategoryValue createTCategoryValue();

	/**
	 * Returns a new object of class '<em>TChoreography</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TChoreography</em>'.
	 * @generated
	 */
	TChoreography createTChoreography();

	/**
	 * Returns a new object of class '<em>TChoreography Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TChoreography Task</em>'.
	 * @generated
	 */
	TChoreographyTask createTChoreographyTask();

	/**
	 * Returns a new object of class '<em>TCollaboration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TCollaboration</em>'.
	 * @generated
	 */
	TCollaboration createTCollaboration();

	/**
	 * Returns a new object of class '<em>TCompensate Event Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TCompensate Event Definition</em>'.
	 * @generated
	 */
	TCompensateEventDefinition createTCompensateEventDefinition();

	/**
	 * Returns a new object of class '<em>TComplex Behavior Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TComplex Behavior Definition</em>'.
	 * @generated
	 */
	TComplexBehaviorDefinition createTComplexBehaviorDefinition();

	/**
	 * Returns a new object of class '<em>TComplex Gateway</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TComplex Gateway</em>'.
	 * @generated
	 */
	TComplexGateway createTComplexGateway();

	/**
	 * Returns a new object of class '<em>TConditional Event Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TConditional Event Definition</em>'.
	 * @generated
	 */
	TConditionalEventDefinition createTConditionalEventDefinition();

	/**
	 * Returns a new object of class '<em>TConversation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TConversation</em>'.
	 * @generated
	 */
	TConversation createTConversation();

	/**
	 * Returns a new object of class '<em>TConversation Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TConversation Association</em>'.
	 * @generated
	 */
	TConversationAssociation createTConversationAssociation();

	/**
	 * Returns a new object of class '<em>TConversation Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TConversation Link</em>'.
	 * @generated
	 */
	TConversationLink createTConversationLink();

	/**
	 * Returns a new object of class '<em>TCorrelation Key</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TCorrelation Key</em>'.
	 * @generated
	 */
	TCorrelationKey createTCorrelationKey();

	/**
	 * Returns a new object of class '<em>TCorrelation Property</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TCorrelation Property</em>'.
	 * @generated
	 */
	TCorrelationProperty createTCorrelationProperty();

	/**
	 * Returns a new object of class '<em>TCorrelation Property Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TCorrelation Property Binding</em>'.
	 * @generated
	 */
	TCorrelationPropertyBinding createTCorrelationPropertyBinding();

	/**
	 * Returns a new object of class '<em>TCorrelation Property Retrieval Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TCorrelation Property Retrieval Expression</em>'.
	 * @generated
	 */
	TCorrelationPropertyRetrievalExpression createTCorrelationPropertyRetrievalExpression();

	/**
	 * Returns a new object of class '<em>TCorrelation Subscription</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TCorrelation Subscription</em>'.
	 * @generated
	 */
	TCorrelationSubscription createTCorrelationSubscription();

	/**
	 * Returns a new object of class '<em>TData Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TData Association</em>'.
	 * @generated
	 */
	TDataAssociation createTDataAssociation();

	/**
	 * Returns a new object of class '<em>TData Input</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TData Input</em>'.
	 * @generated
	 */
	TDataInput createTDataInput();

	/**
	 * Returns a new object of class '<em>TData Input Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TData Input Association</em>'.
	 * @generated
	 */
	TDataInputAssociation createTDataInputAssociation();

	/**
	 * Returns a new object of class '<em>TData Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TData Object</em>'.
	 * @generated
	 */
	TDataObject createTDataObject();

	/**
	 * Returns a new object of class '<em>TData Object Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TData Object Reference</em>'.
	 * @generated
	 */
	TDataObjectReference createTDataObjectReference();

	/**
	 * Returns a new object of class '<em>TData Output</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TData Output</em>'.
	 * @generated
	 */
	TDataOutput createTDataOutput();

	/**
	 * Returns a new object of class '<em>TData Output Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TData Output Association</em>'.
	 * @generated
	 */
	TDataOutputAssociation createTDataOutputAssociation();

	/**
	 * Returns a new object of class '<em>TData State</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TData State</em>'.
	 * @generated
	 */
	TDataState createTDataState();

	/**
	 * Returns a new object of class '<em>TData Store</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TData Store</em>'.
	 * @generated
	 */
	TDataStore createTDataStore();

	/**
	 * Returns a new object of class '<em>TData Store Reference</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TData Store Reference</em>'.
	 * @generated
	 */
	TDataStoreReference createTDataStoreReference();

	/**
	 * Returns a new object of class '<em>TDefinitions</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TDefinitions</em>'.
	 * @generated
	 */
	TDefinitions createTDefinitions();

	/**
	 * Returns a new object of class '<em>TDocumentation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TDocumentation</em>'.
	 * @generated
	 */
	TDocumentation createTDocumentation();

	/**
	 * Returns a new object of class '<em>TEnd Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TEnd Event</em>'.
	 * @generated
	 */
	TEndEvent createTEndEvent();

	/**
	 * Returns a new object of class '<em>TEnd Point</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TEnd Point</em>'.
	 * @generated
	 */
	TEndPoint createTEndPoint();

	/**
	 * Returns a new object of class '<em>TError</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TError</em>'.
	 * @generated
	 */
	TError createTError();

	/**
	 * Returns a new object of class '<em>TError Event Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TError Event Definition</em>'.
	 * @generated
	 */
	TErrorEventDefinition createTErrorEventDefinition();

	/**
	 * Returns a new object of class '<em>TEscalation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TEscalation</em>'.
	 * @generated
	 */
	TEscalation createTEscalation();

	/**
	 * Returns a new object of class '<em>TEscalation Event Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TEscalation Event Definition</em>'.
	 * @generated
	 */
	TEscalationEventDefinition createTEscalationEventDefinition();

	/**
	 * Returns a new object of class '<em>TEvent Based Gateway</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TEvent Based Gateway</em>'.
	 * @generated
	 */
	TEventBasedGateway createTEventBasedGateway();

	/**
	 * Returns a new object of class '<em>TExclusive Gateway</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TExclusive Gateway</em>'.
	 * @generated
	 */
	TExclusiveGateway createTExclusiveGateway();

	/**
	 * Returns a new object of class '<em>TExpression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TExpression</em>'.
	 * @generated
	 */
	TExpression createTExpression();

	/**
	 * Returns a new object of class '<em>TExtension</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TExtension</em>'.
	 * @generated
	 */
	TExtension createTExtension();

	/**
	 * Returns a new object of class '<em>TExtension Elements</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TExtension Elements</em>'.
	 * @generated
	 */
	TExtensionElements createTExtensionElements();

	/**
	 * Returns a new object of class '<em>TFormal Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TFormal Expression</em>'.
	 * @generated
	 */
	TFormalExpression createTFormalExpression();

	/**
	 * Returns a new object of class '<em>TGateway</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TGateway</em>'.
	 * @generated
	 */
	TGateway createTGateway();

	/**
	 * Returns a new object of class '<em>TGlobal Business Rule Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TGlobal Business Rule Task</em>'.
	 * @generated
	 */
	TGlobalBusinessRuleTask createTGlobalBusinessRuleTask();

	/**
	 * Returns a new object of class '<em>TGlobal Choreography Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TGlobal Choreography Task</em>'.
	 * @generated
	 */
	TGlobalChoreographyTask createTGlobalChoreographyTask();

	/**
	 * Returns a new object of class '<em>TGlobal Conversation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TGlobal Conversation</em>'.
	 * @generated
	 */
	TGlobalConversation createTGlobalConversation();

	/**
	 * Returns a new object of class '<em>TGlobal Manual Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TGlobal Manual Task</em>'.
	 * @generated
	 */
	TGlobalManualTask createTGlobalManualTask();

	/**
	 * Returns a new object of class '<em>TGlobal Script Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TGlobal Script Task</em>'.
	 * @generated
	 */
	TGlobalScriptTask createTGlobalScriptTask();

	/**
	 * Returns a new object of class '<em>TGlobal Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TGlobal Task</em>'.
	 * @generated
	 */
	TGlobalTask createTGlobalTask();

	/**
	 * Returns a new object of class '<em>TGlobal User Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TGlobal User Task</em>'.
	 * @generated
	 */
	TGlobalUserTask createTGlobalUserTask();

	/**
	 * Returns a new object of class '<em>TGroup</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TGroup</em>'.
	 * @generated
	 */
	TGroup createTGroup();

	/**
	 * Returns a new object of class '<em>THuman Performer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>THuman Performer</em>'.
	 * @generated
	 */
	THumanPerformer createTHumanPerformer();

	/**
	 * Returns a new object of class '<em>TImplicit Throw Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TImplicit Throw Event</em>'.
	 * @generated
	 */
	TImplicitThrowEvent createTImplicitThrowEvent();

	/**
	 * Returns a new object of class '<em>TImport</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TImport</em>'.
	 * @generated
	 */
	TImport createTImport();

	/**
	 * Returns a new object of class '<em>TInclusive Gateway</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TInclusive Gateway</em>'.
	 * @generated
	 */
	TInclusiveGateway createTInclusiveGateway();

	/**
	 * Returns a new object of class '<em>TInput Output Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TInput Output Binding</em>'.
	 * @generated
	 */
	TInputOutputBinding createTInputOutputBinding();

	/**
	 * Returns a new object of class '<em>TInput Output Specification</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TInput Output Specification</em>'.
	 * @generated
	 */
	TInputOutputSpecification createTInputOutputSpecification();

	/**
	 * Returns a new object of class '<em>TInput Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TInput Set</em>'.
	 * @generated
	 */
	TInputSet createTInputSet();

	/**
	 * Returns a new object of class '<em>TInterface</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TInterface</em>'.
	 * @generated
	 */
	TInterface createTInterface();

	/**
	 * Returns a new object of class '<em>TIntermediate Catch Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TIntermediate Catch Event</em>'.
	 * @generated
	 */
	TIntermediateCatchEvent createTIntermediateCatchEvent();

	/**
	 * Returns a new object of class '<em>TIntermediate Throw Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TIntermediate Throw Event</em>'.
	 * @generated
	 */
	TIntermediateThrowEvent createTIntermediateThrowEvent();

	/**
	 * Returns a new object of class '<em>TItem Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TItem Definition</em>'.
	 * @generated
	 */
	TItemDefinition createTItemDefinition();

	/**
	 * Returns a new object of class '<em>TLane</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TLane</em>'.
	 * @generated
	 */
	TLane createTLane();

	/**
	 * Returns a new object of class '<em>TLane Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TLane Set</em>'.
	 * @generated
	 */
	TLaneSet createTLaneSet();

	/**
	 * Returns a new object of class '<em>TLink Event Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TLink Event Definition</em>'.
	 * @generated
	 */
	TLinkEventDefinition createTLinkEventDefinition();

	/**
	 * Returns a new object of class '<em>TManual Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TManual Task</em>'.
	 * @generated
	 */
	TManualTask createTManualTask();

	/**
	 * Returns a new object of class '<em>TMessage</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TMessage</em>'.
	 * @generated
	 */
	TMessage createTMessage();

	/**
	 * Returns a new object of class '<em>TMessage Event Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TMessage Event Definition</em>'.
	 * @generated
	 */
	TMessageEventDefinition createTMessageEventDefinition();

	/**
	 * Returns a new object of class '<em>TMessage Flow</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TMessage Flow</em>'.
	 * @generated
	 */
	TMessageFlow createTMessageFlow();

	/**
	 * Returns a new object of class '<em>TMessage Flow Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TMessage Flow Association</em>'.
	 * @generated
	 */
	TMessageFlowAssociation createTMessageFlowAssociation();

	/**
	 * Returns a new object of class '<em>TMonitoring</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TMonitoring</em>'.
	 * @generated
	 */
	TMonitoring createTMonitoring();

	/**
	 * Returns a new object of class '<em>TMulti Instance Loop Characteristics</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TMulti Instance Loop Characteristics</em>'.
	 * @generated
	 */
	TMultiInstanceLoopCharacteristics createTMultiInstanceLoopCharacteristics();

	/**
	 * Returns a new object of class '<em>TOperation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TOperation</em>'.
	 * @generated
	 */
	TOperation createTOperation();

	/**
	 * Returns a new object of class '<em>TOutput Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TOutput Set</em>'.
	 * @generated
	 */
	TOutputSet createTOutputSet();

	/**
	 * Returns a new object of class '<em>TParallel Gateway</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TParallel Gateway</em>'.
	 * @generated
	 */
	TParallelGateway createTParallelGateway();

	/**
	 * Returns a new object of class '<em>TParticipant</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TParticipant</em>'.
	 * @generated
	 */
	TParticipant createTParticipant();

	/**
	 * Returns a new object of class '<em>TParticipant Association</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TParticipant Association</em>'.
	 * @generated
	 */
	TParticipantAssociation createTParticipantAssociation();

	/**
	 * Returns a new object of class '<em>TParticipant Multiplicity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TParticipant Multiplicity</em>'.
	 * @generated
	 */
	TParticipantMultiplicity createTParticipantMultiplicity();

	/**
	 * Returns a new object of class '<em>TPartner Entity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TPartner Entity</em>'.
	 * @generated
	 */
	TPartnerEntity createTPartnerEntity();

	/**
	 * Returns a new object of class '<em>TPartner Role</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TPartner Role</em>'.
	 * @generated
	 */
	TPartnerRole createTPartnerRole();

	/**
	 * Returns a new object of class '<em>TPerformer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TPerformer</em>'.
	 * @generated
	 */
	TPerformer createTPerformer();

	/**
	 * Returns a new object of class '<em>TPotential Owner</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TPotential Owner</em>'.
	 * @generated
	 */
	TPotentialOwner createTPotentialOwner();

	/**
	 * Returns a new object of class '<em>TProcess</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TProcess</em>'.
	 * @generated
	 */
	TProcess createTProcess();

	/**
	 * Returns a new object of class '<em>TProperty</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TProperty</em>'.
	 * @generated
	 */
	TProperty createTProperty();

	/**
	 * Returns a new object of class '<em>TReceive Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TReceive Task</em>'.
	 * @generated
	 */
	TReceiveTask createTReceiveTask();

	/**
	 * Returns a new object of class '<em>TRelationship</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TRelationship</em>'.
	 * @generated
	 */
	TRelationship createTRelationship();

	/**
	 * Returns a new object of class '<em>TRendering</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TRendering</em>'.
	 * @generated
	 */
	TRendering createTRendering();

	/**
	 * Returns a new object of class '<em>TResource</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TResource</em>'.
	 * @generated
	 */
	TResource createTResource();

	/**
	 * Returns a new object of class '<em>TResource Assignment Expression</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TResource Assignment Expression</em>'.
	 * @generated
	 */
	TResourceAssignmentExpression createTResourceAssignmentExpression();

	/**
	 * Returns a new object of class '<em>TResource Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TResource Parameter</em>'.
	 * @generated
	 */
	TResourceParameter createTResourceParameter();

	/**
	 * Returns a new object of class '<em>TResource Parameter Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TResource Parameter Binding</em>'.
	 * @generated
	 */
	TResourceParameterBinding createTResourceParameterBinding();

	/**
	 * Returns a new object of class '<em>TResource Role</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TResource Role</em>'.
	 * @generated
	 */
	TResourceRole createTResourceRole();

	/**
	 * Returns a new object of class '<em>TScript</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TScript</em>'.
	 * @generated
	 */
	TScript createTScript();

	/**
	 * Returns a new object of class '<em>TScript Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TScript Task</em>'.
	 * @generated
	 */
	TScriptTask createTScriptTask();

	/**
	 * Returns a new object of class '<em>TSend Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TSend Task</em>'.
	 * @generated
	 */
	TSendTask createTSendTask();

	/**
	 * Returns a new object of class '<em>TSequence Flow</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TSequence Flow</em>'.
	 * @generated
	 */
	TSequenceFlow createTSequenceFlow();

	/**
	 * Returns a new object of class '<em>TService Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TService Task</em>'.
	 * @generated
	 */
	TServiceTask createTServiceTask();

	/**
	 * Returns a new object of class '<em>TSignal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TSignal</em>'.
	 * @generated
	 */
	TSignal createTSignal();

	/**
	 * Returns a new object of class '<em>TSignal Event Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TSignal Event Definition</em>'.
	 * @generated
	 */
	TSignalEventDefinition createTSignalEventDefinition();

	/**
	 * Returns a new object of class '<em>TStandard Loop Characteristics</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TStandard Loop Characteristics</em>'.
	 * @generated
	 */
	TStandardLoopCharacteristics createTStandardLoopCharacteristics();

	/**
	 * Returns a new object of class '<em>TStart Event</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TStart Event</em>'.
	 * @generated
	 */
	TStartEvent createTStartEvent();

	/**
	 * Returns a new object of class '<em>TSub Choreography</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TSub Choreography</em>'.
	 * @generated
	 */
	TSubChoreography createTSubChoreography();

	/**
	 * Returns a new object of class '<em>TSub Conversation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TSub Conversation</em>'.
	 * @generated
	 */
	TSubConversation createTSubConversation();

	/**
	 * Returns a new object of class '<em>TSub Process</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TSub Process</em>'.
	 * @generated
	 */
	TSubProcess createTSubProcess();

	/**
	 * Returns a new object of class '<em>TTask</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TTask</em>'.
	 * @generated
	 */
	TTask createTTask();

	/**
	 * Returns a new object of class '<em>TTerminate Event Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TTerminate Event Definition</em>'.
	 * @generated
	 */
	TTerminateEventDefinition createTTerminateEventDefinition();

	/**
	 * Returns a new object of class '<em>TText</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TText</em>'.
	 * @generated
	 */
	TText createTText();

	/**
	 * Returns a new object of class '<em>TText Annotation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TText Annotation</em>'.
	 * @generated
	 */
	TTextAnnotation createTTextAnnotation();

	/**
	 * Returns a new object of class '<em>TTimer Event Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TTimer Event Definition</em>'.
	 * @generated
	 */
	TTimerEventDefinition createTTimerEventDefinition();

	/**
	 * Returns a new object of class '<em>TTransaction</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TTransaction</em>'.
	 * @generated
	 */
	TTransaction createTTransaction();

	/**
	 * Returns a new object of class '<em>TUser Task</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TUser Task</em>'.
	 * @generated
	 */
	TUserTask createTUserTask();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ModelPackage getModelPackage();

} //ModelFactory
