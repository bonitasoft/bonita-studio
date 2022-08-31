/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Updatability Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getUpdatabilityType()
 * @model
 * @generated
 */
public final class UpdatabilityType extends AbstractEnumerator {
    /**
     * The '<em><b>READ ONLY</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>READ ONLY</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #READ_ONLY_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int READ_ONLY = 0;

    /**
     * The '<em><b>UPDATE</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>UPDATE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #UPDATE_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int UPDATE = 1;

    /**
     * The '<em><b>READ ONLY</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #READ_ONLY
     * @generated
     * @ordered
     */
    public static final UpdatabilityType READ_ONLY_LITERAL = new UpdatabilityType(READ_ONLY, "READ_ONLY", "READ_ONLY");

    /**
     * The '<em><b>UPDATE</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #UPDATE
     * @generated
     * @ordered
     */
    public static final UpdatabilityType UPDATE_LITERAL = new UpdatabilityType(UPDATE, "UPDATE", "UPDATE");

    /**
     * An array of all the '<em><b>Updatability Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final UpdatabilityType[] VALUES_ARRAY =
        new UpdatabilityType[] {
            READ_ONLY_LITERAL,
            UPDATE_LITERAL,
        };

    /**
     * A public read-only list of all the '<em><b>Updatability Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

    /**
     * Returns the '<em><b>Updatability Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static UpdatabilityType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            UpdatabilityType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Updatability Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static UpdatabilityType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            UpdatabilityType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Updatability Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static UpdatabilityType get(int value) {
        switch (value) {
            case READ_ONLY: return READ_ONLY_LITERAL;
            case UPDATE: return UPDATE_LITERAL;
        }
        return null;
    }

    /**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private UpdatabilityType(int value, String name, String literal) {
        super(value, name, literal);
    }

} //UpdatabilityType
