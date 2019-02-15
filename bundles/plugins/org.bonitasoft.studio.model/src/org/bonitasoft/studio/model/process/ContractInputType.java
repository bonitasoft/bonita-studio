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
 * A representation of the literals of the enumeration '<em><b>Contract Input Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.bonitasoft.studio.model.process.ProcessPackage#getContractInputType()
 * @model
 * @generated
 */
public enum ContractInputType implements Enumerator {
	/**
     * The '<em><b>TEXT</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #TEXT_VALUE
     * @generated
     * @ordered
     */
	TEXT(0, "TEXT", "TEXT"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>INTEGER</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #INTEGER_VALUE
     * @generated
     * @ordered
     */
	INTEGER(1, "INTEGER", "INTEGER"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>DECIMAL</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #DECIMAL_VALUE
     * @generated
     * @ordered
     */
	DECIMAL(2, "DECIMAL", "DECIMAL"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>BOOLEAN</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #BOOLEAN_VALUE
     * @generated
     * @ordered
     */
	BOOLEAN(3, "BOOLEAN", "BOOLEAN"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>DATE</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #DATE_VALUE
     * @generated
     * @ordered
     */
	DATE(4, "DATE", "DATE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>COMPLEX</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #COMPLEX_VALUE
     * @generated
     * @ordered
     */
	COMPLEX(5, "COMPLEX", "COMPLEX"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>FILE</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #FILE_VALUE
     * @generated
     * @ordered
     */
	FILE(6, "FILE", "FILE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>LONG</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #LONG_VALUE
     * @generated
     * @ordered
     */
	LONG(7, "LONG", "LONG"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>LOCALDATE</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #LOCALDATE_VALUE
     * @generated
     * @ordered
     */
	LOCALDATE(8, "LOCALDATE", "LOCALDATE"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>LOCALDATETIME</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #LOCALDATETIME_VALUE
     * @generated
     * @ordered
     */
	LOCALDATETIME(9, "LOCALDATETIME", "LOCALDATETIME"), //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>OFFSETDATETIME</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #OFFSETDATETIME_VALUE
     * @generated
     * @ordered
     */
	OFFSETDATETIME(10, "OFFSETDATETIME", "OFFSETDATETIME"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
     * The '<em><b>TEXT</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>TEXT</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #TEXT
     * @model
     * @generated
     * @ordered
     */
	public static final int TEXT_VALUE = 0;

	/**
     * The '<em><b>INTEGER</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>INTEGER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #INTEGER
     * @model
     * @generated
     * @ordered
     */
	public static final int INTEGER_VALUE = 1;

	/**
     * The '<em><b>DECIMAL</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DECIMAL</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #DECIMAL
     * @model
     * @generated
     * @ordered
     */
	public static final int DECIMAL_VALUE = 2;

	/**
     * The '<em><b>BOOLEAN</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>BOOLEAN</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #BOOLEAN
     * @model
     * @generated
     * @ordered
     */
	public static final int BOOLEAN_VALUE = 3;

	/**
     * The '<em><b>DATE</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DATE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #DATE
     * @model
     * @generated
     * @ordered
     */
	public static final int DATE_VALUE = 4;

	/**
     * The '<em><b>COMPLEX</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>COMPLEX</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #COMPLEX
     * @model
     * @generated
     * @ordered
     */
	public static final int COMPLEX_VALUE = 5;

	/**
     * The '<em><b>FILE</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>FILE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #FILE
     * @model
     * @generated
     * @ordered
     */
	public static final int FILE_VALUE = 6;

	/**
     * The '<em><b>LONG</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LONG</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #LONG
     * @model
     * @generated
     * @ordered
     */
	public static final int LONG_VALUE = 7;

	/**
     * The '<em><b>LOCALDATE</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LOCALDATE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #LOCALDATE
     * @model
     * @generated
     * @ordered
     */
	public static final int LOCALDATE_VALUE = 8;

	/**
     * The '<em><b>LOCALDATETIME</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>LOCALDATETIME</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #LOCALDATETIME
     * @model
     * @generated
     * @ordered
     */
	public static final int LOCALDATETIME_VALUE = 9;

	/**
     * The '<em><b>OFFSETDATETIME</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>OFFSETDATETIME</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #OFFSETDATETIME
     * @model
     * @generated
     * @ordered
     */
	public static final int OFFSETDATETIME_VALUE = 10;

	/**
     * An array of all the '<em><b>Contract Input Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static final ContractInputType[] VALUES_ARRAY =
		new ContractInputType[] {
            TEXT,
            INTEGER,
            DECIMAL,
            BOOLEAN,
            DATE,
            COMPLEX,
            FILE,
            LONG,
            LOCALDATE,
            LOCALDATETIME,
            OFFSETDATETIME,
        };

	/**
     * A public read-only list of all the '<em><b>Contract Input Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final List<ContractInputType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>Contract Input Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param literal the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
	public static ContractInputType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ContractInputType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Contract Input Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param name the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
	public static ContractInputType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ContractInputType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Contract Input Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @param value the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
	public static ContractInputType get(int value) {
        switch (value) {
            case TEXT_VALUE: return TEXT;
            case INTEGER_VALUE: return INTEGER;
            case DECIMAL_VALUE: return DECIMAL;
            case BOOLEAN_VALUE: return BOOLEAN;
            case DATE_VALUE: return DATE;
            case COMPLEX_VALUE: return COMPLEX;
            case FILE_VALUE: return FILE;
            case LONG_VALUE: return LONG;
            case LOCALDATE_VALUE: return LOCALDATE;
            case LOCALDATETIME_VALUE: return LOCALDATETIME;
            case OFFSETDATETIME_VALUE: return OFFSETDATETIME;
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
	private ContractInputType(int value, String name, String literal) {
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
	
} //ContractInputType
