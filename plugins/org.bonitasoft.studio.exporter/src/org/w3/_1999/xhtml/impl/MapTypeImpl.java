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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.w3._1999.xhtml.AddressType;
import org.w3._1999.xhtml.AreaType;
import org.w3._1999.xhtml.BlockquoteType;
import org.w3._1999.xhtml.DelType;
import org.w3._1999.xhtml.DirType;
import org.w3._1999.xhtml.DivType;
import org.w3._1999.xhtml.DlType;
import org.w3._1999.xhtml.FieldsetType;
import org.w3._1999.xhtml.FormType;
import org.w3._1999.xhtml.H1Type;
import org.w3._1999.xhtml.H2Type;
import org.w3._1999.xhtml.H3Type;
import org.w3._1999.xhtml.H4Type;
import org.w3._1999.xhtml.H5Type;
import org.w3._1999.xhtml.H6Type;
import org.w3._1999.xhtml.HrType;
import org.w3._1999.xhtml.InsType;
import org.w3._1999.xhtml.MapType;
import org.w3._1999.xhtml.NoscriptType;
import org.w3._1999.xhtml.OlType;
import org.w3._1999.xhtml.PType;
import org.w3._1999.xhtml.PreType;
import org.w3._1999.xhtml.ScriptType;
import org.w3._1999.xhtml.TableType;
import org.w3._1999.xhtml.UlType;
import org.w3._1999.xhtml.XhtmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Map Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getP <em>P</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getH1 <em>H1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getH2 <em>H2</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getH3 <em>H3</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getH4 <em>H4</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getH5 <em>H5</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getH6 <em>H6</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getDiv <em>Div</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getUl <em>Ul</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getOl <em>Ol</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getDl <em>Dl</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getPre <em>Pre</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getHr <em>Hr</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getBlockquote <em>Blockquote</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getAddress <em>Address</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getFieldset <em>Fieldset</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getTable <em>Table</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getForm <em>Form</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getNoscript <em>Noscript</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getIns <em>Ins</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getDel <em>Del</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getScript <em>Script</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getArea <em>Area</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getClass_ <em>Class</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getDir <em>Dir</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getLang <em>Lang</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getLang1 <em>Lang1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getOnclick <em>Onclick</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getOndblclick <em>Ondblclick</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getOnkeydown <em>Onkeydown</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getOnkeypress <em>Onkeypress</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getOnkeyup <em>Onkeyup</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getOnmousedown <em>Onmousedown</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getOnmousemove <em>Onmousemove</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getOnmouseout <em>Onmouseout</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getOnmouseover <em>Onmouseover</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getOnmouseup <em>Onmouseup</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.MapTypeImpl#getTitle <em>Title</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MapTypeImpl extends EObjectImpl implements MapType {
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
	 * The cached value of the '{@link #getArea() <em>Area</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArea()
	 * @generated
	 * @ordered
	 */
	protected EList<AreaType> area;

	/**
	 * The default value of the '{@link #getClass_() <em>Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClass_()
	 * @generated
	 * @ordered
	 */
	protected static final Object CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClass_() <em>Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClass_()
	 * @generated
	 * @ordered
	 */
	protected Object class_ = CLASS_EDEFAULT;

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
	 * The default value of the '{@link #getOnclick() <em>Onclick</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnclick()
	 * @generated
	 * @ordered
	 */
	protected static final String ONCLICK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOnclick() <em>Onclick</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnclick()
	 * @generated
	 * @ordered
	 */
	protected String onclick = ONCLICK_EDEFAULT;

	/**
	 * The default value of the '{@link #getOndblclick() <em>Ondblclick</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOndblclick()
	 * @generated
	 * @ordered
	 */
	protected static final String ONDBLCLICK_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOndblclick() <em>Ondblclick</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOndblclick()
	 * @generated
	 * @ordered
	 */
	protected String ondblclick = ONDBLCLICK_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnkeydown() <em>Onkeydown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnkeydown()
	 * @generated
	 * @ordered
	 */
	protected static final String ONKEYDOWN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOnkeydown() <em>Onkeydown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnkeydown()
	 * @generated
	 * @ordered
	 */
	protected String onkeydown = ONKEYDOWN_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnkeypress() <em>Onkeypress</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnkeypress()
	 * @generated
	 * @ordered
	 */
	protected static final String ONKEYPRESS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOnkeypress() <em>Onkeypress</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnkeypress()
	 * @generated
	 * @ordered
	 */
	protected String onkeypress = ONKEYPRESS_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnkeyup() <em>Onkeyup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnkeyup()
	 * @generated
	 * @ordered
	 */
	protected static final String ONKEYUP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOnkeyup() <em>Onkeyup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnkeyup()
	 * @generated
	 * @ordered
	 */
	protected String onkeyup = ONKEYUP_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnmousedown() <em>Onmousedown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnmousedown()
	 * @generated
	 * @ordered
	 */
	protected static final String ONMOUSEDOWN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOnmousedown() <em>Onmousedown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnmousedown()
	 * @generated
	 * @ordered
	 */
	protected String onmousedown = ONMOUSEDOWN_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnmousemove() <em>Onmousemove</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnmousemove()
	 * @generated
	 * @ordered
	 */
	protected static final String ONMOUSEMOVE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOnmousemove() <em>Onmousemove</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnmousemove()
	 * @generated
	 * @ordered
	 */
	protected String onmousemove = ONMOUSEMOVE_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnmouseout() <em>Onmouseout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnmouseout()
	 * @generated
	 * @ordered
	 */
	protected static final String ONMOUSEOUT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOnmouseout() <em>Onmouseout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnmouseout()
	 * @generated
	 * @ordered
	 */
	protected String onmouseout = ONMOUSEOUT_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnmouseover() <em>Onmouseover</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnmouseover()
	 * @generated
	 * @ordered
	 */
	protected static final String ONMOUSEOVER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOnmouseover() <em>Onmouseover</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnmouseover()
	 * @generated
	 * @ordered
	 */
	protected String onmouseover = ONMOUSEOVER_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnmouseup() <em>Onmouseup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnmouseup()
	 * @generated
	 * @ordered
	 */
	protected static final String ONMOUSEUP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOnmouseup() <em>Onmouseup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnmouseup()
	 * @generated
	 * @ordered
	 */
	protected String onmouseup = ONMOUSEUP_EDEFAULT;

	/**
	 * The default value of the '{@link #getStyle() <em>Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyle()
	 * @generated
	 * @ordered
	 */
	protected static final String STYLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStyle() <em>Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyle()
	 * @generated
	 * @ordered
	 */
	protected String style = STYLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected static final String TITLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected String title = TITLE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MapTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XhtmlPackage.eINSTANCE.getMapType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, XhtmlPackage.MAP_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PType> getP() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getMapType_P());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<H1Type> getH1() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getMapType_H1());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<H2Type> getH2() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getMapType_H2());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<H3Type> getH3() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getMapType_H3());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<H4Type> getH4() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getMapType_H4());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<H5Type> getH5() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getMapType_H5());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<H6Type> getH6() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getMapType_H6());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DivType> getDiv() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getMapType_Div());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UlType> getUl() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getMapType_Ul());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OlType> getOl() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getMapType_Ol());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DlType> getDl() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getMapType_Dl());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PreType> getPre() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getMapType_Pre());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<HrType> getHr() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getMapType_Hr());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BlockquoteType> getBlockquote() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getMapType_Blockquote());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AddressType> getAddress() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getMapType_Address());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FieldsetType> getFieldset() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getMapType_Fieldset());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TableType> getTable() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getMapType_Table());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FormType> getForm() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getMapType_Form());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NoscriptType> getNoscript() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getMapType_Noscript());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InsType> getIns() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getMapType_Ins());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DelType> getDel() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getMapType_Del());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScriptType> getScript() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getMapType_Script());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AreaType> getArea() {
		if (area == null) {
			area = new EObjectContainmentEList<AreaType>(AreaType.class, this, XhtmlPackage.MAP_TYPE__AREA);
		}
		return area;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getClass_() {
		return class_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClass(Object newClass) {
		Object oldClass = class_;
		class_ = newClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.MAP_TYPE__CLASS, oldClass, class_));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.MAP_TYPE__DIR, oldDir, dir, !oldDirESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, XhtmlPackage.MAP_TYPE__DIR, oldDir, DIR_EDEFAULT, oldDirESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.MAP_TYPE__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.MAP_TYPE__LANG, oldLang, lang));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.MAP_TYPE__LANG1, oldLang1, lang1));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.MAP_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOnclick() {
		return onclick;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnclick(String newOnclick) {
		String oldOnclick = onclick;
		onclick = newOnclick;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.MAP_TYPE__ONCLICK, oldOnclick, onclick));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOndblclick() {
		return ondblclick;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOndblclick(String newOndblclick) {
		String oldOndblclick = ondblclick;
		ondblclick = newOndblclick;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.MAP_TYPE__ONDBLCLICK, oldOndblclick, ondblclick));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOnkeydown() {
		return onkeydown;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnkeydown(String newOnkeydown) {
		String oldOnkeydown = onkeydown;
		onkeydown = newOnkeydown;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.MAP_TYPE__ONKEYDOWN, oldOnkeydown, onkeydown));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOnkeypress() {
		return onkeypress;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnkeypress(String newOnkeypress) {
		String oldOnkeypress = onkeypress;
		onkeypress = newOnkeypress;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.MAP_TYPE__ONKEYPRESS, oldOnkeypress, onkeypress));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOnkeyup() {
		return onkeyup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnkeyup(String newOnkeyup) {
		String oldOnkeyup = onkeyup;
		onkeyup = newOnkeyup;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.MAP_TYPE__ONKEYUP, oldOnkeyup, onkeyup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOnmousedown() {
		return onmousedown;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnmousedown(String newOnmousedown) {
		String oldOnmousedown = onmousedown;
		onmousedown = newOnmousedown;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.MAP_TYPE__ONMOUSEDOWN, oldOnmousedown, onmousedown));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOnmousemove() {
		return onmousemove;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnmousemove(String newOnmousemove) {
		String oldOnmousemove = onmousemove;
		onmousemove = newOnmousemove;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.MAP_TYPE__ONMOUSEMOVE, oldOnmousemove, onmousemove));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOnmouseout() {
		return onmouseout;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnmouseout(String newOnmouseout) {
		String oldOnmouseout = onmouseout;
		onmouseout = newOnmouseout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.MAP_TYPE__ONMOUSEOUT, oldOnmouseout, onmouseout));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOnmouseover() {
		return onmouseover;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnmouseover(String newOnmouseover) {
		String oldOnmouseover = onmouseover;
		onmouseover = newOnmouseover;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.MAP_TYPE__ONMOUSEOVER, oldOnmouseover, onmouseover));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOnmouseup() {
		return onmouseup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnmouseup(String newOnmouseup) {
		String oldOnmouseup = onmouseup;
		onmouseup = newOnmouseup;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.MAP_TYPE__ONMOUSEUP, oldOnmouseup, onmouseup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStyle(String newStyle) {
		String oldStyle = style;
		style = newStyle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.MAP_TYPE__STYLE, oldStyle, style));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTitle(String newTitle) {
		String oldTitle = title;
		title = newTitle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.MAP_TYPE__TITLE, oldTitle, title));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case XhtmlPackage.MAP_TYPE__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__P:
				return ((InternalEList<?>)getP()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__H1:
				return ((InternalEList<?>)getH1()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__H2:
				return ((InternalEList<?>)getH2()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__H3:
				return ((InternalEList<?>)getH3()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__H4:
				return ((InternalEList<?>)getH4()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__H5:
				return ((InternalEList<?>)getH5()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__H6:
				return ((InternalEList<?>)getH6()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__DIV:
				return ((InternalEList<?>)getDiv()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__UL:
				return ((InternalEList<?>)getUl()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__OL:
				return ((InternalEList<?>)getOl()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__DL:
				return ((InternalEList<?>)getDl()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__PRE:
				return ((InternalEList<?>)getPre()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__HR:
				return ((InternalEList<?>)getHr()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__BLOCKQUOTE:
				return ((InternalEList<?>)getBlockquote()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__ADDRESS:
				return ((InternalEList<?>)getAddress()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__FIELDSET:
				return ((InternalEList<?>)getFieldset()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__TABLE:
				return ((InternalEList<?>)getTable()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__FORM:
				return ((InternalEList<?>)getForm()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__NOSCRIPT:
				return ((InternalEList<?>)getNoscript()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__INS:
				return ((InternalEList<?>)getIns()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__DEL:
				return ((InternalEList<?>)getDel()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__SCRIPT:
				return ((InternalEList<?>)getScript()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.MAP_TYPE__AREA:
				return ((InternalEList<?>)getArea()).basicRemove(otherEnd, msgs);
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
			case XhtmlPackage.MAP_TYPE__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case XhtmlPackage.MAP_TYPE__P:
				return getP();
			case XhtmlPackage.MAP_TYPE__H1:
				return getH1();
			case XhtmlPackage.MAP_TYPE__H2:
				return getH2();
			case XhtmlPackage.MAP_TYPE__H3:
				return getH3();
			case XhtmlPackage.MAP_TYPE__H4:
				return getH4();
			case XhtmlPackage.MAP_TYPE__H5:
				return getH5();
			case XhtmlPackage.MAP_TYPE__H6:
				return getH6();
			case XhtmlPackage.MAP_TYPE__DIV:
				return getDiv();
			case XhtmlPackage.MAP_TYPE__UL:
				return getUl();
			case XhtmlPackage.MAP_TYPE__OL:
				return getOl();
			case XhtmlPackage.MAP_TYPE__DL:
				return getDl();
			case XhtmlPackage.MAP_TYPE__PRE:
				return getPre();
			case XhtmlPackage.MAP_TYPE__HR:
				return getHr();
			case XhtmlPackage.MAP_TYPE__BLOCKQUOTE:
				return getBlockquote();
			case XhtmlPackage.MAP_TYPE__ADDRESS:
				return getAddress();
			case XhtmlPackage.MAP_TYPE__FIELDSET:
				return getFieldset();
			case XhtmlPackage.MAP_TYPE__TABLE:
				return getTable();
			case XhtmlPackage.MAP_TYPE__FORM:
				return getForm();
			case XhtmlPackage.MAP_TYPE__NOSCRIPT:
				return getNoscript();
			case XhtmlPackage.MAP_TYPE__INS:
				return getIns();
			case XhtmlPackage.MAP_TYPE__DEL:
				return getDel();
			case XhtmlPackage.MAP_TYPE__SCRIPT:
				return getScript();
			case XhtmlPackage.MAP_TYPE__AREA:
				return getArea();
			case XhtmlPackage.MAP_TYPE__CLASS:
				return getClass_();
			case XhtmlPackage.MAP_TYPE__DIR:
				return getDir();
			case XhtmlPackage.MAP_TYPE__ID:
				return getId();
			case XhtmlPackage.MAP_TYPE__LANG:
				return getLang();
			case XhtmlPackage.MAP_TYPE__LANG1:
				return getLang1();
			case XhtmlPackage.MAP_TYPE__NAME:
				return getName();
			case XhtmlPackage.MAP_TYPE__ONCLICK:
				return getOnclick();
			case XhtmlPackage.MAP_TYPE__ONDBLCLICK:
				return getOndblclick();
			case XhtmlPackage.MAP_TYPE__ONKEYDOWN:
				return getOnkeydown();
			case XhtmlPackage.MAP_TYPE__ONKEYPRESS:
				return getOnkeypress();
			case XhtmlPackage.MAP_TYPE__ONKEYUP:
				return getOnkeyup();
			case XhtmlPackage.MAP_TYPE__ONMOUSEDOWN:
				return getOnmousedown();
			case XhtmlPackage.MAP_TYPE__ONMOUSEMOVE:
				return getOnmousemove();
			case XhtmlPackage.MAP_TYPE__ONMOUSEOUT:
				return getOnmouseout();
			case XhtmlPackage.MAP_TYPE__ONMOUSEOVER:
				return getOnmouseover();
			case XhtmlPackage.MAP_TYPE__ONMOUSEUP:
				return getOnmouseup();
			case XhtmlPackage.MAP_TYPE__STYLE:
				return getStyle();
			case XhtmlPackage.MAP_TYPE__TITLE:
				return getTitle();
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
			case XhtmlPackage.MAP_TYPE__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case XhtmlPackage.MAP_TYPE__P:
				getP().clear();
				getP().addAll((Collection<? extends PType>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__H1:
				getH1().clear();
				getH1().addAll((Collection<? extends H1Type>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__H2:
				getH2().clear();
				getH2().addAll((Collection<? extends H2Type>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__H3:
				getH3().clear();
				getH3().addAll((Collection<? extends H3Type>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__H4:
				getH4().clear();
				getH4().addAll((Collection<? extends H4Type>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__H5:
				getH5().clear();
				getH5().addAll((Collection<? extends H5Type>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__H6:
				getH6().clear();
				getH6().addAll((Collection<? extends H6Type>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__DIV:
				getDiv().clear();
				getDiv().addAll((Collection<? extends DivType>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__UL:
				getUl().clear();
				getUl().addAll((Collection<? extends UlType>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__OL:
				getOl().clear();
				getOl().addAll((Collection<? extends OlType>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__DL:
				getDl().clear();
				getDl().addAll((Collection<? extends DlType>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__PRE:
				getPre().clear();
				getPre().addAll((Collection<? extends PreType>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__HR:
				getHr().clear();
				getHr().addAll((Collection<? extends HrType>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__BLOCKQUOTE:
				getBlockquote().clear();
				getBlockquote().addAll((Collection<? extends BlockquoteType>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__ADDRESS:
				getAddress().clear();
				getAddress().addAll((Collection<? extends AddressType>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__FIELDSET:
				getFieldset().clear();
				getFieldset().addAll((Collection<? extends FieldsetType>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__TABLE:
				getTable().clear();
				getTable().addAll((Collection<? extends TableType>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__FORM:
				getForm().clear();
				getForm().addAll((Collection<? extends FormType>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__NOSCRIPT:
				getNoscript().clear();
				getNoscript().addAll((Collection<? extends NoscriptType>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__INS:
				getIns().clear();
				getIns().addAll((Collection<? extends InsType>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__DEL:
				getDel().clear();
				getDel().addAll((Collection<? extends DelType>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__SCRIPT:
				getScript().clear();
				getScript().addAll((Collection<? extends ScriptType>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__AREA:
				getArea().clear();
				getArea().addAll((Collection<? extends AreaType>)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__CLASS:
				setClass(newValue);
				return;
			case XhtmlPackage.MAP_TYPE__DIR:
				setDir((DirType)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__ID:
				setId((String)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__LANG:
				setLang((String)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__LANG1:
				setLang1((String)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__NAME:
				setName((String)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__ONCLICK:
				setOnclick((String)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__ONDBLCLICK:
				setOndblclick((String)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__ONKEYDOWN:
				setOnkeydown((String)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__ONKEYPRESS:
				setOnkeypress((String)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__ONKEYUP:
				setOnkeyup((String)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__ONMOUSEDOWN:
				setOnmousedown((String)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__ONMOUSEMOVE:
				setOnmousemove((String)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__ONMOUSEOUT:
				setOnmouseout((String)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__ONMOUSEOVER:
				setOnmouseover((String)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__ONMOUSEUP:
				setOnmouseup((String)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__STYLE:
				setStyle((String)newValue);
				return;
			case XhtmlPackage.MAP_TYPE__TITLE:
				setTitle((String)newValue);
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
			case XhtmlPackage.MAP_TYPE__GROUP:
				getGroup().clear();
				return;
			case XhtmlPackage.MAP_TYPE__P:
				getP().clear();
				return;
			case XhtmlPackage.MAP_TYPE__H1:
				getH1().clear();
				return;
			case XhtmlPackage.MAP_TYPE__H2:
				getH2().clear();
				return;
			case XhtmlPackage.MAP_TYPE__H3:
				getH3().clear();
				return;
			case XhtmlPackage.MAP_TYPE__H4:
				getH4().clear();
				return;
			case XhtmlPackage.MAP_TYPE__H5:
				getH5().clear();
				return;
			case XhtmlPackage.MAP_TYPE__H6:
				getH6().clear();
				return;
			case XhtmlPackage.MAP_TYPE__DIV:
				getDiv().clear();
				return;
			case XhtmlPackage.MAP_TYPE__UL:
				getUl().clear();
				return;
			case XhtmlPackage.MAP_TYPE__OL:
				getOl().clear();
				return;
			case XhtmlPackage.MAP_TYPE__DL:
				getDl().clear();
				return;
			case XhtmlPackage.MAP_TYPE__PRE:
				getPre().clear();
				return;
			case XhtmlPackage.MAP_TYPE__HR:
				getHr().clear();
				return;
			case XhtmlPackage.MAP_TYPE__BLOCKQUOTE:
				getBlockquote().clear();
				return;
			case XhtmlPackage.MAP_TYPE__ADDRESS:
				getAddress().clear();
				return;
			case XhtmlPackage.MAP_TYPE__FIELDSET:
				getFieldset().clear();
				return;
			case XhtmlPackage.MAP_TYPE__TABLE:
				getTable().clear();
				return;
			case XhtmlPackage.MAP_TYPE__FORM:
				getForm().clear();
				return;
			case XhtmlPackage.MAP_TYPE__NOSCRIPT:
				getNoscript().clear();
				return;
			case XhtmlPackage.MAP_TYPE__INS:
				getIns().clear();
				return;
			case XhtmlPackage.MAP_TYPE__DEL:
				getDel().clear();
				return;
			case XhtmlPackage.MAP_TYPE__SCRIPT:
				getScript().clear();
				return;
			case XhtmlPackage.MAP_TYPE__AREA:
				getArea().clear();
				return;
			case XhtmlPackage.MAP_TYPE__CLASS:
				setClass(CLASS_EDEFAULT);
				return;
			case XhtmlPackage.MAP_TYPE__DIR:
				unsetDir();
				return;
			case XhtmlPackage.MAP_TYPE__ID:
				setId(ID_EDEFAULT);
				return;
			case XhtmlPackage.MAP_TYPE__LANG:
				setLang(LANG_EDEFAULT);
				return;
			case XhtmlPackage.MAP_TYPE__LANG1:
				setLang1(LANG1_EDEFAULT);
				return;
			case XhtmlPackage.MAP_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case XhtmlPackage.MAP_TYPE__ONCLICK:
				setOnclick(ONCLICK_EDEFAULT);
				return;
			case XhtmlPackage.MAP_TYPE__ONDBLCLICK:
				setOndblclick(ONDBLCLICK_EDEFAULT);
				return;
			case XhtmlPackage.MAP_TYPE__ONKEYDOWN:
				setOnkeydown(ONKEYDOWN_EDEFAULT);
				return;
			case XhtmlPackage.MAP_TYPE__ONKEYPRESS:
				setOnkeypress(ONKEYPRESS_EDEFAULT);
				return;
			case XhtmlPackage.MAP_TYPE__ONKEYUP:
				setOnkeyup(ONKEYUP_EDEFAULT);
				return;
			case XhtmlPackage.MAP_TYPE__ONMOUSEDOWN:
				setOnmousedown(ONMOUSEDOWN_EDEFAULT);
				return;
			case XhtmlPackage.MAP_TYPE__ONMOUSEMOVE:
				setOnmousemove(ONMOUSEMOVE_EDEFAULT);
				return;
			case XhtmlPackage.MAP_TYPE__ONMOUSEOUT:
				setOnmouseout(ONMOUSEOUT_EDEFAULT);
				return;
			case XhtmlPackage.MAP_TYPE__ONMOUSEOVER:
				setOnmouseover(ONMOUSEOVER_EDEFAULT);
				return;
			case XhtmlPackage.MAP_TYPE__ONMOUSEUP:
				setOnmouseup(ONMOUSEUP_EDEFAULT);
				return;
			case XhtmlPackage.MAP_TYPE__STYLE:
				setStyle(STYLE_EDEFAULT);
				return;
			case XhtmlPackage.MAP_TYPE__TITLE:
				setTitle(TITLE_EDEFAULT);
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
			case XhtmlPackage.MAP_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case XhtmlPackage.MAP_TYPE__P:
				return !getP().isEmpty();
			case XhtmlPackage.MAP_TYPE__H1:
				return !getH1().isEmpty();
			case XhtmlPackage.MAP_TYPE__H2:
				return !getH2().isEmpty();
			case XhtmlPackage.MAP_TYPE__H3:
				return !getH3().isEmpty();
			case XhtmlPackage.MAP_TYPE__H4:
				return !getH4().isEmpty();
			case XhtmlPackage.MAP_TYPE__H5:
				return !getH5().isEmpty();
			case XhtmlPackage.MAP_TYPE__H6:
				return !getH6().isEmpty();
			case XhtmlPackage.MAP_TYPE__DIV:
				return !getDiv().isEmpty();
			case XhtmlPackage.MAP_TYPE__UL:
				return !getUl().isEmpty();
			case XhtmlPackage.MAP_TYPE__OL:
				return !getOl().isEmpty();
			case XhtmlPackage.MAP_TYPE__DL:
				return !getDl().isEmpty();
			case XhtmlPackage.MAP_TYPE__PRE:
				return !getPre().isEmpty();
			case XhtmlPackage.MAP_TYPE__HR:
				return !getHr().isEmpty();
			case XhtmlPackage.MAP_TYPE__BLOCKQUOTE:
				return !getBlockquote().isEmpty();
			case XhtmlPackage.MAP_TYPE__ADDRESS:
				return !getAddress().isEmpty();
			case XhtmlPackage.MAP_TYPE__FIELDSET:
				return !getFieldset().isEmpty();
			case XhtmlPackage.MAP_TYPE__TABLE:
				return !getTable().isEmpty();
			case XhtmlPackage.MAP_TYPE__FORM:
				return !getForm().isEmpty();
			case XhtmlPackage.MAP_TYPE__NOSCRIPT:
				return !getNoscript().isEmpty();
			case XhtmlPackage.MAP_TYPE__INS:
				return !getIns().isEmpty();
			case XhtmlPackage.MAP_TYPE__DEL:
				return !getDel().isEmpty();
			case XhtmlPackage.MAP_TYPE__SCRIPT:
				return !getScript().isEmpty();
			case XhtmlPackage.MAP_TYPE__AREA:
				return area != null && !area.isEmpty();
			case XhtmlPackage.MAP_TYPE__CLASS:
				return CLASS_EDEFAULT == null ? class_ != null : !CLASS_EDEFAULT.equals(class_);
			case XhtmlPackage.MAP_TYPE__DIR:
				return isSetDir();
			case XhtmlPackage.MAP_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case XhtmlPackage.MAP_TYPE__LANG:
				return LANG_EDEFAULT == null ? lang != null : !LANG_EDEFAULT.equals(lang);
			case XhtmlPackage.MAP_TYPE__LANG1:
				return LANG1_EDEFAULT == null ? lang1 != null : !LANG1_EDEFAULT.equals(lang1);
			case XhtmlPackage.MAP_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case XhtmlPackage.MAP_TYPE__ONCLICK:
				return ONCLICK_EDEFAULT == null ? onclick != null : !ONCLICK_EDEFAULT.equals(onclick);
			case XhtmlPackage.MAP_TYPE__ONDBLCLICK:
				return ONDBLCLICK_EDEFAULT == null ? ondblclick != null : !ONDBLCLICK_EDEFAULT.equals(ondblclick);
			case XhtmlPackage.MAP_TYPE__ONKEYDOWN:
				return ONKEYDOWN_EDEFAULT == null ? onkeydown != null : !ONKEYDOWN_EDEFAULT.equals(onkeydown);
			case XhtmlPackage.MAP_TYPE__ONKEYPRESS:
				return ONKEYPRESS_EDEFAULT == null ? onkeypress != null : !ONKEYPRESS_EDEFAULT.equals(onkeypress);
			case XhtmlPackage.MAP_TYPE__ONKEYUP:
				return ONKEYUP_EDEFAULT == null ? onkeyup != null : !ONKEYUP_EDEFAULT.equals(onkeyup);
			case XhtmlPackage.MAP_TYPE__ONMOUSEDOWN:
				return ONMOUSEDOWN_EDEFAULT == null ? onmousedown != null : !ONMOUSEDOWN_EDEFAULT.equals(onmousedown);
			case XhtmlPackage.MAP_TYPE__ONMOUSEMOVE:
				return ONMOUSEMOVE_EDEFAULT == null ? onmousemove != null : !ONMOUSEMOVE_EDEFAULT.equals(onmousemove);
			case XhtmlPackage.MAP_TYPE__ONMOUSEOUT:
				return ONMOUSEOUT_EDEFAULT == null ? onmouseout != null : !ONMOUSEOUT_EDEFAULT.equals(onmouseout);
			case XhtmlPackage.MAP_TYPE__ONMOUSEOVER:
				return ONMOUSEOVER_EDEFAULT == null ? onmouseover != null : !ONMOUSEOVER_EDEFAULT.equals(onmouseover);
			case XhtmlPackage.MAP_TYPE__ONMOUSEUP:
				return ONMOUSEUP_EDEFAULT == null ? onmouseup != null : !ONMOUSEUP_EDEFAULT.equals(onmouseup);
			case XhtmlPackage.MAP_TYPE__STYLE:
				return STYLE_EDEFAULT == null ? style != null : !STYLE_EDEFAULT.equals(style);
			case XhtmlPackage.MAP_TYPE__TITLE:
				return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
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
		result.append(", class: ");
		result.append(class_);
		result.append(", dir: ");
		if (dirESet) result.append(dir); else result.append("<unset>");
		result.append(", id: ");
		result.append(id);
		result.append(", lang: ");
		result.append(lang);
		result.append(", lang1: ");
		result.append(lang1);
		result.append(", name: ");
		result.append(name);
		result.append(", onclick: ");
		result.append(onclick);
		result.append(", ondblclick: ");
		result.append(ondblclick);
		result.append(", onkeydown: ");
		result.append(onkeydown);
		result.append(", onkeypress: ");
		result.append(onkeypress);
		result.append(", onkeyup: ");
		result.append(onkeyup);
		result.append(", onmousedown: ");
		result.append(onmousedown);
		result.append(", onmousemove: ");
		result.append(onmousemove);
		result.append(", onmouseout: ");
		result.append(onmouseout);
		result.append(", onmouseover: ");
		result.append(onmouseover);
		result.append(", onmouseup: ");
		result.append(onmouseup);
		result.append(", style: ");
		result.append(style);
		result.append(", title: ");
		result.append(title);
		result.append(')');
		return result.toString();
	}

} //MapTypeImpl
