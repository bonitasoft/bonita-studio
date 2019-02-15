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
 * A representation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.simulation.Resource#getType <em>Type</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.Resource#getQuantity <em>Quantity</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.Resource#getMaximumQuantity <em>Maximum Quantity</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.Resource#getCostUnit <em>Cost Unit</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.Resource#getTimeUnit <em>Time Unit</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.Resource#getFixedCost <em>Fixed Cost</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.Resource#getTimeCost <em>Time Cost</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.Resource#getCalendar <em>Calendar</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.Resource#isUnlimited <em>Unlimited</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getResource()
 * @model
 * @generated
 */
public interface Resource extends SimulationElement, ModelVersion {
	/**
     * Returns the value of the '<em><b>Type</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Type</em>' attribute.
     * @see #setType(String)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getResource_Type()
     * @model
     * @generated
     */
	String getType();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.Resource#getType <em>Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Type</em>' attribute.
     * @see #getType()
     * @generated
     */
	void setType(String value);

	/**
     * Returns the value of the '<em><b>Quantity</b></em>' attribute.
     * The default value is <code>"1"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Quantity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Quantity</em>' attribute.
     * @see #setQuantity(int)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getResource_Quantity()
     * @model default="1"
     * @generated
     */
	int getQuantity();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.Resource#getQuantity <em>Quantity</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Quantity</em>' attribute.
     * @see #getQuantity()
     * @generated
     */
	void setQuantity(int value);

	/**
     * Returns the value of the '<em><b>Maximum Quantity</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Maximum Quantity</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Maximum Quantity</em>' attribute.
     * @see #setMaximumQuantity(int)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getResource_MaximumQuantity()
     * @model
     * @generated
     */
	int getMaximumQuantity();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.Resource#getMaximumQuantity <em>Maximum Quantity</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Maximum Quantity</em>' attribute.
     * @see #getMaximumQuantity()
     * @generated
     */
	void setMaximumQuantity(int value);

	/**
     * Returns the value of the '<em><b>Cost Unit</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cost Unit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Cost Unit</em>' attribute.
     * @see #setCostUnit(String)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getResource_CostUnit()
     * @model
     * @generated
     */
	String getCostUnit();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.Resource#getCostUnit <em>Cost Unit</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Cost Unit</em>' attribute.
     * @see #getCostUnit()
     * @generated
     */
	void setCostUnit(String value);

	/**
     * Returns the value of the '<em><b>Time Unit</b></em>' attribute.
     * The literals are from the enumeration {@link org.bonitasoft.studio.model.simulation.TimeUnit}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Unit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Time Unit</em>' attribute.
     * @see org.bonitasoft.studio.model.simulation.TimeUnit
     * @see #setTimeUnit(TimeUnit)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getResource_TimeUnit()
     * @model
     * @generated
     */
	TimeUnit getTimeUnit();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.Resource#getTimeUnit <em>Time Unit</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Time Unit</em>' attribute.
     * @see org.bonitasoft.studio.model.simulation.TimeUnit
     * @see #getTimeUnit()
     * @generated
     */
	void setTimeUnit(TimeUnit value);

	/**
     * Returns the value of the '<em><b>Fixed Cost</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fixed Cost</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Fixed Cost</em>' attribute.
     * @see #setFixedCost(double)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getResource_FixedCost()
     * @model
     * @generated
     */
	double getFixedCost();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.Resource#getFixedCost <em>Fixed Cost</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Fixed Cost</em>' attribute.
     * @see #getFixedCost()
     * @generated
     */
	void setFixedCost(double value);

	/**
     * Returns the value of the '<em><b>Time Cost</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Time Cost</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Time Cost</em>' attribute.
     * @see #setTimeCost(double)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getResource_TimeCost()
     * @model
     * @generated
     */
	double getTimeCost();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.Resource#getTimeCost <em>Time Cost</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Time Cost</em>' attribute.
     * @see #getTimeCost()
     * @generated
     */
	void setTimeCost(double value);

	/**
     * Returns the value of the '<em><b>Calendar</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Calendar</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Calendar</em>' containment reference.
     * @see #setCalendar(SimulationCalendar)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getResource_Calendar()
     * @model containment="true"
     * @generated
     */
	SimulationCalendar getCalendar();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.Resource#getCalendar <em>Calendar</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Calendar</em>' containment reference.
     * @see #getCalendar()
     * @generated
     */
	void setCalendar(SimulationCalendar value);

	/**
     * Returns the value of the '<em><b>Unlimited</b></em>' attribute.
     * The default value is <code>"false"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unlimited</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Unlimited</em>' attribute.
     * @see #setUnlimited(boolean)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getResource_Unlimited()
     * @model default="false"
     * @generated
     */
	boolean isUnlimited();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.Resource#isUnlimited <em>Unlimited</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Unlimited</em>' attribute.
     * @see #isUnlimited()
     * @generated
     */
	void setUnlimited(boolean value);

} // Resource
