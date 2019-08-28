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
package org.bonitasoft.studio.actors.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.provider.IBOSArchiveFileStoreProvider;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.DefinitionMapping;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.emf.common.util.URI;

/**
 * @author Romain Bioteau
 */
public class ActorFilterResourceProvider implements IBOSArchiveFileStoreProvider {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.provider.IBOSArchiveFileStoreProvider#getFileStoreForConfiguration(org.bonitasoft.studio.model.process.
     * AbstractProcess, org.bonitasoft.studio.model.configuration.Configuration)
     */
    @Override
    public Set<IRepositoryFileStore> getFileStoreForConfiguration(final AbstractProcess process, final Configuration configuration) {
        final Set<IRepositoryFileStore> files = new HashSet<IRepositoryFileStore>();

        final ActorFilterDefRepositoryStore filterDefSotre = RepositoryManager.getInstance().getRepositoryStore(ActorFilterDefRepositoryStore.class);
        final ActorFilterImplRepositoryStore filterImplStore = RepositoryManager.getInstance().getRepositoryStore(ActorFilterImplRepositoryStore.class);
        final ActorFilterSourceRepositoryStore filterSourceStore = RepositoryManager.getInstance().getRepositoryStore(ActorFilterSourceRepositoryStore.class);
        final DependencyRepositoryStore depStore = RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class);
        final List<ConnectorDefinition> existingDefinitions = filterDefSotre.getDefinitions();

        for (final DefinitionMapping mapping : configuration.getDefinitionMappings()) {
            if (mapping.getType().equals(FragmentTypes.ACTOR_FILTER)) {
                final String defId = mapping.getDefinitionId();
                final String defVersion = mapping.getDefinitionVersion();
                final ConnectorDefinition def = filterDefSotre.getDefinition(defId, defVersion, existingDefinitions);
                if (def != null) {
                    final IRepositoryFileStore definition = ((IRepositoryStore<? extends IRepositoryFileStore>) filterDefSotre).getChild(URI.decode(def
                            .eResource().getURI().lastSegment()));
                    if (definition != null && definition.canBeShared()) {
                        files.add(definition);

                        ConnectorDefinition connectorDefinition;
                        try {
                            connectorDefinition = (ConnectorDefinition) definition.getContent();
                            for (final String jarName : connectorDefinition.getJarDependency()) {
                                final IRepositoryFileStore jarFile = depStore.getChild(jarName);
                                if (jarFile != null) {
                                    files.add(jarFile);
                                }
                            }
                        } catch (final ReadFileStoreException e) {
                            BonitaStudioLog.error("Failed to read connector configuration", e);
                        }

                    }
                }
                final String implId = mapping.getImplementationId();
                final String implVersion = mapping.getImplementationVersion();
                final IRepositoryFileStore implementation = filterImplStore.getChild(NamingUtils.toConnectorImplementationFilename(implId, implVersion, true));
                if (implementation != null && implementation.canBeShared()) {
                    files.add(implementation);

                    ConnectorImplementation impl;
                    try {
                        impl = (ConnectorImplementation) implementation.getContent();
                        final String className = impl.getImplementationClassname();
                        final String packageName = className.substring(0, className.lastIndexOf("."));
                        final IRepositoryFileStore packageFileStore = filterSourceStore.getChild(packageName);
                        if (packageFileStore != null) {
                            files.add(packageFileStore);
                        }

                        for (final String jarName : impl.getJarDependencies().getJarDependency()) {
                            final IRepositoryFileStore jarFile = depStore.getChild(jarName);
                            if (jarFile != null) {
                                files.add(jarFile);
                            }
                        }
                    } catch (final ReadFileStoreException e) {
                        BonitaStudioLog.error("Failed to read connector implementation", e);
                    }

                }
            }
        }

        return files;
    }

}
