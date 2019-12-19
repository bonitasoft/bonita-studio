/**
 */
package org.bonitasoft.studio.businessobject.editor.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Query</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.Query#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.Query#getContent <em>Content</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.Query#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.Query#getQueryParameters <em>Query Parameters</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getQuery()
 * @model
 * @generated
 */
public interface Query extends EObject {
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
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getQuery_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.businessobject.editor.model.Query#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Content</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Content</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Content</em>' attribute.
     * @see #setContent(String)
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getQuery_Content()
     * @model
     * @generated
     */
    String getContent();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.businessobject.editor.model.Query#getContent <em>Content</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Content</em>' attribute.
     * @see #getContent()
     * @generated
     */
    void setContent(String value);

    /**
     * Returns the value of the '<em><b>Return Type</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Return Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Return Type</em>' attribute.
     * @see #setReturnType(String)
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getQuery_ReturnType()
     * @model
     * @generated
     */
    String getReturnType();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.businessobject.editor.model.Query#getReturnType <em>Return Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Return Type</em>' attribute.
     * @see #getReturnType()
     * @generated
     */
    void setReturnType(String value);

    /**
     * Returns the value of the '<em><b>Query Parameters</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.businessobject.editor.model.QueryParameter}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query Parameters</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query Parameters</em>' containment reference list.
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getQuery_QueryParameters()
     * @model containment="true"
     * @generated
     */
    EList<QueryParameter> getQueryParameters();

} // Query
