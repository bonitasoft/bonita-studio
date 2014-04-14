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
 * A representation of the model object '<em><b>Data Fields Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.DataFieldsType#getDataField <em>Data Field</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDataFieldsType()
 * @model extendedMetaData="name='DataFields_._type' kind='elementOnly'"
 * @generated
 */
public interface DataFieldsType extends EObject {
	/**
	 * Returns the value of the '<em><b>Data Field</b></em>' containment reference list.
	 * The list contents are of type {@link org.wfmc._2002.xpdl1.DataFieldType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Field</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Field</em>' containment reference list.
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getDataFieldsType_DataField()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='DataField' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<DataFieldType> getDataField();

} // DataFieldsType
