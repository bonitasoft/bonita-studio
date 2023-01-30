/**
 * <copyright>
 * </copyright>
 *
 * $Id: OrderingSpecType.java,v 1.4 2007/02/08 17:00:24 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Ordering Spec Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getOrderingSpecType()
 * @model
 * @generated
 */
public final class OrderingSpecType extends AbstractEnumerator {
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
     * The '<em><b>ASC</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ASC</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #ASC_LITERAL
     * @model
     * @generated
     * @ordered
     */
	public static final int ASC = 1;

	/**
     * The '<em><b>DESC</b></em>' literal value.
     * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>DESC</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
     * @see #DESC_LITERAL
     * @model
     * @generated
     * @ordered
     */
	public static final int DESC = 2;

	/**
     * The '<em><b>NONE</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #NONE
     * @generated
     * @ordered
     */
	public static final OrderingSpecType NONE_LITERAL = new OrderingSpecType(NONE, "NONE", "NONE");

	/**
     * The '<em><b>ASC</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #ASC
     * @generated
     * @ordered
     */
	public static final OrderingSpecType ASC_LITERAL = new OrderingSpecType(ASC, "ASC", "ASC");

	/**
     * The '<em><b>DESC</b></em>' literal object.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @see #DESC
     * @generated
     * @ordered
     */
	public static final OrderingSpecType DESC_LITERAL = new OrderingSpecType(DESC, "DESC", "DESC");

	/**
     * An array of all the '<em><b>Ordering Spec Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private static final OrderingSpecType[] VALUES_ARRAY =
		new OrderingSpecType[] {
            NONE_LITERAL,
            ASC_LITERAL,
            DESC_LITERAL,
        };

	/**
     * A public read-only list of all the '<em><b>Ordering Spec Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>Ordering Spec Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static OrderingSpecType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            OrderingSpecType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Ordering Spec Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static OrderingSpecType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            OrderingSpecType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Ordering Spec Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static OrderingSpecType get(int value) {
        switch (value) {
            case NONE: return NONE_LITERAL;
            case ASC: return ASC_LITERAL;
            case DESC: return DESC_LITERAL;
        }
        return null;
    }

	/**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private OrderingSpecType(int value, String name, String literal) {
        super(value, name, literal);
    }

} //OrderingSpecType
