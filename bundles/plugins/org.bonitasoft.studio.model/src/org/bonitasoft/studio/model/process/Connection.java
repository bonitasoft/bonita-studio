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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.Connection#getTarget <em>Target</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Connection#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getConnection()
 * @model
 * @generated
 */
public interface Connection extends Element {
	/**
     * Returns the value of the '<em><b>Target</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.bonitasoft.studio.model.process.TargetElement#getIncoming <em>Incoming</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Target</em>' reference.
     * @see #setTarget(TargetElement)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getConnection_Target()
     * @see org.bonitasoft.studio.model.process.TargetElement#getIncoming
     * @model opposite="incoming"
     * @generated
     */
	TargetElement getTarget();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Connection#getTarget <em>Target</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target</em>' reference.
     * @see #getTarget()
     * @generated
     */
	void setTarget(TargetElement value);

	/**
     * Returns the value of the '<em><b>Source</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.bonitasoft.studio.model.process.SourceElement#getOutgoing <em>Outgoing</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' reference.
     * @see #setSource(SourceElement)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getConnection_Source()
     * @see org.bonitasoft.studio.model.process.SourceElement#getOutgoing
     * @model opposite="outgoing" derived="true"
     * @generated
     */
	SourceElement getSource();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Connection#getSource <em>Source</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' reference.
     * @see #getSource()
     * @generated
     */
	void setSource(SourceElement value);

} // Connection
