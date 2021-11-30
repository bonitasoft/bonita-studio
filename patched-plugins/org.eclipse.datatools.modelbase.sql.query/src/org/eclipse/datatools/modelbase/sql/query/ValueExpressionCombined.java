/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionCombined.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Value Expression Combined</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined#getCombinedOperator <em>Combined Operator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined#getLeftValueExpr <em>Left Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined#getRightValueExpr <em>Right Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionCombined()
 * @model
 * @generated
 */
public interface ValueExpressionCombined extends QueryValueExpression{
	/**
     * Returns the value of the '<em><b>Combined Operator</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Combined Operator</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Combined Operator</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator
     * @see #setCombinedOperator(ValueExpressionCombinedOperator)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionCombined_CombinedOperator()
     * @model
     * @generated
     */
    ValueExpressionCombinedOperator getCombinedOperator();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined#getCombinedOperator <em>Combined Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Combined Operator</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombinedOperator
     * @see #getCombinedOperator()
     * @generated
     */
    void setCombinedOperator(ValueExpressionCombinedOperator value);

	/**
     * Returns the value of the '<em><b>Left Value Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCombinedLeft <em>Value Expr Combined Left</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Left Value Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Left Value Expr</em>' containment reference.
     * @see #setLeftValueExpr(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionCombined_LeftValueExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCombinedLeft
     * @model opposite="valueExprCombinedLeft" containment="true" required="true"
     * @generated
     */
    QueryValueExpression getLeftValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined#getLeftValueExpr <em>Left Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Left Value Expr</em>' containment reference.
     * @see #getLeftValueExpr()
     * @generated
     */
    void setLeftValueExpr(QueryValueExpression value);

	/**
     * Returns the value of the '<em><b>Right Value Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCombinedRight <em>Value Expr Combined Right</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Right Value Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Right Value Expr</em>' containment reference.
     * @see #setRightValueExpr(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionCombined_RightValueExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCombinedRight
     * @model opposite="valueExprCombinedRight" containment="true" required="true"
     * @generated
     */
    QueryValueExpression getRightValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCombined#getRightValueExpr <em>Right Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Right Value Expr</em>' containment reference.
     * @see #getRightValueExpr()
     * @generated
     */
    void setRightValueExpr(QueryValueExpression value);

} // SQLValueExpressionCombined
