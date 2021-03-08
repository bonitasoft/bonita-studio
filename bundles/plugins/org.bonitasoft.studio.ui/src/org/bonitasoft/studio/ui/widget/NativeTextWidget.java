/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class NativeTextWidget extends TextWidget {

    protected NativeTextWidget(Composite container, String id, boolean topLabel, int horizontalLabelAlignment,
            int verticalLabelAlignment, int labelWidth, boolean readOnly, String label, String message,
            boolean useCompositeMessageDecorator,
            Optional<String> labelButton, Optional<Image> imageButton, Optional<String> tooltipButton,
            boolean transactionalEdit, BiConsumer<String, String> onEdit, Supplier<IStatus> canEdit,
            Optional<FormToolkit> toolkit, Optional<IContentProposalProvider> proposalProvider,
            Optional<ComputedValue<Boolean>> editableStrategy,
            Optional<DataBindingContext> ctx, int style, boolean isInShell) {
        super(container, id, topLabel, horizontalLabelAlignment, verticalLabelAlignment, labelWidth, readOnly, label,
                message, useCompositeMessageDecorator, labelButton, imageButton, tooltipButton, transactionalEdit, onEdit,
                canEdit, toolkit, proposalProvider, editableStrategy, ctx, style, isInShell);
    }

    @Override
    protected Color getBorderColor(Control focused, Control container) {
        return container.getBackground();
    }

    @Override
    protected Text newText(final Composite textContainer) {
        final Text newText = new Text(textContainer, SWT.SINGLE | SWT.BORDER | style);
        newText.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).align(SWT.FILL, verticalAlignment()).create());
        return newText;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.ui.widget.TextWidget#configureBackground(org.eclipse.swt.widgets.Control)
     */
    @Override
    protected void configureBackground(Control control) {
        if (toolkit.isPresent()) {
            if (control instanceof Composite) {
                toolkit.get().adapt((Composite) control);
            } else {
                toolkit.get().adapt(control, true, true);
            }
        }
        control.setEnabled(!readOnly);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.ui.widget.TextWidget#verticalAlignment()
     */
    @Override
    protected int verticalAlignment() {
        return SWT.FILL;
    }
}
