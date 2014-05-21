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

package org.bonitasoft.studio.diagram.custom.anchors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;

/**
 * @author Romain Bioteau
 *
 */
public class AnchorUtil {

	private static final int X_OFFSET = 20;
	private static final int Y_OFFSET = 10;
	private static final int X_GATE_OFFSET = 10 ;
	private static final int Y_GATE_OFFSET = 12 ;
	private static int Y_DELTA = 50;
	private static int X_DELTA = 60;
	private static int X_SUPER_DELTA = 150;

	public static Point getEastLocation(IFigure owner){
		Point right = owner.getBounds().getRight();
		Point p = new PrecisionPoint(right.x, right.y);
		owner.translateToAbsolute(p);
		return p ;
	}
	
	public static Point getGateEastLocation(IFigure owner){
		Point right = owner.getBounds().getRight();
		Point p = new PrecisionPoint(right.x, right.y+1);
		owner.translateToAbsolute(p);
		return p ;
	}

	public static Point getNorthEastLocation(IFigure owner){
		Point topRight = owner.getBounds().getTopRight();
		Point p = new PrecisionPoint(topRight.x-X_OFFSET, topRight.y);
		owner.translateToAbsolute(p);
		return p ;
	}

	public static Point getNorthWestLocation(IFigure owner){
		Point topLeft = owner.getBounds().getTopLeft();
		Point p = new PrecisionPoint(topLeft.x+X_OFFSET, topLeft.y);
		owner.translateToAbsolute(p);
		return p ;
	}

	public static Point getWestNorthLocation(IFigure owner){
		Point topLeft = owner.getBounds().getTopLeft();
		Point p = new PrecisionPoint(topLeft.x, topLeft.y+Y_OFFSET);
		owner.translateToAbsolute(p);
		return p ;
	}

	public static Point getEastNorthLocation(IFigure owner){
		Point topRight = owner.getBounds().getTopRight();
		Point p = new PrecisionPoint(topRight.x, topRight.y+Y_OFFSET);
		owner.translateToAbsolute(p);
		return p ;
	}

	public static Point getEastSouthLocation(IFigure owner){
		Point bottomRight = owner.getBounds().getBottomRight();
		Point p = new PrecisionPoint(bottomRight.x, bottomRight.y-Y_OFFSET);
		owner.translateToAbsolute(p);
		return p ;
	}

	public static Point getWestSouthLocation(IFigure owner){
		Point bottomLeft = owner.getBounds().getBottomLeft();
		Point p = new PrecisionPoint(bottomLeft.x , bottomLeft.y - Y_OFFSET);
		owner.translateToAbsolute(p);
		return p ;
	}

	public static Point getSouthWestLocation(IFigure owner) {
		Point bottomLeft = owner.getBounds().getBottomLeft();
		Point p = new PrecisionPoint(bottomLeft.x+X_OFFSET, bottomLeft.y);
		owner.translateToAbsolute(p);
		return p ;
	}

	public static Point getWestLocation(IFigure owner){
		Point left = owner.getBounds().getLeft();
		Point p = new PrecisionPoint(left.x, left.y);
		owner.translateToAbsolute(p);
		return p ;
	}

	public static Point getGateWestLocation(IFigure owner){
		Point left = owner.getBounds().getLeft();
		Point p = new PrecisionPoint(left.x, left.y+1);
		owner.translateToAbsolute(p);
		return p ;
	}
	
	public static Point getSouthEastLocation(IFigure owner) {
		Point bottom = owner.getBounds().getBottomRight();
		Point p = new PrecisionPoint(bottom.x-X_OFFSET, bottom.y);
		owner.translateToAbsolute(p);
		return p ;
	}

	public static Point getNorthLocation(IFigure owner){
		Point top = owner.getBounds().getTop();
		Point p = new PrecisionPoint(top.x, top.y);
		owner.translateToAbsolute(p);
		return p ;
	}
	
	public static Point getGateNorthLocation(IFigure owner){
		Point top = owner.getBounds().getTop();
		Point p = new PrecisionPoint(top.x+1, top.y);
		owner.translateToAbsolute(p);
		return p ;
	}

	public static Point getSouthLocation(IFigure owner){
		Point bottom = owner.getBounds().getBottom();
		Point p = new PrecisionPoint(bottom.x, bottom.y);
		owner.translateToAbsolute(p);
		return p ;
	}

	public static Point getGateSouthLocation(IFigure owner){
		Point bottom = owner.getBounds().getBottom();
		Point p = new PrecisionPoint(bottom.x+1, bottom.y);
		owner.translateToAbsolute(p);
		return p ;
	}

	public static Point getGateNorthEastLocation(IFigure owner){
		Point topRight = owner.getBounds().getTopRight();
		Point p = new PrecisionPoint(topRight.x-X_GATE_OFFSET, topRight.y+Y_GATE_OFFSET);
		owner.translateToAbsolute(p);
		return p ;
	}

	public static Point getGateNorthWestLocation(IFigure owner){
		Point topLeft = owner.getBounds().getTopLeft();
		Point p = new PrecisionPoint(topLeft.x+X_GATE_OFFSET, topLeft.y+Y_GATE_OFFSET);
		owner.translateToAbsolute(p);
		return p ;
	}

	public static Point getGateSouthWestLocation(IFigure owner) {
		Point bottomLeft = owner.getBounds().getBottomLeft();
		Point p = new PrecisionPoint(bottomLeft.x +X_GATE_OFFSET, bottomLeft.y-Y_GATE_OFFSET);
		owner.translateToAbsolute(p);
		return p ;
	}

	public static Point getGateSouthEastLocation(IFigure owner) {
		Point bottom = owner.getBounds().getBottomRight();
		Point p = new PrecisionPoint(bottom.x-X_GATE_OFFSET, bottom.y-Y_GATE_OFFSET);
		owner.translateToAbsolute(p);
		return p ;
	}



	public static int getAnchorPosition(Rectangle source, Rectangle target) {

		if(isTop(source, target) && isEast(source, target)){
			return AnchorPosition.SOUTH_WEST ;
		}else if(isTop(source, target) && isWest(source, target)){
			return AnchorPosition.SOUTH_EAST ;
		}else if(isTop(source, target) && isXCenter(source,target)){
			return AnchorPosition.SOUTH ;
		}else if(isBottom(source, target) && isEast(source, target)){
			return AnchorPosition.NORTH_WEST ;
		}else if(isBottom(source, target) && isWest(source, target)){
			return AnchorPosition.NORTH_EAST ;
		}else if(isBottom(source, target) && isXCenter(source, target)){
			return AnchorPosition.NORTH ;
		}else if(isWest(source, target) && isYCenter(source, target) ) {
			return AnchorPosition.EAST ;
		}else if(isBottom(source, target) && isWideWest(source,target)){
			return AnchorPosition.EAST_NORTH;
		}else if(isTop(source, target) && isWideWest(source,target)){
			return AnchorPosition.EAST_SOUTH;
		}else if(isEast(source, target) && isYCenter(source, target)){
			return AnchorPosition.WEST;
		}else if(isTop(source, target) && isWideEast(source,target)){
			return AnchorPosition.WEST_SOUTH;
		}else if(isBottom(source, target) && isWideEast(source,target)){
			return AnchorPosition.WEST_NORTH;
		}else if(isWideEast(source, target) && isYCenter(source, target)){
			return AnchorPosition.WEST;
		}else if(isWideWest(source, target) && isYCenter(source, target)){
			return AnchorPosition.EAST;
		}

		return -1 ;

	}

	public static boolean isWideEast(Rectangle source, Rectangle target) {
		return source.getCenter().x-X_DELTA > target.getCenter().x && source.getCenter().x-X_DELTA > target.getCenter().x+X_SUPER_DELTA ;
	}

	public static boolean isWideWest(Rectangle source, Rectangle target){
		return source.getCenter().x+X_DELTA <= target.getCenter().x &&  source.getCenter().x+X_DELTA+X_SUPER_DELTA <= target.getCenter().x;
	}


	public static boolean isXCenter(Rectangle source, Rectangle target) {
		return !isEast(source, target) && !isWest(source, target) && !isWideEast(source, target) && !isWideWest(source, target);
	}

	public static boolean isYCenter(Rectangle source, Rectangle target) {
		return !isTop(source, target) && !isBottom(source, target);
	}

	public static boolean isEast(Rectangle source, Rectangle target){
		return source.getCenter().x-X_DELTA > target.getCenter().x && source.getCenter().x-X_DELTA <= target.getCenter().x+X_SUPER_DELTA;
	}

	public static boolean isWest(Rectangle source, Rectangle target){
		return source.getCenter().x+X_DELTA <= target.getCenter().x &&  source.getCenter().x+X_DELTA+X_SUPER_DELTA > target.getCenter().x;
	}

	public static boolean isTop(Rectangle source, Rectangle target){
		return source.getCenter().y+Y_DELTA <= target.getCenter().y ;
	}

	public static boolean isBottom(Rectangle source, Rectangle target){
		return source.getCenter().y-Y_DELTA > target.getCenter().y ;
	}

	public static int getEventAnchorPosition(Rectangle source, Rectangle target) {
		if(isTop(source, target) && isEast(source, target)){
			return AnchorPosition.SOUTH ;
		}else if(isTop(source, target) && isWest(source, target)){
			return AnchorPosition.SOUTH ;
		}else if(isTop(source, target) && isXCenter(source,target)){
			return AnchorPosition.SOUTH ;
		}else if(isBottom(source, target) && isEast(source, target)){
			return AnchorPosition.NORTH ;
		}else if(isBottom(source, target) && isWest(source, target)){
			return AnchorPosition.NORTH ;
		}else if(isBottom(source, target) && isXCenter(source, target)){
			return AnchorPosition.NORTH ;
		}else if(isWest(source, target) && isYCenter(source, target) ) {
			return AnchorPosition.EAST ;
		}else if(isBottom(source, target) && isWideWest(source,target)){
			return AnchorPosition.EAST;
		}else if(isTop(source, target) && isWideWest(source,target)){
			return AnchorPosition.EAST;
		}else if(isEast(source, target) && isYCenter(source, target)){
			return AnchorPosition.WEST;
		}else if(isTop(source, target) && isWideEast(source,target)){
			return AnchorPosition.WEST;
		}else if(isBottom(source, target) && isWideEast(source,target)){
			return AnchorPosition.WEST;
		}else if(isWideEast(source, target) && isYCenter(source, target)){
			return AnchorPosition.WEST;
		}else if(isWideWest(source, target) && isYCenter(source, target)){
			return AnchorPosition.EAST;
		}

		return -1 ;
	}

	public static int getGatewayAnchorPosition(Rectangle source, Rectangle target) {
		if(isTop(source, target) && isEast(source, target)){
			return AnchorPosition.SOUTH_WEST ;
		}else if(isTop(source, target) && isWest(source, target)){
			return AnchorPosition.SOUTH_EAST ;
		}else if(isTop(source, target) && isXCenter(source,target)){
			return AnchorPosition.SOUTH ;
		}else if(isBottom(source, target) && isEast(source, target)){
			return AnchorPosition.NORTH_WEST ;
		}else if(isBottom(source, target) && isWest(source, target)){
			return AnchorPosition.NORTH_EAST ;
		}else if(isBottom(source, target) && isXCenter(source, target)){
			return AnchorPosition.NORTH ;
		}else if(isWest(source, target) && isYCenter(source, target) ) {
			return AnchorPosition.EAST ;
		}else if(isBottom(source, target) && isWideWest(source,target)){
			return AnchorPosition.NORTH_EAST;
		}else if(isTop(source, target) && isWideWest(source,target)){
			return AnchorPosition.SOUTH_EAST;
		}else if(isEast(source, target) && isYCenter(source, target)){
			return AnchorPosition.WEST;
		}else if(isTop(source, target) && isWideEast(source,target)){
			return AnchorPosition.SOUTH_WEST;
		}else if(isBottom(source, target) && isWideEast(source,target)){
			return AnchorPosition.NORTH_WEST;
		}else if(isWideEast(source, target) && isYCenter(source, target)){
			return AnchorPosition.WEST;
		}else if(isWideWest(source, target) && isYCenter(source, target)){
			return AnchorPosition.EAST;
		}

		return -1 ;
	}
	
	public static int getMessageEventAnchorPosition(Rectangle source, Rectangle target) {
		if(isTop(source, target)){
			return AnchorPosition.SOUTH ;
		}else if(isBottom(source, target)){
			return AnchorPosition.NORTH ;
		}

		return -1 ;
	}

	public static List<Integer> getFreeAnchors(GraphicalEditPart gep, ConnectionAnchor sourceAnchor){
		List<Integer> freeAnchors = new ArrayList<Integer>();

		for(int i =0 ; i<12 ; i++){
			freeAnchors.add(i);
		}


		for(Object cep : gep.getSourceConnections()){
			if( (((ConnectionNodeEditPart)cep).getConnectionFigure().getSourceAnchor() instanceof SourceFixedConnectionAnchor)){
				SourceFixedConnectionAnchor anchor  = (SourceFixedConnectionAnchor) (((ConnectionNodeEditPart)cep).getConnectionFigure().getSourceAnchor());
				if(!anchor.equals(sourceAnchor)){
					if(freeAnchors.contains(anchor.getPosition())){
						int i = freeAnchors.indexOf(anchor.getPosition());
						if(freeAnchors.size()>= i){
							freeAnchors.remove(i);
						}
					}
				}
			}
		}

		for(Object cep : gep.getTargetConnections()){
			if( (((ConnectionNodeEditPart)cep).getConnectionFigure().getTargetAnchor() instanceof TargetFixedConnectionAnchor)){
				TargetFixedConnectionAnchor anchor = (TargetFixedConnectionAnchor) (((ConnectionNodeEditPart)cep).getConnectionFigure().getTargetAnchor());
				if(!anchor.equals(sourceAnchor)){
					if(freeAnchors.contains(anchor.getPosition())){
						int i = freeAnchors.indexOf(anchor.getPosition());
						if(freeAnchors.size()>= i){
							freeAnchors.remove(i);
						}
					}
				}
			}
		}


		return freeAnchors ;
	}


}
