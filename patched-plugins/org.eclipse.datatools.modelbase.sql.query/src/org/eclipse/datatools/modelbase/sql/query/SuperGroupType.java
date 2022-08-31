/**
 * <copyright>
 * </copyright>
 *
 * $Id: SuperGroupType.java,v 1.4 2007/02/08 17:00:24 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>SQL Super Group Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getSuperGroupType()
 * @model
 * @generated
 */
public final class SuperGroupType extends AbstractEnumerator {
	/**
     * The '<em><b>CUBE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #CUBE_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int CUBE = 0;

	/**
     * The '<em><b>GRANDTOTAL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #GRANDTOTAL_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int GRANDTOTAL = 1;

	/**
     * The '<em><b>ROLLUP</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #ROLLUP_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int ROLLUP = 2;

	/**
     * The '<em><b>CUBE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>CUBE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #CUBE
     * @generated
     * @ordered
     */
    public static final SuperGroupType CUBE_LITERAL = new SuperGroupType(CUBE, "CUBE", "CUBE");

	/**
     * The '<em><b>GRANDTOTAL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>GRANDTOTAL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #GRANDTOTAL
     * @generated
     * @ordered
     */
    public static final SuperGroupType GRANDTOTAL_LITERAL = new SuperGroupType(GRANDTOTAL, "GRANDTOTAL", "GRANDTOTAL");

	/**
     * The '<em><b>ROLLUP</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>ROLLUP</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #ROLLUP
     * @generated
     * @ordered
     */
    public static final SuperGroupType ROLLUP_LITERAL = new SuperGroupType(ROLLUP, "ROLLUP", "ROLLUP");

	/**
     * An array of all the '<em><b>Super Group Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final SuperGroupType[] VALUES_ARRAY =
		new SuperGroupType[] {
            CUBE_LITERAL,
            GRANDTOTAL_LITERAL,
            ROLLUP_LITERAL,
        };

	/**
     * A public read-only list of all the '<em><b>Super Group Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>Super Group Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static SuperGroupType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            SuperGroupType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Super Group Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static SuperGroupType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            SuperGroupType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Super Group Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static SuperGroupType get(int value) {
        switch (value) {
            case CUBE: return CUBE_LITERAL;
            case GRANDTOTAL: return GRANDTOTAL_LITERAL;
            case ROLLUP: return ROLLUP_LITERAL;
        }
        return null;
    }

	/**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private SuperGroupType(int value, String name, String literal) {
        super(value, name, literal);
    }

} //SQLSuperGroupType
