/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.definition;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Widget Component</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.WidgetComponent#getInputName <em>Input Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage#getWidgetComponent()
 * @model extendedMetaData="name='WidgetComponent' kind='empty'"
 * @generated
 */
public interface WidgetComponent extends Component {
    /**
	 * Returns the value of the '<em><b>Input Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Input Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Input Name</em>' attribute.
	 * @see #setInputName(String)
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage#getWidgetComponent_InputName()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='inputName'"
	 * @generated
	 */
    String getInputName();

    /**
	 * Sets the value of the '{@link org.bonitasoft.studio.connector.model.definition.WidgetComponent#getInputName <em>Input Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Input Name</em>' attribute.
	 * @see #getInputName()
	 * @generated
	 */
    void setInputName(String value);

} // WidgetComponent
