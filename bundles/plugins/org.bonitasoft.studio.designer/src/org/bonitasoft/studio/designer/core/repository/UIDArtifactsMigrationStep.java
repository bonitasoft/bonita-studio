/**
 * Copyright (C) 2025 BonitaSoft S.A.
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
package org.bonitasoft.studio.designer.core.repository;

import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.common.repository.core.migration.MigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.StepDescription;
import org.bonitasoft.studio.common.repository.core.migration.report.MigrationReport;
import org.bonitasoft.studio.designer.core.exception.UIDBackendException;
import org.bonitasoft.studio.designer.core.operation.MigrateUIDOperation;
import org.bonitasoft.studio.designer.i18n.Messages;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import jakarta.annotation.PostConstruct;

public class UIDArtifactsMigrationStep implements MigrationStep {

    @Override
    public StepDescription getDescription() {
        return new StepDescription(Messages.uidArtifactsMigrationTitle, Messages.uidArtifactsMigrationDescription);
    }

    @Override
    public MigrationReport run(Path projectRoot, IProgressMonitor monitor) throws CoreException {
        monitor.subTask(Messages.uidArtifactsMigrationTitle);
        var migrateUIDOperation = new MigrateUIDOperation()
                .useStandaloneUIDAt(projectRoot.resolve(BonitaProject.APP_MODULE));
        try {
            migrateUIDOperation.run(monitor);
            var status = migrateUIDOperation.getStatus();
            if (status.matches(IStatus.ERROR)) {
                var sb = new StringBuilder();
                statusMessage(sb, migrateUIDOperation.getStatus());
                throw new CoreException(
                        Status.error(sb.toString(), new UIDBackendException(migrateUIDOperation.getLogs())));
            }
        } catch (InvocationTargetException e) {
            throw new CoreException(
                    Status.error("An error occured during UID artifacts migration.",
                            new UIDBackendException(migrateUIDOperation.getLogs(), e.getTargetException())));
        } catch (InterruptedException e) {
            BonitaStudioLog.error(e);
        }
        return MigrationReport.emptyReport();
    }

    private void statusMessage(StringBuilder sb, IStatus status) {
        if (status.getSeverity() == IStatus.ERROR) {
            String message = status.getMessage();
            if (message != null && !message.isBlank()) {
                sb.append("ERROR: ");
                sb.append(message);
            }
        }
        for (var child : status.getChildren()) {
            sb.append(System.lineSeparator());
            statusMessage(sb, child);
        }
    }


    @PostConstruct
    void registerMigrationStep() {
        MigrationStep.register("UidMigrationStep", this);
    }

}
