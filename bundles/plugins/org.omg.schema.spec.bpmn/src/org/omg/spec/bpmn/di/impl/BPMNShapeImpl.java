/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.di.impl;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.omg.spec.bpmn.di.BPMNLabel;
import org.omg.spec.bpmn.di.BPMNShape;
import org.omg.spec.bpmn.di.DiPackage;
import org.omg.spec.bpmn.di.ParticipantBandKind;

import org.omg.spec.dd.di.impl.LabeledShapeImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>BPMN Shape</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.di.impl.BPMNShapeImpl#getBPMNLabel <em>BPMN Label</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.di.impl.BPMNShapeImpl#getBpmnElement <em>Bpmn Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.di.impl.BPMNShapeImpl#getChoreographyActivityShape <em>Choreography Activity Shape</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.di.impl.BPMNShapeImpl#isIsExpanded <em>Is Expanded</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.di.impl.BPMNShapeImpl#isIsHorizontal <em>Is Horizontal</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.di.impl.BPMNShapeImpl#isIsMarkerVisible <em>Is Marker Visible</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.di.impl.BPMNShapeImpl#isIsMessageVisible <em>Is Message Visible</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.di.impl.BPMNShapeImpl#getParticipantBandKind <em>Participant Band Kind</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BPMNShapeImpl extends LabeledShapeImpl implements BPMNShape {
	/**
	 * The cached value of the '{@link #getBPMNLabel() <em>BPMN Label</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBPMNLabel()
	 * @generated
	 * @ordered
	 */
	protected BPMNLabel bPMNLabel;

	/**
	 * The default value of the '{@link #getBpmnElement() <em>Bpmn Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBpmnElement()
	 * @generated
	 * @ordered
	 */
	protected static final QName BPMN_ELEMENT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBpmnElement() <em>Bpmn Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBpmnElement()
	 * @generated
	 * @ordered
	 */
	protected QName bpmnElement = BPMN_ELEMENT_EDEFAULT;

	/**
	 * The default value of the '{@link #getChoreographyActivityShape() <em>Choreography Activity Shape</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChoreographyActivityShape()
	 * @generated
	 * @ordered
	 */
	protected static final QName CHOREOGRAPHY_ACTIVITY_SHAPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getChoreographyActivityShape() <em>Choreography Activity Shape</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChoreographyActivityShape()
	 * @generated
	 * @ordered
	 */
	protected QName choreographyActivityShape = CHOREOGRAPHY_ACTIVITY_SHAPE_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsExpanded() <em>Is Expanded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsExpanded()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_EXPANDED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsExpanded() <em>Is Expanded</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsExpanded()
	 * @generated
	 * @ordered
	 */
	protected boolean isExpanded = IS_EXPANDED_EDEFAULT;

	/**
	 * This is true if the Is Expanded attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isExpandedESet;

	/**
	 * The default value of the '{@link #isIsHorizontal() <em>Is Horizontal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsHorizontal()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_HORIZONTAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsHorizontal() <em>Is Horizontal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsHorizontal()
	 * @generated
	 * @ordered
	 */
	protected boolean isHorizontal = IS_HORIZONTAL_EDEFAULT;

	/**
	 * This is true if the Is Horizontal attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isHorizontalESet;

	/**
	 * The default value of the '{@link #isIsMarkerVisible() <em>Is Marker Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsMarkerVisible()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_MARKER_VISIBLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsMarkerVisible() <em>Is Marker Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsMarkerVisible()
	 * @generated
	 * @ordered
	 */
	protected boolean isMarkerVisible = IS_MARKER_VISIBLE_EDEFAULT;

	/**
	 * This is true if the Is Marker Visible attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isMarkerVisibleESet;

	/**
	 * The default value of the '{@link #isIsMessageVisible() <em>Is Message Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsMessageVisible()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_MESSAGE_VISIBLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsMessageVisible() <em>Is Message Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsMessageVisible()
	 * @generated
	 * @ordered
	 */
	protected boolean isMessageVisible = IS_MESSAGE_VISIBLE_EDEFAULT;

	/**
	 * This is true if the Is Message Visible attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isMessageVisibleESet;

	/**
	 * The default value of the '{@link #getParticipantBandKind() <em>Participant Band Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParticipantBandKind()
	 * @generated
	 * @ordered
	 */
	protected static final ParticipantBandKind PARTICIPANT_BAND_KIND_EDEFAULT = ParticipantBandKind.TOP_INITIATING;

	/**
	 * The cached value of the '{@link #getParticipantBandKind() <em>Participant Band Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParticipantBandKind()
	 * @generated
	 * @ordered
	 */
	protected ParticipantBandKind participantBandKind = PARTICIPANT_BAND_KIND_EDEFAULT;

	/**
	 * This is true if the Participant Band Kind attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean participantBandKindESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BPMNShapeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DiPackage.Literals.BPMN_SHAPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BPMNLabel getBPMNLabel() {
		return bPMNLabel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBPMNLabel(BPMNLabel newBPMNLabel, NotificationChain msgs) {
		BPMNLabel oldBPMNLabel = bPMNLabel;
		bPMNLabel = newBPMNLabel;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DiPackage.BPMN_SHAPE__BPMN_LABEL, oldBPMNLabel, newBPMNLabel);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBPMNLabel(BPMNLabel newBPMNLabel) {
		if (newBPMNLabel != bPMNLabel) {
			NotificationChain msgs = null;
			if (bPMNLabel != null)
				msgs = ((InternalEObject)bPMNLabel).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - DiPackage.BPMN_SHAPE__BPMN_LABEL, null, msgs);
			if (newBPMNLabel != null)
				msgs = ((InternalEObject)newBPMNLabel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - DiPackage.BPMN_SHAPE__BPMN_LABEL, null, msgs);
			msgs = basicSetBPMNLabel(newBPMNLabel, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiPackage.BPMN_SHAPE__BPMN_LABEL, newBPMNLabel, newBPMNLabel));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QName getBpmnElement() {
		return bpmnElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBpmnElement(QName newBpmnElement) {
		QName oldBpmnElement = bpmnElement;
		bpmnElement = newBpmnElement;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiPackage.BPMN_SHAPE__BPMN_ELEMENT, oldBpmnElement, bpmnElement));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QName getChoreographyActivityShape() {
		return choreographyActivityShape;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setChoreographyActivityShape(QName newChoreographyActivityShape) {
		QName oldChoreographyActivityShape = choreographyActivityShape;
		choreographyActivityShape = newChoreographyActivityShape;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiPackage.BPMN_SHAPE__CHOREOGRAPHY_ACTIVITY_SHAPE, oldChoreographyActivityShape, choreographyActivityShape));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsExpanded() {
		return isExpanded;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsExpanded(boolean newIsExpanded) {
		boolean oldIsExpanded = isExpanded;
		isExpanded = newIsExpanded;
		boolean oldIsExpandedESet = isExpandedESet;
		isExpandedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiPackage.BPMN_SHAPE__IS_EXPANDED, oldIsExpanded, isExpanded, !oldIsExpandedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsExpanded() {
		boolean oldIsExpanded = isExpanded;
		boolean oldIsExpandedESet = isExpandedESet;
		isExpanded = IS_EXPANDED_EDEFAULT;
		isExpandedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, DiPackage.BPMN_SHAPE__IS_EXPANDED, oldIsExpanded, IS_EXPANDED_EDEFAULT, oldIsExpandedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsExpanded() {
		return isExpandedESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsHorizontal() {
		return isHorizontal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsHorizontal(boolean newIsHorizontal) {
		boolean oldIsHorizontal = isHorizontal;
		isHorizontal = newIsHorizontal;
		boolean oldIsHorizontalESet = isHorizontalESet;
		isHorizontalESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiPackage.BPMN_SHAPE__IS_HORIZONTAL, oldIsHorizontal, isHorizontal, !oldIsHorizontalESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsHorizontal() {
		boolean oldIsHorizontal = isHorizontal;
		boolean oldIsHorizontalESet = isHorizontalESet;
		isHorizontal = IS_HORIZONTAL_EDEFAULT;
		isHorizontalESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, DiPackage.BPMN_SHAPE__IS_HORIZONTAL, oldIsHorizontal, IS_HORIZONTAL_EDEFAULT, oldIsHorizontalESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsHorizontal() {
		return isHorizontalESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsMarkerVisible() {
		return isMarkerVisible;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsMarkerVisible(boolean newIsMarkerVisible) {
		boolean oldIsMarkerVisible = isMarkerVisible;
		isMarkerVisible = newIsMarkerVisible;
		boolean oldIsMarkerVisibleESet = isMarkerVisibleESet;
		isMarkerVisibleESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiPackage.BPMN_SHAPE__IS_MARKER_VISIBLE, oldIsMarkerVisible, isMarkerVisible, !oldIsMarkerVisibleESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsMarkerVisible() {
		boolean oldIsMarkerVisible = isMarkerVisible;
		boolean oldIsMarkerVisibleESet = isMarkerVisibleESet;
		isMarkerVisible = IS_MARKER_VISIBLE_EDEFAULT;
		isMarkerVisibleESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, DiPackage.BPMN_SHAPE__IS_MARKER_VISIBLE, oldIsMarkerVisible, IS_MARKER_VISIBLE_EDEFAULT, oldIsMarkerVisibleESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsMarkerVisible() {
		return isMarkerVisibleESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsMessageVisible() {
		return isMessageVisible;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsMessageVisible(boolean newIsMessageVisible) {
		boolean oldIsMessageVisible = isMessageVisible;
		isMessageVisible = newIsMessageVisible;
		boolean oldIsMessageVisibleESet = isMessageVisibleESet;
		isMessageVisibleESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiPackage.BPMN_SHAPE__IS_MESSAGE_VISIBLE, oldIsMessageVisible, isMessageVisible, !oldIsMessageVisibleESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsMessageVisible() {
		boolean oldIsMessageVisible = isMessageVisible;
		boolean oldIsMessageVisibleESet = isMessageVisibleESet;
		isMessageVisible = IS_MESSAGE_VISIBLE_EDEFAULT;
		isMessageVisibleESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, DiPackage.BPMN_SHAPE__IS_MESSAGE_VISIBLE, oldIsMessageVisible, IS_MESSAGE_VISIBLE_EDEFAULT, oldIsMessageVisibleESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsMessageVisible() {
		return isMessageVisibleESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParticipantBandKind getParticipantBandKind() {
		return participantBandKind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParticipantBandKind(ParticipantBandKind newParticipantBandKind) {
		ParticipantBandKind oldParticipantBandKind = participantBandKind;
		participantBandKind = newParticipantBandKind == null ? PARTICIPANT_BAND_KIND_EDEFAULT : newParticipantBandKind;
		boolean oldParticipantBandKindESet = participantBandKindESet;
		participantBandKindESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DiPackage.BPMN_SHAPE__PARTICIPANT_BAND_KIND, oldParticipantBandKind, participantBandKind, !oldParticipantBandKindESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetParticipantBandKind() {
		ParticipantBandKind oldParticipantBandKind = participantBandKind;
		boolean oldParticipantBandKindESet = participantBandKindESet;
		participantBandKind = PARTICIPANT_BAND_KIND_EDEFAULT;
		participantBandKindESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, DiPackage.BPMN_SHAPE__PARTICIPANT_BAND_KIND, oldParticipantBandKind, PARTICIPANT_BAND_KIND_EDEFAULT, oldParticipantBandKindESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetParticipantBandKind() {
		return participantBandKindESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DiPackage.BPMN_SHAPE__BPMN_LABEL:
				return basicSetBPMNLabel(null, msgs);
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
			case DiPackage.BPMN_SHAPE__BPMN_LABEL:
				return getBPMNLabel();
			case DiPackage.BPMN_SHAPE__BPMN_ELEMENT:
				return getBpmnElement();
			case DiPackage.BPMN_SHAPE__CHOREOGRAPHY_ACTIVITY_SHAPE:
				return getChoreographyActivityShape();
			case DiPackage.BPMN_SHAPE__IS_EXPANDED:
				return isIsExpanded();
			case DiPackage.BPMN_SHAPE__IS_HORIZONTAL:
				return isIsHorizontal();
			case DiPackage.BPMN_SHAPE__IS_MARKER_VISIBLE:
				return isIsMarkerVisible();
			case DiPackage.BPMN_SHAPE__IS_MESSAGE_VISIBLE:
				return isIsMessageVisible();
			case DiPackage.BPMN_SHAPE__PARTICIPANT_BAND_KIND:
				return getParticipantBandKind();
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
			case DiPackage.BPMN_SHAPE__BPMN_LABEL:
				setBPMNLabel((BPMNLabel)newValue);
				return;
			case DiPackage.BPMN_SHAPE__BPMN_ELEMENT:
				setBpmnElement((QName)newValue);
				return;
			case DiPackage.BPMN_SHAPE__CHOREOGRAPHY_ACTIVITY_SHAPE:
				setChoreographyActivityShape((QName)newValue);
				return;
			case DiPackage.BPMN_SHAPE__IS_EXPANDED:
				setIsExpanded((Boolean)newValue);
				return;
			case DiPackage.BPMN_SHAPE__IS_HORIZONTAL:
				setIsHorizontal((Boolean)newValue);
				return;
			case DiPackage.BPMN_SHAPE__IS_MARKER_VISIBLE:
				setIsMarkerVisible((Boolean)newValue);
				return;
			case DiPackage.BPMN_SHAPE__IS_MESSAGE_VISIBLE:
				setIsMessageVisible((Boolean)newValue);
				return;
			case DiPackage.BPMN_SHAPE__PARTICIPANT_BAND_KIND:
				setParticipantBandKind((ParticipantBandKind)newValue);
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
			case DiPackage.BPMN_SHAPE__BPMN_LABEL:
				setBPMNLabel((BPMNLabel)null);
				return;
			case DiPackage.BPMN_SHAPE__BPMN_ELEMENT:
				setBpmnElement(BPMN_ELEMENT_EDEFAULT);
				return;
			case DiPackage.BPMN_SHAPE__CHOREOGRAPHY_ACTIVITY_SHAPE:
				setChoreographyActivityShape(CHOREOGRAPHY_ACTIVITY_SHAPE_EDEFAULT);
				return;
			case DiPackage.BPMN_SHAPE__IS_EXPANDED:
				unsetIsExpanded();
				return;
			case DiPackage.BPMN_SHAPE__IS_HORIZONTAL:
				unsetIsHorizontal();
				return;
			case DiPackage.BPMN_SHAPE__IS_MARKER_VISIBLE:
				unsetIsMarkerVisible();
				return;
			case DiPackage.BPMN_SHAPE__IS_MESSAGE_VISIBLE:
				unsetIsMessageVisible();
				return;
			case DiPackage.BPMN_SHAPE__PARTICIPANT_BAND_KIND:
				unsetParticipantBandKind();
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
			case DiPackage.BPMN_SHAPE__BPMN_LABEL:
				return bPMNLabel != null;
			case DiPackage.BPMN_SHAPE__BPMN_ELEMENT:
				return BPMN_ELEMENT_EDEFAULT == null ? bpmnElement != null : !BPMN_ELEMENT_EDEFAULT.equals(bpmnElement);
			case DiPackage.BPMN_SHAPE__CHOREOGRAPHY_ACTIVITY_SHAPE:
				return CHOREOGRAPHY_ACTIVITY_SHAPE_EDEFAULT == null ? choreographyActivityShape != null : !CHOREOGRAPHY_ACTIVITY_SHAPE_EDEFAULT.equals(choreographyActivityShape);
			case DiPackage.BPMN_SHAPE__IS_EXPANDED:
				return isSetIsExpanded();
			case DiPackage.BPMN_SHAPE__IS_HORIZONTAL:
				return isSetIsHorizontal();
			case DiPackage.BPMN_SHAPE__IS_MARKER_VISIBLE:
				return isSetIsMarkerVisible();
			case DiPackage.BPMN_SHAPE__IS_MESSAGE_VISIBLE:
				return isSetIsMessageVisible();
			case DiPackage.BPMN_SHAPE__PARTICIPANT_BAND_KIND:
				return isSetParticipantBandKind();
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
		result.append(" (bpmnElement: ");
		result.append(bpmnElement);
		result.append(", choreographyActivityShape: ");
		result.append(choreographyActivityShape);
		result.append(", isExpanded: ");
		if (isExpandedESet) result.append(isExpanded); else result.append("<unset>");
		result.append(", isHorizontal: ");
		if (isHorizontalESet) result.append(isHorizontal); else result.append("<unset>");
		result.append(", isMarkerVisible: ");
		if (isMarkerVisibleESet) result.append(isMarkerVisible); else result.append("<unset>");
		result.append(", isMessageVisible: ");
		if (isMessageVisibleESet) result.append(isMessageVisible); else result.append("<unset>");
		result.append(", participantBandKind: ");
		if (participantBandKindESet) result.append(participantBandKind); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //BPMNShapeImpl
