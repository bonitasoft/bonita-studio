package org.bonitasoft.studio.importer.bos.operation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.importer.bos.model.BosArchive;
import org.bonitasoft.studio.importer.bos.model.ImportArchiveModel;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.operation.IRunnableWithProgress;

public class ParseBosArchiveOperation implements IRunnableWithProgress {

    private IStatus status;
    private final File archiveFile;
    private ImportArchiveModel archiveModel;
    protected AbstractRepository repository;

    public ParseBosArchiveOperation(File archiveFile, AbstractRepository repository) {
        Objects.requireNonNull(archiveFile);
        Assert.isTrue(archiveFile.exists());
        Objects.requireNonNull(repository);
        this.archiveFile = archiveFile;
        this.repository = repository;
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        final boolean isOpen = repository.getProject().isOpen();
        try {
            repository.getProject().open(monitor);
        } catch (CoreException e1) {
            throw new InvocationTargetException(e1);
        }
        final ImportConflictsChecker parser = new ImportConflictsChecker(repository);
        try {
            archiveModel = parser.checkConflicts(new BosArchive(archiveFile), monitor);
        } catch (final IOException e) {
            throw new InvocationTargetException(e);
        } finally {
            if (!isOpen) {
                try {
                    repository.getProject().close(monitor);
                } catch (CoreException e) {
                    throw new InvocationTargetException(e);
                }
            }
            monitor.done();
        }
    }

    public IStatus getStatus() {
        return status;
    }

    public ImportArchiveModel getImportArchiveModel() {
        return archiveModel;
    }
}
