/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.wfmc._2002.xpdl1.TransitionRestrictionType;
import org.wfmc._2002.xpdl1.TransitionRestrictionsType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transition Restrictions Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.TransitionRestrictionsTypeImpl#getTransitionRestriction <em>Transition Restriction</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TransitionRestrictionsTypeImpl extends EObjectImpl implements TransitionRestrictionsType {
	/**
	 * The cached value of the '{@link #getTransitionRestriction() <em>Transition Restriction</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransitionRestriction()
	 * @generated
	 * @ordered
	 */
	protected EList<TransitionRestrictionType> transitionRestriction;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransitionRestrictionsTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.TRANSITION_RESTRICTIONS_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TransitionRestrictionType> getTransitionRestriction() {
		if (transitionRestriction == null) {
			transitionRestriction = new EObjectContainmentEList<TransitionRestrictionType>(TransitionRestrictionType.class, this, Xpdl1Package.TRANSITION_RESTRICTIONS_TYPE__TRANSITION_RESTRICTION);
		}
		return transitionRestriction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Xpdl1Package.TRANSITION_RESTRICTIONS_TYPE__TRANSITION_RESTRICTION:
				return ((InternalEList<?>)getTransitionRestriction()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Xpdl1Package.TRANSITION_RESTRICTIONS_TYPE__TRANSITION_RESTRICTION:
				return getTransitionRestriction();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case Xpdl1Package.TRANSITION_RESTRICTIONS_TYPE__TRANSITION_RESTRICTION:
				getTransitionRestriction().clear();
				getTransitionRestriction().addAll((Collection<? extends TransitionRestrictionType>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case Xpdl1Package.TRANSITION_RESTRICTIONS_TYPE__TRANSITION_RESTRICTION:
				getTransitionRestriction().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case Xpdl1Package.TRANSITION_RESTRICTIONS_TYPE__TRANSITION_RESTRICTION:
				return transitionRestriction != null && !transitionRestriction.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TransitionRestrictionsTypeImpl
