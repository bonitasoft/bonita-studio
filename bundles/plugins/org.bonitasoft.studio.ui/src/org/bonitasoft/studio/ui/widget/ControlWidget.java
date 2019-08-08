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

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;

public abstract class ControlWidget extends Composite {

    protected static final String SWTBOT_WIDGET_ID_KEY = "org.eclipse.swtbot.widget.key";

    protected Optional<CLabel> label = Optional.empty();
    protected Optional<Label> filler = Optional.empty();
    protected Optional<FormToolkit> toolkit = Optional.empty();
    protected Optional<String> message = Optional.empty();
    protected boolean readOnly = false;
    protected boolean labelAbove = false;
    protected Control control;
    protected Optional<String> buttonLabel = Optional.empty();
    private final int horizontalLabelAlignment;
    private final int verticalLabelAlignment;
    private final int labelHint;
    private final String labelValue;

    protected String id;

    protected ControlWidget(Composite parent,
            String id,
            boolean labelAbove,
            int horizontalLabelAlignment,
            int verticalLabelAlignment,
            int labelHint,
            String labelValue,
            String message) {
        this(parent, id, labelAbove, horizontalLabelAlignment, verticalLabelAlignment, labelHint, false, labelValue, message,
                Optional.empty(), Optional.empty());
    }

    protected ControlWidget(Composite parent,
            String id,
            boolean labelAbove,
            int horizontalLabelAlignment,
            int verticalLabelAlignment,
            int labelHint,
            boolean readOnly,
            String labelValue,
            String message,
            Optional<String> buttonLabel,
            Optional<FormToolkit> toolkit) {
        super(parent, SWT.NONE);
        this.id = id;
        this.buttonLabel = buttonLabel;
        this.readOnly = readOnly;
        this.labelAbove = labelAbove;
        this.horizontalLabelAlignment = horizontalLabelAlignment;
        this.verticalLabelAlignment = verticalLabelAlignment;
        this.labelHint = labelHint;
        this.labelValue = labelValue;
        this.message = Optional.ofNullable(message);
        this.toolkit = toolkit;
    }

    protected ControlWidget(Composite parent) {
        this(parent, null, false, 0, 0, 0, false, null, null, Optional.empty(), Optional.empty());
    }

    protected void init() {
        setLayout(GridLayoutFactory.fillDefaults()
                .numColumns(numColumn())
                .spacing(horizontalSpacing(), 1)
                .create());

        final Optional<String> labelText = Optional.ofNullable(labelValue);
        labelText.ifPresent(text -> {
            final CLabel lab = new CLabel(this, SWT.NONE);
            lab.setLayoutData(GridDataFactory.swtDefaults()
                    .align(labelAbove ? SWT.LEFT : horizontalLabelAlignment, verticalLabelAlignment)
                    .span(labelAbove ? 2 : 1, 1)
                    .create());
            lab.setAlignment(SWT.LEFT);
            lab.setMargins(0, 0, 0, 0);
            lab.setText(text);
            toolkit.ifPresent(tk -> tk.adapt(lab, false, false));
            label = Optional.of(lab);
        });

        control = createControl();

        //Create a filler label
        filler = labelText.map(text -> new Label(this, SWT.NONE));
        filler.ifPresent(GridDataFactory.swtDefaults().hint(labelHint, SWT.DEFAULT).exclude(labelAbove)::applyTo);
        toolkit.ifPresent(this::adapt);
    }

    protected abstract void adapt(FormToolkit toolkit);

    protected int numColumn() {
        return buttonLabel.isPresent() ? 3 : 2;
    }

    protected int horizontalSpacing() {
        return LayoutConstants.getSpacing().x;
    }

    public Control getControl() {
        return control;
    }

    protected abstract Control createControl();
}
