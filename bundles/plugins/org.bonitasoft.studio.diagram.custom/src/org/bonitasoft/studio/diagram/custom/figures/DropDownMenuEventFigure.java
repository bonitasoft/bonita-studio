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

package org.bonitasoft.studio.diagram.custom.figures;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;

/**
 * @author Romain Bioteau
 *
 */
public class DropDownMenuEventFigure extends DropDownMenuFigure {

	private List<List<IFigure>> figureList ;
	
	public DropDownMenuEventFigure(ImageFigure image, IFigure parent, IFigure layer) {
		super(image, parent, layer, Messages.more);
		allElements = new ArrayList<IFigure>();
		figureList = new ArrayList<List<IFigure>>();
	}

	public void addEventsFigure(List<IFigure> eventFigureList) {
		figureList.add(eventFigureList) ;
	}

	@Override
	public void createSubMenuFigure() {
		subMenuFigure = new RoundedRectangle();
		subMenuFigure.setAlpha(50);
		subMenuFigure.setBackgroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GRAY));
		subMenuFigure.setSize(new Dimension(4*20,figureList.size()*20));
		Rectangle parentBounds = parent.getBounds();
		Point newPointLocation = new Point(parentBounds.getTopLeft().x,parentBounds.getTopLeft().y+40);
		subMenuFigure.setLocation(newPointLocation);
		subMenuFigure.setVisible(false);
		parent.add(subMenuFigure);
	}
	
	@Override
	public void paintElements() {
		
		if (figureList != null) {
			for(List<IFigure> eventLists : figureList){
				Point parentTopLeft = parent.getBounds().getTopLeft();
				for(IFigure f : eventLists){
					f.setSize(new Dimension(20,20));
					f.setLocation(new Point(parentTopLeft.x +f.getSize().width*eventLists.indexOf(f),parentTopLeft.y+20*(figureList.indexOf(eventLists)+2)));
					f.setVisible(false);
					subMenuFigure.add(f);
					if(!(f instanceof RectangleFigure)){
						addElementsToShow(f);
					}
				
				}
				if(figureList.indexOf(eventLists) != figureList.size()-1){
					Polyline lineSeparator = new Polyline();
					lineSeparator.addPoint(new Point(parentTopLeft.x ,parentTopLeft.y+20+20*(figureList.indexOf(eventLists)+2)));
					lineSeparator.addPoint(new Point(parentTopLeft.x + 80,parentTopLeft.y+20+20*(figureList.indexOf(eventLists)+2)));
					lineSeparator.setAlpha(80);
					lineSeparator.setVisible(false);
					subMenuFigure.add(lineSeparator);
					addElementsToShow(lineSeparator);
				}
			}
		}
		isPaint = true ;
	}
	
	protected void removeElements() {
		super.removeElements();
//		if (figureList != null) {
//			for(List<IFigure> eventLists : figureList){
//				for(IFigure f : eventLists){
//					if(f.getParent() != null){
//						f.getParent().remove(f) ;
//					}
//				}
//			}
//		}
//		parent.remove(background) ;
//		isPaint = false ;
	}
	

}
