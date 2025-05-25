package org.bonitasoft.studio.importer.processors;

import org.bonitasoft.studio.importer.handler.BpmnSourceSelectionDialog;
import org.bonitasoft.studio.importer.ui.wizard.ImportFileWizard;
import org.bonitasoft.studio.ui.dialog.SkippableProgressMonitorJobsDialog;
import org.eclipse.core.runtime.IProgressMonitor;

public class BPMNSourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BPMNSourceNotFoundException(IProgressMonitor monitor, ImportFileWizard importFileWizard, SkippableProgressMonitorJobsDialog progressManager) {
		new BpmnSourceSelectionDialog(progressManager.getShell()).open();
	}

}
