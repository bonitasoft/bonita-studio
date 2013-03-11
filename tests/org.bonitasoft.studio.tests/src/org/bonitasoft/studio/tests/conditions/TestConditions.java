/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.tests.conditions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import org.junit.Test;

/**
 * @author Aurelie Zara
 *
 */
public class TestConditions {
	
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
	    public void testConditions() throws Exception{
	    	 final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
	         ImportBosArchiveOperation op = new ImportBosArchiveOperation();
	         URL fileURL1 = FileLocator.toFileURL(TestConditions.class.getResource("testConditions-2.0.bos")); //$NON-NLS-1$
	         op.setArchiveFile(FileLocator.toFileURL(fileURL1).getFile());
	         op.run(new NullProgressMonitor());
	         ProcessDiagramEditor processEditor = (ProcessDiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
	         MainProcess mainProcess = (MainProcess)processEditor.getDiagramEditPart().resolveSemanticElement();
	         
	         final SearchOptions searchOptions = new SearchOptionsBuilder(0, 10).done();
	         final List<HumanTaskInstance> tasks =processApi.searchPendingTasksForUser(session.getUserId(), searchOptions).getResult();

	         final RunProcessCommand runProcessCommand = new RunProcessCommand(true);
	         Map<String,Object> param = new HashMap<String, Object>();
	         param.put(RunProcessCommand.PROCESS, mainProcess.getElements().get(0));
	         ExecutionEvent ee = new ExecutionEvent(null,param,null,null);
	         runProcessCommand.execute(ee);
	         
	         long processId=processApi.getProcessDefinitionId("Pool3", "1.0");
	         final ProcessDefinition processDef = processApi.getProcessDefinition(processId);
	         assertNotNull(processDef);
	         processApi.startProcess(processId);
	         
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
	         assertTrue("a new task should have started for Pool3 task:\n"+errorMessageDetailled,evaluateAsync);
	         assertNotNull("a new task should have started", newTask);
	         assertEquals("This task does not belong to new process", processDef.getId(), newTask.getProcessDefinitionId());
	         assertEquals("the current task should be Step3","Step3",newTask.getName());

	    }
	    
	    
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

}
