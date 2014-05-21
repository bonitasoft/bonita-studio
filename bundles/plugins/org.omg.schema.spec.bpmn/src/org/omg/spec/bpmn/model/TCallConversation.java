/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TCall Conversation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TCallConversation#getParticipantAssociation <em>Participant Association</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TCallConversation#getCalledCollaborationRef <em>Called Collaboration Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTCallConversation()
 * @model extendedMetaData="name='tCallConversation' kind='elementOnly'"
 * @generated
 */
public interface TCallConversation extends TConversationNode {
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
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCallConversation_ParticipantAssociation()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='participantAssociation' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TParticipantAssociation> getParticipantAssociation();

	/**
	 * Returns the value of the '<em><b>Called Collaboration Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Called Collaboration Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Called Collaboration Ref</em>' attribute.
	 * @see #setCalledCollaborationRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTCallConversation_CalledCollaborationRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='attribute' name='calledCollaborationRef'"
	 * @generated
	 */
	QName getCalledCollaborationRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TCallConversation#getCalledCollaborationRef <em>Called Collaboration Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Called Collaboration Ref</em>' attribute.
	 * @see #getCalledCollaborationRef()
	 * @generated
	 */
	void setCalledCollaborationRef(QName value);

} // TCallConversation
