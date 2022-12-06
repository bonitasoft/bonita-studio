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

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class TextAreaWidget extends TextWidget {

    public static class Builder extends TextWidget.Builder {

        @Override
        public TextAreaWidget createIn(Composite container) {
            final TextAreaWidget control = useNativeRender
                    ? new NativeTextAreaWidget(container, id, labelAbove, horizontalLabelAlignment,
                            verticalLabelAlignment, labelWidth, readOnly, label, message, useCompositeMessageDecorator,
                            labelButton, imageButton, tooltipButton, toolkit, editableStrategy, Optional.ofNullable(ctx), 
                            style, editable)
                    : new TextAreaWidget(container, id, labelAbove, horizontalLabelAlignment,
                            verticalLabelAlignment, labelWidth, readOnly, label, message, useCompositeMessageDecorator,
                            labelButton, imageButton, tooltipButton, toolkit, editableStrategy, Optional.ofNullable(ctx), 
                            style, editable);
            control.init();
            control.setLayoutData(layoutData != null ? layoutData : gridData);
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

    protected TextAreaWidget(Composite container,
            String id,
            boolean topLabel,
            int horizontalLabelAlignment,
            int verticalLabelAlignment,
            int labelWidth,
            boolean readOnly,
            String label,
            String message,
            boolean useCompositeMessageDecorator,
            Optional<String> labelButton,
            Optional<Image> imageButton,
            Optional<String> tooltipButton,
            Optional<FormToolkit> toolkit,
            Optional<ComputedValue<Boolean>> editableStrategy,
            Optional<DataBindingContext> ctx,
            int style,
            boolean editable) {
        super(container, id, topLabel, horizontalLabelAlignment, verticalLabelAlignment, labelWidth, readOnly, label,
                message, useCompositeMessageDecorator, labelButton, imageButton, tooltipButton, false, null, null,
                toolkit, Optional.empty(), editableStrategy, ctx, style, editable);
    }

    @Override
    protected void createButton() {
        if (buttonLabel.isPresent()) {
            Button b = new Button(this, SWT.PUSH);
            b.setText(buttonLabel.get());
            toolkit.ifPresent(toolkit -> toolkit.adapt(b, true, true));
            GridDataFactory.swtDefaults().align(SWT.FILL, SWT.TOP).applyTo(b);
            tooltipButton.ifPresent(b::setToolTipText);
            button = Optional.of(b);
        } else if (imageButton.isPresent()) {
            toolBar = new ToolBar(this, SWT.INHERIT_DEFAULT | SWT.NO_FOCUS | SWT.RIGHT);
            toolBar.setLayoutData(
                    GridDataFactory.swtDefaults().align(SWT.FILL, SWT.TOP).create());
            toolkit.ifPresent(toolkit -> toolkit.adapt(toolBar, true, true));
            ToolItem bWithImage = new ToolItem(toolBar, SWT.FLAT);
            imageButton.ifPresent(bWithImage::setImage);
            tooltipButton.ifPresent(bWithImage::setToolTipText);
            buttonWithImage = Optional.of(bWithImage);

            toolBar.addMouseTrackListener(new MouseTrackAdapter() {

                @Override
                public void mouseExit(MouseEvent e) {
                    toolBar.setCursor(cursorArrow);
                }

                @Override
                public void mouseEnter(MouseEvent e) {
                    toolBar.setCursor(cursorHand);
                }
            });
        }
    }

    @Override
    protected boolean grabVerticalSpace() {
        return true;
    }

    @Override
    protected int verticalAlignment() {
        return SWT.FILL;
    }

    @Override
    protected Text newText(final Composite textContainer) {
        final Text text = new Text(textContainer, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP | style);
        text.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
        final Listener scrollBarListener = event -> {
            final Text t = (Text) event.widget;
            final Rectangle r1 = t.getClientArea();
            final Rectangle r2 = t.computeTrim(r1.x, r1.y, r1.width, r1.height);
            final Point p = t.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
            t.getVerticalBar().setVisible(r2.height <= p.y);
            if (event.type == SWT.Modify) {
                t.getParent().layout(true);
                t.showSelection();
            }
        };
        text.addListener(SWT.Resize, scrollBarListener);
        text.addListener(SWT.Modify, scrollBarListener);
        text.setEditable(editable);
        return text;
    }

}
