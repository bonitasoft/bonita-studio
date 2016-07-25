/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TSub Conversation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TSubConversation#getConversationNodeGroup <em>Conversation Node Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TSubConversation#getConversationNode <em>Conversation Node</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTSubConversation()
 * @model extendedMetaData="name='tSubConversation' kind='elementOnly'"
 * @generated
 */
public interface TSubConversation extends TConversationNode {
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
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTSubConversation_ConversationNodeGroup()
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
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTSubConversation_ConversationNode()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='conversationNode' namespace='##targetNamespace' group='conversationNode:group'"
	 * @generated
	 */
	EList<TConversationNode> getConversationNode();

} // TSubConversation
