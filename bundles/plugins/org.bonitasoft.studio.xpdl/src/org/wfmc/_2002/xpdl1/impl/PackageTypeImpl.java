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
import org.wfmc._2002.xpdl1.ApplicationsType;
import org.wfmc._2002.xpdl1.ConformanceClassType;
import org.wfmc._2002.xpdl1.DataFieldsType;
import org.wfmc._2002.xpdl1.ExtendedAttributesType;
import org.wfmc._2002.xpdl1.ExternalPackagesType;
import org.wfmc._2002.xpdl1.PackageHeaderType;
import org.wfmc._2002.xpdl1.PackageType;
import org.wfmc._2002.xpdl1.ParticipantsType;
import org.wfmc._2002.xpdl1.RedefinableHeaderType;
import org.wfmc._2002.xpdl1.ScriptType;
import org.wfmc._2002.xpdl1.TypeDeclarationsType;
import org.wfmc._2002.xpdl1.WorkflowProcessesType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.PackageTypeImpl#getPackageHeader <em>Package Header</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.PackageTypeImpl#getRedefinableHeader <em>Redefinable Header</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.PackageTypeImpl#getConformanceClass <em>Conformance Class</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.PackageTypeImpl#getScript <em>Script</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.PackageTypeImpl#getExternalPackages <em>External Packages</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.PackageTypeImpl#getTypeDeclarations <em>Type Declarations</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.PackageTypeImpl#getParticipants <em>Participants</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.PackageTypeImpl#getApplications <em>Applications</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.PackageTypeImpl#getDataFields <em>Data Fields</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.PackageTypeImpl#getWorkflowProcesses <em>Workflow Processes</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.PackageTypeImpl#getExtendedAttributes <em>Extended Attributes</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.PackageTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.PackageTypeImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackageTypeImpl extends EObjectImpl implements PackageType {
	/**
	 * The cached value of the '{@link #getPackageHeader() <em>Package Header</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageHeader()
	 * @generated
	 * @ordered
	 */
	protected PackageHeaderType packageHeader;

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
	 * The cached value of the '{@link #getConformanceClass() <em>Conformance Class</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConformanceClass()
	 * @generated
	 * @ordered
	 */
	protected ConformanceClassType conformanceClass;

	/**
	 * The cached value of the '{@link #getScript() <em>Script</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScript()
	 * @generated
	 * @ordered
	 */
	protected ScriptType script;

	/**
	 * The cached value of the '{@link #getExternalPackages() <em>External Packages</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExternalPackages()
	 * @generated
	 * @ordered
	 */
	protected ExternalPackagesType externalPackages;

	/**
	 * The cached value of the '{@link #getTypeDeclarations() <em>Type Declarations</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypeDeclarations()
	 * @generated
	 * @ordered
	 */
	protected TypeDeclarationsType typeDeclarations;

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
	 * The cached value of the '{@link #getDataFields() <em>Data Fields</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataFields()
	 * @generated
	 * @ordered
	 */
	protected DataFieldsType dataFields;

	/**
	 * The cached value of the '{@link #getWorkflowProcesses() <em>Workflow Processes</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorkflowProcesses()
	 * @generated
	 * @ordered
	 */
	protected WorkflowProcessesType workflowProcesses;

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
	protected PackageTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.PACKAGE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageHeaderType getPackageHeader() {
		return packageHeader;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPackageHeader(PackageHeaderType newPackageHeader, NotificationChain msgs) {
		PackageHeaderType oldPackageHeader = packageHeader;
		packageHeader = newPackageHeader;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__PACKAGE_HEADER, oldPackageHeader, newPackageHeader);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackageHeader(PackageHeaderType newPackageHeader) {
		if (newPackageHeader != packageHeader) {
			NotificationChain msgs = null;
			if (packageHeader != null)
				msgs = ((InternalEObject)packageHeader).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PACKAGE_TYPE__PACKAGE_HEADER, null, msgs);
			if (newPackageHeader != null)
				msgs = ((InternalEObject)newPackageHeader).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PACKAGE_TYPE__PACKAGE_HEADER, null, msgs);
			msgs = basicSetPackageHeader(newPackageHeader, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__PACKAGE_HEADER, newPackageHeader, newPackageHeader));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__REDEFINABLE_HEADER, oldRedefinableHeader, newRedefinableHeader);
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
				msgs = ((InternalEObject)redefinableHeader).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PACKAGE_TYPE__REDEFINABLE_HEADER, null, msgs);
			if (newRedefinableHeader != null)
				msgs = ((InternalEObject)newRedefinableHeader).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PACKAGE_TYPE__REDEFINABLE_HEADER, null, msgs);
			msgs = basicSetRedefinableHeader(newRedefinableHeader, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__REDEFINABLE_HEADER, newRedefinableHeader, newRedefinableHeader));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConformanceClassType getConformanceClass() {
		return conformanceClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConformanceClass(ConformanceClassType newConformanceClass, NotificationChain msgs) {
		ConformanceClassType oldConformanceClass = conformanceClass;
		conformanceClass = newConformanceClass;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__CONFORMANCE_CLASS, oldConformanceClass, newConformanceClass);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConformanceClass(ConformanceClassType newConformanceClass) {
		if (newConformanceClass != conformanceClass) {
			NotificationChain msgs = null;
			if (conformanceClass != null)
				msgs = ((InternalEObject)conformanceClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PACKAGE_TYPE__CONFORMANCE_CLASS, null, msgs);
			if (newConformanceClass != null)
				msgs = ((InternalEObject)newConformanceClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PACKAGE_TYPE__CONFORMANCE_CLASS, null, msgs);
			msgs = basicSetConformanceClass(newConformanceClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__CONFORMANCE_CLASS, newConformanceClass, newConformanceClass));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScriptType getScript() {
		return script;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScript(ScriptType newScript, NotificationChain msgs) {
		ScriptType oldScript = script;
		script = newScript;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__SCRIPT, oldScript, newScript);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScript(ScriptType newScript) {
		if (newScript != script) {
			NotificationChain msgs = null;
			if (script != null)
				msgs = ((InternalEObject)script).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PACKAGE_TYPE__SCRIPT, null, msgs);
			if (newScript != null)
				msgs = ((InternalEObject)newScript).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PACKAGE_TYPE__SCRIPT, null, msgs);
			msgs = basicSetScript(newScript, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__SCRIPT, newScript, newScript));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalPackagesType getExternalPackages() {
		return externalPackages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetExternalPackages(ExternalPackagesType newExternalPackages, NotificationChain msgs) {
		ExternalPackagesType oldExternalPackages = externalPackages;
		externalPackages = newExternalPackages;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__EXTERNAL_PACKAGES, oldExternalPackages, newExternalPackages);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setExternalPackages(ExternalPackagesType newExternalPackages) {
		if (newExternalPackages != externalPackages) {
			NotificationChain msgs = null;
			if (externalPackages != null)
				msgs = ((InternalEObject)externalPackages).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PACKAGE_TYPE__EXTERNAL_PACKAGES, null, msgs);
			if (newExternalPackages != null)
				msgs = ((InternalEObject)newExternalPackages).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PACKAGE_TYPE__EXTERNAL_PACKAGES, null, msgs);
			msgs = basicSetExternalPackages(newExternalPackages, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__EXTERNAL_PACKAGES, newExternalPackages, newExternalPackages));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeDeclarationsType getTypeDeclarations() {
		return typeDeclarations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTypeDeclarations(TypeDeclarationsType newTypeDeclarations, NotificationChain msgs) {
		TypeDeclarationsType oldTypeDeclarations = typeDeclarations;
		typeDeclarations = newTypeDeclarations;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__TYPE_DECLARATIONS, oldTypeDeclarations, newTypeDeclarations);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypeDeclarations(TypeDeclarationsType newTypeDeclarations) {
		if (newTypeDeclarations != typeDeclarations) {
			NotificationChain msgs = null;
			if (typeDeclarations != null)
				msgs = ((InternalEObject)typeDeclarations).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PACKAGE_TYPE__TYPE_DECLARATIONS, null, msgs);
			if (newTypeDeclarations != null)
				msgs = ((InternalEObject)newTypeDeclarations).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PACKAGE_TYPE__TYPE_DECLARATIONS, null, msgs);
			msgs = basicSetTypeDeclarations(newTypeDeclarations, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__TYPE_DECLARATIONS, newTypeDeclarations, newTypeDeclarations));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__PARTICIPANTS, oldParticipants, newParticipants);
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
				msgs = ((InternalEObject)participants).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PACKAGE_TYPE__PARTICIPANTS, null, msgs);
			if (newParticipants != null)
				msgs = ((InternalEObject)newParticipants).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PACKAGE_TYPE__PARTICIPANTS, null, msgs);
			msgs = basicSetParticipants(newParticipants, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__PARTICIPANTS, newParticipants, newParticipants));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__APPLICATIONS, oldApplications, newApplications);
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
				msgs = ((InternalEObject)applications).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PACKAGE_TYPE__APPLICATIONS, null, msgs);
			if (newApplications != null)
				msgs = ((InternalEObject)newApplications).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PACKAGE_TYPE__APPLICATIONS, null, msgs);
			msgs = basicSetApplications(newApplications, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__APPLICATIONS, newApplications, newApplications));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__DATA_FIELDS, oldDataFields, newDataFields);
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
				msgs = ((InternalEObject)dataFields).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PACKAGE_TYPE__DATA_FIELDS, null, msgs);
			if (newDataFields != null)
				msgs = ((InternalEObject)newDataFields).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PACKAGE_TYPE__DATA_FIELDS, null, msgs);
			msgs = basicSetDataFields(newDataFields, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__DATA_FIELDS, newDataFields, newDataFields));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorkflowProcessesType getWorkflowProcesses() {
		return workflowProcesses;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWorkflowProcesses(WorkflowProcessesType newWorkflowProcesses, NotificationChain msgs) {
		WorkflowProcessesType oldWorkflowProcesses = workflowProcesses;
		workflowProcesses = newWorkflowProcesses;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__WORKFLOW_PROCESSES, oldWorkflowProcesses, newWorkflowProcesses);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkflowProcesses(WorkflowProcessesType newWorkflowProcesses) {
		if (newWorkflowProcesses != workflowProcesses) {
			NotificationChain msgs = null;
			if (workflowProcesses != null)
				msgs = ((InternalEObject)workflowProcesses).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PACKAGE_TYPE__WORKFLOW_PROCESSES, null, msgs);
			if (newWorkflowProcesses != null)
				msgs = ((InternalEObject)newWorkflowProcesses).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PACKAGE_TYPE__WORKFLOW_PROCESSES, null, msgs);
			msgs = basicSetWorkflowProcesses(newWorkflowProcesses, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__WORKFLOW_PROCESSES, newWorkflowProcesses, newWorkflowProcesses));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__EXTENDED_ATTRIBUTES, oldExtendedAttributes, newExtendedAttributes);
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
				msgs = ((InternalEObject)extendedAttributes).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PACKAGE_TYPE__EXTENDED_ATTRIBUTES, null, msgs);
			if (newExtendedAttributes != null)
				msgs = ((InternalEObject)newExtendedAttributes).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.PACKAGE_TYPE__EXTENDED_ATTRIBUTES, null, msgs);
			msgs = basicSetExtendedAttributes(newExtendedAttributes, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__EXTENDED_ATTRIBUTES, newExtendedAttributes, newExtendedAttributes));
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
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.PACKAGE_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Xpdl1Package.PACKAGE_TYPE__PACKAGE_HEADER:
				return basicSetPackageHeader(null, msgs);
			case Xpdl1Package.PACKAGE_TYPE__REDEFINABLE_HEADER:
				return basicSetRedefinableHeader(null, msgs);
			case Xpdl1Package.PACKAGE_TYPE__CONFORMANCE_CLASS:
				return basicSetConformanceClass(null, msgs);
			case Xpdl1Package.PACKAGE_TYPE__SCRIPT:
				return basicSetScript(null, msgs);
			case Xpdl1Package.PACKAGE_TYPE__EXTERNAL_PACKAGES:
				return basicSetExternalPackages(null, msgs);
			case Xpdl1Package.PACKAGE_TYPE__TYPE_DECLARATIONS:
				return basicSetTypeDeclarations(null, msgs);
			case Xpdl1Package.PACKAGE_TYPE__PARTICIPANTS:
				return basicSetParticipants(null, msgs);
			case Xpdl1Package.PACKAGE_TYPE__APPLICATIONS:
				return basicSetApplications(null, msgs);
			case Xpdl1Package.PACKAGE_TYPE__DATA_FIELDS:
				return basicSetDataFields(null, msgs);
			case Xpdl1Package.PACKAGE_TYPE__WORKFLOW_PROCESSES:
				return basicSetWorkflowProcesses(null, msgs);
			case Xpdl1Package.PACKAGE_TYPE__EXTENDED_ATTRIBUTES:
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
			case Xpdl1Package.PACKAGE_TYPE__PACKAGE_HEADER:
				return getPackageHeader();
			case Xpdl1Package.PACKAGE_TYPE__REDEFINABLE_HEADER:
				return getRedefinableHeader();
			case Xpdl1Package.PACKAGE_TYPE__CONFORMANCE_CLASS:
				return getConformanceClass();
			case Xpdl1Package.PACKAGE_TYPE__SCRIPT:
				return getScript();
			case Xpdl1Package.PACKAGE_TYPE__EXTERNAL_PACKAGES:
				return getExternalPackages();
			case Xpdl1Package.PACKAGE_TYPE__TYPE_DECLARATIONS:
				return getTypeDeclarations();
			case Xpdl1Package.PACKAGE_TYPE__PARTICIPANTS:
				return getParticipants();
			case Xpdl1Package.PACKAGE_TYPE__APPLICATIONS:
				return getApplications();
			case Xpdl1Package.PACKAGE_TYPE__DATA_FIELDS:
				return getDataFields();
			case Xpdl1Package.PACKAGE_TYPE__WORKFLOW_PROCESSES:
				return getWorkflowProcesses();
			case Xpdl1Package.PACKAGE_TYPE__EXTENDED_ATTRIBUTES:
				return getExtendedAttributes();
			case Xpdl1Package.PACKAGE_TYPE__ID:
				return getId();
			case Xpdl1Package.PACKAGE_TYPE__NAME:
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
			case Xpdl1Package.PACKAGE_TYPE__PACKAGE_HEADER:
				setPackageHeader((PackageHeaderType)newValue);
				return;
			case Xpdl1Package.PACKAGE_TYPE__REDEFINABLE_HEADER:
				setRedefinableHeader((RedefinableHeaderType)newValue);
				return;
			case Xpdl1Package.PACKAGE_TYPE__CONFORMANCE_CLASS:
				setConformanceClass((ConformanceClassType)newValue);
				return;
			case Xpdl1Package.PACKAGE_TYPE__SCRIPT:
				setScript((ScriptType)newValue);
				return;
			case Xpdl1Package.PACKAGE_TYPE__EXTERNAL_PACKAGES:
				setExternalPackages((ExternalPackagesType)newValue);
				return;
			case Xpdl1Package.PACKAGE_TYPE__TYPE_DECLARATIONS:
				setTypeDeclarations((TypeDeclarationsType)newValue);
				return;
			case Xpdl1Package.PACKAGE_TYPE__PARTICIPANTS:
				setParticipants((ParticipantsType)newValue);
				return;
			case Xpdl1Package.PACKAGE_TYPE__APPLICATIONS:
				setApplications((ApplicationsType)newValue);
				return;
			case Xpdl1Package.PACKAGE_TYPE__DATA_FIELDS:
				setDataFields((DataFieldsType)newValue);
				return;
			case Xpdl1Package.PACKAGE_TYPE__WORKFLOW_PROCESSES:
				setWorkflowProcesses((WorkflowProcessesType)newValue);
				return;
			case Xpdl1Package.PACKAGE_TYPE__EXTENDED_ATTRIBUTES:
				setExtendedAttributes((ExtendedAttributesType)newValue);
				return;
			case Xpdl1Package.PACKAGE_TYPE__ID:
				setId((String)newValue);
				return;
			case Xpdl1Package.PACKAGE_TYPE__NAME:
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
			case Xpdl1Package.PACKAGE_TYPE__PACKAGE_HEADER:
				setPackageHeader((PackageHeaderType)null);
				return;
			case Xpdl1Package.PACKAGE_TYPE__REDEFINABLE_HEADER:
				setRedefinableHeader((RedefinableHeaderType)null);
				return;
			case Xpdl1Package.PACKAGE_TYPE__CONFORMANCE_CLASS:
				setConformanceClass((ConformanceClassType)null);
				return;
			case Xpdl1Package.PACKAGE_TYPE__SCRIPT:
				setScript((ScriptType)null);
				return;
			case Xpdl1Package.PACKAGE_TYPE__EXTERNAL_PACKAGES:
				setExternalPackages((ExternalPackagesType)null);
				return;
			case Xpdl1Package.PACKAGE_TYPE__TYPE_DECLARATIONS:
				setTypeDeclarations((TypeDeclarationsType)null);
				return;
			case Xpdl1Package.PACKAGE_TYPE__PARTICIPANTS:
				setParticipants((ParticipantsType)null);
				return;
			case Xpdl1Package.PACKAGE_TYPE__APPLICATIONS:
				setApplications((ApplicationsType)null);
				return;
			case Xpdl1Package.PACKAGE_TYPE__DATA_FIELDS:
				setDataFields((DataFieldsType)null);
				return;
			case Xpdl1Package.PACKAGE_TYPE__WORKFLOW_PROCESSES:
				setWorkflowProcesses((WorkflowProcessesType)null);
				return;
			case Xpdl1Package.PACKAGE_TYPE__EXTENDED_ATTRIBUTES:
				setExtendedAttributes((ExtendedAttributesType)null);
				return;
			case Xpdl1Package.PACKAGE_TYPE__ID:
				setId(ID_EDEFAULT);
				return;
			case Xpdl1Package.PACKAGE_TYPE__NAME:
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
			case Xpdl1Package.PACKAGE_TYPE__PACKAGE_HEADER:
				return packageHeader != null;
			case Xpdl1Package.PACKAGE_TYPE__REDEFINABLE_HEADER:
				return redefinableHeader != null;
			case Xpdl1Package.PACKAGE_TYPE__CONFORMANCE_CLASS:
				return conformanceClass != null;
			case Xpdl1Package.PACKAGE_TYPE__SCRIPT:
				return script != null;
			case Xpdl1Package.PACKAGE_TYPE__EXTERNAL_PACKAGES:
				return externalPackages != null;
			case Xpdl1Package.PACKAGE_TYPE__TYPE_DECLARATIONS:
				return typeDeclarations != null;
			case Xpdl1Package.PACKAGE_TYPE__PARTICIPANTS:
				return participants != null;
			case Xpdl1Package.PACKAGE_TYPE__APPLICATIONS:
				return applications != null;
			case Xpdl1Package.PACKAGE_TYPE__DATA_FIELDS:
				return dataFields != null;
			case Xpdl1Package.PACKAGE_TYPE__WORKFLOW_PROCESSES:
				return workflowProcesses != null;
			case Xpdl1Package.PACKAGE_TYPE__EXTENDED_ATTRIBUTES:
				return extendedAttributes != null;
			case Xpdl1Package.PACKAGE_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case Xpdl1Package.PACKAGE_TYPE__NAME:
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
		result.append(" (id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //PackageTypeImpl
