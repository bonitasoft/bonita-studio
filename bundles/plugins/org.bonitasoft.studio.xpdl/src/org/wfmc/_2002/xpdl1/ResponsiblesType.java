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
 * A representation of the model object '<em><b>Responsibles Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.ResponsiblesType#getResponsible <em>Responsible</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getResponsiblesType()
 * @model extendedMetaData="name='Responsibles_._type' kind='elementOnly'"
 * @generated
 */
public interface ResponsiblesType extends EObject {
	/**
	 * Returns the value of the '<em><b>Responsible</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Responsible</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Responsible</em>' attribute list.
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getResponsiblesType_Responsible()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='Responsible' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<String> getResponsible();

} // ResponsiblesType
