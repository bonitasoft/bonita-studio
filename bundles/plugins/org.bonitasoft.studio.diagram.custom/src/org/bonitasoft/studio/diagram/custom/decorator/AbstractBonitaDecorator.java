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
package org.bonitasoft.studio.diagram.custom.decorator;

import org.bonitasoft.studio.common.figures.CustomSVGFigure;
import org.bonitasoft.studio.common.figures.DecoratorSVGFigure;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DecorationEditPolicy.DecoratorTarget;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoration;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.gmf.runtime.notation.Size;
import org.eclipse.swt.graphics.Color;

/**
 * @author Aurelien Pupier
 * Abstract class to use Decorator.
 */
public abstract class AbstractBonitaDecorator implements IBonitaDecorator {

    /** the object to be decorated */
    protected IDecoratorTarget decoratorTarget;

    /** the decoration being displayed */
    protected IDecoration decoration;

    protected abstract DecoratorSVGFigure getImageDecorator();


    /**
     * Creates a new <code>AbstractDecorator</code> for the decorator target
     * passed in.
     *
     * @param decoratorTarget
     *            the object to be decorated
     */
    public AbstractBonitaDecorator(final IDecoratorTarget decoratorTarget) {
        this.decoratorTarget = decoratorTarget;

    }

    /**
     * Gets the object to be decorated.
     *
     * @return Returns the object to be decorated
     */
    protected IDecoratorTarget getDecoratorTarget() {
        return decoratorTarget;
    }

    /**
     * @return Returns the decoration.
     */
    public IDecoration getDecoration() {
        return decoration;
    }

    /**
     * @param decoration
     *            The decoration to set.
     */
    public void setDecoration(final IDecoration decoration) {
        this.decoration = decoration;
    }

    /**
     * Removes the decoration if it exists and sets it to null.
     */
    @Override
    public void removeDecoration() {
        if (decoration != null) {
            decoratorTarget.removeDecoration(decoration);
            decoration = null;
        }
    }


    /**
     * Creates the appropriate review decoration if all the criteria is
     * satisfied by the view passed in.
     */
    @Override
    public void refresh() {

        final GraphicalEditPart node = ActivityDecoratorProvider.getDecoratorTargetNode(getDecoratorTarget());

        //BUGFIX : NullPointerException if Root is null
        if(node != null && node.getRoot() != null ){

            final EObject activity= (EObject) node.getAdapter(EObject.class);
            if (activity != null) {
                removeDecoration();
                if(isAppearing(activity)) {
                    final Node view =(Node) ((DecoratorTarget) getDecoratorTarget()).getAdapter(Node.class) ;
                    final IFigure figure = getImageDecorator();
                    setDecoration(getDecoratorTarget().addShapeDecoration(figure, getDirection(), getDelta(view), false));
                }
            }
        }
    }


    /**
     * Indicate where place the decoration, IDecoratorTarget.Direction
     * @return
     */
    protected abstract Direction getDirection();

    /**
     * Indicate if the decoration will appear depending on the activity state/type.
     * @param activity
     * @return
     */
    protected abstract boolean isAppearing(EObject activity);

    /**
     * @param fig
     * @return
     */
    protected int getDelta(final Node view){
        if(getDecoratorTarget().getAdapter(EditPart.class) instanceof IGraphicalEditPart){
            final IGraphicalEditPart ep = (IGraphicalEditPart) getDecoratorTarget().getAdapter(EditPart.class) ;
            if(ep.resolveSemanticElement() instanceof SubProcessEvent){
                return -5 ;
            }
        }

        int delta = -5 ;
        if(view != null){
            final LayoutConstraint layoutConstraint = view.getLayoutConstraint();
            if (layoutConstraint instanceof Size) {
                final int width = ((Size)layoutConstraint).getWidth() ;
                if(width != 0){
                    delta = - (width / 15) ;
                    if(delta > -5){
                        delta = -5 ;
                    }
                }else{
                    delta = - 5 ;
                }
            }

        }

        return delta;
    }


    protected NotificationListener notificationListener = new NotificationListener() {

        /* (non-Javadoc)
         * @see org.eclipse.gmf.runtime.diagram.core.listener.NotificationListener#notifyChanged(org.eclipse.emf.common.notify.Notification)
         */
        @Override
        public void notifyChanged(final Notification notification) {
            refresh();
        }
    };

    /**
     * Adds decoration if applicable.
     */
    @Override
    public void activate() {

        final EObject node = (EObject)getDecoratorTarget().getAdapter(EObject.class);
        final IGraphicalEditPart gep = (IGraphicalEditPart)getDecoratorTarget().getAdapter(IGraphicalEditPart.class);
        assert node != null;
        assert gep != null;
        if(node != null){
            DiagramEventBroker.getInstance(gep.getEditingDomain()).addNotificationListener( gep.getNotationView(),NotationPackage.eINSTANCE.getFillStyle_FillColor(),notificationListener);
            DiagramEventBroker.getInstance(gep.getEditingDomain()).addNotificationListener( gep.getNotationView(),NotationPackage.eINSTANCE.getLineStyle_LineColor(),notificationListener);
            DiagramEventBroker.getInstance(gep.getEditingDomain()).addNotificationListener(gep.getNotationView(),NotationPackage.eINSTANCE.getSize_Width(),notificationListener);
            activateDiagramEventBroker(node, gep.getEditingDomain(), (EObject)gep.getModel());
        }
    }

    protected abstract void activateDiagramEventBroker(EObject node, TransactionalEditingDomain domain, EObject model);
    protected abstract void deactivateDiagramEventBroker(IGraphicalEditPart part);

    /**
     * Removes the decoration.
     */
    @Override
    public void deactivate() {
        removeDecoration();
        if(decoratorTarget != null){
            final IGraphicalEditPart gep = (IGraphicalEditPart)decoratorTarget.getAdapter(IGraphicalEditPart.class);
            assert gep != null;

            DiagramEventBroker.getInstance(gep.getEditingDomain()).removeNotificationListener(gep.getNotationView(),NotationPackage.eINSTANCE.getSize_Width(),notificationListener);
            DiagramEventBroker.getInstance(gep.getEditingDomain()).removeNotificationListener( gep.getNotationView(),NotationPackage.eINSTANCE.getFillStyle_FillColor(),notificationListener);
            DiagramEventBroker.getInstance(gep.getEditingDomain()).removeNotificationListener( gep.getNotationView(),NotationPackage.eINSTANCE.getLineStyle_LineColor(),notificationListener);
            deactivateDiagramEventBroker(gep);

            decoratorTarget = null;
        }
    }

    protected IFigure updateColor(final Color foergroundColor , final Color backgroundColor){
        final CustomSVGFigure figure = getImageDecorator() ;
        figure.setColor(foergroundColor, backgroundColor) ;
        return figure ;
    }


}
