package org.bonitasoft.studio.properties.form.sections.actions.contributions;

import java.util.Date;

import org.bonitasoft.studio.common.DateUtil;
import org.bonitasoft.studio.common.jface.databinding.DateTimeObservable;
import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.form.DateFormField;
import org.bonitasoft.studio.model.form.FormPackage;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class DateFormFielContribution extends InitialValueContribution{

    protected static final Object DEFAULT_DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";//$NON-NLS-1$
    private DateTime timeChooser;
    private DateTime dateChooser;

    @Override
    public void createControl(Composite composite, final TabbedPropertySheetWidgetFactory widgetFactory,
            final ExtensibleGridPropertySection extensibleGridPropertySection) {
        super.createControl(composite, widgetFactory, extensibleGridPropertySection);

        composite = widgetFactory.createComposite(composite);
        final GridLayout layout = new GridLayout(2, false);
        layout.marginHeight = 5;
        layout.marginWidth = 5;
        composite.setLayout(layout);
        composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));


        dateChooser = new DateTime(composite, SWT.BORDER | SWT.DATE | SWT.DROP_DOWN);
        widgetFactory.adapt(dateChooser,false,false);
        timeChooser = new DateTime(composite, SWT.BORDER | SWT.TIME);
        widgetFactory.adapt(timeChooser,false,false);
        bindDateWidget();
    }

    @Override
    protected void doCreateControl(final TabbedPropertySheetWidgetFactory widgetFactory) {
        super.doCreateControl(widgetFactory);
        expressionViewer.setMessage(Messages.data_tooltip_date);
    }

    private void bindDateWidget() {
        final UpdateValueStrategy dateToInCombo = new UpdateValueStrategy().setConverter(new Converter(Date.class,String.class) {

            public Object convert(final Object fromObject) {
                return DateUtil.getWidgetDisplayDate(dateChooser, timeChooser);
            }
        });

        dataBindingContext.bindValue(new DateTimeObservable(dateChooser),
                SWTObservables.observeText(expressionViewer.getTextControl()),
                dateToInCombo,
                new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
        dataBindingContext.bindValue(new DateTimeObservable(timeChooser),
                SWTObservables.observeText(expressionViewer.getTextControl()),
                dateToInCombo,
                new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));


        final UpdateValueStrategy dateToFormat = new UpdateValueStrategy().setConverter(new Converter(Date.class,String.class) {

            public Object convert(final Object fromObject) {
                return DateFormFielContribution.DEFAULT_DATE_FORMAT;
            }
        });
        dataBindingContext.bindValue(new DateTimeObservable(dateChooser),
                EMFEditObservables.observeValue(editingDomain, widget, FormPackage.Literals.DATE_FORM_FIELD__DISPLAY_FORMAT),
                dateToFormat,
                new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
        dataBindingContext.bindValue(new DateTimeObservable(timeChooser), EMFEditObservables.observeValue(editingDomain, widget, FormPackage.Literals.DATE_FORM_FIELD__DISPLAY_FORMAT),
                dateToFormat,
                new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));


    }


    @Override
    public boolean isRelevantFor(final EObject eObject) {
        return eObject instanceof DateFormField;
    }

}
