/**
 * Copyright (C) 2025 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.designer.core.operation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Map;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.net.HttpClientFactory;
import org.bonitasoft.studio.designer.core.PageDesignerURLFactory;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class UIDArtifactMigrationVisitor implements FileVisitor<Path> {

    private Path parentFolder;
    protected Throwable error;
    private IProgressMonitor monitor;
    private PageDesignerURLFactory urlBuilder;
    protected ObjectMapper objectMapper = new ObjectMapper();

    protected UIDArtifactMigrationVisitor(Path parentFolder, PageDesignerURLFactory urlBuilder, IProgressMonitor monitor) {
        this.parentFolder = parentFolder;
        this.urlBuilder = urlBuilder;
        this.monitor = monitor;
    }

    public Throwable getError() {
        return error;
    }

    @Override
    public FileVisitResult preVisitDirectory(
            Path dir, BasicFileAttributes attrs) {
        if (parentFolder.equals(dir)) {
            return FileVisitResult.CONTINUE;
        }
        if (!dir.getParent().equals(parentFolder)) {
            return FileVisitResult.SKIP_SUBTREE;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(
            Path file, BasicFileAttributes attrs) {
        if (file.getFileName().toString().endsWith(".json")) {
           BonitaStudioLog.info("Migration of UI Designer artifact descriptor: " + file.getFileName()); 
           return migrateArtifact(file.toFile(), urlBuilder, monitor);
        }
        return FileVisitResult.CONTINUE;
    }

    protected abstract FileVisitResult migrateArtifact(File file, PageDesignerURLFactory urlBuilder, IProgressMonitor monitor);

    @Override
    public FileVisitResult visitFileFailed(
            Path file, IOException exc) {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(
            Path dir, IOException exc) {
        return FileVisitResult.CONTINUE;
    }
    
    protected IStatus parseMigrationResponse(String artifactId, HttpResponse<InputStream> response) {
        try (var is = response.body()) {
            var migrationReport = objectMapper.readValue(is, Map.class);
            switch ((String) migrationReport.get("status")) {
                case "incompatible":
                    BonitaStudioLog.info(String.format(Messages.migrationNotPossible, artifactId));
                    return ValidationStatus.error(String.format(Messages.migrationNotPossible, artifactId));
                case "error":
                    BonitaStudioLog.info(String.format(Messages.migrationError, artifactId));
                    return ValidationStatus.error(String.format(Messages.migrationError, artifactId));
                case "warning":
                    BonitaStudioLog.warning(String.format(Messages.migrationWarning, artifactId), UIDArtifactMigrationVisitor.class);
                    return ValidationStatus.warning(String.format(Messages.migrationWarning, artifactId));
                case "success": 
                    BonitaStudioLog.info(String.format("%s migrated sucessfully", artifactId));
                    return ValidationStatus.ok();
                case "none": 
                default:
                    return ValidationStatus.ok();
            }
        } catch (IOException e) {
            BonitaStudioLog.error(e);
            return ValidationStatus.error(e.getMessage(), e);
        }
    }
    
    protected HttpResponse<InputStream> retriesUntil(HttpRequest request, int expectedStatus, int nbOfRetries,
            int delayBetweenRetries) {
        int retry = nbOfRetries;
        while (retry >= 0) {
            try {
                HttpResponse<InputStream> httpResponse = HttpClientFactory.INSTANCE.send(request,
                        BodyHandlers.ofInputStream());
                if (expectedStatus == httpResponse.statusCode()) {
                    return httpResponse;
                } else {
                    retry--;
                    Thread.sleep(delayBetweenRetries);
                }

            } catch (IOException | InterruptedException e) {
                retry--;
                try {
                    Thread.sleep(delayBetweenRetries);
                } catch (InterruptedException e1) {
                   BonitaStudioLog.error(e1);
                }
                BonitaStudioLog.error(e);
            }
        }
        return null;
    }

}
