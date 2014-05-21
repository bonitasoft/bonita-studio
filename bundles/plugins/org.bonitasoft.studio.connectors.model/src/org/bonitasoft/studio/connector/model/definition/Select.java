/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.definition;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Select</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.Select#getItems <em>Items</em>}</li>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.Select#isReadOnly <em>Read Only</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage#getSelect()
 * @model extendedMetaData="name='Select' kind='elementOnly'"
 * @generated
 */
public interface Select extends Widget {
    /**
	 * Returns the value of the '<em><b>Items</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Items</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Items</em>' attribute list.
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage#getSelect_Items()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='element' name='items'"
	 * @generated
	 */
    EList<String> getItems();

    /**
	 * Returns the value of the '<em><b>Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Read Only</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Read Only</em>' attribute.
	 * @see #isSetReadOnly()
	 * @see #unsetReadOnly()
	 * @see #setReadOnly(boolean)
	 * @see org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage#getSelect_ReadOnly()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Boolean" required="true"
	 *        extendedMetaData="kind='attribute' name='readOnly'"
	 * @generated
	 */
    boolean isReadOnly();

    /**
	 * Sets the value of the '{@link org.bonitasoft.studio.connector.model.definition.Select#isReadOnly <em>Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Read Only</em>' attribute.
	 * @see #isSetReadOnly()
	 * @see #unsetReadOnly()
	 * @see #isReadOnly()
	 * @generated
	 */
    void setReadOnly(boolean value);

    /**
	 * Unsets the value of the '{@link org.bonitasoft.studio.connector.model.definition.Select#isReadOnly <em>Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isSetReadOnly()
	 * @see #isReadOnly()
	 * @see #setReadOnly(boolean)
	 * @generated
	 */
    void unsetReadOnly();

    /**
	 * Returns whether the value of the '{@link org.bonitasoft.studio.connector.model.definition.Select#isReadOnly <em>Read Only</em>}' attribute is set.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Read Only</em>' attribute is set.
	 * @see #unsetReadOnly()
	 * @see #isReadOnly()
	 * @see #setReadOnly(boolean)
	 * @generated
	 */
    boolean isSetReadOnly();

} // Select
