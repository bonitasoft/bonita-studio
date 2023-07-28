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
package org.bonitasoft.studio.connector.model.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.bpm.connector.model.implementation.util.ConnectorImplementationAdapterFactory;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;


public abstract class AbstractConnectorImplRepositoryStore<T extends EMFFileStore> extends AbstractEMFRepositoryStore<T>
        implements IImplementationRepositoryStore {

	
	@Override
	public void createRepositoryStore(IRepository repository) {
		this.repository = repository;
	}
	
    @Override
    protected void addAdapterFactory(final ComposedAdapterFactory adapterFactory) {
        adapterFactory.addAdapterFactory(new ConnectorImplementationAdapterFactory());
    }

    @Override
    public ConnectorImplementation getImplementation(final String id, final String version) {
        for (final ConnectorImplementation impl : getImplementations()) {
            if (impl.getImplementationId().equals(id) && impl.getImplementationVersion().equals(version)) {
                return impl;
            }
        }
        return null;
    }

    @Override
    public List<ConnectorImplementation> getImplementations() {
        final List<ConnectorImplementation> result = new ArrayList<>();
        for (final IRepositoryFileStore fileStore : getChildren()) {
            try {
                result.add((ConnectorImplementation) fileStore.getContent());
            } catch (final ReadFileStoreException e) {
                BonitaStudioLog.error("Failed to retrieve connector definition", e);
            }
        }
        return result;
    }

    @Override
    public List<ConnectorImplementation> getImplementations(String definitionId, String definitionVersion) {
        return getImplementations().stream()
                .filter(impl -> Objects.equals(impl.getDefinitionId(), definitionId)
                        && Objects.equals(impl.getDefinitionVersion(), definitionVersion))
                .collect(Collectors.toList());
    }

    @Override
    public IRepositoryFileStore getImplementationFileStore(final String implId, final String implVersion) {
        for (final IRepositoryFileStore implStore : getChildren()) {
            try {
                final ConnectorImplementation impl = (ConnectorImplementation) implStore.getContent();
                if (impl != null && implId.equals(impl.getImplementationId())
                        && implVersion.equals(impl.getImplementationVersion())) {
                    return implStore;
                }
            } catch (final ReadFileStoreException e) {
                BonitaStudioLog.error("Failed to retrieve connector implementation", e);
            }

        }
        return null;
    }

}
