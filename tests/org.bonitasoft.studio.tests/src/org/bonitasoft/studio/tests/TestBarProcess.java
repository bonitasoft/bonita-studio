/**
 * Copyright (C) 2009 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests;

import java.net.URL;

import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.runtime.FileLocator;

import junit.framework.TestCase;

/**
 * @author Mickael Istria
 */
public class TestBarProcess extends TestCase {

    private MainProcess process;

    public void testImport() throws Exception {
        URL processURL = FileLocator.toFileURL(getClass().getResource("My_Bar_Process_1.1.proc"));
        fail();
        //		ProcessRepository.ImportBARModule.importProcOrBar(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
        //				new File(processURL.getFile())).open();
        //		process = (MainProcess) ((ProcessDiagramEditor)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor()).getDiagramEditPart().resolveSemanticElement();
        //		assertEquals("My Bar Process", process.getName());
    }

    public void testRun() throws Exception {
        fail();
        //		APISession session =  BOSEngineManager.getInstance().loginDefaultTenant() ;
        //		ProcessManagementAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session) ;
        //		int beforeProcess = processApi.get(ActivityState.READY).size();
        //		lc.logout();
        //		RunProcessCommand runCommand = new RunProcessCommand(true);
        //		runCommand.execute(null);
        //		lc.login();
        //		int afterProcess = AccessorUtil.getQueryRuntimeAPI().getTaskList(ActivityState.READY).size();
        //		lc.logout();
        //		assertEquals(beforeProcess + 1, afterProcess);
    }

    public void testExecute() throws Exception {
        fail();
        //		LoginContext lc = new LoginContext("Bonita", new SimpleCallbackHandler("jack", "bpm"));
        //		lc.login();
        //		Collection<TaskInstance> tasks = AccessorUtil.getQueryRuntimeAPI().getTaskList(ActivityState.READY);
        //		lc.logout();
        //		assertEquals("Jack should only have on task in his TODO-list", 1, tasks.size());
        //		TaskInstance task = tasks.iterator().next();
        //		assertEquals(process.getName(), task.getProcessDefinitionUUID().getProcessName());
        //		lc.login();
        //		AccessorUtil.getRuntimeAPI().finishTask(task.getUUID(), false);
    }
}
