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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.util.EList;
import org.omg.spec.bpmn.di.BPMNEdge;
import org.omg.spec.bpmn.di.BPMNLabel;
import org.omg.spec.dd.dc.Bounds;

/**
 * @author Aurelie Zara
 * @author Aurelien Pupier
 * The location need to be computed in the referential with x following the direction of the edge and y in anti-trigo
 */
public class SequenceFlowLabelLocationCalculator {

	private final BPMNEdge edge;
	private final BPMNLabel label;
	private org.omg.spec.dd.dc.Point startPointOfCenterEdge = null;
	private org.omg.spec.dd.dc.Point endPointOfCenterEdge = null;

	/**
	 * @param edge
	 * @param label
	 */
	public SequenceFlowLabelLocationCalculator(final BPMNEdge edge, final BPMNLabel label) {
		this.edge = edge;
		this.label = label;
	}

	public Point computeLabelLocation() {
		final Bounds bounds = label.getBounds();
		final Point edgeCenter = getEdgeCenter(edge.getWaypoint());
		return computeLabelLocation(bounds, edgeCenter, startPointOfCenterEdge, endPointOfCenterEdge);
	}

	protected Point computeLabelLocation(final Bounds bounds, final Point edgeCenter, final org.omg.spec.dd.dc.Point pointA, final org.omg.spec.dd.dc.Point pointB) {
		boolean isVerticalAndFromUpToDown = pointA.getX() - pointB.getX() == 0 && (pointA.getY() - pointB.getY() > 0);
		boolean isVerticalAndFromDownToUp =pointA.getX() - pointB.getX() == 0 && (pointA.getY() - pointB.getY() < 0);
		boolean isHozitontalAndFromLeftToRigth = pointA.getY() - pointB.getY() == 0 && pointA.getX() - pointB.getX() < 0;
		boolean isHozitontalAndisFromRightToLeft = pointA.getY() - pointB.getY() == 0 && pointA.getX() - pointB.getX() > 0;
		if (isVerticalAndFromUpToDown) {
			return new Point(-(bounds.getY() - edgeCenter.y()), bounds.getX() - edgeCenter.x());//vertical Bottom-Up
		}
		if (isVerticalAndFromDownToUp) {
			return new Point(bounds.getY() - edgeCenter.y(), -(bounds.getX() - edgeCenter.x()));//vertical Up-Down
		}
		if (isHozitontalAndFromLeftToRigth) {
			return new Point(bounds.getX() - edgeCenter.x(), bounds.getY() - edgeCenter.y());//horizontal Left-to-Right
		}
		if (isHozitontalAndisFromRightToLeft) {
			return new Point(-(bounds.getX() - edgeCenter.x()), -(bounds.getY() - edgeCenter.y()));//horizontal Right-to-Left
		}
	return new Point(0, 0);
}

protected Point getEdgeCenter(final EList<org.omg.spec.dd.dc.Point> eList) {
	final double edgeHalfLength = computeEdgeLength(eList) / 2;
	double total = 0;
	double tmp = 0;
	for (int i = 0; i < eList.size() - 1; i++) {
		tmp = total;
		final org.omg.spec.dd.dc.Point pointA = eList.get(i);
		final org.omg.spec.dd.dc.Point pointB = eList.get(i + 1);
		total = total + computeSegmentLength(pointA, pointB);
		if (total > edgeHalfLength) {
			startPointOfCenterEdge = pointA;
			endPointOfCenterEdge = pointB;
			return computeEdgeCenter(edgeHalfLength - tmp, pointA, pointB);
		}
	}
	return new Point(0, 0);
}

protected double computeEdgeLength(final EList<org.omg.spec.dd.dc.Point> eList) {
	double total = 0;
	for (int i = 0; i < eList.size() - 1; i++) {
		final org.omg.spec.dd.dc.Point pointA = eList.get(i);
		final org.omg.spec.dd.dc.Point pointB = eList.get(i + 1);
		total = total + computeSegmentLength(pointA, pointB);
	}
	return total;
}

protected double computeSegmentLength(final org.omg.spec.dd.dc.Point pointA, final org.omg.spec.dd.dc.Point pointB) {
	return Math.sqrt(Math.pow(pointB.getX() - pointA.getX(), 2) + Math.pow(pointB.getY() - pointA.getY(), 2));
}

protected Point computeEdgeCenter(final double segmentLength, final org.omg.spec.dd.dc.Point a, final org.omg.spec.dd.dc.Point b) {
	if (a.getX() - b.getX() == 0) {//Vertical
		if (a.getY() - b.getY() > 0) {//up_to_down
			return new Point(a.getX(), a.getY() - segmentLength);
		} else {//Left-to-right
			return new Point(a.getX(), a.getY() + segmentLength);
		}
	} else {
		if (a.getY() - b.getY() == 0) {//Horizontal
			if (a.getX() - b.getX() > 0) {//right_to_left
				return new Point(a.getX() - segmentLength, a.getY());
			} else {
				return new Point(a.getX() + segmentLength, a.getY());
			}
		}
	}
	return new Point(a.getX(),b.getX());
}

}
