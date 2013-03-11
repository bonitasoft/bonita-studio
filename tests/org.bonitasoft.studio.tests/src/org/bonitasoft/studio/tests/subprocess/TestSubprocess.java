/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.tests.subprocess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.model.ActivityInstanceCriterion;
import org.bonitasoft.engine.bpm.model.HumanTaskInstance;
import org.bonitasoft.engine.bpm.model.ProcessDefinition;
import org.bonitasoft.engine.bpm.model.TaskInstance;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.InvalidSessionException;
import org.bonitasoft.engine.exception.LoginException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.exception.UserNotFoundException;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.search.SearchResult;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.command.RunProcessCommand;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.util.test.async.TestAsyncThread;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.ui.PlatformUI;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Mickael Istria
 * @author Aurelie Zara
 *
 */
public class TestSubprocess {

    private HumanTaskInstance newTask;
    private APISession session;

    @Before
    public void setUp() throws LoginException, BonitaHomeNotSetException, ServerAPIException, UnknownAPITypeException {
        session = BOSEngineManager.getInstance().loginDefaultTenant(Repository.NULL_PROGRESS_MONITOR);
    }

    @After
    public void tearDown(){
        BOSEngineManager.getInstance().logoutDefaultTenant(session);
    }

    @Test
    public void testSubprocess() throws Exception {
        final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
        ImportBosArchiveOperation op = new ImportBosArchiveOperation();
        URL fileURL2 = FileLocator.toFileURL(TestSubprocess.class.getResource("ActivityToAdmin-1.0.bos")); //$NON-NLS-1$
        op.setArchiveFile(FileLocator.toFileURL(fileURL2).getFile());
        op.run(new NullProgressMonitor());
        ProcessDiagramEditor processEditor2 = (ProcessDiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        assertEquals("ActivityToAdmin", ((MainProcess)processEditor2.getDiagramEditPart().resolveSemanticElement()).getName());
        URL fileURL1 = FileLocator.toFileURL(TestSubprocess.class.getResource("InvokeActivityToAdmin-1.0.bos")); //$NON-NLS-1$
        op.setArchiveFile(FileLocator.toFileURL(fileURL1).getFile());
        op.run(new NullProgressMonitor());
        ProcessDiagramEditor processEditor = (ProcessDiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        MainProcess mainProcess = (MainProcess)processEditor.getDiagramEditPart().resolveSemanticElement();
        assertEquals("InvokeActivityToAdmin", mainProcess.getName());
        final SearchOptions searchOptions = new SearchOptionsBuilder(0, 10).done();
        final List<HumanTaskInstance> tasks =processApi.searchPendingTasksForUser(session.getUserId(), searchOptions).getResult();

        final RunProcessCommand runProcessCommand = new RunProcessCommand(true);
        Map<String,Object> param = new HashMap<String, Object>();
        param.put(RunProcessCommand.PROCESS, mainProcess.getElements().get(0));
        ExecutionEvent ee = new ExecutionEvent(null,param,null,null);
        runProcessCommand.execute(ee);
        final String urlGivenToBrowser = runProcessCommand.getUrl().toString();
        assertFalse("The url contains null:"+urlGivenToBrowser, urlGivenToBrowser.contains("null"));
        long processId=processApi.getProcessDefinitionId("ActivityToAdmin", "1.0");
        final ProcessDefinition processDef = processApi.getProcessDefinition(processId);
        assertNotNull(processDef);
        processApi.startProcess(processApi.getProcessDefinitionId("InvokeActivityToAdmin","1.0"));

        boolean evaluateAsync = new TestAsyncThread(30, 1000) {
            @Override
            public boolean isTestGreen() throws Exception {

                newTask = findNewTask(tasks, processDef.getId());

                return newTask != null;
            }
        }.evaluate();
        String errorMessageDetailled = "";
        if(!evaluateAsync){
            final Collection<HumanTaskInstance> actualTask =processApi.getPendingHumanTaskInstances(session.getUserId(),0, 20, ActivityInstanceCriterion.DEFAULT);
            errorMessageDetailled += "\n processUUID searched: "+processDef.getId();
            for (TaskInstance taskInstance : actualTask) {
                errorMessageDetailled += "\n"+taskInstance.getParentProcessInstanceId();
            }

        }
        assertTrue("Subprocess should have started a new task for admin.current task:\n"+errorMessageDetailled,evaluateAsync);
        assertNotNull("Subprocess should have started a new task for admin", newTask);
        assertEquals("This task does not belong to new process", processDef.getId(), newTask.getProcessDefinitionId());

        //ajout du test testDynamicSuprocess � la suite car d�pendant de celui-ci, difficile de le rendre ind�pendant.

        //BOSEngineManager.getInstance().logoutDefaultTenant(session);
        //session = BOSEngineManager.getInstance().loginDefaultTenant(Repository.NULL_PROGRESS_MONITOR);

        final SearchOptions searchOptions2 = new SearchOptionsBuilder(0, 10).done();
        final List<HumanTaskInstance> previoustasks =processApi.searchPendingTasksForUser(session.getUserId(), searchOptions2).getResult();
        final int nbPreviousTasks=previoustasks.size();
        // BOSEngineManager.getInstance().logoutDefaultTenant(session);
        // Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

        URL fileURL3 = FileLocator.toFileURL(TestSubprocess.class.getResource("DynamicSubprocess-1.0.bos")); //$NON-NLS-1$
        op.setArchiveFile(FileLocator.toFileURL(fileURL3).getFile());
        op.run(new NullProgressMonitor());

        ProcessDiagramEditor pe = (ProcessDiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        MainProcess mainProcess2 = (MainProcess)pe.getDiagramEditPart().resolveSemanticElement();
        assertEquals("DynamicSubprocess", mainProcess2.getName());

        //session = BOSEngineManager.getInstance().loginDefaultTenant(Repository.NULL_PROGRESS_MONITOR);

        Map<String,Object> param2 = new HashMap<String, Object>();
        param.put(RunProcessCommand.PROCESS, mainProcess2.getElements().get(0));
        ExecutionEvent ee2 = new ExecutionEvent(null,param,null,null);
        runProcessCommand.execute(ee2);

        assertTrue(new TestAsyncThread(12, 1000) {
            @Override
            public boolean isTestGreen() throws Exception {
                final SearchOptions searchOptions = new SearchOptionsBuilder(0, 10).done();
                final List<HumanTaskInstance> currentTasks =processApi.searchPendingTasksForUser(session.getUserId(), searchOptions).getResult();
                int nbCurrentTasks = currentTasks.size();
                return nbCurrentTasks != nbPreviousTasks + 1;
            }
        }.evaluate());

    }



    @Test
    public void testParametersMapping() throws Exception {
        final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
        ImportBosArchiveOperation op = new ImportBosArchiveOperation();
        URL fileURL2 = FileLocator.toFileURL(TestSubprocess.class.getResource("Calculator-1.0.bos")); //$NON-NLS-1$
        op.setArchiveFile(FileLocator.toFileURL(fileURL2).getFile());
        op.run(new NullProgressMonitor());
        ProcessDiagramEditor processEditor2 = (ProcessDiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        URL fileURL1 = FileLocator.toFileURL(TestSubprocess.class.getResource("InvokeCalculator-1.0.bos")); //$NON-NLS-1$
        op.setArchiveFile(FileLocator.toFileURL(fileURL1).getFile());
        op.run(new NullProgressMonitor());
        ProcessDiagramEditor processEditor = (ProcessDiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        MainProcess mainProcess = (MainProcess)processEditor.getDiagramEditPart().resolveSemanticElement();
        assertEquals("InvokeCalculator", mainProcess.getName());

        final SearchOptions searchOptions = new SearchOptionsBuilder(0, 10).done();
        final List<HumanTaskInstance> tasks =processApi.searchPendingTasksForUser(session.getUserId(), searchOptions).getResult();

        final RunProcessCommand runProcessCommand = new RunProcessCommand(true);
        Map<String,Object> param = new HashMap<String, Object>();
        param.put(RunProcessCommand.PROCESS, mainProcess.getElements().get(0));
        ExecutionEvent ee = new ExecutionEvent(null,param,null,null);
        runProcessCommand.execute(ee);
        long processId=processApi.getProcessDefinitionId("InvokeCalculator", "1.0");
        final ProcessDefinition processDef = processApi.getProcessDefinition(processId);
        assertNotNull(processDef);
        processApi.startProcess(processId);

        boolean isANewTask = false;
        try {
            isANewTask = new TestAsyncThread(30, 1000) {
                @Override
                public boolean isTestGreen() throws Exception {
                    newTask = findNewTask(tasks, processDef.getId());
                    return newTask != null;
                }
            }.evaluate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue("There is no new task",isANewTask);
        assertTrue("This task is not the expected one, name does not contain [check]", newTask.getName().toLowerCase().contains("check"));
        assertEquals("Sum should be 2", 2, processApi.getProcessDataInstance("more", newTask.getParentProcessInstanceId()).getValue());
        assertEquals("Diff should be 0", 0,processApi.getProcessDataInstance("less", newTask.getParentProcessInstanceId()).getValue());
    }

    /**
     * @param previousTasks
     * @param processDefinitionUUID
     * @return
     * @throws UserNotFoundException
     * @throws InvalidSessionException
     * @throws LoginException
     */
    private HumanTaskInstance findNewTask(List<HumanTaskInstance> previousTasks,long processDefinitionUUID) throws InvalidSessionException, UserNotFoundException  {
        final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
        final SearchOptions searchOptions = new SearchOptionsBuilder(0, 10).done();
        SearchResult<HumanTaskInstance> tasks=processApi.searchPendingTasksForUser(session.getUserId(), searchOptions);


        if (tasks.getCount() == previousTasks.size()) {
            return null;
        } else {
            boolean newTaskIsOld = false;
            HumanTaskInstance newTask = null;
            for (HumanTaskInstance currentTask : tasks.getResult()) {
                newTaskIsOld = false;//reinit the value
                for (HumanTaskInstance oldTask : previousTasks) {
                    if (currentTask.getId()==oldTask.getId()) {
                        newTaskIsOld = true;
                    }
                }
                if (!newTaskIsOld && currentTask.getProcessDefinitionId()==processDefinitionUUID) {
                    newTask = currentTask;
                    break;
                }
            }
            return newTask;
        }
    }





    /*    public void testDynamicSubprocess() throws Exception {


        //		BonitaEnvironmentInit.init(new NullProgressMonitor());
        //		final LoginContext lc = BonitaEnvironmentInit.createALoginContext();
        //		lc.login();
        //		final int previousTasks = queryRuntimeAPI.getTaskList("admin", ActivityState.READY).size();
        //		lc.logout();
        //		Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
        //
        //		URL url = getClass().getResource("DynamicSubprocess_1_0_DynamicSubprocess_1.0.bar");
        //		url = FileLocator.toFileURL(url);
        //		File file = new File(url.getFile());
        //		ProcessRepository.ImportBARModule.importProcOrBar(shell, file).open();
        //
        //		new RunProcessCommand(true).execute(null);
//        		lc.login();
//        		assertTrue(new TestAsyncThread(12, 1000) {
//        			@Override
//        			public boolean isTestGreen() throws Exception {
//        				lc.login();
//        				int currentTasks = queryRuntimeAPI.getTaskList("admin", ActivityState.READY).size();
//        				lc.logout();
//        				return currentTasks != previousTasks + 1;
//        			}
//        		}.evaluate());
        //		lc.logout();
    }*/



    @Test
    @Ignore
    public void testTwiceDeploymentOfSubprocessEvent() throws Exception {
        fail();
        //    	 final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
        //    	 final SearchOptions searchOptions2 = new SearchOptionsBuilder(0, 10).done();
        //    	 final List<HumanTaskInstance> previoustasks =processApi.searchPendingTasksForUser(session.getUserId(), searchOptions2).getResult();
        //         final int nbPreviousTasks=previoustasks.size();
        //         ImportBosArchiveOperation op = new ImportBosArchiveOperation();
        //         URL fileURL = FileLocator.toFileURL(TestSubprocess.class.getResource("ParentSubProcEvent-1.0.bos")); //$NON-NLS-1$
        //         op.setArchiveFile(FileLocator.toFileURL(fileURL).getFile());
        //         op.run(new NullProgressMonitor());
        //         ProcessDiagramEditor processEditor = (ProcessDiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
        //         MainProcess mainProcess = (MainProcess)processEditor.getDiagramEditPart().resolveSemanticElement();
        //         RunProcessCommand runProcessCommand = new RunProcessCommand(true);
        //         Map<String,Object> param = new HashMap<String, Object>();
        //         param.put(RunProcessCommand.PROCESS, mainProcess.getElements().get(0));
        //         ExecutionEvent ee = new ExecutionEvent(null,param,null,null);
        //         runProcessCommand.execute(ee);
        //         final String urlGivenToBrowser=runProcessCommand.getUrl().toString();
        //         assertFalse("The url contains null:"+urlGivenToBrowser, urlGivenToBrowser.contains("null"));
        //         assertTrue(new TestAsyncThread(12, 1000) {
        //             			@Override
        //             			public boolean isTestGreen() throws Exception {
        //             				 final SearchOptions searchOptions2 = new SearchOptionsBuilder(0, 10).done();
        //             		    	 final List<HumanTaskInstance> currentTasks =processApi.searchPendingTasksForUser(session.getUserId(), searchOptions2).getResult();
        //             		    	 int nbCurrentTasks = currentTasks.size();
        //             		    	 return nbCurrentTasks != nbPreviousTasks + 1;
        //             			}
        //             		}.evaluate());
        //             processEditor.close(false);
        //             runProcessCommand = new RunProcessCommand(true);
        //             ee=new ExecutionEvent(null,param,null,null);
        //             runProcessCommand.execute(ee);
        //             final String urlGivenToBrowser2 = runProcessCommand.getUrl().toString();
        //             assertFalse("The url contains null:"+urlGivenToBrowser2, urlGivenToBrowser2.contains("null"));
        //             assertTrue(new TestAsyncThread(12, 1000) {
        //                 			@Override
        //                 			public boolean isTestGreen() throws Exception {
        //                 				final SearchOptions searchOptions2 = new SearchOptionsBuilder(0, 10).done();
        //                		    	 final List<HumanTaskInstance> currentTasks =processApi.searchPendingTasksForUser(session.getUserId(), searchOptions2).getResult();
        //                		    	 int nbCurrentTasks = currentTasks.size();
        //                		    	 return nbCurrentTasks != nbPreviousTasks + 2;
        //                 			}
        //                 		}.evaluate());


    }

}
