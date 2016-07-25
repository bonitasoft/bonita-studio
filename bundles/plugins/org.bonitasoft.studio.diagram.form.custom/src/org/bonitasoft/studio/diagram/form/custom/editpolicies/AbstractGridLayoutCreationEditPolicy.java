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
package org.bonitasoft.studio.diagram.form.custom.editpolicies;

import java.util.Iterator;

import org.bonitasoft.studio.common.diagram.tools.FiguresHelper;
import org.bonitasoft.studio.diagram.form.custom.commands.AddColumnCommand;
import org.bonitasoft.studio.diagram.form.custom.commands.AddRowCommand;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormEditPart;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.CreateCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;

public abstract class AbstractGridLayoutCreationEditPolicy extends CreationEditPolicy {

	/*ugly way to avoid the fact that the last request have a bad location (-1,-1)*/
	protected int lineTemp = 0;//init to prevent from not initialized exception
	protected int columnTemp = 0;//init to prevent from not initialized exception


	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy#
	 * getCreateCommand
	 * (org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest)
	 */
	@Override
	protected Command getCreateCommand(CreateViewRequest request) {
		boolean canAdd = true ;
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
		CompositeTransactionalCommand cc = new CompositeTransactionalCommand(editingDomain, DiagramUIMessages.AddCommand_Label);

		IGraphicalEditPart part = (IGraphicalEditPart) getHost();

		/*get the absolute location and considering the margin*/
		Point point = request.getLocation().getCopy().translate(getLayoutOrigin().getNegated());
		AbstractGridLayer layer = getGridLayer(part);
		translateToAbsolute(point, layer);

		/*need to check that it is not the f****ing bad location (negative,negative) 
		 * and we are not at the first creation (which is programmatic and so have (-1,-1)*/

		if((point.y < 0) && request.getLocation().x != -1 && request.getLocation().y != -1){
			return UnexecutableCommand.INSTANCE;
		}
		
		if((point.x < 0 || point.y < 0) && request.getLocation().x != -1 && request.getLocation().y != -1){
			canAdd = false ;
		}

		/*ugly way because the last request have a bad location (negative,negative)*/

		if (point.x >= 0) {
			Point newPoint = layer.getGridLayout().getConstraintFor(point);
			if(! request.getExtendedData().containsKey("convertOperation")){
				if(! layer.getGridLayout().canAddFigure(newPoint)){
					canAdd = false ;
					//return UnexecutableCommand.INSTANCE;
				}
			}
		}
		/* at the right of the grid */
		//		if( layer.getNumColumn()*layer.getGridLayout().getSizeX() < point.x){
		//			return UnexecutableCommand.INSTANCE;
		//		}

		/* at the bottom of the grid */
		//		if( layer.getNumLine()*layer.getGridLayout().getSizeY() < point.y){
		//			return UnexecutableCommand.INSTANCE;
		//		}

		Iterator<?> descriptors = request.getViewDescriptors().iterator();
		while (descriptors.hasNext()) {
			CreateViewRequest.ViewDescriptor descriptor = (CreateViewRequest.ViewDescriptor) descriptors.next();
			
			/*ugly way because the last request have a bad location (negative,negative)*/
			if (point.x < 0) {
				point.x = 0 ;
			}
		
			Point newPosition = layer.getGridLayout().getConstraintFor(point);
			Point insertPosition = layer.getGridLayout().getClosestInsertionPoint(newPosition).getPoint() ; 
			lineTemp = insertPosition.y;
			columnTemp = insertPosition.x;


			if (part instanceof FormEditPart) {
				if (lineTemp + 1 > layer.getNumLine()) {
					cc.compose(new AddRowCommand((FormEditPart) part, lineTemp));
				} else if (columnTemp + 1 > layer.getNumColumn()) {
					cc.compose(new AddColumnCommand((FormEditPart) part,
							columnTemp));
				} else if (columnTemp == 0 && !canAdd) {
					cc.compose(new AddColumnCommand((FormEditPart) part,
							columnTemp));
				}
			}
			CreateCommand createCommand = createGridLayoutCreateCommand(editingDomain, descriptor, (View) (getHost().getModel()),lineTemp, columnTemp);
			cc.compose(createCommand);
		}
		return new ICommandProxy(cc.reduce());
	}


	protected void translateToAbsolute(Point point, AbstractGridLayer layer) {
		FiguresHelper.translateToAbsolute(layer, point);
	}


	protected abstract CreateCommand createGridLayoutCreateCommand(
			TransactionalEditingDomain editingDomain,
			ViewDescriptor descriptor, View view, int line, int column);


	protected abstract AbstractGridLayer getGridLayer(IGraphicalEditPart part);


	/**
	 * Copy/paste from LayoutEditPolicy
	 * 
	 * Returns the layout's origin relative to the {@link
	 * LayoutEditPolicy#getLayoutContainer()}. In other words, what Point on the parent Figure
	 * does the LayoutManager use a reference when generating the child figure's bounds from
	 * the child's constraint.
	 * <P>
	 * By default, it is assumed that the layout manager positions children relative to the
	 * client area of the layout container. Thus, when processing Viewer-relative Points or
	 * Rectangles, the clientArea's location (top-left corner) will be subtracted from the
	 * Point/Rectangle, resulting in an offset from the LayoutOrigin.
	 * @return Point
	 */
	protected Point getLayoutOrigin() {
		return ((GraphicalEditPart)getHost()).getContentPane().getClientArea().getLocation();
	}

	/**
	 * Return null otherwise it will duplicate EditPart if the reparent implies to move from a TopNodeReference to a ChildReference
	 * for instance see Bonita bug 2584 
	 *  (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy#getReparentViewCommand(org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart)
	 */
	@Override
	protected ICommand getReparentViewCommand(IGraphicalEditPart gep) {
		return null;
	}

}
