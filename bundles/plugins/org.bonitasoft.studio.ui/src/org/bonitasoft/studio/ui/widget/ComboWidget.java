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

import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

public class ComboWidget extends EditableControlWidget {

    public static class Builder extends EditableControlWidgetBuilder<Builder, ComboWidget> {

        private String[] items;

        public Builder() {
        }

        public Builder withItems(String... items) {
            this.items = items;
            return this;
        }

        @Override
        public ComboWidget createIn(Composite container) {
            final ComboWidget control = new ComboWidget(container, labelAbove, horizontalLabelAlignment, verticalLabelAlignment, labelWidth, readOnly, label,
                    message);
            if (layoutData != null) {
                control.setLayoutData(layoutData);
            }
            control.setItems(items);
            if (ctx != null && modelObservable != null) {
                control.bindControl(ctx, control.observeComboText(), modelObservable, targetToModelStrategy, modelToTargetStrategy);
            }
            return control;
        }
    }

    private CCombo combo;

    protected ComboWidget(Composite container, boolean topLabel, int horizontalLabelAlignment, int verticalLabelAlignment, int labelWidth, boolean readOnly,
            String label, String message) {
        super(container, topLabel, horizontalLabelAlignment, verticalLabelAlignment, labelWidth, readOnly, label, message);
    }

    public ISWTObservableValue observeComboText() {
        return SWTObservables.observeText(combo);
    }

    @Override
    protected Control createControl() {
        final Composite container = new Composite(this, SWT.NONE);
        container.setLayout(GridLayoutFactory.fillDefaults().margins(1, 3).create());
        container.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        container.setBackground(
                readOnly ? Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND) : Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
        container.addListener(SWT.Paint, e -> drawBorder(container, e));

        int textStyle = 0;
        if (readOnly) {
            textStyle = SWT.READ_ONLY;
        }

        combo = new CCombo(container, SWT.SINGLE | textStyle);
        combo.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        combo.addListener(SWT.FocusIn, event -> redraw(container));
        combo.addListener(SWT.FocusOut, event -> redraw(container));
        combo.setEditable(!readOnly);

        return container;
    }

    public void setItems(String[] items) {
        combo.setItems(items);
    }
}
