/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableJoinedOperator.java,v 1.4 2007/02/08 17:00:24 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>SQL Table Joined Operator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableJoinedOperator()
 * @model
 * @generated
 */
public final class TableJoinedOperator extends AbstractEnumerator {
	/**
     * The '<em><b>DEFAULT INNER</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DEFAULT_INNER_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int DEFAULT_INNER = 0;

	/**
     * The '<em><b>EXPLICIT INNER</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #EXPLICIT_INNER_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int EXPLICIT_INNER = 1;

	/**
     * The '<em><b>LEFT OUTER</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LEFT_OUTER_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int LEFT_OUTER = 2;

	/**
     * The '<em><b>RIGHT OUTER</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #RIGHT_OUTER_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int RIGHT_OUTER = 3;

	/**
     * The '<em><b>FULL OUTER</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #FULL_OUTER_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int FULL_OUTER = 4;

	/**
     * The '<em><b>DEFAULT INNER</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>DEFAULT INNER</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DEFAULT_INNER
     * @generated
     * @ordered
     */
    public static final TableJoinedOperator DEFAULT_INNER_LITERAL = new TableJoinedOperator(DEFAULT_INNER, "DEFAULT_INNER", "DEFAULT_INNER");

	/**
     * The '<em><b>EXPLICIT INNER</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>EXPLICIT INNER</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #EXPLICIT_INNER
     * @generated
     * @ordered
     */
    public static final TableJoinedOperator EXPLICIT_INNER_LITERAL = new TableJoinedOperator(EXPLICIT_INNER, "EXPLICIT_INNER", "EXPLICIT_INNER");

	/**
     * The '<em><b>LEFT OUTER</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>LEFT OUTER</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #LEFT_OUTER
     * @generated
     * @ordered
     */
    public static final TableJoinedOperator LEFT_OUTER_LITERAL = new TableJoinedOperator(LEFT_OUTER, "LEFT_OUTER", "LEFT_OUTER");

	/**
     * The '<em><b>RIGHT OUTER</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>RIGHT OUTER</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #RIGHT_OUTER
     * @generated
     * @ordered
     */
    public static final TableJoinedOperator RIGHT_OUTER_LITERAL = new TableJoinedOperator(RIGHT_OUTER, "RIGHT_OUTER", "RIGHT_OUTER");

	/**
     * The '<em><b>FULL OUTER</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>FULL OUTER</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #FULL_OUTER
     * @generated
     * @ordered
     */
    public static final TableJoinedOperator FULL_OUTER_LITERAL = new TableJoinedOperator(FULL_OUTER, "FULL_OUTER", "FULL_OUTER");

	/**
     * An array of all the '<em><b>Table Joined Operator</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final TableJoinedOperator[] VALUES_ARRAY =
		new TableJoinedOperator[] {
            DEFAULT_INNER_LITERAL,
            EXPLICIT_INNER_LITERAL,
            LEFT_OUTER_LITERAL,
            RIGHT_OUTER_LITERAL,
            FULL_OUTER_LITERAL,
        };

	/**
     * A public read-only list of all the '<em><b>Table Joined Operator</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>Table Joined Operator</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static TableJoinedOperator get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            TableJoinedOperator result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Table Joined Operator</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static TableJoinedOperator getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            TableJoinedOperator result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Table Joined Operator</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static TableJoinedOperator get(int value) {
        switch (value) {
            case DEFAULT_INNER: return DEFAULT_INNER_LITERAL;
            case EXPLICIT_INNER: return EXPLICIT_INNER_LITERAL;
            case LEFT_OUTER: return LEFT_OUTER_LITERAL;
            case RIGHT_OUTER: return RIGHT_OUTER_LITERAL;
            case FULL_OUTER: return FULL_OUTER_LITERAL;
        }
        return null;
    }

	/**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private TableJoinedOperator(int value, String name, String literal) {
        super(value, name, literal);
    }

} //SQLTableJoinedOperator
