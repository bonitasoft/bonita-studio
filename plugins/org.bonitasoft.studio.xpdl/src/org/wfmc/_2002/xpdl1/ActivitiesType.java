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
 * A representation of the model object '<em><b>Activities Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.ActivitiesType#getActivity <em>Activity</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivitiesType()
 * @model extendedMetaData="name='Activities_._type' kind='elementOnly'"
 * @generated
 */
public interface ActivitiesType extends EObject {
	/**
	 * Returns the value of the '<em><b>Activity</b></em>' containment reference list.
	 * The list contents are of type {@link org.wfmc._2002.xpdl1.ActivityType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity</em>' containment reference list.
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivitiesType_Activity()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Activity' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ActivityType> getActivity();

} // ActivitiesType
