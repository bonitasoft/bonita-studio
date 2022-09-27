/**
 * <copyright>
 * </copyright>
 *
 * $Id: NullOrderingType.java,v 1.4 2007/02/08 17:00:23 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Null Ordering Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getNullOrderingType()
 * @model
 * @generated
 */
public final class NullOrderingType extends AbstractEnumerator {
	/**
     * The '<em><b>NONE</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>NONE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #NONE_LITERAL
     * @model
     * @generated
     * @ordered
     */
	public static final int NONE = 0;

	/**
     * The '<em><b>NULLS FIRST</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>NULLS FIRST</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #NULLS_FIRST_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int NULLS_FIRST = 1;

	/**
     * The '<em><b>NULLS LAST</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>NULLS LAST</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #NULLS_LAST_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int NULLS_LAST = 2;

	/**
     * The '<em><b>NONE</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #NONE
     * @generated
     * @ordered
     */
	public static final NullOrderingType NONE_LITERAL = new NullOrderingType(NONE, "NONE", "NONE");

	/**
     * The '<em><b>NULLS FIRST</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NULLS_FIRST
     * @generated
     * @ordered
     */
    public static final NullOrderingType NULLS_FIRST_LITERAL = new NullOrderingType(NULLS_FIRST, "NULLS_FIRST", "NULLS_FIRST");

	/**
     * The '<em><b>NULLS LAST</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NULLS_LAST
     * @generated
     * @ordered
     */
    public static final NullOrderingType NULLS_LAST_LITERAL = new NullOrderingType(NULLS_LAST, "NULLS_LAST", "NULLS_LAST");

	/**
     * An array of all the '<em><b>Null Ordering Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static final NullOrderingType[] VALUES_ARRAY =
		new NullOrderingType[] {
            NONE_LITERAL,
            NULLS_FIRST_LITERAL,
            NULLS_LAST_LITERAL,
        };

	/**
     * A public read-only list of all the '<em><b>Null Ordering Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>Null Ordering Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static NullOrderingType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            NullOrderingType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Null Ordering Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static NullOrderingType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            NullOrderingType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Null Ordering Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static NullOrderingType get(int value) {
        switch (value) {
            case NONE: return NONE_LITERAL;
            case NULLS_FIRST: return NULLS_FIRST_LITERAL;
            case NULLS_LAST: return NULLS_LAST_LITERAL;
        }
        return null;
    }

	/**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private NullOrderingType(int value, String name, String literal) {
        super(value, name, literal);
    }

} //NullOrderingType
