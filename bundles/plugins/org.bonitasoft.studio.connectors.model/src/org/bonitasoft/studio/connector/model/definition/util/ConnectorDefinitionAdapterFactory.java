/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.definition.util;

import org.bonitasoft.studio.connector.model.definition.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage
 * @generated
 */
public class ConnectorDefinitionAdapterFactory extends AdapterFactoryImpl {
    /**
	 * The cached model package.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected static ConnectorDefinitionPackage modelPackage;

    /**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public ConnectorDefinitionAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ConnectorDefinitionPackage.eINSTANCE;
		}
	}

    /**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
     * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
     * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
    @Override
    public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

    /**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ConnectorDefinitionSwitch<Adapter> modelSwitch =
        new ConnectorDefinitionSwitch<Adapter>() {
			@Override
			public Adapter caseArray(Array object) {
				return createArrayAdapter();
			}
			@Override
			public Adapter caseCategory(Category object) {
				return createCategoryAdapter();
			}
			@Override
			public Adapter caseCheckbox(Checkbox object) {
				return createCheckboxAdapter();
			}
			@Override
			public Adapter caseComponent(Component object) {
				return createComponentAdapter();
			}
			@Override
			public Adapter caseConnectorDefinition(ConnectorDefinition object) {
				return createConnectorDefinitionAdapter();
			}
			@Override
			public Adapter caseDocumentRoot(DocumentRoot object) {
				return createDocumentRootAdapter();
			}
			@Override
			public Adapter caseGroup(Group object) {
				return createGroupAdapter();
			}
			@Override
			public Adapter caseInput(Input object) {
				return createInputAdapter();
			}
			@Override
			public Adapter caseList(List object) {
				return createListAdapter();
			}
			@Override
			public Adapter caseOutput(Output object) {
				return createOutputAdapter();
			}
			@Override
			public Adapter casePage(Page object) {
				return createPageAdapter();
			}
			@Override
			public Adapter casePassword(Password object) {
				return createPasswordAdapter();
			}
			@Override
			public Adapter caseRadioGroup(RadioGroup object) {
				return createRadioGroupAdapter();
			}
			@Override
			public Adapter caseSelect(Select object) {
				return createSelectAdapter();
			}
			@Override
			public Adapter caseText(Text object) {
				return createTextAdapter();
			}
			@Override
			public Adapter caseTextArea(TextArea object) {
				return createTextAreaAdapter();
			}
			@Override
			public Adapter caseWidget(Widget object) {
				return createWidgetAdapter();
			}
			@Override
			public Adapter caseWidgetComponent(WidgetComponent object) {
				return createWidgetComponentAdapter();
			}
			@Override
			public Adapter caseUnloadableConnectorDefinition(UnloadableConnectorDefinition object) {
				return createUnloadableConnectorDefinitionAdapter();
			}
			@Override
			public Adapter caseScriptEditor(ScriptEditor object) {
				return createScriptEditorAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

    /**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
    @Override
    public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.definition.Array <em>Array</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.definition.Array
	 * @generated
	 */
    public Adapter createArrayAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.definition.Category <em>Category</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.definition.Category
	 * @generated
	 */
    public Adapter createCategoryAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.definition.Checkbox <em>Checkbox</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.definition.Checkbox
	 * @generated
	 */
    public Adapter createCheckboxAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.definition.Component <em>Component</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.definition.Component
	 * @generated
	 */
    public Adapter createComponentAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.definition.ConnectorDefinition <em>Connector Definition</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinition
	 * @generated
	 */
    public Adapter createConnectorDefinitionAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.definition.DocumentRoot <em>Document Root</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.definition.DocumentRoot
	 * @generated
	 */
    public Adapter createDocumentRootAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.definition.Group <em>Group</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.definition.Group
	 * @generated
	 */
    public Adapter createGroupAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.definition.Input <em>Input</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.definition.Input
	 * @generated
	 */
    public Adapter createInputAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.definition.List <em>List</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.definition.List
	 * @generated
	 */
    public Adapter createListAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.definition.Output <em>Output</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.definition.Output
	 * @generated
	 */
    public Adapter createOutputAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.definition.Page <em>Page</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.definition.Page
	 * @generated
	 */
    public Adapter createPageAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.definition.Password <em>Password</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.definition.Password
	 * @generated
	 */
    public Adapter createPasswordAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.definition.RadioGroup <em>Radio Group</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.definition.RadioGroup
	 * @generated
	 */
    public Adapter createRadioGroupAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.definition.Select <em>Select</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.definition.Select
	 * @generated
	 */
    public Adapter createSelectAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.definition.Text <em>Text</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.definition.Text
	 * @generated
	 */
    public Adapter createTextAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.definition.TextArea <em>Text Area</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.definition.TextArea
	 * @generated
	 */
    public Adapter createTextAreaAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.definition.Widget <em>Widget</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.definition.Widget
	 * @generated
	 */
    public Adapter createWidgetAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.definition.WidgetComponent <em>Widget Component</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.definition.WidgetComponent
	 * @generated
	 */
    public Adapter createWidgetComponentAdapter() {
		return null;
	}

    /**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.definition.UnloadableConnectorDefinition <em>Unloadable Connector Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.definition.UnloadableConnectorDefinition
	 * @generated
	 */
	public Adapter createUnloadableConnectorDefinitionAdapter() {
		return null;
	}

				/**
	 * Creates a new adapter for an object of class '{@link org.bonitasoft.studio.connector.model.definition.ScriptEditor <em>Script Editor</em>}'.
	 * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.bonitasoft.studio.connector.model.definition.ScriptEditor
	 * @generated
	 */
    public Adapter createScriptEditorAdapter() {
		return null;
	}

                /**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
     * This default implementation returns null.
     * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
    public Adapter createEObjectAdapter() {
		return null;
	}

} //ConnectorDefinitionAdapterFactory
