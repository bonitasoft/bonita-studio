/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.importer.bar.factory;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.importer.processors.ToProcProcessor;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;

/**
 * @author Romain Bioteau
 *
 */
public class BosArchiveProcessor extends ToProcProcessor {



    private ImportBosArchiveOperation operation;

	public BosArchiveProcessor(String resourceName) {

    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.importer.ToProcProcessor#createDiagram(java.net.URL, org.eclipse.core.runtime.IProgressMonitor)
     */
    @Override
    public File createDiagram(URL sourceFileURL, IProgressMonitor progressMonitor) throws Exception {
        final File archiveFile = new File(URI.decode(sourceFileURL.getFile())) ;
        operation = new ImportBosArchiveOperation() ;
        operation.setArchiveFile(archiveFile.getAbsolutePath());
        operation.run(progressMonitor) ;
        return null ;
    }



    /* (non-Javadoc)
     * @see org.bonitasoft.studio.importer.ToProcProcessor#getResources()
     */
    @Override
    public List<File> getResources() {
        return null;
    }
    
    @Override
    public List<IRepositoryFileStore> getDiagramFileStoresToOpen() {
    	return operation.getFileStoresToOpen();
    }

    /* (non-Javadoc)
     * @see org.bonitasoft.studio.importer.ToProcProcessor#getExtension()
     */
    @Override
    public String getExtension() {
        return ".bos";
    }

}