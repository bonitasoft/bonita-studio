/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.w3._1999.xhtml.impl;

import java.math.BigInteger;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.w3._1999.xhtml.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XhtmlFactoryImpl extends EFactoryImpl implements XhtmlFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static XhtmlFactory init() {
		try {
			XhtmlFactory theXhtmlFactory = (XhtmlFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.w3.org/1999/xhtml"); 
			if (theXhtmlFactory != null) {
				return theXhtmlFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new XhtmlFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XhtmlFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case XhtmlPackage.ABBR_TYPE: return createAbbrType();
			case XhtmlPackage.ACONTENT: return createAContent();
			case XhtmlPackage.ACRONYM_TYPE: return createAcronymType();
			case XhtmlPackage.ADDRESS_TYPE: return createAddressType();
			case XhtmlPackage.AREA_TYPE: return createAreaType();
			case XhtmlPackage.ATYPE: return createAType();
			case XhtmlPackage.BASE_TYPE: return createBaseType();
			case XhtmlPackage.BDO_TYPE: return createBdoType();
			case XhtmlPackage.BIG_TYPE: return createBigType();
			case XhtmlPackage.BLOCK: return createBlock();
			case XhtmlPackage.BLOCKQUOTE_TYPE: return createBlockquoteType();
			case XhtmlPackage.BODY_TYPE: return createBodyType();
			case XhtmlPackage.BR_TYPE: return createBrType();
			case XhtmlPackage.BTYPE: return createBType();
			case XhtmlPackage.BUTTON_CONTENT: return createButtonContent();
			case XhtmlPackage.BUTTON_TYPE: return createButtonType();
			case XhtmlPackage.CAPTION_TYPE: return createCaptionType();
			case XhtmlPackage.CITE_TYPE: return createCiteType();
			case XhtmlPackage.CODE_TYPE: return createCodeType();
			case XhtmlPackage.COLGROUP_TYPE: return createColgroupType();
			case XhtmlPackage.COL_TYPE: return createColType();
			case XhtmlPackage.DD_TYPE: return createDdType();
			case XhtmlPackage.DEL_TYPE: return createDelType();
			case XhtmlPackage.DFN_TYPE: return createDfnType();
			case XhtmlPackage.DIV_TYPE: return createDivType();
			case XhtmlPackage.DL_TYPE: return createDlType();
			case XhtmlPackage.DOCUMENT_ROOT: return createDocumentRoot();
			case XhtmlPackage.DT_TYPE: return createDtType();
			case XhtmlPackage.EM_TYPE: return createEmType();
			case XhtmlPackage.FIELDSET_TYPE: return createFieldsetType();
			case XhtmlPackage.FLOW: return createFlow();
			case XhtmlPackage.FORM_CONTENT: return createFormContent();
			case XhtmlPackage.FORM_TYPE: return createFormType();
			case XhtmlPackage.H1_TYPE: return createH1Type();
			case XhtmlPackage.H2_TYPE: return createH2Type();
			case XhtmlPackage.H3_TYPE: return createH3Type();
			case XhtmlPackage.H4_TYPE: return createH4Type();
			case XhtmlPackage.H5_TYPE: return createH5Type();
			case XhtmlPackage.H6_TYPE: return createH6Type();
			case XhtmlPackage.HEAD_TYPE: return createHeadType();
			case XhtmlPackage.HR_TYPE: return createHrType();
			case XhtmlPackage.HTML: return createHtml();
			case XhtmlPackage.IMG_TYPE: return createImgType();
			case XhtmlPackage.INLINE: return createInline();
			case XhtmlPackage.INPUT_TYPE1: return createInputType1();
			case XhtmlPackage.INS_TYPE: return createInsType();
			case XhtmlPackage.ITYPE: return createIType();
			case XhtmlPackage.KBD_TYPE: return createKbdType();
			case XhtmlPackage.LABEL_TYPE: return createLabelType();
			case XhtmlPackage.LEGEND_TYPE: return createLegendType();
			case XhtmlPackage.LINK_TYPE: return createLinkType();
			case XhtmlPackage.LI_TYPE: return createLiType();
			case XhtmlPackage.MAP_TYPE: return createMapType();
			case XhtmlPackage.META_TYPE: return createMetaType();
			case XhtmlPackage.NOSCRIPT_TYPE: return createNoscriptType();
			case XhtmlPackage.OBJECT_TYPE: return createObjectType();
			case XhtmlPackage.OL_TYPE: return createOlType();
			case XhtmlPackage.OPTGROUP_TYPE: return createOptgroupType();
			case XhtmlPackage.OPTION_TYPE: return createOptionType();
			case XhtmlPackage.PARAM_TYPE: return createParamType();
			case XhtmlPackage.PRE_CONTENT: return createPreContent();
			case XhtmlPackage.PRE_TYPE: return createPreType();
			case XhtmlPackage.PTYPE: return createPType();
			case XhtmlPackage.QTYPE: return createQType();
			case XhtmlPackage.SAMP_TYPE: return createSampType();
			case XhtmlPackage.SCRIPT_TYPE: return createScriptType();
			case XhtmlPackage.SELECT_TYPE: return createSelectType();
			case XhtmlPackage.SMALL_TYPE: return createSmallType();
			case XhtmlPackage.SPAN_TYPE: return createSpanType();
			case XhtmlPackage.STRONG_TYPE: return createStrongType();
			case XhtmlPackage.STYLE_TYPE: return createStyleType();
			case XhtmlPackage.SUB_TYPE: return createSubType();
			case XhtmlPackage.SUP_TYPE: return createSupType();
			case XhtmlPackage.TABLE_TYPE: return createTableType();
			case XhtmlPackage.TBODY_TYPE: return createTbodyType();
			case XhtmlPackage.TD_TYPE: return createTdType();
			case XhtmlPackage.TEXTAREA_TYPE: return createTextareaType();
			case XhtmlPackage.TFOOT_TYPE: return createTfootType();
			case XhtmlPackage.THEAD_TYPE: return createTheadType();
			case XhtmlPackage.TH_TYPE: return createThType();
			case XhtmlPackage.TITLE_TYPE: return createTitleType();
			case XhtmlPackage.TR_TYPE: return createTrType();
			case XhtmlPackage.TT_TYPE: return createTtType();
			case XhtmlPackage.UL_TYPE: return createUlType();
			case XhtmlPackage.VAR_TYPE: return createVarType();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case XhtmlPackage.ALIGN_TYPE:
				return createAlignTypeFromString(eDataType, initialValue);
			case XhtmlPackage.CHECKED_TYPE:
				return createCheckedTypeFromString(eDataType, initialValue);
			case XhtmlPackage.DECLARE_TYPE:
				return createDeclareTypeFromString(eDataType, initialValue);
			case XhtmlPackage.DEFER_TYPE:
				return createDeferTypeFromString(eDataType, initialValue);
			case XhtmlPackage.DIR_TYPE:
				return createDirTypeFromString(eDataType, initialValue);
			case XhtmlPackage.DIR_TYPE1:
				return createDirType1FromString(eDataType, initialValue);
			case XhtmlPackage.DISABLED_TYPE:
				return createDisabledTypeFromString(eDataType, initialValue);
			case XhtmlPackage.DISABLED_TYPE1:
				return createDisabledType1FromString(eDataType, initialValue);
			case XhtmlPackage.DISABLED_TYPE2:
				return createDisabledType2FromString(eDataType, initialValue);
			case XhtmlPackage.DISABLED_TYPE3:
				return createDisabledType3FromString(eDataType, initialValue);
			case XhtmlPackage.DISABLED_TYPE4:
				return createDisabledType4FromString(eDataType, initialValue);
			case XhtmlPackage.DISABLED_TYPE5:
				return createDisabledType5FromString(eDataType, initialValue);
			case XhtmlPackage.INPUT_TYPE:
				return createInputTypeFromString(eDataType, initialValue);
			case XhtmlPackage.ISMAP_TYPE:
				return createIsmapTypeFromString(eDataType, initialValue);
			case XhtmlPackage.METHOD_TYPE:
				return createMethodTypeFromString(eDataType, initialValue);
			case XhtmlPackage.MULTIPLE_TYPE:
				return createMultipleTypeFromString(eDataType, initialValue);
			case XhtmlPackage.NOHREF_TYPE:
				return createNohrefTypeFromString(eDataType, initialValue);
			case XhtmlPackage.READONLY_TYPE:
				return createReadonlyTypeFromString(eDataType, initialValue);
			case XhtmlPackage.READONLY_TYPE1:
				return createReadonlyType1FromString(eDataType, initialValue);
			case XhtmlPackage.SCOPE:
				return createScopeFromString(eDataType, initialValue);
			case XhtmlPackage.SELECTED_TYPE:
				return createSelectedTypeFromString(eDataType, initialValue);
			case XhtmlPackage.SHAPE:
				return createShapeFromString(eDataType, initialValue);
			case XhtmlPackage.TFRAME:
				return createTFrameFromString(eDataType, initialValue);
			case XhtmlPackage.TRULES:
				return createTRulesFromString(eDataType, initialValue);
			case XhtmlPackage.TYPE_TYPE:
				return createTypeTypeFromString(eDataType, initialValue);
			case XhtmlPackage.VALIGN_TYPE:
				return createValignTypeFromString(eDataType, initialValue);
			case XhtmlPackage.VALUETYPE_TYPE:
				return createValuetypeTypeFromString(eDataType, initialValue);
			case XhtmlPackage.ALIGN_TYPE_OBJECT:
				return createAlignTypeObjectFromString(eDataType, initialValue);
			case XhtmlPackage.CHARACTER:
				return createCharacterFromString(eDataType, initialValue);
			case XhtmlPackage.CHARSET:
				return createCharsetFromString(eDataType, initialValue);
			case XhtmlPackage.CHARSETS:
				return createCharsetsFromString(eDataType, initialValue);
			case XhtmlPackage.CHECKED_TYPE_OBJECT:
				return createCheckedTypeObjectFromString(eDataType, initialValue);
			case XhtmlPackage.CONTENT_TYPE:
				return createContentTypeFromString(eDataType, initialValue);
			case XhtmlPackage.CONTENT_TYPES:
				return createContentTypesFromString(eDataType, initialValue);
			case XhtmlPackage.COORDS:
				return createCoordsFromString(eDataType, initialValue);
			case XhtmlPackage.DATETIME:
				return createDatetimeFromString(eDataType, initialValue);
			case XhtmlPackage.DECLARE_TYPE_OBJECT:
				return createDeclareTypeObjectFromString(eDataType, initialValue);
			case XhtmlPackage.DEFER_TYPE_OBJECT:
				return createDeferTypeObjectFromString(eDataType, initialValue);
			case XhtmlPackage.DIR_TYPE_OBJECT:
				return createDirTypeObjectFromString(eDataType, initialValue);
			case XhtmlPackage.DIR_TYPE_OBJECT1:
				return createDirTypeObject1FromString(eDataType, initialValue);
			case XhtmlPackage.DISABLED_TYPE_OBJECT:
				return createDisabledTypeObjectFromString(eDataType, initialValue);
			case XhtmlPackage.DISABLED_TYPE_OBJECT1:
				return createDisabledTypeObject1FromString(eDataType, initialValue);
			case XhtmlPackage.DISABLED_TYPE_OBJECT2:
				return createDisabledTypeObject2FromString(eDataType, initialValue);
			case XhtmlPackage.DISABLED_TYPE_OBJECT3:
				return createDisabledTypeObject3FromString(eDataType, initialValue);
			case XhtmlPackage.DISABLED_TYPE_OBJECT4:
				return createDisabledTypeObject4FromString(eDataType, initialValue);
			case XhtmlPackage.DISABLED_TYPE_OBJECT5:
				return createDisabledTypeObject5FromString(eDataType, initialValue);
			case XhtmlPackage.INPUT_TYPE_OBJECT:
				return createInputTypeObjectFromString(eDataType, initialValue);
			case XhtmlPackage.ISMAP_TYPE_OBJECT:
				return createIsmapTypeObjectFromString(eDataType, initialValue);
			case XhtmlPackage.LANGUAGE_CODE:
				return createLanguageCodeFromString(eDataType, initialValue);
			case XhtmlPackage.LENGTH:
				return createLengthFromString(eDataType, initialValue);
			case XhtmlPackage.LINK_TYPES:
				return createLinkTypesFromString(eDataType, initialValue);
			case XhtmlPackage.MEDIA_DESC:
				return createMediaDescFromString(eDataType, initialValue);
			case XhtmlPackage.METHOD_TYPE_OBJECT:
				return createMethodTypeObjectFromString(eDataType, initialValue);
			case XhtmlPackage.MULTI_LENGTH:
				return createMultiLengthFromString(eDataType, initialValue);
			case XhtmlPackage.MULTIPLE_TYPE_OBJECT:
				return createMultipleTypeObjectFromString(eDataType, initialValue);
			case XhtmlPackage.NOHREF_TYPE_OBJECT:
				return createNohrefTypeObjectFromString(eDataType, initialValue);
			case XhtmlPackage.NUMBER:
				return createNumberFromString(eDataType, initialValue);
			case XhtmlPackage.PIXELS:
				return createPixelsFromString(eDataType, initialValue);
			case XhtmlPackage.READONLY_TYPE_OBJECT:
				return createReadonlyTypeObjectFromString(eDataType, initialValue);
			case XhtmlPackage.READONLY_TYPE_OBJECT1:
				return createReadonlyTypeObject1FromString(eDataType, initialValue);
			case XhtmlPackage.SCOPE_OBJECT:
				return createScopeObjectFromString(eDataType, initialValue);
			case XhtmlPackage.SCRIPT:
				return createScriptFromString(eDataType, initialValue);
			case XhtmlPackage.SELECTED_TYPE_OBJECT:
				return createSelectedTypeObjectFromString(eDataType, initialValue);
			case XhtmlPackage.SHAPE_OBJECT:
				return createShapeObjectFromString(eDataType, initialValue);
			case XhtmlPackage.STYLE_SHEET:
				return createStyleSheetFromString(eDataType, initialValue);
			case XhtmlPackage.TABINDEX_NUMBER:
				return createTabindexNumberFromString(eDataType, initialValue);
			case XhtmlPackage.TEXT:
				return createTextFromString(eDataType, initialValue);
			case XhtmlPackage.TFRAME_OBJECT:
				return createTFrameObjectFromString(eDataType, initialValue);
			case XhtmlPackage.TRULES_OBJECT:
				return createTRulesObjectFromString(eDataType, initialValue);
			case XhtmlPackage.TYPE_TYPE_OBJECT:
				return createTypeTypeObjectFromString(eDataType, initialValue);
			case XhtmlPackage.URI:
				return createURIFromString(eDataType, initialValue);
			case XhtmlPackage.URI_LIST:
				return createUriListFromString(eDataType, initialValue);
			case XhtmlPackage.VALIGN_TYPE_OBJECT:
				return createValignTypeObjectFromString(eDataType, initialValue);
			case XhtmlPackage.VALUETYPE_TYPE_OBJECT:
				return createValuetypeTypeObjectFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case XhtmlPackage.ALIGN_TYPE:
				return convertAlignTypeToString(eDataType, instanceValue);
			case XhtmlPackage.CHECKED_TYPE:
				return convertCheckedTypeToString(eDataType, instanceValue);
			case XhtmlPackage.DECLARE_TYPE:
				return convertDeclareTypeToString(eDataType, instanceValue);
			case XhtmlPackage.DEFER_TYPE:
				return convertDeferTypeToString(eDataType, instanceValue);
			case XhtmlPackage.DIR_TYPE:
				return convertDirTypeToString(eDataType, instanceValue);
			case XhtmlPackage.DIR_TYPE1:
				return convertDirType1ToString(eDataType, instanceValue);
			case XhtmlPackage.DISABLED_TYPE:
				return convertDisabledTypeToString(eDataType, instanceValue);
			case XhtmlPackage.DISABLED_TYPE1:
				return convertDisabledType1ToString(eDataType, instanceValue);
			case XhtmlPackage.DISABLED_TYPE2:
				return convertDisabledType2ToString(eDataType, instanceValue);
			case XhtmlPackage.DISABLED_TYPE3:
				return convertDisabledType3ToString(eDataType, instanceValue);
			case XhtmlPackage.DISABLED_TYPE4:
				return convertDisabledType4ToString(eDataType, instanceValue);
			case XhtmlPackage.DISABLED_TYPE5:
				return convertDisabledType5ToString(eDataType, instanceValue);
			case XhtmlPackage.INPUT_TYPE:
				return convertInputTypeToString(eDataType, instanceValue);
			case XhtmlPackage.ISMAP_TYPE:
				return convertIsmapTypeToString(eDataType, instanceValue);
			case XhtmlPackage.METHOD_TYPE:
				return convertMethodTypeToString(eDataType, instanceValue);
			case XhtmlPackage.MULTIPLE_TYPE:
				return convertMultipleTypeToString(eDataType, instanceValue);
			case XhtmlPackage.NOHREF_TYPE:
				return convertNohrefTypeToString(eDataType, instanceValue);
			case XhtmlPackage.READONLY_TYPE:
				return convertReadonlyTypeToString(eDataType, instanceValue);
			case XhtmlPackage.READONLY_TYPE1:
				return convertReadonlyType1ToString(eDataType, instanceValue);
			case XhtmlPackage.SCOPE:
				return convertScopeToString(eDataType, instanceValue);
			case XhtmlPackage.SELECTED_TYPE:
				return convertSelectedTypeToString(eDataType, instanceValue);
			case XhtmlPackage.SHAPE:
				return convertShapeToString(eDataType, instanceValue);
			case XhtmlPackage.TFRAME:
				return convertTFrameToString(eDataType, instanceValue);
			case XhtmlPackage.TRULES:
				return convertTRulesToString(eDataType, instanceValue);
			case XhtmlPackage.TYPE_TYPE:
				return convertTypeTypeToString(eDataType, instanceValue);
			case XhtmlPackage.VALIGN_TYPE:
				return convertValignTypeToString(eDataType, instanceValue);
			case XhtmlPackage.VALUETYPE_TYPE:
				return convertValuetypeTypeToString(eDataType, instanceValue);
			case XhtmlPackage.ALIGN_TYPE_OBJECT:
				return convertAlignTypeObjectToString(eDataType, instanceValue);
			case XhtmlPackage.CHARACTER:
				return convertCharacterToString(eDataType, instanceValue);
			case XhtmlPackage.CHARSET:
				return convertCharsetToString(eDataType, instanceValue);
			case XhtmlPackage.CHARSETS:
				return convertCharsetsToString(eDataType, instanceValue);
			case XhtmlPackage.CHECKED_TYPE_OBJECT:
				return convertCheckedTypeObjectToString(eDataType, instanceValue);
			case XhtmlPackage.CONTENT_TYPE:
				return convertContentTypeToString(eDataType, instanceValue);
			case XhtmlPackage.CONTENT_TYPES:
				return convertContentTypesToString(eDataType, instanceValue);
			case XhtmlPackage.COORDS:
				return convertCoordsToString(eDataType, instanceValue);
			case XhtmlPackage.DATETIME:
				return convertDatetimeToString(eDataType, instanceValue);
			case XhtmlPackage.DECLARE_TYPE_OBJECT:
				return convertDeclareTypeObjectToString(eDataType, instanceValue);
			case XhtmlPackage.DEFER_TYPE_OBJECT:
				return convertDeferTypeObjectToString(eDataType, instanceValue);
			case XhtmlPackage.DIR_TYPE_OBJECT:
				return convertDirTypeObjectToString(eDataType, instanceValue);
			case XhtmlPackage.DIR_TYPE_OBJECT1:
				return convertDirTypeObject1ToString(eDataType, instanceValue);
			case XhtmlPackage.DISABLED_TYPE_OBJECT:
				return convertDisabledTypeObjectToString(eDataType, instanceValue);
			case XhtmlPackage.DISABLED_TYPE_OBJECT1:
				return convertDisabledTypeObject1ToString(eDataType, instanceValue);
			case XhtmlPackage.DISABLED_TYPE_OBJECT2:
				return convertDisabledTypeObject2ToString(eDataType, instanceValue);
			case XhtmlPackage.DISABLED_TYPE_OBJECT3:
				return convertDisabledTypeObject3ToString(eDataType, instanceValue);
			case XhtmlPackage.DISABLED_TYPE_OBJECT4:
				return convertDisabledTypeObject4ToString(eDataType, instanceValue);
			case XhtmlPackage.DISABLED_TYPE_OBJECT5:
				return convertDisabledTypeObject5ToString(eDataType, instanceValue);
			case XhtmlPackage.INPUT_TYPE_OBJECT:
				return convertInputTypeObjectToString(eDataType, instanceValue);
			case XhtmlPackage.ISMAP_TYPE_OBJECT:
				return convertIsmapTypeObjectToString(eDataType, instanceValue);
			case XhtmlPackage.LANGUAGE_CODE:
				return convertLanguageCodeToString(eDataType, instanceValue);
			case XhtmlPackage.LENGTH:
				return convertLengthToString(eDataType, instanceValue);
			case XhtmlPackage.LINK_TYPES:
				return convertLinkTypesToString(eDataType, instanceValue);
			case XhtmlPackage.MEDIA_DESC:
				return convertMediaDescToString(eDataType, instanceValue);
			case XhtmlPackage.METHOD_TYPE_OBJECT:
				return convertMethodTypeObjectToString(eDataType, instanceValue);
			case XhtmlPackage.MULTI_LENGTH:
				return convertMultiLengthToString(eDataType, instanceValue);
			case XhtmlPackage.MULTIPLE_TYPE_OBJECT:
				return convertMultipleTypeObjectToString(eDataType, instanceValue);
			case XhtmlPackage.NOHREF_TYPE_OBJECT:
				return convertNohrefTypeObjectToString(eDataType, instanceValue);
			case XhtmlPackage.NUMBER:
				return convertNumberToString(eDataType, instanceValue);
			case XhtmlPackage.PIXELS:
				return convertPixelsToString(eDataType, instanceValue);
			case XhtmlPackage.READONLY_TYPE_OBJECT:
				return convertReadonlyTypeObjectToString(eDataType, instanceValue);
			case XhtmlPackage.READONLY_TYPE_OBJECT1:
				return convertReadonlyTypeObject1ToString(eDataType, instanceValue);
			case XhtmlPackage.SCOPE_OBJECT:
				return convertScopeObjectToString(eDataType, instanceValue);
			case XhtmlPackage.SCRIPT:
				return convertScriptToString(eDataType, instanceValue);
			case XhtmlPackage.SELECTED_TYPE_OBJECT:
				return convertSelectedTypeObjectToString(eDataType, instanceValue);
			case XhtmlPackage.SHAPE_OBJECT:
				return convertShapeObjectToString(eDataType, instanceValue);
			case XhtmlPackage.STYLE_SHEET:
				return convertStyleSheetToString(eDataType, instanceValue);
			case XhtmlPackage.TABINDEX_NUMBER:
				return convertTabindexNumberToString(eDataType, instanceValue);
			case XhtmlPackage.TEXT:
				return convertTextToString(eDataType, instanceValue);
			case XhtmlPackage.TFRAME_OBJECT:
				return convertTFrameObjectToString(eDataType, instanceValue);
			case XhtmlPackage.TRULES_OBJECT:
				return convertTRulesObjectToString(eDataType, instanceValue);
			case XhtmlPackage.TYPE_TYPE_OBJECT:
				return convertTypeTypeObjectToString(eDataType, instanceValue);
			case XhtmlPackage.URI:
				return convertURIToString(eDataType, instanceValue);
			case XhtmlPackage.URI_LIST:
				return convertUriListToString(eDataType, instanceValue);
			case XhtmlPackage.VALIGN_TYPE_OBJECT:
				return convertValignTypeObjectToString(eDataType, instanceValue);
			case XhtmlPackage.VALUETYPE_TYPE_OBJECT:
				return convertValuetypeTypeObjectToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AbbrType createAbbrType() {
		AbbrTypeImpl abbrType = new AbbrTypeImpl();
		return abbrType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AContent createAContent() {
		AContentImpl aContent = new AContentImpl();
		return aContent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AcronymType createAcronymType() {
		AcronymTypeImpl acronymType = new AcronymTypeImpl();
		return acronymType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AddressType createAddressType() {
		AddressTypeImpl addressType = new AddressTypeImpl();
		return addressType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AreaType createAreaType() {
		AreaTypeImpl areaType = new AreaTypeImpl();
		return areaType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AType createAType() {
		ATypeImpl aType = new ATypeImpl();
		return aType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BaseType createBaseType() {
		BaseTypeImpl baseType = new BaseTypeImpl();
		return baseType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BdoType createBdoType() {
		BdoTypeImpl bdoType = new BdoTypeImpl();
		return bdoType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigType createBigType() {
		BigTypeImpl bigType = new BigTypeImpl();
		return bigType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Block createBlock() {
		BlockImpl block = new BlockImpl();
		return block;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BlockquoteType createBlockquoteType() {
		BlockquoteTypeImpl blockquoteType = new BlockquoteTypeImpl();
		return blockquoteType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BodyType createBodyType() {
		BodyTypeImpl bodyType = new BodyTypeImpl();
		return bodyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BrType createBrType() {
		BrTypeImpl brType = new BrTypeImpl();
		return brType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BType createBType() {
		BTypeImpl bType = new BTypeImpl();
		return bType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ButtonContent createButtonContent() {
		ButtonContentImpl buttonContent = new ButtonContentImpl();
		return buttonContent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ButtonType createButtonType() {
		ButtonTypeImpl buttonType = new ButtonTypeImpl();
		return buttonType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CaptionType createCaptionType() {
		CaptionTypeImpl captionType = new CaptionTypeImpl();
		return captionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CiteType createCiteType() {
		CiteTypeImpl citeType = new CiteTypeImpl();
		return citeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CodeType createCodeType() {
		CodeTypeImpl codeType = new CodeTypeImpl();
		return codeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ColgroupType createColgroupType() {
		ColgroupTypeImpl colgroupType = new ColgroupTypeImpl();
		return colgroupType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ColType createColType() {
		ColTypeImpl colType = new ColTypeImpl();
		return colType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DdType createDdType() {
		DdTypeImpl ddType = new DdTypeImpl();
		return ddType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DelType createDelType() {
		DelTypeImpl delType = new DelTypeImpl();
		return delType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DfnType createDfnType() {
		DfnTypeImpl dfnType = new DfnTypeImpl();
		return dfnType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DivType createDivType() {
		DivTypeImpl divType = new DivTypeImpl();
		return divType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DlType createDlType() {
		DlTypeImpl dlType = new DlTypeImpl();
		return dlType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DtType createDtType() {
		DtTypeImpl dtType = new DtTypeImpl();
		return dtType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EmType createEmType() {
		EmTypeImpl emType = new EmTypeImpl();
		return emType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldsetType createFieldsetType() {
		FieldsetTypeImpl fieldsetType = new FieldsetTypeImpl();
		return fieldsetType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Flow createFlow() {
		FlowImpl flow = new FlowImpl();
		return flow;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormContent createFormContent() {
		FormContentImpl formContent = new FormContentImpl();
		return formContent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FormType createFormType() {
		FormTypeImpl formType = new FormTypeImpl();
		return formType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public H1Type createH1Type() {
		H1TypeImpl h1Type = new H1TypeImpl();
		return h1Type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public H2Type createH2Type() {
		H2TypeImpl h2Type = new H2TypeImpl();
		return h2Type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public H3Type createH3Type() {
		H3TypeImpl h3Type = new H3TypeImpl();
		return h3Type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public H4Type createH4Type() {
		H4TypeImpl h4Type = new H4TypeImpl();
		return h4Type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public H5Type createH5Type() {
		H5TypeImpl h5Type = new H5TypeImpl();
		return h5Type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public H6Type createH6Type() {
		H6TypeImpl h6Type = new H6TypeImpl();
		return h6Type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HeadType createHeadType() {
		HeadTypeImpl headType = new HeadTypeImpl();
		return headType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HrType createHrType() {
		HrTypeImpl hrType = new HrTypeImpl();
		return hrType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Html createHtml() {
		HtmlImpl html = new HtmlImpl();
		return html;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImgType createImgType() {
		ImgTypeImpl imgType = new ImgTypeImpl();
		return imgType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Inline createInline() {
		InlineImpl inline = new InlineImpl();
		return inline;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputType1 createInputType1() {
		InputType1Impl inputType1 = new InputType1Impl();
		return inputType1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InsType createInsType() {
		InsTypeImpl insType = new InsTypeImpl();
		return insType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IType createIType() {
		ITypeImpl iType = new ITypeImpl();
		return iType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KbdType createKbdType() {
		KbdTypeImpl kbdType = new KbdTypeImpl();
		return kbdType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LabelType createLabelType() {
		LabelTypeImpl labelType = new LabelTypeImpl();
		return labelType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LegendType createLegendType() {
		LegendTypeImpl legendType = new LegendTypeImpl();
		return legendType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LinkType createLinkType() {
		LinkTypeImpl linkType = new LinkTypeImpl();
		return linkType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LiType createLiType() {
		LiTypeImpl liType = new LiTypeImpl();
		return liType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MapType createMapType() {
		MapTypeImpl mapType = new MapTypeImpl();
		return mapType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MetaType createMetaType() {
		MetaTypeImpl metaType = new MetaTypeImpl();
		return metaType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NoscriptType createNoscriptType() {
		NoscriptTypeImpl noscriptType = new NoscriptTypeImpl();
		return noscriptType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ObjectType createObjectType() {
		ObjectTypeImpl objectType = new ObjectTypeImpl();
		return objectType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OlType createOlType() {
		OlTypeImpl olType = new OlTypeImpl();
		return olType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OptgroupType createOptgroupType() {
		OptgroupTypeImpl optgroupType = new OptgroupTypeImpl();
		return optgroupType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OptionType createOptionType() {
		OptionTypeImpl optionType = new OptionTypeImpl();
		return optionType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ParamType createParamType() {
		ParamTypeImpl paramType = new ParamTypeImpl();
		return paramType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PreContent createPreContent() {
		PreContentImpl preContent = new PreContentImpl();
		return preContent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PreType createPreType() {
		PreTypeImpl preType = new PreTypeImpl();
		return preType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PType createPType() {
		PTypeImpl pType = new PTypeImpl();
		return pType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QType createQType() {
		QTypeImpl qType = new QTypeImpl();
		return qType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SampType createSampType() {
		SampTypeImpl sampType = new SampTypeImpl();
		return sampType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScriptType createScriptType() {
		ScriptTypeImpl scriptType = new ScriptTypeImpl();
		return scriptType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SelectType createSelectType() {
		SelectTypeImpl selectType = new SelectTypeImpl();
		return selectType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SmallType createSmallType() {
		SmallTypeImpl smallType = new SmallTypeImpl();
		return smallType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpanType createSpanType() {
		SpanTypeImpl spanType = new SpanTypeImpl();
		return spanType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StrongType createStrongType() {
		StrongTypeImpl strongType = new StrongTypeImpl();
		return strongType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StyleType createStyleType() {
		StyleTypeImpl styleType = new StyleTypeImpl();
		return styleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubType createSubType() {
		SubTypeImpl subType = new SubTypeImpl();
		return subType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SupType createSupType() {
		SupTypeImpl supType = new SupTypeImpl();
		return supType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TableType createTableType() {
		TableTypeImpl tableType = new TableTypeImpl();
		return tableType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TbodyType createTbodyType() {
		TbodyTypeImpl tbodyType = new TbodyTypeImpl();
		return tbodyType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TdType createTdType() {
		TdTypeImpl tdType = new TdTypeImpl();
		return tdType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TextareaType createTextareaType() {
		TextareaTypeImpl textareaType = new TextareaTypeImpl();
		return textareaType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TfootType createTfootType() {
		TfootTypeImpl tfootType = new TfootTypeImpl();
		return tfootType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TheadType createTheadType() {
		TheadTypeImpl theadType = new TheadTypeImpl();
		return theadType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ThType createThType() {
		ThTypeImpl thType = new ThTypeImpl();
		return thType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TitleType createTitleType() {
		TitleTypeImpl titleType = new TitleTypeImpl();
		return titleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TrType createTrType() {
		TrTypeImpl trType = new TrTypeImpl();
		return trType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TtType createTtType() {
		TtTypeImpl ttType = new TtTypeImpl();
		return ttType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UlType createUlType() {
		UlTypeImpl ulType = new UlTypeImpl();
		return ulType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public VarType createVarType() {
		VarTypeImpl varType = new VarTypeImpl();
		return varType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlignType createAlignTypeFromString(EDataType eDataType, String initialValue) {
		AlignType result = AlignType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAlignTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CheckedType createCheckedTypeFromString(EDataType eDataType, String initialValue) {
		CheckedType result = CheckedType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCheckedTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeclareType createDeclareTypeFromString(EDataType eDataType, String initialValue) {
		DeclareType result = DeclareType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDeclareTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeferType createDeferTypeFromString(EDataType eDataType, String initialValue) {
		DeferType result = DeferType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDeferTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DirType createDirTypeFromString(EDataType eDataType, String initialValue) {
		DirType result = DirType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDirTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DirType1 createDirType1FromString(EDataType eDataType, String initialValue) {
		DirType1 result = DirType1.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDirType1ToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DisabledType createDisabledTypeFromString(EDataType eDataType, String initialValue) {
		DisabledType result = DisabledType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDisabledTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DisabledType1 createDisabledType1FromString(EDataType eDataType, String initialValue) {
		DisabledType1 result = DisabledType1.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDisabledType1ToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DisabledType2 createDisabledType2FromString(EDataType eDataType, String initialValue) {
		DisabledType2 result = DisabledType2.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDisabledType2ToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DisabledType3 createDisabledType3FromString(EDataType eDataType, String initialValue) {
		DisabledType3 result = DisabledType3.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDisabledType3ToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DisabledType4 createDisabledType4FromString(EDataType eDataType, String initialValue) {
		DisabledType4 result = DisabledType4.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDisabledType4ToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DisabledType5 createDisabledType5FromString(EDataType eDataType, String initialValue) {
		DisabledType5 result = DisabledType5.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDisabledType5ToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputType createInputTypeFromString(EDataType eDataType, String initialValue) {
		InputType result = InputType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertInputTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IsmapType createIsmapTypeFromString(EDataType eDataType, String initialValue) {
		IsmapType result = IsmapType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIsmapTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodType createMethodTypeFromString(EDataType eDataType, String initialValue) {
		MethodType result = MethodType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMethodTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultipleType createMultipleTypeFromString(EDataType eDataType, String initialValue) {
		MultipleType result = MultipleType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMultipleTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NohrefType createNohrefTypeFromString(EDataType eDataType, String initialValue) {
		NohrefType result = NohrefType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNohrefTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReadonlyType createReadonlyTypeFromString(EDataType eDataType, String initialValue) {
		ReadonlyType result = ReadonlyType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertReadonlyTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReadonlyType1 createReadonlyType1FromString(EDataType eDataType, String initialValue) {
		ReadonlyType1 result = ReadonlyType1.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertReadonlyType1ToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Scope createScopeFromString(EDataType eDataType, String initialValue) {
		Scope result = Scope.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertScopeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SelectedType createSelectedTypeFromString(EDataType eDataType, String initialValue) {
		SelectedType result = SelectedType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSelectedTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Shape createShapeFromString(EDataType eDataType, String initialValue) {
		Shape result = Shape.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertShapeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFrame createTFrameFromString(EDataType eDataType, String initialValue) {
		TFrame result = TFrame.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTFrameToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TRules createTRulesFromString(EDataType eDataType, String initialValue) {
		TRules result = TRules.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTRulesToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeType createTypeTypeFromString(EDataType eDataType, String initialValue) {
		TypeType result = TypeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValignType createValignTypeFromString(EDataType eDataType, String initialValue) {
		ValignType result = ValignType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertValignTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValuetypeType createValuetypeTypeFromString(EDataType eDataType, String initialValue) {
		ValuetypeType result = ValuetypeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertValuetypeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AlignType createAlignTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createAlignTypeFromString(XhtmlPackage.eINSTANCE.getAlignType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAlignTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertAlignTypeToString(XhtmlPackage.eINSTANCE.getAlignType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createCharacterFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCharacterToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createCharsetFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCharsetToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createCharsetsFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCharsetsToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CheckedType createCheckedTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createCheckedTypeFromString(XhtmlPackage.eINSTANCE.getCheckedType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCheckedTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertCheckedTypeToString(XhtmlPackage.eINSTANCE.getCheckedType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createContentTypeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertContentTypeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createContentTypesFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertContentTypesToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createCoordsFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCoordsToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XMLGregorianCalendar createDatetimeFromString(EDataType eDataType, String initialValue) {
		return (XMLGregorianCalendar)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.DATE_TIME, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDatetimeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.DATE_TIME, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeclareType createDeclareTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createDeclareTypeFromString(XhtmlPackage.eINSTANCE.getDeclareType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDeclareTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertDeclareTypeToString(XhtmlPackage.eINSTANCE.getDeclareType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeferType createDeferTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createDeferTypeFromString(XhtmlPackage.eINSTANCE.getDeferType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDeferTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertDeferTypeToString(XhtmlPackage.eINSTANCE.getDeferType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DirType createDirTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createDirTypeFromString(XhtmlPackage.eINSTANCE.getDirType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDirTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertDirTypeToString(XhtmlPackage.eINSTANCE.getDirType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DirType1 createDirTypeObject1FromString(EDataType eDataType, String initialValue) {
		return createDirType1FromString(XhtmlPackage.eINSTANCE.getDirType1(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDirTypeObject1ToString(EDataType eDataType, Object instanceValue) {
		return convertDirType1ToString(XhtmlPackage.eINSTANCE.getDirType1(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DisabledType1 createDisabledTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createDisabledType1FromString(XhtmlPackage.eINSTANCE.getDisabledType1(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDisabledTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertDisabledType1ToString(XhtmlPackage.eINSTANCE.getDisabledType1(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DisabledType3 createDisabledTypeObject1FromString(EDataType eDataType, String initialValue) {
		return createDisabledType3FromString(XhtmlPackage.eINSTANCE.getDisabledType3(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDisabledTypeObject1ToString(EDataType eDataType, Object instanceValue) {
		return convertDisabledType3ToString(XhtmlPackage.eINSTANCE.getDisabledType3(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DisabledType createDisabledTypeObject2FromString(EDataType eDataType, String initialValue) {
		return createDisabledTypeFromString(XhtmlPackage.eINSTANCE.getDisabledType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDisabledTypeObject2ToString(EDataType eDataType, Object instanceValue) {
		return convertDisabledTypeToString(XhtmlPackage.eINSTANCE.getDisabledType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DisabledType2 createDisabledTypeObject3FromString(EDataType eDataType, String initialValue) {
		return createDisabledType2FromString(XhtmlPackage.eINSTANCE.getDisabledType2(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDisabledTypeObject3ToString(EDataType eDataType, Object instanceValue) {
		return convertDisabledType2ToString(XhtmlPackage.eINSTANCE.getDisabledType2(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DisabledType4 createDisabledTypeObject4FromString(EDataType eDataType, String initialValue) {
		return createDisabledType4FromString(XhtmlPackage.eINSTANCE.getDisabledType4(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDisabledTypeObject4ToString(EDataType eDataType, Object instanceValue) {
		return convertDisabledType4ToString(XhtmlPackage.eINSTANCE.getDisabledType4(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DisabledType5 createDisabledTypeObject5FromString(EDataType eDataType, String initialValue) {
		return createDisabledType5FromString(XhtmlPackage.eINSTANCE.getDisabledType5(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDisabledTypeObject5ToString(EDataType eDataType, Object instanceValue) {
		return convertDisabledType5ToString(XhtmlPackage.eINSTANCE.getDisabledType5(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InputType createInputTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createInputTypeFromString(XhtmlPackage.eINSTANCE.getInputType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertInputTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertInputTypeToString(XhtmlPackage.eINSTANCE.getInputType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IsmapType createIsmapTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createIsmapTypeFromString(XhtmlPackage.eINSTANCE.getIsmapType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIsmapTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertIsmapTypeToString(XhtmlPackage.eINSTANCE.getIsmapType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createLanguageCodeFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.LANGUAGE, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLanguageCodeToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.LANGUAGE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createLengthFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLengthToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<String> createLinkTypesFromString(EDataType eDataType, String initialValue) {
		return (List)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.NMTOKENS, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertLinkTypesToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.NMTOKENS, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createMediaDescFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMediaDescToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MethodType createMethodTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createMethodTypeFromString(XhtmlPackage.eINSTANCE.getMethodType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMethodTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertMethodTypeToString(XhtmlPackage.eINSTANCE.getMethodType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createMultiLengthFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMultiLengthToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultipleType createMultipleTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createMultipleTypeFromString(XhtmlPackage.eINSTANCE.getMultipleType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMultipleTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertMultipleTypeToString(XhtmlPackage.eINSTANCE.getMultipleType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NohrefType createNohrefTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createNohrefTypeFromString(XhtmlPackage.eINSTANCE.getNohrefType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNohrefTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertNohrefTypeToString(XhtmlPackage.eINSTANCE.getNohrefType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createNumberFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNumberToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createPixelsFromString(EDataType eDataType, String initialValue) {
		return (BigInteger)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPixelsToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.NON_NEGATIVE_INTEGER, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReadonlyType createReadonlyTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createReadonlyTypeFromString(XhtmlPackage.eINSTANCE.getReadonlyType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertReadonlyTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertReadonlyTypeToString(XhtmlPackage.eINSTANCE.getReadonlyType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReadonlyType1 createReadonlyTypeObject1FromString(EDataType eDataType, String initialValue) {
		return createReadonlyType1FromString(XhtmlPackage.eINSTANCE.getReadonlyType1(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertReadonlyTypeObject1ToString(EDataType eDataType, Object instanceValue) {
		return convertReadonlyType1ToString(XhtmlPackage.eINSTANCE.getReadonlyType1(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Scope createScopeObjectFromString(EDataType eDataType, String initialValue) {
		return createScopeFromString(XhtmlPackage.eINSTANCE.getScope(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertScopeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertScopeToString(XhtmlPackage.eINSTANCE.getScope(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createScriptFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertScriptToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SelectedType createSelectedTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createSelectedTypeFromString(XhtmlPackage.eINSTANCE.getSelectedType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSelectedTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertSelectedTypeToString(XhtmlPackage.eINSTANCE.getSelectedType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Shape createShapeObjectFromString(EDataType eDataType, String initialValue) {
		return createShapeFromString(XhtmlPackage.eINSTANCE.getShape(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertShapeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertShapeToString(XhtmlPackage.eINSTANCE.getShape(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createStyleSheetFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertStyleSheetToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BigInteger createTabindexNumberFromString(EDataType eDataType, String initialValue) {
		return createNumberFromString(XhtmlPackage.eINSTANCE.getNumber(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTabindexNumberToString(EDataType eDataType, Object instanceValue) {
		return convertNumberToString(XhtmlPackage.eINSTANCE.getNumber(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createTextFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTextToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TFrame createTFrameObjectFromString(EDataType eDataType, String initialValue) {
		return createTFrameFromString(XhtmlPackage.eINSTANCE.getTFrame(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTFrameObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTFrameToString(XhtmlPackage.eINSTANCE.getTFrame(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TRules createTRulesObjectFromString(EDataType eDataType, String initialValue) {
		return createTRulesFromString(XhtmlPackage.eINSTANCE.getTRules(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTRulesObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTRulesToString(XhtmlPackage.eINSTANCE.getTRules(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeType createTypeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createTypeTypeFromString(XhtmlPackage.eINSTANCE.getTypeType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTypeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertTypeTypeToString(XhtmlPackage.eINSTANCE.getTypeType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createURIFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.ANY_URI, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertURIToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.ANY_URI, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String createUriListFromString(EDataType eDataType, String initialValue) {
		return (String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.STRING, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertUriListToString(EDataType eDataType, Object instanceValue) {
		return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.STRING, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValignType createValignTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createValignTypeFromString(XhtmlPackage.eINSTANCE.getValignType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertValignTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertValignTypeToString(XhtmlPackage.eINSTANCE.getValignType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValuetypeType createValuetypeTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createValuetypeTypeFromString(XhtmlPackage.eINSTANCE.getValuetypeType(), initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertValuetypeTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertValuetypeTypeToString(XhtmlPackage.eINSTANCE.getValuetypeType(), instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public XhtmlPackage getXhtmlPackage() {
		return (XhtmlPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static XhtmlPackage getPackage() {
		return XhtmlPackage.eINSTANCE;
	}

} //XhtmlFactoryImpl
