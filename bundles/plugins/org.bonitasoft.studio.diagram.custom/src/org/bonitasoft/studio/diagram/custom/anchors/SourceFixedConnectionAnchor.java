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

import java.util.List;

import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.model.process.Event;
import org.bonitasoft.studio.model.process.Gateway;
import org.bonitasoft.studio.model.process.MessageEvent;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MessageFlowEditPart;
import org.eclipse.draw2d.AbstractConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

/**
 * @author Romain Bioteau
 *
 */
public class SourceFixedConnectionAnchor extends AbstractConnectionAnchor {

	private IFigure owner ;

	private Rectangle target ;
	private Rectangle source ;
	private IFigure sourceFig;
	private IFigure targetFig;
	private GraphicalEditPart ep ;
	private int position ;

	private ConnectionEditPart connEditPart;


	public SourceFixedConnectionAnchor(GraphicalEditPart ep, IFigure targetFigure){
		super(ep.getFigure());
		this.owner = ep.getFigure() ;
		this.sourceFig = ep.getFigure() ;
		this.targetFig = targetFigure;
		this.ep = ep ;
	}
	
	public SourceFixedConnectionAnchor(
			GraphicalEditPart ep,
			ConnectionEditPart connEditPart) {
		this(ep,((IGraphicalEditPart)connEditPart.getTarget()).getFigure());
		this.connEditPart = connEditPart ;
	}

	public Point getLocation(Point reference) {

		this.source = sourceFig.getBounds().getCopy();
		this.target = targetFig.getBounds().getCopy();

		FiguresHelper.translateToAbsolute(owner, source);
		FiguresHelper.translateToAbsolute(targetFig, target);

		if(targetFig.equals(sourceFig)){
			return AnchorUtil.getSouthEastLocation(getOwner());
		}

		List<Integer> freeAnchors = AnchorUtil.getFreeAnchors(ep,this);
		 if(ep.resolveSemanticElement() instanceof MessageEvent && connEditPart != null && connEditPart instanceof MessageFlowEditPart){
				int position = AnchorUtil.getMessageEventAnchorPosition(source,target);
				switch (position) {
				case AnchorPosition.NORTH:return computeGateAvailableAnchor(AnchorPosition.NORTH,freeAnchors);
				case AnchorPosition.SOUTH:return computeGateAvailableAnchor(AnchorPosition.SOUTH,freeAnchors);
				default:return computeGateAvailableAnchor(AnchorPosition.NORTH,freeAnchors);
				}
		 }else if(ep.resolveSemanticElement() instanceof Event){
			int position = AnchorUtil.getEventAnchorPosition(source,target);
			switch (position) {
			case AnchorPosition.NORTH:return computeAvailableAnchor(AnchorPosition.NORTH,freeAnchors);
			case AnchorPosition.SOUTH:return computeAvailableAnchor(AnchorPosition.SOUTH,freeAnchors);
			case AnchorPosition.EAST:return computeAvailableAnchor(AnchorPosition.EAST,freeAnchors);
			case AnchorPosition.WEST:return computeAvailableAnchor(AnchorPosition.WEST,freeAnchors);
			default:return computeAvailableAnchor(AnchorPosition.NORTH,freeAnchors);
			}
		}else if(ep.resolveSemanticElement() instanceof Gateway){
			int position = AnchorUtil.getGatewayAnchorPosition(source,target);
			switch (position) {
			case AnchorPosition.NORTH:return computeGateAvailableAnchor(AnchorPosition.NORTH,freeAnchors);
			case AnchorPosition.SOUTH:return computeGateAvailableAnchor(AnchorPosition.SOUTH,freeAnchors);
			case AnchorPosition.EAST:return computeGateAvailableAnchor(AnchorPosition.EAST,freeAnchors);
			case AnchorPosition.WEST:return computeGateAvailableAnchor(AnchorPosition.WEST,freeAnchors);
			case AnchorPosition.SOUTH_WEST:return computeGateAvailableAnchor(AnchorPosition.SOUTH_WEST,freeAnchors);
			case AnchorPosition.SOUTH_EAST:return computeGateAvailableAnchor(AnchorPosition.SOUTH_EAST,freeAnchors);
			case AnchorPosition.NORTH_WEST:return computeGateAvailableAnchor(AnchorPosition.NORTH_WEST,freeAnchors);
			case AnchorPosition.NORTH_EAST:return computeGateAvailableAnchor(AnchorPosition.NORTH_EAST,freeAnchors);
			default:return computeGateAvailableAnchor(AnchorPosition.NORTH,freeAnchors);
			}
		}else{

			int position = AnchorUtil.getAnchorPosition(source,target);

			switch (position) {
			case AnchorPosition.NORTH:return computeAvailableAnchor(AnchorPosition.NORTH,freeAnchors);
			case AnchorPosition.SOUTH:return computeAvailableAnchor(AnchorPosition.SOUTH,freeAnchors);
			case AnchorPosition.EAST:return computeAvailableAnchor(AnchorPosition.EAST,freeAnchors);
			case AnchorPosition.WEST:return computeAvailableAnchor(AnchorPosition.WEST,freeAnchors);
			case AnchorPosition.SOUTH_WEST:return computeAvailableAnchor(AnchorPosition.SOUTH_WEST,freeAnchors);
			case AnchorPosition.SOUTH_EAST:return computeAvailableAnchor(AnchorPosition.SOUTH_EAST,freeAnchors);
			case AnchorPosition.NORTH_WEST:return computeAvailableAnchor(AnchorPosition.NORTH_WEST,freeAnchors);
			case AnchorPosition.NORTH_EAST:return computeAvailableAnchor(AnchorPosition.NORTH_EAST,freeAnchors);
			case AnchorPosition.WEST_SOUTH:return computeAvailableAnchor(AnchorPosition.WEST_SOUTH,freeAnchors);
			case AnchorPosition.EAST_SOUTH:return computeAvailableAnchor(AnchorPosition.EAST_SOUTH,freeAnchors);
			case AnchorPosition.WEST_NORTH:return computeAvailableAnchor(AnchorPosition.WEST_NORTH,freeAnchors);
			case AnchorPosition.EAST_NORTH:return computeAvailableAnchor(AnchorPosition.EAST_NORTH,freeAnchors);
			default:return computeAvailableAnchor(AnchorPosition.NORTH_WEST,freeAnchors);
			}
		}

	}


	private Point computeGateAvailableAnchor(int position,
			List<Integer> freeAnchors) {
		
		if(position == AnchorPosition.NORTH_WEST){
			if(freeAnchors.contains(AnchorPosition.NORTH)){
				setPosition(AnchorPosition.NORTH);
				return AnchorUtil.getGateNorthLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.EAST)){
				setPosition(AnchorPosition.EAST);
				return AnchorUtil.getGateEastLocation(owner);
			}else{
				setPosition(AnchorPosition.NORTH_WEST);
				return AnchorUtil.getGateNorthWestLocation(owner);
			}
		}
		
		if(position == AnchorPosition.NORTH){
			if(freeAnchors.contains(AnchorPosition.NORTH)){
				setPosition(AnchorPosition.NORTH);
				return AnchorUtil.getGateNorthLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.NORTH_WEST)){
				setPosition(AnchorPosition.NORTH_WEST);
				return AnchorUtil.getGateNorthWestLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.NORTH_EAST)){
				setPosition(AnchorPosition.NORTH_EAST);
				return AnchorUtil.getGateNorthEastLocation(owner);
			}else{
				setPosition(AnchorPosition.NORTH);
				return AnchorUtil.getGateNorthLocation(owner);
			}
		}
		
		if(position == AnchorPosition.NORTH_EAST){
			if(freeAnchors.contains(AnchorPosition.EAST)){
				setPosition(AnchorPosition.EAST);
				return AnchorUtil.getGateEastLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.NORTH)){
				setPosition(AnchorPosition.NORTH);
				return AnchorUtil.getGateNorthLocation(owner);
			}else{
				setPosition(AnchorPosition.NORTH_EAST);
				return AnchorUtil.getGateNorthEastLocation(owner);
			}
		}

		
		if(position == AnchorPosition.EAST){
			if(freeAnchors.contains(AnchorPosition.EAST)){
				setPosition(AnchorPosition.EAST);
				return AnchorUtil.getGateEastLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.NORTH_EAST)){
				setPosition(AnchorPosition.NORTH_EAST);
				return AnchorUtil.getGateNorthEastLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.SOUTH_EAST)){
				setPosition(AnchorPosition.SOUTH_EAST);
				return AnchorUtil.getGateSouthEastLocation(owner);
			}else{
				setPosition(AnchorPosition.EAST);
				return AnchorUtil.getGateEastLocation(owner);
			}
		}
		
	
		
		if(position == AnchorPosition.SOUTH_EAST){
			if(freeAnchors.contains(AnchorPosition.SOUTH)){
				setPosition(AnchorPosition.SOUTH);
				return AnchorUtil.getGateSouthLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.EAST)){
				setPosition(AnchorPosition.EAST);
				return AnchorUtil.getGateEastLocation(owner);
			}else{
				setPosition(AnchorPosition.SOUTH_EAST);
				return AnchorUtil.getGateSouthEastLocation(owner);
			}
		}
		
		if(position == AnchorPosition.SOUTH){
			if(freeAnchors.contains(AnchorPosition.SOUTH)){
				setPosition(AnchorPosition.SOUTH);
				return AnchorUtil.getGateSouthLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.SOUTH_EAST)){
				setPosition(AnchorPosition.SOUTH_EAST);
				return AnchorUtil.getGateSouthEastLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.SOUTH_WEST)){
				setPosition(AnchorPosition.SOUTH_WEST);
				return AnchorUtil.getGateSouthWestLocation(owner);
			}else{
				setPosition(AnchorPosition.SOUTH);
				return AnchorUtil.getGateSouthLocation(owner);
			}
		}
		
		if(position == AnchorPosition.SOUTH_WEST){
			if(freeAnchors.contains(AnchorPosition.SOUTH)){
				setPosition(AnchorPosition.SOUTH);
				return AnchorUtil.getGateSouthLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.WEST)){
				setPosition(AnchorPosition.WEST);
				return AnchorUtil.getGateWestLocation(owner);
			}else{
				setPosition(AnchorPosition.SOUTH_WEST);
				return AnchorUtil.getGateSouthWestLocation(owner);
			}
		}
		
		
		if(position == AnchorPosition.WEST){
			if(freeAnchors.contains(AnchorPosition.WEST)){
				setPosition(AnchorPosition.WEST);
				return AnchorUtil.getGateWestLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.SOUTH_WEST)){
				setPosition(AnchorPosition.SOUTH_WEST);
				return AnchorUtil.getGateSouthWestLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.NORTH_WEST)){
				setPosition(AnchorPosition.NORTH_WEST);
				return AnchorUtil.getGateNorthWestLocation(owner);
			}else{
				setPosition(AnchorPosition.WEST);
				return AnchorUtil.getGateWestLocation(owner);
			}
		}
		
		
		return null ;
	}

	private Point computeAvailableAnchor(int position, List<Integer> freeAnchors){
		
		if(position == AnchorPosition.NORTH_WEST){
			if(freeAnchors.contains(AnchorPosition.NORTH_WEST)){
				setPosition(AnchorPosition.NORTH_WEST);
				return AnchorUtil.getNorthWestLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.NORTH)){
				setPosition(AnchorPosition.NORTH);
				return AnchorUtil.getNorthLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.EAST_NORTH)){
				setPosition(AnchorPosition.EAST_NORTH);
				return AnchorUtil.getEastNorthLocation(owner);
			}else{
				setPosition(AnchorPosition.NORTH_WEST);
				return AnchorUtil.getNorthWestLocation(owner);
			}
		}
		
		if(position == AnchorPosition.NORTH){
			if(freeAnchors.contains(AnchorPosition.NORTH)){
				setPosition(AnchorPosition.NORTH);
				return AnchorUtil.getNorthLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.NORTH_WEST)){
				setPosition(AnchorPosition.NORTH_WEST);
				return AnchorUtil.getNorthWestLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.NORTH_EAST)){
				setPosition(AnchorPosition.NORTH_EAST);
				return AnchorUtil.getNorthEastLocation(owner);
			}else{
				setPosition(AnchorPosition.NORTH);
				return AnchorUtil.getNorthLocation(owner);
			}
		}
		
		if(position == AnchorPosition.NORTH_EAST){
			if(freeAnchors.contains(AnchorPosition.NORTH_EAST)){
				setPosition(AnchorPosition.NORTH_EAST);
				return AnchorUtil.getNorthEastLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.EAST_NORTH)){
				setPosition(AnchorPosition.EAST_NORTH);
				return AnchorUtil.getEastNorthLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.NORTH)){
				setPosition(AnchorPosition.NORTH);
				return AnchorUtil.getNorthLocation(owner);
			}else{
				setPosition(AnchorPosition.NORTH_EAST);
				return AnchorUtil.getNorthEastLocation(owner);
			}
		}
		
		if(position == AnchorPosition.EAST_NORTH){
			if(freeAnchors.contains(AnchorPosition.EAST_NORTH)){
				setPosition(AnchorPosition.EAST_NORTH);
				return AnchorUtil.getEastNorthLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.EAST)){
				setPosition(AnchorPosition.EAST);
				return AnchorUtil.getEastLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.NORTH_EAST)){
				setPosition(AnchorPosition.NORTH_EAST);
				return AnchorUtil.getNorthEastLocation(owner);
			}else{
				setPosition(AnchorPosition.EAST_NORTH);
				return AnchorUtil.getEastNorthLocation(owner);
			}
		}
		
		if(position == AnchorPosition.EAST){
			if(freeAnchors.contains(AnchorPosition.EAST)){
				setPosition(AnchorPosition.EAST);
				return AnchorUtil.getEastLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.EAST_SOUTH)){
				setPosition(AnchorPosition.EAST_SOUTH);
				return AnchorUtil.getEastSouthLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.EAST_NORTH)){
				setPosition(AnchorPosition.EAST_NORTH);
				return AnchorUtil.getEastNorthLocation(owner);
			}else{
				setPosition(AnchorPosition.EAST);
				return AnchorUtil.getEastLocation(owner);
			}
		}
		
		if(position == AnchorPosition.EAST_SOUTH){
			if(freeAnchors.contains(AnchorPosition.EAST_SOUTH)){
				setPosition(AnchorPosition.EAST_SOUTH);
				return AnchorUtil.getEastSouthLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.SOUTH_EAST)){
				setPosition(AnchorPosition.SOUTH_EAST);
				return AnchorUtil.getSouthEastLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.EAST)){
				setPosition(AnchorPosition.EAST);
				return AnchorUtil.getEastLocation(owner);
			}else{
				setPosition(AnchorPosition.EAST_SOUTH);
				return AnchorUtil.getEastSouthLocation(owner);
			}
		}
		
		if(position == AnchorPosition.SOUTH_EAST){
			if(freeAnchors.contains(AnchorPosition.SOUTH_EAST)){
				setPosition(AnchorPosition.SOUTH_EAST);
				return AnchorUtil.getSouthEastLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.SOUTH)){
				setPosition(AnchorPosition.SOUTH);
				return AnchorUtil.getSouthLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.EAST_SOUTH)){
				setPosition(AnchorPosition.EAST_SOUTH);
				return AnchorUtil.getEastSouthLocation(owner);
			}else{
				setPosition(AnchorPosition.SOUTH_EAST);
				return AnchorUtil.getSouthEastLocation(owner);
			}
		}
		
		if(position == AnchorPosition.SOUTH){
			if(freeAnchors.contains(AnchorPosition.SOUTH)){
				setPosition(AnchorPosition.SOUTH);
				return AnchorUtil.getSouthLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.SOUTH_EAST)){
				setPosition(AnchorPosition.SOUTH_EAST);
				return AnchorUtil.getSouthEastLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.SOUTH_WEST)){
				setPosition(AnchorPosition.SOUTH_WEST);
				return AnchorUtil.getSouthWestLocation(owner);
			}else{
				setPosition(AnchorPosition.SOUTH);
				return AnchorUtil.getSouthLocation(owner);
			}
		}
		
		if(position == AnchorPosition.SOUTH_WEST){
			if(freeAnchors.contains(AnchorPosition.SOUTH_WEST)){
				setPosition(AnchorPosition.SOUTH_WEST);
				return AnchorUtil.getSouthWestLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.SOUTH)){
				setPosition(AnchorPosition.SOUTH);
				return AnchorUtil.getSouthLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.WEST_SOUTH)){
				setPosition(AnchorPosition.WEST_SOUTH);
				return AnchorUtil.getWestSouthLocation(owner);
			}else{
				setPosition(AnchorPosition.SOUTH_WEST);
				return AnchorUtil.getSouthWestLocation(owner);
			}
		}
		
		if(position == AnchorPosition.WEST_SOUTH){
			if(freeAnchors.contains(AnchorPosition.WEST_SOUTH)){
				setPosition(AnchorPosition.WEST_SOUTH);
				return AnchorUtil.getWestSouthLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.SOUTH_WEST)){
				setPosition(AnchorPosition.SOUTH_WEST);
				return AnchorUtil.getSouthWestLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.WEST)){
				setPosition(AnchorPosition.WEST);
				return AnchorUtil.getWestLocation(owner);
			}else{
				setPosition(AnchorPosition.WEST_SOUTH);
				return AnchorUtil.getWestSouthLocation(owner);
			}
		}
		
		if(position == AnchorPosition.WEST){
			if(freeAnchors.contains(AnchorPosition.WEST)){
				setPosition(AnchorPosition.WEST);
				return AnchorUtil.getWestLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.WEST_SOUTH)){
				setPosition(AnchorPosition.WEST_SOUTH);
				return AnchorUtil.getWestSouthLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.WEST_NORTH)){
				setPosition(AnchorPosition.WEST_NORTH);
				return AnchorUtil.getWestNorthLocation(owner);
			}else{
				setPosition(AnchorPosition.WEST);
				return AnchorUtil.getWestLocation(owner);
			}
		}
		
		if(position == AnchorPosition.WEST_NORTH){
			if(freeAnchors.contains(AnchorPosition.WEST_NORTH)){
				setPosition(AnchorPosition.WEST_NORTH);
				return AnchorUtil.getWestNorthLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.WEST)){
				setPosition(AnchorPosition.WEST);
				return AnchorUtil.getWestLocation(owner);
			}else if(freeAnchors.contains(AnchorPosition.NORTH_WEST)){
				setPosition(AnchorPosition.NORTH_WEST);
				return AnchorUtil.getNorthWestLocation(owner);
			}else{
				setPosition(AnchorPosition.WEST_NORTH);
				return AnchorUtil.getWestNorthLocation(owner);
			}
		}
		
		return null ;

	}


	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

}
