/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.gmf.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.bonitasoft.studio.common.diagram.tools.BonitaConnectionTypes;
import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.model.process.SubProcessEvent;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.commands.DeferredCreateConnectionViewAndElementCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.commands.SetBoundsCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.commands.SetConnectionBendpointsCommand;
import org.eclipse.gmf.runtime.diagram.ui.internal.parts.PaletteToolTransferDropTargetListener;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest.ConnectionViewAndElementDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest.ViewAndElementDescriptor;
import org.eclipse.gmf.runtime.emf.core.util.EObjectAdapter;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.Connector;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
 * @author Aurelien Pupier
 * Enable Selection of newly created elements on DnD
 */
public class PaletteToolTransferDropTargetListenerWithSelection extends	PaletteToolTransferDropTargetListener {

	public PaletteToolTransferDropTargetListenerWithSelection(EditPartViewer viewer) {
		super(viewer);
	}

	/**
	 * Overridden to select the created object.
	 * 
	 * @see org.eclipse.gef.dnd.AbstractTransferDropTargetListener#handleDrop()
	 * 
	 * /!\ don't call super, this method is copied from AbstractTransferDropTargetListener,
	 * and added a method to select added object (method copied from CreationTool)
	 * Please note that intermediate class are also trying to 
	 */
	protected void handleDrop() {
		CreateRequest request = getCreateRequest();
		Point loc = super.getDropLocation().getCopy();
		loc.translate(0, -25);
		request.setLocation(loc);
		updateTargetEditPart();

		if (getTargetEditPart() != null) {
			Command command = getCommand();
			if (command != null && command.canExecute()){
				getViewer().getEditDomain().getCommandStack().execute(command);
				insertOnSequenceFlow(command,getTargetEditPart(),getViewer());
				selectAddedObject(getViewer(),DiagramCommandStack.getReturnValues(command));
				getViewer().getEditDomain().loadDefaultTool();
			} else {
				getCurrentEvent().detail = DND.DROP_NONE;
			}
		} else {
			getCurrentEvent().detail = DND.DROP_NONE;
		}

	}
	
	
	protected void handleDragOver() {
		updateTargetEditPart();
		updateTargetRequest();
		if(getCommand() != null && getCommand().canExecute()){
			getCurrentEvent().detail = DND.DROP_COPY;
		}else{
			getCurrentEvent().detail = DND.DROP_NONE;
		}
		
		getCurrentEvent().feedback = DND.FEEDBACK_SCROLL | DND.FEEDBACK_EXPAND;
		showTargetFeedback();
	}

	@Override
	protected Point getDropLocation() {
		Point loc = super.getDropLocation().getCopy();
		return loc;
	}


	public static void insertOnSequenceFlow(Command command,final EditPart targetEditPart,EditPartViewer viewer) {
		Collection objects = DiagramCommandStack.getReturnValues(command) ;
		CompoundCommand cc = new CompoundCommand("Check Overlap") ;
		final List editparts = new ArrayList();
		if(targetEditPart instanceof ConnectionEditPart){
			for (Iterator i = objects.iterator(); i.hasNext();) {
				Object object = i.next();
				if (object instanceof ViewAndElementDescriptor) {
					final ViewAndElementDescriptor descriptor = (ViewAndElementDescriptor) object; 
					final ShapeEditPart editPart = (ShapeEditPart) viewer.getEditPartRegistry().get(descriptor.getAdapter(View.class));
					final ReconnectRequest reconnect = new ReconnectRequest(RequestConstants.REQ_RECONNECT_TARGET);
					reconnect.setConnectionEditPart((ConnectionEditPart) targetEditPart);
					reconnect.setTargetEditPart(editPart);
					Command reconnectCommand = 	editPart.getCommand(reconnect);
					CreateConnectionViewAndElementRequest connectionRequest = null;
					DeferredCreateConnectionViewAndElementCommand connectionCommand = null;
					connectionRequest = new CreateConnectionViewAndElementRequest(BonitaConnectionTypes.getElementType("org.bonitasoft.studio.diagram.SequenceFlow_4001"),
							((IHintedType) BonitaConnectionTypes.getElementType("org.bonitasoft.studio.diagram.SequenceFlow_4001")).getSemanticHint(), new PreferencesHint("org.bonitasoft.studio.diagram"));

					connectionCommand = new DeferredCreateConnectionViewAndElementCommand(connectionRequest, descriptor, ((ConnectionEditPart)targetEditPart).getTarget(), editPart.getViewer());
					CompoundCommand coupound = 	new CompoundCommand();
					coupound.add(reconnectCommand);
					coupound.add(new ICommandProxy(connectionCommand));
					viewer.getEditDomain().getCommandStack().execute(coupound);
					if(connectionRequest != null){
						ConnectionViewAndElementDescriptor connectionDescriptor = (ConnectionViewAndElementDescriptor) connectionRequest.getNewObject() ;
						Connector edge = (Connector) connectionDescriptor.getAdapter(Edge.class) ;
						org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart connectionEP =  (org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart) editPart.getViewer().getEditPartRegistry().get(edge);

						if(connectionEP != null){
							SetConnectionBendpointsCommand setConnectionBendPointsCommand = new SetConnectionBendpointsCommand(connectionEP.getEditingDomain());
							setConnectionBendPointsCommand.setEdgeAdapter(new EObjectAdapter(edge));	
							PointList bendpoints =  new PointList() ;
							bendpoints.addPoint(0, 0) ;
							bendpoints.addPoint(0, 0) ;
							setConnectionBendPointsCommand.setNewPointList(bendpoints, bendpoints.getFirstPoint(), bendpoints.getLastPoint());
							viewer.getEditDomain().getCommandStack().execute(new ICommandProxy(setConnectionBendPointsCommand));
							
							setConnectionBendPointsCommand = new SetConnectionBendpointsCommand(((IGraphicalEditPart) targetEditPart).getEditingDomain());
							setConnectionBendPointsCommand.setEdgeAdapter(new EObjectAdapter(((IGraphicalEditPart) targetEditPart).getNotationView()));	
							bendpoints = new PointList() ;
							bendpoints.addPoint(0, 0) ;
							bendpoints.addPoint(0, 0) ;
							setConnectionBendPointsCommand.setNewPointList(bendpoints, bendpoints.getFirstPoint(), bendpoints.getLastPoint());
							viewer.getEditDomain().getCommandStack().execute(new ICommandProxy(setConnectionBendPointsCommand));
						}
					}
				}
			}
		}
		for (Iterator i = objects.iterator(); i.hasNext();) {
			Object object = i.next();
			if (object instanceof IAdaptable) {
				if (viewer != null) {
					Object editPart = viewer.getEditPartRegistry().get(((IAdaptable) object).getAdapter(View.class));
					if (editPart != null) {
						editparts.add(editPart);
					}
				}
			}
		}

		for(Object ep :editparts){
			if(ep instanceof IGraphicalEditPart){
				Location loc = (Location) ((Node)((IGraphicalEditPart) ep).getNotationView()).getLayoutConstraint() ;
				Point newLoc = FiguresHelper.handleCompartmentMargin((IGraphicalEditPart) ep, loc.getX(), loc.getY(),(((IGraphicalEditPart) ep).resolveSemanticElement() instanceof SubProcessEvent)) ;
				if(((IGraphicalEditPart) ep).getParent() instanceof ShapeCompartmentEditPart){
					ShapeCompartmentEditPart compartment = (ShapeCompartmentEditPart) ((IGraphicalEditPart) ep).getParent();
					while(newLoc.y + 65 > compartment.getFigure().getBounds().height){
						newLoc.y = newLoc.y -10;
					}
					while(newLoc.x + 100 > compartment.getFigure().getBounds().width){
						newLoc.x = newLoc.x -10;
					}
				}
				cc.add(new ICommandProxy(new SetBoundsCommand(((IGraphicalEditPart) ep).getEditingDomain(), "Check Overlap", new EObjectAdapter(((IGraphicalEditPart) ep).getNotationView()),newLoc))) ;
			}
		}


		if(!cc.isEmpty()){
			viewer.getEditDomain().getCommandStack().execute(cc) ;
		}
	}

	/**
	 * Select the newly added shape view by default
	 * @param viewer
	 * @param objects
	 * 
	 * Copied from CreationTool
	 */
	protected void selectAddedObject(final EditPartViewer viewer, Collection objects) {
		final List editparts = new ArrayList();
		for (Iterator i = objects.iterator(); i.hasNext();) {
			Object object = i.next();
			if (object instanceof IAdaptable) {
				Object editPart =
					viewer.getEditPartRegistry().get(
						((IAdaptable)object).getAdapter(View.class));
				if (editPart != null)
					editparts.add(editPart);
			}
		}

		if (!editparts.isEmpty()) {
			// automatically put the first shape into edit-mode
			Display.getCurrent().asyncExec(new Runnable() {
				public void run(){
					EditPart editPart = (EditPart) editparts.get(0);
					viewer.setSelection(new StructuredSelection(editPart));
					if ( editPart.isActive() ) {
						editPart.performRequest(new Request(RequestConstants.REQ_DIRECT_EDIT));
						revealEditPart(editPart);
					}
				}
			});
		}
	}

	
	/**
	 * Reveals the newly created editpart
	 * @param editPart
	 * Copied from CreationTool
	 */
	protected void revealEditPart(EditPart editPart){
		if ((editPart != null)&&
				(editPart.getViewer() != null))
			editPart.getViewer().reveal(editPart);
	}

}
