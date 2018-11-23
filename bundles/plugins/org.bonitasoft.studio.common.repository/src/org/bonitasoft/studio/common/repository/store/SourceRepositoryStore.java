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

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.AbstractFileStore;
import org.bonitasoft.studio.common.repository.filestore.PackageFileStore;
import org.bonitasoft.studio.common.repository.filestore.RepositoryFileStoreComparator;
import org.bonitasoft.studio.common.repository.filestore.SourceFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
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
public abstract class SourceRepositoryStore<T extends AbstractFileStore> extends AbstractRepositoryStore<T> {

    public static String SIGNATURE_FILE_NAME = "Generated_With_BOS";

    /**
     * Handles the import of packages folder
     */
    @Override
    protected T doImportIResource(final String fileName, final IResource resource) {
        try {
            if (resource instanceof IFile) {
                return doImportInputStream(fileName, ((IFile) resource).getContents());
            } else if (resource instanceof IFolder) {
                final List<IFile> sourceFiles = new ArrayList<>();
                findParentPackage((IFolder) resource, sourceFiles);
                for (final IFile sourceFile : sourceFiles) {
                    final IPath path = sourceFile.getProjectRelativePath();
                    final IFile targetFile = RepositoryManager.getInstance().getCurrentRepository().getProject()
                            .getFile(path.removeFirstSegments(1));
                    boolean skip = false;
                    if (targetFile.exists()) {
                        if (FileActionDialog.overwriteQuestion(targetFile.getName())) {
                            targetFile.delete(true, Repository.NULL_PROGRESS_MONITOR);
                        } else {
                            skip = true;
                        }
                    }
                    if (!skip) {
                        targetFile.getLocation().toFile().getParentFile().mkdirs();
                        refresh();

                        try {
                            targetFile.create(new FileInputStream(sourceFile.getLocation().toFile()), true,
                                    Repository.NULL_PROGRESS_MONITOR);
                            incrementaBuild();
                        } catch (final Exception e) {
                            BonitaStudioLog.error(e);
                        }

                    }
                }
                return null;
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return createRepositoryFileStore(fileName);
    }

    private void incrementaBuild() throws CoreException {
        if (RepositoryManager.getInstance().getCurrentRepository().isBuildEnable()) {
            final IProject project = RepositoryManager.getInstance().getCurrentRepository().getProject();
            project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, Repository.NULL_PROGRESS_MONITOR);
        }
    }

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
        PackageFileStore packageStore = (PackageFileStore) getChild(packageName);
        if (packageStore == null) {
            final IFolder folder = getResource();
            final IFolder packageFolder = folder.getFolder(packageName);
            if (!packageFolder.exists()) {
                try {
                    packageFolder.getLocation().toFile().mkdirs();
                    packageFolder.refreshLocal(IResource.DEPTH_ONE, Repository.NULL_PROGRESS_MONITOR);
                    packageStore = (PackageFileStore) getChild(packageName);
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
        }

        final IFolder packageFolder = packageStore.getResource();
        final IFile file = packageFolder.getFile(className);
        if (file.exists()) {
            if (FileActionDialog.overwriteQuestion(fileName)) {
                try {
                    file.delete(true, Repository.NULL_PROGRESS_MONITOR);
                    file.create(inputStream, true, Repository.NULL_PROGRESS_MONITOR);
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
        } else {
            try {
                file.create(inputStream, true, Repository.NULL_PROGRESS_MONITOR);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
            }
        }

        return createRepositoryFileStore(packageName);
    }

    private void findParentPackage(final IFolder folder, final List<IFile> sourceFiles) {
        try {
            for (final IResource r : folder.members()) {
                if (r instanceof IFile) {
                    if (!sourceFiles.contains(r)) {
                        sourceFiles.add((IFile) r);
                    }
                } else if (r instanceof IFolder) {
                    findParentPackage((IFolder) r, sourceFiles);
                }
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
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
        final Repository currentRepository = RepositoryManager.getInstance().getCurrentRepository();
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
    public T getChild(final String fileName) {
        if (fileName == null) {
            return null;
        }
        if (fileName.endsWith(".java") || fileName.endsWith(".groovy")) {
            final T fileStore = super.getChild(fileName);
            if (fileStore != null) {
                return fileStore;
            }
        }
        try {
            final IJavaProject javaProject = RepositoryManager.getInstance().getCurrentRepository().getJavaProject();
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
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }

        return null;
    }

    private boolean isSourceType(final String qualifiedClassname, final IJavaProject javaProject) throws JavaModelException {
        final SearchEngine sEngine = new SearchEngine();
        final IJavaSearchScope searchScope = SearchEngine.createJavaSearchScope(new IJavaElement[] { javaProject },
                IJavaSearchScope.SOURCES);
        final TypeNameFoundRequestor nameRequestor = new TypeNameFoundRequestor();
        sEngine.searchAllTypeNames(NamingUtils.getPackageName(qualifiedClassname).toCharArray(), SearchPattern.R_EXACT_MATCH,
                NamingUtils.getSimpleName(qualifiedClassname)
                        .toCharArray(),
                SearchPattern.R_EXACT_MATCH, IJavaSearchConstants.CLASS_AND_INTERFACE, searchScope, nameRequestor,
                IJavaSearchConstants.WAIT_UNTIL_READY_TO_SEARCH,
                Repository.NULL_PROGRESS_MONITOR);
        return nameRequestor.isFound();
    }

    @Override
    public void migrate(final IProgressMonitor monitor) throws CoreException, MigrationException {
        // NOTHING TO MIGRATE
    }
}
