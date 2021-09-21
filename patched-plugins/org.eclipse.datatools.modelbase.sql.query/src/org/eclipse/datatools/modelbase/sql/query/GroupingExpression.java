/**
 * <copyright>
 * </copyright>
 *
 * $Id: GroupingExpression.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Grouping Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.GroupingExpression#getValueExpr <em>Value Expr</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.GroupingExpression#getSuperGroupElementExpr <em>Super Group Element Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getGroupingExpression()
 * @model
 * @generated
 */
public interface GroupingExpression extends Grouping{
	/**
     * Returns the value of the '<em><b>Value Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getGroupingExpr <em>Grouping Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr</em>' containment reference.
     * @see #setValueExpr(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getGroupingExpression_ValueExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getGroupingExpr
     * @model opposite="groupingExpr" containment="true" required="true"
     * @generated
     */
    QueryValueExpression getValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.GroupingExpression#getValueExpr <em>Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr</em>' containment reference.
     * @see #getValueExpr()
     * @generated
     */
    void setValueExpr(QueryValueExpression value);

	/**
     * Returns the value of the '<em><b>Super Group Element Expr</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression#getGroupingExpr <em>Grouping Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Super Group Element Expr</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Super Group Element Expr</em>' container reference.
     * @see #setSuperGroupElementExpr(SuperGroupElementExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getGroupingExpression_SuperGroupElementExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression#getGroupingExpr
     * @model opposite="groupingExpr"
     * @generated
     */
    SuperGroupElementExpression getSuperGroupElementExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.GroupingExpression#getSuperGroupElementExpr <em>Super Group Element Expr</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Super Group Element Expr</em>' container reference.
     * @see #getSuperGroupElementExpr()
     * @generated
     */
    void setSuperGroupElementExpr(SuperGroupElementExpression value);

} // SQLGroupingExpression
