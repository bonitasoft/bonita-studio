/**
 */
package org.bonitasoft.studio.businessobject.editor.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.Package#getBusinessObjects <em>Business Objects</em>}</li>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.Package#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getPackage()
 * @model
 * @generated
 */
public interface Package extends EObject {
    /**
     * Returns the value of the '<em><b>Business Objects</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.businessobject.editor.model.BusinessObject}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Business Objects</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Business Objects</em>' containment reference list.
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getPackage_BusinessObjects()
     * @model containment="true"
     * @generated
     */
    EList<BusinessObject> getBusinessObjects();

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
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getPackage_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.businessobject.editor.model.Package#getName <em>Name</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

} // Package
