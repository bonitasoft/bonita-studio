/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.w3._1999.xhtml.impl;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.w3._1999.xhtml.DirType;
import org.w3._1999.xhtml.ImgType;
import org.w3._1999.xhtml.IsmapType;
import org.w3._1999.xhtml.XhtmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Img Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getAlt <em>Alt</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getClass_ <em>Class</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getDir <em>Dir</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getIsmap <em>Ismap</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getLang <em>Lang</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getLang1 <em>Lang1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getLongdesc <em>Longdesc</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getOnclick <em>Onclick</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getOndblclick <em>Ondblclick</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getOnkeydown <em>Onkeydown</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getOnkeypress <em>Onkeypress</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getOnkeyup <em>Onkeyup</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getOnmousedown <em>Onmousedown</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getOnmousemove <em>Onmousemove</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getOnmouseout <em>Onmouseout</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getOnmouseover <em>Onmouseover</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getOnmouseup <em>Onmouseup</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getSrc <em>Src</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getUsemap <em>Usemap</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ImgTypeImpl#getWidth <em>Width</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ImgTypeImpl extends EObjectImpl implements ImgType {
	/**
	 * The default value of the '{@link #getAlt() <em>Alt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlt()
	 * @generated
	 * @ordered
	 */
	protected static final String ALT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAlt() <em>Alt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAlt()
	 * @generated
	 * @ordered
	 */
	protected String alt = ALT_EDEFAULT;

	/**
	 * The default value of the '{@link #getClass_() <em>Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClass_()
	 * @generated
	 * @ordered
	 */
	protected static final List<String> CLASS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClass_() <em>Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClass_()
	 * @generated
	 * @ordered
	 */
	protected List<String> class_ = CLASS_EDEFAULT;

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
	 * The default value of the '{@link #getHeight() <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeight()
	 * @generated
	 * @ordered
	 */
	protected static final String HEIGHT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHeight() <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeight()
	 * @generated
	 * @ordered
	 */
	protected String height = HEIGHT_EDEFAULT;

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
	 * The default value of the '{@link #getIsmap() <em>Ismap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsmap()
	 * @generated
	 * @ordered
	 */
	protected static final IsmapType ISMAP_EDEFAULT = IsmapType.ISMAP;

	/**
	 * The cached value of the '{@link #getIsmap() <em>Ismap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIsmap()
	 * @generated
	 * @ordered
	 */
	protected IsmapType ismap = ISMAP_EDEFAULT;

	/**
	 * This is true if the Ismap attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean ismapESet;

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
	 * The default value of the '{@link #getLongdesc() <em>Longdesc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLongdesc()
	 * @generated
	 * @ordered
	 */
	protected static final String LONGDESC_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLongdesc() <em>Longdesc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLongdesc()
	 * @generated
	 * @ordered
	 */
	protected String longdesc = LONGDESC_EDEFAULT;

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
	 * The default value of the '{@link #getSrc() <em>Src</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSrc()
	 * @generated
	 * @ordered
	 */
	protected static final String SRC_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSrc() <em>Src</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSrc()
	 * @generated
	 * @ordered
	 */
	protected String src = SRC_EDEFAULT;

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
	 * The default value of the '{@link #getUsemap() <em>Usemap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsemap()
	 * @generated
	 * @ordered
	 */
	protected static final String USEMAP_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUsemap() <em>Usemap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsemap()
	 * @generated
	 * @ordered
	 */
	protected String usemap = USEMAP_EDEFAULT;

	/**
	 * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected static final String WIDTH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected String width = WIDTH_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ImgTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XhtmlPackage.eINSTANCE.getImgType();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAlt() {
		return alt;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAlt(String newAlt) {
		String oldAlt = alt;
		alt = newAlt;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__ALT, oldAlt, alt));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<String> getClass_() {
		return class_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setClass(List<String> newClass) {
		List<String> oldClass = class_;
		class_ = newClass;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__CLASS, oldClass, class_));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__DIR, oldDir, dir, !oldDirESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, XhtmlPackage.IMG_TYPE__DIR, oldDir, DIR_EDEFAULT, oldDirESet));
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
	public String getHeight() {
		return height;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHeight(String newHeight) {
		String oldHeight = height;
		height = newHeight;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__HEIGHT, oldHeight, height));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IsmapType getIsmap() {
		return ismap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsmap(IsmapType newIsmap) {
		IsmapType oldIsmap = ismap;
		ismap = newIsmap == null ? ISMAP_EDEFAULT : newIsmap;
		boolean oldIsmapESet = ismapESet;
		ismapESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__ISMAP, oldIsmap, ismap, !oldIsmapESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsmap() {
		IsmapType oldIsmap = ismap;
		boolean oldIsmapESet = ismapESet;
		ismap = ISMAP_EDEFAULT;
		ismapESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, XhtmlPackage.IMG_TYPE__ISMAP, oldIsmap, ISMAP_EDEFAULT, oldIsmapESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsmap() {
		return ismapESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__LANG, oldLang, lang));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__LANG1, oldLang1, lang1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLongdesc() {
		return longdesc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLongdesc(String newLongdesc) {
		String oldLongdesc = longdesc;
		longdesc = newLongdesc;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__LONGDESC, oldLongdesc, longdesc));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__ONCLICK, oldOnclick, onclick));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__ONDBLCLICK, oldOndblclick, ondblclick));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__ONKEYDOWN, oldOnkeydown, onkeydown));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__ONKEYPRESS, oldOnkeypress, onkeypress));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__ONKEYUP, oldOnkeyup, onkeyup));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__ONMOUSEDOWN, oldOnmousedown, onmousedown));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__ONMOUSEMOVE, oldOnmousemove, onmousemove));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__ONMOUSEOUT, oldOnmouseout, onmouseout));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__ONMOUSEOVER, oldOnmouseover, onmouseover));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__ONMOUSEUP, oldOnmouseup, onmouseup));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSrc() {
		return src;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSrc(String newSrc) {
		String oldSrc = src;
		src = newSrc;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__SRC, oldSrc, src));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__STYLE, oldStyle, style));
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
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__TITLE, oldTitle, title));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUsemap() {
		return usemap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUsemap(String newUsemap) {
		String oldUsemap = usemap;
		usemap = newUsemap;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__USEMAP, oldUsemap, usemap));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWidth(String newWidth) {
		String oldWidth = width;
		width = newWidth;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XhtmlPackage.IMG_TYPE__WIDTH, oldWidth, width));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case XhtmlPackage.IMG_TYPE__ALT:
				return getAlt();
			case XhtmlPackage.IMG_TYPE__CLASS:
				return getClass_();
			case XhtmlPackage.IMG_TYPE__DIR:
				return getDir();
			case XhtmlPackage.IMG_TYPE__HEIGHT:
				return getHeight();
			case XhtmlPackage.IMG_TYPE__ID:
				return getId();
			case XhtmlPackage.IMG_TYPE__ISMAP:
				return getIsmap();
			case XhtmlPackage.IMG_TYPE__LANG:
				return getLang();
			case XhtmlPackage.IMG_TYPE__LANG1:
				return getLang1();
			case XhtmlPackage.IMG_TYPE__LONGDESC:
				return getLongdesc();
			case XhtmlPackage.IMG_TYPE__ONCLICK:
				return getOnclick();
			case XhtmlPackage.IMG_TYPE__ONDBLCLICK:
				return getOndblclick();
			case XhtmlPackage.IMG_TYPE__ONKEYDOWN:
				return getOnkeydown();
			case XhtmlPackage.IMG_TYPE__ONKEYPRESS:
				return getOnkeypress();
			case XhtmlPackage.IMG_TYPE__ONKEYUP:
				return getOnkeyup();
			case XhtmlPackage.IMG_TYPE__ONMOUSEDOWN:
				return getOnmousedown();
			case XhtmlPackage.IMG_TYPE__ONMOUSEMOVE:
				return getOnmousemove();
			case XhtmlPackage.IMG_TYPE__ONMOUSEOUT:
				return getOnmouseout();
			case XhtmlPackage.IMG_TYPE__ONMOUSEOVER:
				return getOnmouseover();
			case XhtmlPackage.IMG_TYPE__ONMOUSEUP:
				return getOnmouseup();
			case XhtmlPackage.IMG_TYPE__SRC:
				return getSrc();
			case XhtmlPackage.IMG_TYPE__STYLE:
				return getStyle();
			case XhtmlPackage.IMG_TYPE__TITLE:
				return getTitle();
			case XhtmlPackage.IMG_TYPE__USEMAP:
				return getUsemap();
			case XhtmlPackage.IMG_TYPE__WIDTH:
				return getWidth();
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
			case XhtmlPackage.IMG_TYPE__ALT:
				setAlt((String)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__CLASS:
				setClass((List<String>)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__DIR:
				setDir((DirType)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__HEIGHT:
				setHeight((String)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__ID:
				setId((String)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__ISMAP:
				setIsmap((IsmapType)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__LANG:
				setLang((String)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__LANG1:
				setLang1((String)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__LONGDESC:
				setLongdesc((String)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__ONCLICK:
				setOnclick((String)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__ONDBLCLICK:
				setOndblclick((String)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__ONKEYDOWN:
				setOnkeydown((String)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__ONKEYPRESS:
				setOnkeypress((String)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__ONKEYUP:
				setOnkeyup((String)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__ONMOUSEDOWN:
				setOnmousedown((String)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__ONMOUSEMOVE:
				setOnmousemove((String)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__ONMOUSEOUT:
				setOnmouseout((String)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__ONMOUSEOVER:
				setOnmouseover((String)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__ONMOUSEUP:
				setOnmouseup((String)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__SRC:
				setSrc((String)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__STYLE:
				setStyle((String)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__TITLE:
				setTitle((String)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__USEMAP:
				setUsemap((String)newValue);
				return;
			case XhtmlPackage.IMG_TYPE__WIDTH:
				setWidth((String)newValue);
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
			case XhtmlPackage.IMG_TYPE__ALT:
				setAlt(ALT_EDEFAULT);
				return;
			case XhtmlPackage.IMG_TYPE__CLASS:
				setClass(CLASS_EDEFAULT);
				return;
			case XhtmlPackage.IMG_TYPE__DIR:
				unsetDir();
				return;
			case XhtmlPackage.IMG_TYPE__HEIGHT:
				setHeight(HEIGHT_EDEFAULT);
				return;
			case XhtmlPackage.IMG_TYPE__ID:
				setId(ID_EDEFAULT);
				return;
			case XhtmlPackage.IMG_TYPE__ISMAP:
				unsetIsmap();
				return;
			case XhtmlPackage.IMG_TYPE__LANG:
				setLang(LANG_EDEFAULT);
				return;
			case XhtmlPackage.IMG_TYPE__LANG1:
				setLang1(LANG1_EDEFAULT);
				return;
			case XhtmlPackage.IMG_TYPE__LONGDESC:
				setLongdesc(LONGDESC_EDEFAULT);
				return;
			case XhtmlPackage.IMG_TYPE__ONCLICK:
				setOnclick(ONCLICK_EDEFAULT);
				return;
			case XhtmlPackage.IMG_TYPE__ONDBLCLICK:
				setOndblclick(ONDBLCLICK_EDEFAULT);
				return;
			case XhtmlPackage.IMG_TYPE__ONKEYDOWN:
				setOnkeydown(ONKEYDOWN_EDEFAULT);
				return;
			case XhtmlPackage.IMG_TYPE__ONKEYPRESS:
				setOnkeypress(ONKEYPRESS_EDEFAULT);
				return;
			case XhtmlPackage.IMG_TYPE__ONKEYUP:
				setOnkeyup(ONKEYUP_EDEFAULT);
				return;
			case XhtmlPackage.IMG_TYPE__ONMOUSEDOWN:
				setOnmousedown(ONMOUSEDOWN_EDEFAULT);
				return;
			case XhtmlPackage.IMG_TYPE__ONMOUSEMOVE:
				setOnmousemove(ONMOUSEMOVE_EDEFAULT);
				return;
			case XhtmlPackage.IMG_TYPE__ONMOUSEOUT:
				setOnmouseout(ONMOUSEOUT_EDEFAULT);
				return;
			case XhtmlPackage.IMG_TYPE__ONMOUSEOVER:
				setOnmouseover(ONMOUSEOVER_EDEFAULT);
				return;
			case XhtmlPackage.IMG_TYPE__ONMOUSEUP:
				setOnmouseup(ONMOUSEUP_EDEFAULT);
				return;
			case XhtmlPackage.IMG_TYPE__SRC:
				setSrc(SRC_EDEFAULT);
				return;
			case XhtmlPackage.IMG_TYPE__STYLE:
				setStyle(STYLE_EDEFAULT);
				return;
			case XhtmlPackage.IMG_TYPE__TITLE:
				setTitle(TITLE_EDEFAULT);
				return;
			case XhtmlPackage.IMG_TYPE__USEMAP:
				setUsemap(USEMAP_EDEFAULT);
				return;
			case XhtmlPackage.IMG_TYPE__WIDTH:
				setWidth(WIDTH_EDEFAULT);
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
			case XhtmlPackage.IMG_TYPE__ALT:
				return ALT_EDEFAULT == null ? alt != null : !ALT_EDEFAULT.equals(alt);
			case XhtmlPackage.IMG_TYPE__CLASS:
				return CLASS_EDEFAULT == null ? class_ != null : !CLASS_EDEFAULT.equals(class_);
			case XhtmlPackage.IMG_TYPE__DIR:
				return isSetDir();
			case XhtmlPackage.IMG_TYPE__HEIGHT:
				return HEIGHT_EDEFAULT == null ? height != null : !HEIGHT_EDEFAULT.equals(height);
			case XhtmlPackage.IMG_TYPE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case XhtmlPackage.IMG_TYPE__ISMAP:
				return isSetIsmap();
			case XhtmlPackage.IMG_TYPE__LANG:
				return LANG_EDEFAULT == null ? lang != null : !LANG_EDEFAULT.equals(lang);
			case XhtmlPackage.IMG_TYPE__LANG1:
				return LANG1_EDEFAULT == null ? lang1 != null : !LANG1_EDEFAULT.equals(lang1);
			case XhtmlPackage.IMG_TYPE__LONGDESC:
				return LONGDESC_EDEFAULT == null ? longdesc != null : !LONGDESC_EDEFAULT.equals(longdesc);
			case XhtmlPackage.IMG_TYPE__ONCLICK:
				return ONCLICK_EDEFAULT == null ? onclick != null : !ONCLICK_EDEFAULT.equals(onclick);
			case XhtmlPackage.IMG_TYPE__ONDBLCLICK:
				return ONDBLCLICK_EDEFAULT == null ? ondblclick != null : !ONDBLCLICK_EDEFAULT.equals(ondblclick);
			case XhtmlPackage.IMG_TYPE__ONKEYDOWN:
				return ONKEYDOWN_EDEFAULT == null ? onkeydown != null : !ONKEYDOWN_EDEFAULT.equals(onkeydown);
			case XhtmlPackage.IMG_TYPE__ONKEYPRESS:
				return ONKEYPRESS_EDEFAULT == null ? onkeypress != null : !ONKEYPRESS_EDEFAULT.equals(onkeypress);
			case XhtmlPackage.IMG_TYPE__ONKEYUP:
				return ONKEYUP_EDEFAULT == null ? onkeyup != null : !ONKEYUP_EDEFAULT.equals(onkeyup);
			case XhtmlPackage.IMG_TYPE__ONMOUSEDOWN:
				return ONMOUSEDOWN_EDEFAULT == null ? onmousedown != null : !ONMOUSEDOWN_EDEFAULT.equals(onmousedown);
			case XhtmlPackage.IMG_TYPE__ONMOUSEMOVE:
				return ONMOUSEMOVE_EDEFAULT == null ? onmousemove != null : !ONMOUSEMOVE_EDEFAULT.equals(onmousemove);
			case XhtmlPackage.IMG_TYPE__ONMOUSEOUT:
				return ONMOUSEOUT_EDEFAULT == null ? onmouseout != null : !ONMOUSEOUT_EDEFAULT.equals(onmouseout);
			case XhtmlPackage.IMG_TYPE__ONMOUSEOVER:
				return ONMOUSEOVER_EDEFAULT == null ? onmouseover != null : !ONMOUSEOVER_EDEFAULT.equals(onmouseover);
			case XhtmlPackage.IMG_TYPE__ONMOUSEUP:
				return ONMOUSEUP_EDEFAULT == null ? onmouseup != null : !ONMOUSEUP_EDEFAULT.equals(onmouseup);
			case XhtmlPackage.IMG_TYPE__SRC:
				return SRC_EDEFAULT == null ? src != null : !SRC_EDEFAULT.equals(src);
			case XhtmlPackage.IMG_TYPE__STYLE:
				return STYLE_EDEFAULT == null ? style != null : !STYLE_EDEFAULT.equals(style);
			case XhtmlPackage.IMG_TYPE__TITLE:
				return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
			case XhtmlPackage.IMG_TYPE__USEMAP:
				return USEMAP_EDEFAULT == null ? usemap != null : !USEMAP_EDEFAULT.equals(usemap);
			case XhtmlPackage.IMG_TYPE__WIDTH:
				return WIDTH_EDEFAULT == null ? width != null : !WIDTH_EDEFAULT.equals(width);
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
		result.append(" (alt: ");
		result.append(alt);
		result.append(", class: ");
		result.append(class_);
		result.append(", dir: ");
		if (dirESet) result.append(dir); else result.append("<unset>");
		result.append(", height: ");
		result.append(height);
		result.append(", id: ");
		result.append(id);
		result.append(", ismap: ");
		if (ismapESet) result.append(ismap); else result.append("<unset>");
		result.append(", lang: ");
		result.append(lang);
		result.append(", lang1: ");
		result.append(lang1);
		result.append(", longdesc: ");
		result.append(longdesc);
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
		result.append(", src: ");
		result.append(src);
		result.append(", style: ");
		result.append(style);
		result.append(", title: ");
		result.append(title);
		result.append(", usemap: ");
		result.append(usemap);
		result.append(", width: ");
		result.append(width);
		result.append(')');
		return result.toString();
	}

} //ImgTypeImpl
