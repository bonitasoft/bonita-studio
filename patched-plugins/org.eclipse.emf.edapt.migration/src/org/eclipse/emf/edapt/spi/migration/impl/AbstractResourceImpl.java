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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.edapt.spi.migration.AbstractResource;
import org.eclipse.emf.edapt.spi.migration.MigrationPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.impl.AbstractResourceImpl#getUri <em>Uri</em>}</li>
 * <li>{@link org.eclipse.emf.edapt.spi.migration.impl.AbstractResourceImpl#getEncoding <em>Encoding</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractResourceImpl extends EObjectImpl implements AbstractResource {
	/**
	 * The default value of the '{@link #getUri() <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
	protected static final URI URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUri() <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getUri()
	 * @generated
	 * @ordered
	 */
	protected URI uri = URI_EDEFAULT;

	/**
	 * The default value of the '{@link #getEncoding() <em>Encoding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getEncoding()
	 * @generated
	 * @ordered
	 */
	protected static final String ENCODING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEncoding() <em>Encoding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @see #getEncoding()
	 * @generated
	 * @ordered
	 */
	protected String encoding = ENCODING_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	protected AbstractResourceImpl() {
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
		return MigrationPackage.Literals.ABSTRACT_RESOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public URI getUri() {
		return uri;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void setUri(URI newUri) {
		final URI oldUri = uri;
		uri = newUri;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, MigrationPackage.ABSTRACT_RESOURCE__URI, oldUri, uri));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public String getEncoding() {
		return encoding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void setEncoding(String newEncoding) {
		final String oldEncoding = encoding;
		encoding = newEncoding;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, MigrationPackage.ABSTRACT_RESOURCE__ENCODING,
				oldEncoding, encoding));
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
		case MigrationPackage.ABSTRACT_RESOURCE__URI:
			return getUri();
		case MigrationPackage.ABSTRACT_RESOURCE__ENCODING:
			return getEncoding();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case MigrationPackage.ABSTRACT_RESOURCE__URI:
			setUri((URI) newValue);
			return;
		case MigrationPackage.ABSTRACT_RESOURCE__ENCODING:
			setEncoding((String) newValue);
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
		case MigrationPackage.ABSTRACT_RESOURCE__URI:
			setUri(URI_EDEFAULT);
			return;
		case MigrationPackage.ABSTRACT_RESOURCE__ENCODING:
			setEncoding(ENCODING_EDEFAULT);
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
		case MigrationPackage.ABSTRACT_RESOURCE__URI:
			return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
		case MigrationPackage.ABSTRACT_RESOURCE__ENCODING:
			return ENCODING_EDEFAULT == null ? encoding != null : !ENCODING_EDEFAULT.equals(encoding);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 *
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) {
			return super.toString();
		}

		final StringBuffer result = new StringBuffer(super.toString());
		result.append(" (uri: "); //$NON-NLS-1$
		result.append(uri);
		result.append(", encoding: "); //$NON-NLS-1$
		result.append(encoding);
		result.append(')');
		return result.toString();
	}

} // AbstractResourceImpl
