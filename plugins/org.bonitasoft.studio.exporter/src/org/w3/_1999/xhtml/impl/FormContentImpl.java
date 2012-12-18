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
import org.w3._1999.xhtml.AddressType;
import org.w3._1999.xhtml.BlockquoteType;
import org.w3._1999.xhtml.DelType;
import org.w3._1999.xhtml.DivType;
import org.w3._1999.xhtml.DlType;
import org.w3._1999.xhtml.FieldsetType;
import org.w3._1999.xhtml.FormContent;
import org.w3._1999.xhtml.H1Type;
import org.w3._1999.xhtml.H2Type;
import org.w3._1999.xhtml.H3Type;
import org.w3._1999.xhtml.H4Type;
import org.w3._1999.xhtml.H5Type;
import org.w3._1999.xhtml.H6Type;
import org.w3._1999.xhtml.HrType;
import org.w3._1999.xhtml.InsType;
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
 * An implementation of the model object '<em><b>Form Content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.w3._1999.xhtml.impl.FormContentImpl#getGroup <em>Group</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.FormContentImpl#getP <em>P</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.FormContentImpl#getH1 <em>H1</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.FormContentImpl#getH2 <em>H2</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.FormContentImpl#getH3 <em>H3</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.FormContentImpl#getH4 <em>H4</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.FormContentImpl#getH5 <em>H5</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.FormContentImpl#getH6 <em>H6</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.FormContentImpl#getDiv <em>Div</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.FormContentImpl#getUl <em>Ul</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.FormContentImpl#getOl <em>Ol</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.FormContentImpl#getDl <em>Dl</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.FormContentImpl#getPre <em>Pre</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.FormContentImpl#getHr <em>Hr</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.FormContentImpl#getBlockquote <em>Blockquote</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.FormContentImpl#getAddress <em>Address</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.FormContentImpl#getFieldset <em>Fieldset</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.FormContentImpl#getTable <em>Table</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.FormContentImpl#getNoscript <em>Noscript</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.FormContentImpl#getIns <em>Ins</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.FormContentImpl#getDel <em>Del</em>}</li>
 *   <li>{@link org.w3._1999.xhtml.impl.FormContentImpl#getScript <em>Script</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FormContentImpl extends EObjectImpl implements FormContent {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FormContentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XhtmlPackage.eINSTANCE.getFormContent();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, XhtmlPackage.FORM_CONTENT__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PType> getP() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getFormContent_P());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<H1Type> getH1() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getFormContent_H1());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<H2Type> getH2() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getFormContent_H2());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<H3Type> getH3() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getFormContent_H3());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<H4Type> getH4() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getFormContent_H4());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<H5Type> getH5() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getFormContent_H5());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<H6Type> getH6() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getFormContent_H6());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DivType> getDiv() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getFormContent_Div());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UlType> getUl() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getFormContent_Ul());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OlType> getOl() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getFormContent_Ol());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DlType> getDl() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getFormContent_Dl());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PreType> getPre() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getFormContent_Pre());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<HrType> getHr() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getFormContent_Hr());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<BlockquoteType> getBlockquote() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getFormContent_Blockquote());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AddressType> getAddress() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getFormContent_Address());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<FieldsetType> getFieldset() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getFormContent_Fieldset());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TableType> getTable() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getFormContent_Table());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NoscriptType> getNoscript() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getFormContent_Noscript());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InsType> getIns() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getFormContent_Ins());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DelType> getDel() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getFormContent_Del());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScriptType> getScript() {
		return getGroup().list(XhtmlPackage.eINSTANCE.getFormContent_Script());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case XhtmlPackage.FORM_CONTENT__GROUP:
				return ((InternalEList<?>)getGroup()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.FORM_CONTENT__P:
				return ((InternalEList<?>)getP()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.FORM_CONTENT__H1:
				return ((InternalEList<?>)getH1()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.FORM_CONTENT__H2:
				return ((InternalEList<?>)getH2()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.FORM_CONTENT__H3:
				return ((InternalEList<?>)getH3()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.FORM_CONTENT__H4:
				return ((InternalEList<?>)getH4()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.FORM_CONTENT__H5:
				return ((InternalEList<?>)getH5()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.FORM_CONTENT__H6:
				return ((InternalEList<?>)getH6()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.FORM_CONTENT__DIV:
				return ((InternalEList<?>)getDiv()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.FORM_CONTENT__UL:
				return ((InternalEList<?>)getUl()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.FORM_CONTENT__OL:
				return ((InternalEList<?>)getOl()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.FORM_CONTENT__DL:
				return ((InternalEList<?>)getDl()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.FORM_CONTENT__PRE:
				return ((InternalEList<?>)getPre()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.FORM_CONTENT__HR:
				return ((InternalEList<?>)getHr()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.FORM_CONTENT__BLOCKQUOTE:
				return ((InternalEList<?>)getBlockquote()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.FORM_CONTENT__ADDRESS:
				return ((InternalEList<?>)getAddress()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.FORM_CONTENT__FIELDSET:
				return ((InternalEList<?>)getFieldset()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.FORM_CONTENT__TABLE:
				return ((InternalEList<?>)getTable()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.FORM_CONTENT__NOSCRIPT:
				return ((InternalEList<?>)getNoscript()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.FORM_CONTENT__INS:
				return ((InternalEList<?>)getIns()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.FORM_CONTENT__DEL:
				return ((InternalEList<?>)getDel()).basicRemove(otherEnd, msgs);
			case XhtmlPackage.FORM_CONTENT__SCRIPT:
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
			case XhtmlPackage.FORM_CONTENT__GROUP:
				if (coreType) return getGroup();
				return ((FeatureMap.Internal)getGroup()).getWrapper();
			case XhtmlPackage.FORM_CONTENT__P:
				return getP();
			case XhtmlPackage.FORM_CONTENT__H1:
				return getH1();
			case XhtmlPackage.FORM_CONTENT__H2:
				return getH2();
			case XhtmlPackage.FORM_CONTENT__H3:
				return getH3();
			case XhtmlPackage.FORM_CONTENT__H4:
				return getH4();
			case XhtmlPackage.FORM_CONTENT__H5:
				return getH5();
			case XhtmlPackage.FORM_CONTENT__H6:
				return getH6();
			case XhtmlPackage.FORM_CONTENT__DIV:
				return getDiv();
			case XhtmlPackage.FORM_CONTENT__UL:
				return getUl();
			case XhtmlPackage.FORM_CONTENT__OL:
				return getOl();
			case XhtmlPackage.FORM_CONTENT__DL:
				return getDl();
			case XhtmlPackage.FORM_CONTENT__PRE:
				return getPre();
			case XhtmlPackage.FORM_CONTENT__HR:
				return getHr();
			case XhtmlPackage.FORM_CONTENT__BLOCKQUOTE:
				return getBlockquote();
			case XhtmlPackage.FORM_CONTENT__ADDRESS:
				return getAddress();
			case XhtmlPackage.FORM_CONTENT__FIELDSET:
				return getFieldset();
			case XhtmlPackage.FORM_CONTENT__TABLE:
				return getTable();
			case XhtmlPackage.FORM_CONTENT__NOSCRIPT:
				return getNoscript();
			case XhtmlPackage.FORM_CONTENT__INS:
				return getIns();
			case XhtmlPackage.FORM_CONTENT__DEL:
				return getDel();
			case XhtmlPackage.FORM_CONTENT__SCRIPT:
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
			case XhtmlPackage.FORM_CONTENT__GROUP:
				((FeatureMap.Internal)getGroup()).set(newValue);
				return;
			case XhtmlPackage.FORM_CONTENT__P:
				getP().clear();
				getP().addAll((Collection<? extends PType>)newValue);
				return;
			case XhtmlPackage.FORM_CONTENT__H1:
				getH1().clear();
				getH1().addAll((Collection<? extends H1Type>)newValue);
				return;
			case XhtmlPackage.FORM_CONTENT__H2:
				getH2().clear();
				getH2().addAll((Collection<? extends H2Type>)newValue);
				return;
			case XhtmlPackage.FORM_CONTENT__H3:
				getH3().clear();
				getH3().addAll((Collection<? extends H3Type>)newValue);
				return;
			case XhtmlPackage.FORM_CONTENT__H4:
				getH4().clear();
				getH4().addAll((Collection<? extends H4Type>)newValue);
				return;
			case XhtmlPackage.FORM_CONTENT__H5:
				getH5().clear();
				getH5().addAll((Collection<? extends H5Type>)newValue);
				return;
			case XhtmlPackage.FORM_CONTENT__H6:
				getH6().clear();
				getH6().addAll((Collection<? extends H6Type>)newValue);
				return;
			case XhtmlPackage.FORM_CONTENT__DIV:
				getDiv().clear();
				getDiv().addAll((Collection<? extends DivType>)newValue);
				return;
			case XhtmlPackage.FORM_CONTENT__UL:
				getUl().clear();
				getUl().addAll((Collection<? extends UlType>)newValue);
				return;
			case XhtmlPackage.FORM_CONTENT__OL:
				getOl().clear();
				getOl().addAll((Collection<? extends OlType>)newValue);
				return;
			case XhtmlPackage.FORM_CONTENT__DL:
				getDl().clear();
				getDl().addAll((Collection<? extends DlType>)newValue);
				return;
			case XhtmlPackage.FORM_CONTENT__PRE:
				getPre().clear();
				getPre().addAll((Collection<? extends PreType>)newValue);
				return;
			case XhtmlPackage.FORM_CONTENT__HR:
				getHr().clear();
				getHr().addAll((Collection<? extends HrType>)newValue);
				return;
			case XhtmlPackage.FORM_CONTENT__BLOCKQUOTE:
				getBlockquote().clear();
				getBlockquote().addAll((Collection<? extends BlockquoteType>)newValue);
				return;
			case XhtmlPackage.FORM_CONTENT__ADDRESS:
				getAddress().clear();
				getAddress().addAll((Collection<? extends AddressType>)newValue);
				return;
			case XhtmlPackage.FORM_CONTENT__FIELDSET:
				getFieldset().clear();
				getFieldset().addAll((Collection<? extends FieldsetType>)newValue);
				return;
			case XhtmlPackage.FORM_CONTENT__TABLE:
				getTable().clear();
				getTable().addAll((Collection<? extends TableType>)newValue);
				return;
			case XhtmlPackage.FORM_CONTENT__NOSCRIPT:
				getNoscript().clear();
				getNoscript().addAll((Collection<? extends NoscriptType>)newValue);
				return;
			case XhtmlPackage.FORM_CONTENT__INS:
				getIns().clear();
				getIns().addAll((Collection<? extends InsType>)newValue);
				return;
			case XhtmlPackage.FORM_CONTENT__DEL:
				getDel().clear();
				getDel().addAll((Collection<? extends DelType>)newValue);
				return;
			case XhtmlPackage.FORM_CONTENT__SCRIPT:
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
			case XhtmlPackage.FORM_CONTENT__GROUP:
				getGroup().clear();
				return;
			case XhtmlPackage.FORM_CONTENT__P:
				getP().clear();
				return;
			case XhtmlPackage.FORM_CONTENT__H1:
				getH1().clear();
				return;
			case XhtmlPackage.FORM_CONTENT__H2:
				getH2().clear();
				return;
			case XhtmlPackage.FORM_CONTENT__H3:
				getH3().clear();
				return;
			case XhtmlPackage.FORM_CONTENT__H4:
				getH4().clear();
				return;
			case XhtmlPackage.FORM_CONTENT__H5:
				getH5().clear();
				return;
			case XhtmlPackage.FORM_CONTENT__H6:
				getH6().clear();
				return;
			case XhtmlPackage.FORM_CONTENT__DIV:
				getDiv().clear();
				return;
			case XhtmlPackage.FORM_CONTENT__UL:
				getUl().clear();
				return;
			case XhtmlPackage.FORM_CONTENT__OL:
				getOl().clear();
				return;
			case XhtmlPackage.FORM_CONTENT__DL:
				getDl().clear();
				return;
			case XhtmlPackage.FORM_CONTENT__PRE:
				getPre().clear();
				return;
			case XhtmlPackage.FORM_CONTENT__HR:
				getHr().clear();
				return;
			case XhtmlPackage.FORM_CONTENT__BLOCKQUOTE:
				getBlockquote().clear();
				return;
			case XhtmlPackage.FORM_CONTENT__ADDRESS:
				getAddress().clear();
				return;
			case XhtmlPackage.FORM_CONTENT__FIELDSET:
				getFieldset().clear();
				return;
			case XhtmlPackage.FORM_CONTENT__TABLE:
				getTable().clear();
				return;
			case XhtmlPackage.FORM_CONTENT__NOSCRIPT:
				getNoscript().clear();
				return;
			case XhtmlPackage.FORM_CONTENT__INS:
				getIns().clear();
				return;
			case XhtmlPackage.FORM_CONTENT__DEL:
				getDel().clear();
				return;
			case XhtmlPackage.FORM_CONTENT__SCRIPT:
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
			case XhtmlPackage.FORM_CONTENT__GROUP:
				return group != null && !group.isEmpty();
			case XhtmlPackage.FORM_CONTENT__P:
				return !getP().isEmpty();
			case XhtmlPackage.FORM_CONTENT__H1:
				return !getH1().isEmpty();
			case XhtmlPackage.FORM_CONTENT__H2:
				return !getH2().isEmpty();
			case XhtmlPackage.FORM_CONTENT__H3:
				return !getH3().isEmpty();
			case XhtmlPackage.FORM_CONTENT__H4:
				return !getH4().isEmpty();
			case XhtmlPackage.FORM_CONTENT__H5:
				return !getH5().isEmpty();
			case XhtmlPackage.FORM_CONTENT__H6:
				return !getH6().isEmpty();
			case XhtmlPackage.FORM_CONTENT__DIV:
				return !getDiv().isEmpty();
			case XhtmlPackage.FORM_CONTENT__UL:
				return !getUl().isEmpty();
			case XhtmlPackage.FORM_CONTENT__OL:
				return !getOl().isEmpty();
			case XhtmlPackage.FORM_CONTENT__DL:
				return !getDl().isEmpty();
			case XhtmlPackage.FORM_CONTENT__PRE:
				return !getPre().isEmpty();
			case XhtmlPackage.FORM_CONTENT__HR:
				return !getHr().isEmpty();
			case XhtmlPackage.FORM_CONTENT__BLOCKQUOTE:
				return !getBlockquote().isEmpty();
			case XhtmlPackage.FORM_CONTENT__ADDRESS:
				return !getAddress().isEmpty();
			case XhtmlPackage.FORM_CONTENT__FIELDSET:
				return !getFieldset().isEmpty();
			case XhtmlPackage.FORM_CONTENT__TABLE:
				return !getTable().isEmpty();
			case XhtmlPackage.FORM_CONTENT__NOSCRIPT:
				return !getNoscript().isEmpty();
			case XhtmlPackage.FORM_CONTENT__INS:
				return !getIns().isEmpty();
			case XhtmlPackage.FORM_CONTENT__DEL:
				return !getDel().isEmpty();
			case XhtmlPackage.FORM_CONTENT__SCRIPT:
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
		result.append(" (group: ");
		result.append(group);
		result.append(')');
		return result.toString();
	}

} //FormContentImpl
