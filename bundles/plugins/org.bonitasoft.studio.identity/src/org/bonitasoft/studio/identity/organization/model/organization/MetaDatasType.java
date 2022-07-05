/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.identity.organization.model.organization;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Meta Datas Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.organization.model.organization.MetaDatasType#getMetaData <em>Meta Data</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getMetaDatasType()
 * @model extendedMetaData="name='metaDatas_._type' kind='elementOnly'"
 * @generated
 */
public interface MetaDatasType extends EObject {
	/**
     * Returns the value of the '<em><b>Meta Data</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.organization.model.organization.Metadata}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Meta Data</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Meta Data</em>' containment reference list.
     * @see org.bonitasoft.studio.organization.model.organization.OrganizationPackage#getMetaDatasType_MetaData()
     * @model containment="true"
     *        extendedMetaData="kind='element' name='metaData'"
     * @generated
     */
	EList<Metadata> getMetaData();

} // MetaDatasType
