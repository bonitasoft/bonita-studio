/**
 * Copyright (C) 2021 Bonitasoft S.A.
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
package org.bonitasoft.studio.la.application.menu;

import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.common.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class ApplicationMenuContributionItem extends ContributionItem {

    private static final String NEW_COMMAND = "org.bonitasoft.studio.la.new.command";
    private static final String OPEN_COMMAND = "org.bonitasoft.studio.la.open.command";
    private static final String DELETE_COMMAND = "org.bonitasoft.studio.la.delete.command";

    private static final String IMPORT_COMMAND = "org.bonitasoft.studio.la.import.command";
    private static final String EXPORT_COMMAND = "org.bonitasoft.studio.la.export.command";

    private static final String DEPLOY_COMMAND = "org.bonitasoft.studio.la.deploy.command";

    private CommandExecutor commandExecutor;

    public ApplicationMenuContributionItem() {
        commandExecutor = new CommandExecutor();
    }

    @Override
    public void fill(Menu parent, int index) {
        var applicationMenuItem = new MenuItem(parent, SWT.CASCADE, index);
        applicationMenuItem.setText(org.bonitasoft.studio.application.i18n.Messages.applicationDescriptor);
        applicationMenuItem.setEnabled(true);
        applicationMenuItem.setImage(Pics.getImage(PicsConstants.application));

        var applicationMenu = new Menu(applicationMenuItem);

        createItem(applicationMenu, NEW_COMMAND, Messages.newWithWizardMenuLabel, 0);
        createItem(applicationMenu, OPEN_COMMAND, Messages.openWithWizardMenuLabel, 1);
        createItem(applicationMenu, DELETE_COMMAND, Messages.deleteWithWizardMenuLabel, 2);

        new MenuItem(applicationMenu, SWT.SEPARATOR, 3);

        createItem(applicationMenu, IMPORT_COMMAND, Messages.importMenuLabel, 4);
        createItem(applicationMenu, EXPORT_COMMAND, Messages.exportMenuLabel, 5);

        new MenuItem(applicationMenu, SWT.SEPARATOR, 6);

        createItem(applicationMenu, DEPLOY_COMMAND, Messages.deployWithWizardMenuLabel, 7);

        applicationMenuItem.setMenu(applicationMenu);
    }

    private void createItem(Menu parent, String command, String text, int index) {
        var item = new MenuItem(parent, SWT.NONE, index);
        item.setText(text);
        item.addListener(SWT.Selection, e -> commandExecutor.executeCommand(command, null));
    }

}
