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
package org.bonitasoft.studio.common.widgets;

import org.eclipse.draw2d.Ellipse;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Event;

/**
 * @author Mickael Istria
 *
 */
public class EventCircle extends Ellipse {

	private static final Color SELECTED =  new Color(null, 73, 137, 168);
	private static final Color HOVER =  new Color(null, 73, 137, 168);
	private static final Color UNSELECTED = new Color(null, 235, 235, 235);
	
	boolean selected = false;
	private String event;
	private LifeCycleWidget parent ;
	
	public EventCircle(LifeCycleWidget parent,final String event, int width) {
		super();
		this.parent = parent ;
		this.event = event;
		setSize(width, width);

		setForegroundColor(UNSELECTED) ;
		setBackgroundColor(UNSELECTED) ;
		
		addMouseListener(new MouseListener() {
			public void mouseDoubleClicked(MouseEvent me) {
			}
	
			public void mousePressed(MouseEvent me) {
				select();
			}
	
			public void mouseReleased(MouseEvent me) {
			}
		});
		addMouseMotionListener(new MouseMotionListener() {
			public void mouseDragged(MouseEvent me) {
			}
	
			public void mouseEntered(MouseEvent me) {
				if (! selected) {

					setForegroundColor(HOVER) ;
					setBackgroundColor(HOVER) ;
				}
			}
	
			public void mouseExited(MouseEvent me) {
				refresh();
			}
	
			public void mouseHover(MouseEvent me) {
			}
	
			public void mouseMoved(MouseEvent me) {
			}
			
		});
	}
	
	public void refresh() {
		if(selected) {
			setForegroundColor(SELECTED) ;
			setBackgroundColor(SELECTED) ;
		}else{
			setForegroundColor(UNSELECTED) ;
			setBackgroundColor(UNSELECTED) ;
		}
	}
	
	public void select() {
		selected = true;
		parent.setEvent(event);
		for(SelectionListener listener : parent.getSelectionListeners()){
			Event e = new Event() ;
			e.widget = parent ;
			SelectionEvent se = new SelectionEvent(e);
			se.data = event;
			listener.widgetSelected(se);
		}

		setForegroundColor(SELECTED) ;
		setBackgroundColor(SELECTED) ;
		if (getParent() != null) {
			// Unselect others
			for (Object item : getParent().getChildren()) {
				IFigure figure = (IFigure)item;
				if (figure instanceof EventCircle && figure != this) {
					((EventCircle)figure).unselect();
				}
			}
		}
	}
	
	public void unselect() {
		selected = false;
		setForegroundColor(UNSELECTED) ;
		setBackgroundColor(UNSELECTED) ;
		
	}

	public boolean isSelected() {
		return selected;
	}

	public String getEvent() {
		return event;
	}
}
