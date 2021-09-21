/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Merge On Condition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * SQL syntax:
 * <search condition>
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.MergeOnCondition#getMergeStatement <em>Merge Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.MergeOnCondition#getSearchCondition <em>Search Condition</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getMergeOnCondition()
 * @model
 * @generated
 */
public interface MergeOnCondition extends SQLQueryObject {
    /**
     * Returns the value of the '<em><b>Merge Statement</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getOnCondition <em>On Condition</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Merge Statement</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Merge Statement</em>' container reference.
     * @see #setMergeStatement(QueryMergeStatement)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getMergeOnCondition_MergeStatement()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryMergeStatement#getOnCondition
     * @model opposite="onCondition"
     * @generated
     */
    QueryMergeStatement getMergeStatement();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.MergeOnCondition#getMergeStatement <em>Merge Statement</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Merge Statement</em>' container reference.
     * @see #getMergeStatement()
     * @generated
     */
    void setMergeStatement(QueryMergeStatement value);

    /**
     * Returns the value of the '<em><b>Search Condition</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getMergeOnCondition <em>Merge On Condition</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Search Condition</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Search Condition</em>' containment reference.
     * @see #setSearchCondition(QuerySearchCondition)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getMergeOnCondition_SearchCondition()
     * @see org.eclipse.datatools.modelbase.sql.query.QuerySearchCondition#getMergeOnCondition
     * @model opposite="mergeOnCondition" containment="true" required="true"
     * @generated
     */
    QuerySearchCondition getSearchCondition();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.MergeOnCondition#getSearchCondition <em>Search Condition</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Search Condition</em>' containment reference.
     * @see #getSearchCondition()
     * @generated
     */
    void setSearchCondition(QuerySearchCondition value);

} // MergeOnCondition
