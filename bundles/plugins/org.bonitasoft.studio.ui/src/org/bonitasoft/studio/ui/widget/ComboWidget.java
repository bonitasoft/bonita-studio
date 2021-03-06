/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.widget;

import java.util.Optional;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class ComboWidget extends EditableControlWidget {

    public static class Builder extends EditableControlWidgetBuilder<Builder, ComboWidget> {

        protected String[] items;

        public Builder withItems(String... items) {
            this.items = items;
            return this;
        }

        @Override
        public ComboWidget createIn(Composite container) {
            final ComboWidget control = new ComboWidget(container, id, labelAbove, horizontalLabelAlignment,
                    verticalLabelAlignment, labelWidth, readOnly, label, message, useCompositeMessageDecorator,
                    toolkit);
            control.init();
            control.setLayoutData(layoutData != null ? layoutData : gridData);
            if (items != null) {
                control.setItems(items);
            }
            if (ctx != null && modelObservable != null) {
                control.bindControl(ctx,
                        delay.map(time -> control.observeComboText(time))
                                .orElse(control.observeComboText()),
                        modelObservable,
                        targetToModelStrategy,
                        modelToTargetStrategy);
                validator.ifPresent(v -> control.bindValidator(ctx,
                        delay.map(time -> control.observeComboText(time))
                                .orElse(control.observeComboText(SWT.Modify)),
                        modelObservable,
                        v));
            }
            return control;
        }

    }

    private Combo combo;

    protected ComboWidget(Composite container,
            String id,
            boolean topLabel,
            int horizontalLabelAlignment,
            int verticalLabelAlignment,
            int labelWidth,
            boolean readOnly,
            String label,
            String message,
            boolean useCompositeMessageDecorator,
            Optional<FormToolkit> toolkit) {
        super(container, id, topLabel, horizontalLabelAlignment, verticalLabelAlignment, labelWidth, readOnly, label,
                message, useCompositeMessageDecorator, Optional.empty(), toolkit);
    }

    public IObservableValue<String> observeComboText() {
        return WidgetProperties.text().observe(combo);
    }

    public IObservableValue<String> observeComboText(int delay) {
        return WidgetProperties.text().observeDelayed(delay, combo);
    }

    public IObservableList<String> observeComboItems() {
        return WidgetProperties.items().observe(combo);
    }

    @Override
    public void adapt(FormToolkit toolkit) {
        super.adapt(toolkit);
        toolkit.adapt(combo.getParent());
        toolkit.adapt(combo, true, true);
    }

    @Override
    protected Control createControl() {
        final Composite container = new Composite(this, SWT.NONE);
        container.setLayout(GridLayoutFactory.fillDefaults().margins(1, 1).create());
        container.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(labelAbove ? 2 : 1, 1).create());

        int textStyle = 0;
        if (readOnly) {
            textStyle = SWT.READ_ONLY;
        }

        combo = new Combo(container, textStyle);
        combo.setData(SWTBOT_WIDGET_ID_KEY, id);
        combo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());

        return container;
    }

    public void setItems(String[] items) {
        combo.setItems(items);
    }

    public Combo getCombo() {
        return combo;
    }

    public IObservableValue<Boolean> observeEnable() {
        return WidgetProperties.enabled().observe(combo);
    }
}
