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
package org.bonitasoft.studio.tests.engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.List;

import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.flownode.HumanTaskInstance;
import org.bonitasoft.engine.bpm.process.ProcessDefinition;
import org.bonitasoft.engine.identity.User;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.core.maven.AddDependencyOperation;
import org.bonitasoft.studio.common.repository.model.IRepositoryFileStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.command.RunProcessCommand;
import org.bonitasoft.studio.engine.operation.ProcessSelector;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.util.test.EngineAPIUtil;
import org.bonitasoft.studio.util.test.async.TestAsyncThread;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.ui.PlatformUI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestUserFilterMatchingEngineVersion {

    private APISession session;

    private HumanTaskInstance newTask;

    private RepositoryAccessor repositoryAccessor;

    @Before
    public void init() throws Exception {
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        AddDependencyOperation addDependencyOperation = new AddDependencyOperation("org.bonitasoft.actorfilter", "bonita-actorfilter-single-user", "1.0.0");
        addDependencyOperation.run(AbstractRepository.NULL_PROGRESS_MONITOR);
    }

    @After
    public void tearDown() {
        if (session != null) {
            BOSEngineManager.getInstance().logoutDefaultTenant(session);
        }
    }

    @Test
    public void testUserFilterRun() throws Exception {
        session = BOSEngineManager.getInstance().loginTenant("william.jobs", "bpm", AbstractRepository.NULL_PROGRESS_MONITOR);
        final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
        final SearchOptions searchOptions = new SearchOptionsBuilder(0, 10).done();
        final User williamJobsUser = BOSEngineManager.getInstance().getIdentityAPI(session)
                .getUserByUserName("william.jobs");
        final Long williamJobsID = williamJobsUser.getId();
        final List<HumanTaskInstance> tasks = processApi.searchPendingTasksForUser(williamJobsID, searchOptions).getResult();

        final ImportBosArchiveOperation op = new ImportBosArchiveOperation(repositoryAccessor);
        final URL fileURL1 = FileLocator
                .toFileURL(TestUserFilterMatchingEngineVersion.class.getResource("DiagramToTestUserFIlter-1.0.bos")); //$NON-NLS-1$
        op.setArchiveFile(FileLocator.toFileURL(fileURL1).getFile());
        op.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        op.run(AbstractRepository.NULL_PROGRESS_MONITOR);

        for (final IRepositoryFileStore f : op.getFileStoresToOpen()) {
            f.open();
        }

        final ProcessDiagramEditor processEditor = (ProcessDiagramEditor) PlatformUI.getWorkbench()
                .getActiveWorkbenchWindow().getActivePage()
                .getActiveEditor();
        final MainProcess mainProcess = (MainProcess) processEditor.getDiagramEditPart().resolveSemanticElement();
        assertEquals("DiagramToTestUserFIlter", mainProcess.getName());

        final AbstractProcess selectedProcess = (AbstractProcess) mainProcess.getElements().get(0);
        final RunProcessCommand runProcessCommand = new RunProcessCommand(true);
        runProcessCommand.execute(ProcessSelector.createExecutionEvent(selectedProcess));

        assertNotNull("The url is null:", runProcessCommand.getUrl());
        final String urlGivenToBrowser = runProcessCommand.getUrl().toString();
        assertFalse("The url contains null:" + urlGivenToBrowser, urlGivenToBrowser.contains("null"));

        final long processId = processApi.getProcessDefinitionId("PoolToTestUserFilter", "1.0");
        final ProcessDefinition processDef = processApi.getProcessDefinition(processId);
        assertNotNull("processDef should not be null", processDef);
        processApi.startProcess(processId);

        final boolean evaluateAsync = new TestAsyncThread(30, 1000) {

            @Override
            public boolean isTestGreen() throws Exception {
                newTask = EngineAPIUtil.findNewAssignedTaskForSpecifiedProcessDefAndUser(session, tasks, processId,
                        williamJobsID);

                return newTask != null;
            }
        }.evaluate();

        assertTrue("No newTask has been found", evaluateAsync);

    }

}
