/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.definition.impl;

import org.bonitasoft.studio.connector.model.definition.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
public class ConnectorDefinitionFactoryImpl extends EFactoryImpl implements ConnectorDefinitionFactory {
    /**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public static ConnectorDefinitionFactory init() {
		try {
			ConnectorDefinitionFactory theConnectorDefinitionFactory = (ConnectorDefinitionFactory)EPackage.Registry.INSTANCE.getEFactory(ConnectorDefinitionPackage.eNS_URI);
			if (theConnectorDefinitionFactory != null) {
				return theConnectorDefinitionFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ConnectorDefinitionFactoryImpl();
	}

    /**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ConnectorDefinitionFactoryImpl() {
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
			case ConnectorDefinitionPackage.ARRAY: return createArray();
			case ConnectorDefinitionPackage.CATEGORY: return createCategory();
			case ConnectorDefinitionPackage.CHECKBOX: return createCheckbox();
			case ConnectorDefinitionPackage.CONNECTOR_DEFINITION: return createConnectorDefinition();
			case ConnectorDefinitionPackage.DOCUMENT_ROOT: return createDocumentRoot();
			case ConnectorDefinitionPackage.GROUP: return createGroup();
			case ConnectorDefinitionPackage.INPUT: return createInput();
			case ConnectorDefinitionPackage.LIST: return createList();
			case ConnectorDefinitionPackage.OUTPUT: return createOutput();
			case ConnectorDefinitionPackage.PAGE: return createPage();
			case ConnectorDefinitionPackage.PASSWORD: return createPassword();
			case ConnectorDefinitionPackage.RADIO_GROUP: return createRadioGroup();
			case ConnectorDefinitionPackage.SELECT: return createSelect();
			case ConnectorDefinitionPackage.TEXT: return createText();
			case ConnectorDefinitionPackage.TEXT_AREA: return createTextArea();
			case ConnectorDefinitionPackage.WIDGET_COMPONENT: return createWidgetComponent();
			case ConnectorDefinitionPackage.UNLOADABLE_CONNECTOR_DEFINITION: return createUnloadableConnectorDefinition();
			case ConnectorDefinitionPackage.SCRIPT_EDITOR: return createScriptEditor();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ConnectorDefinitionPackage.ORIENTATION:
				return createOrientationFromString(eDataType, initialValue);
			case ConnectorDefinitionPackage.ORIENTATION_OBJECT:
				return createOrientationObjectFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ConnectorDefinitionPackage.ORIENTATION:
				return convertOrientationToString(eDataType, instanceValue);
			case ConnectorDefinitionPackage.ORIENTATION_OBJECT:
				return convertOrientationObjectToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Array createArray() {
		ArrayImpl array = new ArrayImpl();
		return array;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Category createCategory() {
		CategoryImpl category = new CategoryImpl();
		return category;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Checkbox createCheckbox() {
		CheckboxImpl checkbox = new CheckboxImpl();
		return checkbox;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ConnectorDefinition createConnectorDefinition() {
		ConnectorDefinitionImpl connectorDefinition = new ConnectorDefinitionImpl();
		return connectorDefinition;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Group createGroup() {
		GroupImpl group = new GroupImpl();
		return group;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Input createInput() {
		InputImpl input = new InputImpl();
		return input;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public List createList() {
		ListImpl list = new ListImpl();
		return list;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Output createOutput() {
		OutputImpl output = new OutputImpl();
		return output;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Page createPage() {
		PageImpl page = new PageImpl();
		return page;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Password createPassword() {
		PasswordImpl password = new PasswordImpl();
		return password;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public RadioGroup createRadioGroup() {
		RadioGroupImpl radioGroup = new RadioGroupImpl();
		return radioGroup;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Select createSelect() {
		SelectImpl select = new SelectImpl();
		return select;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Text createText() {
		TextImpl text = new TextImpl();
		return text;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public TextArea createTextArea() {
		TextAreaImpl textArea = new TextAreaImpl();
		return textArea;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public WidgetComponent createWidgetComponent() {
		WidgetComponentImpl widgetComponent = new WidgetComponentImpl();
		return widgetComponent;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnloadableConnectorDefinition createUnloadableConnectorDefinition() {
		UnloadableConnectorDefinitionImpl unloadableConnectorDefinition = new UnloadableConnectorDefinitionImpl();
		return unloadableConnectorDefinition;
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ScriptEditor createScriptEditor() {
		ScriptEditorImpl scriptEditor = new ScriptEditorImpl();
		return scriptEditor;
	}

                /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Orientation createOrientationFromString(EDataType eDataType, String initialValue) {
		Orientation result = Orientation.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertOrientationToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Orientation createOrientationObjectFromString(EDataType eDataType, String initialValue) {
		return createOrientationFromString(ConnectorDefinitionPackage.Literals.ORIENTATION, initialValue);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String convertOrientationObjectToString(EDataType eDataType, Object instanceValue) {
		return convertOrientationToString(ConnectorDefinitionPackage.Literals.ORIENTATION, instanceValue);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ConnectorDefinitionPackage getConnectorDefinitionPackage() {
		return (ConnectorDefinitionPackage)getEPackage();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
    @Deprecated
    public static ConnectorDefinitionPackage getPackage() {
		return ConnectorDefinitionPackage.eINSTANCE;
	}

} //ConnectorDefinitionFactoryImpl
