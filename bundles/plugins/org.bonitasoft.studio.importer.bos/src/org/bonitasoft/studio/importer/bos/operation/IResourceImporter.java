/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.importer.bos.operation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.Pair;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.internal.jobs.Counter;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Romain Bioteau
 *
 */
public class IResourceImporter {

    final Comparator<IResource> importFolderComparator = new ImportFolderComparator();
    private Set<String> resourcesToOpen;
    private final List<IRepositoryFileStore> fileStoresToOpen = new ArrayList<IRepositoryFileStore>();
    private final List<IRepositoryFileStore> importedProcesses = new ArrayList<IRepositoryFileStore>();
    private final List<IRepositoryFileStore> importedFileStore = new ArrayList<IRepositoryFileStore>();
    private final List<String> failedProcesses = new ArrayList<String>();

    public void run(final IContainer rootContainer, final IRepository repository, final IProgressMonitor monitor) throws ResourceImportException {
        Assert.isLegal(rootContainer != null);
        Assert.isLegal(repository != null);
        final List<IRepositoryStore<? extends IRepositoryFileStore>> allRepositories = repository.getAllStores();
        final Map<String, IRepositoryStore<? extends IRepositoryFileStore>> repositoryStoreMap = new HashMap<String, IRepositoryStore<? extends IRepositoryFileStore>>();
        for (final IRepositoryStore<? extends IRepositoryFileStore> repositoryStore : allRepositories) {
            repositoryStoreMap.put(repositoryStore.getName(), repositoryStore);
        }

        final List<IFolder> folderSortedList = getFolders(rootContainer);

        Collections.sort(folderSortedList, importFolderComparator);
        final Counter nbFileToImport = new Counter();
        for (final IFolder folder : folderSortedList) {
            try {
                processContainer(folder, nbFileToImport);
            } catch (final CoreException e) {

            }
        }
        monitor.beginTask("Importing BOS archive...", (int) nbFileToImport.increment());
        for (final IFolder folder : folderSortedList) {
            findRepository(repositoryStoreMap, folder, monitor);
        }
    }

    void processContainer(final IContainer container, final Counter nbFileToImport) throws CoreException
    {
        final IResource[] members = container.members();
        for (final IResource member : members)
        {
            if (member instanceof IFolder) {
                processContainer((IFolder) member, nbFileToImport);
            } else if (member instanceof IFile) {
                nbFileToImport.increment();
            }
        }
    }

    public List<IRepositoryFileStore> getImportedProcesses() {
        return importedProcesses;
    }

    public List<IRepositoryFileStore> getFileStoresToOpen() {
        return fileStoresToOpen;
    }

    public List<String> getFailedProcesses() {
        return failedProcesses;
    }

    private void findRepository(
            final Map<String, IRepositoryStore<? extends IRepositoryFileStore>> repositoryStoreMap,
            final IFolder folder, final IProgressMonitor monitor) throws ResourceImportException {
        final String path = folder.getProjectRelativePath().removeFirstSegments(1).toOSString();
        final IRepositoryStore<? extends IRepositoryFileStore> store = repositoryStoreMap.get(path);
        if (store != null) {
            importRepositoryStore(new Pair<IRepositoryStore<? extends IRepositoryFileStore>, IFolder>(store, folder), monitor);
        } else {
            for (final IFolder subFolder : getFolders(folder)) {
                findRepository(repositoryStoreMap, subFolder, monitor);
            }
        }
    }

    protected List<IFolder> getFolders(final IContainer container) throws ResourceImportException {
        IResource[] folders;
        try {
            folders = container.members(IContainer.FOLDER);
        } catch (final CoreException e) {
            throw new ResourceImportException("Failed to retrieve folders of resource " + container.getName(), e);
        }
        final List<IFolder> folderList = new ArrayList<IFolder>();
        if (folders != null) {
            for (final IResource res : folders) {
                if (res instanceof IFolder) {
                    folderList.add((IFolder) res);
                }
            }
        }
        return folderList;
    }

    protected void importRepositoryStore(final Pair<IRepositoryStore<? extends IRepositoryFileStore>, IFolder> pair, final IProgressMonitor monitor)
            throws ResourceImportException {
        final IFolder storeFolder = pair.getSecond();
        final IRepositoryStore<? extends IRepositoryFileStore> store = pair.getFirst();
        IResource[] members = null;
        try {
            members = storeFolder.members();
        } catch (final CoreException e) {
            throw new ResourceImportException("Failed to retreive members of resource :" + storeFolder.getName(), e);
        }
        for (final IResource child : members) {
            final String filename = child.getName();
            final boolean openAfterImport = resourcesToOpen != null && resourcesToOpen.contains(filename)
                    || resourcesToOpen == null;
            monitor.subTask(String.format("Importing %s in %s", filename, storeFolder.getName()));
            final IRepositoryFileStore fileStore = store.importIResource(filename, child);
            importedFileStore.add(fileStore);
            if (filename.endsWith(".proc") && fileStore != null) {
                importedProcesses.add(fileStore);
            }

            if (fileStore != null && openAfterImport) {
                fileStoresToOpen.add(fileStore);
            }

            if (fileStore == null && filename.endsWith(".proc")) {
                failedProcesses.add(filename);
            }
            monitor.worked(1);
        }
    }

    public void setResourcesToOpen(final Set<String> resourcesToOpen) {
        this.resourcesToOpen = resourcesToOpen;
    }

    public List<IRepositoryFileStore> getImportedFileStore() {
        return importedFileStore;
    }
}
