/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enumeration Type Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.EnumerationTypeType#getEnumerationValue <em>Enumeration Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getEnumerationTypeType()
 * @model extendedMetaData="name='EnumerationType_._type' kind='elementOnly'"
 * @generated
 */
public interface EnumerationTypeType extends EObject {
	/**
	 * Returns the value of the '<em><b>Enumeration Value</b></em>' containment reference list.
	 * The list contents are of type {@link org.wfmc._2002.xpdl1.EnumerationValueType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Enumeration Value</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Enumeration Value</em>' containment reference list.
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getEnumerationTypeType_EnumerationValue()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='EnumerationValue' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<EnumerationValueType> getEnumerationValue();

} // EnumerationTypeType
