/**
 * Copyright (C) 2012-2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.repository.filestore;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.jdt.CreateJarOperation;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Romain Bioteau
 */
public class PackageFileStore extends AbstractFileStore {

    private final String packageName;

    public PackageFileStore(final String packageName, final IRepositoryStore<?> parentStore) {
        super("", parentStore);
        this.packageName = packageName;
    }

    @Override
    public String getName() {
        return packageName;
    }

    @Override
    public String getDisplayName() {
        return getName();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return Pics.getImage("package.gif", CommonRepositoryPlugin.getDefault());
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getContent()
     */
    @Override
    public IFolder getContent() {
        return getResource();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryFileStore#getResource()
     */
    @Override
    public IFolder getResource() {
        final IPackageFragment packageFragment = getPackageFragment();
        if (packageFragment != null) {
            return (IFolder) packageFragment.getResource();
        }

        return null;
    }

    public void exportAsJar(final String absoluteTargetFilePath, final boolean includeSources) throws InvocationTargetException, InterruptedException {
        try {
            checkWritePermission(new File(absoluteTargetFilePath));
        } catch (final IOException e) {
            throw new InvocationTargetException(e);
        }
        final IPackageFragment packageFragment = getPackageFragment();
        try {
            new CreateJarOperation(new File(absoluteTargetFilePath), packageFragment.getCompilationUnits()).run(Repository.NULL_PROGRESS_MONITOR);
        } catch (final JavaModelException e) {
            throw new InvocationTargetException(e, "Failed to retrieve compilation units from package frgament");
        }
    }

    public IPackageFragment getPackageFragment() {
        final IJavaProject project = RepositoryManager.getInstance().getCurrentRepository().getJavaProject();
        try {
            return project.findPackageFragment(getParentStore().getResource().getFullPath().append(packageName.replace(".", "/")));
        } catch (final JavaModelException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    public List<IFile> getChildren() {
        final List<IFile> result = new ArrayList<IFile>();
        retrieveChildren(getResource(), result);
        return result;
    }

    private void retrieveChildren(final IResource resource, final List<IFile> result) {
        try {
            if (resource instanceof IFolder) {
                for (final IResource r : ((IFolder) resource).members()) {
                    retrieveChildren(r, result);
                }
            } else if (resource instanceof IFile) {
                result.add((IFile) resource);
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doSave(java.lang.Object)
     */
    @Override
    protected void doSave(final Object content) {

    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doOpen()
     */
    @Override
    protected IWorkbenchPart doOpen() {
        return null;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.filestore.AbstractFileStore#doClose()
     */
    @Override
    protected void doClose() {

    }

    @Override
    protected void doDelete() {
        try {
            deleteRecursivelyEmptyPackages(getRepository().getJavaProject(), getPackageFragment());
        } catch (final JavaModelException e) {
            BonitaStudioLog.error(e);
            super.doDelete();
        }
    }

    private void deleteRecursivelyEmptyPackages(final IJavaProject project, IPackageFragment packageFragment) throws JavaModelException {
        if (packageFragment != null) {
            packageFragment.delete(true, new NullProgressMonitor());//delete the first one, we have only one package per Type
            packageFragment = retrieveParentPackageFragment(project, packageFragment);
            while (!packageFragment.hasChildren()) {
                //I don't find another way than passing through IResource, directly using IJavaElement seems not possible.
                final IPackageFragment parent = retrieveParentPackageFragment(project, packageFragment);
                packageFragment.delete(true, new NullProgressMonitor());
                if (parent instanceof IPackageFragment && !parent.isDefaultPackage()) {
                    packageFragment = parent;
                } else {
                    return;
                }
            }
        }
    }

    private IPackageFragment retrieveParentPackageFragment(final IJavaProject project,
            final IPackageFragment packageFragment) throws JavaModelException {
        final IPath pathOfParentPackageFragment = packageFragment.getResource().getParent().getFullPath();
        final IPackageFragment parent = project.findPackageFragment(pathOfParentPackageFragment);
        return parent;
    }

}
