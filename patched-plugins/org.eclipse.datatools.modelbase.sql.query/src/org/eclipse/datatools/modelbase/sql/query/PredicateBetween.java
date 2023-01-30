/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateBetween.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Predicate Between</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateBetween#isNotBetween <em>Not Between</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateBetween#getLeftValueExpr <em>Left Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateBetween#getRightValueExpr1 <em>Right Value Expr1</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateBetween#getRightValueExpr2 <em>Right Value Expr2</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateBetween()
 * @model
 * @generated
 */
public interface PredicateBetween extends Predicate{
	/**
     * Returns the value of the '<em><b>Not Between</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Not Between</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Not Between</em>' attribute.
     * @see #setNotBetween(boolean)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateBetween_NotBetween()
     * @model
     * @generated
     */
    boolean isNotBetween();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBetween#isNotBetween <em>Not Between</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Not Between</em>' attribute.
     * @see #isNotBetween()
     * @generated
     */
    void setNotBetween(boolean value);

	/**
     * Returns the value of the '<em><b>Left Value Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBetweenLeft <em>Between Left</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Left Value Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Left Value Expr</em>' containment reference.
     * @see #setLeftValueExpr(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateBetween_LeftValueExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBetweenLeft
     * @model opposite="betweenLeft" containment="true" required="true"
     * @generated
     */
    QueryValueExpression getLeftValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBetween#getLeftValueExpr <em>Left Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Left Value Expr</em>' containment reference.
     * @see #getLeftValueExpr()
     * @generated
     */
    void setLeftValueExpr(QueryValueExpression value);

	/**
     * Returns the value of the '<em><b>Right Value Expr1</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBetweenRight1 <em>Between Right1</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Right Value Expr1</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Right Value Expr1</em>' containment reference.
     * @see #setRightValueExpr1(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateBetween_RightValueExpr1()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBetweenRight1
     * @model opposite="betweenRight1" containment="true" required="true"
     * @generated
     */
    QueryValueExpression getRightValueExpr1();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBetween#getRightValueExpr1 <em>Right Value Expr1</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Right Value Expr1</em>' containment reference.
     * @see #getRightValueExpr1()
     * @generated
     */
    void setRightValueExpr1(QueryValueExpression value);

	/**
     * Returns the value of the '<em><b>Right Value Expr2</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBetweenRight2 <em>Between Right2</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Right Value Expr2</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Right Value Expr2</em>' containment reference.
     * @see #setRightValueExpr2(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateBetween_RightValueExpr2()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBetweenRight2
     * @model opposite="betweenRight2" containment="true" required="true"
     * @generated
     */
    QueryValueExpression getRightValueExpr2();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBetween#getRightValueExpr2 <em>Right Value Expr2</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Right Value Expr2</em>' containment reference.
     * @see #getRightValueExpr2()
     * @generated
     */
    void setRightValueExpr2(QueryValueExpression value);

} // SQLPredicateBetween
