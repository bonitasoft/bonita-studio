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
 * A representation of the model object '<em><b>TError Event Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.TErrorEventDefinition#getErrorRef <em>Error Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.omg.spec.bpmn.model.ModelPackage#getTErrorEventDefinition()
 * @model extendedMetaData="name='tErrorEventDefinition' kind='elementOnly'"
 * @generated
 */
public interface TErrorEventDefinition extends TEventDefinition {
	/**
	 * Returns the value of the '<em><b>Error Ref</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Error Ref</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Error Ref</em>' attribute.
	 * @see #setErrorRef(QName)
	 * @see org.omg.spec.bpmn.model.ModelPackage#getTErrorEventDefinition_ErrorRef()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.QName"
	 *        extendedMetaData="kind='attribute' name='errorRef'"
	 * @generated
	 */
	QName getErrorRef();

	/**
	 * Sets the value of the '{@link org.omg.spec.bpmn.model.TErrorEventDefinition#getErrorRef <em>Error Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Error Ref</em>' attribute.
	 * @see #getErrorRef()
	 * @generated
	 */
	void setErrorRef(QName value);

} // TErrorEventDefinition
