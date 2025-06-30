/**
 * Copyright (C) 2019 BonitaSoft S.A.
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
package org.bonitasoft.studio.maven;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.maven.project.MavenProject;
import org.bonitasoft.studio.application.views.BonitaProjectExplorer;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.core.maven.model.GAV;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.bonitasoft.studio.common.ui.jface.FileActionDialog;
import org.bonitasoft.studio.common.ui.perspectives.BonitaPerspectivesUtils;
import org.bonitasoft.studio.maven.operation.BuildCustomPageOperation;
import org.bonitasoft.studio.rest.api.extension.ui.perspective.RestAPIExtensionPerspectiveFactory;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.wizards.datatransfer.ArchiveFileExportOperation;

public abstract class CustomPageProjectFileStore<T extends CustomPageProjectDescriptor>
        extends ExtensionProjectFileStore<T> {

    public CustomPageProjectFileStore(String fileName, ExtensionRepositoryStore parentStore) {
        super(fileName, parentStore);
    }

    public String getPageDisplayName() {
        try {
            final ExtensionProjectDescriptor content = getContent();
            return content.getDisplayName();
        } catch (final ReadFileStoreException e) {
            return "";
        }
    }

    public String getContentType() {
        return ExtensionRepositoryStore.detectContentType(getResource());
    }

    protected IWorkbenchPart openEditors(final IWorkbenchPage page, final T descriptor)
            throws PartInitException {
        BonitaProjectExplorer explorerView = (BonitaProjectExplorer) getActivePage().findView(BonitaProjectExplorer.ID);
        if (explorerView != null) {
            explorerView.getCommonViewer().expandToLevel(getResource(), 1);
        }
        boolean editorOpened = false;
        for (final IFile file : descriptor.getFilesToOpen()) {
            if (file.exists()) {
                IDE.openEditor(page, file);
            }
            editorOpened = true;
        }
        if (descriptor.getPropertyFile().exists()) {
            return IDE.openEditor(page, descriptor.getPropertyFile());
        }
        if (!editorOpened) {
            BonitaPerspectivesUtils
                    .switchToPerspective(RestAPIExtensionPerspectiveFactory.REST_API_EXTENSION_PERSPECTIVE_ID);
            PlatformUtil.closeIntro();
        }
        return null;
    }

    @Override
    public IStatus export(final String targetFolderAbsoluteFilePath) throws IOException {
        checkWritePermission(new File(targetFolderAbsoluteFilePath));
        IResource file = getResource();
        if (file != null) {
            File to = new File(targetFolderAbsoluteFilePath);
            if (!to.exists()) {
                to.mkdirs();
            }
            File target = new File(to, file.getName() + ".zip");
            if (target.exists()) {
                if (FileActionDialog.overwriteQuestion(file.getName())) {
                    PlatformUtil.delete(target, AbstractRepository.NULL_PROGRESS_MONITOR);
                } else {
                    return ValidationStatus.cancel("");
                }
            }
            try {
                final ExtensionProjectDescriptor raed = getContent();
                final List<IResource> resourcesToInclude = findResourcesToExport(raed);
                final ArchiveFileExportOperation op = new ArchiveFileExportOperation(raed.getProject(),
                        resourcesToInclude, target.getAbsolutePath());
                op.run(AbstractRepository.NULL_PROGRESS_MONITOR);
                return ValidationStatus.ok();
            } catch (InvocationTargetException | InterruptedException | ReadFileStoreException e) {
                BonitaStudioLog.error("Cannot export REST API Extension project", e);
            }
        }
        return ValidationStatus.error(String
                .format(org.bonitasoft.studio.common.repository.Messages.failedToRetrieveResourceToExport, getName()));
    }

    public BuildCustomPageOperation newBuildOperation() throws ReadFileStoreException {
        return new BuildCustomPageOperation(getContent());
    }

    public String getPageId() {
        try {
            return getContent().getCustomPageName();
        } catch (final ReadFileStoreException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

    public File getArchiveFile() throws IOException {
        MavenProject mavenProject;
        try {
            mavenProject = getContent().getMavenProject().orElseThrow(
                    () -> new ReadFileStoreException(String.format("Maven project not found for %s", getName())));
        } catch (ReadFileStoreException e) {
            throw new IOException("Failed to retrieve maven project", e);
        }
        final File archive = new File(mavenProject.getBasedir(), "target" + File.separatorChar
                + mavenProject.getArtifactId() + "-" + mavenProject.getVersion() + ".zip");
        if (!archive.exists()) {
            throw new FileNotFoundException(archive.getAbsolutePath());
        }
        return archive;
    }

    @Override
    abstract protected T doGetContent() throws ReadFileStoreException;

    public GAV getGAV() {
        try {
            ExtensionProjectDescriptor descriptor = getContent();
            return new GAV(descriptor.getGroupId(), descriptor.getArtifactId(), descriptor.getVersion(),
                    descriptor.getClassifier(), "zip", null);
        } catch (ReadFileStoreException e) {
            BonitaStudioLog.error(e);
            return null;
        }
    }

}
