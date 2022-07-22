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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.model.kpi.KpiPackage
 * @generated
 */
public interface KpiFactory extends EFactory {
	/**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	KpiFactory eINSTANCE = org.bonitasoft.studio.model.kpi.impl.KpiFactoryImpl.init();

	/**
     * Returns a new object of class '<em>KPI Parameter Mapping</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>KPI Parameter Mapping</em>'.
     * @generated
     */
	KPIParameterMapping createKPIParameterMapping();

	/**
     * Returns a new object of class '<em>Database KPI Binding</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Database KPI Binding</em>'.
     * @generated
     */
	DatabaseKPIBinding createDatabaseKPIBinding();

	/**
     * Returns a new object of class '<em>Database KPI Definition</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>Database KPI Definition</em>'.
     * @generated
     */
	DatabaseKPIDefinition createDatabaseKPIDefinition();

	/**
     * Returns a new object of class '<em>KPI Field</em>'.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return a new object of class '<em>KPI Field</em>'.
     * @generated
     */
	KPIField createKPIField();

	/**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
	KpiPackage getKpiPackage();

} //KpiFactory
