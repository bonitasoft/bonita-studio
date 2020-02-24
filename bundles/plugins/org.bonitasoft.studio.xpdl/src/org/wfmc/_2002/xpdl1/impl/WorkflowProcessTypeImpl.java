/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.wfmc._2002.xpdl1.AccessLevelType;
import org.wfmc._2002.xpdl1.ActivitiesType;
import org.wfmc._2002.xpdl1.ActivitySetsType;
import org.wfmc._2002.xpdl1.ApplicationsType;
import org.wfmc._2002.xpdl1.DataFieldsType;
import org.wfmc._2002.xpdl1.ExtendedAttributesType;
import org.wfmc._2002.xpdl1.FormalParametersType;
import org.wfmc._2002.xpdl1.ParticipantsType;
import org.wfmc._2002.xpdl1.ProcessHeaderType;
import org.wfmc._2002.xpdl1.RedefinableHeaderType;
import org.wfmc._2002.xpdl1.TransitionsType;
import org.wfmc._2002.xpdl1.WorkflowProcessType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Workflow Process Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.WorkflowProcessTypeImpl#getProcessHeader <em>Process Header</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.WorkflowProcessTypeImpl#getRedefinableHeader <em>Redefinable Header</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.WorkflowProcessTypeImpl#getFormalParameters <em>Formal Parameters</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.WorkflowProcessTypeImpl#getDataFields <em>Data Fields</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.WorkflowProcessTypeImpl#getParticipants <em>Participants</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.WorkflowProcessTypeImpl#getApplications <em>Applications</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.WorkflowProcessTypeImpl#getActivitySets <em>Activity Sets</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.WorkflowProcessTypeImpl#getActivities <em>Activities</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.WorkflowProcessTypeImpl#getTransitions <em>Transitions</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.WorkflowProcessTypeImpl#getExtendedAttributes <em>Extended Attributes</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.WorkflowProcessTypeImpl#getAccessLevel <em>Access Level</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.WorkflowProcessTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.WorkflowProcessTypeImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WorkflowProcessTypeImpl extends EObjectImpl implements WorkflowProcessType {
	/**
	 * The cached value of the '{@link #getProcessHeader() <em>Process Header</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProcessHeader()
	 * @generated
	 * @ordered
	 */
	protected ProcessHeaderType processHeader;

	/**
	 * The cached value of the '{@link #getRedefinableHeader() <em>Redefinable Header</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRedefinableHeader()
	 * @generated
	 * @ordered
	 */
	protected RedefinableHeaderType redefinableHeader;

	/**
	 * The cached value of the '{@link #getFormalParameters() <em>Formal Parameters</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFormalParameters()
	 * @generated
	 * @ordered
	 */
	protected FormalParametersType formalParameters;

	/**
	 * The cached value of the '{@link #getDataFields() <em>Data Fields</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataFields()
	 * @generated
	 * @ordered
	 */
	protected DataFieldsType dataFields;

	/**
	 * The cached value of the '{@link #getParticipants() <em>Participants</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParticipants()
	 * @generated
	 * @ordered
	 */
	protected ParticipantsType participants;

	/**
	 * The cached value of the '{@link #getApplications() <em>Applications</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getApplications()
	 * @generated
	 * @ordered
	 */
	protected ApplicationsType applications;

	/**
	 * The cached value of the '{@link #getActivitySets() <em>Activity Sets</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivitySets()
	 * @generated
	 * @ordered
	 */
	protected ActivitySetsType activitySets;

	/**
	 * The cached value of the '{@link #getActivities() <em>Activities</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActivities()
	 * @generated
	 * @ordered
	 */
	protected ActivitiesType activities;

	/**
	 * The cached value of the '{@link #getTransitions() <em>Transitions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransitions()
	 * @generated
	 * @ordered
	 */
	protected TransitionsType transitions;

	/**
	 * The cached value of the '{@link #getExtendedAttributes() <em>Extended Attributes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExtendedAttributes()
	 * @generated
	 * @ordered
	 */
	protected ExtendedAttributesType extendedAttributes;

	/**
	 * The default value of the '{@link #getAccessLevel() <em>Access Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccessLevel()
	 * @generated
	 * @ordered
	 */
	protected static final AccessLevelType ACCESS_LEVEL_EDEFAULT = AccessLevelType.PUBLIC;

	/**
	 * The cached value of the '{@link #getAccessLevel() <em>Access Level</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccessLevel()
	 * @generated
	 * @ordered
	 */
	protected AccessLevelType accessLevel = ACCESS_LEVEL_EDEFAULT;

	/**
	 * This is true if the Access Level attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean accessLevelESet;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

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
	protected WorkflowProcessTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.WORKFLOW_PROCESS_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessHeaderType getProcessHeader() {
		return processHeader;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProcessHeader(ProcessHeaderType newProcessHeader, NotificationChain msgs) {
		ProcessHeaderType oldProcessHeader = processHeader;
		processHeader = newProcessHeader;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__PROCESS_HEADER, oldProcessHeader, newProcessHeader);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProcessHeader(ProcessHeaderType newProcessHeader) {
		if (newProcessHeader != processHeader) {
			NotificationChain msgs = null;
			if (processHeader != null)
				msgs = ((InternalEObject)processHeader).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.WORKFLOW_PROCESS_TYPE__PROCESS_HEADER, null, msgs);
			if (newProcessHeader != null)
				msgs = ((InternalEObject)newProcessHeader).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.WORKFLOW_PROCESS_TYPE__PROCESS_HEADER, null, msgs);
			msgs = basicSetProcessHeader(newProcessHeader, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__PROCESS_HEADER, newProcessHeader, newProcessHeader));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RedefinableHeaderType getRedefinableHeader() {
		return redefinableHeader;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRedefinableHeader(RedefinableHeaderType newRedefinableHeader, NotificationChain msgs) {
		RedefinableHeaderType oldRedefinableHeader = redefinableHeader;
		redefinableHeader = newRedefinableHeader;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__REDEFINABLE_HEADER, oldRedefinableHeader, newRedefinableHeader);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRedefinableHeader(RedefinableHeaderType newRedefinableHeader) {
		if (newRedefinableHeader != redefinableHeader) {
			NotificationChain msgs = null;
			if (redefinableHeader != null)
				msgs = ((InternalEObject)redefinableHeader).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.WORKFLOW_PROCESS_TYPE__REDEFINABLE_HEADER, null, msgs);
			if (newRedefinableHeader != null)
				msgs = ((InternalEObject)newRedefinableHeader).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.WORKFLOW_PROCESS_TYPE__REDEFINABLE_HEADER, null, msgs);
			msgs = basicSetRedefinableHeader(newRedefinableHeader, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__REDEFINABLE_HEADER, newRedefinableHeader, newRedefinableHeader));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormalParametersType getFormalParameters() {
		return formalParameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFormalParameters(FormalParametersType newFormalParameters, NotificationChain msgs) {
		FormalParametersType oldFormalParameters = formalParameters;
		formalParameters = newFormalParameters;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__FORMAL_PARAMETERS, oldFormalParameters, newFormalParameters);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFormalParameters(FormalParametersType newFormalParameters) {
		if (newFormalParameters != formalParameters) {
			NotificationChain msgs = null;
			if (formalParameters != null)
				msgs = ((InternalEObject)formalParameters).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.WORKFLOW_PROCESS_TYPE__FORMAL_PARAMETERS, null, msgs);
			if (newFormalParameters != null)
				msgs = ((InternalEObject)newFormalParameters).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.WORKFLOW_PROCESS_TYPE__FORMAL_PARAMETERS, null, msgs);
			msgs = basicSetFormalParameters(newFormalParameters, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__FORMAL_PARAMETERS, newFormalParameters, newFormalParameters));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataFieldsType getDataFields() {
		return dataFields;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataFields(DataFieldsType newDataFields, NotificationChain msgs) {
		DataFieldsType oldDataFields = dataFields;
		dataFields = newDataFields;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__DATA_FIELDS, oldDataFields, newDataFields);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataFields(DataFieldsType newDataFields) {
		if (newDataFields != dataFields) {
			NotificationChain msgs = null;
			if (dataFields != null)
				msgs = ((InternalEObject)dataFields).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.WORKFLOW_PROCESS_TYPE__DATA_FIELDS, null, msgs);
			if (newDataFields != null)
				msgs = ((InternalEObject)newDataFields).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.WORKFLOW_PROCESS_TYPE__DATA_FIELDS, null, msgs);
			msgs = basicSetDataFields(newDataFields, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__DATA_FIELDS, newDataFields, newDataFields));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParticipantsType getParticipants() {
		return participants;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParticipants(ParticipantsType newParticipants, NotificationChain msgs) {
		ParticipantsType oldParticipants = participants;
		participants = newParticipants;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__PARTICIPANTS, oldParticipants, newParticipants);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParticipants(ParticipantsType newParticipants) {
		if (newParticipants != participants) {
			NotificationChain msgs = null;
			if (participants != null)
				msgs = ((InternalEObject)participants).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.WORKFLOW_PROCESS_TYPE__PARTICIPANTS, null, msgs);
			if (newParticipants != null)
				msgs = ((InternalEObject)newParticipants).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.WORKFLOW_PROCESS_TYPE__PARTICIPANTS, null, msgs);
			msgs = basicSetParticipants(newParticipants, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__PARTICIPANTS, newParticipants, newParticipants));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ApplicationsType getApplications() {
		return applications;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetApplications(ApplicationsType newApplications, NotificationChain msgs) {
		ApplicationsType oldApplications = applications;
		applications = newApplications;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__APPLICATIONS, oldApplications, newApplications);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setApplications(ApplicationsType newApplications) {
		if (newApplications != applications) {
			NotificationChain msgs = null;
			if (applications != null)
				msgs = ((InternalEObject)applications).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.WORKFLOW_PROCESS_TYPE__APPLICATIONS, null, msgs);
			if (newApplications != null)
				msgs = ((InternalEObject)newApplications).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.WORKFLOW_PROCESS_TYPE__APPLICATIONS, null, msgs);
			msgs = basicSetApplications(newApplications, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__APPLICATIONS, newApplications, newApplications));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivitySetsType getActivitySets() {
		return activitySets;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActivitySets(ActivitySetsType newActivitySets, NotificationChain msgs) {
		ActivitySetsType oldActivitySets = activitySets;
		activitySets = newActivitySets;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACTIVITY_SETS, oldActivitySets, newActivitySets);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivitySets(ActivitySetsType newActivitySets) {
		if (newActivitySets != activitySets) {
			NotificationChain msgs = null;
			if (activitySets != null)
				msgs = ((InternalEObject)activitySets).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACTIVITY_SETS, null, msgs);
			if (newActivitySets != null)
				msgs = ((InternalEObject)newActivitySets).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACTIVITY_SETS, null, msgs);
			msgs = basicSetActivitySets(newActivitySets, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACTIVITY_SETS, newActivitySets, newActivitySets));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivitiesType getActivities() {
		return activities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetActivities(ActivitiesType newActivities, NotificationChain msgs) {
		ActivitiesType oldActivities = activities;
		activities = newActivities;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACTIVITIES, oldActivities, newActivities);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActivities(ActivitiesType newActivities) {
		if (newActivities != activities) {
			NotificationChain msgs = null;
			if (activities != null)
				msgs = ((InternalEObject)activities).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACTIVITIES, null, msgs);
			if (newActivities != null)
				msgs = ((InternalEObject)newActivities).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACTIVITIES, null, msgs);
			msgs = basicSetActivities(newActivities, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACTIVITIES, newActivities, newActivities));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionsType getTransitions() {
		return transitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTransitions(TransitionsType newTransitions, NotificationChain msgs) {
		TransitionsType oldTransitions = transitions;
		transitions = newTransitions;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__TRANSITIONS, oldTransitions, newTransitions);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransitions(TransitionsType newTransitions) {
		if (newTransitions != transitions) {
			NotificationChain msgs = null;
			if (transitions != null)
				msgs = ((InternalEObject)transitions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.WORKFLOW_PROCESS_TYPE__TRANSITIONS, null, msgs);
			if (newTransitions != null)
				msgs = ((InternalEObject)newTransitions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.WORKFLOW_PROCESS_TYPE__TRANSITIONS, null, msgs);
			msgs = basicSetTransitions(newTransitions, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__TRANSITIONS, newTransitions, newTransitions));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExtendedAttributesType getExtendedAttributes() {
		return extendedAttributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExtendedAttributes(ExtendedAttributesType newExtendedAttributes, NotificationChain msgs) {
		ExtendedAttributesType oldExtendedAttributes = extendedAttributes;
		extendedAttributes = newExtendedAttributes;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__EXTENDED_ATTRIBUTES, oldExtendedAttributes, newExtendedAttributes);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExtendedAttributes(ExtendedAttributesType newExtendedAttributes) {
		if (newExtendedAttributes != extendedAttributes) {
			NotificationChain msgs = null;
			if (extendedAttributes != null)
				msgs = ((InternalEObject)extendedAttributes).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.WORKFLOW_PROCESS_TYPE__EXTENDED_ATTRIBUTES, null, msgs);
			if (newExtendedAttributes != null)
				msgs = ((InternalEObject)newExtendedAttributes).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.WORKFLOW_PROCESS_TYPE__EXTENDED_ATTRIBUTES, null, msgs);
			msgs = basicSetExtendedAttributes(newExtendedAttributes, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__EXTENDED_ATTRIBUTES, newExtendedAttributes, newExtendedAttributes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AccessLevelType getAccessLevel() {
		return accessLevel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAccessLevel(AccessLevelType newAccessLevel) {
		AccessLevelType oldAccessLevel = accessLevel;
		accessLevel = newAccessLevel == null ? ACCESS_LEVEL_EDEFAULT : newAccessLevel;
		boolean oldAccessLevelESet = accessLevelESet;
		accessLevelESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACCESS_LEVEL, oldAccessLevel, accessLevel, !oldAccessLevelESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetAccessLevel() {
		AccessLevelType oldAccessLevel = accessLevel;
		boolean oldAccessLevelESet = accessLevelESet;
		accessLevel = ACCESS_LEVEL_EDEFAULT;
		accessLevelESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACCESS_LEVEL, oldAccessLevel, ACCESS_LEVEL_EDEFAULT, oldAccessLevelESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetAccessLevel() {
		return accessLevelESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.WORKFLOW_PROCESS_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__PROCESS_HEADER:
				return basicSetProcessHeader(null, msgs);
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__REDEFINABLE_HEADER:
				return basicSetRedefinableHeader(null, msgs);
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__FORMAL_PARAMETERS:
				return basicSetFormalParameters(null, msgs);
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__DATA_FIELDS:
				return basicSetDataFields(null, msgs);
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__PARTICIPANTS:
				return basicSetParticipants(null, msgs);
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__APPLICATIONS:
				return basicSetApplications(null, msgs);
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACTIVITY_SETS:
				return basicSetActivitySets(null, msgs);
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACTIVITIES:
				return basicSetActivities(null, msgs);
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__TRANSITIONS:
				return basicSetTransitions(null, msgs);
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__EXTENDED_ATTRIBUTES:
				return basicSetExtendedAttributes(null, msgs);
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
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__PROCESS_HEADER:
				return getProcessHeader();
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__REDEFINABLE_HEADER:
				return getRedefinableHeader();
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__FORMAL_PARAMETERS:
				return getFormalParameters();
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__DATA_FIELDS:
				return getDataFields();
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__PARTICIPANTS:
				return getParticipants();
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__APPLICATIONS:
				return getApplications();
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACTIVITY_SETS:
				return getActivitySets();
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACTIVITIES:
				return getActivities();
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__TRANSITIONS:
				return getTransitions();
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__EXTENDED_ATTRIBUTES:
				return getExtendedAttributes();
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACCESS_LEVEL:
				return getAccessLevel();
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__ID:
				return getId();
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__NAME:
				return getName();
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
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__PROCESS_HEADER:
				setProcessHeader((ProcessHeaderType)newValue);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__REDEFINABLE_HEADER:
				setRedefinableHeader((RedefinableHeaderType)newValue);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__FORMAL_PARAMETERS:
				setFormalParameters((FormalParametersType)newValue);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__DATA_FIELDS:
				setDataFields((DataFieldsType)newValue);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__PARTICIPANTS:
				setParticipants((ParticipantsType)newValue);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__APPLICATIONS:
				setApplications((ApplicationsType)newValue);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACTIVITY_SETS:
				setActivitySets((ActivitySetsType)newValue);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACTIVITIES:
				setActivities((ActivitiesType)newValue);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__TRANSITIONS:
				setTransitions((TransitionsType)newValue);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__EXTENDED_ATTRIBUTES:
				setExtendedAttributes((ExtendedAttributesType)newValue);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACCESS_LEVEL:
				setAccessLevel((AccessLevelType)newValue);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__ID:
				setId((String)newValue);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__NAME:
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
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__PROCESS_HEADER:
				setProcessHeader((ProcessHeaderType)null);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__REDEFINABLE_HEADER:
				setRedefinableHeader((RedefinableHeaderType)null);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__FORMAL_PARAMETERS:
				setFormalParameters((FormalParametersType)null);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__DATA_FIELDS:
				setDataFields((DataFieldsType)null);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__PARTICIPANTS:
				setParticipants((ParticipantsType)null);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__APPLICATIONS:
				setApplications((ApplicationsType)null);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACTIVITY_SETS:
				setActivitySets((ActivitySetsType)null);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACTIVITIES:
				setActivities((ActivitiesType)null);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__TRANSITIONS:
				setTransitions((TransitionsType)null);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__EXTENDED_ATTRIBUTES:
				setExtendedAttributes((ExtendedAttributesType)null);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACCESS_LEVEL:
				unsetAccessLevel();
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__ID:
				setId(ID_EDEFAULT);
				return;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__NAME:
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
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__PROCESS_HEADER:
				return processHeader != null;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__REDEFINABLE_HEADER:
				return redefinableHeader != null;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__FORMAL_PARAMETERS:
				return formalParameters != null;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__DATA_FIELDS:
				return dataFields != null;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__PARTICIPANTS:
				return participants != null;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__APPLICATIONS:
				return applications != null;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACTIVITY_SETS:
				return activitySets != null;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACTIVITIES:
				return activities != null;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__TRANSITIONS:
				return transitions != null;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__EXTENDED_ATTRIBUTES:
				return extendedAttributes != null;
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__ACCESS_LEVEL:
				return isSetAccessLevel();
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case Xpdl1Package.WORKFLOW_PROCESS_TYPE__NAME:
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
		result.append(" (accessLevel: ");
		if (accessLevelESet) result.append(accessLevel); else result.append("<unset>");
		result.append(", id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //WorkflowProcessTypeImpl
