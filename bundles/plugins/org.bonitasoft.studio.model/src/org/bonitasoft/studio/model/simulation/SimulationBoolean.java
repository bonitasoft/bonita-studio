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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Boolean</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.simulation.SimulationBoolean#getProbabilityOfTrue <em>Probability Of True</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getSimulationBoolean()
 * @model
 * @generated
 */
public interface SimulationBoolean extends SimulationData {
	/**
     * Returns the value of the '<em><b>Probability Of True</b></em>' attribute.
     * The default value is <code>"1"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Probability Of True</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Probability Of True</em>' attribute.
     * @see #setProbabilityOfTrue(double)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getSimulationBoolean_ProbabilityOfTrue()
     * @model default="1" required="true"
     * @generated
     */
	double getProbabilityOfTrue();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.SimulationBoolean#getProbabilityOfTrue <em>Probability Of True</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Probability Of True</em>' attribute.
     * @see #getProbabilityOfTrue()
     * @generated
     */
	void setProbabilityOfTrue(double value);

} // SimulationBoolean
