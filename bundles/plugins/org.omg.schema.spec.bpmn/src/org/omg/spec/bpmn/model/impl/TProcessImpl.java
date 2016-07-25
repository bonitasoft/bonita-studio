/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.omg.spec.bpmn.model.impl;

import java.util.Collection;

import javax.xml.namespace.QName;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.omg.spec.bpmn.model.ModelPackage;
import org.omg.spec.bpmn.model.TArtifact;
import org.omg.spec.bpmn.model.TAuditing;
import org.omg.spec.bpmn.model.TCorrelationSubscription;
import org.omg.spec.bpmn.model.TFlowElement;
import org.omg.spec.bpmn.model.TLaneSet;
import org.omg.spec.bpmn.model.TMonitoring;
import org.omg.spec.bpmn.model.TProcess;
import org.omg.spec.bpmn.model.TProcessType;
import org.omg.spec.bpmn.model.TProperty;
import org.omg.spec.bpmn.model.TResourceRole;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TProcess</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TProcessImpl#getAuditing <em>Auditing</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TProcessImpl#getMonitoring <em>Monitoring</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TProcessImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TProcessImpl#getLaneSet <em>Lane Set</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TProcessImpl#getFlowElementGroup <em>Flow Element Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TProcessImpl#getFlowElement <em>Flow Element</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TProcessImpl#getArtifactGroup <em>Artifact Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TProcessImpl#getArtifact <em>Artifact</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TProcessImpl#getResourceRoleGroup <em>Resource Role Group</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TProcessImpl#getResourceRole <em>Resource Role</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TProcessImpl#getCorrelationSubscription <em>Correlation Subscription</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TProcessImpl#getSupports <em>Supports</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TProcessImpl#getDefinitionalCollaborationRef <em>Definitional Collaboration Ref</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TProcessImpl#isIsClosed <em>Is Closed</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TProcessImpl#isIsExecutable <em>Is Executable</em>}</li>
 *   <li>{@link org.omg.spec.bpmn.model.impl.TProcessImpl#getProcessType <em>Process Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TProcessImpl extends TCallableElementImpl implements TProcess {
	/**
	 * The cached value of the '{@link #getAuditing() <em>Auditing</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuditing()
	 * @generated
	 * @ordered
	 */
	protected TAuditing auditing;

	/**
	 * The cached value of the '{@link #getMonitoring() <em>Monitoring</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMonitoring()
	 * @generated
	 * @ordered
	 */
	protected TMonitoring monitoring;

	/**
	 * The cached value of the '{@link #getProperty() <em>Property</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperty()
	 * @generated
	 * @ordered
	 */
	protected EList<TProperty> property;

	/**
	 * The cached value of the '{@link #getLaneSet() <em>Lane Set</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLaneSet()
	 * @generated
	 * @ordered
	 */
	protected EList<TLaneSet> laneSet;

	/**
	 * The cached value of the '{@link #getFlowElementGroup() <em>Flow Element Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFlowElementGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap flowElementGroup;

	/**
	 * The cached value of the '{@link #getArtifactGroup() <em>Artifact Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArtifactGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap artifactGroup;

	/**
	 * The cached value of the '{@link #getResourceRoleGroup() <em>Resource Role Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceRoleGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap resourceRoleGroup;

	/**
	 * The cached value of the '{@link #getCorrelationSubscription() <em>Correlation Subscription</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCorrelationSubscription()
	 * @generated
	 * @ordered
	 */
	protected EList<TCorrelationSubscription> correlationSubscription;

	/**
	 * The cached value of the '{@link #getSupports() <em>Supports</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSupports()
	 * @generated
	 * @ordered
	 */
	protected EList<QName> supports;

	/**
	 * The default value of the '{@link #getDefinitionalCollaborationRef() <em>Definitional Collaboration Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinitionalCollaborationRef()
	 * @generated
	 * @ordered
	 */
	protected static final QName DEFINITIONAL_COLLABORATION_REF_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefinitionalCollaborationRef() <em>Definitional Collaboration Ref</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinitionalCollaborationRef()
	 * @generated
	 * @ordered
	 */
	protected QName definitionalCollaborationRef = DEFINITIONAL_COLLABORATION_REF_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsClosed() <em>Is Closed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsClosed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_CLOSED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsClosed() <em>Is Closed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsClosed()
	 * @generated
	 * @ordered
	 */
	protected boolean isClosed = IS_CLOSED_EDEFAULT;

	/**
	 * This is true if the Is Closed attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isClosedESet;

	/**
	 * The default value of the '{@link #isIsExecutable() <em>Is Executable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsExecutable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_EXECUTABLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsExecutable() <em>Is Executable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsExecutable()
	 * @generated
	 * @ordered
	 */
	protected boolean isExecutable = IS_EXECUTABLE_EDEFAULT;

	/**
	 * This is true if the Is Executable attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isExecutableESet;

	/**
	 * The default value of the '{@link #getProcessType() <em>Process Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessType()
	 * @generated
	 * @ordered
	 */
	protected static final TProcessType PROCESS_TYPE_EDEFAULT = TProcessType.NONE;

	/**
	 * The cached value of the '{@link #getProcessType() <em>Process Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessType()
	 * @generated
	 * @ordered
	 */
	protected TProcessType processType = PROCESS_TYPE_EDEFAULT;

	/**
	 * This is true if the Process Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean processTypeESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TProcessImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TPROCESS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TAuditing getAuditing() {
		return auditing;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAuditing(TAuditing newAuditing, NotificationChain msgs) {
		TAuditing oldAuditing = auditing;
		auditing = newAuditing;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.TPROCESS__AUDITING, oldAuditing, newAuditing);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAuditing(TAuditing newAuditing) {
		if (newAuditing != auditing) {
			NotificationChain msgs = null;
			if (auditing != null)
				msgs = ((InternalEObject)auditing).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TPROCESS__AUDITING, null, msgs);
			if (newAuditing != null)
				msgs = ((InternalEObject)newAuditing).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TPROCESS__AUDITING, null, msgs);
			msgs = basicSetAuditing(newAuditing, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TPROCESS__AUDITING, newAuditing, newAuditing));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMonitoring getMonitoring() {
		return monitoring;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMonitoring(TMonitoring newMonitoring, NotificationChain msgs) {
		TMonitoring oldMonitoring = monitoring;
		monitoring = newMonitoring;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.TPROCESS__MONITORING, oldMonitoring, newMonitoring);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMonitoring(TMonitoring newMonitoring) {
		if (newMonitoring != monitoring) {
			NotificationChain msgs = null;
			if (monitoring != null)
				msgs = ((InternalEObject)monitoring).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TPROCESS__MONITORING, null, msgs);
			if (newMonitoring != null)
				msgs = ((InternalEObject)newMonitoring).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TPROCESS__MONITORING, null, msgs);
			msgs = basicSetMonitoring(newMonitoring, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TPROCESS__MONITORING, newMonitoring, newMonitoring));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TProperty> getProperty() {
		if (property == null) {
			property = new EObjectContainmentEList<TProperty>(TProperty.class, this, ModelPackage.TPROCESS__PROPERTY);
		}
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TLaneSet> getLaneSet() {
		if (laneSet == null) {
			laneSet = new EObjectContainmentEList<TLaneSet>(TLaneSet.class, this, ModelPackage.TPROCESS__LANE_SET);
		}
		return laneSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getFlowElementGroup() {
		if (flowElementGroup == null) {
			flowElementGroup = new BasicFeatureMap(this, ModelPackage.TPROCESS__FLOW_ELEMENT_GROUP);
		}
		return flowElementGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TFlowElement> getFlowElement() {
		return getFlowElementGroup().list(ModelPackage.Literals.TPROCESS__FLOW_ELEMENT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getArtifactGroup() {
		if (artifactGroup == null) {
			artifactGroup = new BasicFeatureMap(this, ModelPackage.TPROCESS__ARTIFACT_GROUP);
		}
		return artifactGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TArtifact> getArtifact() {
		return getArtifactGroup().list(ModelPackage.Literals.TPROCESS__ARTIFACT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getResourceRoleGroup() {
		if (resourceRoleGroup == null) {
			resourceRoleGroup = new BasicFeatureMap(this, ModelPackage.TPROCESS__RESOURCE_ROLE_GROUP);
		}
		return resourceRoleGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TResourceRole> getResourceRole() {
		return getResourceRoleGroup().list(ModelPackage.Literals.TPROCESS__RESOURCE_ROLE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TCorrelationSubscription> getCorrelationSubscription() {
		if (correlationSubscription == null) {
			correlationSubscription = new EObjectContainmentEList<TCorrelationSubscription>(TCorrelationSubscription.class, this, ModelPackage.TPROCESS__CORRELATION_SUBSCRIPTION);
		}
		return correlationSubscription;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<QName> getSupports() {
		if (supports == null) {
			supports = new EDataTypeEList<QName>(QName.class, this, ModelPackage.TPROCESS__SUPPORTS);
		}
		return supports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QName getDefinitionalCollaborationRef() {
		return definitionalCollaborationRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefinitionalCollaborationRef(QName newDefinitionalCollaborationRef) {
		QName oldDefinitionalCollaborationRef = definitionalCollaborationRef;
		definitionalCollaborationRef = newDefinitionalCollaborationRef;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TPROCESS__DEFINITIONAL_COLLABORATION_REF, oldDefinitionalCollaborationRef, definitionalCollaborationRef));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsClosed() {
		return isClosed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsClosed(boolean newIsClosed) {
		boolean oldIsClosed = isClosed;
		isClosed = newIsClosed;
		boolean oldIsClosedESet = isClosedESet;
		isClosedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TPROCESS__IS_CLOSED, oldIsClosed, isClosed, !oldIsClosedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsClosed() {
		boolean oldIsClosed = isClosed;
		boolean oldIsClosedESet = isClosedESet;
		isClosed = IS_CLOSED_EDEFAULT;
		isClosedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TPROCESS__IS_CLOSED, oldIsClosed, IS_CLOSED_EDEFAULT, oldIsClosedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsClosed() {
		return isClosedESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsExecutable() {
		return isExecutable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsExecutable(boolean newIsExecutable) {
		boolean oldIsExecutable = isExecutable;
		isExecutable = newIsExecutable;
		boolean oldIsExecutableESet = isExecutableESet;
		isExecutableESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TPROCESS__IS_EXECUTABLE, oldIsExecutable, isExecutable, !oldIsExecutableESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsExecutable() {
		boolean oldIsExecutable = isExecutable;
		boolean oldIsExecutableESet = isExecutableESet;
		isExecutable = IS_EXECUTABLE_EDEFAULT;
		isExecutableESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TPROCESS__IS_EXECUTABLE, oldIsExecutable, IS_EXECUTABLE_EDEFAULT, oldIsExecutableESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsExecutable() {
		return isExecutableESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TProcessType getProcessType() {
		return processType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProcessType(TProcessType newProcessType) {
		TProcessType oldProcessType = processType;
		processType = newProcessType == null ? PROCESS_TYPE_EDEFAULT : newProcessType;
		boolean oldProcessTypeESet = processTypeESet;
		processTypeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TPROCESS__PROCESS_TYPE, oldProcessType, processType, !oldProcessTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetProcessType() {
		TProcessType oldProcessType = processType;
		boolean oldProcessTypeESet = processTypeESet;
		processType = PROCESS_TYPE_EDEFAULT;
		processTypeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ModelPackage.TPROCESS__PROCESS_TYPE, oldProcessType, PROCESS_TYPE_EDEFAULT, oldProcessTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetProcessType() {
		return processTypeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.TPROCESS__AUDITING:
				return basicSetAuditing(null, msgs);
			case ModelPackage.TPROCESS__MONITORING:
				return basicSetMonitoring(null, msgs);
			case ModelPackage.TPROCESS__PROPERTY:
				return ((InternalEList<?>)getProperty()).basicRemove(otherEnd, msgs);
			case ModelPackage.TPROCESS__LANE_SET:
				return ((InternalEList<?>)getLaneSet()).basicRemove(otherEnd, msgs);
			case ModelPackage.TPROCESS__FLOW_ELEMENT_GROUP:
				return ((InternalEList<?>)getFlowElementGroup()).basicRemove(otherEnd, msgs);
			case ModelPackage.TPROCESS__FLOW_ELEMENT:
				return ((InternalEList<?>)getFlowElement()).basicRemove(otherEnd, msgs);
			case ModelPackage.TPROCESS__ARTIFACT_GROUP:
				return ((InternalEList<?>)getArtifactGroup()).basicRemove(otherEnd, msgs);
			case ModelPackage.TPROCESS__ARTIFACT:
				return ((InternalEList<?>)getArtifact()).basicRemove(otherEnd, msgs);
			case ModelPackage.TPROCESS__RESOURCE_ROLE_GROUP:
				return ((InternalEList<?>)getResourceRoleGroup()).basicRemove(otherEnd, msgs);
			case ModelPackage.TPROCESS__RESOURCE_ROLE:
				return ((InternalEList<?>)getResourceRole()).basicRemove(otherEnd, msgs);
			case ModelPackage.TPROCESS__CORRELATION_SUBSCRIPTION:
				return ((InternalEList<?>)getCorrelationSubscription()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.TPROCESS__AUDITING:
				return getAuditing();
			case ModelPackage.TPROCESS__MONITORING:
				return getMonitoring();
			case ModelPackage.TPROCESS__PROPERTY:
				return getProperty();
			case ModelPackage.TPROCESS__LANE_SET:
				return getLaneSet();
			case ModelPackage.TPROCESS__FLOW_ELEMENT_GROUP:
				if (coreType) return getFlowElementGroup();
				return ((FeatureMap.Internal)getFlowElementGroup()).getWrapper();
			case ModelPackage.TPROCESS__FLOW_ELEMENT:
				return getFlowElement();
			case ModelPackage.TPROCESS__ARTIFACT_GROUP:
				if (coreType) return getArtifactGroup();
				return ((FeatureMap.Internal)getArtifactGroup()).getWrapper();
			case ModelPackage.TPROCESS__ARTIFACT:
				return getArtifact();
			case ModelPackage.TPROCESS__RESOURCE_ROLE_GROUP:
				if (coreType) return getResourceRoleGroup();
				return ((FeatureMap.Internal)getResourceRoleGroup()).getWrapper();
			case ModelPackage.TPROCESS__RESOURCE_ROLE:
				return getResourceRole();
			case ModelPackage.TPROCESS__CORRELATION_SUBSCRIPTION:
				return getCorrelationSubscription();
			case ModelPackage.TPROCESS__SUPPORTS:
				return getSupports();
			case ModelPackage.TPROCESS__DEFINITIONAL_COLLABORATION_REF:
				return getDefinitionalCollaborationRef();
			case ModelPackage.TPROCESS__IS_CLOSED:
				return isIsClosed();
			case ModelPackage.TPROCESS__IS_EXECUTABLE:
				return isIsExecutable();
			case ModelPackage.TPROCESS__PROCESS_TYPE:
				return getProcessType();
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
			case ModelPackage.TPROCESS__AUDITING:
				setAuditing((TAuditing)newValue);
				return;
			case ModelPackage.TPROCESS__MONITORING:
				setMonitoring((TMonitoring)newValue);
				return;
			case ModelPackage.TPROCESS__PROPERTY:
				getProperty().clear();
				getProperty().addAll((Collection<? extends TProperty>)newValue);
				return;
			case ModelPackage.TPROCESS__LANE_SET:
				getLaneSet().clear();
				getLaneSet().addAll((Collection<? extends TLaneSet>)newValue);
				return;
			case ModelPackage.TPROCESS__FLOW_ELEMENT_GROUP:
				((FeatureMap.Internal)getFlowElementGroup()).set(newValue);
				return;
			case ModelPackage.TPROCESS__FLOW_ELEMENT:
				getFlowElement().clear();
				getFlowElement().addAll((Collection<? extends TFlowElement>)newValue);
				return;
			case ModelPackage.TPROCESS__ARTIFACT_GROUP:
				((FeatureMap.Internal)getArtifactGroup()).set(newValue);
				return;
			case ModelPackage.TPROCESS__ARTIFACT:
				getArtifact().clear();
				getArtifact().addAll((Collection<? extends TArtifact>)newValue);
				return;
			case ModelPackage.TPROCESS__RESOURCE_ROLE_GROUP:
				((FeatureMap.Internal)getResourceRoleGroup()).set(newValue);
				return;
			case ModelPackage.TPROCESS__RESOURCE_ROLE:
				getResourceRole().clear();
				getResourceRole().addAll((Collection<? extends TResourceRole>)newValue);
				return;
			case ModelPackage.TPROCESS__CORRELATION_SUBSCRIPTION:
				getCorrelationSubscription().clear();
				getCorrelationSubscription().addAll((Collection<? extends TCorrelationSubscription>)newValue);
				return;
			case ModelPackage.TPROCESS__SUPPORTS:
				getSupports().clear();
				getSupports().addAll((Collection<? extends QName>)newValue);
				return;
			case ModelPackage.TPROCESS__DEFINITIONAL_COLLABORATION_REF:
				setDefinitionalCollaborationRef((QName)newValue);
				return;
			case ModelPackage.TPROCESS__IS_CLOSED:
				setIsClosed((Boolean)newValue);
				return;
			case ModelPackage.TPROCESS__IS_EXECUTABLE:
				setIsExecutable((Boolean)newValue);
				return;
			case ModelPackage.TPROCESS__PROCESS_TYPE:
				setProcessType((TProcessType)newValue);
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
			case ModelPackage.TPROCESS__AUDITING:
				setAuditing((TAuditing)null);
				return;
			case ModelPackage.TPROCESS__MONITORING:
				setMonitoring((TMonitoring)null);
				return;
			case ModelPackage.TPROCESS__PROPERTY:
				getProperty().clear();
				return;
			case ModelPackage.TPROCESS__LANE_SET:
				getLaneSet().clear();
				return;
			case ModelPackage.TPROCESS__FLOW_ELEMENT_GROUP:
				getFlowElementGroup().clear();
				return;
			case ModelPackage.TPROCESS__FLOW_ELEMENT:
				getFlowElement().clear();
				return;
			case ModelPackage.TPROCESS__ARTIFACT_GROUP:
				getArtifactGroup().clear();
				return;
			case ModelPackage.TPROCESS__ARTIFACT:
				getArtifact().clear();
				return;
			case ModelPackage.TPROCESS__RESOURCE_ROLE_GROUP:
				getResourceRoleGroup().clear();
				return;
			case ModelPackage.TPROCESS__RESOURCE_ROLE:
				getResourceRole().clear();
				return;
			case ModelPackage.TPROCESS__CORRELATION_SUBSCRIPTION:
				getCorrelationSubscription().clear();
				return;
			case ModelPackage.TPROCESS__SUPPORTS:
				getSupports().clear();
				return;
			case ModelPackage.TPROCESS__DEFINITIONAL_COLLABORATION_REF:
				setDefinitionalCollaborationRef(DEFINITIONAL_COLLABORATION_REF_EDEFAULT);
				return;
			case ModelPackage.TPROCESS__IS_CLOSED:
				unsetIsClosed();
				return;
			case ModelPackage.TPROCESS__IS_EXECUTABLE:
				unsetIsExecutable();
				return;
			case ModelPackage.TPROCESS__PROCESS_TYPE:
				unsetProcessType();
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
			case ModelPackage.TPROCESS__AUDITING:
				return auditing != null;
			case ModelPackage.TPROCESS__MONITORING:
				return monitoring != null;
			case ModelPackage.TPROCESS__PROPERTY:
				return property != null && !property.isEmpty();
			case ModelPackage.TPROCESS__LANE_SET:
				return laneSet != null && !laneSet.isEmpty();
			case ModelPackage.TPROCESS__FLOW_ELEMENT_GROUP:
				return flowElementGroup != null && !flowElementGroup.isEmpty();
			case ModelPackage.TPROCESS__FLOW_ELEMENT:
				return !getFlowElement().isEmpty();
			case ModelPackage.TPROCESS__ARTIFACT_GROUP:
				return artifactGroup != null && !artifactGroup.isEmpty();
			case ModelPackage.TPROCESS__ARTIFACT:
				return !getArtifact().isEmpty();
			case ModelPackage.TPROCESS__RESOURCE_ROLE_GROUP:
				return resourceRoleGroup != null && !resourceRoleGroup.isEmpty();
			case ModelPackage.TPROCESS__RESOURCE_ROLE:
				return !getResourceRole().isEmpty();
			case ModelPackage.TPROCESS__CORRELATION_SUBSCRIPTION:
				return correlationSubscription != null && !correlationSubscription.isEmpty();
			case ModelPackage.TPROCESS__SUPPORTS:
				return supports != null && !supports.isEmpty();
			case ModelPackage.TPROCESS__DEFINITIONAL_COLLABORATION_REF:
				return DEFINITIONAL_COLLABORATION_REF_EDEFAULT == null ? definitionalCollaborationRef != null : !DEFINITIONAL_COLLABORATION_REF_EDEFAULT.equals(definitionalCollaborationRef);
			case ModelPackage.TPROCESS__IS_CLOSED:
				return isSetIsClosed();
			case ModelPackage.TPROCESS__IS_EXECUTABLE:
				return isSetIsExecutable();
			case ModelPackage.TPROCESS__PROCESS_TYPE:
				return isSetProcessType();
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
		result.append(" (flowElementGroup: ");
		result.append(flowElementGroup);
		result.append(", artifactGroup: ");
		result.append(artifactGroup);
		result.append(", resourceRoleGroup: ");
		result.append(resourceRoleGroup);
		result.append(", supports: ");
		result.append(supports);
		result.append(", definitionalCollaborationRef: ");
		result.append(definitionalCollaborationRef);
		result.append(", isClosed: ");
		if (isClosedESet) result.append(isClosed); else result.append("<unset>");
		result.append(", isExecutable: ");
		if (isExecutableESet) result.append(isExecutable); else result.append("<unset>");
		result.append(", processType: ");
		if (processTypeESet) result.append(processType); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //TProcessImpl
