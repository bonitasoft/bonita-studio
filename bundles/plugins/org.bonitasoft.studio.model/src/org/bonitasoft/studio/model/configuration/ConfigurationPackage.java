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
package org.bonitasoft.studio.model.configuration;

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
 * @see org.bonitasoft.studio.model.configuration.ConfigurationFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/edapt historyURI='process.history'"
 * @generated
 */
public interface ConfigurationPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "configuration"; //$NON-NLS-1$

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http://www.bonitasoft.org/ns/studio/configuration"; //$NON-NLS-1$

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "configuration"; //$NON-NLS-1$

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	ConfigurationPackage eINSTANCE = org.bonitasoft.studio.model.configuration.impl.ConfigurationPackageImpl.init();

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.configuration.impl.ConfigurationImpl <em>Configuration</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.configuration.impl.ConfigurationImpl
     * @see org.bonitasoft.studio.model.configuration.impl.ConfigurationPackageImpl#getConfiguration()
     * @generated
     */
	int CONFIGURATION = 0;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONFIGURATION__NAME = 0;

	/**
     * The feature id for the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONFIGURATION__DESCRIPTION = 1;

	/**
     * The feature id for the '<em><b>Actor Mappings</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONFIGURATION__ACTOR_MAPPINGS = 2;

	/**
     * The feature id for the '<em><b>Anonymous User Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONFIGURATION__ANONYMOUS_USER_NAME = 3;

	/**
     * The feature id for the '<em><b>Anonymous Password</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONFIGURATION__ANONYMOUS_PASSWORD = 4;

	/**
     * The feature id for the '<em><b>Definition Mappings</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONFIGURATION__DEFINITION_MAPPINGS = 5;

	/**
     * The feature id for the '<em><b>Process Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONFIGURATION__PROCESS_DEPENDENCIES = 6;

	/**
     * The feature id for the '<em><b>Application Dependencies</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONFIGURATION__APPLICATION_DEPENDENCIES = 7;

	/**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONFIGURATION__PARAMETERS = 8;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONFIGURATION__VERSION = 9;

	/**
     * The feature id for the '<em><b>Username</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONFIGURATION__USERNAME = 10;

	/**
     * The feature id for the '<em><b>Password</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONFIGURATION__PASSWORD = 11;

	/**
     * The number of structural features of the '<em>Configuration</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int CONFIGURATION_FEATURE_COUNT = 12;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.configuration.impl.FragmentImpl <em>Fragment</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.configuration.impl.FragmentImpl
     * @see org.bonitasoft.studio.model.configuration.impl.ConfigurationPackageImpl#getFragment()
     * @generated
     */
	int FRAGMENT = 1;

	/**
     * The feature id for the '<em><b>Key</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FRAGMENT__KEY = 0;

	/**
     * The feature id for the '<em><b>Value</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FRAGMENT__VALUE = 1;

	/**
     * The feature id for the '<em><b>Exported</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FRAGMENT__EXPORTED = 2;

	/**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FRAGMENT__TYPE = 3;

	/**
     * The number of structural features of the '<em>Fragment</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FRAGMENT_FEATURE_COUNT = 4;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.configuration.impl.DefinitionMappingImpl <em>Definition Mapping</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.configuration.impl.DefinitionMappingImpl
     * @see org.bonitasoft.studio.model.configuration.impl.ConfigurationPackageImpl#getDefinitionMapping()
     * @generated
     */
	int DEFINITION_MAPPING = 2;

	/**
     * The feature id for the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DEFINITION_MAPPING__TYPE = 0;

	/**
     * The feature id for the '<em><b>Definition Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DEFINITION_MAPPING__DEFINITION_ID = 1;

	/**
     * The feature id for the '<em><b>Definition Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DEFINITION_MAPPING__DEFINITION_VERSION = 2;

	/**
     * The feature id for the '<em><b>Implementation Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DEFINITION_MAPPING__IMPLEMENTATION_ID = 3;

	/**
     * The feature id for the '<em><b>Implementation Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DEFINITION_MAPPING__IMPLEMENTATION_VERSION = 4;

	/**
     * The number of structural features of the '<em>Definition Mapping</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DEFINITION_MAPPING_FEATURE_COUNT = 5;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.configuration.impl.FragmentContainerImpl <em>Fragment Container</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.configuration.impl.FragmentContainerImpl
     * @see org.bonitasoft.studio.model.configuration.impl.ConfigurationPackageImpl#getFragmentContainer()
     * @generated
     */
	int FRAGMENT_CONTAINER = 3;

	/**
     * The feature id for the '<em><b>Children</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FRAGMENT_CONTAINER__CHILDREN = 0;

	/**
     * The feature id for the '<em><b>Parent</b></em>' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FRAGMENT_CONTAINER__PARENT = 1;

	/**
     * The feature id for the '<em><b>Fragments</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FRAGMENT_CONTAINER__FRAGMENTS = 2;

	/**
     * The feature id for the '<em><b>Id</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FRAGMENT_CONTAINER__ID = 3;

	/**
     * The number of structural features of the '<em>Fragment Container</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int FRAGMENT_CONTAINER_FEATURE_COUNT = 4;


	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.configuration.Configuration <em>Configuration</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Configuration</em>'.
     * @see org.bonitasoft.studio.model.configuration.Configuration
     * @generated
     */
	EClass getConfiguration();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.configuration.Configuration#getName <em>Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.bonitasoft.studio.model.configuration.Configuration#getName()
     * @see #getConfiguration()
     * @generated
     */
	EAttribute getConfiguration_Name();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.configuration.Configuration#getDescription <em>Description</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Description</em>'.
     * @see org.bonitasoft.studio.model.configuration.Configuration#getDescription()
     * @see #getConfiguration()
     * @generated
     */
	EAttribute getConfiguration_Description();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.configuration.Configuration#getActorMappings <em>Actor Mappings</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Actor Mappings</em>'.
     * @see org.bonitasoft.studio.model.configuration.Configuration#getActorMappings()
     * @see #getConfiguration()
     * @generated
     */
	EReference getConfiguration_ActorMappings();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.configuration.Configuration#getAnonymousUserName <em>Anonymous User Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Anonymous User Name</em>'.
     * @see org.bonitasoft.studio.model.configuration.Configuration#getAnonymousUserName()
     * @see #getConfiguration()
     * @generated
     */
	EAttribute getConfiguration_AnonymousUserName();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.configuration.Configuration#getAnonymousPassword <em>Anonymous Password</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Anonymous Password</em>'.
     * @see org.bonitasoft.studio.model.configuration.Configuration#getAnonymousPassword()
     * @see #getConfiguration()
     * @generated
     */
	EAttribute getConfiguration_AnonymousPassword();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.configuration.Configuration#getDefinitionMappings <em>Definition Mappings</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Definition Mappings</em>'.
     * @see org.bonitasoft.studio.model.configuration.Configuration#getDefinitionMappings()
     * @see #getConfiguration()
     * @generated
     */
	EReference getConfiguration_DefinitionMappings();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.configuration.Configuration#getProcessDependencies <em>Process Dependencies</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Process Dependencies</em>'.
     * @see org.bonitasoft.studio.model.configuration.Configuration#getProcessDependencies()
     * @see #getConfiguration()
     * @generated
     */
	EReference getConfiguration_ProcessDependencies();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.configuration.Configuration#getApplicationDependencies <em>Application Dependencies</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Application Dependencies</em>'.
     * @see org.bonitasoft.studio.model.configuration.Configuration#getApplicationDependencies()
     * @see #getConfiguration()
     * @generated
     */
	EReference getConfiguration_ApplicationDependencies();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.configuration.Configuration#getParameters <em>Parameters</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Parameters</em>'.
     * @see org.bonitasoft.studio.model.configuration.Configuration#getParameters()
     * @see #getConfiguration()
     * @generated
     */
	EReference getConfiguration_Parameters();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.configuration.Configuration#getVersion <em>Version</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Version</em>'.
     * @see org.bonitasoft.studio.model.configuration.Configuration#getVersion()
     * @see #getConfiguration()
     * @generated
     */
	EAttribute getConfiguration_Version();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.configuration.Configuration#getUsername <em>Username</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Username</em>'.
     * @see org.bonitasoft.studio.model.configuration.Configuration#getUsername()
     * @see #getConfiguration()
     * @generated
     */
	EAttribute getConfiguration_Username();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.configuration.Configuration#getPassword <em>Password</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Password</em>'.
     * @see org.bonitasoft.studio.model.configuration.Configuration#getPassword()
     * @see #getConfiguration()
     * @generated
     */
	EAttribute getConfiguration_Password();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.configuration.Fragment <em>Fragment</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Fragment</em>'.
     * @see org.bonitasoft.studio.model.configuration.Fragment
     * @generated
     */
	EClass getFragment();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.configuration.Fragment#getKey <em>Key</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Key</em>'.
     * @see org.bonitasoft.studio.model.configuration.Fragment#getKey()
     * @see #getFragment()
     * @generated
     */
	EAttribute getFragment_Key();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.configuration.Fragment#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Value</em>'.
     * @see org.bonitasoft.studio.model.configuration.Fragment#getValue()
     * @see #getFragment()
     * @generated
     */
	EAttribute getFragment_Value();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.configuration.Fragment#isExported <em>Exported</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Exported</em>'.
     * @see org.bonitasoft.studio.model.configuration.Fragment#isExported()
     * @see #getFragment()
     * @generated
     */
	EAttribute getFragment_Exported();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.configuration.Fragment#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.bonitasoft.studio.model.configuration.Fragment#getType()
     * @see #getFragment()
     * @generated
     */
	EAttribute getFragment_Type();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.configuration.DefinitionMapping <em>Definition Mapping</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Definition Mapping</em>'.
     * @see org.bonitasoft.studio.model.configuration.DefinitionMapping
     * @generated
     */
	EClass getDefinitionMapping();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.configuration.DefinitionMapping#getType <em>Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.bonitasoft.studio.model.configuration.DefinitionMapping#getType()
     * @see #getDefinitionMapping()
     * @generated
     */
	EAttribute getDefinitionMapping_Type();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.configuration.DefinitionMapping#getDefinitionId <em>Definition Id</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Definition Id</em>'.
     * @see org.bonitasoft.studio.model.configuration.DefinitionMapping#getDefinitionId()
     * @see #getDefinitionMapping()
     * @generated
     */
	EAttribute getDefinitionMapping_DefinitionId();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.configuration.DefinitionMapping#getDefinitionVersion <em>Definition Version</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Definition Version</em>'.
     * @see org.bonitasoft.studio.model.configuration.DefinitionMapping#getDefinitionVersion()
     * @see #getDefinitionMapping()
     * @generated
     */
	EAttribute getDefinitionMapping_DefinitionVersion();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.configuration.DefinitionMapping#getImplementationId <em>Implementation Id</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Implementation Id</em>'.
     * @see org.bonitasoft.studio.model.configuration.DefinitionMapping#getImplementationId()
     * @see #getDefinitionMapping()
     * @generated
     */
	EAttribute getDefinitionMapping_ImplementationId();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.configuration.DefinitionMapping#getImplementationVersion <em>Implementation Version</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Implementation Version</em>'.
     * @see org.bonitasoft.studio.model.configuration.DefinitionMapping#getImplementationVersion()
     * @see #getDefinitionMapping()
     * @generated
     */
	EAttribute getDefinitionMapping_ImplementationVersion();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.configuration.FragmentContainer <em>Fragment Container</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Fragment Container</em>'.
     * @see org.bonitasoft.studio.model.configuration.FragmentContainer
     * @generated
     */
	EClass getFragmentContainer();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.configuration.FragmentContainer#getChildren <em>Children</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Children</em>'.
     * @see org.bonitasoft.studio.model.configuration.FragmentContainer#getChildren()
     * @see #getFragmentContainer()
     * @generated
     */
	EReference getFragmentContainer_Children();

	/**
     * Returns the meta object for the container reference '{@link org.bonitasoft.studio.model.configuration.FragmentContainer#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Parent</em>'.
     * @see org.bonitasoft.studio.model.configuration.FragmentContainer#getParent()
     * @see #getFragmentContainer()
     * @generated
     */
	EReference getFragmentContainer_Parent();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.configuration.FragmentContainer#getFragments <em>Fragments</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Fragments</em>'.
     * @see org.bonitasoft.studio.model.configuration.FragmentContainer#getFragments()
     * @see #getFragmentContainer()
     * @generated
     */
	EReference getFragmentContainer_Fragments();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.configuration.FragmentContainer#getId <em>Id</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Id</em>'.
     * @see org.bonitasoft.studio.model.configuration.FragmentContainer#getId()
     * @see #getFragmentContainer()
     * @generated
     */
	EAttribute getFragmentContainer_Id();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	ConfigurationFactory getConfigurationFactory();

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
         * The meta object literal for the '{@link org.bonitasoft.studio.model.configuration.impl.ConfigurationImpl <em>Configuration</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.configuration.impl.ConfigurationImpl
         * @see org.bonitasoft.studio.model.configuration.impl.ConfigurationPackageImpl#getConfiguration()
         * @generated
         */
		EClass CONFIGURATION = eINSTANCE.getConfiguration();

		/**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONFIGURATION__NAME = eINSTANCE.getConfiguration_Name();

		/**
         * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONFIGURATION__DESCRIPTION = eINSTANCE.getConfiguration_Description();

		/**
         * The meta object literal for the '<em><b>Actor Mappings</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONFIGURATION__ACTOR_MAPPINGS = eINSTANCE.getConfiguration_ActorMappings();

		/**
         * The meta object literal for the '<em><b>Anonymous User Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONFIGURATION__ANONYMOUS_USER_NAME = eINSTANCE.getConfiguration_AnonymousUserName();

		/**
         * The meta object literal for the '<em><b>Anonymous Password</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONFIGURATION__ANONYMOUS_PASSWORD = eINSTANCE.getConfiguration_AnonymousPassword();

		/**
         * The meta object literal for the '<em><b>Definition Mappings</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONFIGURATION__DEFINITION_MAPPINGS = eINSTANCE.getConfiguration_DefinitionMappings();

		/**
         * The meta object literal for the '<em><b>Process Dependencies</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONFIGURATION__PROCESS_DEPENDENCIES = eINSTANCE.getConfiguration_ProcessDependencies();

		/**
         * The meta object literal for the '<em><b>Application Dependencies</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONFIGURATION__APPLICATION_DEPENDENCIES = eINSTANCE.getConfiguration_ApplicationDependencies();

		/**
         * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference CONFIGURATION__PARAMETERS = eINSTANCE.getConfiguration_Parameters();

		/**
         * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONFIGURATION__VERSION = eINSTANCE.getConfiguration_Version();

		/**
         * The meta object literal for the '<em><b>Username</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONFIGURATION__USERNAME = eINSTANCE.getConfiguration_Username();

		/**
         * The meta object literal for the '<em><b>Password</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute CONFIGURATION__PASSWORD = eINSTANCE.getConfiguration_Password();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.configuration.impl.FragmentImpl <em>Fragment</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.configuration.impl.FragmentImpl
         * @see org.bonitasoft.studio.model.configuration.impl.ConfigurationPackageImpl#getFragment()
         * @generated
         */
		EClass FRAGMENT = eINSTANCE.getFragment();

		/**
         * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FRAGMENT__KEY = eINSTANCE.getFragment_Key();

		/**
         * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FRAGMENT__VALUE = eINSTANCE.getFragment_Value();

		/**
         * The meta object literal for the '<em><b>Exported</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FRAGMENT__EXPORTED = eINSTANCE.getFragment_Exported();

		/**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FRAGMENT__TYPE = eINSTANCE.getFragment_Type();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.configuration.impl.DefinitionMappingImpl <em>Definition Mapping</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.configuration.impl.DefinitionMappingImpl
         * @see org.bonitasoft.studio.model.configuration.impl.ConfigurationPackageImpl#getDefinitionMapping()
         * @generated
         */
		EClass DEFINITION_MAPPING = eINSTANCE.getDefinitionMapping();

		/**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DEFINITION_MAPPING__TYPE = eINSTANCE.getDefinitionMapping_Type();

		/**
         * The meta object literal for the '<em><b>Definition Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DEFINITION_MAPPING__DEFINITION_ID = eINSTANCE.getDefinitionMapping_DefinitionId();

		/**
         * The meta object literal for the '<em><b>Definition Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DEFINITION_MAPPING__DEFINITION_VERSION = eINSTANCE.getDefinitionMapping_DefinitionVersion();

		/**
         * The meta object literal for the '<em><b>Implementation Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DEFINITION_MAPPING__IMPLEMENTATION_ID = eINSTANCE.getDefinitionMapping_ImplementationId();

		/**
         * The meta object literal for the '<em><b>Implementation Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DEFINITION_MAPPING__IMPLEMENTATION_VERSION = eINSTANCE.getDefinitionMapping_ImplementationVersion();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.configuration.impl.FragmentContainerImpl <em>Fragment Container</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.configuration.impl.FragmentContainerImpl
         * @see org.bonitasoft.studio.model.configuration.impl.ConfigurationPackageImpl#getFragmentContainer()
         * @generated
         */
		EClass FRAGMENT_CONTAINER = eINSTANCE.getFragmentContainer();

		/**
         * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FRAGMENT_CONTAINER__CHILDREN = eINSTANCE.getFragmentContainer_Children();

		/**
         * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FRAGMENT_CONTAINER__PARENT = eINSTANCE.getFragmentContainer_Parent();

		/**
         * The meta object literal for the '<em><b>Fragments</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference FRAGMENT_CONTAINER__FRAGMENTS = eINSTANCE.getFragmentContainer_Fragments();

		/**
         * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute FRAGMENT_CONTAINER__ID = eINSTANCE.getFragmentContainer_Id();

	}

} //ConfigurationPackage
