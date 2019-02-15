/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.configuration;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fragment Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.configuration.FragmentContainer#getChildren <em>Children</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.FragmentContainer#getParent <em>Parent</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.FragmentContainer#getFragments <em>Fragments</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.FragmentContainer#getId <em>Id</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getFragmentContainer()
 * @model
 * @generated
 */
public interface FragmentContainer extends EObject {
	/**
     * Returns the value of the '<em><b>Children</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.configuration.FragmentContainer}.
     * It is bidirectional and its opposite is '{@link org.bonitasoft.studio.model.configuration.FragmentContainer#getParent <em>Parent</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Children</em>' containment reference list.
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getFragmentContainer_Children()
     * @see org.bonitasoft.studio.model.configuration.FragmentContainer#getParent
     * @model opposite="parent" containment="true"
     * @generated
     */
	EList<FragmentContainer> getChildren();

	/**
     * Returns the value of the '<em><b>Parent</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.bonitasoft.studio.model.configuration.FragmentContainer#getChildren <em>Children</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Parent</em>' container reference.
     * @see #setParent(FragmentContainer)
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getFragmentContainer_Parent()
     * @see org.bonitasoft.studio.model.configuration.FragmentContainer#getChildren
     * @model opposite="children" transient="false"
     * @generated
     */
	FragmentContainer getParent();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.configuration.FragmentContainer#getParent <em>Parent</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Parent</em>' container reference.
     * @see #getParent()
     * @generated
     */
	void setParent(FragmentContainer value);

	/**
     * Returns the value of the '<em><b>Fragments</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.configuration.Fragment}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fragments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Fragments</em>' containment reference list.
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getFragmentContainer_Fragments()
     * @model containment="true"
     * @generated
     */
	EList<Fragment> getFragments();

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
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getFragmentContainer_Id()
     * @model required="true"
     * @generated
     */
	String getId();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.configuration.FragmentContainer#getId <em>Id</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
	void setId(String value);

} // FragmentContainer
