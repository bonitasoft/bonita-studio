/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateQuantifiedValueSelect.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Predicate Quantified Value Select</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect#getQuantifiedType <em>Quantified Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect#getComparisonOperator <em>Comparison Operator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect#getQueryExpr <em>Query Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect#getValueExpr <em>Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateQuantifiedValueSelect()
 * @model
 * @generated
 */
public interface PredicateQuantifiedValueSelect extends PredicateQuantified{
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
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateQuantifiedValueSelect_QuantifiedType()
     * @model
     * @generated
     */
    PredicateQuantifiedType getQuantifiedType();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect#getQuantifiedType <em>Quantified Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Quantified Type</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedType
     * @see #getQuantifiedType()
     * @generated
     */
    void setQuantifiedType(PredicateQuantifiedType value);

	/**
     * Returns the value of the '<em><b>Comparison Operator</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Comparison Operator</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Comparison Operator</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator
     * @see #setComparisonOperator(PredicateComparisonOperator)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateQuantifiedValueSelect_ComparisonOperator()
     * @model
     * @generated
     */
    PredicateComparisonOperator getComparisonOperator();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect#getComparisonOperator <em>Comparison Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Comparison Operator</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator
     * @see #getComparisonOperator()
     * @generated
     */
    void setComparisonOperator(PredicateComparisonOperator value);

	/**
     * Returns the value of the '<em><b>Query Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getQuantifiedValueSelectRight <em>Quantified Value Select Right</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query Expr</em>' containment reference.
     * @see #setQueryExpr(QueryExpressionRoot)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateQuantifiedValueSelect_QueryExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryExpressionRoot#getQuantifiedValueSelectRight
     * @model opposite="quantifiedValueSelectRight" containment="true" required="true"
     * @generated
     */
    QueryExpressionRoot getQueryExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect#getQueryExpr <em>Query Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Query Expr</em>' containment reference.
     * @see #getQueryExpr()
     * @generated
     */
    void setQueryExpr(QueryExpressionRoot value);

	/**
     * Returns the value of the '<em><b>Value Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getQuantifiedValueSelectLeft <em>Quantified Value Select Left</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr</em>' containment reference.
     * @see #setValueExpr(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateQuantifiedValueSelect_ValueExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getQuantifiedValueSelectLeft
     * @model opposite="quantifiedValueSelectLeft" containment="true" required="true"
     * @generated
     */
    QueryValueExpression getValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateQuantifiedValueSelect#getValueExpr <em>Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr</em>' containment reference.
     * @see #getValueExpr()
     * @generated
     */
    void setValueExpr(QueryValueExpression value);

} // SQLPredicateQuantifiedValueSelect
