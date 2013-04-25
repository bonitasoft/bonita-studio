/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.definition.impl;

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.studio.connector.model.definition.WidgetComponent;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Widget Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.impl.WidgetComponentImpl#getInputName <em>Input Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WidgetComponentImpl extends ComponentImpl implements WidgetComponent {
    /**
	 * The default value of the '{@link #getInputName() <em>Input Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getInputName()
	 * @generated
	 * @ordered
	 */
    protected static final String INPUT_NAME_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getInputName() <em>Input Name</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getInputName()
	 * @generated
	 * @ordered
	 */
    protected String inputName = INPUT_NAME_EDEFAULT;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected WidgetComponentImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return ConnectorDefinitionPackage.Literals.WIDGET_COMPONENT;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getInputName() {
		return inputName;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setInputName(String newInputName) {
		String oldInputName = inputName;
		inputName = newInputName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorDefinitionPackage.WIDGET_COMPONENT__INPUT_NAME, oldInputName, inputName));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConnectorDefinitionPackage.WIDGET_COMPONENT__INPUT_NAME:
				return getInputName();
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
			case ConnectorDefinitionPackage.WIDGET_COMPONENT__INPUT_NAME:
				setInputName((String)newValue);
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
			case ConnectorDefinitionPackage.WIDGET_COMPONENT__INPUT_NAME:
				setInputName(INPUT_NAME_EDEFAULT);
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
			case ConnectorDefinitionPackage.WIDGET_COMPONENT__INPUT_NAME:
				return INPUT_NAME_EDEFAULT == null ? inputName != null : !INPUT_NAME_EDEFAULT.equals(inputName);
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
		result.append(" (inputName: ");
		result.append(inputName);
		result.append(')');
		return result.toString();
	}

} //WidgetComponentImpl
