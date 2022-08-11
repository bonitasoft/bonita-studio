/**
 */
package org.bonitasoft.studio.businessobject.editor.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relation Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.RelationField#getReference <em>Reference</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.RelationField#getType <em>Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.RelationField#getFetchType <em>Fetch Type</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getRelationField()
 * @model
 * @generated
 */
public interface RelationField extends Field {
    /**
     * Returns the value of the '<em><b>Reference</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Reference</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Reference</em>' reference.
     * @see #setReference(BusinessObject)
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getRelationField_Reference()
     * @model
     * @generated
     */
    BusinessObject getReference();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.businessobject.editor.model.RelationField#getReference <em>Reference</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Reference</em>' reference.
     * @see #getReference()
     * @generated
     */
    void setReference(BusinessObject value);

    /**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * The default value is <code>"AGGREGATION"</code>.
     * The literals are from the enumeration {@link org.bonitasoft.studio.businessobject.editor.model.RelationType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see org.bonitasoft.studio.businessobject.editor.model.RelationType
     * @see #setType(RelationType)
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getRelationField_Type()
     * @model default="AGGREGATION"
     * @generated
     */
    RelationType getType();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.businessobject.editor.model.RelationField#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see org.bonitasoft.studio.businessobject.editor.model.RelationType
     * @see #getType()
     * @generated
     */
    void setType(RelationType value);

    /**
     * Returns the value of the '<em><b>Fetch Type</b></em>' attribute.
     * The default value is <code>"LAZY"</code>.
     * The literals are from the enumeration {@link org.bonitasoft.studio.businessobject.editor.model.FetchType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Fetch Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Fetch Type</em>' attribute.
     * @see org.bonitasoft.studio.businessobject.editor.model.FetchType
     * @see #setFetchType(FetchType)
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getRelationField_FetchType()
     * @model default="LAZY"
     * @generated
     */
    FetchType getFetchType();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.businessobject.editor.model.RelationField#getFetchType <em>Fetch Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Fetch Type</em>' attribute.
     * @see org.bonitasoft.studio.businessobject.editor.model.FetchType
     * @see #getFetchType()
     * @generated
     */
    void setFetchType(FetchType value);

} // RelationField
