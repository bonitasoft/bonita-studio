/**
 * <copyright>
 * </copyright>
 *
 * $Id: QueryMergeStatement.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Merge Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * SQL syntax:
 * MERGE INTO <target table> [ [ AS] <merge correlation name> ]
 * USING <table reference>
 * ON <search condition> 
 * <merge operation specificaion list>
 * 
 * where:
 * <merge operation specification list> ::=
 *     <merge operation specification> [<merge operation specification ...]
 * <merge operation specification> ::=
 *     <merge update specification>  |  <merge insert specification>
 * 
 * Note: a non-syntactic rule is that the operation specification list can contain at most one update specification and one insert specification.
 * 
 * Example:
 * MERGE INTO inventory AS in
 * USING 
 *   (SELECT partno, description, count 
 *    FROM shipment
 *    WHERE shipment.partno IS NOT NULL) AS sh
 * ON (in.partno = sh.partno)
 * WHEN MATCHED THEN
 *    UPDATE SET
 *         description = sh.description,
 *         quantity = in.quantity + sh.count
 * WHEN NOT MATCHED THEN
 *     INSERT (partno, description, quantity)
 *     VALUES (sh.partno, sh.description, sh.count) 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getTargetTable <em>Target Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getSourceTable <em>Source Table</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getOnCondition <em>On Condition</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getOperationSpecList <em>Operation Spec List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryMergeStatement()
 * @model
 * @generated
 */
public interface QueryMergeStatement extends QueryChangeStatement{

    /**
     * Returns the value of the '<em><b>Target Table</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.MergeTargetTable#getMergeStatement <em>Merge Statement</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Table</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Table</em>' containment reference.
     * @see #setTargetTable(MergeTargetTable)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryMergeStatement_TargetTable()
     * @see org.eclipse.datatools.modelbase.sql.query.MergeTargetTable#getMergeStatement
     * @model opposite="mergeStatement" containment="true" required="true"
     * @generated
     */
    MergeTargetTable getTargetTable();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getTargetTable <em>Target Table</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Target Table</em>' containment reference.
     * @see #getTargetTable()
     * @generated
     */
    void setTargetTable(MergeTargetTable value);

    /**
     * Returns the value of the '<em><b>Source Table</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.MergeSourceTable#getMergeStatement <em>Merge Statement</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Table</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Table</em>' containment reference.
     * @see #setSourceTable(MergeSourceTable)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryMergeStatement_SourceTable()
     * @see org.eclipse.datatools.modelbase.sql.query.MergeSourceTable#getMergeStatement
     * @model opposite="mergeStatement" containment="true" required="true"
     * @generated
     */
    MergeSourceTable getSourceTable();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getSourceTable <em>Source Table</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Table</em>' containment reference.
     * @see #getSourceTable()
     * @generated
     */
    void setSourceTable(MergeSourceTable value);

    /**
     * Returns the value of the '<em><b>On Condition</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.MergeOnCondition#getMergeStatement <em>Merge Statement</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>On Condition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>On Condition</em>' containment reference.
     * @see #setOnCondition(MergeOnCondition)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryMergeStatement_OnCondition()
     * @see org.eclipse.datatools.modelbase.sql.query.MergeOnCondition#getMergeStatement
     * @model opposite="mergeStatement" containment="true" required="true"
     * @generated
     */
    MergeOnCondition getOnCondition();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getOnCondition <em>On Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>On Condition</em>' containment reference.
     * @see #getOnCondition()
     * @generated
     */
    void setOnCondition(MergeOnCondition value);

    /**
     * Returns the value of the '<em><b>Operation Spec List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.MergeOperationSpecification}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.MergeOperationSpecification#getMergeStatement <em>Merge Statement</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Operation Spec List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Operation Spec List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getQueryMergeStatement_OperationSpecList()
     * @see org.eclipse.datatools.modelbase.sql.query.MergeOperationSpecification#getMergeStatement
     * @model type="org.eclipse.datatools.modelbase.sql.query.MergeOperationSpecification" opposite="mergeStatement" containment="true" required="true"
     * @generated
     */
    EList getOperationSpecList();
} // SQLMergeStatement
