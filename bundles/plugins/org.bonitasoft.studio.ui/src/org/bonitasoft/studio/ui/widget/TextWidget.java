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

import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class TextWidget extends EditableControlWidget {

    public static class Builder extends EditableControlWidgetBuilder<Builder, TextWidget> {

        private Optional<String> placeholder = Optional.empty();
        private Optional<String> labelButton = Optional.empty();
        private Optional<Listener> buttonListner = Optional.empty();
        private Optional<Integer> delay = Optional.empty();

        /**
         * Adds a placeholder to the resulting {@link Text}
         */
        public Builder withPlaceholder(String placeholder) {
            this.placeholder = Optional.ofNullable(placeholder);
            return this;
        }

        /**
         * Create a button after the Text, with a label
         */
        public Builder withButton(String labelButton) {
            this.labelButton = Optional.ofNullable(labelButton);
            return this;
        }

        /**
         * add an onClick action to the button
         */
        public Builder onClickButton(Listener listener) {
            this.buttonListner = Optional.ofNullable(listener);
            return this;
        }

        public Builder withDelay(int delay) {
            this.delay = Optional.of(delay);
            return this;
        }

        @Override
        public TextWidget createIn(Composite container) {
            final TextWidget control = new TextWidget(container, labelAbove, horizontalLabelAlignment,
                    verticalLabelAlignment, labelWidth, readOnly, label,
                    message, labelButton);
            control.setLayoutData(layoutData != null ? layoutData : gridData);
            buttonListner.ifPresent(control::onCLickButton);
            placeholder.ifPresent(control::setPlaceholder);

            if (ctx != null && modelObservable != null) {
                control.bindControl(ctx,
                        delay.map(time -> control.observeText(time, SWT.Modify))
                                .orElse(control.observeText(SWT.Modify)),
                        modelObservable,
                        targetToModelStrategy,
                        modelToTargetStrategy);
            }
            return control;
        }

    }

    private Text text;
    private Optional<Button> button;

    protected TextWidget(Composite container, boolean topLabel, int horizontalLabelAlignment, int verticalLabelAlignment,
            int labelWidth, boolean readOnly, String label, String message, Optional<String> labelButton) {
        super(container, topLabel, horizontalLabelAlignment, verticalLabelAlignment, labelWidth, readOnly, label, message,
                labelButton);
    }

    public ISWTObservableValue observeText(int event) {
        return SWTObservables.observeText(text, event);
    }

    public ISWTObservableValue observeText(int delay, int event) {
        return SWTObservables.observeDelayedValue(delay, SWTObservables.observeText(text, event));
    }

    public void setPlaceholder(String placeholder) {
        text.setMessage(placeholder);
    }

    public void onCLickButton(Listener listener) {
        if (listener != null) {
            button.ifPresent(b -> b.addListener(SWT.Selection, listener));
        }
    }

    public void focusButton() {
        button.ifPresent(Button::setFocus);
    }

    public void focusText() {
        this.text.setFocus();
    }

    public String getText() {
        return text.getText();
    }

    @Override
    protected Control createControl() {
        final Composite textContainer = new Composite(this, SWT.NONE);
        textContainer.setLayout(GridLayoutFactory.fillDefaults().margins(1, 3).create());
        textContainer.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).span(labelAbove ? 2 : 1, 1).create());
        configureEnablement(textContainer);

        textContainer.addListener(SWT.Paint, e -> drawBorder(textContainer, e));

        text = new Text(textContainer, SWT.SINGLE);
        text.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).align(SWT.FILL, SWT.CENTER).create());
        configureEnablement(text);

        text.addListener(SWT.FocusIn, event -> redraw(textContainer));
        text.addListener(SWT.FocusOut, event -> redraw(textContainer));

        button = buttonLabel.map(label -> {
            final Button b = new Button(this, SWT.PUSH);
            b.setText(label);
            return b;
        });
        button.ifPresent(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL)::applyTo);
        return textContainer;
    }

    public void addTextListener(int eventType, Listener listener) {
        text.addListener(eventType, listener);
    }

    public void configureEnablement(Control control) {
        final Color backgroundColor = Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND);
        final Color whiteColor = Display.getDefault().getSystemColor(SWT.COLOR_WHITE);
        control.setBackground(readOnly ? backgroundColor : whiteColor);
        control.setEnabled(!readOnly);
    }

    public void setText(String text) {
        this.text.setText(text);
    }

}
