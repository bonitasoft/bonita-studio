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
package org.bonitasoft.studio.common.diagram.tools;

import java.util.List;

import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.requests.CreateRequest;

/**
 * @author Mickael Istria
 * @author Aurelien Pupier : improve resource management (free handles)
 */
public class BonitaUnspecifiedTypeProcessCreationTool extends BonitaUnspecifiedTypeCreationTool  {



	/**
	 * @param elementTypes
	 */
	public BonitaUnspecifiedTypeProcessCreationTool(List<?> elementTypes) {
		super(elementTypes);
	}



	@Override
	protected boolean handleDrag() {
		if (applies()) {
			updateTargetUnderMouse();
			updateTargetRequest();
			redrawFeedback();
		}
		return super.handleDrag();
	}


	protected void updateTargetRequest() {
		super.updateTargetRequest();
		CreateRequest req = getCreateRequest();
		req.getExtendedData().clear();
		snapPoint(req);

		if (isInState(STATE_DRAG_IN_PROGRESS) && eClass.equals(ProcessPackage.eINSTANCE.getPool())) { 
			Point loq = getStartLocation();
			Rectangle bounds = new Rectangle(loq, loq);
			bounds.union(loq.getTranslated(getDragMoveDelta()));
			req.setSize(bounds.getSize());
			req.setLocation(bounds.getLocation());
			req.getExtendedData().clear();
			if (!getCurrentInput().isAltKeyDown() && helper != null) {
				PrecisionRectangle baseRect = new PrecisionRectangle(bounds);
				PrecisionRectangle result = baseRect.getPreciseCopy();
				helper.snapRectangle(req, PositionConstants.NSEW, baseRect, result);
			}
			
		} else if (isInState(STATE_DRAG_IN_PROGRESS)) {// avoid resize on drag
			Point loq = getStartLocation();
			Rectangle bounds = new Rectangle(loq, loq);
			bounds.union(loq.getTranslated(getDragMoveDelta()));
			req.getExtendedData().clear();
			if (!getCurrentInput().isAltKeyDown() && helper != null) {
				PrecisionRectangle baseRect = new PrecisionRectangle(bounds);
				PrecisionRectangle result = baseRect.getPreciseCopy();
				helper.snapRectangle(req, PositionConstants.NSEW, baseRect, result);
			}
			req.setLocation(getLocation());
			
		} else {
			req.setSize(null);
		}

		
	}

}
