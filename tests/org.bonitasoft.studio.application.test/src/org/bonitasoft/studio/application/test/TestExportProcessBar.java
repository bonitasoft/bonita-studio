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
package org.bonitasoft.studio.application.test;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import junit.framework.TestCase;

import org.bonitasoft.studio.common.platform.tools.PlatformUtil;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.engine.operation.ExportBarOperation;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * @author Aurelien Pupier
 */
public class TestExportProcessBar extends TestCase {

    public void testExportProcessBarWithAttachmentAndSeveralPool() throws Exception {
        /* Import the processus */
        URL url = getClass().getResource("TestExportProcessBarWithDocument-1.0.bos");
        url = FileLocator.toFileURL(url);
        final File barToImport = new File(url.getFile());
        final ImportBosArchiveOperation op = new ImportBosArchiveOperation();
        op.setArchiveFile(barToImport.getAbsolutePath());
        op.setCurrentRepository(RepositoryManager.getInstance().getCurrentRepository());
        op.run(Repository.NULL_PROGRESS_MONITOR);
        /* Retrieve the AbstractProcess */
        final DiagramRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        final DiagramFileStore diagram = store.getDiagram("TestExportProcessBarWithDocument", "1.0");
        assertNotNull(diagram);
        final MainProcess diagramElement = diagram.getContent();
        assertNotNull(diagramElement);
        final AbstractProcess proc = (AbstractProcess) diagramElement.getElements().get(0);

        /* Export to the specified folder */
        final File targetFolder = new File(System.getProperty("java.io.tmpdir") + File.separator + "testExportBar");
        PlatformUtil.delete(targetFolder, Repository.NULL_PROGRESS_MONITOR);
        targetFolder.mkdirs();
        final ExportBarOperation ebo = new ExportBarOperation();
        ebo.addProcessToDeploy(proc);
        ebo.setTargetFolder(targetFolder.getAbsolutePath());
        ebo.setConfigurationId("Local");
        ebo.run(new NullProgressMonitor());
        assertTrue("Export in bar has failed.", ebo.getStatus().isOK());

        final File generatedBarFile = ebo.getGeneratedBars().get(0);

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

    public void testExportProcessBarApplicationResources() throws Exception {
        /* Import the processus */
        URL url = getClass().getResource("TestExportBarWithApplicationResources-1.0.bos");
        url = FileLocator.toFileURL(url);
        final File barToImport = new File(url.getFile());

        final ImportBosArchiveOperation op = new ImportBosArchiveOperation();
        op.setArchiveFile(barToImport.getAbsolutePath());
        op.setCurrentRepository(RepositoryManager.getInstance().getCurrentRepository());
        op.run(Repository.NULL_PROGRESS_MONITOR);
        assertTrue(op.getStatus().isOK());
        /* Retrieve the AbstractProcess */
        final DiagramRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        final DiagramFileStore diagram = store.getDiagram("TestExportBarWithApplicationResources", "1.0");
        assertNotNull(diagram);
        final MainProcess diagramElement = diagram.getContent();
        assertNotNull(diagramElement);
        final AbstractProcess proc = (AbstractProcess) diagramElement.getElements().get(0);

        /* Export to the specified folder */
        final File targetFolder = new File(System.getProperty("java.io.tmpdir") + File.separator + "testExportBar");
        PlatformUtil.delete(targetFolder, Repository.NULL_PROGRESS_MONITOR);
        targetFolder.mkdirs();

        final ExportBarOperation ebo = new ExportBarOperation();
        ebo.addProcessToDeploy(proc);
        ebo.setTargetFolder(targetFolder.getAbsolutePath());
        ebo.setConfigurationId("Local");
        ebo.run(new NullProgressMonitor());
        assertTrue("Export in bar has failed.", ebo.getStatus().isOK());

        final File generatedBarFile = ebo.getGeneratedBars().get(0);

        /* Check that attachment is in the bar */
        final ZipInputStream generatedBarStream = new ZipInputStream(new FileInputStream(generatedBarFile));
        ZipEntry barEntry;
        boolean formsFolderExists = false;
        boolean formsXmlExists = false;
        boolean resourcesExists = false;
        boolean templateExists = false;
        boolean dependenciesExists = false;
        boolean validatorExists = false;
        while ((barEntry = generatedBarStream.getNextEntry()) != null) {
            if (barEntry.getName().contains("resources/forms/")) {
                formsFolderExists = true;
            }
            if (barEntry.getName().contains("resources/forms/forms.xml")) {
                formsXmlExists = true;
            }
            if (barEntry.getName().contains("resources/forms/html")) {
                templateExists = true;
            }
            if (barEntry.getName().contains("resources/forms/resources/application/css")) {
                resourcesExists = true;
            }
            if (barEntry.getName().contains("resources/forms/validators")) {
                validatorExists = true;
            }
            if (barEntry.getName().contains("resources/forms/lib")) {
                dependenciesExists = true;
            }

        }
        generatedBarStream.close();
        assertTrue("forms folder not found in BAR", formsFolderExists);
        assertTrue("forms.xml folder not found in BAR", formsXmlExists);
        assertTrue("resources not found in BAR", resourcesExists);
        assertTrue("template folder not found in BAR", templateExists);
        assertTrue("validator folder not found in BAR", validatorExists);
        assertTrue("dependencies folder not found in BAR", dependenciesExists);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        final File targetFolder = new File(System.getProperty("java.io.tmpdir") + File.separator + "testExportBar");
        PlatformUtil.delete(targetFolder, Repository.NULL_PROGRESS_MONITOR);
    }

}
