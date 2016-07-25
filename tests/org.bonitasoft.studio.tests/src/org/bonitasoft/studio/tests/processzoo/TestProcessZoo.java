/**
 * Copyright (C) 2009-2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.tests.processzoo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.dependencies.repository.DependencyRepositoryStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.command.RunProcessCommand;
import org.bonitasoft.studio.engine.operation.ProcessSelector;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.configuration.Fragment;
import org.bonitasoft.studio.model.configuration.FragmentContainer;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.ErrorEditorPart;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mickael Istria
 *
 */
public class TestProcessZoo {

    @Before
    public void setUp() throws Exception {
        BOSEngineManager.getInstance().start();
    }

    @Test
    public void testProcesses() throws Throwable {
        int foundProcesses = 0;
        final List<URL> entries = getEntries();
        for (URL url : entries) {
            System.err.println(url.getFile());
            url = FileLocator.toFileURL(url);
            try {
                applyTestsOnProcess(url);
            } catch (final Exception ex) {
                BonitaStudioLog.error(ex);
                throw new Exception("Error on file: " + url.toString(), ex);
            }
            foundProcesses++;
        }
        assertNotSame("No process was tested", 0, foundProcesses);
    }

    protected void applyTestsOnProcess(final URL url) throws Throwable {
        final int beforeImport = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getEditorReferences().length;
        final File file = new File(FileLocator.toFileURL(url).getFile());
        final ImportBosArchiveOperation ibao = new ImportBosArchiveOperation();
        ibao.setArchiveFile(file.getAbsolutePath());
        ibao.setCurrentRepository(RepositoryManager.getInstance().getCurrentRepository());
        ibao.run(Repository.NULL_PROGRESS_MONITOR);

        for (final IRepositoryFileStore f : ibao.getFileStoresToOpen()) {
            f.open();
        }

        final IEditorPart editor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        if (editor instanceof ErrorEditorPart) {
            final ErrorEditorPart errorEditor = (ErrorEditorPart) editor;
            final IStatus error = (IStatus) ErrorEditorPart.class.getField("error").get(errorEditor);
            throw error.getException();
        }
        final ProcessDiagramEditor processEditor = (ProcessDiagramEditor) editor;
        /* for mickeyprocessses .proc will overrided (as it is sorted) when they come so need */
        if (url.toString().contains("mickeyProcesses/") && url.toString().contains(".proc")) {
            assertEquals("Import should have opened another process editor but colsed another one for " + url, beforeImport, PlatformUI.getWorkbench()
                    .getActiveWorkbenchWindow().getActivePage().getEditorReferences().length);
        } else {
            assertEquals("Import should have opened another process editor for " + url, beforeImport + 1, PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                    .getActivePage().getEditorReferences().length);
        }
        final AbstractProcess diagram = (AbstractProcess) processEditor.getDiagramEditPart().resolveSemanticElement();
        if (file.getAbsolutePath().endsWith(".bos")) {// Check unresolved dependencies for BAR Files
            final DependencyRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(
                    DependencyRepositoryStore.class);
            for (final Element element : diagram.getElements()) {
                if (element instanceof AbstractProcess) {
                    for (final Configuration config : ((AbstractProcess) element).getConfigurations()) {
                        for (final FragmentContainer fragmentContainer : config.getProcessDependencies()) {
                            for (final Fragment fragment : fragmentContainer.getFragments()) {
                                final String lib = fragment.getValue();
                                if (lib.endsWith(DependencyRepositoryStore.JAR_EXT)) {
                                    assertNotNull("A lib is unresolved " + lib, store.getChild(lib));
                                }
                            }
                        }
                    }
                }
            }
        }

        final RunProcessCommand runProcessCommand = new RunProcessCommand(true);
        runProcessCommand.execute(ProcessSelector.createExecutionEvent((AbstractProcess) diagram.getElements().get(0)));
        assertThat(runProcessCommand.getUrl()).isNotNull();
        assertNotNull("There should be an application deployed and running for " + url, runProcessCommand.getUrl().getContent());
    }

    /* attempt to be able to run the test locally */
    protected List<URL> getEntries() {
        final List<URL> res = new ArrayList<URL>();
        final String[] nameForEntry = new String[] {
                "toqa/Buy a NEW mini-6.4.bos",
                "BPMN-ShowcaseToTestDynamicLabels-1.0.bos",
                "testonsLesValidateurs-1.0.bos"
                // Deactivate previous processes import
                // "bug1657/Create_User_Sequence--1.1.bar",
                // "My_Bar_Process_1.1.bar",
                // "BPMN_Showcase_1.0.proc",//test bug 1642
                // "testonslesValidators.bar", //test import of validators from .bar doesn't crash
                // "bonita4/approvalWorkflow.bar",
                // "bonita4/carpool.bar",
                // "bonita4/webSale.bar",
                // "mickeyProcesses/1_Enter_order_1.3.bar",
                // "mickeyProcesses/2_Order_Car_from_Factory_1.0.bar",
                // //"mickeyProcesses/Bonita_New_Car_Sales_1.0.proc",
                // "previewExamples/Request_For_Advance_Payment_1.0.proc",
                // //"mickeyProcesses/Enter_order_1.3.proc",
                // "previewExamples/Web_Purchase_1.0.proc",
                // "Travel_request_approval_process--1.0.proc",
                // "mickeyProcesses/3_Bonita_New_Car_Sales_1.0.bar",
                // // "mickeyProcesses/Order_Car_from_Factory_1.0.proc"
                // "JavaConnector-5.4.bar",
                // "bug4181/Trip_Request--1.0.bar",
                // "TestProvidedLibsMigration--1.0.bar",
                // "test_boundary_signal--1.0.proc",
                // "Business_trip_application_process--1.0.bar"
        };

        for (final String name : nameForEntry) {
            res.add(this.getClass().getResource(name));
        }
        return res;
    }

    /**
     * @return the name package separate with "/" instead of "." where to search the process to test.
     */
    protected String getPackage() {
        return "/org/bonitasoft/studio/tests/processzoo";
    }

    @After
    public void tearDown() throws Exception {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().closeAllEditors(false);
    }

}
