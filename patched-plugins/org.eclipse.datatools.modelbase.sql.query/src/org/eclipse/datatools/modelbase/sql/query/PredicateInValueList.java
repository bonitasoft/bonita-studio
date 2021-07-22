/**
 * <copyright>
 * </copyright>
 *
 * $Id: PredicateInValueList.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Predicate In Value List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueList#getValueExprList <em>Value Expr List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueList#getValueExpr <em>Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateInValueList()
 * @model
 * @generated
 */
public interface PredicateInValueList extends PredicateIn{
	/**
     * Returns the value of the '<em><b>Value Expr List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getInValueListRight <em>In Value List Right</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateInValueList_ValueExprList()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getInValueListRight
     * @model type="org.eclipse.datatools.modelbase.sql.query.QueryValueExpression" opposite="inValueListRight" containment="true" required="true"
     * @generated
     */
    EList getValueExprList();

	/**
     * Returns the value of the '<em><b>Value Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getInValueListLeft <em>In Value List Left</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr</em>' containment reference.
     * @see #setValueExpr(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getPredicateInValueList_ValueExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getInValueListLeft
     * @model opposite="inValueListLeft" containment="true" required="true"
     * @generated
     */
    QueryValueExpression getValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.PredicateInValueList#getValueExpr <em>Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr</em>' containment reference.
     * @see #getValueExpr()
     * @generated
     */
    void setValueExpr(QueryValueExpression value);

} // SQLPredicateInValueList
