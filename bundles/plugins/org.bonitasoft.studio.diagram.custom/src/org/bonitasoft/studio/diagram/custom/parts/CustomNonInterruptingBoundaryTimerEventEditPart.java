/*
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.diagram.custom.parts;

import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.model.process.diagram.edit.parts.NonInterruptingBoundaryTimerEvent2EditPart;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.gef.ui.figures.DefaultSizeNodeFigure;
import org.eclipse.gmf.runtime.gef.ui.figures.NodeFigure;
import org.eclipse.gmf.runtime.notation.View;

public class CustomNonInterruptingBoundaryTimerEventEditPart extends NonInterruptingBoundaryTimerEvent2EditPart {

    public CustomNonInterruptingBoundaryTimerEventEditPart(View view) {
        super(view);
    }

    @Override
    protected NodeFigure createNodePlate() {
        final DefaultSizeNodeFigure result = new DefaultSizeNodeFigure(FiguresHelper.BOUNDARY_EVENT_WIDTH,
                FiguresHelper.BOUNDARY_EVENT_WIDTH) {

            @Override
            public PointList getPolygonPoints() {
                final Rectangle anchRect = getHandleBounds();
                return FiguresHelper.CirclePointList(anchRect);
            }
        };

        //FIXME: workaround for #154536
        result.getBounds().setSize(result.getPreferredSize());
        return result;
    }

}
