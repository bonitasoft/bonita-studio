/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryCombinedOperator.java,v 1.4 2007/02/08 17:00:24 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Combined Operator</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * UNION=1
 * UNION_ALL=2
 * INTERSECT=3
 * INTERSECT_ALL=4
 * EXCEPT=5
 * EXCEPT_ALL=6
 * <!-- end-model-doc -->
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryCombinedOperator()
 * @model
 * @generated
 */
public final class QueryCombinedOperator extends AbstractEnumerator {
	/**
     * The '<em><b>UNION</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #UNION_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int UNION = 0;

	/**
     * The '<em><b>UNION ALL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #UNION_ALL_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int UNION_ALL = 1;

	/**
     * The '<em><b>INTERSECT</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #INTERSECT_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int INTERSECT = 2;

	/**
     * The '<em><b>INTERSECT ALL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #INTERSECT_ALL_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int INTERSECT_ALL = 3;

	/**
     * The '<em><b>EXCEPT</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #EXCEPT_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int EXCEPT = 4;

	/**
     * The '<em><b>EXCEPT ALL</b></em>' literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #EXCEPT_ALL_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int EXCEPT_ALL = 5;

	/**
     * The '<em><b>UNION</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>UNION</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #UNION
     * @generated
     * @ordered
     */
    public static final QueryCombinedOperator UNION_LITERAL = new QueryCombinedOperator(UNION, "UNION", "UNION");

	/**
     * The '<em><b>UNION ALL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>UNION ALL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #UNION_ALL
     * @generated
     * @ordered
     */
    public static final QueryCombinedOperator UNION_ALL_LITERAL = new QueryCombinedOperator(UNION_ALL, "UNION_ALL", "UNION_ALL");

	/**
     * The '<em><b>INTERSECT</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>INTERSECT</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #INTERSECT
     * @generated
     * @ordered
     */
    public static final QueryCombinedOperator INTERSECT_LITERAL = new QueryCombinedOperator(INTERSECT, "INTERSECT", "INTERSECT");

	/**
     * The '<em><b>INTERSECT ALL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>INTERSECT ALL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #INTERSECT_ALL
     * @generated
     * @ordered
     */
    public static final QueryCombinedOperator INTERSECT_ALL_LITERAL = new QueryCombinedOperator(INTERSECT_ALL, "INTERSECT_ALL", "INTERSECT_ALL");

	/**
     * The '<em><b>EXCEPT</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>EXCEPT</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #EXCEPT
     * @generated
     * @ordered
     */
    public static final QueryCombinedOperator EXCEPT_LITERAL = new QueryCombinedOperator(EXCEPT, "EXCEPT", "EXCEPT");

	/**
     * The '<em><b>EXCEPT ALL</b></em>' literal object.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>EXCEPT ALL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @see #EXCEPT_ALL
     * @generated
     * @ordered
     */
    public static final QueryCombinedOperator EXCEPT_ALL_LITERAL = new QueryCombinedOperator(EXCEPT_ALL, "EXCEPT_ALL", "EXCEPT_ALL");

	/**
     * An array of all the '<em><b>Query Combined Operator</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private static final QueryCombinedOperator[] VALUES_ARRAY =
		new QueryCombinedOperator[] {
            UNION_LITERAL,
            UNION_ALL_LITERAL,
            INTERSECT_LITERAL,
            INTERSECT_ALL_LITERAL,
            EXCEPT_LITERAL,
            EXCEPT_ALL_LITERAL,
        };

	/**
     * A public read-only list of all the '<em><b>Query Combined Operator</b></em>' enumerators.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
     * Returns the '<em><b>Query Combined Operator</b></em>' literal with the specified literal value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static QueryCombinedOperator get(String literal) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            QueryCombinedOperator result = VALUES_ARRAY[i];
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Query Combined Operator</b></em>' literal with the specified name.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	public static QueryCombinedOperator getByName(String name) {
        for (int i = 0; i < VALUES_ARRAY.length; ++i) {
            QueryCombinedOperator result = VALUES_ARRAY[i];
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

	/**
     * Returns the '<em><b>Query Combined Operator</b></em>' literal with the specified integer value.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static QueryCombinedOperator get(int value) {
        switch (value) {
            case UNION: return UNION_LITERAL;
            case UNION_ALL: return UNION_ALL_LITERAL;
            case INTERSECT: return INTERSECT_LITERAL;
            case INTERSECT_ALL: return INTERSECT_ALL_LITERAL;
            case EXCEPT: return EXCEPT_LITERAL;
            case EXCEPT_ALL: return EXCEPT_ALL_LITERAL;
        }
        return null;
    }

	/**
     * Only this class can construct instances.
     * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * @generated
     */
	private QueryCombinedOperator(int value, String name, String literal) {
        super(value, name, literal);
    }

} //SQLQueryCombinedOperator
