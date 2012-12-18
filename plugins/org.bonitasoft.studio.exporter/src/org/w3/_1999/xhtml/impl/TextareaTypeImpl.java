/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.w3._1999.xhtml.impl;

import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.xml.type.impl.XMLTypeDocumentRootImpl;
import org.w3._1999.xhtml.DirType;
import org.w3._1999.xhtml.DisabledType;
import org.w3._1999.xhtml.ReadonlyType;
import org.w3._1999.xhtml.TextareaType;
import org.w3._1999.xhtml.XhtmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Textarea Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getAccesskey <em>Accesskey</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getClass_ <em>Class</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getCols <em>Cols</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getDir <em>Dir</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getDisabled <em>Disabled</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getLang <em>Lang</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getLang1 <em>Lang1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getOnblur <em>Onblur</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getOnchange <em>Onchange</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getOnclick <em>Onclick</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getOndblclick <em>Ondblclick</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getOnfocus <em>Onfocus</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getOnkeydown <em>Onkeydown</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getOnkeypress <em>Onkeypress</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getOnkeyup <em>Onkeyup</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getOnmousedown <em>Onmousedown</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getOnmousemove <em>Onmousemove</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getOnmouseout <em>Onmouseout</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getOnmouseover <em>Onmouseover</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getOnmouseup <em>Onmouseup</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getOnselect <em>Onselect</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getReadonly <em>Readonly</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getRows <em>Rows</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getTabindex <em>Tabindex</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.TextareaTypeImpl#getTitle <em>Title</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TextareaTypeImpl extends XMLTypeDocumentRootImpl implements TextareaType {
	/**
	 * The default value of the '{@link #getAccesskey() <em>Accesskey</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccesskey()
	 * @generated
	 * @ordered
	 */
	protected static final String ACCESSKEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAccesskey() <em>Accesskey</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAccesskey()
	 * @generated
	 * @ordered
	 */
	protected String accesskey = ACCESSKEY_EDEFAULT;

	/**
	 * The default value of the '{@link #getClass_() <em>Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClass_()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClass_() <em>Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClass_()
	 * @generated
	 * @ordered
	 */
	protected String class_ = CLASS_EDEFAULT;

	/**
	 * The default value of the '{@link #getCols() <em>Cols</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCols()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger COLS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCols() <em>Cols</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCols()
	 * @generated
	 * @ordered
	 */
	protected BigInteger cols = COLS_EDEFAULT;

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
	 * The default value of the '{@link #getDisabled() <em>Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisabled()
	 * @generated
	 * @ordered
	 */
	protected static final DisabledType DISABLED_EDEFAULT = DisabledType.DISABLED;

	/**
	 * The cached value of the '{@link #getDisabled() <em>Disabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisabled()
	 * @generated
	 * @ordered
	 */
	protected DisabledType disabled = DISABLED_EDEFAULT;

	/**
	 * This is true if the Disabled attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean disabledESet;

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
	protected static final Object NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected Object name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnblur() <em>Onblur</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnblur()
	 * @generated
	 * @ordered
	 */
	protected static final String ONBLUR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOnblur() <em>Onblur</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnblur()
	 * @generated
	 * @ordered
	 */
	protected String onblur = ONBLUR_EDEFAULT;

	/**
	 * The default value of the '{@link #getOnchange() <em>Onchange</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnchange()
	 * @generated
	 * @ordered
	 */
	protected static final String ONCHANGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOnchange() <em>Onchange</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnchange()
	 * @generated
	 * @ordered
	 */
	protected String onchange = ONCHANGE_EDEFAULT;

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
	 * The default value of the '{@link #getOnfocus() <em>Onfocus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnfocus()
	 * @generated
	 * @ordered
	 */
	protected static final String ONFOCUS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOnfocus() <em>Onfocus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnfocus()
	 * @generated
	 * @ordered
	 */
	protected String onfocus = ONFOCUS_EDEFAULT;

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
	 * The default value of the '{@link #getOnselect() <em>Onselect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnselect()
	 * @generated
	 * @ordered
	 */
	protected static final String ONSELECT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOnselect() <em>Onselect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOnselect()
	 * @generated
	 * @ordered
	 */
	protected String onselect = ONSELECT_EDEFAULT;

	/**
	 * The default value of the '{@link #getReadonly() <em>Readonly</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReadonly()
	 * @generated
	 * @ordered
	 */
	protected static final ReadonlyType READONLY_EDEFAULT = ReadonlyType.READONLY;

	/**
	 * The cached value of the '{@link #getReadonly() <em>Readonly</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReadonly()
	 * @generated
	 * @ordered
	 */
	protected ReadonlyType readonly = READONLY_EDEFAULT;

	/**
	 * This is true if the Readonly attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean readonlyESet;

	/**
	 * The default value of the '{@link #getRows() <em>Rows</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRows()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger ROWS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRows() <em>Rows</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRows()
	 * @generated
	 * @ordered
	 */
	protected BigInteger rows = ROWS_EDEFAULT;

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
	 * The default value of the '{@link #getTabindex() <em>Tabindex</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTabindex()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger TABINDEX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTabindex() <em>Tabindex</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTabindex()
	 * @generated
	 * @ordered
	 */
	protected BigInteger tabindex = TABINDEX_EDEFAULT;

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
	protected TextareaTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XhtmlPackage.eINSTANCE.getTextareaType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAccesskey() {
		return accesskey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAccesskey(String newAccesskey) {
		String oldAccesskey = accesskey;
		accesskey = newAccesskey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__ACCESSKEY, oldAccesskey, accesskey));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getClass_() {
		return class_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClass(String newClass) {
		String oldClass = class_;
		class_ = newClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__CLASS, oldClass, class_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getCols() {
		return cols;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCols(BigInteger newCols) {
		BigInteger oldCols = cols;
		cols = newCols;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__COLS, oldCols, cols));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__DIR, oldDir, dir, !oldDirESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, XhtmlPackage.TEXTAREA_TYPE__DIR, oldDir, DIR_EDEFAULT, oldDirESet));
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
	public DisabledType getDisabled() {
		return disabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDisabled(DisabledType newDisabled) {
		DisabledType oldDisabled = disabled;
		disabled = newDisabled == null ? DISABLED_EDEFAULT : newDisabled;
		boolean oldDisabledESet = disabledESet;
		disabledESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__DISABLED, oldDisabled, disabled, !oldDisabledESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetDisabled() {
		DisabledType oldDisabled = disabled;
		boolean oldDisabledESet = disabledESet;
		disabled = DISABLED_EDEFAULT;
		disabledESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, XhtmlPackage.TEXTAREA_TYPE__DISABLED, oldDisabled, DISABLED_EDEFAULT, oldDisabledESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetDisabled() {
		return disabledESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__LANG, oldLang, lang));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__LANG1, oldLang1, lang1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(Object newName) {
		Object oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOnblur() {
		return onblur;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnblur(String newOnblur) {
		String oldOnblur = onblur;
		onblur = newOnblur;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__ONBLUR, oldOnblur, onblur));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOnchange() {
		return onchange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnchange(String newOnchange) {
		String oldOnchange = onchange;
		onchange = newOnchange;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__ONCHANGE, oldOnchange, onchange));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__ONCLICK, oldOnclick, onclick));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__ONDBLCLICK, oldOndblclick, ondblclick));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOnfocus() {
		return onfocus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnfocus(String newOnfocus) {
		String oldOnfocus = onfocus;
		onfocus = newOnfocus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__ONFOCUS, oldOnfocus, onfocus));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__ONKEYDOWN, oldOnkeydown, onkeydown));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__ONKEYPRESS, oldOnkeypress, onkeypress));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__ONKEYUP, oldOnkeyup, onkeyup));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__ONMOUSEDOWN, oldOnmousedown, onmousedown));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__ONMOUSEMOVE, oldOnmousemove, onmousemove));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__ONMOUSEOUT, oldOnmouseout, onmouseout));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__ONMOUSEOVER, oldOnmouseover, onmouseover));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__ONMOUSEUP, oldOnmouseup, onmouseup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getOnselect() {
		return onselect;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOnselect(String newOnselect) {
		String oldOnselect = onselect;
		onselect = newOnselect;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__ONSELECT, oldOnselect, onselect));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReadonlyType getReadonly() {
		return readonly;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReadonly(ReadonlyType newReadonly) {
		ReadonlyType oldReadonly = readonly;
		readonly = newReadonly == null ? READONLY_EDEFAULT : newReadonly;
		boolean oldReadonlyESet = readonlyESet;
		readonlyESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__READONLY, oldReadonly, readonly, !oldReadonlyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetReadonly() {
		ReadonlyType oldReadonly = readonly;
		boolean oldReadonlyESet = readonlyESet;
		readonly = READONLY_EDEFAULT;
		readonlyESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, XhtmlPackage.TEXTAREA_TYPE__READONLY, oldReadonly, READONLY_EDEFAULT, oldReadonlyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetReadonly() {
		return readonlyESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getRows() {
		return rows;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRows(BigInteger newRows) {
		BigInteger oldRows = rows;
		rows = newRows;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__ROWS, oldRows, rows));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__STYLE, oldStyle, style));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger getTabindex() {
		return tabindex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTabindex(BigInteger newTabindex) {
		BigInteger oldTabindex = tabindex;
		tabindex = newTabindex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__TABINDEX, oldTabindex, tabindex));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.TEXTAREA_TYPE__TITLE, oldTitle, title));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case XhtmlPackage.TEXTAREA_TYPE__ACCESSKEY:
				return getAccesskey();
			case XhtmlPackage.TEXTAREA_TYPE__CLASS:
				return getClass_();
			case XhtmlPackage.TEXTAREA_TYPE__COLS:
				return getCols();
			case XhtmlPackage.TEXTAREA_TYPE__DIR:
				return getDir();
			case XhtmlPackage.TEXTAREA_TYPE__DISABLED:
				return getDisabled();
			case XhtmlPackage.TEXTAREA_TYPE__ID:
				return getId();
			case XhtmlPackage.TEXTAREA_TYPE__LANG:
				return getLang();
			case XhtmlPackage.TEXTAREA_TYPE__LANG1:
				return getLang1();
			case XhtmlPackage.TEXTAREA_TYPE__NAME:
				return getName();
			case XhtmlPackage.TEXTAREA_TYPE__ONBLUR:
				return getOnblur();
			case XhtmlPackage.TEXTAREA_TYPE__ONCHANGE:
				return getOnchange();
			case XhtmlPackage.TEXTAREA_TYPE__ONCLICK:
				return getOnclick();
			case XhtmlPackage.TEXTAREA_TYPE__ONDBLCLICK:
				return getOndblclick();
			case XhtmlPackage.TEXTAREA_TYPE__ONFOCUS:
				return getOnfocus();
			case XhtmlPackage.TEXTAREA_TYPE__ONKEYDOWN:
				return getOnkeydown();
			case XhtmlPackage.TEXTAREA_TYPE__ONKEYPRESS:
				return getOnkeypress();
			case XhtmlPackage.TEXTAREA_TYPE__ONKEYUP:
				return getOnkeyup();
			case XhtmlPackage.TEXTAREA_TYPE__ONMOUSEDOWN:
				return getOnmousedown();
			case XhtmlPackage.TEXTAREA_TYPE__ONMOUSEMOVE:
				return getOnmousemove();
			case XhtmlPackage.TEXTAREA_TYPE__ONMOUSEOUT:
				return getOnmouseout();
			case XhtmlPackage.TEXTAREA_TYPE__ONMOUSEOVER:
				return getOnmouseover();
			case XhtmlPackage.TEXTAREA_TYPE__ONMOUSEUP:
				return getOnmouseup();
			case XhtmlPackage.TEXTAREA_TYPE__ONSELECT:
				return getOnselect();
			case XhtmlPackage.TEXTAREA_TYPE__READONLY:
				return getReadonly();
			case XhtmlPackage.TEXTAREA_TYPE__ROWS:
				return getRows();
			case XhtmlPackage.TEXTAREA_TYPE__STYLE:
				return getStyle();
			case XhtmlPackage.TEXTAREA_TYPE__TABINDEX:
				return getTabindex();
			case XhtmlPackage.TEXTAREA_TYPE__TITLE:
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
			case XhtmlPackage.TEXTAREA_TYPE__ACCESSKEY:
				setAccesskey((String)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__CLASS:
				setClass((String)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__COLS:
				setCols((BigInteger)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__DIR:
				setDir((DirType)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__DISABLED:
				setDisabled((DisabledType)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ID:
				setId((String)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__LANG:
				setLang((String)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__LANG1:
				setLang1((String)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__NAME:
				setName(newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONBLUR:
				setOnblur((String)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONCHANGE:
				setOnchange((String)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONCLICK:
				setOnclick((String)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONDBLCLICK:
				setOndblclick((String)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONFOCUS:
				setOnfocus((String)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONKEYDOWN:
				setOnkeydown((String)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONKEYPRESS:
				setOnkeypress((String)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONKEYUP:
				setOnkeyup((String)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONMOUSEDOWN:
				setOnmousedown((String)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONMOUSEMOVE:
				setOnmousemove((String)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONMOUSEOUT:
				setOnmouseout((String)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONMOUSEOVER:
				setOnmouseover((String)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONMOUSEUP:
				setOnmouseup((String)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONSELECT:
				setOnselect((String)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__READONLY:
				setReadonly((ReadonlyType)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ROWS:
				setRows((BigInteger)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__STYLE:
				setStyle((String)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__TABINDEX:
				setTabindex((BigInteger)newValue);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__TITLE:
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
			case XhtmlPackage.TEXTAREA_TYPE__ACCESSKEY:
				setAccesskey(ACCESSKEY_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__CLASS:
				setClass(CLASS_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__COLS:
				setCols(COLS_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__DIR:
				unsetDir();
				return;
			case XhtmlPackage.TEXTAREA_TYPE__DISABLED:
				unsetDisabled();
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ID:
				setId(ID_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__LANG:
				setLang(LANG_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__LANG1:
				setLang1(LANG1_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONBLUR:
				setOnblur(ONBLUR_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONCHANGE:
				setOnchange(ONCHANGE_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONCLICK:
				setOnclick(ONCLICK_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONDBLCLICK:
				setOndblclick(ONDBLCLICK_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONFOCUS:
				setOnfocus(ONFOCUS_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONKEYDOWN:
				setOnkeydown(ONKEYDOWN_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONKEYPRESS:
				setOnkeypress(ONKEYPRESS_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONKEYUP:
				setOnkeyup(ONKEYUP_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONMOUSEDOWN:
				setOnmousedown(ONMOUSEDOWN_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONMOUSEMOVE:
				setOnmousemove(ONMOUSEMOVE_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONMOUSEOUT:
				setOnmouseout(ONMOUSEOUT_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONMOUSEOVER:
				setOnmouseover(ONMOUSEOVER_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONMOUSEUP:
				setOnmouseup(ONMOUSEUP_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ONSELECT:
				setOnselect(ONSELECT_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__READONLY:
				unsetReadonly();
				return;
			case XhtmlPackage.TEXTAREA_TYPE__ROWS:
				setRows(ROWS_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__STYLE:
				setStyle(STYLE_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__TABINDEX:
				setTabindex(TABINDEX_EDEFAULT);
				return;
			case XhtmlPackage.TEXTAREA_TYPE__TITLE:
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
			case XhtmlPackage.TEXTAREA_TYPE__ACCESSKEY:
				return ACCESSKEY_EDEFAULT == null ? accesskey != null : !ACCESSKEY_EDEFAULT.equals(accesskey);
			case XhtmlPackage.TEXTAREA_TYPE__CLASS:
				return CLASS_EDEFAULT == null ? class_ != null : !CLASS_EDEFAULT.equals(class_);
			case XhtmlPackage.TEXTAREA_TYPE__COLS:
				return COLS_EDEFAULT == null ? cols != null : !COLS_EDEFAULT.equals(cols);
			case XhtmlPackage.TEXTAREA_TYPE__DIR:
				return isSetDir();
			case XhtmlPackage.TEXTAREA_TYPE__DISABLED:
				return isSetDisabled();
			case XhtmlPackage.TEXTAREA_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case XhtmlPackage.TEXTAREA_TYPE__LANG:
				return LANG_EDEFAULT == null ? lang != null : !LANG_EDEFAULT.equals(lang);
			case XhtmlPackage.TEXTAREA_TYPE__LANG1:
				return LANG1_EDEFAULT == null ? lang1 != null : !LANG1_EDEFAULT.equals(lang1);
			case XhtmlPackage.TEXTAREA_TYPE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case XhtmlPackage.TEXTAREA_TYPE__ONBLUR:
				return ONBLUR_EDEFAULT == null ? onblur != null : !ONBLUR_EDEFAULT.equals(onblur);
			case XhtmlPackage.TEXTAREA_TYPE__ONCHANGE:
				return ONCHANGE_EDEFAULT == null ? onchange != null : !ONCHANGE_EDEFAULT.equals(onchange);
			case XhtmlPackage.TEXTAREA_TYPE__ONCLICK:
				return ONCLICK_EDEFAULT == null ? onclick != null : !ONCLICK_EDEFAULT.equals(onclick);
			case XhtmlPackage.TEXTAREA_TYPE__ONDBLCLICK:
				return ONDBLCLICK_EDEFAULT == null ? ondblclick != null : !ONDBLCLICK_EDEFAULT.equals(ondblclick);
			case XhtmlPackage.TEXTAREA_TYPE__ONFOCUS:
				return ONFOCUS_EDEFAULT == null ? onfocus != null : !ONFOCUS_EDEFAULT.equals(onfocus);
			case XhtmlPackage.TEXTAREA_TYPE__ONKEYDOWN:
				return ONKEYDOWN_EDEFAULT == null ? onkeydown != null : !ONKEYDOWN_EDEFAULT.equals(onkeydown);
			case XhtmlPackage.TEXTAREA_TYPE__ONKEYPRESS:
				return ONKEYPRESS_EDEFAULT == null ? onkeypress != null : !ONKEYPRESS_EDEFAULT.equals(onkeypress);
			case XhtmlPackage.TEXTAREA_TYPE__ONKEYUP:
				return ONKEYUP_EDEFAULT == null ? onkeyup != null : !ONKEYUP_EDEFAULT.equals(onkeyup);
			case XhtmlPackage.TEXTAREA_TYPE__ONMOUSEDOWN:
				return ONMOUSEDOWN_EDEFAULT == null ? onmousedown != null : !ONMOUSEDOWN_EDEFAULT.equals(onmousedown);
			case XhtmlPackage.TEXTAREA_TYPE__ONMOUSEMOVE:
				return ONMOUSEMOVE_EDEFAULT == null ? onmousemove != null : !ONMOUSEMOVE_EDEFAULT.equals(onmousemove);
			case XhtmlPackage.TEXTAREA_TYPE__ONMOUSEOUT:
				return ONMOUSEOUT_EDEFAULT == null ? onmouseout != null : !ONMOUSEOUT_EDEFAULT.equals(onmouseout);
			case XhtmlPackage.TEXTAREA_TYPE__ONMOUSEOVER:
				return ONMOUSEOVER_EDEFAULT == null ? onmouseover != null : !ONMOUSEOVER_EDEFAULT.equals(onmouseover);
			case XhtmlPackage.TEXTAREA_TYPE__ONMOUSEUP:
				return ONMOUSEUP_EDEFAULT == null ? onmouseup != null : !ONMOUSEUP_EDEFAULT.equals(onmouseup);
			case XhtmlPackage.TEXTAREA_TYPE__ONSELECT:
				return ONSELECT_EDEFAULT == null ? onselect != null : !ONSELECT_EDEFAULT.equals(onselect);
			case XhtmlPackage.TEXTAREA_TYPE__READONLY:
				return isSetReadonly();
			case XhtmlPackage.TEXTAREA_TYPE__ROWS:
				return ROWS_EDEFAULT == null ? rows != null : !ROWS_EDEFAULT.equals(rows);
			case XhtmlPackage.TEXTAREA_TYPE__STYLE:
				return STYLE_EDEFAULT == null ? style != null : !STYLE_EDEFAULT.equals(style);
			case XhtmlPackage.TEXTAREA_TYPE__TABINDEX:
				return TABINDEX_EDEFAULT == null ? tabindex != null : !TABINDEX_EDEFAULT.equals(tabindex);
			case XhtmlPackage.TEXTAREA_TYPE__TITLE:
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
		result.append(" (accesskey: ");
		result.append(accesskey);
		result.append(", class: ");
		result.append(class_);
		result.append(", cols: ");
		result.append(cols);
		result.append(", dir: ");
		if (dirESet) result.append(dir); else result.append("<unset>");
		result.append(", disabled: ");
		if (disabledESet) result.append(disabled); else result.append("<unset>");
		result.append(", id: ");
		result.append(id);
		result.append(", lang: ");
		result.append(lang);
		result.append(", lang1: ");
		result.append(lang1);
		result.append(", name: ");
		result.append(name);
		result.append(", onblur: ");
		result.append(onblur);
		result.append(", onchange: ");
		result.append(onchange);
		result.append(", onclick: ");
		result.append(onclick);
		result.append(", ondblclick: ");
		result.append(ondblclick);
		result.append(", onfocus: ");
		result.append(onfocus);
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
		result.append(", onselect: ");
		result.append(onselect);
		result.append(", readonly: ");
		if (readonlyESet) result.append(readonly); else result.append("<unset>");
		result.append(", rows: ");
		result.append(rows);
		result.append(", style: ");
		result.append(style);
		result.append(", tabindex: ");
		result.append(tabindex);
		result.append(", title: ");
		result.append(title);
		result.append(')');
		return result.toString();
	}

} //TextareaTypeImpl
