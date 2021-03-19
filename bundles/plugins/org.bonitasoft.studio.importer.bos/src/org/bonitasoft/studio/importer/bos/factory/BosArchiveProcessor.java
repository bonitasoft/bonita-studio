/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.importer.bos.factory;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.importer.bos.status.BosImportStatusDialogHandler;
import org.bonitasoft.studio.importer.handler.ImportStatusDialogHandler;
import org.bonitasoft.studio.importer.processors.ToProcProcessor;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;

/**
 * @author Romain Bioteau
 */
public class BosArchiveProcessor extends ToProcProcessor {

    private ImportBosArchiveOperation operation;
    private RepositoryAccessor repositoryAccessor;

    public BosArchiveProcessor() {
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
    }

    @Override
    public File createDiagram(final URL sourceFileURL, final IProgressMonitor progressMonitor) throws Exception {
        final File archiveFile = new File(URI.decode(sourceFileURL.getFile()));
        operation = createOperation(archiveFile);
        operation.run(progressMonitor);
        return null;
    }

    protected ImportBosArchiveOperation createOperation(final File archiveFile) {
        operation = new ImportBosArchiveOperation(repositoryAccessor);
        operation.setProgressDialog(progressDialog);
        operation.setArchiveFile(archiveFile.getAbsolutePath());
        operation.setCurrentRepository(repositoryAccessor.getRepository(getRepository()));
        return operation;
    }

    @Override
    public IStatus getStatus() {
        return operation.getStatus();
    }

    @Override
    public List<File> getResources() {
        return null;
    }

    @Override
    public List<IRepositoryFileStore> getDiagramFileStoresToOpen() {
        return operation.getFileStoresToOpen();
    }

    @Override
    public String getExtension() {
        return ".bos";
    }

    @Override
    public ImportStatusDialogHandler getImportStatusDialogHandler(final IStatus status) {
        return new BosImportStatusDialogHandler(status,
                RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class));
    }

}
