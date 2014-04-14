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
 * A representation of the model object '<em><b>Type Declarations Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.TypeDeclarationsType#getTypeDeclaration <em>Type Declaration</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTypeDeclarationsType()
 * @model extendedMetaData="name='TypeDeclarations_._type' kind='elementOnly'"
 * @generated
 */
public interface TypeDeclarationsType extends EObject {
	/**
	 * Returns the value of the '<em><b>Type Declaration</b></em>' containment reference list.
	 * The list contents are of type {@link org.wfmc._2002.xpdl1.TypeDeclarationType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type Declaration</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type Declaration</em>' containment reference list.
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTypeDeclarationsType_TypeDeclaration()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='TypeDeclaration' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TypeDeclarationType> getTypeDeclaration();

} // TypeDeclarationsType
