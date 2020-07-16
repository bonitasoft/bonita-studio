/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.datatools.modelbase.sql.statements.SQLControlStatement;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Call Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.CallStatement#getArgumentList <em>Argument List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.CallStatement#getProcedureRef <em>Procedure Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getCallStatement()
 * @model
 * @generated
 */
public interface CallStatement extends SQLQueryObject, SQLControlStatement {
    /**
     * Returns the value of the '<em><b>Argument List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getCallStatement <em>Call Statement</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Argument List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Argument List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getCallStatement_ArgumentList()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getCallStatement
     * @model type="org.eclipse.datatools.modelbase.sql.query.QueryValueExpression" opposite="callStatement" containment="true"
     * @generated
     */
    EList getArgumentList();

    /**
     * Returns the value of the '<em><b>Procedure Ref</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ProcedureReference#getCallStatement <em>Call Statement</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Procedure Ref</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Procedure Ref</em>' containment reference.
     * @see #setProcedureRef(ProcedureReference)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getCallStatement_ProcedureRef()
     * @see org.eclipse.datatools.modelbase.sql.query.ProcedureReference#getCallStatement
     * @model opposite="callStatement" containment="true" required="true"
     * @generated
     */
    ProcedureReference getProcedureRef();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.CallStatement#getProcedureRef <em>Procedure Ref</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Procedure Ref</em>' containment reference.
     * @see #getProcedureRef()
     * @generated
     */
    void setProcedureRef(ProcedureReference value);

} // CallStatement
