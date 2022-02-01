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
package org.bonitasoft.studio.rest.api.extension.menu;

import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class RestApiExtensionMenuContribution extends ContributionItem {

    private static final String NEW_COMMAND = "org.bonitasoft.studio.rest.api.extension.newCommand";
    private static final String OPEN_COMMAND = "org.bonitasoft.studio.rest.api.extension.openCommand";
    private static final String DELETE_COMMAND = "org.bonitasoft.studio.rest.api.extension.deleteCommand";

    private static final String EDIT_PERMISSIONS_COMMAND = "org.bonitasoft.studio.rest.api.extension.editPermissionCommand";

    private static final String BUILD_COMMAND = "org.bonitasoft.studio.rest.api.extension.buildCommand";
    private static final String DEPLOY_COMMAND = "org.bonitasoft.studio.rest.api.extension.deployCommand";

    private CommandExecutor commandExecutor;

    public RestApiExtensionMenuContribution() {
        commandExecutor = new CommandExecutor();
    }

    @Override
    public void fill(Menu parent, int index) {
        var restApiMenuItem = new MenuItem(parent, SWT.CASCADE, index);
        restApiMenuItem.setText(Messages.restApiExtensionRepositoryName);
        restApiMenuItem.setEnabled(true);
        restApiMenuItem.setImage(Pics.getImage(PicsConstants.restApi));

        var restApiMenu = new Menu(restApiMenuItem);

        createItem(restApiMenu, NEW_COMMAND, org.bonitasoft.studio.common.Messages.newWithWizardMenuLabel, 0);
        createItem(restApiMenu, OPEN_COMMAND, org.bonitasoft.studio.common.Messages.openWithWizardMenuLabel, 1);
        createItem(restApiMenu, DELETE_COMMAND, org.bonitasoft.studio.common.Messages.deleteWithWizardMenuLabel, 2);

        new MenuItem(restApiMenu, SWT.SEPARATOR, 3);

        createItem(restApiMenu, EDIT_PERMISSIONS_COMMAND, Messages.editPermissionsMenuLabel, 4);

        new MenuItem(restApiMenu, SWT.SEPARATOR, 5);

        createItem(restApiMenu, BUILD_COMMAND, Messages.buildMenuLabel, 6);
        createItem(restApiMenu, DEPLOY_COMMAND, org.bonitasoft.studio.common.Messages.deployWithWizardMenuLabel, 7);

        restApiMenuItem.setMenu(restApiMenu);
    }

    private void createItem(Menu parent, String command, String text, int index) {
        var item = new MenuItem(parent, SWT.NONE, index);
        item.setText(text);
        item.addListener(SWT.Selection, e -> commandExecutor.executeCommand(command, null));
    }

}
