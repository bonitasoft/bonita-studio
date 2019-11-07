/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.connector.model.definition.dialog;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.IDefinitionRepositoryStore;
import org.bonitasoft.studio.connector.model.definition.wizard.SaveConnectorConfigurationWizard;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;


/**
 * @author Romain Bioteau
 *
 */
public class SaveConfigurationListener implements Listener {

    private final IRepositoryStore<? extends IRepositoryFileStore> store;
    private final ConnectorConfiguration configuration;
    private final WizardDialog dialog;
    private final IDefinitionRepositoryStore definitionRepositoryStore;

    public SaveConfigurationListener(WizardDialog dialog, ConnectorConfiguration configuration,
            IRepositoryStore<? extends IRepositoryFileStore> configurationStore, IDefinitionRepositoryStore definitionRepositoryStore) {
        store = configurationStore ;
        this.configuration = configuration ;
        this.definitionRepositoryStore = definitionRepositoryStore;
        this.dialog = dialog ;
    }

    /* (non-Javadoc)
     * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
     */
    @Override
    public void handleEvent(Event event) {
        final WizardDialog saveDialog = new WizardDialog(Display.getDefault().getActiveShell(),
                new SaveConnectorConfigurationWizard(configuration, store, definitionRepositoryStore));
        if(saveDialog.open() == Dialog.OK){
            dialog.updateButtons() ;
        }
    }

}
