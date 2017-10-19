/*
 * Copyright (C) 2009-2015 BonitaSoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.model.process.diagram.form.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.CheckBoxMultipleFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.CheckBoxMultipleFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.CheckBoxSingleFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.CheckBoxSingleFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ComboFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ComboFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DateFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DateFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DurationFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DurationFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DynamicTable2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.DynamicTableEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FileWidget2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FileWidgetEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormButton2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormButtonEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.Group2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.GroupEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.HiddenWidget2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.HiddenWidgetEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.HtmlWidget2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.HtmlWidgetEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.IFrameWidget2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.IFrameWidgetEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ImageWidget2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ImageWidgetEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ListFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.ListFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.MessageInfo2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.MessageInfoEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.NextFormButton2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.NextFormButtonEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PasswordFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PasswordFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PreviousFormButton2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.PreviousFormButtonEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.RadioFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.RadioFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.RichTextAreaFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.RichTextAreaFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SelectFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SelectFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SubmitFormButton2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SubmitFormButtonEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SuggestBox2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.SuggestBoxEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.Table2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TableEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextAreaFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextAreaFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextFormField2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextFormFieldEditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextInfo2EditPart;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.TextInfoEditPart;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditorPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypeImages;
import org.eclipse.gmf.tooling.runtime.providers.DiagramElementTypes;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

/**
 * @generated
 */
public class ProcessElementTypes {

	/**
	* @generated
	*/
	private ProcessElementTypes() {
	}

	/**
	* @generated
	*/
	private static Map<IElementType, ENamedElement> elements;

	/**
	* @generated
	*/
	private static DiagramElementTypeImages elementTypeImages = new DiagramElementTypeImages(
			FormDiagramEditorPlugin.getInstance().getItemProvidersAdapterFactory());

	/**
	* @generated
	*/
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;

	/**
	* @generated
	*/
	public static final IElementType Form_1000 = getElementType("org.bonitasoft.studio.diagram.form.Form_1000"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType CheckBoxSingleFormField_2130 = getElementType(
			"org.bonitasoft.studio.diagram.form.CheckBoxSingleFormField_2130"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType ComboFormField_2114 = getElementType(
			"org.bonitasoft.studio.diagram.form.ComboFormField_2114"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType DateFormField_2115 = getElementType(
			"org.bonitasoft.studio.diagram.form.DateFormField_2115"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType ListFormField_2116 = getElementType(
			"org.bonitasoft.studio.diagram.form.ListFormField_2116"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType PasswordFormField_2117 = getElementType(
			"org.bonitasoft.studio.diagram.form.PasswordFormField_2117"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType RadioFormField_2118 = getElementType(
			"org.bonitasoft.studio.diagram.form.RadioFormField_2118"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType SelectFormField_2119 = getElementType(
			"org.bonitasoft.studio.diagram.form.SelectFormField_2119"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType SuggestBox_2144 = getElementType(
			"org.bonitasoft.studio.diagram.form.SuggestBox_2144"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType TextFormField_2120 = getElementType(
			"org.bonitasoft.studio.diagram.form.TextFormField_2120"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType TextAreaFormField_2121 = getElementType(
			"org.bonitasoft.studio.diagram.form.TextAreaFormField_2121"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType RichTextAreaFormField_2140 = getElementType(
			"org.bonitasoft.studio.diagram.form.RichTextAreaFormField_2140"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType SubmitFormButton_2126 = getElementType(
			"org.bonitasoft.studio.diagram.form.SubmitFormButton_2126"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType NextFormButton_2127 = getElementType(
			"org.bonitasoft.studio.diagram.form.NextFormButton_2127"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType PreviousFormButton_2128 = getElementType(
			"org.bonitasoft.studio.diagram.form.PreviousFormButton_2128"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Group_2125 = getElementType("org.bonitasoft.studio.diagram.form.Group_2125"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType MessageInfo_2131 = getElementType(
			"org.bonitasoft.studio.diagram.form.MessageInfo_2131"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType TextInfo_2132 = getElementType("org.bonitasoft.studio.diagram.form.TextInfo_2132"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType FileWidget_2133 = getElementType(
			"org.bonitasoft.studio.diagram.form.FileWidget_2133"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType CheckBoxMultipleFormField_2134 = getElementType(
			"org.bonitasoft.studio.diagram.form.CheckBoxMultipleFormField_2134"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType ImageWidget_2135 = getElementType(
			"org.bonitasoft.studio.diagram.form.ImageWidget_2135"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType HiddenWidget_2136 = getElementType(
			"org.bonitasoft.studio.diagram.form.HiddenWidget_2136"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType DurationFormField_2137 = getElementType(
			"org.bonitasoft.studio.diagram.form.DurationFormField_2137"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType FormButton_2138 = getElementType(
			"org.bonitasoft.studio.diagram.form.FormButton_2138"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Table_2139 = getElementType("org.bonitasoft.studio.diagram.form.Table_2139"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType DynamicTable_2141 = getElementType(
			"org.bonitasoft.studio.diagram.form.DynamicTable_2141"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType IFrameWidget_2142 = getElementType(
			"org.bonitasoft.studio.diagram.form.IFrameWidget_2142"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType HtmlWidget_2143 = getElementType(
			"org.bonitasoft.studio.diagram.form.HtmlWidget_2143"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType PreviousFormButton_3114 = getElementType(
			"org.bonitasoft.studio.diagram.form.PreviousFormButton_3114"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType CheckBoxSingleFormField_3118 = getElementType(
			"org.bonitasoft.studio.diagram.form.CheckBoxSingleFormField_3118"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType ComboFormField_3103 = getElementType(
			"org.bonitasoft.studio.diagram.form.ComboFormField_3103"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType NextFormButton_3115 = getElementType(
			"org.bonitasoft.studio.diagram.form.NextFormButton_3115"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType DateFormField_3105 = getElementType(
			"org.bonitasoft.studio.diagram.form.DateFormField_3105"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Group_3106 = getElementType("org.bonitasoft.studio.diagram.form.Group_3106"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType ListFormField_3107 = getElementType(
			"org.bonitasoft.studio.diagram.form.ListFormField_3107"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType SubmitFormButton_3116 = getElementType(
			"org.bonitasoft.studio.diagram.form.SubmitFormButton_3116"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType PasswordFormField_3109 = getElementType(
			"org.bonitasoft.studio.diagram.form.PasswordFormField_3109"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType RadioFormField_3110 = getElementType(
			"org.bonitasoft.studio.diagram.form.RadioFormField_3110"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType SelectFormField_3111 = getElementType(
			"org.bonitasoft.studio.diagram.form.SelectFormField_3111"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType TextFormField_3112 = getElementType(
			"org.bonitasoft.studio.diagram.form.TextFormField_3112"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType TextAreaFormField_3113 = getElementType(
			"org.bonitasoft.studio.diagram.form.TextAreaFormField_3113"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType RichTextAreaFormField_3128 = getElementType(
			"org.bonitasoft.studio.diagram.form.RichTextAreaFormField_3128"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType FileWidget_3119 = getElementType(
			"org.bonitasoft.studio.diagram.form.FileWidget_3119"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType CheckBoxMultipleFormField_3120 = getElementType(
			"org.bonitasoft.studio.diagram.form.CheckBoxMultipleFormField_3120"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType DurationFormField_3121 = getElementType(
			"org.bonitasoft.studio.diagram.form.DurationFormField_3121"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType HiddenWidget_3122 = getElementType(
			"org.bonitasoft.studio.diagram.form.HiddenWidget_3122"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType ImageWidget_3123 = getElementType(
			"org.bonitasoft.studio.diagram.form.ImageWidget_3123"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType MessageInfo_3124 = getElementType(
			"org.bonitasoft.studio.diagram.form.MessageInfo_3124"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType TextInfo_3125 = getElementType("org.bonitasoft.studio.diagram.form.TextInfo_3125"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType FormButton_3126 = getElementType(
			"org.bonitasoft.studio.diagram.form.FormButton_3126"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType Table_3127 = getElementType("org.bonitasoft.studio.diagram.form.Table_3127"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType DynamicTable_3129 = getElementType(
			"org.bonitasoft.studio.diagram.form.DynamicTable_3129"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType IFrameWidget_3130 = getElementType(
			"org.bonitasoft.studio.diagram.form.IFrameWidget_3130"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType HtmlWidget_3131 = getElementType(
			"org.bonitasoft.studio.diagram.form.HtmlWidget_3131"); //$NON-NLS-1$
	/**
	* @generated
	*/
	public static final IElementType SuggestBox_3132 = getElementType(
			"org.bonitasoft.studio.diagram.form.SuggestBox_3132"); //$NON-NLS-1$

	/**
	* @generated
	*/
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		return elementTypeImages.getImageDescriptor(element);
	}

	/**
	* @generated
	*/
	public static Image getImage(ENamedElement element) {
		return elementTypeImages.getImage(element);
	}

	/**
	* @generated
	*/
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		return getImageDescriptor(getElement(hint));
	}

	/**
	* @generated
	*/
	public static Image getImage(IAdaptable hint) {
		return getImage(getElement(hint));
	}

	/**
	* Returns 'type' of the ecore object associated with the hint.
	* 
	* @generated
	*/
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			initElements();
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	* @generated
	*/
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	* @generated
	*/
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			initKnownElementTypes();
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	* @generated
	*/
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case FormEditPart.VISUAL_ID:
			return Form_1000;
		case CheckBoxSingleFormFieldEditPart.VISUAL_ID:
			return CheckBoxSingleFormField_2130;
		case ComboFormFieldEditPart.VISUAL_ID:
			return ComboFormField_2114;
		case DateFormFieldEditPart.VISUAL_ID:
			return DateFormField_2115;
		case ListFormFieldEditPart.VISUAL_ID:
			return ListFormField_2116;
		case PasswordFormFieldEditPart.VISUAL_ID:
			return PasswordFormField_2117;
		case RadioFormFieldEditPart.VISUAL_ID:
			return RadioFormField_2118;
		case SelectFormFieldEditPart.VISUAL_ID:
			return SelectFormField_2119;
		case SuggestBoxEditPart.VISUAL_ID:
			return SuggestBox_2144;
		case TextFormFieldEditPart.VISUAL_ID:
			return TextFormField_2120;
		case TextAreaFormFieldEditPart.VISUAL_ID:
			return TextAreaFormField_2121;
		case RichTextAreaFormFieldEditPart.VISUAL_ID:
			return RichTextAreaFormField_2140;
		case SubmitFormButtonEditPart.VISUAL_ID:
			return SubmitFormButton_2126;
		case NextFormButtonEditPart.VISUAL_ID:
			return NextFormButton_2127;
		case PreviousFormButtonEditPart.VISUAL_ID:
			return PreviousFormButton_2128;
		case GroupEditPart.VISUAL_ID:
			return Group_2125;
		case MessageInfoEditPart.VISUAL_ID:
			return MessageInfo_2131;
		case TextInfoEditPart.VISUAL_ID:
			return TextInfo_2132;
		case FileWidgetEditPart.VISUAL_ID:
			return FileWidget_2133;
		case CheckBoxMultipleFormFieldEditPart.VISUAL_ID:
			return CheckBoxMultipleFormField_2134;
		case ImageWidgetEditPart.VISUAL_ID:
			return ImageWidget_2135;
		case HiddenWidgetEditPart.VISUAL_ID:
			return HiddenWidget_2136;
		case DurationFormFieldEditPart.VISUAL_ID:
			return DurationFormField_2137;
		case FormButtonEditPart.VISUAL_ID:
			return FormButton_2138;
		case TableEditPart.VISUAL_ID:
			return Table_2139;
		case DynamicTableEditPart.VISUAL_ID:
			return DynamicTable_2141;
		case IFrameWidgetEditPart.VISUAL_ID:
			return IFrameWidget_2142;
		case HtmlWidgetEditPart.VISUAL_ID:
			return HtmlWidget_2143;
		case PreviousFormButton2EditPart.VISUAL_ID:
			return PreviousFormButton_3114;
		case CheckBoxSingleFormField2EditPart.VISUAL_ID:
			return CheckBoxSingleFormField_3118;
		case ComboFormField2EditPart.VISUAL_ID:
			return ComboFormField_3103;
		case NextFormButton2EditPart.VISUAL_ID:
			return NextFormButton_3115;
		case DateFormField2EditPart.VISUAL_ID:
			return DateFormField_3105;
		case Group2EditPart.VISUAL_ID:
			return Group_3106;
		case ListFormField2EditPart.VISUAL_ID:
			return ListFormField_3107;
		case SubmitFormButton2EditPart.VISUAL_ID:
			return SubmitFormButton_3116;
		case PasswordFormField2EditPart.VISUAL_ID:
			return PasswordFormField_3109;
		case RadioFormField2EditPart.VISUAL_ID:
			return RadioFormField_3110;
		case SelectFormField2EditPart.VISUAL_ID:
			return SelectFormField_3111;
		case TextFormField2EditPart.VISUAL_ID:
			return TextFormField_3112;
		case TextAreaFormField2EditPart.VISUAL_ID:
			return TextAreaFormField_3113;
		case RichTextAreaFormField2EditPart.VISUAL_ID:
			return RichTextAreaFormField_3128;
		case FileWidget2EditPart.VISUAL_ID:
			return FileWidget_3119;
		case CheckBoxMultipleFormField2EditPart.VISUAL_ID:
			return CheckBoxMultipleFormField_3120;
		case DurationFormField2EditPart.VISUAL_ID:
			return DurationFormField_3121;
		case HiddenWidget2EditPart.VISUAL_ID:
			return HiddenWidget_3122;
		case ImageWidget2EditPart.VISUAL_ID:
			return ImageWidget_3123;
		case MessageInfo2EditPart.VISUAL_ID:
			return MessageInfo_3124;
		case TextInfo2EditPart.VISUAL_ID:
			return TextInfo_3125;
		case FormButton2EditPart.VISUAL_ID:
			return FormButton_3126;
		case Table2EditPart.VISUAL_ID:
			return Table_3127;
		case DynamicTable2EditPart.VISUAL_ID:
			return DynamicTable_3129;
		case IFrameWidget2EditPart.VISUAL_ID:
			return IFrameWidget_3130;
		case HtmlWidget2EditPart.VISUAL_ID:
			return HtmlWidget_3131;
		case SuggestBox2EditPart.VISUAL_ID:
			return SuggestBox_3132;
		}
		return null;
	}

	/**
	* @generated
	*/
	public static final DiagramElementTypes TYPED_INSTANCE = new DiagramElementTypes(elementTypeImages) {

		/**
		* @generated
		*/
		@Override

		public boolean isKnownElementType(IElementType elementType) {
			return org.bonitasoft.studio.model.process.diagram.form.providers.ProcessElementTypes
					.isKnownElementType(elementType);
		}

		/**
		* @generated
		*/
		@Override

		public IElementType getElementTypeForVisualId(int visualID) {
			return org.bonitasoft.studio.model.process.diagram.form.providers.ProcessElementTypes
					.getElementType(visualID);
		}

		/**
		* @generated
		*/
		@Override

		public ENamedElement getDefiningNamedElement(IAdaptable elementTypeAdapter) {
			return org.bonitasoft.studio.model.process.diagram.form.providers.ProcessElementTypes
					.getElement(elementTypeAdapter);
		}
	};

	/**
	* @generated
	*/
	private synchronized static void initKnownElementTypes() {
		KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
		KNOWN_ELEMENT_TYPES.add(Form_1000);
		KNOWN_ELEMENT_TYPES.add(CheckBoxSingleFormField_2130);
		KNOWN_ELEMENT_TYPES.add(ComboFormField_2114);
		KNOWN_ELEMENT_TYPES.add(DateFormField_2115);
		KNOWN_ELEMENT_TYPES.add(ListFormField_2116);
		KNOWN_ELEMENT_TYPES.add(PasswordFormField_2117);
		KNOWN_ELEMENT_TYPES.add(RadioFormField_2118);
		KNOWN_ELEMENT_TYPES.add(SelectFormField_2119);
		KNOWN_ELEMENT_TYPES.add(SuggestBox_2144);
		KNOWN_ELEMENT_TYPES.add(TextFormField_2120);
		KNOWN_ELEMENT_TYPES.add(TextAreaFormField_2121);
		KNOWN_ELEMENT_TYPES.add(RichTextAreaFormField_2140);
		KNOWN_ELEMENT_TYPES.add(SubmitFormButton_2126);
		KNOWN_ELEMENT_TYPES.add(NextFormButton_2127);
		KNOWN_ELEMENT_TYPES.add(PreviousFormButton_2128);
		KNOWN_ELEMENT_TYPES.add(Group_2125);
		KNOWN_ELEMENT_TYPES.add(MessageInfo_2131);
		KNOWN_ELEMENT_TYPES.add(TextInfo_2132);
		KNOWN_ELEMENT_TYPES.add(FileWidget_2133);
		KNOWN_ELEMENT_TYPES.add(CheckBoxMultipleFormField_2134);
		KNOWN_ELEMENT_TYPES.add(ImageWidget_2135);
		KNOWN_ELEMENT_TYPES.add(HiddenWidget_2136);
		KNOWN_ELEMENT_TYPES.add(DurationFormField_2137);
		KNOWN_ELEMENT_TYPES.add(FormButton_2138);
		KNOWN_ELEMENT_TYPES.add(Table_2139);
		KNOWN_ELEMENT_TYPES.add(DynamicTable_2141);
		KNOWN_ELEMENT_TYPES.add(IFrameWidget_2142);
		KNOWN_ELEMENT_TYPES.add(HtmlWidget_2143);
		KNOWN_ELEMENT_TYPES.add(PreviousFormButton_3114);
		KNOWN_ELEMENT_TYPES.add(CheckBoxSingleFormField_3118);
		KNOWN_ELEMENT_TYPES.add(ComboFormField_3103);
		KNOWN_ELEMENT_TYPES.add(NextFormButton_3115);
		KNOWN_ELEMENT_TYPES.add(DateFormField_3105);
		KNOWN_ELEMENT_TYPES.add(Group_3106);
		KNOWN_ELEMENT_TYPES.add(ListFormField_3107);
		KNOWN_ELEMENT_TYPES.add(SubmitFormButton_3116);
		KNOWN_ELEMENT_TYPES.add(PasswordFormField_3109);
		KNOWN_ELEMENT_TYPES.add(RadioFormField_3110);
		KNOWN_ELEMENT_TYPES.add(SelectFormField_3111);
		KNOWN_ELEMENT_TYPES.add(TextFormField_3112);
		KNOWN_ELEMENT_TYPES.add(TextAreaFormField_3113);
		KNOWN_ELEMENT_TYPES.add(RichTextAreaFormField_3128);
		KNOWN_ELEMENT_TYPES.add(FileWidget_3119);
		KNOWN_ELEMENT_TYPES.add(CheckBoxMultipleFormField_3120);
		KNOWN_ELEMENT_TYPES.add(DurationFormField_3121);
		KNOWN_ELEMENT_TYPES.add(HiddenWidget_3122);
		KNOWN_ELEMENT_TYPES.add(ImageWidget_3123);
		KNOWN_ELEMENT_TYPES.add(MessageInfo_3124);
		KNOWN_ELEMENT_TYPES.add(TextInfo_3125);
		KNOWN_ELEMENT_TYPES.add(FormButton_3126);
		KNOWN_ELEMENT_TYPES.add(Table_3127);
		KNOWN_ELEMENT_TYPES.add(DynamicTable_3129);
		KNOWN_ELEMENT_TYPES.add(IFrameWidget_3130);
		KNOWN_ELEMENT_TYPES.add(HtmlWidget_3131);
		KNOWN_ELEMENT_TYPES.add(SuggestBox_3132);
	}

	/**
	* @generated
	*/
	private synchronized static void initElements() {
		elements = new IdentityHashMap<IElementType, ENamedElement>();

		elements.put(Form_1000, FormPackage.eINSTANCE.getForm());

		elements.put(CheckBoxSingleFormField_2130, FormPackage.eINSTANCE.getCheckBoxSingleFormField());

		elements.put(ComboFormField_2114, FormPackage.eINSTANCE.getComboFormField());

		elements.put(DateFormField_2115, FormPackage.eINSTANCE.getDateFormField());

		elements.put(ListFormField_2116, FormPackage.eINSTANCE.getListFormField());

		elements.put(PasswordFormField_2117, FormPackage.eINSTANCE.getPasswordFormField());

		elements.put(RadioFormField_2118, FormPackage.eINSTANCE.getRadioFormField());

		elements.put(SelectFormField_2119, FormPackage.eINSTANCE.getSelectFormField());

		elements.put(SuggestBox_2144, FormPackage.eINSTANCE.getSuggestBox());

		elements.put(TextFormField_2120, FormPackage.eINSTANCE.getTextFormField());

		elements.put(TextAreaFormField_2121, FormPackage.eINSTANCE.getTextAreaFormField());

		elements.put(RichTextAreaFormField_2140, FormPackage.eINSTANCE.getRichTextAreaFormField());

		elements.put(SubmitFormButton_2126, FormPackage.eINSTANCE.getSubmitFormButton());

		elements.put(NextFormButton_2127, FormPackage.eINSTANCE.getNextFormButton());

		elements.put(PreviousFormButton_2128, FormPackage.eINSTANCE.getPreviousFormButton());

		elements.put(Group_2125, FormPackage.eINSTANCE.getGroup());

		elements.put(MessageInfo_2131, FormPackage.eINSTANCE.getMessageInfo());

		elements.put(TextInfo_2132, FormPackage.eINSTANCE.getTextInfo());

		elements.put(FileWidget_2133, FormPackage.eINSTANCE.getFileWidget());

		elements.put(CheckBoxMultipleFormField_2134, FormPackage.eINSTANCE.getCheckBoxMultipleFormField());

		elements.put(ImageWidget_2135, FormPackage.eINSTANCE.getImageWidget());

		elements.put(HiddenWidget_2136, FormPackage.eINSTANCE.getHiddenWidget());

		elements.put(DurationFormField_2137, FormPackage.eINSTANCE.getDurationFormField());

		elements.put(FormButton_2138, FormPackage.eINSTANCE.getFormButton());

		elements.put(Table_2139, FormPackage.eINSTANCE.getTable());

		elements.put(DynamicTable_2141, FormPackage.eINSTANCE.getDynamicTable());

		elements.put(IFrameWidget_2142, FormPackage.eINSTANCE.getIFrameWidget());

		elements.put(HtmlWidget_2143, FormPackage.eINSTANCE.getHtmlWidget());

		elements.put(PreviousFormButton_3114, FormPackage.eINSTANCE.getPreviousFormButton());

		elements.put(CheckBoxSingleFormField_3118, FormPackage.eINSTANCE.getCheckBoxSingleFormField());

		elements.put(ComboFormField_3103, FormPackage.eINSTANCE.getComboFormField());

		elements.put(NextFormButton_3115, FormPackage.eINSTANCE.getNextFormButton());

		elements.put(DateFormField_3105, FormPackage.eINSTANCE.getDateFormField());

		elements.put(Group_3106, FormPackage.eINSTANCE.getGroup());

		elements.put(ListFormField_3107, FormPackage.eINSTANCE.getListFormField());

		elements.put(SubmitFormButton_3116, FormPackage.eINSTANCE.getSubmitFormButton());

		elements.put(PasswordFormField_3109, FormPackage.eINSTANCE.getPasswordFormField());

		elements.put(RadioFormField_3110, FormPackage.eINSTANCE.getRadioFormField());

		elements.put(SelectFormField_3111, FormPackage.eINSTANCE.getSelectFormField());

		elements.put(TextFormField_3112, FormPackage.eINSTANCE.getTextFormField());

		elements.put(TextAreaFormField_3113, FormPackage.eINSTANCE.getTextAreaFormField());

		elements.put(RichTextAreaFormField_3128, FormPackage.eINSTANCE.getRichTextAreaFormField());

		elements.put(FileWidget_3119, FormPackage.eINSTANCE.getFileWidget());

		elements.put(CheckBoxMultipleFormField_3120, FormPackage.eINSTANCE.getCheckBoxMultipleFormField());

		elements.put(DurationFormField_3121, FormPackage.eINSTANCE.getDurationFormField());

		elements.put(HiddenWidget_3122, FormPackage.eINSTANCE.getHiddenWidget());

		elements.put(ImageWidget_3123, FormPackage.eINSTANCE.getImageWidget());

		elements.put(MessageInfo_3124, FormPackage.eINSTANCE.getMessageInfo());

		elements.put(TextInfo_3125, FormPackage.eINSTANCE.getTextInfo());

		elements.put(FormButton_3126, FormPackage.eINSTANCE.getFormButton());

		elements.put(Table_3127, FormPackage.eINSTANCE.getTable());

		elements.put(DynamicTable_3129, FormPackage.eINSTANCE.getDynamicTable());

		elements.put(IFrameWidget_3130, FormPackage.eINSTANCE.getIFrameWidget());

		elements.put(HtmlWidget_3131, FormPackage.eINSTANCE.getHtmlWidget());

		elements.put(SuggestBox_3132, FormPackage.eINSTANCE.getSuggestBox());
	}

}
