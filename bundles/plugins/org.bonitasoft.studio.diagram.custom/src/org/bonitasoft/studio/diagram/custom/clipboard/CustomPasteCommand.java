/**
 * Copyright (C) 2009-2013 BonitaSoft S.A.
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

package org.bonitasoft.studio.diagram.custom.clipboard;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.diagram.custom.i18n.Messages;
import org.bonitasoft.studio.diagram.custom.parts.CustomLaneCompartmentEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomSubProcessEvent2EditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomSubprocessEventCompartmentEditPart;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Actor;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.Connection;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.Data;
import org.bonitasoft.studio.model.process.DataAware;
import org.bonitasoft.studio.model.process.DataType;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.EnumType;
import org.bonitasoft.studio.model.process.Lane;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.SourceElement;
import org.bonitasoft.studio.model.process.TargetElement;
import org.bonitasoft.studio.model.process.Task;
import org.bonitasoft.studio.model.process.diagram.edit.parts.LaneLaneCompartmentEditPart;
import org.bonitasoft.studio.model.process.diagram.edit.parts.PoolPoolCompartmentEditPart;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.Tool;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.tools.AbstractTool;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.editparts.BorderedBorderItemEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.DecorationNode;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Romain Bioteau
 * @author Mickael Istria: support for multiple past
 */
public class CustomPasteCommand extends AbstractTransactionalCommand {

	public static abstract class LocationStrategy {
		private LocationStrategy() {
		};

		public abstract void alterBounds(Bounds dest, Bounds src, Point additionalData);

		public static LocationStrategy COPY = new LocationStrategy() {
			@Override
			public void alterBounds(Bounds dest, Bounds src, Point additionalData) {
				dest.setX(src.getX()+additionalData.x());
				dest.setY(src.getY()+additionalData.y());
			}
		};
		public static LocationStrategy OFFSET = new LocationStrategy() {
			@Override
			public void alterBounds(Bounds dest, Bounds src, Point additionalData) {
				dest.setX(src.getX() + 40);
				dest.setY(src.getY() + 40);
			}
		};
		public static LocationStrategy MOUSE = new LocationStrategy() {
			@Override
			public void alterBounds(Bounds dest, Bounds src, Point additionalData) {
				dest.setX(additionalData.x);
				dest.setY(additionalData.y);
			}
		};
	}

	private static final String COPY_LABEL = Messages.copyOfLabel;

	private Element targetElement;
	private IGraphicalEditPart selectedTargetEditPart;
	private final Map<EObject, View> mapping = new HashMap<EObject, View>();
	private LocationStrategy locationStrategy;

	public CustomPasteCommand(String label, IGraphicalEditPart target) {
		super(target.getEditingDomain(), label, getWorkspaceFiles(target.resolveSemanticElement()));
		selectedTargetEditPart = target;
		final EObject resolveSemanticElement = target.resolveSemanticElement();
		if(resolveSemanticElement instanceof Container){
			targetElement = (Element) resolveSemanticElement;
		} else {
			targetElement = (Element) resolveSemanticElement.eContainer();
		}
	}

	@Override
	protected CommandResult doExecuteWithResult(final IProgressMonitor progressMonitor, final IAdaptable info) throws ExecutionException {
		if (Clipboard.hasThingsToCopy()) {
			AbstractGraphicalEditPart partUnderCursor = (AbstractGraphicalEditPart) selectedTargetEditPart.getViewer().findObjectAt(getViewerRelativeCursorLocation());

			if(selectedTargetEditPart instanceof CustomSubProcessEvent2EditPart){
				if(!((CustomSubprocessEventCompartmentEditPart)((CustomSubProcessEvent2EditPart)selectedTargetEditPart).getChildren().get(1)).getCompartmentFigure().isExpanded()){
					return CommandResult.newCancelledCommandResult();
				}
			}

			if (!(partUnderCursor instanceof IGraphicalEditPart) || !targetElement.equals(((IGraphicalEditPart) partUnderCursor).resolveSemanticElement())) {
				locationStrategy = LocationStrategy.OFFSET;
			} else {
				locationStrategy = LocationStrategy.MOUSE;
			}
			final List<IGraphicalEditPart> nodesToCopy = GMFTools.addMissingConnectionsAndBoundaries(Clipboard.getToCopy());

			performPaste(progressMonitor, nodesToCopy);


			return CommandResult.newOKCommandResult();
		} else {
			return CommandResult.newCancelledCommandResult();
		}
	}

	/**
	 * @param progressMonitor
	 * @param toCopy
	 */
	public void performPaste(final IProgressMonitor progressMonitor, final List<IGraphicalEditPart> toCopy) {
		for (IGraphicalEditPart part : getNodes(toCopy)) {
			copyOnePart(progressMonitor, part, toCopy);
		}
//		for (IGraphicalEditPart part : getBoundaries(toCopy)) {
//			BoundaryEvent semanticBoundary = (BoundaryEvent)part.resolveSemanticElement();
//			EObject semanticParent = semanticBoundary.eContainer();
//			View parentView = mapping.get(semanticParent);
//			Activity newParent = (Activity)parentView.getElement();
//			BoundaryEvent newSemantic = null;
//			for (BoundaryEvent event : newParent.getBoundaryIntermediateEvents()) {
//				if (event.getName().equals(semanticBoundary.getName())) {
//					newSemantic = event;
//				}
//			}
//			mapping.put(semanticBoundary, copyView(, newSemantic, toCopy));
////			for (Object child : parentView.getChildren()) {
////				if (child instanceof Shape) {
////					Shape shape = (Shape)child;
////					if (shape.getElement().equals(semanticBoundary)) {
////						shape.setElement(newSemantic);
////						mapping.put(semanticBoundary, shape);
////					}
////				}
////			}
//		}
		for (IGraphicalEditPart part : getConnections(toCopy)) {
			ConnectionEditPart connectionPart = (ConnectionEditPart) part;
			if (toCopy.contains(connectionPart.getSource()) && toCopy.contains(connectionPart.getTarget())) {
				copyOnePart(progressMonitor, connectionPart, toCopy);
			}
		}

	}

	/**
	 * @param toCopy
	 * @return
	 */
	private List<IGraphicalEditPart> getNodes(List<IGraphicalEditPart> toCopy) {
		List<IGraphicalEditPart> res = new ArrayList<IGraphicalEditPart>();
		for (IGraphicalEditPart part : toCopy) {
			if (part instanceof ShapeNodeEditPart &&
					! (part instanceof BorderedBorderItemEditPart)) {
				res.add(part);
			}
		}
		return res;
	}

	/**
	 * @param toCopy
	 * @return
	 */
	private List<IGraphicalEditPart> getConnections(List<IGraphicalEditPart> toCopy) {
		List<IGraphicalEditPart> res = new ArrayList<IGraphicalEditPart>();
		for (IGraphicalEditPart part : toCopy) {
			if (part instanceof ConnectionEditPart) {
				res.add(part);
			}
		}
		return res;
	}

	/**
	 * @param toCopy
	 * @return
	 */
	private List<IGraphicalEditPart> getBoundaries(List<IGraphicalEditPart> toCopy) {
		List<IGraphicalEditPart> res = new ArrayList<IGraphicalEditPart>();
		for (IGraphicalEditPart part : toCopy) {
			if (part instanceof BorderedBorderItemEditPart) {
				res.add(part);
			}
		}
		return res;
	}

	/**
	 * @param progressMonitor
	 * @param info
	 * @param part
	 * @param toCopy
	 */
	protected void copyOnePart(final IProgressMonitor progressMonitor, IGraphicalEditPart part, List<IGraphicalEditPart> toCopy) {
		if(part.resolveSemanticElement() instanceof Element){
			final Element toCopyElement = (Element) part.resolveSemanticElement();
			Element res = EcoreUtil.copy(toCopyElement);
			if (canHandlePaste(part, toCopy)) {
				final Container container = (Container) targetElement;

				AbstractProcess mainProc = ModelHelper.getMainProcess(container);
				AbstractProcess pool = ModelHelper.getParentProcess(container);
				List<Element> elems = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.eINSTANCE.getElement()) ;
				Iterator<Element> it = elems.iterator();
				boolean alreadyExists = false ;
				while(it.hasNext() && !alreadyExists){
					Element e = it.next() ;
					if(e.getName() != null && toCopyElement.getName() != null){
						if(e.getName().equals(toCopyElement.getName())){
							alreadyExists = true ;
						}
					}
				}
				if(alreadyExists){
					updateLabelandId(toCopyElement.getName(), res, elems);
				}

				removeConnections(res);
				copyReferencedDataTypes(toCopyElement, res, mainProc);
				copyActorsAndActorsMapping(toCopyElement, res, mainProc, pool);
				resetConnections(toCopyElement, res, container);

				View resView = copyView(retrieveTargetCompartment(selectedTargetEditPart).getNotationView(), part, res,toCopy);
				mapping.put(part.resolveSemanticElement(), resView);
				
				//Copy Boundary view
				if(res instanceof Activity){
					if(!((Activity) res).getBoundaryIntermediateEvents().isEmpty()){
						//remove bad boundary copies
						List<View> boundaryViewsToRemove = new ArrayList<View>();
						for(Object childView : resView.getChildren()){
							if(childView instanceof Shape){

								if(res.equals(((Shape) childView).getElement())){
									boundaryViewsToRemove.add((View) childView);
								}
							}
						}
						resView.getPersistedChildren().removeAll(boundaryViewsToRemove);

						for (IGraphicalEditPart boundaryPart : getBoundaries(toCopy)) {
							BoundaryEvent semanticBoundary = (BoundaryEvent)boundaryPart.resolveSemanticElement();
							EObject semanticParent = semanticBoundary.eContainer();
							View parentView = mapping.get(semanticParent);
							if(parentView != null){
								Activity newParent = (Activity)parentView.getElement();
								if(newParent.equals(res)){
									BoundaryEvent newBoundarySemantic = null;
									for (BoundaryEvent event : newParent.getBoundaryIntermediateEvents()) {
										if (event.getName().equals(semanticBoundary.getName())) {
											newBoundarySemantic = event;
										}
									}
									mapping.put(semanticBoundary, copyView(resView, boundaryPart, newBoundarySemantic, toCopy));
								}
							}
						}					
					}
				}
				AbstractProcess targetProcess = ModelHelper.getParentProcess(targetElement);
				AbstractProcess sourceProcess = ModelHelper.getParentProcess(res);
				if(targetProcess == null || !targetProcess.equals(sourceProcess)){
					ModelHelper.removedReferencedEObjects(res,targetElement);//REMOVE DANDLING REFS !
				}
			}

			final IGraphicalEditPart newEp = (IGraphicalEditPart) selectedTargetEditPart.findEditPart(selectedTargetEditPart, res);

			if (newEp != null) {
				/* Create Node related to form element */
				for (Object child : selectedTargetEditPart.getNotationView().getChildren()) {
					if (child instanceof DecorationNode && ((DecorationNode) child).getType().equals(String.valueOf(PoolPoolCompartmentEditPart.VISUAL_ID))
							|| ((DecorationNode) child).getType().equals(String.valueOf(LaneLaneCompartmentEditPart.VISUAL_ID))) {
						newEp.getNotationView().getPersistedChildren().add(child);
					}
				}

			}
		}
	}


	private void resetConnections(final Element toCopyElement, Element res,
			final Container container) {
		// reset connections
		if (toCopyElement instanceof Connection) {
			Connection targetConnection = (Connection) res;
			Connection srcConnection = (Connection) toCopyElement;
			targetConnection.setSource((SourceElement) mapping.get(srcConnection.getSource()).getElement());
			targetConnection.setTarget((TargetElement) mapping.get(srcConnection.getTarget()).getElement());
			AbstractProcess process = ModelHelper.getParentProcess(container) ;
			process.getConnections().add((Connection) res);

		} else {
			container.getElements().add(res);
		}
	}

	protected void copyActorsAndActorsMapping(final Element originalElement,
			Element elementCopy, AbstractProcess mainProc, AbstractProcess pool) {
		// Copy referenced groups
		if (originalElement instanceof Task && !inSamePool(originalElement, targetElement)) {
			Set<Actor> newActorMappingTypes = getActorsFromElement(originalElement,mainProc) ;
			Set<Actor> existingActorMappingTypes = getExistingGroups(originalElement,mainProc) ;
			for (Actor g : newActorMappingTypes) {
				Actor copiedActorMappingType = EcoreUtil.copy(g);
				// set initiator to false to avoid conflict with target pool
				copiedActorMappingType.setInitiator(false);
				Actor existingActor = getExistingActor(pool.getActors(), copiedActorMappingType);
				if (existingActor==null){
					pool.getActors().add(copiedActorMappingType);
					existingActor = copiedActorMappingType;
				}
				((Task) elementCopy).setActor(existingActor);
			}
			if (ModelHelper.getParentLane(originalElement)!=null && !((Task)originalElement).isOverrideActorsOfTheLane()){
				Actor actor = ModelHelper.getParentLane(originalElement).getActor();
				Actor copiedActorMappingType = EcoreUtil.copy(actor);
				Actor existingActor = getExistingActor(pool.getActors(), copiedActorMappingType);
				if (existingActor==null && copiedActorMappingType!=null){
					pool.getActors().add(copiedActorMappingType);
					existingActor = copiedActorMappingType;
				}
				((Task) elementCopy).setActor(existingActor);
				((Task)elementCopy).setOverrideActorsOfTheLane(true);
			}
			for(Actor existingActorMappingType : existingActorMappingTypes){
				for(Actor procGroup : mainProc.getActors()){
					if(procGroup.getName().equals(existingActorMappingType.getName())){
						((Task) elementCopy).setActor(procGroup);
					}
				}
			}
		}
	}

	private void removeConnections(Element res) {
		// remove connections
		if (res instanceof Activity) {
			Activity activity = (Activity) res;
			activity.getIncoming().clear();
			activity.getOutgoing().clear();
			activity.getTextAnnotationAttachment().clear();
		}
	}

	private void copyReferencedDataTypes(final Element toCopyElement,
			Element res, AbstractProcess mainProc) {
		// Copy referenced data types
		if (res instanceof DataAware && !inSamePool(toCopyElement, targetElement)) {
			List<Data> datas = ModelHelper.getAllItemsOfType(res, ProcessPackage.Literals.DATA);
			for (Data d : datas) {
				if (!mainProc.getDatatypes().contains(d.getDataType()) && d.getDataType() instanceof EnumType) {
					EnumType newDt = (EnumType) EcoreUtil.copy(d.getDataType());
					mainProc.getDatatypes().add(newDt);
					d.setDataType(newDt);
				}
			}
			for (Data d : datas) {
				for(DataType type : mainProc.getDatatypes()) {
					if(d.getDataType().getName().equals(type.getName())){
						d.setDataType(type) ;
					}
				}
			}
		}
	}

	private Set<Actor> getExistingGroups(Element toCopyElement,
			AbstractProcess mainProc) {
		Set<Actor> existingGroups = new HashSet<Actor>();

		boolean exists = false ;
		Actor actor = ((Task)toCopyElement).getActor();
		if(actor != null){
			for (Actor targetGroup : mainProc.getActors()) {
				if (targetGroup.getName().equals(actor.getName())) {
					exists = true ;
				}
			}
			if(exists){
				existingGroups.add(actor)  ;
			}
		}
		return existingGroups;
	}

	private Set<Actor> getActorsFromElement(Element toCopyElement, AbstractProcess mainProc) {
		Set<Actor> actors = new HashSet<Actor>();
		Actor actor = ((Task)toCopyElement).getActor();
		Lane parentLane = ModelHelper.getParentLane((Task)toCopyElement);
		if(actor== null && parentLane != null){
			actor = parentLane.getActor();
		}
		
		if(actor != null){
			boolean exists = false ;
			for (Actor targetGroup : mainProc.getActors()) {
				if (targetGroup.getName().equals(actor.getName())) {
					exists = true ;
				}
			}
			if(!exists){
				actors.add(actor)  ;
			}
		}
		return actors;
	}

	private void updateLabelandId(String name , Element sourceCopy,List<Element> elems) {
		Iterator<Element> it = elems.iterator();
		boolean alreadyExists = false ;
		while(it.hasNext() && !alreadyExists){
			Element e = it.next() ;
			if(e.getName() != null){
				if(e.getName().equals(name)){
					alreadyExists = true ;
				}
			}
		}
		if(!(sourceCopy instanceof Connection) && alreadyExists){
			updateLabelandId(COPY_LABEL +" "+name, sourceCopy, elems);
		}else{
			sourceCopy.setName(name) ;
		}
	}

	/**
	 * 
	 */
	protected Point getPartAbsoluteCursorLocation() {
		org.eclipse.draw2d.geometry.Point toolLocation = null;
		try {
			Tool tool = selectedTargetEditPart.getViewer().getEditDomain().getActiveTool();
			AbstractTool aTool = (AbstractTool) tool;
			Method m = AbstractTool.class.getDeclaredMethod("getLocation"); //$NON-NLS-1$
			m.setAccessible(true);
			toolLocation = ((org.eclipse.draw2d.geometry.Point) m.invoke(aTool)).getCopy();

			IFigure parentFigure = selectedTargetEditPart.getFigure().getParent();
			while( parentFigure != null  ) {
				if(parentFigure instanceof Viewport) {
					Viewport viewport = (Viewport)parentFigure;
					toolLocation.translate(viewport.getHorizontalRangeModel().getValue(),
							viewport.getVerticalRangeModel().getValue());
					parentFigure = parentFigure.getParent();
				} else {
					parentFigure = parentFigure.getParent();
				}
			}

			return toolLocation;
		} catch (Exception e) {
			BonitaStudioLog.log("can't find exact location for pasting"); //$NON-NLS-1$
			return null;
		}
	}

	/**
	 * 
	 */
	protected Point getViewerRelativeCursorLocation() {
		org.eclipse.draw2d.geometry.Point toolLocation = null;
		try {
			Tool tool = selectedTargetEditPart.getViewer().getEditDomain().getActiveTool();
			AbstractTool aTool = (AbstractTool) tool;
			Method m = AbstractTool.class.getDeclaredMethod("getLocation"); //$NON-NLS-1$
			m.setAccessible(true);
			toolLocation = ((org.eclipse.draw2d.geometry.Point) m.invoke(aTool)).getCopy();
			return toolLocation;
		} catch (Exception e) {
			BonitaStudioLog.log("can't find exact location for pasting"); //$NON-NLS-1$
			return null;
		}
	}

	/**
	 * @param part
	 * @param toCopy
	 * @return
	 */
	private boolean canHandlePaste(IGraphicalEditPart part, List<IGraphicalEditPart> toCopy) {
		if (part instanceof ConnectionEditPart) {
			ConnectionEditPart connPart = (ConnectionEditPart) part;
			if (!toCopy.contains(connPart.getSource()) || !toCopy.contains(connPart.getTarget())) {
				return false;
			}
		}
		final Element toCopyElement = (Element) part.resolveSemanticElement();
		return targetElement instanceof Container && !(toCopyElement instanceof Container);
	}

	/**
	 * @param srcPart
	 * @param semanticCopy
	 * @param toCopy
	 * @param locationStrategy2
	 */
	protected View copyView(View targetContainerView, IGraphicalEditPart srcPart, Element semanticCopy, List<IGraphicalEditPart> toCopy) {
		View view = EcoreUtil.copy(srcPart.getNotationView());
		view.setElement(semanticCopy);
		for(Object child : view.getPersistedChildren()){
			if(child instanceof View){
				((View) child).setElement(semanticCopy);
			}
		}
		if (view instanceof Node) {
			Node node = (Node) view;
			if (targetContainerView != null) {
				targetContainerView.getPersistedChildren().add(view);
			}
			Point p = getPartAbsoluteCursorLocation();

			Rectangle b  =  null ;
			if(selectedTargetEditPart instanceof CustomSubProcessEvent2EditPart){
				b  =  ((IGraphicalEditPart)selectedTargetEditPart.getParent().getParent()).getFigure().getBounds().getCopy() ;
				FiguresHelper.translateToAbsolute(((IGraphicalEditPart)selectedTargetEditPart.getParent().getParent()).getFigure(),b) ;
				Point absoluteContainerLocation = b.getLocation()  ;
				p.translate(-absoluteContainerLocation.x, -absoluteContainerLocation.y);
			}else{
				b  =  selectedTargetEditPart.getFigure().getBounds().getCopy() ;
				FiguresHelper.translateToAbsolute(selectedTargetEditPart.getFigure(),b) ;
				Point absoluteContainerLocation = b.getLocation() ;
				p.translate(-absoluteContainerLocation.x, -absoluteContainerLocation.y);
				
			}
			
			Rectangle rect = computeRelativeLocation(p,srcPart,toCopy) ;
			int relativeToPoolY=0;
			if (srcPart.getParent() instanceof CustomLaneCompartmentEditPart){
				relativeToPoolY+=((IGraphicalEditPart)srcPart.getParent().getParent()).getFigure().getBounds().getCopy().y();
			}
			if(locationStrategy.equals(LocationStrategy.OFFSET)){
				locationStrategy.alterBounds((Bounds) node.getLayoutConstraint(), (Bounds) ((Node) selectedTargetEditPart.getNotationView()).getLayoutConstraint(), rect.getLocation());
			}else if(locationStrategy.equals(LocationStrategy.MOUSE)){
				locationStrategy.alterBounds((Bounds) node.getLayoutConstraint(), (Bounds) ((Node) srcPart.getNotationView()).getLayoutConstraint(),rect.getLocation());
			}else{
				locationStrategy.alterBounds((Bounds) node.getLayoutConstraint(), (Bounds) ((Node) srcPart.getNotationView()).getLayoutConstraint(), new Point(0,relativeToPoolY));
			}

		} else if (view instanceof Edge) {
			Edge edgeView = (Edge) view;
			edgeView.setSource(mapping.get(((Connection) srcPart.resolveSemanticElement()).getSource()));
			edgeView.setTarget(mapping.get(((Connection) srcPart.resolveSemanticElement()).getTarget()));
			Diagram diagram = getDiagram(selectedTargetEditPart.getNotationView());
			diagram.getPersistedEdges().add(view);
		}
		
		return view;
	}

	private ShapeCompartmentEditPart retrieveTargetCompartment(IGraphicalEditPart targetEditPart) {
		ShapeCompartmentEditPart targetCompartement = null;
		if (targetEditPart instanceof ShapeCompartmentEditPart) {
			targetCompartement = (ShapeCompartmentEditPart) targetEditPart;
			//selectedTargetEditPart = (IGraphicalEditPart)targetEditPart.getParent();
		} else {
			for (Object childPart : targetEditPart.getChildren()) {
				if (childPart instanceof ShapeCompartmentEditPart) {
					targetCompartement = (ShapeCompartmentEditPart) childPart;
				}
			}
		}
		return targetCompartement;
	}

	/**
	 * Compute the relative location of each copied editparts
	 */
	private Rectangle computeRelativeLocation(Point newOrigin,IGraphicalEditPart srcPart, List<IGraphicalEditPart> toCopy) {
		int relativeToPoolY=0;
		Rectangle newLoc = new Rectangle(newOrigin, new Dimension()) ;
		newLoc.setY(newLoc.y()+relativeToPoolY);
		int minX = Integer.MAX_VALUE ;
		IGraphicalEditPart mostLeftEditPart = null ;
		for(IGraphicalEditPart parts : toCopy){
			if(!(parts instanceof AbstractConnectionEditPart)){
				int x =((Location)((Node)parts.getNotationView()).getLayoutConstraint()).getX() ;

				if(x < minX){
					minX = x ;
					mostLeftEditPart = parts ;
				}
			}
		}

		if(mostLeftEditPart.equals(srcPart)){
			return newLoc;
		}else{
			int referenceX = minX ;
			int referenceY =((Location)((Node)mostLeftEditPart.getNotationView()).getLayoutConstraint()).getY() ;
			int toCopyX = ((Location)((Node)srcPart.getNotationView()).getLayoutConstraint()).getX() ;
			int toCopyY = ((Location)((Node)srcPart.getNotationView()).getLayoutConstraint()).getY() ;
			newLoc = newLoc.translate(toCopyX-referenceX, toCopyY-referenceY);
			newLoc.setY(newLoc.y()+relativeToPoolY);
			return newLoc;
		}

	}

	

	/**
	 * @param notationView
	 * @return
	 */
	private Diagram getDiagram(View notationView) {
		while (!(notationView instanceof Diagram)) {
			notationView = (View) notationView.eContainer();
		}
		return (Diagram) notationView;
	}

	/**
	 * @param mainProc
	 */
	protected boolean inSamePool(Element item1, Element item2) {
		return ModelHelper.getParentProcess(item1).equals(ModelHelper.getParentProcess(item2));
	}

	/**
	 * @param locationStrategy
	 */
	public void setLocationStrategy(LocationStrategy locationStrategy) {
		this.locationStrategy = locationStrategy;
	}


	private Actor getExistingActor(List<Actor>actors,Actor actor){
		for (Actor a:actors){
			if (a.getName().equals(actor.getName())){
				return a;
			}
		}
		return null;
	}
}
