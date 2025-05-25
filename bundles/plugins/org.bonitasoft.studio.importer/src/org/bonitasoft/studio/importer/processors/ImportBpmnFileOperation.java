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
import org.bonitasoft.studio.importer.i18n.Messages;
import org.bonitasoft.studio.importer.ui.wizard.BpmnSourceSelectionDialog;
import org.bonitasoft.studio.importer.ui.wizard.ImportFileWizard;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

public class ImportBpmnFileOperation extends ImportFileOperation {

	private static final String DEFINITIONS_TAG_NAME = "definitions";
	private static final String EXPORTER_ATTRIBUTE_NAME = "exporter";
	private static final String EXPORTER_VERSION_ATTRIBUTE_NAME = "exporterVersion";
	private final ImportFileWizard importFileWizard;
	private final File fileToImport;

	public ImportBpmnFileOperation(ImporterFactory importerFactory, File fileToImport,
			ImportFileWizard importFileWizard) {
		super(importerFactory, fileToImport);
		this.importFileWizard = importFileWizard;
		this.fileToImport = fileToImport;
	}

	@Override
	public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		var checkSource = false;
		try {
			checkSource = checkSource();
		} catch (IOException | XMLStreamException e) {
			throw new InvocationTargetException(e, e.getMessage());
		}
		if (checkSource
				|| new BpmnSourceSelectionDialog(importFileWizard.getShell(), importFileWizard).open() == Dialog.OK) {
			String message = Messages.bind(Messages.importSucessfulWithSourceMessage,
					new String[] { importFileWizard.getImportFileData().getBpmnSource(),
							importFileWizard.getImportFileData().getBpmnSourceVersion() });
			Display.getDefault().asyncExec(new Runnable() {
				@Override
				public void run() {
					new MessageDialog(Display.getDefault().getActiveShell(),
							org.bonitasoft.studio.importer.i18n.Messages.importResultTitle, null, message,
							MessageDialog.INFORMATION, new String[] { IDialogConstants.OK_LABEL }, 0).open();
				}
			});
			super.run(monitor);
		}
	}

	protected boolean checkSource() throws IOException, XMLStreamException {
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
						var exporterExists = false;
						var exporterVersionExists = false;
						if (exporter != null && !exporter.isEmpty()) {
							importFileWizard.getImportFileData().setBpmnSource(exporter);
							exporterExists = true;
						}
						if (exporterVersion != null && !exporterVersion.isEmpty()) {
							importFileWizard.getImportFileData().setBpmnSourceVersion(exporterVersion);
							exporterVersionExists = true;
						}
						reader.close();
						return exporterExists && exporterVersionExists;
					}
				}
			}
			reader.close();
		}
		return false;
	}

}
