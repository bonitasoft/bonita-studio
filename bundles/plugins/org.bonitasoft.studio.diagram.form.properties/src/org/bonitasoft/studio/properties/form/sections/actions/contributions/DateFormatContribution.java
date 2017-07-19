package org.bonitasoft.studio.properties.form.sections.actions.contributions;

import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.common.properties.IExtensibleGridPropertySectionContribution;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.form.DateFormField;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class DateFormatContribution implements IExtensibleGridPropertySectionContribution {

    private DateFormField dateFormField;
    private TransactionalEditingDomain editingDomain;
    private EMFDataBindingContext dataBindingContext;
    private CCombo combo;

    private static final String format1 = "dd MMM yyyy";
    private static final String format2 = "dd-MM-yyyy";
    private static final String format3 = "MM/dd/yyyy";
    private static final String format4 = "dd-MM-yy HH:mm";
    private static final String format5 = "EEE MMM dd, hh:mmaa";

    public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory,
            ExtensibleGridPropertySection extensibleGridPropertySection) {

        GridLayout layout = new GridLayout(1, false);

        layout.marginHeight = InitialValueContribution.MARGIN_HEIGHT;
        layout.marginWidth = InitialValueContribution.MARGIN_WIDTH;
        composite.setLayout(layout);

        combo = widgetFactory.createCCombo(composite, SWT.BORDER);//widgetFactory.createText(composite, "", SWT.BORDER);
        combo.setLayoutData(GridDataFactory.fillDefaults().hint(SWT.DEFAULT, 20).indent(10, 0).create());

        combo.add("");//$NON-NLS-1$
        combo.add(Messages.Widget_Date_Format1);
        combo.add(Messages.Widget_Date_Format2);
        combo.add(Messages.Widget_Date_Format3);
        combo.add(Messages.Widget_Date_Format4);
        combo.add(Messages.Widget_Date_Format5);

        final ControlDecoration hint = new ControlDecoration(combo, SWT.LEFT);
        hint.setImage(Pics.getImage(PicsConstants.hint));
        hint.setMarginWidth(3);
        hint.setDescriptionText(Messages.Widget_DisplayDateFormat_tooltip);
        bindWidgets();

    }

    private void bindWidgets() {
        if (dataBindingContext != null) {
            dataBindingContext.dispose();
        }
        dataBindingContext = new EMFDataBindingContext();
        UpdateValueStrategy comboToFormat = new UpdateValueStrategy()
                .setConverter(new Converter(String.class, String.class) {

                    public Object convert(Object fromObject) {
                        return getComboText((String) fromObject);
                    }
                });
        UpdateValueStrategy formatToCombo = new UpdateValueStrategy()
                .setConverter(new Converter(String.class, String.class) {

                    public Object convert(Object fromObject) {
                        return setComboText((String) fromObject);
                    }
                });
        dataBindingContext.bindValue(SWTObservables.observeText(combo),
                EMFEditObservables.observeValue(editingDomain, dateFormField,
                        FormPackage.Literals.DATE_FORM_FIELD__DISPLAY_FORMAT),
                comboToFormat,
                formatToCombo);
    }

    public void dispose() {
        if (dataBindingContext != null) {
            dataBindingContext.dispose();
        }
    }

    public String getLabel() {
        return Messages.Widget_DisplayDateFormat;
    }

    public boolean isRelevantFor(EObject eObject) {
        return eObject instanceof DateFormField;
    }

    public void refresh() {

    }

    public void setEObject(EObject object) {
        dateFormField = (DateFormField) object;

    }

    public void setEditingDomain(TransactionalEditingDomain editingDomain) {
        this.editingDomain = editingDomain;
    }

    public void setSelection(ISelection selection) {
    }

    private String getComboText(String format) {
        if (Messages.Widget_Date_Format1.equals(format)) {
            return format1;
        } else if (Messages.Widget_Date_Format2.equals(format)) {
            return format2;
        } else if (Messages.Widget_Date_Format3.equals(format)) {
            return format3;
        } else if (Messages.Widget_Date_Format4.equals(format)) {
            return format4;
        } else if (Messages.Widget_Date_Format5.equals(format)) {
            return format5;
        } else {
            return format;
        }
    }

    private String setComboText(String displayFormat) {
        if (format1.equals(displayFormat)) {
            return Messages.Widget_Date_Format1;
        } else if (format2.equals(displayFormat)) {
            return Messages.Widget_Date_Format2;
        } else if (format3.equals(displayFormat)) {
            return Messages.Widget_Date_Format3;
        } else if (format4.equals(displayFormat)) {
            return Messages.Widget_Date_Format4;
        } else if (format5.equals(displayFormat)) {
            return Messages.Widget_Date_Format5;
        } else {
            return displayFormat;
        }
    }

}
