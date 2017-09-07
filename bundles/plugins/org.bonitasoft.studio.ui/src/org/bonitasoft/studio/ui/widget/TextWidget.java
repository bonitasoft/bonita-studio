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

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import org.bonitasoft.studio.common.jface.SWTBotConstants;
import org.bonitasoft.studio.ui.ColorConstants;
import org.bonitasoft.studio.ui.i18n.Messages;
import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.jface.fieldassist.FieldDecorationRegistry;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.TextContentAdapter;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class TextWidget extends EditableControlWidget {

    public static class Builder extends EditableControlWidgetBuilder<Builder, TextWidget> {

        protected Optional<String> placeholder = Optional.empty();
        protected Optional<String> labelButton = Optional.empty();
        protected Optional<Listener> buttonListner = Optional.empty();
        protected Optional<Integer> delay = Optional.empty();
        protected boolean transactionalEdit = false;
        private BiConsumer<String, String> onEdit;
        private Optional<IContentProposalProvider> proposalProvider = Optional.empty();
        private Optional<String> tooltip = Optional.empty();

        /**
         * Adds a placeholder to the resulting {@link Text}
         */
        public Builder withPlaceholder(String placeholder) {
            this.placeholder = Optional.ofNullable(placeholder);
            return this;
        }

        public Builder withTootltip(String tooltip) {
            this.tooltip = Optional.ofNullable(tooltip);
            return this;
        }

        /**
         * Create a button after the Text, with a label
         */
        public Builder withButton(String labelButton) {
            this.labelButton = Optional.ofNullable(labelButton);
            return this;
        }

        public Builder transactionalEdit() {
            this.transactionalEdit = true;
            return this;
        }

        public Builder transactionalEdit(BiConsumer<String /* old value */, String /* new value */> onEdit) {
            this.transactionalEdit = true;
            this.onEdit = onEdit;
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

        public Builder withProposalProvider(IContentProposalProvider proposalProvider) {
            this.proposalProvider = Optional.of(proposalProvider);
            return this;
        }

        @Override
        public TextWidget createIn(Composite container) {
            if (transactionalEdit && targetToModelStrategy == null) {
                throw new IllegalStateException("A target to model strategy is required with transactionalEdit");
            }
            if (transactionalEdit && targetToModelStrategy != null
                    && targetToModelStrategy.getUpdatePolicy() != UpdateValueStrategy.POLICY_CONVERT) {
                throw new IllegalStateException(
                        "Target to model strategy must have a POLICY_CONVERT strategy with transactionalEdit");
            }
            final TextWidget control = new TextWidget(container,
                    id,
                    labelAbove,
                    horizontalLabelAlignment,
                    verticalLabelAlignment,
                    labelWidth,
                    readOnly,
                    label,
                    message,
                    labelButton,
                    transactionalEdit,
                    onEdit,
                    toolkit,
                    proposalProvider);
            control.init();
            control.setLayoutData(layoutData != null ? layoutData : gridData);
            buttonListner.ifPresent(control::onCLickButton);
            placeholder.ifPresent(control::setPlaceholder);
            tooltip.ifPresent(control::setTooltip);

            if (ctx != null && modelObservable != null) {
                control.bindControl(ctx,
                        delay.map(time -> control.observeText(time, SWT.Modify))
                                .orElse(control.observeText(SWT.Modify)),
                        modelObservable,
                        targetToModelStrategy,
                        modelToTargetStrategy);
                validator.ifPresent(v -> control.bindValidator(ctx, delay.map(time -> control.observeText(time, SWT.Modify))
                        .orElse(control.observeText(SWT.Modify)),
                        modelObservable,
                        v));
            }
            return control;
        }

    }

    private Text text;
    private Optional<Button> button;
    private final boolean transactionalEdit;
    private final Optional<BiConsumer<String, String>> onEdit;
    private boolean editing = false;
    private final Color editingColor;
    private ToolItem okButton;
    private final Optional<IContentProposalProvider> proposalProvider;

    protected TextWidget(Composite container, String id, boolean topLabel, int horizontalLabelAlignment,
            int verticalLabelAlignment,
            int labelWidth, boolean readOnly, String label, String message, Optional<String> labelButton,
            boolean transactionalEdit, BiConsumer<String, String> onEdit, Optional<FormToolkit> toolkit,
            Optional<IContentProposalProvider> proposalProvider) {
        super(container, id, topLabel, horizontalLabelAlignment, verticalLabelAlignment, labelWidth, readOnly, label,
                message,
                labelButton,
                toolkit);
        this.transactionalEdit = transactionalEdit;
        this.onEdit = Optional.ofNullable(onEdit);
        this.proposalProvider = proposalProvider;
        this.editingColor = resourceManager.createColor(ColorConstants.EDITING_RGB);
    }

    @Override
    protected int numColumn() {
        return buttonLabel.isPresent() || transactionalEdit ? 3 : 2;
    }

    @Override
    protected int horizontalSpacing() {
        return labelAbove && transactionalEdit ? 1 : LayoutConstants.getSpacing().x;
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

    public void setTooltip(String tooltip) {
        ControlDecoration controlDecoration = null;
        if (label.isPresent()) {
            controlDecoration = new ControlDecoration(label.get(), SWT.RIGHT);
        } else {
            controlDecoration = new ControlDecoration(text, SWT.TOP | SWT.LEFT);
        }
        controlDecoration.setMarginWidth(5);
        controlDecoration.setShowOnlyOnFocus(false);
        controlDecoration.setImage(FieldDecorationRegistry.getDefault()
                .getFieldDecoration(FieldDecorationRegistry.DEC_INFORMATION).getImage());
        controlDecoration.setDescriptionText(tooltip);
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

    public TextWidget setLabelColor(Color color) {
        label.ifPresent(label -> label.setForeground(color));
        return this;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.ui.widget.EditableControlWidget#selectedBorderColor(org.eclipse.swt.widgets.Control)
     */
    @Override
    protected Color selectedBorderColor(Control container) {
        return editing ? editingColor : super.selectedBorderColor(container);
    }

    @Override
    protected Control createControl() {
        final Composite textContainer = new Composite(this, SWT.NONE);
        textContainer
                .setLayout(GridLayoutFactory.fillDefaults().margins(1, 3).spacing(0, 0)
                        .create());
        textContainer.setLayoutData(
                GridDataFactory.fillDefaults().align(SWT.FILL, verticalAlignment()).grab(true, grabVerticalSpace())
                        .span(labelAbove ? 2 : 1, 1)
                        .create());
        readOnly = readOnly || transactionalEdit;
        configureBackground(textContainer);

        textContainer.addListener(SWT.Paint, e -> drawBorder(textContainer, e));

        text = newText(textContainer);
        text.setData(SWTBOT_WIDGET_ID_KEY, id);
        configureBackground(text);

        proposalProvider.ifPresent(provider -> {
            final TextContentAdapter controlContentAdapter = new TextContentAdapter();
            final KeyStroke keyStroke = KeyStroke.getInstance(SWT.MOD1, SWT.SPACE);
            final ContentProposalAdapter proposalAdapter = new ContentProposalAdapter(text,
                    controlContentAdapter,
                    provider,
                    keyStroke,
                    null);
            proposalAdapter.setPropagateKeys(true);
            proposalAdapter.setProposalAcceptanceStyle(ContentProposalAdapter.PROPOSAL_REPLACE);
            proposalAdapter.setAutoActivationDelay(0);
            text.addListener(SWT.FocusIn, e -> fireControlSpaceEvent(proposalAdapter));
            text.addListener(SWT.Modify, e -> {
                if (text.isFocusControl()) {
                    fireControlSpaceEvent(proposalAdapter);
                }
            });
        });

        text.addListener(SWT.FocusIn, event -> redraw(textContainer));
        text.addListener(SWT.FocusOut, event -> redraw(textContainer));
        text.addListener(SWT.Paint, event -> {
            if (!text.isEnabled()) {
                final GC gc = event.gc;

                final String textString = text.getText();
                final Rectangle bounds = text.getBounds();
                final Point caretLocation = text.getCaretLocation();

                gc.setBackground(text.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
                gc.setForeground(text.getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY));
                gc.fillRectangle(0, 0, bounds.width, bounds.height);
                gc.drawText(textString, bounds.x + 1, caretLocation.y);
            }

        });

        if (transactionalEdit) {
            final ToolBar toolBar = new ToolBar(this, SWT.INHERIT_DEFAULT | SWT.NO_FOCUS);
            toolBar.setLayoutData(GridDataFactory.fillDefaults().create());
            if (toolkit.isPresent()) {
                toolkit.get().adapt(toolBar, true, true);
            }
            createEditItem(toolBar);
            text.addListener(SWT.FocusOut, event -> {
                if (Objects.equals(event.widget, text)) {
                    cancel(toolBar);
                }
            });
            text.addTraverseListener(new TraverseListener() {

                @Override
                public void keyTraversed(TraverseEvent e) {
                    if (editing && e.character == SWT.CR) {
                        accept(toolBar);
                        e.doit = false;
                    }
                }
            });
        }

        button = buttonLabel.map(label -> {
            final Button b = new Button(this, SWT.PUSH);
            b.setText(label);
            return b;
        });
        button.ifPresent(GridDataFactory.fillDefaults().align(SWT.FILL, verticalAlignment())::applyTo);
        return textContainer;
    }

    private void fireControlSpaceEvent(ContentProposalAdapter proposalAdapter) {
        text.getDisplay().asyncExec(() -> {
            if (proposalAdapter != null && !proposalAdapter.isProposalPopupOpen()
                    && (text.getText() == null || text.getText().isEmpty())) {
                final Event ctrlSpaceEvent = new Event();
                ctrlSpaceEvent.keyCode = SWT.SPACE;
                ctrlSpaceEvent.stateMask = SWT.MOD1;
                text.notifyListeners(SWT.KeyDown, ctrlSpaceEvent);
            }
        });
    }

    protected int verticalAlignment() {
        return SWT.CENTER;
    }

    protected boolean grabVerticalSpace() {
        return false;
    }

    protected void createEditItem(final ToolBar toolBar) {
        final ToolItem editButton = new ToolItem(toolBar, SWT.FLAT);
        editButton.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, "org.bonitasoft.studio.ui.widget.textWidget.editButton");
        editButton.setImage(ImageDescriptor.createFromFile(TextWidget.class, "edit.png").createImage());
        editButton.addListener(SWT.Dispose, event -> editButton.getImage().dispose());
        editButton.setToolTipText(Messages.edit);
        editButton.addListener(SWT.Selection, editListener(toolBar));
    }

    private Listener editListener(ToolBar toolBar) {
        return event -> edit(toolBar);
    }

    private void edit(ToolBar toolBar) {
        Stream.of(toolBar.getItems()).forEach(ToolItem::dispose);
        updateEditableState(true);

        final ToolItem cancelButton = new ToolItem(toolBar, SWT.FLAT);
        cancelButton.setImage(ImageDescriptor.createFromFile(TextWidget.class, "error.png").createImage());
        cancelButton.setToolTipText(Messages.revertEdit);
        cancelButton.addListener(SWT.Selection, cancelListener(toolBar));
        cancelButton.addListener(SWT.Dispose, event -> cancelButton.getImage().dispose());

        okButton = new ToolItem(toolBar, SWT.FLAT);
        okButton.setData(SWTBotConstants.SWTBOT_WIDGET_ID_KEY, "org.bonitasoft.studio.ui.widget.textWidget.validateEdit");
        okButton.setImage(ImageDescriptor.createFromFile(TextWidget.class, "checked.png").createImage());
        okButton.setToolTipText(Messages.applyEdit);
        okButton.addListener(SWT.Selection, okListener(toolBar));
        okButton.addListener(SWT.Dispose, event -> okButton.getImage().dispose());

        toolBar.getParent().layout();

        text.setFocus();
    }

    private Listener cancelListener(ToolBar toolBar) {
        return event -> cancel(toolBar);
    }

    private void cancel(ToolBar toolBar) {
        if (editing) {
            getValueBinding().updateModelToTarget();
            getValueBinding().validateTargetToModel();
            updateEditableState(false);
            Stream.of(toolBar.getItems()).forEach(ToolItem::dispose);
            createEditItem(toolBar);
            toolBar.getParent().layout();
        }
    }

    private Listener okListener(ToolBar toolBar) {
        return event -> accept(toolBar);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.ui.widget.EditableControlWidget#messageDecoratorVerticalIndent()
     */
    @Override
    protected int messageDecoratorVerticalIndent() {
        return transactionalEdit ? -4 : super.messageDecoratorVerticalIndent();
    }

    @Override
    protected int messageLabelHorizontalSpan(boolean labelAbove) {
        return buttonLabel.isPresent() || transactionalEdit ? horizontalSpanWithButton(labelAbove) : 1;
    }

    private void accept(ToolBar toolBar) {
        if (editing) {
            final Binding binding = getValueBinding();
            final String oldValue = (String) ((IObservableValue) binding.getModel()).getValue();
            binding.updateTargetToModel();
            final String newValue = (String) ((IObservableValue) binding.getModel()).getValue();
            if (onEdit.isPresent()) {
                onEdit.get().accept(oldValue, newValue);
            }
            updateEditableState(false);
            Stream.of(toolBar.getItems()).forEach(ToolItem::dispose);
            createEditItem(toolBar);
            toolBar.getParent().layout();
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.ui.widget.EditableControlWidget#statusChanged(org.eclipse.core.runtime.IStatus)
     */
    @Override
    protected void statusChanged(IStatus status) {
        super.statusChanged(status);
        if (transactionalEdit && editing && okButton != null && !okButton.isDisposed()) {
            okButton.setEnabled(!Objects.equals(status.getSeverity(), IStatus.ERROR));
        }
    }

    private void updateEditableState(boolean editable) {
        readOnly = !editable;
        configureBackground(text);
        configureBackground(text.getParent());
        editing = editable;
    }

    protected Text newText(final Composite textContainer) {
        final Text newText = new Text(textContainer, SWT.SINGLE);
        newText.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).align(SWT.FILL, verticalAlignment()).create());
        return newText;
    }

    public void addTextListener(int eventType, Listener listener) {
        text.addListener(eventType, listener);
    }

    protected void configureBackground(Control control) {
        final Color backgroundColor = control.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND);
        final Color whiteColor = control.getDisplay().getSystemColor(SWT.COLOR_WHITE);
        if (toolkit.isPresent()) {
            toolkit.get().adapt(control, true, true);
        }

        control.setBackground(readOnly ? backgroundColor : whiteColor);
        control.setEnabled(!readOnly);

    }

    public TextWidget setText(String text) {
        this.text.setText(text);
        return this;
    }

}
