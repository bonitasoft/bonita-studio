/**
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.diagram.form.custom.commands;

import org.bonitasoft.studio.model.form.CheckBoxMultipleFormField;
import org.bonitasoft.studio.model.form.CheckBoxSingleFormField;
import org.bonitasoft.studio.model.form.DateFormField;
import org.bonitasoft.studio.model.form.DurationFormField;
import org.bonitasoft.studio.model.form.DynamicTable;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.FileWidgetInputType;
import org.bonitasoft.studio.model.form.FormFactory;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.ListFormField;
import org.bonitasoft.studio.model.form.MessageInfo;
import org.bonitasoft.studio.model.form.PasswordFormField;
import org.bonitasoft.studio.model.form.RadioFormField;
import org.bonitasoft.studio.model.form.RichTextAreaFormField;
import org.bonitasoft.studio.model.form.SelectFormField;
import org.bonitasoft.studio.model.form.Table;
import org.bonitasoft.studio.model.form.TextAreaFormField;
import org.bonitasoft.studio.model.form.TextFormField;
import org.bonitasoft.studio.model.form.TextInfo;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.form.util.FormSwitch;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.diagram.form.providers.ElementInitializers;

/**
 * @author Romain Bioteau
 *
 */
public class CreateWidgetSwitch extends FormSwitch<Widget> {

	private final FormFactory factory ;
	private final ElementInitializers initializer;
    private final Element pageFlow;

    public CreateWidgetSwitch(final Element pageFlow, final ElementInitializers initializer) {
		factory = FormFactory.eINSTANCE;
		this.initializer = initializer;
        this.pageFlow = pageFlow;
	}

	@Override
	public Widget caseTextFormField(final TextFormField object) {
		final TextFormField widget = factory.createTextFormField();
		initializer.init_TextFormField_3112(widget);
		return widget;
	}

	@Override
	public Widget caseTextAreaFormField(final TextAreaFormField object) {
		final TextAreaFormField widget = factory.createTextAreaFormField();
		initializer.init_TextAreaFormField_3113(widget);
		return widget ;
	}

	@Override
	public Widget caseDateFormField(final DateFormField object) {
		final DateFormField widget = factory.createDateFormField();
		initializer.init_DateFormField_3105(widget);
		return widget ;
	}

	@Override
	public Widget caseCheckBoxSingleFormField(final CheckBoxSingleFormField object) {
		final CheckBoxSingleFormField widget = factory.createCheckBoxSingleFormField();
		initializer.init_CheckBoxSingleFormField_3118(widget);
		return widget ;
	}

	@Override
	public Widget caseListFormField(final ListFormField object) {
		final ListFormField widget = factory.createListFormField();
		initializer.init_ListFormField_3107(widget);
		return widget ;
	}

	@Override
	public Widget caseCheckBoxMultipleFormField(final CheckBoxMultipleFormField object) {
		final CheckBoxMultipleFormField widget = factory.createCheckBoxMultipleFormField();
		initializer.init_CheckBoxMultipleFormField_3120(widget);
		return widget ;
	}

	@Override
	public Widget caseFileWidget(final FileWidget object) {
		final FileWidget widget = factory.createFileWidget();
		initializer.init_FileWidget_3119(widget);
        widget.setInputType(getDefaultFileWidgetInputType(widget));
		return widget ;
	}

    private FileWidgetInputType getDefaultFileWidgetInputType(final FileWidget widget) {
        if (pageFlow instanceof Pool) {
            return FileWidgetInputType.RESOURCE;
        }
        return FileWidgetInputType.DOCUMENT;
    }

	@Override
	public Widget caseDurationFormField(final DurationFormField object) {
		final DurationFormField widget = factory.createDurationFormField();
		initializer.init_DurationFormField_3121(widget);
		return widget ;
	}

	@Override
	public Widget caseRadioFormField(final RadioFormField object) {
		final RadioFormField widget = factory.createRadioFormField();
		initializer.init_RadioFormField_3110(widget);
		return widget ;
	}

	@Override
	public Widget casePasswordFormField(final PasswordFormField object) {
		final PasswordFormField widget = factory.createPasswordFormField();
		initializer.init_PasswordFormField_3109(widget);
		return widget ;
	}

	@Override
	public Widget caseSelectFormField(final SelectFormField object) {
		final SelectFormField widget = factory.createSelectFormField();
		initializer.init_SelectFormField_3111(widget);
		return widget ;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.form.util.FormSwitch#caseMessageInfo(org.bonitasoft.studio.model.form.MessageInfo)
	 */
	@Override
	public Widget caseMessageInfo(final MessageInfo object) {
		final MessageInfo messageInfo = factory.createMessageInfo();
		initializer.init_MessageInfo_3124(messageInfo);
		return messageInfo;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.form.util.FormSwitch#caseTextInfo(org.bonitasoft.studio.model.form.TextInfo)
	 */
	@Override
	public Widget caseTextInfo(final TextInfo object) {
		final TextInfo textInfo = factory.createTextInfo();
		initializer.init_TextInfo_3125(textInfo);
		return textInfo;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.form.util.FormSwitch#caseRichTextAreaFormField(org.bonitasoft.studio.model.form.RichTextAreaFormField)
	 */
	@Override
	public Widget caseRichTextAreaFormField(final RichTextAreaFormField object) {
		final RichTextAreaFormField richTextArea = factory.createRichTextAreaFormField();
		initializer.init_RichTextAreaFormField_3128(richTextArea);
		return richTextArea;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.form.util.FormSwitch#caseTable(org.bonitasoft.studio.model.form.Table)
	 */
	@Override
	public Widget caseTable(final Table object) {
		final Table table = factory.createTable();
		initializer.init_Table_3127(table);
		return table;
	}


	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.form.util.FormSwitch#caseDynamicTable(org.bonitasoft.studio.model.form.DynamicTable)
	 */
	@Override
	public Widget caseDynamicTable(final DynamicTable object) {
		final DynamicTable table = factory.createDynamicTable();
		initializer.init_DynamicTable_3129(table);
		return table;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.form.util.FormSwitch#caseGroup(org.bonitasoft.studio.model.form.Group)
	 */
	@Override
	public Widget caseGroup(final Group object) {
		final Group group = factory.createGroup();
		initializer.init_Group_3106(group);
		return group;
	}

}
