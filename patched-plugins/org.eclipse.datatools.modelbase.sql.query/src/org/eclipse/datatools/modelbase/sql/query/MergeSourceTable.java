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
 * A representation of the model object '<em><b>Merge Source Table</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * SQL syntax:
 * <table reference>
 * 
 * The source table reference can be a table name, a query (SELECT expression), a joined table, a table function...
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.MergeSourceTable#getQueryMergeStatement <em>Query Merge Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.MergeSourceTable#getMergeStatement <em>Merge Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.MergeSourceTable#getTableRef <em>Table Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getMergeSourceTable()
 * @model
 * @generated
 */
public interface MergeSourceTable extends SQLQueryObject {
    /**
     * Returns the value of the '<em><b>Query Merge Statement</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Query Merge Statement</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Query Merge Statement</em>' reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getMergeSourceTable_QueryMergeStatement()
     * @model type="org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement"
     * @generated
     */
    EList getQueryMergeStatement();

    /**
     * Returns the value of the '<em><b>Merge Statement</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getSourceTable <em>Source Table</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Merge Statement</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Merge Statement</em>' container reference.
     * @see #setMergeStatement(QueryMergeStatement)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getMergeSourceTable_MergeStatement()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getSourceTable
     * @model opposite="sourceTable"
     * @generated
     */
    QueryMergeStatement getMergeStatement();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.MergeSourceTable#getMergeStatement <em>Merge Statement</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Merge Statement</em>' container reference.
     * @see #getMergeStatement()
     * @generated
     */
    void setMergeStatement(QueryMergeStatement value);

    /**
     * Returns the value of the '<em><b>Table Ref</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.TableReference#getMergeSourceTable <em>Merge Source Table</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Table Ref</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Table Ref</em>' containment reference.
     * @see #setTableRef(TableReference)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getMergeSourceTable_TableRef()
     * @see org.eclipse.datatools.modelbase.sql.query.TableReference#getMergeSourceTable
     * @model opposite="mergeSourceTable" containment="true" required="true"
     * @generated
     */
    TableReference getTableRef();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.MergeSourceTable#getTableRef <em>Table Ref</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Table Ref</em>' containment reference.
     * @see #getTableRef()
     * @generated
     */
    void setTableRef(TableReference value);

} // MergeSourceTable
