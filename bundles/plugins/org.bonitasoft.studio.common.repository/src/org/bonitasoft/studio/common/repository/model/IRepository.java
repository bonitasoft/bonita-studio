/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.repository.model;

import java.io.File;
import java.net.URLClassLoader;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 *
 */
public interface IRepository extends IFileStoreChangeListener {

    void createRepository(String repositoryName) ;

    String getName() ;

    boolean isShared() ;

    IProject getProject() ;

    void create() ;

    void delete() ;

    void open() ;

    void close() ;

    IRepositoryStore<? extends IRepositoryFileStore> getRepositoryStore(Class<?> repositoryStoreClass) ;

    List<IRepositoryStore<? extends IRepositoryFileStore>> getAllStores() ;

    void refresh(IProgressMonitor monitor) ;

    String getVersion() ;

    List<IRepositoryStore<? extends IRepositoryFileStore>> getAllSharedStores();

    List<IRepositoryStore<? extends IRepositoryFileStore>> getAllExportableStores();

    String getDispslayName();

    Image getIcon() ;

    void importFromArchive(File archiveFile, boolean askOverwrite);

    void exportToArchive(String file);

    IRepositoryFileStore getFileStore(IResource resource);

    IRepositoryStore<? extends IRepositoryFileStore> getRepositoryStore(IResource resource);

    boolean isBuildEnable() ;

    IJavaProject getJavaProject();

    List<IRepositoryStore<? extends IRepositoryFileStore>> getAllShareableStores();

    URLClassLoader createProjectClassloader() ;

    IRepositoryFileStore asRepositoryFileStore(IFile res);

	void migrate() throws CoreException, MigrationException;
}
