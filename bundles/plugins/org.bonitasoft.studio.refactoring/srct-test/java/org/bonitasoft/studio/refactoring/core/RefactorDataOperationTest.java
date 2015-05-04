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
package org.bonitasoft.studio.refactoring.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.model.edit.custom.process.CustomProcessItemProviderAdapterFactory;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.assertions.ExpressionAssert;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.BooleanType;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalCommandStackImpl;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Romain Bioteau
 */
public class RefactorDataOperationTest {

    private AbstractProcess parentProcess;
    private Data myData;
    private Expression leftOperand;
    private Expression rightOperand;
    private TransactionalEditingDomain domain;
    private Operation operation;
    private final BooleanType initialBooleanDataType = ProcessFactory.eINSTANCE.createBooleanType();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        domain = new TransactionalEditingDomainImpl(new CustomProcessItemProviderAdapterFactory(), new TransactionalCommandStackImpl());
        parentProcess = ProcessFactory.eINSTANCE.createPool();
        myData = ProcessFactory.eINSTANCE.createData();
        myData.setName("myData");
        myData.setDataType(initialBooleanDataType);
        parentProcess.getData().add(myData);
        final Task task = ProcessFactory.eINSTANCE.createTask();
        operation = ExpressionFactory.eINSTANCE.createOperation();
        leftOperand = ExpressionFactory.eINSTANCE.createExpression();
        leftOperand.setName(myData.getName());
        leftOperand.setContent(myData.getName());
        leftOperand.setType(ExpressionConstants.VARIABLE_TYPE);
        leftOperand.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(myData));
        operation.setLeftOperand(leftOperand);
        operation.setRightOperand(rightOperand);
        task.getOperations().add(operation);
        parentProcess.getElements().add(task);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_run_refactor_data_in_pattern_expression() throws Exception {
        rightOperand = ExpressionFactory.eINSTANCE.createExpression();
        rightOperand.setName("getData");
        rightOperand.setContent("hello ${" + myData.getName() + "}");
        rightOperand.setType(ExpressionConstants.PATTERN_TYPE);
        rightOperand.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(myData));
        operation.setRightOperand(rightOperand);

        final Data newData = ProcessFactory.eINSTANCE.createData();
        newData.setName("refactored");
        final RefactorDataOperation refacorDataOperation = new RefactorDataOperation(RefactoringOperationType.UPDATE);
        refacorDataOperation.setAskConfirmation(false);// Skip UI
        refacorDataOperation.setEditingDomain(domain);
        refacorDataOperation.setDataContainer(parentProcess);
        refacorDataOperation.addItemToRefactor(newData, myData);
        refacorDataOperation.run(null);
        ExpressionAssert.assertThat(leftOperand).hasName(newData.getName());
        ExpressionAssert.assertThat(rightOperand).hasContent("hello ${" + newData.getName() + "}");
        final EList<EObject> referencedElements = rightOperand.getReferencedElements();
        assertThat(referencedElements).hasSize(1);
        final EObject dep = referencedElements.get(0);
        assertThat(dep).isInstanceOf(Data.class);
        assertThat(((Data) dep).getName()).isEqualTo("refactored");
    }

    @Test
    public void testDeleteData() throws Exception {
        rightOperand = ExpressionFactory.eINSTANCE.createExpression();
        rightOperand.setName("getData");
        rightOperand.setContent("hello ${" + myData.getName() + "}");
        rightOperand.setType(ExpressionConstants.PATTERN_TYPE);
        rightOperand.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(myData));
        operation.setRightOperand(rightOperand);

        final RefactorDataOperation refacorDataOperation = new RefactorDataOperation(RefactoringOperationType.REMOVE);
        refacorDataOperation.setAskConfirmation(false);// Skip UI
        refacorDataOperation.setEditingDomain(domain);
        refacorDataOperation.setDataContainer(parentProcess);
        refacorDataOperation.addItemToRefactor(null, myData);
        refacorDataOperation.run(null);
        ExpressionAssert.assertThat(rightOperand).hasContent("hello ${     }");
        final EList<EObject> referencedElements = rightOperand.getReferencedElements();
        assertThat(referencedElements).hasSize(0);
        assertThat(parentProcess.getData()).isEmpty();

    }

    @Test
    public void testTypeUpdateForExpressionDataWithReturnedTypeNotFixed() throws Exception {
        rightOperand = ExpressionFactory.eINSTANCE.createExpression();
        rightOperand.setName(myData.getName());
        rightOperand.setContent(myData.getName());
        rightOperand.setType(ExpressionConstants.VARIABLE_TYPE);
        rightOperand.setReturnType(Boolean.class.getName());
        rightOperand.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(myData));
        operation.setRightOperand(rightOperand);

        final Data newData = ProcessFactory.eINSTANCE.createData();
        newData.setName("refactored");
        newData.setDataType(ProcessFactory.eINSTANCE.createStringType());

        final RefactorDataOperation refacorDataOperation = new RefactorDataOperation(RefactoringOperationType.UPDATE);
        refacorDataOperation.setAskConfirmation(false);// Skip UI
        refacorDataOperation.setEditingDomain(domain);
        refacorDataOperation.setDataContainer(parentProcess);
        refacorDataOperation.setDataContainer(parentProcess);
        refacorDataOperation.setDataContainmentFeature(ProcessPackage.Literals.DATA_AWARE__DATA);
        refacorDataOperation.addItemToRefactor(newData, myData);
        refacorDataOperation.run(null);
        ExpressionAssert.assertThat(leftOperand).hasName(newData.getName());
        ExpressionAssert.assertThat(rightOperand).hasContent(newData.getName());
        ExpressionAssert.assertThat(rightOperand).hasReturnType(String.class.getName());
        final EList<EObject> referencedElements = rightOperand.getReferencedElements();
        assertThat(referencedElements).hasSize(1);
        final EObject dep = referencedElements.get(0);
        assertThat(dep).isInstanceOf(Data.class);
        assertThat(((Data) dep).getName()).isEqualTo("refactored");
    }

    @Test
    public void testTypeUpdateForExpressionDataWithReturnedTypeFixed() throws Exception {
        rightOperand = ExpressionFactory.eINSTANCE.createExpression();
        rightOperand.setName(myData.getName());
        rightOperand.setContent(myData.getName());
        rightOperand.setType(ExpressionConstants.VARIABLE_TYPE);
        rightOperand.setReturnTypeFixed(true);
        rightOperand.setReturnType(Boolean.class.getName());
        rightOperand.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(myData));
        operation.setRightOperand(rightOperand);

        final Data newData = ProcessFactory.eINSTANCE.createData();
        newData.setName("refactored");
        newData.setDataType(ProcessFactory.eINSTANCE.createStringType());

        final RefactorDataOperation refacorDataOperation = new RefactorDataOperation(RefactoringOperationType.UPDATE);
        refacorDataOperation.setAskConfirmation(false);// Skip UI
        refacorDataOperation.setEditingDomain(domain);
        refacorDataOperation.setDataContainer(parentProcess);
        refacorDataOperation.setDataContainmentFeature(ProcessPackage.Literals.DATA_AWARE__DATA);
        refacorDataOperation.addItemToRefactor(newData, myData);
        refacorDataOperation.setUpdateDataReferences(true);
        refacorDataOperation.run(null);
        ExpressionAssert.assertThat(rightOperand)
                .hasContent("")
                .hasName("")
                .hasReturnType(Boolean.class.getName());
        final EList<EObject> referencedElements = rightOperand.getReferencedElements();
        assertThat(referencedElements).hasSize(0);
    }

    @Test
    public void testNameUpdateForExpressionDataWithReturnedTypeFixed() throws Exception {
        rightOperand = ExpressionFactory.eINSTANCE.createExpression();
        rightOperand.setName(myData.getName());
        rightOperand.setContent(myData.getName());
        rightOperand.setType(ExpressionConstants.VARIABLE_TYPE);
        rightOperand.setReturnType(Boolean.class.getName());
        rightOperand.setReturnTypeFixed(true);
        rightOperand.getReferencedElements().add(ExpressionHelper.createDependencyFromEObject(myData));
        operation.setRightOperand(rightOperand);

        final Data newData = ProcessFactory.eINSTANCE.createData();
        newData.setName("refactored");
        newData.setDataType(myData.getDataType());

        final RefactorDataOperation refacorDataOperation = new RefactorDataOperation(RefactoringOperationType.UPDATE);
        refacorDataOperation.setAskConfirmation(false);// Skip UI
        refacorDataOperation.setEditingDomain(domain);
        refacorDataOperation.setDataContainer(parentProcess);
        refacorDataOperation.addItemToRefactor(newData, myData);
        refacorDataOperation.run(null);
        ExpressionAssert.assertThat(leftOperand).hasName(newData.getName());
        ExpressionAssert.assertThat(rightOperand).hasContent(newData.getName());
        ExpressionAssert.assertThat(rightOperand).hasReturnType(Boolean.class.getName());
        final EList<EObject> referencedElements = rightOperand.getReferencedElements();
        assertThat(referencedElements).hasSize(1);
        final EObject dep = referencedElements.get(0);
        assertThat(dep).isInstanceOf(Data.class);
        assertThat(((Data) dep).getName()).isEqualTo("refactored");
    }
}
