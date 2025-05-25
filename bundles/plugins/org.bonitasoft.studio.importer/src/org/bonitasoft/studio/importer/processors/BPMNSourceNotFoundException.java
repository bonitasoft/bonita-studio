package org.bonitasoft.studio.importer.processors;

import org.bonitasoft.studio.importer.handler.BpmnSourceSelectionDialog;
import org.eclipse.swt.widgets.Display;

public class BPMNSourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BPMNSourceNotFoundException() {
		new BpmnSourceSelectionDialog(Display.getDefault().getActiveShell()).open();
	}

}
