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
 * A representation of the model object '<em><b>Activity Sets Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.ActivitySetsType#getActivitySet <em>Activity Set</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivitySetsType()
 * @model extendedMetaData="name='ActivitySets_._type' kind='elementOnly'"
 * @generated
 */
public interface ActivitySetsType extends EObject {
	/**
	 * Returns the value of the '<em><b>Activity Set</b></em>' containment reference list.
	 * The list contents are of type {@link org.wfmc._2002.xpdl1.ActivitySetType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Activity Set</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Activity Set</em>' containment reference list.
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getActivitySetsType_ActivitySet()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='ActivitySet' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ActivitySetType> getActivitySet();

} // ActivitySetsType
