package org.bonitasoft.studio.properties.form.sections.widget.contributions;

import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.form.DateFormField;
import org.bonitasoft.studio.model.form.FormPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class DisplayDateFormatContribution implements IExtensibleGridPropertySectionContribution {

	private Combo combo;
	private DateFormField date;
	private TransactionalEditingDomain editingDomain;

	private final String format1 = "dd MMM yyyy";
	private final String format2 = "dd-MM-yyyy";
	private final String format3 = "MM/dd/yyyy";
	private final String format4 = "dd-MM-yy HH:mm";
	private final String format5 = "EEE MMM dd, hh:mmaa";

	public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory, ExtensibleGridPropertySection extensibleGridPropertySection) {
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		composite.setLayout(new RowLayout());
		// shell = new GroovyShell();
		// Script script;
		RowData rd = new RowData();
		rd.width = 281;
		combo = new Combo(composite, SWT.MULTI);//widgetFactory.createText(composite, "", SWT.BORDER); //$NON-NLS-1$
		combo.setLayoutData(rd);

		combo.setToolTipText(Messages.Widget_DisplayDateFormat_tooltip);

		combo.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				editingDomain.getCommandStack().execute(
						new SetCommand(editingDomain, date, FormPackage.Literals.DATE_FORM_FIELD__DISPLAY_FORMAT, getComboText(combo.getText())));
			}

		});
		combo.add("");//$NON-NLS-1$
		combo.add(Messages.Widget_Date_Format1);
		combo.add(Messages.Widget_Date_Format2);
		combo.add(Messages.Widget_Date_Format3);
		combo.add(Messages.Widget_Date_Format4);
		combo.add(Messages.Widget_Date_Format5);

	}

	public void dispose() {

	}

	public String getLabel() {
		return Messages.Widget_DisplayDateFormat;
	}

	public boolean isRelevantFor(EObject eObject) {
		return eObject instanceof DateFormField;
	}

	public void refresh() {
		if (date.getDisplayFormat() != null) {
			combo.setText(setComboText(date.getDisplayFormat()));
		} else {
			combo.setText("");
		}
	}


	public void setEObject(EObject object) {
		date = (DateFormField) object;

	}

	public void setEditingDomain(TransactionalEditingDomain editingDomain) {
		this.editingDomain = editingDomain;
	}

	public void setSelection(ISelection selection) {

	}

	private Object getComboText(String format) {
		if (format.equals(Messages.Widget_Date_Format1)) {
			return format1;
		} else if (format.equals(Messages.Widget_Date_Format2)) {
			return format2;
		} else if (format.equals(Messages.Widget_Date_Format3)) {
			return format3;
		} else if (format.equals(Messages.Widget_Date_Format4)) {
			return format4;
		} else if (format.equals(Messages.Widget_Date_Format5)) {
			return format5;
		} else {
			return format;
		}
	}
	private String setComboText(String displayFormat) {
		if (displayFormat.equals(format1)) {
			return Messages.Widget_Date_Format1;
		} else if (displayFormat.equals(format2)) {
			return Messages.Widget_Date_Format2;
		} else if (displayFormat.equals(format3)) {
			return Messages.Widget_Date_Format3;
		} else if (displayFormat.equals(format4)) {
			return Messages.Widget_Date_Format4;
		} else if (displayFormat.equals(format5)) {
			return Messages.Widget_Date_Format5;
		} else {
			return displayFormat;
		}
	}

}
