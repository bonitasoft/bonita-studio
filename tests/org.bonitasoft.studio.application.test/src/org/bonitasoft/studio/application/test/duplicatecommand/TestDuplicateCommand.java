/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.application.test.duplicatecommand;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.bonitasoft.studio.application.actions.SaveProcessAsCommand;
import org.bonitasoft.studio.common.OpenNameAndVersionForDiagramDialog.ProcessesNameVersion;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.diagram.custom.commands.NewDiagramCommandHandler;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.diagram.custom.repository.WebTemplatesUtil;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ResourceFolder;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.ui.PlatformUI;
import org.junit.Test;

/**
 * @author Aurelien Pupier
 */
public class TestDuplicateCommand extends TestCase {

    private static DiagramFileStore initialPa;

    private static DiagramFileStore paDuplicated;

    private final DiagramRepositoryStore diagramStore = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(
            DiagramRepositoryStore.class);

    /**
     * @throws Exception
     */
    @Test
    public void testDuplicateCommandChangeName() throws Exception {

        final NewDiagramCommandHandler npch = new NewDiagramCommandHandler();
        npch.execute(null);
        DiagramFileStore pa = npch.getNewDiagramFileStore();
        pa.save(pa.getOpenedEditor());
        final String newProcessLabel = pa.getContent().getName() + "duplicatedInTest";
        new SaveProcessAsCommand().duplicate(pa.getContent(), newProcessLabel, pa.getContent().getVersion(), new ArrayList<ProcessesNameVersion>());
        DiagramFileStore paDuplicated = diagramStore.getDiagram(newProcessLabel, pa.getContent().getVersion());
        assertTrue("Process Artifact not created after duplication", paDuplicated != null);
        assertEquals("Process Name has not been updated correctlty", newProcessLabel, paDuplicated.getContent().getName());
    }

    @Test
    public void testDuplicateCommandChangeVersion() throws Exception {

        final NewDiagramCommandHandler npch = new NewDiagramCommandHandler();
        npch.execute(null);
        DiagramFileStore pa = npch.getNewDiagramFileStore();

        final String newProcessVersion = pa.getContent().getVersion() + ".2";
        new SaveProcessAsCommand().duplicate(pa.getContent(), pa.getContent().getName(), newProcessVersion, new ArrayList<ProcessesNameVersion>());
        DiagramFileStore paDuplicated = diagramStore.getDiagram(pa.getContent().getName(), newProcessVersion);
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
        DiagramRepositoryStore drs = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        if (initialPa == null) {
            /* import the process for the test */
            ImportBosArchiveOperation op = new ImportBosArchiveOperation();
            URL fileURL1 = FileLocator.toFileURL(TestDuplicateCommand.class.getResource("TestDuplicateWithWebTemplates-1.0.bos")); //$NON-NLS-1$
            op.setArchiveFile(FileLocator.toFileURL(fileURL1).getFile());
            op.setCurrentRepository(RepositoryManager.getInstance().getCurrentRepository());
            op.run(new NullProgressMonitor());
            initialPa = drs.getChild("MyDiagram2-1.0.proc");
            final MainProcess mainProcess = initialPa.getContent();
            final String newProcessVersion = mainProcess.getVersion() + ".2";
            new SaveProcessAsCommand().duplicate(mainProcess, mainProcess.getName(), newProcessVersion, new ArrayList<ProcessesNameVersion>());
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

        AbstractProcess oldAp = ModelHelper.getAllProcesses(initialPa.getContent()).get(0);
        String oldPath = oldAp.getConfirmationTemplate().getPath();
        File oldPathFile = WebTemplatesUtil.getFile(oldPath);

        AbstractProcess ap = ModelHelper.getAllProcesses(paDuplicated.getContent()).get(0);
        String newPath = ap.getConfirmationTemplate().getPath();
        File newPathFile = WebTemplatesUtil.getFile(newPath);

        assertNotNull("Confirmation template not duplicated", newPathFile);
        assertTrue("Confirmation template not duplicated", newPathFile.exists());
        assertFalse("Confirmation template have the same name: " + oldPathFile.getAbsolutePath(),
                oldPathFile.getAbsolutePath().equals(newPathFile.getAbsolutePath()));
    }

    @Test
    public void testDuplicateCommandWithLoginPage() throws Exception {
        assertTrue("Process Artifact not created after duplication", paDuplicated != null);

        AbstractProcess oldAp = ModelHelper.getAllProcesses(initialPa.getContent()).get(0);
        String oldPath = oldAp.getLogInPage().getPath();
        File oldPathFile = WebTemplatesUtil.getFile(oldPath);

        AbstractProcess ap = ModelHelper.getAllProcesses(paDuplicated.getContent()).get(0);
        String newPath = ap.getLogInPage().getPath();
        File newPathFile = WebTemplatesUtil.getFile(newPath);

        assertNotNull("Login page not duplicated", newPathFile);
        assertTrue("Login page not duplicated", newPathFile.exists());
        assertFalse("Login Page is the same: " + oldPathFile, oldPathFile.getAbsolutePath().equals(newPathFile.getAbsolutePath()));
    }

    @Test
    public void testDuplicateCommandWithResourceFolder() throws Exception {
        assertTrue("Process Artifact not created after duplication", paDuplicated != null);

        AbstractProcess oldAp = ModelHelper.getAllProcesses(initialPa.getContent()).get(0);
        final EList<ResourceFolder> resourceFolders = oldAp.getResourceFolders();
        String oldPath = "";
        for (ResourceFolder resourceFolder : resourceFolders) {
            final String path = resourceFolder.getPath();
            if (path.contains("final")) {
                oldPath = path;
                break;
            }
        }
        assertFalse("final folder should exist in resources in bos archive", oldPath.isEmpty());
        File oldPathFile = WebTemplatesUtil.getFile(oldPath);

        AbstractProcess ap = ModelHelper.getAllProcesses(paDuplicated.getContent()).get(0);
        final EList<ResourceFolder> resourceFolders2 = ap.getResourceFolders();
        String newPath = "";
        for (ResourceFolder resourceFolder : resourceFolders2) {
            final String path = resourceFolder.getPath();
            if (path.contains("final")) {
                newPath = path;
                break;
            }
        }
        File newPathFile = WebTemplatesUtil.getFile(newPath);

        assertNotNull("Resource folder not duplicated", newPathFile);
        assertTrue("Resource folder not duplicated", newPathFile.exists());
        assertFalse("Resource folder is the same: " + oldPathFile, oldPathFile.getAbsolutePath().equals(newPathFile.getAbsolutePath()));
    }

}
