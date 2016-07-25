/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model.impl;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TExpression;
import org.omg.spec.bpmn.model.TResourceParameterBinding;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TResource Parameter Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TResourceParameterBindingImpl#getExpressionGroup <em>Expression Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TResourceParameterBindingImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TResourceParameterBindingImpl#getParameterRef <em>Parameter Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TResourceParameterBindingImpl extends TBaseElementImpl implements TResourceParameterBinding {
	/**
	 * The cached value of the '{@link #getExpressionGroup() <em>Expression Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExpressionGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap expressionGroup;

	/**
	 * The default value of the '{@link #getParameterRef() <em>Parameter Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterRef()
	 * @generated
	 * @ordered
	 */
	protected static final QName PARAMETER_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getParameterRef() <em>Parameter Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameterRef()
	 * @generated
	 * @ordered
	 */
	protected QName parameterRef = PARAMETER_REF_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TResourceParameterBindingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TRESOURCE_PARAMETER_BINDING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getExpressionGroup() {
		if (expressionGroup == null) {
			expressionGroup = new BasicFeatureMap(this, ModelPackage.TRESOURCE_PARAMETER_BINDING__EXPRESSION_GROUP);
		}
		return expressionGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TExpression getExpression() {
		return (TExpression)getExpressionGroup().get(ModelPackage.Literals.TRESOURCE_PARAMETER_BINDING__EXPRESSION, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExpression(TExpression newExpression, NotificationChain msgs) {
		return ((FeatureMap.Internal)getExpressionGroup()).basicAdd(ModelPackage.Literals.TRESOURCE_PARAMETER_BINDING__EXPRESSION, newExpression, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExpression(TExpression newExpression) {
		((FeatureMap.Internal)getExpressionGroup()).set(ModelPackage.Literals.TRESOURCE_PARAMETER_BINDING__EXPRESSION, newExpression);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QName getParameterRef() {
		return parameterRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParameterRef(QName newParameterRef) {
		QName oldParameterRef = parameterRef;
		parameterRef = newParameterRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TRESOURCE_PARAMETER_BINDING__PARAMETER_REF, oldParameterRef, parameterRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.TRESOURCE_PARAMETER_BINDING__EXPRESSION_GROUP:
				return ((InternalEList<?>)getExpressionGroup()).basicRemove(otherEnd, msgs);
			case ModelPackage.TRESOURCE_PARAMETER_BINDING__EXPRESSION:
				return basicSetExpression(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.TRESOURCE_PARAMETER_BINDING__EXPRESSION_GROUP:
				if (coreType) return getExpressionGroup();
				return ((FeatureMap.Internal)getExpressionGroup()).getWrapper();
			case ModelPackage.TRESOURCE_PARAMETER_BINDING__EXPRESSION:
				return getExpression();
			case ModelPackage.TRESOURCE_PARAMETER_BINDING__PARAMETER_REF:
				return getParameterRef();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.TRESOURCE_PARAMETER_BINDING__EXPRESSION_GROUP:
				((FeatureMap.Internal)getExpressionGroup()).set(newValue);
				return;
			case ModelPackage.TRESOURCE_PARAMETER_BINDING__EXPRESSION:
				setExpression((TExpression)newValue);
				return;
			case ModelPackage.TRESOURCE_PARAMETER_BINDING__PARAMETER_REF:
				setParameterRef((QName)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModelPackage.TRESOURCE_PARAMETER_BINDING__EXPRESSION_GROUP:
				getExpressionGroup().clear();
				return;
			case ModelPackage.TRESOURCE_PARAMETER_BINDING__EXPRESSION:
				setExpression((TExpression)null);
				return;
			case ModelPackage.TRESOURCE_PARAMETER_BINDING__PARAMETER_REF:
				setParameterRef(PARAMETER_REF_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelPackage.TRESOURCE_PARAMETER_BINDING__EXPRESSION_GROUP:
				return expressionGroup != null && !expressionGroup.isEmpty();
			case ModelPackage.TRESOURCE_PARAMETER_BINDING__EXPRESSION:
				return getExpression() != null;
			case ModelPackage.TRESOURCE_PARAMETER_BINDING__PARAMETER_REF:
				return PARAMETER_REF_EDEFAULT == null ? parameterRef != null : !PARAMETER_REF_EDEFAULT.equals(parameterRef);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (expressionGroup: ");
		result.append(expressionGroup);
		result.append(", parameterRef: ");
		result.append(parameterRef);
		result.append(')');
		return result.toString();
	}

} //TResourceParameterBindingImpl
