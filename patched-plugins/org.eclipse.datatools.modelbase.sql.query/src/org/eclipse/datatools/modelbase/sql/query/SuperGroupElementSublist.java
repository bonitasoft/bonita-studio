/**
 * <copyright>
 * </copyright>
 *
 * $Id: SuperGroupElementSublist.java,v 1.2 2005/12/22 22:18:50 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Super Group Element Sublist</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElementSublist#getSuperGroupElementExprList <em>Super Group Element Expr List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getSuperGroupElementSublist()
 * @model
 * @generated
 */
public interface SuperGroupElementSublist extends SuperGroupElement{
	/**
     * Returns the value of the '<em><b>Super Group Element Expr List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression#getSuperGroupElementSublist <em>Super Group Element Sublist</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Super Group Element Expr List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Super Group Element Expr List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getSuperGroupElementSublist_SuperGroupElementExprList()
     * @see org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression#getSuperGroupElementSublist
     * @model type="org.eclipse.datatools.modelbase.sql.query.SuperGroupElementExpression" opposite="superGroupElementSublist" containment="true" required="true"
     * @generated
     */
    EList getSuperGroupElementExprList();

} // SQLSuperGroupElementSublist
