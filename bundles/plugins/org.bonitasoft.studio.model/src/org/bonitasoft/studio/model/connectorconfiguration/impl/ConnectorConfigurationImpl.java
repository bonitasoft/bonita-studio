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
package org.bonitasoft.studio.model.connectorconfiguration.impl;

import java.util.Collection;

import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Connector Configuration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorConfigurationImpl#getDefinitionId <em>Definition Id</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorConfigurationImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorConfigurationImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorConfigurationImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorConfigurationImpl#getModelVersion <em>Model Version</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConnectorConfigurationImpl extends EObjectImpl implements ConnectorConfiguration {
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
     * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getVersion()
     * @generated
     * @ordered
     */
	protected static final String VERSION_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getVersion()
     * @generated
     * @ordered
     */
	protected String version = VERSION_EDEFAULT;

	/**
     * The default value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected static final String NAME_EDEFAULT = null;

	/**
     * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getName()
     * @generated
     * @ordered
     */
	protected String name = NAME_EDEFAULT;

	/**
     * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getParameters()
     * @generated
     * @ordered
     */
	protected EList<ConnectorParameter> parameters;

	/**
     * The default value of the '{@link #getModelVersion() <em>Model Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getModelVersion()
     * @generated
     * @ordered
     */
	protected static final String MODEL_VERSION_EDEFAULT = ""; //$NON-NLS-1$

	/**
     * The cached value of the '{@link #getModelVersion() <em>Model Version</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #getModelVersion()
     * @generated
     * @ordered
     */
	protected String modelVersion = MODEL_VERSION_EDEFAULT;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected ConnectorConfigurationImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	protected EClass eStaticClass() {
        return ConnectorConfigurationPackage.Literals.CONNECTOR_CONFIGURATION;
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
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__DEFINITION_ID, oldDefinitionId, definitionId));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getVersion() {
        return version;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setVersion(String newVersion) {
        String oldVersion = version;
        version = newVersion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__VERSION, oldVersion, version));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getName() {
        return name;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setName(String newName) {
        String oldName = name;
        name = newName;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__NAME, oldName, name));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EList<ConnectorParameter> getParameters() {
        if (parameters == null) {
            parameters = new EObjectContainmentEList<ConnectorParameter>(ConnectorParameter.class, this, ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__PARAMETERS);
        }
        return parameters;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getModelVersion() {
        return modelVersion;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public void setModelVersion(String newModelVersion) {
        String oldModelVersion = modelVersion;
        modelVersion = newModelVersion;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__MODEL_VERSION, oldModelVersion, modelVersion));
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__PARAMETERS:
                return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
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
            case ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__DEFINITION_ID:
                return getDefinitionId();
            case ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__VERSION:
                return getVersion();
            case ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__NAME:
                return getName();
            case ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__PARAMETERS:
                return getParameters();
            case ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__MODEL_VERSION:
                return getModelVersion();
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
            case ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__DEFINITION_ID:
                setDefinitionId((String)newValue);
                return;
            case ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__VERSION:
                setVersion((String)newValue);
                return;
            case ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__NAME:
                setName((String)newValue);
                return;
            case ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__PARAMETERS:
                getParameters().clear();
                getParameters().addAll((Collection<? extends ConnectorParameter>)newValue);
                return;
            case ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__MODEL_VERSION:
                setModelVersion((String)newValue);
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
            case ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__DEFINITION_ID:
                setDefinitionId(DEFINITION_ID_EDEFAULT);
                return;
            case ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__VERSION:
                setVersion(VERSION_EDEFAULT);
                return;
            case ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__NAME:
                setName(NAME_EDEFAULT);
                return;
            case ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__PARAMETERS:
                getParameters().clear();
                return;
            case ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__MODEL_VERSION:
                setModelVersion(MODEL_VERSION_EDEFAULT);
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
            case ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__DEFINITION_ID:
                return DEFINITION_ID_EDEFAULT == null ? definitionId != null : !DEFINITION_ID_EDEFAULT.equals(definitionId);
            case ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__VERSION:
                return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
            case ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__NAME:
                return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
            case ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__PARAMETERS:
                return parameters != null && !parameters.isEmpty();
            case ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION__MODEL_VERSION:
                return MODEL_VERSION_EDEFAULT == null ? modelVersion != null : !MODEL_VERSION_EDEFAULT.equals(modelVersion);
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
        result.append(" (definitionId: "); //$NON-NLS-1$
        result.append(definitionId);
        result.append(", version: "); //$NON-NLS-1$
        result.append(version);
        result.append(", name: "); //$NON-NLS-1$
        result.append(name);
        result.append(", modelVersion: "); //$NON-NLS-1$
        result.append(modelVersion);
        result.append(')');
        return result.toString();
    }

} //ConnectorConfigurationImpl
