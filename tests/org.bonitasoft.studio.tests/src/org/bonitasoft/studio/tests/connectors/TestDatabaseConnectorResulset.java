/**
 * Copyright (C) 2012 BonitaSoft S.A.
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

package org.bonitasoft.studio.tests.connectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.Collection;
import java.util.List;

import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.flownode.ActivityInstanceCriterion;
import org.bonitasoft.engine.bpm.flownode.HumanTaskInstance;
import org.bonitasoft.engine.bpm.flownode.TaskInstance;
import org.bonitasoft.engine.bpm.process.ProcessDefinition;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.platform.LoginException;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.command.RunProcessCommand;
import org.bonitasoft.studio.engine.operation.ProcessSelector;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.util.test.EngineAPIUtil;
import org.bonitasoft.studio.util.test.async.TestAsyncThread;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @Author Aurelie Zara
 *         This test has dependency on Postgres database:
 *         - the running machine need to be authorized in pg_hba.conf (IP network mask)
 *         - the provided .bos hardly point to the machine containing the postgresSQL
 */
@Ignore
public class TestDatabaseConnectorResulset {

    private HumanTaskInstance newTask;

    private APISession session;

    private RepositoryAccessor repositoryAccessor;

    @Before
    public void setUp() throws LoginException, BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException {
        session = BOSEngineManager.getInstance().loginDefaultTenant(AbstractRepository.NULL_PROGRESS_MONITOR);
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
    }

    @After
    public void tearDown() {
        BOSEngineManager.getInstance().logoutDefaultTenant(session);
    }

    @Test
    public void testDatabaseConnectorResultset() throws Exception {
        final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
        final ImportBosArchiveOperation op = new ImportBosArchiveOperation(repositoryAccessor);
        final URL fileURL1 = FileLocator
                .toFileURL(TestDatabaseConnectorResulset.class.getResource("testDatabaseResultSet-2.0.bos")); //$NON-NLS-1$
        op.setArchiveFile(FileLocator.toFileURL(fileURL1).getFile());
        op.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        op.run(new NullProgressMonitor());
        for (final IRepositoryFileStore fStore : op.getFileStoresToOpen()) {
            fStore.open();
        }
        final MainProcess mainProcess = (MainProcess) op.getFileStoresToOpen().get(0).getContent();

        final SearchOptions searchOptions = new SearchOptionsBuilder(0, 10).done();
        final List<HumanTaskInstance> tasks = processApi.searchPendingTasksForUser(session.getUserId(), searchOptions)
                .getResult();

        final RunProcessCommand runProcessCommand = new RunProcessCommand(true);
        runProcessCommand.execute(ProcessSelector.createExecutionEvent((AbstractProcess) mainProcess.getElements().get(0)));

        final long processId = processApi.getProcessDefinitionId("testDatabaseResultSet", "2.0");
        final ProcessDefinition processDef = processApi.getProcessDefinition(processId);
        assertNotNull(processDef);
        processApi.startProcess(processId);

        final boolean evaluateAsync = new TestAsyncThread(30, 1000) {

            @Override
            public boolean isTestGreen() throws Exception {

                newTask = EngineAPIUtil.findNewPendingTaskForSpecifiedProcessDefAndUser(session, tasks, processDef.getId(),
                        session.getUserId());

                return newTask != null;
            }
        }.evaluate();

        String errorMessageDetailled = "";
        if (!evaluateAsync) {
            final Collection<HumanTaskInstance> actualTask = processApi.getPendingHumanTaskInstances(session.getUserId(), 0,
                    20,
                    ActivityInstanceCriterion.DEFAULT);
            errorMessageDetailled += "\n processUUID searched: " + processDef.getId();
            for (final TaskInstance taskInstance : actualTask) {
                errorMessageDetailled += "\n" + taskInstance.getParentProcessInstanceId();
            }

        }
        assertTrue("a new task should have started for testDatabaseResultSet task:\n" + errorMessageDetailled,
                evaluateAsync);
        assertNotNull("a new task should have started", newTask);
        assertEquals("This task does not belong to new process", processDef.getId(), newTask.getProcessDefinitionId());
        assertTrue("the current task should not be \"closed\"", !newTask.getName().equals("closed"));
    }

}
