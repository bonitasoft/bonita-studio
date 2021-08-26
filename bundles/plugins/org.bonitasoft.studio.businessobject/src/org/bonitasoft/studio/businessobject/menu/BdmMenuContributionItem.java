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
package org.bonitasoft.studio.businessobject.menu;

import org.bonitasoft.studio.businessobject.i18n.Messages;
import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class BdmMenuContributionItem extends ContributionItem {

    private static final String NEW_COMMAND = "org.bonitasoft.studio.businessobject.define";
    private static final String H2_COMMAND = "org.bonitasoft.studio.businessobject.openH2Console";
    private static final String EXPLORE_COMMAND = "org.bonitasoft.studio.businessobject.explore.command";
    private static final String DEPLOY_COMMAND = "org.bonitasoft.studio.businessobject.deployCommand";
    private static final String CLEAN_DEPLOY_COMMAND = "org.bonitasoft.studio.businessobject.cleanDeployCommand";
    private static final String IMPORT_COMMAND = "org.bonitasoft.studio.businessobject.command.import";
    private static final String EXPORT_COMMAND = "org.bonitasoft.studio.businessobject.exportCommand";

    private CommandExecutor commandExecutor;

    public BdmMenuContributionItem() {
        commandExecutor = new CommandExecutor();
    }

    @Override
    public void fill(Menu parent, int index) {
        var bdmMenuItem = new MenuItem(parent, SWT.CASCADE, index);
        bdmMenuItem.setText(Messages.businessDataModel);
        bdmMenuItem.setEnabled(true);
        bdmMenuItem.setImage(Pics.getImage(PicsConstants.bdm));

        var bdmMenu = new Menu(bdmMenuItem);

        createItem(bdmMenu, NEW_COMMAND, Messages.define, 0);
        createItem(bdmMenu, H2_COMMAND, Messages.openH2Console, 1);
        createItem(bdmMenu, EXPLORE_COMMAND, Messages.explore, 2);

        new MenuItem(bdmMenu, SWT.SEPARATOR, 3);

        createItem(bdmMenu, DEPLOY_COMMAND, Messages.deploy, 4);
        createItem(bdmMenu, CLEAN_DEPLOY_COMMAND, Messages.cleanDeployTitle, 5);

        new MenuItem(bdmMenu, SWT.SEPARATOR, 6);

        createItem(bdmMenu, IMPORT_COMMAND, org.bonitasoft.studio.common.Messages.importMenuLabel, 7);
        createItem(bdmMenu, EXPORT_COMMAND, org.bonitasoft.studio.common.Messages.exportMenuLabel, 8);

        bdmMenuItem.setMenu(bdmMenu);
    }

    private void createItem(Menu parent, String command, String text, int index) {
        var item = new MenuItem(parent, SWT.NONE, index);
        item.setText(text);
        item.addListener(SWT.Selection, e -> commandExecutor.executeCommand(command, null));
    }

}
