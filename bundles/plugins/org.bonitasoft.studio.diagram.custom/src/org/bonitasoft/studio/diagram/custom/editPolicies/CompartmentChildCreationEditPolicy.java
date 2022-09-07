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

package org.bonitasoft.studio.diagram.custom.editPolicies;

import java.util.Iterator;
import java.util.List;

import org.bonitasoft.studio.diagram.custom.commands.CompartmentChildCreateCommand;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.geometry.Transposer;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.DropRequest;
import org.eclipse.gmf.runtime.diagram.ui.commands.CreateCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CreationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Romain Bioteau
 *
 */
public class CompartmentChildCreationEditPolicy extends CreationEditPolicy {

	@Override
	protected Command getCreateCommand(CreateViewRequest request) {
		TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost())
		.getEditingDomain();
		CompositeTransactionalCommand cc = new CompositeTransactionalCommand(
				editingDomain, DiagramUIMessages.AddCommand_Label);

		Iterator<?> descriptors = request.getViewDescriptors().iterator();

		while (descriptors.hasNext()) {
			CreateViewRequest.ViewDescriptor descriptor =
				(CreateViewRequest.ViewDescriptor)descriptors.next();

			CreateCommand createCommand =
				new CompartmentChildCreateCommand(editingDomain,
						descriptor, 
						(View)(getHost().getModel()), getFeedbackIndexFor(request));

			cc.compose(createCommand);
		}
		return new ICommandProxy(cc.reduce());
	}


	protected int getFeedbackIndexFor(Request request) {
		List<?> children = getHost().getChildren();
		if (children.isEmpty())
			return -1;

		Transposer transposer = new Transposer();
		transposer.setEnabled (!isHorizontal());

		Point p = transposer.t(getLocationFromRequest(request));

		// Current row bottom, initialize to above the top.
		int rowBottom = Integer.MIN_VALUE;
		int candidate = -1;
		for (int i = 0; i < children.size(); i++) {
			EditPart child = (EditPart) children.get(i);
			Rectangle rect = transposer.t(getAbsoluteBounds(((GraphicalEditPart)child)));
			if (rect.y > rowBottom) {
				/*
				 * We are in a new row, so if we don't have a candidate but yet are within the
				 * previous row, then the current entry becomes the candidate. This is because
				 * we know we must be to the right of center of the last Figure in the
				 * previous row, so this Figure (which is at the start of a new row) is the
				 * candidate.
				 */
				if (p.y <= rowBottom) {
					if (candidate == -1)
						candidate = i;
					break;
				} else
					candidate = -1; // Mouse's Y is outside the row, so reset the candidate
			}
			rowBottom = Math.max(rowBottom, rect.bottom());
			if (candidate == -1) {
				/*
				 * See if we have a possible candidate. It is a candidate if the cursor is
				 * left of the center of this candidate.
				 */
				if (p.x <= rect.x + (rect.width / 2))
					candidate = i;
			}
			if (candidate != -1) {
				// We have a candidate, see if the rowBottom has grown to include the mouse Y.
				if (p.y <= rowBottom) {
					/*
					 * Now we have determined that the cursor.Y is above the bottom of the
					 * current row of figures. Stop now, to prevent the next row from being
					 * searched
					 */
					break;
				}
			}
		}
		return candidate;
	}

	protected boolean isHorizontal() {
		IFigure figure = ((GraphicalEditPart)getHost()).getContentPane();
		return ((ToolbarLayout)figure.getLayoutManager()).isHorizontal();
	}

	private Point getLocationFromRequest(Request request) {
		return ((DropRequest)request).getLocation();
	}

	private Rectangle getAbsoluteBounds(GraphicalEditPart ep) {
		Rectangle bounds = ep.getFigure().getBounds().getCopy();
		ep.getFigure().translateToAbsolute(bounds);
		return bounds;
	}
}