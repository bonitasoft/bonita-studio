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
package org.bonitasoft.studio.model.process;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.Activity#getBoundaryIntermediateEvents <em>Boundary Intermediate Events</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getActivity()
 * @model
 * @generated
 */
public interface Activity extends FlowElement, ConnectableElement, OperationContainer, MultiInstantiable {
	/**
	 * Returns the value of the '<em><b>Boundary Intermediate Events</b></em>' containment reference list.
	 * The list contents are of type {@link org.bonitasoft.studio.model.process.BoundaryEvent}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Boundary Intermediate Events</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Boundary Intermediate Events</em>' containment reference list.
	 * @see org.bonitasoft.studio.model.process.ProcessPackage#getActivity_BoundaryIntermediateEvents()
	 * @model containment="true"
	 * @generated
	 */
	EList<BoundaryEvent> getBoundaryIntermediateEvents();

} // Activity
