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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Time Unit</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.model.simulation.SimulationPackage#getTimeUnit()
 * @model
 * @generated
 */
public enum TimeUnit implements Enumerator {
	/**
     * The '<em><b>Minute</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #MINUTE_VALUE
     * @generated
     * @ordered
     */
	MINUTE(0, "Minute", "Minute"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>Hour</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #HOUR_VALUE
     * @generated
     * @ordered
     */
	HOUR(1, "Hour", "Hour"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>Day</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #DAY_VALUE
     * @generated
     * @ordered
     */
	DAY(2, "Day", "Day"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>Week</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #WEEK_VALUE
     * @generated
     * @ordered
     */
	WEEK(3, "Week", "Week"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>Month</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #MONTH_VALUE
     * @generated
     * @ordered
     */
	MONTH(4, "Month", "Month"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>Year</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #YEAR_VALUE
     * @generated
     * @ordered
     */
	YEAR(5, "Year", "Year"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>Minute</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Minute</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #MINUTE
     * @model name="Minute"
     * @generated
     * @ordered
     */
	public static final int MINUTE_VALUE = 0;

	/**
     * The '<em><b>Hour</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Hour</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #HOUR
     * @model name="Hour"
     * @generated
     * @ordered
     */
	public static final int HOUR_VALUE = 1;

	/**
     * The '<em><b>Day</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Day</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #DAY
     * @model name="Day"
     * @generated
     * @ordered
     */
	public static final int DAY_VALUE = 2;

	/**
     * The '<em><b>Week</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Week</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #WEEK
     * @model name="Week"
     * @generated
     * @ordered
     */
	public static final int WEEK_VALUE = 3;

	/**
     * The '<em><b>Month</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Month</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #MONTH
     * @model name="Month"
     * @generated
     * @ordered
     */
	public static final int MONTH_VALUE = 4;

	/**
     * The '<em><b>Year</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Year</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #YEAR
     * @model name="Year"
     * @generated
     * @ordered
     */
	public static final int YEAR_VALUE = 5;

	/**
     * An array of all the '<em><b>Time Unit</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static final TimeUnit[] VALUES_ARRAY =
		new TimeUnit[] {
            MINUTE,
            HOUR,
            DAY,
            WEEK,
            MONTH,
            YEAR,
        };

	/**
     * A public read-only list of all the '<em><b>Time Unit</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final List<TimeUnit> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>Time Unit</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param literal the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
	public static TimeUnit get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            TimeUnit result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Time Unit</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param name the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
	public static TimeUnit getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            TimeUnit result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Time Unit</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
	public static TimeUnit get(int value) {
        switch (value) {
            case MINUTE_VALUE: return MINUTE;
            case HOUR_VALUE: return HOUR;
            case DAY_VALUE: return DAY;
            case WEEK_VALUE: return WEEK;
            case MONTH_VALUE: return MONTH;
            case YEAR_VALUE: return YEAR;
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
	private TimeUnit(int value, String name, String literal) {
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
	
} //TimeUnit
