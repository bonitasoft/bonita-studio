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
package org.bonitasoft.studio.actors.configuration;

import java.io.IOException;

import javax.inject.Inject;

import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.actors.repository.ActorFilterImplRepositoryStore;
import org.bonitasoft.studio.actors.repository.ActorFilterSourceRepositoryStore;
import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.store.SourceRepositoryStore;
import org.bonitasoft.studio.connector.model.implementation.IImplementationRepositoryStore;
import org.bonitasoft.studio.connectors.configuration.ConnectorBarResourceProvider;
import org.bonitasoft.studio.model.configuration.Configuration;

/**
 * @author Romain Bioteau
 */
public class ActorFilterBarResourceProvider extends ConnectorBarResourceProvider implements BARResourcesProvider {

    @Inject
    public ActorFilterBarResourceProvider(final RepositoryAccessor repositoryAccessor) {
        super(repositoryAccessor);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.connectors.configuration.ConnectorBarResourceProvider#getSourceStore()
     */
    @Override
    protected SourceRepositoryStore<?> getSourceStore() {
        return repositoryAccessor.getRepositoryStore(ActorFilterSourceRepositoryStore.class);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.connectors.configuration.ConnectorBarResourceProvider#getImplementationStore()
     */
    @Override
    protected IImplementationRepositoryStore getImplementationStore() {
        return repositoryAccessor.getRepositoryStore(ActorFilterImplRepositoryStore.class);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.connectors.configuration.ConnectorBarResourceProvider#getFragmentType()
     */
    @Override
    protected String getFragmentType() {
        return FragmentTypes.ACTOR_FILTER;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.connectors.configuration.ConnectorBarResourceProvider#addImplementation(org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder,
     * java.lang.String, org.bonitasoft.studio.common.repository.filestore.EMFFileStore, org.bonitasoft.studio.model.configuration.Configuration)
     */
    @Override
    protected void addImplementation(BusinessArchiveBuilder builder, String connectorImplementationFilename, EMFFileStore implementationFileStore,
            Configuration configuration) throws IOException {
        builder.addUserFilters(newBarResource(connectorImplementationFilename, implementationFileStore, configuration));
    }

}
