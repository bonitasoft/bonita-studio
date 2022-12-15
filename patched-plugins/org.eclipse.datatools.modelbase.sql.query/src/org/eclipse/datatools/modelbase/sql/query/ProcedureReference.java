/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.datatools.modelbase.sql.routines.Procedure;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Procedure Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ProcedureReference#getCallStatement <em>Call Statement</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ProcedureReference#getProcedure <em>Procedure</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getProcedureReference()
 * @model
 * @generated
 */
public interface ProcedureReference extends SQLQueryObject {
    /**
     * Returns the value of the '<em><b>Call Statement</b></em>' container reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.CallStatement#getProcedureRef <em>Procedure Ref</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Call Statement</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Call Statement</em>' container reference.
     * @see #setCallStatement(CallStatement)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getProcedureReference_CallStatement()
     * @see org.eclipse.datatools.modelbase.sql.query.CallStatement#getProcedureRef
     * @model opposite="procedureRef" required="true"
     * @generated
     */
    CallStatement getCallStatement();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ProcedureReference#getCallStatement <em>Call Statement</em>}' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Call Statement</em>' container reference.
     * @see #getCallStatement()
     * @generated
     */
    void setCallStatement(CallStatement value);

    /**
     * Returns the value of the '<em><b>Procedure</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Procedure</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Procedure</em>' reference.
     * @see #setProcedure(Procedure)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getProcedureReference_Procedure()
     * @model required="true"
     * @generated
     */
    Procedure getProcedure();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ProcedureReference#getProcedure <em>Procedure</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Procedure</em>' reference.
     * @see #getProcedure()
     * @generated
     */
    void setProcedure(Procedure value);

} // ProcedureReference
