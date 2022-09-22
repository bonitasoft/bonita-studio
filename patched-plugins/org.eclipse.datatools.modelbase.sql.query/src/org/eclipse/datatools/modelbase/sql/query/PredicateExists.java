/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateExists.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Predicate Exists</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateExists#getQueryExpr <em>Query Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateExists()
 * @model
 * @generated
 */
public interface PredicateExists extends Predicate{
	/**
     * Returns the value of the '<em><b>Query Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getPredicateExists <em>Predicate Exists</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query Expr</em>' containment reference.
     * @see #setQueryExpr(QueryExpressionBody)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateExists_QueryExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionBody#getPredicateExists
     * @model opposite="predicateExists" containment="true" required="true"
     * @generated
     */
    QueryExpressionBody getQueryExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateExists#getQueryExpr <em>Query Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query Expr</em>' containment reference.
     * @see #getQueryExpr()
     * @generated
     */
    void setQueryExpr(QueryExpressionBody value);

} // SQLPredicateExists
