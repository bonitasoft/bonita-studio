/**
 * Copyright (C) 2012-2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.common.operation;

import static com.google.common.collect.Lists.newArrayList;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.ui.util.ProcessValidationStatus;
import org.bonitasoft.studio.validation.common.ValidationCommonPlugin;
import org.bonitasoft.studio.validation.common.i18n.Messages;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.eclipse.emf.validation.service.ModelValidationService;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

public class ProcessValidationOperation extends WorkspaceModifyOperation {

    private final List<AbstractProcess> listOfProcessesToValidate = new ArrayList<>();
    private MultiStatus status = new MultiStatus(ValidationCommonPlugin.PLUGIN_ID ,-1, null, null);

    public ProcessValidationOperation addProcess(final AbstractProcess process) {
        listOfProcessesToValidate.add(process);
        return this;
    }

    public ProcessValidationOperation addProcesses(final List<AbstractProcess> processes) {
        listOfProcessesToValidate.addAll(newArrayList(processes));
        return this;
    }

    public IStatus getStatus() {
        return status;
    }

    @Override
    protected void execute(IProgressMonitor monitor)
            throws CoreException, InvocationTargetException, InterruptedException {
        final IBatchValidator validator = (IBatchValidator) ModelValidationService.getInstance().newValidator(
                EvaluationMode.BATCH);
        validator.setIncludeLiveConstraints(true);
        listOfProcessesToValidate.stream().forEach( process -> {
            if(!monitor.isCanceled()) {
                monitor.setTaskName(Messages.bind(Messages.validatingProcess, process.getName(), process.getVersion()));
                ProcessValidationStatus processValidationStatus = new ProcessValidationStatus(process,validator.validate(process, monitor));
                status.add(processValidationStatus);
                if(processValidationStatus.getSeverity() == IStatus.ERROR) {
                    final RunProcessesValidationOperation validationAction = new RunProcessesValidationOperation(
                            new BatchValidationOperation(
                                    new OffscreenEditPartFactory(
                                            org.eclipse.gmf.runtime.diagram.ui.OffscreenEditPartFactory.getInstance()),
                                    new ValidationMarkerProvider()));
                    validationAction.addProcess(process);
                    try {
                        validationAction.run(monitor);
                    } catch (InvocationTargetException | InterruptedException e) {
                       BonitaStudioLog.error(e);
                    }
                }
            }
        });
    }

}
