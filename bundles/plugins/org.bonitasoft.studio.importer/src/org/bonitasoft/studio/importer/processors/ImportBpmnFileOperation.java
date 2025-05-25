package org.bonitasoft.studio.importer.processors;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.bonitasoft.studio.importer.ImporterFactory;
import org.bonitasoft.studio.importer.ImporterPlugin;
import org.bonitasoft.studio.importer.i18n.Messages;
import org.bonitasoft.studio.importer.ui.wizard.BpmnSourceSelectionDialog;
import org.bonitasoft.studio.importer.ui.wizard.ImportFileWizard;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

public class ImportBpmnFileOperation extends ImportFileOperation{

	private static final String DEFINITIONS_TAG_NAME = "definitions";
	private static final String EXPORTER_ATTRIBUTE_NAME = "exporter";
	private static final String EXPORTER_VERSION_ATTRIBUTE_NAME = "exporterVersion";
	private final ImportFileWizard importFileWizard;
	private final File fileToImport;
	
	public ImportBpmnFileOperation(ImporterFactory importerFactory, File fileToImport, ImportFileWizard importFileWizard) {
		super(importerFactory, fileToImport);
		this.importFileWizard = importFileWizard;
		this.fileToImport = fileToImport;
	}

	@Override
	public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		if (!checkSource()
				|| new BpmnSourceSelectionDialog(importFileWizard.getShell(), importFileWizard).open() == Dialog.OK) {
			String message = Messages.bind(Messages.importSucessfulWithSourceMessage
					, new String[] {importFileWizard.getImportFileData().getBpmnSource(), importFileWizard.getImportFileData().getBpmnSourceVersion()});
				Display.getDefault().asyncExec(new Runnable() {
		            @Override
		            public void run() {
		            	getImportStatusDialogHandler(new Status(IStatus.OK, ImporterPlugin.PLUGIN_ID, message))
		                		.open(Display.getDefault().getActiveShell());
		            }
				});
				super.run(monitor);
		}
	}
	


    protected boolean checkSource() {
	        XMLInputFactory factory = XMLInputFactory.newInstance();
	        try (FileInputStream fileInputStream = new FileInputStream(fileToImport)) {
	            XMLStreamReader reader = factory.createXMLStreamReader(fileInputStream);
	            
	            while (reader.hasNext()) {
	                int event = reader.next();

	                if (event == XMLStreamConstants.START_ELEMENT) {
	                    String tagName = reader.getLocalName();

	                    if (DEFINITIONS_TAG_NAME.equals(tagName)) {
	                    	var exporter = reader.getAttributeValue(null, EXPORTER_ATTRIBUTE_NAME);
	                    	var exporterVersion = reader.getAttributeValue(null, EXPORTER_VERSION_ATTRIBUTE_NAME);
	                    	if(exporter == null) {
	                    		return true;
	                    	}
	                    	importFileWizard.getImportFileData().setBpmnSource(exporter);
	                    	importFileWizard.getImportFileData().setBpmnSourceVersion(exporterVersion);
	                    	return false;
	                    }
	                }
	            }
	            reader.close();
	            return true;
	        } catch (IOException | XMLStreamException e) {
				e.printStackTrace();
			}
		return false;
	}

}
