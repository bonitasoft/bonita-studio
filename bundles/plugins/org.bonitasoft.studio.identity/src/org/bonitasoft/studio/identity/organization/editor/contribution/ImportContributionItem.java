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
package org.bonitasoft.studio.identity.organization.editor.contribution;

import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.bonitasoft.studio.identity.organization.editor.formpage.AbstractOrganizationFormPage;
import org.bonitasoft.studio.pics.Pics;
import org.bonitasoft.studio.pics.PicsConstants;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class ImportContributionItem extends ContributionItem {

    public static final String ID = "org.bonitasoft.studio.identity.organization.editor.import";
    private static final String IMPORT_COMMAND = "org.bonitasoft.studio.actors.import";

    protected AbstractOrganizationFormPage formPage;
    protected ToolItem item;
    private CommandExecutor commandExecutor;

    public ImportContributionItem(AbstractOrganizationFormPage formPage) {
        super(ID);
        this.formPage = formPage;
        commandExecutor = new CommandExecutor();
    }

    @Override
    public void fill(ToolBar parent, int index) {
        item = new ToolItem(parent, SWT.PUSH);
        item.setText(Messages.importOrganization);
        item.setToolTipText(Messages.importOrganization);
        item.setImage(Pics.getImage(PicsConstants.import_artifact));
        item.addListener(SWT.Selection, e -> onClick());
    }

    private void onClick() {
        if (commandExecutor.canExecute(IMPORT_COMMAND, null)) {
            commandExecutor.executeCommand(IMPORT_COMMAND, null);
        }
    }

}
