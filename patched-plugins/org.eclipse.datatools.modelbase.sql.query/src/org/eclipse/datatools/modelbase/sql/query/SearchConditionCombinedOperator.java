/**
 * <copyright>
 * </copyright>
 *
 * $Id: SearchConditionCombinedOperator.java,v 1.4 2007/02/08 17:00:24 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>SQL Search Condition Combined Operator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getSearchConditionCombinedOperator()
 * @model
 * @generated
 */
public final class SearchConditionCombinedOperator extends AbstractEnumerator {
	/**
     * The '<em><b>AND</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #AND_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int AND = 0;

	/**
     * The '<em><b>OR</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #OR_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int OR = 1;

	/**
     * The '<em><b>AND</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>AND</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #AND
     * @generated
     * @ordered
     */
    public static final SearchConditionCombinedOperator AND_LITERAL = new SearchConditionCombinedOperator(AND, "AND", "AND");

	/**
     * The '<em><b>OR</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>OR</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #OR
     * @generated
     * @ordered
     */
    public static final SearchConditionCombinedOperator OR_LITERAL = new SearchConditionCombinedOperator(OR, "OR", "OR");

	/**
     * An array of all the '<em><b>Search Condition Combined Operator</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final SearchConditionCombinedOperator[] VALUES_ARRAY =
		new SearchConditionCombinedOperator[] {
            AND_LITERAL,
            OR_LITERAL,
        };

	/**
     * A public read-only list of all the '<em><b>Search Condition Combined Operator</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>Search Condition Combined Operator</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static SearchConditionCombinedOperator get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            SearchConditionCombinedOperator result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Search Condition Combined Operator</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static SearchConditionCombinedOperator getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            SearchConditionCombinedOperator result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Search Condition Combined Operator</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static SearchConditionCombinedOperator get(int value) {
        switch (value) {
            case AND: return AND_LITERAL;
            case OR: return OR_LITERAL;
        }
        return null;
    }

	/**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private SearchConditionCombinedOperator(int value, String name, String literal) {
        super(value, name, literal);
    }

} //SQLSearchConditionCombinedOperator
