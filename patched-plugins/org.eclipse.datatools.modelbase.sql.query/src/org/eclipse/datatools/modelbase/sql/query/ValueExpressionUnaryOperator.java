/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionUnaryOperator.java,v 1.4 2007/02/08 17:00:23 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>SQL Value Expression Unary Operator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionUnaryOperator()
 * @model
 * @generated
 */
public final class ValueExpressionUnaryOperator extends AbstractEnumerator {
	/**
     * The '<em><b>NONE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #NONE_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int NONE = 0;

	/**
     * The '<em><b>PLUS</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #PLUS_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int PLUS = 1;

	/**
     * The '<em><b>MINUS</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MINUS_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int MINUS = 2;

	/**
     * The '<em><b>NONE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>NONE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #NONE
     * @generated
     * @ordered
     */
    public static final ValueExpressionUnaryOperator NONE_LITERAL = new ValueExpressionUnaryOperator(NONE, "NONE", "NONE");

	/**
     * The '<em><b>PLUS</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>PLUS</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #PLUS
     * @generated
     * @ordered
     */
    public static final ValueExpressionUnaryOperator PLUS_LITERAL = new ValueExpressionUnaryOperator(PLUS, "PLUS", "PLUS");

	/**
     * The '<em><b>MINUS</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>MINUS</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #MINUS
     * @generated
     * @ordered
     */
    public static final ValueExpressionUnaryOperator MINUS_LITERAL = new ValueExpressionUnaryOperator(MINUS, "MINUS", "MINUS");

	/**
     * An array of all the '<em><b>Value Expression Unary Operator</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final ValueExpressionUnaryOperator[] VALUES_ARRAY =
		new ValueExpressionUnaryOperator[] {
            NONE_LITERAL,
            PLUS_LITERAL,
            MINUS_LITERAL,
        };

	/**
     * A public read-only list of all the '<em><b>Value Expression Unary Operator</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>Value Expression Unary Operator</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ValueExpressionUnaryOperator get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ValueExpressionUnaryOperator result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Value Expression Unary Operator</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static ValueExpressionUnaryOperator getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ValueExpressionUnaryOperator result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Value Expression Unary Operator</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ValueExpressionUnaryOperator get(int value) {
        switch (value) {
            case NONE: return NONE_LITERAL;
            case PLUS: return PLUS_LITERAL;
            case MINUS: return MINUS_LITERAL;
        }
        return null;
    }

	/**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private ValueExpressionUnaryOperator(int value, String name, String literal) {
        super(value, name, literal);
    }

} //SQLValueExpressionUnaryOperator
