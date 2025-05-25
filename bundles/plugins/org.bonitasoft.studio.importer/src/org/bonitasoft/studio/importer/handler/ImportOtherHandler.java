/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.handler;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.bonitasoft.studio.common.ui.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.ui.jface.CustomWizardDialog;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.ImporterFactory;
import org.bonitasoft.studio.importer.ImporterPlugin;
import org.bonitasoft.studio.importer.bpmn.BPMNToProcFactory;
import org.bonitasoft.studio.importer.i18n.Messages;
import org.bonitasoft.studio.importer.processors.BPMNSourceNotFoundException;
import org.bonitasoft.studio.importer.processors.BpmnSourceResolverOperation;
import org.bonitasoft.studio.importer.processors.ImportFileOperation;
import org.bonitasoft.studio.importer.ui.wizard.ImportFileWizard;
import org.bonitasoft.studio.ui.dialog.SkippableProgressMonitorJobsDialog;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 */
public class ImportOtherHandler {

    @Execute
    public void execute() {
        final ImportFileWizard importFileWizard = createImportWizard();
        if (new CustomWizardDialog(Display.getDefault().getActiveShell(), importFileWizard, Messages.importButtonLabel)
                .open() == Dialog.OK) {
        	final File selectedFile = new File(importFileWizard.getSelectedFilePath());
        	final SkippableProgressMonitorJobsDialog progressManager = new SkippableProgressMonitorJobsDialog(
                    Display.getDefault().getActiveShell());
        	Set<IRunnableWithProgress> operations = getImporterOperations(importFileWizard, selectedFile, progressManager);
        	for(IRunnableWithProgress operation : operations) {
        		try {
                    progressManager.run(false, false, operation);
                } catch (final InvocationTargetException | InterruptedException e) {
                    final Throwable t = e instanceof InvocationTargetException
                            ? ((InvocationTargetException) e).getTargetException() : e;
                    BonitaStudioLog.error("Import has failed for file " + selectedFile.getName(), ImporterPlugin.PLUGIN_ID);
                    BonitaStudioLog.error(e, ImporterPlugin.PLUGIN_ID);
                    String message = Messages.errorWhileImporting_message;
                    if (t != null && !isNullOrEmpty(t.getMessage())) {
                        message = t.getMessage();
                    }
                    new BonitaErrorDialog(Display.getDefault().getActiveShell(), Messages.errorWhileImporting_title, message, e)
                            .open();
                    break;
                }
        	}	
        	
            final ImportFileOperation operation = createImportFileOperation(importFileWizard, selectedFile, progressManager);
            
            for (final DiagramFileStore fileStore : operation.getFileStoresToOpen()) {
                fileStore.open();
            }
            PlatformUtil.openIntroIfNoOtherEditorOpen();
            Display.getDefault().asyncExec(openStatusDialog(operation));
        }
    }

    private Set<IRunnableWithProgress> getImporterOperations(ImportFileWizard importFileWizard, File selectedFile, SkippableProgressMonitorJobsDialog progressManager) {
		Set<IRunnableWithProgress> operations = new TreeSet<IRunnableWithProgress>();
    	if(importFileWizard.getSelectedTransfo() instanceof BPMNToProcFactory) {
    		operations.add(new BpmnSourceResolverOperation(selectedFile));
		}
    	operations.add(createImportFileOperation(importFileWizard, selectedFile, progressManager));
		return operations;
	}

	private Runnable openStatusDialog(final ImportFileOperation operation) {
        return new Runnable() {

            @Override
            public void run() {
                operation.getImportStatusDialogHandler(operation.getStatus()).open(Display.getDefault().getActiveShell());
            }
        };
    }

    protected DiagramRepositoryStore getDiagramRepositoryStore() {
        return RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
    }

    protected ImportFileOperation createImportFileOperation(final ImportFileWizard importFileWizard, final File selectedFile,
            final SkippableProgressMonitorJobsDialog progressManager) {
        return new ImportFileOperation(importFileWizard.getSelectedTransfo(),
                selectedFile, progressManager);
    }

    protected ImportFileWizard createImportWizard() {
        return new ImportFileWizard();
    }
    
    @CanExecute
    public boolean isEnabled() {
        if (RepositoryManager.getInstance().hasActiveRepository()) {
            return true;
        }
        return false;
    }

}
