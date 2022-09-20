/**
 * <copyright>
 * </copyright>
 *
 * $Id: Grouping.java,v 1.2 2005/12/22 22:18:50 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.Grouping#getGroupingSetsElementExpr <em>Grouping Sets Element Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getGrouping()
 * @model abstract="true"
 * @generated
 */
public interface Grouping extends GroupingSpecification{
	/**
     * Returns the value of the '<em><b>Grouping Sets Element Expr</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression#getGrouping <em>Grouping</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Grouping Sets Element Expr</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Grouping Sets Element Expr</em>' container reference.
     * @see #setGroupingSetsElementExpr(GroupingSetsElementExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getGrouping_GroupingSetsElementExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression#getGrouping
     * @model opposite="grouping"
     * @generated
     */
    GroupingSetsElementExpression getGroupingSetsElementExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.Grouping#getGroupingSetsElementExpr <em>Grouping Sets Element Expr</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Grouping Sets Element Expr</em>' container reference.
     * @see #getGroupingSetsElementExpr()
     * @generated
     */
    void setGroupingSetsElementExpr(GroupingSetsElementExpression value);

} // SQLGroup
