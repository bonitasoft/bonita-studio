/**
 * <copyright>
 * </copyright>
 *
 * $Id: SuperGroupElementExpression.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Super Group Element Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression#getSuperGroupElementSublist <em>Super Group Element Sublist</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression#getGroupingExpr <em>Grouping Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getSuperGroupElementExpression()
 * @model
 * @generated
 */
public interface SuperGroupElementExpression extends SuperGroupElement{
	/**
     * Returns the value of the '<em><b>Super Group Element Sublist</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElementSublist#getSuperGroupElementExprList <em>Super Group Element Expr List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Super Group Element Sublist</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Super Group Element Sublist</em>' container reference.
     * @see #setSuperGroupElementSublist(SuperGroupElementSublist)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getSuperGroupElementExpression_SuperGroupElementSublist()
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupElementSublist#getSuperGroupElementExprList
     * @model opposite="superGroupElementExprList"
     * @generated
     */
    SuperGroupElementSublist getSuperGroupElementSublist();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression#getSuperGroupElementSublist <em>Super Group Element Sublist</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Super Group Element Sublist</em>' container reference.
     * @see #getSuperGroupElementSublist()
     * @generated
     */
    void setSuperGroupElementSublist(SuperGroupElementSublist value);

	/**
     * Returns the value of the '<em><b>Grouping Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.GroupingExpression#getSuperGroupElementExpr <em>Super Group Element Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Grouping Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Grouping Expr</em>' containment reference.
     * @see #setGroupingExpr(GroupingExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getSuperGroupElementExpression_GroupingExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingExpression#getSuperGroupElementExpr
     * @model opposite="superGroupElementExpr" containment="true" required="true"
     * @generated
     */
    GroupingExpression getGroupingExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression#getGroupingExpr <em>Grouping Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Grouping Expr</em>' containment reference.
     * @see #getGroupingExpr()
     * @generated
     */
    void setGroupingExpr(GroupingExpression value);

} // SQLSuperGroupElementExpression
