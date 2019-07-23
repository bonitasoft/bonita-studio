/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.engine.command;

import java.util.List;
import java.util.Objects;

import javax.inject.Named;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.engine.operation.ExportBarOperation;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Display;

public class BuildDiagramHandler {

    class BuildDiagramRunnable implements Runnable {

        private IStatus status = ValidationStatus.ok();
        private String fileName;
        private String destinationPath;
        private RepositoryAccessor repositoryAccessor;

        BuildDiagramRunnable(RepositoryAccessor repositoryAccessor, String fileName, String destinationPath) {
            this.repositoryAccessor = repositoryAccessor;
            this.fileName = fileName;
            this.destinationPath = destinationPath;
        }

        @Override
        public void run() {
            List<AbstractProcess> processes = retrieveProcesses(repositoryAccessor, fileName);
            ExportBarOperation exportBarOperation = getExportOperation();
            processes.forEach(exportBarOperation::addProcessToDeploy);
            exportBarOperation.setTargetFolder(destinationPath);
            exportBarOperation.run(new NullProgressMonitor());
            if (Objects.equals(exportBarOperation.getStatus().getSeverity(), ValidationStatus.ERROR)) {
                this.status = exportBarOperation.getStatus();
            }
        }

        public IStatus getStatus() {
            return status;
        }

    }

    @Execute
    public IStatus execute(RepositoryAccessor repositoryAccessor,
            @Named("fileName") String fileName,
            @Named("destinationPath") String destinationPath) {
        BuildDiagramRunnable runnable = new BuildDiagramRunnable(repositoryAccessor, fileName,
                destinationPath);
        Display.getDefault().syncExec(runnable);
        return runnable.getStatus();
    }

    private List<AbstractProcess> retrieveProcesses(RepositoryAccessor repositoryAccessor, String fileName) {
        return repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class).getChild(fileName).getProcesses();
    }

    protected ExportBarOperation getExportOperation() {
        return new ExportBarOperation();
    }

}
