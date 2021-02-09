/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

import java.util.Set;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.DefinitionConfigurationFileStore;
import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.connectors.repository.ConnectorConfRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.ui.wizard.page.SelectConnectorConfigurationWizardPage;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.wizard.IWizardPage;

/**
 * @author Aurelie Zara
 */
public class EditConnectorConfigurationWizard extends ConnectorWizard {

    private SelectConnectorConfigurationWizardPage selectConfigurationPage;

    private final ConnectorDefRepositoryStore connectorDefStore;

    private final ConnectorConfRepositoryStore connectorConfStore;

    public EditConnectorConfigurationWizard(final EObject container, final EStructuralFeature connectorContainmentFeature,
            final Set<EStructuralFeature> featureToCheckForUniqueID) {
        super(container, connectorContainmentFeature, featureToCheckForUniqueID);
        setEditMode(false);
        connectorDefStore = RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
        connectorConfStore = RepositoryManager.getInstance().getRepositoryStore(ConnectorConfRepositoryStore.class);
        setForcePreviousAndNextButtons(true);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.connectors.ui.wizard.ConnectorWizard#addPages()
     */
    @Override
    public void addPages() {
        initialize();
        selectConfigurationPage = new SelectConnectorConfigurationWizardPage();
        addPage(selectConfigurationPage);
    }

    @Override
    public IWizardPage getNextPage(final IWizardPage page) {
        if (page.equals(selectConfigurationPage)) {
            final ConnectorConfiguration conf = selectConfigurationPage.getSelectedConfiguration();
            if (conf != null) {
                final ExtendedConnectorDefinition definition = connectorDefStore.getResourceProvider()
                        .getConnectorDefinitionRegistry()
                        .find(conf.getDefinitionId(), conf.getVersion())
                        .orElse(null);
                connectorWorkingCopy.setDefinitionId(definition.getId());
                connectorWorkingCopy.setDefinitionVersion(definition.getVersion());
                connectorWorkingCopy.setConfiguration(conf);
                extension = findCustomWizardExtension(definition);
                recreateConnectorConfigurationPages(definition, false);
            }
        }
        return super.getNextPage(page);
    }

    @Override
    protected IWizardPage getNameAndDescriptionPage() {
        return null;
    }

    @Override
    protected void addOuputPage(final ExtendedConnectorDefinition definition) {

    }

    @Override
    public boolean performFinish() {
        final ConnectorConfiguration conf = connectorWorkingCopy.getConfiguration();
        final DefinitionConfigurationFileStore fileStore = connectorConfStore.getChild(conf.getName() + "." + ConnectorConfRepositoryStore.CONF_EXT, true);
        fileStore.save(conf);
        return true;

    }

}
