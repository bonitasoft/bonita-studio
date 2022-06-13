/**
 */
package org.bonitasoft.studio.businessobject.editor.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Simple Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.SimpleField#getType <em>Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.SimpleField#getLength <em>Length</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getSimpleField()
 * @model
 * @generated
 */
public interface SimpleField extends Field {
    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * The default value is <code>"STRING"</code>.
     * The literals are from the enumeration {@link org.bonitasoft.studio.businessobject.editor.model.FieldType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see org.bonitasoft.studio.businessobject.editor.model.FieldType
     * @see #setType(FieldType)
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getSimpleField_Type()
     * @model default="STRING"
     * @generated
     */
    FieldType getType();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.businessobject.editor.model.SimpleField#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see org.bonitasoft.studio.businessobject.editor.model.FieldType
     * @see #getType()
     * @generated
     */
    void setType(FieldType value);

    /**
     * Returns the value of the '<em><b>Length</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Length</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Length</em>' attribute.
     * @see #setLength(int)
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getSimpleField_Length()
     * @model
     * @generated
     */
    int getLength();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.businessobject.editor.model.SimpleField#getLength <em>Length</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Length</em>' attribute.
     * @see #getLength()
     * @generated
     */
    void setLength(int value);

} // SimpleField
