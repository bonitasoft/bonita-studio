/**
 * Copyright (C) 2016 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.ui.widget;

import java.util.Optional;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class CComboWidget extends EditableControlWidget {

    public static class Builder extends EditableControlWidgetBuilder<Builder, CComboWidget> {

        private String[] items;

        public Builder withItems(String... items) {
            this.items = items;
            return this;
        }

        @Override
        public CComboWidget createIn(Composite container) {
            final CComboWidget control = new CComboWidget(container, id, labelAbove, horizontalLabelAlignment,
                    verticalLabelAlignment, labelWidth, readOnly, label,
                    message, useCompositeMessageDecorator, toolkit);
            control.init();
            control.setLayoutData(layoutData != null ? layoutData : gridData);
            if (items != null) {
                control.setItems(items);
            }
            if (ctx != null && modelObservable != null) {
                control.bindControl(ctx, control.observeComboText(), modelObservable, targetToModelStrategy,
                        modelToTargetStrategy);
                validator.ifPresent(v -> control.bindValidator(ctx, control.observeComboText(),
                        modelObservable,
                        v));
            }
            return control;
        }
    }

    private CCombo combo;

    protected CComboWidget(Composite container,
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

    public IObservableList<String> observeComboItems() {
        return WidgetProperties.items().observe(combo);
    }

    @Override
    public void adapt(FormToolkit toolkit) {
        super.adapt(toolkit);
        toolkit.adapt(combo.getParent().getParent());
        toolkit.adapt(combo, true, true);
    }

    @Override
    protected Control createControl() {
        final Composite container = new Composite(this, SWT.NONE);
        container.setLayout(GridLayoutFactory.fillDefaults().margins(1, 1).create());
        container.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(labelAbove ? 2 : 1, 1).create());
        container.setBackground(
                readOnly ? Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND)
                        : Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
        container.addListener(SWT.Paint, e -> drawBorder(container, e));

        int textStyle = 0;
        if (readOnly) {
            textStyle = SWT.READ_ONLY;
        }

        combo = new CCombo(container, textStyle);
        combo.setData(SWTBOT_WIDGET_ID_KEY, id);
        combo.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
        combo.addListener(SWT.FocusIn, event -> redraw(container));
        combo.addListener(SWT.FocusOut, event -> redraw(container));
        combo.setEditable(!readOnly);

        return container;
    }

    public void setItems(String[] items) {
        combo.setItems(items);
    }

    public CCombo getCombo() {
        return combo;
    }
}
