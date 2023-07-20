/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionCaseSimple.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Value Expression Case Simple</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple#getContentList <em>Content List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple#getValueExpr <em>Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionCaseSimple()
 * @model
 * @generated
 */
public interface ValueExpressionCaseSimple extends ValueExpressionCase{
	/**
     * Returns the value of the '<em><b>Content List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent#getValueExprCaseSimple <em>Value Expr Case Simple</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Content List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Content List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionCaseSimple_ContentList()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent#getValueExprCaseSimple
     * @model type="org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimpleContent" opposite="valueExprCaseSimple" containment="true" required="true"
     * @generated
     */
    EList getContentList();

	/**
     * Returns the value of the '<em><b>Value Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSimple <em>Value Expr Case Simple</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr</em>' containment reference.
     * @see #setValueExpr(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionCaseSimple_ValueExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprCaseSimple
     * @model opposite="valueExprCaseSimple" containment="true" required="true"
     * @generated
     */
    QueryValueExpression getValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseSimple#getValueExpr <em>Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr</em>' containment reference.
     * @see #getValueExpr()
     * @generated
     */
    void setValueExpr(QueryValueExpression value);

} // SQLValueExpressionCaseSimple
