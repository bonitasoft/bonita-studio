/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateBasic.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Predicate Basic</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateBasic#getComparisonOperator <em>Comparison Operator</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateBasic#getRightValueExpr <em>Right Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateBasic#getLeftValueExpr <em>Left Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateBasic()
 * @model
 * @generated
 */
public interface PredicateBasic extends Predicate{
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
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateBasic_ComparisonOperator()
     * @model
     * @generated
     */
    PredicateComparisonOperator getComparisonOperator();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBasic#getComparisonOperator <em>Comparison Operator</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Comparison Operator</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.PredicateComparisonOperator
     * @see #getComparisonOperator()
     * @generated
     */
    void setComparisonOperator(PredicateComparisonOperator value);

	/**
     * Returns the value of the '<em><b>Right Value Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBasicRight <em>Basic Right</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Right Value Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Right Value Expr</em>' containment reference.
     * @see #setRightValueExpr(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateBasic_RightValueExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBasicRight
     * @model opposite="basicRight" containment="true" required="true"
     * @generated
     */
    QueryValueExpression getRightValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBasic#getRightValueExpr <em>Right Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Right Value Expr</em>' containment reference.
     * @see #getRightValueExpr()
     * @generated
     */
    void setRightValueExpr(QueryValueExpression value);

	/**
     * Returns the value of the '<em><b>Left Value Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBasicLeft <em>Basic Left</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Left Value Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Left Value Expr</em>' containment reference.
     * @see #setLeftValueExpr(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateBasic_LeftValueExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getBasicLeft
     * @model opposite="basicLeft" containment="true" required="true"
     * @generated
     */
    QueryValueExpression getLeftValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateBasic#getLeftValueExpr <em>Left Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Left Value Expr</em>' containment reference.
     * @see #getLeftValueExpr()
     * @generated
     */
    void setLeftValueExpr(QueryValueExpression value);

} // SQLPredicateBasic
