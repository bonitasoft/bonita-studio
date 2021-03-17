/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Update Of Column</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.UpdateOfColumn#getUpdatabilityExpr <em>Updatability Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getUpdateOfColumn()
 * @model
 * @generated
 */
public interface UpdateOfColumn extends SQLQueryObject {
    /**
     * Returns the value of the '<em><b>Updatability Expr</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression#getUpdateOfColumnList <em>Update Of Column List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Updatability Expr</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Updatability Expr</em>' container reference.
     * @see #setUpdatabilityExpr(UpdatabilityExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getUpdateOfColumn_UpdatabilityExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression#getUpdateOfColumnList
     * @model opposite="updateOfColumnList"
     * @generated
     */
    UpdatabilityExpression getUpdatabilityExpr();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.UpdateOfColumn#getUpdatabilityExpr <em>Updatability Expr</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Updatability Expr</em>' container reference.
     * @see #getUpdatabilityExpr()
     * @generated
     */
    void setUpdatabilityExpr(UpdatabilityExpression value);

} // UpdateOfColumn
