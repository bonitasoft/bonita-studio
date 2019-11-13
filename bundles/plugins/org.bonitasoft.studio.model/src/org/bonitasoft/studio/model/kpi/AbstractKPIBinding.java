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

import org.bonitasoft.studio.model.process.Element;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract KPI Binding</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.kpi.AbstractKPIBinding#getKpiDefinitionName <em>Kpi Definition Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.AbstractKPIBinding#getEvent <em>Event</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.AbstractKPIBinding#isIgnoreError <em>Ignore Error</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.AbstractKPIBinding#isUseGraphicalEditor <em>Use Graphical Editor</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.AbstractKPIBinding#getRequest <em>Request</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.kpi.AbstractKPIBinding#getParameters <em>Parameters</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.kpi.KpiPackage#getAbstractKPIBinding()
 * @model abstract="true"
 * @generated
 */
public interface AbstractKPIBinding extends Element {
	/**
     * Returns the value of the '<em><b>Kpi Definition Name</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kpi Definition Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Kpi Definition Name</em>' attribute.
     * @see #setKpiDefinitionName(String)
     * @see org.bonitasoft.studio.model.kpi.KpiPackage#getAbstractKPIBinding_KpiDefinitionName()
     * @model
     * @generated
     */
	String getKpiDefinitionName();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.AbstractKPIBinding#getKpiDefinitionName <em>Kpi Definition Name</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Kpi Definition Name</em>' attribute.
     * @see #getKpiDefinitionName()
     * @generated
     */
	void setKpiDefinitionName(String value);

	/**
     * Returns the value of the '<em><b>Event</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Event</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Event</em>' attribute.
     * @see #setEvent(String)
     * @see org.bonitasoft.studio.model.kpi.KpiPackage#getAbstractKPIBinding_Event()
     * @model
     * @generated
     */
	String getEvent();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.AbstractKPIBinding#getEvent <em>Event</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Event</em>' attribute.
     * @see #getEvent()
     * @generated
     */
	void setEvent(String value);

	/**
     * Returns the value of the '<em><b>Ignore Error</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ignore Error</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Ignore Error</em>' attribute.
     * @see #setIgnoreError(boolean)
     * @see org.bonitasoft.studio.model.kpi.KpiPackage#getAbstractKPIBinding_IgnoreError()
     * @model
     * @generated
     */
	boolean isIgnoreError();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.AbstractKPIBinding#isIgnoreError <em>Ignore Error</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ignore Error</em>' attribute.
     * @see #isIgnoreError()
     * @generated
     */
	void setIgnoreError(boolean value);

	/**
     * Returns the value of the '<em><b>Use Graphical Editor</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Use Graphical Editor</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Use Graphical Editor</em>' attribute.
     * @see #setUseGraphicalEditor(boolean)
     * @see org.bonitasoft.studio.model.kpi.KpiPackage#getAbstractKPIBinding_UseGraphicalEditor()
     * @model default="true"
     * @generated
     */
	boolean isUseGraphicalEditor();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.AbstractKPIBinding#isUseGraphicalEditor <em>Use Graphical Editor</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Use Graphical Editor</em>' attribute.
     * @see #isUseGraphicalEditor()
     * @generated
     */
	void setUseGraphicalEditor(boolean value);

	/**
     * Returns the value of the '<em><b>Request</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Request</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Request</em>' containment reference.
     * @see #setRequest(Expression)
     * @see org.bonitasoft.studio.model.kpi.KpiPackage#getAbstractKPIBinding_Request()
     * @model containment="true"
     * @generated
     */
	Expression getRequest();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.kpi.AbstractKPIBinding#getRequest <em>Request</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Request</em>' containment reference.
     * @see #getRequest()
     * @generated
     */
	void setRequest(Expression value);

	/**
     * Returns the value of the '<em><b>Parameters</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.kpi.KPIParameterMapping}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameters</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Parameters</em>' containment reference list.
     * @see org.bonitasoft.studio.model.kpi.KpiPackage#getAbstractKPIBinding_Parameters()
     * @model containment="true"
     * @generated
     */
	EList<KPIParameterMapping> getParameters();

} // AbstractKPIBinding
