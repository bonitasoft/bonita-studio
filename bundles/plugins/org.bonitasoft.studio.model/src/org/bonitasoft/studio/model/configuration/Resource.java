/**
 * Copyright (C) 2009-2019 BonitaSoft S.A.
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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.configuration.Resource#getBarPath <em>Bar Path</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.configuration.Resource#getProjectPath <em>Project Path</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getResource()
 * @model
 * @generated
 */
public interface Resource extends EObject {
    /**
     * Returns the value of the '<em><b>Bar Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Bar Path</em>' attribute.
     * @see #setBarPath(String)
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getResource_BarPath()
     * @model
     * @generated
     */
    String getBarPath();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.model.configuration.Resource#getBarPath <em>Bar Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Bar Path</em>' attribute.
     * @see #getBarPath()
     * @generated
     */
    void setBarPath(String value);

    /**
     * Returns the value of the '<em><b>Project Path</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the value of the '<em>Project Path</em>' attribute.
     * @see #setProjectPath(String)
     * @see org.bonitasoft.studio.model.configuration.ConfigurationPackage#getResource_ProjectPath()
     * @model
     * @generated
     */
    String getProjectPath();

    /**
     * Sets the value of the '{@link org.bonitasoft.studio.model.configuration.Resource#getProjectPath <em>Project Path</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Project Path</em>' attribute.
     * @see #getProjectPath()
     * @generated
     */
    void setProjectPath(String value);

} // Resource
