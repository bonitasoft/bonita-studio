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
package org.bonitasoft.studio.model.connectorconfiguration;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationPackage
 * @generated
 */
public interface ConnectorConfigurationFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	ConnectorConfigurationFactory eINSTANCE = org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorConfigurationFactoryImpl.init();

	/**
     * Returns a new object of class '<em>Connector Configuration</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Connector Configuration</em>'.
     * @generated
     */
	ConnectorConfiguration createConnectorConfiguration();

	/**
     * Returns a new object of class '<em>Connector Parameter</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Connector Parameter</em>'.
     * @generated
     */
	ConnectorParameter createConnectorParameter();

	/**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	ConnectorConfigurationPackage getConnectorConfigurationPackage();

} //ConnectorConfigurationFactory
