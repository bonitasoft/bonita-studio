/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Query Lateral</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A "lateral" table query is a query that appears in the FROM clause.  However it is semantically different from a regular FROM clause query in that the lateral table query can itself contain references to other tables defined before (that is, to the left of it) in the same FROM clause.  Regular FROM clause queries can only refer to tables defined at a higher level in the statement.  In ISO SQL a lateral table query expression is prefixed by the keyword LATERAL.  (The DB2 SQL dialects prefix the lateral query table with the keyword TABLE.)
 * 
 * For example, here is a statement containing a lateral table query expression:
 * 
 * SELECT d.dept_name, e.emp_name
 *   FROM dept d, LATERAL( 
 *     SELECT emp_id, emp_name
 *     FROM emp
 *     WHERE emp_dept = d.dept_id ) as e
 * 
 * The reference to d.dept_id is legal in a lateral table query, but would not be legal in a regular table query expression.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableQueryLateral#getQuery <em>Query</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableQueryLateral()
 * @model
 * @generated
 */
public interface TableQueryLateral extends TableExpression {
    /**
     * Returns the value of the '<em><b>Query</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query</em>' containment reference.
     * @see #setQuery(QueryExpressionBody)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableQueryLateral_Query()
     * @model containment="true" required="true"
     * @generated
     */
    QueryExpressionBody getQuery();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.TableQueryLateral#getQuery <em>Query</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query</em>' containment reference.
     * @see #getQuery()
     * @generated
     */
    void setQuery(QueryExpressionBody value);

} // TableQueryLateral
