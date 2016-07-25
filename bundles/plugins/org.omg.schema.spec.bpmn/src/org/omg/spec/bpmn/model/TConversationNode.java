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
 * A representation of the model object '<em><b>TConversation Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TConversationNode#getParticipantRef <em>Participant Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TConversationNode#getMessageFlowRef <em>Message Flow Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TConversationNode#getCorrelationKey <em>Correlation Key</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TConversationNode#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTConversationNode()
 * @model abstract="true"
 *        extendedMetaData="name='tConversationNode' kind='elementOnly'"
 * @generated
 */
public interface TConversationNode extends TBaseElement {
	/**
	 * Returns the value of the '<em><b>Participant Ref</b></em>' attribute list.
	 * The list contents are of type {@link javax.xml.namespace.QName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Participant Ref</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Participant Ref</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTConversationNode_ParticipantRef()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='element' name='participantRef' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<QName> getParticipantRef();

	/**
	 * Returns the value of the '<em><b>Message Flow Ref</b></em>' attribute list.
	 * The list contents are of type {@link javax.xml.namespace.QName}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Flow Ref</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Flow Ref</em>' attribute list.
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTConversationNode_MessageFlowRef()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='element' name='messageFlowRef' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<QName> getMessageFlowRef();

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
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTConversationNode_CorrelationKey()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='correlationKey' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TCorrelationKey> getCorrelationKey();

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
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTConversationNode_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TConversationNode#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // TConversationNode
