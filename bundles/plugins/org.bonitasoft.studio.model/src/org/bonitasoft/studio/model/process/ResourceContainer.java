/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.process;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.bonitasoft.studio.model.process.ResourceContainer#getHtmlTemplate <em>Html Template</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.ResourceContainer#getResourceJars <em>Resource Jars</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.ResourceContainer#getResourceValidators <em>Resource Validators</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.ResourceContainer#getResourceFiles <em>Resource Files</em>}</li>
 * <li>{@link org.bonitasoft.studio.model.process.ResourceContainer#getResourceFolders <em>Resource Folders</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getResourceContainer()
 * @model
 * @generated
 */
public interface ResourceContainer extends EObject {

    /**
     * Returns the value of the '<em><b>Html Template</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Html Template</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Html Template</em>' containment reference.
     * @see #setHtmlTemplate(AssociatedFile)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getResourceContainer_HtmlTemplate()
     * @model containment="true"
     * @generated
     */
    AssociatedFile getHtmlTemplate();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.ResourceContainer#getHtmlTemplate <em>Html
     * Template</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value the new value of the '<em>Html Template</em>' containment reference.
     * @see #getHtmlTemplate()
     * @generated
     */
    void setHtmlTemplate(AssociatedFile value);

    /**
     * Returns the value of the '<em><b>Resource Jars</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resource Jars</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Resource Jars</em>' attribute list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getResourceContainer_ResourceJars()
     * @model
     * @generated
     */
    EList<String> getResourceJars();

    /**
     * Returns the value of the '<em><b>Resource Validators</b></em>' attribute list.
     * The list contents are of type {@link java.lang.String}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resource Validators</em>' attribute list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Resource Validators</em>' attribute list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getResourceContainer_ResourceValidators()
     * @model
     * @generated
     */
    EList<String> getResourceValidators();

    /**
     * Returns the value of the '<em><b>Resource Files</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.process.ResourceFile}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resource Files</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Resource Files</em>' containment reference list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getResourceContainer_ResourceFiles()
     * @model containment="true"
     * @generated
     */
    EList<ResourceFile> getResourceFiles();

    /**
     * Returns the value of the '<em><b>Resource Folders</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.process.ResourceFolder}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Resource Folders</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Resource Folders</em>' containment reference list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getResourceContainer_ResourceFolders()
     * @model containment="true"
     * @generated
     */
    EList<ResourceFolder> getResourceFolders();

} // ResourceContainer
