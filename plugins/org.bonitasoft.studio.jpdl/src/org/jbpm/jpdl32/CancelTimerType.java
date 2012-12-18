/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbpm.jpdl32;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cancel Timer Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.CancelTimerType#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.jbpm.jpdl32.jpdl32Package#getCancelTimerType()
 * @model extendedMetaData="name='cancel-timer_._type' kind='empty'"
 * @generated
 */
public interface CancelTimerType extends EObject {
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
	 * @see org.jbpm.jpdl32.jpdl32Package#getCancelTimerType_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='name'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.jbpm.jpdl32.CancelTimerType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // CancelTimerType
