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
package org.bonitasoft.studio.common.repository.operation;

import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.bonitasoft.studio.common.ui.jface.FileActionDialog;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.internal.wizards.datatransfer.ArchiveFileExportOperation;

/**
 * @author Romain Bioteau
 */
public class ExportBosArchiveOperation {

    public static final String VERSION = "version";
    private static final String BOS_MANIFEST_COMMENT = "Generated with BOS";
    public static final String BOS_ARCHIVE_MANIFEST = "MANIFEST";
    public static final String TO_OPEN = "toOpen";
    public static final String NONE = "<NONE>";

    private Set<IResource> resources = new HashSet<>();
    private IStatus status;
    private String destPath;
    private IFile manifestFile;
    private Set<IResource> resourcesToReOpen;
    private BonitaProject project;

    public IStatus run(final IProgressMonitor monitor) {
        status = Status.OK_STATUS;

        Assert.isNotNull(destPath);

        final File destFile = new File(destPath);
        if (destFile.exists()) {
            if (FileActionDialog.overwriteQuestion(destFile.getName())) {
                PlatformUtil.delete(destFile, monitor);
            } else {
                status = Status.CANCEL_STATUS;
                return status;
            }
        }

        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }

        status = addManifest();
        if (!status.isOK()) {
            return status;
        }

        try {
            final ArchiveFileExportOperation op = new ArchiveFileExportOperation(null, makeRelative(resources),
                    destPath);
            op.setCreateLeadupStructure(true);
            op.setUseCompression(true);
            op.setUseTarFormat(false);
            op.run(monitor);
            if (manifestFile != null && manifestFile.exists()) {
                manifestFile.delete(true, AbstractRepository.NULL_PROGRESS_MONITOR);
            }
            status = op.getStatus();
            return status;
        } catch (final CoreException | InterruptedException | InvocationTargetException e) {
            status = new Status(IStatus.ERROR, CommonRepositoryPlugin.PLUGIN_ID, e.getMessage(), e);
            return status;
        }
    }

    protected IStatus addManifest() {
        if (project == null) {
            return Status.error("No active project.");
        }
        var root = project.getParentProject();
        manifestFile = root.getFile(BOS_ARCHIVE_MANIFEST);
        if (manifestFile.exists()) {
            try {
                manifestFile.delete(true, AbstractRepository.NULL_PROGRESS_MONITOR);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
                return new Status(IStatus.ERROR, CommonRepositoryPlugin.PLUGIN_ID, e.getMessage(), e);
            }
        }
        final Properties prop = new Properties();
        prop.put(VERSION, ProductVersion.CURRENT_VERSION);

        if (resourcesToReOpen != null && !resourcesToReOpen.isEmpty()) {
            final StringBuilder sb = new StringBuilder();
            for (final IResource r : resourcesToReOpen) {
                sb.append(r.getName());
                sb.append(",");
            }
            if (sb.length() > 0) {
                sb.delete(sb.length() - 1, sb.length());
            }
            prop.put(TO_OPEN, sb.toString());
        } else {
            prop.put(TO_OPEN, NONE);
        }

        final Writer w = new CharArrayWriter();
        try {
            prop.store(w, BOS_MANIFEST_COMMENT);
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
            return new Status(IStatus.ERROR, CommonRepositoryPlugin.PLUGIN_ID, e.getMessage(), e);
        }

        final InputStream source = new ByteArrayInputStream(w.toString().getBytes());
        try {
            manifestFile.create(source, IResource.FORCE, AbstractRepository.NULL_PROGRESS_MONITOR);
        } catch (final CoreException e1) {
            return new Status(IStatus.ERROR, CommonRepositoryPlugin.PLUGIN_ID, e1.getMessage(), e1);
        }
        resources.add(manifestFile);
        return Status.OK_STATUS;
    }

    private IResource makeRelative(IResource resource) throws CoreException {
        IProject parentProject = project.getParentProject();
        return parentProject.findMember(resource.getLocation().makeRelativeTo(parentProject.getLocation()));
    }

    private List<IResource> makeRelative(Set<IResource> resources) throws CoreException {
        var result = new ArrayList<IResource>();
        project.getParentProject().refreshLocal(IResource.DEPTH_INFINITE,
                new NullProgressMonitor());
        for (IResource r : resources) {
            result.add(makeRelative(r));
        }
        return result;
    }

    public IStatus getStatus() {
        return status;
    }

    public void setResourcesToOpen(final Set<IResource> resourcesToReopenAtImport) {
        resourcesToReOpen = resourcesToReopenAtImport;
    }

    public void setDestinationPath(final String destPath) {
        this.destPath = destPath;
    }

    protected Set<IResource> computeResourcesToExport(Set<IRepositoryFileStore> fileStores) {
        final Set<IResource> resourcesToExport = new HashSet<>();
        for (final IRepositoryFileStore file : fileStores) {
            final IResource resource = file.getResource();
            if (resource != null && resource.exists()) {
                try {
                    addChildrenFile(resource, resourcesToExport);
                } catch (final CoreException e) {
                    BonitaStudioLog.error(e);
                }
                resourcesToExport.addAll(file.getRelatedResources());
            }
        }
        return resourcesToExport;
    }

    private void addChildrenFile(IResource resource, Set<IResource> resourcesToExport) throws CoreException {
        if (resource instanceof IFile) {
            resourcesToExport.add(resource);
        } else if (resource instanceof IFolder && !isMetadataFolder(resource) && !isBuildOutputFolder(resource)) {
            resource.refreshLocal(IResource.DEPTH_ONE, AbstractRepository.NULL_PROGRESS_MONITOR);
            for (final IResource r : ((IFolder) resource).members()) {
                addChildrenFile(r, resourcesToExport);
            }
        }
    }

    private boolean isBuildOutputFolder(IResource resource) {
        return Pattern.matches("target", resource.getName())
                || Pattern.matches("bin", resource.getName())
                || Pattern.matches("node", resource.getName())
                || Pattern.matches("node_modules", resource.getName());
    }

    private boolean isMetadataFolder(IResource resource) {
        return Pattern.matches(".metadata", resource.getName());
    }

    public void setFileStores(Set<IRepositoryFileStore> fileStores) {
        resources.addAll(computeResourcesToExport(fileStores));
    }

    public void setBonitaProject(BonitaProject project) {
        this.project = project;
    }

    public Set<IResource> getResources() {
        return resources;
    }

    public void addResources(List<IResource> selectedResoures) {
        resources.addAll(selectedResoures);
    }
}
