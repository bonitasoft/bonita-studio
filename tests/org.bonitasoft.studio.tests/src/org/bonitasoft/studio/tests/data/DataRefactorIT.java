/**
 * Copyright (C) 2012-2014 Bonitasoft S.A.
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

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;

import org.bonitasoft.studio.common.DataTypeLabels;
import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.EMFModelUpdater;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.model.ModelSearch;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.diagram.custom.commands.NewDiagramCommandHandler;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfiguration;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorConfigurationFactory;
import org.bonitasoft.studio.model.connectorconfiguration.ConnectorParameter;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.expression.Operator;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Connector;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.SequenceFlowConditionType;
import org.bonitasoft.studio.refactoring.core.RefactorDataOperation;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Aurelien Pupier
 */
public class DataRefactorIT {

    private Data processData;
    private Data processData2;
    private Data localData;
    private RefactorDataOperation refactorDataOperation;
    private Pool process;
    private TransactionalEditingDomain editingDomain;
    private DiagramFileStore newDiagram;

    @Test
    public void testNameRefactorWithGlobalDataReferencedInMultiInstanciation() throws ReadFileStoreException {
        final String newDataName = "newDataName";
        final AbstractProcess process = initTestForGlobalDataRefactor(newDataName);

        // Data referenced in multi-instanciation
        final Activity activity = ProcessFactory.eINSTANCE.createActivity();
        activity.setCollectionDataToMultiInstantiate(processData);
        activity.setListDataContainingOutputResults(processData);
        editingDomain.getCommandStack()
                .execute(AddCommand.create(editingDomain, process, ProcessPackage.Literals.CONTAINER__ELEMENTS, activity));
        editingDomain.getCommandStack()
                .execute(SetCommand.create(editingDomain, processData, ProcessPackage.Literals.ELEMENT__NAME, newDataName));

        assertEquals("There are too many datas. The old one migth not be removed.", 2, process.getData().size());
        assertEquals("Data name has not been updated correctly in multinstantiation", newDataName,
                activity.getCollectionDataToMultiInstantiate()
                        .getName());
        assertEquals("Data name has not been updated correctly in multinstantiation", newDataName,
                activity.getListDataContainingOutputResults()
                        .getName());

    }

    @Test
    public void testNameRefactorWithLocalDataReferencedInMultiInstanciation() throws ReadFileStoreException {
        final String newDataName = "newDataName";
        final AbstractProcess process = initTestForLocalDataRefactor(newDataName);

        // Data referenced in multi-instanciation
        final Activity activity = getActivity(process);
        editingDomain.getCommandStack().execute(new RecordingCommand(editingDomain) {

            @Override
            protected void doExecute() {
                activity.setIteratorExpression(ExpressionHelper.createVariableExpression(localData));
                activity.setOutputData(localData);
                localData.setName(newDataName);
            }
        });

        assertEquals("There are too many datas. The old one migth not be removed.", 2, process.getData().size());
        assertEquals("Data name has not been updated correctly in multinstantiation", newDataName,
                activity.getOutputData().getName());
    }

    @Test
    public void testNameRefactorWithLocalDataReferencedInVariableExpression()
            throws InvocationTargetException, InterruptedException, ReadFileStoreException {
        final String newDataName = "newDataName";
        final AbstractProcess process = initTestForLocalDataRefactor(newDataName);

        // Data referenced in expression
        final Activity activity = getActivity(process);
        final Expression variableExpression = ExpressionHelper.createVariableExpression(localData);
        editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, activity,
                ProcessPackage.Literals.MULTI_INSTANTIABLE__CARDINALITY_EXPRESSION, variableExpression));

        refactorDataOperation.run(new NullProgressMonitor());
        assertEquals("There are too many datas. The old one migth not be removed.", 2, process.getData().size());
        assertEquals("Data name has not been updated correctly in expression", newDataName,
                ((Element) variableExpression.getReferencedElements().get(0)).getName());
        assertEquals("Data name has not been updated correctly in expression", newDataName, variableExpression.getName());
        assertEquals("Data name has not been updated correctly in expression", newDataName, variableExpression.getContent());

    }

    @Test
    public void testRenameAndModifyTypeWithReferenceInScriptOperation()
            throws InvocationTargetException, InterruptedException, ReadFileStoreException {
        final String newDataName = "newDataName";
        final String newDataType = DataTypeLabels.integerDataType;
        final AbstractProcess process = initTestForGlobalDataRefactor(newDataName, newDataType);
        final Activity activity = getActivity(process);
        final Operation operationWithScriptUsingData = ExpressionFactory.eINSTANCE.createOperation();
        final Operator assignOperator = ExpressionFactory.eINSTANCE.createOperator();
        assignOperator.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
        operationWithScriptUsingData.setOperator(assignOperator);
        final Expression variableExpression = ExpressionFactory.eINSTANCE.createExpression();
        variableExpression.setType(ExpressionConstants.VARIABLE_TYPE);
        variableExpression.setName(processData.getName());
        variableExpression.setContent(processData.getName());
        variableExpression.getReferencedElements().add(EcoreUtil.copy(processData));
        variableExpression.setReturnType(DataUtil.getTechnicalTypeFor(processData));
        operationWithScriptUsingData.setLeftOperand(variableExpression);
        final Expression scriptUsingData = ExpressionFactory.eINSTANCE.createExpression();
        scriptUsingData.setType(ExpressionConstants.SCRIPT_TYPE);
        scriptUsingData.setName(processData.getName());
        scriptUsingData.setContent(processData.getName());
        scriptUsingData.getReferencedElements().add(EcoreUtil.copy(processData));
        scriptUsingData.setReturnType(DataUtil.getTechnicalTypeFor(processData));
        operationWithScriptUsingData.setRightOperand(scriptUsingData);
        editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, activity,
                ProcessPackage.Literals.OPERATION_CONTAINER__OPERATIONS, operationWithScriptUsingData));

        final String initialDataName = processData.getName();

        refactorDataOperation.run(new NullProgressMonitor());
        assertEquals("There are too many datas. The old one might not be removed.", 2, process.getData().size());
        assertEquals("Data has not been renamed", newDataName, process.getData().get(0).getName());
        assertEquals("Data name has not been updated correctly in expression", newDataName,
                ((Element) variableExpression.getReferencedElements().get(0)).getName());
        assertEquals("Data name has not been updated correctly in expression of left operand operation", newDataName,
                variableExpression.getName());
        assertEquals("Data name has not been updated correctly in expression of left operand operation", newDataName,
                variableExpression.getContent());

        assertEquals("Data name has not been updated correctly in expression of right operand operation", newDataName,
                scriptUsingData.getContent());
        assertEquals("Data name has not been updated correctly in expression of right operand operation", newDataName,
                ((Data) scriptUsingData
                        .getReferencedElements().get(0)).getName());

        editingDomain.getCommandStack().undo();

        assertEquals("There are too many datas. The old one might not be removed.", 2, process.getData().size());
        assertEquals("Data has not been renamed after undo", initialDataName, process.getData().get(0).getName());
        assertEquals("Data name has not been updated correctly in expression", initialDataName,
                ((Element) variableExpression.getReferencedElements().get(0)).getName());
        assertEquals("Data name has not been updated correctly in expression of left operand operation after undo",
                initialDataName,
                variableExpression.getName());
        assertEquals("Data name has not been updated correctly in expression of left operand operation after undo",
                initialDataName,
                variableExpression.getContent());

        assertEquals("Data name has not been updated correctly in expression of right operand operation after undo",
                initialDataName,
                scriptUsingData.getContent());
        assertEquals("Data name has not been updated correctly in expression of right operand operation after undo",
                initialDataName, ((Data) scriptUsingData
                        .getReferencedElements().get(0)).getName());
    }

    @Test
    public void testRenameInGroovyScriptConnector() throws InvocationTargetException, InterruptedException, ReadFileStoreException {
        final String newDataName = "newDataName";
        final String newDataType = DataTypeLabels.integerDataType;
        final AbstractProcess process = initTestForGlobalDataRefactor(newDataName, newDataType);
        final Activity activity = (Activity) new ModelSearch(Collections::emptyList)
                .getAllItemsOfType(process, Activity.class)
                .get(0);
        final Expression scriptUsingData = createGroovyScriptConnectortWithDataReferenced(activity);

        final String initialDataName = processData.getName();

        refactorDataOperation.run(new NullProgressMonitor());

        assertEquals("Data has not been renamed", newDataName, process.getData().get(0).getName());
        assertEquals("Data name has not been updated correctly in expression of right operand operation", newDataName,
                scriptUsingData.getContent());
        assertEquals("Data name has not been updated correctly in expression of right operand operation", newDataName,
                ((Data) scriptUsingData
                        .getReferencedElements().get(0)).getName());

        editingDomain.getCommandStack().undo();

        assertEquals("Data has not been renamed after undo", initialDataName, process.getData().get(0).getName());
        assertEquals("Data name has not been updated correctly in expression of right operand operation after undo",
                initialDataName,
                scriptUsingData.getContent());
        assertEquals("Data name has not been updated correctly in expression of right operand operation after undo",
                initialDataName, ((Data) scriptUsingData
                        .getReferencedElements().get(0)).getName());

    }

    private Expression createGroovyScriptConnectortWithDataReferenced(
            final Activity activity) {
        final Connector groovyScriptConnector = ProcessFactory.eINSTANCE.createConnector();

        final ConnectorConfiguration groovyScriptConnectorConfiguration = ConnectorConfigurationFactory.eINSTANCE
                .createConnectorConfiguration();
        final ConnectorParameter connectorParameter = ConnectorConfigurationFactory.eINSTANCE.createConnectorParameter();
        final Expression scriptUsingData = ExpressionFactory.eINSTANCE.createExpression();
        scriptUsingData.setType(ExpressionConstants.SCRIPT_TYPE);
        scriptUsingData.setName(processData.getName());
        scriptUsingData.setContent(processData.getName());
        scriptUsingData.getReferencedElements().add(EcoreUtil.copy(processData));
        scriptUsingData.setReturnType(DataUtil.getTechnicalTypeFor(processData));
        connectorParameter.setExpression(scriptUsingData);
        groovyScriptConnectorConfiguration.getParameters().add(connectorParameter);
        groovyScriptConnector.setConfiguration(groovyScriptConnectorConfiguration);

        editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, activity,
                ProcessPackage.Literals.CONNECTABLE_ELEMENT__CONNECTORS, groovyScriptConnector));
        return scriptUsingData;
    }

    @Test
    public void testDeleteData() throws InvocationTargetException, InterruptedException, ReadFileStoreException {
        final AbstractProcess process = initTestForGlobalDataRefactor(null);
        refactorDataOperation.run(new NullProgressMonitor());
        assertEquals("The data has not been removed", 1, process.getData().size());

        editingDomain.getCommandStack().undo();

        assertEquals("The data has not been set back after undo", 2, process.getData().size());
    }

    @Test
    public void testDeleteSeveralData() throws InvocationTargetException, InterruptedException, ReadFileStoreException {
        final AbstractProcess process = initTestForGlobalDataRefactor(null);
        refactorDataOperation.addItemToRefactor(null, processData2);
        refactorDataOperation.run(new NullProgressMonitor());
        assertEquals("The data has not been removed", 0, process.getData().size());

        editingDomain.getCommandStack().undo();

        assertEquals("The data has not been set back after undo", 2, process.getData().size());
    }

    @Test
    public void testDeleteDataWithReferenceInScript() throws InvocationTargetException, InterruptedException, ReadFileStoreException {
        final AbstractProcess process = initTestForGlobalDataRefactor(null);

        final Activity activity = getActivity(process);
        final Operation operationWithScriptUsingData = ExpressionFactory.eINSTANCE.createOperation();
        final Operator assignOperator = ExpressionFactory.eINSTANCE.createOperator();
        assignOperator.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
        operationWithScriptUsingData.setOperator(assignOperator);
        final Expression variableExpression = ExpressionFactory.eINSTANCE.createExpression();
        variableExpression.setType(ExpressionConstants.VARIABLE_TYPE);
        variableExpression.setName(processData.getName());
        variableExpression.setContent(processData.getName());
        variableExpression.getReferencedElements().add(EcoreUtil.copy(processData));
        variableExpression.setReturnType(DataUtil.getTechnicalTypeFor(processData));
        operationWithScriptUsingData.setLeftOperand(variableExpression);
        final Expression scriptUsingData = ExpressionFactory.eINSTANCE.createExpression();
        scriptUsingData.setType(ExpressionConstants.SCRIPT_TYPE);
        scriptUsingData.setName(processData.getName());
        scriptUsingData.setContent(processData.getName());
        scriptUsingData.getReferencedElements().add(EcoreUtil.copy(processData));
        scriptUsingData.setReturnType(DataUtil.getTechnicalTypeFor(processData));
        operationWithScriptUsingData.setRightOperand(scriptUsingData);
        editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, activity,
                ProcessPackage.Literals.OPERATION_CONTAINER__OPERATIONS, operationWithScriptUsingData));

        refactorDataOperation.run(new NullProgressMonitor());
        assertEquals("The data has not been removed", 1, process.getData().size());
        assertEquals("Referenced Data has been removed from script", 0, scriptUsingData.getReferencedElements().size());

        editingDomain.getCommandStack().undo();
        assertEquals("The data has not been readded on undo", 2, process.getData().size());
        assertEquals("Referenced Data has been removed from script", 1, scriptUsingData.getReferencedElements().size());
    }

    protected Activity getActivity(final AbstractProcess process) {
        return (Activity) new ModelSearch(Collections::emptyList)
                .getAllItemsOfType(process, Activity.class)
                .get(0);
    }

    @Test
    public void testDeleteSeveralDataWithReferenceInScript() throws InvocationTargetException, InterruptedException, ReadFileStoreException {
        final AbstractProcess process = initTestForGlobalDataRefactor(null);

        final Activity activity = getActivity(process);
        final Operation operationWithScriptUsingData = ExpressionFactory.eINSTANCE.createOperation();
        final Operator assignOperator = ExpressionFactory.eINSTANCE.createOperator();
        assignOperator.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
        operationWithScriptUsingData.setOperator(assignOperator);
        final Expression scriptUsingData = ExpressionFactory.eINSTANCE.createExpression();
        scriptUsingData.setType(ExpressionConstants.SCRIPT_TYPE);
        scriptUsingData.setName("scriptUsingTwoData");
        scriptUsingData.setContent(processData.getName() + "+" + processData2.getName());
        scriptUsingData.getReferencedElements().add(EcoreUtil.copy(processData));
        scriptUsingData.getReferencedElements().add(EcoreUtil.copy(processData2));
        scriptUsingData.setReturnType(DataUtil.getTechnicalTypeFor(processData));
        operationWithScriptUsingData.setRightOperand(scriptUsingData);
        editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, activity,
                ProcessPackage.Literals.OPERATION_CONTAINER__OPERATIONS, operationWithScriptUsingData));

        refactorDataOperation.addItemToRefactor(null, processData2);

        refactorDataOperation.run(new NullProgressMonitor());
        assertEquals("The data has not been removed", 0, process.getData().size());
        assertEquals("Referenced Data has been removed from script", 0, scriptUsingData.getReferencedElements().size());
        //TODO: when handled, check that the script has removed both data from the script

        editingDomain.getCommandStack().undo();
        assertEquals("The data has not been readded on undo", 2, process.getData().size());
        assertEquals("Referenced Data has been removed from script", 2, scriptUsingData.getReferencedElements().size());
    }

    @Test
    public void testDeleteDataWithReferenceInScriptAndOperations() throws InvocationTargetException, InterruptedException, ReadFileStoreException {
        final AbstractProcess process = initTestForGlobalDataRefactor(null);

        final Activity activity = (Activity) new ModelSearch(Collections::emptyList)
                .getAllItemsOfType(process, Activity.class)
                .get(0);
        final Operation operationWithScriptUsingData = ExpressionFactory.eINSTANCE.createOperation();
        final Operator assignOperator = ExpressionFactory.eINSTANCE.createOperator();
        assignOperator.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
        operationWithScriptUsingData.setOperator(assignOperator);
        final Expression variableExpression = ExpressionFactory.eINSTANCE.createExpression();
        variableExpression.setType(ExpressionConstants.VARIABLE_TYPE);
        variableExpression.setName(processData.getName());
        variableExpression.setContent(processData.getName());
        variableExpression.getReferencedElements().add(EcoreUtil.copy(processData));
        variableExpression.setReturnType(DataUtil.getTechnicalTypeFor(processData));
        operationWithScriptUsingData.setLeftOperand(variableExpression);
        final Expression scriptUsingData = ExpressionFactory.eINSTANCE.createExpression();
        scriptUsingData.setType(ExpressionConstants.SCRIPT_TYPE);
        scriptUsingData.setName("scriptUsingASingleData");
        scriptUsingData.setContent(processData.getName());
        scriptUsingData.getReferencedElements().add(EcoreUtil.copy(processData));
        scriptUsingData.setReturnType(DataUtil.getTechnicalTypeFor(processData));
        operationWithScriptUsingData.setRightOperand(scriptUsingData);
        editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, activity,
                ProcessPackage.Literals.OPERATION_CONTAINER__OPERATIONS, operationWithScriptUsingData));

        refactorDataOperation.run(new NullProgressMonitor());
        assertEquals("The data has not been removed", 1, process.getData().size());
        assertEquals("Referenced Data has been removed from script", 0, scriptUsingData.getReferencedElements().size());
        assertEquals("Referenced Data has been removed from Operations", 0,
                variableExpression.getReferencedElements().size());

        editingDomain.getCommandStack().undo();
        assertEquals("The data has not been readded on undo", 2, process.getData().size());
        assertEquals("Referenced Data has been removed from script", 1, scriptUsingData.getReferencedElements().size());
        assertEquals("Referenced Data has been removed from Operations", 1,
                variableExpression.getReferencedElements().size());
    }

    @Test
    public void testDeleteSeveralDataWithReferenceInScriptAndOperations()
            throws InvocationTargetException, InterruptedException, ReadFileStoreException {
        final AbstractProcess process = initTestForGlobalDataRefactor(null);

        final Activity activity = (Activity) new ModelSearch(Collections::emptyList)
                .getAllItemsOfType(process, Activity.class)
                .get(0);
        final Operation operationWithScriptUsingData = ExpressionFactory.eINSTANCE.createOperation();
        final Operator assignOperator = ExpressionFactory.eINSTANCE.createOperator();
        assignOperator.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
        operationWithScriptUsingData.setOperator(assignOperator);
        final Expression variableExpression = ExpressionFactory.eINSTANCE.createExpression();
        variableExpression.setType(ExpressionConstants.VARIABLE_TYPE);
        variableExpression.setName(processData.getName());
        variableExpression.setContent(processData.getName());
        variableExpression.getReferencedElements().add(EcoreUtil.copy(processData));
        variableExpression.setReturnType(DataUtil.getTechnicalTypeFor(processData));
        operationWithScriptUsingData.setLeftOperand(variableExpression);
        final Expression scriptUsingData = ExpressionFactory.eINSTANCE.createExpression();
        scriptUsingData.setType(ExpressionConstants.SCRIPT_TYPE);
        scriptUsingData.setName("scriptUsingTwoData");
        scriptUsingData.setContent(processData.getName() + "+" + processData2.getName());
        scriptUsingData.getReferencedElements().add(EcoreUtil.copy(processData));
        scriptUsingData.getReferencedElements().add(EcoreUtil.copy(processData2));
        scriptUsingData.setReturnType(DataUtil.getTechnicalTypeFor(processData));
        operationWithScriptUsingData.setRightOperand(scriptUsingData);
        editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, activity,
                ProcessPackage.Literals.OPERATION_CONTAINER__OPERATIONS, operationWithScriptUsingData));

        refactorDataOperation.addItemToRefactor(null, processData2);

        refactorDataOperation.run(new NullProgressMonitor());
        assertEquals("The data has not been removed", 0, process.getData().size());
        assertEquals("Referenced Data has been removed from script", 0, scriptUsingData.getReferencedElements().size());
        assertEquals("Referenced Data has been removed from Operations", 0,
                variableExpression.getReferencedElements().size());
        //TODO: when handled, check that the script has removed both data from the script

        editingDomain.getCommandStack().undo();
        assertEquals("The data has not been readded on undo", 2, process.getData().size());
        assertEquals("Referenced Data has been removed from script", 2, scriptUsingData.getReferencedElements().size());
        assertEquals("Referenced Data has been removed from Operations", 1,
                variableExpression.getReferencedElements().size());
    }

    @Test
    public void testDeleteSeveralDataWithReferenceInOperations() throws InvocationTargetException, InterruptedException, ReadFileStoreException {
        final AbstractProcess process = initTestForGlobalDataRefactor(null);

        final Expression variableExpression = addOperationOnFirstActivity(process, processData);
        final Expression variableExpression2 = addOperationOnFirstActivity(process, processData2);

        refactorDataOperation.addItemToRefactor(null, processData2);

        refactorDataOperation.run(new NullProgressMonitor());
        assertEquals("The data has not been removed", 0, process.getData().size());
        assertEquals("Referenced Data has been removed from Operations", 0,
                variableExpression.getReferencedElements().size());
        assertEquals("Referenced Data has been removed from Operations", 0,
                variableExpression2.getReferencedElements().size());

        editingDomain.getCommandStack().undo();
        assertEquals("The data has not been readded on undo", 2, process.getData().size());
        assertEquals("Referenced Data has been removed from Operations", 1,
                variableExpression.getReferencedElements().size());
        assertEquals("Referenced Data has been removed from Operations", 1,
                variableExpression2.getReferencedElements().size());
    }

    private Expression addOperationOnFirstActivity(final AbstractProcess process, final Data data) {
        final Activity activity = getActivity(process);
        final Operation operationWithScriptUsingData = ExpressionFactory.eINSTANCE.createOperation();
        final Operator assignOperator = ExpressionFactory.eINSTANCE.createOperator();
        assignOperator.setType(ExpressionConstants.ASSIGNMENT_OPERATOR);
        operationWithScriptUsingData.setOperator(assignOperator);
        final Expression variableExpression = ExpressionFactory.eINSTANCE.createExpression();
        variableExpression.setType(ExpressionConstants.VARIABLE_TYPE);
        variableExpression.setName(data.getName());
        variableExpression.setContent(data.getName());
        variableExpression.getReferencedElements().add(EcoreUtil.copy(data));
        variableExpression.setReturnType(DataUtil.getTechnicalTypeFor(data));
        operationWithScriptUsingData.setLeftOperand(variableExpression);
        editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, activity,
                ProcessPackage.Literals.OPERATION_CONTAINER__OPERATIONS, operationWithScriptUsingData));
        return variableExpression;
    }

    @Test
    public void testDeleteDataWithReferenceInCondition() throws InvocationTargetException, InterruptedException, ReadFileStoreException {
        final AbstractProcess process = initTestForGlobalDataRefactor(null);

        final Expression conditionExpression = createSequenceFlowWithConditionExpression(process);

        refactorDataOperation.run(new NullProgressMonitor());
        assertEquals("The data has not been removed", 1, process.getData().size());
        assertEquals("Referenced Data has been removed from script", 0, conditionExpression.getReferencedElements().size());

        editingDomain.getCommandStack().undo();
        assertEquals("The data has not been readded on undo", 2, process.getData().size());
        assertEquals("Referenced Data has been removed from script", 1, conditionExpression.getReferencedElements().size());
    }

    @Test
    public void testDeleteDataWithReferenceInPatternExpression() throws InvocationTargetException, InterruptedException, ReadFileStoreException {
        final AbstractProcess process = initTestForGlobalDataRefactor(null);

        final Expression patternExpr = createConnectorWithPatternExpression(process);
        final String initialDataName = processData.getName();

        refactorDataOperation.run(new NullProgressMonitor());
        assertEquals("The data has not been removed", 1, process.getData().size());

        editingDomain.getCommandStack().undo();
        assertEquals("The data has not been readded on undo", 2, process.getData().size());
        assertEquals("Referenced Data has been removed from script", 1, patternExpr.getReferencedElements().size());
        assertEquals("Pattern expression", "${" + initialDataName + "}", patternExpr.getContent());
    }

    @Test
    public void testRenameDataWithReferenceInPatternExpression() throws InvocationTargetException, InterruptedException, ReadFileStoreException {
        final String newDataName = "newDataName";
        final AbstractProcess process = initTestForGlobalDataRefactor(newDataName);

        final Expression patternExpr = createConnectorWithPatternExpression(process);
        final String initialDataName = processData.getName();

        refactorDataOperation.run(new NullProgressMonitor());
        assertEquals("The data has n been duplicated", 2, process.getData().size());
        assertEquals("Referenced Data has been removed from script", 1, patternExpr.getReferencedElements().size());
        assertEquals("Referenced Data has not been renamed", newDataName,
                ((Data) patternExpr.getReferencedElements().get(0)).getName());
        assertEquals("Pattern expression", "${" + newDataName + "}", patternExpr.getContent());

        editingDomain.getCommandStack().undo();
        assertEquals("The data has not been readded on undo", 2, process.getData().size());
        assertEquals("Referenced Data has been removed from script", 1, patternExpr.getReferencedElements().size());
        assertEquals("Pattern expression", "${" + initialDataName + "}", patternExpr.getContent());
    }

    private Expression createConnectorWithPatternExpression(final AbstractProcess process) {
        final Connector mailConnector = ProcessFactory.eINSTANCE.createConnector();

        final ConnectorConfiguration groovyScriptConnectorConfiguration = ConnectorConfigurationFactory.eINSTANCE
                .createConnectorConfiguration();
        final ConnectorParameter connectorParameter = ConnectorConfigurationFactory.eINSTANCE.createConnectorParameter();
        final Expression patternExpr = ExpressionFactory.eINSTANCE.createExpression();
        patternExpr.setType(ExpressionConstants.PATTERN_TYPE);
        patternExpr.setName(processData.getName());
        patternExpr.setContent("${" + processData.getName() + "}");
        patternExpr.getReferencedElements().add(EcoreUtil.copy(processData));
        patternExpr.setReturnType(DataUtil.getTechnicalTypeFor(processData));
        connectorParameter.setExpression(patternExpr);
        groovyScriptConnectorConfiguration.getParameters().add(connectorParameter);
        mailConnector.setConfiguration(groovyScriptConnectorConfiguration);
        editingDomain.getCommandStack().execute(AddCommand.create(editingDomain, process,
                ProcessPackage.Literals.CONNECTABLE_ELEMENT__CONNECTORS, mailConnector));
        return patternExpr;
    }

    @Test
    public void testRenameDataWithReferenceInCondition() throws InvocationTargetException, InterruptedException, ReadFileStoreException {
        final String newDataName = "newDataName";
        final AbstractProcess process = initTestForGlobalDataRefactor(newDataName);

        final Expression conditionExpression = createSequenceFlowWithConditionExpression(process);
        final String initialDataName = processData.getName();

        refactorDataOperation.run(new NullProgressMonitor());
        assertEquals("The old data might not have been updated", 2, process.getData().size());
        assertEquals("The data has not been renamed in condition", newDataName + " == \"plop\"",
                conditionExpression.getContent());
        assertEquals("The data has not been removed from dependency", newDataName,
                ((Data) conditionExpression.getReferencedElements().get(0)).getName());

        editingDomain.getCommandStack().undo();

        assertEquals("The old data might not have been updated", 2, process.getData().size());
        assertEquals("The data has not been renamed in condition", initialDataName + " == \"plop\"",
                conditionExpression.getContent());
        assertEquals("The data dependency has not been back on undo", initialDataName,
                ((Data) conditionExpression.getReferencedElements().get(0)).getName());
    }

    private Expression createSequenceFlowWithConditionExpression(
            final AbstractProcess process) {
        final SequenceFlow sequenceFlow = ProcessFactory.eINSTANCE.createSequenceFlow();
        sequenceFlow.setConditionType(SequenceFlowConditionType.EXPRESSION);
        final Expression conditionExpression = ExpressionFactory.eINSTANCE.createExpression();
        conditionExpression.setType(ExpressionConstants.CONDITION_TYPE);
        conditionExpression.setContent(processData.getName() + " == \"plop\"");
        conditionExpression.setName("conditionExpression");
        conditionExpression.getReferencedElements().add(EcoreUtil.copy(processData));
        sequenceFlow.setCondition(conditionExpression);
        editingDomain.getCommandStack().execute(
                new AddCommand(editingDomain, process, ProcessPackage.Literals.ABSTRACT_PROCESS__CONNECTIONS, sequenceFlow));
        return conditionExpression;
    }


    @Before
    public void setUp() throws Exception {
        createProcessWithData();
    }

    @After
    public void tearDown() throws Exception {
        process = null;
        newDiagram.delete();
        createProcessWithData();
    }

    /**
     * @return an AbstractProcess with a global data and a local data on an activity.
     * @throws ReadFileStoreException 
     */

    private AbstractProcess createProcessWithData() throws ReadFileStoreException {
        if (process == null) {
            newDiagram = new NewDiagramCommandHandler().newDiagram();
            editingDomain = TransactionUtil.getEditingDomain(newDiagram.getEMFResource());
            final MainProcess mainProcess = newDiagram.getContent();
            process = (Pool) mainProcess.getElements().get(0);
            processData = ProcessFactory.eINSTANCE.createData();
            processData.setDatasourceId("BOS");
            processData.setName("globalData");
            processData.setDataType(ModelHelper.getDataTypeForID(mainProcess, DataTypeLabels.stringDataType));
            editingDomain.getCommandStack().execute(
                    AddCommand.create(editingDomain, process, ProcessPackage.Literals.DATA_AWARE__DATA, processData));

            processData2 = ProcessFactory.eINSTANCE.createData();
            processData2.setDatasourceId("BOS");
            processData2.setName("globalData2");
            processData2.setDataType(ModelHelper.getDataTypeForID(mainProcess, DataTypeLabels.stringDataType));
            editingDomain.getCommandStack().execute(
                    AddCommand.create(editingDomain, process, ProcessPackage.Literals.DATA_AWARE__DATA, processData2));

            final Activity activity = new ModelSearch(Collections::emptyList).getAllItemsOfType(mainProcess, Activity.class)
                    .get(0);
            localData = ProcessFactory.eINSTANCE.createData();
            localData.setDatasourceId("BOS");
            localData.setName("localData");
            localData.setDataType(ModelHelper.getDataTypeForID(mainProcess, DataTypeLabels.stringDataType));
            editingDomain.getCommandStack().execute(
                    AddCommand.create(editingDomain, activity, ProcessPackage.Literals.DATA_AWARE__DATA, localData));


        }
        return process;
    }

    private AbstractProcess initTestForLocalDataRefactor(final String newDataName) throws ReadFileStoreException {
        return initTestForDataRefactor(newDataName, localData);
    }

    private AbstractProcess initTestForGlobalDataRefactor(final String newDataName) throws ReadFileStoreException {
        return initTestForDataRefactor(newDataName, processData);
    }

    private AbstractProcess initTestForDataRefactor(final String newDataName, final Data dataToRefactor) throws ReadFileStoreException {
        return initTestForDataRefactor(newDataName, dataToRefactor.getDataType().getName(), dataToRefactor);
    }

    private AbstractProcess initTestForGlobalDataRefactor(final String newDataName, final String newDataType) throws ReadFileStoreException {
        return initTestForDataRefactor(newDataName, newDataType, processData);
    }

    private AbstractProcess initTestForDataRefactor(final String newDataName, final String newDataType,
            final Data dataToRefactor) throws ReadFileStoreException {
        final AbstractProcess process = createProcessWithData();
        if (newDataName != null) {
            refactorDataOperation = new RefactorDataOperation(RefactoringOperationType.UPDATE);
        } else {
            refactorDataOperation = new RefactorDataOperation(RefactoringOperationType.REMOVE);
        }
        refactorDataOperation.setDataContainer(process);
        if (newDataName != null) {
            final Data newProcessData = createNewProcessData(newDataName, ModelHelper.getDataTypeForID(process, newDataType),
                    dataToRefactor);
            EMFModelUpdater<Data> emfModelUpdater = new EMFModelUpdater<Data>();
            Data workingCopy = emfModelUpdater.from(dataToRefactor).getWorkingCopy();
            workingCopy.setName(newDataName);
            refactorDataOperation.setUpdater(emfModelUpdater);
            refactorDataOperation.addItemToRefactor(newProcessData, dataToRefactor);
        } else {
            refactorDataOperation.addItemToRefactor(null, dataToRefactor);
        }
        refactorDataOperation.setEditingDomain(editingDomain);
        return process;
    }

    private Data createNewProcessData(final String newDataName, final DataType newDataType, final Data dataToRefactor) {
        final Data newProcessData = EcoreUtil.copy(dataToRefactor);
        newProcessData.setName(newDataName);
        newProcessData.setDataType(newDataType);
        return newProcessData;
    }

}
