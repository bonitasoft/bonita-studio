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
package org.bonitasoft.studio.identity.actors.ui.wizard;

import java.util.Collections;
import java.util.Set;

import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.bpm.model.connectorconfiguration.ConnectorConfigurationFactory;
import org.bonitasoft.bpm.model.process.ActorFilter;
import org.bonitasoft.bpm.model.process.Connector;
import org.bonitasoft.bpm.model.process.ProcessFactory;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IDefinitionRepositoryStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.provider.ConnectorDefinitionRegistry;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.common.repository.provider.ExtendedConnectorDefinition;
import org.bonitasoft.studio.connector.model.definition.migration.ConnectorConfigurationMigrator;
import org.bonitasoft.studio.connector.model.definition.wizard.SelectNameAndDescWizardPage;
import org.bonitasoft.studio.connector.model.implementation.wizard.AbstractDefinitionSelectionImpementationWizardPage;
import org.bonitasoft.studio.connectors.ui.wizard.ConnectorWizard;
import org.bonitasoft.studio.identity.IdentityPlugin;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.identity.actors.ui.wizard.page.SelectAdvancedFilterDefinitionWizardPage;
import org.bonitasoft.studio.identity.i18n.Messages;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.osgi.util.NLS;

public class FilterWizard extends ConnectorWizard {

    public FilterWizard(EObject container, EStructuralFeature connectorContainmentFeature,
            Set<EStructuralFeature> featureToCheckForUniqueID) {
        super(container, connectorContainmentFeature, featureToCheckForUniqueID);
        setWindowTitle(Messages.actorFilters);
    }

    public FilterWizard(ActorFilter filter, EStructuralFeature connectorContainmentFeature,
            Set<EStructuralFeature> featureToCheckForUniqueID, ConnectorConfigurationMigrator migrator) {
        super(filter, connectorContainmentFeature, featureToCheckForUniqueID, migrator);
    }

    @Override
    protected void initialize() {
        if (!(connectorWorkingCopy instanceof ActorFilter)) {
            connectorWorkingCopy = ProcessFactory.eINSTANCE.createActorFilter();
            connectorWorkingCopy
                    .setConfiguration(ConnectorConfigurationFactory.eINSTANCE.createConnectorConfiguration());
        }
        setUseEvents(false);
        super.initialize();
    }

    @Override
    protected DefinitionResourceProvider initMessageProvider() {
        IRepositoryStore<? extends IRepositoryFileStore> store = RepositoryManager.getInstance()
                .getRepositoryStore(ActorFilterDefRepositoryStore.class);
        return DefinitionResourceProvider.getInstance(store, IdentityPlugin.getDefault().getBundle());
    }

    @Override
    protected IDefinitionRepositoryStore getDefinitionStore() {
        return RepositoryManager.getInstance()
                .getRepositoryStore(ActorFilterDefRepositoryStore.class);
    }

    @Override
    public ExtendedConnectorDefinition getDefinition() {
        ConnectorDefinitionRegistry registry = getDefinitionStore().getResourceProvider().getConnectorDefinitionRegistry();
        if (connectorWorkingCopy.getDefinitionId() != null && !connectorWorkingCopy.getDefinitionId().isEmpty()) {
            return registry.find(connectorWorkingCopy.getDefinitionId(),
                    connectorWorkingCopy.getDefinitionVersion()).orElse(null);
        }
        return null;
    }

    @Override
    protected AbstractDefinitionSelectionImpementationWizardPage getSelectionPage(Connector connectorWorkingCopy,
            DefinitionResourceProvider resourceProvider) {
        return new SelectAdvancedFilterDefinitionWizardPage(connectorWorkingCopy,
                Collections.<ConnectorImplementation> emptyList(),
                resourceProvider.getConnectorDefinitionRegistry().getDefinitions(),
                Messages.selectFilterDefinitionTitle,
                Messages.selectFilterDefinitionDesc);
    }

    @Override
    public IWizardPage getNextPage(IWizardPage page) {
        IWizardPage p = super.getNextPage(page);
        if (p instanceof SelectNameAndDescWizardPage) {
            ConnectorDefinition def = selectionPage.getSelectedConnectorDefinition();
            String connectorDefinitionLabel = messageProvider.getConnectorDefinitionLabel(def);
            if (connectorDefinitionLabel == null && def != null) {
                connectorDefinitionLabel = def.getId();
            }
            p.setTitle(NLS.bind(Messages.filterWizardTitle, connectorDefinitionLabel,
                    connectorWorkingCopy.getDefinitionVersion()));
            p.setDescription(Messages.filterWizardMessage);
        }
        return p;
    }

}
