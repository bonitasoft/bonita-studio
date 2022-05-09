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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.diagram.custom.commands.NewDiagramCommandHandler;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.document.refactoring.RefactorDocumentOperation;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.ExpressionPackage;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.assertions.ExpressionAssert;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.junit.Before;
import org.junit.Test;

public class RefactorDocumentOperationTest {

    private TransactionalEditingDomain domain;
    private Pool parentProcess;
    private Operation operation;
    private Expression leftOperand;
    private Document document;
    private static String INITIAL_DOC_NAME = "docName";

    @Before
    public void setUp() throws Exception {
        DiagramFileStore newDiagram = new NewDiagramCommandHandler().newDiagram();
        domain = TransactionUtil.getEditingDomain(newDiagram.getEMFResource());
        parentProcess = (Pool) newDiagram.getContent().getElements().get(0);
        document = ProcessFactory.eINSTANCE.createDocument();
        document.setName(INITIAL_DOC_NAME);
        document.setMimeType(ExpressionHelper.createConstantExpression("octet/stream", String.class.getName()));
        domain.getCommandStack().execute(new AddCommand(domain, parentProcess.getDocuments(), document));
    }

    private void createTaskWithOperationWithLeftOperandOfType(final String documentType) {
        final Task task = ProcessFactory.eINSTANCE.createTask();
        operation = ExpressionFactory.eINSTANCE.createOperation();
        createLeftOperandWithType(documentType);
        operation.setLeftOperand(leftOperand);
        task.getOperations().add(operation);
        domain.getCommandStack().execute(new AddCommand(domain, parentProcess.getElements(), task));
    }

    private void createLeftOperandWithType(final String documentType) {
        leftOperand = ExpressionFactory.eINSTANCE.createExpression();
        leftOperand.setName(document.getName());
        leftOperand.setContent(document.getName());
        if (ExpressionConstants.DOCUMENT_TYPE.equals(documentType)) {
            leftOperand.setType(documentType);
            leftOperand.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(document));
        } else {
            leftOperand.setType(documentType);
        }
    }

    @Test
    public void testDocumentUpdatedWhenRenamingDocument() throws InvocationTargetException, InterruptedException {
        createTaskWithOperationWithLeftOperandOfType(ExpressionConstants.DOCUMENT_TYPE);
        final Document updatedDocument = ProcessFactory.eINSTANCE.createDocument();
        updatedDocument.setName("docNameUpdated");
        updatedDocument.setMimeType(ExpressionHelper.createConstantExpression("octet/stream", String.class.getName()));
        final RefactorDocumentOperation rdo = new RefactorDocumentOperation(RefactoringOperationType.UPDATE);
        rdo.addItemToRefactor(updatedDocument, document);
        rdo.setEditingDomain(domain);
        rdo.setAskConfirmation(false);
        rdo.run(null);

        assertThat(parentProcess.getDocuments()).hasSize(1);
        assertEquals("docNameUpdated", parentProcess.getDocuments().get(0).getName());
        ExpressionAssert.assertThat(leftOperand).hasName(updatedDocument.getName());
        ExpressionAssert.assertThat(operation.getLeftOperand())
                .hasReturnType(org.bonitasoft.engine.bpm.document.Document.class.getName());

        domain.getCommandStack().undo();
        assertThat(parentProcess.getDocuments()).hasSize(1);
        assertEquals(parentProcess.getDocuments().get(0).getName(), INITIAL_DOC_NAME);
        ExpressionAssert.assertThat(leftOperand).hasName(INITIAL_DOC_NAME);
        ExpressionAssert.assertThat(operation.getLeftOperand()).hasReturnType(String.class.getName());
    }

    @Test
    public void testDocumentUpdatedWhenDeletingDocument() throws InvocationTargetException, InterruptedException {
        createTaskWithOperationWithLeftOperandOfType(ExpressionConstants.DOCUMENT_TYPE);
        final RefactorDocumentOperation rdo = new RefactorDocumentOperation(RefactoringOperationType.REMOVE);
        rdo.addItemToRefactor(null, document);
        rdo.setEditingDomain(domain);
        rdo.setAskConfirmation(false);
        rdo.run(null);

        assertThat(parentProcess.getDocuments()).hasSize(0);
        ExpressionAssert.assertThat(leftOperand).hasName("");
        ExpressionAssert.assertThat(leftOperand).hasType(ExpressionConstants.CONSTANT_TYPE);

        domain.getCommandStack().undo();
        assertThat(parentProcess.getDocuments()).hasSize(1);
        assertEquals(parentProcess.getDocuments().get(0).getName(), INITIAL_DOC_NAME);
        ExpressionAssert.assertThat(leftOperand).hasName(INITIAL_DOC_NAME);
    }

    @Test
    public void testDeleteDocumentBasic() throws InvocationTargetException, InterruptedException {
        final RefactorDocumentOperation rdo = new RefactorDocumentOperation(RefactoringOperationType.REMOVE);
        rdo.addItemToRefactor(null, document);
        rdo.setEditingDomain(domain);
        rdo.setAskConfirmation(false);
        rdo.run(null);

        assertThat(parentProcess.getDocuments()).hasSize(0);

        domain.getCommandStack().undo();
        assertThat(parentProcess.getDocuments()).hasSize(1);
    }

    @Test
    public void testDeleteSeveralDocumentBasic() throws InvocationTargetException, InterruptedException {

        final Document secondDocument = ProcessFactory.eINSTANCE.createDocument();
        secondDocument.setName("secondDocument");
        secondDocument.setMimeType(ExpressionHelper.createConstantExpression("octet/stream", String.class.getName()));
        domain.getCommandStack().execute(new AddCommand(domain, parentProcess.getDocuments(), secondDocument));

        final RefactorDocumentOperation rdo = new RefactorDocumentOperation(RefactoringOperationType.REMOVE);
        rdo.addItemToRefactor(null, document);
        rdo.addItemToRefactor(null, secondDocument);
        rdo.setEditingDomain(domain);
        rdo.setAskConfirmation(false);
        rdo.run(null);

        assertThat(parentProcess.getDocuments()).hasSize(0);

        domain.getCommandStack().undo();
        assertThat(parentProcess.getDocuments()).hasSize(2);
    }

    /**
     * Case of File Widget initial value
     *
     * @throws InvocationTargetException
     * @throws InterruptedException
     */
    @Test
    public void testDocumentRefUpdatedWhenRenamingDocument() throws InvocationTargetException, InterruptedException {
        createTaskWithOperationWithLeftOperandOfType(ExpressionConstants.DOCUMENT_REF_TYPE);
        final Document updatedDocument = ProcessFactory.eINSTANCE.createDocument();
        updatedDocument.setName("docNameUpdated");
        updatedDocument.setMimeType(ExpressionHelper.createConstantExpression("octet/stream", String.class.getName()));
        final RefactorDocumentOperation rdo = new RefactorDocumentOperation(RefactoringOperationType.UPDATE);
        rdo.addItemToRefactor(updatedDocument, document);
        rdo.setEditingDomain(domain);
        rdo.setAskConfirmation(false);
        rdo.run(null);

        assertThat(parentProcess.getDocuments()).hasSize(1);
        assertEquals("docNameUpdated", parentProcess.getDocuments().get(0).getName());
        ExpressionAssert.assertThat(leftOperand).hasName(updatedDocument.getName());
        ExpressionAssert.assertThat(leftOperand).hasReturnType(String.class.getName());
    }

    /**
     * Case of File Widget initial value
     *
     * @throws InvocationTargetException
     * @throws InterruptedException
     */
    @Test
    public void testDocumentRefUpdatedWhenDeletingDocument() throws InvocationTargetException, InterruptedException {
        createTaskWithOperationWithLeftOperandOfType(ExpressionConstants.DOCUMENT_REF_TYPE);
        final RefactorDocumentOperation rdo = new RefactorDocumentOperation(RefactoringOperationType.UPDATE);
        rdo.addItemToRefactor(null, document);
        rdo.setEditingDomain(domain);
        rdo.setAskConfirmation(false);
        rdo.run(null);

        assertThat(parentProcess.getDocuments()).hasSize(0);
        ExpressionAssert.assertThat(leftOperand).hasType(ExpressionConstants.CONSTANT_TYPE);
        ExpressionAssert.assertThat(leftOperand).hasContent("");
        ExpressionAssert.assertThat(leftOperand).hasNoReferencedElements();

        domain.getCommandStack().undo();
        assertThat(parentProcess.getDocuments()).hasSize(1);
        ExpressionAssert.assertThat(leftOperand).hasContent(INITIAL_DOC_NAME);
    }

    /**
     * Case of some Operations
     *
     * @throws InvocationTargetException
     * @throws InterruptedException
     */
    @Test
    public void testDocumentStringWhenRenamingDocument() throws InvocationTargetException, InterruptedException {
        createTaskWithOperationWithLeftOperandOfType(ExpressionConstants.CONSTANT_TYPE);
        final Document updatedDocument = ProcessFactory.eINSTANCE.createDocument();
        updatedDocument.setName("docNameUpdated");
        updatedDocument.setMimeType(ExpressionHelper.createConstantExpression("octet/stream", String.class.getName()));
        final RefactorDocumentOperation rdo = new RefactorDocumentOperation(RefactoringOperationType.UPDATE);
        rdo.addItemToRefactor(updatedDocument, document);
        rdo.setEditingDomain(domain);
        rdo.setAskConfirmation(false);
        rdo.run(null);

        assertThat(parentProcess.getDocuments()).hasSize(1);
        assertEquals("docNameUpdated", parentProcess.getDocuments().get(0).getName());
        ExpressionAssert.assertThat(leftOperand).hasName(updatedDocument.getName());
    }

    /**
     * Case of some Operations
     *
     * @throws InvocationTargetException
     * @throws InterruptedException
     */
    @Test
    public void testDocumentStringWhenDeletingDocument() throws InvocationTargetException, InterruptedException {
        createTaskWithOperationWithLeftOperandOfType(ExpressionConstants.CONSTANT_TYPE);

        final RefactorDocumentOperation rdo = new RefactorDocumentOperation(RefactoringOperationType.REMOVE);
        rdo.addItemToRefactor(null, document);
        rdo.setEditingDomain(domain);
        rdo.setAskConfirmation(false);
        rdo.run(null);

        assertThat(parentProcess.getDocuments()).hasSize(0);
        ExpressionAssert.assertThat(leftOperand).hasNoReferencedElements();
        ExpressionAssert.assertThat(leftOperand).hasContent("");

        domain.getCommandStack().undo();
        assertThat(parentProcess.getDocuments()).hasSize(1);
        ExpressionAssert.assertThat(leftOperand).hasContent(INITIAL_DOC_NAME);
    }

    @Test
    public void testMIMETypeUpdatedWhenEdited() throws InvocationTargetException, InterruptedException {
        createTaskWithOperationWithLeftOperandOfType(ExpressionConstants.DOCUMENT_TYPE);
        final Document updatedDocument = ProcessFactory.eINSTANCE.createDocument();
        updatedDocument.setName("docName");
        updatedDocument.setMimeType(ExpressionHelper.createConstantExpression("pdf", String.class.getName()));
        final RefactorDocumentOperation rdo = new RefactorDocumentOperation(RefactoringOperationType.UPDATE);
        rdo.addItemToRefactor(updatedDocument, document);
        rdo.setEditingDomain(domain);
        rdo.setAskConfirmation(false);
        rdo.run(null);

        assertThat(parentProcess.getDocuments()).hasSize(1);
        assertEquals("pdf", parentProcess.getDocuments().get(0).getMimeType().getName());
        ExpressionAssert.assertThat(leftOperand).hasName(updatedDocument.getName());
    }

    @Test
    public void testDocumentExpressionUpdatedWhenRenamingDocument() throws InvocationTargetException, InterruptedException {
        createTaskWithOperationWithLeftOperandOfType(ExpressionConstants.DOCUMENT_TYPE);
        final Document updatedDocument = ProcessFactory.eINSTANCE.createDocument();
        updatedDocument.setName("docNameUpdated");
        updatedDocument.setMimeType(ExpressionHelper.createConstantExpression("octet/stream", String.class.getName()));
        final RefactorDocumentOperation rdo = new RefactorDocumentOperation(RefactoringOperationType.UPDATE);
        rdo.addItemToRefactor(updatedDocument, document);
        rdo.setEditingDomain(domain);
        rdo.setAskConfirmation(false);
        rdo.run(null);

        ExpressionAssert.assertThat(leftOperand).hasName(updatedDocument.getName());

    }

    @Test
    public void testPatternExpressionWhenRenamingDocument() throws InvocationTargetException, InterruptedException {
        createTaskWithOperationWithLeftOperandOfType(ExpressionConstants.DOCUMENT_TYPE);
        final Expression rightOperand = ExpressionFactory.eINSTANCE.createExpression();
        rightOperand.setName("ManipulateDocument");
        rightOperand.setContent("hello ${" + document.getName() + "}");
        rightOperand.setType(ExpressionConstants.PATTERN_TYPE);
        rightOperand.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(document));
        domain.getCommandStack().execute(
                new SetCommand(domain, operation, ExpressionPackage.Literals.OPERATION__RIGHT_OPERAND, rightOperand));
        final Document updatedDocument = ProcessFactory.eINSTANCE.createDocument();
        updatedDocument.setName("docNameUpdated");
        updatedDocument.setMimeType(ExpressionHelper.createConstantExpression("octet/stream", String.class.getName()));
        final RefactorDocumentOperation rdo = new RefactorDocumentOperation(RefactoringOperationType.UPDATE);
        rdo.addItemToRefactor(updatedDocument, document);
        rdo.setEditingDomain(domain);
        rdo.setAskConfirmation(false);
        rdo.run(null);

        ExpressionAssert.assertThat(leftOperand).hasName(updatedDocument.getName());
        ExpressionAssert.assertThat(rightOperand).hasContent("hello ${" + updatedDocument.getName() + "}");
        final EList<EObject> referencedElements = rightOperand.getReferencedElements();
        assertThat(referencedElements).hasSize(1);
        final EObject dep = referencedElements.get(0);
        assertThat(dep).isInstanceOf(Document.class);
        assertThat(((Document) dep).getName()).isEqualTo(updatedDocument.getName());
    }

    @Test
    public void testMultiplicityUpdatedInReference() throws InvocationTargetException, InterruptedException {
        createTaskWithOperationWithLeftOperandOfType(ExpressionConstants.DOCUMENT_TYPE);
        final Document documentMultiple = ProcessFactory.eINSTANCE.createDocument();
        documentMultiple.setName(INITIAL_DOC_NAME);
        documentMultiple.setMimeType(ExpressionHelper.createConstantExpression("octet/stream", String.class.getName()));
        documentMultiple.setMultiple(true);

        final RefactorDocumentOperation rdo = new RefactorDocumentOperation(RefactoringOperationType.UPDATE);
        rdo.addItemToRefactor(documentMultiple, document);
        rdo.setEditingDomain(domain);
        rdo.setAskConfirmation(false);
        rdo.run(null);

        final Expression leftOperandUpdated = operation.getLeftOperand();
        final Document referencedDocument = (Document) leftOperandUpdated.getReferencedElements().get(0);
        assertThat(referencedDocument.isMultiple()).isTrue();
        ExpressionAssert.assertThat(leftOperandUpdated).hasReturnType(List.class.getName());
    }

}
