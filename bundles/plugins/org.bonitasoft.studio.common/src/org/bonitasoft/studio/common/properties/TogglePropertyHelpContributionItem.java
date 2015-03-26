/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.properties;

import org.bonitasoft.studio.common.Messages;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author Romain Bioteau
 */
public class TogglePropertyHelpContributionItem implements IContributionItem {

    private final String helpContent;
    private final Form form;
    private final FormToolkit toolkit;
    private Label decriptionLabel;
    private MenuItem menuItem;

    public TogglePropertyHelpContributionItem(final FormToolkit toolkit, final Form form, final String helpContent) {
        this.helpContent = helpContent;
        this.form = form;
        this.toolkit = toolkit;
    }

    @Override
    public void update(final String arg0) {

    }

    @Override
    public void update() {
    }

    @Override
    public void setVisible(final boolean arg0) {

    }

    @Override
    public void setParent(final IContributionManager arg0) {

    }

    @Override
    public void saveWidgetState() {

    }

    @Override
    public boolean isVisible() {
        return helpContent != null && !helpContent.isEmpty();
    }

    @Override
    public boolean isSeparator() {
        return false;
    }

    @Override
    public boolean isGroupMarker() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isDynamic() {
        return false;
    }

    @Override
    public boolean isDirty() {
        return false;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void fill(final CoolBar arg0, final int arg1) {

    }

    protected void toggleHelp() {
        if (decriptionLabel != null) {
            decriptionLabel.dispose();
            form.setHeadClient(null);
            decriptionLabel = null;
            if (menuItem != null) {
                menuItem.setText(Messages.showHelp);
            }
        } else {
            decriptionLabel = toolkit.createLabel(form.getHead(), helpContent, SWT.WRAP);
            form.setHeadClient(decriptionLabel);
            if (menuItem != null) {
                menuItem.setText(Messages.hideHelp);
            }
        }
        form.getParent().getParent().layout(true, true);
    }

    @Override
    public void fill(final ToolBar toolbar, final int arg1) {
        final ToolItem toolItem = new ToolItem(toolbar, SWT.LEFT | SWT.PUSH | SWT.NO_FOCUS);
        toolItem.setToolTipText(Messages.toggleHelp);
        toolItem.setImage(JFaceResources.getImage(Dialog.DLG_IMG_HELP));
        toolItem.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                toggleHelp();
            }
        });
    }

    @Override
    public void fill(final Menu parent, final int index) {
        menuItem = new MenuItem(parent, SWT.PUSH);
        if (decriptionLabel == null) {
            menuItem.setText(Messages.showHelp);
        } else {
            menuItem.setText(Messages.hideHelp);
        }

        menuItem.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                toggleHelp();
            };
        });
        menuItem.setSelection(decriptionLabel != null);
    }

    @Override
    public void fill(final Composite arg0) {

    }

    @Override
    public void dispose() {

    }
}
