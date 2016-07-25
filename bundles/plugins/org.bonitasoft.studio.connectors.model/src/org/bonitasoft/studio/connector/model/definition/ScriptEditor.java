/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.definition;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Script Editor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.ScriptEditor#getInterpreter <em>Interpreter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage#getScriptEditor()
 * @model extendedMetaData="name='ScriptEditor' kind='elementOnly'"
 * @generated
 */
public interface ScriptEditor extends Widget {
    /**
	 * Returns the value of the '<em><b>Interpreter</b></em>' attribute.
	 * The default value is <code>"GROOVY"</code>.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Interpreter</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Interpreter</em>' attribute.
	 * @see #isSetInterpreter()
	 * @see #unsetInterpreter()
	 * @see #setInterpreter(String)
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage#getScriptEditor_Interpreter()
	 * @model default="GROOVY" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='interpreter'"
	 * @generated
	 */
    String getInterpreter();

    /**
	 * Sets the value of the '{@link org.bonitasoft.studio.connector.model.definition.ScriptEditor#getInterpreter <em>Interpreter</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Interpreter</em>' attribute.
	 * @see #isSetInterpreter()
	 * @see #unsetInterpreter()
	 * @see #getInterpreter()
	 * @generated
	 */
    void setInterpreter(String value);

    /**
	 * Unsets the value of the '{@link org.bonitasoft.studio.connector.model.definition.ScriptEditor#getInterpreter <em>Interpreter</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isSetInterpreter()
	 * @see #getInterpreter()
	 * @see #setInterpreter(String)
	 * @generated
	 */
    void unsetInterpreter();

    /**
	 * Returns whether the value of the '{@link org.bonitasoft.studio.connector.model.definition.ScriptEditor#getInterpreter <em>Interpreter</em>}' attribute is set.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Interpreter</em>' attribute is set.
	 * @see #unsetInterpreter()
	 * @see #getInterpreter()
	 * @see #setInterpreter(String)
	 * @generated
	 */
    boolean isSetInterpreter();

} // ScriptEditor
