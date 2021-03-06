/**
 * Copyright (C) 2017 Bonitasoft S.A.
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

import org.bonitasoft.studio.ui.ColorConstants;
import org.bonitasoft.studio.ui.databinding.ControlMessageSupport;
import org.bonitasoft.studio.ui.databinding.UpdateStrategyFactory;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.forms.widgets.FormToolkit;

public abstract class EditableControlWidget extends ControlWidget {

    public static final int MESSAGE_GRAY_STYLE = 1;
    public static final int MESSAGE_DEFAULT_STYLE = 0;

    protected final LocalResourceManager resourceManager;
    private final Color errorColor;
    private final Color warningColor;
    private Binding valueBinding;
    private boolean useCompositeMessageDecorator;
    private WidgetMessageDecorator messageDecorator;
    protected IStatus status = Status.OK_STATUS;

    protected EditableControlWidget(Composite parent, String id, boolean labelAbove, int horizontalLabelAlignment,
            int verticalLabelAlignment, int labelHint, boolean readOnly, String labelValue, String message,
            boolean useCompositeMessageDecorator) {
        this(parent, id, labelAbove, horizontalLabelAlignment, verticalLabelAlignment, labelHint, readOnly, labelValue,
                message, useCompositeMessageDecorator, Optional.empty(), Optional.empty());
    }

    protected EditableControlWidget(Composite parent, String id, boolean labelAbove, int horizontalLabelAlignment,
            int verticalLabelAlignment, int labelHint, boolean readOnly, String labelValue, String message,
            boolean useCompositeMessageDecorator, Optional<String> buttonLabel, Optional<FormToolkit> toolkit) {
        super(parent, id, labelAbove, horizontalLabelAlignment, verticalLabelAlignment, labelHint, readOnly, labelValue,
                message, buttonLabel, toolkit);
        this.resourceManager = new LocalResourceManager(JFaceResources.getResources(), parent);
        this.useCompositeMessageDecorator = useCompositeMessageDecorator;
        errorColor = resourceManager.createColor(ColorConstants.ERROR_RGB);
        warningColor = resourceManager.createColor(ColorConstants.WARNING_RGB);
    }

    @Override
    protected void init() {
        super.init();
        createMessageDecorator();
    }

    private void createMessageDecorator() {
        messageDecorator = useCompositeMessageDecorator
                ? new StaticWidgetMessageDecorator(this, message)
                : new WidgetMessageDecorator(this, message);
        messageDecorator
                .setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.FILL).grab(true, false)
                        .span(messageLabelHorizontalSpan(labelAbove), 1).indent(0, messageDecoratorVerticalIndent())
                        .create());
        messageDecorator.setStatus(status);
        if (toolkit.isPresent()) {
            messageDecorator.adapt(toolkit.get());
        }
    }

    protected int messageDecoratorVerticalIndent() {
        return -2;
    }

    protected int messageLabelHorizontalSpan(boolean labelAbove) {
        return buttonLabel.isPresent() ? horizontalSpanWithButton(labelAbove) : 1;
    }

    protected int horizontalSpanWithButton(boolean labelAbove) {
        return labelAbove ? 3 : 2;
    }

    @Override
    protected abstract Control createControl();

    protected Color selectedBorderColor(Control container) {
        return container.getDisplay().getSystemColor(SWT.COLOR_TITLE_BACKGROUND);
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

    protected Color getStatusColor(IStatus status) {
        if (status == null) {
            return Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_FOREGROUND);
        }
        return status.getSeverity() == IStatus.WARNING ? warningColor
                : status.getSeverity() == IStatus.ERROR ? errorColor
                        : Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_FOREGROUND);
    }

    protected Color getBorderColor(Control focused, Control container) {
        if (status.isOK() || status.getSeverity() == IStatus.INFO) {
            if (focused != null && focused.getParent() != null && focused.getParent().equals(container)) {
                return selectedBorderColor(container);
            }
            return container.getDisplay().getSystemColor(SWT.COLOR_GRAY);
        }
        return getStatusColor(status);
    }

    @Override
    protected void adapt(FormToolkit toolkit) {
        label.ifPresent(label -> toolkit.adapt(label, true, true));
        filler.ifPresent(filler -> toolkit.adapt(filler, true, true));
        toolkit.adapt(this);
    }

    protected ControlMessageSupport bindControl(DataBindingContext ctx, IObservableValue controlObservable,
            IObservableValue modelObservable,
            UpdateValueStrategy targetToModel,
            UpdateValueStrategy modelToTarget) {
        valueBinding = ctx.bindValue(controlObservable, modelObservable,
                targetToModel, modelToTarget);
        return new ControlMessageSupport(valueBinding) {

            @Override
            protected void statusChanged(IStatus status) {
                EditableControlWidget.this.statusChanged(status);
            }

        };
    }

    protected ControlMessageSupport bindValidator(DataBindingContext ctx, IObservableValue controlObservable,
            IObservableValue modelObservable, IValidator validator) {
        final UpdateValueStrategy validateOnlyStrategy = UpdateStrategyFactory.convertUpdateValueStrategy()
                .withValidator(validator).create();
        Binding binding = ctx.bindValue(controlObservable, modelObservable,
                validateOnlyStrategy, validateOnlyStrategy);
        ControlMessageSupport controlMessageSupport = new ControlMessageSupport(binding) {

            @Override
            protected void statusChanged(IStatus status) {
                EditableControlWidget.this.statusChanged(status);
            }

        };
        binding.validateTargetToModel();
        return controlMessageSupport;
    }

    protected void statusChanged(IStatus status) {
        EditableControlWidget.this.status = status;
        if (status.getException() != null) {
            status.getException().printStackTrace();
        }
        if (messageDecorator == null) {
            createMessageDecorator();
        } else {
            messageDecorator.setStatus(status);
        }
        redraw(control);
    }

    public Binding getValueBinding() {
        return valueBinding;
    }

    protected void redraw(final Control toRedraw) {
        toRedraw.getDisplay().asyncExec(() -> {
            if (toRedraw != null && !toRedraw.isDisposed()) {
                toRedraw.redraw();
            }
        });
    }

    public void setMessage(String message) {
        setMessage(message, null);
    }
    
    public void setMessage(String message, Image icon) {
        this.message = Optional.ofNullable(message);
        if (messageDecorator == null) {
            createMessageDecorator();
        } else {
            messageDecorator.setMessage(this.message);
        }
        messageDecorator.setImage(icon);
        messageDecorator.setStatus(null);
    }

    public IStatus getStatus() {
        return status;
    }
}
