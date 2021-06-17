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
package org.bonitasoft.studio.tests.actors;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.net.URL;

import org.bonitasoft.studio.common.NamingUtils;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.filestore.SourceFileStore;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.bonitasoft.studio.connector.model.implementation.ConnectorImplementation;
import org.bonitasoft.studio.dependencies.repository.DependencyFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterDefRepositoryStore;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterImplRepositoryStore;
import org.bonitasoft.studio.identity.actors.repository.ActorFilterSourceRepositoryStore;
import org.bonitasoft.studio.identity.actors.repository.ImportActorFilterArchiveOperation;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 * @author Aurelie Zara
 *
 */
public class TestImportActorFilter {


    private ActorFilterDefRepositoryStore cdrs;
    private ActorFilterImplRepositoryStore cirs;
    private ActorFilterSourceRepositoryStore csrs;
    private DependencyRepositoryStore depStore;

    @Before
    public void setUp() throws Exception {
        cdrs = RepositoryManager.getInstance().getRepositoryStore(ActorFilterDefRepositoryStore.class);
        cirs = RepositoryManager.getInstance().getRepositoryStore(ActorFilterImplRepositoryStore.class);
        csrs = RepositoryManager.getInstance().getRepositoryStore(ActorFilterSourceRepositoryStore.class);
        depStore = RepositoryManager.getInstance().getRepositoryStore(DependencyRepositoryStore.class);
    }

    @Test
    public void testImportConnector() throws Exception {
        final ImportActorFilterArchiveOperation operation = new ImportActorFilterArchiveOperation() ;
        URL archiveURL = TestImportActorFilter.class.getResource("test-1.0.0.zip");
        final File toImport = new File(FileLocator.toFileURL(archiveURL).getFile());
        operation.setFile(toImport);
        operation.run(new NullProgressMonitor());

        ConnectorDefinition def = cdrs.getDefinition("test", "1.0.0");
        assertNotNull("Definition not found after import",def);


        ConnectorImplementation impl = cirs.getImplementation("test", "1.0.0");
        assertNotNull("Implmentation not found after import",impl);


        SourceFileStore sourceFile = (SourceFileStore) csrs.getChild(impl.getImplementationClassname(), true);
        assertNotNull("Source file not found after import",sourceFile);
        
        DependencyFileStore depFile = depStore.getChild(NamingUtils.toConnectorImplementationFilename(impl.getImplementationId(), impl.getImplementationVersion(), false)+".jar", true);
        assertNull("Jar file should not be found after import with sources",depFile);
    }

    @After
    public void tearDown() throws Exception {
        IRepositoryFileStore def = cdrs.getChild(NamingUtils.toConnectorDefinitionFilename("test", "1.0.0",true), true);
        if(def != null){
            def.delete();
        }
        IRepositoryFileStore impl = cirs.getChild(NamingUtils.toConnectorImplementationFilename("test", "1.0.0",true), true);
        if(impl != null){
            SourceFileStore sourceFile = (SourceFileStore) csrs.getChild(((ConnectorImplementation)impl.getContent()).getImplementationClassname(), true);
            sourceFile.delete();
            impl.delete();
        }
    }
}
