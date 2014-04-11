/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model;

import javax.xml.namespace.QName;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TMessage Event Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TMessageEventDefinition#getOperationRef <em>Operation Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.TMessageEventDefinition#getMessageRef <em>Message Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTMessageEventDefinition()
 * @model extendedMetaData="name='tMessageEventDefinition' kind='elementOnly'"
 * @generated
 */
public interface TMessageEventDefinition extends TEventDefinition {
	/**
	 * Returns the value of the '<em><b>Operation Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operation Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operation Ref</em>' attribute.
	 * @see #setOperationRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTMessageEventDefinition_OperationRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='element' name='operationRef' namespace='##targetNamespace'"
	 * @generated
	 */
	QName getOperationRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TMessageEventDefinition#getOperationRef <em>Operation Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operation Ref</em>' attribute.
	 * @see #getOperationRef()
	 * @generated
	 */
	void setOperationRef(QName value);

	/**
	 * Returns the value of the '<em><b>Message Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Ref</em>' attribute.
	 * @see #setMessageRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTMessageEventDefinition_MessageRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='attribute' name='messageRef'"
	 * @generated
	 */
	QName getMessageRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TMessageEventDefinition#getMessageRef <em>Message Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message Ref</em>' attribute.
	 * @see #getMessageRef()
	 * @generated
	 */
	void setMessageRef(QName value);

} // TMessageEventDefinition
