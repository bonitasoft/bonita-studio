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

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TCorrelationPropertyBinding;
import org.omg.spec.bpmn.model.TFormalExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TCorrelation Property Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCorrelationPropertyBindingImpl#getDataPath <em>Data Path</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TCorrelationPropertyBindingImpl#getCorrelationPropertyRef <em>Correlation Property Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TCorrelationPropertyBindingImpl extends TBaseElementImpl implements TCorrelationPropertyBinding {
	/**
	 * The cached value of the '{@link #getDataPath() <em>Data Path</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataPath()
	 * @generated
	 * @ordered
	 */
	protected TFormalExpression dataPath;

	/**
	 * The default value of the '{@link #getCorrelationPropertyRef() <em>Correlation Property Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorrelationPropertyRef()
	 * @generated
	 * @ordered
	 */
	protected static final QName CORRELATION_PROPERTY_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCorrelationPropertyRef() <em>Correlation Property Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorrelationPropertyRef()
	 * @generated
	 * @ordered
	 */
	protected QName correlationPropertyRef = CORRELATION_PROPERTY_REF_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TCorrelationPropertyBindingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TCORRELATION_PROPERTY_BINDING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFormalExpression getDataPath() {
		return dataPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataPath(TFormalExpression newDataPath, NotificationChain msgs) {
		TFormalExpression oldDataPath = dataPath;
		dataPath = newDataPath;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.TCORRELATION_PROPERTY_BINDING__DATA_PATH, oldDataPath, newDataPath);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataPath(TFormalExpression newDataPath) {
		if (newDataPath != dataPath) {
			NotificationChain msgs = null;
			if (dataPath != null)
				msgs = ((InternalEObject)dataPath).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TCORRELATION_PROPERTY_BINDING__DATA_PATH, null, msgs);
			if (newDataPath != null)
				msgs = ((InternalEObject)newDataPath).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TCORRELATION_PROPERTY_BINDING__DATA_PATH, null, msgs);
			msgs = basicSetDataPath(newDataPath, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TCORRELATION_PROPERTY_BINDING__DATA_PATH, newDataPath, newDataPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QName getCorrelationPropertyRef() {
		return correlationPropertyRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCorrelationPropertyRef(QName newCorrelationPropertyRef) {
		QName oldCorrelationPropertyRef = correlationPropertyRef;
		correlationPropertyRef = newCorrelationPropertyRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TCORRELATION_PROPERTY_BINDING__CORRELATION_PROPERTY_REF, oldCorrelationPropertyRef, correlationPropertyRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.TCORRELATION_PROPERTY_BINDING__DATA_PATH:
				return basicSetDataPath(null, msgs);
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
			case ModelPackage.TCORRELATION_PROPERTY_BINDING__DATA_PATH:
				return getDataPath();
			case ModelPackage.TCORRELATION_PROPERTY_BINDING__CORRELATION_PROPERTY_REF:
				return getCorrelationPropertyRef();
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
			case ModelPackage.TCORRELATION_PROPERTY_BINDING__DATA_PATH:
				setDataPath((TFormalExpression)newValue);
				return;
			case ModelPackage.TCORRELATION_PROPERTY_BINDING__CORRELATION_PROPERTY_REF:
				setCorrelationPropertyRef((QName)newValue);
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
			case ModelPackage.TCORRELATION_PROPERTY_BINDING__DATA_PATH:
				setDataPath((TFormalExpression)null);
				return;
			case ModelPackage.TCORRELATION_PROPERTY_BINDING__CORRELATION_PROPERTY_REF:
				setCorrelationPropertyRef(CORRELATION_PROPERTY_REF_EDEFAULT);
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
			case ModelPackage.TCORRELATION_PROPERTY_BINDING__DATA_PATH:
				return dataPath != null;
			case ModelPackage.TCORRELATION_PROPERTY_BINDING__CORRELATION_PROPERTY_REF:
				return CORRELATION_PROPERTY_REF_EDEFAULT == null ? correlationPropertyRef != null : !CORRELATION_PROPERTY_REF_EDEFAULT.equals(correlationPropertyRef);
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
		result.append(" (correlationPropertyRef: ");
		result.append(correlationPropertyRef);
		result.append(')');
		return result.toString();
	}

} //TCorrelationPropertyBindingImpl
