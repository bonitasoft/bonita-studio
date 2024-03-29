/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.connectors.ui.wizard;

import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.bpm.model.process.Connector;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.connector.model.definition.dialog.AbstractDefinitionWizardDialog;
import org.bonitasoft.studio.connector.model.definition.dialog.ITestConfigurationListener;
import org.bonitasoft.studio.connectors.repository.ConnectorConfRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.ui.TestConfigurationListener;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Romain Bioteau
 */
public class ConnectorDefinitionWizardDialog extends AbstractDefinitionWizardDialog {

    public ConnectorDefinitionWizardDialog(final Shell parentShell, final IWizard newWizard) {
        super(parentShell, newWizard, RepositoryManager.getInstance().getRepositoryStore(ConnectorConfRepositoryStore.class), RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorDefRepositoryStore.class), RepositoryManager.getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class));
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.bpm.connector.model.definition.dialog.AbstractDefinitionWizardDialog#getTestListener()
     */
    @Override
    protected ITestConfigurationListener getTestListener(final ConnectorConfiguration configuration, final Connector connector) {
        return new TestConfigurationListener(configuration, this, connector);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.bpm.connector.model.definition.dialog.AbstractDefinitionWizardDialog#getTestListener()
     */
    @Override
    protected ITestConfigurationListener getTestListener(final ConnectorConfiguration configuration, final IWizard wizard) {
        Connector connector = null;
        if (wizard instanceof ConnectorWizard) {
            connector = ((ConnectorWizard) wizard).getWorkingCopyConnector();
        }
        return new TestConfigurationListener(configuration, this, connector);
    }

}
