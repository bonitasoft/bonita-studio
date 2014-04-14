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
package org.bonitasoft.studio.diagram.form.custom.editpolicies;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.Layer;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;

/*use a layer and not a freeformlayer in order to avoid extension to the left and top*/
/**
 * @author Aurelien Pupier
 *
 */
public abstract class AbstractGridLayer extends Layer {


	/** setting for drawing of grid or not */
	protected boolean drawGrid = true;
	
	/** setting for grid color */
	protected Color gridColor = ColorConstants.lightGray;

	private static final int LINE_WIDTH = 1;
	
	/** setting for the line style */
	protected int lineStyle = SWT.LINE_DASH;

	protected int margin = 50;

	
	/**
	 * Constructs a new GridFigure with grid drawing enabled and default grid
	 * color.
	 * 
	 * @param formEditPart
	 */
	public AbstractGridLayer() {
		super();
	}
	
	
	/**
	 * Paints the figure and the grid.
	 * 
	 */
	protected void paintFigure(Graphics graphics) {
		setBoundsOfTheGrid();
		
		super.paintFigure(graphics);
		
		if (isDrawGrid()) {
			drawGrid(graphics);
		}

	}
	
	protected abstract void setBoundsOfTheGrid();


	protected void drawGrid(Graphics graphics){

		// set grid color
		Color color = getGridColor();
		graphics.setForegroundColor(color);
		graphics.setLineWidth(LINE_WIDTH) ;
		graphics.setLineCap(SWT.CAP_ROUND) ;
		graphics.setLineStyle(getLineStyle());
		graphics.setLineDash(new int[]{2, 10}) ;

		int numColumn = getNumColumn();
		int numLine = getNumLine();
		
		
		// draw vertical grid lines
		for (int i = getOrigin().x; i <= numColumn * getGridLayout().getSizeX() + getOrigin().x + 1; i += getGridLayout()
				.getSizeX()) {
			graphics.drawLine(i, getOrigin().y, i, numLine * getGridLayout().getSizeY() + getOrigin().y);

		}

		// draw horizontal grid lines
		for (int i = getOrigin().y; i <= numLine* getGridLayout().getSizeY() + getOrigin().y + 1; i += getGridLayout().getSizeY()) {
			graphics.drawLine(getOrigin().x, i, numColumn * getGridLayout().getSizeX() + getOrigin().x, i);
		}
		
		
	}


	/**
	 * Set the numColumn in the model and repaint
	 * 
	 * @param numColumn
	 *            the numColumn to set
	 */
	public abstract void setNumColumn(int numColumn);
	
	/**
	 * @return the numColumn
	 */
	public abstract int getNumColumn();
	
	public abstract int getNumLine();
	
	/**
	 * Sets the drawGrid.
	 * 
	 * @param drawGrid
	 *            The drawGrid to set
	 */
	public void setDrawGrid(boolean drawGrid) {
		this.drawGrid = drawGrid;
		// repaint();
	}

	/**
	 * Sets the gridColor.
	 * 
	 * @param gridColor
	 *            The gridColor to set
	 */
	public void setGridColor(Color gridColor) {
		this.gridColor = gridColor;
		repaint();
	}

	/**
	 * Sets the grid width.
	 * 
	 * @param width
	 *            the grid width to set
	 */
	public void setGridWith(int width) {
		getGridLayout().setSizeX(width);
		revalidate();
		repaint();
	}

	/**
	 * Sets the grid height.
	 * 
	 * @param width
	 *            the grid height to set
	 */
	public void setGridHeight(int height) {
		getGridLayout().setSizeY(height);
		revalidate();
		repaint();
	}

	/**
	 * Returns the gridColor.
	 * 
	 * @return Color
	 */
	public Color getGridColor() {
		if (gridColor == null || gridColor.isDisposed()) {
			gridColor = ColorConstants.lightGray;
		}
		return gridColor;
	}

	/**
	 * Returns the layout manager as <code>GridLayout</code>.
	 * 
	 * @return GridLayout
	 */
	public GridLayoutManager getGridLayout() {
		return (GridLayoutManager) getLayoutManager();
	}

	/**
	 * Indicates if the grid should be drawed or not.
	 * 
	 * @return boolean
	 */
	public boolean isDrawGrid() {
		return drawGrid;
	}
	
	/**
	 * @return the lineStyle
	 */
	public int getLineStyle() {
		return lineStyle;
	}

	/**
	 * @param lineStyle
	 *            the lineStyle to set
	 */
	public void setLineStyle(int lineStyle) {
		this.lineStyle = lineStyle;
	}

	/**
	 * @return
	 */
	public int getMargin() {
		return margin;
	}
	
	
	/** Indicate the origin of the grid
	 * from where it will begin to be drawn
	 * So it will be the top left corner of the grid*/
	public abstract Point getOrigin();
}
