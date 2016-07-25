/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.w3._1999.xhtml;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Head Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getGroup <em>Group</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getScript <em>Script</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getStyle <em>Style</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getMeta <em>Meta</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getLink <em>Link</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getObject <em>Object</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getTitle <em>Title</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getGroup1 <em>Group1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getScript1 <em>Script1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getStyle1 <em>Style1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getMeta1 <em>Meta1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getLink1 <em>Link1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getObject1 <em>Object1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getBase <em>Base</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getGroup2 <em>Group2</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getScript2 <em>Script2</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getStyle2 <em>Style2</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getMeta2 <em>Meta2</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getLink2 <em>Link2</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getObject2 <em>Object2</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getBase1 <em>Base1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getGroup3 <em>Group3</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getScript3 <em>Script3</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getStyle3 <em>Style3</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getMeta3 <em>Meta3</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getLink3 <em>Link3</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getObject3 <em>Object3</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getTitle1 <em>Title1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getGroup4 <em>Group4</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getScript4 <em>Script4</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getStyle4 <em>Style4</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getMeta4 <em>Meta4</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getLink4 <em>Link4</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getObject4 <em>Object4</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getDir <em>Dir</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getId <em>Id</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getLang <em>Lang</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getLang1 <em>Lang1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.HeadType#getProfile <em>Profile</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType()
 * @model extendedMetaData="name='head_._type' kind='elementOnly'"
 * @generated
 */
public interface HeadType extends EObject {
	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Script</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.ScriptType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       script statements, which may include CDATA sections
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Script</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Script()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='script' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ScriptType> getScript();

	/**
	 * Returns the value of the '<em><b>Style</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.StyleType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       style info, which may include CDATA sections
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Style</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Style()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='style' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<StyleType> getStyle();

	/**
	 * Returns the value of the '<em><b>Meta</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.MetaType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       generic metainformation
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Meta</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Meta()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='meta' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<MetaType> getMeta();

	/**
	 * Returns the value of the '<em><b>Link</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.LinkType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       Relationship values can be used in principle:
	 * 
	 *       a) for document specific toolbars/menus when used
	 *          with the link element in document head e.g.
	 *            start, contents, previous, next, index, end, help
	 *       b) to link to a separate style sheet (rel="stylesheet")
	 *       c) to make a link to a script (rel="script")
	 *       d) by stylesheets to control how collections of
	 *          html nodes are rendered into printed documents
	 *       e) to make a link to a printable version of this document
	 *          e.g. a PostScript or PDF version (rel="alternate" media="print")
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Link</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Link()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='link' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<LinkType> getLink();

	/**
	 * Returns the value of the '<em><b>Object</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.ObjectType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Object</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Object()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='object' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ObjectType> getObject();

	/**
	 * Returns the value of the '<em><b>Title</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       The title element is not considered part of the flow of text.
	 *       It should be displayed, for example as the page header or
	 *       window title. Exactly one title is required per document.
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Title</em>' containment reference.
	 * @see #setTitle(TitleType)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Title()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='title' namespace='##targetNamespace'"
	 * @generated
	 */
	TitleType getTitle();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.HeadType#getTitle <em>Title</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title</em>' containment reference.
	 * @see #getTitle()
	 * @generated
	 */
	void setTitle(TitleType value);

	/**
	 * Returns the value of the '<em><b>Group1</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group1</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group1</em>' attribute list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Group1()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:7'"
	 * @generated
	 */
	FeatureMap getGroup1();

	/**
	 * Returns the value of the '<em><b>Script1</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.ScriptType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       script statements, which may include CDATA sections
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Script1</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Script1()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='script' namespace='##targetNamespace' group='#group:7'"
	 * @generated
	 */
	EList<ScriptType> getScript1();

	/**
	 * Returns the value of the '<em><b>Style1</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.StyleType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       style info, which may include CDATA sections
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Style1</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Style1()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='style' namespace='##targetNamespace' group='#group:7'"
	 * @generated
	 */
	EList<StyleType> getStyle1();

	/**
	 * Returns the value of the '<em><b>Meta1</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.MetaType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       generic metainformation
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Meta1</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Meta1()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='meta' namespace='##targetNamespace' group='#group:7'"
	 * @generated
	 */
	EList<MetaType> getMeta1();

	/**
	 * Returns the value of the '<em><b>Link1</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.LinkType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       Relationship values can be used in principle:
	 * 
	 *       a) for document specific toolbars/menus when used
	 *          with the link element in document head e.g.
	 *            start, contents, previous, next, index, end, help
	 *       b) to link to a separate style sheet (rel="stylesheet")
	 *       c) to make a link to a script (rel="script")
	 *       d) by stylesheets to control how collections of
	 *          html nodes are rendered into printed documents
	 *       e) to make a link to a printable version of this document
	 *          e.g. a PostScript or PDF version (rel="alternate" media="print")
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Link1</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Link1()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='link' namespace='##targetNamespace' group='#group:7'"
	 * @generated
	 */
	EList<LinkType> getLink1();

	/**
	 * Returns the value of the '<em><b>Object1</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.ObjectType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Object1</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object1</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Object1()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='object' namespace='##targetNamespace' group='#group:7'"
	 * @generated
	 */
	EList<ObjectType> getObject1();

	/**
	 * Returns the value of the '<em><b>Base</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       document base URI
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Base</em>' containment reference.
	 * @see #setBase(BaseType)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Base()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='base' namespace='##targetNamespace'"
	 * @generated
	 */
	BaseType getBase();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.HeadType#getBase <em>Base</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base</em>' containment reference.
	 * @see #getBase()
	 * @generated
	 */
	void setBase(BaseType value);

	/**
	 * Returns the value of the '<em><b>Group2</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group2</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group2</em>' attribute list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Group2()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:14'"
	 * @generated
	 */
	FeatureMap getGroup2();

	/**
	 * Returns the value of the '<em><b>Script2</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.ScriptType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       script statements, which may include CDATA sections
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Script2</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Script2()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='script' namespace='##targetNamespace' group='#group:14'"
	 * @generated
	 */
	EList<ScriptType> getScript2();

	/**
	 * Returns the value of the '<em><b>Style2</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.StyleType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       style info, which may include CDATA sections
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Style2</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Style2()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='style' namespace='##targetNamespace' group='#group:14'"
	 * @generated
	 */
	EList<StyleType> getStyle2();

	/**
	 * Returns the value of the '<em><b>Meta2</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.MetaType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       generic metainformation
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Meta2</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Meta2()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='meta' namespace='##targetNamespace' group='#group:14'"
	 * @generated
	 */
	EList<MetaType> getMeta2();

	/**
	 * Returns the value of the '<em><b>Link2</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.LinkType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       Relationship values can be used in principle:
	 * 
	 *       a) for document specific toolbars/menus when used
	 *          with the link element in document head e.g.
	 *            start, contents, previous, next, index, end, help
	 *       b) to link to a separate style sheet (rel="stylesheet")
	 *       c) to make a link to a script (rel="script")
	 *       d) by stylesheets to control how collections of
	 *          html nodes are rendered into printed documents
	 *       e) to make a link to a printable version of this document
	 *          e.g. a PostScript or PDF version (rel="alternate" media="print")
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Link2</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Link2()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='link' namespace='##targetNamespace' group='#group:14'"
	 * @generated
	 */
	EList<LinkType> getLink2();

	/**
	 * Returns the value of the '<em><b>Object2</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.ObjectType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Object2</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object2</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Object2()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='object' namespace='##targetNamespace' group='#group:14'"
	 * @generated
	 */
	EList<ObjectType> getObject2();

	/**
	 * Returns the value of the '<em><b>Base1</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       document base URI
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Base1</em>' containment reference.
	 * @see #setBase1(BaseType)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Base1()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='base' namespace='##targetNamespace'"
	 * @generated
	 */
	BaseType getBase1();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.HeadType#getBase1 <em>Base1</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base1</em>' containment reference.
	 * @see #getBase1()
	 * @generated
	 */
	void setBase1(BaseType value);

	/**
	 * Returns the value of the '<em><b>Group3</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group3</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group3</em>' attribute list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Group3()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:21'"
	 * @generated
	 */
	FeatureMap getGroup3();

	/**
	 * Returns the value of the '<em><b>Script3</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.ScriptType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       script statements, which may include CDATA sections
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Script3</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Script3()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='script' namespace='##targetNamespace' group='#group:21'"
	 * @generated
	 */
	EList<ScriptType> getScript3();

	/**
	 * Returns the value of the '<em><b>Style3</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.StyleType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       style info, which may include CDATA sections
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Style3</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Style3()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='style' namespace='##targetNamespace' group='#group:21'"
	 * @generated
	 */
	EList<StyleType> getStyle3();

	/**
	 * Returns the value of the '<em><b>Meta3</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.MetaType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       generic metainformation
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Meta3</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Meta3()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='meta' namespace='##targetNamespace' group='#group:21'"
	 * @generated
	 */
	EList<MetaType> getMeta3();

	/**
	 * Returns the value of the '<em><b>Link3</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.LinkType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       Relationship values can be used in principle:
	 * 
	 *       a) for document specific toolbars/menus when used
	 *          with the link element in document head e.g.
	 *            start, contents, previous, next, index, end, help
	 *       b) to link to a separate style sheet (rel="stylesheet")
	 *       c) to make a link to a script (rel="script")
	 *       d) by stylesheets to control how collections of
	 *          html nodes are rendered into printed documents
	 *       e) to make a link to a printable version of this document
	 *          e.g. a PostScript or PDF version (rel="alternate" media="print")
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Link3</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Link3()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='link' namespace='##targetNamespace' group='#group:21'"
	 * @generated
	 */
	EList<LinkType> getLink3();

	/**
	 * Returns the value of the '<em><b>Object3</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.ObjectType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Object3</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object3</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Object3()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='object' namespace='##targetNamespace' group='#group:21'"
	 * @generated
	 */
	EList<ObjectType> getObject3();

	/**
	 * Returns the value of the '<em><b>Title1</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       The title element is not considered part of the flow of text.
	 *       It should be displayed, for example as the page header or
	 *       window title. Exactly one title is required per document.
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Title1</em>' containment reference.
	 * @see #setTitle1(TitleType)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Title1()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='title' namespace='##targetNamespace'"
	 * @generated
	 */
	TitleType getTitle1();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.HeadType#getTitle1 <em>Title1</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Title1</em>' containment reference.
	 * @see #getTitle1()
	 * @generated
	 */
	void setTitle1(TitleType value);

	/**
	 * Returns the value of the '<em><b>Group4</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group4</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group4</em>' attribute list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Group4()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:28'"
	 * @generated
	 */
	FeatureMap getGroup4();

	/**
	 * Returns the value of the '<em><b>Script4</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.ScriptType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       script statements, which may include CDATA sections
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Script4</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Script4()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='script' namespace='##targetNamespace' group='#group:28'"
	 * @generated
	 */
	EList<ScriptType> getScript4();

	/**
	 * Returns the value of the '<em><b>Style4</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.StyleType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       style info, which may include CDATA sections
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Style4</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Style4()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='style' namespace='##targetNamespace' group='#group:28'"
	 * @generated
	 */
	EList<StyleType> getStyle4();

	/**
	 * Returns the value of the '<em><b>Meta4</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.MetaType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       generic metainformation
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Meta4</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Meta4()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='meta' namespace='##targetNamespace' group='#group:28'"
	 * @generated
	 */
	EList<MetaType> getMeta4();

	/**
	 * Returns the value of the '<em><b>Link4</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.LinkType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       Relationship values can be used in principle:
	 * 
	 *       a) for document specific toolbars/menus when used
	 *          with the link element in document head e.g.
	 *            start, contents, previous, next, index, end, help
	 *       b) to link to a separate style sheet (rel="stylesheet")
	 *       c) to make a link to a script (rel="script")
	 *       d) by stylesheets to control how collections of
	 *          html nodes are rendered into printed documents
	 *       e) to make a link to a printable version of this document
	 *          e.g. a PostScript or PDF version (rel="alternate" media="print")
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Link4</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Link4()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='link' namespace='##targetNamespace' group='#group:28'"
	 * @generated
	 */
	EList<LinkType> getLink4();

	/**
	 * Returns the value of the '<em><b>Object4</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.ObjectType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Object4</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object4</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Object4()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='object' namespace='##targetNamespace' group='#group:28'"
	 * @generated
	 */
	EList<ObjectType> getObject4();

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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Dir()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='dir'"
	 * @generated
	 */
	DirType getDir();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.HeadType#getDir <em>Dir</em>}' attribute.
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
	 * Unsets the value of the '{@link org.w3._1999.xhtml.HeadType#getDir <em>Dir</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDir()
	 * @see #getDir()
	 * @see #setDir(DirType)
	 * @generated
	 */
	void unsetDir();

	/**
	 * Returns whether the value of the '{@link org.w3._1999.xhtml.HeadType#getDir <em>Dir</em>}' attribute is set.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.HeadType#getId <em>Id</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Lang()
	 * @model dataType="org.w3._1999.xhtml.LanguageCode"
	 *        extendedMetaData="kind='attribute' name='lang'"
	 * @generated
	 */
	String getLang();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.HeadType#getLang <em>Lang</em>}' attribute.
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Lang1()
	 * @model dataType="org.eclipse.emf.ecore.xml.namespace.LangType"
	 *        extendedMetaData="kind='attribute' name='lang' namespace='http://www.w3.org/XML/1998/namespace'"
	 * @generated
	 */
	String getLang1();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.HeadType#getLang1 <em>Lang1</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Lang1</em>' attribute.
	 * @see #getLang1()
	 * @generated
	 */
	void setLang1(String value);

	/**
	 * Returns the value of the '<em><b>Profile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Profile</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Profile</em>' attribute.
	 * @see #setProfile(String)
	 * @see org.w3._1999.xhtml.XhtmlPackage#getHeadType_Profile()
	 * @model dataType="org.w3._1999.xhtml.URI"
	 *        extendedMetaData="kind='attribute' name='profile'"
	 * @generated
	 */
	String getProfile();

	/**
	 * Sets the value of the '{@link org.w3._1999.xhtml.HeadType#getProfile <em>Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Profile</em>' attribute.
	 * @see #getProfile()
	 * @generated
	 */
	void setProfile(String value);

} // HeadType
