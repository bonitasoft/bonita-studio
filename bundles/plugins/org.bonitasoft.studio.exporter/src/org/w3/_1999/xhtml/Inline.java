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
import org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Inline</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 *       "Inline" covers inline or "text-level" elements
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.w3._1999.xhtml.Inline#getGroup <em>Group</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getA <em>A</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getBr <em>Br</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getSpan <em>Span</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getBdo <em>Bdo</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getMap <em>Map</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getObject <em>Object</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getImg <em>Img</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getTt <em>Tt</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getI <em>I</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getB <em>B</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getBig <em>Big</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getSmall <em>Small</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getEm <em>Em</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getStrong <em>Strong</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getDfn <em>Dfn</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getCode <em>Code</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getQ <em>Q</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getSamp <em>Samp</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getKbd <em>Kbd</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getVar <em>Var</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getCite <em>Cite</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getAbbr <em>Abbr</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getAcronym <em>Acronym</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getSub <em>Sub</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getSup <em>Sup</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getInput <em>Input</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getSelect <em>Select</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getTextarea <em>Textarea</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getLabel <em>Label</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getButton <em>Button</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getIns <em>Ins</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getDel <em>Del</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.Inline#getScript <em>Script</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.w3._1999.xhtml.XhtmlPackage#getInline()
 * @model extendedMetaData="name='Inline' kind='mixed'"
 * @generated
 */
public interface Inline extends EObject, XMLTypeDocumentRoot {
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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='group' name='group:1'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>A</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.AType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       content is "Inline" except that anchors shouldn't be nested
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>A</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_A()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='a' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<AType> getA();

	/**
	 * Returns the value of the '<em><b>Br</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.BrType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       forced line break
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Br</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Br()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='br' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<BrType> getBr();

	/**
	 * Returns the value of the '<em><b>Span</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.SpanType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       generic language/style container
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Span</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Span()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='span' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<SpanType> getSpan();

	/**
	 * Returns the value of the '<em><b>Bdo</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.BdoType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       I18N BiDi over-ride
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bdo</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Bdo()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='bdo' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<BdoType> getBdo();

	/**
	 * Returns the value of the '<em><b>Map</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.MapType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Map</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Map</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Map()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='map' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<MapType> getMap();

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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Object()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='object' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<ObjectType> getObject();

	/**
	 * Returns the value of the '<em><b>Img</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.ImgType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Img</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Img</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Img()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='img' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<ImgType> getImg();

	/**
	 * Returns the value of the '<em><b>Tt</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.TtType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       fixed pitch font
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Tt</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Tt()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='tt' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<TtType> getTt();

	/**
	 * Returns the value of the '<em><b>I</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.IType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       italic font
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>I</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_I()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='i' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<IType> getI();

	/**
	 * Returns the value of the '<em><b>B</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.BType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       bold font
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>B</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_B()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='b' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<BType> getB();

	/**
	 * Returns the value of the '<em><b>Big</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.BigType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       bigger font
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Big</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Big()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='big' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<BigType> getBig();

	/**
	 * Returns the value of the '<em><b>Small</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.SmallType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       smaller font
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Small</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Small()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='small' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<SmallType> getSmall();

	/**
	 * Returns the value of the '<em><b>Em</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.EmType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       emphasis
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Em</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Em()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='em' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<EmType> getEm();

	/**
	 * Returns the value of the '<em><b>Strong</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.StrongType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       strong emphasis
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Strong</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Strong()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='strong' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<StrongType> getStrong();

	/**
	 * Returns the value of the '<em><b>Dfn</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.DfnType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       definitional
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dfn</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Dfn()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='dfn' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<DfnType> getDfn();

	/**
	 * Returns the value of the '<em><b>Code</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.CodeType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       program code
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Code</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Code()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='code' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<CodeType> getCode();

	/**
	 * Returns the value of the '<em><b>Q</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.QType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       inlined quote
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Q</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Q()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='q' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<QType> getQ();

	/**
	 * Returns the value of the '<em><b>Samp</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.SampType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       sample
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Samp</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Samp()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='samp' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<SampType> getSamp();

	/**
	 * Returns the value of the '<em><b>Kbd</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.KbdType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       something user would type
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Kbd</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Kbd()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='kbd' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<KbdType> getKbd();

	/**
	 * Returns the value of the '<em><b>Var</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.VarType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       variable
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Var</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Var()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='var' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<VarType> getVar();

	/**
	 * Returns the value of the '<em><b>Cite</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.CiteType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       citation
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cite</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Cite()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='cite' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<CiteType> getCite();

	/**
	 * Returns the value of the '<em><b>Abbr</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.AbbrType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       abbreviation
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Abbr</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Abbr()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='abbr' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<AbbrType> getAbbr();

	/**
	 * Returns the value of the '<em><b>Acronym</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.AcronymType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       acronym
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Acronym</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Acronym()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='acronym' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<AcronymType> getAcronym();

	/**
	 * Returns the value of the '<em><b>Sub</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.SubType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       subscript
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sub</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Sub()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='sub' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<SubType> getSub();

	/**
	 * Returns the value of the '<em><b>Sup</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.SupType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       superscript
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sup</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Sup()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='sup' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<SupType> getSup();

	/**
	 * Returns the value of the '<em><b>Input</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.InputType1}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       form control
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Input</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Input()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='input' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<InputType1> getInput();

	/**
	 * Returns the value of the '<em><b>Select</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.SelectType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       option selector
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Select</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Select()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='select' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<SelectType> getSelect();

	/**
	 * Returns the value of the '<em><b>Textarea</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.TextareaType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       multi-line text field
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Textarea</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Textarea()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='textarea' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<TextareaType> getTextarea();

	/**
	 * Returns the value of the '<em><b>Label</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.LabelType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       Each label must not contain more than ONE field
	 *       Label elements shouldn't be nested.
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Label</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Label()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='label' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<LabelType> getLabel();

	/**
	 * Returns the value of the '<em><b>Button</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.ButtonType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *       Content is "Flow" excluding a, form and form controls
	 *       
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Button</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Button()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='button' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<ButtonType> getButton();

	/**
	 * Returns the value of the '<em><b>Ins</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.InsType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ins</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ins</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Ins()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='ins' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<InsType> getIns();

	/**
	 * Returns the value of the '<em><b>Del</b></em>' containment reference list.
	 * The list contents are of type {@link org.w3._1999.xhtml.DelType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Del</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Del</em>' containment reference list.
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Del()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='del' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<DelType> getDel();

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
	 * @see org.w3._1999.xhtml.XhtmlPackage#getInline_Script()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='script' namespace='##targetNamespace' group='#group:1'"
	 * @generated
	 */
	EList<ScriptType> getScript();

} // Inline
