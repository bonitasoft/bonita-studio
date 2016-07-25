/**
 * Copyright (C) 2009-2011 BonitaSoft S.A.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bonitasoft.engine.bpm.connector.ConnectorEvent;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

/**
 * @author Romain Bioteau
 *
 */
public class LifeCycleWidget extends FigureCanvas {

    private Image imageLifeCycle;
    private String event ;
    private final Color color;
    public static final int ACTIVITY_LIFE_CYCLE = 0 ;
    public static final int PROCESS_LIFE_CYCLE = 1;
    public static final int TASK_LIFE_CYCLE = 2;
    private final List<SelectionListener> selectionListeners = new ArrayList<SelectionListener>();
    private final Map<String, EventCircle> eventFigures = new HashMap<String, EventCircle>();

    public LifeCycleWidget(Composite parent, String event,Color backgroundColor) {
        super(parent, SWT.NONE);
        this.event = event ;
        createLifecycleImage();

        color = new Color(getShell().getDisplay(), 42, 108, 161) ;
        IFigure  figure = createLifecycleFigure();


        if(backgroundColor != null) {
            setBackground(backgroundColor);
        }

        setContents(figure);

        for(EventCircle eventCircle : eventFigures.values()){
            eventCircle.refresh();
        }
    }

    public void addSelectionListener(SelectionListener listener){
        selectionListeners.add(listener);
    }

    public void removeSelectionListener(SelectionListener listener){
        selectionListeners.remove(listener);
    }


    /**
     * @param figure
     * @param eventName
     * @return the widget associated to specified Event
     */
    private EventCircle addEvent(IFigure figure, String eventName, org.eclipse.draw2d.geometry.Point location, int width) {
        final EventCircle onEnterFile = new EventCircle(this,eventName, width);
        if (eventName.equals(event)) {
            onEnterFile.select();
        }
        onEnterFile.setForegroundColor(color);
        onEnterFile.setBackgroundColor(color);
        onEnterFile.setLocation(location);
        figure.add(onEnterFile);
        eventFigures.put(eventName, onEnterFile);
        return onEnterFile;
    }

    private IFigure createLifecycleFigure() {
        IFigure figure = new ImageFigure(imageLifeCycle);
        addEvent(figure, ConnectorEvent.ON_ENTER.toString(), new org.eclipse.draw2d.geometry.Point(45, 40), 10);
        EventCircle defaultEvent = addEvent(figure, ConnectorEvent.ON_FINISH.toString(), new org.eclipse.draw2d.geometry.Point(226, 40), 10);
        if(event == null){
            defaultEvent.select();
        }
        return figure;
    }



    private void createLifecycleImage() {
        imageLifeCycle = Pics.getImage("automatic_lifecycle.png");	 //$NON-NLS-1$
    }

    @Override
    public void dispose() {
        super.dispose();
        if(color != null && !color.isDisposed()){
            color.dispose();
        }
    }

    public int getWidth(){
        return imageLifeCycle.getImageData().width ;
    }

    public int getHeight(){
        return imageLifeCycle.getImageData().height ;
    }

    public List<SelectionListener> getSelectionListeners() {
        return selectionListeners;
    }

    public void setEvent(String event) {
        this.event = event ;
    }

    public String getEvent() {
        return event  ;
    }

    public void fireEventSelected(String event) {
        eventFigures.get(event).select();
    }

    /**
     * Usd for Test only
     * @return
     */
    public Collection<EventCircle> getEventFigures() {
        return eventFigures.values();
    }

}
