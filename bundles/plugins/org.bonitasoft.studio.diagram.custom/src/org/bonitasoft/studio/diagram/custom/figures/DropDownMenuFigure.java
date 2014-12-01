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

package org.bonitasoft.studio.diagram.custom.figures;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.Pair;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.ButtonModel;
import org.eclipse.draw2d.Clickable;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * @author Romain Bioteau
 *
 */
public class DropDownMenuFigure extends Clickable {

	static final Color THIS_BACK = new Color(null, 246, 246, 237);
	protected List<Pair<IFigure,MouseListener>> elements = new ArrayList<Pair<IFigure,MouseListener>>();
	protected List<IFigure> allElements = new ArrayList<IFigure>();
	protected RoundedRectangle highLightFigure ;
	protected RoundedRectangle subMenuFigure ;
	protected IFigure parent;
	private Point location;
	private Point zero = new Point(0, 0);
	protected final IFigure layer;
	protected boolean isPaint = false ;
	protected List<Listener> listeners ;
	
	
	public DropDownMenuFigure(final ImageFigure image,IFigure parent, IFigure layer){
		this(image, parent, layer, Messages.more);
	}

	public DropDownMenuFigure(final ImageFigure image,IFigure parent, IFigure layer, String tooltip){
		super(image);
		setToolTip(new Label(tooltip));
		this.parent = parent ;
		this.layer = layer;
		location = parent.getBounds().getLocation().getCopy();
		listeners = new ArrayList<Listener>() ;
		ButtonModel bModel = createDefaultModel();
		bModel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				switchVisible();
				fireVisibilityChanged() ;
			}
		});
		setModel(bModel);

	}
	
	protected void fireVisibilityChanged() {
		for(Listener l : listeners){
			l.handleEvent(new Event()) ;
		}
		
	}


	/**
	 * 
	 */
	protected void switchVisible() {
		if(subMenuFigure.isVisible()){
			subMenuFigure.setVisible(false);
			removeElements();
			parent.setLocation(location);
			parent.getBounds().height=10;
		}else{
			subMenuFigure.setVisible(true);
			addElements();
			parent.setLocation(location);
			parent.getBounds().height=20;
		}
	}
	
	public boolean isCollapsed(){
		return !subMenuFigure.isVisible() ;
	}

	private void addElements() {
		if(!isPaint){
			paintElements();
		}
		for(IFigure elem : getAllFigures()){
			elem.setVisible(true);
		}
		for (Pair<IFigure, MouseListener> pair : elements) {
			if(allElements.contains(pair.getFirst())){
				if(pair.getSecond() != null){
					pair.getFirst().addMouseListener(pair.getSecond());
				}
			}
		}
	}

	/**
	 * 
	 */
	protected void removeElements() {
		for (Pair<IFigure, MouseListener> elem : elements) {
			if(allElements.contains(elem.getFirst())){
				elem.getFirst().setLocation(zero );
				layer.remove(elem.getFirst());
				if(elem.getSecond() != null)
					elem.getFirst().removeMouseListener(elem.getSecond());
			}
		}
		allElements.clear();
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if(!visible){
			for(IFigure f : getAllFigures()){
				f.setVisible(visible);
			}
		}
	}


	protected List<IFigure> getAllFigures() {
		return allElements;
	}

	public void addToMenu(IFigure menuEntry, MouseListener mouseListener){
		elements.add(new Pair<IFigure, MouseListener>(menuEntry, mouseListener));
	}

	public void paintElements(){
		if (elements != null) {
			for(Pair<IFigure, MouseListener> pair : elements){
				IFigure elem = pair.getFirst();
				if (elem != null && !(elem instanceof Polyline)) {
					elem.setSize(new Dimension(20,20));
					elem.setLocation(new Point(location.x +elem.getSize().width*elements.indexOf(pair),location.y+20/*+elem.getSize().height*elements.indexOf(elem)*/));
					elem.setVisible(false);
					layer.add(elem);
					addElementsToShow(elem);
				}
			}
		}
	}

	protected void addElementsToShow(IFigure f) {
		allElements.add(f);
	}

	public void createSubMenuFigure(){
		if(subMenuFigure == null){
			subMenuFigure = new RoundedRectangle();
			subMenuFigure.setCornerDimensions(new Dimension(0,0));
			subMenuFigure.setBackgroundColor(THIS_BACK);
			subMenuFigure.setSize(20*elements.size(),20);
			subMenuFigure.setAlpha(0);
			subMenuFigure.setLocation(new Point(parent.getBounds().getCopy().getTopLeft().x,parent.getBounds().getCopy().getTopLeft().y+20));
			subMenuFigure.setVisible(false);
			parent.add(subMenuFigure);
		}
	}

	public void addToggleVisibilityListener(Listener listener) {
		if(!listeners.contains(listener)){
			listeners.add(listener) ;
		}
	}

	public void collapse() {
		subMenuFigure.setVisible(false);
		removeElements();
		parent.setLocation(location);
		parent.getBounds().height=10;
	}

}
