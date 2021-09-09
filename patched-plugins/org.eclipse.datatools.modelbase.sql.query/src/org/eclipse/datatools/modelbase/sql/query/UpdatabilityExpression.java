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
 * A representation of the model object '<em><b>Updatability Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression#getUpdatabilityType <em>Updatability Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression#getUpdateOfColumnList <em>Update Of Column List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression#getSelectStatement <em>Select Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getUpdatabilityExpression()
 * @model
 * @generated
 */
public interface UpdatabilityExpression extends SQLQueryObject {
    /**
     * Returns the value of the '<em><b>Updatability Type</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.query.UpdatabilityType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Updatability Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Updatability Type</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdatabilityType
     * @see #setUpdatabilityType(UpdatabilityType)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getUpdatabilityExpression_UpdatabilityType()
     * @model
     * @generated
     */
    UpdatabilityType getUpdatabilityType();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression#getUpdatabilityType <em>Updatability Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Updatability Type</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.UpdatabilityType
     * @see #getUpdatabilityType()
     * @generated
     */
    void setUpdatabilityType(UpdatabilityType value);

    /**
     * Returns the value of the '<em><b>Update Of Column List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.UpdateOfColumn}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.UpdateOfColumn#getUpdatabilityExpr <em>Updatability Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Update Of Column List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Update Of Column List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getUpdatabilityExpression_UpdateOfColumnList()
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateOfColumn#getUpdatabilityExpr
     * @model type="org.eclipse.datatools.modelbase.sql.query.UpdateOfColumn" opposite="updatabilityExpr" containment="true"
     * @generated
     */
    EList getUpdateOfColumnList();

    /**
     * Returns the value of the '<em><b>Select Statement</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement#getUpdatabilityExpr <em>Updatability Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Select Statement</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Select Statement</em>' container reference.
     * @see #setSelectStatement(QuerySelectStatement)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getUpdatabilityExpression_SelectStatement()
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySelectStatement#getUpdatabilityExpr
     * @model opposite="updatabilityExpr"
     * @generated
     */
    QuerySelectStatement getSelectStatement();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.UpdatabilityExpression#getSelectStatement <em>Select Statement</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Select Statement</em>' container reference.
     * @see #getSelectStatement()
     * @generated
     */
    void setSelectStatement(QuerySelectStatement value);

} // UpdatabilityExpression
