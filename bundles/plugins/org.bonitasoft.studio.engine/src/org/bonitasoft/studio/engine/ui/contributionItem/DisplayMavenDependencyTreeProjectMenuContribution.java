/**
 * Copyright (C) 2025 Bonitasoft S.A.
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
package org.bonitasoft.studio.engine.ui.contributionItem;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.engine.i18n.Messages;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;

public class DisplayMavenDependencyTreeProjectMenuContribution extends ContributionItem {

    private static final String DISPLAY_TREE_COMMAND = "org.bonitasoft.studio.engine.showMavenDependencyTreeCommand";

    public DisplayMavenDependencyTreeProjectMenuContribution() {

    }

    private Command getCommand() {
        final ICommandService service = PlatformUI.getWorkbench().getService(ICommandService.class);
        return service.getCommand(DISPLAY_TREE_COMMAND);
    }

    @Override
    public void fill(Menu parent, int index) {
        var item = new MenuItem(parent, SWT.NONE, index);
        item.setText(Messages.displayTreeDependencies);
        item.addListener(SWT.Selection, e -> {
            try {
                getCommand().executeWithChecks(new ExecutionEvent());
            } catch (Exception ex) {
                BonitaStudioLog.error(ex);
            }
        });
        item.setEnabled(true);
    }

}
