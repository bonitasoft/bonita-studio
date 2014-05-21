/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.migration.model.report.impl;

import java.util.Collection;

import org.bonitasoft.studio.migration.model.report.Change;
import org.bonitasoft.studio.migration.model.report.MigrationReportPackage;
import org.bonitasoft.studio.migration.model.report.Report;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Report</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.impl.ReportImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.impl.ReportImpl#getChanges <em>Changes</em>}</li>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.impl.ReportImpl#getCompletion <em>Completion</em>}</li>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.impl.ReportImpl#getSourceRelease <em>Source Release</em>}</li>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.impl.ReportImpl#getTargetRelease <em>Target Release</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReportImpl extends EObjectImpl implements Report {
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
	 * The cached value of the '{@link #getChanges() <em>Changes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChanges()
	 * @generated
	 * @ordered
	 */
	protected EList<Change> changes;

	/**
	 * The default value of the '{@link #getCompletion() <em>Completion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompletion()
	 * @generated
	 * @ordered
	 */
	protected static final double COMPLETION_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getCompletion() <em>Completion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCompletion()
	 * @generated
	 * @ordered
	 */
	protected double completion = COMPLETION_EDEFAULT;

	/**
	 * The default value of the '{@link #getSourceRelease() <em>Source Release</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceRelease()
	 * @generated
	 * @ordered
	 */
	protected static final String SOURCE_RELEASE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourceRelease() <em>Source Release</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceRelease()
	 * @generated
	 * @ordered
	 */
	protected String sourceRelease = SOURCE_RELEASE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTargetRelease() <em>Target Release</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetRelease()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_RELEASE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTargetRelease() <em>Target Release</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTargetRelease()
	 * @generated
	 * @ordered
	 */
	protected String targetRelease = TARGET_RELEASE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReportImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MigrationReportPackage.Literals.REPORT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, MigrationReportPackage.REPORT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Change> getChanges() {
		if (changes == null) {
			changes = new EObjectContainmentEList<Change>(Change.class, this, MigrationReportPackage.REPORT__CHANGES);
		}
		return changes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getCompletion() {
		return completion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCompletion(double newCompletion) {
		double oldCompletion = completion;
		completion = newCompletion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MigrationReportPackage.REPORT__COMPLETION, oldCompletion, completion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSourceRelease() {
		return sourceRelease;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceRelease(String newSourceRelease) {
		String oldSourceRelease = sourceRelease;
		sourceRelease = newSourceRelease;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MigrationReportPackage.REPORT__SOURCE_RELEASE, oldSourceRelease, sourceRelease));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTargetRelease() {
		return targetRelease;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTargetRelease(String newTargetRelease) {
		String oldTargetRelease = targetRelease;
		targetRelease = newTargetRelease;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MigrationReportPackage.REPORT__TARGET_RELEASE, oldTargetRelease, targetRelease));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MigrationReportPackage.REPORT__CHANGES:
				return ((InternalEList<?>)getChanges()).basicRemove(otherEnd, msgs);
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
			case MigrationReportPackage.REPORT__NAME:
				return getName();
			case MigrationReportPackage.REPORT__CHANGES:
				return getChanges();
			case MigrationReportPackage.REPORT__COMPLETION:
				return getCompletion();
			case MigrationReportPackage.REPORT__SOURCE_RELEASE:
				return getSourceRelease();
			case MigrationReportPackage.REPORT__TARGET_RELEASE:
				return getTargetRelease();
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
			case MigrationReportPackage.REPORT__NAME:
				setName((String)newValue);
				return;
			case MigrationReportPackage.REPORT__CHANGES:
				getChanges().clear();
				getChanges().addAll((Collection<? extends Change>)newValue);
				return;
			case MigrationReportPackage.REPORT__COMPLETION:
				setCompletion((Double)newValue);
				return;
			case MigrationReportPackage.REPORT__SOURCE_RELEASE:
				setSourceRelease((String)newValue);
				return;
			case MigrationReportPackage.REPORT__TARGET_RELEASE:
				setTargetRelease((String)newValue);
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
			case MigrationReportPackage.REPORT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case MigrationReportPackage.REPORT__CHANGES:
				getChanges().clear();
				return;
			case MigrationReportPackage.REPORT__COMPLETION:
				setCompletion(COMPLETION_EDEFAULT);
				return;
			case MigrationReportPackage.REPORT__SOURCE_RELEASE:
				setSourceRelease(SOURCE_RELEASE_EDEFAULT);
				return;
			case MigrationReportPackage.REPORT__TARGET_RELEASE:
				setTargetRelease(TARGET_RELEASE_EDEFAULT);
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
			case MigrationReportPackage.REPORT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case MigrationReportPackage.REPORT__CHANGES:
				return changes != null && !changes.isEmpty();
			case MigrationReportPackage.REPORT__COMPLETION:
				return completion != COMPLETION_EDEFAULT;
			case MigrationReportPackage.REPORT__SOURCE_RELEASE:
				return SOURCE_RELEASE_EDEFAULT == null ? sourceRelease != null : !SOURCE_RELEASE_EDEFAULT.equals(sourceRelease);
			case MigrationReportPackage.REPORT__TARGET_RELEASE:
				return TARGET_RELEASE_EDEFAULT == null ? targetRelease != null : !TARGET_RELEASE_EDEFAULT.equals(targetRelease);
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
		result.append(" (name: ");
		result.append(name);
		result.append(", completion: ");
		result.append(completion);
		result.append(", sourceRelease: ");
		result.append(sourceRelease);
		result.append(", targetRelease: ");
		result.append(targetRelease);
		result.append(')');
		return result.toString();
	}

} //ReportImpl
