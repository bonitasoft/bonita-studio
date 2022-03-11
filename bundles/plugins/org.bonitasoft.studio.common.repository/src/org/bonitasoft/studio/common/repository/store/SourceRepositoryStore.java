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

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.filestore.PackageFileStore;
import org.bonitasoft.studio.common.repository.filestore.RepositoryFileStoreComparator;
import org.bonitasoft.studio.common.repository.filestore.SourceFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.internal.core.SourceType;

/**
 * @author Romain Bioteau
 */
public abstract class SourceRepositoryStore<T extends AbstractFileStore<?>> extends AbstractRepositoryStore<T> {

    public static String SIGNATURE_FILE_NAME = "Generated_With_BOS";

    @Override
    protected T doImportInputStream(final String fileName, final InputStream inputStream) {
        if (fileName.indexOf("/") == -1) {
            final T fileStore = super.doImportInputStream(fileName, inputStream);
            if (fileStore instanceof SourceFileStore) {
                fileStore.save(null);// notify svn event
            }
            return fileStore;
        }
        final String packageName = fileName.substring(0, fileName.lastIndexOf("/"));
        final String className = fileName.substring(packageName.length() + 1, fileName.length());
        PackageFileStore packageStore = (PackageFileStore) getChild(packageName, true);
        IFolder packageFolder = null;
        if (packageStore == null) {
            final IFolder folder = getResource();
            packageFolder = folder.getFolder(packageName);
            if (!packageFolder.exists()) {
                try {
                    packageFolder.getLocation().toFile().mkdirs();
                    packageFolder.refreshLocal(IResource.DEPTH_ONE, AbstractRepository.NULL_PROGRESS_MONITOR);
                    packageStore = (PackageFileStore) getChild(packageName, true);
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }
        if (packageFolder == null && packageStore != null) {
            packageFolder = packageStore.getResource();
        }

        final IFile file = packageFolder.getFile(className);
        if (file.exists()) {
            if (FileActionDialog.overwriteQuestion(fileName)) {
                try {
                    file.delete(true, AbstractRepository.NULL_PROGRESS_MONITOR);
                    file.create(inputStream, true, AbstractRepository.NULL_PROGRESS_MONITOR);
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
        } else {
            try {
                file.create(inputStream, true, AbstractRepository.NULL_PROGRESS_MONITOR);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }

        return createRepositoryFileStore(packageName);
    }

    @Override
    public List<T> getChildren() {
        refresh();
        final Set<String> compatibleExtensions = getCompatibleExtensions();
        if (compatibleExtensions != null && !compatibleExtensions.isEmpty()) {
            return super.getChildren();
        }
        final List<T> result = new ArrayList<>();
        final IFolder folder = getResource();
        try {
            for (final IResource r : folder.members()) {
                addChildren(r, result);
            }

        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
        Collections.sort(result, new RepositoryFileStoreComparator());
        return result;
    }

    private void addChildren(final IResource r, final List<T> result) throws CoreException {
        final AbstractRepository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
        if (r instanceof IFolder && !r.isHidden() && !r.getName().startsWith(".")) {
            if (containsSourceFile((IFolder) r)) {
                final IPackageFragment pk = currentRepository.getJavaProject().findPackageFragment(r.getFullPath());
                if (pk != null) {
                    result.add(createRepositoryFileStore(pk.getElementName()));
                }
            }
            for (final IResource child : ((IFolder) r).members()) {
                addChildren(child, result);
            }
        }
    }

    private boolean containsSourceFile(final IFolder folder) throws CoreException {
        for (final IResource res : folder.members()) {
            if (res.getFileExtension() != null
                    && (res.getFileExtension().equals("java") || res.getFileExtension().equals("groovy"))) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getChild(final String fileName, boolean force) {
        if (fileName == null) {
            return null;
        }
        if (fileName.endsWith(".java") || fileName.endsWith(".groovy")) {
            final T fileStore = super.getChild(fileName, force);
            if (fileStore != null) {
                return fileStore;
            }
        }
        try {
            final IJavaProject javaProject = RepositoryManager.getInstance().getCurrentRepository().getJavaProject();
            if (javaProject != null) {
                final IType javaType = javaProject.findType(fileName);
                if (javaType != null) {
                    if (javaType instanceof SourceType || isSourceType(fileName, javaProject)) {
                        return (T) new SourceFileStore(fileName, this);
                    }
                    return null;
                } else { // package name
                    final IPackageFragment packageFragment = javaProject
                            .findPackageFragment(getResource().getFullPath().append(fileName.replace(".", "/")));
                    if (packageFragment != null) {
                        return (T) new PackageFileStore(fileName, this);
                    }
                }
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    private boolean isSourceType(final String qualifiedClassname, final IJavaProject javaProject)
            throws JavaModelException {
        final SearchEngine sEngine = new SearchEngine();
        final IJavaSearchScope searchScope = SearchEngine.createJavaSearchScope(new IJavaElement[] { javaProject },
                IJavaSearchScope.SOURCES);
        final TypeNameFoundRequestor nameRequestor = new TypeNameFoundRequestor();
        sEngine.searchAllTypeNames(NamingUtils.getPackageName(qualifiedClassname).toCharArray(),
                SearchPattern.R_EXACT_MATCH,
                NamingUtils.getSimpleName(qualifiedClassname)
                        .toCharArray(),
                SearchPattern.R_EXACT_MATCH, IJavaSearchConstants.CLASS_AND_INTERFACE, searchScope, nameRequestor,
                IJavaSearchConstants.WAIT_UNTIL_READY_TO_SEARCH,
                AbstractRepository.NULL_PROGRESS_MONITOR);
        return nameRequestor.isFound();
    }

    @Override
    public MigrationReport migrate(IProgressMonitor monitor) throws CoreException, MigrationException {
        // NOTHING TO MIGRATE
        return MigrationReport.emptyReport();
    }
}
