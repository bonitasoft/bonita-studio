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
package org.bonitasoft.studio.validation.constraints.process;

import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.BoundaryEvent;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessMarkerNavigationProvider;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;

/**
 * @author Baptiste Mesta
 * 
 */
public class UniqueBoundaryEventIdConstraint extends AbstractLiveValidationMarkerConstraint {


	@Override
	protected IStatus performLiveValidation(IValidationContext ctx) {
		return ctx.createSuccessStatus();
	}

	@Override
	protected IStatus performBatchValidation(IValidationContext ctx) {
		EObject eObj = ctx.getTarget();
		if(eObj instanceof BoundaryEvent){
			if(eObj.eContainer() instanceof Activity){
				Activity activity = (Activity)eObj.eContainer();
				String elementName = ((Element) eObj).getName();
				for(BoundaryEvent be :activity.getBoundaryIntermediateEvents()){
					if(!be.equals(eObj) && be.getName().equals(elementName)){
						return ctx.createFailureStatus(new Object[] { Messages.Validation_Element_SameName + ": " + elementName });
					}
				}
			}
		}
		return ctx.createSuccessStatus();
	}


	@Override
	protected String getMarkerType(DiagramEditor editor) {
		return ProcessMarkerNavigationProvider.MARKER_TYPE;
	}

	@Override
	protected String getConstraintId() {
		return "org.bonitasoft.studio.validation.constraints.uniqueboudaryid";
	}

}
