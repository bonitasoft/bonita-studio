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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Output Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.OutputMapping#getSubprocessSource <em>Subprocess Source</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.OutputMapping#getProcessTarget <em>Process Target</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getOutputMapping()
 * @model
 * @generated
 */
public interface OutputMapping extends EObject {
	/**
     * Returns the value of the '<em><b>Subprocess Source</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subprocess Source</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Subprocess Source</em>' attribute.
     * @see #setSubprocessSource(String)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getOutputMapping_SubprocessSource()
     * @model
     * @generated
     */
	String getSubprocessSource();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.OutputMapping#getSubprocessSource <em>Subprocess Source</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Subprocess Source</em>' attribute.
     * @see #getSubprocessSource()
     * @generated
     */
	void setSubprocessSource(String value);

	/**
     * Returns the value of the '<em><b>Process Target</b></em>' reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Process Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Process Target</em>' reference.
     * @see #setProcessTarget(Data)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getOutputMapping_ProcessTarget()
     * @model
     * @generated
     */
	Data getProcessTarget();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.OutputMapping#getProcessTarget <em>Process Target</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Process Target</em>' reference.
     * @see #getProcessTarget()
     * @generated
     */
	void setProcessTarget(Data value);

} // OutputMapping
