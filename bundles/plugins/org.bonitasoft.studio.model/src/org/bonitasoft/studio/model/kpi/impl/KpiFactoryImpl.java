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
package org.bonitasoft.studio.model.kpi.impl;

import org.bonitasoft.studio.model.kpi.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class KpiFactoryImpl extends EFactoryImpl implements KpiFactory {
	/**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static KpiFactory init() {
        try {
            KpiFactory theKpiFactory = (KpiFactory)EPackage.Registry.INSTANCE.getEFactory(KpiPackage.eNS_URI);
            if (theKpiFactory != null) {
                return theKpiFactory;
            }
        }
        catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new KpiFactoryImpl();
    }

	/**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public KpiFactoryImpl() {
        super();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
            case KpiPackage.KPI_PARAMETER_MAPPING: return createKPIParameterMapping();
            case KpiPackage.DATABASE_KPI_BINDING: return createDatabaseKPIBinding();
            case KpiPackage.DATABASE_KPI_DEFINITION: return createDatabaseKPIDefinition();
            case KpiPackage.KPI_FIELD: return createKPIField();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public KPIParameterMapping createKPIParameterMapping() {
        KPIParameterMappingImpl kpiParameterMapping = new KPIParameterMappingImpl();
        return kpiParameterMapping;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public DatabaseKPIBinding createDatabaseKPIBinding() {
        DatabaseKPIBindingImpl databaseKPIBinding = new DatabaseKPIBindingImpl();
        return databaseKPIBinding;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public DatabaseKPIDefinition createDatabaseKPIDefinition() {
        DatabaseKPIDefinitionImpl databaseKPIDefinition = new DatabaseKPIDefinitionImpl();
        return databaseKPIDefinition;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public KPIField createKPIField() {
        KPIFieldImpl kpiField = new KPIFieldImpl();
        return kpiField;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public KpiPackage getKpiPackage() {
        return (KpiPackage)getEPackage();
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
	@Deprecated
	public static KpiPackage getPackage() {
        return KpiPackage.eINSTANCE;
    }

} //KpiFactoryImpl
