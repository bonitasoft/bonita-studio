/**
 * <copyright>
 * </copyright>
 *
 * $Id: UpdateSource.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Update Source</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.UpdateSource#getUpdateAssignmentExpr <em>Update Assignment Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getUpdateSource()
 * @model
 * @generated
 */
public interface UpdateSource extends SQLQueryObject{
	/**
     * Returns the value of the '<em><b>Update Assignment Expr</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression#getUpdateSource <em>Update Source</em>}'.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Update Assignment Expr</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Update Assignment Expr</em>' container reference.
     * @see #setUpdateAssignmentExpr(UpdateAssignmentExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getUpdateSource_UpdateAssignmentExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression#getUpdateSource
     * @model opposite="updateSource"
     * @generated
     */
  UpdateAssignmentExpression getUpdateAssignmentExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.UpdateSource#getUpdateAssignmentExpr <em>Update Assignment Expr</em>}' container reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Update Assignment Expr</em>' container reference.
     * @see #getUpdateAssignmentExpr()
     * @generated
     */
  void setUpdateAssignmentExpr(UpdateAssignmentExpression value);

} // UpdateSource
