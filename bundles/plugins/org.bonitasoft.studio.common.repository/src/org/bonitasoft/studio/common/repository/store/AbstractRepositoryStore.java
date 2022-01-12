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
package org.bonitasoft.studio.common.repository.store;

import static com.google.common.base.Predicates.notNull;
import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.toArray;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Lists.newArrayList;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.bonitasoft.studio.common.extension.BonitaStudioExtensionRegistryManager;
import org.bonitasoft.studio.common.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.ImportArchiveData;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.filestore.RepositoryFileStoreComparator;
import org.bonitasoft.studio.common.repository.model.IFileStoreContribution;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.widgets.Display;

import com.google.common.base.Function;

/**
 * @author Romain Bioteau
 */
public abstract class AbstractRepositoryStore<T extends IRepositoryFileStore<?>> implements IRepositoryStore<T> {

    private static final String CLASS = "class";
    public static final IValidator<InputStream> DEFAULT_MODEL_VALIDATOR = is -> ValidationStatus.ok();
    private IFolder folder;
    protected IRepository repository;

    @Override
    public void createRepositoryStore(final IRepository repository) {
        this.repository = repository;
        final IProject project = repository.getProject();
        this.folder = project.getFolder(getName());
        if (!this.folder.exists() && this.folder.getLocation() != null) {
            try {
                if (!this.folder.getParent().exists()) {
                    this.folder.getLocation().toFile().mkdirs();
                    this.folder.getParent().refreshLocal(IResource.DEPTH_ONE, AbstractRepository.NULL_PROGRESS_MONITOR);
                } else {
                    this.folder.create(true, true, AbstractRepository.NULL_PROGRESS_MONITOR);
                }
                processDefaultContribution();
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    protected void processDefaultContribution() {
        final IConfigurationElement[] elements = BonitaStudioExtensionRegistryManager.getInstance()
                .getConfigurationElements(
                        "org.bonitasoft.studio.repository.fileContribution");
        for (final IConfigurationElement element : elements) {
            IFileStoreContribution contribution;
            try {
                contribution = (IFileStoreContribution) element.createExecutableExtension(CLASS);
                if (contribution.appliesTo(this)) {
                    contribution.execute(this);
                }
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    @Override
    public final T importInputStream(final String fileName, final InputStream inputStream) {
        Assert.isNotNull(inputStream);
        Assert.isNotNull(fileName);
        InputStream newIs = null;
        try {
            if (inputStream.available() == 0) { // empty file case
                return createEmptyFile(fileName, inputStream);
            }
            newIs = handlePreImport(fileName, inputStream);
            if (newIs == null) {
                return null;
            }
            boolean exists = false;
            try {
                exists = getResource().members().length > 0;
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
            final T store = doImportInputStream(fileName, newIs);
            if (!exists) {
                AbstractFileStore.refreshExplorerView();
            }
            return store;
        } catch (final MigrationException e) {
            BonitaStudioLog.error(e);
            if (!FileActionDialog.getDisablePopup()) {
                Display.getDefault()
                        .syncExec(() -> new BonitaErrorDialog(Display.getDefault().getActiveShell(),
                                Messages.migrationFailedTitle,
                                Messages.migrationFailedMessage, e).open());
            }
            return null;
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
            if (!FileActionDialog.getDisablePopup()) {
                Display.getDefault()
                        .syncExec(() -> new BonitaErrorDialog(Display.getDefault().getActiveShell(),
                                Messages.importedFileIsInvalidTitle,
                                Messages.bind(
                                        Messages.importedFileIsInvalid, fileName),
                                e).open());
            }
            return null;
        } 
    }

    private T createEmptyFile(final String fileName, final InputStream inputStream) {
        try {
            final IFile iFile = getResource().getFile(fileName);
            final File file = iFile.getLocation().toFile();
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
                getResource().refreshLocal(IResource.DEPTH_INFINITE, AbstractRepository.NULL_PROGRESS_MONITOR);
            }
            if (!iFile.exists()) {
                iFile.create(inputStream, true, AbstractRepository.NULL_PROGRESS_MONITOR);
            }
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    @Override
    public T importArchiveData(String folderName, List<ImportArchiveData> importArchiveData, IProgressMonitor monitor)
            throws CoreException {
        importArchiveData.stream().forEach(data -> {
            setUserChoice(data);
            try {
                importArchiveData(data, monitor);
            } catch (final CoreException e) {
                throw new RuntimeException(e);
            }
        });
        return createRepositoryFileStore(folderName);
    }

    @Override
    public final T importArchiveData(ImportArchiveData importArchiveData, IProgressMonitor monitor)
            throws CoreException {
        setUserChoice(importArchiveData);
        return doImportArchiveData(importArchiveData, monitor);
    }

    private void setUserChoice(ImportArchiveData data) {
        if (data.shouldOverwrite()) {
            FileActionDialog.setYesToAll();
        } else {
            FileActionDialog.setNoToAll();
        }
    }

    protected T doImportArchiveData(ImportArchiveData importArchiveData, IProgressMonitor monitor)
            throws CoreException {
        try {
            return importInputStream(importArchiveData.getName().split("/", 3)[2], importArchiveData.getInputStream());
        } catch (final IOException e) {
            throw new CoreException(new Status(IStatus.ERROR, CommonRepositoryPlugin.PLUGIN_ID, e.getMessage(), e));
        }
    }

    /**
     * Handler use to call migration action if needed
     *
     * @param fileName
     * @param resource
     * @return A the IResource with a migrated content
     */
    protected IResource handlePreImport(final String fileName, final IResource resource) {
        return resource;
    }

    /**
     * Handler use to call migration action if needed
     *
     * @param fileName
     * @param inputStream
     * @return A new InputStream with a migrated content
     */
    protected InputStream handlePreImport(final String fileName, final InputStream inputStream)
            throws MigrationException, IOException {
        return inputStream;
    }

    /**
     * @param fileName
     * @param inputStream , read and closed inside this method
     * @return IRepositoryFileStore
     */
    protected T doImportInputStream(final String fileName, final InputStream inputStream) {
        IFolder folder = getResource();
        final IFile file = folder.getFile(fileName);
        try {
            if (file.exists()) {
                if (FileActionDialog.overwriteQuestion(fileName)) {
                    handleOverwrite(file);
                } else {
                    inputStream.close();
                    return createRepositoryFileStore(fileName);
                }
            }
            final File f = file.getLocation().toFile();
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
                folder.refreshLocal(IResource.DEPTH_INFINITE, AbstractRepository.NULL_PROGRESS_MONITOR);
            }
            file.create(inputStream, true, AbstractRepository.NULL_PROGRESS_MONITOR);
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return createRepositoryFileStore(fileName);
    }

    protected void handleOverwrite(final IFile file) throws CoreException {
        file.delete(true, false, AbstractRepository.NULL_PROGRESS_MONITOR);
    }

    @Override
    public IFolder getResource() {
        return folder;
    }

    @Override
    public List<T> getChildren() {
        try {
            final List<T> result = newArrayList(filter(transform(listChildren(), toFileStore()), notNull()));
            Collections.sort(result, new RepositoryFileStoreComparator());
            return result;
        } catch (final CoreException e1) {
            BonitaStudioLog.error("Failed to retrieve store children", e1);
            return newArrayList();
        }
    }

    protected Function<IResource, T> toFileStore() {
        return resource -> createRepositoryFileStore(resource.getName());
    }

    @Override
    public T getChild(final String fileName, boolean force) {
        Assert.isNotNull(fileName);
        final IFile file = getResource().getFile(fileName);
        if (force && !file.isSynchronized(IResource.DEPTH_ONE) && file.isAccessible()) {
            try {
                file.refreshLocal(IResource.DEPTH_ONE, AbstractRepository.NULL_PROGRESS_MONITOR);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        if (file.exists()) {
            return createRepositoryFileStore(fileName);
        }

        return null;
    }

    @Override
    public void refresh() {
        if (folder != null && !folder.isSynchronized(IResource.DEPTH_INFINITE)) {
            try {
                folder.refreshLocal(IResource.DEPTH_INFINITE, AbstractRepository.NULL_PROGRESS_MONITOR);
            } catch (final CoreException e1) {
                BonitaStudioLog.warning(String.format("An error occured wihle refreshing folder %s: %s",
                        folder.getName(), e1.getMessage()), CommonRepositoryPlugin.PLUGIN_ID);
            }
        }
    }

    @Override
    public boolean isShared() {
        return canBeShared() && repository.isShared();
    }

    @Override
    public boolean canBeShared() {
        return true;
    }

    @Override
    public boolean canBeExported() {
        return true;
    }

    @Override
    public MigrationReport migrate(IProgressMonitor monitor) throws CoreException, MigrationException {
        List<T> filesToMigrate = getChildren().stream()
                .filter(fs -> !fs.isReadOnly())
                .filter(IRepositoryFileStore::canBeShared)
                .collect(Collectors.toList());

        for (T fStore : filesToMigrate) {
            migrate(fStore, monitor);
        }
        
        return MigrationReport.emptyReport();
    }

    @Override
    public void migrate(IRepositoryFileStore<?> fileStore, final IProgressMonitor monitor)
            throws CoreException, MigrationException {
        IResource resource = fileStore.getResource();
        if (resource instanceof IFile
                && resource.exists()
                && validate(resource.getName(), ((IFile) resource).getContents()).getSeverity() != IStatus.ERROR) {
            try (final InputStream is = ((IFile) resource).getContents()) {
                monitor.subTask(fileStore.getName());
                InputStream newIs = handlePreImport(resource.getName(), is);
                if (!is.equals(newIs)) {
                    ((IFile) resource).setContents(newIs, IResource.FORCE, monitor);
                    resource.refreshLocal(IResource.DEPTH_ONE, monitor);
                }
            } catch (final IOException e) {
                throw new MigrationException("Cannot migrate resource " + resource.getName() + " (not a valid file)",
                        e);
            }
        }
    }

    @Override
    public void close() {
        //INTENDED TO BE OVERRIDE
    }

    @Override
    public boolean isEmpty() {
        try {
            return !listChildren().stream().findFirst().isPresent();
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
            return true;
        }
    }

    protected List<IResource> listChildren() throws CoreException {
        refresh();
        final IFolder folder = getResource();
        final FileStoreCollector collector = new FileStoreCollector(folder,
                toArray(getCompatibleExtensions(), String.class));
        if (folder.exists()) {
            folder.accept(collector);
        }
        return collector.toList();
    }

    @Override
    public Set<String> getCompatibleExtensions() {
        return Collections.emptySet();
    }

    @Override
    public void repositoryUpdated() {
        //NOTHING TO UPDATE
    }

    @Override
    public StyledString getStyledString() {
        return new StyledString(getDisplayName());
    }

    protected IRepository getRepository() {
        return repository;
    }
}
