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
import java.util.Map;

import org.bonitasoft.studio.common.palette.ProcessPaletteLabelProvider;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessElementTypes;
import org.bonitasoft.studio.pics.Pics;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.Polyline;
import org.eclipse.draw2d.RectangleFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.common.ui.services.icon.IconService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;

/**
 * @author Romain Bioteau
 *
 */
public class MenuEventFigure extends Figure {

    private static final IFigure EMPTY_FIGURE = new RectangleFigure() ;
    static {
        EMPTY_FIGURE.setSize(20, 20);
        EMPTY_FIGURE.setVisible(false);
    }
    private final List<List<IFigure>> figureList ;
    protected List<IFigure> allElements = new ArrayList<IFigure>();
    private final IFigure parent;
    private final List<IEventSelectionListener> registeredListeners;
    private final Map<IElementType, Command> createCmds;
    private final ProcessPaletteLabelProvider processPaletteLabelProvider;

    public MenuEventFigure(final FreeformLayer parent ,final Map<IElementType, Command> createCmds) {
        super();
        allElements = new ArrayList<IFigure>();
        figureList = new ArrayList<List<IFigure>>();
        registeredListeners = new ArrayList<IEventSelectionListener>();
        this.parent = parent ;
        this.createCmds = createCmds ;
        processPaletteLabelProvider = new ProcessPaletteLabelProvider();
        addEventsFigure(addNoneEventFigures());
        addEventsFigure(addMessageEventFigures());
        addEventsFigure(addTimerEventFigures());
        addEventsFigure(addSignalEventFigures());
        addEventsFigure(addLinkEventFigures());
        addEventsFigure(addErrorEventFigures());
        addEventsFigure(addTerminatedEventFigures());

    }

    public void addEventsFigure(final List<IFigure> eventFigureList) {
        figureList.add(eventFigureList) ;
    }



    public void show(){
        parent.setVisible(true);
        for(final IFigure elem : allElements){
            elem.setVisible(true);
        }
    }

    public void hide(){
        parent.setVisible(false);
        for(final IFigure elem : allElements){
            elem.setVisible(false);
        }
    }

    public void paintElements() {
        final RoundedRectangle background = new RoundedRectangle();
        background.setAlpha(50);
        background.setBackgroundColor(Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GRAY));
        background.setSize(new Dimension(4*20,figureList.size()*20));
        background.setLocation(new Point(parent.getBounds().getTopLeft().x,parent.getBounds().getTopLeft().y+40));
        background.setVisible(false);
        parent.add(background);
        addElementsToShow(background);
        if (figureList != null) {
            for(final List<IFigure> eventLists : figureList){
                for(final IFigure f : eventLists){
                    f.setSize(new Dimension(20,20));
                    f.setLocation(new Point(parent.getBounds().getTopLeft().x +f.getSize().width*eventLists.indexOf(f),parent.getBounds().getTopLeft().y+20*(figureList.indexOf(eventLists)+2)));
                    f.setVisible(false);
                    background.add(f);
                    if(!(f instanceof RectangleFigure)){
                        addElementsToShow(f);
                    }

                }
                if(figureList.indexOf(eventLists) != figureList.size()-1){
                    final Polyline lineSeparator = new Polyline();
                    lineSeparator.addPoint(new Point(parent.getBounds().getTopLeft().x ,parent.getBounds().getTopLeft().y+20+20*(figureList.indexOf(eventLists)+2)));
                    lineSeparator.addPoint(new Point(parent.getBounds().getTopLeft().x + 80,parent.getBounds().getTopLeft().y+20+20*(figureList.indexOf(eventLists)+2)));
                    lineSeparator.setAlpha(80);
                    lineSeparator.setVisible(false);
                    background.add(lineSeparator);
                    addElementsToShow(lineSeparator);
                }
            }
        }
    }
    protected void addElementsToShow(final IFigure f) {
        allElements.add(f);
    }

    private List<IFigure> addNoneEventFigures() {
        final List<IFigure> result = new ArrayList<IFigure>();
        if(createCmds != null){
            if(createCmds.get(ProcessElementTypes.StartEvent_2002) == null && createCmds.get(ProcessElementTypes.StartEvent_3002) == null){
                result.add(EMPTY_FIGURE);
            }else{
                result.add(createItem( ProcessElementTypes.StartEvent_2002));
            }
        }else{
            result.add(createItem( ProcessElementTypes.StartEvent_2002));
        }

        result.add(EMPTY_FIGURE);
        result.add(EMPTY_FIGURE);
        result.add(createItem( ProcessElementTypes.EndEvent_2003));
        result.add(EMPTY_FIGURE);
        return result;
    }

    private List<IFigure> addLinkEventFigures() {
        final List<IFigure> result = new ArrayList<IFigure>();

        result.add(EMPTY_FIGURE);
        result.add(createItem(ProcessElementTypes.CatchLinkEvent_2018));
        result.add(createItem(ProcessElementTypes.ThrowLinkEvent_2019));
        result.add(EMPTY_FIGURE);
        result.add(EMPTY_FIGURE);
        return result;
    }

    private List<IFigure> addSignalEventFigures() {
        final List<IFigure> result = new ArrayList<IFigure>();
        result.add(createItem(ProcessElementTypes.StartSignalEvent_2022));
        result.add(createItem(ProcessElementTypes.IntermediateCatchSignalEvent_2021));
        result.add(createItem(ProcessElementTypes.IntermediateThrowSignalEvent_2020));
        result.add(createItem(ProcessElementTypes.EndSignalEvent_2023));
        result.add(EMPTY_FIGURE);
        return result;
    }

    private List<IFigure> addTimerEventFigures() {
        final List<IFigure> result = new ArrayList<IFigure>();
        //result.add(createClickableItem(new Point(0, 0),getHost(), ProcessElementTypes.StartTimerEvent_2016));
        result.add(createItem(ProcessElementTypes.StartTimerEvent_2016));
        result.add(createItem(ProcessElementTypes.IntermediateCatchTimerEvent_2017));
        result.add(EMPTY_FIGURE);
        result.add(EMPTY_FIGURE);
        result.add(EMPTY_FIGURE);
        return result;
    }

    private List<IFigure> addMessageEventFigures() {
        final List<IFigure> result = new ArrayList<IFigure>();
        result.add(createItem(ProcessElementTypes.StartMessageEvent_2010));
        result.add(createItem(ProcessElementTypes.IntermediateCatchMessageEvent_2013));
        result.add(createItem(ProcessElementTypes.IntermediateThrowMessageEvent_2014));
        result.add(createItem( ProcessElementTypes.EndMessageEvent_2011));
        result.add(EMPTY_FIGURE);
        return result;
    }

    private List<IFigure> addErrorEventFigures() {
        final List<IFigure> result = new ArrayList<IFigure>();
        if(createCmds != null){
            if(createCmds.get(ProcessElementTypes.StartErrorEvent_2033) == null && createCmds.get(ProcessElementTypes.StartErrorEvent_3060) == null){
                result.add(EMPTY_FIGURE);
            }else{
                result.add(createItem(ProcessElementTypes.StartErrorEvent_2033));
            }
        }else{
            result.add(createItem(ProcessElementTypes.StartErrorEvent_2033));
        }
        result.add(EMPTY_FIGURE);
        result.add(EMPTY_FIGURE);
        result.add(createItem(ProcessElementTypes.EndErrorEvent_2029));
        result.add(EMPTY_FIGURE);
        return result;
    }


    private List<IFigure> addTerminatedEventFigures() {
        final List<IFigure> result = new ArrayList<IFigure>();
        result.add(EMPTY_FIGURE);
        result.add(EMPTY_FIGURE);
        result.add(EMPTY_FIGURE);
        result.add(createItem(ProcessElementTypes.EndTerminatedEvent_2035));
        result.add(EMPTY_FIGURE);
        return result;
    }

    private IFigure createItem(final IElementType type) {
        ImageFigure image  ;
        if(type.equals(ProcessElementTypes.Task_2004) || type.equals(ProcessElementTypes.Task_3005)){
            image = new ImageFigure(Pics.getImage("decoration/","task")); //$NON-NLS-1$ //$NON-NLS-2$
        }else if(type.equals(ProcessElementTypes.CallActivity_2036) || type.equals(ProcessElementTypes.CallActivity_3063)){
            image = new ImageFigure(Pics.getImage("decoration/","plus"));  //$NON-NLS-1$//$NON-NLS-2$
        }else{
            image = new ImageFigure(IconService.getInstance().getIcon(type));
        }
        image.setSize(16, 16);
        image.setToolTip(new Label(processPaletteLabelProvider.getProcessPaletteText(type.getEClass())));

        image.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(final MouseEvent me) {
            }

            @Override
            public void mousePressed(final MouseEvent me) {
                for(final IEventSelectionListener listener : registeredListeners){
                    final Event ev = new Event();
                    ev.widget = Display.getCurrent().getActiveShell() ;
                    final SelectionEvent selev= new SelectionEvent(ev);
                    selev.data = getTypesFor(type);
                    listener.eventSelected(selev);
                }
            }
            @Override
            public void mouseDoubleClicked(final MouseEvent me) {

            }
        });

        return image;
    }

    public void addSelectionListener(final IEventSelectionListener iEventSelectionListener) {
        registeredListeners.add(iEventSelectionListener);
    }

    public void removeSelectionListener(final IEventSelectionListener iEventSelectionListener) {
        registeredListeners.remove(iEventSelectionListener);
    }

    private Object[] getTypesFor(final IElementType type) {
        if(type.equals(ProcessElementTypes.CatchLinkEvent_2018)){
            return new Object[]{ProcessElementTypes.CatchLinkEvent_2018,ProcessElementTypes.CatchLinkEvent_3019};
        }else if(type.equals(ProcessElementTypes.ThrowLinkEvent_2019)){
            return new Object[]{ProcessElementTypes.ThrowLinkEvent_2019,ProcessElementTypes.ThrowLinkEvent_3018};
        }else if(type.equals(ProcessElementTypes.StartEvent_2002)){
            return new Object[]{ProcessElementTypes.StartEvent_2002,ProcessElementTypes.StartEvent_3002};
        }else if(type.equals(ProcessElementTypes.EndEvent_2003)){
            return new Object[]{ProcessElementTypes.EndEvent_2003,ProcessElementTypes.EndEvent_3003};
        }else if(type.equals(ProcessElementTypes.StartMessageEvent_2010)){
            return new Object[]{ProcessElementTypes.StartMessageEvent_2010,ProcessElementTypes.StartMessageEvent_3012};
        }else if(type.equals(ProcessElementTypes.StartSignalEvent_2022)){
            return new Object[]{ProcessElementTypes.StartSignalEvent_2022,ProcessElementTypes.StartSignalEvent_3023};
        }else if(type.equals(ProcessElementTypes.StartTimerEvent_2016)){
            return new Object[]{ProcessElementTypes.StartTimerEvent_2016,ProcessElementTypes.StartTimerEvent_3016};
        }else if(type.equals(ProcessElementTypes.IntermediateCatchMessageEvent_2013)){
            return new Object[]{ProcessElementTypes.IntermediateCatchMessageEvent_2013,ProcessElementTypes.IntermediateCatchMessageEvent_3013};
        }else if(type.equals(ProcessElementTypes.IntermediateCatchSignalEvent_2021)){
            return new Object[]{ProcessElementTypes.IntermediateCatchSignalEvent_2021,ProcessElementTypes.IntermediateCatchSignalEvent_3021};
        }else if(type.equals(ProcessElementTypes.IntermediateCatchTimerEvent_2017)){
            return new Object[]{ProcessElementTypes.IntermediateCatchTimerEvent_2017,ProcessElementTypes.IntermediateCatchTimerEvent_3017};
        }else if(type.equals(ProcessElementTypes.IntermediateThrowMessageEvent_2014)){
            return new Object[]{ProcessElementTypes.IntermediateThrowMessageEvent_2014,ProcessElementTypes.IntermediateThrowMessageEvent_3014};
        }else if(type.equals(ProcessElementTypes.IntermediateThrowSignalEvent_2020)){
            return new Object[]{ProcessElementTypes.IntermediateThrowSignalEvent_2020,ProcessElementTypes.IntermediateThrowSignalEvent_3022};
        }else if(type.equals(ProcessElementTypes.EndMessageEvent_2011)){
            return new Object[]{ProcessElementTypes.EndMessageEvent_2011,ProcessElementTypes.EndMessageEvent_3011};
        }else if(type.equals(ProcessElementTypes.EndSignalEvent_2023)){
            return new Object[]{ProcessElementTypes.EndSignalEvent_2023,ProcessElementTypes.EndSignalEvent_3020};
        }else if(type.equals(ProcessElementTypes.EndErrorEvent_2029)){
            return new Object[]{ProcessElementTypes.EndErrorEvent_2029,ProcessElementTypes.EndErrorEvent_3050};
        }else if(type.equals(ProcessElementTypes.EndTerminatedEvent_2035)){
            return new Object[]{ProcessElementTypes.EndTerminatedEvent_2035,ProcessElementTypes.EndTerminatedEvent_3062};
        }else if(type.equals(ProcessElementTypes.StartErrorEvent_2033)){
            return new Object[]{ProcessElementTypes.StartErrorEvent_2033,ProcessElementTypes.StartErrorEvent_3060};
        }
        return null;
    }

}
