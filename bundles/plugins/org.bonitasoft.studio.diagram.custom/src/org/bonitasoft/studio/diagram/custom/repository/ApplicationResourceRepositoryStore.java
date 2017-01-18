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
package org.bonitasoft.studio.diagram.custom.repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.jface.FileActionDialog;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.store.AbstractRepositoryStore;
import org.bonitasoft.studio.diagram.custom.Activator;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.edapt.migration.MigrationException;
import org.eclipse.swt.graphics.Image;

/**
 * @author Romain Bioteau
 */
public class ApplicationResourceRepositoryStore extends AbstractRepositoryStore<ApplicationResourceFileStore> {

    private static final String STORE_NAME = "application_resources";

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#createRepositoryFileStore(java.lang.String)
     */
    @Override
    public ApplicationResourceFileStore createRepositoryFileStore(final String processUUID) {
        if (processUUID.contains(".")) {
            return null;
        }
        final IFile f = getResource().getFile(processUUID);
        if (f.exists() && f.getLocation().toFile().isFile()) {
            return null;
        }
        return new ApplicationResourceFileStore(processUUID, this);
    }

    @Override
    public boolean canBeExported() {
        return false;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getName()
     */
    @Override
    public String getName() {
        return STORE_NAME;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getDisplayName()
     */
    @Override
    public String getDisplayName() {
        return Messages.applicationResources;
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.common.repository.model.IRepositoryStore#getIcon()
     */
    @Override
    public Image getIcon() {
        return Pics.getImage("resources.gif", Activator.getDefault());
    }

    @Override
    public ApplicationResourceFileStore getChild(final String processUUID) {
        if (processUUID != null) {
            final IFolder folder = getResource().getFolder(processUUID);
            if (!folder.isSynchronized(IResource.DEPTH_INFINITE) && folder.isAccessible()) {
                try {
                    folder.refreshLocal(IResource.DEPTH_INFINITE, Repository.NULL_PROGRESS_MONITOR);
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e);
                }
            }
            if (folder.exists()) {
                return createRepositoryFileStore(processUUID);
            }
        }
        return null;
    }

    @Override
    public List<ApplicationResourceFileStore> getChildren() {
        refresh();

        final List<ApplicationResourceFileStore> result = new ArrayList<ApplicationResourceFileStore>();
        final IFolder folder = getResource();
        try {
            for (final IResource r : folder.members()) {
                if (!r.isHidden() && !r.getName().startsWith(".")) { //Hoping that .DS_STORE & .svn are hidden resources
                    result.add(createRepositoryFileStore(r.getName()));
                }
            }
        } catch (final CoreException e) {
            BonitaStudioLog.error(e);
        }
        return result;
    }

    public File getFileInProject(final String path) {
        return getResource().getLocation().append(path).toFile();
    }

    public IFile getIFileInProject(final String path) {
        final Path ipath = new Path(path);
        return getResource().getFile(ipath);
    }

    @Override
    protected ApplicationResourceFileStore doImportIResource(final String fileName, final IResource resource) {
        try {
            if (resource instanceof IFile) {
                return doImportInputStream(fileName, ((IFile) resource).getContents());
            } else if (resource instanceof IFolder) {
                final IPath path = getResource().getFullPath().append(fileName);
                final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
                final IFolder targetFolder = root.getFolder(path);
                if (targetFolder.exists()) {
                    String fileNameLabel = fileName;
                    final String processUUID = fileName;
                    final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance()
                            .getRepositoryStore(DiagramRepositoryStore.class);
                    final AbstractProcess process = diagramStore.getProcessByUUID(processUUID);
                    if (process != null) {
                        fileNameLabel = Messages.bind(Messages.applicationResourcesFor,
                                process.getName() + " (" + process.getVersion() + ")");
                    }
                    if (FileActionDialog.overwriteQuestion(fileNameLabel)) {
                        targetFolder.delete(true, Repository.NULL_PROGRESS_MONITOR);
                    } else {
                        return createRepositoryFileStore(fileName);
                    }
                }
                resource.copy(getResource().getFullPath().append(fileName), true, Repository.NULL_PROGRESS_MONITOR);
            }
        } catch (final Exception e) {
            BonitaStudioLog.error(e);
        }
        return createRepositoryFileStore(fileName);
    }

    @Override
    public void migrate(final IProgressMonitor monitor) throws CoreException, MigrationException {
        //NOTHING TO MIGRATE
    }

}
