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

import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.TableExpression;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Catch Message Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.AbstractCatchMessageEvent#getEvent <em>Event</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.AbstractCatchMessageEvent#getIncomingMessag <em>Incoming Messag</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.AbstractCatchMessageEvent#getCorrelation <em>Correlation</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.AbstractCatchMessageEvent#getMessageContent <em>Message Content</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getAbstractCatchMessageEvent()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface AbstractCatchMessageEvent extends Element {
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
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getAbstractCatchMessageEvent_Event()
     * @model
     * @generated
     */
	String getEvent();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.AbstractCatchMessageEvent#getEvent <em>Event</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Event</em>' attribute.
     * @see #getEvent()
     * @generated
     */
	void setEvent(String value);

	/**
     * Returns the value of the '<em><b>Incoming Messag</b></em>' reference.
     * It is bidirectional and its opposite is '{@link org.bonitasoft.studio.model.process.MessageFlow#getTarget <em>Target</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Incoming Messag</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Incoming Messag</em>' reference.
     * @see #setIncomingMessag(MessageFlow)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getAbstractCatchMessageEvent_IncomingMessag()
     * @see org.bonitasoft.studio.model.process.MessageFlow#getTarget
     * @model opposite="target"
     * @generated
     */
	MessageFlow getIncomingMessag();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.AbstractCatchMessageEvent#getIncomingMessag <em>Incoming Messag</em>}' reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Incoming Messag</em>' reference.
     * @see #getIncomingMessag()
     * @generated
     */
	void setIncomingMessag(MessageFlow value);

	/**
     * Returns the value of the '<em><b>Correlation</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Correlation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Correlation</em>' containment reference.
     * @see #setCorrelation(TableExpression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getAbstractCatchMessageEvent_Correlation()
     * @model containment="true"
     * @generated
     */
	TableExpression getCorrelation();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.AbstractCatchMessageEvent#getCorrelation <em>Correlation</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Correlation</em>' containment reference.
     * @see #getCorrelation()
     * @generated
     */
	void setCorrelation(TableExpression value);

	/**
     * Returns the value of the '<em><b>Message Content</b></em>' containment reference list.
     * The list contents are of type {@link org.bonitasoft.studio.model.expression.Operation}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Content</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Message Content</em>' containment reference list.
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getAbstractCatchMessageEvent_MessageContent()
     * @model containment="true"
     * @generated
     */
	EList<Operation> getMessageContent();

} // AbstractCatchMessageEvent
