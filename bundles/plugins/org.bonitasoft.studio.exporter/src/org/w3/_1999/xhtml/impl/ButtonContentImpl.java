/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.w3._1999.xhtml.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.w3._1999.xhtml.AbbrType;
import org.w3._1999.xhtml.AcronymType;
import org.w3._1999.xhtml.AddressType;
import org.w3._1999.xhtml.BType;
import org.w3._1999.xhtml.BdoType;
import org.w3._1999.xhtml.BigType;
import org.w3._1999.xhtml.BlockquoteType;
import org.w3._1999.xhtml.BrType;
import org.w3._1999.xhtml.ButtonContent;
import org.w3._1999.xhtml.CiteType;
import org.w3._1999.xhtml.CodeType;
import org.w3._1999.xhtml.DelType;
import org.w3._1999.xhtml.DfnType;
import org.w3._1999.xhtml.DivType;
import org.w3._1999.xhtml.DlType;
import org.w3._1999.xhtml.EmType;
import org.w3._1999.xhtml.H1Type;
import org.w3._1999.xhtml.H2Type;
import org.w3._1999.xhtml.H3Type;
import org.w3._1999.xhtml.H4Type;
import org.w3._1999.xhtml.H5Type;
import org.w3._1999.xhtml.H6Type;
import org.w3._1999.xhtml.HrType;
import org.w3._1999.xhtml.IType;
import org.w3._1999.xhtml.ImgType;
import org.w3._1999.xhtml.InsType;
import org.w3._1999.xhtml.KbdType;
import org.w3._1999.xhtml.MapType;
import org.w3._1999.xhtml.NoscriptType;
import org.w3._1999.xhtml.ObjectType;
import org.w3._1999.xhtml.OlType;
import org.w3._1999.xhtml.PType;
import org.w3._1999.xhtml.PreType;
import org.w3._1999.xhtml.QType;
import org.w3._1999.xhtml.SampType;
import org.w3._1999.xhtml.ScriptType;
import org.w3._1999.xhtml.SmallType;
import org.w3._1999.xhtml.SpanType;
import org.w3._1999.xhtml.StrongType;
import org.w3._1999.xhtml.SubType;
import org.w3._1999.xhtml.SupType;
import org.w3._1999.xhtml.TableType;
import org.w3._1999.xhtml.TtType;
import org.w3._1999.xhtml.UlType;
import org.w3._1999.xhtml.VarType;
import org.w3._1999.xhtml.XhtmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Button Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getP <em>P</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getH1 <em>H1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getH2 <em>H2</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getH3 <em>H3</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getH4 <em>H4</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getH5 <em>H5</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getH6 <em>H6</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getDiv <em>Div</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getUl <em>Ul</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getOl <em>Ol</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getDl <em>Dl</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getPre <em>Pre</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getHr <em>Hr</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getBlockquote <em>Blockquote</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getAddress <em>Address</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getTable <em>Table</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getBr <em>Br</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getSpan <em>Span</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getBdo <em>Bdo</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getMap <em>Map</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getObject <em>Object</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getImg <em>Img</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getTt <em>Tt</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getI <em>I</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getB <em>B</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getBig <em>Big</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getSmall <em>Small</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getEm <em>Em</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getStrong <em>Strong</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getDfn <em>Dfn</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getCode <em>Code</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getQ <em>Q</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getSamp <em>Samp</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getKbd <em>Kbd</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getVar <em>Var</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getCite <em>Cite</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getAbbr <em>Abbr</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getAcronym <em>Acronym</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getSub <em>Sub</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getSup <em>Sup</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getNoscript <em>Noscript</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getIns <em>Ins</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getDel <em>Del</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.ButtonContentImpl#getScript <em>Script</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ButtonContentImpl extends EObjectImpl implements ButtonContent {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ButtonContentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XhtmlPackage.eINSTANCE.getButtonContent();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, XhtmlPackage.BUTTON_CONTENT__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		return (FeatureMap)getMixed().<FeatureMap.Entry>list(XhtmlPackage.eINSTANCE.getButtonContent_Group());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PType> getP() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_P());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<H1Type> getH1() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_H1());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<H2Type> getH2() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_H2());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<H3Type> getH3() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_H3());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<H4Type> getH4() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_H4());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<H5Type> getH5() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_H5());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<H6Type> getH6() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_H6());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DivType> getDiv() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Div());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UlType> getUl() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Ul());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OlType> getOl() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Ol());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DlType> getDl() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Dl());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PreType> getPre() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Pre());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<HrType> getHr() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Hr());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BlockquoteType> getBlockquote() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Blockquote());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AddressType> getAddress() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Address());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TableType> getTable() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Table());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BrType> getBr() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Br());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SpanType> getSpan() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Span());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BdoType> getBdo() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Bdo());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MapType> getMap() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Map());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ObjectType> getObject() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Object());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ImgType> getImg() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Img());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TtType> getTt() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Tt());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IType> getI() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_I());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BType> getB() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_B());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BigType> getBig() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Big());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SmallType> getSmall() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Small());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EmType> getEm() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Em());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StrongType> getStrong() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Strong());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DfnType> getDfn() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Dfn());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CodeType> getCode() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Code());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<QType> getQ() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Q());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SampType> getSamp() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Samp());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<KbdType> getKbd() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Kbd());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<VarType> getVar() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Var());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CiteType> getCite() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Cite());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AbbrType> getAbbr() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Abbr());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AcronymType> getAcronym() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Acronym());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SubType> getSub() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Sub());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<SupType> getSup() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Sup());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NoscriptType> getNoscript() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Noscript());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InsType> getIns() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Ins());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DelType> getDel() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Del());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScriptType> getScript() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getButtonContent_Script());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case XhtmlPackage.BUTTON_CONTENT__MIXED:
				return ((InternalEList<?>)getMixed()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__P:
				return ((InternalEList<?>)getP()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__H1:
				return ((InternalEList<?>)getH1()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__H2:
				return ((InternalEList<?>)getH2()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__H3:
				return ((InternalEList<?>)getH3()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__H4:
				return ((InternalEList<?>)getH4()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__H5:
				return ((InternalEList<?>)getH5()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__H6:
				return ((InternalEList<?>)getH6()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__DIV:
				return ((InternalEList<?>)getDiv()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__UL:
				return ((InternalEList<?>)getUl()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__OL:
				return ((InternalEList<?>)getOl()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__DL:
				return ((InternalEList<?>)getDl()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__PRE:
				return ((InternalEList<?>)getPre()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__HR:
				return ((InternalEList<?>)getHr()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__BLOCKQUOTE:
				return ((InternalEList<?>)getBlockquote()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__ADDRESS:
				return ((InternalEList<?>)getAddress()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__TABLE:
				return ((InternalEList<?>)getTable()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__BR:
				return ((InternalEList<?>)getBr()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__SPAN:
				return ((InternalEList<?>)getSpan()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__BDO:
				return ((InternalEList<?>)getBdo()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__MAP:
				return ((InternalEList<?>)getMap()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__OBJECT:
				return ((InternalEList<?>)getObject()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__IMG:
				return ((InternalEList<?>)getImg()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__TT:
				return ((InternalEList<?>)getTt()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__I:
				return ((InternalEList<?>)getI()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__B:
				return ((InternalEList<?>)getB()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__BIG:
				return ((InternalEList<?>)getBig()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__SMALL:
				return ((InternalEList<?>)getSmall()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__EM:
				return ((InternalEList<?>)getEm()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__STRONG:
				return ((InternalEList<?>)getStrong()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__DFN:
				return ((InternalEList<?>)getDfn()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__CODE:
				return ((InternalEList<?>)getCode()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__Q:
				return ((InternalEList<?>)getQ()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__SAMP:
				return ((InternalEList<?>)getSamp()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__KBD:
				return ((InternalEList<?>)getKbd()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__VAR:
				return ((InternalEList<?>)getVar()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__CITE:
				return ((InternalEList<?>)getCite()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__ABBR:
				return ((InternalEList<?>)getAbbr()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__ACRONYM:
				return ((InternalEList<?>)getAcronym()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__SUB:
				return ((InternalEList<?>)getSub()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__SUP:
				return ((InternalEList<?>)getSup()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__NOSCRIPT:
				return ((InternalEList<?>)getNoscript()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__INS:
				return ((InternalEList<?>)getIns()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__DEL:
				return ((InternalEList<?>)getDel()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.BUTTON_CONTENT__SCRIPT:
				return ((InternalEList<?>)getScript()).basicRemove(otherEnd, msgs);
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
			case XhtmlPackage.BUTTON_CONTENT__MIXED:
				if (coreType) return getMixed();
				return ((FeatureMap.Internal)getMixed()).getWrapper();
			case XhtmlPackage.BUTTON_CONTENT__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case XhtmlPackage.BUTTON_CONTENT__P:
				return getP();
			case XhtmlPackage.BUTTON_CONTENT__H1:
				return getH1();
			case XhtmlPackage.BUTTON_CONTENT__H2:
				return getH2();
			case XhtmlPackage.BUTTON_CONTENT__H3:
				return getH3();
			case XhtmlPackage.BUTTON_CONTENT__H4:
				return getH4();
			case XhtmlPackage.BUTTON_CONTENT__H5:
				return getH5();
			case XhtmlPackage.BUTTON_CONTENT__H6:
				return getH6();
			case XhtmlPackage.BUTTON_CONTENT__DIV:
				return getDiv();
			case XhtmlPackage.BUTTON_CONTENT__UL:
				return getUl();
			case XhtmlPackage.BUTTON_CONTENT__OL:
				return getOl();
			case XhtmlPackage.BUTTON_CONTENT__DL:
				return getDl();
			case XhtmlPackage.BUTTON_CONTENT__PRE:
				return getPre();
			case XhtmlPackage.BUTTON_CONTENT__HR:
				return getHr();
			case XhtmlPackage.BUTTON_CONTENT__BLOCKQUOTE:
				return getBlockquote();
			case XhtmlPackage.BUTTON_CONTENT__ADDRESS:
				return getAddress();
			case XhtmlPackage.BUTTON_CONTENT__TABLE:
				return getTable();
			case XhtmlPackage.BUTTON_CONTENT__BR:
				return getBr();
			case XhtmlPackage.BUTTON_CONTENT__SPAN:
				return getSpan();
			case XhtmlPackage.BUTTON_CONTENT__BDO:
				return getBdo();
			case XhtmlPackage.BUTTON_CONTENT__MAP:
				return getMap();
			case XhtmlPackage.BUTTON_CONTENT__OBJECT:
				return getObject();
			case XhtmlPackage.BUTTON_CONTENT__IMG:
				return getImg();
			case XhtmlPackage.BUTTON_CONTENT__TT:
				return getTt();
			case XhtmlPackage.BUTTON_CONTENT__I:
				return getI();
			case XhtmlPackage.BUTTON_CONTENT__B:
				return getB();
			case XhtmlPackage.BUTTON_CONTENT__BIG:
				return getBig();
			case XhtmlPackage.BUTTON_CONTENT__SMALL:
				return getSmall();
			case XhtmlPackage.BUTTON_CONTENT__EM:
				return getEm();
			case XhtmlPackage.BUTTON_CONTENT__STRONG:
				return getStrong();
			case XhtmlPackage.BUTTON_CONTENT__DFN:
				return getDfn();
			case XhtmlPackage.BUTTON_CONTENT__CODE:
				return getCode();
			case XhtmlPackage.BUTTON_CONTENT__Q:
				return getQ();
			case XhtmlPackage.BUTTON_CONTENT__SAMP:
				return getSamp();
			case XhtmlPackage.BUTTON_CONTENT__KBD:
				return getKbd();
			case XhtmlPackage.BUTTON_CONTENT__VAR:
				return getVar();
			case XhtmlPackage.BUTTON_CONTENT__CITE:
				return getCite();
			case XhtmlPackage.BUTTON_CONTENT__ABBR:
				return getAbbr();
			case XhtmlPackage.BUTTON_CONTENT__ACRONYM:
				return getAcronym();
			case XhtmlPackage.BUTTON_CONTENT__SUB:
				return getSub();
			case XhtmlPackage.BUTTON_CONTENT__SUP:
				return getSup();
			case XhtmlPackage.BUTTON_CONTENT__NOSCRIPT:
				return getNoscript();
			case XhtmlPackage.BUTTON_CONTENT__INS:
				return getIns();
			case XhtmlPackage.BUTTON_CONTENT__DEL:
				return getDel();
			case XhtmlPackage.BUTTON_CONTENT__SCRIPT:
				return getScript();
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
			case XhtmlPackage.BUTTON_CONTENT__MIXED:
				((FeatureMap.Internal)getMixed()).set(newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__P:
				getP().clear();
				getP().addAll((Collection<? extends PType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__H1:
				getH1().clear();
				getH1().addAll((Collection<? extends H1Type>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__H2:
				getH2().clear();
				getH2().addAll((Collection<? extends H2Type>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__H3:
				getH3().clear();
				getH3().addAll((Collection<? extends H3Type>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__H4:
				getH4().clear();
				getH4().addAll((Collection<? extends H4Type>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__H5:
				getH5().clear();
				getH5().addAll((Collection<? extends H5Type>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__H6:
				getH6().clear();
				getH6().addAll((Collection<? extends H6Type>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__DIV:
				getDiv().clear();
				getDiv().addAll((Collection<? extends DivType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__UL:
				getUl().clear();
				getUl().addAll((Collection<? extends UlType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__OL:
				getOl().clear();
				getOl().addAll((Collection<? extends OlType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__DL:
				getDl().clear();
				getDl().addAll((Collection<? extends DlType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__PRE:
				getPre().clear();
				getPre().addAll((Collection<? extends PreType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__HR:
				getHr().clear();
				getHr().addAll((Collection<? extends HrType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__BLOCKQUOTE:
				getBlockquote().clear();
				getBlockquote().addAll((Collection<? extends BlockquoteType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__ADDRESS:
				getAddress().clear();
				getAddress().addAll((Collection<? extends AddressType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__TABLE:
				getTable().clear();
				getTable().addAll((Collection<? extends TableType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__BR:
				getBr().clear();
				getBr().addAll((Collection<? extends BrType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__SPAN:
				getSpan().clear();
				getSpan().addAll((Collection<? extends SpanType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__BDO:
				getBdo().clear();
				getBdo().addAll((Collection<? extends BdoType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__MAP:
				getMap().clear();
				getMap().addAll((Collection<? extends MapType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__OBJECT:
				getObject().clear();
				getObject().addAll((Collection<? extends ObjectType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__IMG:
				getImg().clear();
				getImg().addAll((Collection<? extends ImgType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__TT:
				getTt().clear();
				getTt().addAll((Collection<? extends TtType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__I:
				getI().clear();
				getI().addAll((Collection<? extends IType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__B:
				getB().clear();
				getB().addAll((Collection<? extends BType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__BIG:
				getBig().clear();
				getBig().addAll((Collection<? extends BigType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__SMALL:
				getSmall().clear();
				getSmall().addAll((Collection<? extends SmallType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__EM:
				getEm().clear();
				getEm().addAll((Collection<? extends EmType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__STRONG:
				getStrong().clear();
				getStrong().addAll((Collection<? extends StrongType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__DFN:
				getDfn().clear();
				getDfn().addAll((Collection<? extends DfnType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__CODE:
				getCode().clear();
				getCode().addAll((Collection<? extends CodeType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__Q:
				getQ().clear();
				getQ().addAll((Collection<? extends QType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__SAMP:
				getSamp().clear();
				getSamp().addAll((Collection<? extends SampType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__KBD:
				getKbd().clear();
				getKbd().addAll((Collection<? extends KbdType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__VAR:
				getVar().clear();
				getVar().addAll((Collection<? extends VarType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__CITE:
				getCite().clear();
				getCite().addAll((Collection<? extends CiteType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__ABBR:
				getAbbr().clear();
				getAbbr().addAll((Collection<? extends AbbrType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__ACRONYM:
				getAcronym().clear();
				getAcronym().addAll((Collection<? extends AcronymType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__SUB:
				getSub().clear();
				getSub().addAll((Collection<? extends SubType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__SUP:
				getSup().clear();
				getSup().addAll((Collection<? extends SupType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__NOSCRIPT:
				getNoscript().clear();
				getNoscript().addAll((Collection<? extends NoscriptType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__INS:
				getIns().clear();
				getIns().addAll((Collection<? extends InsType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__DEL:
				getDel().clear();
				getDel().addAll((Collection<? extends DelType>)newValue);
				return;
			case XhtmlPackage.BUTTON_CONTENT__SCRIPT:
				getScript().clear();
				getScript().addAll((Collection<? extends ScriptType>)newValue);
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
			case XhtmlPackage.BUTTON_CONTENT__MIXED:
				getMixed().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__GROUP:
				getGroup().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__P:
				getP().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__H1:
				getH1().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__H2:
				getH2().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__H3:
				getH3().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__H4:
				getH4().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__H5:
				getH5().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__H6:
				getH6().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__DIV:
				getDiv().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__UL:
				getUl().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__OL:
				getOl().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__DL:
				getDl().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__PRE:
				getPre().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__HR:
				getHr().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__BLOCKQUOTE:
				getBlockquote().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__ADDRESS:
				getAddress().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__TABLE:
				getTable().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__BR:
				getBr().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__SPAN:
				getSpan().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__BDO:
				getBdo().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__MAP:
				getMap().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__OBJECT:
				getObject().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__IMG:
				getImg().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__TT:
				getTt().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__I:
				getI().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__B:
				getB().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__BIG:
				getBig().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__SMALL:
				getSmall().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__EM:
				getEm().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__STRONG:
				getStrong().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__DFN:
				getDfn().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__CODE:
				getCode().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__Q:
				getQ().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__SAMP:
				getSamp().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__KBD:
				getKbd().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__VAR:
				getVar().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__CITE:
				getCite().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__ABBR:
				getAbbr().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__ACRONYM:
				getAcronym().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__SUB:
				getSub().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__SUP:
				getSup().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__NOSCRIPT:
				getNoscript().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__INS:
				getIns().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__DEL:
				getDel().clear();
				return;
			case XhtmlPackage.BUTTON_CONTENT__SCRIPT:
				getScript().clear();
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
			case XhtmlPackage.BUTTON_CONTENT__MIXED:
				return mixed != null && !mixed.isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__GROUP:
				return !getGroup().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__P:
				return !getP().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__H1:
				return !getH1().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__H2:
				return !getH2().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__H3:
				return !getH3().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__H4:
				return !getH4().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__H5:
				return !getH5().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__H6:
				return !getH6().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__DIV:
				return !getDiv().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__UL:
				return !getUl().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__OL:
				return !getOl().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__DL:
				return !getDl().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__PRE:
				return !getPre().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__HR:
				return !getHr().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__BLOCKQUOTE:
				return !getBlockquote().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__ADDRESS:
				return !getAddress().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__TABLE:
				return !getTable().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__BR:
				return !getBr().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__SPAN:
				return !getSpan().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__BDO:
				return !getBdo().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__MAP:
				return !getMap().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__OBJECT:
				return !getObject().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__IMG:
				return !getImg().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__TT:
				return !getTt().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__I:
				return !getI().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__B:
				return !getB().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__BIG:
				return !getBig().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__SMALL:
				return !getSmall().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__EM:
				return !getEm().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__STRONG:
				return !getStrong().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__DFN:
				return !getDfn().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__CODE:
				return !getCode().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__Q:
				return !getQ().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__SAMP:
				return !getSamp().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__KBD:
				return !getKbd().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__VAR:
				return !getVar().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__CITE:
				return !getCite().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__ABBR:
				return !getAbbr().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__ACRONYM:
				return !getAcronym().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__SUB:
				return !getSub().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__SUP:
				return !getSup().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__NOSCRIPT:
				return !getNoscript().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__INS:
				return !getIns().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__DEL:
				return !getDel().isEmpty();
			case XhtmlPackage.BUTTON_CONTENT__SCRIPT:
				return !getScript().isEmpty();
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

} //ButtonContentImpl
