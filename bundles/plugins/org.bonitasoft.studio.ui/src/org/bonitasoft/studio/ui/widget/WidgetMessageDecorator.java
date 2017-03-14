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

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

public class WidgetMessageDecorator extends ExpandableComposite {

    private final CLabel messageLabel;

    public WidgetMessageDecorator(Composite parent) {
        super(parent, SWT.NONE, ExpandableComposite.NO_TITLE);
        messageLabel = new CLabel(this, SWT.NONE);
        messageLabel.setLeftMargin(0);
        messageLabel.setLayoutData(GridDataFactory.swtDefaults().align(SWT.LEFT, SWT.FILL).grab(true, true).create());
        setClient(messageLabel);
        setExpanded(false);
    }

    public void setColor(Color messageColor) {
        messageLabel.setForeground(messageColor);
    }

    public void setMessage(String message) {
        messageLabel.setText(message);
    }

    public void setImage(Image image) {
        messageLabel.setImage(image);
    }

    public void updateExpandState() {
        setExpanded(messageLabel.getText() != null && !messageLabel.getText().isEmpty());
    }
}
