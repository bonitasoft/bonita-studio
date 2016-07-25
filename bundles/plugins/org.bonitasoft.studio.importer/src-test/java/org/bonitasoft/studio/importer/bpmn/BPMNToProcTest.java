/**
 * Copyright (C) 2015 BonitaSoft S.A.
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
package org.bonitasoft.studio.importer.bpmn;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.importer.builder.IProcBuilder;
import org.bonitasoft.studio.importer.builder.IProcBuilder.TestTimeType;
import org.bonitasoft.studio.importer.builder.ProcBuilder;
import org.bonitasoft.studio.importer.builder.ProcBuilderException;
import org.bonitasoft.studio.model.expression.Expression;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.omg.spec.bpmn.di.BPMNEdge;
import org.omg.spec.bpmn.di.BPMNLabel;
import org.omg.spec.bpmn.di.BPMNShape;
import org.omg.spec.bpmn.di.DiFactory;
import org.omg.spec.bpmn.model.DocumentRoot;
import org.omg.spec.bpmn.model.ModelFactory;
import org.omg.spec.bpmn.model.TComplexGateway;
import org.omg.spec.bpmn.model.TDefinitions;
import org.omg.spec.bpmn.model.TEvent;
import org.omg.spec.bpmn.model.TExclusiveGateway;
import org.omg.spec.bpmn.model.TInclusiveGateway;
import org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics;
import org.omg.spec.bpmn.model.TProcess;
import org.omg.spec.bpmn.model.TSequenceFlow;
import org.omg.spec.bpmn.model.TStandardLoopCharacteristics;
import org.omg.spec.bpmn.model.TUserTask;
import org.omg.spec.dd.dc.Bounds;
import org.omg.spec.dd.dc.DcFactory;

@RunWith(MockitoJUnitRunner.class)
public class BPMNToProcTest {

    private BPMNToProc bpmnToProc;
    private ProcBuilder builder;

    @Before
    public void setup() throws Exception {
        bpmnToProc = spy(new BPMNToProc("myProc"));
        builder = spy(new ProcBuilder());
        doReturn(GMFEditingDomainFactory.INSTANCE.createEditingDomain()).when(builder).createEditingDomain();
    }

    @Test
    public void shouldinitializeLabelPositionOnSequenceFlow() throws ProcBuilderException {
        final BPMNEdge edge = DiFactory.eINSTANCE.createBPMNEdge();
        final org.omg.spec.dd.dc.Point pointA = DcFactory.eINSTANCE.createPoint();
        final org.omg.spec.dd.dc.Point pointB = DcFactory.eINSTANCE.createPoint();
        final org.omg.spec.dd.dc.Point pointC = DcFactory.eINSTANCE.createPoint();
        final org.omg.spec.dd.dc.Point pointD = DcFactory.eINSTANCE.createPoint();
        pointA.setX(100);
        pointA.setY(900);
        pointB.setX(400);
        pointB.setY(100);
        pointC.setX(400);
        pointC.setY(200);
        pointD.setX(500);
        pointD.setY(900);
        edge.getWaypoint().add(pointA);
        edge.getWaypoint().add(pointB);
        edge.getWaypoint().add(pointC);
        edge.getWaypoint().add(pointD);

        final BPMNLabel label = DiFactory.eINSTANCE.createBPMNLabel();
        final Bounds bounds = DcFactory.eINSTANCE.createBounds();
        bounds.setX(100);
        bounds.setY(800);
        label.setBounds(bounds);
        edge.setBPMNLabel(label);
        final String id = "bpmnEdge";
        doReturn(edge).when(bpmnToProc).getBPMNEdgeFor(id);
        final ProcBuilder builder = spy(new ProcBuilder());

        bpmnToProc.setBuilder(builder);
        doNothing().when(builder).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));
        bpmnToProc.initializeLabelPositionOnSequenceFlow(id);
        verify(builder).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));

    }

    @Test
    public void shouldNotinitializeLabelPositionOnSequenceFlowWhenIdIsNull() throws ProcBuilderException {
        final ProcBuilder builder = spy(new ProcBuilder());
        bpmnToProc.setBuilder(builder);
        doNothing().when(builder).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));
        bpmnToProc.initializeLabelPositionOnSequenceFlow(null);
        verify(builder, never()).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));
    }

    @Test
    public void shouldNotinitializeLabelPositionOnSequenceFlowWhenIdIsEmpty() throws ProcBuilderException {
        final ProcBuilder builder = spy(new ProcBuilder());
        bpmnToProc.setBuilder(builder);
        doNothing().when(builder).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));
        bpmnToProc.initializeLabelPositionOnSequenceFlow("");
        verify(builder, never()).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));
    }

    @Test
    public void shouldNotinitializeLabelPositionOnSequenceFlowWhenEdgeIsNull() throws ProcBuilderException {
        final ProcBuilder builder = spy(new ProcBuilder());
        bpmnToProc.setBuilder(builder);
        doNothing().when(builder).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));
        doReturn(null).when(bpmnToProc).getBPMNEdgeFor("mySequenceFlow");
        bpmnToProc.initializeLabelPositionOnSequenceFlow("mySequenceFlow");
        verify(builder, never()).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));
    }

    @Test
    public void shouldNotinitializeLabelPositionOnSequenceFlowWhenLabelIsNull() throws ProcBuilderException {
        final BPMNEdge edge = DiFactory.eINSTANCE.createBPMNEdge();
        final String id = "bpmnEdge";
        doReturn(edge).when(bpmnToProc).getBPMNEdgeFor(id);
        final ProcBuilder builder = spy(new ProcBuilder());
        bpmnToProc.setBuilder(builder);
        doNothing().when(builder).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));
        bpmnToProc.initializeLabelPositionOnSequenceFlow(id);
        verify(builder, never()).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));
    }

    @Test
    public void shouldNotinitializeLabelPositionOnSequenceFlowWhenBoundsIsNull() throws ProcBuilderException {
        final BPMNEdge edge = DiFactory.eINSTANCE.createBPMNEdge();
        final String id = "bpmnEdge";
        final BPMNLabel label = DiFactory.eINSTANCE.createBPMNLabel();
        edge.setBPMNLabel(label);
        doReturn(edge).when(bpmnToProc).getBPMNEdgeFor(id);
        final ProcBuilder builder = spy(new ProcBuilder());
        bpmnToProc.setBuilder(builder);
        doNothing().when(builder).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));
        bpmnToProc.initializeLabelPositionOnSequenceFlow(id);
        verify(builder, never()).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));
    }

    @Test
    public void shouldInitializeLabelPositionOnEvent() throws ProcBuilderException {
        final BPMNShape shape = DiFactory.eINSTANCE.createBPMNShape();
        final BPMNLabel label = DiFactory.eINSTANCE.createBPMNLabel();
        final Bounds bounds = DcFactory.eINSTANCE.createBounds();
        final Bounds shapeBounds = DcFactory.eINSTANCE.createBounds();
        shapeBounds.setX(400);
        shapeBounds.setY(600);
        bounds.setX(100);
        bounds.setY(800);
        label.setBounds(bounds);
        shape.setBPMNLabel(label);
        shape.setBounds(shapeBounds);
        final String id = "bpmnShape";
        doReturn(shape).when(bpmnToProc).getBPMNShapeForBpmnID(id);
        final ProcBuilder builder = spy(new ProcBuilder());
        bpmnToProc.setBuilder(builder);
        doNothing().when(builder).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));
        bpmnToProc.initializeLabelPositionOnEvent(id);
        verify(builder).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));

    }

    @Test
    public void shouldNotinitializeLabelPositionOnEventWhenIdIsNull() throws ProcBuilderException {
        final ProcBuilder builder = spy(new ProcBuilder());
        bpmnToProc.setBuilder(builder);
        doNothing().when(builder).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));
        bpmnToProc.initializeLabelPositionOnEvent(null);
        verify(builder, never()).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));
    }

    @Test
    public void shouldNotinitializeLabelPositionOnEventWhenIdIsEmpty() throws ProcBuilderException {
        final ProcBuilder builder = spy(new ProcBuilder());
        bpmnToProc.setBuilder(builder);
        doNothing().when(builder).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));
        bpmnToProc.initializeLabelPositionOnEvent("");
        verify(builder, never()).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));
    }

    @Test
    public void shouldNotinitializeLabelPositionOnEventWhenIdShapeIsNull() throws ProcBuilderException {
        final ProcBuilder builder = spy(new ProcBuilder());
        bpmnToProc.setBuilder(builder);
        final String id = "MyShpae";
        doReturn(null).when(bpmnToProc).getBPMNShapeForBpmnID(id);
        doNothing().when(builder).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));
        bpmnToProc.initializeLabelPositionOnEvent(id);
        verify(builder, never()).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));
    }

    @Test
    public void shouldNotinitializeLabelPositionOnEventWhenLabelIsNull() throws ProcBuilderException {
        final BPMNShape shape = DiFactory.eINSTANCE.createBPMNShape();
        final ProcBuilder builder = spy(new ProcBuilder());
        bpmnToProc.setBuilder(builder);
        final String id = "MyShpae";
        doReturn(shape).when(bpmnToProc).getBPMNShapeForBpmnID(id);
        doNothing().when(builder).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));
        bpmnToProc.initializeLabelPositionOnEvent(id);
        verify(builder, never()).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));
    }

    @Test
    public void shouldNotinitializeLabelPositionOnEventWhenLabelBoundsIsNull() throws ProcBuilderException {
        final BPMNShape shape = DiFactory.eINSTANCE.createBPMNShape();
        final BPMNLabel label = DiFactory.eINSTANCE.createBPMNLabel();
        shape.setBPMNLabel(label);
        final ProcBuilder builder = spy(new ProcBuilder());
        bpmnToProc.setBuilder(builder);
        final String id = "MyShpae";
        doReturn(shape).when(bpmnToProc).getBPMNShapeForBpmnID(id);
        doNothing().when(builder).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));
        bpmnToProc.initializeLabelPositionOnEvent(id);
        verify(builder, never()).setLabelPositionOnSequenceFlowOrEvent(any(Point.class));
    }

    @Test
    public void shouldNotUpdateXMLNamespace() {
        final DocumentRoot documentRoot = mock(DocumentRoot.class);
        final EMap<String, String> map = mock(EMap.class);
        doReturn(map).when(documentRoot).getXMLNSPrefixMap();
        bpmnToProc.updateXMLNamespaceIfNeeded(documentRoot);
    }

    @Test
    public void shouldReturnNullWhenComputingBoundaryName() {
        final TEvent event = ModelFactory.eINSTANCE.createTBoundaryEvent();
        final String name = bpmnToProc.computeBoundaryName(event);
        assertNull(name);
    }

    @Test
    public void shouldReturnStringWhenComputingBoundaryName() {
        final TEvent event = ModelFactory.eINSTANCE.createTBoundaryEvent();
        final String name = "myEvent";
        event.setName(name);
        assertEquals(name, bpmnToProc.computeBoundaryName(event));
    }

    @Test
    public void shouldReturnIdWhenComputingBoundaryName() {
        final TEvent event = ModelFactory.eINSTANCE.createTBoundaryEvent();
        event.setName("");
        final String id = "myEvent";
        event.setId(id);
        assertEquals(id, bpmnToProc.computeBoundaryName(event));
    }

    @Test
    public void isDefaultSequenceFlow_shouldReturnFalse_WhenSequenceFlowHasNotId() {
        assertFalse(bpmnToProc.isSequenceFlowDefault(ModelFactory.eINSTANCE.createTSequenceFlow(), null));
    }

    @Test
    public void isDefaultSequenceFlow_shouldReturnFalse_WhenSequenceFlowHasNoSourceRef() {
        assertFalse(bpmnToProc.isSequenceFlowDefault(ModelFactory.eINSTANCE.createTSequenceFlow(), "myId"));

    }

    @Test
    public void isDefaultSequenceFlow_shouldReturnFalse_WhenSequenceFlowourceRefIsEmpty() {
        final TSequenceFlow sequenceFlow = ModelFactory.eINSTANCE.createTSequenceFlow();
        sequenceFlow.setSourceRef("");
        assertFalse(bpmnToProc.isSequenceFlowDefault(sequenceFlow, "myId"));

    }

    @Test
    public void isDefaultSequenceFlow_shouldReturnFalse_WhenSequenceFlowourceRefIsUserTask_andDefaultIsNotSet() {
        final TProcess process = ModelFactory.eINSTANCE.createTProcess();
        process.setId("processId");
        final List<TProcess> processes = new ArrayList<TProcess>();
        processes.add(process);
        final TSequenceFlow sequenceFlow = ModelFactory.eINSTANCE.createTSequenceFlow();
        final String sequenceFlowId = "sequenceFlowId";
        sequenceFlow.setId(sequenceFlowId);
        final TUserTask userTask = ModelFactory.eINSTANCE.createTUserTask();
        final String userTaskId = "userTaskId";
        userTask.setId(userTaskId);
        sequenceFlow.setSourceRef(userTaskId);
        process.getFlowElement().add(userTask);
        bpmnToProc.setBpmnProcess(processes);
        assertFalse(bpmnToProc.isSequenceFlowDefault(sequenceFlow, sequenceFlowId));

    }

    @Test
    public void isDefaultSequenceFlow_shouldReturnTrue_WhenSequenceFlowourceRefIsUserTask_andDefaultIsSet() {
        final TProcess process = ModelFactory.eINSTANCE.createTProcess();
        process.setId("processId");
        final List<TProcess> processes = new ArrayList<TProcess>();
        processes.add(process);
        final TSequenceFlow sequenceFlow = ModelFactory.eINSTANCE.createTSequenceFlow();
        final String sequenceFlowId = "sequenceFlowId";
        sequenceFlow.setId(sequenceFlowId);
        final TUserTask userTask = ModelFactory.eINSTANCE.createTUserTask();
        final String userTaskId = "userTaskId";
        userTask.setId(userTaskId);
        userTask.setDefault(sequenceFlowId);
        sequenceFlow.setSourceRef(userTaskId);
        process.getFlowElement().add(userTask);
        bpmnToProc.setBpmnProcess(processes);
        assertTrue(bpmnToProc.isSequenceFlowDefault(sequenceFlow, sequenceFlowId));
    }

    @Test
    public void isDefaultSequenceFlow_shouldReturnFalse_WhenSequenceFlowourceRefIsInclusiveGateway_andDefaultIsNotSet() {
        final TProcess process = ModelFactory.eINSTANCE.createTProcess();
        process.setId("processId");
        final List<TProcess> processes = new ArrayList<TProcess>();
        processes.add(process);
        final TSequenceFlow sequenceFlow = ModelFactory.eINSTANCE.createTSequenceFlow();
        final String sequenceFlowId = "sequenceFlowId";
        sequenceFlow.setId(sequenceFlowId);
        final TInclusiveGateway inclusiveGateway = ModelFactory.eINSTANCE.createTInclusiveGateway();
        final String inclusiveGatewayId = "inclusiveGatewayId";
        inclusiveGateway.setId(inclusiveGatewayId);
        sequenceFlow.setSourceRef(inclusiveGatewayId);
        process.getFlowElement().add(inclusiveGateway);
        bpmnToProc.setBpmnProcess(processes);
        assertFalse(bpmnToProc.isSequenceFlowDefault(sequenceFlow, sequenceFlowId));

    }

    @Test
    public void isDefaultSequenceFlow_shouldReturnFalse_WhenSequenceFlowourceRefIsInclusiveGateway_andDefaultIsSet() {
        final TProcess process = ModelFactory.eINSTANCE.createTProcess();
        process.setId("processId");
        final List<TProcess> processes = new ArrayList<TProcess>();
        processes.add(process);
        final TSequenceFlow sequenceFlow = ModelFactory.eINSTANCE.createTSequenceFlow();
        final String sequenceFlowId = "sequenceFlowId";
        sequenceFlow.setId(sequenceFlowId);
        final TInclusiveGateway inclusiveGateway = ModelFactory.eINSTANCE.createTInclusiveGateway();
        final String inclusiveGatewayId = "inclusiveGatewayId";
        inclusiveGateway.setId(inclusiveGatewayId);
        inclusiveGateway.setDefault(sequenceFlowId);
        sequenceFlow.setSourceRef(inclusiveGatewayId);
        process.getFlowElement().add(inclusiveGateway);
        bpmnToProc.setBpmnProcess(processes);
        assertTrue(bpmnToProc.isSequenceFlowDefault(sequenceFlow, sequenceFlowId));
    }

    @Test
    public void isDefaultSequenceFlow_shouldReturnFalse_WhenSequenceFlowourceRefIsExclusiveGateway_andDefaultIsNotSet() {
        final TProcess process = ModelFactory.eINSTANCE.createTProcess();
        process.setId("processId");
        final List<TProcess> processes = new ArrayList<TProcess>();
        processes.add(process);
        final TSequenceFlow sequenceFlow = ModelFactory.eINSTANCE.createTSequenceFlow();
        final String sequenceFlowId = "sequenceFlowId";
        sequenceFlow.setId(sequenceFlowId);
        final TExclusiveGateway exclusiveGateway = ModelFactory.eINSTANCE.createTExclusiveGateway();
        final String exclusiveGatewayId = "exclusiveGatewayId";
        exclusiveGateway.setId(exclusiveGatewayId);
        sequenceFlow.setSourceRef(exclusiveGatewayId);
        process.getFlowElement().add(exclusiveGateway);
        bpmnToProc.setBpmnProcess(processes);
        assertFalse(bpmnToProc.isSequenceFlowDefault(sequenceFlow, sequenceFlowId));

    }

    @Test
    public void isDefaultSequenceFlow_shouldReturnFalse_WhenSequenceFlowourceRefIsExclusiveGateway_andDefaultIsSet() {
        final TProcess process = ModelFactory.eINSTANCE.createTProcess();
        process.setId("processId");
        final List<TProcess> processes = new ArrayList<TProcess>();
        processes.add(process);
        final TSequenceFlow sequenceFlow = ModelFactory.eINSTANCE.createTSequenceFlow();
        final String sequenceFlowId = "sequenceFlowId";
        sequenceFlow.setId(sequenceFlowId);
        final TExclusiveGateway exclusiveGateway = ModelFactory.eINSTANCE.createTExclusiveGateway();
        final String exclusiveGatewayId = "exclusiveGatewayId";
        exclusiveGateway.setId(exclusiveGatewayId);
        exclusiveGateway.setDefault(sequenceFlowId);
        sequenceFlow.setSourceRef(exclusiveGatewayId);
        process.getFlowElement().add(exclusiveGateway);
        bpmnToProc.setBpmnProcess(processes);
        assertTrue(bpmnToProc.isSequenceFlowDefault(sequenceFlow, sequenceFlowId));
    }

    @Test
    public void isDefaultSequenceFlow_shouldReturnFalse_WhenSequenceFlowourceRefIsComplexGateway_andDefaultIsNotSet() {
        final TProcess process = ModelFactory.eINSTANCE.createTProcess();
        process.setId("processId");
        final List<TProcess> processes = new ArrayList<TProcess>();
        processes.add(process);
        final TSequenceFlow sequenceFlow = ModelFactory.eINSTANCE.createTSequenceFlow();
        final String sequenceFlowId = "sequenceFlowId";
        sequenceFlow.setId(sequenceFlowId);
        final TComplexGateway complexGateway = ModelFactory.eINSTANCE.createTComplexGateway();
        final String complexGatewayId = "complexGatewayId";
        complexGateway.setId(complexGatewayId);
        sequenceFlow.setSourceRef(complexGatewayId);
        process.getFlowElement().add(complexGateway);
        bpmnToProc.setBpmnProcess(processes);
        assertFalse(bpmnToProc.isSequenceFlowDefault(sequenceFlow, sequenceFlowId));

    }

    @Test
    public void isDefaultSequenceFlow_shouldReturnFalse_WhenSequenceFlowourceRefIsComplexGateway_andDefaultIsSet() {
        final TProcess process = ModelFactory.eINSTANCE.createTProcess();
        process.setId("processId");
        final List<TProcess> processes = new ArrayList<TProcess>();
        processes.add(process);
        final TSequenceFlow sequenceFlow = ModelFactory.eINSTANCE.createTSequenceFlow();
        final String sequenceFlowId = "sequenceFlowId";
        sequenceFlow.setId(sequenceFlowId);
        final TComplexGateway complexGateway = ModelFactory.eINSTANCE.createTComplexGateway();
        final String complexGatewayId = "exclusiveGatewayId";
        complexGateway.setId(complexGatewayId);
        complexGateway.setDefault(sequenceFlowId);
        sequenceFlow.setSourceRef(complexGatewayId);
        process.getFlowElement().add(complexGateway);
        bpmnToProc.setBpmnProcess(processes);
        assertTrue(bpmnToProc.isSequenceFlowDefault(sequenceFlow, sequenceFlowId));
    }

    @Test
    public void should_handle_LoopCharacteristics_on_activities_for_ParallelMultiInstance() throws Exception {
        final TProcess process = ModelFactory.eINSTANCE.createTProcess();
        process.setId("processId");
        final TUserTask userTask = ModelFactory.eINSTANCE.createTUserTask();
        final String userTaskId = "userTaskId";
        userTask.setId(userTaskId);
        final TMultiInstanceLoopCharacteristics loopCharacteristics = ModelFactory.eINSTANCE.createTMultiInstanceLoopCharacteristics();
        loopCharacteristics.setCompletionCondition(ModelFactory.eINSTANCE.createTExpression());
        userTask.setLoopCharacteristics(loopCharacteristics);
        process.getFlowElement().add(userTask);
        bpmnToProc.setBpmnProcess(newArrayList(process));
        final IProcBuilder builder = mock(IProcBuilder.class);
        bpmnToProc.setBuilder(builder);
        final TDefinitions definitions = ModelFactory.eINSTANCE.createTDefinitions();
        definitions.getRootElement().add(process);
        bpmnToProc.importFromBPMN(definitions);

        verify(builder).addMultiInstantiation(false);
        verify(builder).addCompletionConditionExpression(notNull(Expression.class));
    }

    @Test
    public void should_handle_LoopCharacteristics_on_activities_for_SequentialMultiInstance() throws Exception {
        final TProcess process = ModelFactory.eINSTANCE.createTProcess();
        process.setId("processId");
        final TUserTask userTask = ModelFactory.eINSTANCE.createTUserTask();
        final String userTaskId = "userTaskId";
        userTask.setId(userTaskId);
        final TMultiInstanceLoopCharacteristics loopCharacteristics = ModelFactory.eINSTANCE.createTMultiInstanceLoopCharacteristics();
        loopCharacteristics.setLoopCardinality(ModelFactory.eINSTANCE.createTExpression());
        loopCharacteristics.setIsSequential(true);
        userTask.setLoopCharacteristics(loopCharacteristics);
        process.getFlowElement().add(userTask);
        bpmnToProc.setBpmnProcess(newArrayList(process));
        final IProcBuilder builder = mock(IProcBuilder.class);
        bpmnToProc.setBuilder(builder);
        final TDefinitions definitions = ModelFactory.eINSTANCE.createTDefinitions();
        definitions.getRootElement().add(process);
        bpmnToProc.importFromBPMN(definitions);

        verify(builder).addMultiInstantiation(true);
        verify(builder).addCardinalityExpression(notNull(Expression.class));
    }

    @Test
    public void should_handle_LoopCharacteristics_on_activities_for_StandardLoop() throws Exception {
        final TProcess process = ModelFactory.eINSTANCE.createTProcess();
        process.setId("processId");
        final TUserTask userTask = ModelFactory.eINSTANCE.createTUserTask();
        final String userTaskId = "userTaskId";
        userTask.setId(userTaskId);
        final TStandardLoopCharacteristics loopCharacteristics = ModelFactory.eINSTANCE.createTStandardLoopCharacteristics();
        loopCharacteristics.setLoopCondition(ModelFactory.eINSTANCE.createTExpression());
        loopCharacteristics.setLoopMaximum(BigInteger.TEN);
        loopCharacteristics.setTestBefore(false);
        userTask.setLoopCharacteristics(loopCharacteristics);
        process.getFlowElement().add(userTask);
        bpmnToProc.setBpmnProcess(newArrayList(process));
        final IProcBuilder builder = mock(IProcBuilder.class);
        bpmnToProc.setBuilder(builder);
        final TDefinitions definitions = ModelFactory.eINSTANCE.createTDefinitions();
        definitions.getRootElement().add(process);
        bpmnToProc.importFromBPMN(definitions);

        verify(builder).addLoopCondition(notNull(Expression.class), eq("10"), eq(TestTimeType.AFTER));
    }

}
