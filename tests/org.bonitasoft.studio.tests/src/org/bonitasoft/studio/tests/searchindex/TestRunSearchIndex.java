package org.bonitasoft.studio.tests.searchindex;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.bonitasoft.engine.api.ProcessAPI;
import org.bonitasoft.engine.bpm.process.ProcessDefinition;
import org.bonitasoft.engine.bpm.process.ProcessInstance;
import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.engine.BOSEngineManager;
import org.bonitasoft.studio.engine.command.RunProcessCommand;
import org.bonitasoft.studio.engine.operation.ProcessSelector;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.core.runtime.FileLocator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestRunSearchIndex {

    private APISession session;

    private final DiagramRepositoryStore store = RepositoryManager.getInstance()
            .getRepositoryStore(DiagramRepositoryStore.class);

    RepositoryAccessor repositoryAccessor;

    @Before
    public void init() {
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
    }

    @After
    public void tearDown() {
        if (session != null) {
            BOSEngineManager.getInstance().logoutDefaultTenant(session);
        }
    }

    @Test
    public void testRunOnDiagramWithSearchIndex() throws Exception {
        session = BOSEngineManager.getInstance().loginDefaultTenant(AbstractRepository.NULL_PROGRESS_MONITOR);
        final ProcessAPI processApi = BOSEngineManager.getInstance().getProcessAPI(session);
        final ImportBosArchiveOperation op = new ImportBosArchiveOperation(repositoryAccessor);
        final URL fileURL1 = FileLocator.toFileURL(TestRunSearchIndex.class.getResource("SearchIndex-1.0.bos")); //$NON-NLS-1$
        op.setArchiveFile(FileLocator.toFileURL(fileURL1).getFile());
        op.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        op.run(AbstractRepository.NULL_PROGRESS_MONITOR);

        final MainProcess mainProcess = store.getChild("SearchIndex-1.0.proc", true).getContent();
        assertEquals("SearchIndex", mainProcess.getName());

        final RunProcessCommand runProcessCommand = new RunProcessCommand(true);
        runProcessCommand.execute(ProcessSelector.createExecutionEvent((AbstractProcess) mainProcess.getElements().get(0)));

        assertNotNull("The url is null:", runProcessCommand.getUrl());
        final String urlGivenToBrowser = runProcessCommand.getUrl().toString();
        assertFalse("The url contains null:" + urlGivenToBrowser, urlGivenToBrowser.contains("null"));

        final long processId = processApi.getProcessDefinitionId("Search", "1.0");
        final ProcessDefinition processDef = processApi.getProcessDefinition(processId);
        assertNotNull("processDef should not be null", processDef);
        final ProcessInstance processInstance = processApi.startProcess(processId);

        assertNotNull("processInstance of Pool should not be null", processInstance);
        assertNotNull("search index value should not be null", processInstance.getStringIndex1());
        assertEquals("MyIndex1", processInstance.getStringIndexLabel(1));
        assertEquals("MyValue1", processInstance.getStringIndex1());
    }
}
