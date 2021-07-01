/**
 * Copyright (C) 2011 BonitaSoft S.A.
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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.common.figures.DecoratorSVGFigure;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Locator;
import org.eclipse.draw2d.StackLayout;
import org.eclipse.draw2d.TreeSearch;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.editparts.ZoomListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.handles.AbstractHandle;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IResizableCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.properties.Properties;
import org.eclipse.gmf.runtime.diagram.ui.internal.tools.CompartmentCollapseTracker;
import org.eclipse.gmf.runtime.draw2d.ui.graphics.ColorRegistry;
import org.eclipse.gmf.runtime.notation.DrawerStyle;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Romain Bioteau
 *
 */
public class SubprocessCollapseHandle extends AbstractHandle implements PropertyChangeListener, NotificationListener,ZoomListener {

	/** 
	 * Positions the supplied figure in its owner's top left corner offset by [1,1] 
	 */
	private class CollapseHandleLocator implements Locator {
		public void relocate(IFigure target) {
			Rectangle theBounds = getOwnerFigure().getParent().getBounds().getCopy();          
			getOwnerFigure().translateToAbsolute(theBounds);
			target.translateToRelative(theBounds);
			Point loc = theBounds.getBottom(); 
			double zoom = zoomManager.getZoom() ;
			loc.translate(Math.round((float)zoom*-5), Math.round((float)zoom*-20)) ;
			target.setLocation(loc);            
		}
	}


	/** the handle figure */
	protected DecoratorSVGFigure collapseFigure = null;

	private DecoratorSVGFigure plus ;
	private DecoratorSVGFigure minus;

	private ZoomManager zoomManager;

	/**
	 * Creates a new Compartment Collapse Handle
	 * 
	 * @param owner
	 */
	public SubprocessCollapseHandle(IGraphicalEditPart owner) {

		setOwner(owner);
		zoomManager = ((DiagramRootEditPart) owner.getRoot()).getZoomManager();
		setLocator(new CollapseHandleLocator());
		setCursor(Cursors.HAND);

		plus = FiguresHelper.getDecoratorFigure(FiguresHelper.SUBPROCESS_DECORATOR_EXPAND) ;
		minus = FiguresHelper.getDecoratorFigure(FiguresHelper.SUBPROCESS_DECORATOR_COLLAPSE) ;


		setLayoutManager(new StackLayout());

		zoomManager.addZoomListener(this) ;
		setSize(plus.getSize().scale(zoomManager.getZoom()));

		View view = owner.getNotationView();
		if (view != null) {
			DrawerStyle style = (DrawerStyle) view.getStyle(NotationPackage.eINSTANCE.getDrawerStyle());
			if (style != null) {
				if(style.isCollapsed()){
					add(plus) ;
				}else{
					add(minus) ;
				}
				for(Object child : getChildren()){
					if(child instanceof DecoratorSVGFigure){
						final IGraphicalEditPart parentEditpart = (IGraphicalEditPart)getOwner().getParent();
						((DecoratorSVGFigure) child).setColor(ColorRegistry.getInstance().getColor((Integer) parentEditpart.getStructuralFeatureValue(NotationPackage.eINSTANCE.getLineStyle_LineColor()))
								,ColorRegistry.getInstance().getColor((Integer) parentEditpart.getStructuralFeatureValue(NotationPackage.eINSTANCE.getFillStyle_FillColor())));
					}
				}
				return;
			}
			
		}
	}

	/**
	 * @see org.eclipse.draw2d.IFigure#findFigureAt(int, int, TreeSearch)
	 */
	public IFigure findFigureAt(int x, int y, TreeSearch search) {
		IFigure found = super.findFigureAt(x, y, search);
		return (minus.equals(found) || plus.equals(found)) ? this : found;
	}

	/**
	 * @see org.eclipse.gef.handles.AbstractHandle#createDragTracker()
	 */
	protected DragTracker createDragTracker() {
		return new CompartmentCollapseTracker(
				(IResizableCompartmentEditPart) getOwner());
	}

	/**
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Properties.ID_COLLAPSED)){
			if(((Boolean) evt.getNewValue()).booleanValue()){
				remove(minus);
				add(plus) ;
			}else{
				remove(plus) ;
				add(minus) ;
			}
		}
	}

	/**
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void notifyChanged(Notification notification) {
		if (NotationPackage.eINSTANCE.getDrawerStyle_Collapsed()==notification.getFeature()){
			if(notification.getNewBooleanValue()){
				if(getChildren().contains(minus)){
					remove(minus);
				}
				add(plus) ;
			}else{
				remove(plus) ;
				add(minus) ;
			}
			for(Object child : getChildren()){
				if(child instanceof DecoratorSVGFigure){
					((DecoratorSVGFigure) child).setColor(ColorRegistry.getInstance().getColor((Integer) ((IGraphicalEditPart)getOwner().getParent()).getStructuralFeatureValue(NotationPackage.eINSTANCE.getLineStyle_LineColor()))
							,ColorRegistry.getInstance().getColor((Integer) ((IGraphicalEditPart)getOwner().getParent()).getStructuralFeatureValue(NotationPackage.eINSTANCE.getFillStyle_FillColor())));
				}
			}
		}else if(NotationPackage.eINSTANCE.getLineStyle_LineColor()==notification.getFeature()){
			for(Object child : getChildren()){
				if(child instanceof DecoratorSVGFigure){
					((DecoratorSVGFigure) child).setColor(ColorRegistry.getInstance().getColor(notification.getNewIntValue()),ColorRegistry.getInstance().getColor((Integer) ((IGraphicalEditPart)getOwner().getParent()).getStructuralFeatureValue(NotationPackage.eINSTANCE.getFillStyle_FillColor())));
				}
			}
		}

	}

	/**
	 * @see org.eclipse.draw2d.IFigure#addNotify()
	 */
	public void addNotify() {
		super.addNotify();
		IGraphicalEditPart owner = (IGraphicalEditPart) getOwner();
		View view = owner.getNotationView();
		if (view!=null){
			getDiagramEventBroker().addNotificationListener(owner.getNotationView(),SubprocessCollapseHandle.this);
			getDiagramEventBroker().addNotificationListener(((IGraphicalEditPart) owner.getParent()).getNotationView(),NotationPackage.eINSTANCE.getLineStyle_LineColor(),SubprocessCollapseHandle.this);
		}
	}

	/**
	 * @see org.eclipse.draw2d.IFigure#removeNotify()
	 */
	public void removeNotify() {
		IGraphicalEditPart owner = (IGraphicalEditPart) getOwner();
		getDiagramEventBroker().removeNotificationListener(owner.getNotationView(),this);
		super.removeNotify();
	}

	private DiagramEventBroker getDiagramEventBroker() {
		TransactionalEditingDomain theEditingDomain = ((IGraphicalEditPart) getOwner())
		.getEditingDomain();
		if (theEditingDomain != null) {
			return DiagramEventBroker.getInstance(theEditingDomain);
		}
		return null;
	}

	public void zoomChanged(double zoom) {
		for(Object child : getChildren()){
			if(child instanceof DecoratorSVGFigure){
				setSize(new Dimension(16, 16).scale(zoom)) ;
			}
		}
	}

}
