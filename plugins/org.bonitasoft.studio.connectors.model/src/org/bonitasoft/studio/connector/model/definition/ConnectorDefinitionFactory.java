/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.definition;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage
 * @generated
 */
public interface ConnectorDefinitionFactory extends EFactory {
    /**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    ConnectorDefinitionFactory eINSTANCE = org.bonitasoft.studio.connector.model.definition.impl.ConnectorDefinitionFactoryImpl.init();

    /**
	 * Returns a new object of class '<em>Array</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Array</em>'.
	 * @generated
	 */
    Array createArray();

    /**
	 * Returns a new object of class '<em>Category</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Category</em>'.
	 * @generated
	 */
    Category createCategory();

    /**
	 * Returns a new object of class '<em>Checkbox</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Checkbox</em>'.
	 * @generated
	 */
    Checkbox createCheckbox();

    /**
	 * Returns a new object of class '<em>Connector Definition</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connector Definition</em>'.
	 * @generated
	 */
    ConnectorDefinition createConnectorDefinition();

    /**
	 * Returns a new object of class '<em>Document Root</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Document Root</em>'.
	 * @generated
	 */
    DocumentRoot createDocumentRoot();

    /**
	 * Returns a new object of class '<em>Group</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Group</em>'.
	 * @generated
	 */
    Group createGroup();

    /**
	 * Returns a new object of class '<em>Input</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Input</em>'.
	 * @generated
	 */
    Input createInput();

    /**
	 * Returns a new object of class '<em>List</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>List</em>'.
	 * @generated
	 */
    List createList();

    /**
	 * Returns a new object of class '<em>Output</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Output</em>'.
	 * @generated
	 */
    Output createOutput();

    /**
	 * Returns a new object of class '<em>Page</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Page</em>'.
	 * @generated
	 */
    Page createPage();

    /**
	 * Returns a new object of class '<em>Password</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Password</em>'.
	 * @generated
	 */
    Password createPassword();

    /**
	 * Returns a new object of class '<em>Radio Group</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Radio Group</em>'.
	 * @generated
	 */
    RadioGroup createRadioGroup();

    /**
	 * Returns a new object of class '<em>Select</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Select</em>'.
	 * @generated
	 */
    Select createSelect();

    /**
	 * Returns a new object of class '<em>Text</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Text</em>'.
	 * @generated
	 */
    Text createText();

    /**
	 * Returns a new object of class '<em>Text Area</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Text Area</em>'.
	 * @generated
	 */
    TextArea createTextArea();

    /**
	 * Returns a new object of class '<em>Widget Component</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Widget Component</em>'.
	 * @generated
	 */
    WidgetComponent createWidgetComponent();

    /**
	 * Returns a new object of class '<em>Unloadable Connector Definition</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Unloadable Connector Definition</em>'.
	 * @generated
	 */
	UnloadableConnectorDefinition createUnloadableConnectorDefinition();

				/**
	 * Returns a new object of class '<em>Script Editor</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Script Editor</em>'.
	 * @generated
	 */
    ScriptEditor createScriptEditor();

                /**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
    ConnectorDefinitionPackage getConnectorDefinitionPackage();

} //ConnectorDefinitionFactory
