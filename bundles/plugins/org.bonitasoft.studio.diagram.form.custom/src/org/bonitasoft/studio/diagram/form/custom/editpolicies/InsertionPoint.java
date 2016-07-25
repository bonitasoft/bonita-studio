/*******************************************************************************
 * Copyright (C) 2013 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 *      BonitaSoft, 32 rue Gustave Eiffel a 38000 Grenoble
 *      or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.diagram.form.custom.editpolicies;

import org.eclipse.draw2d.geometry.Point;

/**
 * @author Maxence Raoux
 * 
 */
public class InsertionPoint {

	private int x;
	private int y;

	public static enum Position {
		UP, DOWN, LEFT, RIGHT, UNDEFINE
	};

	private Position outBoundsPosition;

	public InsertionPoint(int x, int y) {
		this.x = x;
		this.y = y;
		outBoundsPosition = Position.UNDEFINE;
	}

	public Point getPoint() {
		return new Point(x, y);
	}

	public Position getOutBoundsPosition() {
		return outBoundsPosition;
	}

	public void setOutBoundsPosition(Position outBoundsPosition) {
		this.outBoundsPosition = outBoundsPosition;
	}

	@Override
	public String toString() {
		return "InsertionPoint [x=" + x + ", y=" + y + ", outBoundsPosition="
				+ outBoundsPosition + "]";
	}
}
