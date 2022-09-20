/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.connector.model.definition.impl;

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinitionPackage;
import org.bonitasoft.studio.connector.model.definition.ScriptEditor;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Script Editor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.connector.model.definition.impl.ScriptEditorImpl#getInterpreter <em>Interpreter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScriptEditorImpl extends WidgetImpl implements ScriptEditor {
    /**
	 * The default value of the '{@link #getInterpreter() <em>Interpreter</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getInterpreter()
	 * @generated
	 * @ordered
	 */
    protected static final String INTERPRETER_EDEFAULT = "GROOVY";

    /**
	 * The cached value of the '{@link #getInterpreter() <em>Interpreter</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getInterpreter()
	 * @generated
	 * @ordered
	 */
    protected String interpreter = INTERPRETER_EDEFAULT;

    /**
	 * This is true if the Interpreter attribute has been set.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
    protected boolean interpreterESet;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ScriptEditorImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    protected EClass eStaticClass() {
		return ConnectorDefinitionPackage.Literals.SCRIPT_EDITOR;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getInterpreter() {
		return interpreter;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setInterpreter(String newInterpreter) {
		String oldInterpreter = interpreter;
		interpreter = newInterpreter;
		boolean oldInterpreterESet = interpreterESet;
		interpreterESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ConnectorDefinitionPackage.SCRIPT_EDITOR__INTERPRETER, oldInterpreter, interpreter, !oldInterpreterESet));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void unsetInterpreter() {
		String oldInterpreter = interpreter;
		boolean oldInterpreterESet = interpreterESet;
		interpreter = INTERPRETER_EDEFAULT;
		interpreterESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ConnectorDefinitionPackage.SCRIPT_EDITOR__INTERPRETER, oldInterpreter, INTERPRETER_EDEFAULT, oldInterpreterESet));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isSetInterpreter() {
		return interpreterESet;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ConnectorDefinitionPackage.SCRIPT_EDITOR__INTERPRETER:
				return getInterpreter();
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
			case ConnectorDefinitionPackage.SCRIPT_EDITOR__INTERPRETER:
				setInterpreter((String)newValue);
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
			case ConnectorDefinitionPackage.SCRIPT_EDITOR__INTERPRETER:
				unsetInterpreter();
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
			case ConnectorDefinitionPackage.SCRIPT_EDITOR__INTERPRETER:
				return isSetInterpreter();
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
		result.append(" (interpreter: ");
		if (interpreterESet) result.append(interpreter); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //ScriptEditorImpl
