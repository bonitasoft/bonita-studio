/**
 * Copyright (C) 2009 BonitaSoft S.A.
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

package org.bonitasoft.studio.diagram.custom.editPolicies;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.SnapToGeometry;
import org.eclipse.gef.SnapToGuides;
import org.eclipse.gef.editpolicies.GraphicalEditPolicy;
import org.eclipse.gmf.runtime.draw2d.ui.figures.FigureUtilities;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 * @author Aurelien Pupier - improve resource management, avoid SWT leaks
 *         (handle)
 */
public class CustomSnapFeedbackPolicy extends GraphicalEditPolicy {

	private static final Object REQ_DROP = "drop"; //$NON-NLS-1$
	
	IFigure guide[] = new IFigure[6];
	Integer location[] = new Integer[6];
	private Color guideColor = ColorConstants.red;

	/**
	 * @see org.eclipse.gef.EditPolicy#eraseTargetFeedback(org.eclipse.gef.Request)
	 */
	public void eraseTargetFeedback(Request request) {
		for (int i = 0; i < guide.length; i++) {
			if (guide[i] != null)
				removeFeedback(guide[i]);
			guide[i] = null;
			location[i] = null;
		}
	}

	static class FadeIn extends Figure {
		int opacity = 0;
		static final int FRAMES = 6;
		Image image;
		private Color localBgColor;
		static int count;

		FadeIn(Color bg) {
			setBackgroundColor(bg);
			super.setOpaque(true);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.draw2d.Figure#erase()
		 */
		@Override
		public void erase() {
			super.erase();
			if (localBgColor != null && !localBgColor.isDisposed()) {
				localBgColor.dispose();
			}
		}

		/**
		 * @see org.eclipse.draw2d.IFigure#getLocalBackgroundColor()
		 */
		public Color getLocalBackgroundColor() {
			/* get the correct color */
			Color temp = FigureUtilities.mixColors(super.getLocalBackgroundColor(), getParent().getBackgroundColor(),
					(double) opacity / FRAMES);

			if (localBgColor != null && !localBgColor.isDisposed() && temp.equals(localBgColor)) {
				/*
				 * if it's the same than previous, just dispose the just new
				 * created
				 */
				temp.dispose();
			} else {
				/* else dispose the old color... */
				if (localBgColor != null && !localBgColor.isDisposed()) {
					localBgColor.dispose();
				}
				/* and replace it by the new one */
				localBgColor = temp;
			}
			return localBgColor;
		}

		/**
		 * @see org.eclipse.draw2d.Figure#paintFigure(org.eclipse.draw2d.Graphics)
		 */
		protected void paintFigure(Graphics graphics) {
			if (opacity != FRAMES) {
				if (image != null) {
					image.dispose();
					count--;
					image = null;
				}
				if (opacity != FRAMES - 1) {
					Display display = Display.getCurrent();
					PaletteData pData = new PaletteData(0xFF, 0xFF00, 0xFF0000);
					Color localBgColor = getLocalBackgroundColor();
					int fillColor = pData.getPixel(localBgColor.getRGB());
					localBgColor.dispose();
					ImageData iData = new ImageData(1, 1, 24, pData);
					iData.setPixel(0, 0, fillColor);
					iData.setAlpha(0, 0, 255 * opacity / FRAMES);
					if (image != null && !image.isDisposed()) {
						image.dispose();
					}
					image = new Image(display, iData);
					count++;
				}
				Display.getCurrent().timerExec(100, new Runnable() {
					public void run() {
						opacity = Math.min(FRAMES, opacity + 1);
						repaint();
					}
				});
			}
			Rectangle r = getBounds();
			if (image != null)
				graphics.drawImage(image, 0, 0, 1, 1, r.x, r.y, r.width, r.height);
			else
				super.paintFigure(graphics);
		}

		/**
		 * @see org.eclipse.draw2d.Figure#removeNotify()
		 */
		public void removeNotify() {
			if (image != null) {
				image.dispose();
				count--;
				image = null;
			}
		}
	}

	// Even offset indicates a vertical feedback line; odd, horizontal.
	void highlightGuide(Integer pos, Color color, int offset) {
		if (pos == null) {
			if (guide[offset] != null) {
				removeFeedback(guide[offset]);
				guide[offset] = null;
			}
			location[offset] = pos;
			return;
		}

		// pos is an integer relative to target's client area.
		// translate pos to absolute, and then make it relative to fig.
		int position = pos.intValue();
		PrecisionPoint loc = new PrecisionPoint(position, position);

		IFigure contentPane = ((GraphicalEditPart) getHost().getViewer().getContents()).getContentPane();
		contentPane.translateToParent(loc);
		contentPane.translateToAbsolute(loc);
		
		if (location[offset] == null || !location[offset].equals(pos)) {
			location[offset] = pos;
			if (guide[offset] != null) {
				removeFeedback(guide[offset]);
				guide[offset] = null;
			}

			IFigure fig = new FadeIn(color);
			guide[offset] = fig;
			addFeedback(fig);
			fig.translateToRelative(loc);
	
			position = offset % 2 == 0 ? (int) Math.round(loc.preciseX) : (int) Math.round(loc.preciseY);
			Rectangle figBounds = getFeedbackLayer().getBounds().getCopy();
			if ((offset % 2) == 1) {
				figBounds.height = 1;
				figBounds.y = position;
			} else {
				figBounds.x = position;
				figBounds.width = 1;
			}
			fig.setBounds(figBounds);
		} else {
			// The feedback layer could have grown (if auto-scrolling), so
			// resize the fade-in
			// line.
			IFigure fig = guide[offset];
			Rectangle figBounds = fig.getBounds().getCopy();
			Rectangle feedbackBounds = getFeedbackLayer().getBounds();
			if ((offset % 2) == 1) {
				figBounds.x = feedbackBounds.x;
				figBounds.width = feedbackBounds.width;
			} else {
				figBounds.y = feedbackBounds.y;
				figBounds.height = feedbackBounds.height;
			}
			fig.setBounds(figBounds);
		}

	}

	/**
	 * @see org.eclipse.gef.EditPolicy#showTargetFeedback(org.eclipse.gef.Request)
	 */
	public void showTargetFeedback(Request req) {
	
		
		if (req.getType().equals(REQ_MOVE) || req.getType().equals(REQ_RESIZE) || req.getType().equals(REQ_CLONE)
				|| req.getType().equals(REQ_ADD) || req.getType().equals(REQ_CREATE) || req.getType().equals(REQ_DROP)) {

			
			Integer value;
			value = (Integer) req.getExtendedData().get(SnapToGeometry.KEY_WEST_ANCHOR);
			highlightGuide(value, guideColor, 0);

			value = (Integer) req.getExtendedData().get(SnapToGeometry.KEY_NORTH_ANCHOR);
			highlightGuide(value, guideColor, 1);

			value = (Integer) req.getExtendedData().get(SnapToGeometry.KEY_EAST_ANCHOR);
			highlightGuide(value, guideColor, 2);

			value = (Integer) req.getExtendedData().get(SnapToGeometry.KEY_SOUTH_ANCHOR);
			highlightGuide(value, guideColor, 3);

			value = (Integer) req.getExtendedData().get(SnapToGuides.KEY_VERTICAL_GUIDE);
			highlightGuide(value, guideColor, 4);

			value = (Integer) req.getExtendedData().get(SnapToGuides.KEY_HORIZONTAL_GUIDE);
			highlightGuide(value, guideColor, 5);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#deactivate()
	 */
	@Override
	public void deactivate() {
		super.deactivate();
		for (IFigure fig : guide) {
			if(fig != null)
				fig.erase();
			fig = null;
		}
	}
}
