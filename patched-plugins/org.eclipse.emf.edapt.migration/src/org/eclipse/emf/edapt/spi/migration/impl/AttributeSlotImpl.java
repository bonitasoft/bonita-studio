/*******************************************************************************
 * Copyright (c) 2007, 2010 BMW Car IT, Technische Universitaet Muenchen, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * BMW Car IT - Initial API and implementation
 * Technische Universitaet Muenchen - Major refactoring and extension
 *******************************************************************************/
package org.eclipse.emf.edapt.spi.migration.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.edapt.spi.migration.AttributeSlot;
import org.eclipse.emf.edapt.spi.migration.MigrationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute Slot</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.impl.AttributeSlotImpl#getEAttribute <em>EAttribute</em>}</li>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.impl.AttributeSlotImpl#getValues <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AttributeSlotImpl extends SlotImpl implements AttributeSlot {
	/**
	 * The cached value of the '{@link #getEAttribute() <em>EAttribute</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getEAttribute()
	 * @generated
	 * @ordered
	 */
	protected EAttribute eAttribute;

	/**
	 * The cached value of the '{@link #getValues() <em>Values</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getValues()
	 * @generated
	 * @ordered
	 */
	protected EList<Object> values;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected AttributeSlotImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MigrationPackage.Literals.ATTRIBUTE_SLOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public EAttribute getEAttribute() {
		if (eAttribute != null && eAttribute.eIsProxy()) {
			final InternalEObject oldEAttribute = (InternalEObject) eAttribute;
			eAttribute = (EAttribute) eResolveProxy(oldEAttribute);
			if (eAttribute != oldEAttribute) {
				if (eNotificationRequired()) {
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
						MigrationPackage.ATTRIBUTE_SLOT__EATTRIBUTE, oldEAttribute, eAttribute));
				}
			}
		}
		return eAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public EAttribute basicGetEAttribute() {
		return eAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void setEAttribute(EAttribute newEAttribute) {
		final EAttribute oldEAttribute = eAttribute;
		eAttribute = newEAttribute;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, MigrationPackage.ATTRIBUTE_SLOT__EATTRIBUTE,
				oldEAttribute, eAttribute));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public EList<Object> getValues() {
		if (values == null) {
			values = new EDataTypeEList<Object>(Object.class, this, MigrationPackage.ATTRIBUTE_SLOT__VALUES);
		}
		return values;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case MigrationPackage.ATTRIBUTE_SLOT__EATTRIBUTE:
			if (resolve) {
				return getEAttribute();
			}
			return basicGetEAttribute();
		case MigrationPackage.ATTRIBUTE_SLOT__VALUES:
			return getValues();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case MigrationPackage.ATTRIBUTE_SLOT__EATTRIBUTE:
			setEAttribute((EAttribute) newValue);
			return;
		case MigrationPackage.ATTRIBUTE_SLOT__VALUES:
			getValues().clear();
			getValues().addAll((Collection<? extends Object>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case MigrationPackage.ATTRIBUTE_SLOT__EATTRIBUTE:
			setEAttribute((EAttribute) null);
			return;
		case MigrationPackage.ATTRIBUTE_SLOT__VALUES:
			getValues().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case MigrationPackage.ATTRIBUTE_SLOT__EATTRIBUTE:
			return eAttribute != null;
		case MigrationPackage.ATTRIBUTE_SLOT__VALUES:
			return values != null && !values.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public String toString() {
		if (eIsProxy()) {
			return super.toString();
		}

		final StringBuffer result = new StringBuffer();
		result.append(getEAttribute().getName());
		result.append(" = "); //$NON-NLS-1$
		result.append('[');
		boolean first = true;
		for (final Object value : getValues()) {
			if (!first) {
				result.append(", "); //$NON-NLS-1$
			}
			result.append(value.toString());
			first = false;
		}
		result.append(']');
		return result.toString();
	}

} // AttributeSlotImpl
