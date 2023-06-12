/**
 */
package org.bonitasoft.studio.businessobject.editor.model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Business Object Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.businessobject.editor.model.BusinessObjectModel#getPackages <em>Packages</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getBusinessObjectModel()
 * @model
 * @generated
 */
public interface BusinessObjectModel extends EObject {
    /**
     * Returns the value of the '<em><b>Packages</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.businessobject.editor.model.Package}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Packages</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Packages</em>' containment reference list.
     * @see org.bonitasoft.studio.businessobject.editor.model.BusinessDataModelPackage#getBusinessObjectModel_Packages()
     * @model containment="true"
     * @generated
     */
    EList<org.bonitasoft.studio.businessobject.editor.model.Package> getPackages();


} // BusinessObjectModel
