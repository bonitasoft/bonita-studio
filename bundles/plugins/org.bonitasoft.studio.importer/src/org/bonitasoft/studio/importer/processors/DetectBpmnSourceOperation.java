package org.bonitasoft.studio.importer.processors;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.importer.i18n.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

public class DetectBpmnSourceOperation implements IRunnableWithProgress {

	private final File fileToImport;
	
	public DetectBpmnSourceOperation(final File fileToImport) {
		this.fileToImport = fileToImport;
	}
	
	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		monitor.beginTask(Messages.importProcessProgressDialog, IProgressMonitor.UNKNOWN);
		//to be replaced with the logic
		throw new BPMNSourceNotFoundException();
	}

}
