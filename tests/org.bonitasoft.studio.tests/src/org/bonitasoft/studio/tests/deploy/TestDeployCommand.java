/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.deploy;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;

import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.api.ProcessManagementAPI;
import org.bonitasoft.engine.bpm.process.ProcessDefinition;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfo;
import org.bonitasoft.engine.bpm.process.ProcessDeploymentInfoCriterion;
import org.bonitasoft.engine.bpm.process.ProcessInstanceSearchDescriptor;
import org.bonitasoft.engine.search.Order;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.command.RunProcessCommand;
import org.bonitasoft.studio.engine.operation.ProcessSelector;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.util.test.async.TestAsyncThread;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.ui.PlatformUI;
import org.junit.Test;

/**
 * @author Aurelien Pupier
 */
public class TestDeployCommand {

    /**
     * Used to check bug 1965
     *
     * @throws Exception
     */
    @Test
    public void testDeployCommandWithSubProcessLoop() throws Exception {
        /* import the two process for the test */
        final ProcessDiagramEditor processEditor = importBos("ProcessForSubProcessLoopTest-1-1.0.bos");
        final MainProcess mainProcess = (MainProcess) processEditor.getDiagramEditPart().resolveSemanticElement();
        importBos("ProcessForSubProcessLoopTest-2-1.0.bos");

        /* And deploy it twice */
        final AbstractProcess selectedProcess = (AbstractProcess) mainProcess.getElements().get(0);
        final RunProcessCommand runProcessCommand = new RunProcessCommand(true);
        runProcessCommand.execute(ProcessSelector.createExecutionEvent(selectedProcess));
        runProcessCommand.execute(ProcessSelector.createExecutionEvent(selectedProcess));
    }

    /**
     * used to check fix 2204
     *
     * @throws Exception
     */
    @Test
    public void testDeployAfterRenamedOfParentProcess() throws Exception {
        /* Import a base process for the test */
        final ProcessDiagramEditor processEditor = importBos("ProcessForTestBug2204.bos");
        final MainProcess mainProcess = (MainProcess) processEditor.getDiagramEditPart().resolveSemanticElement();

        /* Run a first time */
        final AbstractProcess selectedProcess = (AbstractProcess) mainProcess.getElements().get(0);
        final RunProcessCommand runProcessCommand = new RunProcessCommand(true);
        runProcessCommand.execute(ProcessSelector.createExecutionEvent(selectedProcess));

        /* Rename the parent */
        final Pool parentProcess = (Pool) mainProcess.getElements().get(0);
        TransactionalEditingDomain domain = GMFEditingDomainFactory.getInstance()
                .getEditingDomain(parentProcess.eResource().getResourceSet());
        if (domain == null) {
            domain = TransactionUtil.getEditingDomain(parentProcess);
        }
        if (domain == null) {
            domain = GMFEditingDomainFactory.getInstance().createEditingDomain();
        }
        final CompoundCommand cc = new CompoundCommand();
        cc.append(SetCommand.create(domain, parentProcess, ProcessPackage.eINSTANCE.getElement_Name(), "ParentRenamed"));
        domain.getCommandStack().execute(cc);
        processEditor.doSave(Repository.NULL_PROGRESS_MONITOR);
        /* Retry to deploy */
        runProcessCommand.execute(null);

        APISession session = null;
        try {
            session = BOSEngineManager.getInstance().loginDefaultTenant(Repository.NULL_PROGRESS_MONITOR);
            final ProcessManagementAPI processAPI = BOSEngineManager.getInstance().getProcessAPI(session);
            final int nbProcess = (int) processAPI.getNumberOfProcessDeploymentInfos();
            final List<ProcessDeploymentInfo> infos = processAPI.getProcessDeploymentInfos(0, nbProcess,
                    ProcessDeploymentInfoCriterion.DEFAULT);
            boolean found = false;
            for (final ProcessDeploymentInfo info : infos) {
                if (info.getName().equals(parentProcess.getName()) && info.getVersion().equals(parentProcess.getVersion())) {
                    found = true;
                }
            }
            assertTrue("Process not found", found);
        } catch (final Exception e) {
            fail();
        } finally {
            if (session != null) {
                BOSEngineManager.getInstance().logoutDefaultTenant(session);
            }
        }
    }

    @Test
    public void testTwiceDeploymentWithCallActiviesInstances() throws Exception {
        final ProcessDiagramEditor processEditor = importBos("TestTwiceDeployWithSubProc-1.0.bos");
        final MainProcess mainProcess = (MainProcess) processEditor.getDiagramEditPart().resolveSemanticElement();
        runAndStartProcess(mainProcess, "ParentTestTwiceDeployWithSubProc", "1.0");
        runAndStartProcess(mainProcess, "ChildTestTwiceDeployWithSubProc", "1.0");
        runAndStartProcess(mainProcess, "ParentTestTwiceDeployWithSubProc", "1.0");
        runAndStartProcess(mainProcess, "ChildTestTwiceDeployWithSubProc", "1.0");
    }

    protected void runAndStartProcess(final MainProcess mainProcess, final String processName, final String processVersion)
            throws Exception {
        Pool toDeploy = null;
        for (final Element e : mainProcess.getElements()) {
            if (e instanceof Pool && e.getName().equals(processName) && ((Pool) e).getVersion().equals(processVersion)) {
                toDeploy = (Pool) e;
            }
        }

        final RunProcessCommand runProcessCommand1 = new RunProcessCommand(true);
        runProcessCommand1.execute(ProcessSelector.createExecutionEvent(toDeploy));

        final APISession session = BOSEngineManager.getInstance().loginDefaultTenant(Repository.NULL_PROGRESS_MONITOR);
        final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
        final long processId = processApi.getProcessDefinitionId(processName, processVersion);
        final ProcessDefinition processDef = processApi.getProcessDefinition(processId);
        assertNotNull(processDef);
        processApi.startProcess(processId);
        new TestAsyncThread(5, 500) {

            @Override
            public boolean isTestGreen() throws Exception {
                final SearchOptions searchOptions = new SearchOptionsBuilder(0, 10)
                        .filter(ProcessInstanceSearchDescriptor.PROCESS_DEFINITION_ID, processId)
                        .sort(ProcessInstanceSearchDescriptor.ID, Order.ASC).done();
                return processApi.searchProcessInstances(searchOptions).getCount() > 0;
            }
        }.evaluate();
    }

    private ProcessDiagramEditor importBos(final String processResourceName)
            throws IOException, InvocationTargetException, InterruptedException {
        RepositoryAccessor repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        final ImportBosArchiveOperation op = new ImportBosArchiveOperation(repositoryAccessor);
        final URL fileURL = FileLocator.toFileURL(TestDeployCommand.class.getResource(processResourceName));
        op.setArchiveFile(FileLocator.toFileURL(fileURL).getFile());
        op.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        op.run(new NullProgressMonitor());
        for (final IRepositoryFileStore f : op.getFileStoresToOpen()) {
            f.open();
        }
        return (ProcessDiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
                .getActiveEditor();
    }

}
