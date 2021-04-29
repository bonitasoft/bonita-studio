/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionFunction.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;

import org.eclipse.emf.common.util.EList;

import org.eclipse.datatools.modelbase.sql.routines.Function;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Value Expression Function</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction#isSpecialRegister <em>Special Register</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction#isDistinct <em>Distinct</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction#isColumnFunction <em>Column Function</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction#getParameterList <em>Parameter List</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction#getFunction <em>Function</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionFunction()
 * @model
 * @generated
 */
public interface ValueExpressionFunction extends ValueExpressionAtomic{
	/**
     * Returns the value of the '<em><b>Special Register</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Special Register</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Special Register</em>' attribute.
     * @see #setSpecialRegister(boolean)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionFunction_SpecialRegister()
     * @model
     * @generated
     */
    boolean isSpecialRegister();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction#isSpecialRegister <em>Special Register</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Special Register</em>' attribute.
     * @see #isSpecialRegister()
     * @generated
     */
    void setSpecialRegister(boolean value);

	/**
     * Returns the value of the '<em><b>Distinct</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Distinct</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Distinct</em>' attribute.
     * @see #setDistinct(boolean)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionFunction_Distinct()
     * @model
     * @generated
     */
    boolean isDistinct();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction#isDistinct <em>Distinct</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Distinct</em>' attribute.
     * @see #isDistinct()
     * @generated
     */
    void setDistinct(boolean value);

	/**
     * Returns the value of the '<em><b>Column Function</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Column Function</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Column Function</em>' attribute.
     * @see #setColumnFunction(boolean)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionFunction_ColumnFunction()
     * @model
     * @generated
     */
    boolean isColumnFunction();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction#isColumnFunction <em>Column Function</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Column Function</em>' attribute.
     * @see #isColumnFunction()
     * @generated
     */
    void setColumnFunction(boolean value);

	/**
     * Returns the value of the '<em><b>Parameter List</b></em>' containment reference list.
     * The list contents are of type {@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression}.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprFunction <em>Value Expr Function</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Parameter List</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Parameter List</em>' containment reference list.
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionFunction_ParameterList()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprFunction
     * @model type="org.eclipse.datatools.modelbase.sql.query.QueryValueExpression" opposite="valueExprFunction" containment="true"
     * @generated
     */
    EList getParameterList();

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
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionFunction_Function()
     * @model required="true"
     * @generated
     */
    Function getFunction();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionFunction#getFunction <em>Function</em>}' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Function</em>' reference.
     * @see #getFunction()
     * @generated
     */
    void setFunction(Function value);

} // SQLValueExpressionFunction
