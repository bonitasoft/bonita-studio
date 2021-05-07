/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.tests.exporter.bpmn;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.bonitasoft.studio.common.ExpressionConstants;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.diagram.custom.commands.NewDiagramCommandHandler;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.model.expression.Expression;
import org.bonitasoft.studio.model.expression.ExpressionFactory;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SequenceFlow;
import org.bonitasoft.studio.model.process.SequenceFlowConditionType;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.junit.Test;
import org.omg.spec.bpmn.model.DocumentRoot;
import org.omg.spec.bpmn.model.TExpression;
import org.omg.spec.bpmn.model.TFlowElement;
import org.omg.spec.bpmn.model.TProcess;
import org.omg.spec.bpmn.model.TRootElement;
import org.omg.spec.bpmn.model.TSequenceFlow;


public class BPMNSequenceFlowConditionExportImportTest {

    @Test
    public void testProcessWithConstantCondition() throws Exception {
        final Expression condition = ExpressionFactory.eINSTANCE.createExpression();
        condition.setContent("toto");
        condition.setType(ExpressionConstants.CONSTANT_TYPE);
        condition.setReturnType("java.lang.Boolean");

        testWithCondition(condition);
    }

    @Test
    public void testProcessWithGroovyCondition() throws Exception {
        final Expression condition = ExpressionFactory.eINSTANCE.createExpression();
        condition.setContent("return true;");
        condition.setInterpreter(ExpressionConstants.GROOVY);
        condition.setType(ExpressionConstants.SCRIPT_TYPE);
        condition.setReturnType("java.lang.Boolean");

        testWithCondition(condition);
    }

    protected void testWithCondition(final Expression condition) throws Exception {
        final DocumentRoot model2 = exportToBPMNProcessWithCondition(condition);
        checkConditionExistAtProcessLevelWithGoodStructure(model2);

        final MainProcess mainProcess = BPMNTestUtil.importBPMNFile(model2);
        compareProcessCondition(condition, mainProcess);
    }

    private void checkConditionExistAtProcessLevelWithGoodStructure(final DocumentRoot model2) {
        for (final TRootElement rootElement : model2.getDefinitions().getRootElement()) {
            if (rootElement instanceof TProcess) {
                for (final TFlowElement fe : ((TProcess) rootElement).getFlowElement()) {
                    if (fe instanceof TSequenceFlow) {
                        final TExpression conditionExpression = ((TSequenceFlow) fe).getConditionExpression();
                        assertNotNull(conditionExpression);
                    }
                }
            }
        }
    }

    private void compareProcessCondition(final Expression initialCondition, final MainProcess mainProcess) {
        final AbstractProcess abstractProcess = ModelHelper.getAllProcesses(mainProcess).get(0);
        final SequenceFlow sequenceFlowReimport = (SequenceFlow) abstractProcess.getConnections().get(0);
        compareSequenceFlows((SequenceFlow) initialCondition.eContainer(), sequenceFlowReimport);
    }

    private void compareSequenceFlows(final SequenceFlow initialSQ, final SequenceFlow reImportedSQ) {
        assertEquals("Condition type is not the same", initialSQ.getConditionType(), reImportedSQ.getConditionType());
        if (SequenceFlowConditionType.EXPRESSION.equals(initialSQ.getConditionType())) {
            compareConditions(initialSQ.getCondition(), reImportedSQ.getCondition());
        } 
    }

    private void compareConditions(final Expression initialCondition, final Expression reImportedCondition) {
        assertEquals(initialCondition.getContent(), reImportedCondition.getContent());
        assertEquals(initialCondition.getInterpreter(), reImportedCondition.getInterpreter());
        assertEquals(initialCondition.getType(), reImportedCondition.getType());
    }

    protected DocumentRoot exportToBPMNProcessWithCondition(final Expression condition) throws IOException {
        final NewDiagramCommandHandler newDiagramCommandHandler = new NewDiagramCommandHandler();
        final DiagramFileStore newDiagramFileStore = newDiagramCommandHandler.newDiagram();
        Resource emfResource = newDiagramFileStore.getEMFResource();
        TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(emfResource);
        final AbstractProcess abstractProcess = newDiagramFileStore.getProcesses(false).get(0);
        final SequenceFlow sequenceFlow = (SequenceFlow) abstractProcess.getConnections().get(0);
        editingDomain.getCommandStack().execute(
                SetCommand.create(editingDomain, sequenceFlow, ProcessPackage.Literals.SEQUENCE_FLOW__CONDITION, condition));
        editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, sequenceFlow,
                ProcessPackage.Literals.SEQUENCE_FLOW__CONDITION_TYPE, SequenceFlowConditionType.EXPRESSION));
        newDiagramFileStore.save(abstractProcess.eContainer());
        return BPMNTestUtil.exportToBpmn(emfResource);
    }

}
