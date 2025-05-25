package org.bonitasoft.studio.importer.processors;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.importer.i18n.Messages;
import org.bonitasoft.studio.importer.ui.wizard.ImportFileWizard;
import org.bonitasoft.studio.ui.dialog.SkippableProgressMonitorJobsDialog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

public class BpmnSourceResolverOperation implements IRunnableWithProgress {

	private final File fileToImport;
	private final ImportFileWizard importFileWizard;
	private final SkippableProgressMonitorJobsDialog progressManager;
	
	public BpmnSourceResolverOperation(ImportFileWizard importFileWizard, final File fileToImport, SkippableProgressMonitorJobsDialog progressManager) {
		this.fileToImport = fileToImport;
		this.importFileWizard = importFileWizard;
		this.progressManager = progressManager;
	}
	
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		monitor.beginTask(Messages.importProcessProgressDialog, IProgressMonitor.UNKNOWN);
		//to be replaced with the logic
		throw new BPMNSourceNotFoundException(monitor, importFileWizard, progressManager);
	}

}
