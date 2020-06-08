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
package org.bonitasoft.studio.model.process;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Recap Flow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.RecapFlow#getOverviewFormMapping <em>Overview Form Mapping</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getRecapFlow()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface RecapFlow extends AbstractPageFlow {
	/**
     * Returns the value of the '<em><b>Overview Form Mapping</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Overview Form Mapping</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Overview Form Mapping</em>' containment reference.
     * @see #setOverviewFormMapping(FormMapping)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getRecapFlow_OverviewFormMapping()
     * @model containment="true"
     * @generated
     */
	FormMapping getOverviewFormMapping();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.RecapFlow#getOverviewFormMapping <em>Overview Form Mapping</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Overview Form Mapping</em>' containment reference.
     * @see #getOverviewFormMapping()
     * @generated
     */
	void setOverviewFormMapping(FormMapping value);

} // RecapFlow
