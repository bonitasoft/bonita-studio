/*******************************************************************************
 * Copyright (c) 2007, 2010 BMW Car IT, Technische Universitaet Muenchen, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * BMW Car IT - Initial API and implementation
 * Technische Universitaet Muenchen - Major refactoring and extension
 *******************************************************************************/
package org.eclipse.emf.edapt.spi.migration.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.MigrationPackage;
import org.eclipse.emf.edapt.spi.migration.ReferenceSlot;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reference Slot</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.impl.ReferenceSlotImpl#getEReference <em>EReference</em>}</li>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.impl.ReferenceSlotImpl#getValues <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReferenceSlotImpl extends SlotImpl implements ReferenceSlot {
	/**
	 * The cached value of the '{@link #getEReference() <em>EReference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getEReference()
	 * @generated
	 * @ordered
	 */
	protected EReference eReference;

	/**
	 * The cached value of the '{@link #getValues() <em>Values</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getValues()
	 * @generated
	 * @ordered
	 */
	protected EList<Instance> values;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected ReferenceSlotImpl() {
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
		return MigrationPackage.Literals.REFERENCE_SLOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public EReference getEReference() {
		if (eReference != null && eReference.eIsProxy()) {
			final InternalEObject oldEReference = (InternalEObject) eReference;
			eReference = (EReference) eResolveProxy(oldEReference);
			if (eReference != oldEReference) {
				if (eNotificationRequired()) {
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
						MigrationPackage.REFERENCE_SLOT__EREFERENCE, oldEReference, eReference));
				}
			}
		}
		return eReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public EReference basicGetEReference() {
		return eReference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void setEReference(EReference newEReference) {
		final EReference oldEReference = eReference;
		eReference = newEReference;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, MigrationPackage.REFERENCE_SLOT__EREFERENCE,
				oldEReference, eReference));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public EList<Instance> getValues() {
		if (values == null) {
			values = new EObjectWithInverseResolvingEList.ManyInverse<Instance>(Instance.class, this,
				MigrationPackage.REFERENCE_SLOT__VALUES, MigrationPackage.INSTANCE__REFERENCES);
		}
		return values;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case MigrationPackage.REFERENCE_SLOT__VALUES:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getValues()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case MigrationPackage.REFERENCE_SLOT__VALUES:
			return ((InternalEList<?>) getValues()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
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
		case MigrationPackage.REFERENCE_SLOT__EREFERENCE:
			if (resolve) {
				return getEReference();
			}
			return basicGetEReference();
		case MigrationPackage.REFERENCE_SLOT__VALUES:
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
		case MigrationPackage.REFERENCE_SLOT__EREFERENCE:
			setEReference((EReference) newValue);
			return;
		case MigrationPackage.REFERENCE_SLOT__VALUES:
			getValues().clear();
			getValues().addAll((Collection<? extends Instance>) newValue);
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
		case MigrationPackage.REFERENCE_SLOT__EREFERENCE:
			setEReference((EReference) null);
			return;
		case MigrationPackage.REFERENCE_SLOT__VALUES:
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
		case MigrationPackage.REFERENCE_SLOT__EREFERENCE:
			return eReference != null;
		case MigrationPackage.REFERENCE_SLOT__VALUES:
			return values != null && !values.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		if (eIsProxy()) {
			return super.toString();
		}

		final StringBuffer result = new StringBuffer();
		result.append(getEReference().getName());
		result.append(" = "); //$NON-NLS-1$
		result.append('[');
		boolean first = true;
		for (final Instance value : getValues()) {
			if (!first) {
				result.append(", "); //$NON-NLS-1$
			}
			result.append(value.toString());
			first = false;
		}
		result.append(']');
		return result.toString();

	}
} // ReferenceSlotImpl
