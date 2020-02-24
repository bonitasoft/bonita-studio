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
 * A representation of the model object '<em><b>Block Activity Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.BlockActivityType#getBlockId <em>Block Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getBlockActivityType()
 * @model extendedMetaData="name='BlockActivity_._type' kind='empty'"
 * @generated
 */
public interface BlockActivityType extends EObject {
	/**
	 * Returns the value of the '<em><b>Block Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Block Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Block Id</em>' attribute.
	 * @see #setBlockId(String)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getBlockActivityType_BlockId()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKEN" required="true"
	 *        extendedMetaData="kind='attribute' name='BlockId'"
	 * @generated
	 */
	String getBlockId();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.BlockActivityType#getBlockId <em>Block Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Block Id</em>' attribute.
	 * @see #getBlockId()
	 * @generated
	 */
	void setBlockId(String value);

} // BlockActivityType
