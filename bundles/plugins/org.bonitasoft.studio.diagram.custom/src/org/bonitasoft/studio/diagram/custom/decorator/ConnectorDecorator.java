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

package org.bonitasoft.studio.diagram.custom.decorator;

import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.common.figures.DecoratorSVGFigure;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction;

public class ConnectorDecorator extends AbstractBonitaDecorator/*implements IBonitaDecorator*/ { 

	/**
	 * Creates a new <code>AbstractDecorator</code> for the decorator target
	 * passed in.
	 * 
	 * @param decoratorTarget
	 *            the object to be decorated
	 * @param activityDecoratorProvider 
	 */
	public ConnectorDecorator(IDecoratorTarget decoratorTarget, ActivityDecoratorProvider activityDecoratorProvider) {
		super(decoratorTarget);
	}

	@Override
	protected DecoratorSVGFigure getImageDecorator() {
		DecoratorSVGFigure figure = FiguresHelper.getDecoratorFigure(FiguresHelper.CONNECTOR_DECORATOR) ;
		figure.setSize(24, 16) ;
		return figure;
	}
	
	@Override
	protected void activateDiagramEventBroker(EObject node,
			TransactionalEditingDomain domain, EObject model) {
		DiagramEventBroker.getInstance(domain).addNotificationListener(node,ProcessPackage.eINSTANCE.getConnectableElement_Connectors(),notificationListener); 
	}

	@Override
	protected void deactivateDiagramEventBroker(IGraphicalEditPart part) {
		DiagramEventBroker.getInstance(part.getEditingDomain()).removeNotificationListener(part.resolveSemanticElement(),ProcessPackage.eINSTANCE.getConnectableElement_Connectors(), notificationListener);
	}


	@Override
	protected boolean isAppearing(EObject activity) {
		return activity instanceof Activity && !((Activity)activity).getConnectors().isEmpty();
	}

	@Override
	protected Direction getDirection() {
		return IDecoratorTarget.Direction.NORTH_EAST;
	}

}