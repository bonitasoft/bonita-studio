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
package org.bonitasoft.studio.validation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebFragmentRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebPageRepositoryStore;
import org.bonitasoft.studio.designer.core.repository.WebWidgetRepositoryStore;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;

public class ModelFileCompatibilityValidator implements IRunnableWithStatus {

    public static final String MODEL_VERSION_MARKER_TYPE = "org.bonitasoft.studio.validation.modelVersionMarker";
    private File projectRootFolder;
    private IRepository repository;
    private int fileCount = 0;
    private MultiStatus status = new MultiStatus(ValidationPlugin.PLUGIN_ID, -1, null, null);
    private boolean addResourceMarkers = false;
    private List<File> filesToValidate = new ArrayList<>();

    public ModelFileCompatibilityValidator(File projectRootFolder, IRepository repository) {
        this.projectRootFolder = projectRootFolder;
        this.repository = repository;
    }

    public ModelFileCompatibilityValidator(Repository currentRepository) {
        this(currentRepository.getProject().getLocation().toFile(), currentRepository);
    }

    /**
     * When this option is enabled, the validator will add a problem marker on the resources
     * when a incompatible version is detected.
     * By default, this option is disabled and the validator stops at the first incompatible resource.
     */
    public ModelFileCompatibilityValidator addResourceMarkers() {
        this.addResourceMarkers = true;
        return this;
    }

    public ModelFileCompatibilityValidator addFile(File fileToValidate) {
        this.filesToValidate.add(fileToValidate);
        return this;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        IProject project = repository.getProject();
        if (addResourceMarkers) {
            File projectRoot = project.getLocation().toFile();
            if (!Objects.equals(projectRoot, projectRootFolder)) {
                throw new InvocationTargetException(new IllegalArgumentException(
                        "Cannot use 'addResourceMarkers' option when the project does not exists in the worksapce."));
            }
        }
        try {
            BonitaModelVisitor fileCountVisitor = new BonitaModelVisitor(new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    fileCount++;
                    return super.visitFile(file, attrs);
                }
            });
            Files.walkFileTree(projectRootFolder.toPath(), fileCountVisitor);
            monitor.beginTask(Messages.checkingModelCompatibility, fileCount);
            Files.walkFileTree(projectRootFolder.toPath(), new BonitaModelVisitor(new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (!filesToValidate.isEmpty() && !filesToValidate.contains(file.toFile())) {
                        return FileVisitResult.CONTINUE;
                    }
                    IRepositoryStore<? extends IRepositoryFileStore> repositoryStore = repository
                            .getRepositoryStoreByName(file.toFile().getParentFile().getName())
                            .orElse(repository
                                    .getRepositoryStoreByName(file.toFile().getParentFile().getParentFile().getName())
                                    .orElse(null));
                    if (repositoryStore == null) {
                        return FileVisitResult.CONTINUE;
                    }
                    try (InputStream inputStream = new FileInputStream(file.toFile())) {
                        IFile resource = null;
                        if (addResourceMarkers) {
                            IPath absolutePath = org.eclipse.core.runtime.Path
                                    .fromOSString(file.toFile().getAbsolutePath());
                            resource = project.getFile(absolutePath.makeRelativeTo(project.getLocation()));
                            if (resource != null && resource.exists()) {
                                try {
                                    resource.deleteMarkers(MODEL_VERSION_MARKER_TYPE, true, IResource.DEPTH_ONE);
                                } catch (CoreException e) {
                                    BonitaStudioLog.error(e);
                                }
                            }
                        }
                        IStatus status = repositoryStore.validate(file.toFile().getName(), inputStream);
                        if (status.getSeverity() == IStatus.ERROR) {
                            ModelFileCompatibilityValidator.this.status.add(status);
                            if (resource != null && resource.exists()) {
                                try {
                                    IMarker marker = resource.createMarker(MODEL_VERSION_MARKER_TYPE);
                                    marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
                                    marker.setAttribute(IMarker.MESSAGE, status.getMessage());
                                    marker.setAttribute(IMarker.LOCATION, "");
                                } catch (CoreException e) {
                                    BonitaStudioLog.error(e);
                                }
                                return FileVisitResult.CONTINUE;
                            }
                            return FileVisitResult.TERMINATE;
                        }
                    }
                    monitor.worked(1);
                    return FileVisitResult.CONTINUE;
                }
            }));
        } catch (IOException e) {
            throw new InvocationTargetException(e);
        }

    }

    @Override
    public MultiStatus getStatus() {
        return status;
    }

    class BonitaModelVisitor extends SimpleFileVisitor<Path> {

        private FileVisitor<Path> visitorDelegate;

        public BonitaModelVisitor(FileVisitor<Path> visitorDelegate) {
            this.visitorDelegate = visitorDelegate;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            if (Objects.equals(dir, projectRootFolder.toPath())) {
                return FileVisitResult.CONTINUE;
            }
            if (".metadata".equals(dir.toFile().getName())) {
                return FileVisitResult.SKIP_SUBTREE;
            }
            if (!repository.getRepositoryStoreByName(dir.toFile().getName()).isPresent()
                    && !isWebArtifactRepositor(dir.toFile().getParentFile().getName())) {
                return FileVisitResult.SKIP_SUBTREE;
            }
            return visitorDelegate.preVisitDirectory(dir, attrs);
        }

        private boolean isWebArtifactRepositor(String name) {
            return WebPageRepositoryStore.WEB_FORM_REPOSITORY_NAME.equals(name) ||
                    WebWidgetRepositoryStore.WEB_WIDGET_REPOSITORY_NAME.equals(name) ||
                    WebFragmentRepositoryStore.WEB_FRAGMENT_REPOSITORY_NAME.equals(name);
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (file.toFile().getParentFile().equals(projectRootFolder)) {
                return FileVisitResult.CONTINUE;
            }
            return visitorDelegate.visitFile(file, attrs);
        }

    }

}
