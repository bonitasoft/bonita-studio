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
package org.bonitasoft.studio.pagedesigner.ui.contribution;

import org.bonitasoft.studio.common.extension.IBonitaContributionItem;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.pagedesigner.PageDesignerPlugin;
import org.bonitasoft.studio.pagedesigner.i18n.Messages;
import org.bonitasoft.studio.pagedesigner.ui.handler.OpenUIDesignerHandler;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class OpenUIDesignerCoolBarItem extends ContributionItem implements IBonitaContributionItem {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.extension.IBonitaContributionItem#fill(org.eclipse.swt.widgets.ToolBar, int, int)
     */
    @Override
    public void fill(final ToolBar toolbar, final int index, final int iconSize) {
        final ToolItem item = new ToolItem(toolbar, SWT.PUSH);
        item.setToolTipText(Messages.openUIDesigner);
        if (iconSize < 0) {
            item.setText(Messages.uiDesignerLabel);
        }
        configureItemImage(item, iconSize < 0 ? "ui_designer_48x48.png" : "ui_designer_24x24.png");
        item.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
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

    private void configureItemImage(final ToolItem item, final String imageFileName) {
        item.setImage(Pics.getImage(imageFileName, PageDesignerPlugin.getDefault()));
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.action.ContributionItem#getId()
     */
    @Override
    public String getId() {
        return "org.bonitasoft.studio.coolbar.uiDesigner";
    }
}
