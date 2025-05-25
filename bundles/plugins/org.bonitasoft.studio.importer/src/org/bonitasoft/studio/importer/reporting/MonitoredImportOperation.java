package org.bonitasoft.studio.importer.reporting;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

public interface MonitoredImportOperation extends IRunnableWithProgress {

	void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException;
}
