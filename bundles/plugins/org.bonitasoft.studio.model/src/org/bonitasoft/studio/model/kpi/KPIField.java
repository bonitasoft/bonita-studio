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
package org.bonitasoft.studio.model.kpi;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KPI Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.kpi.KPIField#getFieldName <em>Field Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.KPIField#getFieldType <em>Field Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.KPIField#isUseQuotes <em>Use Quotes</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.kpi.KpiPackage#getKPIField()
 * @model
 * @generated
 */
public interface KPIField extends EObject {
	/**
	 * Returns the value of the '<em><b>Field Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Field Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field Name</em>' attribute.
	 * @see #setFieldName(String)
	 * @see org.bonitasoft.studio.model.kpi.KpiPackage#getKPIField_FieldName()
	 * @model
	 * @generated
	 */
	String getFieldName();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.KPIField#getFieldName <em>Field Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field Name</em>' attribute.
	 * @see #getFieldName()
	 * @generated
	 */
	void setFieldName(String value);

	/**
	 * Returns the value of the '<em><b>Field Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Field Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Field Type</em>' attribute.
	 * @see #setFieldType(String)
	 * @see org.bonitasoft.studio.model.kpi.KpiPackage#getKPIField_FieldType()
	 * @model
	 * @generated
	 */
	String getFieldType();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.KPIField#getFieldType <em>Field Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Field Type</em>' attribute.
	 * @see #getFieldType()
	 * @generated
	 */
	void setFieldType(String value);

	/**
	 * Returns the value of the '<em><b>Use Quotes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Use Quotes</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Use Quotes</em>' attribute.
	 * @see #setUseQuotes(boolean)
	 * @see org.bonitasoft.studio.model.kpi.KpiPackage#getKPIField_UseQuotes()
	 * @model
	 * @generated
	 */
	boolean isUseQuotes();

	/**
	 * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.KPIField#isUseQuotes <em>Use Quotes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Use Quotes</em>' attribute.
	 * @see #isUseQuotes()
	 * @generated
	 */
	void setUseQuotes(boolean value);

} // KPIField
