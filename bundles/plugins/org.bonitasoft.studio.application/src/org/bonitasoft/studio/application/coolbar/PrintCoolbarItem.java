/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.coolbar;

import org.bonitasoft.studio.application.actions.PrintCommandHandler;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.extension.IBonitaContributionItem;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * @author Romain Bioteau
 */
public class PrintCoolbarItem extends ContributionItem implements IBonitaContributionItem {

    private final PrintCommandHandler handler;

    public PrintCoolbarItem() {
        handler = new PrintCommandHandler();
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.action.IContributionItem#getId()
     */
    @Override
    public String getId() {
        return "org.bonitasoft.studio.coolbar.printDiagram";
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.action.IContributionItem#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return handler.isEnabled();
    }

    @Override
    public void fill(final ToolBar toolbar, final int index, final int iconSize) {
        final ToolItem item = new ToolItem(toolbar, SWT.PUSH);
        item.setToolTipText(Messages.PrintProcessButtonLabel);
        if (iconSize < 0) {
            item.setText(Messages.PrintProcessButtonLabel);
            item.setImage(Pics.getImage(PicsConstants.coolbar_print_48));
            item.setDisabledImage(Pics.getImage(PicsConstants.coolbar_print_disabled_48));
        } else {
            item.setImage(Pics.getImage(PicsConstants.coolbar_print_16));
            item.setDisabledImage(Pics.getImage(PicsConstants.coolbar_print_disabled_16));
        }
        item.setEnabled(false);
        item.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(final SelectionEvent e) {
                try {
                    handler.execute(null);
                } catch (final Exception ex) {
                    BonitaStudioLog.error(ex);
                }
            }
        });

    }

}
