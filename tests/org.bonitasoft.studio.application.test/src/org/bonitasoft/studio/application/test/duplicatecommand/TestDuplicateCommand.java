/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.test.duplicatecommand;

import java.io.File;
import java.net.URL;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.commands.NewDiagramCommandHandler;
import org.bonitasoft.studio.diagram.custom.operation.DuplicateDiagramOperation;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.WebTemplatesUtil;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ResourceFolder;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.ui.PlatformUI;
import org.junit.Test;

import junit.framework.TestCase;

/**
 * @author Aurelien Pupier
 */
public class TestDuplicateCommand extends TestCase {

    private static DiagramFileStore initialPa;

    private static DiagramFileStore paDuplicated;

    private final DiagramRepositoryStore diagramStore = RepositoryManager.getInstance().getRepositoryStore(
            DiagramRepositoryStore.class);

    /**
     * @throws Exception
     */
    @Test
    public void testDuplicateCommandChangeName() throws Exception {
        final DiagramFileStore pa = new NewDiagramCommandHandler().newDiagram();
        pa.open();
        pa.save(pa.getOpenedEditor());
        final String newProcessLabel = pa.getContent().getName() + "duplicatedInTest";

        final DuplicateDiagramOperation op = new DuplicateDiagramOperation();
        final MainProcess diagram = pa.getContent();
        op.setDiagramToDuplicate(diagram);
        op.setNewDiagramName(newProcessLabel);
        op.setNewDiagramVersion(diagram.getVersion());
        op.run(Repository.NULL_PROGRESS_MONITOR);

        final DiagramFileStore paDuplicated = diagramStore.getDiagram(newProcessLabel, pa.getContent().getVersion());
        assertTrue("Process Artifact not created after duplication", paDuplicated != null);
        assertEquals("Process Name has not been updated correctlty", newProcessLabel, paDuplicated.getContent().getName());
    }

    @Test
    public void testDuplicateCommandChangeVersion() throws Exception {
        final DiagramFileStore pa = new NewDiagramCommandHandler().newDiagram();
        pa.open();

        final String newProcessVersion = pa.getContent().getVersion() + ".2";

        final DuplicateDiagramOperation op = new DuplicateDiagramOperation();
        final MainProcess diagram = pa.getContent();
        op.setDiagramToDuplicate(diagram);
        op.setNewDiagramName(diagram.getName());
        op.setNewDiagramVersion(newProcessVersion);
        op.run(Repository.NULL_PROGRESS_MONITOR);

        final DiagramFileStore paDuplicated = diagramStore.getDiagram(pa.getContent().getName(), newProcessVersion);
        assertTrue("Process Artifact not created after duplication", paDuplicated != null);
        assertEquals("Version has not been updated correctly", newProcessVersion, paDuplicated.getContent().getVersion());
    }

    /*
     * This setup is useful for all the method above.
     * We import one process and duplicate it.
     * Then in each method we test a different part of the imported process
     */
    @Override
    public void setUp() throws Exception {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().saveAllEditors(false);
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(false);
        final DiagramRepositoryStore drs = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        if (initialPa == null) {
            /* import the process for the test */
            final ImportBosArchiveOperation op = new ImportBosArchiveOperation();
            final URL fileURL1 = FileLocator
                    .toFileURL(TestDuplicateCommand.class.getResource("TestDuplicateWithWebTemplates-1.0.bos")); //$NON-NLS-1$
            op.setArchiveFile(FileLocator.toFileURL(fileURL1).getFile());
            op.setCurrentRepository(RepositoryManager.getInstance().getCurrentRepository());
            op.run(new NullProgressMonitor());
            initialPa = drs.getChild("MyDiagram2-1.0.proc");
            final MainProcess mainProcess = initialPa.getContent();
            final String newProcessVersion = mainProcess.getVersion() + ".2";

            final DuplicateDiagramOperation dupOp = new DuplicateDiagramOperation();
            dupOp.setDiagramToDuplicate(mainProcess);
            dupOp.setNewDiagramName(mainProcess.getName());
            dupOp.setNewDiagramVersion(newProcessVersion);
            dupOp.run(Repository.NULL_PROGRESS_MONITOR);

            paDuplicated = diagramStore.getDiagram(mainProcess.getName(), newProcessVersion);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(false);
    }

    @Test
    public void testDuplicateCommandWithConfirmationTemplate() throws Exception {
        assertTrue("Process Artifact not created after duplication", paDuplicated != null);

        final AbstractProcess oldAp = ModelHelper.getAllProcesses(initialPa.getContent()).get(0);
        final String oldPath = oldAp.getConfirmationTemplate().getPath();
        final File oldPathFile = WebTemplatesUtil.getFile(oldPath);

        final AbstractProcess ap = ModelHelper.getAllProcesses(paDuplicated.getContent()).get(0);
        final String newPath = ap.getConfirmationTemplate().getPath();
        final File newPathFile = WebTemplatesUtil.getFile(newPath);

        assertNotNull("Confirmation template not duplicated", newPathFile);
        assertTrue("Confirmation template not duplicated", newPathFile.exists());
        assertFalse("Confirmation template have the same name: " + oldPathFile.getAbsolutePath(),
                oldPathFile.getAbsolutePath().equals(newPathFile.getAbsolutePath()));
        oldPathFile.deleteOnExit();
        newPathFile.deleteOnExit();
    }

    @Test
    public void testDuplicateCommandWithLoginPage() throws Exception {
        assertTrue("Process Artifact not created after duplication", paDuplicated != null);

        final AbstractProcess oldAp = ModelHelper.getAllProcesses(initialPa.getContent()).get(0);
        final String oldPath = oldAp.getLogInPage().getPath();
        final File oldPathFile = WebTemplatesUtil.getFile(oldPath);

        final AbstractProcess ap = ModelHelper.getAllProcesses(paDuplicated.getContent()).get(0);
        final String newPath = ap.getLogInPage().getPath();
        final File newPathFile = WebTemplatesUtil.getFile(newPath);

        assertNotNull("Login page not duplicated", newPathFile);
        assertTrue("Login page not duplicated", newPathFile.exists());
        assertFalse("Login Page is the same: " + oldPathFile,
                oldPathFile.getAbsolutePath().equals(newPathFile.getAbsolutePath()));

        oldPathFile.deleteOnExit();
        newPathFile.deleteOnExit();
    }

    @Test
    public void testDuplicateCommandWithResourceFolder() throws Exception {
        assertTrue("Process Artifact not created after duplication", paDuplicated != null);

        final AbstractProcess oldAp = ModelHelper.getAllProcesses(initialPa.getContent()).get(0);
        final EList<ResourceFolder> resourceFolders = oldAp.getResourceFolders();
        String oldPath = "";
        for (final ResourceFolder resourceFolder : resourceFolders) {
            final String path = resourceFolder.getPath();
            if (path.contains("final")) {
                oldPath = path;
                break;
            }
        }
        assertFalse("final folder should exist in resources in bos archive", oldPath.isEmpty());
        final File oldPathFile = WebTemplatesUtil.getFile(oldPath);

        final AbstractProcess ap = ModelHelper.getAllProcesses(paDuplicated.getContent()).get(0);
        final EList<ResourceFolder> resourceFolders2 = ap.getResourceFolders();
        String newPath = "";
        for (final ResourceFolder resourceFolder : resourceFolders2) {
            final String path = resourceFolder.getPath();
            if (path.contains("final")) {
                newPath = path;
                break;
            }
        }
        final File newPathFile = WebTemplatesUtil.getFile(newPath);

        assertNotNull("Resource folder not duplicated", newPathFile);
        assertTrue("Resource folder not duplicated", newPathFile.exists());
        assertFalse("Resource folder is the same: " + oldPathFile,
                oldPathFile.getAbsolutePath().equals(newPathFile.getAbsolutePath()));

        oldPathFile.deleteOnExit();
        newPathFile.deleteOnExit();
    }

}
