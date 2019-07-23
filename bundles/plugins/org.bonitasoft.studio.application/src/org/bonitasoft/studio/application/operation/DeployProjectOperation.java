/**
 * Copyright (C) 2019 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.operation;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.common.ZipUtil;
import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IBuildable;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;

public class DeployProjectOperation implements IRunnableWithStatus {

    private IStatus status;
    private RepositoryAccessor repositoryAccessor;

    public DeployProjectOperation(RepositoryAccessor repositoryAccessor) {
        this.repositoryAccessor = repositoryAccessor;
        status = ValidationStatus.ok();
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        IPath buildPath;
        try {
            buildPath = createBuildDestination(monitor);
            IPath archiveFilePath = buildProject(buildPath, monitor);
            if (status.isOK()) {
                deployProject(archiveFilePath);
            }
        } catch (CoreException e) {
            status = ValidationStatus.error("An error occured while deploying project", e);
        }
    }

    private void deployProject(IPath archiveFilePath) {
        BonitaStudioLog.info("Deploy not implemented yet ... ZzzZ ...", ApplicationPlugin.PLUGIN_ID);
    }

    private IPath createBuildDestination(IProgressMonitor monitor) throws CoreException {
        monitor.beginTask("Creating target folder...", IProgressMonitor.UNKNOWN);
        IFolder targetFolder = repositoryAccessor.getCurrentRepository().getProject().getFolder("target");
        if (targetFolder.exists()) {
            targetFolder.delete(true, new NullProgressMonitor());
        }
        targetFolder.create(true, true, new NullProgressMonitor());
        targetFolder.getFolder(repositoryAccessor.getCurrentRepository().getName()).create(true, true,
                new NullProgressMonitor());
        monitor.done();
        return targetFolder.getFullPath();
    }

    private IPath buildProject(IPath buildPath, IProgressMonitor monitor) {
        List<IRepositoryFileStore> storeToBuild = retrieveFileStoresToBuild();
        monitor.beginTask("Building project...", storeToBuild.size() + 1);
        for (IRepositoryFileStore fileStore : storeToBuild) {
            try {
                ((IBuildable) fileStore).build(buildPath.append(repositoryAccessor.getCurrentRepository().getName()),
                        monitor);
                monitor.worked(1);
            } catch (CoreException e) {
                String buildErrorMessage = String.format(Messages.buildError, fileStore.getName());
                status = ValidationStatus.error(String.format("%s\n\n%s", buildErrorMessage, Messages.buildErrorHelp), e);
                return buildPath;
            }
        }
        String archiveFileName = String.format("%s_%s.zip", repositoryAccessor.getCurrentRepository().getName(),
                System.currentTimeMillis());
        IPath archiveFilePath = buildPath.append(archiveFileName);
        if (status.isOK()) {
            monitor.subTask(String.format(Messages.creatingArchive, archiveFileName));
            createZip(buildPath, archiveFileName, archiveFilePath);
            monitor.done();
        }
        return archiveFilePath;
    }

    private void createZip(IPath buildPath, String archiveFileName, IPath archiveFilePath) {
        IProject project = repositoryAccessor.getCurrentRepository().getProject();
        IFolder sourceFolder = project.getFolder(
                buildPath.append(repositoryAccessor.getCurrentRepository().getName()).makeRelativeTo(project.getFullPath()));
        IFile zipFile = project.getFile(archiveFilePath.makeRelativeTo(project.getFullPath()));
        try {
            ZipUtil.zip(sourceFolder.getRawLocation().toFile().toPath(), zipFile.getRawLocation().toFile().toPath());
            project.getFolder(buildPath.makeRelativeTo(project.getFullPath())).refreshLocal(IResource.DEPTH_INFINITE,
                    new NullProgressMonitor());
        } catch (IOException | CoreException e) {
            status = ValidationStatus.error(String.format("An error occured while creating archive %s.", archiveFileName),
                    e);
        }
    }

    protected List<IRepositoryFileStore> retrieveFileStoresToBuild() {
        return repositoryAccessor.getCurrentRepository().getAllStores()
                .stream()
                .map(IRepositoryStore::getChildren)
                .flatMap(Collection::stream)
                .filter(IBuildable.class::isInstance)
                .collect(Collectors.toList());
    }

    @Override
    public IStatus getStatus() {
        return status;
    }

}
