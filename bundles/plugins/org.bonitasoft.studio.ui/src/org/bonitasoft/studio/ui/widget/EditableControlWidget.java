/*******************************************************************************
 * Copyright (C) 2016 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel � 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.ui.widget;

import java.util.Optional;

import org.bonitasoft.studio.ui.ColorConstants;
import org.bonitasoft.studio.ui.databinding.ControlMessageSupport;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;

public abstract class EditableControlWidget extends ControlWidget {

    private LocalResourceManager resourceManager;
    private Color errorColor;
    private Color warningColor;

    protected EditableControlWidget(Composite parent, boolean labelAbove, int horizontalLabelAlignment,
            int verticalLabelAlignment, int labelHint,
            boolean readOnly, String labelValue, String message) {
        super(parent, labelAbove, horizontalLabelAlignment, verticalLabelAlignment, labelHint, labelValue, message);
        initEditable(parent, labelAbove, horizontalLabelAlignment, verticalLabelAlignment, labelHint, readOnly, labelValue,
                message);
    }

    protected EditableControlWidget(Composite parent, boolean labelAbove, int horizontalLabelAlignment,
            int verticalLabelAlignment, int labelHint,
            boolean readOnly, String labelValue, String message, Optional<String> buttonLabel) {
        super(parent, labelAbove, horizontalLabelAlignment, verticalLabelAlignment, labelHint, readOnly, labelValue, message,
                buttonLabel);
        initEditable(parent, labelAbove, horizontalLabelAlignment, verticalLabelAlignment, labelHint, readOnly, labelValue,
                message);
    }

    protected void initEditable(Composite parent, boolean labelAbove, int horizontalLabelAlignment,
            int verticalLabelAlignment, int labelHint, boolean readOnly, String labelValue, String message) {
        this.readOnly = readOnly;
        this.resourceManager = new LocalResourceManager(JFaceResources.getResources(), parent);
        errorColor = resourceManager.createColor(ColorConstants.ERROR_RGB);
        warningColor = resourceManager.createColor(ColorConstants.WARNING_RGB);

        messageLabel = new CLabel(this, SWT.NONE);
        messageLabel
                .setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.FILL)
                        .span(buttonLabel.isPresent() ? 2 : 1, 1).create());
        messageLabel.setForeground(getStatusColor(status));
        messageLabel.setText(message);
    }

    @Override
    protected abstract Control createControl();

    protected Color getStatusColor(IStatus status) {
        return status.getSeverity() == IStatus.WARNING ? warningColor
                : status.getSeverity() == IStatus.ERROR ? errorColor
                        : Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_FOREGROUND);
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

    private Image getStatusImage(IStatus status) {
        return status.getSeverity() == IStatus.WARNING ? JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING)
                : status.getSeverity() == IStatus.ERROR ? JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR) : null;
    }

    protected ControlMessageSupport bindControl(DataBindingContext ctx, IObservableValue controlObservable,
            IObservableValue modelObservable,
            UpdateValueStrategy targetToModel,
            UpdateValueStrategy modelToTarget) {
        return new ControlMessageSupport(ctx.bindValue(controlObservable, modelObservable,
                targetToModel, modelToTarget)) {

            @Override
            protected void statusChanged(IStatus status) {
                EditableControlWidget.this.status = status;
                if (status == null || status.isOK()) {
                    messageLabel.setText(message != null ? message : "");
                } else {
                    messageLabel.setText(status.getMessage());
                }
                messageLabel.setImage(getStatusImage(status));
                messageLabel.setForeground(getStatusColor(status));
                redraw(control);
                EditableControlWidget.this.layout();
            }

        };
    }

    protected void redraw(final Control toRedraw) {
        toRedraw.getDisplay().asyncExec(() -> {
            toRedraw.redraw();
        });
    }
}
