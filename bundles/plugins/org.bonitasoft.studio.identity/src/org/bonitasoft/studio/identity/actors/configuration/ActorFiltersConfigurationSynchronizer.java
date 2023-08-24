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
package org.bonitasoft.studio.identity.actors.configuration;

import java.util.List;

import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.bpm.model.process.AbstractProcess;
import org.bonitasoft.bpm.model.process.Connector;
import org.bonitasoft.bpm.model.process.ProcessPackage;
import org.bonitasoft.bpm.model.util.FragmentTypes;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.extension.IConfigurationSynchronizer;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.provider.DefinitionResourceProvider;
import org.bonitasoft.studio.configuration.AbstractConnectorConfigurationSynchronizer;
import org.bonitasoft.studio.connector.model.implementation.AbstractConnectorImplRepositoryStore;
import org.bonitasoft.studio.identity.IdentityPlugin;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterImplRepositoryStore;

/**
 * @author Romain Bioteau
 */
public class ActorFiltersConfigurationSynchronizer extends AbstractConnectorConfigurationSynchronizer
        implements IConfigurationSynchronizer {

    @Override
    public String getFragmentContainerId() {
        return FragmentTypes.ACTOR_FILTER;
    }

    @Override
    protected List<Connector> getExistingConnectors(AbstractProcess process) {
        return ModelHelper.getAllItemsOfType(process, ProcessPackage.Literals.ACTOR_FILTER);
    }

    @Override
    protected List<ConnectorImplementation> getAllImplementations(String defId, String defVersion) {
        final ActorFilterImplRepositoryStore store = RepositoryManager.getInstance()
                .getRepositoryStore(ActorFilterImplRepositoryStore.class);
        return store.getImplementations(defId, defVersion);
    }

    @Override
    protected DefinitionResourceProvider getDefinitionResourceProvider() {
        final IRepositoryStore defStore = RepositoryManager.getInstance()
                .getRepositoryStore(ActorFilterDefRepositoryStore.class);
        return DefinitionResourceProvider.getInstance(defStore, IdentityPlugin.getDefault().getBundle());
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.configuration.AbstractConnectorConfigurationSynchronizer#getImplementationStore()
     */
    @Override
    protected AbstractConnectorImplRepositoryStore<EMFFileStore> getImplementationStore() {
        return (AbstractConnectorImplRepositoryStore) RepositoryManager.getInstance()
                .getRepositoryStore(ActorFilterImplRepositoryStore.class);
    }

}
