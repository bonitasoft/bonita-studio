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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/edapt historyURI='process.history'"
 * @generated
 */
public interface ConnectorConfigurationPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "connectorconfiguration"; //$NON-NLS-1$

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http://wwww.bonitasoft.org/model/connector/configuration"; //$NON-NLS-1$

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "connectorconfiguration"; //$NON-NLS-1$

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	ConnectorConfigurationPackage eINSTANCE = org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorConfigurationPackageImpl.init();

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorConfigurationImpl <em>Connector Configuration</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorConfigurationImpl
     * @see org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorConfigurationPackageImpl#getConnectorConfiguration()
     * @generated
     */
	int CONNECTOR_CONFIGURATION = 0;

	/**
     * The feature id for the '<em><b>Definition Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTOR_CONFIGURATION__DEFINITION_ID = 0;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTOR_CONFIGURATION__VERSION = 1;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTOR_CONFIGURATION__NAME = 2;

	/**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTOR_CONFIGURATION__PARAMETERS = 3;

	/**
     * The feature id for the '<em><b>Model Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTOR_CONFIGURATION__MODEL_VERSION = 4;

	/**
     * The number of structural features of the '<em>Connector Configuration</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTOR_CONFIGURATION_FEATURE_COUNT = 5;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorParameterImpl <em>Connector Parameter</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorParameterImpl
     * @see org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorConfigurationPackageImpl#getConnectorParameter()
     * @generated
     */
	int CONNECTOR_PARAMETER = 1;

	/**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTOR_PARAMETER__KEY = 0;

	/**
     * The feature id for the '<em><b>Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTOR_PARAMETER__EXPRESSION = 1;

	/**
     * The number of structural features of the '<em>Connector Parameter</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONNECTOR_PARAMETER_FEATURE_COUNT = 2;


	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration <em>Connector Configuration</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Connector Configuration</em>'.
     * @see org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration
     * @generated
     */
	EClass getConnectorConfiguration();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration#getDefinitionId <em>Definition Id</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Definition Id</em>'.
     * @see org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration#getDefinitionId()
     * @see #getConnectorConfiguration()
     * @generated
     */
	EAttribute getConnectorConfiguration_DefinitionId();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration#getVersion <em>Version</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Version</em>'.
     * @see org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration#getVersion()
     * @see #getConnectorConfiguration()
     * @generated
     */
	EAttribute getConnectorConfiguration_Version();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration#getName()
     * @see #getConnectorConfiguration()
     * @generated
     */
	EAttribute getConnectorConfiguration_Name();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration#getParameters <em>Parameters</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Parameters</em>'.
     * @see org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration#getParameters()
     * @see #getConnectorConfiguration()
     * @generated
     */
	EReference getConnectorConfiguration_Parameters();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration#getModelVersion <em>Model Version</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Model Version</em>'.
     * @see org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration#getModelVersion()
     * @see #getConnectorConfiguration()
     * @generated
     */
	EAttribute getConnectorConfiguration_ModelVersion();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter <em>Connector Parameter</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Connector Parameter</em>'.
     * @see org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter
     * @generated
     */
	EClass getConnectorParameter();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter#getKey <em>Key</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Key</em>'.
     * @see org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter#getKey()
     * @see #getConnectorParameter()
     * @generated
     */
	EAttribute getConnectorParameter_Key();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter#getExpression <em>Expression</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Expression</em>'.
     * @see org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter#getExpression()
     * @see #getConnectorParameter()
     * @generated
     */
	EReference getConnectorParameter_Expression();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	ConnectorConfigurationFactory getConnectorConfigurationFactory();

	/**
     * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
     * @generated
     */
	interface Literals {
		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorConfigurationImpl <em>Connector Configuration</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorConfigurationImpl
         * @see org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorConfigurationPackageImpl#getConnectorConfiguration()
         * @generated
         */
		EClass CONNECTOR_CONFIGURATION = eINSTANCE.getConnectorConfiguration();

		/**
         * The meta object literal for the '<em><b>Definition Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONNECTOR_CONFIGURATION__DEFINITION_ID = eINSTANCE.getConnectorConfiguration_DefinitionId();

		/**
         * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONNECTOR_CONFIGURATION__VERSION = eINSTANCE.getConnectorConfiguration_Version();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONNECTOR_CONFIGURATION__NAME = eINSTANCE.getConnectorConfiguration_Name();

		/**
         * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONNECTOR_CONFIGURATION__PARAMETERS = eINSTANCE.getConnectorConfiguration_Parameters();

		/**
         * The meta object literal for the '<em><b>Model Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONNECTOR_CONFIGURATION__MODEL_VERSION = eINSTANCE.getConnectorConfiguration_ModelVersion();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorParameterImpl <em>Connector Parameter</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorParameterImpl
         * @see org.bonitasoft.studio.model.connectorconfiguration.impl.ConnectorConfigurationPackageImpl#getConnectorParameter()
         * @generated
         */
		EClass CONNECTOR_PARAMETER = eINSTANCE.getConnectorParameter();

		/**
         * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONNECTOR_PARAMETER__KEY = eINSTANCE.getConnectorParameter_Key();

		/**
         * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONNECTOR_PARAMETER__EXPRESSION = eINSTANCE.getConnectorParameter_Expression();

	}

} //ConnectorConfigurationPackage
