/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.w3._1999.xhtml.impl;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.w3._1999.xhtml.AType;
import org.w3._1999.xhtml.AbbrType;
import org.w3._1999.xhtml.AcronymType;
import org.w3._1999.xhtml.AddressType;
import org.w3._1999.xhtml.AreaType;
import org.w3._1999.xhtml.BType;
import org.w3._1999.xhtml.BaseType;
import org.w3._1999.xhtml.BdoType;
import org.w3._1999.xhtml.BigType;
import org.w3._1999.xhtml.BlockquoteType;
import org.w3._1999.xhtml.BodyType;
import org.w3._1999.xhtml.BrType;
import org.w3._1999.xhtml.ButtonType;
import org.w3._1999.xhtml.CaptionType;
import org.w3._1999.xhtml.CiteType;
import org.w3._1999.xhtml.CodeType;
import org.w3._1999.xhtml.ColType;
import org.w3._1999.xhtml.ColgroupType;
import org.w3._1999.xhtml.DdType;
import org.w3._1999.xhtml.DelType;
import org.w3._1999.xhtml.DfnType;
import org.w3._1999.xhtml.DivType;
import org.w3._1999.xhtml.DlType;
import org.w3._1999.xhtml.DocumentRoot;
import org.w3._1999.xhtml.DtType;
import org.w3._1999.xhtml.EmType;
import org.w3._1999.xhtml.FieldsetType;
import org.w3._1999.xhtml.FormType;
import org.w3._1999.xhtml.H1Type;
import org.w3._1999.xhtml.H2Type;
import org.w3._1999.xhtml.H3Type;
import org.w3._1999.xhtml.H4Type;
import org.w3._1999.xhtml.H5Type;
import org.w3._1999.xhtml.H6Type;
import org.w3._1999.xhtml.HeadType;
import org.w3._1999.xhtml.HrType;
import org.w3._1999.xhtml.Html;
import org.w3._1999.xhtml.IType;
import org.w3._1999.xhtml.ImgType;
import org.w3._1999.xhtml.InputType1;
import org.w3._1999.xhtml.InsType;
import org.w3._1999.xhtml.KbdType;
import org.w3._1999.xhtml.LabelType;
import org.w3._1999.xhtml.LegendType;
import org.w3._1999.xhtml.LiType;
import org.w3._1999.xhtml.LinkType;
import org.w3._1999.xhtml.MapType;
import org.w3._1999.xhtml.MetaType;
import org.w3._1999.xhtml.NoscriptType;
import org.w3._1999.xhtml.ObjectType;
import org.w3._1999.xhtml.OlType;
import org.w3._1999.xhtml.OptgroupType;
import org.w3._1999.xhtml.OptionType;
import org.w3._1999.xhtml.PType;
import org.w3._1999.xhtml.ParamType;
import org.w3._1999.xhtml.PreType;
import org.w3._1999.xhtml.QType;
import org.w3._1999.xhtml.SampType;
import org.w3._1999.xhtml.ScriptType;
import org.w3._1999.xhtml.SelectType;
import org.w3._1999.xhtml.SmallType;
import org.w3._1999.xhtml.SpanType;
import org.w3._1999.xhtml.StrongType;
import org.w3._1999.xhtml.StyleType;
import org.w3._1999.xhtml.SubType;
import org.w3._1999.xhtml.SupType;
import org.w3._1999.xhtml.TableType;
import org.w3._1999.xhtml.TbodyType;
import org.w3._1999.xhtml.TdType;
import org.w3._1999.xhtml.TextareaType;
import org.w3._1999.xhtml.TfootType;
import org.w3._1999.xhtml.ThType;
import org.w3._1999.xhtml.TheadType;
import org.w3._1999.xhtml.TitleType;
import org.w3._1999.xhtml.TrType;
import org.w3._1999.xhtml.TtType;
import org.w3._1999.xhtml.UlType;
import org.w3._1999.xhtml.VarType;
import org.w3._1999.xhtml.XhtmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getA <em>A</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getAbbr <em>Abbr</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getAcronym <em>Acronym</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getAddress <em>Address</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getArea <em>Area</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getB <em>B</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getBase <em>Base</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getBdo <em>Bdo</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getBig <em>Big</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getBlockquote <em>Blockquote</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getBody <em>Body</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getBr <em>Br</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getButton <em>Button</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getCaption <em>Caption</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getCite <em>Cite</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getCode <em>Code</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getCol <em>Col</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getColgroup <em>Colgroup</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getDd <em>Dd</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getDel <em>Del</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getDfn <em>Dfn</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getDiv <em>Div</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getDl <em>Dl</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getDt <em>Dt</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getEm <em>Em</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getFieldset <em>Fieldset</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getForm <em>Form</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getH1 <em>H1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getH2 <em>H2</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getH3 <em>H3</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getH4 <em>H4</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getH5 <em>H5</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getH6 <em>H6</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getHead <em>Head</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getHr <em>Hr</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getHtml <em>Html</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getI <em>I</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getImg <em>Img</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getInput <em>Input</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getIns <em>Ins</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getKbd <em>Kbd</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getLegend <em>Legend</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getLi <em>Li</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getLink <em>Link</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getMap <em>Map</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getMeta <em>Meta</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getNoscript <em>Noscript</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getObject <em>Object</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getOl <em>Ol</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getOptgroup <em>Optgroup</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getOption <em>Option</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getP <em>P</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getParam <em>Param</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getPre <em>Pre</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getQ <em>Q</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getSamp <em>Samp</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getScript <em>Script</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getSelect <em>Select</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getSmall <em>Small</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getSpan <em>Span</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getStrong <em>Strong</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getSub <em>Sub</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getSup <em>Sup</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getTable <em>Table</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getTbody <em>Tbody</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getTd <em>Td</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getTextarea <em>Textarea</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getTfoot <em>Tfoot</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getTh <em>Th</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getThead <em>Thead</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getTr <em>Tr</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getTt <em>Tt</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getUl <em>Ul</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.DocumentRootImpl#getVar <em>Var</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends EObjectImpl implements DocumentRoot {
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed;

	/**
	 * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXMLNSPrefixMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xMLNSPrefixMap;

	/**
	 * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXSISchemaLocation()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xSISchemaLocation;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DocumentRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XhtmlPackage.eINSTANCE.getDocumentRoot();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, XhtmlPackage.DOCUMENT_ROOT__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXMLNSPrefixMap() {
		if (xMLNSPrefixMap == null) {
			xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, XhtmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		}
		return xMLNSPrefixMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getXSISchemaLocation() {
		if (xSISchemaLocation == null) {
			xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, XhtmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AType getA() {
		return (AType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_A(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetA(AType newA, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_A(), newA, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setA(AType newA) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_A(), newA);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbbrType getAbbr() {
		return (AbbrType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Abbr(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAbbr(AbbrType newAbbr, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Abbr(), newAbbr, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAbbr(AbbrType newAbbr) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Abbr(), newAbbr);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AcronymType getAcronym() {
		return (AcronymType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Acronym(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAcronym(AcronymType newAcronym, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Acronym(), newAcronym, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAcronym(AcronymType newAcronym) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Acronym(), newAcronym);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddressType getAddress() {
		return (AddressType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Address(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAddress(AddressType newAddress, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Address(), newAddress, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAddress(AddressType newAddress) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Address(), newAddress);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AreaType getArea() {
		return (AreaType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Area(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArea(AreaType newArea, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Area(), newArea, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArea(AreaType newArea) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Area(), newArea);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BType getB() {
		return (BType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_B(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetB(BType newB, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_B(), newB, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setB(BType newB) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_B(), newB);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BaseType getBase() {
		return (BaseType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Base(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBase(BaseType newBase, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Base(), newBase, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBase(BaseType newBase) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Base(), newBase);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BdoType getBdo() {
		return (BdoType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Bdo(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBdo(BdoType newBdo, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Bdo(), newBdo, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBdo(BdoType newBdo) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Bdo(), newBdo);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigType getBig() {
		return (BigType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Big(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBig(BigType newBig, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Big(), newBig, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBig(BigType newBig) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Big(), newBig);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BlockquoteType getBlockquote() {
		return (BlockquoteType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Blockquote(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBlockquote(BlockquoteType newBlockquote, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Blockquote(), newBlockquote, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBlockquote(BlockquoteType newBlockquote) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Blockquote(), newBlockquote);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BodyType getBody() {
		return (BodyType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Body(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBody(BodyType newBody, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Body(), newBody, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBody(BodyType newBody) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Body(), newBody);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BrType getBr() {
		return (BrType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Br(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBr(BrType newBr, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Br(), newBr, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBr(BrType newBr) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Br(), newBr);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ButtonType getButton() {
		return (ButtonType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Button(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetButton(ButtonType newButton, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Button(), newButton, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setButton(ButtonType newButton) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Button(), newButton);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CaptionType getCaption() {
		return (CaptionType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Caption(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCaption(CaptionType newCaption, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Caption(), newCaption, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCaption(CaptionType newCaption) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Caption(), newCaption);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CiteType getCite() {
		return (CiteType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Cite(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCite(CiteType newCite, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Cite(), newCite, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCite(CiteType newCite) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Cite(), newCite);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CodeType getCode() {
		return (CodeType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Code(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCode(CodeType newCode, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Code(), newCode, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCode(CodeType newCode) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Code(), newCode);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ColType getCol() {
		return (ColType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Col(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCol(ColType newCol, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Col(), newCol, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCol(ColType newCol) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Col(), newCol);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ColgroupType getColgroup() {
		return (ColgroupType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Colgroup(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetColgroup(ColgroupType newColgroup, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Colgroup(), newColgroup, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setColgroup(ColgroupType newColgroup) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Colgroup(), newColgroup);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DdType getDd() {
		return (DdType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Dd(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDd(DdType newDd, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Dd(), newDd, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDd(DdType newDd) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Dd(), newDd);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DelType getDel() {
		return (DelType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Del(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDel(DelType newDel, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Del(), newDel, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDel(DelType newDel) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Del(), newDel);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DfnType getDfn() {
		return (DfnType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Dfn(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDfn(DfnType newDfn, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Dfn(), newDfn, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDfn(DfnType newDfn) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Dfn(), newDfn);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DivType getDiv() {
		return (DivType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Div(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDiv(DivType newDiv, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Div(), newDiv, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDiv(DivType newDiv) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Div(), newDiv);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DlType getDl() {
		return (DlType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Dl(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDl(DlType newDl, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Dl(), newDl, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDl(DlType newDl) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Dl(), newDl);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DtType getDt() {
		return (DtType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Dt(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDt(DtType newDt, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Dt(), newDt, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDt(DtType newDt) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Dt(), newDt);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EmType getEm() {
		return (EmType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Em(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEm(EmType newEm, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Em(), newEm, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEm(EmType newEm) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Em(), newEm);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldsetType getFieldset() {
		return (FieldsetType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Fieldset(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFieldset(FieldsetType newFieldset, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Fieldset(), newFieldset, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFieldset(FieldsetType newFieldset) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Fieldset(), newFieldset);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormType getForm() {
		return (FormType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Form(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetForm(FormType newForm, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Form(), newForm, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setForm(FormType newForm) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Form(), newForm);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public H1Type getH1() {
		return (H1Type)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_H1(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetH1(H1Type newH1, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_H1(), newH1, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setH1(H1Type newH1) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_H1(), newH1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public H2Type getH2() {
		return (H2Type)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_H2(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetH2(H2Type newH2, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_H2(), newH2, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setH2(H2Type newH2) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_H2(), newH2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public H3Type getH3() {
		return (H3Type)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_H3(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetH3(H3Type newH3, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_H3(), newH3, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setH3(H3Type newH3) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_H3(), newH3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public H4Type getH4() {
		return (H4Type)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_H4(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetH4(H4Type newH4, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_H4(), newH4, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setH4(H4Type newH4) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_H4(), newH4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public H5Type getH5() {
		return (H5Type)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_H5(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetH5(H5Type newH5, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_H5(), newH5, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setH5(H5Type newH5) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_H5(), newH5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public H6Type getH6() {
		return (H6Type)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_H6(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetH6(H6Type newH6, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_H6(), newH6, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setH6(H6Type newH6) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_H6(), newH6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HeadType getHead() {
		return (HeadType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Head(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHead(HeadType newHead, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Head(), newHead, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHead(HeadType newHead) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Head(), newHead);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HrType getHr() {
		return (HrType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Hr(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHr(HrType newHr, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Hr(), newHr, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHr(HrType newHr) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Hr(), newHr);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Html getHtml() {
		return (Html)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Html(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetHtml(Html newHtml, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Html(), newHtml, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHtml(Html newHtml) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Html(), newHtml);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IType getI() {
		return (IType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_I(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetI(IType newI, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_I(), newI, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setI(IType newI) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_I(), newI);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImgType getImg() {
		return (ImgType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Img(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetImg(ImgType newImg, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Img(), newImg, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImg(ImgType newImg) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Img(), newImg);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputType1 getInput() {
		return (InputType1)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Input(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInput(InputType1 newInput, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Input(), newInput, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInput(InputType1 newInput) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Input(), newInput);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InsType getIns() {
		return (InsType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Ins(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIns(InsType newIns, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Ins(), newIns, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIns(InsType newIns) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Ins(), newIns);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KbdType getKbd() {
		return (KbdType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Kbd(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetKbd(KbdType newKbd, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Kbd(), newKbd, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKbd(KbdType newKbd) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Kbd(), newKbd);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LabelType getLabel() {
		return (LabelType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Label(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLabel(LabelType newLabel, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Label(), newLabel, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabel(LabelType newLabel) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Label(), newLabel);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LegendType getLegend() {
		return (LegendType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Legend(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLegend(LegendType newLegend, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Legend(), newLegend, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLegend(LegendType newLegend) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Legend(), newLegend);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LiType getLi() {
		return (LiType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Li(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLi(LiType newLi, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Li(), newLi, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLi(LiType newLi) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Li(), newLi);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkType getLink() {
		return (LinkType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Link(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetLink(LinkType newLink, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Link(), newLink, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLink(LinkType newLink) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Link(), newLink);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MapType getMap() {
		return (MapType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Map(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMap(MapType newMap, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Map(), newMap, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMap(MapType newMap) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Map(), newMap);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetaType getMeta() {
		return (MetaType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Meta(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMeta(MetaType newMeta, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Meta(), newMeta, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMeta(MetaType newMeta) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Meta(), newMeta);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NoscriptType getNoscript() {
		return (NoscriptType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Noscript(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNoscript(NoscriptType newNoscript, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Noscript(), newNoscript, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNoscript(NoscriptType newNoscript) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Noscript(), newNoscript);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObjectType getObject() {
		return (ObjectType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Object(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetObject(ObjectType newObject, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Object(), newObject, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setObject(ObjectType newObject) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Object(), newObject);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OlType getOl() {
		return (OlType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Ol(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOl(OlType newOl, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Ol(), newOl, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOl(OlType newOl) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Ol(), newOl);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OptgroupType getOptgroup() {
		return (OptgroupType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Optgroup(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOptgroup(OptgroupType newOptgroup, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Optgroup(), newOptgroup, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOptgroup(OptgroupType newOptgroup) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Optgroup(), newOptgroup);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OptionType getOption() {
		return (OptionType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Option(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOption(OptionType newOption, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Option(), newOption, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOption(OptionType newOption) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Option(), newOption);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PType getP() {
		return (PType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_P(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetP(PType newP, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_P(), newP, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setP(PType newP) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_P(), newP);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParamType getParam() {
		return (ParamType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Param(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetParam(ParamType newParam, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Param(), newParam, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setParam(ParamType newParam) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Param(), newParam);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PreType getPre() {
		return (PreType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Pre(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPre(PreType newPre, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Pre(), newPre, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPre(PreType newPre) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Pre(), newPre);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QType getQ() {
		return (QType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Q(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetQ(QType newQ, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Q(), newQ, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setQ(QType newQ) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Q(), newQ);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SampType getSamp() {
		return (SampType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Samp(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSamp(SampType newSamp, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Samp(), newSamp, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSamp(SampType newSamp) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Samp(), newSamp);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScriptType getScript() {
		return (ScriptType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Script(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetScript(ScriptType newScript, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Script(), newScript, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScript(ScriptType newScript) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Script(), newScript);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SelectType getSelect() {
		return (SelectType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Select(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSelect(SelectType newSelect, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Select(), newSelect, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSelect(SelectType newSelect) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Select(), newSelect);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SmallType getSmall() {
		return (SmallType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Small(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSmall(SmallType newSmall, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Small(), newSmall, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSmall(SmallType newSmall) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Small(), newSmall);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpanType getSpan() {
		return (SpanType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Span(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSpan(SpanType newSpan, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Span(), newSpan, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSpan(SpanType newSpan) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Span(), newSpan);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StrongType getStrong() {
		return (StrongType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Strong(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStrong(StrongType newStrong, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Strong(), newStrong, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStrong(StrongType newStrong) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Strong(), newStrong);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StyleType getStyle() {
		return (StyleType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Style(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStyle(StyleType newStyle, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Style(), newStyle, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStyle(StyleType newStyle) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Style(), newStyle);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubType getSub() {
		return (SubType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Sub(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSub(SubType newSub, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Sub(), newSub, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSub(SubType newSub) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Sub(), newSub);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SupType getSup() {
		return (SupType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Sup(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSup(SupType newSup, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Sup(), newSup, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSup(SupType newSup) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Sup(), newSup);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableType getTable() {
		return (TableType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Table(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTable(TableType newTable, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Table(), newTable, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTable(TableType newTable) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Table(), newTable);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TbodyType getTbody() {
		return (TbodyType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Tbody(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTbody(TbodyType newTbody, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Tbody(), newTbody, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTbody(TbodyType newTbody) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Tbody(), newTbody);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TdType getTd() {
		return (TdType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Td(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTd(TdType newTd, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Td(), newTd, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTd(TdType newTd) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Td(), newTd);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TextareaType getTextarea() {
		return (TextareaType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Textarea(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTextarea(TextareaType newTextarea, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Textarea(), newTextarea, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTextarea(TextareaType newTextarea) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Textarea(), newTextarea);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TfootType getTfoot() {
		return (TfootType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Tfoot(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTfoot(TfootType newTfoot, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Tfoot(), newTfoot, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTfoot(TfootType newTfoot) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Tfoot(), newTfoot);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ThType getTh() {
		return (ThType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Th(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTh(ThType newTh, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Th(), newTh, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTh(ThType newTh) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Th(), newTh);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TheadType getThead() {
		return (TheadType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Thead(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetThead(TheadType newThead, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Thead(), newThead, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setThead(TheadType newThead) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Thead(), newThead);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TitleType getTitle() {
		return (TitleType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Title(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTitle(TitleType newTitle, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Title(), newTitle, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTitle(TitleType newTitle) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Title(), newTitle);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TrType getTr() {
		return (TrType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Tr(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTr(TrType newTr, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Tr(), newTr, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTr(TrType newTr) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Tr(), newTr);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TtType getTt() {
		return (TtType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Tt(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTt(TtType newTt, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Tt(), newTt, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTt(TtType newTt) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Tt(), newTt);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UlType getUl() {
		return (UlType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Ul(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUl(UlType newUl, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Ul(), newUl, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUl(UlType newUl) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Ul(), newUl);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VarType getVar() {
		return (VarType)getMixed().get(XhtmlPackage.eINSTANCE.getDocumentRoot_Var(), true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetVar(VarType newVar, NotificationChain msgs) {
		return ((FeatureMap.Internal)getMixed()).basicAdd(XhtmlPackage.eINSTANCE.getDocumentRoot_Var(), newVar, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVar(VarType newVar) {
		((FeatureMap.Internal)getMixed()).set(XhtmlPackage.eINSTANCE.getDocumentRoot_Var(), newVar);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case XhtmlPackage.DOCUMENT_ROOT__MIXED:
				return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return ((InternalEList<?>)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return ((InternalEList<?>)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__A:
				return basicSetA(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__ABBR:
				return basicSetAbbr(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__ACRONYM:
				return basicSetAcronym(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__ADDRESS:
				return basicSetAddress(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__AREA:
				return basicSetArea(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__B:
				return basicSetB(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__BASE:
				return basicSetBase(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__BDO:
				return basicSetBdo(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__BIG:
				return basicSetBig(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__BLOCKQUOTE:
				return basicSetBlockquote(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__BODY:
				return basicSetBody(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__BR:
				return basicSetBr(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__BUTTON:
				return basicSetButton(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__CAPTION:
				return basicSetCaption(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__CITE:
				return basicSetCite(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__CODE:
				return basicSetCode(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__COL:
				return basicSetCol(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__COLGROUP:
				return basicSetColgroup(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__DD:
				return basicSetDd(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__DEL:
				return basicSetDel(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__DFN:
				return basicSetDfn(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__DIV:
				return basicSetDiv(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__DL:
				return basicSetDl(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__DT:
				return basicSetDt(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__EM:
				return basicSetEm(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__FIELDSET:
				return basicSetFieldset(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__FORM:
				return basicSetForm(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__H1:
				return basicSetH1(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__H2:
				return basicSetH2(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__H3:
				return basicSetH3(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__H4:
				return basicSetH4(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__H5:
				return basicSetH5(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__H6:
				return basicSetH6(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__HEAD:
				return basicSetHead(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__HR:
				return basicSetHr(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__HTML:
				return basicSetHtml(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__I:
				return basicSetI(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__IMG:
				return basicSetImg(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__INPUT:
				return basicSetInput(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__INS:
				return basicSetIns(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__KBD:
				return basicSetKbd(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__LABEL:
				return basicSetLabel(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__LEGEND:
				return basicSetLegend(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__LI:
				return basicSetLi(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__LINK:
				return basicSetLink(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__MAP:
				return basicSetMap(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__META:
				return basicSetMeta(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__NOSCRIPT:
				return basicSetNoscript(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__OBJECT:
				return basicSetObject(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__OL:
				return basicSetOl(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__OPTGROUP:
				return basicSetOptgroup(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__OPTION:
				return basicSetOption(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__P:
				return basicSetP(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__PARAM:
				return basicSetParam(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__PRE:
				return basicSetPre(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__Q:
				return basicSetQ(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__SAMP:
				return basicSetSamp(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__SCRIPT:
				return basicSetScript(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__SELECT:
				return basicSetSelect(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__SMALL:
				return basicSetSmall(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__SPAN:
				return basicSetSpan(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__STRONG:
				return basicSetStrong(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__STYLE:
				return basicSetStyle(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__SUB:
				return basicSetSub(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__SUP:
				return basicSetSup(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__TABLE:
				return basicSetTable(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__TBODY:
				return basicSetTbody(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__TD:
				return basicSetTd(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__TEXTAREA:
				return basicSetTextarea(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__TFOOT:
				return basicSetTfoot(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__TH:
				return basicSetTh(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__THEAD:
				return basicSetThead(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__TITLE:
				return basicSetTitle(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__TR:
				return basicSetTr(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__TT:
				return basicSetTt(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__UL:
				return basicSetUl(null, msgs);
			case XhtmlPackage.DOCUMENT_ROOT__VAR:
				return basicSetVar(null, msgs);
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
			case XhtmlPackage.DOCUMENT_ROOT__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case XhtmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				if (coreType) return getXMLNSPrefixMap();
				else return getXMLNSPrefixMap().map();
			case XhtmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				if (coreType) return getXSISchemaLocation();
				else return getXSISchemaLocation().map();
			case XhtmlPackage.DOCUMENT_ROOT__A:
				return getA();
			case XhtmlPackage.DOCUMENT_ROOT__ABBR:
				return getAbbr();
			case XhtmlPackage.DOCUMENT_ROOT__ACRONYM:
				return getAcronym();
			case XhtmlPackage.DOCUMENT_ROOT__ADDRESS:
				return getAddress();
			case XhtmlPackage.DOCUMENT_ROOT__AREA:
				return getArea();
			case XhtmlPackage.DOCUMENT_ROOT__B:
				return getB();
			case XhtmlPackage.DOCUMENT_ROOT__BASE:
				return getBase();
			case XhtmlPackage.DOCUMENT_ROOT__BDO:
				return getBdo();
			case XhtmlPackage.DOCUMENT_ROOT__BIG:
				return getBig();
			case XhtmlPackage.DOCUMENT_ROOT__BLOCKQUOTE:
				return getBlockquote();
			case XhtmlPackage.DOCUMENT_ROOT__BODY:
				return getBody();
			case XhtmlPackage.DOCUMENT_ROOT__BR:
				return getBr();
			case XhtmlPackage.DOCUMENT_ROOT__BUTTON:
				return getButton();
			case XhtmlPackage.DOCUMENT_ROOT__CAPTION:
				return getCaption();
			case XhtmlPackage.DOCUMENT_ROOT__CITE:
				return getCite();
			case XhtmlPackage.DOCUMENT_ROOT__CODE:
				return getCode();
			case XhtmlPackage.DOCUMENT_ROOT__COL:
				return getCol();
			case XhtmlPackage.DOCUMENT_ROOT__COLGROUP:
				return getColgroup();
			case XhtmlPackage.DOCUMENT_ROOT__DD:
				return getDd();
			case XhtmlPackage.DOCUMENT_ROOT__DEL:
				return getDel();
			case XhtmlPackage.DOCUMENT_ROOT__DFN:
				return getDfn();
			case XhtmlPackage.DOCUMENT_ROOT__DIV:
				return getDiv();
			case XhtmlPackage.DOCUMENT_ROOT__DL:
				return getDl();
			case XhtmlPackage.DOCUMENT_ROOT__DT:
				return getDt();
			case XhtmlPackage.DOCUMENT_ROOT__EM:
				return getEm();
			case XhtmlPackage.DOCUMENT_ROOT__FIELDSET:
				return getFieldset();
			case XhtmlPackage.DOCUMENT_ROOT__FORM:
				return getForm();
			case XhtmlPackage.DOCUMENT_ROOT__H1:
				return getH1();
			case XhtmlPackage.DOCUMENT_ROOT__H2:
				return getH2();
			case XhtmlPackage.DOCUMENT_ROOT__H3:
				return getH3();
			case XhtmlPackage.DOCUMENT_ROOT__H4:
				return getH4();
			case XhtmlPackage.DOCUMENT_ROOT__H5:
				return getH5();
			case XhtmlPackage.DOCUMENT_ROOT__H6:
				return getH6();
			case XhtmlPackage.DOCUMENT_ROOT__HEAD:
				return getHead();
			case XhtmlPackage.DOCUMENT_ROOT__HR:
				return getHr();
			case XhtmlPackage.DOCUMENT_ROOT__HTML:
				return getHtml();
			case XhtmlPackage.DOCUMENT_ROOT__I:
				return getI();
			case XhtmlPackage.DOCUMENT_ROOT__IMG:
				return getImg();
			case XhtmlPackage.DOCUMENT_ROOT__INPUT:
				return getInput();
			case XhtmlPackage.DOCUMENT_ROOT__INS:
				return getIns();
			case XhtmlPackage.DOCUMENT_ROOT__KBD:
				return getKbd();
			case XhtmlPackage.DOCUMENT_ROOT__LABEL:
				return getLabel();
			case XhtmlPackage.DOCUMENT_ROOT__LEGEND:
				return getLegend();
			case XhtmlPackage.DOCUMENT_ROOT__LI:
				return getLi();
			case XhtmlPackage.DOCUMENT_ROOT__LINK:
				return getLink();
			case XhtmlPackage.DOCUMENT_ROOT__MAP:
				return getMap();
			case XhtmlPackage.DOCUMENT_ROOT__META:
				return getMeta();
			case XhtmlPackage.DOCUMENT_ROOT__NOSCRIPT:
				return getNoscript();
			case XhtmlPackage.DOCUMENT_ROOT__OBJECT:
				return getObject();
			case XhtmlPackage.DOCUMENT_ROOT__OL:
				return getOl();
			case XhtmlPackage.DOCUMENT_ROOT__OPTGROUP:
				return getOptgroup();
			case XhtmlPackage.DOCUMENT_ROOT__OPTION:
				return getOption();
			case XhtmlPackage.DOCUMENT_ROOT__P:
				return getP();
			case XhtmlPackage.DOCUMENT_ROOT__PARAM:
				return getParam();
			case XhtmlPackage.DOCUMENT_ROOT__PRE:
				return getPre();
			case XhtmlPackage.DOCUMENT_ROOT__Q:
				return getQ();
			case XhtmlPackage.DOCUMENT_ROOT__SAMP:
				return getSamp();
			case XhtmlPackage.DOCUMENT_ROOT__SCRIPT:
				return getScript();
			case XhtmlPackage.DOCUMENT_ROOT__SELECT:
				return getSelect();
			case XhtmlPackage.DOCUMENT_ROOT__SMALL:
				return getSmall();
			case XhtmlPackage.DOCUMENT_ROOT__SPAN:
				return getSpan();
			case XhtmlPackage.DOCUMENT_ROOT__STRONG:
				return getStrong();
			case XhtmlPackage.DOCUMENT_ROOT__STYLE:
				return getStyle();
			case XhtmlPackage.DOCUMENT_ROOT__SUB:
				return getSub();
			case XhtmlPackage.DOCUMENT_ROOT__SUP:
				return getSup();
			case XhtmlPackage.DOCUMENT_ROOT__TABLE:
				return getTable();
			case XhtmlPackage.DOCUMENT_ROOT__TBODY:
				return getTbody();
			case XhtmlPackage.DOCUMENT_ROOT__TD:
				return getTd();
			case XhtmlPackage.DOCUMENT_ROOT__TEXTAREA:
				return getTextarea();
			case XhtmlPackage.DOCUMENT_ROOT__TFOOT:
				return getTfoot();
			case XhtmlPackage.DOCUMENT_ROOT__TH:
				return getTh();
			case XhtmlPackage.DOCUMENT_ROOT__THEAD:
				return getThead();
			case XhtmlPackage.DOCUMENT_ROOT__TITLE:
				return getTitle();
			case XhtmlPackage.DOCUMENT_ROOT__TR:
				return getTr();
			case XhtmlPackage.DOCUMENT_ROOT__TT:
				return getTt();
			case XhtmlPackage.DOCUMENT_ROOT__UL:
				return getUl();
			case XhtmlPackage.DOCUMENT_ROOT__VAR:
				return getVar();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case XhtmlPackage.DOCUMENT_ROOT__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__A:
				setA((AType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__ABBR:
				setAbbr((AbbrType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__ACRONYM:
				setAcronym((AcronymType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__ADDRESS:
				setAddress((AddressType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__AREA:
				setArea((AreaType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__B:
				setB((BType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__BASE:
				setBase((BaseType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__BDO:
				setBdo((BdoType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__BIG:
				setBig((BigType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__BLOCKQUOTE:
				setBlockquote((BlockquoteType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__BODY:
				setBody((BodyType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__BR:
				setBr((BrType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__BUTTON:
				setButton((ButtonType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__CAPTION:
				setCaption((CaptionType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__CITE:
				setCite((CiteType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__CODE:
				setCode((CodeType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__COL:
				setCol((ColType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__COLGROUP:
				setColgroup((ColgroupType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__DD:
				setDd((DdType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__DEL:
				setDel((DelType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__DFN:
				setDfn((DfnType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__DIV:
				setDiv((DivType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__DL:
				setDl((DlType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__DT:
				setDt((DtType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__EM:
				setEm((EmType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__FIELDSET:
				setFieldset((FieldsetType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__FORM:
				setForm((FormType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__H1:
				setH1((H1Type)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__H2:
				setH2((H2Type)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__H3:
				setH3((H3Type)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__H4:
				setH4((H4Type)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__H5:
				setH5((H5Type)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__H6:
				setH6((H6Type)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__HEAD:
				setHead((HeadType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__HR:
				setHr((HrType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__HTML:
				setHtml((Html)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__I:
				setI((IType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__IMG:
				setImg((ImgType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__INPUT:
				setInput((InputType1)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__INS:
				setIns((InsType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__KBD:
				setKbd((KbdType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__LABEL:
				setLabel((LabelType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__LEGEND:
				setLegend((LegendType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__LI:
				setLi((LiType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__LINK:
				setLink((LinkType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__MAP:
				setMap((MapType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__META:
				setMeta((MetaType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__NOSCRIPT:
				setNoscript((NoscriptType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__OBJECT:
				setObject((ObjectType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__OL:
				setOl((OlType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__OPTGROUP:
				setOptgroup((OptgroupType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__OPTION:
				setOption((OptionType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__P:
				setP((PType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__PARAM:
				setParam((ParamType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__PRE:
				setPre((PreType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__Q:
				setQ((QType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__SAMP:
				setSamp((SampType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__SCRIPT:
				setScript((ScriptType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__SELECT:
				setSelect((SelectType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__SMALL:
				setSmall((SmallType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__SPAN:
				setSpan((SpanType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__STRONG:
				setStrong((StrongType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__STYLE:
				setStyle((StyleType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__SUB:
				setSub((SubType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__SUP:
				setSup((SupType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__TABLE:
				setTable((TableType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__TBODY:
				setTbody((TbodyType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__TD:
				setTd((TdType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__TEXTAREA:
				setTextarea((TextareaType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__TFOOT:
				setTfoot((TfootType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__TH:
				setTh((ThType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__THEAD:
				setThead((TheadType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__TITLE:
				setTitle((TitleType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__TR:
				setTr((TrType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__TT:
				setTt((TtType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__UL:
				setUl((UlType)newValue);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__VAR:
				setVar((VarType)newValue);
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
			case XhtmlPackage.DOCUMENT_ROOT__MIXED:
				getMixed().clear();
				return;
			case XhtmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				getXMLNSPrefixMap().clear();
				return;
			case XhtmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				getXSISchemaLocation().clear();
				return;
			case XhtmlPackage.DOCUMENT_ROOT__A:
				setA((AType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__ABBR:
				setAbbr((AbbrType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__ACRONYM:
				setAcronym((AcronymType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__ADDRESS:
				setAddress((AddressType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__AREA:
				setArea((AreaType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__B:
				setB((BType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__BASE:
				setBase((BaseType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__BDO:
				setBdo((BdoType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__BIG:
				setBig((BigType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__BLOCKQUOTE:
				setBlockquote((BlockquoteType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__BODY:
				setBody((BodyType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__BR:
				setBr((BrType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__BUTTON:
				setButton((ButtonType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__CAPTION:
				setCaption((CaptionType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__CITE:
				setCite((CiteType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__CODE:
				setCode((CodeType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__COL:
				setCol((ColType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__COLGROUP:
				setColgroup((ColgroupType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__DD:
				setDd((DdType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__DEL:
				setDel((DelType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__DFN:
				setDfn((DfnType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__DIV:
				setDiv((DivType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__DL:
				setDl((DlType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__DT:
				setDt((DtType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__EM:
				setEm((EmType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__FIELDSET:
				setFieldset((FieldsetType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__FORM:
				setForm((FormType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__H1:
				setH1((H1Type)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__H2:
				setH2((H2Type)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__H3:
				setH3((H3Type)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__H4:
				setH4((H4Type)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__H5:
				setH5((H5Type)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__H6:
				setH6((H6Type)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__HEAD:
				setHead((HeadType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__HR:
				setHr((HrType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__HTML:
				setHtml((Html)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__I:
				setI((IType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__IMG:
				setImg((ImgType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__INPUT:
				setInput((InputType1)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__INS:
				setIns((InsType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__KBD:
				setKbd((KbdType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__LABEL:
				setLabel((LabelType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__LEGEND:
				setLegend((LegendType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__LI:
				setLi((LiType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__LINK:
				setLink((LinkType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__MAP:
				setMap((MapType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__META:
				setMeta((MetaType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__NOSCRIPT:
				setNoscript((NoscriptType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__OBJECT:
				setObject((ObjectType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__OL:
				setOl((OlType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__OPTGROUP:
				setOptgroup((OptgroupType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__OPTION:
				setOption((OptionType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__P:
				setP((PType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__PARAM:
				setParam((ParamType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__PRE:
				setPre((PreType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__Q:
				setQ((QType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__SAMP:
				setSamp((SampType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__SCRIPT:
				setScript((ScriptType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__SELECT:
				setSelect((SelectType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__SMALL:
				setSmall((SmallType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__SPAN:
				setSpan((SpanType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__STRONG:
				setStrong((StrongType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__STYLE:
				setStyle((StyleType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__SUB:
				setSub((SubType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__SUP:
				setSup((SupType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__TABLE:
				setTable((TableType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__TBODY:
				setTbody((TbodyType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__TD:
				setTd((TdType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__TEXTAREA:
				setTextarea((TextareaType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__TFOOT:
				setTfoot((TfootType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__TH:
				setTh((ThType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__THEAD:
				setThead((TheadType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__TITLE:
				setTitle((TitleType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__TR:
				setTr((TrType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__TT:
				setTt((TtType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__UL:
				setUl((UlType)null);
				return;
			case XhtmlPackage.DOCUMENT_ROOT__VAR:
				setVar((VarType)null);
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
			case XhtmlPackage.DOCUMENT_ROOT__MIXED:
				return mixed != null && !mixed.isEmpty();
			case XhtmlPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
				return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
			case XhtmlPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
				return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
			case XhtmlPackage.DOCUMENT_ROOT__A:
				return getA() != null;
			case XhtmlPackage.DOCUMENT_ROOT__ABBR:
				return getAbbr() != null;
			case XhtmlPackage.DOCUMENT_ROOT__ACRONYM:
				return getAcronym() != null;
			case XhtmlPackage.DOCUMENT_ROOT__ADDRESS:
				return getAddress() != null;
			case XhtmlPackage.DOCUMENT_ROOT__AREA:
				return getArea() != null;
			case XhtmlPackage.DOCUMENT_ROOT__B:
				return getB() != null;
			case XhtmlPackage.DOCUMENT_ROOT__BASE:
				return getBase() != null;
			case XhtmlPackage.DOCUMENT_ROOT__BDO:
				return getBdo() != null;
			case XhtmlPackage.DOCUMENT_ROOT__BIG:
				return getBig() != null;
			case XhtmlPackage.DOCUMENT_ROOT__BLOCKQUOTE:
				return getBlockquote() != null;
			case XhtmlPackage.DOCUMENT_ROOT__BODY:
				return getBody() != null;
			case XhtmlPackage.DOCUMENT_ROOT__BR:
				return getBr() != null;
			case XhtmlPackage.DOCUMENT_ROOT__BUTTON:
				return getButton() != null;
			case XhtmlPackage.DOCUMENT_ROOT__CAPTION:
				return getCaption() != null;
			case XhtmlPackage.DOCUMENT_ROOT__CITE:
				return getCite() != null;
			case XhtmlPackage.DOCUMENT_ROOT__CODE:
				return getCode() != null;
			case XhtmlPackage.DOCUMENT_ROOT__COL:
				return getCol() != null;
			case XhtmlPackage.DOCUMENT_ROOT__COLGROUP:
				return getColgroup() != null;
			case XhtmlPackage.DOCUMENT_ROOT__DD:
				return getDd() != null;
			case XhtmlPackage.DOCUMENT_ROOT__DEL:
				return getDel() != null;
			case XhtmlPackage.DOCUMENT_ROOT__DFN:
				return getDfn() != null;
			case XhtmlPackage.DOCUMENT_ROOT__DIV:
				return getDiv() != null;
			case XhtmlPackage.DOCUMENT_ROOT__DL:
				return getDl() != null;
			case XhtmlPackage.DOCUMENT_ROOT__DT:
				return getDt() != null;
			case XhtmlPackage.DOCUMENT_ROOT__EM:
				return getEm() != null;
			case XhtmlPackage.DOCUMENT_ROOT__FIELDSET:
				return getFieldset() != null;
			case XhtmlPackage.DOCUMENT_ROOT__FORM:
				return getForm() != null;
			case XhtmlPackage.DOCUMENT_ROOT__H1:
				return getH1() != null;
			case XhtmlPackage.DOCUMENT_ROOT__H2:
				return getH2() != null;
			case XhtmlPackage.DOCUMENT_ROOT__H3:
				return getH3() != null;
			case XhtmlPackage.DOCUMENT_ROOT__H4:
				return getH4() != null;
			case XhtmlPackage.DOCUMENT_ROOT__H5:
				return getH5() != null;
			case XhtmlPackage.DOCUMENT_ROOT__H6:
				return getH6() != null;
			case XhtmlPackage.DOCUMENT_ROOT__HEAD:
				return getHead() != null;
			case XhtmlPackage.DOCUMENT_ROOT__HR:
				return getHr() != null;
			case XhtmlPackage.DOCUMENT_ROOT__HTML:
				return getHtml() != null;
			case XhtmlPackage.DOCUMENT_ROOT__I:
				return getI() != null;
			case XhtmlPackage.DOCUMENT_ROOT__IMG:
				return getImg() != null;
			case XhtmlPackage.DOCUMENT_ROOT__INPUT:
				return getInput() != null;
			case XhtmlPackage.DOCUMENT_ROOT__INS:
				return getIns() != null;
			case XhtmlPackage.DOCUMENT_ROOT__KBD:
				return getKbd() != null;
			case XhtmlPackage.DOCUMENT_ROOT__LABEL:
				return getLabel() != null;
			case XhtmlPackage.DOCUMENT_ROOT__LEGEND:
				return getLegend() != null;
			case XhtmlPackage.DOCUMENT_ROOT__LI:
				return getLi() != null;
			case XhtmlPackage.DOCUMENT_ROOT__LINK:
				return getLink() != null;
			case XhtmlPackage.DOCUMENT_ROOT__MAP:
				return getMap() != null;
			case XhtmlPackage.DOCUMENT_ROOT__META:
				return getMeta() != null;
			case XhtmlPackage.DOCUMENT_ROOT__NOSCRIPT:
				return getNoscript() != null;
			case XhtmlPackage.DOCUMENT_ROOT__OBJECT:
				return getObject() != null;
			case XhtmlPackage.DOCUMENT_ROOT__OL:
				return getOl() != null;
			case XhtmlPackage.DOCUMENT_ROOT__OPTGROUP:
				return getOptgroup() != null;
			case XhtmlPackage.DOCUMENT_ROOT__OPTION:
				return getOption() != null;
			case XhtmlPackage.DOCUMENT_ROOT__P:
				return getP() != null;
			case XhtmlPackage.DOCUMENT_ROOT__PARAM:
				return getParam() != null;
			case XhtmlPackage.DOCUMENT_ROOT__PRE:
				return getPre() != null;
			case XhtmlPackage.DOCUMENT_ROOT__Q:
				return getQ() != null;
			case XhtmlPackage.DOCUMENT_ROOT__SAMP:
				return getSamp() != null;
			case XhtmlPackage.DOCUMENT_ROOT__SCRIPT:
				return getScript() != null;
			case XhtmlPackage.DOCUMENT_ROOT__SELECT:
				return getSelect() != null;
			case XhtmlPackage.DOCUMENT_ROOT__SMALL:
				return getSmall() != null;
			case XhtmlPackage.DOCUMENT_ROOT__SPAN:
				return getSpan() != null;
			case XhtmlPackage.DOCUMENT_ROOT__STRONG:
				return getStrong() != null;
			case XhtmlPackage.DOCUMENT_ROOT__STYLE:
				return getStyle() != null;
			case XhtmlPackage.DOCUMENT_ROOT__SUB:
				return getSub() != null;
			case XhtmlPackage.DOCUMENT_ROOT__SUP:
				return getSup() != null;
			case XhtmlPackage.DOCUMENT_ROOT__TABLE:
				return getTable() != null;
			case XhtmlPackage.DOCUMENT_ROOT__TBODY:
				return getTbody() != null;
			case XhtmlPackage.DOCUMENT_ROOT__TD:
				return getTd() != null;
			case XhtmlPackage.DOCUMENT_ROOT__TEXTAREA:
				return getTextarea() != null;
			case XhtmlPackage.DOCUMENT_ROOT__TFOOT:
				return getTfoot() != null;
			case XhtmlPackage.DOCUMENT_ROOT__TH:
				return getTh() != null;
			case XhtmlPackage.DOCUMENT_ROOT__THEAD:
				return getThead() != null;
			case XhtmlPackage.DOCUMENT_ROOT__TITLE:
				return getTitle() != null;
			case XhtmlPackage.DOCUMENT_ROOT__TR:
				return getTr() != null;
			case XhtmlPackage.DOCUMENT_ROOT__TT:
				return getTt() != null;
			case XhtmlPackage.DOCUMENT_ROOT__UL:
				return getUl() != null;
			case XhtmlPackage.DOCUMENT_ROOT__VAR:
				return getVar() != null;
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
		result.append(" (mixed: ");
		result.append(mixed);
		result.append(')');
		return result.toString();
	}

} //DocumentRootImpl
