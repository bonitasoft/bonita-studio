package org.bonitasoft.studio.tests.searchindex;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.model.HumanTaskInstance;
import org.bonitasoft.engine.bpm.model.ProcessDefinition;
import org.bonitasoft.engine.bpm.model.ProcessInstance;
import org.bonitasoft.engine.exception.InvalidSessionException;
import org.bonitasoft.engine.exception.ProcessDefinitionNotEnabledException;
import org.bonitasoft.engine.exception.ProcessDefinitionNotFoundException;
import org.bonitasoft.engine.exception.ProcessDefinitionReadException;
import org.bonitasoft.engine.exception.ProcessInstanceCreationException;
import org.bonitasoft.engine.exception.ProcessInstanceNotFoundException;
import org.bonitasoft.engine.exception.ProcessInstanceReadException;
import org.bonitasoft.engine.exception.UserNotFoundException;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.command.RunProcessCommand;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.ui.PlatformUI;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RunSearchIndexTest extends TestCase{

	private HumanTaskInstance newTask;
	private APISession session;

	@Before
	public void setUp() {
		session = BOSEngineManager.getInstance().loginDefaultTenant(Repository.NULL_PROGRESS_MONITOR);
	}

	@After
	public void tearDown(){
		BOSEngineManager.getInstance().logoutDefaultTenant(session);
	}
	@Test
	public void testRunOnDiagramWithSearchIndex() throws IOException, UserNotFoundException, InvalidSessionException, ExecutionException, ProcessDefinitionNotFoundException, ProcessDefinitionReadException, ProcessDefinitionNotEnabledException, ProcessInstanceCreationException, ProcessInstanceNotFoundException, ProcessInstanceReadException{
		final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
		ImportBosArchiveOperation op = new ImportBosArchiveOperation();
		URL fileURL1 = FileLocator.toFileURL(RunSearchIndexTest.class.getResource("SearchIndex.bos")); //$NON-NLS-1$
		op.setArchiveFile(FileLocator.toFileURL(fileURL1).getFile());
		op.run(new NullProgressMonitor());
		ProcessDiagramEditor processEditor = (ProcessDiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
		MainProcess mainProcess = (MainProcess)processEditor.getDiagramEditPart().resolveSemanticElement();
		assertEquals("MyDiagram", mainProcess.getName());
		final SearchOptions searchOptions = new SearchOptionsBuilder(0, 10).done();
		//final List<HumanTaskInstance> tasks =processApi.searchPendingTasksForUser(session.getUserId(), searchOptions).getResult();
        final RunProcessCommand runProcessCommand = new RunProcessCommand(true);
        Map<String,Object> param = new HashMap<String, Object>();
        param.put(RunProcessCommand.PROCESS, mainProcess.getElements().get(0));
        ExecutionEvent ee = new ExecutionEvent(null,param,null,null);
        runProcessCommand.execute(ee);
        final String urlGivenToBrowser = runProcessCommand.getUrl().toString();
         assertFalse("The url contains null:"+urlGivenToBrowser, urlGivenToBrowser.contains("null"));
        long processId=processApi.getProcessDefinitionId("Pool", "1.0");
        final ProcessDefinition processDef = processApi.getProcessDefinition(processId);
        assertNotNull("processDef should not be null",processDef);
        ProcessInstance processInstance = processApi.startProcess(processApi.getProcessDefinitionId("Pool","1.0"));
        assertNotNull("processInstance of Pool should not be null",processInstance);
        assertNotNull("search index value should not be null",processInstance.getStringIndex1());
        assertEquals("MyIndex1",processInstance.getStringIndexLabel(0));
        assertEquals("MyValue1",processInstance.getStringIndex1());
	}
}
