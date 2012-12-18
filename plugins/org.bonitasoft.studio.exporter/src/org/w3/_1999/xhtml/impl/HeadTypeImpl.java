/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.w3._1999.xhtml.impl;

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
import org.w3._1999.xhtml.BaseType;
import org.w3._1999.xhtml.DirType;
import org.w3._1999.xhtml.HeadType;
import org.w3._1999.xhtml.LinkType;
import org.w3._1999.xhtml.MetaType;
import org.w3._1999.xhtml.ObjectType;
import org.w3._1999.xhtml.ScriptType;
import org.w3._1999.xhtml.StyleType;
import org.w3._1999.xhtml.TitleType;
import org.w3._1999.xhtml.XhtmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Head Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getScript <em>Script</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getMeta <em>Meta</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getLink <em>Link</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getObject <em>Object</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getGroup1 <em>Group1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getScript1 <em>Script1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getStyle1 <em>Style1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getMeta1 <em>Meta1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getLink1 <em>Link1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getObject1 <em>Object1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getBase <em>Base</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getGroup2 <em>Group2</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getScript2 <em>Script2</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getStyle2 <em>Style2</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getMeta2 <em>Meta2</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getLink2 <em>Link2</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getObject2 <em>Object2</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getBase1 <em>Base1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getGroup3 <em>Group3</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getScript3 <em>Script3</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getStyle3 <em>Style3</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getMeta3 <em>Meta3</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getLink3 <em>Link3</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getObject3 <em>Object3</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getTitle1 <em>Title1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getGroup4 <em>Group4</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getScript4 <em>Script4</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getStyle4 <em>Style4</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getMeta4 <em>Meta4</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getLink4 <em>Link4</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getObject4 <em>Object4</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getDir <em>Dir</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getLang <em>Lang</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getLang1 <em>Lang1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.HeadTypeImpl#getProfile <em>Profile</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HeadTypeImpl extends EObjectImpl implements HeadType {
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
	 * The cached value of the '{@link #getTitle() <em>Title</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected TitleType title;

	/**
	 * The cached value of the '{@link #getGroup1() <em>Group1</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup1()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group1;

	/**
	 * The cached value of the '{@link #getBase() <em>Base</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBase()
	 * @generated
	 * @ordered
	 */
	protected BaseType base;

	/**
	 * The cached value of the '{@link #getGroup2() <em>Group2</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup2()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group2;

	/**
	 * The cached value of the '{@link #getBase1() <em>Base1</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBase1()
	 * @generated
	 * @ordered
	 */
	protected BaseType base1;

	/**
	 * The cached value of the '{@link #getGroup3() <em>Group3</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup3()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group3;

	/**
	 * The cached value of the '{@link #getTitle1() <em>Title1</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle1()
	 * @generated
	 * @ordered
	 */
	protected TitleType title1;

	/**
	 * The cached value of the '{@link #getGroup4() <em>Group4</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGroup4()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group4;

	/**
	 * The default value of the '{@link #getDir() <em>Dir</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDir()
	 * @generated
	 * @ordered
	 */
	protected static final DirType DIR_EDEFAULT = DirType.LTR;

	/**
	 * The cached value of the '{@link #getDir() <em>Dir</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDir()
	 * @generated
	 * @ordered
	 */
	protected DirType dir = DIR_EDEFAULT;

	/**
	 * This is true if the Dir attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean dirESet;

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
	 * The default value of the '{@link #getLang() <em>Lang</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLang()
	 * @generated
	 * @ordered
	 */
	protected static final String LANG_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLang() <em>Lang</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLang()
	 * @generated
	 * @ordered
	 */
	protected String lang = LANG_EDEFAULT;

	/**
	 * The default value of the '{@link #getLang1() <em>Lang1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLang1()
	 * @generated
	 * @ordered
	 */
	protected static final String LANG1_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLang1() <em>Lang1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLang1()
	 * @generated
	 * @ordered
	 */
	protected String lang1 = LANG1_EDEFAULT;

	/**
	 * The default value of the '{@link #getProfile() <em>Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProfile()
	 * @generated
	 * @ordered
	 */
	protected static final String PROFILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProfile() <em>Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProfile()
	 * @generated
	 * @ordered
	 */
	protected String profile = PROFILE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HeadTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XhtmlPackage.eINSTANCE.getHeadType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, XhtmlPackage.HEAD_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScriptType> getScript() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getHeadType_Script());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StyleType> getStyle() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getHeadType_Style());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MetaType> getMeta() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getHeadType_Meta());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LinkType> getLink() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getHeadType_Link());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ObjectType> getObject() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getHeadType_Object());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TitleType getTitle() {
		return title;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTitle(TitleType newTitle, NotificationChain msgs) {
		TitleType oldTitle = title;
		title = newTitle;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XhtmlPackage.HEAD_TYPE__TITLE, oldTitle, newTitle);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTitle(TitleType newTitle) {
		if (newTitle != title) {
			NotificationChain msgs = null;
			if (title != null)
				msgs = ((InternalEObject)title).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XhtmlPackage.HEAD_TYPE__TITLE, null, msgs);
			if (newTitle != null)
				msgs = ((InternalEObject)newTitle).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XhtmlPackage.HEAD_TYPE__TITLE, null, msgs);
			msgs = basicSetTitle(newTitle, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.HEAD_TYPE__TITLE, newTitle, newTitle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup1() {
		if (group1 == null) {
			group1 = new BasicFeatureMap(this, XhtmlPackage.HEAD_TYPE__GROUP1);
		}
		return group1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScriptType> getScript1() {
		return getGroup1().list(XhtmlPackage.eINSTANCE.getHeadType_Script1());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StyleType> getStyle1() {
		return getGroup1().list(XhtmlPackage.eINSTANCE.getHeadType_Style1());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MetaType> getMeta1() {
		return getGroup1().list(XhtmlPackage.eINSTANCE.getHeadType_Meta1());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LinkType> getLink1() {
		return getGroup1().list(XhtmlPackage.eINSTANCE.getHeadType_Link1());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ObjectType> getObject1() {
		return getGroup1().list(XhtmlPackage.eINSTANCE.getHeadType_Object1());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BaseType getBase() {
		return base;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBase(BaseType newBase, NotificationChain msgs) {
		BaseType oldBase = base;
		base = newBase;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XhtmlPackage.HEAD_TYPE__BASE, oldBase, newBase);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBase(BaseType newBase) {
		if (newBase != base) {
			NotificationChain msgs = null;
			if (base != null)
				msgs = ((InternalEObject)base).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XhtmlPackage.HEAD_TYPE__BASE, null, msgs);
			if (newBase != null)
				msgs = ((InternalEObject)newBase).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XhtmlPackage.HEAD_TYPE__BASE, null, msgs);
			msgs = basicSetBase(newBase, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.HEAD_TYPE__BASE, newBase, newBase));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup2() {
		if (group2 == null) {
			group2 = new BasicFeatureMap(this, XhtmlPackage.HEAD_TYPE__GROUP2);
		}
		return group2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScriptType> getScript2() {
		return getGroup2().list(XhtmlPackage.eINSTANCE.getHeadType_Script2());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StyleType> getStyle2() {
		return getGroup2().list(XhtmlPackage.eINSTANCE.getHeadType_Style2());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MetaType> getMeta2() {
		return getGroup2().list(XhtmlPackage.eINSTANCE.getHeadType_Meta2());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LinkType> getLink2() {
		return getGroup2().list(XhtmlPackage.eINSTANCE.getHeadType_Link2());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ObjectType> getObject2() {
		return getGroup2().list(XhtmlPackage.eINSTANCE.getHeadType_Object2());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BaseType getBase1() {
		return base1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBase1(BaseType newBase1, NotificationChain msgs) {
		BaseType oldBase1 = base1;
		base1 = newBase1;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XhtmlPackage.HEAD_TYPE__BASE1, oldBase1, newBase1);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBase1(BaseType newBase1) {
		if (newBase1 != base1) {
			NotificationChain msgs = null;
			if (base1 != null)
				msgs = ((InternalEObject)base1).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XhtmlPackage.HEAD_TYPE__BASE1, null, msgs);
			if (newBase1 != null)
				msgs = ((InternalEObject)newBase1).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XhtmlPackage.HEAD_TYPE__BASE1, null, msgs);
			msgs = basicSetBase1(newBase1, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.HEAD_TYPE__BASE1, newBase1, newBase1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup3() {
		if (group3 == null) {
			group3 = new BasicFeatureMap(this, XhtmlPackage.HEAD_TYPE__GROUP3);
		}
		return group3;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScriptType> getScript3() {
		return getGroup3().list(XhtmlPackage.eINSTANCE.getHeadType_Script3());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StyleType> getStyle3() {
		return getGroup3().list(XhtmlPackage.eINSTANCE.getHeadType_Style3());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MetaType> getMeta3() {
		return getGroup3().list(XhtmlPackage.eINSTANCE.getHeadType_Meta3());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LinkType> getLink3() {
		return getGroup3().list(XhtmlPackage.eINSTANCE.getHeadType_Link3());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ObjectType> getObject3() {
		return getGroup3().list(XhtmlPackage.eINSTANCE.getHeadType_Object3());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TitleType getTitle1() {
		return title1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTitle1(TitleType newTitle1, NotificationChain msgs) {
		TitleType oldTitle1 = title1;
		title1 = newTitle1;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XhtmlPackage.HEAD_TYPE__TITLE1, oldTitle1, newTitle1);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTitle1(TitleType newTitle1) {
		if (newTitle1 != title1) {
			NotificationChain msgs = null;
			if (title1 != null)
				msgs = ((InternalEObject)title1).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XhtmlPackage.HEAD_TYPE__TITLE1, null, msgs);
			if (newTitle1 != null)
				msgs = ((InternalEObject)newTitle1).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XhtmlPackage.HEAD_TYPE__TITLE1, null, msgs);
			msgs = basicSetTitle1(newTitle1, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.HEAD_TYPE__TITLE1, newTitle1, newTitle1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup4() {
		if (group4 == null) {
			group4 = new BasicFeatureMap(this, XhtmlPackage.HEAD_TYPE__GROUP4);
		}
		return group4;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScriptType> getScript4() {
		return getGroup4().list(XhtmlPackage.eINSTANCE.getHeadType_Script4());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StyleType> getStyle4() {
		return getGroup4().list(XhtmlPackage.eINSTANCE.getHeadType_Style4());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MetaType> getMeta4() {
		return getGroup4().list(XhtmlPackage.eINSTANCE.getHeadType_Meta4());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LinkType> getLink4() {
		return getGroup4().list(XhtmlPackage.eINSTANCE.getHeadType_Link4());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ObjectType> getObject4() {
		return getGroup4().list(XhtmlPackage.eINSTANCE.getHeadType_Object4());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DirType getDir() {
		return dir;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDir(DirType newDir) {
		DirType oldDir = dir;
		dir = newDir == null ? DIR_EDEFAULT : newDir;
		boolean oldDirESet = dirESet;
		dirESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.HEAD_TYPE__DIR, oldDir, dir, !oldDirESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetDir() {
		DirType oldDir = dir;
		boolean oldDirESet = dirESet;
		dir = DIR_EDEFAULT;
		dirESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, XhtmlPackage.HEAD_TYPE__DIR, oldDir, DIR_EDEFAULT, oldDirESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetDir() {
		return dirESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.HEAD_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLang(String newLang) {
		String oldLang = lang;
		lang = newLang;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.HEAD_TYPE__LANG, oldLang, lang));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLang1() {
		return lang1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLang1(String newLang1) {
		String oldLang1 = lang1;
		lang1 = newLang1;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.HEAD_TYPE__LANG1, oldLang1, lang1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getProfile() {
		return profile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProfile(String newProfile) {
		String oldProfile = profile;
		profile = newProfile;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.HEAD_TYPE__PROFILE, oldProfile, profile));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case XhtmlPackage.HEAD_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__SCRIPT:
				return ((InternalEList<?>)getScript()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__STYLE:
				return ((InternalEList<?>)getStyle()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__META:
				return ((InternalEList<?>)getMeta()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__LINK:
				return ((InternalEList<?>)getLink()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__OBJECT:
				return ((InternalEList<?>)getObject()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__TITLE:
				return basicSetTitle(null, msgs);
			case XhtmlPackage.HEAD_TYPE__GROUP1:
				return ((InternalEList<?>)getGroup1()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__SCRIPT1:
				return ((InternalEList<?>)getScript1()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__STYLE1:
				return ((InternalEList<?>)getStyle1()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__META1:
				return ((InternalEList<?>)getMeta1()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__LINK1:
				return ((InternalEList<?>)getLink1()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__OBJECT1:
				return ((InternalEList<?>)getObject1()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__BASE:
				return basicSetBase(null, msgs);
			case XhtmlPackage.HEAD_TYPE__GROUP2:
				return ((InternalEList<?>)getGroup2()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__SCRIPT2:
				return ((InternalEList<?>)getScript2()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__STYLE2:
				return ((InternalEList<?>)getStyle2()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__META2:
				return ((InternalEList<?>)getMeta2()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__LINK2:
				return ((InternalEList<?>)getLink2()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__OBJECT2:
				return ((InternalEList<?>)getObject2()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__BASE1:
				return basicSetBase1(null, msgs);
			case XhtmlPackage.HEAD_TYPE__GROUP3:
				return ((InternalEList<?>)getGroup3()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__SCRIPT3:
				return ((InternalEList<?>)getScript3()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__STYLE3:
				return ((InternalEList<?>)getStyle3()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__META3:
				return ((InternalEList<?>)getMeta3()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__LINK3:
				return ((InternalEList<?>)getLink3()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__OBJECT3:
				return ((InternalEList<?>)getObject3()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__TITLE1:
				return basicSetTitle1(null, msgs);
			case XhtmlPackage.HEAD_TYPE__GROUP4:
				return ((InternalEList<?>)getGroup4()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__SCRIPT4:
				return ((InternalEList<?>)getScript4()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__STYLE4:
				return ((InternalEList<?>)getStyle4()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__META4:
				return ((InternalEList<?>)getMeta4()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__LINK4:
				return ((InternalEList<?>)getLink4()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.HEAD_TYPE__OBJECT4:
				return ((InternalEList<?>)getObject4()).basicRemove(otherEnd, msgs);
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
			case XhtmlPackage.HEAD_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case XhtmlPackage.HEAD_TYPE__SCRIPT:
				return getScript();
			case XhtmlPackage.HEAD_TYPE__STYLE:
				return getStyle();
			case XhtmlPackage.HEAD_TYPE__META:
				return getMeta();
			case XhtmlPackage.HEAD_TYPE__LINK:
				return getLink();
			case XhtmlPackage.HEAD_TYPE__OBJECT:
				return getObject();
			case XhtmlPackage.HEAD_TYPE__TITLE:
				return getTitle();
			case XhtmlPackage.HEAD_TYPE__GROUP1:
				if (coreType) return getGroup1();
				return ((FeatureMap.Internal)getGroup1()).getWrapper();
			case XhtmlPackage.HEAD_TYPE__SCRIPT1:
				return getScript1();
			case XhtmlPackage.HEAD_TYPE__STYLE1:
				return getStyle1();
			case XhtmlPackage.HEAD_TYPE__META1:
				return getMeta1();
			case XhtmlPackage.HEAD_TYPE__LINK1:
				return getLink1();
			case XhtmlPackage.HEAD_TYPE__OBJECT1:
				return getObject1();
			case XhtmlPackage.HEAD_TYPE__BASE:
				return getBase();
			case XhtmlPackage.HEAD_TYPE__GROUP2:
				if (coreType) return getGroup2();
				return ((FeatureMap.Internal)getGroup2()).getWrapper();
			case XhtmlPackage.HEAD_TYPE__SCRIPT2:
				return getScript2();
			case XhtmlPackage.HEAD_TYPE__STYLE2:
				return getStyle2();
			case XhtmlPackage.HEAD_TYPE__META2:
				return getMeta2();
			case XhtmlPackage.HEAD_TYPE__LINK2:
				return getLink2();
			case XhtmlPackage.HEAD_TYPE__OBJECT2:
				return getObject2();
			case XhtmlPackage.HEAD_TYPE__BASE1:
				return getBase1();
			case XhtmlPackage.HEAD_TYPE__GROUP3:
				if (coreType) return getGroup3();
				return ((FeatureMap.Internal)getGroup3()).getWrapper();
			case XhtmlPackage.HEAD_TYPE__SCRIPT3:
				return getScript3();
			case XhtmlPackage.HEAD_TYPE__STYLE3:
				return getStyle3();
			case XhtmlPackage.HEAD_TYPE__META3:
				return getMeta3();
			case XhtmlPackage.HEAD_TYPE__LINK3:
				return getLink3();
			case XhtmlPackage.HEAD_TYPE__OBJECT3:
				return getObject3();
			case XhtmlPackage.HEAD_TYPE__TITLE1:
				return getTitle1();
			case XhtmlPackage.HEAD_TYPE__GROUP4:
				if (coreType) return getGroup4();
				return ((FeatureMap.Internal)getGroup4()).getWrapper();
			case XhtmlPackage.HEAD_TYPE__SCRIPT4:
				return getScript4();
			case XhtmlPackage.HEAD_TYPE__STYLE4:
				return getStyle4();
			case XhtmlPackage.HEAD_TYPE__META4:
				return getMeta4();
			case XhtmlPackage.HEAD_TYPE__LINK4:
				return getLink4();
			case XhtmlPackage.HEAD_TYPE__OBJECT4:
				return getObject4();
			case XhtmlPackage.HEAD_TYPE__DIR:
				return getDir();
			case XhtmlPackage.HEAD_TYPE__ID:
				return getId();
			case XhtmlPackage.HEAD_TYPE__LANG:
				return getLang();
			case XhtmlPackage.HEAD_TYPE__LANG1:
				return getLang1();
			case XhtmlPackage.HEAD_TYPE__PROFILE:
				return getProfile();
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
			case XhtmlPackage.HEAD_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__SCRIPT:
				getScript().clear();
				getScript().addAll((Collection<? extends ScriptType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__STYLE:
				getStyle().clear();
				getStyle().addAll((Collection<? extends StyleType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__META:
				getMeta().clear();
				getMeta().addAll((Collection<? extends MetaType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__LINK:
				getLink().clear();
				getLink().addAll((Collection<? extends LinkType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__OBJECT:
				getObject().clear();
				getObject().addAll((Collection<? extends ObjectType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__TITLE:
				setTitle((TitleType)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__GROUP1:
				((FeatureMap.Internal)getGroup1()).set(newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__SCRIPT1:
				getScript1().clear();
				getScript1().addAll((Collection<? extends ScriptType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__STYLE1:
				getStyle1().clear();
				getStyle1().addAll((Collection<? extends StyleType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__META1:
				getMeta1().clear();
				getMeta1().addAll((Collection<? extends MetaType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__LINK1:
				getLink1().clear();
				getLink1().addAll((Collection<? extends LinkType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__OBJECT1:
				getObject1().clear();
				getObject1().addAll((Collection<? extends ObjectType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__BASE:
				setBase((BaseType)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__GROUP2:
				((FeatureMap.Internal)getGroup2()).set(newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__SCRIPT2:
				getScript2().clear();
				getScript2().addAll((Collection<? extends ScriptType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__STYLE2:
				getStyle2().clear();
				getStyle2().addAll((Collection<? extends StyleType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__META2:
				getMeta2().clear();
				getMeta2().addAll((Collection<? extends MetaType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__LINK2:
				getLink2().clear();
				getLink2().addAll((Collection<? extends LinkType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__OBJECT2:
				getObject2().clear();
				getObject2().addAll((Collection<? extends ObjectType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__BASE1:
				setBase1((BaseType)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__GROUP3:
				((FeatureMap.Internal)getGroup3()).set(newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__SCRIPT3:
				getScript3().clear();
				getScript3().addAll((Collection<? extends ScriptType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__STYLE3:
				getStyle3().clear();
				getStyle3().addAll((Collection<? extends StyleType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__META3:
				getMeta3().clear();
				getMeta3().addAll((Collection<? extends MetaType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__LINK3:
				getLink3().clear();
				getLink3().addAll((Collection<? extends LinkType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__OBJECT3:
				getObject3().clear();
				getObject3().addAll((Collection<? extends ObjectType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__TITLE1:
				setTitle1((TitleType)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__GROUP4:
				((FeatureMap.Internal)getGroup4()).set(newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__SCRIPT4:
				getScript4().clear();
				getScript4().addAll((Collection<? extends ScriptType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__STYLE4:
				getStyle4().clear();
				getStyle4().addAll((Collection<? extends StyleType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__META4:
				getMeta4().clear();
				getMeta4().addAll((Collection<? extends MetaType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__LINK4:
				getLink4().clear();
				getLink4().addAll((Collection<? extends LinkType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__OBJECT4:
				getObject4().clear();
				getObject4().addAll((Collection<? extends ObjectType>)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__DIR:
				setDir((DirType)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__ID:
				setId((String)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__LANG:
				setLang((String)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__LANG1:
				setLang1((String)newValue);
				return;
			case XhtmlPackage.HEAD_TYPE__PROFILE:
				setProfile((String)newValue);
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
			case XhtmlPackage.HEAD_TYPE__GROUP:
				getGroup().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__SCRIPT:
				getScript().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__STYLE:
				getStyle().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__META:
				getMeta().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__LINK:
				getLink().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__OBJECT:
				getObject().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__TITLE:
				setTitle((TitleType)null);
				return;
			case XhtmlPackage.HEAD_TYPE__GROUP1:
				getGroup1().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__SCRIPT1:
				getScript1().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__STYLE1:
				getStyle1().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__META1:
				getMeta1().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__LINK1:
				getLink1().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__OBJECT1:
				getObject1().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__BASE:
				setBase((BaseType)null);
				return;
			case XhtmlPackage.HEAD_TYPE__GROUP2:
				getGroup2().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__SCRIPT2:
				getScript2().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__STYLE2:
				getStyle2().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__META2:
				getMeta2().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__LINK2:
				getLink2().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__OBJECT2:
				getObject2().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__BASE1:
				setBase1((BaseType)null);
				return;
			case XhtmlPackage.HEAD_TYPE__GROUP3:
				getGroup3().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__SCRIPT3:
				getScript3().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__STYLE3:
				getStyle3().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__META3:
				getMeta3().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__LINK3:
				getLink3().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__OBJECT3:
				getObject3().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__TITLE1:
				setTitle1((TitleType)null);
				return;
			case XhtmlPackage.HEAD_TYPE__GROUP4:
				getGroup4().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__SCRIPT4:
				getScript4().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__STYLE4:
				getStyle4().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__META4:
				getMeta4().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__LINK4:
				getLink4().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__OBJECT4:
				getObject4().clear();
				return;
			case XhtmlPackage.HEAD_TYPE__DIR:
				unsetDir();
				return;
			case XhtmlPackage.HEAD_TYPE__ID:
				setId(ID_EDEFAULT);
				return;
			case XhtmlPackage.HEAD_TYPE__LANG:
				setLang(LANG_EDEFAULT);
				return;
			case XhtmlPackage.HEAD_TYPE__LANG1:
				setLang1(LANG1_EDEFAULT);
				return;
			case XhtmlPackage.HEAD_TYPE__PROFILE:
				setProfile(PROFILE_EDEFAULT);
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
			case XhtmlPackage.HEAD_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case XhtmlPackage.HEAD_TYPE__SCRIPT:
				return !getScript().isEmpty();
			case XhtmlPackage.HEAD_TYPE__STYLE:
				return !getStyle().isEmpty();
			case XhtmlPackage.HEAD_TYPE__META:
				return !getMeta().isEmpty();
			case XhtmlPackage.HEAD_TYPE__LINK:
				return !getLink().isEmpty();
			case XhtmlPackage.HEAD_TYPE__OBJECT:
				return !getObject().isEmpty();
			case XhtmlPackage.HEAD_TYPE__TITLE:
				return title != null;
			case XhtmlPackage.HEAD_TYPE__GROUP1:
				return group1 != null && !group1.isEmpty();
			case XhtmlPackage.HEAD_TYPE__SCRIPT1:
				return !getScript1().isEmpty();
			case XhtmlPackage.HEAD_TYPE__STYLE1:
				return !getStyle1().isEmpty();
			case XhtmlPackage.HEAD_TYPE__META1:
				return !getMeta1().isEmpty();
			case XhtmlPackage.HEAD_TYPE__LINK1:
				return !getLink1().isEmpty();
			case XhtmlPackage.HEAD_TYPE__OBJECT1:
				return !getObject1().isEmpty();
			case XhtmlPackage.HEAD_TYPE__BASE:
				return base != null;
			case XhtmlPackage.HEAD_TYPE__GROUP2:
				return group2 != null && !group2.isEmpty();
			case XhtmlPackage.HEAD_TYPE__SCRIPT2:
				return !getScript2().isEmpty();
			case XhtmlPackage.HEAD_TYPE__STYLE2:
				return !getStyle2().isEmpty();
			case XhtmlPackage.HEAD_TYPE__META2:
				return !getMeta2().isEmpty();
			case XhtmlPackage.HEAD_TYPE__LINK2:
				return !getLink2().isEmpty();
			case XhtmlPackage.HEAD_TYPE__OBJECT2:
				return !getObject2().isEmpty();
			case XhtmlPackage.HEAD_TYPE__BASE1:
				return base1 != null;
			case XhtmlPackage.HEAD_TYPE__GROUP3:
				return group3 != null && !group3.isEmpty();
			case XhtmlPackage.HEAD_TYPE__SCRIPT3:
				return !getScript3().isEmpty();
			case XhtmlPackage.HEAD_TYPE__STYLE3:
				return !getStyle3().isEmpty();
			case XhtmlPackage.HEAD_TYPE__META3:
				return !getMeta3().isEmpty();
			case XhtmlPackage.HEAD_TYPE__LINK3:
				return !getLink3().isEmpty();
			case XhtmlPackage.HEAD_TYPE__OBJECT3:
				return !getObject3().isEmpty();
			case XhtmlPackage.HEAD_TYPE__TITLE1:
				return title1 != null;
			case XhtmlPackage.HEAD_TYPE__GROUP4:
				return group4 != null && !group4.isEmpty();
			case XhtmlPackage.HEAD_TYPE__SCRIPT4:
				return !getScript4().isEmpty();
			case XhtmlPackage.HEAD_TYPE__STYLE4:
				return !getStyle4().isEmpty();
			case XhtmlPackage.HEAD_TYPE__META4:
				return !getMeta4().isEmpty();
			case XhtmlPackage.HEAD_TYPE__LINK4:
				return !getLink4().isEmpty();
			case XhtmlPackage.HEAD_TYPE__OBJECT4:
				return !getObject4().isEmpty();
			case XhtmlPackage.HEAD_TYPE__DIR:
				return isSetDir();
			case XhtmlPackage.HEAD_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case XhtmlPackage.HEAD_TYPE__LANG:
				return LANG_EDEFAULT == null ? lang != null : !LANG_EDEFAULT.equals(lang);
			case XhtmlPackage.HEAD_TYPE__LANG1:
				return LANG1_EDEFAULT == null ? lang1 != null : !LANG1_EDEFAULT.equals(lang1);
			case XhtmlPackage.HEAD_TYPE__PROFILE:
				return PROFILE_EDEFAULT == null ? profile != null : !PROFILE_EDEFAULT.equals(profile);
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
		result.append(", group1: ");
		result.append(group1);
		result.append(", group2: ");
		result.append(group2);
		result.append(", group3: ");
		result.append(group3);
		result.append(", group4: ");
		result.append(group4);
		result.append(", dir: ");
		if (dirESet) result.append(dir); else result.append("<unset>");
		result.append(", id: ");
		result.append(id);
		result.append(", lang: ");
		result.append(lang);
		result.append(", lang1: ");
		result.append(lang1);
		result.append(", profile: ");
		result.append(profile);
		result.append(')');
		return result.toString();
	}

} //HeadTypeImpl
