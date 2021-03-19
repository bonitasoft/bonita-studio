/*******************************************************************************
 * Copyright (C) 2013-2014 Bonitasoft S.A.
 * BonitaSoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.tests.parameter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationFileStore;
import org.bonitasoft.studio.diagram.custom.repository.ProcessConfigurationRepositoryStore;
import org.bonitasoft.studio.importer.bos.operation.ImportBosArchiveOperation;
import org.bonitasoft.studio.model.configuration.Configuration;
import org.bonitasoft.studio.model.expression.Operation;
import org.bonitasoft.studio.model.parameter.Parameter;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.parameters.action.RefactorParametersOperation;
import org.bonitasoft.studio.parameters.action.RemoveParametersOperation;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.IProgressService;
import org.junit.After;
import org.junit.Test;

/**
 * @author Aurelie Zara
 */
public class TestParametersRefactoring {

    private final String mainProcessName = "testParametersRefactoring";

    private final String newParameterName = "parameter1Refactored";

    private final String parameterName = "parameter1";

    private final DiagramRepositoryStore store = RepositoryManager.getInstance()
            .getRepositoryStore(DiagramRepositoryStore.class);

    @Test
    public void testParametersRefactoring()
            throws IOException, InvocationTargetException, InterruptedException, ExecutionException, ReadFileStoreException {
        final MainProcess mainProcess = importDiagramAndOpen();
        final List<Pool> pools = ModelHelper.getChildrenProcess(mainProcess);
        assertEquals("2 pools should have been imported", 2, pools.size());
        final List<Parameter> pool1Parameters = pools.get(0).getParameters();
        assertEquals("2 parameters should be on first pool", 2, pool1Parameters.size());
        final List<Parameter> pool2Parameters = pools.get(1).getParameters();
        assertEquals("2 parameters should be on second pool", 2, pool2Parameters.size());
        final List<Operation> pool1Operations = getOperationsOnTask(pools.get(0));
        assertEquals("2 operations should be on first pool task", 2, pool1Operations.size());
        final List<Operation> pool2Operations = getOperationsOnTask(pools.get(1));
        assertEquals("2 operations should be on second pool task", 2, pool2Operations.size());
        refactorParameter(pools, pool1Parameters, pool2Parameters, pool1Operations, pool2Operations);
        undoParameterRefactoring(pools, pool1Parameters, pool1Operations);
        removeParameter(pools, pool1Parameters, pool1Operations);
    }

    public MainProcess importDiagramAndOpen() throws IOException, InvocationTargetException, InterruptedException, ReadFileStoreException {
        RepositoryAccessor repositoryAccessor = RepositoryManager.getInstance().getAccessor();
        final ImportBosArchiveOperation op = new ImportBosArchiveOperation(repositoryAccessor);
        final URL fileURL1 = FileLocator
                .toFileURL(TestParametersRefactoring.class.getResource("testParametersRefactoring-1.0.bos")); //$NON-NLS-1$
        op.setArchiveFile(FileLocator.toFileURL(fileURL1).getFile());
        op.setCurrentRepository(repositoryAccessor.getCurrentRepository());
        op.run(AbstractRepository.NULL_PROGRESS_MONITOR);
        final DiagramFileStore diagramFileStore = store.getChild("testParametersRefactoring-1.0.proc", true);
        diagramFileStore.open();
        final MainProcess mainProcess = diagramFileStore.getContent();
        assertEquals(mainProcessName, mainProcess.getName());
        return mainProcess;
    }

    private List<Operation> getOperationsOnTask(final Pool pool) {
        final List<EObject> tasks = ModelHelper.getAllItemsOfType(pool, ProcessPackage.Literals.ACTIVITY);
        assertEquals("there should be only one task defined on the pool", 1, tasks.size());
        final Activity task = (Activity) tasks.get(0);
        final List<Operation> operations = task.getOperations();
        assertEquals("two operations should be defined on " + task.getName(), 2, operations.size());
        return operations;
    }

    private void refactorParameter(final List<Pool> pools, final List<Parameter> pool1Parameters,
            final List<Parameter> pool2Parameters,
            final List<Operation> pool1Operations,
            final List<Operation> pool2Operations) throws InvocationTargetException, InterruptedException, ReadFileStoreException {
        final Parameter parameterToRefactor = pool1Parameters.get(0);
        final Parameter parameterWithSameName = pool2Parameters.get(0);
        final String parameterWithSameNameName = parameterWithSameName.getName();
        final String secondParameterOldName = pool1Parameters.get(1).getName();
        Configuration localeConfiguration = getLocalConfiguration(pools.get(0));
        final String parameterValue = localeConfiguration.getParameters().get(0).getValue();
        final RefactorParametersOperation op = new RefactorParametersOperation(pools.get(0));
        op.setEditingDomain(TransactionUtil.getEditingDomain(pools.get(0)));
        final Parameter newParameter = EcoreUtil.copy(parameterToRefactor);
        newParameter.setName(newParameterName);
        op.addItemToRefactor(newParameter, parameterToRefactor);
        final IProgressService service = PlatformUI.getWorkbench().getProgressService();
        service.busyCursorWhile(op);
        localeConfiguration = getLocalConfiguration(pools.get(0));
        assertEquals("refactoring first parameter also changed second parameter", secondParameterOldName,
                pool1Parameters.get(1).getName());
        assertEquals("parameter reference has not beeen refactored", newParameterName,
                pool1Operations.get(0).getRightOperand().getName());
        assertEquals("second parameter reference should not have changed", secondParameterOldName,
                pool1Operations.get(1).getRightOperand().getName());
        assertEquals("parameter with same name in second pool should not have changed", parameterWithSameNameName,
                parameterWithSameName.getName());
        assertEquals("parameter reference in second pool should not have been refactored", parameterWithSameNameName,
                pool2Operations.get(0).getRightOperand()
                        .getName());
        assertEquals("parameter name in configuration has not been refactored", newParameterName,
                localeConfiguration.getParameters().get(0).getName());
        assertEquals("refactored parameter has no value anymore", parameterValue,
                localeConfiguration.getParameters().get(0).getValue());
    }

    private void removeParameter(final List<Pool> pools, final List<Parameter> pool1Parameters,
            final List<Operation> operations)
            throws InvocationTargetException,
            InterruptedException, ReadFileStoreException {
        final RemoveParametersOperation op = new RemoveParametersOperation(pool1Parameters.get(0), pools.get(0));
        op.setEditingDomain(TransactionUtil.getEditingDomain(pools.get(0)));
        final IProgressService service = PlatformUI.getWorkbench().getProgressService();
        service.busyCursorWhile(op);
        assertEquals("parameter has not been removed", 1, pools.get(0).getParameters().size());
        assertEquals("parameter reference has not been removed", "", operations.get(0).getRightOperand().getName());
        final Configuration localeConfiguration = getLocalConfiguration(pools.get(0));
        assertEquals("parameter has not been removed correctly in ", 1, localeConfiguration.getParameters().size());
    }

    private void undoParameterRefactoring(final List<Pool> pools, final List<Parameter> pool1Parameters,
            final List<Operation> operations)
            throws ExecutionException, ReadFileStoreException {
        final UndoCommandHandler undoCommand = new UndoCommandHandler();
        undoCommand.execute(new ExecutionEvent());
        assertEquals("undo was not performed correctly", parameterName, pool1Parameters.get(0).getName());
        final Configuration localeConfiguration = getLocalConfiguration(pools.get(0));
        assertEquals("undo was not performed correctly in local configuration", parameterName,
                localeConfiguration.getParameters().get(0).getName());
        assertEquals("undo was not performed correctly on parameter reference", parameterName,
                operations.get(0).getRightOperand().getName());
    }

    @After
    public void tearDown() {
        final SaveCommandHandler saveCommand = new SaveCommandHandler();
        saveCommand.execute(new ExecutionEvent());
    }

    public Configuration getLocalConfiguration(final Pool pool) throws ReadFileStoreException {
        final String id = ModelHelper.getEObjectID(pool);
        final String fileName = id + ".conf";
        final ProcessConfigurationRepositoryStore processConfStore = RepositoryManager.getInstance().getRepositoryStore(
                ProcessConfigurationRepositoryStore.class);
        final ProcessConfigurationFileStore file = processConfStore.getChild(fileName, true);
        final Configuration localeConfiguration = file.getContent();
        assertNotNull("local configuration does not exist", localeConfiguration);
        return localeConfiguration;
    }
}
