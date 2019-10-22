/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.definition;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/edapt historyURI='connector.history'"
 * @generated
 */
public interface ConnectorDefinitionPackage extends EPackage {
    /**
	 * The package name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNAME = "definition";

    /**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_URI = "http://www.bonitasoft.org/ns/connector/definition/6.1";

    /**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    String eNS_PREFIX = "definition";

    /**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    ConnectorDefinitionPackage eINSTANCE = org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl.init();

    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.definition.impl.ComponentImpl <em>Component</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ComponentImpl
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getComponent()
	 * @generated
	 */
    int COMPONENT = 3;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT__ID = 0;

    /**
	 * The number of structural features of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int COMPONENT_FEATURE_COUNT = 1;

    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.definition.impl.WidgetComponentImpl <em>Widget Component</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.definition.impl.WidgetComponentImpl
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getWidgetComponent()
	 * @generated
	 */
    int WIDGET_COMPONENT = 17;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int WIDGET_COMPONENT__ID = COMPONENT__ID;

    /**
	 * The feature id for the '<em><b>Input Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int WIDGET_COMPONENT__INPUT_NAME = COMPONENT_FEATURE_COUNT + 0;

    /**
	 * The number of structural features of the '<em>Widget Component</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int WIDGET_COMPONENT_FEATURE_COUNT = COMPONENT_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.definition.impl.WidgetImpl <em>Widget</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.definition.impl.WidgetImpl
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getWidget()
	 * @generated
	 */
    int WIDGET = 16;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int WIDGET__ID = WIDGET_COMPONENT__ID;

    /**
	 * The feature id for the '<em><b>Input Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int WIDGET__INPUT_NAME = WIDGET_COMPONENT__INPUT_NAME;

    /**
	 * The number of structural features of the '<em>Widget</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int WIDGET_FEATURE_COUNT = WIDGET_COMPONENT_FEATURE_COUNT + 0;

    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.definition.impl.ArrayImpl <em>Array</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ArrayImpl
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getArray()
	 * @generated
	 */
    int ARRAY = 0;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ARRAY__ID = WIDGET__ID;

    /**
	 * The feature id for the '<em><b>Input Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ARRAY__INPUT_NAME = WIDGET__INPUT_NAME;

    /**
	 * The feature id for the '<em><b>Cols Caption</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ARRAY__COLS_CAPTION = WIDGET_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Cols</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ARRAY__COLS = WIDGET_FEATURE_COUNT + 1;

    /**
	 * The feature id for the '<em><b>Fixed Cols</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ARRAY__FIXED_COLS = WIDGET_FEATURE_COUNT + 2;

    /**
	 * The feature id for the '<em><b>Fixed Rows</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ARRAY__FIXED_ROWS = WIDGET_FEATURE_COUNT + 3;

    /**
	 * The feature id for the '<em><b>Rows</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ARRAY__ROWS = WIDGET_FEATURE_COUNT + 4;

    /**
	 * The number of structural features of the '<em>Array</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int ARRAY_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 5;

    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.definition.impl.CategoryImpl <em>Category</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.definition.impl.CategoryImpl
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getCategory()
	 * @generated
	 */
    int CATEGORY = 1;

    /**
	 * The feature id for the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CATEGORY__ICON = 0;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CATEGORY__ID = 1;

    /**
	 * The feature id for the '<em><b>Parent Category Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CATEGORY__PARENT_CATEGORY_ID = 2;

				/**
	 * The number of structural features of the '<em>Category</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CATEGORY_FEATURE_COUNT = 3;

    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.definition.impl.CheckboxImpl <em>Checkbox</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.definition.impl.CheckboxImpl
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getCheckbox()
	 * @generated
	 */
    int CHECKBOX = 2;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CHECKBOX__ID = WIDGET__ID;

    /**
	 * The feature id for the '<em><b>Input Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CHECKBOX__INPUT_NAME = WIDGET__INPUT_NAME;

    /**
	 * The number of structural features of the '<em>Checkbox</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CHECKBOX_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 0;

    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionImpl <em>Connector Definition</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionImpl
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getConnectorDefinition()
	 * @generated
	 */
    int CONNECTOR_DEFINITION = 4;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONNECTOR_DEFINITION__ID = 0;

    /**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONNECTOR_DEFINITION__VERSION = 1;

    /**
	 * The feature id for the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONNECTOR_DEFINITION__ICON = 2;

    /**
	 * The feature id for the '<em><b>Category</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONNECTOR_DEFINITION__CATEGORY = 3;

    /**
	 * The feature id for the '<em><b>Input</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONNECTOR_DEFINITION__INPUT = 4;

    /**
	 * The feature id for the '<em><b>Output</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONNECTOR_DEFINITION__OUTPUT = 5;

    /**
	 * The feature id for the '<em><b>Page</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONNECTOR_DEFINITION__PAGE = 6;

    /**
	 * The feature id for the '<em><b>Jar Dependency</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONNECTOR_DEFINITION__JAR_DEPENDENCY = 7;

    /**
	 * The number of structural features of the '<em>Connector Definition</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int CONNECTOR_DEFINITION_FEATURE_COUNT = 8;

    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.definition.impl.DocumentRootImpl <em>Document Root</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.definition.impl.DocumentRootImpl
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getDocumentRoot()
	 * @generated
	 */
    int DOCUMENT_ROOT = 5;

    /**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DOCUMENT_ROOT__MIXED = 0;

    /**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

    /**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

    /**
	 * The feature id for the '<em><b>Connector Definition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DOCUMENT_ROOT__CONNECTOR_DEFINITION = 3;

    /**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int DOCUMENT_ROOT_FEATURE_COUNT = 4;

    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.definition.impl.GroupImpl <em>Group</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.definition.impl.GroupImpl
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getGroup()
	 * @generated
	 */
    int GROUP = 6;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GROUP__ID = COMPONENT__ID;

    /**
	 * The feature id for the '<em><b>Widget</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GROUP__WIDGET = COMPONENT_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Optional</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GROUP__OPTIONAL = COMPONENT_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Group</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int GROUP_FEATURE_COUNT = COMPONENT_FEATURE_COUNT + 2;

    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.definition.impl.InputImpl <em>Input</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.definition.impl.InputImpl
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getInput()
	 * @generated
	 */
    int INPUT = 7;

    /**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INPUT__DEFAULT_VALUE = 0;

    /**
	 * The feature id for the '<em><b>Mandatory</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INPUT__MANDATORY = 1;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INPUT__NAME = 2;

    /**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INPUT__TYPE = 3;

    /**
	 * The number of structural features of the '<em>Input</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int INPUT_FEATURE_COUNT = 4;

    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.definition.impl.ListImpl <em>List</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ListImpl
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getList()
	 * @generated
	 */
    int LIST = 8;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LIST__ID = WIDGET__ID;

    /**
	 * The feature id for the '<em><b>Input Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LIST__INPUT_NAME = WIDGET__INPUT_NAME;

    /**
	 * The feature id for the '<em><b>Show Documents</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST__SHOW_DOCUMENTS = WIDGET_FEATURE_COUNT + 0;

				/**
	 * The number of structural features of the '<em>List</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int LIST_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.definition.impl.OutputImpl <em>Output</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.definition.impl.OutputImpl
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getOutput()
	 * @generated
	 */
    int OUTPUT = 9;

    /**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int OUTPUT__NAME = 0;

    /**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int OUTPUT__TYPE = 1;

    /**
	 * The number of structural features of the '<em>Output</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int OUTPUT_FEATURE_COUNT = 2;

    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.definition.impl.PageImpl <em>Page</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.definition.impl.PageImpl
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getPage()
	 * @generated
	 */
    int PAGE = 10;

    /**
	 * The feature id for the '<em><b>Widget</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PAGE__WIDGET = 0;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PAGE__ID = 1;

    /**
	 * The number of structural features of the '<em>Page</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PAGE_FEATURE_COUNT = 2;

    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.definition.impl.TextImpl <em>Text</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.definition.impl.TextImpl
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getText()
	 * @generated
	 */
    int TEXT = 14;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int TEXT__ID = WIDGET__ID;

    /**
	 * The feature id for the '<em><b>Input Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int TEXT__INPUT_NAME = WIDGET__INPUT_NAME;

    /**
	 * The feature id for the '<em><b>Show Documents</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__SHOW_DOCUMENTS = WIDGET_FEATURE_COUNT + 0;

				/**
	 * The number of structural features of the '<em>Text</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int TEXT_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 1;

    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.definition.impl.PasswordImpl <em>Password</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.definition.impl.PasswordImpl
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getPassword()
	 * @generated
	 */
    int PASSWORD = 11;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PASSWORD__ID = TEXT__ID;

    /**
	 * The feature id for the '<em><b>Input Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PASSWORD__INPUT_NAME = TEXT__INPUT_NAME;

    /**
	 * The feature id for the '<em><b>Show Documents</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PASSWORD__SHOW_DOCUMENTS = TEXT__SHOW_DOCUMENTS;

				/**
	 * The number of structural features of the '<em>Password</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int PASSWORD_FEATURE_COUNT = TEXT_FEATURE_COUNT + 0;

    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.definition.impl.RadioGroupImpl <em>Radio Group</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.definition.impl.RadioGroupImpl
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getRadioGroup()
	 * @generated
	 */
    int RADIO_GROUP = 12;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RADIO_GROUP__ID = WIDGET__ID;

    /**
	 * The feature id for the '<em><b>Input Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RADIO_GROUP__INPUT_NAME = WIDGET__INPUT_NAME;

    /**
	 * The feature id for the '<em><b>Choices</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RADIO_GROUP__CHOICES = WIDGET_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Orientation</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RADIO_GROUP__ORIENTATION = WIDGET_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Radio Group</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int RADIO_GROUP_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 2;

    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.definition.impl.SelectImpl <em>Select</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.definition.impl.SelectImpl
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getSelect()
	 * @generated
	 */
    int SELECT = 13;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SELECT__ID = WIDGET__ID;

    /**
	 * The feature id for the '<em><b>Input Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SELECT__INPUT_NAME = WIDGET__INPUT_NAME;

    /**
	 * The feature id for the '<em><b>Items</b></em>' attribute list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SELECT__ITEMS = WIDGET_FEATURE_COUNT + 0;

    /**
	 * The feature id for the '<em><b>Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SELECT__READ_ONLY = WIDGET_FEATURE_COUNT + 1;

    /**
	 * The number of structural features of the '<em>Select</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SELECT_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 2;

    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.definition.impl.TextAreaImpl <em>Text Area</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.definition.impl.TextAreaImpl
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getTextArea()
	 * @generated
	 */
    int TEXT_AREA = 15;

    /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int TEXT_AREA__ID = WIDGET__ID;

    /**
	 * The feature id for the '<em><b>Input Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int TEXT_AREA__INPUT_NAME = WIDGET__INPUT_NAME;

    /**
	 * The number of structural features of the '<em>Text Area</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int TEXT_AREA_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 0;

    /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.definition.impl.UnloadableConnectorDefinitionImpl <em>Unloadable Connector Definition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.definition.impl.UnloadableConnectorDefinitionImpl
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getUnloadableConnectorDefinition()
	 * @generated
	 */
	int UNLOADABLE_CONNECTOR_DEFINITION = 18;

				/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNLOADABLE_CONNECTOR_DEFINITION__ID = CONNECTOR_DEFINITION__ID;

				/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNLOADABLE_CONNECTOR_DEFINITION__VERSION = CONNECTOR_DEFINITION__VERSION;

				/**
	 * The feature id for the '<em><b>Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNLOADABLE_CONNECTOR_DEFINITION__ICON = CONNECTOR_DEFINITION__ICON;

				/**
	 * The feature id for the '<em><b>Category</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNLOADABLE_CONNECTOR_DEFINITION__CATEGORY = CONNECTOR_DEFINITION__CATEGORY;

				/**
	 * The feature id for the '<em><b>Input</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNLOADABLE_CONNECTOR_DEFINITION__INPUT = CONNECTOR_DEFINITION__INPUT;

				/**
	 * The feature id for the '<em><b>Output</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNLOADABLE_CONNECTOR_DEFINITION__OUTPUT = CONNECTOR_DEFINITION__OUTPUT;

				/**
	 * The feature id for the '<em><b>Page</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNLOADABLE_CONNECTOR_DEFINITION__PAGE = CONNECTOR_DEFINITION__PAGE;

				/**
	 * The feature id for the '<em><b>Jar Dependency</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNLOADABLE_CONNECTOR_DEFINITION__JAR_DEPENDENCY = CONNECTOR_DEFINITION__JAR_DEPENDENCY;

				/**
	 * The number of structural features of the '<em>Unloadable Connector Definition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNLOADABLE_CONNECTOR_DEFINITION_FEATURE_COUNT = CONNECTOR_DEFINITION_FEATURE_COUNT + 0;

				/**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.definition.impl.ScriptEditorImpl <em>Script Editor</em>}' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ScriptEditorImpl
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getScriptEditor()
	 * @generated
	 */
    int SCRIPT_EDITOR = 19;

                /**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SCRIPT_EDITOR__ID = WIDGET__ID;

                /**
	 * The feature id for the '<em><b>Input Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SCRIPT_EDITOR__INPUT_NAME = WIDGET__INPUT_NAME;

                /**
	 * The feature id for the '<em><b>Interpreter</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SCRIPT_EDITOR__INTERPRETER = WIDGET_FEATURE_COUNT + 0;

                /**
	 * The number of structural features of the '<em>Script Editor</em>' class.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    int SCRIPT_EDITOR_FEATURE_COUNT = WIDGET_FEATURE_COUNT + 1;

                /**
	 * The meta object id for the '{@link org.bonitasoft.studio.connector.model.definition.Orientation <em>Orientation</em>}' enum.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.definition.Orientation
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getOrientation()
	 * @generated
	 */
    int ORIENTATION = 20;

    /**
	 * The meta object id for the '<em>Orientation Object</em>' data type.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.bonitasoft.studio.connector.model.definition.Orientation
	 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getOrientationObject()
	 * @generated
	 */
    int ORIENTATION_OBJECT = 21;


    /**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.definition.Array <em>Array</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Array
	 * @generated
	 */
    EClass getArray();

    /**
	 * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.connector.model.definition.Array#getColsCaption <em>Cols Caption</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Cols Caption</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Array#getColsCaption()
	 * @see #getArray()
	 * @generated
	 */
    EAttribute getArray_ColsCaption();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.Array#getCols <em>Cols</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Cols</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Array#getCols()
	 * @see #getArray()
	 * @generated
	 */
    EAttribute getArray_Cols();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.Array#isFixedCols <em>Fixed Cols</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fixed Cols</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Array#isFixedCols()
	 * @see #getArray()
	 * @generated
	 */
    EAttribute getArray_FixedCols();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.Array#isFixedRows <em>Fixed Rows</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fixed Rows</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Array#isFixedRows()
	 * @see #getArray()
	 * @generated
	 */
    EAttribute getArray_FixedRows();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.Array#getRows <em>Rows</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rows</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Array#getRows()
	 * @see #getArray()
	 * @generated
	 */
    EAttribute getArray_Rows();

    /**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.definition.Category <em>Category</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Category</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Category
	 * @generated
	 */
    EClass getCategory();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.Category#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Icon</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Category#getIcon()
	 * @see #getCategory()
	 * @generated
	 */
    EAttribute getCategory_Icon();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.Category#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Category#getId()
	 * @see #getCategory()
	 * @generated
	 */
    EAttribute getCategory_Id();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.Category#getParentCategoryId <em>Parent Category Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Parent Category Id</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Category#getParentCategoryId()
	 * @see #getCategory()
	 * @generated
	 */
	EAttribute getCategory_ParentCategoryId();

				/**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.definition.Checkbox <em>Checkbox</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Checkbox</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Checkbox
	 * @generated
	 */
    EClass getCheckbox();

    /**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.definition.Component <em>Component</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Component
	 * @generated
	 */
    EClass getComponent();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.Component#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Component#getId()
	 * @see #getComponent()
	 * @generated
	 */
    EAttribute getComponent_Id();

    /**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.definition.ConnectorDefinition <em>Connector Definition</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connector Definition</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinition
	 * @generated
	 */
    EClass getConnectorDefinition();

    /**
	 * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.connector.model.definition.ConnectorDefinition#getCategory <em>Category</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Category</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinition#getCategory()
	 * @see #getConnectorDefinition()
	 * @generated
	 */
    EReference getConnectorDefinition_Category();

    /**
	 * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.connector.model.definition.ConnectorDefinition#getInput <em>Input</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Input</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinition#getInput()
	 * @see #getConnectorDefinition()
	 * @generated
	 */
    EReference getConnectorDefinition_Input();

    /**
	 * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.connector.model.definition.ConnectorDefinition#getOutput <em>Output</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Output</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinition#getOutput()
	 * @see #getConnectorDefinition()
	 * @generated
	 */
    EReference getConnectorDefinition_Output();

    /**
	 * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.connector.model.definition.ConnectorDefinition#getPage <em>Page</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Page</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinition#getPage()
	 * @see #getConnectorDefinition()
	 * @generated
	 */
    EReference getConnectorDefinition_Page();

    /**
	 * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.connector.model.definition.ConnectorDefinition#getJarDependency <em>Jar Dependency</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Jar Dependency</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinition#getJarDependency()
	 * @see #getConnectorDefinition()
	 * @generated
	 */
    EAttribute getConnectorDefinition_JarDependency();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.ConnectorDefinition#getIcon <em>Icon</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Icon</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinition#getIcon()
	 * @see #getConnectorDefinition()
	 * @generated
	 */
    EAttribute getConnectorDefinition_Icon();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.ConnectorDefinition#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinition#getId()
	 * @see #getConnectorDefinition()
	 * @generated
	 */
    EAttribute getConnectorDefinition_Id();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.ConnectorDefinition#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinition#getVersion()
	 * @see #getConnectorDefinition()
	 * @generated
	 */
    EAttribute getConnectorDefinition_Version();

    /**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.definition.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.DocumentRoot
	 * @generated
	 */
    EClass getDocumentRoot();

    /**
	 * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.connector.model.definition.DocumentRoot#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.DocumentRoot#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
    EAttribute getDocumentRoot_Mixed();

    /**
	 * Returns the meta object for the map '{@link org.bonitasoft.studio.connector.model.definition.DocumentRoot#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.DocumentRoot#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
    EReference getDocumentRoot_XMLNSPrefixMap();

    /**
	 * Returns the meta object for the map '{@link org.bonitasoft.studio.connector.model.definition.DocumentRoot#getXSISchemaLocation <em>XSI Schema Location</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.DocumentRoot#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
    EReference getDocumentRoot_XSISchemaLocation();

    /**
	 * Returns the meta object for the containment reference '{@link org.bonitasoft.studio.connector.model.definition.DocumentRoot#getConnectorDefinition <em>Connector Definition</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Connector Definition</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.DocumentRoot#getConnectorDefinition()
	 * @see #getDocumentRoot()
	 * @generated
	 */
    EReference getDocumentRoot_ConnectorDefinition();

    /**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.definition.Group <em>Group</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Group</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Group
	 * @generated
	 */
    EClass getGroup();

    /**
	 * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.connector.model.definition.Group#getWidget <em>Widget</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Widget</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Group#getWidget()
	 * @see #getGroup()
	 * @generated
	 */
    EReference getGroup_Widget();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.Group#isOptional <em>Optional</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Optional</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Group#isOptional()
	 * @see #getGroup()
	 * @generated
	 */
    EAttribute getGroup_Optional();

    /**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.definition.Input <em>Input</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Input
	 * @generated
	 */
    EClass getInput();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.Input#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Value</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Input#getDefaultValue()
	 * @see #getInput()
	 * @generated
	 */
    EAttribute getInput_DefaultValue();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.Input#isMandatory <em>Mandatory</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Mandatory</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Input#isMandatory()
	 * @see #getInput()
	 * @generated
	 */
    EAttribute getInput_Mandatory();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.Input#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Input#getName()
	 * @see #getInput()
	 * @generated
	 */
    EAttribute getInput_Name();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.Input#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Input#getType()
	 * @see #getInput()
	 * @generated
	 */
    EAttribute getInput_Type();

    /**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.definition.List <em>List</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.List
	 * @generated
	 */
    EClass getList();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.List#isShowDocuments <em>Show Documents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Show Documents</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.List#isShowDocuments()
	 * @see #getList()
	 * @generated
	 */
	EAttribute getList_ShowDocuments();

				/**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.definition.Output <em>Output</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Output</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Output
	 * @generated
	 */
    EClass getOutput();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.Output#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Output#getName()
	 * @see #getOutput()
	 * @generated
	 */
    EAttribute getOutput_Name();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.Output#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Output#getType()
	 * @see #getOutput()
	 * @generated
	 */
    EAttribute getOutput_Type();

    /**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.definition.Page <em>Page</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Page</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Page
	 * @generated
	 */
    EClass getPage();

    /**
	 * Returns the meta object for the containment reference list '{@link org.bonitasoft.studio.connector.model.definition.Page#getWidget <em>Widget</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Widget</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Page#getWidget()
	 * @see #getPage()
	 * @generated
	 */
    EReference getPage_Widget();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.Page#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Page#getId()
	 * @see #getPage()
	 * @generated
	 */
    EAttribute getPage_Id();

    /**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.definition.Password <em>Password</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Password</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Password
	 * @generated
	 */
    EClass getPassword();

    /**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.definition.RadioGroup <em>Radio Group</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Radio Group</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.RadioGroup
	 * @generated
	 */
    EClass getRadioGroup();

    /**
	 * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.connector.model.definition.RadioGroup#getChoices <em>Choices</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Choices</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.RadioGroup#getChoices()
	 * @see #getRadioGroup()
	 * @generated
	 */
    EAttribute getRadioGroup_Choices();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.RadioGroup#getOrientation <em>Orientation</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Orientation</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.RadioGroup#getOrientation()
	 * @see #getRadioGroup()
	 * @generated
	 */
    EAttribute getRadioGroup_Orientation();

    /**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.definition.Select <em>Select</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Select</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Select
	 * @generated
	 */
    EClass getSelect();

    /**
	 * Returns the meta object for the attribute list '{@link org.bonitasoft.studio.connector.model.definition.Select#getItems <em>Items</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Items</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Select#getItems()
	 * @see #getSelect()
	 * @generated
	 */
    EAttribute getSelect_Items();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.Select#isReadOnly <em>Read Only</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Read Only</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Select#isReadOnly()
	 * @see #getSelect()
	 * @generated
	 */
    EAttribute getSelect_ReadOnly();

    /**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.definition.Text <em>Text</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Text
	 * @generated
	 */
    EClass getText();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.Text#isShowDocuments <em>Show Documents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Show Documents</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Text#isShowDocuments()
	 * @see #getText()
	 * @generated
	 */
	EAttribute getText_ShowDocuments();

				/**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.definition.TextArea <em>Text Area</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text Area</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.TextArea
	 * @generated
	 */
    EClass getTextArea();

    /**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.definition.Widget <em>Widget</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Widget</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Widget
	 * @generated
	 */
    EClass getWidget();

    /**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.definition.WidgetComponent <em>Widget Component</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Widget Component</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.WidgetComponent
	 * @generated
	 */
    EClass getWidgetComponent();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.WidgetComponent#getInputName <em>Input Name</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Input Name</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.WidgetComponent#getInputName()
	 * @see #getWidgetComponent()
	 * @generated
	 */
    EAttribute getWidgetComponent_InputName();

    /**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.definition.UnloadableConnectorDefinition <em>Unloadable Connector Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unloadable Connector Definition</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.UnloadableConnectorDefinition
	 * @generated
	 */
	EClass getUnloadableConnectorDefinition();

				/**
	 * Returns the meta object for class '{@link org.bonitasoft.studio.connector.model.definition.ScriptEditor <em>Script Editor</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Script Editor</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.ScriptEditor
	 * @generated
	 */
    EClass getScriptEditor();

    /**
	 * Returns the meta object for the attribute '{@link org.bonitasoft.studio.connector.model.definition.ScriptEditor#getInterpreter <em>Interpreter</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interpreter</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.ScriptEditor#getInterpreter()
	 * @see #getScriptEditor()
	 * @generated
	 */
    EAttribute getScriptEditor_Interpreter();

                /**
	 * Returns the meta object for enum '{@link org.bonitasoft.studio.connector.model.definition.Orientation <em>Orientation</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Orientation</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Orientation
	 * @generated
	 */
    EEnum getOrientation();

    /**
	 * Returns the meta object for data type '{@link org.bonitasoft.studio.connector.model.definition.Orientation <em>Orientation Object</em>}'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Orientation Object</em>'.
	 * @see org.bonitasoft.studio.connector.model.definition.Orientation
	 * @model instanceClass="org.bonitasoft.studio.connector.model.definition.Orientation"
	 *        extendedMetaData="name='Orientation:Object' baseType='Orientation'"
	 * @generated
	 */
    EDataType getOrientationObject();

    /**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
    ConnectorDefinitionFactory getConnectorDefinitionFactory();

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
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.definition.impl.ArrayImpl <em>Array</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ArrayImpl
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getArray()
		 * @generated
		 */
        EClass ARRAY = eINSTANCE.getArray();

        /**
		 * The meta object literal for the '<em><b>Cols Caption</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute ARRAY__COLS_CAPTION = eINSTANCE.getArray_ColsCaption();

        /**
		 * The meta object literal for the '<em><b>Cols</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute ARRAY__COLS = eINSTANCE.getArray_Cols();

        /**
		 * The meta object literal for the '<em><b>Fixed Cols</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute ARRAY__FIXED_COLS = eINSTANCE.getArray_FixedCols();

        /**
		 * The meta object literal for the '<em><b>Fixed Rows</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute ARRAY__FIXED_ROWS = eINSTANCE.getArray_FixedRows();

        /**
		 * The meta object literal for the '<em><b>Rows</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute ARRAY__ROWS = eINSTANCE.getArray_Rows();

        /**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.definition.impl.CategoryImpl <em>Category</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.definition.impl.CategoryImpl
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getCategory()
		 * @generated
		 */
        EClass CATEGORY = eINSTANCE.getCategory();

        /**
		 * The meta object literal for the '<em><b>Icon</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute CATEGORY__ICON = eINSTANCE.getCategory_Icon();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute CATEGORY__ID = eINSTANCE.getCategory_Id();

        /**
		 * The meta object literal for the '<em><b>Parent Category Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CATEGORY__PARENT_CATEGORY_ID = eINSTANCE.getCategory_ParentCategoryId();

								/**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.definition.impl.CheckboxImpl <em>Checkbox</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.definition.impl.CheckboxImpl
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getCheckbox()
		 * @generated
		 */
        EClass CHECKBOX = eINSTANCE.getCheckbox();

        /**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.definition.impl.ComponentImpl <em>Component</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ComponentImpl
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getComponent()
		 * @generated
		 */
        EClass COMPONENT = eINSTANCE.getComponent();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute COMPONENT__ID = eINSTANCE.getComponent_Id();

        /**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionImpl <em>Connector Definition</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionImpl
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getConnectorDefinition()
		 * @generated
		 */
        EClass CONNECTOR_DEFINITION = eINSTANCE.getConnectorDefinition();

        /**
		 * The meta object literal for the '<em><b>Category</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference CONNECTOR_DEFINITION__CATEGORY = eINSTANCE.getConnectorDefinition_Category();

        /**
		 * The meta object literal for the '<em><b>Input</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference CONNECTOR_DEFINITION__INPUT = eINSTANCE.getConnectorDefinition_Input();

        /**
		 * The meta object literal for the '<em><b>Output</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference CONNECTOR_DEFINITION__OUTPUT = eINSTANCE.getConnectorDefinition_Output();

        /**
		 * The meta object literal for the '<em><b>Page</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference CONNECTOR_DEFINITION__PAGE = eINSTANCE.getConnectorDefinition_Page();

        /**
		 * The meta object literal for the '<em><b>Jar Dependency</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute CONNECTOR_DEFINITION__JAR_DEPENDENCY = eINSTANCE.getConnectorDefinition_JarDependency();

        /**
		 * The meta object literal for the '<em><b>Icon</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute CONNECTOR_DEFINITION__ICON = eINSTANCE.getConnectorDefinition_Icon();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute CONNECTOR_DEFINITION__ID = eINSTANCE.getConnectorDefinition_Id();

        /**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute CONNECTOR_DEFINITION__VERSION = eINSTANCE.getConnectorDefinition_Version();

        /**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.definition.impl.DocumentRootImpl <em>Document Root</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.definition.impl.DocumentRootImpl
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getDocumentRoot()
		 * @generated
		 */
        EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

        /**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

        /**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

        /**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

        /**
		 * The meta object literal for the '<em><b>Connector Definition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference DOCUMENT_ROOT__CONNECTOR_DEFINITION = eINSTANCE.getDocumentRoot_ConnectorDefinition();

        /**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.definition.impl.GroupImpl <em>Group</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.definition.impl.GroupImpl
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getGroup()
		 * @generated
		 */
        EClass GROUP = eINSTANCE.getGroup();

        /**
		 * The meta object literal for the '<em><b>Widget</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference GROUP__WIDGET = eINSTANCE.getGroup_Widget();

        /**
		 * The meta object literal for the '<em><b>Optional</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute GROUP__OPTIONAL = eINSTANCE.getGroup_Optional();

        /**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.definition.impl.InputImpl <em>Input</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.definition.impl.InputImpl
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getInput()
		 * @generated
		 */
        EClass INPUT = eINSTANCE.getInput();

        /**
		 * The meta object literal for the '<em><b>Default Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute INPUT__DEFAULT_VALUE = eINSTANCE.getInput_DefaultValue();

        /**
		 * The meta object literal for the '<em><b>Mandatory</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute INPUT__MANDATORY = eINSTANCE.getInput_Mandatory();

        /**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute INPUT__NAME = eINSTANCE.getInput_Name();

        /**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute INPUT__TYPE = eINSTANCE.getInput_Type();

        /**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.definition.impl.ListImpl <em>List</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ListImpl
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getList()
		 * @generated
		 */
        EClass LIST = eINSTANCE.getList();

        /**
		 * The meta object literal for the '<em><b>Show Documents</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LIST__SHOW_DOCUMENTS = eINSTANCE.getList_ShowDocuments();

								/**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.definition.impl.OutputImpl <em>Output</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.definition.impl.OutputImpl
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getOutput()
		 * @generated
		 */
        EClass OUTPUT = eINSTANCE.getOutput();

        /**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute OUTPUT__NAME = eINSTANCE.getOutput_Name();

        /**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute OUTPUT__TYPE = eINSTANCE.getOutput_Type();

        /**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.definition.impl.PageImpl <em>Page</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.definition.impl.PageImpl
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getPage()
		 * @generated
		 */
        EClass PAGE = eINSTANCE.getPage();

        /**
		 * The meta object literal for the '<em><b>Widget</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EReference PAGE__WIDGET = eINSTANCE.getPage_Widget();

        /**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute PAGE__ID = eINSTANCE.getPage_Id();

        /**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.definition.impl.PasswordImpl <em>Password</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.definition.impl.PasswordImpl
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getPassword()
		 * @generated
		 */
        EClass PASSWORD = eINSTANCE.getPassword();

        /**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.definition.impl.RadioGroupImpl <em>Radio Group</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.definition.impl.RadioGroupImpl
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getRadioGroup()
		 * @generated
		 */
        EClass RADIO_GROUP = eINSTANCE.getRadioGroup();

        /**
		 * The meta object literal for the '<em><b>Choices</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute RADIO_GROUP__CHOICES = eINSTANCE.getRadioGroup_Choices();

        /**
		 * The meta object literal for the '<em><b>Orientation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute RADIO_GROUP__ORIENTATION = eINSTANCE.getRadioGroup_Orientation();

        /**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.definition.impl.SelectImpl <em>Select</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.definition.impl.SelectImpl
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getSelect()
		 * @generated
		 */
        EClass SELECT = eINSTANCE.getSelect();

        /**
		 * The meta object literal for the '<em><b>Items</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute SELECT__ITEMS = eINSTANCE.getSelect_Items();

        /**
		 * The meta object literal for the '<em><b>Read Only</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute SELECT__READ_ONLY = eINSTANCE.getSelect_ReadOnly();

        /**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.definition.impl.TextImpl <em>Text</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.definition.impl.TextImpl
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getText()
		 * @generated
		 */
        EClass TEXT = eINSTANCE.getText();

        /**
		 * The meta object literal for the '<em><b>Show Documents</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT__SHOW_DOCUMENTS = eINSTANCE.getText_ShowDocuments();

								/**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.definition.impl.TextAreaImpl <em>Text Area</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.definition.impl.TextAreaImpl
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getTextArea()
		 * @generated
		 */
        EClass TEXT_AREA = eINSTANCE.getTextArea();

        /**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.definition.impl.WidgetImpl <em>Widget</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.definition.impl.WidgetImpl
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getWidget()
		 * @generated
		 */
        EClass WIDGET = eINSTANCE.getWidget();

        /**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.definition.impl.WidgetComponentImpl <em>Widget Component</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.definition.impl.WidgetComponentImpl
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getWidgetComponent()
		 * @generated
		 */
        EClass WIDGET_COMPONENT = eINSTANCE.getWidgetComponent();

        /**
		 * The meta object literal for the '<em><b>Input Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute WIDGET_COMPONENT__INPUT_NAME = eINSTANCE.getWidgetComponent_InputName();

        /**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.definition.impl.UnloadableConnectorDefinitionImpl <em>Unloadable Connector Definition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.definition.impl.UnloadableConnectorDefinitionImpl
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getUnloadableConnectorDefinition()
		 * @generated
		 */
		EClass UNLOADABLE_CONNECTOR_DEFINITION = eINSTANCE.getUnloadableConnectorDefinition();

								/**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.definition.impl.ScriptEditorImpl <em>Script Editor</em>}' class.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ScriptEditorImpl
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getScriptEditor()
		 * @generated
		 */
        EClass SCRIPT_EDITOR = eINSTANCE.getScriptEditor();

        /**
		 * The meta object literal for the '<em><b>Interpreter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @generated
		 */
        EAttribute SCRIPT_EDITOR__INTERPRETER = eINSTANCE.getScriptEditor_Interpreter();

                                /**
		 * The meta object literal for the '{@link org.bonitasoft.studio.connector.model.definition.Orientation <em>Orientation</em>}' enum.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.definition.Orientation
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getOrientation()
		 * @generated
		 */
        EEnum ORIENTATION = eINSTANCE.getOrientation();

        /**
		 * The meta object literal for the '<em>Orientation Object</em>' data type.
		 * <!-- begin-user-doc -->
         * <!-- end-user-doc -->
		 * @see org.bonitasoft.studio.connector.model.definition.Orientation
		 * @see org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionPackageImpl#getOrientationObject()
		 * @generated
		 */
        EDataType ORIENTATION_OBJECT = eINSTANCE.getOrientationObject();

    }

} //ConnectorDefinitionPackage
