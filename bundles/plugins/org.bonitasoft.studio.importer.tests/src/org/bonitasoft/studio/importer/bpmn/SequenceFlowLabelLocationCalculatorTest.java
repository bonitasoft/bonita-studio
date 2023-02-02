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

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.omg.spec.bpmn.di.BPMNEdge;
import org.omg.spec.bpmn.di.BPMNLabel;
import org.omg.spec.bpmn.di.DiFactory;
import org.omg.spec.dd.dc.Bounds;
import org.omg.spec.dd.dc.DcFactory;
import org.omg.spec.dd.dc.Point;


public class SequenceFlowLabelLocationCalculatorTest {

    private SequenceFlowLabelLocationCalculator calculator ;
    private BPMNEdge edge ;

    @Before
     public void setup(){

        edge = DiFactory.eINSTANCE.createBPMNEdge();
        final BPMNLabel label =DiFactory.eINSTANCE.createBPMNLabel();
        final Bounds bounds = DcFactory.eINSTANCE.createBounds();
        bounds.setX(100);
        bounds.setY(800);
        bounds.setHeight(50);
        bounds.setWidth(50);
        label.setBounds(bounds);
        final Point pointA =DcFactory.eINSTANCE.createPoint();
        final Point pointB =DcFactory.eINSTANCE.createPoint();
        final Point pointC =DcFactory.eINSTANCE.createPoint();
        final Point pointD =DcFactory.eINSTANCE.createPoint();
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

        calculator = new SequenceFlowLabelLocationCalculator(edge, label);
     }

    @Test
    public void computeEdgeLocationTest(){
        final org.eclipse.draw2d.geometry.Point point=calculator.computeLabelLocation();
        assertEquals(0,point.x());
        assertEquals(0, point.y());
    }

    @Test
    public void computeSegmentLengthTest(){
        final Point pointA =DcFactory.eINSTANCE.createPoint();
        final Point pointB =DcFactory.eINSTANCE.createPoint();
        pointA.setX(100);
        pointA.setY(900);
        pointB.setX(400);
        pointB.setY(100);
        final double segmentLength = calculator.computeSegmentLength(pointA, pointB);
        assertEquals(854,(int)segmentLength);
    }

    @Test
    public void computeEdgeCenter(){
        final  org.eclipse.draw2d.geometry.Point edgeCenter = calculator.getEdgeCenter(edge.getWaypoint());
        assertEquals(100,edgeCenter.x());
        assertEquals(400,edgeCenter.y());
    }

    @Test
    public void should_compute_edge_length(){
        final double edgeLength = calculator.computeEdgeLength(edge.getWaypoint());
        assertEquals(1661,(int)edgeLength);
    }


    @Test
    public void should_compute_edgeCenter_when_edgeIsHorizontal_andFromLeftToRight(){
        final Point pointA =DcFactory.eINSTANCE.createPoint();
        final Point pointB =DcFactory.eINSTANCE.createPoint();
        pointA.setX(200);
        pointA.setY(100);
        pointB.setX(400);
        pointB.setY(100);
        final double segmentLength = 100;
        final org.eclipse.draw2d.geometry.Point point = calculator.computeEdgeCenter(segmentLength, pointA, pointB);
        assertEquals(300,point.x());
        assertEquals(100,point.y());
    }

    @Test
    public void should_compute_edgeCenter_when_edgeIsHorizontal_andFromRightToLeft(){
        final Point pointA =DcFactory.eINSTANCE.createPoint();
        final Point pointB =DcFactory.eINSTANCE.createPoint();
        pointA.setX(200);
        pointA.setY(100);
        pointB.setX(400);
        pointB.setY(100);
        final double segmentLength = 100;
        final org.eclipse.draw2d.geometry.Point point = calculator.computeEdgeCenter(segmentLength, pointB, pointA);
        assertEquals(300,point.x());
        assertEquals(100,point.y());

    }

    @Test
    public void should_compute_edgeCenter_when_edgeIsVertical_andFromDownToUp(){
        final Point pointA =DcFactory.eINSTANCE.createPoint();
        final Point pointB =DcFactory.eINSTANCE.createPoint();
        pointA.setX(100);
        pointA.setY(200);
        pointB.setX(100);
        pointB.setY(400);
        final double segmentLength = 100;
        final org.eclipse.draw2d.geometry.Point point = calculator.computeEdgeCenter(segmentLength, pointA, pointB);
        assertEquals(100,point.x());
        assertEquals(300,point.y());
    }

    @Test
    public void should_compute_edgeCenter_when_edgeIsVertical_andFromUpToDown(){
        final Point pointA =DcFactory.eINSTANCE.createPoint();
        final Point pointB =DcFactory.eINSTANCE.createPoint();
        pointA.setX(100);
        pointA.setY(200);
        pointB.setX(100);
        pointB.setY(400);
        final double segmentLength = 100;
        final org.eclipse.draw2d.geometry.Point point = calculator.computeEdgeCenter(segmentLength, pointB, pointA);
        assertEquals(100,point.x());
        assertEquals(300,point.y());
    }


    @Test
    public void should_compute_edgeCenter_when_edgeIsAPoint(){
        final Point pointA =DcFactory.eINSTANCE.createPoint();
        final Point pointB =DcFactory.eINSTANCE.createPoint();
        pointA.setX(100);
        pointA.setY(100);
        pointB.setX(100);
        pointB.setY(100);
        final double segmentLength = 0;
        final org.eclipse.draw2d.geometry.Point point = calculator.computeEdgeCenter(segmentLength, pointA, pointB);
        assertEquals(100,point.x());
        assertEquals(100,point.y());
    }

    @Test
    public void should_compute_labelPosition_when_edgeIsHorizontal_andFromLeftToRight(){
        final Point pointA =DcFactory.eINSTANCE.createPoint();
        final Point pointB =DcFactory.eINSTANCE.createPoint();
        pointA.setX(200);
        pointA.setY(100);
        pointB.setX(400);
        pointB.setY(100);
        final org.eclipse.draw2d.geometry.Point edgeCenter=new org.eclipse.draw2d.geometry.Point(300,100);
        final Bounds bounds = DcFactory.eINSTANCE.createBounds();
        bounds.setHeight(20);
        bounds.setWidth(50);
        bounds.setX(300);
        bounds.setY(400);
        final org.eclipse.draw2d.geometry.Point labelLocation=calculator.computeLabelLocation(bounds, edgeCenter, pointA, pointB);
        assertEquals(0,labelLocation.x());
        assertEquals(300, labelLocation.y());
    }

    @Test
    public void should_compute_labelPosition_when_edgeIsHorizontal_andFromRightToLeft(){
        final Point pointA =DcFactory.eINSTANCE.createPoint();
        final Point pointB =DcFactory.eINSTANCE.createPoint();
        pointA.setX(200);
        pointA.setY(100);
        pointB.setX(400);
        pointB.setY(100);
        final org.eclipse.draw2d.geometry.Point edgeCenter=new org.eclipse.draw2d.geometry.Point(300,100);
        final Bounds bounds = DcFactory.eINSTANCE.createBounds();
        bounds.setHeight(20);
        bounds.setWidth(50);
        bounds.setX(300);
        bounds.setY(400);
        final org.eclipse.draw2d.geometry.Point labelLocation=calculator.computeLabelLocation(bounds, edgeCenter, pointB, pointA);
        assertEquals(0,labelLocation.x());
        assertEquals(-300, labelLocation.y());
    }

    @Test
    public void should_compute_labelPosition_when_edgeIsVertical_andFromDownToUp(){
        final Point pointA =DcFactory.eINSTANCE.createPoint();
        final Point pointB =DcFactory.eINSTANCE.createPoint();
        pointA.setX(100);
        pointA.setY(200);
        pointB.setX(100);
        pointB.setY(400);
        final org.eclipse.draw2d.geometry.Point edgeCenter=new org.eclipse.draw2d.geometry.Point(100,300);
        final Bounds bounds = DcFactory.eINSTANCE.createBounds();
        bounds.setHeight(20);
        bounds.setWidth(50);
        bounds.setX(400);
        bounds.setY(300);
        final org.eclipse.draw2d.geometry.Point labelLocation=calculator.computeLabelLocation(bounds, edgeCenter, pointA, pointB);
        assertEquals(0,labelLocation.x());
        assertEquals(-300, labelLocation.y());
    }

    @Test
    public void should_compute_labelPosition_when_edgeIsVertical_andFromUpToDown(){
        final Point pointA =DcFactory.eINSTANCE.createPoint();
        final Point pointB =DcFactory.eINSTANCE.createPoint();
        pointA.setX(100);
        pointA.setY(200);
        pointB.setX(100);
        pointB.setY(400);
        final org.eclipse.draw2d.geometry.Point edgeCenter=new org.eclipse.draw2d.geometry.Point(100,300);
        final Bounds bounds = DcFactory.eINSTANCE.createBounds();
        bounds.setHeight(20);
        bounds.setWidth(50);
        bounds.setX(400);
        bounds.setY(300);
        final org.eclipse.draw2d.geometry.Point labelLocation=calculator.computeLabelLocation(bounds, edgeCenter, pointB, pointA);
        assertEquals(0,labelLocation.x());
        assertEquals(300, labelLocation.y());
    }

}
