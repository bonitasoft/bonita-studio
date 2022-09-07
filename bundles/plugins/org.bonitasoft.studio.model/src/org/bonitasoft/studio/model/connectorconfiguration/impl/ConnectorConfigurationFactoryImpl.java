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

import org.bonitasoft.studio.model.connectorconfiguration.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConnectorConfigurationFactoryImpl extends EFactoryImpl implements ConnectorConfigurationFactory {
	/**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static ConnectorConfigurationFactory init() {
        try {
            ConnectorConfigurationFactory theConnectorConfigurationFactory = (ConnectorConfigurationFactory)EPackage.Registry.INSTANCE.getEFactory(ConnectorConfigurationPackage.eNS_URI);
            if (theConnectorConfigurationFactory != null) {
                return theConnectorConfigurationFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ConnectorConfigurationFactoryImpl();
    }

	/**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public ConnectorConfigurationFactoryImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case ConnectorConfigurationPackage.CONNECTOR_CONFIGURATION: return createConnectorConfiguration();
            case ConnectorConfigurationPackage.CONNECTOR_PARAMETER: return createConnectorParameter();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ConnectorConfiguration createConnectorConfiguration() {
        ConnectorConfigurationImpl connectorConfiguration = new ConnectorConfigurationImpl();
        return connectorConfiguration;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ConnectorParameter createConnectorParameter() {
        ConnectorParameterImpl connectorParameter = new ConnectorParameterImpl();
        return connectorParameter;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public ConnectorConfigurationPackage getConnectorConfigurationPackage() {
        return (ConnectorConfigurationPackage)getEPackage();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
	@Deprecated
	public static ConnectorConfigurationPackage getPackage() {
        return ConnectorConfigurationPackage.eINSTANCE;
    }

} //ConnectorConfigurationFactoryImpl
