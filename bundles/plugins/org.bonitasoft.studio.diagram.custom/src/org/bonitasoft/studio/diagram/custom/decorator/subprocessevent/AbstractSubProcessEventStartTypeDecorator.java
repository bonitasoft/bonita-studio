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
package org.bonitasoft.studio.diagram.custom.decorator.subprocessevent;

import org.bonitasoft.studio.diagram.custom.decorator.AbstractBonitaDecorator;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.bonitasoft.studio.model.process.diagram.edit.parts.SubProcessEvent2EditPart;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.core.listener.DiagramEventBroker;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoratorTarget.Direction;
import org.eclipse.gmf.runtime.notation.BasicCompartment;
import org.eclipse.gmf.runtime.notation.NotationPackage;

/**
 * @author Aurelien Pupier
 */
public abstract class AbstractSubProcessEventStartTypeDecorator extends AbstractBonitaDecorator {

	public AbstractSubProcessEventStartTypeDecorator(IDecoratorTarget decoratorTarget) {
		super(decoratorTarget);
	}

	@Override
	protected Direction getDirection() {
		return IDecoratorTarget.Direction.NORTH_WEST;
	}


	@Override
	protected boolean isAppearing(EObject activity) {
		if(getDecoratorTarget().getAdapter(EditPart.class) != null && getDecoratorTarget().getAdapter(EditPart.class) instanceof SubProcessEvent2EditPart){
			SubProcessEvent2EditPart ep = (SubProcessEvent2EditPart) getDecoratorTarget().getAdapter(EditPart.class)  ;
			boolean collapse = false ;
			for(Object c : ep.getChildren()){
				if(c instanceof ShapeCompartmentEditPart){
					collapse = !((ShapeCompartmentEditPart) c).getCompartmentFigure().isExpanded() ;
				}
			}
			boolean startWithEvent = false ; 
			for(EObject eObject : ((SubProcessEvent)activity).getElements()){
				if(getClassOfStartType().isInstance(eObject)){
					startWithEvent = true ;
				}
			}
			
			return activity instanceof SubProcessEvent && collapse && startWithEvent ;
		}
		return false;
	}

	protected abstract Class<?> getClassOfStartType();

	@Override
	protected void activateDiagramEventBroker(EObject node, TransactionalEditingDomain domain,
			EObject model) {
		if(node instanceof SubProcessEvent){
			if(getDecoratorTarget().getAdapter(EditPart.class) != null && getDecoratorTarget().getAdapter(EditPart.class) instanceof SubProcessEvent2EditPart){
				SubProcessEvent2EditPart ep = (SubProcessEvent2EditPart) getDecoratorTarget().getAdapter(EditPart.class)  ;
				BasicCompartment v = null ;
				for(Object c : ep.getChildren()){
					if(c instanceof ShapeCompartmentEditPart){
						v = (BasicCompartment) ((ShapeCompartmentEditPart) c).getNotationView() ;
					}
				}
				if(v != null ){
					DiagramEventBroker.getInstance(domain).addNotificationListener(v,NotationPackage.eINSTANCE.getDrawerStyle_Collapsed(),notificationListener);  
				}
			}
			DiagramEventBroker.getInstance(domain).addNotificationListener(((SubProcessEvent)node),ProcessPackage.eINSTANCE.getContainer_Elements(),notificationListener);  
			  
		}
	}

	@Override
	protected void deactivateDiagramEventBroker(IGraphicalEditPart part) {
		if(part instanceof SubProcessEvent2EditPart){
			SubProcessEvent2EditPart ep = (SubProcessEvent2EditPart) part ;
			BasicCompartment v = null ;
			for(Object c : ep.getChildren()){
				if(c instanceof ShapeCompartmentEditPart){
					v = (BasicCompartment) ((ShapeCompartmentEditPart) c).getNotationView() ;
				}
			}
			if(v != null ){
				DiagramEventBroker.getInstance(part.getEditingDomain()).removeNotificationListener(v,NotationPackage.eINSTANCE.getDrawerStyle_Collapsed(),notificationListener);  
			}
		}
		DiagramEventBroker.getInstance(part.getEditingDomain()).removeNotificationListener(part.resolveSemanticElement(),ProcessPackage.eINSTANCE.getContainer_Elements(), notificationListener);
	}

}
