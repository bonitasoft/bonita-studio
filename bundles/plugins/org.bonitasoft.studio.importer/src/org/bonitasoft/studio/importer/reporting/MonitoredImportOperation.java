package org.bonitasoft.studio.importer.reporting;

import java.lang.reflect.InvocationTargetException;

import org.bonitasoft.studio.importer.processors.ImportOperation;
import org.eclipse.core.runtime.IProgressMonitor;

public interface MonitoredImportOperation extends ImportOperation {

	void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException;
}
