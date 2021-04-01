/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Query Nested</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryNested#getNestedQuery <em>Nested Query</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryNested()
 * @model
 * @generated
 */
public interface QueryNested extends QueryExpressionBody {
    /**
     * Returns the value of the '<em><b>Nested Query</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getQueryNest <em>Query Nest</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Nested Query</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Nested Query</em>' containment reference.
     * @see #setNestedQuery(QueryExpressionBody)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryNested_NestedQuery()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getQueryNest
     * @model opposite="queryNest" containment="true" required="true"
     * @generated
     */
    QueryExpressionBody getNestedQuery();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryNested#getNestedQuery <em>Nested Query</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Nested Query</em>' containment reference.
     * @see #getNestedQuery()
     * @generated
     */
    void setNestedQuery(QueryExpressionBody value);

} // QueryNested
