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

import org.bonitasoft.studio.model.expression.Expression;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>KPI Parameter Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.kpi.KPIParameterMapping#getKpiFieldName <em>Kpi Field Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.KPIParameterMapping#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.kpi.KpiPackage#getKPIParameterMapping()
 * @model
 * @generated
 */
public interface KPIParameterMapping extends EObject {
	/**
     * Returns the value of the '<em><b>Kpi Field Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kpi Field Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Kpi Field Name</em>' attribute.
     * @see #setKpiFieldName(String)
     * @see org.bonitasoft.studio.model.kpi.KpiPackage#getKPIParameterMapping_KpiFieldName()
     * @model
     * @generated
     */
	String getKpiFieldName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.KPIParameterMapping#getKpiFieldName <em>Kpi Field Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Kpi Field Name</em>' attribute.
     * @see #getKpiFieldName()
     * @generated
     */
	void setKpiFieldName(String value);

	/**
     * Returns the value of the '<em><b>Value</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' containment reference.
     * @see #setValue(Expression)
     * @see org.bonitasoft.studio.model.kpi.KpiPackage#getKPIParameterMapping_Value()
     * @model containment="true"
     * @generated
     */
	Expression getValue();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.KPIParameterMapping#getValue <em>Value</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' containment reference.
     * @see #getValue()
     * @generated
     */
	void setValue(Expression value);

} // KPIParameterMapping
