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

import org.bonitasoft.studio.ui.ColorConstants;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class WidgetMessageDecorator extends ExpandableComposite {

    private final CLabel messageLabel;
    private Optional<String> defaultMessage;
    private final LocalResourceManager resourceManager;
    private final Color errorColor;
    private final Color warningColor;
    private Color foregroundColor;

    public WidgetMessageDecorator(Composite parent, Optional<String> defaultMessage) {
        super(parent, SWT.NONE, ExpandableComposite.NO_TITLE);
        this.resourceManager = new LocalResourceManager(JFaceResources.getResources(), parent);
        errorColor = resourceManager.createColor(ColorConstants.ERROR_RGB);
        warningColor = resourceManager.createColor(ColorConstants.WARNING_RGB);
        this.defaultMessage = defaultMessage;
        messageLabel = new CLabel(this, SWT.NONE);
        messageLabel.setTopMargin(1);
        messageLabel.setLeftMargin(0);
        messageLabel.setFont(getMessageFont());
        messageLabel.setText(defaultMessage.orElse(""));
        foregroundColor = Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_FOREGROUND);
        updateExpandState();
    }

    private Font getMessageFont() {
        final FontRegistry fontRegistry = JFaceResources.getFontRegistry();
        if (fontRegistry.hasValueFor(WidgetMessageDecorator.class.getName())) {
            return fontRegistry.get(WidgetMessageDecorator.class.getName());
        }
        fontRegistry.put(WidgetMessageDecorator.class.getName(),
                new FontData[] { new FontData(WidgetMessageDecorator.class.getName(), 10, SWT.NORMAL) });
        return fontRegistry.get(WidgetMessageDecorator.class.getName());
    }

    private void updateExpandState() {
        final Composite parent = getParent().getParent();
        if (messageLabel.getText() != null && !messageLabel.getText().isEmpty()) {
            setClient(messageLabel);
            setExpanded(true);
            pack();
        } else {
            setExpanded(false);
        }
        parent.layout();

    }

    public void adapt(FormToolkit toolkit) {
        toolkit.adapt(this);
        toolkit.adapt(messageLabel, true, true);
        foregroundColor = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GRAY);
        messageLabel.setForeground(foregroundColor);
    }

    public void setStatus(IStatus status) {
        if (status == null || status.isOK()) {
            messageLabel.setText(defaultMessage.orElse(""));
        } else {
            messageLabel.setText(status.getMessage());
        }
        messageLabel.setForeground(getStatusColor(status));
        messageLabel.setImage(getStatusImage(status));
        updateExpandState();
    }

    private Image getStatusImage(IStatus status) {
        if (status == null) {
            return null;
        }
        switch (status.getSeverity()) {
            case IStatus.INFO:
                return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_INFO);
            case IStatus.WARNING:
                return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING);
            case IStatus.ERROR:
                return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR);
            default:
                return null;
        }
    }

    private Color getStatusColor(IStatus status) {
        if (status == null || status.isOK()) {
            return foregroundColor;
        }
        if (status.getSeverity() == IStatus.INFO) {
            return foregroundColor;
        }
        return status.getSeverity() == IStatus.WARNING ? warningColor
                : errorColor;
    }

    public void setMessage(Optional<String> message) {
        this.defaultMessage = message;
    }
}
