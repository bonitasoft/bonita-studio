/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.definition;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Page</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.Page#getWidget <em>Widget</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.Page#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage#getPage()
 * @model extendedMetaData="name='Page' kind='elementOnly'"
 * @generated
 */
public interface Page extends EObject {
    /**
	 * Returns the value of the '<em><b>Widget</b></em>' containment reference list.
	 * The list contents are of type {@link org.bonitasoft.studio.connector.model.definition.Component}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Widget</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Widget</em>' containment reference list.
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage#getPage_Widget()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='widget'"
	 * @generated
	 */
    EList<Component> getWidget();

    /**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage#getPage_Id()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
    String getId();

    /**
	 * Sets the value of the '{@link org.bonitasoft.studio.connector.model.definition.Page#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
    void setId(String value);

} // Page
