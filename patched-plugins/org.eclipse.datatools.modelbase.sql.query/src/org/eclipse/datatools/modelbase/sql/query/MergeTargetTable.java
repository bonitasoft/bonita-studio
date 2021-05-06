/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Merge Target Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * SQL syntax:
 * <target table> [[AS] <merge correlation name>]
 * 
 * where:
 * <target table> is a simple or qualified table name.
 * <merge correlation name> is a simple identifier
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.MergeTargetTable#getMergeStatement <em>Merge Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.MergeTargetTable#getTableExpr <em>Table Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getMergeTargetTable()
 * @model
 * @generated
 */
public interface MergeTargetTable extends SQLQueryObject {
    /**
     * Returns the value of the '<em><b>Merge Statement</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getTargetTable <em>Target Table</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Merge Statement</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Merge Statement</em>' container reference.
     * @see #setMergeStatement(QueryMergeStatement)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getMergeTargetTable_MergeStatement()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getTargetTable
     * @model opposite="targetTable"
     * @generated
     */
    QueryMergeStatement getMergeStatement();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.MergeTargetTable#getMergeStatement <em>Merge Statement</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Merge Statement</em>' container reference.
     * @see #getMergeStatement()
     * @generated
     */
    void setMergeStatement(QueryMergeStatement value);

    /**
     * Returns the value of the '<em><b>Table Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.TableExpression#getMergeTargetTable <em>Merge Target Table</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Table Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Table Expr</em>' containment reference.
     * @see #setTableExpr(TableExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getMergeTargetTable_TableExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.TableExpression#getMergeTargetTable
     * @model opposite="mergeTargetTable" containment="true" required="true"
     * @generated
     */
    TableExpression getTableExpr();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.MergeTargetTable#getTableExpr <em>Table Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Table Expr</em>' containment reference.
     * @see #getTableExpr()
     * @generated
     */
    void setTableExpr(TableExpression value);

} // MergeTargetTable
