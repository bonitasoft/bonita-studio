/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.tests.data;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import org.bonitasoft.studio.common.DataTypeLabels;
import org.bonitasoft.studio.common.DataUtil;
import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.data.operation.RefactorDataOperation;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.MultiInstantiation;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.util.ProcessAdapterFactory;
import org.bonitasoft.studio.refactoring.core.RefactoringOperationType;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Aurelien Pupier
 * 
 */
public class TestDataRefactor {

    private Data processData;

    private Data localData;

    private RefactorDataOperation refactorDataOperation;

    private Pool process;

    private CompoundCommand cc;

    private EditingDomain editingDomain;

    @Test
    public void testNameRefactorWithGlobalDataReferencedInMultiInstanciation() throws InvocationTargetException, InterruptedException {
        final String newDataName = "newDataName";
        AbstractProcess process = initTestForGlobalDataRefactor(newDataName);

        // Data referenced in multi-instanciation
        Activity activity = ProcessFactory.eINSTANCE.createActivity();
        final MultiInstantiation multiInstantiation = ProcessFactory.eINSTANCE.createMultiInstantiation();
        multiInstantiation.setCollectionDataToMultiInstantiate(processData);
        multiInstantiation.setListDataContainingOutputResults(processData);
        activity.setMultiInstantiation(multiInstantiation);
        process.getElements().add(activity);

        processData.setName(newDataName);

        assertEquals("There are too many datas. The old one migth not be removed.", 1, process.getData().size());
        assertEquals("Data name has not been updated correctly in multinstantiation", newDataName, multiInstantiation.getCollectionDataToMultiInstantiate()
                .getName());
        assertEquals("Data name has not been updated correctly in multinstantiation", newDataName, multiInstantiation.getListDataContainingOutputResults()
                .getName());

    }

    @Test
    public void testNameRefactorWithLocalDataReferencedInMultiInstanciation() throws InvocationTargetException, InterruptedException {
        final String newDataName = "newDataName";
        AbstractProcess process = initTestForLocalDataRefactor(newDataName);

        // Data referenced in multi-instanciation
        Activity activity = (Activity) process.getElements().get(0);
        final MultiInstantiation multiInstantiation = ProcessFactory.eINSTANCE.createMultiInstantiation();
        multiInstantiation.setInputData(localData);
        multiInstantiation.setOutputData(localData);
        activity.setMultiInstantiation(multiInstantiation);
        process.getElements().add(activity);

        localData.setName(newDataName);

        assertEquals("There are too many datas. The old one migth not be removed.", 1, process.getData().size());
        assertEquals("Data name has not been updated correctly in multinstantiation", newDataName, multiInstantiation.getInputData().getName());
        assertEquals("Data name has not been updated correctly in multinstantiation", newDataName, multiInstantiation.getOutputData().getName());

    }

    @Test
    public void testNameRefactorWithLocalDataReferencedInVariableExpression() throws InvocationTargetException, InterruptedException {
        final String newDataName = "newDataName";
        AbstractProcess process = initTestForLocalDataRefactor(newDataName);

        // Data referenced in expression
        Activity activity = (Activity) process.getElements().get(0);
        final MultiInstantiation multiInstantiation = ProcessFactory.eINSTANCE.createMultiInstantiation();
        final Expression variableExpression = ExpressionFactory.eINSTANCE.createExpression();
        variableExpression.setType(ExpressionConstants.VARIABLE_TYPE);
        variableExpression.setName(localData.getName());
        variableExpression.setContent(localData.getName());
        variableExpression.getReferencedElements().add(localData);
        variableExpression.setReturnType(DataUtil.getTechnicalTypeFor(localData));
        multiInstantiation.setCardinality(variableExpression);
        activity.setMultiInstantiation(multiInstantiation);
        process.getElements().add(activity);

        refactorDataOperation.run(new NullProgressMonitor());
        editingDomain.getCommandStack().execute(cc);
        assertEquals("There are too many datas. The old one migth not be removed.", 1, process.getData().size());
        assertEquals("Data name has not been updated correctly in expression", newDataName,
                ((Element) variableExpression.getReferencedElements().get(0)).getName());
        assertEquals("Data name has not been updated correctly in expression", newDataName, variableExpression.getName());
        assertEquals("Data name has not been updated correctly in expression", newDataName, variableExpression.getContent());

    }

    private AdapterFactoryEditingDomain createEditingDomain() {
        ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
        adapterFactory.addAdapterFactory(new ProcessAdapterFactory());

        // command stack that will notify this editor as commands are executed
        BasicCommandStack commandStack = new BasicCommandStack();

        // Create the editing domain with our adapterFactory and command stack.
        return new AdapterFactoryEditingDomain(adapterFactory, commandStack, new HashMap<Resource, Boolean>());
    }

    @Before
    public void setUp() throws Exception {
        createProcessWithData();
    }

    /**
     * @return an AbstractProcess with a global data and a local data on an activity.
     */

    private AbstractProcess createProcessWithData() {
        if (process == null) {
            final MainProcess mainProcess = ProcessFactory.eINSTANCE.createMainProcess();
            ModelHelper.addDataTypes(mainProcess);
            process = ProcessFactory.eINSTANCE.createPool();
            mainProcess.getElements().add(process);

            processData = ProcessFactory.eINSTANCE.createData();
            processData.setDatasourceId("BOS");
            processData.setName("globalData");
            processData.setDataType(ModelHelper.getDataTypeForID(mainProcess, DataTypeLabels.stringDataType));
            process.getData().add(processData);

            final Activity activity = ProcessFactory.eINSTANCE.createActivity();
            localData = ProcessFactory.eINSTANCE.createData();
            localData.setDatasourceId("BOS");
            localData.setName("localData");
            localData.setDataType(ModelHelper.getDataTypeForID(mainProcess, DataTypeLabels.stringDataType));
            activity.getData().add(localData);
            process.getElements().add(activity);
        }
        return process;
    }

    private AbstractProcess initTestForLocalDataRefactor(final String newDataName) {
        return initTestForDataRefactor(newDataName, localData);
    }

    private AbstractProcess initTestForGlobalDataRefactor(final String newDataName) {
        return initTestForDataRefactor(newDataName, processData);
    }

    private AbstractProcess initTestForDataRefactor(final String newDataName, final Data dataToRefactor) {
        final AbstractProcess process = createProcessWithData();
        refactorDataOperation = new RefactorDataOperation(RefactoringOperationType.UPDATE);
        refactorDataOperation.setContainer(process);
        refactorDataOperation.setOldData(dataToRefactor);
        final Data newProcessData = EcoreUtil.copy(dataToRefactor);
        newProcessData.setName(newDataName);
        refactorDataOperation.setNewData(newProcessData);
        editingDomain = createEditingDomain();
        refactorDataOperation.setEditingDomain(editingDomain);
        return process;
    }

}
