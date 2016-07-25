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
 * A representation of the model object '<em><b>Record Type Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.RecordTypeType#getMember <em>Member</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getRecordTypeType()
 * @model extendedMetaData="name='RecordType_._type' kind='elementOnly'"
 * @generated
 */
public interface RecordTypeType extends EObject {
	/**
	 * Returns the value of the '<em><b>Member</b></em>' containment reference list.
	 * The list contents are of type {@link org.wfmc._2002.xpdl1.MemberType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Member</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Member</em>' containment reference list.
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getRecordTypeType_Member()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='Member' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<MemberType> getMember();

} // RecordTypeType
