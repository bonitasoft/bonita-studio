/**
 * Copyright (C) 2014-2015 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.exporter.bpmn.transfo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.assertj.core.api.Assertions;
import org.bonitasoft.studio.common.emf.tools.ExpressionHelper;
import org.bonitasoft.studio.common.gmf.CustomEventLabelEditPart;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.MultiInstanceType;
import org.bonitasoft.studio.model.process.Pool;
import org.bonitasoft.studio.model.process.ProcessFactory;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.builders.XMLDataBuilder;
import org.bonitasoft.studio.model.process.builders.XMLDataTypeBuilder;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SequenceFlowNameEditPart;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.omg.spec.bpmn.di.BPMNEdge;
import org.omg.spec.bpmn.di.BPMNLabel;
import org.omg.spec.bpmn.di.BPMNLabelStyle;
import org.omg.spec.bpmn.di.BPMNShape;
import org.omg.spec.bpmn.di.DiFactory;
import org.omg.spec.bpmn.model.TActivity;
import org.omg.spec.bpmn.model.TFormalExpression;
import org.omg.spec.bpmn.model.TMultiInstanceLoopCharacteristics;
import org.omg.spec.bpmn.model.TStandardLoopCharacteristics;
import org.omg.spec.bpmn.model.TTask;
import org.omg.spec.dd.dc.Font;

@RunWith(MockitoJUnitRunner.class)
public class BonitaToBPMNTest extends BonitaToBPMN {

	private BonitaToBPMN bonitaToBPMN;

    @Before
    public void setUp() throws Exception {
        bonitaToBPMN = spy(new BonitaToBPMN());
    }

    @Test
    public void testMultiInstanceLoop() {
        final Task bonitaTask = ProcessFactory.eINSTANCE.createTask();
        bonitaTask.setType(MultiInstanceType.STANDARD);
        bonitaTask.setLoopMaximum(ExpressionHelper.createConstantExpression("45", Integer.class.getName()));
        bonitaTask.setLoopCondition(ExpressionHelper.createGroovyScriptExpression("my condition script", Boolean.class.getName()));
        bonitaTask.setTestBefore(true);

        final TTask bpmTask = (TTask) bonitaToBPMN.createActivity(bonitaTask);
        final TStandardLoopCharacteristics loopCharacteristics = (TStandardLoopCharacteristics) bpmTask.getLoopCharacteristics();
        assertThat(loopCharacteristics).isNotNull();
        assertThat(loopCharacteristics.getLoopMaximum()).isEqualTo(BigInteger.valueOf(45));
        assertThat(((TFormalExpression) loopCharacteristics.getLoopCondition()).getMixed().getValue(0)).isEqualTo("my condition script");
        assertThat(loopCharacteristics.isTestBefore()).isTrue();
    }

    @Test
    public void testMultiInstanceParallelWithFilledAttributes() {
        final Activity bonitaActivity = ProcessFactory.eINSTANCE.createActivity();
        bonitaActivity.setType(MultiInstanceType.PARALLEL);
        bonitaActivity.setCompletionCondition(ExpressionHelper.createGroovyScriptExpression("my completion condition", Boolean.class.getName()));
        bonitaActivity.setCardinalityExpression(ExpressionHelper.createConstantExpression("45", Integer.class.getName()));

        final TActivity bpmTask = (TActivity) bonitaToBPMN.createActivity(bonitaActivity);
        final TMultiInstanceLoopCharacteristics loopCharacteristics = (TMultiInstanceLoopCharacteristics) bpmTask.getLoopCharacteristics();
        Assertions.assertThat(loopCharacteristics).isNotNull();
        Assertions.assertThat(loopCharacteristics.isIsSequential()).isFalse();
        Assertions.assertThat(loopCharacteristics.getCompletionCondition().getMixed().getValue(0)).isEqualTo("my completion condition");
        Assertions.assertThat(loopCharacteristics.getLoopCardinality().getMixed().getValue(0)).isEqualTo("45");
    }

    @Test
    public void testMultiInstanceSequentialWithFilledAttributes() {
        final Activity bonitaActivity = ProcessFactory.eINSTANCE.createActivity();
        bonitaActivity.setType(MultiInstanceType.SEQUENTIAL);
        bonitaActivity.setCompletionCondition(ExpressionHelper.createGroovyScriptExpression("my completion condition", Boolean.class.getName()));

        final TActivity bpmTask = (TActivity) bonitaToBPMN.createActivity(bonitaActivity);
        final TMultiInstanceLoopCharacteristics loopCharacteristics = (TMultiInstanceLoopCharacteristics) bpmTask.getLoopCharacteristics();
        Assertions.assertThat(loopCharacteristics).isNotNull();
        Assertions.assertThat(loopCharacteristics.isIsSequential()).isTrue();
        Assertions.assertThat(loopCharacteristics.getCompletionCondition().getMixed().getValue(0)).isEqualTo("my completion condition");
    }

    @Test
    @Ignore("Multi instance initialized by a data is not implemented yet")
    public void testMultiInstanceInitializedByData() {
        final Pool pool = ProcessFactory.eINSTANCE.createPool();
        final Data data = ProcessFactory.eINSTANCE.createData();
        data.setName("myData");
        pool.getData().add(data);
        final Activity bonitaActivity = ProcessFactory.eINSTANCE.createActivity();
        pool.getElements().add(bonitaActivity);
        bonitaActivity.setType(MultiInstanceType.PARALLEL);
        bonitaActivity.setCollectionDataToMultiInstantiate(data);
        //Assertions.assertThat(loopCharacteristics.getLoopDataInputRef().getLocalPart()).isEqualTo("myData");
    }

    @Test
    public void testMultiInstanceParallelWithMinimalAttributesFilled() {
        final Activity bonitaActivity = ProcessFactory.eINSTANCE.createActivity();
        bonitaActivity.setType(MultiInstanceType.PARALLEL);

        final TActivity bpmTask = (TActivity) bonitaToBPMN.createActivity(bonitaActivity);
        final TMultiInstanceLoopCharacteristics loopCharacteristics = (TMultiInstanceLoopCharacteristics) bpmTask.getLoopCharacteristics();
        Assertions.assertThat(loopCharacteristics).isNotNull();
        Assertions.assertThat(loopCharacteristics.isIsSequential()).isFalse();
        Assertions.assertThat(loopCharacteristics.getCompletionCondition()).isNull();
        Assertions.assertThat(loopCharacteristics.getLoopCardinality()).isNull();
    }

    @Test
    public void should_export_xmldata_without_namespace() throws Exception {
        bonitaToBPMN.initializeDocumentRoot();
        bonitaToBPMN.configureNamespaces();
        final QName ref = bonitaToBPMN.getStructureRef(XMLDataBuilder.anXMLData().havingDataType(XMLDataTypeBuilder.create()).build());
        final QName ref2 = bonitaToBPMN.getStructureRef(XMLDataBuilder.anXMLData().havingDataType(XMLDataTypeBuilder.create()).build());
        assertThat(ref.getLocalPart()).isEqualTo("n0:null");
        assertThat(ref2.getLocalPart()).isEqualTo("n1:null");
    }

    @Test
    public void should_set_sequenceFlow_label(){
    	final SequenceFlowNameEditPart labelPart=mock(SequenceFlowNameEditPart.class);
    	final Node node = mock(Node.class);
    	final Figure figure = mock(Figure.class);
    	when(labelPart.getNotationView()).thenReturn(node);
    	when(labelPart.getFigure()).thenReturn(figure);
    	final Rectangle rectangle = new Rectangle(15,30,50,200);
    	when(figure.getBounds()).thenReturn(rectangle);
    	doReturn(rectangle).when(bonitaToBPMN).getAbsoluteLabelBounds(figure);
    	final Location location = mock(Location.class);
    	when(node.getLayoutConstraint()).thenReturn(location);
    	final BPMNEdge edge = DiFactory.eINSTANCE.createBPMNEdge();
    	bonitaToBPMN.setSequenceFLowLabel(labelPart, edge);
    	assertNotNull(edge.getBPMNLabel());
    }

    @Test
    public void should_set_sequenceFlow_label_DontCrashIfNoEditPart() {
        final BPMNEdge edge = DiFactory.eINSTANCE.createBPMNEdge();
        bonitaToBPMN.setSequenceFLowLabel(null, edge);
    }

    @Test
    public void should_set_event_label(){
    	final CustomEventLabelEditPart labelPart=mock(CustomEventLabelEditPart.class);
    	final Node node = mock(Node.class);
    	final Figure figure = mock(Figure.class);
    	when(labelPart.getNotationView()).thenReturn(node);
    	when(labelPart.getFigure()).thenReturn(figure);
    	final Rectangle rectangle = new Rectangle(15,30,50,200);
    	when(figure.getBounds()).thenReturn(rectangle);
    	doReturn(rectangle).when(bonitaToBPMN).getAbsoluteLabelBounds(figure);
    	final BPMNLabel label = DiFactory.eINSTANCE.createBPMNLabel();
    	bonitaToBPMN.setEventLabelBounds(labelPart, label);
    	assertEquals(15,(int)label.getBounds().getX());
    	assertEquals(30,(int)label.getBounds().getY());
    }

    @Test
    public void shouldCreateBPMNShape(){
        final ShapeNodeEditPart shapePart = mock(ShapeNodeEditPart.class);
        final CustomEventLabelEditPart labelPart = mock(CustomEventLabelEditPart.class);
        final ArrayList<EditPart> children = new ArrayList<EditPart>();
        children.add(labelPart);
        when(shapePart.getChildren()).thenReturn(children);
        final Font font = mock(Font.class);
        final BPMNLabelStyle labelStyle = DiFactory.eINSTANCE.createBPMNLabelStyle();
        labelStyle.setId("myLabelStyle");
        doReturn(font).when(bonitaToBPMN).createFont(shapePart);
        doReturn(labelStyle).when(bonitaToBPMN).getLabelStyle(font);
        final Map<String, String> map = new HashMap<String, String>();
        doReturn(map).when(bonitaToBPMN).getShapeColors(shapePart);
        doNothing().when(bonitaToBPMN).setEventLabelBounds(any(CustomEventLabelEditPart.class), any(BPMNLabel.class));
        final BPMNShape bpmnShape = bonitaToBPMN.createBPMNShape(shapePart);
        assertNotNull(bpmnShape);
        assertNotNull(bpmnShape.getBPMNLabel());
    }
}
