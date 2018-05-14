/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.process;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Entry Page Flow Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * 
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getEntryPageFlowType()
 * @model
 * @generated
 */
public enum EntryPageFlowType implements Enumerator {
    /**
     * The '<em><b>PAGEFLOW</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #PAGEFLOW_VALUE
     * @generated
     * @ordered
     */
    PAGEFLOW(0, "PAGEFLOW", "PAGEFLOW"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>SKIP</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #SKIP_VALUE
     * @generated
     * @ordered
     */
    SKIP(1, "SKIP", "SKIP"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>REDIRECT</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #REDIRECT_VALUE
     * @generated
     * @ordered
     */
    REDIRECT(2, "REDIRECT", "REDIRECT"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>PAGEFLOW</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>PAGEFLOW</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #PAGEFLOW
     * @model
     * @generated
     * @ordered
     */
    public static final int PAGEFLOW_VALUE = 0;

    /**
     * The '<em><b>SKIP</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>SKIP</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #SKIP
     * @model
     * @generated
     * @ordered
     */
    public static final int SKIP_VALUE = 1;

    /**
     * The '<em><b>REDIRECT</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>REDIRECT</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #REDIRECT
     * @model
     * @generated
     * @ordered
     */
    public static final int REDIRECT_VALUE = 2;

    /**
     * An array of all the '<em><b>Entry Page Flow Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private static final EntryPageFlowType[] VALUES_ARRAY = new EntryPageFlowType[] {
            PAGEFLOW,
            SKIP,
            REDIRECT,
    };

    /**
     * A public read-only list of all the '<em><b>Entry Page Flow Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final List<EntryPageFlowType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Entry Page Flow Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param literal the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static EntryPageFlowType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            EntryPageFlowType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Entry Page Flow Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param name the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static EntryPageFlowType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            EntryPageFlowType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Entry Page Flow Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @param value the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static EntryPageFlowType get(int value) {
        switch (value) {
            case PAGEFLOW_VALUE:
                return PAGEFLOW;
            case SKIP_VALUE:
                return SKIP;
            case REDIRECT_VALUE:
                return REDIRECT;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    private EntryPageFlowType(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public int getValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getLiteral() {
        return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string representation.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }

} //EntryPageFlowType
