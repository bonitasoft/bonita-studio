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

import org.bonitasoft.studio.preferences.PreferenceUtil;
import org.bonitasoft.studio.ui.ColorConstants;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class WidgetMessageDecorator {

    protected final CustomCLabel messageLabel;
    private Optional<String> defaultMessage;
    private final LocalResourceManager resourceManager;
    private final Color errorColor;
    private final Color warningColor;
    private Color foregroundColor;
    protected Composite composite;
    private Image icon;

    public WidgetMessageDecorator(Composite parent, Optional<String> defaultMessage) {
        createComposite(parent);
        this.resourceManager = new LocalResourceManager(JFaceResources.getResources(), parent);
        errorColor = resourceManager.createColor(ColorConstants.ERROR_RGB);
        warningColor = resourceManager.createColor(ColorConstants.WARNING_RGB);
        this.defaultMessage = defaultMessage;
        messageLabel = new CustomCLabel(composite, SWT.NONE);
        messageLabel.setTopMargin(1);
        messageLabel.setLeftMargin(0);
        messageLabel.setText(defaultMessage.orElse(""));
        foregroundColor = PreferenceUtil.isDarkTheme()
                ? Display.getDefault().getSystemColor(SWT.COLOR_GRAY)
                : Display.getDefault().getSystemColor(SWT.COLOR_WIDGET_FOREGROUND);
        messageLabel.setForeground(foregroundColor);
        updateExpandState();
    }

    protected void createComposite(Composite parent) {
        this.composite = new ExpandableComposite(parent, SWT.NONE, ExpandableComposite.NO_TITLE);
        composite.setLayoutDeferred(true);
        ((ExpandableComposite) composite).setExpanded(false);
        composite.setLayoutDeferred(false);
        this.composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
    }

    protected void updateExpandState() {
        Composite parent = composite.getParent().getParent();
        ExpandableComposite expandableComposite = (ExpandableComposite) composite;
        boolean isExpanded = expandableComposite.isExpanded();
        if (messageLabel.getText() != null && !messageLabel.getText().isEmpty()) {
            expandableComposite.setClient(messageLabel);
            expandableComposite.setExpanded(true);
            expandableComposite.pack();
        } else {
            expandableComposite.setExpanded(false);
        }
        if(isExpanded != expandableComposite.isExpanded()) {
            parent.layout();
        }
    }

    public void adapt(FormToolkit toolkit) {
        toolkit.adapt(composite);
        toolkit.adapt(messageLabel, true, true);
        foregroundColor = Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GRAY);
        messageLabel.setForeground(foregroundColor);
        if (PreferenceUtil.isDarkTheme()) {
            messageLabel.setBackground(resourceManager.createColor(ColorConstants.DARK_MODE_EDITORS_BACKGROUND));
        }
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
            return icon;
        }
        switch (status.getSeverity()) {
            case IStatus.INFO:
                return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_INFO);
            case IStatus.WARNING:
                return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING);
            case IStatus.ERROR:
                return JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_ERROR);
            default:
                return icon;
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
    
    public void setImage(Image icon) {
        this.icon = icon;
     }

    public void setLayoutData(Object layoutData) {
        composite.setLayoutData(layoutData);
    }

}

/**
 * When using themes, CLabel background doesn't fit when used in an editor
 * because of a 'hack' that has been implemented in the eclipse theme css files to render selceted and unselected CTabItems.
 * -> Extend the CLabel class is enough to solve the issue, because the problematic css rule isn't applied.
 */
class CustomCLabel extends CLabel {

    public CustomCLabel(Composite parent, int style) {
        super(parent, style);
        setBackgroundMode(SWT.INHERIT_DEFAULT);
    }

}
