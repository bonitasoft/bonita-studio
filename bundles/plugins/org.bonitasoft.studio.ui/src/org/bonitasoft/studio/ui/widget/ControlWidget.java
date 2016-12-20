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

import org.bonitasoft.studio.ui.databinding.ControlMessageSupport;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;

public abstract class ControlWidget extends Composite {

    private static final RGB ERROR_RGB = new RGB(214, 77, 77);
    private static final RGB WARNING_RGB = new RGB(155, 170, 20);
    private Label label;
    private final CLabel messageLabel;
    private final LocalResourceManager resourceManager;
    private final Color errorColor;
    private final Color warningColor;
    protected IStatus status = ValidationStatus.ok();
    private final String message;
    protected boolean readOnly;
    private final Control control;

    protected ControlWidget(Composite parent,
            boolean labelAbove,
            int horizontalLabelAlignment,
            int verticalLabelAlignment,
            int labelHint,
            boolean readOnly,
            String labelValue,
            String message) {
        super(parent, SWT.NONE);
        this.message = message;
        this.readOnly = readOnly;
        this.resourceManager = new LocalResourceManager(JFaceResources.getResources(), parent);
        errorColor = resourceManager.createColor(ERROR_RGB);
        warningColor = resourceManager.createColor(WARNING_RGB);
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

        messageLabel = new CLabel(this, SWT.NONE);
        messageLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.FILL).create());
        messageLabel.setForeground(getStatusColor(status));
        messageLabel.setText(message);
    }

    protected abstract Control createControl();

    protected void drawBorder(final Composite container, Event e) {
        final GC gc = e.gc;
        final Display display = e.display;
        if (display != null && gc != null && !gc.isDisposed()) {
            final Control focused = display.getFocusControl();
            gc.setAdvanced(true);
            gc.setForeground(getBorderColor(focused, container));
            gc.setLineWidth(1);
            final Rectangle r = container.getBounds();
            gc.drawRectangle(0, 0, r.width - 1, r.height - 1);
        }
    }

    protected Color getStatusColor(IStatus status) {
        return status.getSeverity() == IStatus.WARNING ? warningColor
                : status.getSeverity() == IStatus.ERROR ? errorColor : Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_FOREGROUND);
    }

    private Image getStatusImage(IStatus status) {
        return status.getSeverity() == IStatus.WARNING ? JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING)
                : status.getSeverity() == IStatus.ERROR ? JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR) : null;
    }

    protected Color getBorderColor(Control focused, Control container) {
        if (status.isOK() || status.getSeverity() == IStatus.INFO) {
            if (focused != null && focused.getParent() != null && focused.getParent().equals(container)) {
                return container.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BORDER);
            } else {
                return container.getDisplay().getSystemColor(SWT.COLOR_GRAY);
            }
        }
        return getStatusColor(status);
    }

    protected void redraw(final Control toRedraw) {
        toRedraw.getDisplay().asyncExec(() -> {
            toRedraw.redraw();
        });
    }

    protected ControlMessageSupport bindControl(DataBindingContext ctx, IObservableValue controlObservable, IObservableValue modelObservable,
            UpdateValueStrategy targetToModel,
            UpdateValueStrategy modelToTarget) {
        return new ControlMessageSupport(ctx.bindValue(controlObservable, modelObservable,
                targetToModel, modelToTarget)) {

            @Override
            protected void statusChanged(IStatus status) {
                ControlWidget.this.status = status;
                if (status == null || status.isOK()) {
                    messageLabel.setText(message != null ? message : "");
                } else {
                    messageLabel.setText(status.getMessage());
                }
                messageLabel.setImage(getStatusImage(status));
                messageLabel.setForeground(getStatusColor(status));
                redraw(control);
                ControlWidget.this.layout();
            }

        };
    }

}
