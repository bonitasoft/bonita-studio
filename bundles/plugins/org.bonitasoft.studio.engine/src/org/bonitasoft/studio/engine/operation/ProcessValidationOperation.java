/*******************************************************************************
 * Copyright (C) 2018 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.engine.operation;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.Parameterization;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;

public class ProcessValidationOperation {

    private static final String PROCESS_VALIDATION_CMD = "org.bonitasoft.studio.validation.batchValidation";
    public IStatus status = ValidationStatus.ok();
    private Set<AbstractProcess> processes;

    public ProcessValidationOperation(Set<AbstractProcess> processes) {
        this.processes = processes;
    }

    public ProcessValidationOperation(AbstractProcess process) {
        processes = new HashSet<>();
        processes.add(process);
    }

    public IStatus getStatus() {
        return status;
    }

    public void run() {
        ICommandService cmdService = PlatformUI.getWorkbench().getService(ICommandService.class);
        Command cmd = cmdService.getCommand(PROCESS_VALIDATION_CMD);
        if (cmd.isEnabled()) {
            IHandlerService handlerService = PlatformUI.getWorkbench().getService(IHandlerService.class);
            Set<String> procFiles = new HashSet<>();
            for (AbstractProcess p : processes) {
                Resource eResource = p.eResource();
                if (eResource != null) {
                    procFiles.add(URI.decode(eResource.getURI().lastSegment()));
                }
            }
            Parameterization showReportParam;
            try {
                showReportParam = new Parameterization(cmd.getParameter("showReport"),
                        Boolean.FALSE.toString());
                Parameterization filesParam = new Parameterization(cmd.getParameter("diagrams"), procFiles.toString());
                status = (IStatus) handlerService.executeCommand(
                        new ParameterizedCommand(cmd, new Parameterization[] { showReportParam, filesParam }), null);
            } catch (NotDefinedException | ExecutionException | NotEnabledException | NotHandledException e) {
                throw new RuntimeException("An error occured during porocess validation", e);
            }
        }
    }
}
