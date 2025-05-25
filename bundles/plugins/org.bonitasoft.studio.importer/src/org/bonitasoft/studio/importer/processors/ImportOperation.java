package org.bonitasoft.studio.importer.processors;

import java.util.List;

import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.importer.handler.ImportStatusDialogHandler;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.operation.IRunnableWithProgress;

public interface ImportOperation extends IRunnableWithProgress{
	
	List<DiagramFileStore> getFileStoresToOpen();
	
	IStatus getStatus();
	
	ImportStatusDialogHandler getImportStatusDialogHandler(final IStatus status);

}
