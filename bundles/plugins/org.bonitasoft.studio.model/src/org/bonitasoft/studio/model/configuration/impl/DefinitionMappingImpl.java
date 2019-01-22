/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.configuration.impl;

import org.bonitasoft.studio.model.configuration.ConfigurationPackage;
import org.bonitasoft.studio.model.configuration.DefinitionMapping;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Definition Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.configuration.impl.DefinitionMappingImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.impl.DefinitionMappingImpl#getDefinitionId <em>Definition Id</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.impl.DefinitionMappingImpl#getDefinitionVersion <em>Definition Version</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.impl.DefinitionMappingImpl#getImplementationId <em>Implementation Id</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.impl.DefinitionMappingImpl#getImplementationVersion <em>Implementation Version</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DefinitionMappingImpl extends EObjectImpl implements DefinitionMapping {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefinitionId() <em>Definition Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinitionId()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFINITION_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefinitionId() <em>Definition Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinitionId()
	 * @generated
	 * @ordered
	 */
	protected String definitionId = DEFINITION_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefinitionVersion() <em>Definition Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinitionVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFINITION_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefinitionVersion() <em>Definition Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinitionVersion()
	 * @generated
	 * @ordered
	 */
	protected String definitionVersion = DEFINITION_VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getImplementationId() <em>Implementation Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementationId()
	 * @generated
	 * @ordered
	 */
	protected static final String IMPLEMENTATION_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImplementationId() <em>Implementation Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementationId()
	 * @generated
	 * @ordered
	 */
	protected String implementationId = IMPLEMENTATION_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getImplementationVersion() <em>Implementation Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementationVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String IMPLEMENTATION_VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImplementationVersion() <em>Implementation Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementationVersion()
	 * @generated
	 * @ordered
	 */
	protected String implementationVersion = IMPLEMENTATION_VERSION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DefinitionMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ConfigurationPackage.Literals.DEFINITION_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigurationPackage.DEFINITION_MAPPING__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDefinitionId() {
		return definitionId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDefinitionId(String newDefinitionId) {
		String oldDefinitionId = definitionId;
		definitionId = newDefinitionId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigurationPackage.DEFINITION_MAPPING__DEFINITION_ID, oldDefinitionId, definitionId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDefinitionVersion() {
		return definitionVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDefinitionVersion(String newDefinitionVersion) {
		String oldDefinitionVersion = definitionVersion;
		definitionVersion = newDefinitionVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigurationPackage.DEFINITION_MAPPING__DEFINITION_VERSION, oldDefinitionVersion, definitionVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getImplementationId() {
		return implementationId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setImplementationId(String newImplementationId) {
		String oldImplementationId = implementationId;
		implementationId = newImplementationId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigurationPackage.DEFINITION_MAPPING__IMPLEMENTATION_ID, oldImplementationId, implementationId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getImplementationVersion() {
		return implementationVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setImplementationVersion(String newImplementationVersion) {
		String oldImplementationVersion = implementationVersion;
		implementationVersion = newImplementationVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConfigurationPackage.DEFINITION_MAPPING__IMPLEMENTATION_VERSION, oldImplementationVersion, implementationVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConfigurationPackage.DEFINITION_MAPPING__TYPE:
				return getType();
			case ConfigurationPackage.DEFINITION_MAPPING__DEFINITION_ID:
				return getDefinitionId();
			case ConfigurationPackage.DEFINITION_MAPPING__DEFINITION_VERSION:
				return getDefinitionVersion();
			case ConfigurationPackage.DEFINITION_MAPPING__IMPLEMENTATION_ID:
				return getImplementationId();
			case ConfigurationPackage.DEFINITION_MAPPING__IMPLEMENTATION_VERSION:
				return getImplementationVersion();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ConfigurationPackage.DEFINITION_MAPPING__TYPE:
				setType((String)newValue);
				return;
			case ConfigurationPackage.DEFINITION_MAPPING__DEFINITION_ID:
				setDefinitionId((String)newValue);
				return;
			case ConfigurationPackage.DEFINITION_MAPPING__DEFINITION_VERSION:
				setDefinitionVersion((String)newValue);
				return;
			case ConfigurationPackage.DEFINITION_MAPPING__IMPLEMENTATION_ID:
				setImplementationId((String)newValue);
				return;
			case ConfigurationPackage.DEFINITION_MAPPING__IMPLEMENTATION_VERSION:
				setImplementationVersion((String)newValue);
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
			case ConfigurationPackage.DEFINITION_MAPPING__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case ConfigurationPackage.DEFINITION_MAPPING__DEFINITION_ID:
				setDefinitionId(DEFINITION_ID_EDEFAULT);
				return;
			case ConfigurationPackage.DEFINITION_MAPPING__DEFINITION_VERSION:
				setDefinitionVersion(DEFINITION_VERSION_EDEFAULT);
				return;
			case ConfigurationPackage.DEFINITION_MAPPING__IMPLEMENTATION_ID:
				setImplementationId(IMPLEMENTATION_ID_EDEFAULT);
				return;
			case ConfigurationPackage.DEFINITION_MAPPING__IMPLEMENTATION_VERSION:
				setImplementationVersion(IMPLEMENTATION_VERSION_EDEFAULT);
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
			case ConfigurationPackage.DEFINITION_MAPPING__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case ConfigurationPackage.DEFINITION_MAPPING__DEFINITION_ID:
				return DEFINITION_ID_EDEFAULT == null ? definitionId != null : !DEFINITION_ID_EDEFAULT.equals(definitionId);
			case ConfigurationPackage.DEFINITION_MAPPING__DEFINITION_VERSION:
				return DEFINITION_VERSION_EDEFAULT == null ? definitionVersion != null : !DEFINITION_VERSION_EDEFAULT.equals(definitionVersion);
			case ConfigurationPackage.DEFINITION_MAPPING__IMPLEMENTATION_ID:
				return IMPLEMENTATION_ID_EDEFAULT == null ? implementationId != null : !IMPLEMENTATION_ID_EDEFAULT.equals(implementationId);
			case ConfigurationPackage.DEFINITION_MAPPING__IMPLEMENTATION_VERSION:
				return IMPLEMENTATION_VERSION_EDEFAULT == null ? implementationVersion != null : !IMPLEMENTATION_VERSION_EDEFAULT.equals(implementationVersion);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (type: "); //$NON-NLS-1$
		result.append(type);
		result.append(", definitionId: "); //$NON-NLS-1$
		result.append(definitionId);
		result.append(", definitionVersion: "); //$NON-NLS-1$
		result.append(definitionVersion);
		result.append(", implementationId: "); //$NON-NLS-1$
		result.append(implementationId);
		result.append(", implementationVersion: "); //$NON-NLS-1$
		result.append(implementationVersion);
		result.append(')');
		return result.toString();
	}

} //DefinitionMappingImpl
