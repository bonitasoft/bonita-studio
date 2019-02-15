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
package org.bonitasoft.studio.model.kpi.util;

import org.bonitasoft.studio.model.kpi.*;

import org.bonitasoft.studio.model.process.Element;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.model.kpi.KpiPackage
 * @generated
 */
public class KpiSwitch<T> extends Switch<T> {
	/**
     * The cached model package
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	protected static KpiPackage modelPackage;

	/**
     * Creates an instance of the switch.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public KpiSwitch() {
        if (modelPackage == null) {
            modelPackage = KpiPackage.eINSTANCE;
        }
    }

	/**
     * Checks whether this is a switch for the given package.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param ePackage the package in question.
     * @return whether this is a switch for the given package.
     * @generated
     */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
        return ePackage == modelPackage;
    }

	/**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
            case KpiPackage.ABSTRACT_KPI_DEFINITION: {
                AbstractKPIDefinition abstractKPIDefinition = (AbstractKPIDefinition)theEObject;
                T result = caseAbstractKPIDefinition(abstractKPIDefinition);
                if (result == null) result = caseElement(abstractKPIDefinition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KpiPackage.ABSTRACT_KPI_BINDING: {
                AbstractKPIBinding abstractKPIBinding = (AbstractKPIBinding)theEObject;
                T result = caseAbstractKPIBinding(abstractKPIBinding);
                if (result == null) result = caseElement(abstractKPIBinding);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KpiPackage.KPI_PARAMETER_MAPPING: {
                KPIParameterMapping kpiParameterMapping = (KPIParameterMapping)theEObject;
                T result = caseKPIParameterMapping(kpiParameterMapping);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KpiPackage.DATABASE_KPI_BINDING: {
                DatabaseKPIBinding databaseKPIBinding = (DatabaseKPIBinding)theEObject;
                T result = caseDatabaseKPIBinding(databaseKPIBinding);
                if (result == null) result = caseAbstractKPIBinding(databaseKPIBinding);
                if (result == null) result = caseElement(databaseKPIBinding);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KpiPackage.DATABASE_KPI_DEFINITION: {
                DatabaseKPIDefinition databaseKPIDefinition = (DatabaseKPIDefinition)theEObject;
                T result = caseDatabaseKPIDefinition(databaseKPIDefinition);
                if (result == null) result = caseAbstractKPIDefinition(databaseKPIDefinition);
                if (result == null) result = caseElement(databaseKPIDefinition);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            case KpiPackage.KPI_FIELD: {
                KPIField kpiField = (KPIField)theEObject;
                T result = caseKPIField(kpiField);
                if (result == null) result = defaultCase(theEObject);
                return result;
            }
            default: return defaultCase(theEObject);
        }
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Abstract KPI Definition</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract KPI Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseAbstractKPIDefinition(AbstractKPIDefinition object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Abstract KPI Binding</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract KPI Binding</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseAbstractKPIBinding(AbstractKPIBinding object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>KPI Parameter Mapping</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KPI Parameter Mapping</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseKPIParameterMapping(KPIParameterMapping object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Database KPI Binding</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Database KPI Binding</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseDatabaseKPIBinding(DatabaseKPIBinding object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Database KPI Definition</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Database KPI Definition</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseDatabaseKPIDefinition(DatabaseKPIDefinition object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>KPI Field</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>KPI Field</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseKPIField(KPIField object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>Element</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
	public T caseElement(Element object) {
        return null;
    }

	/**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
     * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
     * @param object the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
	@Override
	public T defaultCase(EObject object) {
        return null;
    }

} //KpiSwitch
