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

import org.eclipse.draw2d.FigureListener;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editpolicies.SelectionEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;

/**
 * @author Mickael Istria
 *
 */
public abstract class AbstractSingleSelectionEditPolicy extends SelectionEditPolicy {

	/**
	 * Listens to the owner figure being moved so the handles can be removed
	 * when this occurs.
	 */
	class OwnerMovedListener implements FigureListener {

		/**
		 * @see org.eclipse.draw2d.FigureListener#figureMoved(org.eclipse.draw2d.IFigure)
		 */
		public void figureMoved(IFigure source) {
			hideFeedback();
			showFeedback();
		}
	}
	
	protected OwnerMovedListener ownerMovedListener = new OwnerMovedListener();
	private int state = -1;

	/**
	 * 
	 */
	public AbstractSingleSelectionEditPolicy() {
		super();
	}

	@Override
	public void showSelection() {
		// NOTHING
	}

	/**
	 * @param host
	 * @return
	 */
	protected boolean isSingleSelection() {
		return getHost() != null && getHost().getViewer().getSelectedEditParts().size() == 1;
	}

	@Override
	public void setSelectedState(int type) {
		if (this.state == type) {
			return;
		}
		this.state = type;
		if (type == EditPart.SELECTED_PRIMARY && isSingleSelection()) {
			showPrimarySelection();
		} else {
			hideSelection();
		}
	}

	@Override
	public void hideSelection() {
		getHost().getFigure().removeFigureListener(ownerMovedListener);
		hideFeedback();
	}
	
	@Override
	public void showPrimarySelection() {
		if (!isSingleSelection()) {
			return;
		}
		
		
		IGraphicalEditPart host = getHost();
		if (isSingleSelection()) {
			host.getFigure().addFigureListener(ownerMovedListener);
			showFeedback();
		}
	}

	/**
	 * @return
	 */
	@Override
	public IGraphicalEditPart getHost() {
		return (IGraphicalEditPart) super.getHost();
	}
	
	protected abstract void showFeedback();
	protected abstract void hideFeedback();

}
