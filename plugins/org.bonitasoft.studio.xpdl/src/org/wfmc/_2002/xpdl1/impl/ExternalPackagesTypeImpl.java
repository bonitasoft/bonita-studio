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
import org.wfmc._2002.xpdl1.ExternalPackageType;
import org.wfmc._2002.xpdl1.ExternalPackagesType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>External Packages Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ExternalPackagesTypeImpl#getExternalPackage <em>External Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExternalPackagesTypeImpl extends EObjectImpl implements ExternalPackagesType {
	/**
	 * The cached value of the '{@link #getExternalPackage() <em>External Package</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalPackage()
	 * @generated
	 * @ordered
	 */
	protected EList<ExternalPackageType> externalPackage;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExternalPackagesTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.EXTERNAL_PACKAGES_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ExternalPackageType> getExternalPackage() {
		if (externalPackage == null) {
			externalPackage = new EObjectContainmentEList<ExternalPackageType>(ExternalPackageType.class, this, Xpdl1Package.EXTERNAL_PACKAGES_TYPE__EXTERNAL_PACKAGE);
		}
		return externalPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Xpdl1Package.EXTERNAL_PACKAGES_TYPE__EXTERNAL_PACKAGE:
				return ((InternalEList<?>)getExternalPackage()).basicRemove(otherEnd, msgs);
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
			case Xpdl1Package.EXTERNAL_PACKAGES_TYPE__EXTERNAL_PACKAGE:
				return getExternalPackage();
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
			case Xpdl1Package.EXTERNAL_PACKAGES_TYPE__EXTERNAL_PACKAGE:
				getExternalPackage().clear();
				getExternalPackage().addAll((Collection<? extends ExternalPackageType>)newValue);
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
			case Xpdl1Package.EXTERNAL_PACKAGES_TYPE__EXTERNAL_PACKAGE:
				getExternalPackage().clear();
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
			case Xpdl1Package.EXTERNAL_PACKAGES_TYPE__EXTERNAL_PACKAGE:
				return externalPackage != null && !externalPackage.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ExternalPackagesTypeImpl
