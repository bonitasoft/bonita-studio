/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionCase.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Value Expression Case</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCase#getCaseElse <em>Case Else</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionCase()
 * @model abstract="true"
 * @generated
 */
public interface ValueExpressionCase extends ValueExpressionAtomic{
	/**
     * Returns the value of the '<em><b>Case Else</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse#getValueExprCase <em>Value Expr Case</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Case Else</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Case Else</em>' containment reference.
     * @see #setCaseElse(ValueExpressionCaseElse)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionCase_CaseElse()
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionCaseElse#getValueExprCase
     * @model opposite="valueExprCase" containment="true"
     * @generated
     */
    ValueExpressionCaseElse getCaseElse();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionCase#getCaseElse <em>Case Else</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Case Else</em>' containment reference.
     * @see #getCaseElse()
     * @generated
     */
    void setCaseElse(ValueExpressionCaseElse value);

} // SQLValueExpressionCase
