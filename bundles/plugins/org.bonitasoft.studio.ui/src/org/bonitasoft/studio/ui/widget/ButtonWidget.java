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
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Listener;

/**
 * API to create and parameterize a button
 *
 * @author Adrien lachambre
 */
public class ButtonWidget extends ControlWidget {

    public static class Builder extends ControlWidgetBuilder<Builder, ButtonWidget> {

        private Listener listener;

        /**
         * Add a {@link Listener} to this button
         */
        public Builder onClick(Listener listener) {
            this.listener = listener;
            return this;
        }

        @Override
        public ButtonWidget createIn(Composite container) {
            final ButtonWidget control = new ButtonWidget(container);
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

    protected ButtonWidget(Composite parent) {
        super(parent);
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

    public IObservableValue observeEnabled() {
        return SWTObservables.observeEnabled(button);
    }

    @Override
    protected Control createControl() {
        button = new Button(this, SWT.PUSH);
        button.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        return this;
    }
}
