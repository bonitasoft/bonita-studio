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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.AbstractLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

/**
 * LayoutManager that provides a grid layout. The grid layout allows figures to
 * be arranged in a table. It defines a simple positive zero based integer
 * coordinate system.
 * 
 * @author Gunnar Wagenknecht
 * @author Stefan Holzknecht
 * @author Aurelien Pupier : use Rectangle instead of point, be aware of span
 */
public class GridLayoutManager extends AbstractLayout {
	/** locations by figure */
	Map<IFigure, Rectangle> locations = new HashMap<IFigure, Rectangle>();

	/** figures by location */
	Map<Point, IFigure> figureByLocation = new HashMap<Point, IFigure>();

	protected int sizeX = 400;
	protected int sizeY = 100;

	protected AbstractGridLayer gridLayer;

	public GridLayoutManager(AbstractGridLayer gridLayer) {
		this.gridLayer = gridLayer;
	}

	/**
	 * Sets the constraint for the child figure. The grid layout accepts
	 * rectangles as constraints. The rectangle is interpreted as coordinate of
	 * the table and width and height field.
	 */
	public void setConstraint(IFigure figure, Object newConstraint) {
		super.setConstraint(figure, newConstraint);
		if (newConstraint instanceof Rectangle) {
			Rectangle r = (Rectangle) newConstraint;
			/* Add the new locations */
			locations.put(figure, (Rectangle) newConstraint);
			for (int i = 0; i < r.width; i++) {
				for (int j = 0; j < r.height; j++) {
					figureByLocation.put(new Point(r.x + i, r.y + j), figure);
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.draw2d.AbstractLayout#calculatePreferredSize(org.eclipse.
	 * draw2d.IFigure, int, int)
	 */
	protected Dimension calculatePreferredSize(IFigure f, int wHint, int hHint) {
		return new Dimension(sizeX, sizeY);
	}

	/**
	 * Gets the constraint for a figure. The constraint will be a rectangle.
	 * 
	 * @see org.eclipse.draw2d.AbstractLayout#getConstraint(org.eclipse.draw2d.IFigure)
	 */
	public Object getConstraint(IFigure child) {
		return locations.get(child);
	}

	/**
	 * Gets the figure at the point p. P is addressed in grid coordinates.
	 * 
	 * @param p
	 * @return
	 */
	protected IFigure getFigure(Point p) {
		if (figureByLocation.containsKey(p)) {
			IFigure figure = figureByLocation.get(p);
			if (locations.containsKey(figure)) {
				return figure;
			} else {
				figureByLocation.remove(p);
			}
		}
		return null;
	}

	/**
	 * Know if we can move a figure at the constraint r
	 * 
	 * @param r
	 * @param figure
	 * @return
	 */
	protected boolean canAddFigure(Rectangle r, IFigure figure) {
		/* in the grid */
		if (r.x + r.width > gridLayer.getNumColumn()
				|| r.y + r.height > gridLayer.getNumLine() || r.x < 0
				|| r.y < 0) {

			return false;
		}
		/* Check each cell took by the figure */
		for (int i = 0; i < r.width; i++) {
			for (int j = 0; j < r.height; j++) {
				IFigure temp = getFigure(new Point(r.x + i, r.y + j));
				if (temp != null && !temp.equals(figure)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Check that the figure can be moved to the newPoint in grid coordinate
	 * taking care of span.
	 * 
	 * @param newPoint
	 * @param figure
	 * @return
	 */
	public boolean canAddFigure(Point newPoint, IFigure figure) {
		Object constraint = getConstraint(figure);
		if (constraint instanceof Rectangle) {
			Rectangle newConstraint = ((Rectangle) constraint).getCopy();
			newConstraint.x = newPoint.x;
			newConstraint.y = newPoint.y;
			return canAddFigure(newConstraint, figure);
		}
		/*
		 * i.e. this figure come from another gridLayer (typically called on a
		 * reparent)
		 */
		if (constraint == null) {
			Rectangle newConstraint = getConstraintFor(figure.getBounds());
			newConstraint.x = newPoint.x;
			newConstraint.y = newPoint.y;
			return canAddFigure(newConstraint, figure);
		}
		return false;
	}

	/**
	 * Check that we can add a figure at the point in grid coordinates (with
	 * span 1,1).
	 * 
	 * @param point
	 * @return
	 */
	public boolean canAddFigure(Point point) {
		/* in the grid */
		if (point.x < 0 || point.y < 0 || point.x >= gridLayer.getNumColumn()
				|| point.y >= gridLayer.getNumLine()) {
			return false;
		}
		/* not on existing figure */
		IFigure temp = getFigure(point);
		if (temp != null) {
			return false;
		}

		return true;
	}

	/**
	 * Gets the point in grid coordinates for the given point that is in
	 * gridLayer coordinates.
	 * 
	 * @param p
	 * @return
	 */
	public Point getConstraintFor(Point p) {
		int x = p.x >= 0 ? (p.x) / sizeX : 0;
		int y = p.y >= 0 ? (p.y) / sizeY : 0;

		return new Point(x, y);
	}

	/**
	 * Gets the rectangle in grid coordinates for the given rectangle in
	 * gridLayer coordinates.
	 * 
	 * @param r
	 * @return
	 */
	public Rectangle getConstraintFor(Rectangle r) {
		int x = r.x >= 0 ? (r.x) / sizeX : 0;
		int y = r.y >= 0 ? (r.y) / sizeY : 0;
		int width = r.width / sizeX;
		int height = r.height / sizeY;
		return new Rectangle(x, y, width, height);
	}

	/**
	 * Gets a rectangle in canvas coordinates that ecloses the given point that
	 * addresses a grid coordinate.
	 * 
	 * @param p
	 * @return
	 */
	public Rectangle getGridField(Point p) {
		return getGridField(new Rectangle(p.x, p.y, 1, 1));// new
															// Rectangle(sizeX *
															// p.x, sizeY * p.y,
															// sizeX, sizeY);
	}

	/**
	 * Gets a rectangle in canvas coordinates that respect the information
	 * contained in Rectangle: x:column y:line width:horizontal span (number of
	 * column) height: vertical span (number of line)
	 * 
	 * @param r
	 * @return
	 */
	public Rectangle getGridField(Rectangle r) {
		int x = sizeX * r.x;
		int y = sizeY * r.y;
		int width = sizeX * r.width;
		int height = sizeY * r.height;
		return new Rectangle(x, y, width, height);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.LayoutManager#layout(org.eclipse.draw2d.IFigure)
	 */
	public void layout(IFigure parent) {
		List<IFigure> figures = parent.getChildren();
		Iterator<IFigure> children = figures.iterator();
		Point offset = parent.getClientArea().getLocation();
		IFigure f;
		while (children.hasNext()) {
			f = children.next();
			Rectangle location = (Rectangle) getConstraint(f);
			if (null != location) {
				/* width and height are used for the span */
				Rectangle bounds = new Rectangle(location.x * sizeX, location.y
						* sizeY, location.width * sizeX, location.height
						* sizeY);
				bounds = bounds.getTranslated(offset);
				f.setBounds(bounds);
			}
		}
	}

	/**
	 * @return
	 */
	public int getSizeX() {
		return sizeX;
	}

	/**
	 * @return
	 */
	public int getSizeY() {
		return sizeY;
	}

	/**
	 * @param i
	 */
	public void setSizeX(int i) {
		sizeX = i;
	}

	/**
	 * @param i
	 */
	public void setSizeY(int i) {
		sizeY = i;
	}

	@Override
	public void remove(IFigure child) {
		super.remove(child);
		invalidate(child);
	}

	/**
	 * Removes any cached information about the given figure.
	 * 
	 * @param child
	 *            the child that is invalidated
	 */
	@Override
	protected void invalidate(IFigure child) {
		// super.invalidate(child);
		/* Remove info from the map */
		Rectangle oldConstraintToRemove = locations.get(child);
		if (oldConstraintToRemove != null) {
			for (int i = 0; i < oldConstraintToRemove.width; i++) {
				for (int j = 0; j < oldConstraintToRemove.height; j++) {
					figureByLocation.remove(new Point(oldConstraintToRemove.x
							+ i, oldConstraintToRemove.y + j));
				}
			}
		}
		locations.remove(child);
	}

	public InsertionPoint getClosestInsertionPoint(Point newPoint) {
		return getClosestInsertionPoint(newPoint, null);
	}

	public InsertionPoint getClosestInsertionPoint(Point newPoint,
			Point relativePoint) {
		int newX = newPoint.x;
		int newY = newPoint.y;

		//Define new x y
		if (newPoint.x + 1 > gridLayer.getNumColumn() && newPoint.y + 1 > gridLayer.getNumLine()) {
			newX = gridLayer.getNumColumn();
			newY = gridLayer.getNumLine() - 1;
		} else {
			if (newPoint.x + 1 > gridLayer.getNumColumn()) {
				newX = gridLayer.getNumColumn();
			}
			if (newPoint.y + 1 > gridLayer.getNumLine()) {
				newY = gridLayer.getNumLine();
			}
		}
		
		InsertionPoint insertionPoint = new InsertionPoint(newX, newY);
		
		//Define out of bound position
		if (relativePoint != null) {
			if ((newX == 0 && newY == 0)) {
				if (relativePoint.x < relativePoint.y) {
					insertionPoint.setOutBoundsPosition(InsertionPoint.Position.LEFT);
				} else {
					insertionPoint.setOutBoundsPosition(InsertionPoint.Position.UP);
				}
			} else {
				int width = sizeX * gridLayer.getNumColumn();
				int height = sizeY * gridLayer.getNumLine();
				if (relativePoint.x > width) {
					insertionPoint.setOutBoundsPosition(InsertionPoint.Position.RIGHT);
				} else if (relativePoint.y > height) {
					insertionPoint.setOutBoundsPosition(InsertionPoint.Position.DOWN);
				} else if (relativePoint.x < 0) {
					insertionPoint.setOutBoundsPosition(InsertionPoint.Position.LEFT);
				} else if(relativePoint.y < 0){
					insertionPoint.setOutBoundsPosition(InsertionPoint.Position.UP);
				} else {
					insertionPoint.setOutBoundsPosition(InsertionPoint.Position.UNDEFINE);
				}
			}
		} else {
			insertionPoint.setOutBoundsPosition(InsertionPoint.Position.UNDEFINE);
		}
		return insertionPoint;
	}

}