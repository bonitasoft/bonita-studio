/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.wfmc._2002.xpdl1.ScriptType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Script Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ScriptTypeImpl#getGrammar <em>Grammar</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ScriptTypeImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ScriptTypeImpl#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScriptTypeImpl extends EObjectImpl implements ScriptType {
	/**
	 * The default value of the '{@link #getGrammar() <em>Grammar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGrammar()
	 * @generated
	 * @ordered
	 */
	protected static final String GRAMMAR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGrammar() <em>Grammar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGrammar()
	 * @generated
	 * @ordered
	 */
	protected String grammar = GRAMMAR_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScriptTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.SCRIPT_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getGrammar() {
		return grammar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setGrammar(String newGrammar) {
		String oldGrammar = grammar;
		grammar = newGrammar;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.SCRIPT_TYPE__GRAMMAR, oldGrammar, grammar));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.SCRIPT_TYPE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.SCRIPT_TYPE__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case Xpdl1Package.SCRIPT_TYPE__GRAMMAR:
				return getGrammar();
			case Xpdl1Package.SCRIPT_TYPE__TYPE:
				return getType();
			case Xpdl1Package.SCRIPT_TYPE__VERSION:
				return getVersion();
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
			case Xpdl1Package.SCRIPT_TYPE__GRAMMAR:
				setGrammar((String)newValue);
				return;
			case Xpdl1Package.SCRIPT_TYPE__TYPE:
				setType((String)newValue);
				return;
			case Xpdl1Package.SCRIPT_TYPE__VERSION:
				setVersion((String)newValue);
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
			case Xpdl1Package.SCRIPT_TYPE__GRAMMAR:
				setGrammar(GRAMMAR_EDEFAULT);
				return;
			case Xpdl1Package.SCRIPT_TYPE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case Xpdl1Package.SCRIPT_TYPE__VERSION:
				setVersion(VERSION_EDEFAULT);
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
			case Xpdl1Package.SCRIPT_TYPE__GRAMMAR:
				return GRAMMAR_EDEFAULT == null ? grammar != null : !GRAMMAR_EDEFAULT.equals(grammar);
			case Xpdl1Package.SCRIPT_TYPE__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case Xpdl1Package.SCRIPT_TYPE__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
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
		result.append(" (grammar: ");
		result.append(grammar);
		result.append(", type: ");
		result.append(type);
		result.append(", version: ");
		result.append(version);
		result.append(')');
		return result.toString();
	}

} //ScriptTypeImpl
