/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionLabeledDurationType.java,v 1.4 2007/02/08 17:00:23 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>SQL Value Expression Labeled Duration Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionLabeledDurationType()
 * @model
 * @generated
 */
public final class ValueExpressionLabeledDurationType extends AbstractEnumerator {
	/**
     * The '<em><b>YEARS</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #YEARS_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int YEARS = 0;

	/**
     * The '<em><b>MONTHS</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MONTHS_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int MONTHS = 1;

	/**
     * The '<em><b>DAYS</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #DAYS_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int DAYS = 2;

	/**
     * The '<em><b>HOURS</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #HOURS_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int HOURS = 3;

	/**
     * The '<em><b>MINUTES</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MINUTES_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int MINUTES = 4;

	/**
     * The '<em><b>SECONDS</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #SECONDS_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int SECONDS = 5;

	/**
     * The '<em><b>MICROSECONDS</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #MICROSECONDS_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int MICROSECONDS = 6;

	/**
     * The '<em><b>YEARS</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>YEARS</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #YEARS
     * @generated
     * @ordered
     */
    public static final ValueExpressionLabeledDurationType YEARS_LITERAL = new ValueExpressionLabeledDurationType(YEARS, "YEARS", "YEARS");

	/**
     * The '<em><b>MONTHS</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>MONTHS</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #MONTHS
     * @generated
     * @ordered
     */
    public static final ValueExpressionLabeledDurationType MONTHS_LITERAL = new ValueExpressionLabeledDurationType(MONTHS, "MONTHS", "MONTHS");

	/**
     * The '<em><b>DAYS</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>DAYS</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #DAYS
     * @generated
     * @ordered
     */
    public static final ValueExpressionLabeledDurationType DAYS_LITERAL = new ValueExpressionLabeledDurationType(DAYS, "DAYS", "DAYS");

	/**
     * The '<em><b>HOURS</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>HOURS</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #HOURS
     * @generated
     * @ordered
     */
    public static final ValueExpressionLabeledDurationType HOURS_LITERAL = new ValueExpressionLabeledDurationType(HOURS, "HOURS", "HOURS");

	/**
     * The '<em><b>MINUTES</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>MINUTES</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #MINUTES
     * @generated
     * @ordered
     */
    public static final ValueExpressionLabeledDurationType MINUTES_LITERAL = new ValueExpressionLabeledDurationType(MINUTES, "MINUTES", "MINUTES");

	/**
     * The '<em><b>SECONDS</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>SECONDS</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #SECONDS
     * @generated
     * @ordered
     */
    public static final ValueExpressionLabeledDurationType SECONDS_LITERAL = new ValueExpressionLabeledDurationType(SECONDS, "SECONDS", "SECONDS");

	/**
     * The '<em><b>MICROSECONDS</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>MICROSECONDS</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #MICROSECONDS
     * @generated
     * @ordered
     */
    public static final ValueExpressionLabeledDurationType MICROSECONDS_LITERAL = new ValueExpressionLabeledDurationType(MICROSECONDS, "MICROSECONDS", "MICROSECONDS");

	/**
     * An array of all the '<em><b>Value Expression Labeled Duration Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final ValueExpressionLabeledDurationType[] VALUES_ARRAY =
		new ValueExpressionLabeledDurationType[] {
            YEARS_LITERAL,
            MONTHS_LITERAL,
            DAYS_LITERAL,
            HOURS_LITERAL,
            MINUTES_LITERAL,
            SECONDS_LITERAL,
            MICROSECONDS_LITERAL,
        };

	/**
     * A public read-only list of all the '<em><b>Value Expression Labeled Duration Type</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>Value Expression Labeled Duration Type</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ValueExpressionLabeledDurationType get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ValueExpressionLabeledDurationType result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Value Expression Labeled Duration Type</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static ValueExpressionLabeledDurationType getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            ValueExpressionLabeledDurationType result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Value Expression Labeled Duration Type</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static ValueExpressionLabeledDurationType get(int value) {
        switch (value) {
            case YEARS: return YEARS_LITERAL;
            case MONTHS: return MONTHS_LITERAL;
            case DAYS: return DAYS_LITERAL;
            case HOURS: return HOURS_LITERAL;
            case MINUTES: return MINUTES_LITERAL;
            case SECONDS: return SECONDS_LITERAL;
            case MICROSECONDS: return MICROSECONDS_LITERAL;
        }
        return null;
    }

	/**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private ValueExpressionLabeledDurationType(int value, String name, String literal) {
        super(value, name, literal);
    }

} //SQLValueExpressionLabeledDurationType
