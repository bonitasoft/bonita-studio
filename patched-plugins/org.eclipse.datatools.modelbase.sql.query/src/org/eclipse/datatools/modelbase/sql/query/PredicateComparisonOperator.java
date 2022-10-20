/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateComparisonOperator.java,v 1.4 2007/02/08 17:00:24 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>SQL Predicate Comparison Operator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateComparisonOperator()
 * @model
 * @generated
 */
public final class PredicateComparisonOperator extends AbstractEnumerator {
	/**
     * The '<em><b>EQUAL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #EQUAL_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int EQUAL = 0;

	/**
     * The '<em><b>NOT EQUAL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NOT_EQUAL_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int NOT_EQUAL = 1;

	/**
     * The '<em><b>LESS THAN</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LESS_THAN_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int LESS_THAN = 2;

	/**
     * The '<em><b>GREATER THAN</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #GREATER_THAN_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int GREATER_THAN = 3;

	/**
     * The '<em><b>LESS THAN OR EQUAL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #LESS_THAN_OR_EQUAL_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int LESS_THAN_OR_EQUAL = 4;

	/**
     * The '<em><b>GREATER THAN OR EQUAL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #GREATER_THAN_OR_EQUAL_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int GREATER_THAN_OR_EQUAL = 5;

	/**
     * The '<em><b>EQUAL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>EQUAL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #EQUAL
     * @generated
     * @ordered
     */
    public static final PredicateComparisonOperator EQUAL_LITERAL = new PredicateComparisonOperator(EQUAL, "EQUAL", "EQUAL");

	/**
     * The '<em><b>NOT EQUAL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>NOT EQUAL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #NOT_EQUAL
     * @generated
     * @ordered
     */
    public static final PredicateComparisonOperator NOT_EQUAL_LITERAL = new PredicateComparisonOperator(NOT_EQUAL, "NOT_EQUAL", "NOT_EQUAL");

	/**
     * The '<em><b>LESS THAN</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>LESS THAN</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #LESS_THAN
     * @generated
     * @ordered
     */
    public static final PredicateComparisonOperator LESS_THAN_LITERAL = new PredicateComparisonOperator(LESS_THAN, "LESS_THAN", "LESS_THAN");

	/**
     * The '<em><b>GREATER THAN</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>GREATER THAN</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #GREATER_THAN
     * @generated
     * @ordered
     */
    public static final PredicateComparisonOperator GREATER_THAN_LITERAL = new PredicateComparisonOperator(GREATER_THAN, "GREATER_THAN", "GREATER_THAN");

	/**
     * The '<em><b>LESS THAN OR EQUAL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>LESS THAN OR EQUAL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #LESS_THAN_OR_EQUAL
     * @generated
     * @ordered
     */
    public static final PredicateComparisonOperator LESS_THAN_OR_EQUAL_LITERAL = new PredicateComparisonOperator(LESS_THAN_OR_EQUAL, "LESS_THAN_OR_EQUAL", "LESS_THAN_OR_EQUAL");

	/**
     * The '<em><b>GREATER THAN OR EQUAL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>GREATER THAN OR EQUAL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #GREATER_THAN_OR_EQUAL
     * @generated
     * @ordered
     */
    public static final PredicateComparisonOperator GREATER_THAN_OR_EQUAL_LITERAL = new PredicateComparisonOperator(GREATER_THAN_OR_EQUAL, "GREATER_THAN_OR_EQUAL", "GREATER_THAN_OR_EQUAL");

	/**
     * An array of all the '<em><b>Predicate Comparison Operator</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final PredicateComparisonOperator[] VALUES_ARRAY =
		new PredicateComparisonOperator[] {
            EQUAL_LITERAL,
            NOT_EQUAL_LITERAL,
            LESS_THAN_LITERAL,
            GREATER_THAN_LITERAL,
            LESS_THAN_OR_EQUAL_LITERAL,
            GREATER_THAN_OR_EQUAL_LITERAL,
        };

	/**
     * A public read-only list of all the '<em><b>Predicate Comparison Operator</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>Predicate Comparison Operator</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static PredicateComparisonOperator get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            PredicateComparisonOperator result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Predicate Comparison Operator</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static PredicateComparisonOperator getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            PredicateComparisonOperator result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Predicate Comparison Operator</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static PredicateComparisonOperator get(int value) {
        switch (value) {
            case EQUAL: return EQUAL_LITERAL;
            case NOT_EQUAL: return NOT_EQUAL_LITERAL;
            case LESS_THAN: return LESS_THAN_LITERAL;
            case GREATER_THAN: return GREATER_THAN_LITERAL;
            case LESS_THAN_OR_EQUAL: return LESS_THAN_OR_EQUAL_LITERAL;
            case GREATER_THAN_OR_EQUAL: return GREATER_THAN_OR_EQUAL_LITERAL;
        }
        return null;
    }

	/**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private PredicateComparisonOperator(int value, String name, String literal) {
        super(value, name, literal);
    }

} //SQLPredicateComparisonOperator
