/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.wfmc._2002.xpdl1.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.wfmc._2002.xpdl1.ActivityType;
import org.wfmc._2002.xpdl1.BlockActivityType;
import org.wfmc._2002.xpdl1.DeadlineType;
import org.wfmc._2002.xpdl1.ExtendedAttributesType;
import org.wfmc._2002.xpdl1.FinishModeType;
import org.wfmc._2002.xpdl1.ImplementationType;
import org.wfmc._2002.xpdl1.RouteType;
import org.wfmc._2002.xpdl1.SimulationInformationType;
import org.wfmc._2002.xpdl1.StartModeType;
import org.wfmc._2002.xpdl1.TransitionRestrictionsType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Activity Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ActivityTypeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ActivityTypeImpl#getLimit <em>Limit</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ActivityTypeImpl#getRoute <em>Route</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ActivityTypeImpl#getImplementation <em>Implementation</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ActivityTypeImpl#getBlockActivity <em>Block Activity</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ActivityTypeImpl#getPerformer <em>Performer</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ActivityTypeImpl#getStartMode <em>Start Mode</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ActivityTypeImpl#getFinishMode <em>Finish Mode</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ActivityTypeImpl#getPriority <em>Priority</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ActivityTypeImpl#getDeadline <em>Deadline</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ActivityTypeImpl#getSimulationInformation <em>Simulation Information</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ActivityTypeImpl#getIcon <em>Icon</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ActivityTypeImpl#getDocumentation <em>Documentation</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ActivityTypeImpl#getTransitionRestrictions <em>Transition Restrictions</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ActivityTypeImpl#getExtendedAttributes <em>Extended Attributes</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ActivityTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.ActivityTypeImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActivityTypeImpl extends EObjectImpl implements ActivityType {
	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getLimit() <em>Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLimit()
	 * @generated
	 * @ordered
	 */
	protected static final String LIMIT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLimit() <em>Limit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLimit()
	 * @generated
	 * @ordered
	 */
	protected String limit = LIMIT_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRoute() <em>Route</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoute()
	 * @generated
	 * @ordered
	 */
	protected RouteType route;

	/**
	 * The cached value of the '{@link #getImplementation() <em>Implementation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImplementation()
	 * @generated
	 * @ordered
	 */
	protected ImplementationType implementation;

	/**
	 * The cached value of the '{@link #getBlockActivity() <em>Block Activity</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBlockActivity()
	 * @generated
	 * @ordered
	 */
	protected BlockActivityType blockActivity;

	/**
	 * The default value of the '{@link #getPerformer() <em>Performer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerformer()
	 * @generated
	 * @ordered
	 */
	protected static final String PERFORMER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPerformer() <em>Performer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPerformer()
	 * @generated
	 * @ordered
	 */
	protected String performer = PERFORMER_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStartMode() <em>Start Mode</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStartMode()
	 * @generated
	 * @ordered
	 */
	protected StartModeType startMode;

	/**
	 * The cached value of the '{@link #getFinishMode() <em>Finish Mode</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFinishMode()
	 * @generated
	 * @ordered
	 */
	protected FinishModeType finishMode;

	/**
	 * The default value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected static final String PRIORITY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPriority() <em>Priority</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPriority()
	 * @generated
	 * @ordered
	 */
	protected String priority = PRIORITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDeadline() <em>Deadline</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeadline()
	 * @generated
	 * @ordered
	 */
	protected EList<DeadlineType> deadline;

	/**
	 * The cached value of the '{@link #getSimulationInformation() <em>Simulation Information</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSimulationInformation()
	 * @generated
	 * @ordered
	 */
	protected SimulationInformationType simulationInformation;

	/**
	 * The default value of the '{@link #getIcon() <em>Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcon()
	 * @generated
	 * @ordered
	 */
	protected static final String ICON_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIcon() <em>Icon</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcon()
	 * @generated
	 * @ordered
	 */
	protected String icon = ICON_EDEFAULT;

	/**
	 * The default value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected static final String DOCUMENTATION_EDEFAULT = null;

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
	 * The cached value of the '{@link #getTransitionRestrictions() <em>Transition Restrictions</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransitionRestrictions()
	 * @generated
	 * @ordered
	 */
	protected TransitionRestrictionsType transitionRestrictions;

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
	protected ActivityTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.ACTIVITY_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLimit() {
		return limit;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLimit(String newLimit) {
		String oldLimit = limit;
		limit = newLimit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__LIMIT, oldLimit, limit));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RouteType getRoute() {
		return route;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRoute(RouteType newRoute, NotificationChain msgs) {
		RouteType oldRoute = route;
		route = newRoute;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__ROUTE, oldRoute, newRoute);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoute(RouteType newRoute) {
		if (newRoute != route) {
			NotificationChain msgs = null;
			if (route != null)
				msgs = ((InternalEObject)route).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.ACTIVITY_TYPE__ROUTE, null, msgs);
			if (newRoute != null)
				msgs = ((InternalEObject)newRoute).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.ACTIVITY_TYPE__ROUTE, null, msgs);
			msgs = basicSetRoute(newRoute, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__ROUTE, newRoute, newRoute));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImplementationType getImplementation() {
		return implementation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetImplementation(ImplementationType newImplementation, NotificationChain msgs) {
		ImplementationType oldImplementation = implementation;
		implementation = newImplementation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__IMPLEMENTATION, oldImplementation, newImplementation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImplementation(ImplementationType newImplementation) {
		if (newImplementation != implementation) {
			NotificationChain msgs = null;
			if (implementation != null)
				msgs = ((InternalEObject)implementation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.ACTIVITY_TYPE__IMPLEMENTATION, null, msgs);
			if (newImplementation != null)
				msgs = ((InternalEObject)newImplementation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.ACTIVITY_TYPE__IMPLEMENTATION, null, msgs);
			msgs = basicSetImplementation(newImplementation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__IMPLEMENTATION, newImplementation, newImplementation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BlockActivityType getBlockActivity() {
		return blockActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBlockActivity(BlockActivityType newBlockActivity, NotificationChain msgs) {
		BlockActivityType oldBlockActivity = blockActivity;
		blockActivity = newBlockActivity;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__BLOCK_ACTIVITY, oldBlockActivity, newBlockActivity);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBlockActivity(BlockActivityType newBlockActivity) {
		if (newBlockActivity != blockActivity) {
			NotificationChain msgs = null;
			if (blockActivity != null)
				msgs = ((InternalEObject)blockActivity).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.ACTIVITY_TYPE__BLOCK_ACTIVITY, null, msgs);
			if (newBlockActivity != null)
				msgs = ((InternalEObject)newBlockActivity).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.ACTIVITY_TYPE__BLOCK_ACTIVITY, null, msgs);
			msgs = basicSetBlockActivity(newBlockActivity, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__BLOCK_ACTIVITY, newBlockActivity, newBlockActivity));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPerformer() {
		return performer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPerformer(String newPerformer) {
		String oldPerformer = performer;
		performer = newPerformer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__PERFORMER, oldPerformer, performer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StartModeType getStartMode() {
		return startMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStartMode(StartModeType newStartMode, NotificationChain msgs) {
		StartModeType oldStartMode = startMode;
		startMode = newStartMode;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__START_MODE, oldStartMode, newStartMode);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStartMode(StartModeType newStartMode) {
		if (newStartMode != startMode) {
			NotificationChain msgs = null;
			if (startMode != null)
				msgs = ((InternalEObject)startMode).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.ACTIVITY_TYPE__START_MODE, null, msgs);
			if (newStartMode != null)
				msgs = ((InternalEObject)newStartMode).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.ACTIVITY_TYPE__START_MODE, null, msgs);
			msgs = basicSetStartMode(newStartMode, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__START_MODE, newStartMode, newStartMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FinishModeType getFinishMode() {
		return finishMode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFinishMode(FinishModeType newFinishMode, NotificationChain msgs) {
		FinishModeType oldFinishMode = finishMode;
		finishMode = newFinishMode;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__FINISH_MODE, oldFinishMode, newFinishMode);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFinishMode(FinishModeType newFinishMode) {
		if (newFinishMode != finishMode) {
			NotificationChain msgs = null;
			if (finishMode != null)
				msgs = ((InternalEObject)finishMode).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.ACTIVITY_TYPE__FINISH_MODE, null, msgs);
			if (newFinishMode != null)
				msgs = ((InternalEObject)newFinishMode).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.ACTIVITY_TYPE__FINISH_MODE, null, msgs);
			msgs = basicSetFinishMode(newFinishMode, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__FINISH_MODE, newFinishMode, newFinishMode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPriority(String newPriority) {
		String oldPriority = priority;
		priority = newPriority;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__PRIORITY, oldPriority, priority));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DeadlineType> getDeadline() {
		if (deadline == null) {
			deadline = new EObjectContainmentEList<DeadlineType>(DeadlineType.class, this, Xpdl1Package.ACTIVITY_TYPE__DEADLINE);
		}
		return deadline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimulationInformationType getSimulationInformation() {
		return simulationInformation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSimulationInformation(SimulationInformationType newSimulationInformation, NotificationChain msgs) {
		SimulationInformationType oldSimulationInformation = simulationInformation;
		simulationInformation = newSimulationInformation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__SIMULATION_INFORMATION, oldSimulationInformation, newSimulationInformation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSimulationInformation(SimulationInformationType newSimulationInformation) {
		if (newSimulationInformation != simulationInformation) {
			NotificationChain msgs = null;
			if (simulationInformation != null)
				msgs = ((InternalEObject)simulationInformation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.ACTIVITY_TYPE__SIMULATION_INFORMATION, null, msgs);
			if (newSimulationInformation != null)
				msgs = ((InternalEObject)newSimulationInformation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.ACTIVITY_TYPE__SIMULATION_INFORMATION, null, msgs);
			msgs = basicSetSimulationInformation(newSimulationInformation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__SIMULATION_INFORMATION, newSimulationInformation, newSimulationInformation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIcon(String newIcon) {
		String oldIcon = icon;
		icon = newIcon;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__ICON, oldIcon, icon));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDocumentation() {
		return documentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDocumentation(String newDocumentation) {
		String oldDocumentation = documentation;
		documentation = newDocumentation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__DOCUMENTATION, oldDocumentation, documentation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransitionRestrictionsType getTransitionRestrictions() {
		return transitionRestrictions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTransitionRestrictions(TransitionRestrictionsType newTransitionRestrictions, NotificationChain msgs) {
		TransitionRestrictionsType oldTransitionRestrictions = transitionRestrictions;
		transitionRestrictions = newTransitionRestrictions;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__TRANSITION_RESTRICTIONS, oldTransitionRestrictions, newTransitionRestrictions);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransitionRestrictions(TransitionRestrictionsType newTransitionRestrictions) {
		if (newTransitionRestrictions != transitionRestrictions) {
			NotificationChain msgs = null;
			if (transitionRestrictions != null)
				msgs = ((InternalEObject)transitionRestrictions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.ACTIVITY_TYPE__TRANSITION_RESTRICTIONS, null, msgs);
			if (newTransitionRestrictions != null)
				msgs = ((InternalEObject)newTransitionRestrictions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.ACTIVITY_TYPE__TRANSITION_RESTRICTIONS, null, msgs);
			msgs = basicSetTransitionRestrictions(newTransitionRestrictions, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__TRANSITION_RESTRICTIONS, newTransitionRestrictions, newTransitionRestrictions));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__EXTENDED_ATTRIBUTES, oldExtendedAttributes, newExtendedAttributes);
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
				msgs = ((InternalEObject)extendedAttributes).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.ACTIVITY_TYPE__EXTENDED_ATTRIBUTES, null, msgs);
			if (newExtendedAttributes != null)
				msgs = ((InternalEObject)newExtendedAttributes).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.ACTIVITY_TYPE__EXTENDED_ATTRIBUTES, null, msgs);
			msgs = basicSetExtendedAttributes(newExtendedAttributes, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__EXTENDED_ATTRIBUTES, newExtendedAttributes, newExtendedAttributes));
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
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.ACTIVITY_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Xpdl1Package.ACTIVITY_TYPE__ROUTE:
				return basicSetRoute(null, msgs);
			case Xpdl1Package.ACTIVITY_TYPE__IMPLEMENTATION:
				return basicSetImplementation(null, msgs);
			case Xpdl1Package.ACTIVITY_TYPE__BLOCK_ACTIVITY:
				return basicSetBlockActivity(null, msgs);
			case Xpdl1Package.ACTIVITY_TYPE__START_MODE:
				return basicSetStartMode(null, msgs);
			case Xpdl1Package.ACTIVITY_TYPE__FINISH_MODE:
				return basicSetFinishMode(null, msgs);
			case Xpdl1Package.ACTIVITY_TYPE__DEADLINE:
				return ((InternalEList<?>)getDeadline()).basicRemove(otherEnd, msgs);
			case Xpdl1Package.ACTIVITY_TYPE__SIMULATION_INFORMATION:
				return basicSetSimulationInformation(null, msgs);
			case Xpdl1Package.ACTIVITY_TYPE__TRANSITION_RESTRICTIONS:
				return basicSetTransitionRestrictions(null, msgs);
			case Xpdl1Package.ACTIVITY_TYPE__EXTENDED_ATTRIBUTES:
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
			case Xpdl1Package.ACTIVITY_TYPE__DESCRIPTION:
				return getDescription();
			case Xpdl1Package.ACTIVITY_TYPE__LIMIT:
				return getLimit();
			case Xpdl1Package.ACTIVITY_TYPE__ROUTE:
				return getRoute();
			case Xpdl1Package.ACTIVITY_TYPE__IMPLEMENTATION:
				return getImplementation();
			case Xpdl1Package.ACTIVITY_TYPE__BLOCK_ACTIVITY:
				return getBlockActivity();
			case Xpdl1Package.ACTIVITY_TYPE__PERFORMER:
				return getPerformer();
			case Xpdl1Package.ACTIVITY_TYPE__START_MODE:
				return getStartMode();
			case Xpdl1Package.ACTIVITY_TYPE__FINISH_MODE:
				return getFinishMode();
			case Xpdl1Package.ACTIVITY_TYPE__PRIORITY:
				return getPriority();
			case Xpdl1Package.ACTIVITY_TYPE__DEADLINE:
				return getDeadline();
			case Xpdl1Package.ACTIVITY_TYPE__SIMULATION_INFORMATION:
				return getSimulationInformation();
			case Xpdl1Package.ACTIVITY_TYPE__ICON:
				return getIcon();
			case Xpdl1Package.ACTIVITY_TYPE__DOCUMENTATION:
				return getDocumentation();
			case Xpdl1Package.ACTIVITY_TYPE__TRANSITION_RESTRICTIONS:
				return getTransitionRestrictions();
			case Xpdl1Package.ACTIVITY_TYPE__EXTENDED_ATTRIBUTES:
				return getExtendedAttributes();
			case Xpdl1Package.ACTIVITY_TYPE__ID:
				return getId();
			case Xpdl1Package.ACTIVITY_TYPE__NAME:
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
			case Xpdl1Package.ACTIVITY_TYPE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__LIMIT:
				setLimit((String)newValue);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__ROUTE:
				setRoute((RouteType)newValue);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__IMPLEMENTATION:
				setImplementation((ImplementationType)newValue);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__BLOCK_ACTIVITY:
				setBlockActivity((BlockActivityType)newValue);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__PERFORMER:
				setPerformer((String)newValue);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__START_MODE:
				setStartMode((StartModeType)newValue);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__FINISH_MODE:
				setFinishMode((FinishModeType)newValue);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__PRIORITY:
				setPriority((String)newValue);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__DEADLINE:
				getDeadline().clear();
				getDeadline().addAll((Collection<? extends DeadlineType>)newValue);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__SIMULATION_INFORMATION:
				setSimulationInformation((SimulationInformationType)newValue);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__ICON:
				setIcon((String)newValue);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__DOCUMENTATION:
				setDocumentation((String)newValue);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__TRANSITION_RESTRICTIONS:
				setTransitionRestrictions((TransitionRestrictionsType)newValue);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__EXTENDED_ATTRIBUTES:
				setExtendedAttributes((ExtendedAttributesType)newValue);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__ID:
				setId((String)newValue);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__NAME:
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
			case Xpdl1Package.ACTIVITY_TYPE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__LIMIT:
				setLimit(LIMIT_EDEFAULT);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__ROUTE:
				setRoute((RouteType)null);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__IMPLEMENTATION:
				setImplementation((ImplementationType)null);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__BLOCK_ACTIVITY:
				setBlockActivity((BlockActivityType)null);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__PERFORMER:
				setPerformer(PERFORMER_EDEFAULT);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__START_MODE:
				setStartMode((StartModeType)null);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__FINISH_MODE:
				setFinishMode((FinishModeType)null);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__PRIORITY:
				setPriority(PRIORITY_EDEFAULT);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__DEADLINE:
				getDeadline().clear();
				return;
			case Xpdl1Package.ACTIVITY_TYPE__SIMULATION_INFORMATION:
				setSimulationInformation((SimulationInformationType)null);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__ICON:
				setIcon(ICON_EDEFAULT);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__DOCUMENTATION:
				setDocumentation(DOCUMENTATION_EDEFAULT);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__TRANSITION_RESTRICTIONS:
				setTransitionRestrictions((TransitionRestrictionsType)null);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__EXTENDED_ATTRIBUTES:
				setExtendedAttributes((ExtendedAttributesType)null);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__ID:
				setId(ID_EDEFAULT);
				return;
			case Xpdl1Package.ACTIVITY_TYPE__NAME:
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
			case Xpdl1Package.ACTIVITY_TYPE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case Xpdl1Package.ACTIVITY_TYPE__LIMIT:
				return LIMIT_EDEFAULT == null ? limit != null : !LIMIT_EDEFAULT.equals(limit);
			case Xpdl1Package.ACTIVITY_TYPE__ROUTE:
				return route != null;
			case Xpdl1Package.ACTIVITY_TYPE__IMPLEMENTATION:
				return implementation != null;
			case Xpdl1Package.ACTIVITY_TYPE__BLOCK_ACTIVITY:
				return blockActivity != null;
			case Xpdl1Package.ACTIVITY_TYPE__PERFORMER:
				return PERFORMER_EDEFAULT == null ? performer != null : !PERFORMER_EDEFAULT.equals(performer);
			case Xpdl1Package.ACTIVITY_TYPE__START_MODE:
				return startMode != null;
			case Xpdl1Package.ACTIVITY_TYPE__FINISH_MODE:
				return finishMode != null;
			case Xpdl1Package.ACTIVITY_TYPE__PRIORITY:
				return PRIORITY_EDEFAULT == null ? priority != null : !PRIORITY_EDEFAULT.equals(priority);
			case Xpdl1Package.ACTIVITY_TYPE__DEADLINE:
				return deadline != null && !deadline.isEmpty();
			case Xpdl1Package.ACTIVITY_TYPE__SIMULATION_INFORMATION:
				return simulationInformation != null;
			case Xpdl1Package.ACTIVITY_TYPE__ICON:
				return ICON_EDEFAULT == null ? icon != null : !ICON_EDEFAULT.equals(icon);
			case Xpdl1Package.ACTIVITY_TYPE__DOCUMENTATION:
				return DOCUMENTATION_EDEFAULT == null ? documentation != null : !DOCUMENTATION_EDEFAULT.equals(documentation);
			case Xpdl1Package.ACTIVITY_TYPE__TRANSITION_RESTRICTIONS:
				return transitionRestrictions != null;
			case Xpdl1Package.ACTIVITY_TYPE__EXTENDED_ATTRIBUTES:
				return extendedAttributes != null;
			case Xpdl1Package.ACTIVITY_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case Xpdl1Package.ACTIVITY_TYPE__NAME:
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
		result.append(" (description: ");
		result.append(description);
		result.append(", limit: ");
		result.append(limit);
		result.append(", performer: ");
		result.append(performer);
		result.append(", priority: ");
		result.append(priority);
		result.append(", icon: ");
		result.append(icon);
		result.append(", documentation: ");
		result.append(documentation);
		result.append(", id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //ActivityTypeImpl
