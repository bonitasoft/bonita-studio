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

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.ui.PlatformUtil;
import org.bonitasoft.studio.common.ui.jface.BonitaErrorDialog;
import org.bonitasoft.studio.common.ui.jface.CustomWizardDialog;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.ImporterPlugin;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.omg.spec.bpmn.model.TDefinitions;
import org.bonitasoft.studio.importer.i18n.Messages;
import org.bonitasoft.studio.importer.processors.ImportFileOperation;
import org.bonitasoft.studio.importer.ui.wizard.ImportFileWizard;
import org.bonitasoft.studio.ui.dialog.SkippableProgressMonitorJobsDialog;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 */
public class ImportOtherHandler {

    /**
     * Simple data holder for BPMN source tool information used for logging.
     */
    private static class BpmnSourceInfo {
        private final String exporter;
        private final String exporterVersion;
        
        public BpmnSourceInfo(String exporter, String exporterVersion) {
            this.exporter = exporter != null ? exporter.trim() : "";
            this.exporterVersion = exporterVersion != null ? exporterVersion.trim() : "";
        }
        
        public String getExporter() {
            return exporter;
        }
        
        public String getExporterVersion() {
            return exporterVersion;
        }
        
        public boolean hasExporter() {
            return !exporter.isEmpty();
        }
    }

    @Execute
    public void execute() {
        final ImportFileWizard importFileWizard = createImportWizard();
        if (new CustomWizardDialog(Display.getDefault().getActiveShell(), importFileWizard, Messages.importButtonLabel)
                .open() == Dialog.OK) {
            final File selectedFile = new File(importFileWizard.getSelectedFilePath());
            

            
            final SkippableProgressMonitorJobsDialog progressManager = new SkippableProgressMonitorJobsDialog(
                    Display.getDefault().getActiveShell());
            final ImportFileOperation operation = createImportFileOperation(importFileWizard, selectedFile, progressManager);
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
            }
            // Handle BPMN source tool detection and user prompt if needed
            handleBpmnSourceAfterImport(operation, selectedFile);
            
            for (final DiagramFileStore fileStore : operation.getFileStoresToOpen()) {
                fileStore.open();
            }
            PlatformUtil.openIntroIfNoOtherEditorOpen();
            Display.getDefault().asyncExec(openStatusDialogWithBpmnInfo(operation, selectedFile));
        }
    }

    private Runnable openStatusDialog(final ImportFileOperation operation) {
        return new Runnable() {

            @Override
            public void run() {
                operation.getImportStatusDialogHandler(operation.getStatus()).open(Display.getDefault().getActiveShell());
            }
        };
    }
    
    /**
     * Opens status dialog with BPMN source tool information included.
     * Reuses existing status dialog infrastructure.
     */
    private Runnable openStatusDialogWithBpmnInfo(final ImportFileOperation operation, final File selectedFile) {
        return new Runnable() {

            @Override
            public void run() {
                // Get BPMN source info for confirmation message
                String sourceInfo = getBpmnSourceInfoForConfirmation(operation, selectedFile);
                
                // Create enhanced status dialog handler with BPMN info
                ImportStatusDialogHandler handler = operation.getImportStatusDialogHandler(operation.getStatus());
                
                // If it's a DefaultImportStatusDialogHandler, we can enhance the message
                if (handler instanceof DefaultImportStatusDialogHandler && sourceInfo != null) {
                    String enhancedMessage = Messages.importSucessfulMessage + "\n\n" + sourceInfo;
                    handler = new DefaultImportStatusDialogHandler(operation.getStatus(), enhancedMessage, null);
                }
                
                handler.open(Display.getDefault().getActiveShell());
            }
        };
    }
    
    /**
     * Gets BPMN source info for confirmation dialog.
     */
    private String getBpmnSourceInfoForConfirmation(ImportFileOperation operation, File selectedFile) {
        try {
            if (!selectedFile.getName().toLowerCase().endsWith(".bpmn")) {
                return null;
            }
            
            TDefinitions definitions = operation.getBpmnDefinitions();
            if (definitions != null) {
                String toolName = definitions.getExporter();
                String toolVersion = definitions.getExporterVersion();
                
                if (toolName != null && !toolName.trim().isEmpty()) {
                    String info = "Source tool: " + toolName.trim();
                    if (toolVersion != null && !toolVersion.trim().isEmpty()) {
                        info += " (version " + toolVersion.trim() + ")";
                    }
                    return info;
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
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
    
    /**
     * Handles BPMN source tool detection and user prompt after import.
     * Uses the already parsed TDefinitions to avoid double parsing.
     */
    private void handleBpmnSourceAfterImport(ImportFileOperation operation, File selectedFile) {
        try {
            // Only handle BPMN files
            if (!selectedFile.getName().toLowerCase().endsWith(".bpmn")) {
                return;
            }
            
            TDefinitions definitions = operation.getBpmnDefinitions();
            if (definitions == null) {
                return; // Not a BPMN import
            }
            
            String toolName = definitions.getExporter();
            String toolVersion = definitions.getExporterVersion();
            
            // If no source detected automatically, prompt user
            BpmnSourceInfo sourceInfoForLog = new BpmnSourceInfo(toolName, toolVersion);
            if (toolName == null || toolName.trim().isEmpty()) {
                sourceInfoForLog = promptUserForMissingSource(selectedFile);
                if (sourceInfoForLog != null) {
                    toolName = sourceInfoForLog.getExporter();
                    toolVersion = sourceInfoForLog.getExporterVersion();
                }
            }
            
            // Log the final result (detected or user-provided)
            logBpmnImport(sourceInfoForLog, selectedFile);
            
        } catch (Exception e) {
            BonitaStudioLog.error("Error handling BPMN source detection: " + e.getMessage(), ImporterPlugin.PLUGIN_ID);
        }
    }
    
    /**
     * Prompts user when no source tool was detected automatically.
     * Uses existing Eclipse ElementListSelectionDialog - simple and effective!
     * @return BpmnSourceInfo with user-provided exporter info or null if cancelled
     */
    private BpmnSourceInfo promptUserForMissingSource(File selectedFile) {
        try {
            String[] tools = {
                "Camunda Modeler",
                "Signavio", 
                "Bizagi Modeler",
                "Others"
            };
            
            ElementListSelectionDialog dialog = new ElementListSelectionDialog(
                Display.getDefault().getActiveShell(),
                new LabelProvider()
            );
            dialog.setTitle("BPMN Source Tool");
            dialog.setMessage("The source modeling tool could not be detected. Please select the tool used:");
            dialog.setElements(tools);
            
            if (dialog.open() == Dialog.OK) {
                String selectedTool = (String) dialog.getFirstResult();
                
                // Log user action
                BonitaStudioLog.info("User specified BPMN source tool: " + selectedTool, ImporterPlugin.PLUGIN_ID);
                
                // Create BpmnSourceInfo with user-provided data (no version for simplicity)
                return new BpmnSourceInfo(selectedTool, "");
            }
            return null; // User cancelled
        } catch (Exception e) {
            BonitaStudioLog.error("Error prompting for BPMN source: " + e.getMessage(), ImporterPlugin.PLUGIN_ID);
            return null;
        }
    }
    
    /**
     * Logs BPMN import with source tool information.
     */
    private void logBpmnImport(BpmnSourceInfo sourceInfoForLog, File selectedFile) {
        try {
            if (sourceInfoForLog == null) {
                BonitaStudioLog.info("BPMN file imported with no source tool information (file: " + selectedFile.getName() + ")", ImporterPlugin.PLUGIN_ID);
                return;
            }
            
            StringBuilder logMessage = new StringBuilder();
            logMessage.append("BPMN file imported from \"");
            
            // Get tool info from BpmnSourceInfo
            String toolName = sourceInfoForLog.getExporter();
            String toolVersion = sourceInfoForLog.getExporterVersion();
            
            String safeTool = (toolName != null && !toolName.trim().isEmpty()) ? toolName.trim() : "Unknown";
            logMessage.append(safeTool).append("\"");
            
            if (toolVersion != null && !toolVersion.trim().isEmpty()) {
                logMessage.append(" version \"").append(toolVersion.trim()).append("\"");
            }
            
            logMessage.append(" at ").append(java.time.LocalDateTime.now().format(
                java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            logMessage.append(" (file: ").append(selectedFile.getName()).append(")");
            
            BonitaStudioLog.info(logMessage.toString(), ImporterPlugin.PLUGIN_ID);
        } catch (Exception e) {
            BonitaStudioLog.error("Error logging BPMN import: " + e.getMessage(), ImporterPlugin.PLUGIN_ID);
        }
    }
    
    @CanExecute
    public boolean isEnabled() {
        if (RepositoryManager.getInstance().hasActiveRepository()) {
            return true;
        }
        return false;
    }

}
