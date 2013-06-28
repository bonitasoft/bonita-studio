/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.definition.impl;

import org.bonitasoft.studio.connector.model.definition.Array;
import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.Checkbox;
import org.bonitasoft.studio.connector.model.definition.Component;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.studio.connector.model.definition.DocumentRoot;
import org.bonitasoft.studio.connector.model.definition.Group;
import org.bonitasoft.studio.connector.model.definition.Input;
import org.bonitasoft.studio.connector.model.definition.List;
import org.bonitasoft.studio.connector.model.definition.Orientation;
import org.bonitasoft.studio.connector.model.definition.Output;
import org.bonitasoft.studio.connector.model.definition.Page;
import org.bonitasoft.studio.connector.model.definition.Password;
import org.bonitasoft.studio.connector.model.definition.RadioGroup;
import org.bonitasoft.studio.connector.model.definition.ScriptEditor;
import org.bonitasoft.studio.connector.model.definition.Select;
import org.bonitasoft.studio.connector.model.definition.Text;
import org.bonitasoft.studio.connector.model.definition.TextArea;
import org.bonitasoft.studio.connector.model.definition.UnloadableConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.Widget;
import org.bonitasoft.studio.connector.model.definition.WidgetComponent;

import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementationPackage;

import org.bonitasoft.studio.connector.model.implementation.impl.ConnectorImplementationPackageImpl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ConnectorDefinitionPackageImpl extends EPackageImpl implements ConnectorDefinitionPackage {
    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass arrayEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass categoryEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass checkboxEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass componentEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass connectorDefinitionEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass documentRootEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass groupEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass inputEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass listEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass outputEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass pageEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass passwordEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass radioGroupEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass selectEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass textEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass textAreaEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass widgetEClass = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass widgetComponentEClass = null;

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unloadableConnectorDefinitionEClass = null;

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EClass scriptEditorEClass = null;

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EEnum orientationEEnum = null;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private EDataType orientationObjectEDataType = null;

    /**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
    private ConnectorDefinitionPackageImpl() {
		super(eNS_URI, ConnectorDefinitionFactory.eINSTANCE);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private static boolean isInited = false;

    /**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ConnectorDefinitionPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
    public static ConnectorDefinitionPackage init() {
		if (isInited) return (ConnectorDefinitionPackage)EPackage.Registry.INSTANCE.getEPackage(ConnectorDefinitionPackage.eNS_URI);

		// Obtain or create and register package
		ConnectorDefinitionPackageImpl theConnectorDefinitionPackage = (ConnectorDefinitionPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ConnectorDefinitionPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new ConnectorDefinitionPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		XMLTypePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		ConnectorImplementationPackageImpl theConnectorImplementationPackage = (ConnectorImplementationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ConnectorImplementationPackage.eNS_URI) instanceof ConnectorImplementationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ConnectorImplementationPackage.eNS_URI) : ConnectorImplementationPackage.eINSTANCE);

		// Create package meta-data objects
		theConnectorDefinitionPackage.createPackageContents();
		theConnectorImplementationPackage.createPackageContents();

		// Initialize created meta-data
		theConnectorDefinitionPackage.initializePackageContents();
		theConnectorImplementationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theConnectorDefinitionPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ConnectorDefinitionPackage.eNS_URI, theConnectorDefinitionPackage);
		return theConnectorDefinitionPackage;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getArray() {
		return arrayEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getArray_ColsCaption() {
		return (EAttribute)arrayEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getArray_Cols() {
		return (EAttribute)arrayEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getArray_FixedCols() {
		return (EAttribute)arrayEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getArray_FixedRows() {
		return (EAttribute)arrayEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getArray_Rows() {
		return (EAttribute)arrayEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getCategory() {
		return categoryEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCategory_Icon() {
		return (EAttribute)categoryEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getCategory_Id() {
		return (EAttribute)categoryEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCategory_ParentCategoryId() {
		return (EAttribute)categoryEClass.getEStructuralFeatures().get(2);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getCheckbox() {
		return checkboxEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getComponent() {
		return componentEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getComponent_Id() {
		return (EAttribute)componentEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getConnectorDefinition() {
		return connectorDefinitionEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getConnectorDefinition_Category() {
		return (EReference)connectorDefinitionEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getConnectorDefinition_Input() {
		return (EReference)connectorDefinitionEClass.getEStructuralFeatures().get(4);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getConnectorDefinition_Output() {
		return (EReference)connectorDefinitionEClass.getEStructuralFeatures().get(5);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getConnectorDefinition_Page() {
		return (EReference)connectorDefinitionEClass.getEStructuralFeatures().get(6);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getConnectorDefinition_JarDependency() {
		return (EAttribute)connectorDefinitionEClass.getEStructuralFeatures().get(7);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getConnectorDefinition_Icon() {
		return (EAttribute)connectorDefinitionEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getConnectorDefinition_Id() {
		return (EAttribute)connectorDefinitionEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getConnectorDefinition_Version() {
		return (EAttribute)connectorDefinitionEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getDocumentRoot() {
		return documentRootEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getDocumentRoot_ConnectorDefinition() {
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getGroup() {
		return groupEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getGroup_Widget() {
		return (EReference)groupEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getGroup_Optional() {
		return (EAttribute)groupEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getInput() {
		return inputEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getInput_DefaultValue() {
		return (EAttribute)inputEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getInput_Mandatory() {
		return (EAttribute)inputEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getInput_Name() {
		return (EAttribute)inputEClass.getEStructuralFeatures().get(2);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getInput_Type() {
		return (EAttribute)inputEClass.getEStructuralFeatures().get(3);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getList() {
		return listEClass;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getList_ShowDocuments() {
		return (EAttribute)listEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getOutput() {
		return outputEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getOutput_Name() {
		return (EAttribute)outputEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getOutput_Type() {
		return (EAttribute)outputEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getPage() {
		return pageEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EReference getPage_Widget() {
		return (EReference)pageEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getPage_Id() {
		return (EAttribute)pageEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getPassword() {
		return passwordEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getRadioGroup() {
		return radioGroupEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRadioGroup_Choices() {
		return (EAttribute)radioGroupEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getRadioGroup_Orientation() {
		return (EAttribute)radioGroupEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getSelect() {
		return selectEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSelect_Items() {
		return (EAttribute)selectEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getSelect_ReadOnly() {
		return (EAttribute)selectEClass.getEStructuralFeatures().get(1);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getText() {
		return textEClass;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getText_ShowDocuments() {
		return (EAttribute)textEClass.getEStructuralFeatures().get(0);
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getTextArea() {
		return textAreaEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getWidget() {
		return widgetEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getWidgetComponent() {
		return widgetComponentEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getWidgetComponent_InputName() {
		return (EAttribute)widgetComponentEClass.getEStructuralFeatures().get(0);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnloadableConnectorDefinition() {
		return unloadableConnectorDefinitionEClass;
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EClass getScriptEditor() {
		return scriptEditorEClass;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EAttribute getScriptEditor_Interpreter() {
		return (EAttribute)scriptEditorEClass.getEStructuralFeatures().get(0);
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EEnum getOrientation() {
		return orientationEEnum;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EDataType getOrientationObject() {
		return orientationObjectEDataType;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ConnectorDefinitionFactory getConnectorDefinitionFactory() {
		return (ConnectorDefinitionFactory)getEFactoryInstance();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private boolean isCreated = false;

    /**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		arrayEClass = createEClass(ARRAY);
		createEAttribute(arrayEClass, ARRAY__COLS_CAPTION);
		createEAttribute(arrayEClass, ARRAY__COLS);
		createEAttribute(arrayEClass, ARRAY__FIXED_COLS);
		createEAttribute(arrayEClass, ARRAY__FIXED_ROWS);
		createEAttribute(arrayEClass, ARRAY__ROWS);

		categoryEClass = createEClass(CATEGORY);
		createEAttribute(categoryEClass, CATEGORY__ICON);
		createEAttribute(categoryEClass, CATEGORY__ID);
		createEAttribute(categoryEClass, CATEGORY__PARENT_CATEGORY_ID);

		checkboxEClass = createEClass(CHECKBOX);

		componentEClass = createEClass(COMPONENT);
		createEAttribute(componentEClass, COMPONENT__ID);

		connectorDefinitionEClass = createEClass(CONNECTOR_DEFINITION);
		createEAttribute(connectorDefinitionEClass, CONNECTOR_DEFINITION__ID);
		createEAttribute(connectorDefinitionEClass, CONNECTOR_DEFINITION__VERSION);
		createEAttribute(connectorDefinitionEClass, CONNECTOR_DEFINITION__ICON);
		createEReference(connectorDefinitionEClass, CONNECTOR_DEFINITION__CATEGORY);
		createEReference(connectorDefinitionEClass, CONNECTOR_DEFINITION__INPUT);
		createEReference(connectorDefinitionEClass, CONNECTOR_DEFINITION__OUTPUT);
		createEReference(connectorDefinitionEClass, CONNECTOR_DEFINITION__PAGE);
		createEAttribute(connectorDefinitionEClass, CONNECTOR_DEFINITION__JAR_DEPENDENCY);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CONNECTOR_DEFINITION);

		groupEClass = createEClass(GROUP);
		createEReference(groupEClass, GROUP__WIDGET);
		createEAttribute(groupEClass, GROUP__OPTIONAL);

		inputEClass = createEClass(INPUT);
		createEAttribute(inputEClass, INPUT__DEFAULT_VALUE);
		createEAttribute(inputEClass, INPUT__MANDATORY);
		createEAttribute(inputEClass, INPUT__NAME);
		createEAttribute(inputEClass, INPUT__TYPE);

		listEClass = createEClass(LIST);
		createEAttribute(listEClass, LIST__SHOW_DOCUMENTS);

		outputEClass = createEClass(OUTPUT);
		createEAttribute(outputEClass, OUTPUT__NAME);
		createEAttribute(outputEClass, OUTPUT__TYPE);

		pageEClass = createEClass(PAGE);
		createEReference(pageEClass, PAGE__WIDGET);
		createEAttribute(pageEClass, PAGE__ID);

		passwordEClass = createEClass(PASSWORD);

		radioGroupEClass = createEClass(RADIO_GROUP);
		createEAttribute(radioGroupEClass, RADIO_GROUP__CHOICES);
		createEAttribute(radioGroupEClass, RADIO_GROUP__ORIENTATION);

		selectEClass = createEClass(SELECT);
		createEAttribute(selectEClass, SELECT__ITEMS);
		createEAttribute(selectEClass, SELECT__READ_ONLY);

		textEClass = createEClass(TEXT);
		createEAttribute(textEClass, TEXT__SHOW_DOCUMENTS);

		textAreaEClass = createEClass(TEXT_AREA);

		widgetEClass = createEClass(WIDGET);

		widgetComponentEClass = createEClass(WIDGET_COMPONENT);
		createEAttribute(widgetComponentEClass, WIDGET_COMPONENT__INPUT_NAME);

		unloadableConnectorDefinitionEClass = createEClass(UNLOADABLE_CONNECTOR_DEFINITION);

		scriptEditorEClass = createEClass(SCRIPT_EDITOR);
		createEAttribute(scriptEditorEClass, SCRIPT_EDITOR__INTERPRETER);

		// Create enums
		orientationEEnum = createEEnum(ORIENTATION);

		// Create data types
		orientationObjectEDataType = createEDataType(ORIENTATION_OBJECT);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    private boolean isInitialized = false;

    /**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		arrayEClass.getESuperTypes().add(this.getWidget());
		checkboxEClass.getESuperTypes().add(this.getWidget());
		groupEClass.getESuperTypes().add(this.getComponent());
		listEClass.getESuperTypes().add(this.getWidget());
		passwordEClass.getESuperTypes().add(this.getText());
		radioGroupEClass.getESuperTypes().add(this.getWidget());
		selectEClass.getESuperTypes().add(this.getWidget());
		textEClass.getESuperTypes().add(this.getWidget());
		textAreaEClass.getESuperTypes().add(this.getWidget());
		widgetEClass.getESuperTypes().add(this.getWidgetComponent());
		widgetComponentEClass.getESuperTypes().add(this.getComponent());
		unloadableConnectorDefinitionEClass.getESuperTypes().add(this.getConnectorDefinition());
		scriptEditorEClass.getESuperTypes().add(this.getWidget());

		// Initialize classes and features; add operations and parameters
		initEClass(arrayEClass, Array.class, "Array", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArray_ColsCaption(), theXMLTypePackage.getString(), "colsCaption", null, 0, -1, Array.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArray_Cols(), theXMLTypePackage.getInteger(), "cols", null, 0, 1, Array.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArray_FixedCols(), theXMLTypePackage.getBoolean(), "fixedCols", "true", 0, 1, Array.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArray_FixedRows(), theXMLTypePackage.getBoolean(), "fixedRows", null, 0, 1, Array.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArray_Rows(), theXMLTypePackage.getInteger(), "rows", null, 0, 1, Array.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(categoryEClass, Category.class, "Category", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCategory_Icon(), theXMLTypePackage.getString(), "icon", null, 0, 1, Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCategory_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCategory_ParentCategoryId(), theXMLTypePackage.getString(), "parentCategoryId", null, 0, 1, Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(checkboxEClass, Checkbox.class, "Checkbox", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(componentEClass, Component.class, "Component", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComponent_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, Component.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(connectorDefinitionEClass, ConnectorDefinition.class, "ConnectorDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConnectorDefinition_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, ConnectorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorDefinition_Version(), theXMLTypePackage.getString(), "version", null, 1, 1, ConnectorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorDefinition_Icon(), theXMLTypePackage.getString(), "icon", null, 1, 1, ConnectorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConnectorDefinition_Category(), this.getCategory(), null, "category", null, 0, -1, ConnectorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConnectorDefinition_Input(), this.getInput(), null, "input", null, 0, -1, ConnectorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConnectorDefinition_Output(), this.getOutput(), null, "output", null, 0, -1, ConnectorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConnectorDefinition_Page(), this.getPage(), null, "page", null, 0, -1, ConnectorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnectorDefinition_JarDependency(), theXMLTypePackage.getString(), "jarDependency", null, 0, -1, ConnectorDefinition.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, DocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_ConnectorDefinition(), this.getConnectorDefinition(), null, "connectorDefinition", null, 0, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(groupEClass, Group.class, "Group", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGroup_Widget(), this.getComponent(), null, "widget", null, 0, -1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGroup_Optional(), theXMLTypePackage.getBoolean(), "optional", null, 0, 1, Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(inputEClass, Input.class, "Input", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInput_DefaultValue(), theXMLTypePackage.getString(), "defaultValue", null, 0, 1, Input.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInput_Mandatory(), theXMLTypePackage.getBoolean(), "mandatory", "false", 0, 1, Input.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInput_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, Input.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInput_Type(), theXMLTypePackage.getString(), "type", null, 1, 1, Input.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(listEClass, List.class, "List", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getList_ShowDocuments(), ecorePackage.getEBoolean(), "showDocuments", "false", 0, 1, List.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(outputEClass, Output.class, "Output", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getOutput_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, Output.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getOutput_Type(), theXMLTypePackage.getString(), "type", null, 1, 1, Output.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pageEClass, Page.class, "Page", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPage_Widget(), this.getComponent(), null, "widget", null, 0, -1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPage_Id(), theXMLTypePackage.getString(), "id", null, 1, 1, Page.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(passwordEClass, Password.class, "Password", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(radioGroupEClass, RadioGroup.class, "RadioGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRadioGroup_Choices(), theXMLTypePackage.getString(), "choices", null, 0, -1, RadioGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRadioGroup_Orientation(), this.getOrientation(), "orientation", "HORIZONTAL", 0, 1, RadioGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(selectEClass, Select.class, "Select", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSelect_Items(), theXMLTypePackage.getString(), "items", null, 0, -1, Select.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSelect_ReadOnly(), theXMLTypePackage.getBoolean(), "readOnly", null, 1, 1, Select.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(textEClass, Text.class, "Text", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getText_ShowDocuments(), ecorePackage.getEBoolean(), "showDocuments", "false", 0, 1, Text.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(textAreaEClass, TextArea.class, "TextArea", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(widgetEClass, Widget.class, "Widget", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(widgetComponentEClass, WidgetComponent.class, "WidgetComponent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWidgetComponent_InputName(), theXMLTypePackage.getString(), "inputName", null, 1, 1, WidgetComponent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unloadableConnectorDefinitionEClass, UnloadableConnectorDefinition.class, "UnloadableConnectorDefinition", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(scriptEditorEClass, ScriptEditor.class, "ScriptEditor", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getScriptEditor_Interpreter(), theXMLTypePackage.getString(), "interpreter", "GROOVY", 1, 1, ScriptEditor.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(orientationEEnum, Orientation.class, "Orientation");
		addEEnumLiteral(orientationEEnum, Orientation.HORIZONTAL);
		addEEnumLiteral(orientationEEnum, Orientation.VERTICAL);

		// Initialize data types
		initEDataType(orientationObjectEDataType, Orientation.class, "OrientationObject", IS_SERIALIZABLE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/edapt
		createEdaptAnnotations();
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

    /**
	 * Initializes the annotations for <b>http://www.eclipse.org/edapt</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEdaptAnnotations() {
		String source = "http://www.eclipse.org/edapt";		
		addAnnotation
		  (this, 
		   source, 
		   new String[] {
			 "historyURI", "connector.history"
		   });																																																												
	}

				/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";			
		addAnnotation
		  (arrayEClass, 
		   source, 
		   new String[] {
			 "name", "Array",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getArray_ColsCaption(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "colsCaption"
		   });		
		addAnnotation
		  (getArray_Cols(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "cols"
		   });		
		addAnnotation
		  (getArray_FixedCols(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "fixedCols"
		   });		
		addAnnotation
		  (getArray_FixedRows(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "fixedRows"
		   });		
		addAnnotation
		  (getArray_Rows(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "rows"
		   });		
		addAnnotation
		  (categoryEClass, 
		   source, 
		   new String[] {
			 "name", "Category",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getCategory_Icon(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "icon"
		   });		
		addAnnotation
		  (getCategory_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (getCategory_ParentCategoryId(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "parentCategoryId"
		   });		
		addAnnotation
		  (checkboxEClass, 
		   source, 
		   new String[] {
			 "name", "Checkbox",
			 "kind", "empty"
		   });		
		addAnnotation
		  (componentEClass, 
		   source, 
		   new String[] {
			 "name", "Component",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getComponent_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (connectorDefinitionEClass, 
		   source, 
		   new String[] {
			 "name", "ConnectorDefinition",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getConnectorDefinition_Id(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "id"
		   });		
		addAnnotation
		  (getConnectorDefinition_Version(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "version"
		   });		
		addAnnotation
		  (getConnectorDefinition_Icon(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "icon"
		   });		
		addAnnotation
		  (getConnectorDefinition_Category(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "category"
		   });		
		addAnnotation
		  (getConnectorDefinition_Input(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "input"
		   });		
		addAnnotation
		  (getConnectorDefinition_Output(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "output"
		   });		
		addAnnotation
		  (getConnectorDefinition_Page(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "page"
		   });		
		addAnnotation
		  (getConnectorDefinition_JarDependency(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "jarDependency"
		   });		
		addAnnotation
		  (documentRootEClass, 
		   source, 
		   new String[] {
			 "name", "",
			 "kind", "mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_Mixed(), 
		   source, 
		   new String[] {
			 "kind", "elementWildcard",
			 "name", ":mixed"
		   });		
		addAnnotation
		  (getDocumentRoot_XMLNSPrefixMap(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xmlns:prefix"
		   });		
		addAnnotation
		  (getDocumentRoot_XSISchemaLocation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "xsi:schemaLocation"
		   });		
		addAnnotation
		  (getDocumentRoot_ConnectorDefinition(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "ConnectorDefinition",
			 "namespace", "##targetNamespace"
		   });		
		addAnnotation
		  (groupEClass, 
		   source, 
		   new String[] {
			 "name", "Group",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getGroup_Widget(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "widget"
		   });		
		addAnnotation
		  (getGroup_Optional(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "optional"
		   });		
		addAnnotation
		  (inputEClass, 
		   source, 
		   new String[] {
			 "name", "Input",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getInput_DefaultValue(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "defaultValue"
		   });		
		addAnnotation
		  (getInput_Mandatory(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "mandatory"
		   });		
		addAnnotation
		  (getInput_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getInput_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });		
		addAnnotation
		  (listEClass, 
		   source, 
		   new String[] {
			 "name", "List",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getList_ShowDocuments(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "showDocuments"
		   });		
		addAnnotation
		  (orientationEEnum, 
		   source, 
		   new String[] {
			 "name", "Orientation"
		   });		
		addAnnotation
		  (orientationObjectEDataType, 
		   source, 
		   new String[] {
			 "name", "Orientation:Object",
			 "baseType", "Orientation"
		   });		
		addAnnotation
		  (outputEClass, 
		   source, 
		   new String[] {
			 "name", "Output",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getOutput_Name(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "name"
		   });		
		addAnnotation
		  (getOutput_Type(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "type"
		   });		
		addAnnotation
		  (pageEClass, 
		   source, 
		   new String[] {
			 "name", "Page",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getPage_Widget(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "widget"
		   });		
		addAnnotation
		  (getPage_Id(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "id"
		   });		
		addAnnotation
		  (passwordEClass, 
		   source, 
		   new String[] {
			 "name", "Password",
			 "kind", "empty"
		   });		
		addAnnotation
		  (radioGroupEClass, 
		   source, 
		   new String[] {
			 "name", "RadioGroup",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getRadioGroup_Choices(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "choices"
		   });		
		addAnnotation
		  (getRadioGroup_Orientation(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "orientation"
		   });		
		addAnnotation
		  (selectEClass, 
		   source, 
		   new String[] {
			 "name", "Select",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getSelect_Items(), 
		   source, 
		   new String[] {
			 "kind", "element",
			 "name", "items"
		   });		
		addAnnotation
		  (getSelect_ReadOnly(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "readOnly"
		   });		
		addAnnotation
		  (textEClass, 
		   source, 
		   new String[] {
			 "name", "Text",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getText_ShowDocuments(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "showDocuments"
		   });		
		addAnnotation
		  (textAreaEClass, 
		   source, 
		   new String[] {
			 "name", "TextArea",
			 "kind", "empty"
		   });		
		addAnnotation
		  (widgetEClass, 
		   source, 
		   new String[] {
			 "name", "Widget",
			 "kind", "empty"
		   });		
		addAnnotation
		  (widgetComponentEClass, 
		   source, 
		   new String[] {
			 "name", "WidgetComponent",
			 "kind", "empty"
		   });		
		addAnnotation
		  (getWidgetComponent_InputName(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "inputName"
		   });		
		addAnnotation
		  (scriptEditorEClass, 
		   source, 
		   new String[] {
			 "name", "ScriptEditor",
			 "kind", "elementOnly"
		   });		
		addAnnotation
		  (getScriptEditor_Interpreter(), 
		   source, 
		   new String[] {
			 "kind", "attribute",
			 "name", "interpreter"
		   });
	}

} //ConnectorDefinitionPackageImpl
