/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
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
package org.bonitasoft.studio.tests.connectors;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.SourceFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.connectors.repository.ConnectorDefRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorImplRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ConnectorSourceRepositoryStore;
import org.bonitasoft.studio.connectors.repository.ImportConnectorArchiveOperation;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 *
 */
public class TestImportConnector{


    private ConnectorDefRepositoryStore cdrs;
    private ConnectorImplRepositoryStore cirs;
    private ConnectorSourceRepositoryStore csrs;

    @Before
    public void setUp() throws Exception {
        cdrs = (ConnectorDefRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(ConnectorDefRepositoryStore.class);
        cirs = (ConnectorImplRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(ConnectorImplRepositoryStore.class);
        csrs = (ConnectorSourceRepositoryStore)RepositoryManager.getInstance().getRepositoryStore(ConnectorSourceRepositoryStore.class);
    }

    @Test
    public void testImportConnector() throws Exception {
        final ImportConnectorArchiveOperation operation = new ImportConnectorArchiveOperation() ;
        URL archiveURL = TestImportConnector.class.getResource("process-cloner-impl-1.0.0.zip");
        final File toImport = new File(FileLocator.toFileURL(archiveURL).getFile());
        operation.setFile(toImport);
        operation.run(new NullProgressMonitor());

        ConnectorDefinition def = cdrs.getDefinition("process-cloner", "1.0.0");
        assertNotNull("Definition not found after import",def);


        ConnectorImplementation impl = cirs.getImplementation("process-cloner-impl", "1.0.0");
        assertNotNull("Implmentation not found after import",impl);


        SourceFileStore sourceFile = (SourceFileStore) csrs.getChild(impl.getImplementationClassname(), true);
        assertNotNull("Source file not found after import",sourceFile);
    }

    @Test
    public void testImportExternalConnector() throws IOException{

        final ImportConnectorArchiveOperation operation = new ImportConnectorArchiveOperation() ;
        URL archiveURL = TestImportConnector.class.getResource("SampleConnectorDef-impl-1.0.0.zip");
        final File toImport = new File(FileLocator.toFileURL(archiveURL).getFile());
        operation.setFile(toImport);
        operation.run(new NullProgressMonitor());

        ConnectorDefinition def = cdrs.getDefinition("SampleConnectorDef", "1.0.0");
        assertNotNull("Definition not found after import",def);


        ConnectorImplementation impl = cirs.getImplementation("SampleConnectorDef-impl", "1.0.0");
        assertNotNull("Implmentation not found after import",impl);


        SourceFileStore sourceFile = (SourceFileStore) csrs.getChild(impl.getImplementationClassname(), true);
        assertNotNull("Source file not found after import",sourceFile);
    }

    @After
    public void tearDown() throws Exception {
        IRepositoryFileStore def = cdrs.getChild(NamingUtils.toConnectorDefinitionFilename("process-cloner", "1.0.0",true), true);
        if(def != null){
            def.delete();
        }
        IRepositoryFileStore impl = cirs.getChild(NamingUtils.toConnectorImplementationFilename("process-cloner-impl", "1.0.0",true), true);
        if(impl != null){
            SourceFileStore sourceFile = (SourceFileStore) csrs.getChild(((ConnectorImplementation)impl.getContent()).getImplementationClassname(), true);
            sourceFile.delete();
            impl.delete();
        }
    }

}
