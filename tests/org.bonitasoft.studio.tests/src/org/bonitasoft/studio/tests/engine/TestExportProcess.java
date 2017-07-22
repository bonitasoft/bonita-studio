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
package org.bonitasoft.studio.tests.engine;

import org.bonitasoft.studio.model.process.CatchMessageEvent;
import org.bonitasoft.studio.model.process.Event;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.ThrowMessageEvent;

import junit.framework.TestCase;

/**
 * @author Mickael Istria
 */
public class TestExportProcess extends TestCase {

    private static final String MATCHER_CONDITION = "true && false";
    private static final String TTL = "3600";
    private static final String END = "end";
    private static final String ACTIVITY_NAME = "lol";
    /**
     * 
     */
    private static final String PROCESS_VERSION = "1.0";

    public void testExportSimpleProcess() throws Exception {
        fail();
        //		String processName = "test" + System.currentTimeMillis();
        //		BonitaEnvironmentInit.init(new NullProgressMonitor());
        //		LoginContext lc = BonitaEnvironmentInit.createALoginContext();
        //		lc.login();
        //
        //		QueryRuntimeAPI queryRuntimeApi = AccessorUtil.getAPIAccessor().getQueryRuntimeAPI();
        //		int beforeTasks = queryRuntimeApi.getTaskList("john", ActivityState.READY).size();
        //		System.err.println(beforeTasks);
        //
        //		MainProcess process = createStudioProcess(processName);
        //		ProcessDefinition def = new BonitaExporter().createProcessDefinition(process).get(0);
        //		BusinessArchive bar = BusinessArchiveFactory.getBusinessArchive(def);
        //		AccessorUtil.getManagementAPI().deploy(bar);
        //		RuntimeAPI runtimeApi = AccessorUtil.getAPIAccessor().getRuntimeAPI();
        //
        //		QueryDefinitionAPI queryDefinitionAPI = AccessorUtil.getAPIAccessor().getQueryDefinitionAPI();
        //		ProcessDefinitionUUID processDefUUID = queryDefinitionAPI.getProcess(processName, PROCESS_VERSION).getUUID();
        //		ProcessInstanceUUID instance = runtimeApi.instantiateProcess(processDefUUID);
        //		//queryRuntimeApi.getProcessInstance(instance)
        //		Assert.assertEquals(beforeTasks + 1, queryRuntimeApi.getTaskList("john", ActivityState.READY).size());
        //		System.err.println(beforeTasks + 1);
        //		lc.logout();
    }

    public void testSynchronousProperty() throws Exception {
        fail();
        //		String processName = "test" + System.currentTimeMillis();
        //		MainProcess process = createStudioWithASynchronousProcess(processName);
        //		ProcessDefinition def = new BonitaExporter().createProcessDefinition(process).get(0);
        //		assertTrue("Synchronous poperty is not set properly",def.getActivity(ACTIVITY_NAME).isAsynchronous() == true);
        //		assertTrue("Synchronous poperty is not set properly",def.getActivity(END).isAsynchronous() == false);
    }

    public void testTTL() throws Exception {
        fail();
        //		String processName = "test" + System.currentTimeMillis();
        //		MainProcess process = createStudioWithMessage(processName);
        //		ProcessDefinition def = new BonitaExporter().createProcessDefinition(process).get(0);
        //
        //		Iterator<OutgoingEventDefinition> it = def.getActivity(ACTIVITY_NAME).getOutgoingEvents().iterator() ;
        //		while (it.hasNext()) {
        //			OutgoingEventDefinition outgoingEventDefinition = (OutgoingEventDefinition) it.next();
        //			assertTrue("TTL is incorrect",outgoingEventDefinition.getTimeToLive() == Long.parseLong(TTL));
        //		}
        //
        //		assertTrue("Event exists",def.getActivity(ACTIVITY_NAME).getOutgoingEvents().size() == 1);
    }

    private MainProcess createStudioWithCatchMessage(String processName) {
        MainProcess process = ProcessFactory.eINSTANCE.createMainProcess();
        process.setName(processName);
        process.setVersion(PROCESS_VERSION);
        Event start = ProcessFactory.eINSTANCE.createStartEvent();
        start.setName("start");

        CatchMessageEvent catchMessageEvent = ProcessFactory.eINSTANCE.createIntermediateCatchMessageEvent();
        catchMessageEvent.setName(ACTIVITY_NAME);
        catchMessageEvent.setEvent("event1");
        SequenceFlow transition = ProcessFactory.eINSTANCE.createSequenceFlow();
        transition.setName("transition");
        transition.setSource(start);
        transition.setTarget(catchMessageEvent);
        Event end = ProcessFactory.eINSTANCE.createEndEvent();
        end.setName(END);
        SequenceFlow transition2 = ProcessFactory.eINSTANCE.createSequenceFlow();
        transition2.setName("transition2");
        transition2.setSource(catchMessageEvent);
        transition2.setTarget(end);
        process.getElements().add(catchMessageEvent);
        process.getElements().add(start);
        process.getElements().add(end);
        process.getConnections().add(transition);
        process.getConnections().add(transition2);
        return process;
    }

    private MainProcess createStudioWithMessage(String processName) {
        MainProcess process = ProcessFactory.eINSTANCE.createMainProcess();
        process.setName(processName);
        process.setVersion(PROCESS_VERSION);
        Event start = ProcessFactory.eINSTANCE.createStartEvent();
        start.setName("start");
        ThrowMessageEvent throwMessageTask = ProcessFactory.eINSTANCE.createThrowMessageEvent();
        throwMessageTask.setName(ACTIVITY_NAME);
        fail();
        //		EventObject ev = ProcessFactory.eINSTANCE.createEventObject() ;
        //		ev.setName("event1");
        //		ev.setTtl(TTL);
        //		ev.setTargetElementName("testCatch");
        //		ev.setTargetProcessName("testCatchParent");
        //		throwMessageTask.getEvents().add(ev);
        SequenceFlow transition = ProcessFactory.eINSTANCE.createSequenceFlow();
        transition.setName("transition");
        transition.setSource(start);
        transition.setTarget(throwMessageTask);
        Event end = ProcessFactory.eINSTANCE.createEndEvent();
        end.setName(END);
        SequenceFlow transition2 = ProcessFactory.eINSTANCE.createSequenceFlow();
        transition2.setName("transition2");
        transition2.setSource(throwMessageTask);
        transition2.setTarget(end);
        process.getElements().add(throwMessageTask);
        process.getElements().add(start);
        process.getElements().add(end);
        process.getConnections().add(transition);
        process.getConnections().add(transition2);
        return process;
    }

    private MainProcess createStudioWithASynchronousProcess(String processName) {
        MainProcess process = ProcessFactory.eINSTANCE.createMainProcess();
        process.setName(processName);
        process.setVersion(PROCESS_VERSION);
        Event start = ProcessFactory.eINSTANCE.createStartEvent();
        start.setName("start");
        Task task = ProcessFactory.eINSTANCE.createTask();
        task.setName(ACTIVITY_NAME);
        fail();
        //		task.setActorType(ActorType.USER);
        //		task.setUser("john");
        //		task.setSynchronous(false);
        SequenceFlow transition = ProcessFactory.eINSTANCE.createSequenceFlow();
        transition.setName("transition");
        transition.setSource(start);
        transition.setTarget(task);
        Event end = ProcessFactory.eINSTANCE.createEndEvent();
        end.setName(END);
        SequenceFlow transition2 = ProcessFactory.eINSTANCE.createSequenceFlow();
        transition2.setName("transition2");
        transition2.setSource(task);
        transition2.setTarget(end);
        process.getElements().add(task);
        process.getElements().add(start);
        process.getElements().add(end);
        process.getConnections().add(transition);
        process.getConnections().add(transition2);
        return process;
    }

    /**
     * @return
     */
    private MainProcess createStudioProcess(String processName) {
        MainProcess process = ProcessFactory.eINSTANCE.createMainProcess();
        process.setName(processName);
        process.setVersion(PROCESS_VERSION);
        Event start = ProcessFactory.eINSTANCE.createStartEvent();
        start.setName("start");
        Task task = ProcessFactory.eINSTANCE.createTask();
        task.setName(ACTIVITY_NAME);
        fail();
        //		task.setActorType(ActorType.USER);
        //		task.setUser("john");
        SequenceFlow transition = ProcessFactory.eINSTANCE.createSequenceFlow();
        transition.setName("transition");
        transition.setSource(start);
        transition.setTarget(task);
        Event end = ProcessFactory.eINSTANCE.createEndEvent();
        end.setName(END);
        SequenceFlow transition2 = ProcessFactory.eINSTANCE.createSequenceFlow();
        transition2.setName("transition2");
        transition2.setSource(task);
        transition2.setTarget(end);
        process.getElements().add(task);
        process.getElements().add(start);
        process.getElements().add(end);
        process.getConnections().add(transition);
        process.getConnections().add(transition2);
        return process;
    }

    public void testExportProcessWithInvalidTransistions() throws Exception {
        fail();
        //		URL url =  getClass().getResource("Required_Action--1.0.proc") ;
        //		File procFile = new File(FileLocator.toFileURL(url).getFile()) ;
        //	
        //		ProcessArtifact artifact = ImportBARModule.importProcOrBar(Display.getDefault().getActiveShell(), procFile) ;
        //		artifact.open();
        //		Map<String, String> param = new HashMap<String, String>() ;
        //		File destFile = new File(ProjectUtil.getBonitaStudioWorkFolder(),"testExport.bar") ;
        //		param.put(ExportAsBarFileCommand.DEST_FILE_PARAMETER_NAME,destFile.getAbsolutePath());
        //		ExportAsBarFileCommand cmd = new ExportAsBarFileCommand() ;
        //		ExecutionEvent event = new ExecutionEvent(param,null,null) ;
        //		new ExportAsBarFileCommand().execute(event) ;
        //		assertTrue("BAR not exporter", destFile.exists() && destFile.length() > 0) ;
        //		destFile.delete();
    }
}
