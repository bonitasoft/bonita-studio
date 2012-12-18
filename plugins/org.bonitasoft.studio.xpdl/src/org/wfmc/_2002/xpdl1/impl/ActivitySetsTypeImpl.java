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
import org.wfmc._2002.xpdl1.ActivitySetType;
import org.wfmc._2002.xpdl1.ActivitySetsType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity Sets Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ActivitySetsTypeImpl#getActivitySet <em>Activity Set</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActivitySetsTypeImpl extends EObjectImpl implements ActivitySetsType {
	/**
	 * The cached value of the '{@link #getActivitySet() <em>Activity Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivitySet()
	 * @generated
	 * @ordered
	 */
	protected EList<ActivitySetType> activitySet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ActivitySetsTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.ACTIVITY_SETS_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ActivitySetType> getActivitySet() {
		if (activitySet == null) {
			activitySet = new EObjectContainmentEList<ActivitySetType>(ActivitySetType.class, this, Xpdl1Package.ACTIVITY_SETS_TYPE__ACTIVITY_SET);
		}
		return activitySet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Xpdl1Package.ACTIVITY_SETS_TYPE__ACTIVITY_SET:
				return ((InternalEList<?>)getActivitySet()).basicRemove(otherEnd, msgs);
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
			case Xpdl1Package.ACTIVITY_SETS_TYPE__ACTIVITY_SET:
				return getActivitySet();
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
			case Xpdl1Package.ACTIVITY_SETS_TYPE__ACTIVITY_SET:
				getActivitySet().clear();
				getActivitySet().addAll((Collection<? extends ActivitySetType>)newValue);
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
			case Xpdl1Package.ACTIVITY_SETS_TYPE__ACTIVITY_SET:
				getActivitySet().clear();
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
			case Xpdl1Package.ACTIVITY_SETS_TYPE__ACTIVITY_SET:
				return activitySet != null && !activitySet.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ActivitySetsTypeImpl
