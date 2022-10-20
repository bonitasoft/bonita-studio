/**
 */
package org.bonitasoft.studio.businessobject.editor.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Business Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getQualifiedName <em>Qualified Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getSimpleName <em>Simple Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getDescription <em>Description</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getFields <em>Fields</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getUniqueConstraints <em>Unique Constraints</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getIndexes <em>Indexes</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getQueries <em>Queries</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getDefaultQueries <em>Default Queries</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getBusinessObject()
 * @model
 * @generated
 */
public interface BusinessObject extends EObject {
    /**
     * Returns the value of the '<em><b>Qualified Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Qualified Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Qualified Name</em>' attribute.
     * @see #setQualifiedName(String)
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getBusinessObject_QualifiedName()
     * @model
     * @generated
     */
    String getQualifiedName();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getQualifiedName <em>Qualified Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Qualified Name</em>' attribute.
     * @see #getQualifiedName()
     * @generated
     */
    void setQualifiedName(String value);

    /**
     * Returns the value of the '<em><b>Simple Name</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Simple Name</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Simple Name</em>' attribute.
     * @see #setSimpleName(String)
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getBusinessObject_SimpleName()
     * @model
     * @generated
     */
    String getSimpleName();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getSimpleName <em>Simple Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Simple Name</em>' attribute.
     * @see #getSimpleName()
     * @generated
     */
    void setSimpleName(String value);

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
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getBusinessObject_Description()
     * @model
     * @generated
     */
    String getDescription();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObject#getDescription <em>Description</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Description</em>' attribute.
     * @see #getDescription()
     * @generated
     */
    void setDescription(String value);

    /**
     * Returns the value of the '<em><b>Fields</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.businessobject.editor.model.Field}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Fields</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Fields</em>' containment reference list.
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getBusinessObject_Fields()
     * @model containment="true"
     * @generated
     */
    EList<Field> getFields();

    /**
     * Returns the value of the '<em><b>Unique Constraints</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.businessobject.editor.model.UniqueConstraint}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Unique Constraints</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Unique Constraints</em>' containment reference list.
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getBusinessObject_UniqueConstraints()
     * @model containment="true"
     * @generated
     */
    EList<UniqueConstraint> getUniqueConstraints();

    /**
     * Returns the value of the '<em><b>Indexes</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.businessobject.editor.model.Index}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Indexes</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Indexes</em>' containment reference list.
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getBusinessObject_Indexes()
     * @model containment="true"
     * @generated
     */
    EList<Index> getIndexes();

    /**
     * Returns the value of the '<em><b>Queries</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.businessobject.editor.model.Query}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Queries</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Queries</em>' containment reference list.
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getBusinessObject_Queries()
     * @model containment="true"
     * @generated
     */
    EList<Query> getQueries();

    /**
     * Returns the value of the '<em><b>Default Queries</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.businessobject.editor.model.Query}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Default Queries</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Default Queries</em>' containment reference list.
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getBusinessObject_DefaultQueries()
     * @model containment="true"
     * @generated
     */
    EList<Query> getDefaultQueries();

} // BusinessObject
