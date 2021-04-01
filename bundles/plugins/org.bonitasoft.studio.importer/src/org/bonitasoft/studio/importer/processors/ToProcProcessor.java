/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.processors;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.List;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.importer.handler.DefaultImportStatusDialogHandler;
import org.bonitasoft.studio.importer.handler.ImportStatusDialogHandler;
import org.bonitasoft.studio.ui.dialog.SkippableProgressMonitorJobsDialog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * @author Romain Bioteau
 */
public abstract class ToProcProcessor {

    protected String resourceName;
    protected String repository;
    protected SkippableProgressMonitorJobsDialog progressDialog;

    public abstract File createDiagram(URL sourceFileURL, IProgressMonitor progressMonitor) throws Exception;

    public void setResourceName(final String resourceName) {
        this.resourceName = resourceName;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    /**
     * @deprecated Use getDiagramFileStoresToOpen instead
     */
    @Deprecated
    public abstract List<File> getResources();

    /**
     * Default implementation returns an empty list
     *
     * @return
     */
    public List<IRepositoryFileStore> getDiagramFileStoresToOpen() {
        return Collections.emptyList();
    }

    public abstract String getExtension();

    public void setProgressDialog(final SkippableProgressMonitorJobsDialog progressDialog) {
        this.progressDialog = progressDialog;
    }

    public IStatus getStatus() {
        return Status.OK_STATUS;
    }

    public ImportStatusDialogHandler getImportStatusDialogHandler(final IStatus status) {
        return new DefaultImportStatusDialogHandler(status);
    }

}
