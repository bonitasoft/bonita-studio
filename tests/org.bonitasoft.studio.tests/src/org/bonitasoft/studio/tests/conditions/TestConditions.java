/**
 * Copyright (C) 2012-2014 BonitaSoft S.A.
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

package org.bonitasoft.studio.tests.conditions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;

import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.flownode.HumanTaskInstance;
import org.bonitasoft.engine.bpm.process.ProcessActivationException;
import org.bonitasoft.engine.bpm.process.ProcessDefinition;
import org.bonitasoft.engine.bpm.process.ProcessDefinitionNotFoundException;
import org.bonitasoft.engine.bpm.process.ProcessExecutionException;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.SearchException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.platform.LoginException;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.engine.session.InvalidSessionException;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.command.RunProcessCommand;
import org.bonitasoft.studio.engine.operation.ProcessSelector;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.tests.util.Await;
import org.bonitasoft.studio.tests.util.EngineAPIUtil;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.PlatformUI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Aurelie Zara
 */
public class TestConditions {

    private HumanTaskInstance newTask;
    private APISession session;
    private RepositoryAccessor repositoryAccessor;

    @Before
    public void setUp()
            throws LoginException, BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException,
            LoginException {
        session = BOSEngineManager.getInstance().loginDefaultTenant(AbstractRepository.NULL_PROGRESS_MONITOR);
        repositoryAccessor = RepositoryManager.getInstance().getAccessor();
    }

    @After
    public void tearDown() {
        BOSEngineManager.getInstance().logoutDefaultTenant(session);
    }

    @Test
    public void testConditions() throws Exception {
        final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
        final MainProcess mainProcess = importProcessToTest();
        Job.getJobManager().join(ResourcesPlugin.FAMILY_AUTO_BUILD, null);
        final List<HumanTaskInstance> tasks = getPendingTasks(processApi);
        runProcess(mainProcess);
        final ProcessDefinition processDef = startDeployedProcess(processApi);

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

        assertNotNull("a new task should have started", newTask);
        assertEquals("This task does not belong to new process", processDef.getId(), newTask.getProcessDefinitionId());
        assertEquals("the current task should be Step3", "Step3", newTask.getName());
    }

    private ProcessDefinition startDeployedProcess(final ProcessAPI processApi)
            throws ProcessDefinitionNotFoundException, ProcessActivationException,
            ProcessExecutionException, InterruptedException {
        int i = 0;
        long processId = -1;
        while (processId < 0 || i > 10) {
            try {
                processId = processApi.getProcessDefinitionId("Pool3", "1.0");
            } catch (ProcessDefinitionNotFoundException e) {
                i++;
                Thread.sleep(100);
            }
        }
        if (processId < 0) {
            fail(String.format("Process definition not found: %s (%s)", "Pool3", "1.0"));
        }
        i = 0;
        ProcessDefinition processDef = null;
        while (processDef == null || i > 10) {
            try {
                processDef = processApi.getProcessDefinition(processId);
            } catch (final ProcessDefinitionNotFoundException pdnf) {
                i++;
                BonitaStudioLog.log("Failed Attempt " + i + " to retrieve Process def with id" + processId);
                BonitaStudioLog.error(pdnf);
                Thread.sleep(100);
            }
        }
        assertNotNull("Process definition not found", processDef);
        processApi.startProcess(processId);
        return processDef;
    }

    private void runProcess(final MainProcess mainProcess) throws ExecutionException {
        final RunProcessCommand runProcessCommand = new RunProcessCommand(true);
        runProcessCommand
                .execute(ProcessSelector.createExecutionEvent((AbstractProcess) mainProcess.getElements().get(0)));
    }

    private List<HumanTaskInstance> getPendingTasks(final ProcessAPI processApi) throws SearchException {
        final SearchOptions searchOptions = new SearchOptionsBuilder(0, 10).done();
        return processApi.searchPendingTasksForUser(session.getUserId(), searchOptions)
                .getResult();
    }

    private MainProcess importProcessToTest() throws IOException, InvocationTargetException, InterruptedException {
        final ImportBosArchiveOperation op = new ImportBosArchiveOperation(repositoryAccessor);
        final URL fileURL1 = FileLocator.toFileURL(TestConditions.class.getResource("testConditions-2.0.bos")); //$NON-NLS-1$
        op.setArchiveFile(FileLocator.toFileURL(fileURL1).getFile());
        op.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        PlatformUI.getWorkbench().getProgressService().run(true, false, op);
        for (final IRepositoryFileStore f : op.getFileStoresToOpen()) {
            f.open();
        }
        MainProcess mainProcess;
        try {
            mainProcess = (MainProcess) op.getFileStoresToOpen().get(0).getContent();
            return mainProcess;
        } catch (final ReadFileStoreException e) {
            BonitaStudioLog.error("Failed read diagram content", e);
        }

        return null;
    }
}
