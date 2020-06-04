/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.bonitasoft.studio.migration.model.report.impl;

import org.bonitasoft.studio.migration.model.report.Change;
import org.bonitasoft.studio.migration.model.report.MigrationReportPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.impl.ChangeImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.impl.ChangeImpl#isReviewed <em>Reviewed</em>}</li>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.impl.ChangeImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.impl.ChangeImpl#getPropertyName <em>Property Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.impl.ChangeImpl#getTransformationKind <em>Transformation Kind</em>}</li>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.impl.ChangeImpl#getElementUUID <em>Element UUID</em>}</li>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.impl.ChangeImpl#getElementName <em>Element Name</em>}</li>
 *   <li>{@link org.bonitasoft.studio.migration.model.report.impl.ChangeImpl#getElementType <em>Element Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ChangeImpl extends EObjectImpl implements Change {
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
	 * The default value of the '{@link #isReviewed() <em>Reviewed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReviewed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REVIEWED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isReviewed() <em>Reviewed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isReviewed()
	 * @generated
	 * @ordered
	 */
	protected boolean reviewed = REVIEWED_EDEFAULT;

	/**
	 * The default value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected static final int STATUS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected int status = STATUS_EDEFAULT;

	/**
	 * The default value of the '{@link #getPropertyName() <em>Property Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertyName()
	 * @generated
	 * @ordered
	 */
	protected static final String PROPERTY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPropertyName() <em>Property Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertyName()
	 * @generated
	 * @ordered
	 */
	protected String propertyName = PROPERTY_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getTransformationKind() <em>Transformation Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransformationKind()
	 * @generated
	 * @ordered
	 */
	protected static final String TRANSFORMATION_KIND_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTransformationKind() <em>Transformation Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTransformationKind()
	 * @generated
	 * @ordered
	 */
	protected String transformationKind = TRANSFORMATION_KIND_EDEFAULT;

	/**
	 * The default value of the '{@link #getElementUUID() <em>Element UUID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementUUID()
	 * @generated
	 * @ordered
	 */
	protected static final String ELEMENT_UUID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getElementUUID() <em>Element UUID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementUUID()
	 * @generated
	 * @ordered
	 */
	protected String elementUUID = ELEMENT_UUID_EDEFAULT;

	/**
	 * The default value of the '{@link #getElementName() <em>Element Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementName()
	 * @generated
	 * @ordered
	 */
	protected static final String ELEMENT_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getElementName() <em>Element Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementName()
	 * @generated
	 * @ordered
	 */
	protected String elementName = ELEMENT_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getElementType() <em>Element Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementType()
	 * @generated
	 * @ordered
	 */
	protected static final String ELEMENT_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getElementType() <em>Element Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElementType()
	 * @generated
	 * @ordered
	 */
	protected String elementType = ELEMENT_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ChangeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MigrationReportPackage.Literals.CHANGE;
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
			eNotify(new ENotificationImpl(this, Notification.SET, MigrationReportPackage.CHANGE__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isReviewed() {
		return reviewed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReviewed(boolean newReviewed) {
		boolean oldReviewed = reviewed;
		reviewed = newReviewed;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MigrationReportPackage.CHANGE__REVIEWED, oldReviewed, reviewed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatus(int newStatus) {
		int oldStatus = status;
		status = newStatus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MigrationReportPackage.CHANGE__STATUS, oldStatus, status));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPropertyName(String newPropertyName) {
		String oldPropertyName = propertyName;
		propertyName = newPropertyName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MigrationReportPackage.CHANGE__PROPERTY_NAME, oldPropertyName, propertyName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTransformationKind() {
		return transformationKind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransformationKind(String newTransformationKind) {
		String oldTransformationKind = transformationKind;
		transformationKind = newTransformationKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MigrationReportPackage.CHANGE__TRANSFORMATION_KIND, oldTransformationKind, transformationKind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getElementUUID() {
		return elementUUID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElementUUID(String newElementUUID) {
		String oldElementUUID = elementUUID;
		elementUUID = newElementUUID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MigrationReportPackage.CHANGE__ELEMENT_UUID, oldElementUUID, elementUUID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getElementName() {
		return elementName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElementName(String newElementName) {
		String oldElementName = elementName;
		elementName = newElementName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MigrationReportPackage.CHANGE__ELEMENT_NAME, oldElementName, elementName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getElementType() {
		return elementType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElementType(String newElementType) {
		String oldElementType = elementType;
		elementType = newElementType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MigrationReportPackage.CHANGE__ELEMENT_TYPE, oldElementType, elementType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MigrationReportPackage.CHANGE__DESCRIPTION:
				return getDescription();
			case MigrationReportPackage.CHANGE__REVIEWED:
				return isReviewed();
			case MigrationReportPackage.CHANGE__STATUS:
				return getStatus();
			case MigrationReportPackage.CHANGE__PROPERTY_NAME:
				return getPropertyName();
			case MigrationReportPackage.CHANGE__TRANSFORMATION_KIND:
				return getTransformationKind();
			case MigrationReportPackage.CHANGE__ELEMENT_UUID:
				return getElementUUID();
			case MigrationReportPackage.CHANGE__ELEMENT_NAME:
				return getElementName();
			case MigrationReportPackage.CHANGE__ELEMENT_TYPE:
				return getElementType();
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
			case MigrationReportPackage.CHANGE__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case MigrationReportPackage.CHANGE__REVIEWED:
				setReviewed((Boolean)newValue);
				return;
			case MigrationReportPackage.CHANGE__STATUS:
				setStatus((Integer)newValue);
				return;
			case MigrationReportPackage.CHANGE__PROPERTY_NAME:
				setPropertyName((String)newValue);
				return;
			case MigrationReportPackage.CHANGE__TRANSFORMATION_KIND:
				setTransformationKind((String)newValue);
				return;
			case MigrationReportPackage.CHANGE__ELEMENT_UUID:
				setElementUUID((String)newValue);
				return;
			case MigrationReportPackage.CHANGE__ELEMENT_NAME:
				setElementName((String)newValue);
				return;
			case MigrationReportPackage.CHANGE__ELEMENT_TYPE:
				setElementType((String)newValue);
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
			case MigrationReportPackage.CHANGE__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case MigrationReportPackage.CHANGE__REVIEWED:
				setReviewed(REVIEWED_EDEFAULT);
				return;
			case MigrationReportPackage.CHANGE__STATUS:
				setStatus(STATUS_EDEFAULT);
				return;
			case MigrationReportPackage.CHANGE__PROPERTY_NAME:
				setPropertyName(PROPERTY_NAME_EDEFAULT);
				return;
			case MigrationReportPackage.CHANGE__TRANSFORMATION_KIND:
				setTransformationKind(TRANSFORMATION_KIND_EDEFAULT);
				return;
			case MigrationReportPackage.CHANGE__ELEMENT_UUID:
				setElementUUID(ELEMENT_UUID_EDEFAULT);
				return;
			case MigrationReportPackage.CHANGE__ELEMENT_NAME:
				setElementName(ELEMENT_NAME_EDEFAULT);
				return;
			case MigrationReportPackage.CHANGE__ELEMENT_TYPE:
				setElementType(ELEMENT_TYPE_EDEFAULT);
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
			case MigrationReportPackage.CHANGE__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case MigrationReportPackage.CHANGE__REVIEWED:
				return reviewed != REVIEWED_EDEFAULT;
			case MigrationReportPackage.CHANGE__STATUS:
				return status != STATUS_EDEFAULT;
			case MigrationReportPackage.CHANGE__PROPERTY_NAME:
				return PROPERTY_NAME_EDEFAULT == null ? propertyName != null : !PROPERTY_NAME_EDEFAULT.equals(propertyName);
			case MigrationReportPackage.CHANGE__TRANSFORMATION_KIND:
				return TRANSFORMATION_KIND_EDEFAULT == null ? transformationKind != null : !TRANSFORMATION_KIND_EDEFAULT.equals(transformationKind);
			case MigrationReportPackage.CHANGE__ELEMENT_UUID:
				return ELEMENT_UUID_EDEFAULT == null ? elementUUID != null : !ELEMENT_UUID_EDEFAULT.equals(elementUUID);
			case MigrationReportPackage.CHANGE__ELEMENT_NAME:
				return ELEMENT_NAME_EDEFAULT == null ? elementName != null : !ELEMENT_NAME_EDEFAULT.equals(elementName);
			case MigrationReportPackage.CHANGE__ELEMENT_TYPE:
				return ELEMENT_TYPE_EDEFAULT == null ? elementType != null : !ELEMENT_TYPE_EDEFAULT.equals(elementType);
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
		result.append(", reviewed: ");
		result.append(reviewed);
		result.append(", status: ");
		result.append(status);
		result.append(", propertyName: ");
		result.append(propertyName);
		result.append(", transformationKind: ");
		result.append(transformationKind);
		result.append(", elementUUID: ");
		result.append(elementUUID);
		result.append(", elementName: ");
		result.append(elementName);
		result.append(", elementType: ");
		result.append(elementType);
		result.append(')');
		return result.toString();
	}

} //ChangeImpl
