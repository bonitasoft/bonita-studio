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

import java.util.HashMap;
import java.util.Map;

import org.bonitasoft.studio.common.CommandExecutor;
import org.bonitasoft.studio.identity.organization.editor.formpage.AbstractOrganizationFormPage;
import org.bonitasoft.studio.ui.editors.contribution.AbstractExportContributionItem;
import org.eclipse.swt.widgets.Shell;

public class ExportContributionItem extends AbstractExportContributionItem<AbstractOrganizationFormPage> {

    private static final String ID = "org.bonitasoft.studio.identity.organization.editor.export";
    private static final String EXPORT_COMMAND = "org.bonitasoft.studio.actors.export";
    private static final String ORGANIZATION_TO_EXPORT_PARAMETER = "organizationToExport";
    private CommandExecutor commandExecutor;

    public ExportContributionItem(AbstractOrganizationFormPage formPage) {
        super(ID, formPage);
        commandExecutor = new CommandExecutor();
    }

    @Override
    protected void exportAction(Shell shell) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put(ORGANIZATION_TO_EXPORT_PARAMETER, formPage.getEditorInput().getName());
        if (commandExecutor.canExecute(EXPORT_COMMAND, parameters)) {
            commandExecutor.executeCommand(EXPORT_COMMAND, parameters);
        }
    }

}
