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
package org.bonitasoft.studio.common.repository.model;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import org.bonitasoft.studio.common.repository.IBonitaProjectListener;
import org.bonitasoft.studio.common.repository.core.DatabaseHandler;
import org.bonitasoft.studio.common.repository.core.ProjectDependenciesStore;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edapt.migration.MigrationException;

/**
 * @author Romain Bioteau
 */
public interface IRepository extends IFileStoreChangeListener, IJavaContainer {

    boolean exists();

    boolean isLoaded();

    boolean isShared();

    boolean isShared(String providerId);

    IProject getProject();

    void delete(IProgressMonitor monitor);

    IRepository open(IProgressMonitor monitor);

    void close(IProgressMonitor monitor);

    <T> T getRepositoryStore(final Class<T> repositoryStoreClass);

    Optional<IRepositoryStore<? extends IRepositoryFileStore>> getRepositoryStoreByName(String storeName);

    List<IRepositoryStore<? extends IRepositoryFileStore>> getAllStores();

    List<IRepositoryStore<? extends IRepositoryFileStore>> getAllSharedStores();

    List<IRepositoryStore<? extends IRepositoryFileStore>> getAllExportableStores();

    IStatus exportToArchive(String file);

    IRepositoryFileStore getFileStore(IResource resource);

    ProjectDependenciesStore getProjectDependenciesStore();

    IRepositoryStore<? extends IRepositoryFileStore> getRepositoryStore(IResource resource);

    List<IRepositoryStore<? extends IRepositoryFileStore>> getAllShareableStores();

    IRepositoryFileStore asRepositoryFileStore(Path path, boolean force) throws IOException, CoreException;

    void migrate(MigrationReport report, IProgressMonitor monitor) throws CoreException, MigrationException;

    IRepository create(ProjectMetadata metadata, IProgressMonitor monitor);

    boolean isOnline();

    DatabaseHandler getDatabaseHandler();

    void addProjectListener(IBonitaProjectListener listener);

    LocalDependenciesStore getLocalDependencyStore();

    void rename(String name, IProgressMonitor monitor)
            throws InvocationTargetException, InterruptedException;

    boolean closeAllEditors(boolean save);

    String getBonitaRuntimeVersion();

    List<IBonitaProjectListener> getProjectListeners();

    void disableOpenIntroListener();

    boolean isOpenIntroListenerEnabled();

    String getProjectId();

}
