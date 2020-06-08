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
 * A representation of the model object '<em><b>Actual Parameters Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.ActualParametersType#getActualParameter <em>Actual Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActualParametersType()
 * @model extendedMetaData="name='ActualParameters_._type' kind='elementOnly'"
 * @generated
 */
public interface ActualParametersType extends EObject {
	/**
	 * Returns the value of the '<em><b>Actual Parameter</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actual Parameter</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actual Parameter</em>' attribute list.
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActualParametersType_ActualParameter()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='ActualParameter' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<String> getActualParameter();

} // ActualParametersType
