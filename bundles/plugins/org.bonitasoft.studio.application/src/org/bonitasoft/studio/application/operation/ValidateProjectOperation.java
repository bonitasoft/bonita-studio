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

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.bonitasoft.studio.application.ApplicationPlugin;
import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.validation.common.operation.BatchValidationOperation;
import org.bonitasoft.studio.validation.common.operation.OffscreenEditPartFactory;
import org.bonitasoft.studio.validation.common.operation.RunProcessesValidationOperation;
import org.bonitasoft.studio.validation.common.operation.ValidationMarkerProvider;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;

public class ValidateProjectOperation implements IRunnableWithStatus {

    private MultiStatus status;
    private List<DiagramFileStore> fileStoreToValidate;

    public ValidateProjectOperation(Collection<? extends IRepositoryFileStore> artifactsToValidate) {
        this.fileStoreToValidate = artifactsToValidate.stream()
                .filter(DiagramFileStore.class::isInstance)
                .map(DiagramFileStore.class::cast)
                .collect(Collectors.toList());
        this.status = new MultiStatus(ApplicationPlugin.PLUGIN_ID, 0, "", null);
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        for (DiagramFileStore fileStore : fileStoreToValidate) {
            if (monitor.isCanceled()) {
                break;
            }
            RunProcessesValidationOperation validationAction = new RunProcessesValidationOperation(
                    new BatchValidationOperation(
                            new OffscreenEditPartFactory(
                                    org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory.getInstance()),
                            new ValidationMarkerProvider()));
            validationAction.addProcess(fileStore.getContent());
            validationAction.run(monitor);
            if (!monitor.isCanceled()
                    && Objects.equals(validationAction.getStatus().getSeverity(), ValidationStatus.ERROR)) {
                status.add(validationAction.getStatus());
            }
        }
    }

    @Override
    public IStatus getStatus() {
        return status;
    }
}
