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
 * A representation of the model object '<em><b>Split Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.SplitType#getTransitionRefs <em>Transition Refs</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.SplitType#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getSplitType()
 * @model extendedMetaData="name='Split_._type' kind='elementOnly'"
 * @generated
 */
public interface SplitType extends EObject {
	/**
	 * Returns the value of the '<em><b>Transition Refs</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Transition Refs</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Transition Refs</em>' containment reference.
	 * @see #setTransitionRefs(TransitionRefsType)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getSplitType_TransitionRefs()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='TransitionRefs' namespace='##targetNamespace'"
	 * @generated
	 */
	TransitionRefsType getTransitionRefs();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.SplitType#getTransitionRefs <em>Transition Refs</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transition Refs</em>' containment reference.
	 * @see #getTransitionRefs()
	 * @generated
	 */
	void setTransitionRefs(TransitionRefsType value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.wfmc._2002.xpdl1.TypeType4}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see org.wfmc._2002.xpdl1.TypeType4
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #setType(TypeType4)
	 * @see org.wfmc._2002.xpdl1.Xpdl1Package#getSplitType_Type()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='Type'"
	 * @generated
	 */
	TypeType4 getType();

	/**
	 * Sets the value of the '{@link org.wfmc._2002.xpdl1.SplitType#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see org.wfmc._2002.xpdl1.TypeType4
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #getType()
	 * @generated
	 */
	void setType(TypeType4 value);

	/**
	 * Unsets the value of the '{@link org.wfmc._2002.xpdl1.SplitType#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetType()
	 * @see #getType()
	 * @see #setType(TypeType4)
	 * @generated
	 */
	void unsetType();

	/**
	 * Returns whether the value of the '{@link org.wfmc._2002.xpdl1.SplitType#getType <em>Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Type</em>' attribute is set.
	 * @see #unsetType()
	 * @see #getType()
	 * @see #setType(TypeType4)
	 * @generated
	 */
	boolean isSetType();

} // SplitType
