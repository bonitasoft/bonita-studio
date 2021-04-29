/**
 * <copyright>
 * </copyright>
 *
 * $Id: ValueExpressionLabeledDuration.java,v 1.2 2005/12/22 22:18:49 bpayton Exp $
 */
package org.eclipse.datatools.modelbase.sql.query;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>SQL Value Expression Labeled Duration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration#getLabeledDurationType <em>Labeled Duration Type</em>}</li>
 *   <li>{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration#getValueExpr <em>Value Expr</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionLabeledDuration()
 * @model
 * @generated
 */
public interface ValueExpressionLabeledDuration extends ValueExpressionAtomic{
	/**
     * Returns the value of the '<em><b>Labeled Duration Type</b></em>' attribute.
     * The literals are from the enumeration {@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDurationType}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Labeled Duration Type</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Labeled Duration Type</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDurationType
     * @see #setLabeledDurationType(ValueExpressionLabeledDurationType)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionLabeledDuration_LabeledDurationType()
     * @model
     * @generated
     */
    ValueExpressionLabeledDurationType getLabeledDurationType();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration#getLabeledDurationType <em>Labeled Duration Type</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Labeled Duration Type</em>' attribute.
     * @see org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDurationType
     * @see #getLabeledDurationType()
     * @generated
     */
    void setLabeledDurationType(ValueExpressionLabeledDurationType value);

	/**
     * Returns the value of the '<em><b>Value Expr</b></em>' containment reference.
     * It is bidirectional and its opposite is '{@link org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprLabeledDuration <em>Value Expr Labeled Duration</em>}'.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expr</em>' containment reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * @return the value of the '<em>Value Expr</em>' containment reference.
     * @see #setValueExpr(QueryValueExpression)
     * @see org.eclipse.datatools.modelbase.sql.query.SQLQueryModelPackage#getValueExpressionLabeledDuration_ValueExpr()
     * @see org.eclipse.datatools.modelbase.sql.query.QueryValueExpression#getValueExprLabeledDuration
     * @model opposite="valueExprLabeledDuration" containment="true" required="true"
     * @generated
     */
    QueryValueExpression getValueExpr();

	/**
     * Sets the value of the '{@link org.eclipse.datatools.modelbase.sql.query.ValueExpressionLabeledDuration#getValueExpr <em>Value Expr</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @param value the new value of the '<em>Value Expr</em>' containment reference.
     * @see #getValueExpr()
     * @generated
     */
    void setValueExpr(QueryValueExpression value);

} // SQLValueExpressionLabeledDuration
