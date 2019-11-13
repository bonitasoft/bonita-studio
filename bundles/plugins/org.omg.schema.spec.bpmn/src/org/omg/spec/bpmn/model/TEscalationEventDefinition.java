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
 * A representation of the model object '<em><b>TEscalation Event Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TEscalationEventDefinition#getEscalationRef <em>Escalation Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTEscalationEventDefinition()
 * @model extendedMetaData="name='tEscalationEventDefinition' kind='elementOnly'"
 * @generated
 */
public interface TEscalationEventDefinition extends TEventDefinition {
	/**
	 * Returns the value of the '<em><b>Escalation Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Escalation Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Escalation Ref</em>' attribute.
	 * @see #setEscalationRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTEscalationEventDefinition_EscalationRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='attribute' name='escalationRef'"
	 * @generated
	 */
	QName getEscalationRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TEscalationEventDefinition#getEscalationRef <em>Escalation Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Escalation Ref</em>' attribute.
	 * @see #getEscalationRef()
	 * @generated
	 */
	void setEscalationRef(QName value);

} // TEscalationEventDefinition
