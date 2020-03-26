/**
 */
package org.bonitasoft.studio.businessobject.editor.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.Field#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.Field#getDescription <em>Description</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.Field#isNullable <em>Nullable</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.Field#isCollection <em>Collection</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getField()
 * @model abstract="true"
 * @generated
 */
public interface Field extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getField_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.businessobject.editor.model.Field#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Description</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Description</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Description</em>' attribute.
     * @see #setDescription(String)
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getField_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.businessobject.editor.model.Field#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Nullable</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Nullable</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Nullable</em>' attribute.
     * @see #setNullable(boolean)
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getField_Nullable()
     * @model default="true"
     * @generated
     */
    boolean isNullable();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.businessobject.editor.model.Field#isNullable <em>Nullable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Nullable</em>' attribute.
     * @see #isNullable()
     * @generated
     */
    void setNullable(boolean value);

    /**
     * Returns the value of the '<em><b>Collection</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Collection</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Collection</em>' attribute.
     * @see #setCollection(boolean)
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getField_Collection()
     * @model default="false"
     * @generated
     */
    boolean isCollection();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.businessobject.editor.model.Field#isCollection <em>Collection</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Collection</em>' attribute.
     * @see #isCollection()
     * @generated
     */
    void setCollection(boolean value);

} // Field
