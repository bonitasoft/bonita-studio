/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.subprocess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.List;

import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.flownode.HumanTaskInstance;
import org.bonitasoft.engine.bpm.process.ProcessDefinition;
import org.bonitasoft.engine.exception.SearchException;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.session.InvalidSessionException;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.AddDependencyOperation;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.command.RunProcessCommand;
import org.bonitasoft.studio.engine.operation.ProcessSelector;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.tests.util.Await;
import org.bonitasoft.studio.tests.util.EngineAPIUtil;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.ui.PlatformUI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Mickael Istria
 * @author Aurelie Zara
 */
public class TestSubprocess {

    private HumanTaskInstance newTask;

    private APISession session;

    private DiagramRepositoryStore store;

    private RepositoryAccessor repositoryAccessor;

    @Before
    public void setUp() throws Exception {
        session = BOSEngineManager.getInstance().loginDefaultTenant(AbstractRepository.NULL_PROGRESS_MONITOR);
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        store = RepositoryManager.getInstance()
                .getRepositoryStore(DiagramRepositoryStore.class);
        AddDependencyOperation addDependencyOperation = new AddDependencyOperation("org.bonitasoft.connectors",
                "bonita-connector-groovy", "1.1.3");
        addDependencyOperation.run(AbstractRepository.NULL_PROGRESS_MONITOR);
    }

    @After
    public void tearDown() {
        BOSEngineManager.getInstance().logoutDefaultTenant(session);
    }

    @Test
    public void testSubprocess() throws Exception {
        final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
        final ImportBosArchiveOperation op = new ImportBosArchiveOperation(repositoryAccessor);
        final URL fileURL2 = FileLocator.toFileURL(TestSubprocess.class.getResource("ActivityToAdmin-1.0.bos")); //$NON-NLS-1$
        op.setArchiveFile(FileLocator.toFileURL(fileURL2).getFile());
        op.setCurrentRepository(RepositoryManager.getInstance().getCurrentRepository());
        PlatformUI.getWorkbench().getProgressService().run(true, false, op);
        final MainProcess diagram = store.getChild("ActivityToAdmin-1.0.proc", true).getContent();
        assertEquals("ActivityToAdmin", diagram.getName());

        final URL fileURL1 = FileLocator.toFileURL(TestSubprocess.class.getResource("InvokeActivityToAdmin-1.0.bos")); //$NON-NLS-1$
        op.setArchiveFile(FileLocator.toFileURL(fileURL1).getFile());
        PlatformUI.getWorkbench().getProgressService().run(true, false, op);
        final MainProcess mainProcess = store.getChild("InvokeActivityToAdmin-1.0.proc", true).getContent();
        assertEquals("InvokeActivityToAdmin", mainProcess.getName());
        final SearchOptions searchOptions = new SearchOptionsBuilder(0, 10).done();
        final List<HumanTaskInstance> tasks = processApi.searchPendingTasksForUser(session.getUserId(), searchOptions)
                .getResult();

        final RunProcessCommand runProcessCommand = new RunProcessCommand(true);
        runProcessCommand
                .execute(ProcessSelector.createExecutionEvent((AbstractProcess) mainProcess.getElements().get(0)));
        final String urlGivenToBrowser = runProcessCommand.getUrl().toString();
        assertFalse("The url contains null:" + urlGivenToBrowser, urlGivenToBrowser.contains("null"));
        final long processId = processApi.getProcessDefinitionId("ActivityToAdmin", "1.0");
        final ProcessDefinition processDef = processApi.getProcessDefinition(processId);
        assertNotNull(processDef);
        processApi.startProcess(processApi.getProcessDefinitionId("InvokeActivityToAdmin", "1.0"));

        Await.waitUntil(() -> {
            try {
                newTask = null;
                newTask = EngineAPIUtil.findNewPendingTaskForSpecifiedProcessDefAndUser(session, tasks,
                        processDef.getId(),
                        session.getUserId());
                return newTask != null;
            } catch (InvalidSessionException | SearchException e) {
                throw new RuntimeException(e);
            }
        }, 30000, 200);

        assertNotNull("Subprocess should have started a new task for admin", newTask);
        assertEquals("This task does not belong to new process", processDef.getId(), newTask.getProcessDefinitionId());

        final SearchOptions searchOptions2 = new SearchOptionsBuilder(0, 10).done();
        final List<HumanTaskInstance> previoustasks = processApi
                .searchPendingTasksForUser(session.getUserId(), searchOptions2).getResult();
        final int nbPreviousTasks = previoustasks.size();

        final URL fileURL3 = FileLocator.toFileURL(TestSubprocess.class.getResource("DynamicSubprocess-1.0.bos")); //$NON-NLS-1$
        op.setArchiveFile(FileLocator.toFileURL(fileURL3).getFile());
        PlatformUI.getWorkbench().getProgressService().run(true, false, op);

        final MainProcess mainProcess2 = store.getChild("DynamicSubprocess-1.0.proc", true).getContent();
        assertEquals("DynamicSubprocess", mainProcess2.getName());

        runProcessCommand
                .execute(ProcessSelector.createExecutionEvent((AbstractProcess) mainProcess2.getElements().get(0)));

        Await.waitUntil(() -> {
            List<HumanTaskInstance> currentTasks;
            try {
                currentTasks = processApi
                        .searchPendingTasksForUser(session.getUserId(), new SearchOptionsBuilder(0, 10).done())
                        .getResult();
                final int nbCurrentTasks = currentTasks.size();
                return nbCurrentTasks != nbPreviousTasks + 1;
            } catch (SearchException e) {
                throw new RuntimeException(e);
            }
        }, 15000, 200);

    }

    @Test
    public void testParametersMapping() throws Exception {
        final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
        final ImportBosArchiveOperation op = new ImportBosArchiveOperation(repositoryAccessor);
        final URL fileURL2 = FileLocator.toFileURL(TestSubprocess.class.getResource("Calculator-1.0.bos")); //$NON-NLS-1$
        op.setArchiveFile(FileLocator.toFileURL(fileURL2).getFile());
        op.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        PlatformUI.getWorkbench().getProgressService().run(true, false, op);

        final URL fileURL1 = FileLocator.toFileURL(TestSubprocess.class.getResource("InvokeCalculator-1.0.bos")); //$NON-NLS-1$
        op.setArchiveFile(FileLocator.toFileURL(fileURL1).getFile());
        op.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        PlatformUI.getWorkbench().getProgressService().run(true, false, op);
        final MainProcess mainProcess = store.getChild("InvokeCalculator-1.0.proc", true).getContent();
        assertEquals("InvokeCalculator", mainProcess.getName());

        final SearchOptions searchOptions = new SearchOptionsBuilder(0, 10).done();
        final List<HumanTaskInstance> tasks = processApi.searchPendingTasksForUser(session.getUserId(), searchOptions)
                .getResult();

        final RunProcessCommand runProcessCommand = new RunProcessCommand(true);
        runProcessCommand
                .execute(ProcessSelector.createExecutionEvent((AbstractProcess) mainProcess.getElements().get(0)));
        final long processId = processApi.getProcessDefinitionId("InvokeCalculator", "1.0");
        final ProcessDefinition processDef = processApi.getProcessDefinition(processId);
        assertNotNull(processDef);
        processApi.startProcess(processId);

        Await.waitUntil(() -> {
            try {
                newTask = null;
                newTask = EngineAPIUtil.findNewPendingTaskForSpecifiedProcessDefAndUser(session, tasks,
                        processDef.getId(), session.getUserId());
                return newTask != null;
            } catch (InvalidSessionException | SearchException e) {
                throw new RuntimeException(e);
            }
        }, 30000, 200);

        assertTrue("This task is not the expected one, name does not contain [check]",
                newTask.getName().toLowerCase().contains("check"));
        assertEquals("Sum should be 2", 2,
                processApi.getProcessDataInstance("more", newTask.getParentProcessInstanceId()).getValue());
        assertEquals("Diff should be 0", 0,
                processApi.getProcessDataInstance("less", newTask.getParentProcessInstanceId()).getValue());
    }

    @Test
    public void testTwiceDeploymentOfSubprocessEvent() throws Exception {
        final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
        final SearchOptions searchOptions2 = new SearchOptionsBuilder(0, 10).done();
        final List<HumanTaskInstance> previoustasks = processApi
                .searchPendingTasksForUser(session.getUserId(), searchOptions2).getResult();
        final int nbPreviousTasks = previoustasks.size();
        final ImportBosArchiveOperation op = new ImportBosArchiveOperation(repositoryAccessor);

        final URL fileURL = FileLocator.toFileURL(TestSubprocess.class.getResource("ParentSubProcEvent-1.0.bos")); //$NON-NLS-1$
        op.setArchiveFile(FileLocator.toFileURL(fileURL).getFile());
        op.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        PlatformUI.getWorkbench().getProgressService().run(true, false, op);
        final MainProcess mainProcess = store.getChild("ParentSubProcEvent-1.0.proc", true).getContent();
        RunProcessCommand runProcessCommand = new RunProcessCommand(true);
        runProcessCommand
                .execute(ProcessSelector.createExecutionEvent((AbstractProcess) mainProcess.getElements().get(0)));
        final String urlGivenToBrowser = runProcessCommand.getUrl().toString();
        assertFalse("The url contains null:" + urlGivenToBrowser, urlGivenToBrowser.contains("null"));

        Await.waitUntil(() -> {
            try {
                return processApi
                        .searchPendingTasksForUser(session.getUserId(), new SearchOptionsBuilder(0, 10).done())
                        .getCount() != nbPreviousTasks + 1;
            } catch (SearchException e) {
                throw new RuntimeException(e);
            }
        }, 15000, 200);


        runProcessCommand = new RunProcessCommand(true);
        runProcessCommand
                .execute(ProcessSelector.createExecutionEvent((AbstractProcess) mainProcess.getElements().get(0)));
        final String urlGivenToBrowser2 = runProcessCommand.getUrl().toString();
        assertFalse("The url contains null:" + urlGivenToBrowser2, urlGivenToBrowser2.contains("null"));
        
        Await.waitUntil(() -> {
            try {
                return processApi
                        .searchPendingTasksForUser(session.getUserId(), new SearchOptionsBuilder(0, 10).done())
                        .getCount() != nbPreviousTasks + 2;
            } catch (SearchException e) {
                throw new RuntimeException(e);
            }
        }, 15000, 200);
    }
}
