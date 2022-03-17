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
package org.bonitasoft.studio.identity.actors.menu;

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class FilterMenuContributionItem extends ContributionItem {

    private static final String EDIT_DEF_COMMAND = "org.bonitasoft.studio.actors.editFilterDef";
    private static final String EDIT_IMPL_COMMAND = "org.bonitasoft.studio.actors.editFilterImpl";
    private static final String EXPORT_COMMAND = "org.bonitasoft.studio.actors.filter.export";

    private CommandExecutor commandExecutor;
    private Map<String, MenuItem> items = new HashMap<>();

    public FilterMenuContributionItem() {
        commandExecutor = new CommandExecutor();
    }

    @Override
    public void fill(Menu parent, int index) {
        var connectorsMenuItem = new MenuItem(parent, SWT.CASCADE, index);
        connectorsMenuItem.setText(Messages.actorFilters);
        connectorsMenuItem.setEnabled(true);
        connectorsMenuItem.setImage(Pics.getImage(PicsConstants.filterDef));

        var connectorsMenu = new Menu(connectorsMenuItem);

        createItem(connectorsMenu, EDIT_DEF_COMMAND, Messages.editDefinitionMenuLabel, 0)
                .setImage(Pics.getImage(PicsConstants.filterDef));
        new MenuItem(connectorsMenu, SWT.SEPARATOR, 1);
        createItem(connectorsMenu, EDIT_IMPL_COMMAND, Messages.editImplementationMenuLabel, 2)
                .setImage(Pics.getImage(PicsConstants.filterImpl));
        new MenuItem(connectorsMenu, SWT.SEPARATOR, 3);
        createItem(connectorsMenu, EXPORT_COMMAND, org.bonitasoft.studio.common.Messages.exportMenuLabel, 4);

        connectorsMenuItem.setMenu(connectorsMenu);
    }

    private MenuItem createItem(Menu parent, String command, String text, int index) {
        var item = new MenuItem(parent, SWT.NONE, index);
        item.setText(text);
        item.addListener(SWT.Selection, e -> commandExecutor.executeCommand(command, null));
        item.setEnabled(commandExecutor.canExecute(command, null));
        items.put(command, item);
        return item;
    }

    @Override
    public void update(String id) {
        items.forEach((command, item) -> item.setEnabled(commandExecutor.canExecute(command, null)));
    }

}
