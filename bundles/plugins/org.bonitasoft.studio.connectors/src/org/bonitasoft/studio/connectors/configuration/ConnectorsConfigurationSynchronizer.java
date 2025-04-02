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
package org.bonitasoft.studio.connectors.configuration;

import static java.util.function.Predicate.not;

import java.util.List;
import java.util.stream.Collectors;

import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.bpm.model.configuration.Configuration;
import org.bonitasoft.bpm.model.configuration.ConfigurationFactory;
import org.bonitasoft.bpm.model.configuration.ConfigurationPackage;
import org.bonitasoft.bpm.model.configuration.DefinitionMapping;
import org.bonitasoft.bpm.model.kpi.DatabaseKPIBinding;
import org.bonitasoft.bpm.model.kpi.KpiPackage;
import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.bpm.model.process.ActorFilter;
import org.bonitasoft.bpm.model.process.Connector;
import org.bonitasoft.bpm.model.util.FragmentTypes;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.configuration.AbstractConnectorConfigurationSynchronizer;
import org.bonitasoft.studio.connector.model.implementation.AbstractConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.ConnectorPlugin;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.repository.DatabaseConnectorPropertiesRepositoryStore;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @author Romain Bioteau
 */
public class ConnectorsConfigurationSynchronizer extends AbstractConnectorConfigurationSynchronizer {

    private DatabaseConnectorPropertiesRepositoryStore store;

    @Override
    public String getFragmentContainerId() {
        return FragmentTypes.CONNECTOR;
    }

    @Override
    protected List<Connector> getExistingConnectors(AbstractProcess process) {
        return ModelHelper.getAllElementOfTypeIn(process, Connector.class).stream()
                .filter(not(ActorFilter.class::isInstance))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    protected List<ConnectorImplementation> getAllImplementations(String defId, String defVersion) {
        final ConnectorImplRepositoryStore store = RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorImplRepositoryStore.class);
        return store.getImplementations(defId, defVersion);
    }

    @Override
    protected DefinitionResourceProvider getDefinitionResourceProvider() {
        final IRepositoryStore<?> defStore = RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorDefRepositoryStore.class);
        return DefinitionResourceProvider.getInstance(defStore, ConnectorPlugin.getDefault().getBundle());
    }

    @Override
    protected void addNewConnectorDefinition(Configuration configuration, AbstractProcess process, CompoundCommand cc,
            EditingDomain editingDomain) {
        super.addNewConnectorDefinition(configuration, process, cc, editingDomain);
        addNewKPIConnectorDefinition(configuration, process, cc, editingDomain);
    }

    private void addNewKPIConnectorDefinition(Configuration configuration, AbstractProcess process, CompoundCommand cc,
            EditingDomain editingDomain) {
        final List<DatabaseKPIBinding> kpiBindings = ModelHelper.getAllItemsOfType(process,
                KpiPackage.Literals.DATABASE_KPI_BINDING);
        if (!kpiBindings.isEmpty()) {
            final String defId = DB_CONNECTOR_FOR_KPI_ID;
            final String defVersion = DB_CONNECTOR_VERSION;
            boolean exists = false;
            for (final DefinitionMapping association : configuration.getDefinitionMappings()) {
                if (FragmentTypes.CONNECTOR.equals(association.getType()) && association.getDefinitionId().equals(defId)
                        && association.getDefinitionVersion().equals(defVersion)) {
                    exists = true;
                    updateAssociation(configuration, association, cc, editingDomain);
                    break;
                }
            }
            if (!exists) {
                final DefinitionMapping newAssociation = ConfigurationFactory.eINSTANCE.createDefinitionMapping();
                newAssociation.setDefinitionId(defId);
                newAssociation.setDefinitionVersion(defVersion);
                newAssociation.setType(getFragmentContainerId());
                editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, configuration,
                        ConfigurationPackage.Literals.CONFIGURATION__DEFINITION_MAPPINGS, newAssociation));
                updateAssociation(configuration, newAssociation, cc, editingDomain);
            }
        }
    }

    @Override
    protected AbstractConnectorImplRepositoryStore<EMFFileStore> getImplementationStore() {
        return (AbstractConnectorImplRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(ConnectorImplRepositoryStore.class);
    }

}
