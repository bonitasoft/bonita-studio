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
import org.wfmc._2002.xpdl1.ExtendedAttributeType;
import org.wfmc._2002.xpdl1.ExtendedAttributesType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Extended Attributes Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ExtendedAttributesTypeImpl#getExtendedAttribute <em>Extended Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExtendedAttributesTypeImpl extends EObjectImpl implements ExtendedAttributesType {
	/**
	 * The cached value of the '{@link #getExtendedAttribute() <em>Extended Attribute</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtendedAttribute()
	 * @generated
	 * @ordered
	 */
	protected EList<ExtendedAttributeType> extendedAttribute;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExtendedAttributesTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.EXTENDED_ATTRIBUTES_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExtendedAttributeType> getExtendedAttribute() {
		if (extendedAttribute == null) {
			extendedAttribute = new EObjectContainmentEList<ExtendedAttributeType>(ExtendedAttributeType.class, this, Xpdl1Package.EXTENDED_ATTRIBUTES_TYPE__EXTENDED_ATTRIBUTE);
		}
		return extendedAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Xpdl1Package.EXTENDED_ATTRIBUTES_TYPE__EXTENDED_ATTRIBUTE:
				return ((InternalEList<?>)getExtendedAttribute()).basicRemove(otherEnd, msgs);
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
			case Xpdl1Package.EXTENDED_ATTRIBUTES_TYPE__EXTENDED_ATTRIBUTE:
				return getExtendedAttribute();
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
			case Xpdl1Package.EXTENDED_ATTRIBUTES_TYPE__EXTENDED_ATTRIBUTE:
				getExtendedAttribute().clear();
				getExtendedAttribute().addAll((Collection<? extends ExtendedAttributeType>)newValue);
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
			case Xpdl1Package.EXTENDED_ATTRIBUTES_TYPE__EXTENDED_ATTRIBUTE:
				getExtendedAttribute().clear();
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
			case Xpdl1Package.EXTENDED_ATTRIBUTES_TYPE__EXTENDED_ATTRIBUTE:
				return extendedAttribute != null && !extendedAttribute.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ExtendedAttributesTypeImpl
