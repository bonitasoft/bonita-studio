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
import org.wfmc._2002.xpdl1.PublicationStatusType;
import org.wfmc._2002.xpdl1.RedefinableHeaderType;
import org.wfmc._2002.xpdl1.ResponsiblesType;
import org.wfmc._2002.xpdl1.Xpdl1Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Redefinable Header Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.RedefinableHeaderTypeImpl#getAuthor <em>Author</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.RedefinableHeaderTypeImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.RedefinableHeaderTypeImpl#getCodepage <em>Codepage</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.RedefinableHeaderTypeImpl#getCountrykey <em>Countrykey</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.RedefinableHeaderTypeImpl#getResponsibles <em>Responsibles</em>}</li>
 *   <li>{@link org.wfmc._2002.xpdl1.impl.RedefinableHeaderTypeImpl#getPublicationStatus <em>Publication Status</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RedefinableHeaderTypeImpl extends EObjectImpl implements RedefinableHeaderType {
	/**
	 * The default value of the '{@link #getAuthor() <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthor()
	 * @generated
	 * @ordered
	 */
	protected static final String AUTHOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAuthor() <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthor()
	 * @generated
	 * @ordered
	 */
	protected String author = AUTHOR_EDEFAULT;

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
	 * The default value of the '{@link #getCodepage() <em>Codepage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCodepage()
	 * @generated
	 * @ordered
	 */
	protected static final String CODEPAGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCodepage() <em>Codepage</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCodepage()
	 * @generated
	 * @ordered
	 */
	protected String codepage = CODEPAGE_EDEFAULT;

	/**
	 * The default value of the '{@link #getCountrykey() <em>Countrykey</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCountrykey()
	 * @generated
	 * @ordered
	 */
	protected static final String COUNTRYKEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCountrykey() <em>Countrykey</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCountrykey()
	 * @generated
	 * @ordered
	 */
	protected String countrykey = COUNTRYKEY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getResponsibles() <em>Responsibles</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResponsibles()
	 * @generated
	 * @ordered
	 */
	protected ResponsiblesType responsibles;

	/**
	 * The default value of the '{@link #getPublicationStatus() <em>Publication Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPublicationStatus()
	 * @generated
	 * @ordered
	 */
	protected static final PublicationStatusType PUBLICATION_STATUS_EDEFAULT = PublicationStatusType.UNDERREVISION;

	/**
	 * The cached value of the '{@link #getPublicationStatus() <em>Publication Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPublicationStatus()
	 * @generated
	 * @ordered
	 */
	protected PublicationStatusType publicationStatus = PUBLICATION_STATUS_EDEFAULT;

	/**
	 * This is true if the Publication Status attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean publicationStatusESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RedefinableHeaderTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Xpdl1Package.Literals.REDEFINABLE_HEADER_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAuthor(String newAuthor) {
		String oldAuthor = author;
		author = newAuthor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.REDEFINABLE_HEADER_TYPE__AUTHOR, oldAuthor, author));
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
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.REDEFINABLE_HEADER_TYPE__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCodepage() {
		return codepage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCodepage(String newCodepage) {
		String oldCodepage = codepage;
		codepage = newCodepage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.REDEFINABLE_HEADER_TYPE__CODEPAGE, oldCodepage, codepage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCountrykey() {
		return countrykey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCountrykey(String newCountrykey) {
		String oldCountrykey = countrykey;
		countrykey = newCountrykey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.REDEFINABLE_HEADER_TYPE__COUNTRYKEY, oldCountrykey, countrykey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResponsiblesType getResponsibles() {
		return responsibles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResponsibles(ResponsiblesType newResponsibles, NotificationChain msgs) {
		ResponsiblesType oldResponsibles = responsibles;
		responsibles = newResponsibles;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Xpdl1Package.REDEFINABLE_HEADER_TYPE__RESPONSIBLES, oldResponsibles, newResponsibles);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResponsibles(ResponsiblesType newResponsibles) {
		if (newResponsibles != responsibles) {
			NotificationChain msgs = null;
			if (responsibles != null)
				msgs = ((InternalEObject)responsibles).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.REDEFINABLE_HEADER_TYPE__RESPONSIBLES, null, msgs);
			if (newResponsibles != null)
				msgs = ((InternalEObject)newResponsibles).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - Xpdl1Package.REDEFINABLE_HEADER_TYPE__RESPONSIBLES, null, msgs);
			msgs = basicSetResponsibles(newResponsibles, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.REDEFINABLE_HEADER_TYPE__RESPONSIBLES, newResponsibles, newResponsibles));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PublicationStatusType getPublicationStatus() {
		return publicationStatus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPublicationStatus(PublicationStatusType newPublicationStatus) {
		PublicationStatusType oldPublicationStatus = publicationStatus;
		publicationStatus = newPublicationStatus == null ? PUBLICATION_STATUS_EDEFAULT : newPublicationStatus;
		boolean oldPublicationStatusESet = publicationStatusESet;
		publicationStatusESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Xpdl1Package.REDEFINABLE_HEADER_TYPE__PUBLICATION_STATUS, oldPublicationStatus, publicationStatus, !oldPublicationStatusESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPublicationStatus() {
		PublicationStatusType oldPublicationStatus = publicationStatus;
		boolean oldPublicationStatusESet = publicationStatusESet;
		publicationStatus = PUBLICATION_STATUS_EDEFAULT;
		publicationStatusESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, Xpdl1Package.REDEFINABLE_HEADER_TYPE__PUBLICATION_STATUS, oldPublicationStatus, PUBLICATION_STATUS_EDEFAULT, oldPublicationStatusESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPublicationStatus() {
		return publicationStatusESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__RESPONSIBLES:
				return basicSetResponsibles(null, msgs);
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
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__AUTHOR:
				return getAuthor();
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__VERSION:
				return getVersion();
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__CODEPAGE:
				return getCodepage();
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__COUNTRYKEY:
				return getCountrykey();
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__RESPONSIBLES:
				return getResponsibles();
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__PUBLICATION_STATUS:
				return getPublicationStatus();
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
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__AUTHOR:
				setAuthor((String)newValue);
				return;
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__VERSION:
				setVersion((String)newValue);
				return;
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__CODEPAGE:
				setCodepage((String)newValue);
				return;
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__COUNTRYKEY:
				setCountrykey((String)newValue);
				return;
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__RESPONSIBLES:
				setResponsibles((ResponsiblesType)newValue);
				return;
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__PUBLICATION_STATUS:
				setPublicationStatus((PublicationStatusType)newValue);
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
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__AUTHOR:
				setAuthor(AUTHOR_EDEFAULT);
				return;
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__CODEPAGE:
				setCodepage(CODEPAGE_EDEFAULT);
				return;
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__COUNTRYKEY:
				setCountrykey(COUNTRYKEY_EDEFAULT);
				return;
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__RESPONSIBLES:
				setResponsibles((ResponsiblesType)null);
				return;
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__PUBLICATION_STATUS:
				unsetPublicationStatus();
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
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__AUTHOR:
				return AUTHOR_EDEFAULT == null ? author != null : !AUTHOR_EDEFAULT.equals(author);
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__CODEPAGE:
				return CODEPAGE_EDEFAULT == null ? codepage != null : !CODEPAGE_EDEFAULT.equals(codepage);
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__COUNTRYKEY:
				return COUNTRYKEY_EDEFAULT == null ? countrykey != null : !COUNTRYKEY_EDEFAULT.equals(countrykey);
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__RESPONSIBLES:
				return responsibles != null;
			case Xpdl1Package.REDEFINABLE_HEADER_TYPE__PUBLICATION_STATUS:
				return isSetPublicationStatus();
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
		result.append(" (author: ");
		result.append(author);
		result.append(", version: ");
		result.append(version);
		result.append(", codepage: ");
		result.append(codepage);
		result.append(", countrykey: ");
		result.append(countrykey);
		result.append(", publicationStatus: ");
		if (publicationStatusESet) result.append(publicationStatus); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //RedefinableHeaderTypeImpl
