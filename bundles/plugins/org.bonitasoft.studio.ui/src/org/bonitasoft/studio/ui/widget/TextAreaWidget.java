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

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class TextAreaWidget extends TextWidget {

    public static class Builder extends TextWidget.Builder {

        protected Optional<Integer> delay = Optional.empty();

        @Override
        public Builder withDelay(int delay) {
            this.delay = Optional.of(delay);
            return this;
        }

        @Override
        public TextAreaWidget createIn(Composite container) {
            final TextAreaWidget control = new TextAreaWidget(container, id, labelAbove, horizontalLabelAlignment,
                    verticalLabelAlignment, labelWidth, readOnly, label, message, labelButton, toolkit);
            control.init();
            control.setLayoutData(layoutData != null ? layoutData : gridData);
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

    protected TextAreaWidget(Composite container, String id, boolean topLabel, int horizontalLabelAlignment,
            int verticalLabelAlignment,
            int labelWidth, boolean readOnly, String label, String message, Optional<String> labelButton,
            Optional<FormToolkit> toolkit) {
        super(container, id, topLabel, horizontalLabelAlignment, verticalLabelAlignment, labelWidth, readOnly, label,
                message, labelButton, false, null, toolkit);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.ui.widget.TextWidget#grabVerticalSpace()
     */
    @Override
    protected boolean grabVerticalSpace() {
        return true;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.ui.widget.TextWidget#verticalAlignment()
     */
    @Override
    protected int verticalAlignment() {
        return SWT.FILL;
    }

    @Override
    protected Text newText(final Composite textContainer) {
        final Text text = new Text(textContainer, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP);
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
        return text;
    }

}
