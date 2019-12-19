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

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.value.ComputedValue;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class SearchWidget extends TextWidget {

    public static class Builder extends TextWidget.Builder {

        @Override
        public SearchWidget createIn(Composite container) {
            final SearchWidget control = new SearchWidget(container, id, labelAbove, horizontalLabelAlignment,
                    verticalLabelAlignment, labelWidth, readOnly, label, message, useCompositeMessageDecorator, labelButton,
                    toolkit, proposalProvider, editableStrategy, Optional.ofNullable(ctx));
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

    protected SearchWidget(Composite container, String id, boolean topLabel, int horizontalLabelAlignment,
            int verticalLabelAlignment,
            int labelWidth,
            boolean readOnly,
            String label,
            String message,
            boolean useCompositeMessageDecorator,
            Optional<String> labelButton,
            Optional<FormToolkit> toolkit,
            Optional<IContentProposalProvider> proposalProvider,
            Optional<ComputedValue<Boolean>> editableStrategy,
            Optional<DataBindingContext> ctx) {
        super(container, id, topLabel, horizontalLabelAlignment, verticalLabelAlignment, labelWidth, readOnly, label,
                message, useCompositeMessageDecorator, labelButton, false, null, toolkit, proposalProvider, editableStrategy,
                ctx);
    }

    @Override
    protected Text newText(Composite textContainer) {
        final Text newText = new Text(textContainer, SWT.SEARCH | SWT.ICON_CANCEL);
        newText.setLayoutData(
                GridDataFactory.fillDefaults().grab(true, true).align(SWT.FILL, verticalAlignment()).create());
        return newText;
    }

    @Override
    protected void drawBorder(Composite container, Event e) {
        // SWT.SEARCH provides a border 
    }

    @Override
    protected void configureBackground(Control control) {
        if (toolkit.isPresent()) {
            if (control instanceof Composite) {
                toolkit.get().adapt((Composite) control);
            } else {
                toolkit.get().adapt(control, true, true);
            }
        }
    }

}
