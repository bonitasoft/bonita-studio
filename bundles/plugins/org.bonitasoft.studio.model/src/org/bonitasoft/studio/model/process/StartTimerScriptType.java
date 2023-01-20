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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Start Timer Script Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getStartTimerScriptType()
 * @model
 * @generated
 */
public enum StartTimerScriptType implements Enumerator {
	/**
     * The '<em><b>GROOVY</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #GROOVY_VALUE
     * @generated
     * @ordered
     */
	GROOVY(0, "GROOVY", "GROOVY"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>CUSTOM</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #CUSTOM_VALUE
     * @generated
     * @ordered
     */
	CUSTOM(1, "CUSTOM", "CUSTOM"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>YEARLY</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #YEARLY_VALUE
     * @generated
     * @ordered
     */
	YEARLY(2, "YEARLY", "YEARLY"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>YEARLY DAY OF MONTH</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #YEARLY_DAY_OF_MONTH_VALUE
     * @generated
     * @ordered
     */
	YEARLY_DAY_OF_MONTH(3, "YEARLY_DAY_OF_MONTH", "YEARLY_DAY_OF_MONTH"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>YEARLY DAY OF YEAR</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #YEARLY_DAY_OF_YEAR_VALUE
     * @generated
     * @ordered
     */
	YEARLY_DAY_OF_YEAR(4, "YEARLY_DAY_OF_YEAR", "YEARLY_DAY_OF_YEAR"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>MONTHLY</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #MONTHLY_VALUE
     * @generated
     * @ordered
     */
	MONTHLY(5, "MONTHLY", "MONTHLY"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>MONTHLY DAY NUMBER</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #MONTHLY_DAY_NUMBER_VALUE
     * @generated
     * @ordered
     */
	MONTHLY_DAY_NUMBER(6, "MONTHLY_DAY_NUMBER", "MONTHLY_DAY_NUMBER"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>MONTHLY DAY OF WEEK</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #MONTHLY_DAY_OF_WEEK_VALUE
     * @generated
     * @ordered
     */
	MONTHLY_DAY_OF_WEEK(7, "MONTHLY_DAY_OF_WEEK", "MONTHLY_DAY_OF_WEEK"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>WEEKLY</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #WEEKLY_VALUE
     * @generated
     * @ordered
     */
	WEEKLY(8, "WEEKLY", "WEEKLY"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>DAILY</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #DAILY_VALUE
     * @generated
     * @ordered
     */
	DAILY(9, "DAILY", "DAILY"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>HOURLY</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #HOURLY_VALUE
     * @generated
     * @ordered
     */
	HOURLY(10, "HOURLY", "HOURLY"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>MINUTELY</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #MINUTELY_VALUE
     * @generated
     * @ordered
     */
	MINUTELY(11, "MINUTELY", "MINUTELY"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>CONSTANT</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #CONSTANT_VALUE
     * @generated
     * @ordered
     */
	CONSTANT(12, "CONSTANT", "CONSTANT"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>GROOVY</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>GROOVY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #GROOVY
     * @model
     * @generated
     * @ordered
     */
	public static final int GROOVY_VALUE = 0;

	/**
     * The '<em><b>CUSTOM</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CUSTOM</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #CUSTOM
     * @model
     * @generated
     * @ordered
     */
	public static final int CUSTOM_VALUE = 1;

	/**
     * The '<em><b>YEARLY</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>YEARLY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #YEARLY
     * @model
     * @generated
     * @ordered
     */
	public static final int YEARLY_VALUE = 2;

	/**
     * The '<em><b>YEARLY DAY OF MONTH</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>YEARLY DAY OF MONTH</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #YEARLY_DAY_OF_MONTH
     * @model
     * @generated
     * @ordered
     */
	public static final int YEARLY_DAY_OF_MONTH_VALUE = 3;

	/**
     * The '<em><b>YEARLY DAY OF YEAR</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>YEARLY DAY OF YEAR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #YEARLY_DAY_OF_YEAR
     * @model
     * @generated
     * @ordered
     */
	public static final int YEARLY_DAY_OF_YEAR_VALUE = 4;

	/**
     * The '<em><b>MONTHLY</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MONTHLY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #MONTHLY
     * @model
     * @generated
     * @ordered
     */
	public static final int MONTHLY_VALUE = 5;

	/**
     * The '<em><b>MONTHLY DAY NUMBER</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MONTHLY DAY NUMBER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #MONTHLY_DAY_NUMBER
     * @model
     * @generated
     * @ordered
     */
	public static final int MONTHLY_DAY_NUMBER_VALUE = 6;

	/**
     * The '<em><b>MONTHLY DAY OF WEEK</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MONTHLY DAY OF WEEK</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #MONTHLY_DAY_OF_WEEK
     * @model
     * @generated
     * @ordered
     */
	public static final int MONTHLY_DAY_OF_WEEK_VALUE = 7;

	/**
     * The '<em><b>WEEKLY</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>WEEKLY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #WEEKLY
     * @model
     * @generated
     * @ordered
     */
	public static final int WEEKLY_VALUE = 8;

	/**
     * The '<em><b>DAILY</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DAILY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #DAILY
     * @model
     * @generated
     * @ordered
     */
	public static final int DAILY_VALUE = 9;

	/**
     * The '<em><b>HOURLY</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>HOURLY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #HOURLY
     * @model
     * @generated
     * @ordered
     */
	public static final int HOURLY_VALUE = 10;

	/**
     * The '<em><b>MINUTELY</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>MINUTELY</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #MINUTELY
     * @model
     * @generated
     * @ordered
     */
	public static final int MINUTELY_VALUE = 11;

	/**
     * The '<em><b>CONSTANT</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>CONSTANT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #CONSTANT
     * @model
     * @generated
     * @ordered
     */
	public static final int CONSTANT_VALUE = 12;

	/**
     * An array of all the '<em><b>Start Timer Script Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static final StartTimerScriptType[] VALUES_ARRAY =
		new StartTimerScriptType[] {
            GROOVY,
            CUSTOM,
            YEARLY,
            YEARLY_DAY_OF_MONTH,
            YEARLY_DAY_OF_YEAR,
            MONTHLY,
            MONTHLY_DAY_NUMBER,
            MONTHLY_DAY_OF_WEEK,
            WEEKLY,
            DAILY,
            HOURLY,
            MINUTELY,
            CONSTANT,
        };

	/**
     * A public read-only list of all the '<em><b>Start Timer Script Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final List<StartTimerScriptType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>Start Timer Script Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param literal the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
	public static StartTimerScriptType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            StartTimerScriptType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Start Timer Script Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param name the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
	public static StartTimerScriptType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            StartTimerScriptType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Start Timer Script Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
	public static StartTimerScriptType get(int value) {
        switch (value) {
            case GROOVY_VALUE: return GROOVY;
            case CUSTOM_VALUE: return CUSTOM;
            case YEARLY_VALUE: return YEARLY;
            case YEARLY_DAY_OF_MONTH_VALUE: return YEARLY_DAY_OF_MONTH;
            case YEARLY_DAY_OF_YEAR_VALUE: return YEARLY_DAY_OF_YEAR;
            case MONTHLY_VALUE: return MONTHLY;
            case MONTHLY_DAY_NUMBER_VALUE: return MONTHLY_DAY_NUMBER;
            case MONTHLY_DAY_OF_WEEK_VALUE: return MONTHLY_DAY_OF_WEEK;
            case WEEKLY_VALUE: return WEEKLY;
            case DAILY_VALUE: return DAILY;
            case HOURLY_VALUE: return HOURLY;
            case MINUTELY_VALUE: return MINUTELY;
            case CONSTANT_VALUE: return CONSTANT;
        }
        return null;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private final int value;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private final String name;

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private final String literal;

	/**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private StartTimerScriptType(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public int getValue() {
      return value;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getName() {
      return name;
    }

	/**
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String getLiteral() {
      return literal;
    }

	/**
     * Returns the literal value of the enumerator, which is its string representation.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	@Override
	public String toString() {
        return literal;
    }
	
} //StartTimerScriptType
