/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Value Expression Row</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionRow#getValueExprList <em>Value Expr List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionRow()
 * @model
 * @generated
 */
public interface ValueExpressionRow extends QueryValueExpression {
    /**
     * Returns the value of the '<em><b>Value Expr List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprRow <em>Value Expr Row</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionRow_ValueExprList()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprRow
     * @model type="org.eclipse.datatools.modelbase.sql.query.QueryValueExpression" opposite="valueExprRow" containment="true"
     * @generated
     */
    EList getValueExprList();

} // ValueExpressionRow
