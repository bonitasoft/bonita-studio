/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TCollaboration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TCollaboration#getParticipant <em>Participant</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCollaboration#getMessageFlow <em>Message Flow</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCollaboration#getArtifactGroup <em>Artifact Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCollaboration#getArtifact <em>Artifact</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCollaboration#getConversationNodeGroup <em>Conversation Node Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCollaboration#getConversationNode <em>Conversation Node</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCollaboration#getConversationAssociation <em>Conversation Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCollaboration#getParticipantAssociation <em>Participant Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCollaboration#getMessageFlowAssociation <em>Message Flow Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCollaboration#getCorrelationKey <em>Correlation Key</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCollaboration#getChoreographyRef <em>Choreography Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCollaboration#getConversationLink <em>Conversation Link</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCollaboration#isIsClosed <em>Is Closed</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCollaboration#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTCollaboration()
 * @model extendedMetaData="name='tCollaboration' kind='elementOnly'"
 * @generated
 */
public interface TCollaboration extends TRootElement {
	/**
	 * Returns the value of the '<em><b>Participant</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TParticipant}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participant</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participant</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCollaboration_Participant()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='participant' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TParticipant> getParticipant();

	/**
	 * Returns the value of the '<em><b>Message Flow</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TMessageFlow}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Flow</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Flow</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCollaboration_MessageFlow()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='messageFlow' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TMessageFlow> getMessageFlow();

	/**
	 * Returns the value of the '<em><b>Artifact Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Artifact Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifact Group</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCollaboration_ArtifactGroup()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='artifact:group' namespace='##targetNamespace'"
	 * @generated
	 */
	FeatureMap getArtifactGroup();

	/**
	 * Returns the value of the '<em><b>Artifact</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TArtifact}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Artifact</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Artifact</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCollaboration_Artifact()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='artifact' namespace='##targetNamespace' group='artifact:group'"
	 * @generated
	 */
	EList<TArtifact> getArtifact();

	/**
	 * Returns the value of the '<em><b>Conversation Node Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Conversation Node Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conversation Node Group</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCollaboration_ConversationNodeGroup()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='conversationNode:group' namespace='##targetNamespace'"
	 * @generated
	 */
	FeatureMap getConversationNodeGroup();

	/**
	 * Returns the value of the '<em><b>Conversation Node</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TConversationNode}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Conversation Node</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conversation Node</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCollaboration_ConversationNode()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='conversationNode' namespace='##targetNamespace' group='conversationNode:group'"
	 * @generated
	 */
	EList<TConversationNode> getConversationNode();

	/**
	 * Returns the value of the '<em><b>Conversation Association</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TConversationAssociation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Conversation Association</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conversation Association</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCollaboration_ConversationAssociation()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='conversationAssociation' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TConversationAssociation> getConversationAssociation();

	/**
	 * Returns the value of the '<em><b>Participant Association</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TParticipantAssociation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participant Association</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participant Association</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCollaboration_ParticipantAssociation()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='participantAssociation' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TParticipantAssociation> getParticipantAssociation();

	/**
	 * Returns the value of the '<em><b>Message Flow Association</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TMessageFlowAssociation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Flow Association</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Flow Association</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCollaboration_MessageFlowAssociation()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='messageFlowAssociation' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TMessageFlowAssociation> getMessageFlowAssociation();

	/**
	 * Returns the value of the '<em><b>Correlation Key</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TCorrelationKey}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Correlation Key</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Correlation Key</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCollaboration_CorrelationKey()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='correlationKey' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TCorrelationKey> getCorrelationKey();

	/**
	 * Returns the value of the '<em><b>Choreography Ref</b></em>' attribute list.
	 * The list contents are of type {@link javax.xml.namespace.QName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Choreography Ref</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Choreography Ref</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCollaboration_ChoreographyRef()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='element' name='choreographyRef' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<QName> getChoreographyRef();

	/**
	 * Returns the value of the '<em><b>Conversation Link</b></em>' containment reference list.
	 * The list contents are of type {@link org.omg.spec.bpmn.model.TConversationLink}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Conversation Link</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conversation Link</em>' containment reference list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCollaboration_ConversationLink()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='conversationLink' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TConversationLink> getConversationLink();

	/**
	 * Returns the value of the '<em><b>Is Closed</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Closed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Closed</em>' attribute.
	 * @see #isSetIsClosed()
	 * @see #unsetIsClosed()
	 * @see #setIsClosed(boolean)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCollaboration_IsClosed()
	 * @model default="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='isClosed'"
	 * @generated
	 */
	boolean isIsClosed();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TCollaboration#isIsClosed <em>Is Closed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Closed</em>' attribute.
	 * @see #isSetIsClosed()
	 * @see #unsetIsClosed()
	 * @see #isIsClosed()
	 * @generated
	 */
	void setIsClosed(boolean value);

	/**
	 * Unsets the value of the '{@link org.omg.spec.bpmn.model.TCollaboration#isIsClosed <em>Is Closed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsClosed()
	 * @see #isIsClosed()
	 * @see #setIsClosed(boolean)
	 * @generated
	 */
	void unsetIsClosed();

	/**
	 * Returns whether the value of the '{@link org.omg.spec.bpmn.model.TCollaboration#isIsClosed <em>Is Closed</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Is Closed</em>' attribute is set.
	 * @see #unsetIsClosed()
	 * @see #isIsClosed()
	 * @see #setIsClosed(boolean)
	 * @generated
	 */
	boolean isSetIsClosed();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCollaboration_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TCollaboration#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // TCollaboration
