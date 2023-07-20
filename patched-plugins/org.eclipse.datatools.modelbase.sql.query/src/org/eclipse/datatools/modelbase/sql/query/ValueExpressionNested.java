/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionNested.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Value Expression Nested</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested#getNestedValueExpr <em>Nested Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionNested()
 * @model
 * @generated
 */
public interface ValueExpressionNested extends QueryValueExpression{
	/**
     * Returns the value of the '<em><b>Nested Value Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getNest <em>Nest</em>}'.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Nested Value Expr</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Nested Value Expr</em>' containment reference.
     * @see #setNestedValueExpr(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionNested_NestedValueExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getNest
     * @model opposite="nest" containment="true" required="true"
     * @generated
     */
  QueryValueExpression getNestedValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionNested#getNestedValueExpr <em>Nested Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Nested Value Expr</em>' containment reference.
     * @see #getNestedValueExpr()
     * @generated
     */
  void setNestedValueExpr(QueryValueExpression value);

} // SQLValueExpressionNested
