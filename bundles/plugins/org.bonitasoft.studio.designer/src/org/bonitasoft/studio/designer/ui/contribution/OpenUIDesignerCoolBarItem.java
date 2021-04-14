/**
 * Copyright (C) 2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.designer.ui.contribution;

import org.bonitasoft.studio.common.extension.IBonitaContributionItem;
import org.bonitasoft.studio.common.jface.MessageDialogWithPrompt;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.designer.UIDesignerPlugin;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.bonitasoft.studio.designer.ui.handler.OpenUIDesignerHandler;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.PlatformUI;

public class OpenUIDesignerCoolBarItem extends ContributionItem implements IBonitaContributionItem {

    protected static final String HIDE_UIDESIGNER_INFO_DIALOG = "HIDE_UIDESIGNER_INFO_DIALOG";

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.extension.IBonitaContributionItem#fill(org.eclipse.swt.widgets.ToolBar, int, int)
     */
    @Override
    public void fill(final ToolBar toolbar, final int index, final int iconSize) {
        final ToolItem item = new ToolItem(toolbar, SWT.PUSH);
        item.setToolTipText(Messages.openUIDesigner);
        if (iconSize < 0) {
            item.setImage(Pics.getImage(PicsConstants.uid48));
        } else {
            item.setImage(Pics.getImage(PicsConstants.uid24));
        }
        item.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                openInformationDialogOnUiDesigner();
                try {
                    getHandler().execute();
                } catch (final ExecutionException ex) {
                    BonitaStudioLog.error("Failed to open ui designer", ex);
                }
            }

        });
    }

    protected OpenUIDesignerHandler getHandler() {
        return new OpenUIDesignerHandler();
    }

    protected void openInformationDialogOnUiDesigner() {
        if (!getEclipsePreferences().getBoolean(HIDE_UIDESIGNER_INFO_DIALOG, false)) {
            MessageDialogWithPrompt
                    .open(
                            MessageDialog.INFORMATION,
                            getShell(),
                            Messages.openUiDesignerInformationWindowTitle,
                            Messages.openUiDesignerInformationMessage,
                            Messages.openUiDesignerInformationToggleMessage,
                            false,
                            getPreferenceStore(),
                            HIDE_UIDESIGNER_INFO_DIALOG,
                            SWT.NONE);
        }
    }

    protected Shell getShell() {
        return PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    }

    protected IPreferenceStore getPreferenceStore() {
        return UIDesignerPlugin.getDefault().getPreferenceStore();
    }

    protected IEclipsePreferences getEclipsePreferences() {
        return InstanceScope.INSTANCE.getNode(UIDesignerPlugin.PLUGIN_ID);
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.action.ContributionItem#getId()
     */
    @Override
    public String getId() {
        return "org.bonitasoft.studio.coolbar.uiDesigner";
    }

    @Override
    public String getText() {
        return Messages.uiDesignerLabel;
    }
}
