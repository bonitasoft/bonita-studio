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
 * A representation of the model object '<em><b>Merge Insert Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * SQL syntax:
 * WHEN NOT MATCHED THEN 
 * INSERT <target column list> 
 * VALUES <insert value list>
 * 
 * where
 * <target column list> is a simple list of column names
 * <insert value list> is a list of value expressions
 * 
 * Note that the MergeInsertSpecification object does not "own" the column objects in the target column list.  They are "owned" by the table object in the MergeTargetTable
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.MergeInsertSpecification#getTargetColumnList <em>Target Column List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.MergeInsertSpecification#getSourceValuesRow <em>Source Values Row</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getMergeInsertSpecification()
 * @model
 * @generated
 */
public interface MergeInsertSpecification extends MergeOperationSpecification {
    /**
     * Returns the value of the '<em><b>Target Column List</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getMergeInsertSpec <em>Merge Insert Spec</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Column List</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Column List</em>' reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getMergeInsertSpecification_TargetColumnList()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getMergeInsertSpec
     * @model type="org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn" opposite="mergeInsertSpec"
     * @generated
     */
    EList getTargetColumnList();

    /**
     * Returns the value of the '<em><b>Source Values Row</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Source Values Row</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Source Values Row</em>' containment reference.
     * @see #setSourceValuesRow(ValuesRow)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getMergeInsertSpecification_SourceValuesRow()
     * @model containment="true" required="true"
     * @generated
     */
    ValuesRow getSourceValuesRow();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.MergeInsertSpecification#getSourceValuesRow <em>Source Values Row</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Source Values Row</em>' containment reference.
     * @see #getSourceValuesRow()
     * @generated
     */
    void setSourceValuesRow(ValuesRow value);

} // MergeInsertSpecification
