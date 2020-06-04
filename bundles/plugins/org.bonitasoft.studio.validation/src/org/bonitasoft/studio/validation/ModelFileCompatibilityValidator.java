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
import java.util.Objects;

import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.common.repository.model.IRepository;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryStore;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class ModelFileCompatibilityValidator implements IRunnableWithStatus {

    private File projectRootFolder;
    private IRepository repository;
    private int fileCount = 0;
    private IStatus status = Status.OK_STATUS;

    public ModelFileCompatibilityValidator(File projectRootFolder, IRepository repository) {
        this.projectRootFolder = projectRootFolder;
        this.repository = repository;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
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
            Files.walkFileTree(projectRootFolder.toPath(),  new BonitaModelVisitor(new SimpleFileVisitor<Path>() {

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    IRepositoryStore<? extends IRepositoryFileStore> repositoryStore = repository.getRepositoryStoreByName(file.toFile().getParentFile().getName()).get();
                    try(InputStream inputStream = new FileInputStream(file.toFile())){
                        IStatus status = repositoryStore.validate(file.toFile().getName(), inputStream);
                        if(status.getSeverity() == IStatus.ERROR) {
                            ModelFileCompatibilityValidator.this.status = status;
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
    public IStatus getStatus() {
        return status;
    }

    class BonitaModelVisitor extends SimpleFileVisitor<Path> {
        
        private FileVisitor<Path> visitorDelegate;

        public BonitaModelVisitor(FileVisitor<Path> visitorDelegate) {
            this.visitorDelegate = visitorDelegate;
        }
        
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            if(Objects.equals(dir, projectRootFolder.toPath())) {
                return FileVisitResult.CONTINUE;
            }
            if (!repository.getRepositoryStoreByName(dir.toFile().getName()).isPresent()) {
                return FileVisitResult.SKIP_SUBTREE;
            }
            return visitorDelegate.preVisitDirectory(dir, attrs);
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if(file.toFile().getParentFile().equals(projectRootFolder)) {
                return FileVisitResult.CONTINUE;
            }
            return visitorDelegate.visitFile(file, attrs);
        }
        
    }
}
