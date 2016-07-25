/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.w3._1999.xhtml;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Img Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getAlt <em>Alt</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getClass_ <em>Class</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getDir <em>Dir</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getHeight <em>Height</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getId <em>Id</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getIsmap <em>Ismap</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getLang <em>Lang</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getLang1 <em>Lang1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getLongdesc <em>Longdesc</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getOnclick <em>Onclick</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getOndblclick <em>Ondblclick</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getOnkeydown <em>Onkeydown</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getOnkeypress <em>Onkeypress</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getOnkeyup <em>Onkeyup</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getOnmousedown <em>Onmousedown</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getOnmousemove <em>Onmousemove</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getOnmouseout <em>Onmouseout</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getOnmouseover <em>Onmouseover</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getOnmouseup <em>Onmouseup</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getSrc <em>Src</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getStyle <em>Style</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getTitle <em>Title</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getUsemap <em>Usemap</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.ImgType#getWidth <em>Width</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType()
 * @model extendedMetaData="name='img_._type' kind='empty'"
 * @generated
 */
public interface ImgType extends EObject {
	/**
	 * Returns the value of the '<em><b>Alt</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Alt</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Alt</em>' attribute.
	 * @see #setAlt(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Alt()
	 * @model dataType="org.w3._1999.xhtml.Text" required="true"
	 *        extendedMetaData="kind='attribute' name='alt'"
	 * @generated
	 */
	String getAlt();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getAlt <em>Alt</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Alt</em>' attribute.
	 * @see #getAlt()
	 * @generated
	 */
	void setAlt(String value);

	/**
	 * Returns the value of the '<em><b>Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class</em>' attribute.
	 * @see #setClass(List)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Class()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKENS" many="false"
	 *        extendedMetaData="kind='attribute' name='class'"
	 * @generated
	 */
	List<String> getClass_();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getClass_ <em>Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class</em>' attribute.
	 * @see #getClass_()
	 * @generated
	 */
	void setClass(List<String> value);

	/**
	 * Returns the value of the '<em><b>Dir</b></em>' attribute.
	 * The literals are from the enumeration {@link org.w3._1999.xhtml.DirType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dir</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dir</em>' attribute.
	 * @see org.w3._1999.xhtml.DirType
	 * @see #isSetDir()
	 * @see #unsetDir()
	 * @see #setDir(DirType)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Dir()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='dir'"
	 * @generated
	 */
	DirType getDir();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getDir <em>Dir</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dir</em>' attribute.
	 * @see org.w3._1999.xhtml.DirType
	 * @see #isSetDir()
	 * @see #unsetDir()
	 * @see #getDir()
	 * @generated
	 */
	void setDir(DirType value);

	/**
	 * Unsets the value of the '{@link org.w3._1999.xhtml.ImgType#getDir <em>Dir</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDir()
	 * @see #getDir()
	 * @see #setDir(DirType)
	 * @generated
	 */
	void unsetDir();

	/**
	 * Returns whether the value of the '{@link org.w3._1999.xhtml.ImgType#getDir <em>Dir</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Dir</em>' attribute is set.
	 * @see #unsetDir()
	 * @see #getDir()
	 * @see #setDir(DirType)
	 * @generated
	 */
	boolean isSetDir();

	/**
	 * Returns the value of the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Height</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Height</em>' attribute.
	 * @see #setHeight(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Height()
	 * @model dataType="org.w3._1999.xhtml.Length"
	 *        extendedMetaData="kind='attribute' name='height'"
	 * @generated
	 */
	String getHeight();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getHeight <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Height</em>' attribute.
	 * @see #getHeight()
	 * @generated
	 */
	void setHeight(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Ismap</b></em>' attribute.
	 * The literals are from the enumeration {@link org.w3._1999.xhtml.IsmapType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ismap</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ismap</em>' attribute.
	 * @see org.w3._1999.xhtml.IsmapType
	 * @see #isSetIsmap()
	 * @see #unsetIsmap()
	 * @see #setIsmap(IsmapType)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Ismap()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='ismap'"
	 * @generated
	 */
	IsmapType getIsmap();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getIsmap <em>Ismap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ismap</em>' attribute.
	 * @see org.w3._1999.xhtml.IsmapType
	 * @see #isSetIsmap()
	 * @see #unsetIsmap()
	 * @see #getIsmap()
	 * @generated
	 */
	void setIsmap(IsmapType value);

	/**
	 * Unsets the value of the '{@link org.w3._1999.xhtml.ImgType#getIsmap <em>Ismap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIsmap()
	 * @see #getIsmap()
	 * @see #setIsmap(IsmapType)
	 * @generated
	 */
	void unsetIsmap();

	/**
	 * Returns whether the value of the '{@link org.w3._1999.xhtml.ImgType#getIsmap <em>Ismap</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Ismap</em>' attribute is set.
	 * @see #unsetIsmap()
	 * @see #getIsmap()
	 * @see #setIsmap(IsmapType)
	 * @generated
	 */
	boolean isSetIsmap();

	/**
	 * Returns the value of the '<em><b>Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lang</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lang</em>' attribute.
	 * @see #setLang(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Lang()
	 * @model dataType="org.w3._1999.xhtml.LanguageCode"
	 *        extendedMetaData="kind='attribute' name='lang'"
	 * @generated
	 */
	String getLang();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getLang <em>Lang</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lang</em>' attribute.
	 * @see #getLang()
	 * @generated
	 */
	void setLang(String value);

	/**
	 * Returns the value of the '<em><b>Lang1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *     <div xmlns="http://www.w3.org/1999/xhtml">
	 *      
	 *       <h3>lang (as an attribute name)</h3>
	 *       <p>
	 *        denotes an attribute whose value
	 *        is a language code for the natural language of the content of
	 *        any element; its value is inherited.  This name is reserved
	 *        by virtue of its definition in the XML specification.</p>
	 *      
	 *     </div>
	 *     <div xmlns="http://www.w3.org/1999/xhtml">
	 *      <h4>Notes</h4>
	 *      <p>
	 *       Attempting to install the relevant ISO 2- and 3-letter
	 *       codes as the enumerated possible values is probably never
	 *       going to be a realistic possibility.  
	 *      </p>
	 *      <p>
	 *       See BCP 47 at <a href="http://www.rfc-editor.org/rfc/bcp/bcp47.txt">
	 *        http://www.rfc-editor.org/rfc/bcp/bcp47.txt</a>
	 *       and the IANA language subtag registry at
	 *       <a href="http://www.iana.org/assignments/language-subtag-registry">
	 *        http://www.iana.org/assignments/language-subtag-registry</a>
	 *       for further information.
	 *      </p>
	 *      <p>
	 *       The union allows for the 'un-declaration' of xml:lang with
	 *       the empty string.
	 *      </p>
	 *     </div>
	 *    
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Lang1</em>' attribute.
	 * @see #setLang1(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Lang1()
	 * @model dataType="org.eclipse.emf.ecore.xml.namespace.LangType"
	 *        extendedMetaData="kind='attribute' name='lang' namespace='http://www.w3.org/XML/1998/namespace'"
	 * @generated
	 */
	String getLang1();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getLang1 <em>Lang1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lang1</em>' attribute.
	 * @see #getLang1()
	 * @generated
	 */
	void setLang1(String value);

	/**
	 * Returns the value of the '<em><b>Longdesc</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Longdesc</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Longdesc</em>' attribute.
	 * @see #setLongdesc(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Longdesc()
	 * @model dataType="org.w3._1999.xhtml.URI"
	 *        extendedMetaData="kind='attribute' name='longdesc'"
	 * @generated
	 */
	String getLongdesc();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getLongdesc <em>Longdesc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Longdesc</em>' attribute.
	 * @see #getLongdesc()
	 * @generated
	 */
	void setLongdesc(String value);

	/**
	 * Returns the value of the '<em><b>Onclick</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Onclick</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Onclick</em>' attribute.
	 * @see #setOnclick(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Onclick()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onclick'"
	 * @generated
	 */
	String getOnclick();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getOnclick <em>Onclick</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Onclick</em>' attribute.
	 * @see #getOnclick()
	 * @generated
	 */
	void setOnclick(String value);

	/**
	 * Returns the value of the '<em><b>Ondblclick</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ondblclick</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ondblclick</em>' attribute.
	 * @see #setOndblclick(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Ondblclick()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='ondblclick'"
	 * @generated
	 */
	String getOndblclick();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getOndblclick <em>Ondblclick</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ondblclick</em>' attribute.
	 * @see #getOndblclick()
	 * @generated
	 */
	void setOndblclick(String value);

	/**
	 * Returns the value of the '<em><b>Onkeydown</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Onkeydown</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Onkeydown</em>' attribute.
	 * @see #setOnkeydown(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Onkeydown()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onkeydown'"
	 * @generated
	 */
	String getOnkeydown();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getOnkeydown <em>Onkeydown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Onkeydown</em>' attribute.
	 * @see #getOnkeydown()
	 * @generated
	 */
	void setOnkeydown(String value);

	/**
	 * Returns the value of the '<em><b>Onkeypress</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Onkeypress</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Onkeypress</em>' attribute.
	 * @see #setOnkeypress(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Onkeypress()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onkeypress'"
	 * @generated
	 */
	String getOnkeypress();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getOnkeypress <em>Onkeypress</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Onkeypress</em>' attribute.
	 * @see #getOnkeypress()
	 * @generated
	 */
	void setOnkeypress(String value);

	/**
	 * Returns the value of the '<em><b>Onkeyup</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Onkeyup</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Onkeyup</em>' attribute.
	 * @see #setOnkeyup(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Onkeyup()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onkeyup'"
	 * @generated
	 */
	String getOnkeyup();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getOnkeyup <em>Onkeyup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Onkeyup</em>' attribute.
	 * @see #getOnkeyup()
	 * @generated
	 */
	void setOnkeyup(String value);

	/**
	 * Returns the value of the '<em><b>Onmousedown</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Onmousedown</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Onmousedown</em>' attribute.
	 * @see #setOnmousedown(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Onmousedown()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onmousedown'"
	 * @generated
	 */
	String getOnmousedown();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getOnmousedown <em>Onmousedown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Onmousedown</em>' attribute.
	 * @see #getOnmousedown()
	 * @generated
	 */
	void setOnmousedown(String value);

	/**
	 * Returns the value of the '<em><b>Onmousemove</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Onmousemove</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Onmousemove</em>' attribute.
	 * @see #setOnmousemove(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Onmousemove()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onmousemove'"
	 * @generated
	 */
	String getOnmousemove();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getOnmousemove <em>Onmousemove</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Onmousemove</em>' attribute.
	 * @see #getOnmousemove()
	 * @generated
	 */
	void setOnmousemove(String value);

	/**
	 * Returns the value of the '<em><b>Onmouseout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Onmouseout</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Onmouseout</em>' attribute.
	 * @see #setOnmouseout(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Onmouseout()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onmouseout'"
	 * @generated
	 */
	String getOnmouseout();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getOnmouseout <em>Onmouseout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Onmouseout</em>' attribute.
	 * @see #getOnmouseout()
	 * @generated
	 */
	void setOnmouseout(String value);

	/**
	 * Returns the value of the '<em><b>Onmouseover</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Onmouseover</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Onmouseover</em>' attribute.
	 * @see #setOnmouseover(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Onmouseover()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onmouseover'"
	 * @generated
	 */
	String getOnmouseover();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getOnmouseover <em>Onmouseover</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Onmouseover</em>' attribute.
	 * @see #getOnmouseover()
	 * @generated
	 */
	void setOnmouseover(String value);

	/**
	 * Returns the value of the '<em><b>Onmouseup</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Onmouseup</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Onmouseup</em>' attribute.
	 * @see #setOnmouseup(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Onmouseup()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onmouseup'"
	 * @generated
	 */
	String getOnmouseup();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getOnmouseup <em>Onmouseup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Onmouseup</em>' attribute.
	 * @see #getOnmouseup()
	 * @generated
	 */
	void setOnmouseup(String value);

	/**
	 * Returns the value of the '<em><b>Src</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Src</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Src</em>' attribute.
	 * @see #setSrc(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Src()
	 * @model dataType="org.w3._1999.xhtml.URI" required="true"
	 *        extendedMetaData="kind='attribute' name='src'"
	 * @generated
	 */
	String getSrc();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getSrc <em>Src</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Src</em>' attribute.
	 * @see #getSrc()
	 * @generated
	 */
	void setSrc(String value);

	/**
	 * Returns the value of the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Style</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Style</em>' attribute.
	 * @see #setStyle(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Style()
	 * @model dataType="org.w3._1999.xhtml.StyleSheet"
	 *        extendedMetaData="kind='attribute' name='style'"
	 * @generated
	 */
	String getStyle();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getStyle <em>Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Style</em>' attribute.
	 * @see #getStyle()
	 * @generated
	 */
	void setStyle(String value);

	/**
	 * Returns the value of the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Title</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Title</em>' attribute.
	 * @see #setTitle(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Title()
	 * @model dataType="org.w3._1999.xhtml.Text"
	 *        extendedMetaData="kind='attribute' name='title'"
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

	/**
	 * Returns the value of the '<em><b>Usemap</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *           usemap points to a map element which may be in this document
	 *           or an external document, although the latter is not widely supported
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Usemap</em>' attribute.
	 * @see #setUsemap(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Usemap()
	 * @model dataType="org.w3._1999.xhtml.URI"
	 *        extendedMetaData="kind='attribute' name='usemap'"
	 * @generated
	 */
	String getUsemap();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getUsemap <em>Usemap</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Usemap</em>' attribute.
	 * @see #getUsemap()
	 * @generated
	 */
	void setUsemap(String value);

	/**
	 * Returns the value of the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Width</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Width</em>' attribute.
	 * @see #setWidth(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getImgType_Width()
	 * @model dataType="org.w3._1999.xhtml.Length"
	 *        extendedMetaData="kind='attribute' name='width'"
	 * @generated
	 */
	String getWidth();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.ImgType#getWidth <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Width</em>' attribute.
	 * @see #getWidth()
	 * @generated
	 */
	void setWidth(String value);

} // ImgType
