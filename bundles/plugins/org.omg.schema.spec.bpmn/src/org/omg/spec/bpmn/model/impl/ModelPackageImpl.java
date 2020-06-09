/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model.impl;

import java.io.IOException;

import java.net.URL;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.omg.spec.bpmn.di.DiPackage;

import org.omg.spec.bpmn.di.impl.DiPackageImpl;

import org.omg.spec.bpmn.model.ModelFactory;
import org.omg.spec.bpmn.model.ModelPackage;

import org.omg.spec.bpmn.model.util.ModelValidator;

import org.omg.spec.dd.dc.DcPackage;

import org.omg.spec.dd.dc.impl.DcPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelPackageImpl extends EPackageImpl implements ModelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected String packageFilename = "model.ecore";

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentRootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tActivityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tAdHocSubProcessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tArtifactEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tAssignmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tAssociationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tAuditingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tBaseElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tBaseElementWithMixedContentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tBoundaryEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tBusinessRuleTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tCallableElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tCallActivityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tCallChoreographyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tCallConversationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tCancelEventDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tCatchEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tCategoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tCategoryValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tChoreographyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tChoreographyActivityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tChoreographyTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tCollaborationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tCompensateEventDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tComplexBehaviorDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tComplexGatewayEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tConditionalEventDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tConversationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tConversationAssociationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tConversationLinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tConversationNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tCorrelationKeyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tCorrelationPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tCorrelationPropertyBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tCorrelationPropertyRetrievalExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tCorrelationSubscriptionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tDataAssociationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tDataInputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tDataInputAssociationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tDataObjectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tDataObjectReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tDataOutputEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tDataOutputAssociationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tDataStateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tDataStoreEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tDataStoreReferenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tDefinitionsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tDocumentationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tEndEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tEndPointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tErrorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tErrorEventDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tEscalationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tEscalationEventDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tEventBasedGatewayEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tEventDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tExclusiveGatewayEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tExtensionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tExtensionElementsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tFlowElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tFlowNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tFormalExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tGatewayEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tGlobalBusinessRuleTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tGlobalChoreographyTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tGlobalConversationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tGlobalManualTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tGlobalScriptTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tGlobalTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tGlobalUserTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tGroupEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tHumanPerformerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tImplicitThrowEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tImportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tInclusiveGatewayEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tInputOutputBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tInputOutputSpecificationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tInputSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tInterfaceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tIntermediateCatchEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tIntermediateThrowEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tItemDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tLaneEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tLaneSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tLinkEventDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tLoopCharacteristicsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tManualTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tMessageEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tMessageEventDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tMessageFlowEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tMessageFlowAssociationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tMonitoringEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tMultiInstanceLoopCharacteristicsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tOutputSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tParallelGatewayEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tParticipantEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tParticipantAssociationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tParticipantMultiplicityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tPartnerEntityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tPartnerRoleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tPerformerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tPotentialOwnerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tProcessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tReceiveTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tRelationshipEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tRenderingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tResourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tResourceAssignmentExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tResourceParameterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tResourceParameterBindingEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tResourceRoleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tRootElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tScriptEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tScriptTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tSendTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tSequenceFlowEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tServiceTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tSignalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tSignalEventDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tStandardLoopCharacteristicsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tStartEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tSubChoreographyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tSubConversationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tSubProcessEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tTerminateEventDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tTextEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tTextAnnotationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tThrowEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tTimerEventDefinitionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tTransactionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tUserTaskEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum tAdHocOrderingEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum tAssociationDirectionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum tChoreographyLoopTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum tEventBasedGatewayTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum tGatewayDirectionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum tImplementationMember1EEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum tItemKindEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum tMultiInstanceFlowConditionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum tProcessTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum tRelationshipDirectionEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum tTransactionMethodMember1EEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType tAdHocOrderingObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType tAssociationDirectionObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType tChoreographyLoopTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType tEventBasedGatewayTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType tGatewayDirectionObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType tImplementationEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType tImplementationMember1ObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType tItemKindObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType tMultiInstanceFlowConditionObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType tProcessTypeObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType tRelationshipDirectionObjectEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType tTransactionMethodEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType tTransactionMethodMember1ObjectEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.omg.spec.bpmn.model.ModelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ModelPackageImpl() {
		super(eNS_URI, ModelFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ModelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @generated
	 */
	public static ModelPackage init() {
		if (isInited) return (ModelPackage)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI);

		// Obtain or create and register package
		ModelPackageImpl theModelPackage = (ModelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ModelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ModelPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		DcPackageImpl theDcPackage = (DcPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DcPackage.eNS_URI) instanceof DcPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DcPackage.eNS_URI) : DcPackage.eINSTANCE);
		DiPackageImpl theDiPackage = (DiPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DiPackage.eNS_URI) instanceof DiPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DiPackage.eNS_URI) : DiPackage.eINSTANCE);
		org.omg.spec.dd.di.impl.DiPackageImpl theDiPackage_1 = (org.omg.spec.dd.di.impl.DiPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(org.omg.spec.dd.di.DiPackage.eNS_URI) instanceof org.omg.spec.dd.di.impl.DiPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(org.omg.spec.dd.di.DiPackage.eNS_URI) : org.omg.spec.dd.di.DiPackage.eINSTANCE);

		// Load packages
		theModelPackage.loadPackage();

		// Create package meta-data objects
		theDcPackage.createPackageContents();
		theDiPackage.createPackageContents();
		theDiPackage_1.createPackageContents();

		// Initialize created meta-data
		theDcPackage.initializePackageContents();
		theDiPackage.initializePackageContents();
		theDiPackage_1.initializePackageContents();

		// Fix loaded packages
		theModelPackage.fixPackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theModelPackage, 
			 new EValidator.Descriptor() {
				 public EValidator getEValidator() {
					 return ModelValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theModelPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, theModelPackage);
		return theModelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getDocumentRoot() {
		if (documentRootEClass == null) {
			documentRootEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(0);
		}
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getDocumentRoot_Mixed() {
        return (EAttribute)getDocumentRoot().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XMLNSPrefixMap() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_XSISchemaLocation() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Activity() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_AdHocSubProcess() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_FlowElement() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Artifact() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Assignment() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Association() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Auditing() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_BaseElement() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_BaseElementWithMixedContent() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_BoundaryEvent() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_BusinessRuleTask() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_CallableElement() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_CallActivity() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_CallChoreography() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(16);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_CallConversation() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(17);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ConversationNode() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(18);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_CancelEventDefinition() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(19);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_EventDefinition() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(20);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_RootElement() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(21);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_CatchEvent() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(22);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Category() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(23);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_CategoryValue() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(24);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Choreography() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(25);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Collaboration() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(26);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ChoreographyActivity() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(27);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ChoreographyTask() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(28);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_CompensateEventDefinition() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(29);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ComplexBehaviorDefinition() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(30);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ComplexGateway() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(31);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ConditionalEventDefinition() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(32);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Conversation() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(33);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ConversationAssociation() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(34);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ConversationLink() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(35);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_CorrelationKey() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(36);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_CorrelationProperty() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(37);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_CorrelationPropertyBinding() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(38);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_CorrelationPropertyRetrievalExpression() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(39);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_CorrelationSubscription() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(40);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_DataAssociation() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(41);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_DataInput() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(42);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_DataInputAssociation() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(43);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_DataObject() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(44);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_DataObjectReference() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(45);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_DataOutput() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(46);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_DataOutputAssociation() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(47);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_DataState() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(48);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_DataStore() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(49);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_DataStoreReference() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(50);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Definitions() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(51);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Documentation() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(52);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_EndEvent() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(53);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_EndPoint() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(54);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Error() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(55);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ErrorEventDefinition() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(56);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Escalation() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(57);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_EscalationEventDefinition() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(58);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Event() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(59);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_EventBasedGateway() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(60);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ExclusiveGateway() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(61);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Expression() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(62);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Extension() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(63);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ExtensionElements() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(64);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_FlowNode() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(65);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_FormalExpression() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(66);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Gateway() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(67);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_GlobalBusinessRuleTask() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(68);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_GlobalChoreographyTask() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(69);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_GlobalConversation() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(70);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_GlobalManualTask() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(71);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_GlobalScriptTask() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(72);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_GlobalTask() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(73);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_GlobalUserTask() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(74);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Group() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(75);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_HumanPerformer() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(76);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Performer() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(77);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ResourceRole() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(78);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ImplicitThrowEvent() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(79);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Import() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(80);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_InclusiveGateway() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(81);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_InputSet() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(82);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Interface() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(83);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_IntermediateCatchEvent() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(84);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_IntermediateThrowEvent() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(85);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_IoBinding() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(86);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_IoSpecification() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(87);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ItemDefinition() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(88);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Lane() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(89);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_LaneSet() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(90);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_LinkEventDefinition() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(91);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_LoopCharacteristics() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(92);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ManualTask() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(93);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Message() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(94);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_MessageEventDefinition() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(95);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_MessageFlow() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(96);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_MessageFlowAssociation() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(97);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Monitoring() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(98);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_MultiInstanceLoopCharacteristics() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(99);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Operation() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(100);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_OutputSet() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(101);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ParallelGateway() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(102);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Participant() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(103);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ParticipantAssociation() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(104);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ParticipantMultiplicity() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(105);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_PartnerEntity() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(106);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_PartnerRole() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(107);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_PotentialOwner() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(108);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Process() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(109);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Property() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(110);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ReceiveTask() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(111);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Relationship() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(112);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Rendering() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(113);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Resource() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(114);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ResourceAssignmentExpression() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(115);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ResourceParameter() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(116);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ResourceParameterBinding() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(117);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Script() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(118);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ScriptTask() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(119);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_SendTask() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(120);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_SequenceFlow() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(121);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ServiceTask() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(122);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Signal() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(123);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_SignalEventDefinition() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(124);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StandardLoopCharacteristics() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(125);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_StartEvent() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(126);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_SubChoreography() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(127);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_SubConversation() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(128);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_SubProcess() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(129);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Task() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(130);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_TerminateEventDefinition() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(131);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Text() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(132);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_TextAnnotation() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(133);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_ThrowEvent() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(134);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_TimerEventDefinition() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(135);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_Transaction() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(136);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getDocumentRoot_UserTask() {
        return (EReference)getDocumentRoot().getEStructuralFeatures().get(137);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTActivity() {
		if (tActivityEClass == null) {
			tActivityEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(1);
		}
		return tActivityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTActivity_IoSpecification() {
        return (EReference)getTActivity().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTActivity_Property() {
        return (EReference)getTActivity().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTActivity_DataInputAssociation() {
        return (EReference)getTActivity().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTActivity_DataOutputAssociation() {
        return (EReference)getTActivity().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTActivity_ResourceRoleGroup() {
        return (EAttribute)getTActivity().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTActivity_ResourceRole() {
        return (EReference)getTActivity().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTActivity_LoopCharacteristicsGroup() {
        return (EAttribute)getTActivity().getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTActivity_LoopCharacteristics() {
        return (EReference)getTActivity().getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTActivity_CompletionQuantity() {
        return (EAttribute)getTActivity().getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTActivity_Default() {
        return (EAttribute)getTActivity().getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTActivity_IsForCompensation() {
        return (EAttribute)getTActivity().getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTActivity_StartQuantity() {
        return (EAttribute)getTActivity().getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTAdHocSubProcess() {
		if (tAdHocSubProcessEClass == null) {
			tAdHocSubProcessEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(4);
		}
		return tAdHocSubProcessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTAdHocSubProcess_CompletionCondition() {
        return (EReference)getTAdHocSubProcess().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTAdHocSubProcess_CancelRemainingInstances() {
        return (EAttribute)getTAdHocSubProcess().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTAdHocSubProcess_Ordering() {
        return (EAttribute)getTAdHocSubProcess().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTArtifact() {
		if (tArtifactEClass == null) {
			tArtifactEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(5);
		}
		return tArtifactEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTAssignment() {
		if (tAssignmentEClass == null) {
			tAssignmentEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(6);
		}
		return tAssignmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTAssignment_From() {
        return (EReference)getTAssignment().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTAssignment_To() {
        return (EReference)getTAssignment().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTAssociation() {
		if (tAssociationEClass == null) {
			tAssociationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(7);
		}
		return tAssociationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTAssociation_AssociationDirection() {
        return (EAttribute)getTAssociation().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTAssociation_SourceRef() {
        return (EAttribute)getTAssociation().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTAssociation_TargetRef() {
        return (EAttribute)getTAssociation().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTAuditing() {
		if (tAuditingEClass == null) {
			tAuditingEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(10);
		}
		return tAuditingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTBaseElement() {
		if (tBaseElementEClass == null) {
			tBaseElementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(11);
		}
		return tBaseElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTBaseElement_Documentation() {
        return (EReference)getTBaseElement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTBaseElement_ExtensionElements() {
        return (EReference)getTBaseElement().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTBaseElement_Id() {
        return (EAttribute)getTBaseElement().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTBaseElement_AnyAttribute() {
        return (EAttribute)getTBaseElement().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTBaseElementWithMixedContent() {
		if (tBaseElementWithMixedContentEClass == null) {
			tBaseElementWithMixedContentEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(12);
		}
		return tBaseElementWithMixedContentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTBaseElementWithMixedContent_Mixed() {
        return (EAttribute)getTBaseElementWithMixedContent().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTBaseElementWithMixedContent_Documentation() {
        return (EReference)getTBaseElementWithMixedContent().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTBaseElementWithMixedContent_ExtensionElements() {
        return (EReference)getTBaseElementWithMixedContent().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTBaseElementWithMixedContent_Id() {
        return (EAttribute)getTBaseElementWithMixedContent().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTBaseElementWithMixedContent_AnyAttribute() {
        return (EAttribute)getTBaseElementWithMixedContent().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTBoundaryEvent() {
		if (tBoundaryEventEClass == null) {
			tBoundaryEventEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(13);
		}
		return tBoundaryEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTBoundaryEvent_AttachedToRef() {
        return (EAttribute)getTBoundaryEvent().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTBoundaryEvent_CancelActivity() {
        return (EAttribute)getTBoundaryEvent().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTBusinessRuleTask() {
		if (tBusinessRuleTaskEClass == null) {
			tBusinessRuleTaskEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(14);
		}
		return tBusinessRuleTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTBusinessRuleTask_Implementation() {
        return (EAttribute)getTBusinessRuleTask().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTCallableElement() {
		if (tCallableElementEClass == null) {
			tCallableElementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(15);
		}
		return tCallableElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCallableElement_SupportedInterfaceRef() {
        return (EAttribute)getTCallableElement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCallableElement_IoSpecification() {
        return (EReference)getTCallableElement().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCallableElement_IoBinding() {
        return (EReference)getTCallableElement().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCallableElement_Name() {
        return (EAttribute)getTCallableElement().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTCallActivity() {
		if (tCallActivityEClass == null) {
			tCallActivityEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(16);
		}
		return tCallActivityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCallActivity_CalledElement() {
        return (EAttribute)getTCallActivity().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTCallChoreography() {
		if (tCallChoreographyEClass == null) {
			tCallChoreographyEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(17);
		}
		return tCallChoreographyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCallChoreography_ParticipantAssociation() {
        return (EReference)getTCallChoreography().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCallChoreography_CalledChoreographyRef() {
        return (EAttribute)getTCallChoreography().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTCallConversation() {
		if (tCallConversationEClass == null) {
			tCallConversationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(18);
		}
		return tCallConversationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCallConversation_ParticipantAssociation() {
        return (EReference)getTCallConversation().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCallConversation_CalledCollaborationRef() {
        return (EAttribute)getTCallConversation().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTCancelEventDefinition() {
		if (tCancelEventDefinitionEClass == null) {
			tCancelEventDefinitionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(19);
		}
		return tCancelEventDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTCatchEvent() {
		if (tCatchEventEClass == null) {
			tCatchEventEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(20);
		}
		return tCatchEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCatchEvent_DataOutput() {
        return (EReference)getTCatchEvent().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCatchEvent_DataOutputAssociation() {
        return (EReference)getTCatchEvent().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCatchEvent_OutputSet() {
        return (EReference)getTCatchEvent().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCatchEvent_EventDefinitionGroup() {
        return (EAttribute)getTCatchEvent().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCatchEvent_EventDefinition() {
        return (EReference)getTCatchEvent().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCatchEvent_EventDefinitionRef() {
        return (EAttribute)getTCatchEvent().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCatchEvent_ParallelMultiple() {
        return (EAttribute)getTCatchEvent().getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTCategory() {
		if (tCategoryEClass == null) {
			tCategoryEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(21);
		}
		return tCategoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCategory_CategoryValue() {
        return (EReference)getTCategory().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCategory_Name() {
        return (EAttribute)getTCategory().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTCategoryValue() {
		if (tCategoryValueEClass == null) {
			tCategoryValueEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(22);
		}
		return tCategoryValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCategoryValue_Value() {
        return (EAttribute)getTCategoryValue().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTChoreography() {
		if (tChoreographyEClass == null) {
			tChoreographyEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(23);
		}
		return tChoreographyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTChoreography_FlowElementGroup() {
        return (EAttribute)getTChoreography().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTChoreography_FlowElement() {
        return (EReference)getTChoreography().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTChoreographyActivity() {
		if (tChoreographyActivityEClass == null) {
			tChoreographyActivityEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(24);
		}
		return tChoreographyActivityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTChoreographyActivity_ParticipantRef() {
        return (EAttribute)getTChoreographyActivity().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTChoreographyActivity_CorrelationKey() {
        return (EReference)getTChoreographyActivity().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTChoreographyActivity_InitiatingParticipantRef() {
        return (EAttribute)getTChoreographyActivity().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTChoreographyActivity_LoopType() {
        return (EAttribute)getTChoreographyActivity().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTChoreographyTask() {
		if (tChoreographyTaskEClass == null) {
			tChoreographyTaskEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(27);
		}
		return tChoreographyTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTChoreographyTask_MessageFlowRef() {
        return (EAttribute)getTChoreographyTask().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTCollaboration() {
		if (tCollaborationEClass == null) {
			tCollaborationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(28);
		}
		return tCollaborationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCollaboration_Participant() {
        return (EReference)getTCollaboration().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCollaboration_MessageFlow() {
        return (EReference)getTCollaboration().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCollaboration_ArtifactGroup() {
        return (EAttribute)getTCollaboration().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCollaboration_Artifact() {
        return (EReference)getTCollaboration().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCollaboration_ConversationNodeGroup() {
        return (EAttribute)getTCollaboration().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCollaboration_ConversationNode() {
        return (EReference)getTCollaboration().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCollaboration_ConversationAssociation() {
        return (EReference)getTCollaboration().getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCollaboration_ParticipantAssociation() {
        return (EReference)getTCollaboration().getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCollaboration_MessageFlowAssociation() {
        return (EReference)getTCollaboration().getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCollaboration_CorrelationKey() {
        return (EReference)getTCollaboration().getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCollaboration_ChoreographyRef() {
        return (EAttribute)getTCollaboration().getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCollaboration_ConversationLink() {
        return (EReference)getTCollaboration().getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCollaboration_IsClosed() {
        return (EAttribute)getTCollaboration().getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCollaboration_Name() {
        return (EAttribute)getTCollaboration().getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTCompensateEventDefinition() {
		if (tCompensateEventDefinitionEClass == null) {
			tCompensateEventDefinitionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(29);
		}
		return tCompensateEventDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCompensateEventDefinition_ActivityRef() {
        return (EAttribute)getTCompensateEventDefinition().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCompensateEventDefinition_WaitForCompletion() {
        return (EAttribute)getTCompensateEventDefinition().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTComplexBehaviorDefinition() {
		if (tComplexBehaviorDefinitionEClass == null) {
			tComplexBehaviorDefinitionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(30);
		}
		return tComplexBehaviorDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTComplexBehaviorDefinition_Condition() {
        return (EReference)getTComplexBehaviorDefinition().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTComplexBehaviorDefinition_Event() {
        return (EReference)getTComplexBehaviorDefinition().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTComplexGateway() {
		if (tComplexGatewayEClass == null) {
			tComplexGatewayEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(31);
		}
		return tComplexGatewayEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTComplexGateway_ActivationCondition() {
        return (EReference)getTComplexGateway().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTComplexGateway_Default() {
        return (EAttribute)getTComplexGateway().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTConditionalEventDefinition() {
		if (tConditionalEventDefinitionEClass == null) {
			tConditionalEventDefinitionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(32);
		}
		return tConditionalEventDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTConditionalEventDefinition_Condition() {
        return (EReference)getTConditionalEventDefinition().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTConversation() {
		if (tConversationEClass == null) {
			tConversationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(33);
		}
		return tConversationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTConversationAssociation() {
		if (tConversationAssociationEClass == null) {
			tConversationAssociationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(34);
		}
		return tConversationAssociationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTConversationAssociation_InnerConversationNodeRef() {
        return (EAttribute)getTConversationAssociation().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTConversationAssociation_OuterConversationNodeRef() {
        return (EAttribute)getTConversationAssociation().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTConversationLink() {
		if (tConversationLinkEClass == null) {
			tConversationLinkEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(35);
		}
		return tConversationLinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTConversationLink_Name() {
        return (EAttribute)getTConversationLink().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTConversationLink_SourceRef() {
        return (EAttribute)getTConversationLink().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTConversationLink_TargetRef() {
        return (EAttribute)getTConversationLink().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTConversationNode() {
		if (tConversationNodeEClass == null) {
			tConversationNodeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(36);
		}
		return tConversationNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTConversationNode_ParticipantRef() {
        return (EAttribute)getTConversationNode().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTConversationNode_MessageFlowRef() {
        return (EAttribute)getTConversationNode().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTConversationNode_CorrelationKey() {
        return (EReference)getTConversationNode().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTConversationNode_Name() {
        return (EAttribute)getTConversationNode().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTCorrelationKey() {
		if (tCorrelationKeyEClass == null) {
			tCorrelationKeyEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(37);
		}
		return tCorrelationKeyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCorrelationKey_CorrelationPropertyRef() {
        return (EAttribute)getTCorrelationKey().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCorrelationKey_Name() {
        return (EAttribute)getTCorrelationKey().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTCorrelationProperty() {
		if (tCorrelationPropertyEClass == null) {
			tCorrelationPropertyEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(38);
		}
		return tCorrelationPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCorrelationProperty_CorrelationPropertyRetrievalExpression() {
        return (EReference)getTCorrelationProperty().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCorrelationProperty_Name() {
        return (EAttribute)getTCorrelationProperty().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCorrelationProperty_Type() {
        return (EAttribute)getTCorrelationProperty().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTCorrelationPropertyBinding() {
		if (tCorrelationPropertyBindingEClass == null) {
			tCorrelationPropertyBindingEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(39);
		}
		return tCorrelationPropertyBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCorrelationPropertyBinding_DataPath() {
        return (EReference)getTCorrelationPropertyBinding().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCorrelationPropertyBinding_CorrelationPropertyRef() {
        return (EAttribute)getTCorrelationPropertyBinding().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTCorrelationPropertyRetrievalExpression() {
		if (tCorrelationPropertyRetrievalExpressionEClass == null) {
			tCorrelationPropertyRetrievalExpressionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(40);
		}
		return tCorrelationPropertyRetrievalExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCorrelationPropertyRetrievalExpression_MessagePath() {
        return (EReference)getTCorrelationPropertyRetrievalExpression().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCorrelationPropertyRetrievalExpression_MessageRef() {
        return (EAttribute)getTCorrelationPropertyRetrievalExpression().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTCorrelationSubscription() {
		if (tCorrelationSubscriptionEClass == null) {
			tCorrelationSubscriptionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(41);
		}
		return tCorrelationSubscriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTCorrelationSubscription_CorrelationPropertyBinding() {
        return (EReference)getTCorrelationSubscription().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTCorrelationSubscription_CorrelationKeyRef() {
        return (EAttribute)getTCorrelationSubscription().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTDataAssociation() {
		if (tDataAssociationEClass == null) {
			tDataAssociationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(42);
		}
		return tDataAssociationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDataAssociation_SourceRef() {
        return (EAttribute)getTDataAssociation().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDataAssociation_TargetRef() {
        return (EAttribute)getTDataAssociation().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTDataAssociation_Transformation() {
        return (EReference)getTDataAssociation().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTDataAssociation_Assignment() {
        return (EReference)getTDataAssociation().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTDataInput() {
		if (tDataInputEClass == null) {
			tDataInputEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(43);
		}
		return tDataInputEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTDataInput_DataState() {
        return (EReference)getTDataInput().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDataInput_IsCollection() {
        return (EAttribute)getTDataInput().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDataInput_ItemSubjectRef() {
        return (EAttribute)getTDataInput().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDataInput_Name() {
        return (EAttribute)getTDataInput().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTDataInputAssociation() {
		if (tDataInputAssociationEClass == null) {
			tDataInputAssociationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(44);
		}
		return tDataInputAssociationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTDataObject() {
		if (tDataObjectEClass == null) {
			tDataObjectEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(45);
		}
		return tDataObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTDataObject_DataState() {
        return (EReference)getTDataObject().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDataObject_IsCollection() {
        return (EAttribute)getTDataObject().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDataObject_ItemSubjectRef() {
        return (EAttribute)getTDataObject().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTDataObjectReference() {
		if (tDataObjectReferenceEClass == null) {
			tDataObjectReferenceEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(46);
		}
		return tDataObjectReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTDataObjectReference_DataState() {
        return (EReference)getTDataObjectReference().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDataObjectReference_DataObjectRef() {
        return (EAttribute)getTDataObjectReference().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDataObjectReference_ItemSubjectRef() {
        return (EAttribute)getTDataObjectReference().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTDataOutput() {
		if (tDataOutputEClass == null) {
			tDataOutputEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(47);
		}
		return tDataOutputEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTDataOutput_DataState() {
        return (EReference)getTDataOutput().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDataOutput_IsCollection() {
        return (EAttribute)getTDataOutput().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDataOutput_ItemSubjectRef() {
        return (EAttribute)getTDataOutput().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDataOutput_Name() {
        return (EAttribute)getTDataOutput().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTDataOutputAssociation() {
		if (tDataOutputAssociationEClass == null) {
			tDataOutputAssociationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(48);
		}
		return tDataOutputAssociationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTDataState() {
		if (tDataStateEClass == null) {
			tDataStateEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(49);
		}
		return tDataStateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDataState_Name() {
        return (EAttribute)getTDataState().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTDataStore() {
		if (tDataStoreEClass == null) {
			tDataStoreEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(50);
		}
		return tDataStoreEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTDataStore_DataState() {
        return (EReference)getTDataStore().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDataStore_Capacity() {
        return (EAttribute)getTDataStore().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDataStore_IsUnlimited() {
        return (EAttribute)getTDataStore().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDataStore_ItemSubjectRef() {
        return (EAttribute)getTDataStore().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDataStore_Name() {
        return (EAttribute)getTDataStore().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTDataStoreReference() {
		if (tDataStoreReferenceEClass == null) {
			tDataStoreReferenceEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(51);
		}
		return tDataStoreReferenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTDataStoreReference_DataState() {
        return (EReference)getTDataStoreReference().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDataStoreReference_DataStoreRef() {
        return (EAttribute)getTDataStoreReference().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDataStoreReference_ItemSubjectRef() {
        return (EAttribute)getTDataStoreReference().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTDefinitions() {
		if (tDefinitionsEClass == null) {
			tDefinitionsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(52);
		}
		return tDefinitionsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTDefinitions_Import() {
        return (EReference)getTDefinitions().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTDefinitions_Extension() {
        return (EReference)getTDefinitions().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDefinitions_RootElementGroup() {
        return (EAttribute)getTDefinitions().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTDefinitions_RootElement() {
        return (EReference)getTDefinitions().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTDefinitions_BPMNDiagram() {
        return (EReference)getTDefinitions().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTDefinitions_Relationship() {
        return (EReference)getTDefinitions().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDefinitions_Exporter() {
        return (EAttribute)getTDefinitions().getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDefinitions_ExporterVersion() {
        return (EAttribute)getTDefinitions().getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDefinitions_ExpressionLanguage() {
        return (EAttribute)getTDefinitions().getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDefinitions_Id() {
        return (EAttribute)getTDefinitions().getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDefinitions_Name() {
        return (EAttribute)getTDefinitions().getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDefinitions_TargetNamespace() {
        return (EAttribute)getTDefinitions().getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDefinitions_TypeLanguage() {
        return (EAttribute)getTDefinitions().getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDefinitions_AnyAttribute() {
        return (EAttribute)getTDefinitions().getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTDocumentation() {
		if (tDocumentationEClass == null) {
			tDocumentationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(53);
		}
		return tDocumentationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDocumentation_Mixed() {
        return (EAttribute)getTDocumentation().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDocumentation_Any() {
        return (EAttribute)getTDocumentation().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDocumentation_Id() {
        return (EAttribute)getTDocumentation().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTDocumentation_TextFormat() {
        return (EAttribute)getTDocumentation().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTEndEvent() {
		if (tEndEventEClass == null) {
			tEndEventEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(54);
		}
		return tEndEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTEndPoint() {
		if (tEndPointEClass == null) {
			tEndPointEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(55);
		}
		return tEndPointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTError() {
		if (tErrorEClass == null) {
			tErrorEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(56);
		}
		return tErrorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTError_ErrorCode() {
        return (EAttribute)getTError().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTError_Name() {
        return (EAttribute)getTError().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTError_StructureRef() {
        return (EAttribute)getTError().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTErrorEventDefinition() {
		if (tErrorEventDefinitionEClass == null) {
			tErrorEventDefinitionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(57);
		}
		return tErrorEventDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTErrorEventDefinition_ErrorRef() {
        return (EAttribute)getTErrorEventDefinition().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTEscalation() {
		if (tEscalationEClass == null) {
			tEscalationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(58);
		}
		return tEscalationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTEscalation_EscalationCode() {
        return (EAttribute)getTEscalation().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTEscalation_Name() {
        return (EAttribute)getTEscalation().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTEscalation_StructureRef() {
        return (EAttribute)getTEscalation().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTEscalationEventDefinition() {
		if (tEscalationEventDefinitionEClass == null) {
			tEscalationEventDefinitionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(59);
		}
		return tEscalationEventDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTEscalationEventDefinition_EscalationRef() {
        return (EAttribute)getTEscalationEventDefinition().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTEvent() {
		if (tEventEClass == null) {
			tEventEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(60);
		}
		return tEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTEvent_Property() {
        return (EReference)getTEvent().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTEventBasedGateway() {
		if (tEventBasedGatewayEClass == null) {
			tEventBasedGatewayEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(61);
		}
		return tEventBasedGatewayEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTEventBasedGateway_EventGatewayType() {
        return (EAttribute)getTEventBasedGateway().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTEventBasedGateway_Instantiate() {
        return (EAttribute)getTEventBasedGateway().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTEventDefinition() {
		if (tEventDefinitionEClass == null) {
			tEventDefinitionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(64);
		}
		return tEventDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTExclusiveGateway() {
		if (tExclusiveGatewayEClass == null) {
			tExclusiveGatewayEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(65);
		}
		return tExclusiveGatewayEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTExclusiveGateway_Default() {
        return (EAttribute)getTExclusiveGateway().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTExpression() {
		if (tExpressionEClass == null) {
			tExpressionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(66);
		}
		return tExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTExtension() {
		if (tExtensionEClass == null) {
			tExtensionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(67);
		}
		return tExtensionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTExtension_Documentation() {
        return (EReference)getTExtension().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTExtension_Definition() {
        return (EAttribute)getTExtension().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTExtension_MustUnderstand() {
        return (EAttribute)getTExtension().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTExtensionElements() {
		if (tExtensionElementsEClass == null) {
			tExtensionElementsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(68);
		}
		return tExtensionElementsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTExtensionElements_Any() {
        return (EAttribute)getTExtensionElements().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTFlowElement() {
		if (tFlowElementEClass == null) {
			tFlowElementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(69);
		}
		return tFlowElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTFlowElement_Auditing() {
        return (EReference)getTFlowElement().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTFlowElement_Monitoring() {
        return (EReference)getTFlowElement().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTFlowElement_CategoryValueRef() {
        return (EAttribute)getTFlowElement().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTFlowElement_Name() {
        return (EAttribute)getTFlowElement().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTFlowNode() {
		if (tFlowNodeEClass == null) {
			tFlowNodeEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(70);
		}
		return tFlowNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTFlowNode_Incoming() {
        return (EAttribute)getTFlowNode().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTFlowNode_Outgoing() {
        return (EAttribute)getTFlowNode().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTFormalExpression() {
		if (tFormalExpressionEClass == null) {
			tFormalExpressionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(71);
		}
		return tFormalExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTFormalExpression_EvaluatesToTypeRef() {
        return (EAttribute)getTFormalExpression().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTFormalExpression_Language() {
        return (EAttribute)getTFormalExpression().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTGateway() {
		if (tGatewayEClass == null) {
			tGatewayEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(72);
		}
		return tGatewayEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTGateway_GatewayDirection() {
        return (EAttribute)getTGateway().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTGlobalBusinessRuleTask() {
		if (tGlobalBusinessRuleTaskEClass == null) {
			tGlobalBusinessRuleTaskEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(75);
		}
		return tGlobalBusinessRuleTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTGlobalBusinessRuleTask_Implementation() {
        return (EAttribute)getTGlobalBusinessRuleTask().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTGlobalChoreographyTask() {
		if (tGlobalChoreographyTaskEClass == null) {
			tGlobalChoreographyTaskEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(76);
		}
		return tGlobalChoreographyTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTGlobalChoreographyTask_InitiatingParticipantRef() {
        return (EAttribute)getTGlobalChoreographyTask().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTGlobalConversation() {
		if (tGlobalConversationEClass == null) {
			tGlobalConversationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(77);
		}
		return tGlobalConversationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTGlobalManualTask() {
		if (tGlobalManualTaskEClass == null) {
			tGlobalManualTaskEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(78);
		}
		return tGlobalManualTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTGlobalScriptTask() {
		if (tGlobalScriptTaskEClass == null) {
			tGlobalScriptTaskEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(79);
		}
		return tGlobalScriptTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTGlobalScriptTask_Script() {
        return (EReference)getTGlobalScriptTask().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTGlobalScriptTask_ScriptLanguage() {
        return (EAttribute)getTGlobalScriptTask().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTGlobalTask() {
		if (tGlobalTaskEClass == null) {
			tGlobalTaskEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(80);
		}
		return tGlobalTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTGlobalTask_ResourceRoleGroup() {
        return (EAttribute)getTGlobalTask().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTGlobalTask_ResourceRole() {
        return (EReference)getTGlobalTask().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTGlobalUserTask() {
		if (tGlobalUserTaskEClass == null) {
			tGlobalUserTaskEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(81);
		}
		return tGlobalUserTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTGlobalUserTask_Rendering() {
        return (EReference)getTGlobalUserTask().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTGlobalUserTask_Implementation() {
        return (EAttribute)getTGlobalUserTask().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTGroup() {
		if (tGroupEClass == null) {
			tGroupEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(82);
		}
		return tGroupEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTGroup_CategoryValueRef() {
        return (EAttribute)getTGroup().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTHumanPerformer() {
		if (tHumanPerformerEClass == null) {
			tHumanPerformerEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(83);
		}
		return tHumanPerformerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTImplicitThrowEvent() {
		if (tImplicitThrowEventEClass == null) {
			tImplicitThrowEventEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(87);
		}
		return tImplicitThrowEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTImport() {
		if (tImportEClass == null) {
			tImportEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(88);
		}
		return tImportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTImport_ImportType() {
        return (EAttribute)getTImport().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTImport_Location() {
        return (EAttribute)getTImport().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTImport_Namespace() {
        return (EAttribute)getTImport().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTInclusiveGateway() {
		if (tInclusiveGatewayEClass == null) {
			tInclusiveGatewayEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(89);
		}
		return tInclusiveGatewayEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTInclusiveGateway_Default() {
        return (EAttribute)getTInclusiveGateway().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTInputOutputBinding() {
		if (tInputOutputBindingEClass == null) {
			tInputOutputBindingEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(90);
		}
		return tInputOutputBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTInputOutputBinding_InputDataRef() {
        return (EAttribute)getTInputOutputBinding().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTInputOutputBinding_OperationRef() {
        return (EAttribute)getTInputOutputBinding().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTInputOutputBinding_OutputDataRef() {
        return (EAttribute)getTInputOutputBinding().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTInputOutputSpecification() {
		if (tInputOutputSpecificationEClass == null) {
			tInputOutputSpecificationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(91);
		}
		return tInputOutputSpecificationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTInputOutputSpecification_DataInput() {
        return (EReference)getTInputOutputSpecification().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTInputOutputSpecification_DataOutput() {
        return (EReference)getTInputOutputSpecification().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTInputOutputSpecification_InputSet() {
        return (EReference)getTInputOutputSpecification().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTInputOutputSpecification_OutputSet() {
        return (EReference)getTInputOutputSpecification().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTInputSet() {
		if (tInputSetEClass == null) {
			tInputSetEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(92);
		}
		return tInputSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTInputSet_DataInputRefs() {
        return (EAttribute)getTInputSet().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTInputSet_OptionalInputRefs() {
        return (EAttribute)getTInputSet().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTInputSet_WhileExecutingInputRefs() {
        return (EAttribute)getTInputSet().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTInputSet_OutputSetRefs() {
        return (EAttribute)getTInputSet().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTInputSet_Name() {
        return (EAttribute)getTInputSet().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTInterface() {
		if (tInterfaceEClass == null) {
			tInterfaceEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(93);
		}
		return tInterfaceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTInterface_Operation() {
        return (EReference)getTInterface().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTInterface_ImplementationRef() {
        return (EAttribute)getTInterface().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTInterface_Name() {
        return (EAttribute)getTInterface().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTIntermediateCatchEvent() {
		if (tIntermediateCatchEventEClass == null) {
			tIntermediateCatchEventEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(94);
		}
		return tIntermediateCatchEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTIntermediateThrowEvent() {
		if (tIntermediateThrowEventEClass == null) {
			tIntermediateThrowEventEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(95);
		}
		return tIntermediateThrowEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTItemDefinition() {
		if (tItemDefinitionEClass == null) {
			tItemDefinitionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(96);
		}
		return tItemDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTItemDefinition_IsCollection() {
        return (EAttribute)getTItemDefinition().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTItemDefinition_ItemKind() {
        return (EAttribute)getTItemDefinition().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTItemDefinition_StructureRef() {
        return (EAttribute)getTItemDefinition().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTLane() {
		if (tLaneEClass == null) {
			tLaneEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(99);
		}
		return tLaneEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTLane_PartitionElement() {
        return (EReference)getTLane().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTLane_FlowNodeRef() {
        return (EAttribute)getTLane().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTLane_ChildLaneSet() {
        return (EReference)getTLane().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTLane_Name() {
        return (EAttribute)getTLane().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTLane_PartitionElementRef() {
        return (EAttribute)getTLane().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTLaneSet() {
		if (tLaneSetEClass == null) {
			tLaneSetEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(100);
		}
		return tLaneSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTLaneSet_Lane() {
        return (EReference)getTLaneSet().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTLaneSet_Name() {
        return (EAttribute)getTLaneSet().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTLinkEventDefinition() {
		if (tLinkEventDefinitionEClass == null) {
			tLinkEventDefinitionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(101);
		}
		return tLinkEventDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTLinkEventDefinition_Source() {
        return (EAttribute)getTLinkEventDefinition().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTLinkEventDefinition_Target() {
        return (EAttribute)getTLinkEventDefinition().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTLinkEventDefinition_Name() {
        return (EAttribute)getTLinkEventDefinition().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTLoopCharacteristics() {
		if (tLoopCharacteristicsEClass == null) {
			tLoopCharacteristicsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(102);
		}
		return tLoopCharacteristicsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTManualTask() {
		if (tManualTaskEClass == null) {
			tManualTaskEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(103);
		}
		return tManualTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTMessage() {
		if (tMessageEClass == null) {
			tMessageEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(104);
		}
		return tMessageEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTMessage_ItemRef() {
        return (EAttribute)getTMessage().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTMessage_Name() {
        return (EAttribute)getTMessage().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTMessageEventDefinition() {
		if (tMessageEventDefinitionEClass == null) {
			tMessageEventDefinitionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(105);
		}
		return tMessageEventDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTMessageEventDefinition_OperationRef() {
        return (EAttribute)getTMessageEventDefinition().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTMessageEventDefinition_MessageRef() {
        return (EAttribute)getTMessageEventDefinition().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTMessageFlow() {
		if (tMessageFlowEClass == null) {
			tMessageFlowEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(106);
		}
		return tMessageFlowEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTMessageFlow_MessageRef() {
        return (EAttribute)getTMessageFlow().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTMessageFlow_Name() {
        return (EAttribute)getTMessageFlow().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTMessageFlow_SourceRef() {
        return (EAttribute)getTMessageFlow().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTMessageFlow_TargetRef() {
        return (EAttribute)getTMessageFlow().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTMessageFlowAssociation() {
		if (tMessageFlowAssociationEClass == null) {
			tMessageFlowAssociationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(107);
		}
		return tMessageFlowAssociationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTMessageFlowAssociation_InnerMessageFlowRef() {
        return (EAttribute)getTMessageFlowAssociation().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTMessageFlowAssociation_OuterMessageFlowRef() {
        return (EAttribute)getTMessageFlowAssociation().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTMonitoring() {
		if (tMonitoringEClass == null) {
			tMonitoringEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(108);
		}
		return tMonitoringEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTMultiInstanceLoopCharacteristics() {
		if (tMultiInstanceLoopCharacteristicsEClass == null) {
			tMultiInstanceLoopCharacteristicsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(111);
		}
		return tMultiInstanceLoopCharacteristicsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTMultiInstanceLoopCharacteristics_LoopCardinality() {
        return (EReference)getTMultiInstanceLoopCharacteristics().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTMultiInstanceLoopCharacteristics_LoopDataInputRef() {
        return (EAttribute)getTMultiInstanceLoopCharacteristics().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTMultiInstanceLoopCharacteristics_LoopDataOutputRef() {
        return (EAttribute)getTMultiInstanceLoopCharacteristics().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTMultiInstanceLoopCharacteristics_InputDataItem() {
        return (EReference)getTMultiInstanceLoopCharacteristics().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTMultiInstanceLoopCharacteristics_OutputDataItem() {
        return (EReference)getTMultiInstanceLoopCharacteristics().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTMultiInstanceLoopCharacteristics_ComplexBehaviorDefinition() {
        return (EReference)getTMultiInstanceLoopCharacteristics().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTMultiInstanceLoopCharacteristics_CompletionCondition() {
        return (EReference)getTMultiInstanceLoopCharacteristics().getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTMultiInstanceLoopCharacteristics_Behavior() {
        return (EAttribute)getTMultiInstanceLoopCharacteristics().getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTMultiInstanceLoopCharacteristics_IsSequential() {
        return (EAttribute)getTMultiInstanceLoopCharacteristics().getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTMultiInstanceLoopCharacteristics_NoneBehaviorEventRef() {
        return (EAttribute)getTMultiInstanceLoopCharacteristics().getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTMultiInstanceLoopCharacteristics_OneBehaviorEventRef() {
        return (EAttribute)getTMultiInstanceLoopCharacteristics().getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTOperation() {
		if (tOperationEClass == null) {
			tOperationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(112);
		}
		return tOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTOperation_InMessageRef() {
        return (EAttribute)getTOperation().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTOperation_OutMessageRef() {
        return (EAttribute)getTOperation().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTOperation_ErrorRef() {
        return (EAttribute)getTOperation().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTOperation_ImplementationRef() {
        return (EAttribute)getTOperation().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTOperation_Name() {
        return (EAttribute)getTOperation().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTOutputSet() {
		if (tOutputSetEClass == null) {
			tOutputSetEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(113);
		}
		return tOutputSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTOutputSet_DataOutputRefs() {
        return (EAttribute)getTOutputSet().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTOutputSet_OptionalOutputRefs() {
        return (EAttribute)getTOutputSet().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTOutputSet_WhileExecutingOutputRefs() {
        return (EAttribute)getTOutputSet().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTOutputSet_InputSetRefs() {
        return (EAttribute)getTOutputSet().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTOutputSet_Name() {
        return (EAttribute)getTOutputSet().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTParallelGateway() {
		if (tParallelGatewayEClass == null) {
			tParallelGatewayEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(114);
		}
		return tParallelGatewayEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTParticipant() {
		if (tParticipantEClass == null) {
			tParticipantEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(115);
		}
		return tParticipantEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTParticipant_InterfaceRef() {
        return (EAttribute)getTParticipant().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTParticipant_EndPointRef() {
        return (EAttribute)getTParticipant().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTParticipant_ParticipantMultiplicity() {
        return (EReference)getTParticipant().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTParticipant_Name() {
        return (EAttribute)getTParticipant().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTParticipant_ProcessRef() {
        return (EAttribute)getTParticipant().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTParticipantAssociation() {
		if (tParticipantAssociationEClass == null) {
			tParticipantAssociationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(116);
		}
		return tParticipantAssociationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTParticipantAssociation_InnerParticipantRef() {
        return (EAttribute)getTParticipantAssociation().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTParticipantAssociation_OuterParticipantRef() {
        return (EAttribute)getTParticipantAssociation().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTParticipantMultiplicity() {
		if (tParticipantMultiplicityEClass == null) {
			tParticipantMultiplicityEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(117);
		}
		return tParticipantMultiplicityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTParticipantMultiplicity_Maximum() {
        return (EAttribute)getTParticipantMultiplicity().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTParticipantMultiplicity_Minimum() {
        return (EAttribute)getTParticipantMultiplicity().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTPartnerEntity() {
		if (tPartnerEntityEClass == null) {
			tPartnerEntityEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(118);
		}
		return tPartnerEntityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTPartnerEntity_ParticipantRef() {
        return (EAttribute)getTPartnerEntity().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTPartnerEntity_Name() {
        return (EAttribute)getTPartnerEntity().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTPartnerRole() {
		if (tPartnerRoleEClass == null) {
			tPartnerRoleEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(119);
		}
		return tPartnerRoleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTPartnerRole_ParticipantRef() {
        return (EAttribute)getTPartnerRole().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTPartnerRole_Name() {
        return (EAttribute)getTPartnerRole().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTPerformer() {
		if (tPerformerEClass == null) {
			tPerformerEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(120);
		}
		return tPerformerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTPotentialOwner() {
		if (tPotentialOwnerEClass == null) {
			tPotentialOwnerEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(121);
		}
		return tPotentialOwnerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTProcess() {
		if (tProcessEClass == null) {
			tProcessEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(122);
		}
		return tProcessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTProcess_Auditing() {
        return (EReference)getTProcess().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTProcess_Monitoring() {
        return (EReference)getTProcess().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTProcess_Property() {
        return (EReference)getTProcess().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTProcess_LaneSet() {
        return (EReference)getTProcess().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTProcess_FlowElementGroup() {
        return (EAttribute)getTProcess().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTProcess_FlowElement() {
        return (EReference)getTProcess().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTProcess_ArtifactGroup() {
        return (EAttribute)getTProcess().getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTProcess_Artifact() {
        return (EReference)getTProcess().getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTProcess_ResourceRoleGroup() {
        return (EAttribute)getTProcess().getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTProcess_ResourceRole() {
        return (EReference)getTProcess().getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTProcess_CorrelationSubscription() {
        return (EReference)getTProcess().getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTProcess_Supports() {
        return (EAttribute)getTProcess().getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTProcess_DefinitionalCollaborationRef() {
        return (EAttribute)getTProcess().getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTProcess_IsClosed() {
        return (EAttribute)getTProcess().getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTProcess_IsExecutable() {
        return (EAttribute)getTProcess().getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTProcess_ProcessType() {
        return (EAttribute)getTProcess().getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTProperty() {
		if (tPropertyEClass == null) {
			tPropertyEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(125);
		}
		return tPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTProperty_DataState() {
        return (EReference)getTProperty().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTProperty_ItemSubjectRef() {
        return (EAttribute)getTProperty().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTProperty_Name() {
        return (EAttribute)getTProperty().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTReceiveTask() {
		if (tReceiveTaskEClass == null) {
			tReceiveTaskEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(126);
		}
		return tReceiveTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTReceiveTask_Implementation() {
        return (EAttribute)getTReceiveTask().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTReceiveTask_Instantiate() {
        return (EAttribute)getTReceiveTask().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTReceiveTask_MessageRef() {
        return (EAttribute)getTReceiveTask().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTReceiveTask_OperationRef() {
        return (EAttribute)getTReceiveTask().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTRelationship() {
		if (tRelationshipEClass == null) {
			tRelationshipEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(127);
		}
		return tRelationshipEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTRelationship_Source() {
        return (EAttribute)getTRelationship().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTRelationship_Target() {
        return (EAttribute)getTRelationship().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTRelationship_Direction() {
        return (EAttribute)getTRelationship().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTRelationship_Type() {
        return (EAttribute)getTRelationship().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTRendering() {
		if (tRenderingEClass == null) {
			tRenderingEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(130);
		}
		return tRenderingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTResource() {
		if (tResourceEClass == null) {
			tResourceEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(131);
		}
		return tResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTResource_ResourceParameter() {
        return (EReference)getTResource().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTResource_Name() {
        return (EAttribute)getTResource().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTResourceAssignmentExpression() {
		if (tResourceAssignmentExpressionEClass == null) {
			tResourceAssignmentExpressionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(132);
		}
		return tResourceAssignmentExpressionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTResourceAssignmentExpression_ExpressionGroup() {
        return (EAttribute)getTResourceAssignmentExpression().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTResourceAssignmentExpression_Expression() {
        return (EReference)getTResourceAssignmentExpression().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTResourceParameter() {
		if (tResourceParameterEClass == null) {
			tResourceParameterEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(133);
		}
		return tResourceParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTResourceParameter_IsRequired() {
        return (EAttribute)getTResourceParameter().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTResourceParameter_Name() {
        return (EAttribute)getTResourceParameter().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTResourceParameter_Type() {
        return (EAttribute)getTResourceParameter().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTResourceParameterBinding() {
		if (tResourceParameterBindingEClass == null) {
			tResourceParameterBindingEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(134);
		}
		return tResourceParameterBindingEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTResourceParameterBinding_ExpressionGroup() {
        return (EAttribute)getTResourceParameterBinding().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTResourceParameterBinding_Expression() {
        return (EReference)getTResourceParameterBinding().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTResourceParameterBinding_ParameterRef() {
        return (EAttribute)getTResourceParameterBinding().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTResourceRole() {
		if (tResourceRoleEClass == null) {
			tResourceRoleEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(135);
		}
		return tResourceRoleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTResourceRole_ResourceRef() {
        return (EAttribute)getTResourceRole().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTResourceRole_ResourceParameterBinding() {
        return (EReference)getTResourceRole().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTResourceRole_ResourceAssignmentExpression() {
        return (EReference)getTResourceRole().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTResourceRole_Name() {
        return (EAttribute)getTResourceRole().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTRootElement() {
		if (tRootElementEClass == null) {
			tRootElementEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(136);
		}
		return tRootElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTScript() {
		if (tScriptEClass == null) {
			tScriptEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(137);
		}
		return tScriptEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTScript_Mixed() {
        return (EAttribute)getTScript().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTScript_Any() {
        return (EAttribute)getTScript().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTScriptTask() {
		if (tScriptTaskEClass == null) {
			tScriptTaskEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(138);
		}
		return tScriptTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTScriptTask_Script() {
        return (EReference)getTScriptTask().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTScriptTask_ScriptFormat() {
        return (EAttribute)getTScriptTask().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTSendTask() {
		if (tSendTaskEClass == null) {
			tSendTaskEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(139);
		}
		return tSendTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSendTask_Implementation() {
        return (EAttribute)getTSendTask().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSendTask_MessageRef() {
        return (EAttribute)getTSendTask().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSendTask_OperationRef() {
        return (EAttribute)getTSendTask().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTSequenceFlow() {
		if (tSequenceFlowEClass == null) {
			tSequenceFlowEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(140);
		}
		return tSequenceFlowEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTSequenceFlow_ConditionExpression() {
        return (EReference)getTSequenceFlow().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSequenceFlow_IsImmediate() {
        return (EAttribute)getTSequenceFlow().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSequenceFlow_SourceRef() {
        return (EAttribute)getTSequenceFlow().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSequenceFlow_TargetRef() {
        return (EAttribute)getTSequenceFlow().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTServiceTask() {
		if (tServiceTaskEClass == null) {
			tServiceTaskEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(141);
		}
		return tServiceTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTServiceTask_Implementation() {
        return (EAttribute)getTServiceTask().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTServiceTask_OperationRef() {
        return (EAttribute)getTServiceTask().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTSignal() {
		if (tSignalEClass == null) {
			tSignalEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(142);
		}
		return tSignalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSignal_Name() {
        return (EAttribute)getTSignal().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSignal_StructureRef() {
        return (EAttribute)getTSignal().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTSignalEventDefinition() {
		if (tSignalEventDefinitionEClass == null) {
			tSignalEventDefinitionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(143);
		}
		return tSignalEventDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSignalEventDefinition_SignalRef() {
        return (EAttribute)getTSignalEventDefinition().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTStandardLoopCharacteristics() {
		if (tStandardLoopCharacteristicsEClass == null) {
			tStandardLoopCharacteristicsEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(144);
		}
		return tStandardLoopCharacteristicsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTStandardLoopCharacteristics_LoopCondition() {
        return (EReference)getTStandardLoopCharacteristics().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTStandardLoopCharacteristics_LoopMaximum() {
        return (EAttribute)getTStandardLoopCharacteristics().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTStandardLoopCharacteristics_TestBefore() {
        return (EAttribute)getTStandardLoopCharacteristics().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTStartEvent() {
		if (tStartEventEClass == null) {
			tStartEventEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(145);
		}
		return tStartEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTStartEvent_IsInterrupting() {
        return (EAttribute)getTStartEvent().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTSubChoreography() {
		if (tSubChoreographyEClass == null) {
			tSubChoreographyEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(146);
		}
		return tSubChoreographyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSubChoreography_FlowElementGroup() {
        return (EAttribute)getTSubChoreography().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTSubChoreography_FlowElement() {
        return (EReference)getTSubChoreography().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSubChoreography_ArtifactGroup() {
        return (EAttribute)getTSubChoreography().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTSubChoreography_Artifact() {
        return (EReference)getTSubChoreography().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTSubConversation() {
		if (tSubConversationEClass == null) {
			tSubConversationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(147);
		}
		return tSubConversationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSubConversation_ConversationNodeGroup() {
        return (EAttribute)getTSubConversation().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTSubConversation_ConversationNode() {
        return (EReference)getTSubConversation().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTSubProcess() {
		if (tSubProcessEClass == null) {
			tSubProcessEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(148);
		}
		return tSubProcessEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTSubProcess_LaneSet() {
        return (EReference)getTSubProcess().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSubProcess_FlowElementGroup() {
        return (EAttribute)getTSubProcess().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTSubProcess_FlowElement() {
        return (EReference)getTSubProcess().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSubProcess_ArtifactGroup() {
        return (EAttribute)getTSubProcess().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTSubProcess_Artifact() {
        return (EReference)getTSubProcess().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTSubProcess_TriggeredByEvent() {
        return (EAttribute)getTSubProcess().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTTask() {
		if (tTaskEClass == null) {
			tTaskEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(149);
		}
		return tTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTTerminateEventDefinition() {
		if (tTerminateEventDefinitionEClass == null) {
			tTerminateEventDefinitionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(150);
		}
		return tTerminateEventDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTText() {
		if (tTextEClass == null) {
			tTextEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(151);
		}
		return tTextEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTText_Mixed() {
        return (EAttribute)getTText().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTText_Any() {
        return (EAttribute)getTText().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTTextAnnotation() {
		if (tTextAnnotationEClass == null) {
			tTextAnnotationEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(152);
		}
		return tTextAnnotationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTTextAnnotation_Text() {
        return (EReference)getTTextAnnotation().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTTextAnnotation_TextFormat() {
        return (EAttribute)getTTextAnnotation().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTThrowEvent() {
		if (tThrowEventEClass == null) {
			tThrowEventEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(153);
		}
		return tThrowEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTThrowEvent_DataInput() {
        return (EReference)getTThrowEvent().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTThrowEvent_DataInputAssociation() {
        return (EReference)getTThrowEvent().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTThrowEvent_InputSet() {
        return (EReference)getTThrowEvent().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTThrowEvent_EventDefinitionGroup() {
        return (EAttribute)getTThrowEvent().getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTThrowEvent_EventDefinition() {
        return (EReference)getTThrowEvent().getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTThrowEvent_EventDefinitionRef() {
        return (EAttribute)getTThrowEvent().getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTTimerEventDefinition() {
		if (tTimerEventDefinitionEClass == null) {
			tTimerEventDefinitionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(154);
		}
		return tTimerEventDefinitionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTTimerEventDefinition_TimeDate() {
        return (EReference)getTTimerEventDefinition().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTTimerEventDefinition_TimeDuration() {
        return (EReference)getTTimerEventDefinition().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTTimerEventDefinition_TimeCycle() {
        return (EReference)getTTimerEventDefinition().getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTTransaction() {
		if (tTransactionEClass == null) {
			tTransactionEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(155);
		}
		return tTransactionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTTransaction_Method() {
        return (EAttribute)getTTransaction().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTUserTask() {
		if (tUserTaskEClass == null) {
			tUserTaskEClass = (EClass)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(159);
		}
		return tUserTaskEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTUserTask_Rendering() {
        return (EReference)getTUserTask().getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTUserTask_Implementation() {
        return (EAttribute)getTUserTask().getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTAdHocOrdering() {
		if (tAdHocOrderingEEnum == null) {
			tAdHocOrderingEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(2);
		}
		return tAdHocOrderingEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTAssociationDirection() {
		if (tAssociationDirectionEEnum == null) {
			tAssociationDirectionEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(8);
		}
		return tAssociationDirectionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTChoreographyLoopType() {
		if (tChoreographyLoopTypeEEnum == null) {
			tChoreographyLoopTypeEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(25);
		}
		return tChoreographyLoopTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTEventBasedGatewayType() {
		if (tEventBasedGatewayTypeEEnum == null) {
			tEventBasedGatewayTypeEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(62);
		}
		return tEventBasedGatewayTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTGatewayDirection() {
		if (tGatewayDirectionEEnum == null) {
			tGatewayDirectionEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(73);
		}
		return tGatewayDirectionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTImplementationMember1() {
		if (tImplementationMember1EEnum == null) {
			tImplementationMember1EEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(85);
		}
		return tImplementationMember1EEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTItemKind() {
		if (tItemKindEEnum == null) {
			tItemKindEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(97);
		}
		return tItemKindEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTMultiInstanceFlowCondition() {
		if (tMultiInstanceFlowConditionEEnum == null) {
			tMultiInstanceFlowConditionEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(109);
		}
		return tMultiInstanceFlowConditionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTProcessType() {
		if (tProcessTypeEEnum == null) {
			tProcessTypeEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(123);
		}
		return tProcessTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTRelationshipDirection() {
		if (tRelationshipDirectionEEnum == null) {
			tRelationshipDirectionEEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(128);
		}
		return tRelationshipDirectionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getTTransactionMethodMember1() {
		if (tTransactionMethodMember1EEnum == null) {
			tTransactionMethodMember1EEnum = (EEnum)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(157);
		}
		return tTransactionMethodMember1EEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTAdHocOrderingObject() {
		if (tAdHocOrderingObjectEDataType == null) {
			tAdHocOrderingObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(3);
		}
		return tAdHocOrderingObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTAssociationDirectionObject() {
		if (tAssociationDirectionObjectEDataType == null) {
			tAssociationDirectionObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(9);
		}
		return tAssociationDirectionObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTChoreographyLoopTypeObject() {
		if (tChoreographyLoopTypeObjectEDataType == null) {
			tChoreographyLoopTypeObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(26);
		}
		return tChoreographyLoopTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTEventBasedGatewayTypeObject() {
		if (tEventBasedGatewayTypeObjectEDataType == null) {
			tEventBasedGatewayTypeObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(63);
		}
		return tEventBasedGatewayTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTGatewayDirectionObject() {
		if (tGatewayDirectionObjectEDataType == null) {
			tGatewayDirectionObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(74);
		}
		return tGatewayDirectionObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTImplementation() {
		if (tImplementationEDataType == null) {
			tImplementationEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(84);
		}
		return tImplementationEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTImplementationMember1Object() {
		if (tImplementationMember1ObjectEDataType == null) {
			tImplementationMember1ObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(86);
		}
		return tImplementationMember1ObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTItemKindObject() {
		if (tItemKindObjectEDataType == null) {
			tItemKindObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(98);
		}
		return tItemKindObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTMultiInstanceFlowConditionObject() {
		if (tMultiInstanceFlowConditionObjectEDataType == null) {
			tMultiInstanceFlowConditionObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(110);
		}
		return tMultiInstanceFlowConditionObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTProcessTypeObject() {
		if (tProcessTypeObjectEDataType == null) {
			tProcessTypeObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(124);
		}
		return tProcessTypeObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTRelationshipDirectionObject() {
		if (tRelationshipDirectionObjectEDataType == null) {
			tRelationshipDirectionObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(129);
		}
		return tRelationshipDirectionObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTTransactionMethod() {
		if (tTransactionMethodEDataType == null) {
			tTransactionMethodEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(156);
		}
		return tTransactionMethodEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EDataType getTTransactionMethodMember1Object() {
		if (tTransactionMethodMember1ObjectEDataType == null) {
			tTransactionMethodMember1ObjectEDataType = (EDataType)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI).getEClassifiers().get(158);
		}
		return tTransactionMethodMember1ObjectEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelFactory getModelFactory() {
		return (ModelFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isLoaded = false;

	/**
	 * Laods the package and any sub-packages from their serialized form.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void loadPackage() {
		if (isLoaded) return;
		isLoaded = true;

		URL url = getClass().getResource(packageFilename);
		if (url == null) {
			throw new RuntimeException("Missing serialized package: " + packageFilename);
		}
		URI uri = URI.createURI(url.toString());
		Resource resource = new EcoreResourceFactoryImpl().createResource(uri);
		try {
			resource.load(null);
		}
		catch (IOException exception) {
			throw new WrappedException(exception);
		}
		initializeFromLoadedEPackage(this, (EPackage)resource.getContents().get(0));
		createResource(eNS_URI);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isFixed = false;

	/**
	 * Fixes up the loaded package, to make it appear as if it had been programmatically built.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void fixPackageContents() {
		if (isFixed) return;
		isFixed = true;
		fixEClassifiers();
	}

	/**
	 * Sets the instance class on the given classifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void fixInstanceClass(EClassifier eClassifier) {
		if (eClassifier.getInstanceClassName() == null) {
			eClassifier.setInstanceClassName("org.omg.spec.bpmn.model." + eClassifier.getName());
			setGeneratedClassName(eClassifier);
		}
	}

} //ModelPackageImpl
