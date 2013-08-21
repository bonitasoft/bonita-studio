package org.bonitasoft.studio.diagram.custom.editPolicies;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.diagram.custom.parts.CustomLaneEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomPoolCompartmentEditPart;
import org.bonitasoft.studio.diagram.custom.parts.CustomPoolEditPart;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.handles.AbstractHandle;
import org.eclipse.gef.handles.HandleBounds;
import org.eclipse.gef.handles.RelativeHandleLocator;
import org.eclipse.gef.handles.SquareHandle;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gef.tools.ResizeTracker;
import org.eclipse.swt.SWT;

public class CustomPoolResizeHandle extends SquareHandle {

	/**
	 * The default size for square handles.
	 */
	protected static final int DEFAULT_HANDLE_SIZE = 12;
	private int cursorDirection;
	private boolean isContrained = true ;
	private CustomLaneEditPart lane = null;



	public CustomPoolResizeHandle(GraphicalEditPart owner, int direction) {
		setOwner(owner);
		setLocator(new RelativeHandleLocator(owner.getFigure(), direction));
		setCursor(Cursors.getDirectionalCursor(direction, owner.getFigure()
				.isMirrored()));
		cursorDirection = direction;
	}



	@Override
	protected DragTracker createDragTracker() {
		if (getOwner() instanceof CustomPoolEditPart){
			CustomPoolEditPart pool = (CustomPoolEditPart)getOwner();
			for (Object o:pool.getChildren()){
				if (o instanceof CustomPoolCompartmentEditPart){
					for (Object o2:((CustomPoolCompartmentEditPart)o).getChildren()){
						if (o2 instanceof CustomLaneEditPart){
							lane = (CustomLaneEditPart)o2;
						}
					}
				}
			}
			return new ResizeTracker(lane, cursorDirection){
				@Override
				protected GraphicalEditPart getTargetEditPart() {
					return lane;
				}
				
				@Override
				protected List getOperationSet() {
					List operationSet = new ArrayList();
					operationSet.add(lane);
					return operationSet;
				}
			};
		}
		return new ResizeTracker(getOwner(),cursorDirection);


	}

}
