/**
 * Copyright (C) 2014 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.repository.Repository;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.document.refactoring.RefactorDocumentOperation;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.DocumentType;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;
import org.junit.Before;
import org.junit.Test;

/**
 * @author aurelie
 */
public class TestDocumentRefactoring {

    private final String diagramName = "DocumentRefactoring-1.0";

    private final String mainProcessName = "DocumentRefactoring";

    private final String newDocumentName = "myDocumentRefactored";

    private final String parameterName = "myDocument";

    private final String empty = "     ";

    private final DiagramRepositoryStore store = RepositoryManager.getInstance()
            .getRepositoryStore(DiagramRepositoryStore.class);

    private RepositoryAccessor repositoryAccessor;

    @Before
    public void init() {
        repositoryAccessor = new RepositoryAccessor();
        repositoryAccessor.init();
    }

    @Test
    public void testDocumentRefactoring() throws IOException, InvocationTargetException, InterruptedException {
        final MainProcess diagram = importDiagramAndOpen();
        final List<Pool> pools = ModelHelper.getChildrenProcess(diagram);
        assertEquals(pools.size(), 1);
        final Pool pool = pools.get(0);
        assertEquals(pool.getDocuments().size(), 1);
        refactorDocument(pool);

    }

    private void refactorDocument(final Pool pool) throws InvocationTargetException, InterruptedException {
        final Document documentToRefactor = pool.getDocuments().get(0);
        final RefactorDocumentOperation refactorOperation = new RefactorDocumentOperation(RefactoringOperationType.UPDATE);
        refactorOperation.setEditingDomain(TransactionUtil.getEditingDomain(pool));
        final Document newDocument = EcoreUtil.copy(documentToRefactor);
        newDocument.setName(newDocumentName);
        refactorOperation.addItemToRefactor(newDocument, documentToRefactor);
        final IProgressService service = PlatformUI.getWorkbench().getProgressService();
        service.busyCursorWhile(refactorOperation);
        final Document documentRefactored = pool.getDocuments().get(0);
        assertEquals(newDocument.getName(), documentRefactored.getName());
        testDocumentInExpressionsRefactored(documentRefactored, 2, pool);
        final Document currentDoc = refactorDocumentTypeAndMultiplicity(pool, documentRefactored);
        testRemoveDocumentRefactoring(currentDoc, pool);

    }

    private Document refactorDocumentTypeAndMultiplicity(final Pool pool, final Document document)
            throws InvocationTargetException, InterruptedException {
        final Document newDocument = EcoreUtil.copy(document);
        newDocument.setMultiple(true);
        newDocument.setDocumentType(DocumentType.EXTERNAL);
        final RefactorDocumentOperation refactorOperation = new RefactorDocumentOperation(RefactoringOperationType.UPDATE);
        refactorOperation.setEditingDomain(TransactionUtil.getEditingDomain(pool));
        refactorOperation.addItemToRefactor(newDocument, document);
        final IProgressService service = PlatformUI.getWorkbench().getProgressService();
        service.busyCursorWhile(refactorOperation);
        final Document documentRefactored = pool.getDocuments().get(0);
        assertEquals(newDocument.getName(), documentRefactored.getName());
        assertEquals(newDocument.getDocumentType(), documentRefactored.getDocumentType());
        return documentRefactored;

    }

    private void testDocumentInExpressionsRefactored(final Document document, final int numberOfExpression,
            final Pool pool) {
        final List<Task> tasks = ModelHelper.getAllItemsOfType(pool, ProcessPackage.Literals.TASK);
        assertEquals(1, tasks.size());
        final Task task = tasks.get(0);
        final Operation op = task.getOperations().get(0);
        final Expression expr = op.getRightOperand();
        assertEquals(document.getName(), ((Document) expr.getReferencedElements().get(0)).getName());
        assertEquals(document.getName(), expr.getContent());
        Document refDocument = ModelHelper.getDocumentReferencedInExpression(expr);
        assertEquals(document.getDocumentType(), refDocument.getDocumentType());
        final Form form = task.getForm().get(0);
        final FileWidget widget = (FileWidget) form.getWidgets().get(0);
        assertEquals(widget.getInputExpression().getName(), document.getName());
        final Operation outputOp = widget.getAction();
        assertEquals(document.getName(), outputOp.getLeftOperand().getContent());
        refDocument = ModelHelper.getDocumentReferencedInExpression(outputOp.getLeftOperand());
        assertEquals(document.getDocumentType(), refDocument.getDocumentType());
        assertEquals(document.getName(), outputOp.getRightOperand().getContent());
        refDocument = ModelHelper.getDocumentReferencedInExpression(outputOp.getRightOperand());
        assertEquals(document.getDocumentType(), refDocument.getDocumentType());
    }

    private void testRemoveDocumentRefactoring(final Document document, final Pool pool)
            throws InvocationTargetException, InterruptedException {
        final RefactorDocumentOperation refactorOperation = new RefactorDocumentOperation(RefactoringOperationType.REMOVE);
        refactorOperation.setEditingDomain(TransactionUtil.getEditingDomain(pool));
        refactorOperation.addItemToRefactor(null, document);
        final IProgressService service = PlatformUI.getWorkbench().getProgressService();
        service.busyCursorWhile(refactorOperation);
        assertTrue(pool.getDocuments().isEmpty());
        final List<Task> tasks = ModelHelper.getAllItemsOfType(pool, ProcessPackage.Literals.TASK);
        final Task step1 = tasks.get(0);
        final Operation op = step1.getOperations().get(0);
        final Expression expr = op.getRightOperand();
        assertEquals(expr.getContent(), empty);
        final Form form = step1.getForm().get(0);
        final FileWidget widget = (FileWidget) form.getWidgets().get(0);
        assertTrue(widget.getInputExpression().getName().isEmpty());
        final Operation outputOp = widget.getAction();
        assertTrue(outputOp.getLeftOperand().getContent().isEmpty());
        assertTrue(outputOp.getRightOperand().getContent().equals(empty));

    }

    public MainProcess importDiagramAndOpen() throws IOException, InvocationTargetException, InterruptedException {
        final ImportBosArchiveOperation op = new ImportBosArchiveOperation(repositoryAccessor);
        final URL fileURL1 = FileLocator.toFileURL(TestDocumentRefactoring.class.getResource(diagramName + ".bos")); //$NON-NLS-1$
        op.setArchiveFile(FileLocator.toFileURL(fileURL1).getFile());
        op.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        op.run(Repository.NULL_PROGRESS_MONITOR);
        final DiagramFileStore diagramFileStore = store.getChild(diagramName + ".proc");
        diagramFileStore.open();
        final MainProcess mainProcess = diagramFileStore.getContent();
        assertEquals(mainProcessName, mainProcess.getName());
        return mainProcess;
    }
}
