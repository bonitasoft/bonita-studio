/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionCaseSimpleContent.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Value Expression Case Simple Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent#getValueExprCaseSimple <em>Value Expr Case Simple</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent#getWhenValueExpr <em>When Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent#getResultValueExpr <em>Result Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionCaseSimpleContent()
 * @model
 * @generated
 */
public interface ValueExpressionCaseSimpleContent extends SQLQueryObject{
	/**
     * Returns the value of the '<em><b>Value Expr Case Simple</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple#getContentList <em>Content List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr Case Simple</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr Case Simple</em>' container reference.
     * @see #setValueExprCaseSimple(ValueExpressionCaseSimple)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionCaseSimpleContent_ValueExprCaseSimple()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple#getContentList
     * @model opposite="contentList"
     * @generated
     */
    ValueExpressionCaseSimple getValueExprCaseSimple();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent#getValueExprCaseSimple <em>Value Expr Case Simple</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr Case Simple</em>' container reference.
     * @see #getValueExprCaseSimple()
     * @generated
     */
    void setValueExprCaseSimple(ValueExpressionCaseSimple value);

	/**
     * Returns the value of the '<em><b>When Value Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSimpleContentWhen <em>Value Expr Case Simple Content When</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>When Value Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>When Value Expr</em>' containment reference.
     * @see #setWhenValueExpr(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionCaseSimpleContent_WhenValueExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSimpleContentWhen
     * @model opposite="valueExprCaseSimpleContentWhen" containment="true" required="true"
     * @generated
     */
    QueryValueExpression getWhenValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent#getWhenValueExpr <em>When Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>When Value Expr</em>' containment reference.
     * @see #getWhenValueExpr()
     * @generated
     */
    void setWhenValueExpr(QueryValueExpression value);

	/**
     * Returns the value of the '<em><b>Result Value Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSimpleContentResult <em>Value Expr Case Simple Content Result</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Result Value Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Result Value Expr</em>' containment reference.
     * @see #setResultValueExpr(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionCaseSimpleContent_ResultValueExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSimpleContentResult
     * @model opposite="valueExprCaseSimpleContentResult" containment="true" required="true"
     * @generated
     */
    QueryValueExpression getResultValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent#getResultValueExpr <em>Result Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Result Value Expr</em>' containment reference.
     * @see #getResultValueExpr()
     * @generated
     */
    void setResultValueExpr(QueryValueExpression value);

} // SQLValueExpressionCaseSimpleContent
