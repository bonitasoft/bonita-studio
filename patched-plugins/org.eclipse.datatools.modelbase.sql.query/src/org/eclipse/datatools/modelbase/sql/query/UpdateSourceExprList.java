/**
 * <copyright>
 * </copyright>
 *
 * $Id: UpdateSourceExprList.java,v 1.2 2005/12/22 22:18:50 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Update Source Expr List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.UpdateSourceExprList#getValueExprList <em>Value Expr List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getUpdateSourceExprList()
 * @model
 * @generated
 */
public interface UpdateSourceExprList extends UpdateSource{
	/**
     * Returns the value of the '<em><b>Value Expr List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getUpdateSourceExprList <em>Update Source Expr List</em>}'.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value Expr List</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getUpdateSourceExprList_ValueExprList()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getUpdateSourceExprList
     * @model type="org.eclipse.datatools.modelbase.sql.query.QueryValueExpression" opposite="updateSourceExprList" containment="true" required="true"
     * @generated
     */
  EList getValueExprList();

} // UpdateSourceExprList
