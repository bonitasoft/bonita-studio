/**
 * Copyright (C) 2017 Bonitasoft S.A.
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

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.LineSeg;
import org.eclipse.gmf.runtime.draw2d.ui.geometry.PointListUtilities;

public class LabelHelper {

    public static Point calculatePointRelativeToPointOnLine(PointList ptLst, Point ptOnLine, Point offset) {
        // Calculate slope of line
        if (ptLst.size() == 1) {
            // This is a node...
            return ptLst.getFirstPoint().getTranslated(offset);
        } else if (ptLst.size() >= 2) {
            // This is a edge...
            int index = PointListUtilities.findNearestLineSegIndexOfPoint(ptLst, ptOnLine);
            if (index < 1) {
                return ptLst.getFirstPoint().getTranslated(offset);
            }
            LineSeg segment = (LineSeg) PointListUtilities.getLineSegments(ptLst).get(index - 1);
            if (segment != null) {
                if (segment.isHorizontal()) {
                    if (segment.getOrigin().x > segment.getTerminus().x) {
                        return ptOnLine.getTranslated(offset.getNegated());
                    } else {
                        return ptOnLine.getTranslated(offset);
                    }
                } else if (segment.isVertical()) {
                    if (segment.getOrigin().y > segment.getTerminus().y) {
                        return ptOnLine.getTranslated(offset.getCopy().scale(-1, 1).transpose());
                    } else {
                        return ptOnLine.getTranslated(offset.getCopy().scale(1, -1).transpose());
                    }
                } else {
                    double slope = segment.slope();
                    double theta = Math.atan(slope);
                    Point normalizedOffset = new Point(offset);
                    Point calculatedOffset = new Point();
                    if (segment.getOrigin().x > segment.getTerminus().x) {
                        normalizedOffset = offset.getCopy().scale(-1, -1);
                    }

                    calculatedOffset = new Point(normalizedOffset.x
                            * Math.cos(theta)
                            - normalizedOffset.y
                                    * Math.sin(theta),
                            normalizedOffset.x * Math.sin(theta)
                                    + normalizedOffset.y * Math.cos(theta));
                    return ptOnLine.getTranslated(calculatedOffset);
                }
            }
        }
        return null;
    }

}
