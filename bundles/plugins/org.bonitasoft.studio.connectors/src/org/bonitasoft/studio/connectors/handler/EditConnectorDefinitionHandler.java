/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.connectors.handler;

import org.bonitasoft.studio.common.jface.CustomWizardDialog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.dialog.ConnectorDefinitionWizardDialog;
import org.bonitasoft.studio.connector.model.i18n.DefinitionResourceProvider;
import org.bonitasoft.studio.connector.model.i18n.Messages;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.ui.wizard.ConnectorDefinitionWizard;
import org.bonitasoft.studio.connectors.ui.wizard.SelectUserConnectorDefinitionWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 */
public class EditConnectorDefinitionHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        SelectUserConnectorDefinitionWizard selectWizard = new SelectUserConnectorDefinitionWizard();
        WizardDialog selectDialog = new CustomWizardDialog(Display.getCurrent().getActiveShell(), selectWizard,
                Messages.Edit);
        if (selectDialog.open() == Dialog.OK) {
            ConnectorDefRepositoryStore defStore = RepositoryManager.getInstance()
                    .getRepositoryStore(ConnectorDefRepositoryStore.class);
            final DefinitionResourceProvider messageProvider = DefinitionResourceProvider.getInstance(defStore,
                    ConnectorPlugin.getDefault().getBundle());
            final ConnectorDefinitionWizard wizard = new ConnectorDefinitionWizard(selectWizard.getDefinition(),
                    messageProvider);
            WizardDialog wd = new ConnectorDefinitionWizardDialog(Display.getCurrent().getActiveShell(), wizard,
                    messageProvider);
            wd.open();
        }
        return null;
    }

}
