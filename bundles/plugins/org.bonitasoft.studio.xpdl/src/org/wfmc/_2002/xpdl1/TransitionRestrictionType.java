/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transition Restriction Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.TransitionRestrictionType#getJoin <em>Join</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.TransitionRestrictionType#getSplit <em>Split</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTransitionRestrictionType()
 * @model extendedMetaData="name='TransitionRestriction_._type' kind='elementOnly'"
 * @generated
 */
public interface TransitionRestrictionType extends EObject {
	/**
	 * Returns the value of the '<em><b>Join</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Join</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Join</em>' containment reference.
	 * @see #setJoin(JoinType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTransitionRestrictionType_Join()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Join' namespace='##targetNamespace'"
	 * @generated
	 */
	JoinType getJoin();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.TransitionRestrictionType#getJoin <em>Join</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Join</em>' containment reference.
	 * @see #getJoin()
	 * @generated
	 */
	void setJoin(JoinType value);

	/**
	 * Returns the value of the '<em><b>Split</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Split</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Split</em>' containment reference.
	 * @see #setSplit(SplitType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getTransitionRestrictionType_Split()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='Split' namespace='##targetNamespace'"
	 * @generated
	 */
	SplitType getSplit();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.TransitionRestrictionType#getSplit <em>Split</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Split</em>' containment reference.
	 * @see #getSplit()
	 * @generated
	 */
	void setSplit(SplitType value);

} // TransitionRestrictionType
