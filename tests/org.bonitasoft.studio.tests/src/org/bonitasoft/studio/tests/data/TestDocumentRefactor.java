/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.tests.data;

import static org.junit.Assert.assertEquals;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.document.refactoring.RefactorDocumentOperation;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Document;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.util.ProcessAdapterFactory;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalCommandStackImpl;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.junit.Test;

public class TestDocumentRefactor {

    private Document document;

    @Test
    public void testRenameWithReferenceInScriptOperation() throws Exception {
        final String initialDocumentName = "initialDocumentName";
        final Pool process = createProcessWithDocument(initialDocumentName);

        final Activity activity = (Activity) process.getElements().get(0);
        final Operation operationWithScriptUsingData = ExpressionFactory.eINSTANCE.createOperation();
        final Operator assignOperator = ExpressionFactory.eINSTANCE.createOperator();
        assignOperator.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
        operationWithScriptUsingData.setOperator(assignOperator);
        final Expression scriptUsingDocument = ExpressionFactory.eINSTANCE.createExpression();
        scriptUsingDocument.setType(ExpressionConstants.SCRIPT_TYPE);
        scriptUsingDocument.setName("ScriptUsingDocument");
        scriptUsingDocument.setContent(document.getName());
        scriptUsingDocument.getReferencedElements().add(EcoreUtil.copy(document));
        scriptUsingDocument.setReturnType(String.class.getName());
        operationWithScriptUsingData.setRightOperand(scriptUsingDocument);
        activity.getOperations().add(operationWithScriptUsingData);
        process.getElements().add(activity);

        final String newDocumentName = "updatedDocumentName";
        final Document newDocument = ProcessFactory.eINSTANCE.createDocument();
        newDocument.setName(newDocumentName);

        final RefactorDocumentOperation rdo = new RefactorDocumentOperation(RefactoringOperationType.UPDATE);
        final TransactionalEditingDomain editingDomain = createEditingDomain();
        rdo.setEditingDomain(editingDomain);
        rdo.setAskConfirmation(false);
        rdo.addItemToRefactor(newDocument, document);

        rdo.run(new NullProgressMonitor());

        assertEquals("There are too many documents. The old one might not be removed.", 1, process.getDocuments().size());
        assertEquals("Document has not been renamed", newDocumentName, process.getDocuments().get(0).getName());
        assertEquals("Document name has not been updated correctly in expression of right operand operation",
                newDocumentName, scriptUsingDocument.getContent());
        assertEquals("Document name has not been updated correctly in expression of right operand operation",
                newDocumentName, ((Document) scriptUsingDocument
                        .getReferencedElements().get(0)).getName());

        editingDomain.getCommandStack().undo();

        assertEquals("There are too many documents. The old one might not be removed.", 1, process.getDocuments().size());
        assertEquals("Document has not been renamed after undo", initialDocumentName,
                process.getDocuments().get(0).getName());
        assertEquals("Document name has not been updated correctly in expression of right operand operation after undo",
                initialDocumentName,
                scriptUsingDocument.getContent());
        assertEquals("Document name has not been updated correctly in expression of right operand operation after undo",
                initialDocumentName,
                ((Document) scriptUsingDocument
                        .getReferencedElements().get(0)).getName());

    }

    private Pool createProcessWithDocument(final String documentName) {
        final Pool pool = ProcessFactory.eINSTANCE.createPool();
        document = ProcessFactory.eINSTANCE.createDocument();
        document.setName(documentName);
        document.setMimeType(ExpressionHelper.createConstantExpression("octet/stream", String.class.getName()));
        pool.getDocuments().add(document);

        final Activity activity = ProcessFactory.eINSTANCE.createActivity();
        pool.getElements().add(activity);
        return pool;
    }

    private TransactionalEditingDomain createEditingDomain() {
        final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
                ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        adapterFactory.addAdapterFactory(new ProcessAdapterFactory());

        // command stack that will notify this editor as commands are executed
        final TransactionalCommandStackImpl commandStack = new TransactionalCommandStackImpl();

        // Create the editing domain with our adapterFactory and command stack.
        return new TransactionalEditingDomainImpl(adapterFactory, commandStack);
    }
}
