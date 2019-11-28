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
 * A representation of the model object '<em><b>External Packages Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.ExternalPackagesType#getExternalPackage <em>External Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getExternalPackagesType()
 * @model extendedMetaData="name='ExternalPackages_._type' kind='elementOnly'"
 * @generated
 */
public interface ExternalPackagesType extends EObject {
	/**
	 * Returns the value of the '<em><b>External Package</b></em>' containment reference list.
	 * The list contents are of type {@link org.wfmc._2002.xpdl1.ExternalPackageType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>External Package</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>External Package</em>' containment reference list.
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getExternalPackagesType_ExternalPackage()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ExternalPackage' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ExternalPackageType> getExternalPackage();

} // ExternalPackagesType
