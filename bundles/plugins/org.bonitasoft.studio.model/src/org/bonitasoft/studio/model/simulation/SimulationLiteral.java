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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.simulation.SimulationLiteral#getProbability <em>Probability</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.SimulationLiteral#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getSimulationLiteral()
 * @model
 * @generated
 */
public interface SimulationLiteral extends EObject {
	/**
     * Returns the value of the '<em><b>Probability</b></em>' attribute.
     * The default value is <code>"1"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Probability</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Probability</em>' attribute.
     * @see #setProbability(double)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getSimulationLiteral_Probability()
     * @model default="1" required="true"
     * @generated
     */
	double getProbability();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.SimulationLiteral#getProbability <em>Probability</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Probability</em>' attribute.
     * @see #getProbability()
     * @generated
     */
	void setProbability(double value);

	/**
     * Returns the value of the '<em><b>Value</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(String)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getSimulationLiteral_Value()
     * @model default="" required="true"
     * @generated
     */
	String getValue();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.SimulationLiteral#getValue <em>Value</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
	void setValue(String value);

} // SimulationLiteral
