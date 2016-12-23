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

import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public abstract class ControlWidget extends Composite {

    protected Label label;
    protected CLabel messageLabel;

    protected IStatus status = ValidationStatus.ok();
    protected String message;
    protected boolean readOnly;
    protected final Control control;

    protected ControlWidget(Composite parent,
            boolean labelAbove,
            int horizontalLabelAlignment,
            int verticalLabelAlignment,
            int labelHint,
            String labelValue,
            String message) {
        super(parent, SWT.NONE);
        this.message = message;
        setLayout(GridLayoutFactory.fillDefaults()
                .numColumns(1)
                .spacing(LayoutConstants.getSpacing().x, 1)
                .create());

        final Optional<String> labelText = Optional.ofNullable(labelValue);
        labelText.ifPresent(text -> {
            label = new Label(this, SWT.NONE);
            label.setLayoutData(GridDataFactory.swtDefaults().align(labelAbove ? SWT.LEFT : horizontalLabelAlignment, verticalLabelAlignment).create());
            label.setText(text);
            setLayout(GridLayoutFactory.fillDefaults()
                    .numColumns(labelAbove ? 1 : 2)
                    .spacing(LayoutConstants.getSpacing().x, 1)
                    .create());
        });

        control = createControl();

        labelText.ifPresent(text -> { //Create a filler label
            new Label(this, SWT.NONE).setLayoutData(GridDataFactory.swtDefaults().hint(labelHint, SWT.DEFAULT).exclude(labelAbove).create());
        });
    }

    protected ControlWidget(Composite parent) {
        super(parent, SWT.NONE);
        setLayout(GridLayoutFactory.fillDefaults()
                .numColumns(1)
                .spacing(LayoutConstants.getSpacing().x, 1)
                .create());
        control = createControl();
    }

    protected abstract Control createControl();
}
