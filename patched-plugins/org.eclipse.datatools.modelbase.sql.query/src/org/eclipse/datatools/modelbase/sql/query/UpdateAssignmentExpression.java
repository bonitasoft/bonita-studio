/**
 * <copyright>
 * </copyright>
 *
 * $Id: UpdateAssignmentExpression.java,v 1.3 2008/01/31 02:57:16 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Assignment Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression#getUpdateStatement <em>Update Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression#getTargetColumnList <em>Target Column List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression#getUpdateSource <em>Update Source</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression#getMergeUpdateSpec <em>Merge Update Spec</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getUpdateAssignmentExpression()
 * @model
 * @generated
 */
public interface UpdateAssignmentExpression extends SQLQueryObject{
	/**
     * Returns the value of the '<em><b>Update Statement</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement#getAssignmentClause <em>Assignment Clause</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Update Statement</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Update Statement</em>' container reference.
     * @see #setUpdateStatement(QueryUpdateStatement)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getUpdateAssignmentExpression_UpdateStatement()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryUpdateStatement#getAssignmentClause
     * @model opposite="assignmentClause"
     * @generated
     */
    QueryUpdateStatement getUpdateStatement();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression#getUpdateStatement <em>Update Statement</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Update Statement</em>' container reference.
     * @see #getUpdateStatement()
     * @generated
     */
    void setUpdateStatement(QueryUpdateStatement value);

	/**
     * Returns the value of the '<em><b>Target Column List</b></em>' reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getAssignmentExprTarget <em>Assignment Expr Target</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Target Column List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Target Column List</em>' reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getUpdateAssignmentExpression_TargetColumnList()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn#getAssignmentExprTarget
     * @model type="org.eclipse.datatools.modelbase.sql.query.ValueExpressionColumn" opposite="assignmentExprTarget" required="true"
     * @generated
     */
    EList getTargetColumnList();

	/**
     * Returns the value of the '<em><b>Update Source</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.UpdateSource#getUpdateAssignmentExpr <em>Update Assignment Expr</em>}'.
     * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Update Source</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
     * @return the value of the '<em>Update Source</em>' containment reference.
     * @see #setUpdateSource(UpdateSource)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getUpdateAssignmentExpression_UpdateSource()
     * @see org.eclipse.datatools.modelbase.sql.query.UpdateSource#getUpdateAssignmentExpr
     * @model opposite="updateAssignmentExpr" containment="true" required="true"
     * @generated
     */
  UpdateSource getUpdateSource();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression#getUpdateSource <em>Update Source</em>}' containment reference.
     * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
     * @param value the new value of the '<em>Update Source</em>' containment reference.
     * @see #getUpdateSource()
     * @generated
     */
  void setUpdateSource(UpdateSource value);

    /**
     * Returns the value of the '<em><b>Merge Update Spec</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.MergeUpdateSpecification#getAssignementExprList <em>Assignement Expr List</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Merge Update Spec</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Merge Update Spec</em>' container reference.
     * @see #setMergeUpdateSpec(MergeUpdateSpecification)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getUpdateAssignmentExpression_MergeUpdateSpec()
     * @see org.eclipse.datatools.modelbase.sql.query.MergeUpdateSpecification#getAssignementExprList
     * @model opposite="assignementExprList"
     * @generated
     */
    MergeUpdateSpecification getMergeUpdateSpec();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.UpdateAssignmentExpression#getMergeUpdateSpec <em>Merge Update Spec</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Merge Update Spec</em>' container reference.
     * @see #getMergeUpdateSpec()
     * @generated
     */
    void setMergeUpdateSpec(MergeUpdateSpecification value);

} // SQLAssignmentExpression
