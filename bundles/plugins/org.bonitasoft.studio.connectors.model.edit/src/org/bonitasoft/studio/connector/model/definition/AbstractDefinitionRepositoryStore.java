/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.connector.model.definition;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bonitasoft.bpm.connector.model.definition.Category;
import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.bpm.connector.model.definition.ConnectorDefinitionFactory;
import org.bonitasoft.bpm.connector.model.definition.util.ConnectorDefinitionAdapterFactory;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IDefinitionRepositoryStore;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.osgi.framework.Bundle;

public abstract class AbstractDefinitionRepositoryStore<T extends EMFFileStore> extends AbstractEMFRepositoryStore<T>
        implements IDefinitionRepositoryStore<T> {

    @Override
    public void createRepositoryStore(IRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ConnectorDefinition> getDefinitions() {
        return getChildren().stream()
                .map(this::toConnectorDefinition)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<T> find(ConnectorDefinition definition) {
        return getChildren().stream()
                .filter(fStore -> {
                    try {
                        ConnectorDefinition def = (ConnectorDefinition) fStore.getContent();
                        return Objects.equals(def.getId(), definition.getId())
                                && Objects.equals(def.getVersion(), definition.getVersion());
                    } catch (ReadFileStoreException e) {
                        return false;
                    }
                })
                .findFirst();
    }

    @Override
    public Optional<T> findFirst(Category category) {
        return getChildren().stream()
                .filter(fStore -> {
                    try {
                        ConnectorDefinition def = (ConnectorDefinition) fStore.getContent();
                        return def.getCategory().stream()
                                .anyMatch(c -> Objects.equals(c.getId(), category.getId()));
                    } catch (ReadFileStoreException e) {
                        return false;
                    }
                })
                .findFirst();
    }

    private ConnectorDefinition toConnectorDefinition(IRepositoryFileStore fStore) {
        ConnectorDefinition def;
        try {
            def = (ConnectorDefinition) fStore.getContent();
        } catch (Exception e) {
            def = ConnectorDefinitionFactory.eINSTANCE.createUnloadableConnectorDefinition();
            def.setId(fStore.getName());
            def.setVersion("");
        }
        if (def == null) {
            def = ConnectorDefinitionFactory.eINSTANCE.createUnloadableConnectorDefinition();
            def.setId(fStore.getName());
            def.setVersion("");
        }
        return def;
    }

    @Override
    public ConnectorDefinition getDefinition(final String id, final String version) {
        return getDefinitions().stream()
                .filter(definition -> Objects.equals(definition.getId(), id))
                .filter(definition -> Objects.equals(definition.getVersion(), version))
                .findFirst()
                .orElse(null);
    }

    @Override
    protected void addAdapterFactory(final ComposedAdapterFactory adapterFactory) {
        adapterFactory.addAdapterFactory(new ConnectorDefinitionAdapterFactory());
    }

    protected abstract Bundle getBundle();

    @Override
    public int getImportOrder() {
        return 5;
    }

    @Override
    protected T doImportInputStream(final String fileName, final InputStream inputStream) {
        /*
         * Import it in the legacy folder
         * in order to migrate it with the ConnectorsModuleMigrationStep afterward
         * (and do not worry about modules creation here)
         */
        IFolder legacyFolder = getRepository().getProject().getFolder(getName());
        try {
            final IFile file = legacyFolder.getFile(fileName);
            final File f = file.getLocation().toFile();
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
                legacyFolder.refreshLocal(IResource.DEPTH_INFINITE, AbstractRepository.NULL_PROGRESS_MONITOR);
            }
            if (file.exists()) {
                // overwrite the file if it already exists
                file.setContents(inputStream, true, true, AbstractRepository.NULL_PROGRESS_MONITOR);
            } else {
                // create the file if it does not exist
                file.create(inputStream, true, AbstractRepository.NULL_PROGRESS_MONITOR);
            }
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }
        var result = createRepositoryFileStore(fileName);
        var shouldMigrate = false;
        if (shouldMigrate) {
            try {
                var report = migrate(AbstractRepository.NULL_PROGRESS_MONITOR);
                report.merge(result.getMigrationReport());
            } catch (CoreException | MigrationException e) {
                BonitaStudioLog.error(e);
            }
        }
        return result;
    }

}
