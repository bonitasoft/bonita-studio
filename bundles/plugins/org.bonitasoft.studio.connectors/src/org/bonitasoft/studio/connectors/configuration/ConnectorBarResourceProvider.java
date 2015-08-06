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

import java.io.IOException;

import javax.inject.Inject;

import org.bonitasoft.engine.bpm.bar.BarResource;
import org.bonitasoft.engine.bpm.bar.BusinessArchiveBuilder;
import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.extension.BARResourcesProvider;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.store.SourceRepositoryStore;
import org.bonitasoft.studio.connector.model.implementation.AbstractConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorSourceRepositoryStore;

/**
 * @author Romain Bioteau
 */
public class ConnectorBarResourceProvider extends AbstractConnectorBarResourceProvider implements BARResourcesProvider {

    @Inject
    public ConnectorBarResourceProvider(final RepositoryAccessor repositoryAccessor) {
        super(repositoryAccessor);
    }

    @Override
    protected AbstractConnectorImplRepositoryStore<?> getImplementationStore() {
        return repositoryAccessor.getRepositoryStore(ConnectorImplRepositoryStore.class);
    }

    @Override
    protected String getFragmentType() {
        return FragmentTypes.CONNECTOR;
    }

    @Override
    protected SourceRepositoryStore<?> getSourceStore() {
        return repositoryAccessor.getRepositoryStore(ConnectorSourceRepositoryStore.class);
    }

    @Override
    protected void addImplementation(final BusinessArchiveBuilder builder, final String connectorImplementationFilename,
            final EMFFileStore implementationFileStore)
            throws IOException {
        builder.addConnectorImplementation(new BarResource(connectorImplementationFilename, implementationFileStore.toByteArray()));
    }

}
