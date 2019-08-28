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
import org.bonitasoft.studio.application.ui.control.model.Artifact;
import org.bonitasoft.studio.application.ui.control.model.ProcessVersion;
import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.validation.common.operation.ProcessValidationOperation;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;

public class ValidateProjectOperation implements IRunnableWithStatus {

    private MultiStatus status;
    private List<ProcessVersion> processToValidate;

    public ValidateProjectOperation(Collection<Artifact> artifactsToValidate) {
        this.processToValidate = artifactsToValidate.stream()
                .filter(ProcessVersion.class::isInstance)
                .map(ProcessVersion.class::cast)
                .collect(Collectors.toList());
        this.status = new MultiStatus(ApplicationPlugin.PLUGIN_ID, 0, "", null);
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        ProcessValidationOperation validationAction = new ProcessValidationOperation();
        for (ProcessVersion pv : processToValidate) {
            if (monitor.isCanceled()) {
                this.status.add(Status.CANCEL_STATUS);
                break;
            }
            validationAction.addProcess(pv.getModel());
        }
        validationAction.run(monitor);
        if(monitor.isCanceled()) {
            status.add(Status.CANCEL_STATUS);
        }else if(Objects.equals(validationAction.getStatus().getSeverity(), ValidationStatus.ERROR)) {
            status.addAll(validationAction.getStatus());
        }
    }

    @Override
    public IStatus getStatus() {
        return status;
    }
}
