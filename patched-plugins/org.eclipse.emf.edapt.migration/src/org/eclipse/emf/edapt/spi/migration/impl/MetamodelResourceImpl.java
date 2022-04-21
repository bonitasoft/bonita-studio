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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.MetamodelResource;
import org.eclipse.emf.edapt.spi.migration.MigrationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Metamodel Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.impl.MetamodelResourceImpl#getRootPackages <em>Root Packages</em>}</li>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.impl.MetamodelResourceImpl#getMetamodel <em>Metamodel</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MetamodelResourceImpl extends AbstractResourceImpl implements MetamodelResource {
	/**
	 * The cached value of the '{@link #getRootPackages() <em>Root Packages</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getRootPackages()
	 * @generated
	 * @ordered
	 */
	protected EList<EPackage> rootPackages;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected MetamodelResourceImpl() {
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
		return MigrationPackage.Literals.METAMODEL_RESOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public EList<EPackage> getRootPackages() {
		if (rootPackages == null) {
			rootPackages = new EObjectResolvingEList<EPackage>(EPackage.class, this,
				MigrationPackage.METAMODEL_RESOURCE__ROOT_PACKAGES);
		}
		return rootPackages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Metamodel getMetamodel() {
		if (eContainerFeatureID() != MigrationPackage.METAMODEL_RESOURCE__METAMODEL) {
			return null;
		}
		return (Metamodel) eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public NotificationChain basicSetMetamodel(Metamodel newMetamodel, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newMetamodel, MigrationPackage.METAMODEL_RESOURCE__METAMODEL, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void setMetamodel(Metamodel newMetamodel) {
		if (newMetamodel != eInternalContainer()
			|| eContainerFeatureID() != MigrationPackage.METAMODEL_RESOURCE__METAMODEL && newMetamodel != null) {
			if (EcoreUtil.isAncestor(this, (EObject) newMetamodel)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newMetamodel != null) {
				msgs = ((InternalEObject) newMetamodel).eInverseAdd(this, MigrationPackage.METAMODEL__RESOURCES,
					Metamodel.class, msgs);
			}
			msgs = basicSetMetamodel(newMetamodel, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		}
		else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, MigrationPackage.METAMODEL_RESOURCE__METAMODEL,
				newMetamodel, newMetamodel));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case MigrationPackage.METAMODEL_RESOURCE__METAMODEL:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetMetamodel((Metamodel) otherEnd, msgs);
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
		case MigrationPackage.METAMODEL_RESOURCE__METAMODEL:
			return basicSetMetamodel(null, msgs);
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
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case MigrationPackage.METAMODEL_RESOURCE__METAMODEL:
			return eInternalContainer().eInverseRemove(this, MigrationPackage.METAMODEL__RESOURCES, Metamodel.class,
				msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
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
		case MigrationPackage.METAMODEL_RESOURCE__ROOT_PACKAGES:
			return getRootPackages();
		case MigrationPackage.METAMODEL_RESOURCE__METAMODEL:
			return getMetamodel();
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
		case MigrationPackage.METAMODEL_RESOURCE__ROOT_PACKAGES:
			getRootPackages().clear();
			getRootPackages().addAll((Collection<? extends EPackage>) newValue);
			return;
		case MigrationPackage.METAMODEL_RESOURCE__METAMODEL:
			setMetamodel((Metamodel) newValue);
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
		case MigrationPackage.METAMODEL_RESOURCE__ROOT_PACKAGES:
			getRootPackages().clear();
			return;
		case MigrationPackage.METAMODEL_RESOURCE__METAMODEL:
			setMetamodel((Metamodel) null);
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
		case MigrationPackage.METAMODEL_RESOURCE__ROOT_PACKAGES:
			return rootPackages != null && !rootPackages.isEmpty();
		case MigrationPackage.METAMODEL_RESOURCE__METAMODEL:
			return getMetamodel() != null;
		}
		return super.eIsSet(featureID);
	}

} // MetamodelResourceImpl
