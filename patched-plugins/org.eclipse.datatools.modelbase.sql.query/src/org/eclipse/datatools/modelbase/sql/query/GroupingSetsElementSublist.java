/**
 * <copyright>
 * </copyright>
 *
 * $Id: GroupingSetsElementSublist.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Grouping Sets Element Sublist</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementSublist#getGroupingSetsElementExprList <em>Grouping Sets Element Expr List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getGroupingSetsElementSublist()
 * @model
 * @generated
 */
public interface GroupingSetsElementSublist extends GroupingSetsElement{
	/**
     * Returns the value of the '<em><b>Grouping Sets Element Expr List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression#getGroupingSetsElementSublist <em>Grouping Sets Element Sublist</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Grouping Sets Element Expr List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Grouping Sets Element Expr List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getGroupingSetsElementSublist_GroupingSetsElementExprList()
     * @see org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression#getGroupingSetsElementSublist
     * @model type="org.eclipse.datatools.modelbase.sql.query.GroupingSetsElementExpression" opposite="groupingSetsElementSublist" containment="true" required="true"
     * @generated
     */
    EList getGroupingSetsElementExprList();

} // SQLGroupingSetsElementSublist
