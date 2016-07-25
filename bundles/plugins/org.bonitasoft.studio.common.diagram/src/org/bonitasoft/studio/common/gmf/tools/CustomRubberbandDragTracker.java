/**
 * Copyright (C) 2010 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.gmf.tools;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.tools.RubberbandDragTracker;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class CustomRubberbandDragTracker extends RubberbandDragTracker {

	private IFigure marqueeRectangleFigure;

	@Override
	protected void performMarqueeSelect() {
		super.performMarqueeSelect() ;
		EditPartViewer viewer = getCurrentViewer();
		if(viewer.getSelection().isEmpty() || 
				((StructuredSelection) viewer.getSelection()).getFirstElement() instanceof DiagramEditPart){
			viewer.setSelection(new StructuredSelection(viewer.findObjectAt(getStartLocation()).getParent())) ;
		}

	}
	
	protected IFigure getMarqueeFeedbackFigure() {		

		if (marqueeRectangleFigure == null) {
			marqueeRectangleFigure = new CustomMarqueeRectangleFigure();
			addFeedback(marqueeRectangleFigure);
		}
		return marqueeRectangleFigure;
	}

	private void eraseMarqueeFeedback() {
		if (marqueeRectangleFigure != null) {
			removeFeedback(marqueeRectangleFigure);
			marqueeRectangleFigure = null;		
		}
	}

	@Override
	public void deactivate() {
		if (isInState(STATE_DRAG_IN_PROGRESS)) {
			eraseMarqueeFeedback();
		}
		super.deactivate();
	}
	@Override
	protected boolean handleButtonUp(int button) {
		super.handleButtonUp(button);
		eraseMarqueeFeedback();		
		return true ;
	}

	@Override
	protected boolean handleInvalidInput() {
		eraseMarqueeFeedback();
		return super.handleInvalidInput();
	}

	private class CustomMarqueeRectangleFigure extends Figure {

		private int offset = 0;
		private boolean schedulePaint = true;
		private static final int DELAY = 110; //animation delay in millisecond
		/**
		 * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
		 */
		protected void paintFigure(Graphics graphics) {	
			Rectangle bounds1 = getBounds().getCopy();
			graphics.translate(getLocation());

			graphics.setXORMode(false);
			graphics.setForegroundColor(ColorConstants.black);
			graphics.setBackgroundColor(ColorConstants.black);

			//graphics.drawRectangle(bounds1) ;

			graphics.setLineStyle(Graphics.LINE_DOT);

			int[] points = new int[6];

			points[0] = 0 + offset;
			points[1] = 0;
			points[2] = bounds1.width - 1;
			points[3] = 0;
			points[4] = bounds1.width - 1;
			points[5] = bounds1.height - 1;

			graphics.drawPolyline(points);

			points[0] = 0;
			points[1] = 0 + offset;
			points[2] = 0;
			points[3] = bounds1.height - 1;
			points[4] = bounds1.width - 1;
			points[5] = bounds1.height - 1;

			graphics.drawPolyline(points);

			graphics.translate(getLocation().getNegated());

			if (schedulePaint) {
				Display.getCurrent().timerExec(DELAY, new Runnable() {
					public void run() {
						offset++;
						if (offset > 5)
							offset = 0;	

						schedulePaint = true;
						repaint();
					}
				});
			}

			schedulePaint = false;
		}

	}
}
