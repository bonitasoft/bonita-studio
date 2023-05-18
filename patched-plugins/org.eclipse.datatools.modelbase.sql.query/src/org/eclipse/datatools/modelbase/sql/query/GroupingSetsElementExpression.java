/**
 * <copyright>
 * </copyright>
 *
 * $Id: GroupingSetsElementExpression.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Grouping Sets Element Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression#getGroupingSetsElementSublist <em>Grouping Sets Element Sublist</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression#getGrouping <em>Grouping</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getGroupingSetsElementExpression()
 * @model
 * @generated
 */
public interface GroupingSetsElementExpression extends GroupingSetsElement{
	/**
     * Returns the value of the '<em><b>Grouping Sets Element Sublist</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementSublist#getGroupingSetsElementExprList <em>Grouping Sets Element Expr List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Grouping Sets Element Sublist</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Grouping Sets Element Sublist</em>' container reference.
     * @see #setGroupingSetsElementSublist(GroupingSetsElementSublist)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getGroupingSetsElementExpression_GroupingSetsElementSublist()
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementSublist#getGroupingSetsElementExprList
     * @model opposite="groupingSetsElementExprList"
     * @generated
     */
    GroupingSetsElementSublist getGroupingSetsElementSublist();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression#getGroupingSetsElementSublist <em>Grouping Sets Element Sublist</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Grouping Sets Element Sublist</em>' container reference.
     * @see #getGroupingSetsElementSublist()
     * @generated
     */
    void setGroupingSetsElementSublist(GroupingSetsElementSublist value);

	/**
     * Returns the value of the '<em><b>Grouping</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.Grouping#getGroupingSetsElementExpr <em>Grouping Sets Element Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Grouping</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Grouping</em>' containment reference.
     * @see #setGrouping(Grouping)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getGroupingSetsElementExpression_Grouping()
     * @see org.eclipse.datatools.modelbase.sql.query.Grouping#getGroupingSetsElementExpr
     * @model opposite="groupingSetsElementExpr" containment="true" required="true"
     * @generated
     */
    Grouping getGrouping();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression#getGrouping <em>Grouping</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Grouping</em>' containment reference.
     * @see #getGrouping()
     * @generated
     */
    void setGrouping(Grouping value);

} // SQLGroupingSetsElementExpression
