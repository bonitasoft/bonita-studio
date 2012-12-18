/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.w3._1999.xhtml;

import java.math.BigInteger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Table Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.w3._1999.xhtml.TableType#getCaption <em>Caption</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getCol <em>Col</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getColgroup <em>Colgroup</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getThead <em>Thead</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getTfoot <em>Tfoot</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getTbody <em>Tbody</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getTr <em>Tr</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getBorder <em>Border</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getCellpadding <em>Cellpadding</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getCellspacing <em>Cellspacing</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getClass_ <em>Class</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getDir <em>Dir</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getFrame <em>Frame</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getId <em>Id</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getLang <em>Lang</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getLang1 <em>Lang1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getOnclick <em>Onclick</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getOndblclick <em>Ondblclick</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getOnkeydown <em>Onkeydown</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getOnkeypress <em>Onkeypress</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getOnkeyup <em>Onkeyup</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getOnmousedown <em>Onmousedown</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getOnmousemove <em>Onmousemove</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getOnmouseout <em>Onmouseout</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getOnmouseover <em>Onmouseover</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getOnmouseup <em>Onmouseup</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getRules <em>Rules</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getStyle <em>Style</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getSummary <em>Summary</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getTitle <em>Title</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.TableType#getWidth <em>Width</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType()
 * @model extendedMetaData="name='table_._type' kind='elementOnly'"
 * @generated
 */
public interface TableType extends EObject {
	/**
	 * Returns the value of the '<em><b>Caption</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Caption</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Caption</em>' containment reference.
	 * @see #setCaption(CaptionType)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Caption()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='caption' namespace='##targetNamespace'"
	 * @generated
	 */
	CaptionType getCaption();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getCaption <em>Caption</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Caption</em>' containment reference.
	 * @see #getCaption()
	 * @generated
	 */
	void setCaption(CaptionType value);

	/**
	 * Returns the value of the '<em><b>Col</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.ColType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       col elements define the alignment properties for cells in
	 *       one or more columns.
	 * 
	 *       The width attribute specifies the width of the columns, e.g.
	 * 
	 *           width=64        width in screen pixels
	 *           width=0.5*      relative width of 0.5
	 * 
	 *       The span attribute causes the attributes of one
	 *       col element to apply to more than one column.
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Col</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Col()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='col' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ColType> getCol();

	/**
	 * Returns the value of the '<em><b>Colgroup</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.ColgroupType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       colgroup groups a set of col elements. It allows you to group
	 *       several semantically related columns together.
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Colgroup</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Colgroup()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='colgroup' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<ColgroupType> getColgroup();

	/**
	 * Returns the value of the '<em><b>Thead</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Thead</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Thead</em>' containment reference.
	 * @see #setThead(TheadType)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Thead()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='thead' namespace='##targetNamespace'"
	 * @generated
	 */
	TheadType getThead();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getThead <em>Thead</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Thead</em>' containment reference.
	 * @see #getThead()
	 * @generated
	 */
	void setThead(TheadType value);

	/**
	 * Returns the value of the '<em><b>Tfoot</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tfoot</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tfoot</em>' containment reference.
	 * @see #setTfoot(TfootType)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Tfoot()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='tfoot' namespace='##targetNamespace'"
	 * @generated
	 */
	TfootType getTfoot();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getTfoot <em>Tfoot</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tfoot</em>' containment reference.
	 * @see #getTfoot()
	 * @generated
	 */
	void setTfoot(TfootType value);

	/**
	 * Returns the value of the '<em><b>Tbody</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.TbodyType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Tbody</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tbody</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Tbody()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='tbody' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TbodyType> getTbody();

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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Tr()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='tr' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<TrType> getTr();

	/**
	 * Returns the value of the '<em><b>Border</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Border</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Border</em>' attribute.
	 * @see #setBorder(BigInteger)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Border()
	 * @model dataType="org.w3._1999.xhtml.Pixels"
	 *        extendedMetaData="kind='attribute' name='border'"
	 * @generated
	 */
	BigInteger getBorder();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getBorder <em>Border</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Border</em>' attribute.
	 * @see #getBorder()
	 * @generated
	 */
	void setBorder(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Cellpadding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cellpadding</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cellpadding</em>' attribute.
	 * @see #setCellpadding(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Cellpadding()
	 * @model dataType="org.w3._1999.xhtml.Length"
	 *        extendedMetaData="kind='attribute' name='cellpadding'"
	 * @generated
	 */
	String getCellpadding();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getCellpadding <em>Cellpadding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cellpadding</em>' attribute.
	 * @see #getCellpadding()
	 * @generated
	 */
	void setCellpadding(String value);

	/**
	 * Returns the value of the '<em><b>Cellspacing</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cellspacing</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cellspacing</em>' attribute.
	 * @see #setCellspacing(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Cellspacing()
	 * @model dataType="org.w3._1999.xhtml.Length"
	 *        extendedMetaData="kind='attribute' name='cellspacing'"
	 * @generated
	 */
	String getCellspacing();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getCellspacing <em>Cellspacing</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cellspacing</em>' attribute.
	 * @see #getCellspacing()
	 * @generated
	 */
	void setCellspacing(String value);

	/**
	 * Returns the value of the '<em><b>Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Class</em>' attribute.
	 * @see #setClass(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Class()
	 * @model extendedMetaData="kind='attribute' name='class'"
	 * @generated
	 */
	String getClass_();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getClass_ <em>Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class</em>' attribute.
	 * @see #getClass_()
	 * @generated
	 */
	void setClass(String value);

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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Dir()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='dir'"
	 * @generated
	 */
	DirType getDir();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getDir <em>Dir</em>}' attribute.
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
	 * Unsets the value of the '{@link org.w3._1999.xhtml.TableType#getDir <em>Dir</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDir()
	 * @see #getDir()
	 * @see #setDir(DirType)
	 * @generated
	 */
	void unsetDir();

	/**
	 * Returns whether the value of the '{@link org.w3._1999.xhtml.TableType#getDir <em>Dir</em>}' attribute is set.
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
	 * Returns the value of the '<em><b>Frame</b></em>' attribute.
	 * The literals are from the enumeration {@link org.w3._1999.xhtml.TFrame}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Frame</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Frame</em>' attribute.
	 * @see org.w3._1999.xhtml.TFrame
	 * @see #isSetFrame()
	 * @see #unsetFrame()
	 * @see #setFrame(TFrame)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Frame()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='frame'"
	 * @generated
	 */
	TFrame getFrame();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getFrame <em>Frame</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Frame</em>' attribute.
	 * @see org.w3._1999.xhtml.TFrame
	 * @see #isSetFrame()
	 * @see #unsetFrame()
	 * @see #getFrame()
	 * @generated
	 */
	void setFrame(TFrame value);

	/**
	 * Unsets the value of the '{@link org.w3._1999.xhtml.TableType#getFrame <em>Frame</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetFrame()
	 * @see #getFrame()
	 * @see #setFrame(TFrame)
	 * @generated
	 */
	void unsetFrame();

	/**
	 * Returns whether the value of the '{@link org.w3._1999.xhtml.TableType#getFrame <em>Frame</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Frame</em>' attribute is set.
	 * @see #unsetFrame()
	 * @see #getFrame()
	 * @see #setFrame(TFrame)
	 * @generated
	 */
	boolean isSetFrame();

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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getId <em>Id</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Lang()
	 * @model dataType="org.w3._1999.xhtml.LanguageCode"
	 *        extendedMetaData="kind='attribute' name='lang'"
	 * @generated
	 */
	String getLang();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getLang <em>Lang</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Lang1()
	 * @model dataType="org.eclipse.emf.ecore.xml.namespace.LangType"
	 *        extendedMetaData="kind='attribute' name='lang' namespace='http://www.w3.org/XML/1998/namespace'"
	 * @generated
	 */
	String getLang1();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getLang1 <em>Lang1</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Onclick()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onclick'"
	 * @generated
	 */
	String getOnclick();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getOnclick <em>Onclick</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Ondblclick()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='ondblclick'"
	 * @generated
	 */
	String getOndblclick();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getOndblclick <em>Ondblclick</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Onkeydown()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onkeydown'"
	 * @generated
	 */
	String getOnkeydown();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getOnkeydown <em>Onkeydown</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Onkeypress()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onkeypress'"
	 * @generated
	 */
	String getOnkeypress();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getOnkeypress <em>Onkeypress</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Onkeyup()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onkeyup'"
	 * @generated
	 */
	String getOnkeyup();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getOnkeyup <em>Onkeyup</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Onmousedown()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onmousedown'"
	 * @generated
	 */
	String getOnmousedown();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getOnmousedown <em>Onmousedown</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Onmousemove()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onmousemove'"
	 * @generated
	 */
	String getOnmousemove();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getOnmousemove <em>Onmousemove</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Onmouseout()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onmouseout'"
	 * @generated
	 */
	String getOnmouseout();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getOnmouseout <em>Onmouseout</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Onmouseover()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onmouseover'"
	 * @generated
	 */
	String getOnmouseover();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getOnmouseover <em>Onmouseover</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Onmouseup()
	 * @model dataType="org.w3._1999.xhtml.Script"
	 *        extendedMetaData="kind='attribute' name='onmouseup'"
	 * @generated
	 */
	String getOnmouseup();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getOnmouseup <em>Onmouseup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Onmouseup</em>' attribute.
	 * @see #getOnmouseup()
	 * @generated
	 */
	void setOnmouseup(String value);

	/**
	 * Returns the value of the '<em><b>Rules</b></em>' attribute.
	 * The literals are from the enumeration {@link org.w3._1999.xhtml.TRules}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rules</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rules</em>' attribute.
	 * @see org.w3._1999.xhtml.TRules
	 * @see #isSetRules()
	 * @see #unsetRules()
	 * @see #setRules(TRules)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Rules()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='rules'"
	 * @generated
	 */
	TRules getRules();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getRules <em>Rules</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rules</em>' attribute.
	 * @see org.w3._1999.xhtml.TRules
	 * @see #isSetRules()
	 * @see #unsetRules()
	 * @see #getRules()
	 * @generated
	 */
	void setRules(TRules value);

	/**
	 * Unsets the value of the '{@link org.w3._1999.xhtml.TableType#getRules <em>Rules</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetRules()
	 * @see #getRules()
	 * @see #setRules(TRules)
	 * @generated
	 */
	void unsetRules();

	/**
	 * Returns whether the value of the '{@link org.w3._1999.xhtml.TableType#getRules <em>Rules</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Rules</em>' attribute is set.
	 * @see #unsetRules()
	 * @see #getRules()
	 * @see #setRules(TRules)
	 * @generated
	 */
	boolean isSetRules();

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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Style()
	 * @model dataType="org.w3._1999.xhtml.StyleSheet"
	 *        extendedMetaData="kind='attribute' name='style'"
	 * @generated
	 */
	String getStyle();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getStyle <em>Style</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Style</em>' attribute.
	 * @see #getStyle()
	 * @generated
	 */
	void setStyle(String value);

	/**
	 * Returns the value of the '<em><b>Summary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Summary</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Summary</em>' attribute.
	 * @see #setSummary(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Summary()
	 * @model dataType="org.w3._1999.xhtml.Text"
	 *        extendedMetaData="kind='attribute' name='summary'"
	 * @generated
	 */
	String getSummary();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getSummary <em>Summary</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Summary</em>' attribute.
	 * @see #getSummary()
	 * @generated
	 */
	void setSummary(String value);

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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Title()
	 * @model dataType="org.w3._1999.xhtml.Text"
	 *        extendedMetaData="kind='attribute' name='title'"
	 * @generated
	 */
	String getTitle();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getTitle <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' attribute.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(String value);

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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getTableType_Width()
	 * @model dataType="org.w3._1999.xhtml.Length"
	 *        extendedMetaData="kind='attribute' name='width'"
	 * @generated
	 */
	String getWidth();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.TableType#getWidth <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Width</em>' attribute.
	 * @see #getWidth()
	 * @generated
	 */
	void setWidth(String value);

} // TableType
