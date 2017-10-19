
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
package org.bonitasoft.studio.model.process.diagram.form.part;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.diagram.tools.BonitaUnspecifiedTypeCreationTool;
import org.bonitasoft.studio.model.process.diagram.form.providers.ProcessElementTypes;
import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteGroup;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.internal.services.palette.PaletteToolEntry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @generated
 */
public class ProcessPaletteFactory {

	/**
	* @generated
	*/
	public void fillPalette(PaletteRoot paletteRoot) {
		paletteRoot.add(createDefault1Group());
	}

	/*Set as protected by Bonita to override it*/
	/**
	* Creates "Default" palette tool group
	* @generated
	*/
	protected PaletteContainer createDefault1Group() {
		PaletteGroup paletteContainer = new PaletteGroup(Messages.Default1Group_title);
		paletteContainer.setId("createDefault1Group"); //$NON-NLS-1$
		paletteContainer.setDescription(Messages.Default1Group_desc);
		paletteContainer.add(createCheckbox1CreationTool());
		paletteContainer.add(createCheckboxList2CreationTool());
		paletteContainer.add(createDate3CreationTool());
		paletteContainer.add(createDuration4CreationTool());
		paletteContainer.add(createPassword5CreationTool());
		paletteContainer.add(createList6CreationTool());
		paletteContainer.add(createRadio7CreationTool());
		paletteContainer.add(createSelect8CreationTool());
		paletteContainer.add(createSuggestBox9CreationTool());
		paletteContainer.add(createTextBox10CreationTool());
		paletteContainer.add(createTextArea11CreationTool());
		paletteContainer.add(createRichTextArea12CreationTool());
		paletteContainer.add(createText13CreationTool());
		paletteContainer.add(createMessage14CreationTool());
		paletteContainer.add(createSubmit15CreationTool());
		paletteContainer.add(createPrevious16CreationTool());
		paletteContainer.add(createNext17CreationTool());
		paletteContainer.add(createSimpleButton18CreationTool());
		paletteContainer.add(createFile19CreationTool());
		paletteContainer.add(createImage20CreationTool());
		paletteContainer.add(createTable21CreationTool());
		paletteContainer.add(createEditableGrid22CreationTool());
		paletteContainer.add(createHidden23CreationTool());
		paletteContainer.add(createIFrame24CreationTool());
		paletteContainer.add(createHTML25CreationTool());
		return paletteContainer;
	}

	/**
	* @generated
	*/
	private ToolEntry createCheckbox1CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.CheckBoxSingleFormField_2130);
		types.add(ProcessElementTypes.CheckBoxSingleFormField_3118);
		NodeToolEntry entry = new NodeToolEntry(Messages.Checkbox1CreationTool_title,
				Messages.Checkbox1CreationTool_desc, types);
		entry.setId("createCheckbox1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(FormDiagramEditorPlugin
				.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/checkbox16.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createCheckboxList2CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.CheckBoxMultipleFormField_3120);
		types.add(ProcessElementTypes.CheckBoxMultipleFormField_2134);
		NodeToolEntry entry = new NodeToolEntry(Messages.CheckboxList2CreationTool_title,
				Messages.CheckboxList2CreationTool_desc, types);
		entry.setId("createCheckboxList2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(FormDiagramEditorPlugin
				.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/checkBoxGroup.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createDate3CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.DateFormField_2115);
		types.add(ProcessElementTypes.DateFormField_3105);
		NodeToolEntry entry = new NodeToolEntry(Messages.Date3CreationTool_title, Messages.Date3CreationTool_desc,
				types);
		entry.setId("createDate3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				FormDiagramEditorPlugin.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/date16.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createDuration4CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.DurationFormField_3121);
		types.add(ProcessElementTypes.DurationFormField_2137);
		NodeToolEntry entry = new NodeToolEntry(Messages.Duration4CreationTool_title,
				Messages.Duration4CreationTool_desc, types);
		entry.setId("createDuration4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(FormDiagramEditorPlugin
				.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/duration.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createPassword5CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.PasswordFormField_2117);
		types.add(ProcessElementTypes.PasswordFormField_3109);
		NodeToolEntry entry = new NodeToolEntry(Messages.Password5CreationTool_title,
				Messages.Password5CreationTool_desc, types);
		entry.setId("createPassword5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(FormDiagramEditorPlugin
				.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/password16.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createList6CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.ListFormField_2116);
		types.add(ProcessElementTypes.ListFormField_3107);
		NodeToolEntry entry = new NodeToolEntry(Messages.List6CreationTool_title, Messages.List6CreationTool_desc,
				types);
		entry.setId("createList6CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				FormDiagramEditorPlugin.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/list16.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createRadio7CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.RadioFormField_2118);
		types.add(ProcessElementTypes.RadioFormField_3110);
		NodeToolEntry entry = new NodeToolEntry(Messages.Radio7CreationTool_title, Messages.Radio7CreationTool_desc,
				types);
		entry.setId("createRadio7CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(FormDiagramEditorPlugin
				.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/radio16.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createSelect8CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.SelectFormField_2119);
		types.add(ProcessElementTypes.SelectFormField_3111);
		NodeToolEntry entry = new NodeToolEntry(Messages.Select8CreationTool_title, Messages.Select8CreationTool_desc,
				types);
		entry.setId("createSelect8CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(FormDiagramEditorPlugin
				.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/select16.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createSuggestBox9CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.SuggestBox_2144);
		types.add(ProcessElementTypes.SuggestBox_3132);
		NodeToolEntry entry = new NodeToolEntry(Messages.SuggestBox9CreationTool_title,
				Messages.SuggestBox9CreationTool_desc, types);
		entry.setId("createSuggestBox9CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(FormDiagramEditorPlugin
				.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/suggestbox16.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createTextBox10CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.TextFormField_2120);
		types.add(ProcessElementTypes.TextFormField_3112);
		NodeToolEntry entry = new NodeToolEntry(Messages.TextBox10CreationTool_title,
				Messages.TextBox10CreationTool_desc, types);
		entry.setId("createTextBox10CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(FormDiagramEditorPlugin
				.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/textBox16.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createTextArea11CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.TextAreaFormField_2121);
		types.add(ProcessElementTypes.TextAreaFormField_3113);
		NodeToolEntry entry = new NodeToolEntry(Messages.TextArea11CreationTool_title,
				Messages.TextArea11CreationTool_desc, types);
		entry.setId("createTextArea11CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(FormDiagramEditorPlugin
				.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/textarea16.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createRichTextArea12CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.RichTextAreaFormField_2140);
		types.add(ProcessElementTypes.RichTextAreaFormField_3128);
		NodeToolEntry entry = new NodeToolEntry(Messages.RichTextArea12CreationTool_title,
				Messages.RichTextArea12CreationTool_desc, types);
		entry.setId("createRichTextArea12CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(FormDiagramEditorPlugin
				.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/richtextarea16.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createText13CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.TextInfo_3125);
		types.add(ProcessElementTypes.TextInfo_2132);
		NodeToolEntry entry = new NodeToolEntry(Messages.Text13CreationTool_title, Messages.Text13CreationTool_desc,
				types);
		entry.setId("createText13CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				FormDiagramEditorPlugin.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/text16.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createMessage14CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.MessageInfo_3124);
		types.add(ProcessElementTypes.MessageInfo_2131);
		NodeToolEntry entry = new NodeToolEntry(Messages.Message14CreationTool_title,
				Messages.Message14CreationTool_desc, types);
		entry.setId("createMessage14CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(FormDiagramEditorPlugin
				.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/message16.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createSubmit15CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.SubmitFormButton_2126);
		types.add(ProcessElementTypes.SubmitFormButton_3116);
		NodeToolEntry entry = new NodeToolEntry(Messages.Submit15CreationTool_title, Messages.Submit15CreationTool_desc,
				types);
		entry.setId("createSubmit15CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(FormDiagramEditorPlugin
				.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/okButton16.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createPrevious16CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.PreviousFormButton_2128);
		types.add(ProcessElementTypes.PreviousFormButton_3114);
		NodeToolEntry entry = new NodeToolEntry(Messages.Previous16CreationTool_title,
				Messages.Previous16CreationTool_desc, types);
		entry.setId("createPrevious16CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(FormDiagramEditorPlugin
				.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/previous.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createNext17CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.NextFormButton_2127);
		types.add(ProcessElementTypes.NextFormButton_3115);
		NodeToolEntry entry = new NodeToolEntry(Messages.Next17CreationTool_title, Messages.Next17CreationTool_desc,
				types);
		entry.setId("createNext17CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				FormDiagramEditorPlugin.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/next.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createSimpleButton18CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.FormButton_3126);
		types.add(ProcessElementTypes.FormButton_2138);
		NodeToolEntry entry = new NodeToolEntry(Messages.SimpleButton18CreationTool_title,
				Messages.SimpleButton18CreationTool_desc, types);
		entry.setId("createSimpleButton18CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				FormDiagramEditorPlugin.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/blank.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createFile19CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.FileWidget_3119);
		types.add(ProcessElementTypes.FileWidget_2133);
		NodeToolEntry entry = new NodeToolEntry(Messages.File19CreationTool_title, Messages.File19CreationTool_desc,
				types);
		entry.setId("createFile19CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				FormDiagramEditorPlugin.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/file16.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createImage20CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.ImageWidget_3123);
		types.add(ProcessElementTypes.ImageWidget_2135);
		NodeToolEntry entry = new NodeToolEntry(Messages.Image20CreationTool_title, Messages.Image20CreationTool_desc,
				types);
		entry.setId("createImage20CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(FormDiagramEditorPlugin
				.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/image16.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createTable21CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.Table_3127);
		types.add(ProcessElementTypes.Table_2139);
		NodeToolEntry entry = new NodeToolEntry(Messages.Table21CreationTool_title, Messages.Table21CreationTool_desc,
				types);
		entry.setId("createTable21CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(ProcessElementTypes.getImageDescriptor(ProcessElementTypes.Table_3127));
		entry.setLargeIcon(FormDiagramEditorPlugin
				.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/table16.png")); //$NON-NLS-1$
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createEditableGrid22CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.DynamicTable_3129);
		types.add(ProcessElementTypes.DynamicTable_2141);
		NodeToolEntry entry = new NodeToolEntry(Messages.EditableGrid22CreationTool_title,
				Messages.EditableGrid22CreationTool_desc, types);
		entry.setId("createEditableGrid22CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(FormDiagramEditorPlugin
				.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/dynamicTable.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createHidden23CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.HiddenWidget_3122);
		types.add(ProcessElementTypes.HiddenWidget_2136);
		NodeToolEntry entry = new NodeToolEntry(Messages.Hidden23CreationTool_title, Messages.Hidden23CreationTool_desc,
				types);
		entry.setId("createHidden23CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(FormDiagramEditorPlugin
				.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/hidden16.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createIFrame24CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.IFrameWidget_3130);
		types.add(ProcessElementTypes.IFrameWidget_2142);
		NodeToolEntry entry = new NodeToolEntry(Messages.IFrame24CreationTool_title, Messages.IFrame24CreationTool_desc,
				types);
		entry.setId("createIFrame24CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(
				FormDiagramEditorPlugin.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/iframe.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/
	private ToolEntry createHTML25CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(2);
		types.add(ProcessElementTypes.HtmlWidget_3131);
		types.add(ProcessElementTypes.HtmlWidget_2143);
		NodeToolEntry entry = new NodeToolEntry(Messages.HTML25CreationTool_title, Messages.HTML25CreationTool_desc,
				types);
		entry.setId("createHTML25CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(FormDiagramEditorPlugin
				.findImageDescriptor("/org.bonitasoft.studio.pics/icons/formFields/htmlwidget.png")); //$NON-NLS-1$
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	* @generated
	*/

	/**@deprecated WARN : set t public only as a workaround in order to be able be used in a sublcass of PaletteToolEntry*/
	public static class NodeToolEntry extends PaletteToolEntry {

		/**
		* @generated
		*/
		private final List<IElementType> elementTypes;;

		/**
		* @generated
		*/
		/**@deprecated WARN : set t public only as a workaround in order to be able be used in a sublcass of PaletteToolEntry*/
		public NodeToolEntry(String title, String description, List<IElementType> elementTypes) {

			super(null, NamingUtils.getFormPaletteTitle(elementTypes), null);
			super.setDescription(NamingUtils.getFormPaletteDescription(elementTypes));
			this.elementTypes = elementTypes;
		}

		/**
		* Used a custom tool to have a better feedback.
		* @generated
		*/
		public Tool createTool() {

			Tool tool = new BonitaUnspecifiedTypeCreationTool(elementTypes);
			tool.setProperties(getToolProperties());
			return tool;
		}
	}

}
