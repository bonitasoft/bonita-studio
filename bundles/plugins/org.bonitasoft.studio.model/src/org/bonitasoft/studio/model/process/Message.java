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

import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.TableExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Message</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.Message#getThrowEvent <em>Throw Event</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Message#getTtl <em>Ttl</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Message#getCorrelation <em>Correlation</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Message#getSource <em>Source</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Message#getTargetProcessExpression <em>Target Process Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Message#getTargetElementExpression <em>Target Element Expression</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.Message#getMessageContent <em>Message Content</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getMessage()
 * @model
 * @generated
 */
public interface Message extends Element {
	/**
     * Returns the value of the '<em><b>Throw Event</b></em>' attribute.
     * The default value is <code>""</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Throw Event</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Throw Event</em>' attribute.
     * @see #setThrowEvent(String)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMessage_ThrowEvent()
     * @model default="" required="true"
     * @generated
     */
	String getThrowEvent();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Message#getThrowEvent <em>Throw Event</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Throw Event</em>' attribute.
     * @see #getThrowEvent()
     * @generated
     */
	void setThrowEvent(String value);

	/**
     * Returns the value of the '<em><b>Ttl</b></em>' attribute.
     * The default value is <code>"31104000000"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ttl</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Ttl</em>' attribute.
     * @see #setTtl(String)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMessage_Ttl()
     * @model default="31104000000"
     * @generated
     */
	String getTtl();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Message#getTtl <em>Ttl</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Ttl</em>' attribute.
     * @see #getTtl()
     * @generated
     */
	void setTtl(String value);

	/**
     * Returns the value of the '<em><b>Correlation</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Correlation</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Correlation</em>' containment reference.
     * @see #setCorrelation(Correlation)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMessage_Correlation()
     * @model containment="true" required="true"
     * @generated
     */
	Correlation getCorrelation();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Message#getCorrelation <em>Correlation</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Correlation</em>' containment reference.
     * @see #getCorrelation()
     * @generated
     */
	void setCorrelation(Correlation value);

	/**
     * Returns the value of the '<em><b>Source</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.bonitasoft.studio.model.process.ThrowMessageEvent#getEvents <em>Events</em>}'.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Source</em>' container reference.
     * @see #setSource(ThrowMessageEvent)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMessage_Source()
     * @see org.bonitasoft.studio.model.process.ThrowMessageEvent#getEvents
     * @model opposite="events" transient="false"
     * @generated
     */
	ThrowMessageEvent getSource();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Message#getSource <em>Source</em>}' container reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source</em>' container reference.
     * @see #getSource()
     * @generated
     */
	void setSource(ThrowMessageEvent value);

	/**
     * Returns the value of the '<em><b>Target Process Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Process Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Target Process Expression</em>' containment reference.
     * @see #setTargetProcessExpression(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMessage_TargetProcessExpression()
     * @model containment="true"
     * @generated
     */
	Expression getTargetProcessExpression();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Message#getTargetProcessExpression <em>Target Process Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Process Expression</em>' containment reference.
     * @see #getTargetProcessExpression()
     * @generated
     */
	void setTargetProcessExpression(Expression value);

	/**
     * Returns the value of the '<em><b>Target Element Expression</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target Element Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Target Element Expression</em>' containment reference.
     * @see #setTargetElementExpression(Expression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMessage_TargetElementExpression()
     * @model containment="true"
     * @generated
     */
	Expression getTargetElementExpression();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Message#getTargetElementExpression <em>Target Element Expression</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Element Expression</em>' containment reference.
     * @see #getTargetElementExpression()
     * @generated
     */
	void setTargetElementExpression(Expression value);

	/**
     * Returns the value of the '<em><b>Message Content</b></em>' containment reference.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Content</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Message Content</em>' containment reference.
     * @see #setMessageContent(TableExpression)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getMessage_MessageContent()
     * @model containment="true" required="true"
     * @generated
     */
	TableExpression getMessageContent();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.Message#getMessageContent <em>Message Content</em>}' containment reference.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Message Content</em>' containment reference.
     * @see #getMessageContent()
     * @generated
     */
	void setMessageContent(TableExpression value);

} // Message
