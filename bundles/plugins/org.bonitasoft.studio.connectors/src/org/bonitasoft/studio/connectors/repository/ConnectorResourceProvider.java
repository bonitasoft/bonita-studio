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
package org.bonitasoft.studio.connectors.repository;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.common.FragmentTypes;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.provider.IBOSArchiveFileStoreProvider;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connectors.i18n.Messages;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.DefinitionMapping;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 */
public class ConnectorResourceProvider implements IBOSArchiveFileStoreProvider {

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.provider.IBOSArchiveFileStoreProvider#getFileStoreForConfiguration(org.bonitasoft.studio.model.process.
     * AbstractProcess, org.bonitasoft.studio.model.configuration.Configuration)
     */
    @Override
    public Set<IRepositoryFileStore<?>> getFileStoreForConfiguration(final AbstractProcess process,
            final Configuration configuration) {
        final Set<IRepositoryFileStore<?>> files = new HashSet<>();

        final ConnectorDefRepositoryStore connectorDefSotre = RepositoryManager.getInstance().getRepositoryStore(
                ConnectorDefRepositoryStore.class);
        final ConnectorImplRepositoryStore connectorImplStore = RepositoryManager.getInstance().getRepositoryStore(
                ConnectorImplRepositoryStore.class);
        final ConnectorSourceRepositoryStore connectorSourceStore = RepositoryManager.getInstance().getRepositoryStore(
                ConnectorSourceRepositoryStore.class);
        final DependencyRepositoryStore depStore = RepositoryManager.getInstance().getRepositoryStore(
                DependencyRepositoryStore.class);
        for (final DefinitionMapping mapping : configuration.getDefinitionMappings()) {
            if (mapping.getType().equals(FragmentTypes.CONNECTOR)) {
                final String defId = mapping.getDefinitionId();
                final String defVersion = mapping.getDefinitionVersion();
                final ConnectorDefinition def = connectorDefSotre.getDefinition(defId, defVersion);
                if (def != null) {
                    final IRepositoryFileStore definition = ((IRepositoryStore<? extends IRepositoryFileStore>) connectorDefSotre)
                            .getChild(URI.decode(def
                                    .eResource().getURI().lastSegment()), true);
                    if (definition != null && definition.canBeShared()) {
                        files.add(definition);

                        try {
                            for (final String jarName : ((ConnectorDefinition) definition.getContent())
                                    .getJarDependency()) {
                                final IRepositoryFileStore jarFile = depStore.getChild(jarName, true);
                                if (jarFile != null) {
                                    files.add(jarFile);
                                }
                            }
                        } catch (final ReadFileStoreException e) {
                            BonitaStudioLog.error("Failed read connector definition content", e);
                        }
                    }

                    final String implId = mapping.getImplementationId();
                    final String implVersion = mapping.getImplementationVersion();
                    if (implId == null) {
                        MessageDialog.openError(Display.getDefault().getActiveShell(),
                                Messages.noImplementationFoundErrorTitle,
                                Messages.bind(Messages.noImplementationFoundErrorMessage, def.getId()));
                        return null;
                    }
                    final IRepositoryFileStore implementation = connectorImplStore.getImplementationFileStore(implId,
                            implVersion);
                    if (implementation != null && implementation.canBeShared()) {
                        files.add(implementation);
                        try {
                            final ConnectorImplementation impl = (ConnectorImplementation) implementation.getContent();
                            final String className = impl.getImplementationClassname();
                            final String packageName = className.substring(0, className.lastIndexOf("."));
                            final IRepositoryFileStore packageFileStore = connectorSourceStore.getChild(packageName,
                                    true);
                            if (packageFileStore != null) {
                                files.add(packageFileStore);
                            }

                            for (final String jarName : impl.getJarDependencies().getJarDependency()) {
                                final IRepositoryFileStore jarFile = depStore.getChild(jarName, true);
                                if (jarFile != null) {
                                    files.add(jarFile);
                                }
                            }
                        } catch (final ReadFileStoreException e) {
                            BonitaStudioLog.error("Failed read connector implementation content", e);
                        }

                    }
                }
            }
        }

        return files;
    }

}
