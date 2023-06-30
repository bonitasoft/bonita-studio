/**
 * <copyright>
 * </copyright>
 *
 * $Id: TableFunction.java,v 1.2 2005/12/22 22:18:50 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.datatools.modelbase.sql.routines.Function;
import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Table Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableFunction#getFunction <em>Function</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.TableFunction#getParameterList <em>Parameter List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableFunction()
 * @model
 * @generated
 */
public interface TableFunction extends TableExpression{

    /**
     * Returns the value of the '<em><b>Function</b></em>' reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Function</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Function</em>' reference.
     * @see #setFunction(Function)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableFunction_Function()
     * @model required="true"
     * @generated
     */
    Function getFunction();

    /**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.TableFunction#getFunction <em>Function</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Function</em>' reference.
     * @see #getFunction()
     * @generated
     */
    void setFunction(Function value);

    /**
     * Returns the value of the '<em><b>Parameter List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getTableFunction <em>Table Function</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parameter List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parameter List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getTableFunction_ParameterList()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getTableFunction
     * @model type="org.eclipse.datatools.modelbase.sql.query.QueryValueExpression" opposite="tableFunction" containment="true"
     * @generated
     */
    EList getParameterList();
} // SQLTableFunction
