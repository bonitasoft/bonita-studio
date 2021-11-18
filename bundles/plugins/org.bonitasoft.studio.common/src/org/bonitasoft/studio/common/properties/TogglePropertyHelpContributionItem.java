/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.properties;

import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.osgi.service.prefs.BackingStoreException;

/**
 * @author Romain Bioteau
 */
public class TogglePropertyHelpContributionItem extends ContributionItem implements IContributionItem {

    private String helpContent;
    private final Form form;
    private final FormToolkit toolkit;
    private MenuItem menuItem;
    private final PropertySectionHistory propertySectionHistory;
    private Well descriptionWell;
    private int wellSeverity;

    public TogglePropertyHelpContributionItem(final FormToolkit toolkit,
            final Form form,
            final String helpContent,
            int wellSeverity,
            final PropertySectionHistory propertySectionHistory) {
        this.helpContent = helpContent;
        this.wellSeverity = wellSeverity;
        this.form = form;
        this.toolkit = toolkit;
        this.propertySectionHistory = propertySectionHistory;
    }

    @Override
    public boolean isVisible() {
        return helpContent != null && !helpContent.isEmpty();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.action.ContributionItem#dispose()
     */
    @Override
    public void dispose() {
        try {
            propertySectionHistory.save();
        } catch (final BackingStoreException e) {
            BonitaStudioLog.error(e);
        }
        super.dispose();
    }

    protected void toggleHelp() {
        if (descriptionWell != null) {
            descriptionWell.dispose();
            form.setHeadClient(null);
            descriptionWell = null;
            if (menuItem != null) {
                menuItem.setText(Messages.showHelp);
            }
            propertySectionHistory.hideDescription();
        } else {
            descriptionWell = new Well(form.getHead(), helpContent, toolkit, wellSeverity);
            form.setHeadClient(descriptionWell);
            if (menuItem != null) {
                menuItem.setText(Messages.hideHelp);
            }
            propertySectionHistory.showDescription();
        }
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
        if (propertySectionHistory.isDescriptionVisible()) {
            toggleHelp();
        }
    }

    public void setHelpContent(final String helpContent) {
        this.helpContent = helpContent;
        if (descriptionWell != null) {
            descriptionWell.setText(helpContent);
        }
    }

    public String getHelpContent() {
        return helpContent;
    }

}
