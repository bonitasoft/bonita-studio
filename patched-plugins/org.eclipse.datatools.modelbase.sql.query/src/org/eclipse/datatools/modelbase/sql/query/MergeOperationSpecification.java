/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Merge Operation Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * SQL syntax:
 * <merge update specification>  |  <merge insert specification>
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.MergeOperationSpecification#getMergeStatement <em>Merge Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getMergeOperationSpecification()
 * @model
 * @generated
 */
public interface MergeOperationSpecification extends SQLQueryObject {
    /**
     * Returns the value of the '<em><b>Merge Statement</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getOperationSpecList <em>Operation Spec List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Merge Statement</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Merge Statement</em>' container reference.
     * @see #setMergeStatement(QueryMergeStatement)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getMergeOperationSpecification_MergeStatement()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getOperationSpecList
     * @model opposite="operationSpecList"
     * @generated
     */
    QueryMergeStatement getMergeStatement();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.MergeOperationSpecification#getMergeStatement <em>Merge Statement</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Merge Statement</em>' container reference.
     * @see #getMergeStatement()
     * @generated
     */
    void setMergeStatement(QueryMergeStatement value);

} // MergeOperationSpecification
