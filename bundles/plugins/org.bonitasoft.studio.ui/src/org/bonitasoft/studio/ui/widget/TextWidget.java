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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Text;

public class TextWidget extends EditableControlWidget {

    public static class Builder extends EditableControlWidgetBuilder<Builder, TextWidget> {

        private String placeholder;

        public Builder() {
        }

        /**
         * Adds a placeholder to the resulting {@link Text}
         */
        public Builder withPlaceholder(String placeholder) {
            this.placeholder = placeholder;
            return this;
        }

        @Override
        public TextWidget createIn(Composite container) {
            final TextWidget control = new TextWidget(container, labelAbove, horizontalLabelAlignment, verticalLabelAlignment, labelWidth, readOnly, label,
                    message);
            if (layoutData != null) {
                control.setLayoutData(layoutData);
            } else {
                control.setLayoutData(gridData);
            }
            if (placeholder != null) {
                control.setPlaceholder(placeholder);
            }
            if (ctx != null && modelObservable != null) {
                control.bindControl(ctx, control.observeText(SWT.Modify), modelObservable, targetToModelStrategy, modelToTargetStrategy);
            }
            return control;
        }
    }

    private Text text;

    protected TextWidget(Composite container, boolean topLabel, int horizontalLabelAlignment, int verticalLabelAlignment, int labelWidth, boolean readOnly,
            String label, String message) {
        super(container, topLabel, horizontalLabelAlignment, verticalLabelAlignment, labelWidth, readOnly, label, message);
    }

    public ISWTObservableValue observeText(int event) {
        return SWTObservables.observeText(text, event);
    }

    public void setPlaceholder(String placeholder) {
        text.setMessage(placeholder);
    }

    @Override
    protected Control createControl() {
        final Composite textContainer = new Composite(this, SWT.NONE);
        textContainer.setLayout(GridLayoutFactory.fillDefaults().margins(1, 3).create());
        textContainer.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        textContainer.setBackground(
                readOnly ? Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND) : Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
        textContainer.addListener(SWT.Paint, e -> drawBorder(textContainer, e));

        int textStyle = 0;
        if (readOnly) {
            textStyle = SWT.READ_ONLY;
        }

        text = new Text(textContainer, SWT.SINGLE | textStyle);
        text.setEditable(!readOnly);
        text.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        text.setBackground(
                readOnly ? Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND) : Display.getDefault().getSystemColor(SWT.COLOR_WHITE));
        text.addListener(SWT.FocusIn, event -> redraw(textContainer));
        text.addListener(SWT.FocusOut, event -> redraw(textContainer));
        return textContainer;
    }

}
