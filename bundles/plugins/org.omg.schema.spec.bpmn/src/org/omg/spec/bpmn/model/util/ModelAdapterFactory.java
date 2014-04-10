/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.omg.spec.bpmn.model.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.omg.spec.bpmn.model.ModelPackage
 * @generated
 */
public class ModelAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ModelPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ModelPackage.eINSTANCE;
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
	protected ModelSwitch<Adapter> modelSwitch =
		new ModelSwitch<Adapter>() {
			@Override
			public Adapter caseDocumentRoot(DocumentRoot object) {
				return createDocumentRootAdapter();
			}
			@Override
			public Adapter caseTActivity(TActivity object) {
				return createTActivityAdapter();
			}
			@Override
			public Adapter caseTAdHocSubProcess(TAdHocSubProcess object) {
				return createTAdHocSubProcessAdapter();
			}
			@Override
			public Adapter caseTArtifact(TArtifact object) {
				return createTArtifactAdapter();
			}
			@Override
			public Adapter caseTAssignment(TAssignment object) {
				return createTAssignmentAdapter();
			}
			@Override
			public Adapter caseTAssociation(TAssociation object) {
				return createTAssociationAdapter();
			}
			@Override
			public Adapter caseTAuditing(TAuditing object) {
				return createTAuditingAdapter();
			}
			@Override
			public Adapter caseTBaseElement(TBaseElement object) {
				return createTBaseElementAdapter();
			}
			@Override
			public Adapter caseTBaseElementWithMixedContent(TBaseElementWithMixedContent object) {
				return createTBaseElementWithMixedContentAdapter();
			}
			@Override
			public Adapter caseTBoundaryEvent(TBoundaryEvent object) {
				return createTBoundaryEventAdapter();
			}
			@Override
			public Adapter caseTBusinessRuleTask(TBusinessRuleTask object) {
				return createTBusinessRuleTaskAdapter();
			}
			@Override
			public Adapter caseTCallableElement(TCallableElement object) {
				return createTCallableElementAdapter();
			}
			@Override
			public Adapter caseTCallActivity(TCallActivity object) {
				return createTCallActivityAdapter();
			}
			@Override
			public Adapter caseTCallChoreography(TCallChoreography object) {
				return createTCallChoreographyAdapter();
			}
			@Override
			public Adapter caseTCallConversation(TCallConversation object) {
				return createTCallConversationAdapter();
			}
			@Override
			public Adapter caseTCancelEventDefinition(TCancelEventDefinition object) {
				return createTCancelEventDefinitionAdapter();
			}
			@Override
			public Adapter caseTCatchEvent(TCatchEvent object) {
				return createTCatchEventAdapter();
			}
			@Override
			public Adapter caseTCategory(TCategory object) {
				return createTCategoryAdapter();
			}
			@Override
			public Adapter caseTCategoryValue(TCategoryValue object) {
				return createTCategoryValueAdapter();
			}
			@Override
			public Adapter caseTChoreography(TChoreography object) {
				return createTChoreographyAdapter();
			}
			@Override
			public Adapter caseTChoreographyActivity(TChoreographyActivity object) {
				return createTChoreographyActivityAdapter();
			}
			@Override
			public Adapter caseTChoreographyTask(TChoreographyTask object) {
				return createTChoreographyTaskAdapter();
			}
			@Override
			public Adapter caseTCollaboration(TCollaboration object) {
				return createTCollaborationAdapter();
			}
			@Override
			public Adapter caseTCompensateEventDefinition(TCompensateEventDefinition object) {
				return createTCompensateEventDefinitionAdapter();
			}
			@Override
			public Adapter caseTComplexBehaviorDefinition(TComplexBehaviorDefinition object) {
				return createTComplexBehaviorDefinitionAdapter();
			}
			@Override
			public Adapter caseTComplexGateway(TComplexGateway object) {
				return createTComplexGatewayAdapter();
			}
			@Override
			public Adapter caseTConditionalEventDefinition(TConditionalEventDefinition object) {
				return createTConditionalEventDefinitionAdapter();
			}
			@Override
			public Adapter caseTConversation(TConversation object) {
				return createTConversationAdapter();
			}
			@Override
			public Adapter caseTConversationAssociation(TConversationAssociation object) {
				return createTConversationAssociationAdapter();
			}
			@Override
			public Adapter caseTConversationLink(TConversationLink object) {
				return createTConversationLinkAdapter();
			}
			@Override
			public Adapter caseTConversationNode(TConversationNode object) {
				return createTConversationNodeAdapter();
			}
			@Override
			public Adapter caseTCorrelationKey(TCorrelationKey object) {
				return createTCorrelationKeyAdapter();
			}
			@Override
			public Adapter caseTCorrelationProperty(TCorrelationProperty object) {
				return createTCorrelationPropertyAdapter();
			}
			@Override
			public Adapter caseTCorrelationPropertyBinding(TCorrelationPropertyBinding object) {
				return createTCorrelationPropertyBindingAdapter();
			}
			@Override
			public Adapter caseTCorrelationPropertyRetrievalExpression(TCorrelationPropertyRetrievalExpression object) {
				return createTCorrelationPropertyRetrievalExpressionAdapter();
			}
			@Override
			public Adapter caseTCorrelationSubscription(TCorrelationSubscription object) {
				return createTCorrelationSubscriptionAdapter();
			}
			@Override
			public Adapter caseTDataAssociation(TDataAssociation object) {
				return createTDataAssociationAdapter();
			}
			@Override
			public Adapter caseTDataInput(TDataInput object) {
				return createTDataInputAdapter();
			}
			@Override
			public Adapter caseTDataInputAssociation(TDataInputAssociation object) {
				return createTDataInputAssociationAdapter();
			}
			@Override
			public Adapter caseTDataObject(TDataObject object) {
				return createTDataObjectAdapter();
			}
			@Override
			public Adapter caseTDataObjectReference(TDataObjectReference object) {
				return createTDataObjectReferenceAdapter();
			}
			@Override
			public Adapter caseTDataOutput(TDataOutput object) {
				return createTDataOutputAdapter();
			}
			@Override
			public Adapter caseTDataOutputAssociation(TDataOutputAssociation object) {
				return createTDataOutputAssociationAdapter();
			}
			@Override
			public Adapter caseTDataState(TDataState object) {
				return createTDataStateAdapter();
			}
			@Override
			public Adapter caseTDataStore(TDataStore object) {
				return createTDataStoreAdapter();
			}
			@Override
			public Adapter caseTDataStoreReference(TDataStoreReference object) {
				return createTDataStoreReferenceAdapter();
			}
			@Override
			public Adapter caseTDefinitions(TDefinitions object) {
				return createTDefinitionsAdapter();
			}
			@Override
			public Adapter caseTDocumentation(TDocumentation object) {
				return createTDocumentationAdapter();
			}
			@Override
			public Adapter caseTEndEvent(TEndEvent object) {
				return createTEndEventAdapter();
			}
			@Override
			public Adapter caseTEndPoint(TEndPoint object) {
				return createTEndPointAdapter();
			}
			@Override
			public Adapter caseTError(TError object) {
				return createTErrorAdapter();
			}
			@Override
			public Adapter caseTErrorEventDefinition(TErrorEventDefinition object) {
				return createTErrorEventDefinitionAdapter();
			}
			@Override
			public Adapter caseTEscalation(TEscalation object) {
				return createTEscalationAdapter();
			}
			@Override
			public Adapter caseTEscalationEventDefinition(TEscalationEventDefinition object) {
				return createTEscalationEventDefinitionAdapter();
			}
			@Override
			public Adapter caseTEvent(TEvent object) {
				return createTEventAdapter();
			}
			@Override
			public Adapter caseTEventBasedGateway(TEventBasedGateway object) {
				return createTEventBasedGatewayAdapter();
			}
			@Override
			public Adapter caseTEventDefinition(TEventDefinition object) {
				return createTEventDefinitionAdapter();
			}
			@Override
			public Adapter caseTExclusiveGateway(TExclusiveGateway object) {
				return createTExclusiveGatewayAdapter();
			}
			@Override
			public Adapter caseTExpression(TExpression object) {
				return createTExpressionAdapter();
			}
			@Override
			public Adapter caseTExtension(TExtension object) {
				return createTExtensionAdapter();
			}
			@Override
			public Adapter caseTExtensionElements(TExtensionElements object) {
				return createTExtensionElementsAdapter();
			}
			@Override
			public Adapter caseTFlowElement(TFlowElement object) {
				return createTFlowElementAdapter();
			}
			@Override
			public Adapter caseTFlowNode(TFlowNode object) {
				return createTFlowNodeAdapter();
			}
			@Override
			public Adapter caseTFormalExpression(TFormalExpression object) {
				return createTFormalExpressionAdapter();
			}
			@Override
			public Adapter caseTGateway(TGateway object) {
				return createTGatewayAdapter();
			}
			@Override
			public Adapter caseTGlobalBusinessRuleTask(TGlobalBusinessRuleTask object) {
				return createTGlobalBusinessRuleTaskAdapter();
			}
			@Override
			public Adapter caseTGlobalChoreographyTask(TGlobalChoreographyTask object) {
				return createTGlobalChoreographyTaskAdapter();
			}
			@Override
			public Adapter caseTGlobalConversation(TGlobalConversation object) {
				return createTGlobalConversationAdapter();
			}
			@Override
			public Adapter caseTGlobalManualTask(TGlobalManualTask object) {
				return createTGlobalManualTaskAdapter();
			}
			@Override
			public Adapter caseTGlobalScriptTask(TGlobalScriptTask object) {
				return createTGlobalScriptTaskAdapter();
			}
			@Override
			public Adapter caseTGlobalTask(TGlobalTask object) {
				return createTGlobalTaskAdapter();
			}
			@Override
			public Adapter caseTGlobalUserTask(TGlobalUserTask object) {
				return createTGlobalUserTaskAdapter();
			}
			@Override
			public Adapter caseTGroup(TGroup object) {
				return createTGroupAdapter();
			}
			@Override
			public Adapter caseTHumanPerformer(THumanPerformer object) {
				return createTHumanPerformerAdapter();
			}
			@Override
			public Adapter caseTImplicitThrowEvent(TImplicitThrowEvent object) {
				return createTImplicitThrowEventAdapter();
			}
			@Override
			public Adapter caseTImport(TImport object) {
				return createTImportAdapter();
			}
			@Override
			public Adapter caseTInclusiveGateway(TInclusiveGateway object) {
				return createTInclusiveGatewayAdapter();
			}
			@Override
			public Adapter caseTInputOutputBinding(TInputOutputBinding object) {
				return createTInputOutputBindingAdapter();
			}
			@Override
			public Adapter caseTInputOutputSpecification(TInputOutputSpecification object) {
				return createTInputOutputSpecificationAdapter();
			}
			@Override
			public Adapter caseTInputSet(TInputSet object) {
				return createTInputSetAdapter();
			}
			@Override
			public Adapter caseTInterface(TInterface object) {
				return createTInterfaceAdapter();
			}
			@Override
			public Adapter caseTIntermediateCatchEvent(TIntermediateCatchEvent object) {
				return createTIntermediateCatchEventAdapter();
			}
			@Override
			public Adapter caseTIntermediateThrowEvent(TIntermediateThrowEvent object) {
				return createTIntermediateThrowEventAdapter();
			}
			@Override
			public Adapter caseTItemDefinition(TItemDefinition object) {
				return createTItemDefinitionAdapter();
			}
			@Override
			public Adapter caseTLane(TLane object) {
				return createTLaneAdapter();
			}
			@Override
			public Adapter caseTLaneSet(TLaneSet object) {
				return createTLaneSetAdapter();
			}
			@Override
			public Adapter caseTLinkEventDefinition(TLinkEventDefinition object) {
				return createTLinkEventDefinitionAdapter();
			}
			@Override
			public Adapter caseTLoopCharacteristics(TLoopCharacteristics object) {
				return createTLoopCharacteristicsAdapter();
			}
			@Override
			public Adapter caseTManualTask(TManualTask object) {
				return createTManualTaskAdapter();
			}
			@Override
			public Adapter caseTMessage(TMessage object) {
				return createTMessageAdapter();
			}
			@Override
			public Adapter caseTMessageEventDefinition(TMessageEventDefinition object) {
				return createTMessageEventDefinitionAdapter();
			}
			@Override
			public Adapter caseTMessageFlow(TMessageFlow object) {
				return createTMessageFlowAdapter();
			}
			@Override
			public Adapter caseTMessageFlowAssociation(TMessageFlowAssociation object) {
				return createTMessageFlowAssociationAdapter();
			}
			@Override
			public Adapter caseTMonitoring(TMonitoring object) {
				return createTMonitoringAdapter();
			}
			@Override
			public Adapter caseTMultiInstanceLoopCharacteristics(TMultiInstanceLoopCharacteristics object) {
				return createTMultiInstanceLoopCharacteristicsAdapter();
			}
			@Override
			public Adapter caseTOperation(TOperation object) {
				return createTOperationAdapter();
			}
			@Override
			public Adapter caseTOutputSet(TOutputSet object) {
				return createTOutputSetAdapter();
			}
			@Override
			public Adapter caseTParallelGateway(TParallelGateway object) {
				return createTParallelGatewayAdapter();
			}
			@Override
			public Adapter caseTParticipant(TParticipant object) {
				return createTParticipantAdapter();
			}
			@Override
			public Adapter caseTParticipantAssociation(TParticipantAssociation object) {
				return createTParticipantAssociationAdapter();
			}
			@Override
			public Adapter caseTParticipantMultiplicity(TParticipantMultiplicity object) {
				return createTParticipantMultiplicityAdapter();
			}
			@Override
			public Adapter caseTPartnerEntity(TPartnerEntity object) {
				return createTPartnerEntityAdapter();
			}
			@Override
			public Adapter caseTPartnerRole(TPartnerRole object) {
				return createTPartnerRoleAdapter();
			}
			@Override
			public Adapter caseTPerformer(TPerformer object) {
				return createTPerformerAdapter();
			}
			@Override
			public Adapter caseTPotentialOwner(TPotentialOwner object) {
				return createTPotentialOwnerAdapter();
			}
			@Override
			public Adapter caseTProcess(TProcess object) {
				return createTProcessAdapter();
			}
			@Override
			public Adapter caseTProperty(TProperty object) {
				return createTPropertyAdapter();
			}
			@Override
			public Adapter caseTReceiveTask(TReceiveTask object) {
				return createTReceiveTaskAdapter();
			}
			@Override
			public Adapter caseTRelationship(TRelationship object) {
				return createTRelationshipAdapter();
			}
			@Override
			public Adapter caseTRendering(TRendering object) {
				return createTRenderingAdapter();
			}
			@Override
			public Adapter caseTResource(TResource object) {
				return createTResourceAdapter();
			}
			@Override
			public Adapter caseTResourceAssignmentExpression(TResourceAssignmentExpression object) {
				return createTResourceAssignmentExpressionAdapter();
			}
			@Override
			public Adapter caseTResourceParameter(TResourceParameter object) {
				return createTResourceParameterAdapter();
			}
			@Override
			public Adapter caseTResourceParameterBinding(TResourceParameterBinding object) {
				return createTResourceParameterBindingAdapter();
			}
			@Override
			public Adapter caseTResourceRole(TResourceRole object) {
				return createTResourceRoleAdapter();
			}
			@Override
			public Adapter caseTRootElement(TRootElement object) {
				return createTRootElementAdapter();
			}
			@Override
			public Adapter caseTScript(TScript object) {
				return createTScriptAdapter();
			}
			@Override
			public Adapter caseTScriptTask(TScriptTask object) {
				return createTScriptTaskAdapter();
			}
			@Override
			public Adapter caseTSendTask(TSendTask object) {
				return createTSendTaskAdapter();
			}
			@Override
			public Adapter caseTSequenceFlow(TSequenceFlow object) {
				return createTSequenceFlowAdapter();
			}
			@Override
			public Adapter caseTServiceTask(TServiceTask object) {
				return createTServiceTaskAdapter();
			}
			@Override
			public Adapter caseTSignal(TSignal object) {
				return createTSignalAdapter();
			}
			@Override
			public Adapter caseTSignalEventDefinition(TSignalEventDefinition object) {
				return createTSignalEventDefinitionAdapter();
			}
			@Override
			public Adapter caseTStandardLoopCharacteristics(TStandardLoopCharacteristics object) {
				return createTStandardLoopCharacteristicsAdapter();
			}
			@Override
			public Adapter caseTStartEvent(TStartEvent object) {
				return createTStartEventAdapter();
			}
			@Override
			public Adapter caseTSubChoreography(TSubChoreography object) {
				return createTSubChoreographyAdapter();
			}
			@Override
			public Adapter caseTSubConversation(TSubConversation object) {
				return createTSubConversationAdapter();
			}
			@Override
			public Adapter caseTSubProcess(TSubProcess object) {
				return createTSubProcessAdapter();
			}
			@Override
			public Adapter caseTTask(TTask object) {
				return createTTaskAdapter();
			}
			@Override
			public Adapter caseTTerminateEventDefinition(TTerminateEventDefinition object) {
				return createTTerminateEventDefinitionAdapter();
			}
			@Override
			public Adapter caseTText(TText object) {
				return createTTextAdapter();
			}
			@Override
			public Adapter caseTTextAnnotation(TTextAnnotation object) {
				return createTTextAnnotationAdapter();
			}
			@Override
			public Adapter caseTThrowEvent(TThrowEvent object) {
				return createTThrowEventAdapter();
			}
			@Override
			public Adapter caseTTimerEventDefinition(TTimerEventDefinition object) {
				return createTTimerEventDefinitionAdapter();
			}
			@Override
			public Adapter caseTTransaction(TTransaction object) {
				return createTTransactionAdapter();
			}
			@Override
			public Adapter caseTUserTask(TUserTask object) {
				return createTUserTaskAdapter();
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
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.DocumentRoot
	 * @generated
	 */
	public Adapter createDocumentRootAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TActivity <em>TActivity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TActivity
	 * @generated
	 */
	public Adapter createTActivityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TAdHocSubProcess <em>TAd Hoc Sub Process</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TAdHocSubProcess
	 * @generated
	 */
	public Adapter createTAdHocSubProcessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TArtifact <em>TArtifact</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TArtifact
	 * @generated
	 */
	public Adapter createTArtifactAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TAssignment <em>TAssignment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TAssignment
	 * @generated
	 */
	public Adapter createTAssignmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TAssociation <em>TAssociation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TAssociation
	 * @generated
	 */
	public Adapter createTAssociationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TAuditing <em>TAuditing</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TAuditing
	 * @generated
	 */
	public Adapter createTAuditingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TBaseElement <em>TBase Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TBaseElement
	 * @generated
	 */
	public Adapter createTBaseElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TBaseElementWithMixedContent <em>TBase Element With Mixed Content</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TBaseElementWithMixedContent
	 * @generated
	 */
	public Adapter createTBaseElementWithMixedContentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TBoundaryEvent <em>TBoundary Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TBoundaryEvent
	 * @generated
	 */
	public Adapter createTBoundaryEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TBusinessRuleTask <em>TBusiness Rule Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TBusinessRuleTask
	 * @generated
	 */
	public Adapter createTBusinessRuleTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TCallableElement <em>TCallable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TCallableElement
	 * @generated
	 */
	public Adapter createTCallableElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TCallActivity <em>TCall Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TCallActivity
	 * @generated
	 */
	public Adapter createTCallActivityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TCallChoreography <em>TCall Choreography</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TCallChoreography
	 * @generated
	 */
	public Adapter createTCallChoreographyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TCallConversation <em>TCall Conversation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TCallConversation
	 * @generated
	 */
	public Adapter createTCallConversationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TCancelEventDefinition <em>TCancel Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TCancelEventDefinition
	 * @generated
	 */
	public Adapter createTCancelEventDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TCatchEvent <em>TCatch Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TCatchEvent
	 * @generated
	 */
	public Adapter createTCatchEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TCategory <em>TCategory</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TCategory
	 * @generated
	 */
	public Adapter createTCategoryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TCategoryValue <em>TCategory Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TCategoryValue
	 * @generated
	 */
	public Adapter createTCategoryValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TChoreography <em>TChoreography</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TChoreography
	 * @generated
	 */
	public Adapter createTChoreographyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TChoreographyActivity <em>TChoreography Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TChoreographyActivity
	 * @generated
	 */
	public Adapter createTChoreographyActivityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TChoreographyTask <em>TChoreography Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TChoreographyTask
	 * @generated
	 */
	public Adapter createTChoreographyTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TCollaboration <em>TCollaboration</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TCollaboration
	 * @generated
	 */
	public Adapter createTCollaborationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TCompensateEventDefinition <em>TCompensate Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TCompensateEventDefinition
	 * @generated
	 */
	public Adapter createTCompensateEventDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TComplexBehaviorDefinition <em>TComplex Behavior Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TComplexBehaviorDefinition
	 * @generated
	 */
	public Adapter createTComplexBehaviorDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TComplexGateway <em>TComplex Gateway</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TComplexGateway
	 * @generated
	 */
	public Adapter createTComplexGatewayAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TConditionalEventDefinition <em>TConditional Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TConditionalEventDefinition
	 * @generated
	 */
	public Adapter createTConditionalEventDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TConversation <em>TConversation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TConversation
	 * @generated
	 */
	public Adapter createTConversationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TConversationAssociation <em>TConversation Association</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TConversationAssociation
	 * @generated
	 */
	public Adapter createTConversationAssociationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TConversationLink <em>TConversation Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TConversationLink
	 * @generated
	 */
	public Adapter createTConversationLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TConversationNode <em>TConversation Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TConversationNode
	 * @generated
	 */
	public Adapter createTConversationNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TCorrelationKey <em>TCorrelation Key</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TCorrelationKey
	 * @generated
	 */
	public Adapter createTCorrelationKeyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TCorrelationProperty <em>TCorrelation Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TCorrelationProperty
	 * @generated
	 */
	public Adapter createTCorrelationPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TCorrelationPropertyBinding <em>TCorrelation Property Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TCorrelationPropertyBinding
	 * @generated
	 */
	public Adapter createTCorrelationPropertyBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TCorrelationPropertyRetrievalExpression <em>TCorrelation Property Retrieval Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TCorrelationPropertyRetrievalExpression
	 * @generated
	 */
	public Adapter createTCorrelationPropertyRetrievalExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TCorrelationSubscription <em>TCorrelation Subscription</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TCorrelationSubscription
	 * @generated
	 */
	public Adapter createTCorrelationSubscriptionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TDataAssociation <em>TData Association</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TDataAssociation
	 * @generated
	 */
	public Adapter createTDataAssociationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TDataInput <em>TData Input</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TDataInput
	 * @generated
	 */
	public Adapter createTDataInputAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TDataInputAssociation <em>TData Input Association</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TDataInputAssociation
	 * @generated
	 */
	public Adapter createTDataInputAssociationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TDataObject <em>TData Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TDataObject
	 * @generated
	 */
	public Adapter createTDataObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TDataObjectReference <em>TData Object Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TDataObjectReference
	 * @generated
	 */
	public Adapter createTDataObjectReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TDataOutput <em>TData Output</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TDataOutput
	 * @generated
	 */
	public Adapter createTDataOutputAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TDataOutputAssociation <em>TData Output Association</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TDataOutputAssociation
	 * @generated
	 */
	public Adapter createTDataOutputAssociationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TDataState <em>TData State</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TDataState
	 * @generated
	 */
	public Adapter createTDataStateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TDataStore <em>TData Store</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TDataStore
	 * @generated
	 */
	public Adapter createTDataStoreAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TDataStoreReference <em>TData Store Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TDataStoreReference
	 * @generated
	 */
	public Adapter createTDataStoreReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TDefinitions <em>TDefinitions</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TDefinitions
	 * @generated
	 */
	public Adapter createTDefinitionsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TDocumentation <em>TDocumentation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TDocumentation
	 * @generated
	 */
	public Adapter createTDocumentationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TEndEvent <em>TEnd Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TEndEvent
	 * @generated
	 */
	public Adapter createTEndEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TEndPoint <em>TEnd Point</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TEndPoint
	 * @generated
	 */
	public Adapter createTEndPointAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TError <em>TError</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TError
	 * @generated
	 */
	public Adapter createTErrorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TErrorEventDefinition <em>TError Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TErrorEventDefinition
	 * @generated
	 */
	public Adapter createTErrorEventDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TEscalation <em>TEscalation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TEscalation
	 * @generated
	 */
	public Adapter createTEscalationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TEscalationEventDefinition <em>TEscalation Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TEscalationEventDefinition
	 * @generated
	 */
	public Adapter createTEscalationEventDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TEvent <em>TEvent</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TEvent
	 * @generated
	 */
	public Adapter createTEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TEventBasedGateway <em>TEvent Based Gateway</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TEventBasedGateway
	 * @generated
	 */
	public Adapter createTEventBasedGatewayAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TEventDefinition <em>TEvent Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TEventDefinition
	 * @generated
	 */
	public Adapter createTEventDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TExclusiveGateway <em>TExclusive Gateway</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TExclusiveGateway
	 * @generated
	 */
	public Adapter createTExclusiveGatewayAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TExpression <em>TExpression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TExpression
	 * @generated
	 */
	public Adapter createTExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TExtension <em>TExtension</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TExtension
	 * @generated
	 */
	public Adapter createTExtensionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TExtensionElements <em>TExtension Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TExtensionElements
	 * @generated
	 */
	public Adapter createTExtensionElementsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TFlowElement <em>TFlow Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TFlowElement
	 * @generated
	 */
	public Adapter createTFlowElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TFlowNode <em>TFlow Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TFlowNode
	 * @generated
	 */
	public Adapter createTFlowNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TFormalExpression <em>TFormal Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TFormalExpression
	 * @generated
	 */
	public Adapter createTFormalExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TGateway <em>TGateway</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TGateway
	 * @generated
	 */
	public Adapter createTGatewayAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TGlobalBusinessRuleTask <em>TGlobal Business Rule Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TGlobalBusinessRuleTask
	 * @generated
	 */
	public Adapter createTGlobalBusinessRuleTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TGlobalChoreographyTask <em>TGlobal Choreography Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TGlobalChoreographyTask
	 * @generated
	 */
	public Adapter createTGlobalChoreographyTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TGlobalConversation <em>TGlobal Conversation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TGlobalConversation
	 * @generated
	 */
	public Adapter createTGlobalConversationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TGlobalManualTask <em>TGlobal Manual Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TGlobalManualTask
	 * @generated
	 */
	public Adapter createTGlobalManualTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TGlobalScriptTask <em>TGlobal Script Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TGlobalScriptTask
	 * @generated
	 */
	public Adapter createTGlobalScriptTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TGlobalTask <em>TGlobal Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TGlobalTask
	 * @generated
	 */
	public Adapter createTGlobalTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TGlobalUserTask <em>TGlobal User Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TGlobalUserTask
	 * @generated
	 */
	public Adapter createTGlobalUserTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TGroup <em>TGroup</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TGroup
	 * @generated
	 */
	public Adapter createTGroupAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.THumanPerformer <em>THuman Performer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.THumanPerformer
	 * @generated
	 */
	public Adapter createTHumanPerformerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TImplicitThrowEvent <em>TImplicit Throw Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TImplicitThrowEvent
	 * @generated
	 */
	public Adapter createTImplicitThrowEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TImport <em>TImport</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TImport
	 * @generated
	 */
	public Adapter createTImportAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TInclusiveGateway <em>TInclusive Gateway</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TInclusiveGateway
	 * @generated
	 */
	public Adapter createTInclusiveGatewayAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TInputOutputBinding <em>TInput Output Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TInputOutputBinding
	 * @generated
	 */
	public Adapter createTInputOutputBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TInputOutputSpecification <em>TInput Output Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TInputOutputSpecification
	 * @generated
	 */
	public Adapter createTInputOutputSpecificationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TInputSet <em>TInput Set</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TInputSet
	 * @generated
	 */
	public Adapter createTInputSetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TInterface <em>TInterface</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TInterface
	 * @generated
	 */
	public Adapter createTInterfaceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TIntermediateCatchEvent <em>TIntermediate Catch Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TIntermediateCatchEvent
	 * @generated
	 */
	public Adapter createTIntermediateCatchEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TIntermediateThrowEvent <em>TIntermediate Throw Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TIntermediateThrowEvent
	 * @generated
	 */
	public Adapter createTIntermediateThrowEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TItemDefinition <em>TItem Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TItemDefinition
	 * @generated
	 */
	public Adapter createTItemDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TLane <em>TLane</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TLane
	 * @generated
	 */
	public Adapter createTLaneAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TLaneSet <em>TLane Set</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TLaneSet
	 * @generated
	 */
	public Adapter createTLaneSetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TLinkEventDefinition <em>TLink Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TLinkEventDefinition
	 * @generated
	 */
	public Adapter createTLinkEventDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TLoopCharacteristics <em>TLoop Characteristics</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TLoopCharacteristics
	 * @generated
	 */
	public Adapter createTLoopCharacteristicsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TManualTask <em>TManual Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TManualTask
	 * @generated
	 */
	public Adapter createTManualTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TMessage <em>TMessage</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TMessage
	 * @generated
	 */
	public Adapter createTMessageAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TMessageEventDefinition <em>TMessage Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TMessageEventDefinition
	 * @generated
	 */
	public Adapter createTMessageEventDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TMessageFlow <em>TMessage Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TMessageFlow
	 * @generated
	 */
	public Adapter createTMessageFlowAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TMessageFlowAssociation <em>TMessage Flow Association</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TMessageFlowAssociation
	 * @generated
	 */
	public Adapter createTMessageFlowAssociationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TMonitoring <em>TMonitoring</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TMonitoring
	 * @generated
	 */
	public Adapter createTMonitoringAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics <em>TMulti Instance Loop Characteristics</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics
	 * @generated
	 */
	public Adapter createTMultiInstanceLoopCharacteristicsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TOperation <em>TOperation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TOperation
	 * @generated
	 */
	public Adapter createTOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TOutputSet <em>TOutput Set</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TOutputSet
	 * @generated
	 */
	public Adapter createTOutputSetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TParallelGateway <em>TParallel Gateway</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TParallelGateway
	 * @generated
	 */
	public Adapter createTParallelGatewayAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TParticipant <em>TParticipant</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TParticipant
	 * @generated
	 */
	public Adapter createTParticipantAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TParticipantAssociation <em>TParticipant Association</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TParticipantAssociation
	 * @generated
	 */
	public Adapter createTParticipantAssociationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TParticipantMultiplicity <em>TParticipant Multiplicity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TParticipantMultiplicity
	 * @generated
	 */
	public Adapter createTParticipantMultiplicityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TPartnerEntity <em>TPartner Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TPartnerEntity
	 * @generated
	 */
	public Adapter createTPartnerEntityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TPartnerRole <em>TPartner Role</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TPartnerRole
	 * @generated
	 */
	public Adapter createTPartnerRoleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TPerformer <em>TPerformer</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TPerformer
	 * @generated
	 */
	public Adapter createTPerformerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TPotentialOwner <em>TPotential Owner</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TPotentialOwner
	 * @generated
	 */
	public Adapter createTPotentialOwnerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TProcess <em>TProcess</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TProcess
	 * @generated
	 */
	public Adapter createTProcessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TProperty <em>TProperty</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TProperty
	 * @generated
	 */
	public Adapter createTPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TReceiveTask <em>TReceive Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TReceiveTask
	 * @generated
	 */
	public Adapter createTReceiveTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TRelationship <em>TRelationship</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TRelationship
	 * @generated
	 */
	public Adapter createTRelationshipAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TRendering <em>TRendering</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TRendering
	 * @generated
	 */
	public Adapter createTRenderingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TResource <em>TResource</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TResource
	 * @generated
	 */
	public Adapter createTResourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TResourceAssignmentExpression <em>TResource Assignment Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TResourceAssignmentExpression
	 * @generated
	 */
	public Adapter createTResourceAssignmentExpressionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TResourceParameter <em>TResource Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TResourceParameter
	 * @generated
	 */
	public Adapter createTResourceParameterAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TResourceParameterBinding <em>TResource Parameter Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TResourceParameterBinding
	 * @generated
	 */
	public Adapter createTResourceParameterBindingAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TResourceRole <em>TResource Role</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TResourceRole
	 * @generated
	 */
	public Adapter createTResourceRoleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TRootElement <em>TRoot Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TRootElement
	 * @generated
	 */
	public Adapter createTRootElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TScript <em>TScript</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TScript
	 * @generated
	 */
	public Adapter createTScriptAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TScriptTask <em>TScript Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TScriptTask
	 * @generated
	 */
	public Adapter createTScriptTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TSendTask <em>TSend Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TSendTask
	 * @generated
	 */
	public Adapter createTSendTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TSequenceFlow <em>TSequence Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TSequenceFlow
	 * @generated
	 */
	public Adapter createTSequenceFlowAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TServiceTask <em>TService Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TServiceTask
	 * @generated
	 */
	public Adapter createTServiceTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TSignal <em>TSignal</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TSignal
	 * @generated
	 */
	public Adapter createTSignalAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TSignalEventDefinition <em>TSignal Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TSignalEventDefinition
	 * @generated
	 */
	public Adapter createTSignalEventDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TStandardLoopCharacteristics <em>TStandard Loop Characteristics</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TStandardLoopCharacteristics
	 * @generated
	 */
	public Adapter createTStandardLoopCharacteristicsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TStartEvent <em>TStart Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TStartEvent
	 * @generated
	 */
	public Adapter createTStartEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TSubChoreography <em>TSub Choreography</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TSubChoreography
	 * @generated
	 */
	public Adapter createTSubChoreographyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TSubConversation <em>TSub Conversation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TSubConversation
	 * @generated
	 */
	public Adapter createTSubConversationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TSubProcess <em>TSub Process</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TSubProcess
	 * @generated
	 */
	public Adapter createTSubProcessAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TTask <em>TTask</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TTask
	 * @generated
	 */
	public Adapter createTTaskAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TTerminateEventDefinition <em>TTerminate Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TTerminateEventDefinition
	 * @generated
	 */
	public Adapter createTTerminateEventDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TText <em>TText</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TText
	 * @generated
	 */
	public Adapter createTTextAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TTextAnnotation <em>TText Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TTextAnnotation
	 * @generated
	 */
	public Adapter createTTextAnnotationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TThrowEvent <em>TThrow Event</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TThrowEvent
	 * @generated
	 */
	public Adapter createTThrowEventAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TTimerEventDefinition <em>TTimer Event Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TTimerEventDefinition
	 * @generated
	 */
	public Adapter createTTimerEventDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TTransaction <em>TTransaction</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TTransaction
	 * @generated
	 */
	public Adapter createTTransactionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.omg.spec.bpmn.model.TUserTask <em>TUser Task</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.omg.spec.bpmn.model.TUserTask
	 * @generated
	 */
	public Adapter createTUserTaskAdapter() {
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

} //ModelAdapterFactory
