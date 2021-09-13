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
 * A representation of the model object '<em><b>Resource Usage</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.simulation.ResourceUsage#getDuration <em>Duration</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.ResourceUsage#getResourceID <em>Resource ID</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.ResourceUsage#getQuantity <em>Quantity</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.simulation.ResourceUsage#isUseActivityDuration <em>Use Activity Duration</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getResourceUsage()
 * @model
 * @generated
 */
public interface ResourceUsage extends EObject {
	/**
     * Returns the value of the '<em><b>Duration</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Duration</em>' attribute.
     * @see #setDuration(long)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getResourceUsage_Duration()
     * @model
     * @generated
     */
	long getDuration();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.ResourceUsage#getDuration <em>Duration</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Duration</em>' attribute.
     * @see #getDuration()
     * @generated
     */
	void setDuration(long value);

	/**
     * Returns the value of the '<em><b>Resource ID</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resource ID</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Resource ID</em>' attribute.
     * @see #setResourceID(String)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getResourceUsage_ResourceID()
     * @model
     * @generated
     */
	String getResourceID();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.ResourceUsage#getResourceID <em>Resource ID</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Resource ID</em>' attribute.
     * @see #getResourceID()
     * @generated
     */
	void setResourceID(String value);

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
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getResourceUsage_Quantity()
     * @model default="1" required="true"
     * @generated
     */
	int getQuantity();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.ResourceUsage#getQuantity <em>Quantity</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Quantity</em>' attribute.
     * @see #getQuantity()
     * @generated
     */
	void setQuantity(int value);

	/**
     * Returns the value of the '<em><b>Use Activity Duration</b></em>' attribute.
     * The default value is <code>"true"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Use Activity Duration</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Use Activity Duration</em>' attribute.
     * @see #setUseActivityDuration(boolean)
     * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getResourceUsage_UseActivityDuration()
     * @model default="true" required="true"
     * @generated
     */
	boolean isUseActivityDuration();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.simulation.ResourceUsage#isUseActivityDuration <em>Use Activity Duration</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Use Activity Duration</em>' attribute.
     * @see #isUseActivityDuration()
     * @generated
     */
	void setUseActivityDuration(boolean value);

} // ResourceUsage
