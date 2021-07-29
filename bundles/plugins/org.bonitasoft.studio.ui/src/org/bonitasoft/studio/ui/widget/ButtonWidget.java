/*******************************************************************************
 * Copyright (C) 2016 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.widget;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * API to create and parameterize a button
 *
 * @author Adrien lachambre
 */
public class ButtonWidget extends ControlWidget {

    public static class Builder extends ControlWidgetBuilder<Builder, ButtonWidget> {

        private Listener listener;
        private int style = SWT.PUSH;

        /**
         * Add a {@link Listener} to this button
         */
        public Builder onClick(Listener listener) {
            this.listener = listener;
            return this;
        }

        /**
         * Default value: {@link SWT.PUSH}
         */
        public Builder withStyle(int style) {
            this.style = style;
            return this;
        }

        @Override
        public ButtonWidget createIn(Composite container) {
            final ButtonWidget control = new ButtonWidget(container, style);
            control.init();
            control.setLayoutData(layoutData != null ? layoutData : gridData);
            if (this.label != null) {
                control.setText(label);
            }
            if (listener != null) {
                control.onClick(listener);
            }
            return control;
        }
    }

    private Button button;
    private int style;

    protected ButtonWidget(Composite parent, int style) {
        super(parent);
        this.style = style;
    }

    @Override
    protected int numColumn() {
        return 1;
    }

    /**
     * enable the possibility to click on the button
     */
    public void enable() {
        button.setEnabled(true);
    }

    /**
     * disable the possibility to click on the button
     */
    public void disable() {
        button.setEnabled(false);
    }

    public void onClick(Listener listener) {
        button.addListener(SWT.Selection, listener);
    }

    public void setText(String text) {
        this.button.setText(text);
    }

    public IObservableValue<Boolean> observeEnabled() {
        return WidgetProperties.enabled().observe(button);
    }

    @Override
    protected Control createControl() {
        button = new Button(this, style);
        button.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        return this;
    }

    @Override
    protected void adapt(FormToolkit toolkit) {
        toolkit.adapt(button, true, true);
        toolkit.adapt(this, true, true);
    }

    public Button getButton() {
        return button;
    }
}
