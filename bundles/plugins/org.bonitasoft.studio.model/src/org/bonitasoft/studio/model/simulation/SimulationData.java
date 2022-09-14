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
package org.bonitasoft.studio.model.simulation;

import org.bonitasoft.studio.model.expression.Expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.simulation.SimulationData#isExpressionBased <em>Expression Based</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.SimulationData#getExpression <em>Expression</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getSimulationData()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface SimulationData extends SimulationElement {
	/**
     * Returns the value of the '<em><b>Expression Based</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression Based</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Expression Based</em>' attribute.
     * @see #setExpressionBased(boolean)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getSimulationData_ExpressionBased()
     * @model default="true" required="true"
     * @generated
     */
	boolean isExpressionBased();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.SimulationData#isExpressionBased <em>Expression Based</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression Based</em>' attribute.
     * @see #isExpressionBased()
     * @generated
     */
	void setExpressionBased(boolean value);

	/**
     * Returns the value of the '<em><b>Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Expression</em>' containment reference.
     * @see #setExpression(Expression)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getSimulationData_Expression()
     * @model containment="true"
     * @generated
     */
	Expression getExpression();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.SimulationData#getExpression <em>Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Expression</em>' containment reference.
     * @see #getExpression()
     * @generated
     */
	void setExpression(Expression value);

} // SimulationData
