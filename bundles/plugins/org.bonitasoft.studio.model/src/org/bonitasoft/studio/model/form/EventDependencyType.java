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
package org.bonitasoft.studio.model.form;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Event Dependency Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.model.form.FormPackage#getEventDependencyType()
 * @model
 * @generated
 */
public enum EventDependencyType implements Enumerator {
	/**
     * The '<em><b>On Value Change</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #ON_VALUE_CHANGE_VALUE
     * @generated
     * @ordered
     */
	ON_VALUE_CHANGE(0, "onValueChange", "onValueChange"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>On Change</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #ON_CHANGE_VALUE
     * @generated
     * @ordered
     */
	ON_CHANGE(1, "onChange", "onChange"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>On Blur</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #ON_BLUR_VALUE
     * @generated
     * @ordered
     */
	ON_BLUR(2, "onBlur", "onBlur"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>On Click</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #ON_CLICK_VALUE
     * @generated
     * @ordered
     */
	ON_CLICK(3, "onClick", "onClick"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>On Value Change</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>On Value Change</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #ON_VALUE_CHANGE
     * @model name="onValueChange"
     * @generated
     * @ordered
     */
	public static final int ON_VALUE_CHANGE_VALUE = 0;

	/**
     * The '<em><b>On Change</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>On Change</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #ON_CHANGE
     * @model name="onChange"
     * @generated
     * @ordered
     */
	public static final int ON_CHANGE_VALUE = 1;

	/**
     * The '<em><b>On Blur</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>On Blur</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #ON_BLUR
     * @model name="onBlur"
     * @generated
     * @ordered
     */
	public static final int ON_BLUR_VALUE = 2;

	/**
     * The '<em><b>On Click</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>On Click</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #ON_CLICK
     * @model name="onClick"
     * @generated
     * @ordered
     */
	public static final int ON_CLICK_VALUE = 3;

	/**
     * An array of all the '<em><b>Event Dependency Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static final EventDependencyType[] VALUES_ARRAY =
		new EventDependencyType[] {
            ON_VALUE_CHANGE,
            ON_CHANGE,
            ON_BLUR,
            ON_CLICK,
        };

	/**
     * A public read-only list of all the '<em><b>Event Dependency Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final List<EventDependencyType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>Event Dependency Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param literal the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
	public static EventDependencyType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            EventDependencyType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Event Dependency Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param name the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
	public static EventDependencyType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            EventDependencyType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Event Dependency Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
	public static EventDependencyType get(int value) {
        switch (value) {
            case ON_VALUE_CHANGE_VALUE: return ON_VALUE_CHANGE;
            case ON_CHANGE_VALUE: return ON_CHANGE;
            case ON_BLUR_VALUE: return ON_BLUR;
            case ON_CLICK_VALUE: return ON_CLICK;
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
	private EventDependencyType(int value, String name, String literal) {
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
	
} //EventDependencyType
