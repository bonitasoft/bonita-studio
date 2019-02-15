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
package org.bonitasoft.studio.model.kpi;

import org.bonitasoft.studio.model.process.ProcessPackage;

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
 * @see org.bonitasoft.studio.model.kpi.KpiFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/edapt historyURI='process.history'"
 * @generated
 */
public interface KpiPackage extends EPackage {
	/**
     * The package name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNAME = "kpi"; //$NON-NLS-1$

	/**
     * The package namespace URI.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_URI = "http://www.bonitasoft.org/ns/studio/kpi"; //$NON-NLS-1$

	/**
     * The package namespace name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	String eNS_PREFIX = "kpi"; //$NON-NLS-1$

	/**
     * The singleton instance of the package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	KpiPackage eINSTANCE = org.bonitasoft.studio.model.kpi.impl.KpiPackageImpl.init();

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.kpi.impl.AbstractKPIDefinitionImpl <em>Abstract KPI Definition</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.kpi.impl.AbstractKPIDefinitionImpl
     * @see org.bonitasoft.studio.model.kpi.impl.KpiPackageImpl#getAbstractKPIDefinition()
     * @generated
     */
	int ABSTRACT_KPI_DEFINITION = 0;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_KPI_DEFINITION__DOCUMENTATION = ProcessPackage.ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_KPI_DEFINITION__NAME = ProcessPackage.ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_KPI_DEFINITION__TEXT_ANNOTATION_ATTACHMENT = ProcessPackage.ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_KPI_DEFINITION__VERSION = ProcessPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Fields</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_KPI_DEFINITION__FIELDS = ProcessPackage.ELEMENT_FEATURE_COUNT + 1;

	/**
     * The number of structural features of the '<em>Abstract KPI Definition</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_KPI_DEFINITION_FEATURE_COUNT = ProcessPackage.ELEMENT_FEATURE_COUNT + 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.kpi.impl.AbstractKPIBindingImpl <em>Abstract KPI Binding</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.kpi.impl.AbstractKPIBindingImpl
     * @see org.bonitasoft.studio.model.kpi.impl.KpiPackageImpl#getAbstractKPIBinding()
     * @generated
     */
	int ABSTRACT_KPI_BINDING = 1;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_KPI_BINDING__DOCUMENTATION = ProcessPackage.ELEMENT__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_KPI_BINDING__NAME = ProcessPackage.ELEMENT__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_KPI_BINDING__TEXT_ANNOTATION_ATTACHMENT = ProcessPackage.ELEMENT__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Kpi Definition Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_KPI_BINDING__KPI_DEFINITION_NAME = ProcessPackage.ELEMENT_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Event</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_KPI_BINDING__EVENT = ProcessPackage.ELEMENT_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Ignore Error</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_KPI_BINDING__IGNORE_ERROR = ProcessPackage.ELEMENT_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Use Graphical Editor</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_KPI_BINDING__USE_GRAPHICAL_EDITOR = ProcessPackage.ELEMENT_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Request</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_KPI_BINDING__REQUEST = ProcessPackage.ELEMENT_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_KPI_BINDING__PARAMETERS = ProcessPackage.ELEMENT_FEATURE_COUNT + 5;

	/**
     * The number of structural features of the '<em>Abstract KPI Binding</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int ABSTRACT_KPI_BINDING_FEATURE_COUNT = ProcessPackage.ELEMENT_FEATURE_COUNT + 6;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.kpi.impl.KPIParameterMappingImpl <em>KPI Parameter Mapping</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.kpi.impl.KPIParameterMappingImpl
     * @see org.bonitasoft.studio.model.kpi.impl.KpiPackageImpl#getKPIParameterMapping()
     * @generated
     */
	int KPI_PARAMETER_MAPPING = 2;

	/**
     * The feature id for the '<em><b>Kpi Field Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KPI_PARAMETER_MAPPING__KPI_FIELD_NAME = 0;

	/**
     * The feature id for the '<em><b>Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KPI_PARAMETER_MAPPING__VALUE = 1;

	/**
     * The number of structural features of the '<em>KPI Parameter Mapping</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KPI_PARAMETER_MAPPING_FEATURE_COUNT = 2;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.kpi.impl.DatabaseKPIBindingImpl <em>Database KPI Binding</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.kpi.impl.DatabaseKPIBindingImpl
     * @see org.bonitasoft.studio.model.kpi.impl.KpiPackageImpl#getDatabaseKPIBinding()
     * @generated
     */
	int DATABASE_KPI_BINDING = 3;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_BINDING__DOCUMENTATION = ABSTRACT_KPI_BINDING__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_BINDING__NAME = ABSTRACT_KPI_BINDING__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_BINDING__TEXT_ANNOTATION_ATTACHMENT = ABSTRACT_KPI_BINDING__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Kpi Definition Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_BINDING__KPI_DEFINITION_NAME = ABSTRACT_KPI_BINDING__KPI_DEFINITION_NAME;

	/**
     * The feature id for the '<em><b>Event</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_BINDING__EVENT = ABSTRACT_KPI_BINDING__EVENT;

	/**
     * The feature id for the '<em><b>Ignore Error</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_BINDING__IGNORE_ERROR = ABSTRACT_KPI_BINDING__IGNORE_ERROR;

	/**
     * The feature id for the '<em><b>Use Graphical Editor</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_BINDING__USE_GRAPHICAL_EDITOR = ABSTRACT_KPI_BINDING__USE_GRAPHICAL_EDITOR;

	/**
     * The feature id for the '<em><b>Request</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_BINDING__REQUEST = ABSTRACT_KPI_BINDING__REQUEST;

	/**
     * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_BINDING__PARAMETERS = ABSTRACT_KPI_BINDING__PARAMETERS;

	/**
     * The feature id for the '<em><b>Driverclass Name</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_BINDING__DRIVERCLASS_NAME = ABSTRACT_KPI_BINDING_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Jdbc Url</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_BINDING__JDBC_URL = ABSTRACT_KPI_BINDING_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>User</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_BINDING__USER = ABSTRACT_KPI_BINDING_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Password</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_BINDING__PASSWORD = ABSTRACT_KPI_BINDING_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Jndi Url</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_BINDING__JNDI_URL = ABSTRACT_KPI_BINDING_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Table Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_BINDING__TABLE_NAME = ABSTRACT_KPI_BINDING_FEATURE_COUNT + 5;

	/**
     * The number of structural features of the '<em>Database KPI Binding</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_BINDING_FEATURE_COUNT = ABSTRACT_KPI_BINDING_FEATURE_COUNT + 6;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.kpi.impl.DatabaseKPIDefinitionImpl <em>Database KPI Definition</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.kpi.impl.DatabaseKPIDefinitionImpl
     * @see org.bonitasoft.studio.model.kpi.impl.KpiPackageImpl#getDatabaseKPIDefinition()
     * @generated
     */
	int DATABASE_KPI_DEFINITION = 4;

	/**
     * The feature id for the '<em><b>Documentation</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_DEFINITION__DOCUMENTATION = ABSTRACT_KPI_DEFINITION__DOCUMENTATION;

	/**
     * The feature id for the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_DEFINITION__NAME = ABSTRACT_KPI_DEFINITION__NAME;

	/**
     * The feature id for the '<em><b>Text Annotation Attachment</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_DEFINITION__TEXT_ANNOTATION_ATTACHMENT = ABSTRACT_KPI_DEFINITION__TEXT_ANNOTATION_ATTACHMENT;

	/**
     * The feature id for the '<em><b>Version</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_DEFINITION__VERSION = ABSTRACT_KPI_DEFINITION__VERSION;

	/**
     * The feature id for the '<em><b>Fields</b></em>' containment reference list.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_DEFINITION__FIELDS = ABSTRACT_KPI_DEFINITION__FIELDS;

	/**
     * The feature id for the '<em><b>Default Driverclass Name</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_DEFINITION__DEFAULT_DRIVERCLASS_NAME = ABSTRACT_KPI_DEFINITION_FEATURE_COUNT + 0;

	/**
     * The feature id for the '<em><b>Default Jdbc Url</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_DEFINITION__DEFAULT_JDBC_URL = ABSTRACT_KPI_DEFINITION_FEATURE_COUNT + 1;

	/**
     * The feature id for the '<em><b>Default User</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_DEFINITION__DEFAULT_USER = ABSTRACT_KPI_DEFINITION_FEATURE_COUNT + 2;

	/**
     * The feature id for the '<em><b>Default Password</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_DEFINITION__DEFAULT_PASSWORD = ABSTRACT_KPI_DEFINITION_FEATURE_COUNT + 3;

	/**
     * The feature id for the '<em><b>Default JNDI Url</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_DEFINITION__DEFAULT_JNDI_URL = ABSTRACT_KPI_DEFINITION_FEATURE_COUNT + 4;

	/**
     * The feature id for the '<em><b>Default DB Name</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_DEFINITION__DEFAULT_DB_NAME = ABSTRACT_KPI_DEFINITION_FEATURE_COUNT + 5;

	/**
     * The feature id for the '<em><b>Default Table Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_DEFINITION__DEFAULT_TABLE_NAME = ABSTRACT_KPI_DEFINITION_FEATURE_COUNT + 6;

	/**
     * The number of structural features of the '<em>Database KPI Definition</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int DATABASE_KPI_DEFINITION_FEATURE_COUNT = ABSTRACT_KPI_DEFINITION_FEATURE_COUNT + 7;

	/**
     * The meta object id for the '{@link org.bonitasoft.studio.model.kpi.impl.KPIFieldImpl <em>KPI Field</em>}' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see org.bonitasoft.studio.model.kpi.impl.KPIFieldImpl
     * @see org.bonitasoft.studio.model.kpi.impl.KpiPackageImpl#getKPIField()
     * @generated
     */
	int KPI_FIELD = 5;

	/**
     * The feature id for the '<em><b>Field Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KPI_FIELD__FIELD_NAME = 0;

	/**
     * The feature id for the '<em><b>Field Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KPI_FIELD__FIELD_TYPE = 1;

	/**
     * The feature id for the '<em><b>Use Quotes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KPI_FIELD__USE_QUOTES = 2;

	/**
     * The number of structural features of the '<em>KPI Field</em>' class.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
	int KPI_FIELD_FEATURE_COUNT = 3;


	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.kpi.AbstractKPIDefinition <em>Abstract KPI Definition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract KPI Definition</em>'.
     * @see org.bonitasoft.studio.model.kpi.AbstractKPIDefinition
     * @generated
     */
	EClass getAbstractKPIDefinition();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.kpi.AbstractKPIDefinition#getVersion <em>Version</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Version</em>'.
     * @see org.bonitasoft.studio.model.kpi.AbstractKPIDefinition#getVersion()
     * @see #getAbstractKPIDefinition()
     * @generated
     */
	EAttribute getAbstractKPIDefinition_Version();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.kpi.AbstractKPIDefinition#getFields <em>Fields</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Fields</em>'.
     * @see org.bonitasoft.studio.model.kpi.AbstractKPIDefinition#getFields()
     * @see #getAbstractKPIDefinition()
     * @generated
     */
	EReference getAbstractKPIDefinition_Fields();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.kpi.AbstractKPIBinding <em>Abstract KPI Binding</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Abstract KPI Binding</em>'.
     * @see org.bonitasoft.studio.model.kpi.AbstractKPIBinding
     * @generated
     */
	EClass getAbstractKPIBinding();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.kpi.AbstractKPIBinding#getKpiDefinitionName <em>Kpi Definition Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Kpi Definition Name</em>'.
     * @see org.bonitasoft.studio.model.kpi.AbstractKPIBinding#getKpiDefinitionName()
     * @see #getAbstractKPIBinding()
     * @generated
     */
	EAttribute getAbstractKPIBinding_KpiDefinitionName();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.kpi.AbstractKPIBinding#getEvent <em>Event</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Event</em>'.
     * @see org.bonitasoft.studio.model.kpi.AbstractKPIBinding#getEvent()
     * @see #getAbstractKPIBinding()
     * @generated
     */
	EAttribute getAbstractKPIBinding_Event();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.kpi.AbstractKPIBinding#isIgnoreError <em>Ignore Error</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Ignore Error</em>'.
     * @see org.bonitasoft.studio.model.kpi.AbstractKPIBinding#isIgnoreError()
     * @see #getAbstractKPIBinding()
     * @generated
     */
	EAttribute getAbstractKPIBinding_IgnoreError();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.kpi.AbstractKPIBinding#isUseGraphicalEditor <em>Use Graphical Editor</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Use Graphical Editor</em>'.
     * @see org.bonitasoft.studio.model.kpi.AbstractKPIBinding#isUseGraphicalEditor()
     * @see #getAbstractKPIBinding()
     * @generated
     */
	EAttribute getAbstractKPIBinding_UseGraphicalEditor();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.kpi.AbstractKPIBinding#getRequest <em>Request</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Request</em>'.
     * @see org.bonitasoft.studio.model.kpi.AbstractKPIBinding#getRequest()
     * @see #getAbstractKPIBinding()
     * @generated
     */
	EReference getAbstractKPIBinding_Request();

	/**
     * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.model.kpi.AbstractKPIBinding#getParameters <em>Parameters</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference list '<em>Parameters</em>'.
     * @see org.bonitasoft.studio.model.kpi.AbstractKPIBinding#getParameters()
     * @see #getAbstractKPIBinding()
     * @generated
     */
	EReference getAbstractKPIBinding_Parameters();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.kpi.KPIParameterMapping <em>KPI Parameter Mapping</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>KPI Parameter Mapping</em>'.
     * @see org.bonitasoft.studio.model.kpi.KPIParameterMapping
     * @generated
     */
	EClass getKPIParameterMapping();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.kpi.KPIParameterMapping#getKpiFieldName <em>Kpi Field Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Kpi Field Name</em>'.
     * @see org.bonitasoft.studio.model.kpi.KPIParameterMapping#getKpiFieldName()
     * @see #getKPIParameterMapping()
     * @generated
     */
	EAttribute getKPIParameterMapping_KpiFieldName();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.kpi.KPIParameterMapping#getValue <em>Value</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Value</em>'.
     * @see org.bonitasoft.studio.model.kpi.KPIParameterMapping#getValue()
     * @see #getKPIParameterMapping()
     * @generated
     */
	EReference getKPIParameterMapping_Value();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIBinding <em>Database KPI Binding</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Database KPI Binding</em>'.
     * @see org.bonitasoft.studio.model.kpi.DatabaseKPIBinding
     * @generated
     */
	EClass getDatabaseKPIBinding();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getDriverclassName <em>Driverclass Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Driverclass Name</em>'.
     * @see org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getDriverclassName()
     * @see #getDatabaseKPIBinding()
     * @generated
     */
	EReference getDatabaseKPIBinding_DriverclassName();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getJdbcUrl <em>Jdbc Url</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Jdbc Url</em>'.
     * @see org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getJdbcUrl()
     * @see #getDatabaseKPIBinding()
     * @generated
     */
	EReference getDatabaseKPIBinding_JdbcUrl();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getUser <em>User</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>User</em>'.
     * @see org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getUser()
     * @see #getDatabaseKPIBinding()
     * @generated
     */
	EReference getDatabaseKPIBinding_User();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getPassword <em>Password</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Password</em>'.
     * @see org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getPassword()
     * @see #getDatabaseKPIBinding()
     * @generated
     */
	EReference getDatabaseKPIBinding_Password();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getJndiUrl <em>Jndi Url</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Jndi Url</em>'.
     * @see org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getJndiUrl()
     * @see #getDatabaseKPIBinding()
     * @generated
     */
	EReference getDatabaseKPIBinding_JndiUrl();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getTableName <em>Table Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Table Name</em>'.
     * @see org.bonitasoft.studio.model.kpi.DatabaseKPIBinding#getTableName()
     * @see #getDatabaseKPIBinding()
     * @generated
     */
	EAttribute getDatabaseKPIBinding_TableName();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition <em>Database KPI Definition</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>Database KPI Definition</em>'.
     * @see org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition
     * @generated
     */
	EClass getDatabaseKPIDefinition();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultDriverclassName <em>Default Driverclass Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Default Driverclass Name</em>'.
     * @see org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultDriverclassName()
     * @see #getDatabaseKPIDefinition()
     * @generated
     */
	EReference getDatabaseKPIDefinition_DefaultDriverclassName();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultJdbcUrl <em>Default Jdbc Url</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Default Jdbc Url</em>'.
     * @see org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultJdbcUrl()
     * @see #getDatabaseKPIDefinition()
     * @generated
     */
	EReference getDatabaseKPIDefinition_DefaultJdbcUrl();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultUser <em>Default User</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Default User</em>'.
     * @see org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultUser()
     * @see #getDatabaseKPIDefinition()
     * @generated
     */
	EReference getDatabaseKPIDefinition_DefaultUser();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultPassword <em>Default Password</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Default Password</em>'.
     * @see org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultPassword()
     * @see #getDatabaseKPIDefinition()
     * @generated
     */
	EReference getDatabaseKPIDefinition_DefaultPassword();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultJNDIUrl <em>Default JNDI Url</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Default JNDI Url</em>'.
     * @see org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultJNDIUrl()
     * @see #getDatabaseKPIDefinition()
     * @generated
     */
	EReference getDatabaseKPIDefinition_DefaultJNDIUrl();

	/**
     * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultDBName <em>Default DB Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the containment reference '<em>Default DB Name</em>'.
     * @see org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultDBName()
     * @see #getDatabaseKPIDefinition()
     * @generated
     */
	EReference getDatabaseKPIDefinition_DefaultDBName();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultTableName <em>Default Table Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Default Table Name</em>'.
     * @see org.bonitasoft.studio.model.kpi.DatabaseKPIDefinition#getDefaultTableName()
     * @see #getDatabaseKPIDefinition()
     * @generated
     */
	EAttribute getDatabaseKPIDefinition_DefaultTableName();

	/**
     * Returns the meta object for class '{@link org.bonitasoft.studio.model.kpi.KPIField <em>KPI Field</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for class '<em>KPI Field</em>'.
     * @see org.bonitasoft.studio.model.kpi.KPIField
     * @generated
     */
	EClass getKPIField();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.kpi.KPIField#getFieldName <em>Field Name</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Field Name</em>'.
     * @see org.bonitasoft.studio.model.kpi.KPIField#getFieldName()
     * @see #getKPIField()
     * @generated
     */
	EAttribute getKPIField_FieldName();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.kpi.KPIField#getFieldType <em>Field Type</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Field Type</em>'.
     * @see org.bonitasoft.studio.model.kpi.KPIField#getFieldType()
     * @see #getKPIField()
     * @generated
     */
	EAttribute getKPIField_FieldType();

	/**
     * Returns the meta object for the attribute '{@link org.bonitasoft.studio.model.kpi.KPIField#isUseQuotes <em>Use Quotes</em>}'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Use Quotes</em>'.
     * @see org.bonitasoft.studio.model.kpi.KPIField#isUseQuotes()
     * @see #getKPIField()
     * @generated
     */
	EAttribute getKPIField_UseQuotes();

	/**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
	KpiFactory getKpiFactory();

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
         * The meta object literal for the '{@link org.bonitasoft.studio.model.kpi.impl.AbstractKPIDefinitionImpl <em>Abstract KPI Definition</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.kpi.impl.AbstractKPIDefinitionImpl
         * @see org.bonitasoft.studio.model.kpi.impl.KpiPackageImpl#getAbstractKPIDefinition()
         * @generated
         */
		EClass ABSTRACT_KPI_DEFINITION = eINSTANCE.getAbstractKPIDefinition();

		/**
         * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ABSTRACT_KPI_DEFINITION__VERSION = eINSTANCE.getAbstractKPIDefinition_Version();

		/**
         * The meta object literal for the '<em><b>Fields</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ABSTRACT_KPI_DEFINITION__FIELDS = eINSTANCE.getAbstractKPIDefinition_Fields();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.kpi.impl.AbstractKPIBindingImpl <em>Abstract KPI Binding</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.kpi.impl.AbstractKPIBindingImpl
         * @see org.bonitasoft.studio.model.kpi.impl.KpiPackageImpl#getAbstractKPIBinding()
         * @generated
         */
		EClass ABSTRACT_KPI_BINDING = eINSTANCE.getAbstractKPIBinding();

		/**
         * The meta object literal for the '<em><b>Kpi Definition Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ABSTRACT_KPI_BINDING__KPI_DEFINITION_NAME = eINSTANCE.getAbstractKPIBinding_KpiDefinitionName();

		/**
         * The meta object literal for the '<em><b>Event</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ABSTRACT_KPI_BINDING__EVENT = eINSTANCE.getAbstractKPIBinding_Event();

		/**
         * The meta object literal for the '<em><b>Ignore Error</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ABSTRACT_KPI_BINDING__IGNORE_ERROR = eINSTANCE.getAbstractKPIBinding_IgnoreError();

		/**
         * The meta object literal for the '<em><b>Use Graphical Editor</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute ABSTRACT_KPI_BINDING__USE_GRAPHICAL_EDITOR = eINSTANCE.getAbstractKPIBinding_UseGraphicalEditor();

		/**
         * The meta object literal for the '<em><b>Request</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ABSTRACT_KPI_BINDING__REQUEST = eINSTANCE.getAbstractKPIBinding_Request();

		/**
         * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference ABSTRACT_KPI_BINDING__PARAMETERS = eINSTANCE.getAbstractKPIBinding_Parameters();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.kpi.impl.KPIParameterMappingImpl <em>KPI Parameter Mapping</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.kpi.impl.KPIParameterMappingImpl
         * @see org.bonitasoft.studio.model.kpi.impl.KpiPackageImpl#getKPIParameterMapping()
         * @generated
         */
		EClass KPI_PARAMETER_MAPPING = eINSTANCE.getKPIParameterMapping();

		/**
         * The meta object literal for the '<em><b>Kpi Field Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute KPI_PARAMETER_MAPPING__KPI_FIELD_NAME = eINSTANCE.getKPIParameterMapping_KpiFieldName();

		/**
         * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference KPI_PARAMETER_MAPPING__VALUE = eINSTANCE.getKPIParameterMapping_Value();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.kpi.impl.DatabaseKPIBindingImpl <em>Database KPI Binding</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.kpi.impl.DatabaseKPIBindingImpl
         * @see org.bonitasoft.studio.model.kpi.impl.KpiPackageImpl#getDatabaseKPIBinding()
         * @generated
         */
		EClass DATABASE_KPI_BINDING = eINSTANCE.getDatabaseKPIBinding();

		/**
         * The meta object literal for the '<em><b>Driverclass Name</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DATABASE_KPI_BINDING__DRIVERCLASS_NAME = eINSTANCE.getDatabaseKPIBinding_DriverclassName();

		/**
         * The meta object literal for the '<em><b>Jdbc Url</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DATABASE_KPI_BINDING__JDBC_URL = eINSTANCE.getDatabaseKPIBinding_JdbcUrl();

		/**
         * The meta object literal for the '<em><b>User</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DATABASE_KPI_BINDING__USER = eINSTANCE.getDatabaseKPIBinding_User();

		/**
         * The meta object literal for the '<em><b>Password</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DATABASE_KPI_BINDING__PASSWORD = eINSTANCE.getDatabaseKPIBinding_Password();

		/**
         * The meta object literal for the '<em><b>Jndi Url</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DATABASE_KPI_BINDING__JNDI_URL = eINSTANCE.getDatabaseKPIBinding_JndiUrl();

		/**
         * The meta object literal for the '<em><b>Table Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DATABASE_KPI_BINDING__TABLE_NAME = eINSTANCE.getDatabaseKPIBinding_TableName();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.kpi.impl.DatabaseKPIDefinitionImpl <em>Database KPI Definition</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.kpi.impl.DatabaseKPIDefinitionImpl
         * @see org.bonitasoft.studio.model.kpi.impl.KpiPackageImpl#getDatabaseKPIDefinition()
         * @generated
         */
		EClass DATABASE_KPI_DEFINITION = eINSTANCE.getDatabaseKPIDefinition();

		/**
         * The meta object literal for the '<em><b>Default Driverclass Name</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DATABASE_KPI_DEFINITION__DEFAULT_DRIVERCLASS_NAME = eINSTANCE.getDatabaseKPIDefinition_DefaultDriverclassName();

		/**
         * The meta object literal for the '<em><b>Default Jdbc Url</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DATABASE_KPI_DEFINITION__DEFAULT_JDBC_URL = eINSTANCE.getDatabaseKPIDefinition_DefaultJdbcUrl();

		/**
         * The meta object literal for the '<em><b>Default User</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DATABASE_KPI_DEFINITION__DEFAULT_USER = eINSTANCE.getDatabaseKPIDefinition_DefaultUser();

		/**
         * The meta object literal for the '<em><b>Default Password</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DATABASE_KPI_DEFINITION__DEFAULT_PASSWORD = eINSTANCE.getDatabaseKPIDefinition_DefaultPassword();

		/**
         * The meta object literal for the '<em><b>Default JNDI Url</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DATABASE_KPI_DEFINITION__DEFAULT_JNDI_URL = eINSTANCE.getDatabaseKPIDefinition_DefaultJNDIUrl();

		/**
         * The meta object literal for the '<em><b>Default DB Name</b></em>' containment reference feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EReference DATABASE_KPI_DEFINITION__DEFAULT_DB_NAME = eINSTANCE.getDatabaseKPIDefinition_DefaultDBName();

		/**
         * The meta object literal for the '<em><b>Default Table Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute DATABASE_KPI_DEFINITION__DEFAULT_TABLE_NAME = eINSTANCE.getDatabaseKPIDefinition_DefaultTableName();

		/**
         * The meta object literal for the '{@link org.bonitasoft.studio.model.kpi.impl.KPIFieldImpl <em>KPI Field</em>}' class.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @see org.bonitasoft.studio.model.kpi.impl.KPIFieldImpl
         * @see org.bonitasoft.studio.model.kpi.impl.KpiPackageImpl#getKPIField()
         * @generated
         */
		EClass KPI_FIELD = eINSTANCE.getKPIField();

		/**
         * The meta object literal for the '<em><b>Field Name</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute KPI_FIELD__FIELD_NAME = eINSTANCE.getKPIField_FieldName();

		/**
         * The meta object literal for the '<em><b>Field Type</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute KPI_FIELD__FIELD_TYPE = eINSTANCE.getKPIField_FieldType();

		/**
         * The meta object literal for the '<em><b>Use Quotes</b></em>' attribute feature.
         * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
         * @generated
         */
		EAttribute KPI_FIELD__USE_QUOTES = eINSTANCE.getKPIField_UseQuotes();

	}

} //KpiPackage
