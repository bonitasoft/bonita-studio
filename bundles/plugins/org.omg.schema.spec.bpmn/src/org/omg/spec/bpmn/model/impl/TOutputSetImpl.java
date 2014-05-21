/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TOutputSet;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TOutput Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TOutputSetImpl#getDataOutputRefs <em>Data Output Refs</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TOutputSetImpl#getOptionalOutputRefs <em>Optional Output Refs</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TOutputSetImpl#getWhileExecutingOutputRefs <em>While Executing Output Refs</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TOutputSetImpl#getInputSetRefs <em>Input Set Refs</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TOutputSetImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TOutputSetImpl extends TBaseElementImpl implements TOutputSet {
	/**
	 * The cached value of the '{@link #getDataOutputRefs() <em>Data Output Refs</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataOutputRefs()
	 * @generated
	 * @ordered
	 */
	protected EList<String> dataOutputRefs;

	/**
	 * The cached value of the '{@link #getOptionalOutputRefs() <em>Optional Output Refs</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOptionalOutputRefs()
	 * @generated
	 * @ordered
	 */
	protected EList<String> optionalOutputRefs;

	/**
	 * The cached value of the '{@link #getWhileExecutingOutputRefs() <em>While Executing Output Refs</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWhileExecutingOutputRefs()
	 * @generated
	 * @ordered
	 */
	protected EList<String> whileExecutingOutputRefs;

	/**
	 * The cached value of the '{@link #getInputSetRefs() <em>Input Set Refs</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInputSetRefs()
	 * @generated
	 * @ordered
	 */
	protected EList<String> inputSetRefs;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TOutputSetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TOUTPUT_SET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getDataOutputRefs() {
		if (dataOutputRefs == null) {
			dataOutputRefs = new EDataTypeEList<String>(String.class, this, ModelPackage.TOUTPUT_SET__DATA_OUTPUT_REFS);
		}
		return dataOutputRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getOptionalOutputRefs() {
		if (optionalOutputRefs == null) {
			optionalOutputRefs = new EDataTypeEList<String>(String.class, this, ModelPackage.TOUTPUT_SET__OPTIONAL_OUTPUT_REFS);
		}
		return optionalOutputRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getWhileExecutingOutputRefs() {
		if (whileExecutingOutputRefs == null) {
			whileExecutingOutputRefs = new EDataTypeEList<String>(String.class, this, ModelPackage.TOUTPUT_SET__WHILE_EXECUTING_OUTPUT_REFS);
		}
		return whileExecutingOutputRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getInputSetRefs() {
		if (inputSetRefs == null) {
			inputSetRefs = new EDataTypeEList<String>(String.class, this, ModelPackage.TOUTPUT_SET__INPUT_SET_REFS);
		}
		return inputSetRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TOUTPUT_SET__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.TOUTPUT_SET__DATA_OUTPUT_REFS:
				return getDataOutputRefs();
			case ModelPackage.TOUTPUT_SET__OPTIONAL_OUTPUT_REFS:
				return getOptionalOutputRefs();
			case ModelPackage.TOUTPUT_SET__WHILE_EXECUTING_OUTPUT_REFS:
				return getWhileExecutingOutputRefs();
			case ModelPackage.TOUTPUT_SET__INPUT_SET_REFS:
				return getInputSetRefs();
			case ModelPackage.TOUTPUT_SET__NAME:
				return getName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.TOUTPUT_SET__DATA_OUTPUT_REFS:
				getDataOutputRefs().clear();
				getDataOutputRefs().addAll((Collection<? extends String>)newValue);
				return;
			case ModelPackage.TOUTPUT_SET__OPTIONAL_OUTPUT_REFS:
				getOptionalOutputRefs().clear();
				getOptionalOutputRefs().addAll((Collection<? extends String>)newValue);
				return;
			case ModelPackage.TOUTPUT_SET__WHILE_EXECUTING_OUTPUT_REFS:
				getWhileExecutingOutputRefs().clear();
				getWhileExecutingOutputRefs().addAll((Collection<? extends String>)newValue);
				return;
			case ModelPackage.TOUTPUT_SET__INPUT_SET_REFS:
				getInputSetRefs().clear();
				getInputSetRefs().addAll((Collection<? extends String>)newValue);
				return;
			case ModelPackage.TOUTPUT_SET__NAME:
				setName((String)newValue);
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
			case ModelPackage.TOUTPUT_SET__DATA_OUTPUT_REFS:
				getDataOutputRefs().clear();
				return;
			case ModelPackage.TOUTPUT_SET__OPTIONAL_OUTPUT_REFS:
				getOptionalOutputRefs().clear();
				return;
			case ModelPackage.TOUTPUT_SET__WHILE_EXECUTING_OUTPUT_REFS:
				getWhileExecutingOutputRefs().clear();
				return;
			case ModelPackage.TOUTPUT_SET__INPUT_SET_REFS:
				getInputSetRefs().clear();
				return;
			case ModelPackage.TOUTPUT_SET__NAME:
				setName(NAME_EDEFAULT);
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
			case ModelPackage.TOUTPUT_SET__DATA_OUTPUT_REFS:
				return dataOutputRefs != null && !dataOutputRefs.isEmpty();
			case ModelPackage.TOUTPUT_SET__OPTIONAL_OUTPUT_REFS:
				return optionalOutputRefs != null && !optionalOutputRefs.isEmpty();
			case ModelPackage.TOUTPUT_SET__WHILE_EXECUTING_OUTPUT_REFS:
				return whileExecutingOutputRefs != null && !whileExecutingOutputRefs.isEmpty();
			case ModelPackage.TOUTPUT_SET__INPUT_SET_REFS:
				return inputSetRefs != null && !inputSetRefs.isEmpty();
			case ModelPackage.TOUTPUT_SET__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		result.append(" (dataOutputRefs: ");
		result.append(dataOutputRefs);
		result.append(", optionalOutputRefs: ");
		result.append(optionalOutputRefs);
		result.append(", whileExecutingOutputRefs: ");
		result.append(whileExecutingOutputRefs);
		result.append(", inputSetRefs: ");
		result.append(inputSetRefs);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //TOutputSetImpl
