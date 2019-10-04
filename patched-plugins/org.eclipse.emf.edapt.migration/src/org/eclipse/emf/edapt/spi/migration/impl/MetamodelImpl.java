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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStructuralFeatureImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.edapt.internal.common.EcoreUtils;
import org.eclipse.emf.edapt.internal.migration.DiagnosticException;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edapt.spi.migration.Instance;
import org.eclipse.emf.edapt.spi.migration.Metamodel;
import org.eclipse.emf.edapt.spi.migration.MetamodelResource;
import org.eclipse.emf.edapt.spi.migration.MigrationPackage;
import org.eclipse.emf.edapt.spi.migration.Model;
import org.eclipse.emf.edapt.spi.migration.ReferenceSlot;
import org.eclipse.emf.edapt.spi.migration.Repository;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Metamodel</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.impl.MetamodelImpl#getResources <em>Resources</em>}</li>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.impl.MetamodelImpl#getRepository <em>Repository</em>}</li>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.impl.MetamodelImpl#getDefaultPackage <em>Default Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MetamodelImpl extends EObjectImpl implements Metamodel {
	/**
	 * The cached value of the '{@link #getResources() <em>Resources</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getResources()
	 * @generated
	 * @ordered
	 */
	protected EList<MetamodelResource> resources;
	/**
	 * The cached value of the '{@link #getDefaultPackage() <em>Default Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getDefaultPackage()
	 * @generated
	 * @ordered
	 */
	protected EPackage defaultPackage;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected MetamodelImpl() {
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
		return MigrationPackage.Literals.METAMODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public EList<MetamodelResource> getResources() {
		if (resources == null) {
			resources = new EObjectContainmentWithInverseEList<MetamodelResource>(MetamodelResource.class, this,
				MigrationPackage.METAMODEL__RESOURCES, MigrationPackage.METAMODEL_RESOURCE__METAMODEL);
		}
		return resources;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public Repository getRepository() {
		if (eContainerFeatureID() != MigrationPackage.METAMODEL__REPOSITORY) {
			return null;
		}
		return (Repository) eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public NotificationChain basicSetRepository(Repository newRepository, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newRepository, MigrationPackage.METAMODEL__REPOSITORY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void setRepository(Repository newRepository) {
		if (newRepository != eInternalContainer()
			|| eContainerFeatureID() != MigrationPackage.METAMODEL__REPOSITORY && newRepository != null) {
			if (EcoreUtil.isAncestor(this, (EObject) newRepository)) {
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			}
			NotificationChain msgs = null;
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			if (newRepository != null) {
				msgs = ((InternalEObject) newRepository).eInverseAdd(this, MigrationPackage.REPOSITORY__METAMODEL,
					Repository.class, msgs);
			}
			msgs = basicSetRepository(newRepository, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		}
		else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, MigrationPackage.METAMODEL__REPOSITORY,
				newRepository, newRepository));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public EPackage getDefaultPackage() {
		if (defaultPackage != null && defaultPackage.eIsProxy()) {
			final InternalEObject oldDefaultPackage = (InternalEObject) defaultPackage;
			defaultPackage = (EPackage) eResolveProxy(oldDefaultPackage);
			if (defaultPackage != oldDefaultPackage) {
				if (eNotificationRequired()) {
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
						MigrationPackage.METAMODEL__DEFAULT_PACKAGE, oldDefaultPackage, defaultPackage));
				}
			}
		}
		return defaultPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	public EPackage basicGetDefaultPackage() {
		return defaultPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void setDefaultPackage(EPackage newDefaultPackage) {
		final EPackage oldDefaultPackage = defaultPackage;
		defaultPackage = newDefaultPackage;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, MigrationPackage.METAMODEL__DEFAULT_PACKAGE,
				oldDefaultPackage, defaultPackage));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public EList<EPackage> getEPackages() {
		final EList<EPackage> ePackages = new UniqueEList<EPackage>();
		for (final MetamodelResource resource : getResources()) {
			ePackages.addAll(resource.getRootPackages());
		}
		return ePackages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public void setDefaultPackage(String packageName) {
		final EPackage ePackage = getEPackage(packageName);
		setDefaultPackage(ePackage);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public EEnum getEEnum(String name) {
		try {
			return (EEnum) getEDataType(name);
		} catch (final ClassCastException e) {
			return null;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public EEnumLiteral getEEnumLiteral(String name) {
		try {
			final int pos = name.lastIndexOf('.');
			final String classifierName = name.substring(0, pos);
			final EEnum eEnum = getEEnum(classifierName);
			final String literalName = name.substring(pos + 1);
			final EEnumLiteral literal = eEnum.getEEnumLiteral(literalName);
			return literal;
		} catch (final NullPointerException e) {
			return null;
		} catch (final IndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public void delete(EModelElement metamodelElement) {
		EcoreUtil.delete(metamodelElement);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <V> EList<V> getInverse(EModelElement metamodelElement, EReference reference) {
		return new UniqueEList<V>((List) EcoreUtils.getInverse(
			metamodelElement, reference, getEPackages()));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public EList<EClass> getESubTypes(EClass eClass) {
		return getInverse(eClass, EcorePackage.eINSTANCE.getEClass_ESuperTypes());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public EList<EClass> getEAllSubTypes(EClass eClass) {
		final EList<EClass> subTypes = new UniqueEList<EClass>();
		for (final EClass subType : getESubTypes(eClass)) {
			if (!subTypes.contains(subType)) {
				subTypes.add(subType);
			}
			subTypes.addAll(getEAllSubTypes(subType));
		}
		return subTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public void setEOpposite(EReference reference, EReference opposite) {
		if (reference.getEOpposite() != null) {
			reference.getEOpposite().setEOpposite(null);
		}
		if (opposite != null) {
			final Model model = getRepository().getModel();
			if (model != null) {
				for (final Instance instance : model.getAllInstances(opposite
					.getEContainingClass())) {
					final EList<Instance> inverse = instance.getInverse(reference);
					if (!inverse.isEmpty()) {
						final ReferenceSlot referenceSlot = ((InstanceImpl) instance)
							.getCreateReferenceSlot(opposite);
						referenceSlot.getValues().clear();
						referenceSlot.getValues().addAll(inverse);
					}
				}
			}
			opposite.setEOpposite(reference);
		}
		reference.setEOpposite(opposite);
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
		case MigrationPackage.METAMODEL__RESOURCES:
			return ((InternalEList<InternalEObject>) (InternalEList<?>) getResources()).basicAdd(otherEnd, msgs);
		case MigrationPackage.METAMODEL__REPOSITORY:
			if (eInternalContainer() != null) {
				msgs = eBasicRemoveFromContainer(msgs);
			}
			return basicSetRepository((Repository) otherEnd, msgs);
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
		case MigrationPackage.METAMODEL__RESOURCES:
			return ((InternalEList<?>) getResources()).basicRemove(otherEnd, msgs);
		case MigrationPackage.METAMODEL__REPOSITORY:
			return basicSetRepository(null, msgs);
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
		case MigrationPackage.METAMODEL__REPOSITORY:
			return eInternalContainer().eInverseRemove(this, MigrationPackage.REPOSITORY__METAMODEL, Repository.class,
				msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public EPackage getEPackage(String name) {
		try {
			final String[] path = name.split("\\."); //$NON-NLS-1$
			final int len = path.length;
			EPackage p = findPackage(getEPackages(), path[0]);
			for (int i = 1; i < len; i++) {
				p = findPackage(p.getESubpackages(), path[i]);
			}
			return p;
		} catch (final NullPointerException e) {
			return null;
		} catch (final ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * Find a package by name within a list of packages
	 *
	 * @param name
	 * @return Package
	 */
	private EPackage findPackage(List<EPackage> packages, String name) {
		for (final EPackage p : packages) {
			if (name.equals(p.getName()) || name.equals(p.getNsURI())) {
				return p;
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public EClassifier getEClassifier(String name) {
		try {
			if (getDefaultPackage() != null
				&& getDefaultPackage().getEClassifier(name) != null) {
				return getDefaultPackage().getEClassifier(name);
			}
			final int pos = name.lastIndexOf('.');
			final String packageName = name.substring(0, pos);
			final EPackage p = getEPackage(packageName);
			final String classifierName = name.substring(pos + 1);
			return p.getEClassifier(classifierName);
		} catch (final NullPointerException e) {
			return null;
		} catch (final IndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public EStructuralFeature getEFeature(String name) {
		try {
			final int pos = name.lastIndexOf('.');
			final String classifierName = name.substring(0, pos);
			final EClass c = getEClass(classifierName);
			final String featureName = name.substring(pos + 1);
			final EStructuralFeature feature = c.getEStructuralFeature(featureName);
			return feature;
		} catch (final NullPointerException e) {
			return null;
		} catch (final IndexOutOfBoundsException e) {
			return null;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public EClass getEClass(String name) {
		try {
			return (EClass) getEClassifier(name);
		} catch (final ClassCastException e) {
			return null;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public EReference getEReference(String name) {
		try {
			return (EReference) getEFeature(name);
		} catch (final ClassCastException e) {
			return null;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public EAttribute getEAttribute(String name) {
		try {
			return (EAttribute) getEFeature(name);
		} catch (final ClassCastException e) {
			return null;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public EDataType getEDataType(String name) {
		try {
			return (EDataType) getEClassifier(name);
		} catch (final ClassCastException e) {
			return null;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public EModelElement getElement(String name) {
		final EPackage p = getEPackage(name);
		if (p != null) {
			return p;
		}
		final EClassifier c = getEClassifier(name);
		if (c != null) {
			return c;
		}
		final EStructuralFeature f = getEFeature(name);
		if (f != null) {
			return f;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated NOT
	 */
	@Override
	public void validate() throws MigrationException {
		final BasicDiagnostic diagnostic = new BasicDiagnostic();
		final Diagnostician diagnostician = new Diagnostician();
		for (final EPackage p : getEPackages()) {
			diagnostician.validate(p, diagnostic);
		}
		if (diagnostic.getSeverity() != Diagnostic.OK) {
			throw new MigrationException(new DiagnosticException("Metamodel not valid", diagnostic)); //$NON-NLS-1$
		}
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
		case MigrationPackage.METAMODEL__RESOURCES:
			return getResources();
		case MigrationPackage.METAMODEL__REPOSITORY:
			return getRepository();
		case MigrationPackage.METAMODEL__DEFAULT_PACKAGE:
			if (resolve) {
				return getDefaultPackage();
			}
			return basicGetDefaultPackage();
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
		case MigrationPackage.METAMODEL__RESOURCES:
			getResources().clear();
			getResources().addAll((Collection<? extends MetamodelResource>) newValue);
			return;
		case MigrationPackage.METAMODEL__REPOSITORY:
			setRepository((Repository) newValue);
			return;
		case MigrationPackage.METAMODEL__DEFAULT_PACKAGE:
			setDefaultPackage((EPackage) newValue);
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
		case MigrationPackage.METAMODEL__RESOURCES:
			getResources().clear();
			return;
		case MigrationPackage.METAMODEL__REPOSITORY:
			setRepository((Repository) null);
			return;
		case MigrationPackage.METAMODEL__DEFAULT_PACKAGE:
			setDefaultPackage((EPackage) null);
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
		case MigrationPackage.METAMODEL__RESOURCES:
			return resources != null && !resources.isEmpty();
		case MigrationPackage.METAMODEL__REPOSITORY:
			return getRepository() != null;
		case MigrationPackage.METAMODEL__DEFAULT_PACKAGE:
			return defaultPackage != null;
		}
		return super.eIsSet(featureID);
	}

	/** {@inheritDoc} */
	@Override
	public void refreshCaches() {
		for (final EPackage ePackage : getEPackages()) {
			for (final Iterator<EObject> i = ePackage.eAllContents(); i.hasNext();) {
				final EObject element = i.next();
				if (element instanceof EStructuralFeatureImpl) {
					final EStructuralFeatureImpl feature = (EStructuralFeatureImpl) element;
					feature.setSettingDelegate(null);
				}
				if (element instanceof EEnumLiteral) {
					final EEnumLiteral literal = (EEnumLiteral) element;
					literal.setInstance(literal);
				}
			}
		}
	}

} // MetamodelImpl
