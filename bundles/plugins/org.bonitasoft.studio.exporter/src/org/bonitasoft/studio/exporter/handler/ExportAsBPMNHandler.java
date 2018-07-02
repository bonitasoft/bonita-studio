/**
 * Copyright (C) 2017 Bonitasoft S.A.
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
package org.bonitasoft.studio.exporter.handler;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import org.bonitasoft.studio.common.model.IModelSearch;
import org.bonitasoft.studio.common.model.ModelSearch;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.exporter.Messages;
import org.bonitasoft.studio.exporter.bpmn.transfo.BonitaToBPMNExporter;
import org.bonitasoft.studio.exporter.extension.BonitaModelExporterImpl;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.ui.dialog.ExceptionDialogHandler;
import org.bonitasoft.studio.ui.dialog.MultiStatusDialog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.progress.IProgressService;

public class ExportAsBPMNHandler {

    @Execute
    public void export(Shell shell,
            BonitaToBPMNExporter transformer,
            IWorkbenchPage activePage,
            IProgressService progressService,
            ExceptionDialogHandler exceptionDialogHandler,
            RepositoryAccessor repositoryAccessor) {
        ProcessDiagramEditor editor = (ProcessDiagramEditor) activePage.getActiveEditor();
        MainProcess diagram = (MainProcess) editor.getDiagramEditPart().resolveSemanticElement();
        FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
        fileDialog.setFilterExtensions(new String[] { "*.bpmn" });
        fileDialog.setFileName(String.format("%s - %s.bpmn", diagram.getName(), diagram.getVersion()));
        Optional.ofNullable(fileDialog.open())
                .ifPresent(targetPath -> {
                    final File destFile = new File(targetPath);
                    boolean overwrite = destFile.exists()
                            && MessageDialog.openQuestion(Display.getDefault().getActiveShell(),
                                    Messages.overwriteBPMNFile_title,
                                    Messages.bind(Messages.overwriteBPMNFile_message, destFile.getName()));
                    if (!destFile.exists() || overwrite) {
                        try {
                            progressService.run(true, false, monitor -> {
                                monitor.beginTask(Messages.exportingTo + " " + destFile.getName() + "...",
                                        IProgressMonitor.UNKNOWN);
                                DiagramRepositoryStore diagramRepoStore = repositoryAccessor
                                        .getRepositoryStore(DiagramRepositoryStore.class);
                                ConnectorDefRepositoryStore connectorDefRepoStore = repositoryAccessor
                                        .getRepositoryStore(ConnectorDefRepositoryStore.class);
                                IModelSearch modelSearch = new ModelSearch(
                                        () -> diagramRepoStore.getAllProcesses(),
                                        () -> connectorDefRepoStore.getDefinitions());
                                transformer.export(
                                        new BonitaModelExporterImpl(diagram.eResource(), modelSearch),
                                        modelSearch,
                                        destFile);
                            });
                            MultiStatus status = transformer.getStatus();
                            if (status.getSeverity() < IStatus.ERROR) {
                                new MultiStatusDialog(shell,
                                        Messages.exportSuccessfulTitle,
                                        Messages.exportSuccessfulMessage,
                                        MessageDialog.INFORMATION,
                                        new String[] { IDialogConstants.OK_LABEL },
                                        status).open();
                            } else {
                                new MultiStatusDialog(shell,
                                        Messages.exportFailedTitle,
                                        Messages.exportFailedMessage,
                                        MessageDialog.ERROR,
                                        new String[] { IDialogConstants.OK_LABEL },
                                        status).open();
                            }
                        } catch (InvocationTargetException | InterruptedException e) {
                            exceptionDialogHandler.openErrorDialog(shell, Messages.exportFailedMessage, e);
                        }
                    }
                });
    }

    @CanExecute
    public boolean isDiagramEditorActive(IWorkbenchPage activePage) {
        return activePage != null && activePage.getActiveEditor() instanceof ProcessDiagramEditor;
    }

}
