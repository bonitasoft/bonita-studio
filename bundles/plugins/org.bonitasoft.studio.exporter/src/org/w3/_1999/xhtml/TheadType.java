/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.w3._1999.xhtml;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Thead Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.w3._1999.xhtml.TheadType#getTr <em>Tr</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TheadType#getAlign <em>Align</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TheadType#getChar <em>Char</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TheadType#getCharoff <em>Charoff</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TheadType#getClass_ <em>Class</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TheadType#getDir <em>Dir</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TheadType#getId <em>Id</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TheadType#getLang <em>Lang</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TheadType#getLang1 <em>Lang1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TheadType#getOnclick <em>Onclick</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TheadType#getOndblclick <em>Ondblclick</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TheadType#getOnkeydown <em>Onkeydown</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TheadType#getOnkeypress <em>Onkeypress</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TheadType#getOnkeyup <em>Onkeyup</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TheadType#getOnmousedown <em>Onmousedown</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TheadType#getOnmousemove <em>Onmousemove</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TheadType#getOnmouseout <em>Onmouseout</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TheadType#getOnmouseover <em>Onmouseover</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TheadType#getOnmouseup <em>Onmouseup</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TheadType#getStyle <em>Style</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TheadType#getTitle <em>Title</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TheadType#getValign <em>Valign</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType()
 * @model extendedMetaData="name='thead_._type' kind='elementOnly'"
 * @generated
 */
public interface TheadType extends EObject {
	/**
	 * Returns the value of the '<em><b>Tr</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.TrType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tr</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tr</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType_Tr()
	 * @model containment="true" required="true"
	 *        extendedMetaData="kind='element' name='tr' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TrType> getTr();

	/**
	 * Returns the value of the '<em><b>Align</b></em>' attribute.
	 * The literals are from the enumeration {@link org.w3._1999.xhtml.AlignType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Align</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Align</em>' attribute.
	 * @see org.w3._1999.xhtml.AlignType
	 * @see #isSetAlign()
	 * @see #unsetAlign()
	 * @see #setAlign(AlignType)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType_Align()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='align'"
	 * @generated
	 */
	AlignType getAlign();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TheadType#getAlign <em>Align</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Align</em>' attribute.
	 * @see org.w3._1999.xhtml.AlignType
	 * @see #isSetAlign()
	 * @see #unsetAlign()
	 * @see #getAlign()
	 * @generated
	 */
	void setAlign(AlignType value);

	/**
	 * Unsets the value of the '{@link org.w3._1999.xhtml.TheadType#getAlign <em>Align</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAlign()
	 * @see #getAlign()
	 * @see #setAlign(AlignType)
	 * @generated
	 */
	void unsetAlign();

	/**
	 * Returns whether the value of the '{@link org.w3._1999.xhtml.TheadType#getAlign <em>Align</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Align</em>' attribute is set.
	 * @see #unsetAlign()
	 * @see #getAlign()
	 * @see #setAlign(AlignType)
	 * @generated
	 */
	boolean isSetAlign();

	/**
	 * Returns the value of the '<em><b>Char</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Char</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Char</em>' attribute.
	 * @see #setChar(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType_Char()
	 * @model dataType="org.w3._1999.xhtml.Character"
	 *        extendedMetaData="kind='attribute' name='char'"
	 * @generated
	 */
	String getChar();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TheadType#getChar <em>Char</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Char</em>' attribute.
	 * @see #getChar()
	 * @generated
	 */
	void setChar(String value);

	/**
	 * Returns the value of the '<em><b>Charoff</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Charoff</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Charoff</em>' attribute.
	 * @see #setCharoff(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType_Charoff()
	 * @model dataType="org.w3._1999.xhtml.Length"
	 *        extendedMetaData="kind='attribute' name='charoff'"
	 * @generated
	 */
	String getCharoff();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TheadType#getCharoff <em>Charoff</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Charoff</em>' attribute.
	 * @see #getCharoff()
	 * @generated
	 */
	void setCharoff(String value);

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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType_Class()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.NMTOKENS" many="false"
	 *        extendedMetaData="kind='attribute' name='class'"
	 * @generated
	 */
	List<String> getClass_();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TheadType#getClass_ <em>Class</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType_Dir()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='dir'"
	 * @generated
	 */
	DirType getDir();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TheadType#getDir <em>Dir</em>}' attribute.
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
	 * Unsets the value of the '{@link org.w3._1999.xhtml.TheadType#getDir <em>Dir</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDir()
	 * @see #getDir()
	 * @see #setDir(DirType)
	 * @generated
	 */
	void unsetDir();

	/**
	 * Returns whether the value of the '{@link org.w3._1999.xhtml.TheadType#getDir <em>Dir</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TheadType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType_Lang()
	 * @model dataType="org.w3._1999.xhtml.LanguageCode"
	 *        extendedMetaData="kind='attribute' name='lang'"
	 * @generated
	 */
	String getLang();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TheadType#getLang <em>Lang</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType_Lang1()
	 * @model dataType="org.eclipse.emf.ecore.xml.namespace.LangType"
	 *        extendedMetaData="kind='attribute' name='lang' namespace='http://www.w3.org/XML/1998/namespace'"
	 * @generated
	 */
	String getLang1();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TheadType#getLang1 <em>Lang1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lang1</em>' attribute.
	 * @see #getLang1()
	 * @generated
	 */
	void setLang1(String value);

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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType_Onclick()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onclick'"
	 * @generated
	 */
	String getOnclick();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TheadType#getOnclick <em>Onclick</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType_Ondblclick()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='ondblclick'"
	 * @generated
	 */
	String getOndblclick();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TheadType#getOndblclick <em>Ondblclick</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType_Onkeydown()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onkeydown'"
	 * @generated
	 */
	String getOnkeydown();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TheadType#getOnkeydown <em>Onkeydown</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType_Onkeypress()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onkeypress'"
	 * @generated
	 */
	String getOnkeypress();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TheadType#getOnkeypress <em>Onkeypress</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType_Onkeyup()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onkeyup'"
	 * @generated
	 */
	String getOnkeyup();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TheadType#getOnkeyup <em>Onkeyup</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType_Onmousedown()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onmousedown'"
	 * @generated
	 */
	String getOnmousedown();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TheadType#getOnmousedown <em>Onmousedown</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType_Onmousemove()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onmousemove'"
	 * @generated
	 */
	String getOnmousemove();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TheadType#getOnmousemove <em>Onmousemove</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType_Onmouseout()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onmouseout'"
	 * @generated
	 */
	String getOnmouseout();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TheadType#getOnmouseout <em>Onmouseout</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType_Onmouseover()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onmouseover'"
	 * @generated
	 */
	String getOnmouseover();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TheadType#getOnmouseover <em>Onmouseover</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType_Onmouseup()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onmouseup'"
	 * @generated
	 */
	String getOnmouseup();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TheadType#getOnmouseup <em>Onmouseup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Onmouseup</em>' attribute.
	 * @see #getOnmouseup()
	 * @generated
	 */
	void setOnmouseup(String value);

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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType_Style()
	 * @model dataType="org.w3._1999.xhtml.StyleSheet"
	 *        extendedMetaData="kind='attribute' name='style'"
	 * @generated
	 */
	String getStyle();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TheadType#getStyle <em>Style</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType_Title()
	 * @model dataType="org.w3._1999.xhtml.Text"
	 *        extendedMetaData="kind='attribute' name='title'"
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TheadType#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

	/**
	 * Returns the value of the '<em><b>Valign</b></em>' attribute.
	 * The literals are from the enumeration {@link org.w3._1999.xhtml.ValignType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Valign</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Valign</em>' attribute.
	 * @see org.w3._1999.xhtml.ValignType
	 * @see #isSetValign()
	 * @see #unsetValign()
	 * @see #setValign(ValignType)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTheadType_Valign()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='valign'"
	 * @generated
	 */
	ValignType getValign();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TheadType#getValign <em>Valign</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Valign</em>' attribute.
	 * @see org.w3._1999.xhtml.ValignType
	 * @see #isSetValign()
	 * @see #unsetValign()
	 * @see #getValign()
	 * @generated
	 */
	void setValign(ValignType value);

	/**
	 * Unsets the value of the '{@link org.w3._1999.xhtml.TheadType#getValign <em>Valign</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetValign()
	 * @see #getValign()
	 * @see #setValign(ValignType)
	 * @generated
	 */
	void unsetValign();

	/**
	 * Returns whether the value of the '{@link org.w3._1999.xhtml.TheadType#getValign <em>Valign</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Valign</em>' attribute is set.
	 * @see #unsetValign()
	 * @see #getValign()
	 * @see #setValign(ValignType)
	 * @generated
	 */
	boolean isSetValign();

} // TheadType
