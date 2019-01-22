/**
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.process.impl;

import java.util.Collection;

import org.bonitasoft.studio.model.expression.Expression;

import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.FlowElement;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SourceElement;
import org.bonitasoft.studio.model.process.TargetElement;
import org.bonitasoft.studio.model.process.TextAnnotationAttachment;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Flow Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.FlowElementImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.FlowElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.FlowElementImpl#getTextAnnotationAttachment <em>Text Annotation Attachment</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.FlowElementImpl#getOutgoing <em>Outgoing</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.FlowElementImpl#getIncoming <em>Incoming</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.FlowElementImpl#getDynamicLabel <em>Dynamic Label</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.FlowElementImpl#getDynamicDescription <em>Dynamic Description</em>}</li>
 *   <li>{@link org.bonitasoft.studio.model.process.impl.FlowElementImpl#getStepSummary <em>Step Summary</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FlowElementImpl extends EObjectImpl implements FlowElement {
	/**
	 * The default value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected static final String DOCUMENTATION_EDEFAULT = ""; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected String documentation = DOCUMENTATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = ""; //$NON-NLS-1$

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
	 * The cached value of the '{@link #getTextAnnotationAttachment() <em>Text Annotation Attachment</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTextAnnotationAttachment()
	 * @generated
	 * @ordered
	 */
	protected EList<TextAnnotationAttachment> textAnnotationAttachment;

	/**
	 * The cached value of the '{@link #getOutgoing() <em>Outgoing</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutgoing()
	 * @generated
	 * @ordered
	 */
	protected EList<Connection> outgoing;

	/**
	 * The cached value of the '{@link #getIncoming() <em>Incoming</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncoming()
	 * @generated
	 * @ordered
	 */
	protected EList<Connection> incoming;

	/**
	 * The cached value of the '{@link #getDynamicLabel() <em>Dynamic Label</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDynamicLabel()
	 * @generated
	 * @ordered
	 */
	protected Expression dynamicLabel;

	/**
	 * The cached value of the '{@link #getDynamicDescription() <em>Dynamic Description</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDynamicDescription()
	 * @generated
	 * @ordered
	 */
	protected Expression dynamicDescription;

	/**
	 * The cached value of the '{@link #getStepSummary() <em>Step Summary</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStepSummary()
	 * @generated
	 * @ordered
	 */
	protected Expression stepSummary;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FlowElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ProcessPackage.Literals.FLOW_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDocumentation() {
		return documentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDocumentation(String newDocumentation) {
		String oldDocumentation = documentation;
		documentation = newDocumentation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.FLOW_ELEMENT__DOCUMENTATION, oldDocumentation, documentation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.FLOW_ELEMENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<TextAnnotationAttachment> getTextAnnotationAttachment() {
		if (textAnnotationAttachment == null) {
			textAnnotationAttachment = new EObjectContainmentWithInverseEList<TextAnnotationAttachment>(TextAnnotationAttachment.class, this, ProcessPackage.FLOW_ELEMENT__TEXT_ANNOTATION_ATTACHMENT, ProcessPackage.TEXT_ANNOTATION_ATTACHMENT__TARGET);
		}
		return textAnnotationAttachment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Connection> getOutgoing() {
		if (outgoing == null) {
			outgoing = new EObjectWithInverseResolvingEList<Connection>(Connection.class, this, ProcessPackage.FLOW_ELEMENT__OUTGOING, ProcessPackage.CONNECTION__SOURCE);
		}
		return outgoing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Connection> getIncoming() {
		if (incoming == null) {
			incoming = new EObjectWithInverseResolvingEList<Connection>(Connection.class, this, ProcessPackage.FLOW_ELEMENT__INCOMING, ProcessPackage.CONNECTION__TARGET);
		}
		return incoming;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getDynamicLabel() {
		return dynamicLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDynamicLabel(Expression newDynamicLabel, NotificationChain msgs) {
		Expression oldDynamicLabel = dynamicLabel;
		dynamicLabel = newDynamicLabel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.FLOW_ELEMENT__DYNAMIC_LABEL, oldDynamicLabel, newDynamicLabel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDynamicLabel(Expression newDynamicLabel) {
		if (newDynamicLabel != dynamicLabel) {
			NotificationChain msgs = null;
			if (dynamicLabel != null)
				msgs = ((InternalEObject)dynamicLabel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.FLOW_ELEMENT__DYNAMIC_LABEL, null, msgs);
			if (newDynamicLabel != null)
				msgs = ((InternalEObject)newDynamicLabel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.FLOW_ELEMENT__DYNAMIC_LABEL, null, msgs);
			msgs = basicSetDynamicLabel(newDynamicLabel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.FLOW_ELEMENT__DYNAMIC_LABEL, newDynamicLabel, newDynamicLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getDynamicDescription() {
		return dynamicDescription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDynamicDescription(Expression newDynamicDescription, NotificationChain msgs) {
		Expression oldDynamicDescription = dynamicDescription;
		dynamicDescription = newDynamicDescription;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.FLOW_ELEMENT__DYNAMIC_DESCRIPTION, oldDynamicDescription, newDynamicDescription);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDynamicDescription(Expression newDynamicDescription) {
		if (newDynamicDescription != dynamicDescription) {
			NotificationChain msgs = null;
			if (dynamicDescription != null)
				msgs = ((InternalEObject)dynamicDescription).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.FLOW_ELEMENT__DYNAMIC_DESCRIPTION, null, msgs);
			if (newDynamicDescription != null)
				msgs = ((InternalEObject)newDynamicDescription).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.FLOW_ELEMENT__DYNAMIC_DESCRIPTION, null, msgs);
			msgs = basicSetDynamicDescription(newDynamicDescription, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.FLOW_ELEMENT__DYNAMIC_DESCRIPTION, newDynamicDescription, newDynamicDescription));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Expression getStepSummary() {
		return stepSummary;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStepSummary(Expression newStepSummary, NotificationChain msgs) {
		Expression oldStepSummary = stepSummary;
		stepSummary = newStepSummary;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ProcessPackage.FLOW_ELEMENT__STEP_SUMMARY, oldStepSummary, newStepSummary);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStepSummary(Expression newStepSummary) {
		if (newStepSummary != stepSummary) {
			NotificationChain msgs = null;
			if (stepSummary != null)
				msgs = ((InternalEObject)stepSummary).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.FLOW_ELEMENT__STEP_SUMMARY, null, msgs);
			if (newStepSummary != null)
				msgs = ((InternalEObject)newStepSummary).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ProcessPackage.FLOW_ELEMENT__STEP_SUMMARY, null, msgs);
			msgs = basicSetStepSummary(newStepSummary, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ProcessPackage.FLOW_ELEMENT__STEP_SUMMARY, newStepSummary, newStepSummary));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProcessPackage.FLOW_ELEMENT__TEXT_ANNOTATION_ATTACHMENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTextAnnotationAttachment()).basicAdd(otherEnd, msgs);
			case ProcessPackage.FLOW_ELEMENT__OUTGOING:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOutgoing()).basicAdd(otherEnd, msgs);
			case ProcessPackage.FLOW_ELEMENT__INCOMING:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getIncoming()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ProcessPackage.FLOW_ELEMENT__TEXT_ANNOTATION_ATTACHMENT:
				return ((InternalEList<?>)getTextAnnotationAttachment()).basicRemove(otherEnd, msgs);
			case ProcessPackage.FLOW_ELEMENT__OUTGOING:
				return ((InternalEList<?>)getOutgoing()).basicRemove(otherEnd, msgs);
			case ProcessPackage.FLOW_ELEMENT__INCOMING:
				return ((InternalEList<?>)getIncoming()).basicRemove(otherEnd, msgs);
			case ProcessPackage.FLOW_ELEMENT__DYNAMIC_LABEL:
				return basicSetDynamicLabel(null, msgs);
			case ProcessPackage.FLOW_ELEMENT__DYNAMIC_DESCRIPTION:
				return basicSetDynamicDescription(null, msgs);
			case ProcessPackage.FLOW_ELEMENT__STEP_SUMMARY:
				return basicSetStepSummary(null, msgs);
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
			case ProcessPackage.FLOW_ELEMENT__DOCUMENTATION:
				return getDocumentation();
			case ProcessPackage.FLOW_ELEMENT__NAME:
				return getName();
			case ProcessPackage.FLOW_ELEMENT__TEXT_ANNOTATION_ATTACHMENT:
				return getTextAnnotationAttachment();
			case ProcessPackage.FLOW_ELEMENT__OUTGOING:
				return getOutgoing();
			case ProcessPackage.FLOW_ELEMENT__INCOMING:
				return getIncoming();
			case ProcessPackage.FLOW_ELEMENT__DYNAMIC_LABEL:
				return getDynamicLabel();
			case ProcessPackage.FLOW_ELEMENT__DYNAMIC_DESCRIPTION:
				return getDynamicDescription();
			case ProcessPackage.FLOW_ELEMENT__STEP_SUMMARY:
				return getStepSummary();
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
			case ProcessPackage.FLOW_ELEMENT__DOCUMENTATION:
				setDocumentation((String)newValue);
				return;
			case ProcessPackage.FLOW_ELEMENT__NAME:
				setName((String)newValue);
				return;
			case ProcessPackage.FLOW_ELEMENT__TEXT_ANNOTATION_ATTACHMENT:
				getTextAnnotationAttachment().clear();
				getTextAnnotationAttachment().addAll((Collection<? extends TextAnnotationAttachment>)newValue);
				return;
			case ProcessPackage.FLOW_ELEMENT__OUTGOING:
				getOutgoing().clear();
				getOutgoing().addAll((Collection<? extends Connection>)newValue);
				return;
			case ProcessPackage.FLOW_ELEMENT__INCOMING:
				getIncoming().clear();
				getIncoming().addAll((Collection<? extends Connection>)newValue);
				return;
			case ProcessPackage.FLOW_ELEMENT__DYNAMIC_LABEL:
				setDynamicLabel((Expression)newValue);
				return;
			case ProcessPackage.FLOW_ELEMENT__DYNAMIC_DESCRIPTION:
				setDynamicDescription((Expression)newValue);
				return;
			case ProcessPackage.FLOW_ELEMENT__STEP_SUMMARY:
				setStepSummary((Expression)newValue);
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
			case ProcessPackage.FLOW_ELEMENT__DOCUMENTATION:
				setDocumentation(DOCUMENTATION_EDEFAULT);
				return;
			case ProcessPackage.FLOW_ELEMENT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ProcessPackage.FLOW_ELEMENT__TEXT_ANNOTATION_ATTACHMENT:
				getTextAnnotationAttachment().clear();
				return;
			case ProcessPackage.FLOW_ELEMENT__OUTGOING:
				getOutgoing().clear();
				return;
			case ProcessPackage.FLOW_ELEMENT__INCOMING:
				getIncoming().clear();
				return;
			case ProcessPackage.FLOW_ELEMENT__DYNAMIC_LABEL:
				setDynamicLabel((Expression)null);
				return;
			case ProcessPackage.FLOW_ELEMENT__DYNAMIC_DESCRIPTION:
				setDynamicDescription((Expression)null);
				return;
			case ProcessPackage.FLOW_ELEMENT__STEP_SUMMARY:
				setStepSummary((Expression)null);
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
			case ProcessPackage.FLOW_ELEMENT__DOCUMENTATION:
				return DOCUMENTATION_EDEFAULT == null ? documentation != null : !DOCUMENTATION_EDEFAULT.equals(documentation);
			case ProcessPackage.FLOW_ELEMENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ProcessPackage.FLOW_ELEMENT__TEXT_ANNOTATION_ATTACHMENT:
				return textAnnotationAttachment != null && !textAnnotationAttachment.isEmpty();
			case ProcessPackage.FLOW_ELEMENT__OUTGOING:
				return outgoing != null && !outgoing.isEmpty();
			case ProcessPackage.FLOW_ELEMENT__INCOMING:
				return incoming != null && !incoming.isEmpty();
			case ProcessPackage.FLOW_ELEMENT__DYNAMIC_LABEL:
				return dynamicLabel != null;
			case ProcessPackage.FLOW_ELEMENT__DYNAMIC_DESCRIPTION:
				return dynamicDescription != null;
			case ProcessPackage.FLOW_ELEMENT__STEP_SUMMARY:
				return stepSummary != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == SourceElement.class) {
			switch (derivedFeatureID) {
				case ProcessPackage.FLOW_ELEMENT__OUTGOING: return ProcessPackage.SOURCE_ELEMENT__OUTGOING;
				default: return -1;
			}
		}
		if (baseClass == TargetElement.class) {
			switch (derivedFeatureID) {
				case ProcessPackage.FLOW_ELEMENT__INCOMING: return ProcessPackage.TARGET_ELEMENT__INCOMING;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == SourceElement.class) {
			switch (baseFeatureID) {
				case ProcessPackage.SOURCE_ELEMENT__OUTGOING: return ProcessPackage.FLOW_ELEMENT__OUTGOING;
				default: return -1;
			}
		}
		if (baseClass == TargetElement.class) {
			switch (baseFeatureID) {
				case ProcessPackage.TARGET_ELEMENT__INCOMING: return ProcessPackage.FLOW_ELEMENT__INCOMING;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (documentation: "); //$NON-NLS-1$
		result.append(documentation);
		result.append(", name: "); //$NON-NLS-1$
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //FlowElementImpl
