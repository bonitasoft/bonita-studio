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
package org.bonitasoft.studio.diagram.custom.menu;

import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class NewDiagramContributionItem extends ContributionItem {

    private final static String NEW_COMMAND = "org.bonitasoft.studio.diagram.command.newDiagram";
    private CommandExecutor commandExecutor;

    public NewDiagramContributionItem() {
        commandExecutor = new CommandExecutor();
    }

    @Override
    public void fill(Menu parent, int index) {
        MenuItem item = new MenuItem(parent, SWT.NONE, index);
        item.setText(Messages.newDiagramCommandName);
        item.addListener(SWT.Selection, e -> commandExecutor.executeCommand(NEW_COMMAND, null));
        item.setEnabled(true);
        item.setImage(Pics.getImage(PicsConstants.diagram));
    }

}
