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
import org.wfmc._2002.xpdl1.EnumerationTypeType;
import org.wfmc._2002.xpdl1.EnumerationValueType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enumeration Type Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.EnumerationTypeTypeImpl#getEnumerationValue <em>Enumeration Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnumerationTypeTypeImpl extends EObjectImpl implements EnumerationTypeType {
	/**
	 * The cached value of the '{@link #getEnumerationValue() <em>Enumeration Value</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnumerationValue()
	 * @generated
	 * @ordered
	 */
	protected EList<EnumerationValueType> enumerationValue;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EnumerationTypeTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.ENUMERATION_TYPE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EnumerationValueType> getEnumerationValue() {
		if (enumerationValue == null) {
			enumerationValue = new EObjectContainmentEList<EnumerationValueType>(EnumerationValueType.class, this, Xpdl1Package.ENUMERATION_TYPE_TYPE__ENUMERATION_VALUE);
		}
		return enumerationValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Xpdl1Package.ENUMERATION_TYPE_TYPE__ENUMERATION_VALUE:
				return ((InternalEList<?>)getEnumerationValue()).basicRemove(otherEnd, msgs);
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
			case Xpdl1Package.ENUMERATION_TYPE_TYPE__ENUMERATION_VALUE:
				return getEnumerationValue();
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
			case Xpdl1Package.ENUMERATION_TYPE_TYPE__ENUMERATION_VALUE:
				getEnumerationValue().clear();
				getEnumerationValue().addAll((Collection<? extends EnumerationValueType>)newValue);
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
			case Xpdl1Package.ENUMERATION_TYPE_TYPE__ENUMERATION_VALUE:
				getEnumerationValue().clear();
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
			case Xpdl1Package.ENUMERATION_TYPE_TYPE__ENUMERATION_VALUE:
				return enumerationValue != null && !enumerationValue.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //EnumerationTypeTypeImpl
