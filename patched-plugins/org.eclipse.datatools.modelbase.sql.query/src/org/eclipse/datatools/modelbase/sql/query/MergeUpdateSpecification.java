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
 * A representation of the model object '<em><b>Merge Update Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * SQL syntax:
 * WHEN MATCHED THEN UPDATE SET <set clause list>
 * 
 * where
 * <set clause list> is a list of assignement expressions, such as 
 *   description = sh.description
 * 
 * The assignment expression list is modelled as a list of UpdateAssignementExpression objects, which in the general case can have a query exrpession as the assignement source, but for Merge only simple assignment list is allowed.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.MergeUpdateSpecification#getAssignementExprList <em>Assignement Expr List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getMergeUpdateSpecification()
 * @model
 * @generated
 */
public interface MergeUpdateSpecification extends MergeOperationSpecification {
    /**
     * Returns the value of the '<em><b>Assignement Expr List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression#getMergeUpdateSpec <em>Merge Update Spec</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Assignement Expr List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Assignement Expr List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getMergeUpdateSpecification_AssignementExprList()
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression#getMergeUpdateSpec
     * @model type="org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression" opposite="mergeUpdateSpec" containment="true" required="true"
     * @generated
     */
    EList getAssignementExprList();

} // MergeUpdateSpecification
