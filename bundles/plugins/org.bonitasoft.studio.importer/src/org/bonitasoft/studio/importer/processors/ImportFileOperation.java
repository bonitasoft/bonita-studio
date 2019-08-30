/**
 * Copyright (C) 2013-2015 Bonitasoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.importer.processors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.ImporterFactory;
import org.bonitasoft.studio.importer.ImporterPlugin;
import org.bonitasoft.studio.importer.handler.ImportStatusDialogHandler;
import org.bonitasoft.studio.importer.i18n.Messages;
import org.bonitasoft.studio.importer.ui.dialog.SkippableProgressMonitorJobsDialog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;

/**
 * @author Romain Bioteau
 */
public class ImportFileOperation implements IRunnableWithProgress {

    private final ImporterFactory importerFactory;
    private final File fileToImport;
    private final List<DiagramFileStore> fileStoresToOpen;
    private IStatus status;
    private ToProcProcessor processor;
    private SkippableProgressMonitorJobsDialog progressDialog;
    private String repository;

    public List<DiagramFileStore> getFileStoresToOpen() {
        return fileStoresToOpen;
    }

    public ImportFileOperation(final ImporterFactory importerFactory,
            final File fileToImport) {
        this.importerFactory = importerFactory;
        this.fileToImport = fileToImport;
        fileStoresToOpen = new ArrayList<>();
    }

    public ImportFileOperation(final ImporterFactory importerFactory,
            final File fileToImport, final SkippableProgressMonitorJobsDialog progressDialog) {
        this(importerFactory, fileToImport);
        this.progressDialog = progressDialog;
    }

    /*
     * (non-Javadoc)
     * @see org.eclipse.jface.operation.IRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public void run(final IProgressMonitor monitor) throws InvocationTargetException,
            InterruptedException {
        monitor.beginTask(Messages.importProcessProgressDialog, IProgressMonitor.UNKNOWN);
        processor = importerFactory.createProcessor(fileToImport.getName());
        if (repository != null) {
            processor.setRepository(repository);
        } else {
            processor.setRepository(RepositoryManager.getInstance().getCurrentRepository().getName());
        }
        processor.setProgressDialog(progressDialog);
        try {
            processor.createDiagram(fileToImport.toURI().toURL(), monitor);
        } catch (final MalformedURLException e) {
            status = new Status(IStatus.ERROR, ImporterPlugin.PLUGIN_ID, e.getMessage(), e);
            throw new InvocationTargetException(e, e.getMessage());
        } catch (final Exception e) {
            status = new Status(IStatus.ERROR, ImporterPlugin.PLUGIN_ID, e.getMessage(), e);
            throw new InvocationTargetException(e, e.getMessage());
        }
        //handleErrors(processor);
        addFileStoresToOpen(processor);
        status = processor.getStatus();
    }

    protected void addFileStoresToOpen(final ToProcProcessor processor)
            throws InvocationTargetException {
        for (final IRepositoryFileStore fStore : processor.getDiagramFileStoresToOpen()) {
            if (fStore instanceof DiagramFileStore) {
                fileStoresToOpen.add((DiagramFileStore) fStore);
            }
        }
        if (processor.getResources() != null) {
            for (final File f : processor.getResources()) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(f);
                    final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance().getCurrentRepository()
                            .getRepositoryStore(DiagramRepositoryStore.class);
                    final DiagramFileStore fileStore = diagramStore.importInputStream(f.getName(), fis);
                    if (fileStore instanceof DiagramFileStore) {
                        fileStoresToOpen.add(fileStore);
                    }
                    f.delete();
                } catch (final FileNotFoundException e) {
                    status = new Status(IStatus.ERROR, ImporterPlugin.PLUGIN_ID, e.getMessage(), e);
                    throw new InvocationTargetException(e);
                } finally {
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (final IOException e) {
                            throw new InvocationTargetException(e);
                        }
                    }
                }
            }
        }
    }

    public IStatus getStatus() {
        return status;
    }

    public ImportStatusDialogHandler getImportStatusDialogHandler(final IStatus status) {
        return processor.getImportStatusDialogHandler(status);
    }

    public void setRepositroy(String repository) {
        this.repository = repository;
    }

}
