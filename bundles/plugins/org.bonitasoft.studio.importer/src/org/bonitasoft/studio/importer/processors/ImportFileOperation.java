/**
 * Copyright (C) 2013-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.importer.processors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.ImporterFactory;
import org.bonitasoft.studio.importer.ImporterPlugin;
import org.bonitasoft.studio.importer.bpmn.BPMNToProcFactory;
import org.bonitasoft.studio.importer.handler.ImportStatusDialogHandler;
import org.bonitasoft.studio.importer.i18n.Messages;
import org.bonitasoft.studio.importer.ui.wizard.BpmnExporterSource;
import org.bonitasoft.studio.importer.ui.wizard.BpmnSourceSelectionDialog;
import org.bonitasoft.studio.importer.ui.wizard.ImportFileData;
import org.bonitasoft.studio.importer.ui.wizard.ImportFileWizard;
import org.bonitasoft.studio.ui.dialog.SkippableProgressMonitorJobsDialog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * @author Romain Bioteau
 */
public class ImportFileOperation implements IRunnableWithProgress {

    private final ImporterFactory importerFactory;
    private final File fileToImport;
    private final List<DiagramFileStore> fileStoresToOpen;
    private final ImportFileWizard importFileWizard;
    private IStatus status;
    private ToProcProcessor processor;
    private SkippableProgressMonitorJobsDialog progressDialog;

    public List<DiagramFileStore> getFileStoresToOpen() {
        return fileStoresToOpen;
    }

    public ImportFileOperation(final ImportFileWizard importFileWizard,
            final File fileToImport) {
    	this.importFileWizard = importFileWizard;
        this.importerFactory = importFileWizard.getSelectedTransfo();
        this.fileToImport = fileToImport;
        fileStoresToOpen = new ArrayList<>();
    }

    public ImportFileOperation(final ImportFileWizard importFileWizard,
            final File fileToImport, final SkippableProgressMonitorJobsDialog progressDialog) {
        this(importFileWizard, fileToImport);
        this.progressDialog = progressDialog;
    }

	@Override
	public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		monitor.beginTask(Messages.importProcessProgressDialog, IProgressMonitor.UNKNOWN);
		if (!requiresSourceDetection()
				|| new BpmnSourceSelectionDialog(progressDialog.getShell(), importFileWizard).open() == Dialog.OK) {
			
			processor = importerFactory.createProcessor(fileToImport.getName());
			processor
					.setRepository(RepositoryManager.getInstance().getCurrentRepository().orElseThrow().getProjectId());
			processor.setProgressDialog(progressDialog);
			try {
				processor.createDiagram(fileToImport.toURI().toURL(), monitor);
			} catch (final MalformedURLException e) {
				status = new Status(IStatus.ERROR, ImporterPlugin.PLUGIN_ID, e.getMessage(), e);
				throw new InvocationTargetException(e, e.getMessage());
			} catch (final Exception e) {
				status = new Status(IStatus.ERROR, ImporterPlugin.PLUGIN_ID, e.getMessage(), e);
				throw new InvocationTargetException(e, e.getMessage());
			}
			// handleErrors(processor);
			addFileStoresToOpen(processor);
			status = processor.getStatus();
			return;
		}
		status = new Status(IStatus.CANCEL, ImporterPlugin.PLUGIN_ID, "BPMN File Import cancelled");
	}

    protected boolean requiresSourceDetection() {
		if(importerFactory instanceof BPMNToProcFactory) {
	        String targetTag = "definitions";  // Change to the tag you are looking for

	        XMLInputFactory factory = XMLInputFactory.newInstance();
	        try (FileInputStream fileInputStream = new FileInputStream(fileToImport)) {
	            XMLStreamReader reader = factory.createXMLStreamReader(fileInputStream);

	            while (reader.hasNext()) {
	                int event = reader.next();

	                if (event == XMLStreamConstants.START_ELEMENT) {
	                    String tagName = reader.getLocalName();

	                    if (tagName.equals(targetTag)) {
	                    	var exporter = reader.getAttributeValue(null, "exporter");
	                    	var exporterVersion = reader.getAttributeValue(null, "exporterVersion");
	                    	if(exporter == null) {
	                    		return true;
	                    	}
	                    	importFileWizard.getImportFileData().setBpmnSource(exporter);
	                    	importFileWizard.getImportFileData().setBpmnSourceVersion(exporterVersion);
	                        break;
	                    }
	                }
	            }
	            reader.close();
	            return true;
	        } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (XMLStreamException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	protected void addFileStoresToOpen(final ToProcProcessor processor)
            throws InvocationTargetException {
        for (final IRepositoryFileStore fStore : processor.getDiagramFileStoresToOpen()) {
            if (fStore instanceof DiagramFileStore) {
                fileStoresToOpen.add((DiagramFileStore) fStore);
            }
        }
        if (processor.getResources() != null) {
            for (final File f : processor.getResources()) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(f);
                    final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance().getCurrentRepository()
                            .orElseThrow()
                            .getRepositoryStore(DiagramRepositoryStore.class);
                    final DiagramFileStore fileStore = diagramStore.importInputStream(f.getName(), fis);
                    if (fileStore instanceof DiagramFileStore) {
                        fileStoresToOpen.add(fileStore);
                    }
                    f.delete();
                } catch (final FileNotFoundException e) {
                    status = new Status(IStatus.ERROR, ImporterPlugin.PLUGIN_ID, e.getMessage(), e);
                    throw new InvocationTargetException(e);
                } finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (final IOException e) {
                            throw new InvocationTargetException(e);
                        }
                    }
                }
            }
        }
    }

    public IStatus getStatus() {
        return status;
    }

    public ImportStatusDialogHandler getImportStatusDialogHandler(final IStatus status) {
        return processor.getImportStatusDialogHandler(status);
    }

}
