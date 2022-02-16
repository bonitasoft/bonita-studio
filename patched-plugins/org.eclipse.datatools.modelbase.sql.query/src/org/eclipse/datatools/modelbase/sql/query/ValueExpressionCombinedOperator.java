/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionCombinedOperator.java,v 1.4 2007/02/08 17:00:24 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>SQL Value Expression Combined Operator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionCombinedOperator()
 * @model
 * @generated
 */
public final class ValueExpressionCombinedOperator extends AbstractEnumerator {
	/**
     * The '<em><b>ADD</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ADD_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int ADD = 0;

	/**
     * The '<em><b>SUBTRACT</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SUBTRACT_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int SUBTRACT = 1;

	/**
     * The '<em><b>MULTIPLY</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MULTIPLY_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int MULTIPLY = 2;

	/**
     * The '<em><b>DIVIDE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DIVIDE_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int DIVIDE = 3;

	/**
     * The '<em><b>CONCATENATE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CONCATENATE_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int CONCATENATE = 4;

	/**
     * The '<em><b>ADD</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>ADD</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ADD
     * @generated
     * @ordered
     */
    public static final ValueExpressionCombinedOperator ADD_LITERAL = new ValueExpressionCombinedOperator(ADD, "ADD", "ADD");

	/**
     * The '<em><b>SUBTRACT</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>SUBTRACT</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #SUBTRACT
     * @generated
     * @ordered
     */
    public static final ValueExpressionCombinedOperator SUBTRACT_LITERAL = new ValueExpressionCombinedOperator(SUBTRACT, "SUBTRACT", "SUBTRACT");

	/**
     * The '<em><b>MULTIPLY</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>MULTIPLY</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #MULTIPLY
     * @generated
     * @ordered
     */
    public static final ValueExpressionCombinedOperator MULTIPLY_LITERAL = new ValueExpressionCombinedOperator(MULTIPLY, "MULTIPLY", "MULTIPLY");

	/**
     * The '<em><b>DIVIDE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>DIVIDE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DIVIDE
     * @generated
     * @ordered
     */
    public static final ValueExpressionCombinedOperator DIVIDE_LITERAL = new ValueExpressionCombinedOperator(DIVIDE, "DIVIDE", "DIVIDE");

	/**
     * The '<em><b>CONCATENATE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>CONCATENATE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CONCATENATE
     * @generated
     * @ordered
     */
    public static final ValueExpressionCombinedOperator CONCATENATE_LITERAL = new ValueExpressionCombinedOperator(CONCATENATE, "CONCATENATE", "CONCATENATE");

	/**
     * An array of all the '<em><b>Value Expression Combined Operator</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final ValueExpressionCombinedOperator[] VALUES_ARRAY =
		new ValueExpressionCombinedOperator[] {
            ADD_LITERAL,
            SUBTRACT_LITERAL,
            MULTIPLY_LITERAL,
            DIVIDE_LITERAL,
            CONCATENATE_LITERAL,
        };

	/**
     * A public read-only list of all the '<em><b>Value Expression Combined Operator</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>Value Expression Combined Operator</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ValueExpressionCombinedOperator get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ValueExpressionCombinedOperator result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Value Expression Combined Operator</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static ValueExpressionCombinedOperator getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ValueExpressionCombinedOperator result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Value Expression Combined Operator</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ValueExpressionCombinedOperator get(int value) {
        switch (value) {
            case ADD: return ADD_LITERAL;
            case SUBTRACT: return SUBTRACT_LITERAL;
            case MULTIPLY: return MULTIPLY_LITERAL;
            case DIVIDE: return DIVIDE_LITERAL;
            case CONCATENATE: return CONCATENATE_LITERAL;
        }
        return null;
    }

	/**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private ValueExpressionCombinedOperator(int value, String name, String literal) {
        super(value, name, literal);
    }

} //SQLValueExpressionCombinedOperator
