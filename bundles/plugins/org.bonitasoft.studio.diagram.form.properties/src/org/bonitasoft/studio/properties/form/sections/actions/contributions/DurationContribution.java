package org.bonitasoft.studio.properties.form.sections.actions.contributions;

import org.bonitasoft.studio.common.properties.ExtensibleGridPropertySection;
import org.bonitasoft.studio.form.properties.i18n.Messages;
import org.bonitasoft.studio.model.form.DurationFormField;
import org.bonitasoft.studio.model.form.FormPackage;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class DurationContribution extends InitialValueContribution {

    private Button day;
    private Button hour;
    private Button min;
    private Button sec;

    @Override
    public void createControl(Composite composite, TabbedPropertySheetWidgetFactory widgetFactory,
            ExtensibleGridPropertySection extensibleGridPropertySection) {

        super.createControl(composite, widgetFactory, extensibleGridPropertySection);
        Composite panel = widgetFactory.createComposite(composite);
        GridData layoutData = new GridData(SWT.BEGINNING, SWT.FILL, false, false);
        panel.setLayoutData(layoutData);
        GridLayout layout = new GridLayout(4, true);
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        panel.setLayout(layout);
        day = widgetFactory.createButton(panel, Messages.daysLabel, SWT.CHECK);
        day.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));

        hour = widgetFactory.createButton(panel, Messages.hoursLabel, SWT.CHECK);
        hour.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));

        min = widgetFactory.createButton(panel, Messages.minutesLabel, SWT.CHECK);
        min.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));

        sec = widgetFactory.createButton(panel, Messages.secondsLabel, SWT.CHECK);
        sec.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));

        expressionViewer.setMessage(Messages.data_tooltip_long,IStatus.INFO);
        bindDurationWidget();

    }

    private void bindDurationWidget() {
        dataBindingContext.bindValue(SWTObservables.observeSelection(day), EMFEditObservables.observeValue(editingDomain, widget, FormPackage.Literals.DURATION_FORM_FIELD__DAY));
        dataBindingContext.bindValue(SWTObservables.observeSelection(hour), EMFEditObservables.observeValue(editingDomain, widget, FormPackage.Literals.DURATION_FORM_FIELD__HOUR));
        dataBindingContext.bindValue(SWTObservables.observeSelection(min), EMFEditObservables.observeValue(editingDomain, widget, FormPackage.Literals.DURATION_FORM_FIELD__MIN));
        dataBindingContext.bindValue(SWTObservables.observeSelection(sec), EMFEditObservables.observeValue(editingDomain, widget, FormPackage.Literals.DURATION_FORM_FIELD__SEC));

    }


    @Override
    public boolean isRelevantFor(EObject eObject) {
        return eObject instanceof DurationFormField;
    }


}
