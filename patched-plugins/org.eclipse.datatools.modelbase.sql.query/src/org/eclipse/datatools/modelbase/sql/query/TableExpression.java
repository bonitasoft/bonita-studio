/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableExpression.java,v 1.3 2008/01/31 02:57:16 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Table Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableExpression#getColumnList <em>Column List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableExpression#getTableCorrelation <em>Table Correlation</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableExpression#getResultTableAllColumns <em>Result Table All Columns</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableExpression#getValueExprColumns <em>Value Expr Columns</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableExpression#getMergeTargetTable <em>Merge Target Table</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableExpression()
 * @model abstract="true"
 * @generated
 */
public interface TableExpression extends TableReference{
	/**
     * Returns the value of the '<em><b>Column List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getParentTableExpr <em>Parent Table Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Column List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Column List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableExpression_ColumnList()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getParentTableExpr
     * @model type="org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn" opposite="parentTableExpr" containment="true"
     * @generated
     */
    EList getColumnList();

	/**
     * Returns the value of the '<em><b>Table Correlation</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.TableCorrelation#getTableExpr <em>Table Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Table Correlation</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Table Correlation</em>' containment reference.
     * @see #setTableCorrelation(TableCorrelation)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableExpression_TableCorrelation()
     * @see org.eclipse.datatools.modelbase.sql.query.TableCorrelation#getTableExpr
     * @model opposite="tableExpr" containment="true"
     * @generated
     */
    TableCorrelation getTableCorrelation();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.TableExpression#getTableCorrelation <em>Table Correlation</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Table Correlation</em>' containment reference.
     * @see #getTableCorrelation()
     * @generated
     */
    void setTableCorrelation(TableCorrelation value);

	/**
     * Returns the value of the '<em><b>Result Table All Columns</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns#getTableExpr <em>Table Expr</em>}'.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Result Table All Columns</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Result Table All Columns</em>' reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableExpression_ResultTableAllColumns()
     * @see org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns#getTableExpr
     * @model type="org.eclipse.datatools.modelbase.sql.query.ResultTableAllColumns" opposite="tableExpr"
     * @generated
     */
  EList getResultTableAllColumns();

	/**
     * Returns the value of the '<em><b>Value Expr Columns</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getTableExpr <em>Table Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr Columns</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr Columns</em>' reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableExpression_ValueExprColumns()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getTableExpr
     * @model type="org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn" opposite="tableExpr"
     * @generated
     */
    EList getValueExprColumns();

    /**
     * Returns the value of the '<em><b>Merge Target Table</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.MergeTargetTable#getTableExpr <em>Table Expr</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Merge Target Table</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Merge Target Table</em>' container reference.
     * @see #setMergeTargetTable(MergeTargetTable)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableExpression_MergeTargetTable()
     * @see org.eclipse.datatools.modelbase.sql.query.MergeTargetTable#getTableExpr
     * @model opposite="tableExpr"
     * @generated
     */
    MergeTargetTable getMergeTargetTable();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.TableExpression#getMergeTargetTable <em>Merge Target Table</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Merge Target Table</em>' container reference.
     * @see #getMergeTargetTable()
     * @generated
     */
    void setMergeTargetTable(MergeTargetTable value);

} // SQLTableExpression
