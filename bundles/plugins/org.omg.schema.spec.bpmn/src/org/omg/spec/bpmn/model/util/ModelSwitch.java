/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.omg.spec.bpmn.model.*;

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
 * @see org.omg.spec.bpmn.model.ModelPackage
 * @generated
 */
public class ModelSwitch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ModelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelSwitch() {
		if (modelPackage == null) {
			modelPackage = ModelPackage.eINSTANCE;
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	public T doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List<EClass> eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch(eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ModelPackage.DOCUMENT_ROOT: {
				DocumentRoot documentRoot = (DocumentRoot)theEObject;
				T result = caseDocumentRoot(documentRoot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TACTIVITY: {
				TActivity tActivity = (TActivity)theEObject;
				T result = caseTActivity(tActivity);
				if (result == null) result = caseTFlowNode(tActivity);
				if (result == null) result = caseTFlowElement(tActivity);
				if (result == null) result = caseTBaseElement(tActivity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TAD_HOC_SUB_PROCESS: {
				TAdHocSubProcess tAdHocSubProcess = (TAdHocSubProcess)theEObject;
				T result = caseTAdHocSubProcess(tAdHocSubProcess);
				if (result == null) result = caseTSubProcess(tAdHocSubProcess);
				if (result == null) result = caseTActivity(tAdHocSubProcess);
				if (result == null) result = caseTFlowNode(tAdHocSubProcess);
				if (result == null) result = caseTFlowElement(tAdHocSubProcess);
				if (result == null) result = caseTBaseElement(tAdHocSubProcess);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TARTIFACT: {
				TArtifact tArtifact = (TArtifact)theEObject;
				T result = caseTArtifact(tArtifact);
				if (result == null) result = caseTBaseElement(tArtifact);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TASSIGNMENT: {
				TAssignment tAssignment = (TAssignment)theEObject;
				T result = caseTAssignment(tAssignment);
				if (result == null) result = caseTBaseElement(tAssignment);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TASSOCIATION: {
				TAssociation tAssociation = (TAssociation)theEObject;
				T result = caseTAssociation(tAssociation);
				if (result == null) result = caseTArtifact(tAssociation);
				if (result == null) result = caseTBaseElement(tAssociation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TAUDITING: {
				TAuditing tAuditing = (TAuditing)theEObject;
				T result = caseTAuditing(tAuditing);
				if (result == null) result = caseTBaseElement(tAuditing);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TBASE_ELEMENT: {
				TBaseElement tBaseElement = (TBaseElement)theEObject;
				T result = caseTBaseElement(tBaseElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TBASE_ELEMENT_WITH_MIXED_CONTENT: {
				TBaseElementWithMixedContent tBaseElementWithMixedContent = (TBaseElementWithMixedContent)theEObject;
				T result = caseTBaseElementWithMixedContent(tBaseElementWithMixedContent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TBOUNDARY_EVENT: {
				TBoundaryEvent tBoundaryEvent = (TBoundaryEvent)theEObject;
				T result = caseTBoundaryEvent(tBoundaryEvent);
				if (result == null) result = caseTCatchEvent(tBoundaryEvent);
				if (result == null) result = caseTEvent(tBoundaryEvent);
				if (result == null) result = caseTFlowNode(tBoundaryEvent);
				if (result == null) result = caseTFlowElement(tBoundaryEvent);
				if (result == null) result = caseTBaseElement(tBoundaryEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TBUSINESS_RULE_TASK: {
				TBusinessRuleTask tBusinessRuleTask = (TBusinessRuleTask)theEObject;
				T result = caseTBusinessRuleTask(tBusinessRuleTask);
				if (result == null) result = caseTTask(tBusinessRuleTask);
				if (result == null) result = caseTActivity(tBusinessRuleTask);
				if (result == null) result = caseTFlowNode(tBusinessRuleTask);
				if (result == null) result = caseTFlowElement(tBusinessRuleTask);
				if (result == null) result = caseTBaseElement(tBusinessRuleTask);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCALLABLE_ELEMENT: {
				TCallableElement tCallableElement = (TCallableElement)theEObject;
				T result = caseTCallableElement(tCallableElement);
				if (result == null) result = caseTRootElement(tCallableElement);
				if (result == null) result = caseTBaseElement(tCallableElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCALL_ACTIVITY: {
				TCallActivity tCallActivity = (TCallActivity)theEObject;
				T result = caseTCallActivity(tCallActivity);
				if (result == null) result = caseTActivity(tCallActivity);
				if (result == null) result = caseTFlowNode(tCallActivity);
				if (result == null) result = caseTFlowElement(tCallActivity);
				if (result == null) result = caseTBaseElement(tCallActivity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCALL_CHOREOGRAPHY: {
				TCallChoreography tCallChoreography = (TCallChoreography)theEObject;
				T result = caseTCallChoreography(tCallChoreography);
				if (result == null) result = caseTChoreographyActivity(tCallChoreography);
				if (result == null) result = caseTFlowNode(tCallChoreography);
				if (result == null) result = caseTFlowElement(tCallChoreography);
				if (result == null) result = caseTBaseElement(tCallChoreography);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCALL_CONVERSATION: {
				TCallConversation tCallConversation = (TCallConversation)theEObject;
				T result = caseTCallConversation(tCallConversation);
				if (result == null) result = caseTConversationNode(tCallConversation);
				if (result == null) result = caseTBaseElement(tCallConversation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCANCEL_EVENT_DEFINITION: {
				TCancelEventDefinition tCancelEventDefinition = (TCancelEventDefinition)theEObject;
				T result = caseTCancelEventDefinition(tCancelEventDefinition);
				if (result == null) result = caseTEventDefinition(tCancelEventDefinition);
				if (result == null) result = caseTRootElement(tCancelEventDefinition);
				if (result == null) result = caseTBaseElement(tCancelEventDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCATCH_EVENT: {
				TCatchEvent tCatchEvent = (TCatchEvent)theEObject;
				T result = caseTCatchEvent(tCatchEvent);
				if (result == null) result = caseTEvent(tCatchEvent);
				if (result == null) result = caseTFlowNode(tCatchEvent);
				if (result == null) result = caseTFlowElement(tCatchEvent);
				if (result == null) result = caseTBaseElement(tCatchEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCATEGORY: {
				TCategory tCategory = (TCategory)theEObject;
				T result = caseTCategory(tCategory);
				if (result == null) result = caseTRootElement(tCategory);
				if (result == null) result = caseTBaseElement(tCategory);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCATEGORY_VALUE: {
				TCategoryValue tCategoryValue = (TCategoryValue)theEObject;
				T result = caseTCategoryValue(tCategoryValue);
				if (result == null) result = caseTBaseElement(tCategoryValue);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCHOREOGRAPHY: {
				TChoreography tChoreography = (TChoreography)theEObject;
				T result = caseTChoreography(tChoreography);
				if (result == null) result = caseTCollaboration(tChoreography);
				if (result == null) result = caseTRootElement(tChoreography);
				if (result == null) result = caseTBaseElement(tChoreography);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCHOREOGRAPHY_ACTIVITY: {
				TChoreographyActivity tChoreographyActivity = (TChoreographyActivity)theEObject;
				T result = caseTChoreographyActivity(tChoreographyActivity);
				if (result == null) result = caseTFlowNode(tChoreographyActivity);
				if (result == null) result = caseTFlowElement(tChoreographyActivity);
				if (result == null) result = caseTBaseElement(tChoreographyActivity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCHOREOGRAPHY_TASK: {
				TChoreographyTask tChoreographyTask = (TChoreographyTask)theEObject;
				T result = caseTChoreographyTask(tChoreographyTask);
				if (result == null) result = caseTChoreographyActivity(tChoreographyTask);
				if (result == null) result = caseTFlowNode(tChoreographyTask);
				if (result == null) result = caseTFlowElement(tChoreographyTask);
				if (result == null) result = caseTBaseElement(tChoreographyTask);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCOLLABORATION: {
				TCollaboration tCollaboration = (TCollaboration)theEObject;
				T result = caseTCollaboration(tCollaboration);
				if (result == null) result = caseTRootElement(tCollaboration);
				if (result == null) result = caseTBaseElement(tCollaboration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCOMPENSATE_EVENT_DEFINITION: {
				TCompensateEventDefinition tCompensateEventDefinition = (TCompensateEventDefinition)theEObject;
				T result = caseTCompensateEventDefinition(tCompensateEventDefinition);
				if (result == null) result = caseTEventDefinition(tCompensateEventDefinition);
				if (result == null) result = caseTRootElement(tCompensateEventDefinition);
				if (result == null) result = caseTBaseElement(tCompensateEventDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCOMPLEX_BEHAVIOR_DEFINITION: {
				TComplexBehaviorDefinition tComplexBehaviorDefinition = (TComplexBehaviorDefinition)theEObject;
				T result = caseTComplexBehaviorDefinition(tComplexBehaviorDefinition);
				if (result == null) result = caseTBaseElement(tComplexBehaviorDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCOMPLEX_GATEWAY: {
				TComplexGateway tComplexGateway = (TComplexGateway)theEObject;
				T result = caseTComplexGateway(tComplexGateway);
				if (result == null) result = caseTGateway(tComplexGateway);
				if (result == null) result = caseTFlowNode(tComplexGateway);
				if (result == null) result = caseTFlowElement(tComplexGateway);
				if (result == null) result = caseTBaseElement(tComplexGateway);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCONDITIONAL_EVENT_DEFINITION: {
				TConditionalEventDefinition tConditionalEventDefinition = (TConditionalEventDefinition)theEObject;
				T result = caseTConditionalEventDefinition(tConditionalEventDefinition);
				if (result == null) result = caseTEventDefinition(tConditionalEventDefinition);
				if (result == null) result = caseTRootElement(tConditionalEventDefinition);
				if (result == null) result = caseTBaseElement(tConditionalEventDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCONVERSATION: {
				TConversation tConversation = (TConversation)theEObject;
				T result = caseTConversation(tConversation);
				if (result == null) result = caseTConversationNode(tConversation);
				if (result == null) result = caseTBaseElement(tConversation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCONVERSATION_ASSOCIATION: {
				TConversationAssociation tConversationAssociation = (TConversationAssociation)theEObject;
				T result = caseTConversationAssociation(tConversationAssociation);
				if (result == null) result = caseTBaseElement(tConversationAssociation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCONVERSATION_LINK: {
				TConversationLink tConversationLink = (TConversationLink)theEObject;
				T result = caseTConversationLink(tConversationLink);
				if (result == null) result = caseTBaseElement(tConversationLink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCONVERSATION_NODE: {
				TConversationNode tConversationNode = (TConversationNode)theEObject;
				T result = caseTConversationNode(tConversationNode);
				if (result == null) result = caseTBaseElement(tConversationNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCORRELATION_KEY: {
				TCorrelationKey tCorrelationKey = (TCorrelationKey)theEObject;
				T result = caseTCorrelationKey(tCorrelationKey);
				if (result == null) result = caseTBaseElement(tCorrelationKey);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCORRELATION_PROPERTY: {
				TCorrelationProperty tCorrelationProperty = (TCorrelationProperty)theEObject;
				T result = caseTCorrelationProperty(tCorrelationProperty);
				if (result == null) result = caseTRootElement(tCorrelationProperty);
				if (result == null) result = caseTBaseElement(tCorrelationProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCORRELATION_PROPERTY_BINDING: {
				TCorrelationPropertyBinding tCorrelationPropertyBinding = (TCorrelationPropertyBinding)theEObject;
				T result = caseTCorrelationPropertyBinding(tCorrelationPropertyBinding);
				if (result == null) result = caseTBaseElement(tCorrelationPropertyBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCORRELATION_PROPERTY_RETRIEVAL_EXPRESSION: {
				TCorrelationPropertyRetrievalExpression tCorrelationPropertyRetrievalExpression = (TCorrelationPropertyRetrievalExpression)theEObject;
				T result = caseTCorrelationPropertyRetrievalExpression(tCorrelationPropertyRetrievalExpression);
				if (result == null) result = caseTBaseElement(tCorrelationPropertyRetrievalExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TCORRELATION_SUBSCRIPTION: {
				TCorrelationSubscription tCorrelationSubscription = (TCorrelationSubscription)theEObject;
				T result = caseTCorrelationSubscription(tCorrelationSubscription);
				if (result == null) result = caseTBaseElement(tCorrelationSubscription);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TDATA_ASSOCIATION: {
				TDataAssociation tDataAssociation = (TDataAssociation)theEObject;
				T result = caseTDataAssociation(tDataAssociation);
				if (result == null) result = caseTBaseElement(tDataAssociation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TDATA_INPUT: {
				TDataInput tDataInput = (TDataInput)theEObject;
				T result = caseTDataInput(tDataInput);
				if (result == null) result = caseTBaseElement(tDataInput);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TDATA_INPUT_ASSOCIATION: {
				TDataInputAssociation tDataInputAssociation = (TDataInputAssociation)theEObject;
				T result = caseTDataInputAssociation(tDataInputAssociation);
				if (result == null) result = caseTDataAssociation(tDataInputAssociation);
				if (result == null) result = caseTBaseElement(tDataInputAssociation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TDATA_OBJECT: {
				TDataObject tDataObject = (TDataObject)theEObject;
				T result = caseTDataObject(tDataObject);
				if (result == null) result = caseTFlowElement(tDataObject);
				if (result == null) result = caseTBaseElement(tDataObject);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TDATA_OBJECT_REFERENCE: {
				TDataObjectReference tDataObjectReference = (TDataObjectReference)theEObject;
				T result = caseTDataObjectReference(tDataObjectReference);
				if (result == null) result = caseTFlowElement(tDataObjectReference);
				if (result == null) result = caseTBaseElement(tDataObjectReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TDATA_OUTPUT: {
				TDataOutput tDataOutput = (TDataOutput)theEObject;
				T result = caseTDataOutput(tDataOutput);
				if (result == null) result = caseTBaseElement(tDataOutput);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TDATA_OUTPUT_ASSOCIATION: {
				TDataOutputAssociation tDataOutputAssociation = (TDataOutputAssociation)theEObject;
				T result = caseTDataOutputAssociation(tDataOutputAssociation);
				if (result == null) result = caseTDataAssociation(tDataOutputAssociation);
				if (result == null) result = caseTBaseElement(tDataOutputAssociation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TDATA_STATE: {
				TDataState tDataState = (TDataState)theEObject;
				T result = caseTDataState(tDataState);
				if (result == null) result = caseTBaseElement(tDataState);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TDATA_STORE: {
				TDataStore tDataStore = (TDataStore)theEObject;
				T result = caseTDataStore(tDataStore);
				if (result == null) result = caseTRootElement(tDataStore);
				if (result == null) result = caseTBaseElement(tDataStore);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TDATA_STORE_REFERENCE: {
				TDataStoreReference tDataStoreReference = (TDataStoreReference)theEObject;
				T result = caseTDataStoreReference(tDataStoreReference);
				if (result == null) result = caseTFlowElement(tDataStoreReference);
				if (result == null) result = caseTBaseElement(tDataStoreReference);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TDEFINITIONS: {
				TDefinitions tDefinitions = (TDefinitions)theEObject;
				T result = caseTDefinitions(tDefinitions);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TDOCUMENTATION: {
				TDocumentation tDocumentation = (TDocumentation)theEObject;
				T result = caseTDocumentation(tDocumentation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TEND_EVENT: {
				TEndEvent tEndEvent = (TEndEvent)theEObject;
				T result = caseTEndEvent(tEndEvent);
				if (result == null) result = caseTThrowEvent(tEndEvent);
				if (result == null) result = caseTEvent(tEndEvent);
				if (result == null) result = caseTFlowNode(tEndEvent);
				if (result == null) result = caseTFlowElement(tEndEvent);
				if (result == null) result = caseTBaseElement(tEndEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TEND_POINT: {
				TEndPoint tEndPoint = (TEndPoint)theEObject;
				T result = caseTEndPoint(tEndPoint);
				if (result == null) result = caseTRootElement(tEndPoint);
				if (result == null) result = caseTBaseElement(tEndPoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TERROR: {
				TError tError = (TError)theEObject;
				T result = caseTError(tError);
				if (result == null) result = caseTRootElement(tError);
				if (result == null) result = caseTBaseElement(tError);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TERROR_EVENT_DEFINITION: {
				TErrorEventDefinition tErrorEventDefinition = (TErrorEventDefinition)theEObject;
				T result = caseTErrorEventDefinition(tErrorEventDefinition);
				if (result == null) result = caseTEventDefinition(tErrorEventDefinition);
				if (result == null) result = caseTRootElement(tErrorEventDefinition);
				if (result == null) result = caseTBaseElement(tErrorEventDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TESCALATION: {
				TEscalation tEscalation = (TEscalation)theEObject;
				T result = caseTEscalation(tEscalation);
				if (result == null) result = caseTRootElement(tEscalation);
				if (result == null) result = caseTBaseElement(tEscalation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TESCALATION_EVENT_DEFINITION: {
				TEscalationEventDefinition tEscalationEventDefinition = (TEscalationEventDefinition)theEObject;
				T result = caseTEscalationEventDefinition(tEscalationEventDefinition);
				if (result == null) result = caseTEventDefinition(tEscalationEventDefinition);
				if (result == null) result = caseTRootElement(tEscalationEventDefinition);
				if (result == null) result = caseTBaseElement(tEscalationEventDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TEVENT: {
				TEvent tEvent = (TEvent)theEObject;
				T result = caseTEvent(tEvent);
				if (result == null) result = caseTFlowNode(tEvent);
				if (result == null) result = caseTFlowElement(tEvent);
				if (result == null) result = caseTBaseElement(tEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TEVENT_BASED_GATEWAY: {
				TEventBasedGateway tEventBasedGateway = (TEventBasedGateway)theEObject;
				T result = caseTEventBasedGateway(tEventBasedGateway);
				if (result == null) result = caseTGateway(tEventBasedGateway);
				if (result == null) result = caseTFlowNode(tEventBasedGateway);
				if (result == null) result = caseTFlowElement(tEventBasedGateway);
				if (result == null) result = caseTBaseElement(tEventBasedGateway);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TEVENT_DEFINITION: {
				TEventDefinition tEventDefinition = (TEventDefinition)theEObject;
				T result = caseTEventDefinition(tEventDefinition);
				if (result == null) result = caseTRootElement(tEventDefinition);
				if (result == null) result = caseTBaseElement(tEventDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TEXCLUSIVE_GATEWAY: {
				TExclusiveGateway tExclusiveGateway = (TExclusiveGateway)theEObject;
				T result = caseTExclusiveGateway(tExclusiveGateway);
				if (result == null) result = caseTGateway(tExclusiveGateway);
				if (result == null) result = caseTFlowNode(tExclusiveGateway);
				if (result == null) result = caseTFlowElement(tExclusiveGateway);
				if (result == null) result = caseTBaseElement(tExclusiveGateway);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TEXPRESSION: {
				TExpression tExpression = (TExpression)theEObject;
				T result = caseTExpression(tExpression);
				if (result == null) result = caseTBaseElementWithMixedContent(tExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TEXTENSION: {
				TExtension tExtension = (TExtension)theEObject;
				T result = caseTExtension(tExtension);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TEXTENSION_ELEMENTS: {
				TExtensionElements tExtensionElements = (TExtensionElements)theEObject;
				T result = caseTExtensionElements(tExtensionElements);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TFLOW_ELEMENT: {
				TFlowElement tFlowElement = (TFlowElement)theEObject;
				T result = caseTFlowElement(tFlowElement);
				if (result == null) result = caseTBaseElement(tFlowElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TFLOW_NODE: {
				TFlowNode tFlowNode = (TFlowNode)theEObject;
				T result = caseTFlowNode(tFlowNode);
				if (result == null) result = caseTFlowElement(tFlowNode);
				if (result == null) result = caseTBaseElement(tFlowNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TFORMAL_EXPRESSION: {
				TFormalExpression tFormalExpression = (TFormalExpression)theEObject;
				T result = caseTFormalExpression(tFormalExpression);
				if (result == null) result = caseTExpression(tFormalExpression);
				if (result == null) result = caseTBaseElementWithMixedContent(tFormalExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TGATEWAY: {
				TGateway tGateway = (TGateway)theEObject;
				T result = caseTGateway(tGateway);
				if (result == null) result = caseTFlowNode(tGateway);
				if (result == null) result = caseTFlowElement(tGateway);
				if (result == null) result = caseTBaseElement(tGateway);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TGLOBAL_BUSINESS_RULE_TASK: {
				TGlobalBusinessRuleTask tGlobalBusinessRuleTask = (TGlobalBusinessRuleTask)theEObject;
				T result = caseTGlobalBusinessRuleTask(tGlobalBusinessRuleTask);
				if (result == null) result = caseTGlobalTask(tGlobalBusinessRuleTask);
				if (result == null) result = caseTCallableElement(tGlobalBusinessRuleTask);
				if (result == null) result = caseTRootElement(tGlobalBusinessRuleTask);
				if (result == null) result = caseTBaseElement(tGlobalBusinessRuleTask);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TGLOBAL_CHOREOGRAPHY_TASK: {
				TGlobalChoreographyTask tGlobalChoreographyTask = (TGlobalChoreographyTask)theEObject;
				T result = caseTGlobalChoreographyTask(tGlobalChoreographyTask);
				if (result == null) result = caseTChoreography(tGlobalChoreographyTask);
				if (result == null) result = caseTCollaboration(tGlobalChoreographyTask);
				if (result == null) result = caseTRootElement(tGlobalChoreographyTask);
				if (result == null) result = caseTBaseElement(tGlobalChoreographyTask);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TGLOBAL_CONVERSATION: {
				TGlobalConversation tGlobalConversation = (TGlobalConversation)theEObject;
				T result = caseTGlobalConversation(tGlobalConversation);
				if (result == null) result = caseTCollaboration(tGlobalConversation);
				if (result == null) result = caseTRootElement(tGlobalConversation);
				if (result == null) result = caseTBaseElement(tGlobalConversation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TGLOBAL_MANUAL_TASK: {
				TGlobalManualTask tGlobalManualTask = (TGlobalManualTask)theEObject;
				T result = caseTGlobalManualTask(tGlobalManualTask);
				if (result == null) result = caseTGlobalTask(tGlobalManualTask);
				if (result == null) result = caseTCallableElement(tGlobalManualTask);
				if (result == null) result = caseTRootElement(tGlobalManualTask);
				if (result == null) result = caseTBaseElement(tGlobalManualTask);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TGLOBAL_SCRIPT_TASK: {
				TGlobalScriptTask tGlobalScriptTask = (TGlobalScriptTask)theEObject;
				T result = caseTGlobalScriptTask(tGlobalScriptTask);
				if (result == null) result = caseTGlobalTask(tGlobalScriptTask);
				if (result == null) result = caseTCallableElement(tGlobalScriptTask);
				if (result == null) result = caseTRootElement(tGlobalScriptTask);
				if (result == null) result = caseTBaseElement(tGlobalScriptTask);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TGLOBAL_TASK: {
				TGlobalTask tGlobalTask = (TGlobalTask)theEObject;
				T result = caseTGlobalTask(tGlobalTask);
				if (result == null) result = caseTCallableElement(tGlobalTask);
				if (result == null) result = caseTRootElement(tGlobalTask);
				if (result == null) result = caseTBaseElement(tGlobalTask);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TGLOBAL_USER_TASK: {
				TGlobalUserTask tGlobalUserTask = (TGlobalUserTask)theEObject;
				T result = caseTGlobalUserTask(tGlobalUserTask);
				if (result == null) result = caseTGlobalTask(tGlobalUserTask);
				if (result == null) result = caseTCallableElement(tGlobalUserTask);
				if (result == null) result = caseTRootElement(tGlobalUserTask);
				if (result == null) result = caseTBaseElement(tGlobalUserTask);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TGROUP: {
				TGroup tGroup = (TGroup)theEObject;
				T result = caseTGroup(tGroup);
				if (result == null) result = caseTArtifact(tGroup);
				if (result == null) result = caseTBaseElement(tGroup);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.THUMAN_PERFORMER: {
				THumanPerformer tHumanPerformer = (THumanPerformer)theEObject;
				T result = caseTHumanPerformer(tHumanPerformer);
				if (result == null) result = caseTPerformer(tHumanPerformer);
				if (result == null) result = caseTResourceRole(tHumanPerformer);
				if (result == null) result = caseTBaseElement(tHumanPerformer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TIMPLICIT_THROW_EVENT: {
				TImplicitThrowEvent tImplicitThrowEvent = (TImplicitThrowEvent)theEObject;
				T result = caseTImplicitThrowEvent(tImplicitThrowEvent);
				if (result == null) result = caseTThrowEvent(tImplicitThrowEvent);
				if (result == null) result = caseTEvent(tImplicitThrowEvent);
				if (result == null) result = caseTFlowNode(tImplicitThrowEvent);
				if (result == null) result = caseTFlowElement(tImplicitThrowEvent);
				if (result == null) result = caseTBaseElement(tImplicitThrowEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TIMPORT: {
				TImport tImport = (TImport)theEObject;
				T result = caseTImport(tImport);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TINCLUSIVE_GATEWAY: {
				TInclusiveGateway tInclusiveGateway = (TInclusiveGateway)theEObject;
				T result = caseTInclusiveGateway(tInclusiveGateway);
				if (result == null) result = caseTGateway(tInclusiveGateway);
				if (result == null) result = caseTFlowNode(tInclusiveGateway);
				if (result == null) result = caseTFlowElement(tInclusiveGateway);
				if (result == null) result = caseTBaseElement(tInclusiveGateway);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TINPUT_OUTPUT_BINDING: {
				TInputOutputBinding tInputOutputBinding = (TInputOutputBinding)theEObject;
				T result = caseTInputOutputBinding(tInputOutputBinding);
				if (result == null) result = caseTBaseElement(tInputOutputBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TINPUT_OUTPUT_SPECIFICATION: {
				TInputOutputSpecification tInputOutputSpecification = (TInputOutputSpecification)theEObject;
				T result = caseTInputOutputSpecification(tInputOutputSpecification);
				if (result == null) result = caseTBaseElement(tInputOutputSpecification);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TINPUT_SET: {
				TInputSet tInputSet = (TInputSet)theEObject;
				T result = caseTInputSet(tInputSet);
				if (result == null) result = caseTBaseElement(tInputSet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TINTERFACE: {
				TInterface tInterface = (TInterface)theEObject;
				T result = caseTInterface(tInterface);
				if (result == null) result = caseTRootElement(tInterface);
				if (result == null) result = caseTBaseElement(tInterface);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TINTERMEDIATE_CATCH_EVENT: {
				TIntermediateCatchEvent tIntermediateCatchEvent = (TIntermediateCatchEvent)theEObject;
				T result = caseTIntermediateCatchEvent(tIntermediateCatchEvent);
				if (result == null) result = caseTCatchEvent(tIntermediateCatchEvent);
				if (result == null) result = caseTEvent(tIntermediateCatchEvent);
				if (result == null) result = caseTFlowNode(tIntermediateCatchEvent);
				if (result == null) result = caseTFlowElement(tIntermediateCatchEvent);
				if (result == null) result = caseTBaseElement(tIntermediateCatchEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TINTERMEDIATE_THROW_EVENT: {
				TIntermediateThrowEvent tIntermediateThrowEvent = (TIntermediateThrowEvent)theEObject;
				T result = caseTIntermediateThrowEvent(tIntermediateThrowEvent);
				if (result == null) result = caseTThrowEvent(tIntermediateThrowEvent);
				if (result == null) result = caseTEvent(tIntermediateThrowEvent);
				if (result == null) result = caseTFlowNode(tIntermediateThrowEvent);
				if (result == null) result = caseTFlowElement(tIntermediateThrowEvent);
				if (result == null) result = caseTBaseElement(tIntermediateThrowEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TITEM_DEFINITION: {
				TItemDefinition tItemDefinition = (TItemDefinition)theEObject;
				T result = caseTItemDefinition(tItemDefinition);
				if (result == null) result = caseTRootElement(tItemDefinition);
				if (result == null) result = caseTBaseElement(tItemDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TLANE: {
				TLane tLane = (TLane)theEObject;
				T result = caseTLane(tLane);
				if (result == null) result = caseTBaseElement(tLane);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TLANE_SET: {
				TLaneSet tLaneSet = (TLaneSet)theEObject;
				T result = caseTLaneSet(tLaneSet);
				if (result == null) result = caseTBaseElement(tLaneSet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TLINK_EVENT_DEFINITION: {
				TLinkEventDefinition tLinkEventDefinition = (TLinkEventDefinition)theEObject;
				T result = caseTLinkEventDefinition(tLinkEventDefinition);
				if (result == null) result = caseTEventDefinition(tLinkEventDefinition);
				if (result == null) result = caseTRootElement(tLinkEventDefinition);
				if (result == null) result = caseTBaseElement(tLinkEventDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TLOOP_CHARACTERISTICS: {
				TLoopCharacteristics tLoopCharacteristics = (TLoopCharacteristics)theEObject;
				T result = caseTLoopCharacteristics(tLoopCharacteristics);
				if (result == null) result = caseTBaseElement(tLoopCharacteristics);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TMANUAL_TASK: {
				TManualTask tManualTask = (TManualTask)theEObject;
				T result = caseTManualTask(tManualTask);
				if (result == null) result = caseTTask(tManualTask);
				if (result == null) result = caseTActivity(tManualTask);
				if (result == null) result = caseTFlowNode(tManualTask);
				if (result == null) result = caseTFlowElement(tManualTask);
				if (result == null) result = caseTBaseElement(tManualTask);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TMESSAGE: {
				TMessage tMessage = (TMessage)theEObject;
				T result = caseTMessage(tMessage);
				if (result == null) result = caseTRootElement(tMessage);
				if (result == null) result = caseTBaseElement(tMessage);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TMESSAGE_EVENT_DEFINITION: {
				TMessageEventDefinition tMessageEventDefinition = (TMessageEventDefinition)theEObject;
				T result = caseTMessageEventDefinition(tMessageEventDefinition);
				if (result == null) result = caseTEventDefinition(tMessageEventDefinition);
				if (result == null) result = caseTRootElement(tMessageEventDefinition);
				if (result == null) result = caseTBaseElement(tMessageEventDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TMESSAGE_FLOW: {
				TMessageFlow tMessageFlow = (TMessageFlow)theEObject;
				T result = caseTMessageFlow(tMessageFlow);
				if (result == null) result = caseTBaseElement(tMessageFlow);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TMESSAGE_FLOW_ASSOCIATION: {
				TMessageFlowAssociation tMessageFlowAssociation = (TMessageFlowAssociation)theEObject;
				T result = caseTMessageFlowAssociation(tMessageFlowAssociation);
				if (result == null) result = caseTBaseElement(tMessageFlowAssociation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TMONITORING: {
				TMonitoring tMonitoring = (TMonitoring)theEObject;
				T result = caseTMonitoring(tMonitoring);
				if (result == null) result = caseTBaseElement(tMonitoring);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TMULTI_INSTANCE_LOOP_CHARACTERISTICS: {
				TMultiInstanceLoopCharacteristics tMultiInstanceLoopCharacteristics = (TMultiInstanceLoopCharacteristics)theEObject;
				T result = caseTMultiInstanceLoopCharacteristics(tMultiInstanceLoopCharacteristics);
				if (result == null) result = caseTLoopCharacteristics(tMultiInstanceLoopCharacteristics);
				if (result == null) result = caseTBaseElement(tMultiInstanceLoopCharacteristics);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TOPERATION: {
				TOperation tOperation = (TOperation)theEObject;
				T result = caseTOperation(tOperation);
				if (result == null) result = caseTBaseElement(tOperation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TOUTPUT_SET: {
				TOutputSet tOutputSet = (TOutputSet)theEObject;
				T result = caseTOutputSet(tOutputSet);
				if (result == null) result = caseTBaseElement(tOutputSet);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TPARALLEL_GATEWAY: {
				TParallelGateway tParallelGateway = (TParallelGateway)theEObject;
				T result = caseTParallelGateway(tParallelGateway);
				if (result == null) result = caseTGateway(tParallelGateway);
				if (result == null) result = caseTFlowNode(tParallelGateway);
				if (result == null) result = caseTFlowElement(tParallelGateway);
				if (result == null) result = caseTBaseElement(tParallelGateway);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TPARTICIPANT: {
				TParticipant tParticipant = (TParticipant)theEObject;
				T result = caseTParticipant(tParticipant);
				if (result == null) result = caseTBaseElement(tParticipant);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TPARTICIPANT_ASSOCIATION: {
				TParticipantAssociation tParticipantAssociation = (TParticipantAssociation)theEObject;
				T result = caseTParticipantAssociation(tParticipantAssociation);
				if (result == null) result = caseTBaseElement(tParticipantAssociation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TPARTICIPANT_MULTIPLICITY: {
				TParticipantMultiplicity tParticipantMultiplicity = (TParticipantMultiplicity)theEObject;
				T result = caseTParticipantMultiplicity(tParticipantMultiplicity);
				if (result == null) result = caseTBaseElement(tParticipantMultiplicity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TPARTNER_ENTITY: {
				TPartnerEntity tPartnerEntity = (TPartnerEntity)theEObject;
				T result = caseTPartnerEntity(tPartnerEntity);
				if (result == null) result = caseTRootElement(tPartnerEntity);
				if (result == null) result = caseTBaseElement(tPartnerEntity);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TPARTNER_ROLE: {
				TPartnerRole tPartnerRole = (TPartnerRole)theEObject;
				T result = caseTPartnerRole(tPartnerRole);
				if (result == null) result = caseTRootElement(tPartnerRole);
				if (result == null) result = caseTBaseElement(tPartnerRole);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TPERFORMER: {
				TPerformer tPerformer = (TPerformer)theEObject;
				T result = caseTPerformer(tPerformer);
				if (result == null) result = caseTResourceRole(tPerformer);
				if (result == null) result = caseTBaseElement(tPerformer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TPOTENTIAL_OWNER: {
				TPotentialOwner tPotentialOwner = (TPotentialOwner)theEObject;
				T result = caseTPotentialOwner(tPotentialOwner);
				if (result == null) result = caseTHumanPerformer(tPotentialOwner);
				if (result == null) result = caseTPerformer(tPotentialOwner);
				if (result == null) result = caseTResourceRole(tPotentialOwner);
				if (result == null) result = caseTBaseElement(tPotentialOwner);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TPROCESS: {
				TProcess tProcess = (TProcess)theEObject;
				T result = caseTProcess(tProcess);
				if (result == null) result = caseTCallableElement(tProcess);
				if (result == null) result = caseTRootElement(tProcess);
				if (result == null) result = caseTBaseElement(tProcess);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TPROPERTY: {
				TProperty tProperty = (TProperty)theEObject;
				T result = caseTProperty(tProperty);
				if (result == null) result = caseTBaseElement(tProperty);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TRECEIVE_TASK: {
				TReceiveTask tReceiveTask = (TReceiveTask)theEObject;
				T result = caseTReceiveTask(tReceiveTask);
				if (result == null) result = caseTTask(tReceiveTask);
				if (result == null) result = caseTActivity(tReceiveTask);
				if (result == null) result = caseTFlowNode(tReceiveTask);
				if (result == null) result = caseTFlowElement(tReceiveTask);
				if (result == null) result = caseTBaseElement(tReceiveTask);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TRELATIONSHIP: {
				TRelationship tRelationship = (TRelationship)theEObject;
				T result = caseTRelationship(tRelationship);
				if (result == null) result = caseTBaseElement(tRelationship);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TRENDERING: {
				TRendering tRendering = (TRendering)theEObject;
				T result = caseTRendering(tRendering);
				if (result == null) result = caseTBaseElement(tRendering);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TRESOURCE: {
				TResource tResource = (TResource)theEObject;
				T result = caseTResource(tResource);
				if (result == null) result = caseTRootElement(tResource);
				if (result == null) result = caseTBaseElement(tResource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TRESOURCE_ASSIGNMENT_EXPRESSION: {
				TResourceAssignmentExpression tResourceAssignmentExpression = (TResourceAssignmentExpression)theEObject;
				T result = caseTResourceAssignmentExpression(tResourceAssignmentExpression);
				if (result == null) result = caseTBaseElement(tResourceAssignmentExpression);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TRESOURCE_PARAMETER: {
				TResourceParameter tResourceParameter = (TResourceParameter)theEObject;
				T result = caseTResourceParameter(tResourceParameter);
				if (result == null) result = caseTBaseElement(tResourceParameter);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TRESOURCE_PARAMETER_BINDING: {
				TResourceParameterBinding tResourceParameterBinding = (TResourceParameterBinding)theEObject;
				T result = caseTResourceParameterBinding(tResourceParameterBinding);
				if (result == null) result = caseTBaseElement(tResourceParameterBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TRESOURCE_ROLE: {
				TResourceRole tResourceRole = (TResourceRole)theEObject;
				T result = caseTResourceRole(tResourceRole);
				if (result == null) result = caseTBaseElement(tResourceRole);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TROOT_ELEMENT: {
				TRootElement tRootElement = (TRootElement)theEObject;
				T result = caseTRootElement(tRootElement);
				if (result == null) result = caseTBaseElement(tRootElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TSCRIPT: {
				TScript tScript = (TScript)theEObject;
				T result = caseTScript(tScript);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TSCRIPT_TASK: {
				TScriptTask tScriptTask = (TScriptTask)theEObject;
				T result = caseTScriptTask(tScriptTask);
				if (result == null) result = caseTTask(tScriptTask);
				if (result == null) result = caseTActivity(tScriptTask);
				if (result == null) result = caseTFlowNode(tScriptTask);
				if (result == null) result = caseTFlowElement(tScriptTask);
				if (result == null) result = caseTBaseElement(tScriptTask);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TSEND_TASK: {
				TSendTask tSendTask = (TSendTask)theEObject;
				T result = caseTSendTask(tSendTask);
				if (result == null) result = caseTTask(tSendTask);
				if (result == null) result = caseTActivity(tSendTask);
				if (result == null) result = caseTFlowNode(tSendTask);
				if (result == null) result = caseTFlowElement(tSendTask);
				if (result == null) result = caseTBaseElement(tSendTask);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TSEQUENCE_FLOW: {
				TSequenceFlow tSequenceFlow = (TSequenceFlow)theEObject;
				T result = caseTSequenceFlow(tSequenceFlow);
				if (result == null) result = caseTFlowElement(tSequenceFlow);
				if (result == null) result = caseTBaseElement(tSequenceFlow);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TSERVICE_TASK: {
				TServiceTask tServiceTask = (TServiceTask)theEObject;
				T result = caseTServiceTask(tServiceTask);
				if (result == null) result = caseTTask(tServiceTask);
				if (result == null) result = caseTActivity(tServiceTask);
				if (result == null) result = caseTFlowNode(tServiceTask);
				if (result == null) result = caseTFlowElement(tServiceTask);
				if (result == null) result = caseTBaseElement(tServiceTask);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TSIGNAL: {
				TSignal tSignal = (TSignal)theEObject;
				T result = caseTSignal(tSignal);
				if (result == null) result = caseTRootElement(tSignal);
				if (result == null) result = caseTBaseElement(tSignal);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TSIGNAL_EVENT_DEFINITION: {
				TSignalEventDefinition tSignalEventDefinition = (TSignalEventDefinition)theEObject;
				T result = caseTSignalEventDefinition(tSignalEventDefinition);
				if (result == null) result = caseTEventDefinition(tSignalEventDefinition);
				if (result == null) result = caseTRootElement(tSignalEventDefinition);
				if (result == null) result = caseTBaseElement(tSignalEventDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TSTANDARD_LOOP_CHARACTERISTICS: {
				TStandardLoopCharacteristics tStandardLoopCharacteristics = (TStandardLoopCharacteristics)theEObject;
				T result = caseTStandardLoopCharacteristics(tStandardLoopCharacteristics);
				if (result == null) result = caseTLoopCharacteristics(tStandardLoopCharacteristics);
				if (result == null) result = caseTBaseElement(tStandardLoopCharacteristics);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TSTART_EVENT: {
				TStartEvent tStartEvent = (TStartEvent)theEObject;
				T result = caseTStartEvent(tStartEvent);
				if (result == null) result = caseTCatchEvent(tStartEvent);
				if (result == null) result = caseTEvent(tStartEvent);
				if (result == null) result = caseTFlowNode(tStartEvent);
				if (result == null) result = caseTFlowElement(tStartEvent);
				if (result == null) result = caseTBaseElement(tStartEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TSUB_CHOREOGRAPHY: {
				TSubChoreography tSubChoreography = (TSubChoreography)theEObject;
				T result = caseTSubChoreography(tSubChoreography);
				if (result == null) result = caseTChoreographyActivity(tSubChoreography);
				if (result == null) result = caseTFlowNode(tSubChoreography);
				if (result == null) result = caseTFlowElement(tSubChoreography);
				if (result == null) result = caseTBaseElement(tSubChoreography);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TSUB_CONVERSATION: {
				TSubConversation tSubConversation = (TSubConversation)theEObject;
				T result = caseTSubConversation(tSubConversation);
				if (result == null) result = caseTConversationNode(tSubConversation);
				if (result == null) result = caseTBaseElement(tSubConversation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TSUB_PROCESS: {
				TSubProcess tSubProcess = (TSubProcess)theEObject;
				T result = caseTSubProcess(tSubProcess);
				if (result == null) result = caseTActivity(tSubProcess);
				if (result == null) result = caseTFlowNode(tSubProcess);
				if (result == null) result = caseTFlowElement(tSubProcess);
				if (result == null) result = caseTBaseElement(tSubProcess);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TTASK: {
				TTask tTask = (TTask)theEObject;
				T result = caseTTask(tTask);
				if (result == null) result = caseTActivity(tTask);
				if (result == null) result = caseTFlowNode(tTask);
				if (result == null) result = caseTFlowElement(tTask);
				if (result == null) result = caseTBaseElement(tTask);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TTERMINATE_EVENT_DEFINITION: {
				TTerminateEventDefinition tTerminateEventDefinition = (TTerminateEventDefinition)theEObject;
				T result = caseTTerminateEventDefinition(tTerminateEventDefinition);
				if (result == null) result = caseTEventDefinition(tTerminateEventDefinition);
				if (result == null) result = caseTRootElement(tTerminateEventDefinition);
				if (result == null) result = caseTBaseElement(tTerminateEventDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TTEXT: {
				TText tText = (TText)theEObject;
				T result = caseTText(tText);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TTEXT_ANNOTATION: {
				TTextAnnotation tTextAnnotation = (TTextAnnotation)theEObject;
				T result = caseTTextAnnotation(tTextAnnotation);
				if (result == null) result = caseTArtifact(tTextAnnotation);
				if (result == null) result = caseTBaseElement(tTextAnnotation);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TTHROW_EVENT: {
				TThrowEvent tThrowEvent = (TThrowEvent)theEObject;
				T result = caseTThrowEvent(tThrowEvent);
				if (result == null) result = caseTEvent(tThrowEvent);
				if (result == null) result = caseTFlowNode(tThrowEvent);
				if (result == null) result = caseTFlowElement(tThrowEvent);
				if (result == null) result = caseTBaseElement(tThrowEvent);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TTIMER_EVENT_DEFINITION: {
				TTimerEventDefinition tTimerEventDefinition = (TTimerEventDefinition)theEObject;
				T result = caseTTimerEventDefinition(tTimerEventDefinition);
				if (result == null) result = caseTEventDefinition(tTimerEventDefinition);
				if (result == null) result = caseTRootElement(tTimerEventDefinition);
				if (result == null) result = caseTBaseElement(tTimerEventDefinition);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TTRANSACTION: {
				TTransaction tTransaction = (TTransaction)theEObject;
				T result = caseTTransaction(tTransaction);
				if (result == null) result = caseTSubProcess(tTransaction);
				if (result == null) result = caseTActivity(tTransaction);
				if (result == null) result = caseTFlowNode(tTransaction);
				if (result == null) result = caseTFlowElement(tTransaction);
				if (result == null) result = caseTBaseElement(tTransaction);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TUSER_TASK: {
				TUserTask tUserTask = (TUserTask)theEObject;
				T result = caseTUserTask(tUserTask);
				if (result == null) result = caseTTask(tUserTask);
				if (result == null) result = caseTActivity(tUserTask);
				if (result == null) result = caseTFlowNode(tUserTask);
				if (result == null) result = caseTFlowElement(tUserTask);
				if (result == null) result = caseTBaseElement(tUserTask);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Document Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDocumentRoot(DocumentRoot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TActivity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TActivity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTActivity(TActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TAd Hoc Sub Process</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TAd Hoc Sub Process</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTAdHocSubProcess(TAdHocSubProcess object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TArtifact</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TArtifact</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTArtifact(TArtifact object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TAssignment</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TAssignment</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTAssignment(TAssignment object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TAssociation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TAssociation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTAssociation(TAssociation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TAuditing</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TAuditing</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTAuditing(TAuditing object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TBase Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TBase Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTBaseElement(TBaseElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TBase Element With Mixed Content</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TBase Element With Mixed Content</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTBaseElementWithMixedContent(TBaseElementWithMixedContent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TBoundary Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TBoundary Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTBoundaryEvent(TBoundaryEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TBusiness Rule Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TBusiness Rule Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTBusinessRuleTask(TBusinessRuleTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TCallable Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TCallable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTCallableElement(TCallableElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TCall Activity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TCall Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTCallActivity(TCallActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TCall Choreography</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TCall Choreography</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTCallChoreography(TCallChoreography object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TCall Conversation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TCall Conversation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTCallConversation(TCallConversation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TCancel Event Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TCancel Event Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTCancelEventDefinition(TCancelEventDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TCatch Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TCatch Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTCatchEvent(TCatchEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TCategory</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TCategory</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTCategory(TCategory object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TCategory Value</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TCategory Value</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTCategoryValue(TCategoryValue object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TChoreography</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TChoreography</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTChoreography(TChoreography object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TChoreography Activity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TChoreography Activity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTChoreographyActivity(TChoreographyActivity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TChoreography Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TChoreography Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTChoreographyTask(TChoreographyTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TCollaboration</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TCollaboration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTCollaboration(TCollaboration object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TCompensate Event Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TCompensate Event Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTCompensateEventDefinition(TCompensateEventDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TComplex Behavior Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TComplex Behavior Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTComplexBehaviorDefinition(TComplexBehaviorDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TComplex Gateway</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TComplex Gateway</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTComplexGateway(TComplexGateway object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TConditional Event Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TConditional Event Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTConditionalEventDefinition(TConditionalEventDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TConversation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TConversation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTConversation(TConversation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TConversation Association</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TConversation Association</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTConversationAssociation(TConversationAssociation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TConversation Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TConversation Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTConversationLink(TConversationLink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TConversation Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TConversation Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTConversationNode(TConversationNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TCorrelation Key</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TCorrelation Key</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTCorrelationKey(TCorrelationKey object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TCorrelation Property</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TCorrelation Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTCorrelationProperty(TCorrelationProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TCorrelation Property Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TCorrelation Property Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTCorrelationPropertyBinding(TCorrelationPropertyBinding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TCorrelation Property Retrieval Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TCorrelation Property Retrieval Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTCorrelationPropertyRetrievalExpression(TCorrelationPropertyRetrievalExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TCorrelation Subscription</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TCorrelation Subscription</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTCorrelationSubscription(TCorrelationSubscription object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TData Association</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TData Association</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTDataAssociation(TDataAssociation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TData Input</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TData Input</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTDataInput(TDataInput object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TData Input Association</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TData Input Association</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTDataInputAssociation(TDataInputAssociation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TData Object</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TData Object</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTDataObject(TDataObject object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TData Object Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TData Object Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTDataObjectReference(TDataObjectReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TData Output</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TData Output</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTDataOutput(TDataOutput object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TData Output Association</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TData Output Association</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTDataOutputAssociation(TDataOutputAssociation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TData State</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TData State</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTDataState(TDataState object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TData Store</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TData Store</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTDataStore(TDataStore object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TData Store Reference</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TData Store Reference</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTDataStoreReference(TDataStoreReference object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TDefinitions</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TDefinitions</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTDefinitions(TDefinitions object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TDocumentation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TDocumentation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTDocumentation(TDocumentation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TEnd Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TEnd Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTEndEvent(TEndEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TEnd Point</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TEnd Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTEndPoint(TEndPoint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TError</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TError</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTError(TError object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TError Event Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TError Event Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTErrorEventDefinition(TErrorEventDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TEscalation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TEscalation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTEscalation(TEscalation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TEscalation Event Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TEscalation Event Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTEscalationEventDefinition(TEscalationEventDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TEvent</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TEvent</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTEvent(TEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TEvent Based Gateway</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TEvent Based Gateway</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTEventBasedGateway(TEventBasedGateway object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TEvent Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TEvent Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTEventDefinition(TEventDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TExclusive Gateway</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TExclusive Gateway</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTExclusiveGateway(TExclusiveGateway object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TExpression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TExpression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTExpression(TExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TExtension</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TExtension</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTExtension(TExtension object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TExtension Elements</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TExtension Elements</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTExtensionElements(TExtensionElements object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TFlow Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TFlow Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTFlowElement(TFlowElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TFlow Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TFlow Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTFlowNode(TFlowNode object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TFormal Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TFormal Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTFormalExpression(TFormalExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TGateway</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TGateway</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTGateway(TGateway object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TGlobal Business Rule Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TGlobal Business Rule Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTGlobalBusinessRuleTask(TGlobalBusinessRuleTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TGlobal Choreography Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TGlobal Choreography Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTGlobalChoreographyTask(TGlobalChoreographyTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TGlobal Conversation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TGlobal Conversation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTGlobalConversation(TGlobalConversation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TGlobal Manual Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TGlobal Manual Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTGlobalManualTask(TGlobalManualTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TGlobal Script Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TGlobal Script Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTGlobalScriptTask(TGlobalScriptTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TGlobal Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TGlobal Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTGlobalTask(TGlobalTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TGlobal User Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TGlobal User Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTGlobalUserTask(TGlobalUserTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TGroup</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TGroup</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTGroup(TGroup object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>THuman Performer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>THuman Performer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTHumanPerformer(THumanPerformer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TImplicit Throw Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TImplicit Throw Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTImplicitThrowEvent(TImplicitThrowEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TImport</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TImport</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTImport(TImport object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TInclusive Gateway</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TInclusive Gateway</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTInclusiveGateway(TInclusiveGateway object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TInput Output Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TInput Output Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTInputOutputBinding(TInputOutputBinding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TInput Output Specification</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TInput Output Specification</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTInputOutputSpecification(TInputOutputSpecification object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TInput Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TInput Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTInputSet(TInputSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TInterface</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TInterface</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTInterface(TInterface object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TIntermediate Catch Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TIntermediate Catch Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTIntermediateCatchEvent(TIntermediateCatchEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TIntermediate Throw Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TIntermediate Throw Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTIntermediateThrowEvent(TIntermediateThrowEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TItem Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TItem Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTItemDefinition(TItemDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TLane</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TLane</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTLane(TLane object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TLane Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TLane Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTLaneSet(TLaneSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TLink Event Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TLink Event Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTLinkEventDefinition(TLinkEventDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TLoop Characteristics</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TLoop Characteristics</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTLoopCharacteristics(TLoopCharacteristics object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TManual Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TManual Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTManualTask(TManualTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TMessage</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TMessage</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTMessage(TMessage object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TMessage Event Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TMessage Event Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTMessageEventDefinition(TMessageEventDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TMessage Flow</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TMessage Flow</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTMessageFlow(TMessageFlow object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TMessage Flow Association</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TMessage Flow Association</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTMessageFlowAssociation(TMessageFlowAssociation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TMonitoring</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TMonitoring</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTMonitoring(TMonitoring object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TMulti Instance Loop Characteristics</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TMulti Instance Loop Characteristics</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTMultiInstanceLoopCharacteristics(TMultiInstanceLoopCharacteristics object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TOperation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TOperation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTOperation(TOperation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TOutput Set</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TOutput Set</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTOutputSet(TOutputSet object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TParallel Gateway</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TParallel Gateway</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTParallelGateway(TParallelGateway object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TParticipant</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TParticipant</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTParticipant(TParticipant object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TParticipant Association</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TParticipant Association</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTParticipantAssociation(TParticipantAssociation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TParticipant Multiplicity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TParticipant Multiplicity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTParticipantMultiplicity(TParticipantMultiplicity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TPartner Entity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TPartner Entity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTPartnerEntity(TPartnerEntity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TPartner Role</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TPartner Role</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTPartnerRole(TPartnerRole object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TPerformer</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TPerformer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTPerformer(TPerformer object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TPotential Owner</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TPotential Owner</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTPotentialOwner(TPotentialOwner object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TProcess</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TProcess</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTProcess(TProcess object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TProperty</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TProperty</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTProperty(TProperty object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TReceive Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TReceive Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTReceiveTask(TReceiveTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TRelationship</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TRelationship</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTRelationship(TRelationship object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TRendering</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TRendering</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTRendering(TRendering object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TResource</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TResource</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTResource(TResource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TResource Assignment Expression</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TResource Assignment Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTResourceAssignmentExpression(TResourceAssignmentExpression object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TResource Parameter</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TResource Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTResourceParameter(TResourceParameter object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TResource Parameter Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TResource Parameter Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTResourceParameterBinding(TResourceParameterBinding object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TResource Role</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TResource Role</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTResourceRole(TResourceRole object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TRoot Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TRoot Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTRootElement(TRootElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TScript</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TScript</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTScript(TScript object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TScript Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TScript Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTScriptTask(TScriptTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TSend Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TSend Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTSendTask(TSendTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TSequence Flow</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TSequence Flow</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTSequenceFlow(TSequenceFlow object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TService Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TService Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTServiceTask(TServiceTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TSignal</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TSignal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTSignal(TSignal object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TSignal Event Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TSignal Event Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTSignalEventDefinition(TSignalEventDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TStandard Loop Characteristics</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TStandard Loop Characteristics</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTStandardLoopCharacteristics(TStandardLoopCharacteristics object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TStart Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TStart Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTStartEvent(TStartEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TSub Choreography</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TSub Choreography</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTSubChoreography(TSubChoreography object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TSub Conversation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TSub Conversation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTSubConversation(TSubConversation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TSub Process</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TSub Process</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTSubProcess(TSubProcess object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TTask</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TTask</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTTask(TTask object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TTerminate Event Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TTerminate Event Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTTerminateEventDefinition(TTerminateEventDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TText</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TText</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTText(TText object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TText Annotation</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TText Annotation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTTextAnnotation(TTextAnnotation object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TThrow Event</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TThrow Event</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTThrowEvent(TThrowEvent object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TTimer Event Definition</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TTimer Event Definition</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTTimerEventDefinition(TTimerEventDefinition object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TTransaction</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TTransaction</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTTransaction(TTransaction object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>TUser Task</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>TUser Task</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTUserTask(TUserTask object) {
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
	public T defaultCase(EObject object) {
		return null;
	}

} //ModelSwitch
