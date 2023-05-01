/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateQuantifiedRowSelect.java,v 1.2 2005/12/22 22:18:50 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Predicate Quantified Row Select</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect#getQuantifiedType <em>Quantified Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect#getQueryExpr <em>Query Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect#getValueExprList <em>Value Expr List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateQuantifiedRowSelect()
 * @model
 * @generated
 */
public interface PredicateQuantifiedRowSelect extends PredicateQuantified{
	/**
     * Returns the value of the '<em><b>Quantified Type</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Quantified Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Quantified Type</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedType
     * @see #setQuantifiedType(PredicateQuantifiedType)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateQuantifiedRowSelect_QuantifiedType()
     * @model
     * @generated
     */
    PredicateQuantifiedType getQuantifiedType();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect#getQuantifiedType <em>Quantified Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Quantified Type</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedType
     * @see #getQuantifiedType()
     * @generated
     */
    void setQuantifiedType(PredicateQuantifiedType value);

	/**
     * Returns the value of the '<em><b>Query Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getQuantifiedRowSelectRight <em>Quantified Row Select Right</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query Expr</em>' containment reference.
     * @see #setQueryExpr(QueryExpressionRoot)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateQuantifiedRowSelect_QueryExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getQuantifiedRowSelectRight
     * @model opposite="quantifiedRowSelectRight" containment="true" required="true"
     * @generated
     */
    QueryExpressionRoot getQueryExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedRowSelect#getQueryExpr <em>Query Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query Expr</em>' containment reference.
     * @see #getQueryExpr()
     * @generated
     */
    void setQueryExpr(QueryExpressionRoot value);

	/**
     * Returns the value of the '<em><b>Value Expr List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getQuantifiedRowSelectLeft <em>Quantified Row Select Left</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateQuantifiedRowSelect_ValueExprList()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getQuantifiedRowSelectLeft
     * @model type="org.eclipse.datatools.modelbase.sql.query.QueryValueExpression" opposite="quantifiedRowSelectLeft" containment="true" required="true"
     * @generated
     */
    EList getValueExprList();

} // SQLPredicateQuantifiedRowSelect
