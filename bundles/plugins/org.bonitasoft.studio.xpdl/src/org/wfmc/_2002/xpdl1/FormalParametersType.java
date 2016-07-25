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
 * A representation of the model object '<em><b>Formal Parameters Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.FormalParametersType#getFormalParameter <em>Formal Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getFormalParametersType()
 * @model extendedMetaData="name='FormalParameters_._type' kind='elementOnly'"
 * @generated
 */
public interface FormalParametersType extends EObject {
	/**
	 * Returns the value of the '<em><b>Formal Parameter</b></em>' containment reference list.
	 * The list contents are of type {@link org.wfmc._2002.xpdl1.FormalParameterType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Formal Parameter</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Formal Parameter</em>' containment reference list.
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getFormalParametersType_FormalParameter()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='FormalParameter' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<FormalParameterType> getFormalParameter();

} // FormalParametersType
