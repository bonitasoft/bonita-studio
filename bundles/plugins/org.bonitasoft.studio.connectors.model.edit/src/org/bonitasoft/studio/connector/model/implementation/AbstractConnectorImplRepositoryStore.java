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

import java.io.File;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.bpm.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.bpm.connector.model.implementation.util.ConnectorImplementationAdapterFactory;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.ImportArchiveData;
import org.bonitasoft.studio.common.repository.filestore.EMFFileStore;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.repository.store.AbstractEMFRepositoryStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;

public abstract class AbstractConnectorImplRepositoryStore<T extends EMFFileStore> extends AbstractEMFRepositoryStore<T>
        implements IImplementationRepositoryStore {

    /** For import of legacy bos. True when import is in the source folder. */
    private boolean useSourceFolderAsParent;

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

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore#doImportArchiveData(org.bonitasoft.studio.common.repository.ImportArchiveData,
     * org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    protected T doImportArchiveData(ImportArchiveData importArchiveData, IProgressMonitor monitor)
            throws CoreException {
        // We may encounter have both implementations and source folders here...
        var folderSegment = Path.of(importArchiveData.getEntry().getName()).subpath(1, 2).toString();
        this.useSourceFolderAsParent = (!getName().equals(folderSegment)
                && folderSegment.equals(getLegacySourceFolderName()));
        return super.doImportArchiveData(importArchiveData, monitor);
    }

    /**
     * @return the name of the legacy source folder
     */
    protected abstract String getLegacySourceFolderName();

    @Override
    protected T doImportInputStream(final String fileName, final InputStream inputStream) {
        /*
         * Import it in the legacy folder
         * in order to migrate it with the ConnectorsModuleMigrationStep afterward
         * (and do not worry about modules creation here)
         */
        IFolder legacyFolder = getRepository().getProject()
                .getFolder(useSourceFolderAsParent ? getLegacySourceFolderName() : getName());
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
