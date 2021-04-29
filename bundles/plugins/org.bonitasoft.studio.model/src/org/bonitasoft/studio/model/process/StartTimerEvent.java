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

import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Start Timer Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.StartTimerEvent#getFrom <em>From</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.StartTimerEvent#getAt <em>At</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.StartTimerEvent#getMonth <em>Month</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.StartTimerEvent#getDay <em>Day</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.StartTimerEvent#getHours <em>Hours</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.StartTimerEvent#getDayNumber <em>Day Number</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.StartTimerEvent#getMinutes <em>Minutes</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.StartTimerEvent#getSeconds <em>Seconds</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.StartTimerEvent#getScriptType <em>Script Type</em>}</li>
 * </ul>
 *
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getStartTimerEvent()
 * @model
 * @generated
 */
public interface StartTimerEvent extends TimerEvent {
	/**
     * Returns the value of the '<em><b>From</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>From</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>From</em>' attribute.
     * @see #setFrom(Date)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getStartTimerEvent_From()
     * @model
     * @generated
     */
	Date getFrom();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.StartTimerEvent#getFrom <em>From</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>From</em>' attribute.
     * @see #getFrom()
     * @generated
     */
	void setFrom(Date value);

	/**
     * Returns the value of the '<em><b>At</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>At</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>At</em>' attribute.
     * @see #setAt(Date)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getStartTimerEvent_At()
     * @model
     * @generated
     */
	Date getAt();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.StartTimerEvent#getAt <em>At</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>At</em>' attribute.
     * @see #getAt()
     * @generated
     */
	void setAt(Date value);

	/**
     * Returns the value of the '<em><b>Month</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Month</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Month</em>' attribute.
     * @see #setMonth(int)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getStartTimerEvent_Month()
     * @model default="-1"
     * @generated
     */
	int getMonth();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.StartTimerEvent#getMonth <em>Month</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Month</em>' attribute.
     * @see #getMonth()
     * @generated
     */
	void setMonth(int value);

	/**
     * Returns the value of the '<em><b>Day</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Day</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Day</em>' attribute.
     * @see #setDay(int)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getStartTimerEvent_Day()
     * @model default="-1"
     * @generated
     */
	int getDay();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.StartTimerEvent#getDay <em>Day</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Day</em>' attribute.
     * @see #getDay()
     * @generated
     */
	void setDay(int value);

	/**
     * Returns the value of the '<em><b>Hours</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hours</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Hours</em>' attribute.
     * @see #setHours(int)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getStartTimerEvent_Hours()
     * @model default="-1"
     * @generated
     */
	int getHours();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.StartTimerEvent#getHours <em>Hours</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Hours</em>' attribute.
     * @see #getHours()
     * @generated
     */
	void setHours(int value);

	/**
     * Returns the value of the '<em><b>Day Number</b></em>' attribute.
     * The default value is <code>"-1"</code>.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Day Number</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Day Number</em>' attribute.
     * @see #setDayNumber(int)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getStartTimerEvent_DayNumber()
     * @model default="-1"
     * @generated
     */
	int getDayNumber();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.StartTimerEvent#getDayNumber <em>Day Number</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Day Number</em>' attribute.
     * @see #getDayNumber()
     * @generated
     */
	void setDayNumber(int value);

	/**
     * Returns the value of the '<em><b>Minutes</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Minutes</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Minutes</em>' attribute.
     * @see #setMinutes(int)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getStartTimerEvent_Minutes()
     * @model
     * @generated
     */
	int getMinutes();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.StartTimerEvent#getMinutes <em>Minutes</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Minutes</em>' attribute.
     * @see #getMinutes()
     * @generated
     */
	void setMinutes(int value);

	/**
     * Returns the value of the '<em><b>Seconds</b></em>' attribute.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Seconds</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Seconds</em>' attribute.
     * @see #setSeconds(int)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getStartTimerEvent_Seconds()
     * @model
     * @generated
     */
	int getSeconds();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.StartTimerEvent#getSeconds <em>Seconds</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Seconds</em>' attribute.
     * @see #getSeconds()
     * @generated
     */
	void setSeconds(int value);

	/**
     * Returns the value of the '<em><b>Script Type</b></em>' attribute.
     * The default value is <code>"GROOVY"</code>.
     * The literals are from the enumeration {@link org.bonitasoft.studio.model.process.StartTimerScriptType}.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Script Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @return the value of the '<em>Script Type</em>' attribute.
     * @see org.bonitasoft.studio.model.process.StartTimerScriptType
     * @see #setScriptType(StartTimerScriptType)
     * @see org.bonitasoft.studio.model.process.ProcessPackage#getStartTimerEvent_ScriptType()
     * @model default="GROOVY"
     * @generated
     */
	StartTimerScriptType getScriptType();

	/**
     * Sets the value of the '{@link org.bonitasoft.studio.model.process.StartTimerEvent#getScriptType <em>Script Type</em>}' attribute.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the new value of the '<em>Script Type</em>' attribute.
     * @see org.bonitasoft.studio.model.process.StartTimerScriptType
     * @see #getScriptType()
     * @generated
     */
	void setScriptType(StartTimerScriptType value);

} // StartTimerEvent
