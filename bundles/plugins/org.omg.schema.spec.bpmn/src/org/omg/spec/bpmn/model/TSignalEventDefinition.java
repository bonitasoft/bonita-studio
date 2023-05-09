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
 * A representation of the model object '<em><b>TSignal Event Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TSignalEventDefinition#getSignalRef <em>Signal Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTSignalEventDefinition()
 * @model extendedMetaData="name='tSignalEventDefinition' kind='elementOnly'"
 * @generated
 */
public interface TSignalEventDefinition extends TEventDefinition {
	/**
	 * Returns the value of the '<em><b>Signal Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Signal Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Signal Ref</em>' attribute.
	 * @see #setSignalRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTSignalEventDefinition_SignalRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='attribute' name='signalRef'"
	 * @generated
	 */
	QName getSignalRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TSignalEventDefinition#getSignalRef <em>Signal Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Signal Ref</em>' attribute.
	 * @see #getSignalRef()
	 * @generated
	 */
	void setSignalRef(QName value);

} // TSignalEventDefinition
