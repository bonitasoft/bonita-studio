/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateInValueRowSelect.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Predicate In Value Row Select</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect#getValueExprList <em>Value Expr List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect#getQueryExpr <em>Query Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateInValueRowSelect()
 * @model
 * @generated
 */
public interface PredicateInValueRowSelect extends PredicateIn{
	/**
     * Returns the value of the '<em><b>Value Expr List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getInValueRowSelectLeft <em>In Value Row Select Left</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateInValueRowSelect_ValueExprList()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getInValueRowSelectLeft
     * @model type="org.eclipse.datatools.modelbase.sql.query.QueryValueExpression" opposite="inValueRowSelectLeft" containment="true" required="true"
     * @generated
     */
    EList getValueExprList();

	/**
     * Returns the value of the '<em><b>Query Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getInValueRowSelectRight <em>In Value Row Select Right</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query Expr</em>' containment reference.
     * @see #setQueryExpr(QueryExpressionRoot)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateInValueRowSelect_QueryExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getInValueRowSelectRight
     * @model opposite="inValueRowSelectRight" containment="true" required="true"
     * @generated
     */
    QueryExpressionRoot getQueryExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueRowSelect#getQueryExpr <em>Query Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query Expr</em>' containment reference.
     * @see #getQueryExpr()
     * @generated
     */
    void setQueryExpr(QueryExpressionRoot value);

} // SQLPredicateInValueRowSelect
