/*******************************************************************************
 * Copyright (C) 2013-2014 Bonitasoft S.A.
 * BonitaSoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.tests.searchindex;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;

import org.bonitasoft.studio.application.actions.SaveCommandHandler;
import org.bonitasoft.studio.application.actions.UndoCommandHandler;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SearchIndex;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.properties.sections.index.RefactorSearchIndexOperation;
import org.bonitasoft.studio.properties.sections.index.RemoveSearchReferencesOperation;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;
import org.junit.After;
import org.junit.Test;

public class TestSearchIndexRefactoring {

    private final String diagramName = "searchIndexRefactoringTest-1.0.bos";

    private final String mainProcessName = "searchIndexRefactoringTest";

    private final String searchIndex1 = "search1";

    private final String searchIndex2 = "search2";

    private final String newSearchIndexValue = "searchRefactored";

    private final DiagramRepositoryStore store = RepositoryManager.getInstance()
            .getRepositoryStore(DiagramRepositoryStore.class);

    @Test
    public void testSearchIndexRefactoring()
            throws IOException, InvocationTargetException, InterruptedException, ExecutionException, ReadFileStoreException {
        final MainProcess mainProcess = importDiagramAndOpen();
        final Pool pool = getPool(mainProcess);
        final List<SearchIndex> searchIndexes = getSearchIndexes(pool);
        final List<Operation> operations = getOperationsOnTask(pool);
        refactorSearchIndex(pool, searchIndexes, operations);
        undoLastSearchIndexRefactoring(searchIndexes, operations);
        removeSearchIndexReferencesOperation(pool, searchIndexes, operations);

    }

    private MainProcess importDiagramAndOpen() throws IOException, InvocationTargetException, InterruptedException, ReadFileStoreException {
        RepositoryAccessor repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
        final ImportBosArchiveOperation op = new ImportBosArchiveOperation(repositoryAccessor);
        final URL fileURL1 = FileLocator.toFileURL(TestRunSearchIndex.class.getResource(diagramName));
        op.setArchiveFile(FileLocator.toFileURL(fileURL1).getFile());
        op.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        op.run(AbstractRepository.NULL_PROGRESS_MONITOR);
        final DiagramFileStore diagramFileStore = store.getChild("searchIndexRefactoringTest-1.0.proc", true);
        diagramFileStore.open();
        final MainProcess mainProcess = diagramFileStore.getContent();
        assertEquals(mainProcessName, mainProcess.getName());
        return mainProcess;
    }

    private Pool getPool(final MainProcess mainProcess) {
        final List<EObject> pools = ModelHelper.getAllItemsOfType(mainProcess, ProcessPackage.Literals.POOL);
        assertEquals("only one pool is defined in the diagram" + mainProcessName, 1, pools.size());
        return (Pool) pools.get(0);
    }

    private List<SearchIndex> getSearchIndexes(final Pool pool) {
        final List<SearchIndex> searchIndexes = pool.getSearchIndexes();
        assertEquals("the number of searchIndexes imported is not correct", 5, searchIndexes.size());
        return searchIndexes;
    }

    private List<Operation> getOperationsOnTask(final Pool pool) {
        final List<EObject> tasks = ModelHelper.getAllItemsOfType(pool, ProcessPackage.Literals.TASK);
        assertEquals("there should be only one task defined on the pool", 1, tasks.size());
        final Task task = (Task) tasks.get(0);
        final List<Operation> operations = task.getOperations();
        assertEquals("two operations should be defined on " + task.getName(), 2, operations.size());
        return operations;
    }

    private void refactorSearchIndex(final Pool pool, final List<SearchIndex> searchIndexes,
            final List<Operation> operations)
            throws InvocationTargetException,
            InterruptedException {
        final SearchIndex searchIndexToRefactor = searchIndexes.get(0);
        final String oldName = searchIndexToRefactor.getName().getName();
        final String searchIndex2Name = searchIndexes.get(1).getName().getName();
        final Operation operationUsingSearchIndex = operations.get(0);
        final RefactorSearchIndexOperation refactorOperation = new RefactorSearchIndexOperation(pool);
        final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(pool);
        refactorOperation.setEditingDomain(editingDomain);
        final SearchIndex copy = EcoreUtil.copy(searchIndexToRefactor);
        copy.getName().setContent(newSearchIndexValue);
        copy.getName().setName(newSearchIndexValue);
        refactorOperation.addItemToRefactor(copy, searchIndexToRefactor);
        refactorOperation.run(AbstractRepository.NULL_PROGRESS_MONITOR);
        final String newLeftOperandValue = operationUsingSearchIndex.getLeftOperand().getContent();
        assertEquals("refactoring of " + oldName + " was not completed correctly", newSearchIndexValue, newLeftOperandValue);
        assertEquals("the value of " + searchIndex2Name + " should not have changed", searchIndex2Name,
                searchIndexes.get(1).getName().getName());
        final String leftOperandValueofOperation2 = operations.get(1).getLeftOperand().getContent();
        assertEquals("the value of " + searchIndex2Name + " should not have changed in operation on step1", searchIndex2Name,
                leftOperandValueofOperation2);
    }

    private void undoLastSearchIndexRefactoring(final List<SearchIndex> searchIndexes, final List<Operation> operations)
            throws ExecutionException {

        final SearchIndex searchIndexRefactoredToUndo = searchIndexes.get(0);
        final SearchIndex searchIndexNotRefactored = searchIndexes.get(1);
        final Operation operationUsingSearchIndex = operations.get(0);
        final UndoCommandHandler undoCommand = new UndoCommandHandler();
        undoCommand.execute(new ExecutionEvent());
        assertEquals("the undo operation on search index refactoring wasn't executed correctly", searchIndex1,
                searchIndexRefactoredToUndo.getName().getName());
        assertEquals("the undo operation on search index references refactoring wasn't executed correctly", searchIndex1,
                operationUsingSearchIndex
                        .getLeftOperand().getName());
        assertEquals("the undo operation affected other search index", searchIndex2,
                searchIndexNotRefactored.getName().getName());
        assertEquals("the undo operation affected other search index in operation", searchIndex2,
                operations.get(1).getLeftOperand().getName());

    }

    private void removeSearchIndexReferencesOperation(final Pool pool, final List<SearchIndex> searchIndexes,
            final List<Operation> operations)
            throws InvocationTargetException,
            InterruptedException {
        final SearchIndex searchIndexToRemove = searchIndexes.get(0);
        final Operation operationUsingSearchIndexToRemove = operations.get(0);
        final RemoveSearchReferencesOperation removeOperation = new RemoveSearchReferencesOperation(pool,
                searchIndexToRemove);
        final IProgressService service = PlatformUI.getWorkbench().getProgressService();
        removeOperation.setEditingDomain(TransactionUtil.getEditingDomain(pool));
        service.busyCursorWhile(removeOperation);
        // assertEquals("search index was not remove correctly","",searchIndexToRemove.getName().getName());
        assertEquals("search index reference in operation was not remove correctly", "",
                operationUsingSearchIndexToRemove.getLeftOperand().getName());

    }

    @After
    public void tearDown() {
        final SaveCommandHandler saveCommand = new SaveCommandHandler();
        saveCommand.execute(new ExecutionEvent());
    }

}
