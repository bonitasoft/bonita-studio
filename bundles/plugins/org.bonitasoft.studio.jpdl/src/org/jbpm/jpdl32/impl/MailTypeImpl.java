/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.jbpm.jpdl32.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.jbpm.jpdl32.MailType;
import org.jbpm.jpdl32.jpdl32Package;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mail Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.jbpm.jpdl32.impl.MailTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.MailTypeImpl#getSubject <em>Subject</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.MailTypeImpl#getText <em>Text</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.MailTypeImpl#getActors <em>Actors</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.MailTypeImpl#getAsync <em>Async</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.MailTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.MailTypeImpl#getSubject1 <em>Subject1</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.MailTypeImpl#getTemplate <em>Template</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.MailTypeImpl#getText1 <em>Text1</em>}</li>
 *   <li>{@link org.jbpm.jpdl32.impl.MailTypeImpl#getTo <em>To</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MailTypeImpl extends EObjectImpl implements MailType {
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * The default value of the '{@link #getActors() <em>Actors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActors()
	 * @generated
	 * @ordered
	 */
	protected static final String ACTORS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getActors() <em>Actors</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActors()
	 * @generated
	 * @ordered
	 */
	protected String actors = ACTORS_EDEFAULT;

	/**
	 * The default value of the '{@link #getAsync() <em>Async</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAsync()
	 * @generated
	 * @ordered
	 */
	protected static final String ASYNC_EDEFAULT = "false";

	/**
	 * The cached value of the '{@link #getAsync() <em>Async</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAsync()
	 * @generated
	 * @ordered
	 */
	protected String async = ASYNC_EDEFAULT;

	/**
	 * This is true if the Async attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean asyncESet;

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
	 * The default value of the '{@link #getSubject1() <em>Subject1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubject1()
	 * @generated
	 * @ordered
	 */
	protected static final String SUBJECT1_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSubject1() <em>Subject1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubject1()
	 * @generated
	 * @ordered
	 */
	protected String subject1 = SUBJECT1_EDEFAULT;

	/**
	 * The default value of the '{@link #getTemplate() <em>Template</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemplate()
	 * @generated
	 * @ordered
	 */
	protected static final String TEMPLATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTemplate() <em>Template</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemplate()
	 * @generated
	 * @ordered
	 */
	protected String template = TEMPLATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getText1() <em>Text1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getText1()
	 * @generated
	 * @ordered
	 */
	protected static final String TEXT1_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getText1() <em>Text1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getText1()
	 * @generated
	 * @ordered
	 */
	protected String text1 = TEXT1_EDEFAULT;

	/**
	 * The default value of the '{@link #getTo() <em>To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTo()
	 * @generated
	 * @ordered
	 */
	protected static final String TO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTo() <em>To</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTo()
	 * @generated
	 * @ordered
	 */
	protected String to = TO_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MailTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return jpdl32Package.Literals.MAIL_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, jpdl32Package.MAIL_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getSubject() {
		return getGroup().list(jpdl32Package.Literals.MAIL_TYPE__SUBJECT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getText() {
		return getGroup().list(jpdl32Package.Literals.MAIL_TYPE__TEXT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getActors() {
		return actors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActors(String newActors) {
		String oldActors = actors;
		actors = newActors;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.MAIL_TYPE__ACTORS, oldActors, actors));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAsync() {
		return async;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAsync(String newAsync) {
		String oldAsync = async;
		async = newAsync;
		boolean oldAsyncESet = asyncESet;
		asyncESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.MAIL_TYPE__ASYNC, oldAsync, async, !oldAsyncESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetAsync() {
		String oldAsync = async;
		boolean oldAsyncESet = asyncESet;
		async = ASYNC_EDEFAULT;
		asyncESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, jpdl32Package.MAIL_TYPE__ASYNC, oldAsync, ASYNC_EDEFAULT, oldAsyncESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetAsync() {
		return asyncESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.MAIL_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSubject1() {
		return subject1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubject1(String newSubject1) {
		String oldSubject1 = subject1;
		subject1 = newSubject1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.MAIL_TYPE__SUBJECT1, oldSubject1, subject1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTemplate() {
		return template;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTemplate(String newTemplate) {
		String oldTemplate = template;
		template = newTemplate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.MAIL_TYPE__TEMPLATE, oldTemplate, template));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getText1() {
		return text1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setText1(String newText1) {
		String oldText1 = text1;
		text1 = newText1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.MAIL_TYPE__TEXT1, oldText1, text1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTo() {
		return to;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTo(String newTo) {
		String oldTo = to;
		to = newTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, jpdl32Package.MAIL_TYPE__TO, oldTo, to));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case jpdl32Package.MAIL_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
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
			case jpdl32Package.MAIL_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case jpdl32Package.MAIL_TYPE__SUBJECT:
				return getSubject();
			case jpdl32Package.MAIL_TYPE__TEXT:
				return getText();
			case jpdl32Package.MAIL_TYPE__ACTORS:
				return getActors();
			case jpdl32Package.MAIL_TYPE__ASYNC:
				return getAsync();
			case jpdl32Package.MAIL_TYPE__NAME:
				return getName();
			case jpdl32Package.MAIL_TYPE__SUBJECT1:
				return getSubject1();
			case jpdl32Package.MAIL_TYPE__TEMPLATE:
				return getTemplate();
			case jpdl32Package.MAIL_TYPE__TEXT1:
				return getText1();
			case jpdl32Package.MAIL_TYPE__TO:
				return getTo();
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
			case jpdl32Package.MAIL_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case jpdl32Package.MAIL_TYPE__SUBJECT:
				getSubject().clear();
				getSubject().addAll((Collection<? extends String>)newValue);
				return;
			case jpdl32Package.MAIL_TYPE__TEXT:
				getText().clear();
				getText().addAll((Collection<? extends String>)newValue);
				return;
			case jpdl32Package.MAIL_TYPE__ACTORS:
				setActors((String)newValue);
				return;
			case jpdl32Package.MAIL_TYPE__ASYNC:
				setAsync((String)newValue);
				return;
			case jpdl32Package.MAIL_TYPE__NAME:
				setName((String)newValue);
				return;
			case jpdl32Package.MAIL_TYPE__SUBJECT1:
				setSubject1((String)newValue);
				return;
			case jpdl32Package.MAIL_TYPE__TEMPLATE:
				setTemplate((String)newValue);
				return;
			case jpdl32Package.MAIL_TYPE__TEXT1:
				setText1((String)newValue);
				return;
			case jpdl32Package.MAIL_TYPE__TO:
				setTo((String)newValue);
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
			case jpdl32Package.MAIL_TYPE__GROUP:
				getGroup().clear();
				return;
			case jpdl32Package.MAIL_TYPE__SUBJECT:
				getSubject().clear();
				return;
			case jpdl32Package.MAIL_TYPE__TEXT:
				getText().clear();
				return;
			case jpdl32Package.MAIL_TYPE__ACTORS:
				setActors(ACTORS_EDEFAULT);
				return;
			case jpdl32Package.MAIL_TYPE__ASYNC:
				unsetAsync();
				return;
			case jpdl32Package.MAIL_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case jpdl32Package.MAIL_TYPE__SUBJECT1:
				setSubject1(SUBJECT1_EDEFAULT);
				return;
			case jpdl32Package.MAIL_TYPE__TEMPLATE:
				setTemplate(TEMPLATE_EDEFAULT);
				return;
			case jpdl32Package.MAIL_TYPE__TEXT1:
				setText1(TEXT1_EDEFAULT);
				return;
			case jpdl32Package.MAIL_TYPE__TO:
				setTo(TO_EDEFAULT);
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
			case jpdl32Package.MAIL_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case jpdl32Package.MAIL_TYPE__SUBJECT:
				return !getSubject().isEmpty();
			case jpdl32Package.MAIL_TYPE__TEXT:
				return !getText().isEmpty();
			case jpdl32Package.MAIL_TYPE__ACTORS:
				return ACTORS_EDEFAULT == null ? actors != null : !ACTORS_EDEFAULT.equals(actors);
			case jpdl32Package.MAIL_TYPE__ASYNC:
				return isSetAsync();
			case jpdl32Package.MAIL_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case jpdl32Package.MAIL_TYPE__SUBJECT1:
				return SUBJECT1_EDEFAULT == null ? subject1 != null : !SUBJECT1_EDEFAULT.equals(subject1);
			case jpdl32Package.MAIL_TYPE__TEMPLATE:
				return TEMPLATE_EDEFAULT == null ? template != null : !TEMPLATE_EDEFAULT.equals(template);
			case jpdl32Package.MAIL_TYPE__TEXT1:
				return TEXT1_EDEFAULT == null ? text1 != null : !TEXT1_EDEFAULT.equals(text1);
			case jpdl32Package.MAIL_TYPE__TO:
				return TO_EDEFAULT == null ? to != null : !TO_EDEFAULT.equals(to);
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
		result.append(" (group: ");
		result.append(group);
		result.append(", actors: ");
		result.append(actors);
		result.append(", async: ");
		if (asyncESet) result.append(async); else result.append("<unset>");
		result.append(", name: ");
		result.append(name);
		result.append(", subject1: ");
		result.append(subject1);
		result.append(", template: ");
		result.append(template);
		result.append(", text1: ");
		result.append(text1);
		result.append(", to: ");
		result.append(to);
		result.append(')');
		return result.toString();
	}

} //MailTypeImpl
