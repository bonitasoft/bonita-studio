/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Basic Type Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.BasicTypeType#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getBasicTypeType()
 * @model extendedMetaData="name='BasicType_._type' kind='empty'"
 * @generated
 */
public interface BasicTypeType extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.wfmc._2002.xpdl1.TypeType3}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.wfmc._2002.xpdl1.TypeType3
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #setType(TypeType3)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getBasicTypeType_Type()
	 * @model unsettable="true" required="true"
	 *        extendedMetaData="kind='attribute' name='Type'"
	 * @generated
	 */
	TypeType3 getType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.BasicTypeType#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.wfmc._2002.xpdl1.TypeType3
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #getType()
	 * @generated
	 */
	void setType(TypeType3 value);

	/**
	 * Unsets the value of the '{@link org.wfmc._2002.xpdl1.BasicTypeType#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetType()
	 * @see #getType()
	 * @see #setType(TypeType3)
	 * @generated
	 */
	void unsetType();

	/**
	 * Returns whether the value of the '{@link org.wfmc._2002.xpdl1.BasicTypeType#getType <em>Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Type</em>' attribute is set.
	 * @see #unsetType()
	 * @see #getType()
	 * @see #setType(TypeType3)
	 * @generated
	 */
	boolean isSetType();

} // BasicTypeType
