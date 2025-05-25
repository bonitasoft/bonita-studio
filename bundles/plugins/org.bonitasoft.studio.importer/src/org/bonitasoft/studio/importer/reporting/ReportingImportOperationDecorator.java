package org.bonitasoft.studio.importer.reporting;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.importer.handler.ImportStatusDialogHandler;
import org.bonitasoft.studio.importer.processors.ImportOperation;
import org.bonitasoft.studio.importer.ui.wizard.ImportFileWizard;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

public class ReportingImportOperationDecorator implements ImportOperation, MonitoredImportOperation {

    private final ImportOperation delegate;
    private final ImportFileWizard wizard;
    private final ReportHandler handler;

    public ReportingImportOperationDecorator(ImportOperation delegate, ImportFileWizard wizard) {
        this.delegate = delegate;
        this.wizard = wizard;
        this.handler = ReportHandlerFactory.getHandler();
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        delegate.run(monitor); 
        var source = wizard.getImportFileData().getBpmnSource();
        var version = wizard.getImportFileData().getBpmnSourceVersion();
        handler.traceImport(source, version); 
    }

	@Override
	public List<DiagramFileStore> getFileStoresToOpen() {
		return delegate.getFileStoresToOpen();
	}

	@Override
	public IStatus getStatus() {
		return delegate.getStatus();
	}

	@Override
	public ImportStatusDialogHandler getImportStatusDialogHandler(IStatus status) {
		return delegate.getImportStatusDialogHandler(status);
	}

}
