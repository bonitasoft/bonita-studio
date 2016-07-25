/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getActivity <em>Activity</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getAdHocSubProcess <em>Ad Hoc Sub Process</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getFlowElement <em>Flow Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getArtifact <em>Artifact</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getAssignment <em>Assignment</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getAssociation <em>Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getAuditing <em>Auditing</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getBaseElement <em>Base Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getBaseElementWithMixedContent <em>Base Element With Mixed Content</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getBoundaryEvent <em>Boundary Event</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getBusinessRuleTask <em>Business Rule Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getCallableElement <em>Callable Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getCallActivity <em>Call Activity</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getCallChoreography <em>Call Choreography</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getCallConversation <em>Call Conversation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getConversationNode <em>Conversation Node</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getCancelEventDefinition <em>Cancel Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getEventDefinition <em>Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getRootElement <em>Root Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getCatchEvent <em>Catch Event</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getCategory <em>Category</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getCategoryValue <em>Category Value</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getChoreography <em>Choreography</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getCollaboration <em>Collaboration</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getChoreographyActivity <em>Choreography Activity</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getChoreographyTask <em>Choreography Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getCompensateEventDefinition <em>Compensate Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getComplexBehaviorDefinition <em>Complex Behavior Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getComplexGateway <em>Complex Gateway</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getConditionalEventDefinition <em>Conditional Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getConversation <em>Conversation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getConversationAssociation <em>Conversation Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getConversationLink <em>Conversation Link</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getCorrelationKey <em>Correlation Key</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getCorrelationProperty <em>Correlation Property</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getCorrelationPropertyBinding <em>Correlation Property Binding</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getCorrelationPropertyRetrievalExpression <em>Correlation Property Retrieval Expression</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getCorrelationSubscription <em>Correlation Subscription</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getDataAssociation <em>Data Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getDataInput <em>Data Input</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getDataInputAssociation <em>Data Input Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getDataObject <em>Data Object</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getDataObjectReference <em>Data Object Reference</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getDataOutput <em>Data Output</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getDataOutputAssociation <em>Data Output Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getDataState <em>Data State</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getDataStore <em>Data Store</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getDataStoreReference <em>Data Store Reference</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getDefinitions <em>Definitions</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getEndEvent <em>End Event</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getEndPoint <em>End Point</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getError <em>Error</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getErrorEventDefinition <em>Error Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getEscalation <em>Escalation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getEscalationEventDefinition <em>Escalation Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getEvent <em>Event</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getEventBasedGateway <em>Event Based Gateway</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getExclusiveGateway <em>Exclusive Gateway</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getExtension <em>Extension</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getExtensionElements <em>Extension Elements</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getFlowNode <em>Flow Node</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getFormalExpression <em>Formal Expression</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getGateway <em>Gateway</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getGlobalBusinessRuleTask <em>Global Business Rule Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getGlobalChoreographyTask <em>Global Choreography Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getGlobalConversation <em>Global Conversation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getGlobalManualTask <em>Global Manual Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getGlobalScriptTask <em>Global Script Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getGlobalTask <em>Global Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getGlobalUserTask <em>Global User Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getGroup <em>Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getHumanPerformer <em>Human Performer</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getPerformer <em>Performer</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getResourceRole <em>Resource Role</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getImplicitThrowEvent <em>Implicit Throw Event</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getImport <em>Import</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getInclusiveGateway <em>Inclusive Gateway</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getInputSet <em>Input Set</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getInterface <em>Interface</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getIntermediateCatchEvent <em>Intermediate Catch Event</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getIntermediateThrowEvent <em>Intermediate Throw Event</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getIoBinding <em>Io Binding</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getIoSpecification <em>Io Specification</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getItemDefinition <em>Item Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getLane <em>Lane</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getLaneSet <em>Lane Set</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getLinkEventDefinition <em>Link Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getLoopCharacteristics <em>Loop Characteristics</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getManualTask <em>Manual Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getMessage <em>Message</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getMessageEventDefinition <em>Message Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getMessageFlow <em>Message Flow</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getMessageFlowAssociation <em>Message Flow Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getMonitoring <em>Monitoring</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getMultiInstanceLoopCharacteristics <em>Multi Instance Loop Characteristics</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getOperation <em>Operation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getOutputSet <em>Output Set</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getParallelGateway <em>Parallel Gateway</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getParticipant <em>Participant</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getParticipantAssociation <em>Participant Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getParticipantMultiplicity <em>Participant Multiplicity</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getPartnerEntity <em>Partner Entity</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getPartnerRole <em>Partner Role</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getPotentialOwner <em>Potential Owner</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getProcess <em>Process</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getProperty <em>Property</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getReceiveTask <em>Receive Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getRelationship <em>Relationship</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getRendering <em>Rendering</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getResource <em>Resource</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getResourceAssignmentExpression <em>Resource Assignment Expression</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getResourceParameter <em>Resource Parameter</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getResourceParameterBinding <em>Resource Parameter Binding</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getScript <em>Script</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getScriptTask <em>Script Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getSendTask <em>Send Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getSequenceFlow <em>Sequence Flow</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getServiceTask <em>Service Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getSignal <em>Signal</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getSignalEventDefinition <em>Signal Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getStandardLoopCharacteristics <em>Standard Loop Characteristics</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getStartEvent <em>Start Event</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getSubChoreography <em>Sub Choreography</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getSubConversation <em>Sub Conversation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getSubProcess <em>Sub Process</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getTask <em>Task</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getTerminateEventDefinition <em>Terminate Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getText <em>Text</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getTextAnnotation <em>Text Annotation</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getThrowEvent <em>Throw Event</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getTimerEventDefinition <em>Timer Event Definition</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getTransaction <em>Transaction</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.DocumentRoot#getUserTask <em>User Task</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot()
 * @model extendedMetaData="name='' kind='mixed'"
 * @generated
 */
public interface DocumentRoot extends EObject {
	/**
	 * Returns the value of the '<em><b>Mixed</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mixed</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mixed</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Mixed()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='elementWildcard' name=':mixed'"
	 * @generated
	 */
	FeatureMap getMixed();

	/**
	 * Returns the value of the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XMLNS Prefix Map</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XMLNS Prefix Map</em>' map.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_XMLNSPrefixMap()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xmlns:prefix'"
	 * @generated
	 */
	EMap<String, String> getXMLNSPrefixMap();

	/**
	 * Returns the value of the '<em><b>XSI Schema Location</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XSI Schema Location</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XSI Schema Location</em>' map.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_XSISchemaLocation()
	 * @model mapType="org.eclipse.emf.ecore.EStringToStringMapEntry<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>" transient="true"
	 *        extendedMetaData="kind='attribute' name='xsi:schemaLocation'"
	 * @generated
	 */
	EMap<String, String> getXSISchemaLocation();

	/**
	 * Returns the value of the '<em><b>Activity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity</em>' containment reference.
	 * @see #setActivity(TActivity)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Activity()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='activity' namespace='##targetNamespace'"
	 * @generated
	 */
	TActivity getActivity();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getActivity <em>Activity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity</em>' containment reference.
	 * @see #getActivity()
	 * @generated
	 */
	void setActivity(TActivity value);

	/**
	 * Returns the value of the '<em><b>Ad Hoc Sub Process</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ad Hoc Sub Process</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ad Hoc Sub Process</em>' containment reference.
	 * @see #setAdHocSubProcess(TAdHocSubProcess)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_AdHocSubProcess()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='adHocSubProcess' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TAdHocSubProcess getAdHocSubProcess();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getAdHocSubProcess <em>Ad Hoc Sub Process</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ad Hoc Sub Process</em>' containment reference.
	 * @see #getAdHocSubProcess()
	 * @generated
	 */
	void setAdHocSubProcess(TAdHocSubProcess value);

	/**
	 * Returns the value of the '<em><b>Flow Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Flow Element</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Flow Element</em>' containment reference.
	 * @see #setFlowElement(TFlowElement)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_FlowElement()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='flowElement' namespace='##targetNamespace'"
	 * @generated
	 */
	TFlowElement getFlowElement();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getFlowElement <em>Flow Element</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Flow Element</em>' containment reference.
	 * @see #getFlowElement()
	 * @generated
	 */
	void setFlowElement(TFlowElement value);

	/**
	 * Returns the value of the '<em><b>Artifact</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Artifact</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifact</em>' containment reference.
	 * @see #setArtifact(TArtifact)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Artifact()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='artifact' namespace='##targetNamespace'"
	 * @generated
	 */
	TArtifact getArtifact();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getArtifact <em>Artifact</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Artifact</em>' containment reference.
	 * @see #getArtifact()
	 * @generated
	 */
	void setArtifact(TArtifact value);

	/**
	 * Returns the value of the '<em><b>Assignment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assignment</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assignment</em>' containment reference.
	 * @see #setAssignment(TAssignment)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Assignment()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='assignment' namespace='##targetNamespace'"
	 * @generated
	 */
	TAssignment getAssignment();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getAssignment <em>Assignment</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Assignment</em>' containment reference.
	 * @see #getAssignment()
	 * @generated
	 */
	void setAssignment(TAssignment value);

	/**
	 * Returns the value of the '<em><b>Association</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Association</em>' containment reference.
	 * @see #setAssociation(TAssociation)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Association()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='association' namespace='##targetNamespace' affiliation='artifact'"
	 * @generated
	 */
	TAssociation getAssociation();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getAssociation <em>Association</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Association</em>' containment reference.
	 * @see #getAssociation()
	 * @generated
	 */
	void setAssociation(TAssociation value);

	/**
	 * Returns the value of the '<em><b>Auditing</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Auditing</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Auditing</em>' containment reference.
	 * @see #setAuditing(TAuditing)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Auditing()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='auditing' namespace='##targetNamespace'"
	 * @generated
	 */
	TAuditing getAuditing();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getAuditing <em>Auditing</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auditing</em>' containment reference.
	 * @see #getAuditing()
	 * @generated
	 */
	void setAuditing(TAuditing value);

	/**
	 * Returns the value of the '<em><b>Base Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Element</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Element</em>' containment reference.
	 * @see #setBaseElement(TBaseElement)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_BaseElement()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='baseElement' namespace='##targetNamespace'"
	 * @generated
	 */
	TBaseElement getBaseElement();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getBaseElement <em>Base Element</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Element</em>' containment reference.
	 * @see #getBaseElement()
	 * @generated
	 */
	void setBaseElement(TBaseElement value);

	/**
	 * Returns the value of the '<em><b>Base Element With Mixed Content</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Element With Mixed Content</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Element With Mixed Content</em>' containment reference.
	 * @see #setBaseElementWithMixedContent(TBaseElementWithMixedContent)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_BaseElementWithMixedContent()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='baseElementWithMixedContent' namespace='##targetNamespace'"
	 * @generated
	 */
	TBaseElementWithMixedContent getBaseElementWithMixedContent();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getBaseElementWithMixedContent <em>Base Element With Mixed Content</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Element With Mixed Content</em>' containment reference.
	 * @see #getBaseElementWithMixedContent()
	 * @generated
	 */
	void setBaseElementWithMixedContent(TBaseElementWithMixedContent value);

	/**
	 * Returns the value of the '<em><b>Boundary Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Boundary Event</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Boundary Event</em>' containment reference.
	 * @see #setBoundaryEvent(TBoundaryEvent)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_BoundaryEvent()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='boundaryEvent' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TBoundaryEvent getBoundaryEvent();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getBoundaryEvent <em>Boundary Event</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Boundary Event</em>' containment reference.
	 * @see #getBoundaryEvent()
	 * @generated
	 */
	void setBoundaryEvent(TBoundaryEvent value);

	/**
	 * Returns the value of the '<em><b>Business Rule Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Business Rule Task</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Business Rule Task</em>' containment reference.
	 * @see #setBusinessRuleTask(TBusinessRuleTask)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_BusinessRuleTask()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='businessRuleTask' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TBusinessRuleTask getBusinessRuleTask();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getBusinessRuleTask <em>Business Rule Task</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Business Rule Task</em>' containment reference.
	 * @see #getBusinessRuleTask()
	 * @generated
	 */
	void setBusinessRuleTask(TBusinessRuleTask value);

	/**
	 * Returns the value of the '<em><b>Callable Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Callable Element</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Callable Element</em>' containment reference.
	 * @see #setCallableElement(TCallableElement)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_CallableElement()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='callableElement' namespace='##targetNamespace'"
	 * @generated
	 */
	TCallableElement getCallableElement();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getCallableElement <em>Callable Element</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Callable Element</em>' containment reference.
	 * @see #getCallableElement()
	 * @generated
	 */
	void setCallableElement(TCallableElement value);

	/**
	 * Returns the value of the '<em><b>Call Activity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Call Activity</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Call Activity</em>' containment reference.
	 * @see #setCallActivity(TCallActivity)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_CallActivity()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='callActivity' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TCallActivity getCallActivity();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getCallActivity <em>Call Activity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Call Activity</em>' containment reference.
	 * @see #getCallActivity()
	 * @generated
	 */
	void setCallActivity(TCallActivity value);

	/**
	 * Returns the value of the '<em><b>Call Choreography</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Call Choreography</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Call Choreography</em>' containment reference.
	 * @see #setCallChoreography(TCallChoreography)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_CallChoreography()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='callChoreography' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TCallChoreography getCallChoreography();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getCallChoreography <em>Call Choreography</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Call Choreography</em>' containment reference.
	 * @see #getCallChoreography()
	 * @generated
	 */
	void setCallChoreography(TCallChoreography value);

	/**
	 * Returns the value of the '<em><b>Call Conversation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Call Conversation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Call Conversation</em>' containment reference.
	 * @see #setCallConversation(TCallConversation)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_CallConversation()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='callConversation' namespace='##targetNamespace' affiliation='conversationNode'"
	 * @generated
	 */
	TCallConversation getCallConversation();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getCallConversation <em>Call Conversation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Call Conversation</em>' containment reference.
	 * @see #getCallConversation()
	 * @generated
	 */
	void setCallConversation(TCallConversation value);

	/**
	 * Returns the value of the '<em><b>Conversation Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Conversation Node</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conversation Node</em>' containment reference.
	 * @see #setConversationNode(TConversationNode)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ConversationNode()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='conversationNode' namespace='##targetNamespace'"
	 * @generated
	 */
	TConversationNode getConversationNode();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getConversationNode <em>Conversation Node</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Conversation Node</em>' containment reference.
	 * @see #getConversationNode()
	 * @generated
	 */
	void setConversationNode(TConversationNode value);

	/**
	 * Returns the value of the '<em><b>Cancel Event Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cancel Event Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cancel Event Definition</em>' containment reference.
	 * @see #setCancelEventDefinition(TCancelEventDefinition)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_CancelEventDefinition()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='cancelEventDefinition' namespace='##targetNamespace' affiliation='eventDefinition'"
	 * @generated
	 */
	TCancelEventDefinition getCancelEventDefinition();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getCancelEventDefinition <em>Cancel Event Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cancel Event Definition</em>' containment reference.
	 * @see #getCancelEventDefinition()
	 * @generated
	 */
	void setCancelEventDefinition(TCancelEventDefinition value);

	/**
	 * Returns the value of the '<em><b>Event Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Definition</em>' containment reference.
	 * @see #setEventDefinition(TEventDefinition)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_EventDefinition()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='eventDefinition' namespace='##targetNamespace' affiliation='rootElement'"
	 * @generated
	 */
	TEventDefinition getEventDefinition();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getEventDefinition <em>Event Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event Definition</em>' containment reference.
	 * @see #getEventDefinition()
	 * @generated
	 */
	void setEventDefinition(TEventDefinition value);

	/**
	 * Returns the value of the '<em><b>Root Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root Element</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root Element</em>' containment reference.
	 * @see #setRootElement(TRootElement)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_RootElement()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='rootElement' namespace='##targetNamespace'"
	 * @generated
	 */
	TRootElement getRootElement();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getRootElement <em>Root Element</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root Element</em>' containment reference.
	 * @see #getRootElement()
	 * @generated
	 */
	void setRootElement(TRootElement value);

	/**
	 * Returns the value of the '<em><b>Catch Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Catch Event</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Catch Event</em>' containment reference.
	 * @see #setCatchEvent(TCatchEvent)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_CatchEvent()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='catchEvent' namespace='##targetNamespace'"
	 * @generated
	 */
	TCatchEvent getCatchEvent();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getCatchEvent <em>Catch Event</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Catch Event</em>' containment reference.
	 * @see #getCatchEvent()
	 * @generated
	 */
	void setCatchEvent(TCatchEvent value);

	/**
	 * Returns the value of the '<em><b>Category</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Category</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Category</em>' containment reference.
	 * @see #setCategory(TCategory)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Category()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='category' namespace='##targetNamespace' affiliation='rootElement'"
	 * @generated
	 */
	TCategory getCategory();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getCategory <em>Category</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Category</em>' containment reference.
	 * @see #getCategory()
	 * @generated
	 */
	void setCategory(TCategory value);

	/**
	 * Returns the value of the '<em><b>Category Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Category Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Category Value</em>' containment reference.
	 * @see #setCategoryValue(TCategoryValue)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_CategoryValue()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='categoryValue' namespace='##targetNamespace'"
	 * @generated
	 */
	TCategoryValue getCategoryValue();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getCategoryValue <em>Category Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Category Value</em>' containment reference.
	 * @see #getCategoryValue()
	 * @generated
	 */
	void setCategoryValue(TCategoryValue value);

	/**
	 * Returns the value of the '<em><b>Choreography</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Choreography</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Choreography</em>' containment reference.
	 * @see #setChoreography(TChoreography)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Choreography()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='choreography' namespace='##targetNamespace' affiliation='collaboration'"
	 * @generated
	 */
	TChoreography getChoreography();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getChoreography <em>Choreography</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Choreography</em>' containment reference.
	 * @see #getChoreography()
	 * @generated
	 */
	void setChoreography(TChoreography value);

	/**
	 * Returns the value of the '<em><b>Collaboration</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collaboration</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Collaboration</em>' containment reference.
	 * @see #setCollaboration(TCollaboration)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Collaboration()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='collaboration' namespace='##targetNamespace' affiliation='rootElement'"
	 * @generated
	 */
	TCollaboration getCollaboration();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getCollaboration <em>Collaboration</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Collaboration</em>' containment reference.
	 * @see #getCollaboration()
	 * @generated
	 */
	void setCollaboration(TCollaboration value);

	/**
	 * Returns the value of the '<em><b>Choreography Activity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Choreography Activity</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Choreography Activity</em>' containment reference.
	 * @see #setChoreographyActivity(TChoreographyActivity)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ChoreographyActivity()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='choreographyActivity' namespace='##targetNamespace'"
	 * @generated
	 */
	TChoreographyActivity getChoreographyActivity();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getChoreographyActivity <em>Choreography Activity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Choreography Activity</em>' containment reference.
	 * @see #getChoreographyActivity()
	 * @generated
	 */
	void setChoreographyActivity(TChoreographyActivity value);

	/**
	 * Returns the value of the '<em><b>Choreography Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Choreography Task</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Choreography Task</em>' containment reference.
	 * @see #setChoreographyTask(TChoreographyTask)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ChoreographyTask()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='choreographyTask' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TChoreographyTask getChoreographyTask();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getChoreographyTask <em>Choreography Task</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Choreography Task</em>' containment reference.
	 * @see #getChoreographyTask()
	 * @generated
	 */
	void setChoreographyTask(TChoreographyTask value);

	/**
	 * Returns the value of the '<em><b>Compensate Event Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Compensate Event Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Compensate Event Definition</em>' containment reference.
	 * @see #setCompensateEventDefinition(TCompensateEventDefinition)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_CompensateEventDefinition()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='compensateEventDefinition' namespace='##targetNamespace' affiliation='eventDefinition'"
	 * @generated
	 */
	TCompensateEventDefinition getCompensateEventDefinition();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getCompensateEventDefinition <em>Compensate Event Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Compensate Event Definition</em>' containment reference.
	 * @see #getCompensateEventDefinition()
	 * @generated
	 */
	void setCompensateEventDefinition(TCompensateEventDefinition value);

	/**
	 * Returns the value of the '<em><b>Complex Behavior Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Complex Behavior Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Complex Behavior Definition</em>' containment reference.
	 * @see #setComplexBehaviorDefinition(TComplexBehaviorDefinition)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ComplexBehaviorDefinition()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='complexBehaviorDefinition' namespace='##targetNamespace'"
	 * @generated
	 */
	TComplexBehaviorDefinition getComplexBehaviorDefinition();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getComplexBehaviorDefinition <em>Complex Behavior Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Complex Behavior Definition</em>' containment reference.
	 * @see #getComplexBehaviorDefinition()
	 * @generated
	 */
	void setComplexBehaviorDefinition(TComplexBehaviorDefinition value);

	/**
	 * Returns the value of the '<em><b>Complex Gateway</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Complex Gateway</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Complex Gateway</em>' containment reference.
	 * @see #setComplexGateway(TComplexGateway)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ComplexGateway()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='complexGateway' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TComplexGateway getComplexGateway();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getComplexGateway <em>Complex Gateway</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Complex Gateway</em>' containment reference.
	 * @see #getComplexGateway()
	 * @generated
	 */
	void setComplexGateway(TComplexGateway value);

	/**
	 * Returns the value of the '<em><b>Conditional Event Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Conditional Event Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conditional Event Definition</em>' containment reference.
	 * @see #setConditionalEventDefinition(TConditionalEventDefinition)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ConditionalEventDefinition()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='conditionalEventDefinition' namespace='##targetNamespace' affiliation='eventDefinition'"
	 * @generated
	 */
	TConditionalEventDefinition getConditionalEventDefinition();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getConditionalEventDefinition <em>Conditional Event Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Conditional Event Definition</em>' containment reference.
	 * @see #getConditionalEventDefinition()
	 * @generated
	 */
	void setConditionalEventDefinition(TConditionalEventDefinition value);

	/**
	 * Returns the value of the '<em><b>Conversation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Conversation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conversation</em>' containment reference.
	 * @see #setConversation(TConversation)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Conversation()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='conversation' namespace='##targetNamespace' affiliation='conversationNode'"
	 * @generated
	 */
	TConversation getConversation();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getConversation <em>Conversation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Conversation</em>' containment reference.
	 * @see #getConversation()
	 * @generated
	 */
	void setConversation(TConversation value);

	/**
	 * Returns the value of the '<em><b>Conversation Association</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Conversation Association</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conversation Association</em>' containment reference.
	 * @see #setConversationAssociation(TConversationAssociation)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ConversationAssociation()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='conversationAssociation' namespace='##targetNamespace'"
	 * @generated
	 */
	TConversationAssociation getConversationAssociation();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getConversationAssociation <em>Conversation Association</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Conversation Association</em>' containment reference.
	 * @see #getConversationAssociation()
	 * @generated
	 */
	void setConversationAssociation(TConversationAssociation value);

	/**
	 * Returns the value of the '<em><b>Conversation Link</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Conversation Link</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conversation Link</em>' containment reference.
	 * @see #setConversationLink(TConversationLink)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ConversationLink()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='conversationLink' namespace='##targetNamespace'"
	 * @generated
	 */
	TConversationLink getConversationLink();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getConversationLink <em>Conversation Link</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Conversation Link</em>' containment reference.
	 * @see #getConversationLink()
	 * @generated
	 */
	void setConversationLink(TConversationLink value);

	/**
	 * Returns the value of the '<em><b>Correlation Key</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Correlation Key</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Correlation Key</em>' containment reference.
	 * @see #setCorrelationKey(TCorrelationKey)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_CorrelationKey()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='correlationKey' namespace='##targetNamespace'"
	 * @generated
	 */
	TCorrelationKey getCorrelationKey();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getCorrelationKey <em>Correlation Key</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Correlation Key</em>' containment reference.
	 * @see #getCorrelationKey()
	 * @generated
	 */
	void setCorrelationKey(TCorrelationKey value);

	/**
	 * Returns the value of the '<em><b>Correlation Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Correlation Property</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Correlation Property</em>' containment reference.
	 * @see #setCorrelationProperty(TCorrelationProperty)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_CorrelationProperty()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='correlationProperty' namespace='##targetNamespace' affiliation='rootElement'"
	 * @generated
	 */
	TCorrelationProperty getCorrelationProperty();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getCorrelationProperty <em>Correlation Property</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Correlation Property</em>' containment reference.
	 * @see #getCorrelationProperty()
	 * @generated
	 */
	void setCorrelationProperty(TCorrelationProperty value);

	/**
	 * Returns the value of the '<em><b>Correlation Property Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Correlation Property Binding</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Correlation Property Binding</em>' containment reference.
	 * @see #setCorrelationPropertyBinding(TCorrelationPropertyBinding)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_CorrelationPropertyBinding()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='correlationPropertyBinding' namespace='##targetNamespace'"
	 * @generated
	 */
	TCorrelationPropertyBinding getCorrelationPropertyBinding();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getCorrelationPropertyBinding <em>Correlation Property Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Correlation Property Binding</em>' containment reference.
	 * @see #getCorrelationPropertyBinding()
	 * @generated
	 */
	void setCorrelationPropertyBinding(TCorrelationPropertyBinding value);

	/**
	 * Returns the value of the '<em><b>Correlation Property Retrieval Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Correlation Property Retrieval Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Correlation Property Retrieval Expression</em>' containment reference.
	 * @see #setCorrelationPropertyRetrievalExpression(TCorrelationPropertyRetrievalExpression)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_CorrelationPropertyRetrievalExpression()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='correlationPropertyRetrievalExpression' namespace='##targetNamespace'"
	 * @generated
	 */
	TCorrelationPropertyRetrievalExpression getCorrelationPropertyRetrievalExpression();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getCorrelationPropertyRetrievalExpression <em>Correlation Property Retrieval Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Correlation Property Retrieval Expression</em>' containment reference.
	 * @see #getCorrelationPropertyRetrievalExpression()
	 * @generated
	 */
	void setCorrelationPropertyRetrievalExpression(TCorrelationPropertyRetrievalExpression value);

	/**
	 * Returns the value of the '<em><b>Correlation Subscription</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Correlation Subscription</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Correlation Subscription</em>' containment reference.
	 * @see #setCorrelationSubscription(TCorrelationSubscription)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_CorrelationSubscription()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='correlationSubscription' namespace='##targetNamespace'"
	 * @generated
	 */
	TCorrelationSubscription getCorrelationSubscription();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getCorrelationSubscription <em>Correlation Subscription</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Correlation Subscription</em>' containment reference.
	 * @see #getCorrelationSubscription()
	 * @generated
	 */
	void setCorrelationSubscription(TCorrelationSubscription value);

	/**
	 * Returns the value of the '<em><b>Data Association</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Association</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Association</em>' containment reference.
	 * @see #setDataAssociation(TDataAssociation)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_DataAssociation()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='dataAssociation' namespace='##targetNamespace'"
	 * @generated
	 */
	TDataAssociation getDataAssociation();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getDataAssociation <em>Data Association</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Association</em>' containment reference.
	 * @see #getDataAssociation()
	 * @generated
	 */
	void setDataAssociation(TDataAssociation value);

	/**
	 * Returns the value of the '<em><b>Data Input</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Input</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Input</em>' containment reference.
	 * @see #setDataInput(TDataInput)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_DataInput()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='dataInput' namespace='##targetNamespace'"
	 * @generated
	 */
	TDataInput getDataInput();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getDataInput <em>Data Input</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Input</em>' containment reference.
	 * @see #getDataInput()
	 * @generated
	 */
	void setDataInput(TDataInput value);

	/**
	 * Returns the value of the '<em><b>Data Input Association</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Input Association</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Input Association</em>' containment reference.
	 * @see #setDataInputAssociation(TDataInputAssociation)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_DataInputAssociation()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='dataInputAssociation' namespace='##targetNamespace'"
	 * @generated
	 */
	TDataInputAssociation getDataInputAssociation();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getDataInputAssociation <em>Data Input Association</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Input Association</em>' containment reference.
	 * @see #getDataInputAssociation()
	 * @generated
	 */
	void setDataInputAssociation(TDataInputAssociation value);

	/**
	 * Returns the value of the '<em><b>Data Object</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Object</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Object</em>' containment reference.
	 * @see #setDataObject(TDataObject)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_DataObject()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='dataObject' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TDataObject getDataObject();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getDataObject <em>Data Object</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Object</em>' containment reference.
	 * @see #getDataObject()
	 * @generated
	 */
	void setDataObject(TDataObject value);

	/**
	 * Returns the value of the '<em><b>Data Object Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Object Reference</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Object Reference</em>' containment reference.
	 * @see #setDataObjectReference(TDataObjectReference)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_DataObjectReference()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='dataObjectReference' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TDataObjectReference getDataObjectReference();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getDataObjectReference <em>Data Object Reference</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Object Reference</em>' containment reference.
	 * @see #getDataObjectReference()
	 * @generated
	 */
	void setDataObjectReference(TDataObjectReference value);

	/**
	 * Returns the value of the '<em><b>Data Output</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Output</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Output</em>' containment reference.
	 * @see #setDataOutput(TDataOutput)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_DataOutput()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='dataOutput' namespace='##targetNamespace'"
	 * @generated
	 */
	TDataOutput getDataOutput();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getDataOutput <em>Data Output</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Output</em>' containment reference.
	 * @see #getDataOutput()
	 * @generated
	 */
	void setDataOutput(TDataOutput value);

	/**
	 * Returns the value of the '<em><b>Data Output Association</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Output Association</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Output Association</em>' containment reference.
	 * @see #setDataOutputAssociation(TDataOutputAssociation)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_DataOutputAssociation()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='dataOutputAssociation' namespace='##targetNamespace'"
	 * @generated
	 */
	TDataOutputAssociation getDataOutputAssociation();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getDataOutputAssociation <em>Data Output Association</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Output Association</em>' containment reference.
	 * @see #getDataOutputAssociation()
	 * @generated
	 */
	void setDataOutputAssociation(TDataOutputAssociation value);

	/**
	 * Returns the value of the '<em><b>Data State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data State</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data State</em>' containment reference.
	 * @see #setDataState(TDataState)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_DataState()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='dataState' namespace='##targetNamespace'"
	 * @generated
	 */
	TDataState getDataState();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getDataState <em>Data State</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data State</em>' containment reference.
	 * @see #getDataState()
	 * @generated
	 */
	void setDataState(TDataState value);

	/**
	 * Returns the value of the '<em><b>Data Store</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Store</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Store</em>' containment reference.
	 * @see #setDataStore(TDataStore)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_DataStore()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='dataStore' namespace='##targetNamespace' affiliation='rootElement'"
	 * @generated
	 */
	TDataStore getDataStore();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getDataStore <em>Data Store</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Store</em>' containment reference.
	 * @see #getDataStore()
	 * @generated
	 */
	void setDataStore(TDataStore value);

	/**
	 * Returns the value of the '<em><b>Data Store Reference</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Store Reference</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Store Reference</em>' containment reference.
	 * @see #setDataStoreReference(TDataStoreReference)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_DataStoreReference()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='dataStoreReference' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TDataStoreReference getDataStoreReference();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getDataStoreReference <em>Data Store Reference</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Store Reference</em>' containment reference.
	 * @see #getDataStoreReference()
	 * @generated
	 */
	void setDataStoreReference(TDataStoreReference value);

	/**
	 * Returns the value of the '<em><b>Definitions</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Definitions</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Definitions</em>' containment reference.
	 * @see #setDefinitions(TDefinitions)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Definitions()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='definitions' namespace='##targetNamespace'"
	 * @generated
	 */
	TDefinitions getDefinitions();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getDefinitions <em>Definitions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Definitions</em>' containment reference.
	 * @see #getDefinitions()
	 * @generated
	 */
	void setDefinitions(TDefinitions value);

	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documentation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Documentation</em>' containment reference.
	 * @see #setDocumentation(TDocumentation)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Documentation()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='documentation' namespace='##targetNamespace'"
	 * @generated
	 */
	TDocumentation getDocumentation();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getDocumentation <em>Documentation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Documentation</em>' containment reference.
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(TDocumentation value);

	/**
	 * Returns the value of the '<em><b>End Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End Event</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End Event</em>' containment reference.
	 * @see #setEndEvent(TEndEvent)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_EndEvent()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='endEvent' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TEndEvent getEndEvent();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getEndEvent <em>End Event</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End Event</em>' containment reference.
	 * @see #getEndEvent()
	 * @generated
	 */
	void setEndEvent(TEndEvent value);

	/**
	 * Returns the value of the '<em><b>End Point</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End Point</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>End Point</em>' containment reference.
	 * @see #setEndPoint(TEndPoint)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_EndPoint()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='endPoint' namespace='##targetNamespace' affiliation='rootElement'"
	 * @generated
	 */
	TEndPoint getEndPoint();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getEndPoint <em>End Point</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>End Point</em>' containment reference.
	 * @see #getEndPoint()
	 * @generated
	 */
	void setEndPoint(TEndPoint value);

	/**
	 * Returns the value of the '<em><b>Error</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Error</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Error</em>' containment reference.
	 * @see #setError(TError)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Error()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='error' namespace='##targetNamespace' affiliation='rootElement'"
	 * @generated
	 */
	TError getError();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getError <em>Error</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Error</em>' containment reference.
	 * @see #getError()
	 * @generated
	 */
	void setError(TError value);

	/**
	 * Returns the value of the '<em><b>Error Event Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Error Event Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Error Event Definition</em>' containment reference.
	 * @see #setErrorEventDefinition(TErrorEventDefinition)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ErrorEventDefinition()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='errorEventDefinition' namespace='##targetNamespace' affiliation='eventDefinition'"
	 * @generated
	 */
	TErrorEventDefinition getErrorEventDefinition();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getErrorEventDefinition <em>Error Event Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Error Event Definition</em>' containment reference.
	 * @see #getErrorEventDefinition()
	 * @generated
	 */
	void setErrorEventDefinition(TErrorEventDefinition value);

	/**
	 * Returns the value of the '<em><b>Escalation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Escalation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Escalation</em>' containment reference.
	 * @see #setEscalation(TEscalation)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Escalation()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='escalation' namespace='##targetNamespace' affiliation='rootElement'"
	 * @generated
	 */
	TEscalation getEscalation();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getEscalation <em>Escalation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Escalation</em>' containment reference.
	 * @see #getEscalation()
	 * @generated
	 */
	void setEscalation(TEscalation value);

	/**
	 * Returns the value of the '<em><b>Escalation Event Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Escalation Event Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Escalation Event Definition</em>' containment reference.
	 * @see #setEscalationEventDefinition(TEscalationEventDefinition)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_EscalationEventDefinition()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='escalationEventDefinition' namespace='##targetNamespace' affiliation='eventDefinition'"
	 * @generated
	 */
	TEscalationEventDefinition getEscalationEventDefinition();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getEscalationEventDefinition <em>Escalation Event Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Escalation Event Definition</em>' containment reference.
	 * @see #getEscalationEventDefinition()
	 * @generated
	 */
	void setEscalationEventDefinition(TEscalationEventDefinition value);

	/**
	 * Returns the value of the '<em><b>Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event</em>' containment reference.
	 * @see #setEvent(TEvent)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Event()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='event' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TEvent getEvent();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getEvent <em>Event</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event</em>' containment reference.
	 * @see #getEvent()
	 * @generated
	 */
	void setEvent(TEvent value);

	/**
	 * Returns the value of the '<em><b>Event Based Gateway</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event Based Gateway</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Event Based Gateway</em>' containment reference.
	 * @see #setEventBasedGateway(TEventBasedGateway)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_EventBasedGateway()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='eventBasedGateway' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TEventBasedGateway getEventBasedGateway();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getEventBasedGateway <em>Event Based Gateway</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event Based Gateway</em>' containment reference.
	 * @see #getEventBasedGateway()
	 * @generated
	 */
	void setEventBasedGateway(TEventBasedGateway value);

	/**
	 * Returns the value of the '<em><b>Exclusive Gateway</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exclusive Gateway</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exclusive Gateway</em>' containment reference.
	 * @see #setExclusiveGateway(TExclusiveGateway)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ExclusiveGateway()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='exclusiveGateway' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TExclusiveGateway getExclusiveGateway();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getExclusiveGateway <em>Exclusive Gateway</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exclusive Gateway</em>' containment reference.
	 * @see #getExclusiveGateway()
	 * @generated
	 */
	void setExclusiveGateway(TExclusiveGateway value);

	/**
	 * Returns the value of the '<em><b>Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expression</em>' containment reference.
	 * @see #setExpression(TExpression)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Expression()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='expression' namespace='##targetNamespace'"
	 * @generated
	 */
	TExpression getExpression();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getExpression <em>Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expression</em>' containment reference.
	 * @see #getExpression()
	 * @generated
	 */
	void setExpression(TExpression value);

	/**
	 * Returns the value of the '<em><b>Extension</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extension</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extension</em>' containment reference.
	 * @see #setExtension(TExtension)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Extension()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='extension' namespace='##targetNamespace'"
	 * @generated
	 */
	TExtension getExtension();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getExtension <em>Extension</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extension</em>' containment reference.
	 * @see #getExtension()
	 * @generated
	 */
	void setExtension(TExtension value);

	/**
	 * Returns the value of the '<em><b>Extension Elements</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extension Elements</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extension Elements</em>' containment reference.
	 * @see #setExtensionElements(TExtensionElements)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ExtensionElements()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='extensionElements' namespace='##targetNamespace'"
	 * @generated
	 */
	TExtensionElements getExtensionElements();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getExtensionElements <em>Extension Elements</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Extension Elements</em>' containment reference.
	 * @see #getExtensionElements()
	 * @generated
	 */
	void setExtensionElements(TExtensionElements value);

	/**
	 * Returns the value of the '<em><b>Flow Node</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Flow Node</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Flow Node</em>' containment reference.
	 * @see #setFlowNode(TFlowNode)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_FlowNode()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='flowNode' namespace='##targetNamespace'"
	 * @generated
	 */
	TFlowNode getFlowNode();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getFlowNode <em>Flow Node</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Flow Node</em>' containment reference.
	 * @see #getFlowNode()
	 * @generated
	 */
	void setFlowNode(TFlowNode value);

	/**
	 * Returns the value of the '<em><b>Formal Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Formal Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Formal Expression</em>' containment reference.
	 * @see #setFormalExpression(TFormalExpression)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_FormalExpression()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='formalExpression' namespace='##targetNamespace' affiliation='expression'"
	 * @generated
	 */
	TFormalExpression getFormalExpression();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getFormalExpression <em>Formal Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Formal Expression</em>' containment reference.
	 * @see #getFormalExpression()
	 * @generated
	 */
	void setFormalExpression(TFormalExpression value);

	/**
	 * Returns the value of the '<em><b>Gateway</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gateway</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gateway</em>' containment reference.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Gateway()
	 * @model containment="true" upper="-2" transient="true" changeable="false" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='gateway' namespace='##targetNamespace'"
	 * @generated
	 */
	TGateway getGateway();

	/**
	 * Returns the value of the '<em><b>Global Business Rule Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Global Business Rule Task</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Global Business Rule Task</em>' containment reference.
	 * @see #setGlobalBusinessRuleTask(TGlobalBusinessRuleTask)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_GlobalBusinessRuleTask()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='globalBusinessRuleTask' namespace='##targetNamespace' affiliation='rootElement'"
	 * @generated
	 */
	TGlobalBusinessRuleTask getGlobalBusinessRuleTask();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getGlobalBusinessRuleTask <em>Global Business Rule Task</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Global Business Rule Task</em>' containment reference.
	 * @see #getGlobalBusinessRuleTask()
	 * @generated
	 */
	void setGlobalBusinessRuleTask(TGlobalBusinessRuleTask value);

	/**
	 * Returns the value of the '<em><b>Global Choreography Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Global Choreography Task</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Global Choreography Task</em>' containment reference.
	 * @see #setGlobalChoreographyTask(TGlobalChoreographyTask)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_GlobalChoreographyTask()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='globalChoreographyTask' namespace='##targetNamespace' affiliation='choreography'"
	 * @generated
	 */
	TGlobalChoreographyTask getGlobalChoreographyTask();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getGlobalChoreographyTask <em>Global Choreography Task</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Global Choreography Task</em>' containment reference.
	 * @see #getGlobalChoreographyTask()
	 * @generated
	 */
	void setGlobalChoreographyTask(TGlobalChoreographyTask value);

	/**
	 * Returns the value of the '<em><b>Global Conversation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Global Conversation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Global Conversation</em>' containment reference.
	 * @see #setGlobalConversation(TGlobalConversation)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_GlobalConversation()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='globalConversation' namespace='##targetNamespace' affiliation='collaboration'"
	 * @generated
	 */
	TGlobalConversation getGlobalConversation();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getGlobalConversation <em>Global Conversation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Global Conversation</em>' containment reference.
	 * @see #getGlobalConversation()
	 * @generated
	 */
	void setGlobalConversation(TGlobalConversation value);

	/**
	 * Returns the value of the '<em><b>Global Manual Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Global Manual Task</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Global Manual Task</em>' containment reference.
	 * @see #setGlobalManualTask(TGlobalManualTask)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_GlobalManualTask()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='globalManualTask' namespace='##targetNamespace' affiliation='rootElement'"
	 * @generated
	 */
	TGlobalManualTask getGlobalManualTask();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getGlobalManualTask <em>Global Manual Task</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Global Manual Task</em>' containment reference.
	 * @see #getGlobalManualTask()
	 * @generated
	 */
	void setGlobalManualTask(TGlobalManualTask value);

	/**
	 * Returns the value of the '<em><b>Global Script Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Global Script Task</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Global Script Task</em>' containment reference.
	 * @see #setGlobalScriptTask(TGlobalScriptTask)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_GlobalScriptTask()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='globalScriptTask' namespace='##targetNamespace' affiliation='rootElement'"
	 * @generated
	 */
	TGlobalScriptTask getGlobalScriptTask();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getGlobalScriptTask <em>Global Script Task</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Global Script Task</em>' containment reference.
	 * @see #getGlobalScriptTask()
	 * @generated
	 */
	void setGlobalScriptTask(TGlobalScriptTask value);

	/**
	 * Returns the value of the '<em><b>Global Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Global Task</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Global Task</em>' containment reference.
	 * @see #setGlobalTask(TGlobalTask)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_GlobalTask()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='globalTask' namespace='##targetNamespace' affiliation='rootElement'"
	 * @generated
	 */
	TGlobalTask getGlobalTask();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getGlobalTask <em>Global Task</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Global Task</em>' containment reference.
	 * @see #getGlobalTask()
	 * @generated
	 */
	void setGlobalTask(TGlobalTask value);

	/**
	 * Returns the value of the '<em><b>Global User Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Global User Task</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Global User Task</em>' containment reference.
	 * @see #setGlobalUserTask(TGlobalUserTask)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_GlobalUserTask()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='globalUserTask' namespace='##targetNamespace' affiliation='rootElement'"
	 * @generated
	 */
	TGlobalUserTask getGlobalUserTask();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getGlobalUserTask <em>Global User Task</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Global User Task</em>' containment reference.
	 * @see #getGlobalUserTask()
	 * @generated
	 */
	void setGlobalUserTask(TGlobalUserTask value);

	/**
	 * Returns the value of the '<em><b>Group</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' containment reference.
	 * @see #setGroup(TGroup)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Group()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='group' namespace='##targetNamespace' affiliation='artifact'"
	 * @generated
	 */
	TGroup getGroup();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getGroup <em>Group</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group</em>' containment reference.
	 * @see #getGroup()
	 * @generated
	 */
	void setGroup(TGroup value);

	/**
	 * Returns the value of the '<em><b>Human Performer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Human Performer</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Human Performer</em>' containment reference.
	 * @see #setHumanPerformer(THumanPerformer)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_HumanPerformer()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='humanPerformer' namespace='##targetNamespace' affiliation='performer'"
	 * @generated
	 */
	THumanPerformer getHumanPerformer();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getHumanPerformer <em>Human Performer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Human Performer</em>' containment reference.
	 * @see #getHumanPerformer()
	 * @generated
	 */
	void setHumanPerformer(THumanPerformer value);

	/**
	 * Returns the value of the '<em><b>Performer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Performer</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Performer</em>' containment reference.
	 * @see #setPerformer(TPerformer)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Performer()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='performer' namespace='##targetNamespace' affiliation='resourceRole'"
	 * @generated
	 */
	TPerformer getPerformer();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getPerformer <em>Performer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Performer</em>' containment reference.
	 * @see #getPerformer()
	 * @generated
	 */
	void setPerformer(TPerformer value);

	/**
	 * Returns the value of the '<em><b>Resource Role</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Role</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Role</em>' containment reference.
	 * @see #setResourceRole(TResourceRole)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ResourceRole()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='resourceRole' namespace='##targetNamespace'"
	 * @generated
	 */
	TResourceRole getResourceRole();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getResourceRole <em>Resource Role</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Role</em>' containment reference.
	 * @see #getResourceRole()
	 * @generated
	 */
	void setResourceRole(TResourceRole value);

	/**
	 * Returns the value of the '<em><b>Implicit Throw Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Implicit Throw Event</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Implicit Throw Event</em>' containment reference.
	 * @see #setImplicitThrowEvent(TImplicitThrowEvent)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ImplicitThrowEvent()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='implicitThrowEvent' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TImplicitThrowEvent getImplicitThrowEvent();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getImplicitThrowEvent <em>Implicit Throw Event</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implicit Throw Event</em>' containment reference.
	 * @see #getImplicitThrowEvent()
	 * @generated
	 */
	void setImplicitThrowEvent(TImplicitThrowEvent value);

	/**
	 * Returns the value of the '<em><b>Import</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Import</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Import</em>' containment reference.
	 * @see #setImport(TImport)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Import()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='import' namespace='##targetNamespace'"
	 * @generated
	 */
	TImport getImport();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getImport <em>Import</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Import</em>' containment reference.
	 * @see #getImport()
	 * @generated
	 */
	void setImport(TImport value);

	/**
	 * Returns the value of the '<em><b>Inclusive Gateway</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inclusive Gateway</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inclusive Gateway</em>' containment reference.
	 * @see #setInclusiveGateway(TInclusiveGateway)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_InclusiveGateway()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='inclusiveGateway' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TInclusiveGateway getInclusiveGateway();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getInclusiveGateway <em>Inclusive Gateway</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inclusive Gateway</em>' containment reference.
	 * @see #getInclusiveGateway()
	 * @generated
	 */
	void setInclusiveGateway(TInclusiveGateway value);

	/**
	 * Returns the value of the '<em><b>Input Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Input Set</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Set</em>' containment reference.
	 * @see #setInputSet(TInputSet)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_InputSet()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='inputSet' namespace='##targetNamespace'"
	 * @generated
	 */
	TInputSet getInputSet();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getInputSet <em>Input Set</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Input Set</em>' containment reference.
	 * @see #getInputSet()
	 * @generated
	 */
	void setInputSet(TInputSet value);

	/**
	 * Returns the value of the '<em><b>Interface</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Interface</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Interface</em>' containment reference.
	 * @see #setInterface(TInterface)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Interface()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='interface' namespace='##targetNamespace' affiliation='rootElement'"
	 * @generated
	 */
	TInterface getInterface();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getInterface <em>Interface</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interface</em>' containment reference.
	 * @see #getInterface()
	 * @generated
	 */
	void setInterface(TInterface value);

	/**
	 * Returns the value of the '<em><b>Intermediate Catch Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Intermediate Catch Event</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Intermediate Catch Event</em>' containment reference.
	 * @see #setIntermediateCatchEvent(TIntermediateCatchEvent)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_IntermediateCatchEvent()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='intermediateCatchEvent' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TIntermediateCatchEvent getIntermediateCatchEvent();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getIntermediateCatchEvent <em>Intermediate Catch Event</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Intermediate Catch Event</em>' containment reference.
	 * @see #getIntermediateCatchEvent()
	 * @generated
	 */
	void setIntermediateCatchEvent(TIntermediateCatchEvent value);

	/**
	 * Returns the value of the '<em><b>Intermediate Throw Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Intermediate Throw Event</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Intermediate Throw Event</em>' containment reference.
	 * @see #setIntermediateThrowEvent(TIntermediateThrowEvent)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_IntermediateThrowEvent()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='intermediateThrowEvent' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TIntermediateThrowEvent getIntermediateThrowEvent();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getIntermediateThrowEvent <em>Intermediate Throw Event</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Intermediate Throw Event</em>' containment reference.
	 * @see #getIntermediateThrowEvent()
	 * @generated
	 */
	void setIntermediateThrowEvent(TIntermediateThrowEvent value);

	/**
	 * Returns the value of the '<em><b>Io Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Io Binding</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Io Binding</em>' containment reference.
	 * @see #setIoBinding(TInputOutputBinding)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_IoBinding()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ioBinding' namespace='##targetNamespace'"
	 * @generated
	 */
	TInputOutputBinding getIoBinding();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getIoBinding <em>Io Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Io Binding</em>' containment reference.
	 * @see #getIoBinding()
	 * @generated
	 */
	void setIoBinding(TInputOutputBinding value);

	/**
	 * Returns the value of the '<em><b>Io Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Io Specification</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Io Specification</em>' containment reference.
	 * @see #setIoSpecification(TInputOutputSpecification)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_IoSpecification()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ioSpecification' namespace='##targetNamespace'"
	 * @generated
	 */
	TInputOutputSpecification getIoSpecification();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getIoSpecification <em>Io Specification</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Io Specification</em>' containment reference.
	 * @see #getIoSpecification()
	 * @generated
	 */
	void setIoSpecification(TInputOutputSpecification value);

	/**
	 * Returns the value of the '<em><b>Item Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Item Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Item Definition</em>' containment reference.
	 * @see #setItemDefinition(TItemDefinition)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ItemDefinition()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='itemDefinition' namespace='##targetNamespace' affiliation='rootElement'"
	 * @generated
	 */
	TItemDefinition getItemDefinition();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getItemDefinition <em>Item Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Item Definition</em>' containment reference.
	 * @see #getItemDefinition()
	 * @generated
	 */
	void setItemDefinition(TItemDefinition value);

	/**
	 * Returns the value of the '<em><b>Lane</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lane</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lane</em>' containment reference.
	 * @see #setLane(TLane)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Lane()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='lane' namespace='##targetNamespace'"
	 * @generated
	 */
	TLane getLane();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getLane <em>Lane</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lane</em>' containment reference.
	 * @see #getLane()
	 * @generated
	 */
	void setLane(TLane value);

	/**
	 * Returns the value of the '<em><b>Lane Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lane Set</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lane Set</em>' containment reference.
	 * @see #setLaneSet(TLaneSet)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_LaneSet()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='laneSet' namespace='##targetNamespace'"
	 * @generated
	 */
	TLaneSet getLaneSet();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getLaneSet <em>Lane Set</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lane Set</em>' containment reference.
	 * @see #getLaneSet()
	 * @generated
	 */
	void setLaneSet(TLaneSet value);

	/**
	 * Returns the value of the '<em><b>Link Event Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Link Event Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Link Event Definition</em>' containment reference.
	 * @see #setLinkEventDefinition(TLinkEventDefinition)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_LinkEventDefinition()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='linkEventDefinition' namespace='##targetNamespace' affiliation='eventDefinition'"
	 * @generated
	 */
	TLinkEventDefinition getLinkEventDefinition();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getLinkEventDefinition <em>Link Event Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Link Event Definition</em>' containment reference.
	 * @see #getLinkEventDefinition()
	 * @generated
	 */
	void setLinkEventDefinition(TLinkEventDefinition value);

	/**
	 * Returns the value of the '<em><b>Loop Characteristics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Loop Characteristics</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Loop Characteristics</em>' containment reference.
	 * @see #setLoopCharacteristics(TLoopCharacteristics)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_LoopCharacteristics()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='loopCharacteristics' namespace='##targetNamespace'"
	 * @generated
	 */
	TLoopCharacteristics getLoopCharacteristics();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getLoopCharacteristics <em>Loop Characteristics</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Loop Characteristics</em>' containment reference.
	 * @see #getLoopCharacteristics()
	 * @generated
	 */
	void setLoopCharacteristics(TLoopCharacteristics value);

	/**
	 * Returns the value of the '<em><b>Manual Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Manual Task</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Manual Task</em>' containment reference.
	 * @see #setManualTask(TManualTask)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ManualTask()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='manualTask' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TManualTask getManualTask();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getManualTask <em>Manual Task</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manual Task</em>' containment reference.
	 * @see #getManualTask()
	 * @generated
	 */
	void setManualTask(TManualTask value);

	/**
	 * Returns the value of the '<em><b>Message</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message</em>' containment reference.
	 * @see #setMessage(TMessage)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Message()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='message' namespace='##targetNamespace' affiliation='rootElement'"
	 * @generated
	 */
	TMessage getMessage();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getMessage <em>Message</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message</em>' containment reference.
	 * @see #getMessage()
	 * @generated
	 */
	void setMessage(TMessage value);

	/**
	 * Returns the value of the '<em><b>Message Event Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Event Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Event Definition</em>' containment reference.
	 * @see #setMessageEventDefinition(TMessageEventDefinition)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_MessageEventDefinition()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='messageEventDefinition' namespace='##targetNamespace' affiliation='eventDefinition'"
	 * @generated
	 */
	TMessageEventDefinition getMessageEventDefinition();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getMessageEventDefinition <em>Message Event Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message Event Definition</em>' containment reference.
	 * @see #getMessageEventDefinition()
	 * @generated
	 */
	void setMessageEventDefinition(TMessageEventDefinition value);

	/**
	 * Returns the value of the '<em><b>Message Flow</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Flow</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Flow</em>' containment reference.
	 * @see #setMessageFlow(TMessageFlow)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_MessageFlow()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='messageFlow' namespace='##targetNamespace'"
	 * @generated
	 */
	TMessageFlow getMessageFlow();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getMessageFlow <em>Message Flow</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message Flow</em>' containment reference.
	 * @see #getMessageFlow()
	 * @generated
	 */
	void setMessageFlow(TMessageFlow value);

	/**
	 * Returns the value of the '<em><b>Message Flow Association</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Flow Association</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Flow Association</em>' containment reference.
	 * @see #setMessageFlowAssociation(TMessageFlowAssociation)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_MessageFlowAssociation()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='messageFlowAssociation' namespace='##targetNamespace'"
	 * @generated
	 */
	TMessageFlowAssociation getMessageFlowAssociation();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getMessageFlowAssociation <em>Message Flow Association</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message Flow Association</em>' containment reference.
	 * @see #getMessageFlowAssociation()
	 * @generated
	 */
	void setMessageFlowAssociation(TMessageFlowAssociation value);

	/**
	 * Returns the value of the '<em><b>Monitoring</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Monitoring</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Monitoring</em>' containment reference.
	 * @see #setMonitoring(TMonitoring)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Monitoring()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='monitoring' namespace='##targetNamespace'"
	 * @generated
	 */
	TMonitoring getMonitoring();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getMonitoring <em>Monitoring</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Monitoring</em>' containment reference.
	 * @see #getMonitoring()
	 * @generated
	 */
	void setMonitoring(TMonitoring value);

	/**
	 * Returns the value of the '<em><b>Multi Instance Loop Characteristics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multi Instance Loop Characteristics</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multi Instance Loop Characteristics</em>' containment reference.
	 * @see #setMultiInstanceLoopCharacteristics(TMultiInstanceLoopCharacteristics)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_MultiInstanceLoopCharacteristics()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='multiInstanceLoopCharacteristics' namespace='##targetNamespace' affiliation='loopCharacteristics'"
	 * @generated
	 */
	TMultiInstanceLoopCharacteristics getMultiInstanceLoopCharacteristics();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getMultiInstanceLoopCharacteristics <em>Multi Instance Loop Characteristics</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multi Instance Loop Characteristics</em>' containment reference.
	 * @see #getMultiInstanceLoopCharacteristics()
	 * @generated
	 */
	void setMultiInstanceLoopCharacteristics(TMultiInstanceLoopCharacteristics value);

	/**
	 * Returns the value of the '<em><b>Operation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation</em>' containment reference.
	 * @see #setOperation(TOperation)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Operation()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='operation' namespace='##targetNamespace'"
	 * @generated
	 */
	TOperation getOperation();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getOperation <em>Operation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation</em>' containment reference.
	 * @see #getOperation()
	 * @generated
	 */
	void setOperation(TOperation value);

	/**
	 * Returns the value of the '<em><b>Output Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Output Set</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Output Set</em>' containment reference.
	 * @see #setOutputSet(TOutputSet)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_OutputSet()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='outputSet' namespace='##targetNamespace'"
	 * @generated
	 */
	TOutputSet getOutputSet();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getOutputSet <em>Output Set</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Output Set</em>' containment reference.
	 * @see #getOutputSet()
	 * @generated
	 */
	void setOutputSet(TOutputSet value);

	/**
	 * Returns the value of the '<em><b>Parallel Gateway</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parallel Gateway</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parallel Gateway</em>' containment reference.
	 * @see #setParallelGateway(TParallelGateway)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ParallelGateway()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='parallelGateway' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TParallelGateway getParallelGateway();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getParallelGateway <em>Parallel Gateway</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parallel Gateway</em>' containment reference.
	 * @see #getParallelGateway()
	 * @generated
	 */
	void setParallelGateway(TParallelGateway value);

	/**
	 * Returns the value of the '<em><b>Participant</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participant</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participant</em>' containment reference.
	 * @see #setParticipant(TParticipant)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Participant()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='participant' namespace='##targetNamespace'"
	 * @generated
	 */
	TParticipant getParticipant();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getParticipant <em>Participant</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Participant</em>' containment reference.
	 * @see #getParticipant()
	 * @generated
	 */
	void setParticipant(TParticipant value);

	/**
	 * Returns the value of the '<em><b>Participant Association</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participant Association</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participant Association</em>' containment reference.
	 * @see #setParticipantAssociation(TParticipantAssociation)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ParticipantAssociation()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='participantAssociation' namespace='##targetNamespace'"
	 * @generated
	 */
	TParticipantAssociation getParticipantAssociation();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getParticipantAssociation <em>Participant Association</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Participant Association</em>' containment reference.
	 * @see #getParticipantAssociation()
	 * @generated
	 */
	void setParticipantAssociation(TParticipantAssociation value);

	/**
	 * Returns the value of the '<em><b>Participant Multiplicity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participant Multiplicity</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participant Multiplicity</em>' containment reference.
	 * @see #setParticipantMultiplicity(TParticipantMultiplicity)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ParticipantMultiplicity()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='participantMultiplicity' namespace='##targetNamespace'"
	 * @generated
	 */
	TParticipantMultiplicity getParticipantMultiplicity();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getParticipantMultiplicity <em>Participant Multiplicity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Participant Multiplicity</em>' containment reference.
	 * @see #getParticipantMultiplicity()
	 * @generated
	 */
	void setParticipantMultiplicity(TParticipantMultiplicity value);

	/**
	 * Returns the value of the '<em><b>Partner Entity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partner Entity</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partner Entity</em>' containment reference.
	 * @see #setPartnerEntity(TPartnerEntity)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_PartnerEntity()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='partnerEntity' namespace='##targetNamespace' affiliation='rootElement'"
	 * @generated
	 */
	TPartnerEntity getPartnerEntity();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getPartnerEntity <em>Partner Entity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partner Entity</em>' containment reference.
	 * @see #getPartnerEntity()
	 * @generated
	 */
	void setPartnerEntity(TPartnerEntity value);

	/**
	 * Returns the value of the '<em><b>Partner Role</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Partner Role</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Partner Role</em>' containment reference.
	 * @see #setPartnerRole(TPartnerRole)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_PartnerRole()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='partnerRole' namespace='##targetNamespace' affiliation='rootElement'"
	 * @generated
	 */
	TPartnerRole getPartnerRole();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getPartnerRole <em>Partner Role</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Partner Role</em>' containment reference.
	 * @see #getPartnerRole()
	 * @generated
	 */
	void setPartnerRole(TPartnerRole value);

	/**
	 * Returns the value of the '<em><b>Potential Owner</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Potential Owner</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Potential Owner</em>' containment reference.
	 * @see #setPotentialOwner(TPotentialOwner)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_PotentialOwner()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='potentialOwner' namespace='##targetNamespace' affiliation='performer'"
	 * @generated
	 */
	TPotentialOwner getPotentialOwner();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getPotentialOwner <em>Potential Owner</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Potential Owner</em>' containment reference.
	 * @see #getPotentialOwner()
	 * @generated
	 */
	void setPotentialOwner(TPotentialOwner value);

	/**
	 * Returns the value of the '<em><b>Process</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Process</em>' containment reference.
	 * @see #setProcess(TProcess)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Process()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='process' namespace='##targetNamespace' affiliation='rootElement'"
	 * @generated
	 */
	TProcess getProcess();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getProcess <em>Process</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Process</em>' containment reference.
	 * @see #getProcess()
	 * @generated
	 */
	void setProcess(TProcess value);

	/**
	 * Returns the value of the '<em><b>Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property</em>' containment reference.
	 * @see #setProperty(TProperty)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Property()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='property' namespace='##targetNamespace'"
	 * @generated
	 */
	TProperty getProperty();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getProperty <em>Property</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property</em>' containment reference.
	 * @see #getProperty()
	 * @generated
	 */
	void setProperty(TProperty value);

	/**
	 * Returns the value of the '<em><b>Receive Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Receive Task</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Receive Task</em>' containment reference.
	 * @see #setReceiveTask(TReceiveTask)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ReceiveTask()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='receiveTask' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TReceiveTask getReceiveTask();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getReceiveTask <em>Receive Task</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Receive Task</em>' containment reference.
	 * @see #getReceiveTask()
	 * @generated
	 */
	void setReceiveTask(TReceiveTask value);

	/**
	 * Returns the value of the '<em><b>Relationship</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Relationship</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relationship</em>' containment reference.
	 * @see #setRelationship(TRelationship)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Relationship()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='relationship' namespace='##targetNamespace'"
	 * @generated
	 */
	TRelationship getRelationship();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getRelationship <em>Relationship</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Relationship</em>' containment reference.
	 * @see #getRelationship()
	 * @generated
	 */
	void setRelationship(TRelationship value);

	/**
	 * Returns the value of the '<em><b>Rendering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rendering</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rendering</em>' containment reference.
	 * @see #setRendering(TRendering)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Rendering()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='rendering' namespace='##targetNamespace'"
	 * @generated
	 */
	TRendering getRendering();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getRendering <em>Rendering</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rendering</em>' containment reference.
	 * @see #getRendering()
	 * @generated
	 */
	void setRendering(TRendering value);

	/**
	 * Returns the value of the '<em><b>Resource</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource</em>' containment reference.
	 * @see #setResource(TResource)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Resource()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='resource' namespace='##targetNamespace' affiliation='rootElement'"
	 * @generated
	 */
	TResource getResource();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getResource <em>Resource</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource</em>' containment reference.
	 * @see #getResource()
	 * @generated
	 */
	void setResource(TResource value);

	/**
	 * Returns the value of the '<em><b>Resource Assignment Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Assignment Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Assignment Expression</em>' containment reference.
	 * @see #setResourceAssignmentExpression(TResourceAssignmentExpression)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ResourceAssignmentExpression()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='resourceAssignmentExpression' namespace='##targetNamespace'"
	 * @generated
	 */
	TResourceAssignmentExpression getResourceAssignmentExpression();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getResourceAssignmentExpression <em>Resource Assignment Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Assignment Expression</em>' containment reference.
	 * @see #getResourceAssignmentExpression()
	 * @generated
	 */
	void setResourceAssignmentExpression(TResourceAssignmentExpression value);

	/**
	 * Returns the value of the '<em><b>Resource Parameter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Parameter</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Parameter</em>' containment reference.
	 * @see #setResourceParameter(TResourceParameter)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ResourceParameter()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='resourceParameter' namespace='##targetNamespace'"
	 * @generated
	 */
	TResourceParameter getResourceParameter();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getResourceParameter <em>Resource Parameter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Parameter</em>' containment reference.
	 * @see #getResourceParameter()
	 * @generated
	 */
	void setResourceParameter(TResourceParameter value);

	/**
	 * Returns the value of the '<em><b>Resource Parameter Binding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource Parameter Binding</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resource Parameter Binding</em>' containment reference.
	 * @see #setResourceParameterBinding(TResourceParameterBinding)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ResourceParameterBinding()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='resourceParameterBinding' namespace='##targetNamespace'"
	 * @generated
	 */
	TResourceParameterBinding getResourceParameterBinding();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getResourceParameterBinding <em>Resource Parameter Binding</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resource Parameter Binding</em>' containment reference.
	 * @see #getResourceParameterBinding()
	 * @generated
	 */
	void setResourceParameterBinding(TResourceParameterBinding value);

	/**
	 * Returns the value of the '<em><b>Script</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Script</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Script</em>' containment reference.
	 * @see #setScript(TScript)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Script()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='script' namespace='##targetNamespace'"
	 * @generated
	 */
	TScript getScript();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getScript <em>Script</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script</em>' containment reference.
	 * @see #getScript()
	 * @generated
	 */
	void setScript(TScript value);

	/**
	 * Returns the value of the '<em><b>Script Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Script Task</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Script Task</em>' containment reference.
	 * @see #setScriptTask(TScriptTask)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ScriptTask()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='scriptTask' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TScriptTask getScriptTask();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getScriptTask <em>Script Task</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Script Task</em>' containment reference.
	 * @see #getScriptTask()
	 * @generated
	 */
	void setScriptTask(TScriptTask value);

	/**
	 * Returns the value of the '<em><b>Send Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Send Task</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Send Task</em>' containment reference.
	 * @see #setSendTask(TSendTask)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_SendTask()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='sendTask' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TSendTask getSendTask();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getSendTask <em>Send Task</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Send Task</em>' containment reference.
	 * @see #getSendTask()
	 * @generated
	 */
	void setSendTask(TSendTask value);

	/**
	 * Returns the value of the '<em><b>Sequence Flow</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sequence Flow</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sequence Flow</em>' containment reference.
	 * @see #setSequenceFlow(TSequenceFlow)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_SequenceFlow()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='sequenceFlow' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TSequenceFlow getSequenceFlow();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getSequenceFlow <em>Sequence Flow</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sequence Flow</em>' containment reference.
	 * @see #getSequenceFlow()
	 * @generated
	 */
	void setSequenceFlow(TSequenceFlow value);

	/**
	 * Returns the value of the '<em><b>Service Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Task</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Service Task</em>' containment reference.
	 * @see #setServiceTask(TServiceTask)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ServiceTask()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='serviceTask' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TServiceTask getServiceTask();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getServiceTask <em>Service Task</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Task</em>' containment reference.
	 * @see #getServiceTask()
	 * @generated
	 */
	void setServiceTask(TServiceTask value);

	/**
	 * Returns the value of the '<em><b>Signal</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Signal</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Signal</em>' containment reference.
	 * @see #setSignal(TSignal)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Signal()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='signal' namespace='##targetNamespace' affiliation='rootElement'"
	 * @generated
	 */
	TSignal getSignal();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getSignal <em>Signal</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Signal</em>' containment reference.
	 * @see #getSignal()
	 * @generated
	 */
	void setSignal(TSignal value);

	/**
	 * Returns the value of the '<em><b>Signal Event Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Signal Event Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Signal Event Definition</em>' containment reference.
	 * @see #setSignalEventDefinition(TSignalEventDefinition)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_SignalEventDefinition()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='signalEventDefinition' namespace='##targetNamespace' affiliation='eventDefinition'"
	 * @generated
	 */
	TSignalEventDefinition getSignalEventDefinition();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getSignalEventDefinition <em>Signal Event Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Signal Event Definition</em>' containment reference.
	 * @see #getSignalEventDefinition()
	 * @generated
	 */
	void setSignalEventDefinition(TSignalEventDefinition value);

	/**
	 * Returns the value of the '<em><b>Standard Loop Characteristics</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Standard Loop Characteristics</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Standard Loop Characteristics</em>' containment reference.
	 * @see #setStandardLoopCharacteristics(TStandardLoopCharacteristics)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_StandardLoopCharacteristics()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='standardLoopCharacteristics' namespace='##targetNamespace' affiliation='loopCharacteristics'"
	 * @generated
	 */
	TStandardLoopCharacteristics getStandardLoopCharacteristics();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getStandardLoopCharacteristics <em>Standard Loop Characteristics</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Standard Loop Characteristics</em>' containment reference.
	 * @see #getStandardLoopCharacteristics()
	 * @generated
	 */
	void setStandardLoopCharacteristics(TStandardLoopCharacteristics value);

	/**
	 * Returns the value of the '<em><b>Start Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start Event</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Start Event</em>' containment reference.
	 * @see #setStartEvent(TStartEvent)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_StartEvent()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='startEvent' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TStartEvent getStartEvent();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getStartEvent <em>Start Event</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Start Event</em>' containment reference.
	 * @see #getStartEvent()
	 * @generated
	 */
	void setStartEvent(TStartEvent value);

	/**
	 * Returns the value of the '<em><b>Sub Choreography</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Choreography</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Choreography</em>' containment reference.
	 * @see #setSubChoreography(TSubChoreography)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_SubChoreography()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='subChoreography' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TSubChoreography getSubChoreography();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getSubChoreography <em>Sub Choreography</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sub Choreography</em>' containment reference.
	 * @see #getSubChoreography()
	 * @generated
	 */
	void setSubChoreography(TSubChoreography value);

	/**
	 * Returns the value of the '<em><b>Sub Conversation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Conversation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Conversation</em>' containment reference.
	 * @see #setSubConversation(TSubConversation)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_SubConversation()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='subConversation' namespace='##targetNamespace' affiliation='conversationNode'"
	 * @generated
	 */
	TSubConversation getSubConversation();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getSubConversation <em>Sub Conversation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sub Conversation</em>' containment reference.
	 * @see #getSubConversation()
	 * @generated
	 */
	void setSubConversation(TSubConversation value);

	/**
	 * Returns the value of the '<em><b>Sub Process</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Process</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Process</em>' containment reference.
	 * @see #setSubProcess(TSubProcess)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_SubProcess()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='subProcess' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TSubProcess getSubProcess();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getSubProcess <em>Sub Process</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sub Process</em>' containment reference.
	 * @see #getSubProcess()
	 * @generated
	 */
	void setSubProcess(TSubProcess value);

	/**
	 * Returns the value of the '<em><b>Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Task</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Task</em>' containment reference.
	 * @see #setTask(TTask)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Task()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='task' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TTask getTask();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getTask <em>Task</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Task</em>' containment reference.
	 * @see #getTask()
	 * @generated
	 */
	void setTask(TTask value);

	/**
	 * Returns the value of the '<em><b>Terminate Event Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Terminate Event Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Terminate Event Definition</em>' containment reference.
	 * @see #setTerminateEventDefinition(TTerminateEventDefinition)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_TerminateEventDefinition()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='terminateEventDefinition' namespace='##targetNamespace' affiliation='eventDefinition'"
	 * @generated
	 */
	TTerminateEventDefinition getTerminateEventDefinition();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getTerminateEventDefinition <em>Terminate Event Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Terminate Event Definition</em>' containment reference.
	 * @see #getTerminateEventDefinition()
	 * @generated
	 */
	void setTerminateEventDefinition(TTerminateEventDefinition value);

	/**
	 * Returns the value of the '<em><b>Text</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text</em>' containment reference.
	 * @see #setText(TText)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Text()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='text' namespace='##targetNamespace'"
	 * @generated
	 */
	TText getText();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getText <em>Text</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text</em>' containment reference.
	 * @see #getText()
	 * @generated
	 */
	void setText(TText value);

	/**
	 * Returns the value of the '<em><b>Text Annotation</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Text Annotation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Text Annotation</em>' containment reference.
	 * @see #setTextAnnotation(TTextAnnotation)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_TextAnnotation()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='textAnnotation' namespace='##targetNamespace' affiliation='artifact'"
	 * @generated
	 */
	TTextAnnotation getTextAnnotation();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getTextAnnotation <em>Text Annotation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text Annotation</em>' containment reference.
	 * @see #getTextAnnotation()
	 * @generated
	 */
	void setTextAnnotation(TTextAnnotation value);

	/**
	 * Returns the value of the '<em><b>Throw Event</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Throw Event</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Throw Event</em>' containment reference.
	 * @see #setThrowEvent(TThrowEvent)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_ThrowEvent()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='throwEvent' namespace='##targetNamespace'"
	 * @generated
	 */
	TThrowEvent getThrowEvent();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getThrowEvent <em>Throw Event</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Throw Event</em>' containment reference.
	 * @see #getThrowEvent()
	 * @generated
	 */
	void setThrowEvent(TThrowEvent value);

	/**
	 * Returns the value of the '<em><b>Timer Event Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timer Event Definition</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timer Event Definition</em>' containment reference.
	 * @see #setTimerEventDefinition(TTimerEventDefinition)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_TimerEventDefinition()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='timerEventDefinition' namespace='##targetNamespace' affiliation='eventDefinition'"
	 * @generated
	 */
	TTimerEventDefinition getTimerEventDefinition();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getTimerEventDefinition <em>Timer Event Definition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timer Event Definition</em>' containment reference.
	 * @see #getTimerEventDefinition()
	 * @generated
	 */
	void setTimerEventDefinition(TTimerEventDefinition value);

	/**
	 * Returns the value of the '<em><b>Transaction</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transaction</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transaction</em>' containment reference.
	 * @see #setTransaction(TTransaction)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_Transaction()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='transaction' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TTransaction getTransaction();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getTransaction <em>Transaction</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transaction</em>' containment reference.
	 * @see #getTransaction()
	 * @generated
	 */
	void setTransaction(TTransaction value);

	/**
	 * Returns the value of the '<em><b>User Task</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Task</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Task</em>' containment reference.
	 * @see #setUserTask(TUserTask)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getDocumentRoot_UserTask()
	 * @model containment="true" upper="-2" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='userTask' namespace='##targetNamespace' affiliation='flowElement'"
	 * @generated
	 */
	TUserTask getUserTask();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.DocumentRoot#getUserTask <em>User Task</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User Task</em>' containment reference.
	 * @see #getUserTask()
	 * @generated
	 */
	void setUserTask(TUserTask value);

} // DocumentRoot
