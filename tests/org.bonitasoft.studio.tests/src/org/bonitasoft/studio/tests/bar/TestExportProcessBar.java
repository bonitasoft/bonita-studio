/**
 * Copyright (C) 2010-2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.bar;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.operation.ExportBarOperation;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestExportProcessBar  {

    @Before
    public void setUp() throws Exception{
        BOSEngineManager.getInstance().start();
    }
    
    @Test
    public void testExportProcessBarWithAttachmentAndSeveralPool() throws Exception {
        RepositoryAccessor repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        

        /* Import the processus */
        URL url = getClass().getResource("TestExportProcessBarWithDocument-1.0.bos");
        url = FileLocator.toFileURL(url);
        final File barToImport = new File(url.getFile());
        final ImportBosArchiveOperation op = new ImportBosArchiveOperation(repositoryAccessor);
        op.setArchiveFile(barToImport.getAbsolutePath());
        op.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        op.run(AbstractRepository.NULL_PROGRESS_MONITOR);
        /* Retrieve the AbstractProcess */
        final DiagramRepositoryStore store = repositoryAccessor.getRepositoryStore(DiagramRepositoryStore.class);
        final DiagramFileStore diagram = store.getDiagram("TestExportProcessBarWithDocument", "1.0");
        assertNotNull(diagram);
        final MainProcess diagramElement = diagram.getContent();
        assertNotNull(diagramElement);
        final AbstractProcess proc = (AbstractProcess) diagramElement.getElements().get(0);

        /* Export to the specified folder */
        final File targetFolder = new File(System.getProperty("java.io.tmpdir") + File.separator + "testExportBar");
        PlatformUtil.delete(targetFolder, AbstractRepository.NULL_PROGRESS_MONITOR);
        targetFolder.mkdirs();
        final ExportBarOperation ebo = new ExportBarOperation();
        ebo.addProcessToDeploy(proc);
        ebo.setTargetFolder(targetFolder.getAbsolutePath());
        ebo.setConfigurationId("Local");
        ebo.run(new NullProgressMonitor());
        assertTrue("Export in bar has failed.", ebo.getStatus().isOK());

        final File generatedBarFile = ebo.getGeneratedBars().get(0);

        targetFolder.deleteOnExit();
        generatedBarFile.deleteOnExit();

        /* Check that attachment is in the bar */
        final ZipInputStream generatedBarStream = new ZipInputStream(new FileInputStream(generatedBarFile));
        ZipEntry barEntry;
        while ((barEntry = generatedBarStream.getNextEntry()) != null) {
            if (barEntry.getName().contains("documents")) {
                generatedBarStream.close();
                return;
            }
        }
        generatedBarStream.close();
        fail("There is no attachment in the genrated bar.");
    }


    @After
    public void tearDown() throws Exception {
        final File targetFolder = new File(System.getProperty("java.io.tmpdir") + File.separator + "testExportBar");
        PlatformUtil.delete(targetFolder, AbstractRepository.NULL_PROGRESS_MONITOR);
    }

}
