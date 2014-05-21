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
 * A representation of the model object '<em><b>Transition Restrictions Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.TransitionRestrictionsType#getTransitionRestriction <em>Transition Restriction</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTransitionRestrictionsType()
 * @model extendedMetaData="name='TransitionRestrictions_._type' kind='elementOnly'"
 * @generated
 */
public interface TransitionRestrictionsType extends EObject {
	/**
	 * Returns the value of the '<em><b>Transition Restriction</b></em>' containment reference list.
	 * The list contents are of type {@link org.wfmc._2002.xpdl1.TransitionRestrictionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transition Restriction</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transition Restriction</em>' containment reference list.
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTransitionRestrictionsType_TransitionRestriction()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='TransitionRestriction' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TransitionRestrictionType> getTransitionRestriction();

} // TransitionRestrictionsType
