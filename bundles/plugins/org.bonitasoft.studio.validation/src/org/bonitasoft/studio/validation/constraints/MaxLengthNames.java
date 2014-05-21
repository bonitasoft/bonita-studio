/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.validation.constraints;

import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessMarkerNavigationProvider;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;

/**
 * @author Aurelien Pupier
 *
 */
public class MaxLengthNames extends AbstractLiveValidationMarkerConstraint {


	private static final int MAX_LENGTH_NAMES = 50;

	@Override
	protected IStatus performLiveValidation(IValidationContext ctx) {
		final EObject eObj = ctx.getTarget();
        Object newValue = ctx.getFeatureNewValue();
        if (eObj instanceof Activity) {
            if (newValue != null && ((String) newValue).length() > MAX_LENGTH_NAMES) {
                return ctx.createFailureStatus(new Object[] { eObj.eClass().getName() });
            }
        }
        return ctx.createSuccessStatus();
	}

	@Override
	protected IStatus performBatchValidation(IValidationContext ctx) {
		final EObject eObj = ctx.getTarget();
        final String name = ((Element) eObj).getName();
        if (eObj instanceof Activity) {
            if (name != null && name.length() > MAX_LENGTH_NAMES){
                return ctx.createFailureStatus(new Object[] { eObj.eClass().getName() });
            }
        }
        return ctx.createSuccessStatus();
	}

	@Override
	protected String getMarkerType(DiagramEditor editor) {
		 if(editor instanceof ProcessDiagramEditor){
	            return ProcessMarkerNavigationProvider.MARKER_TYPE;
	        }else if(editor instanceof FormDiagramEditor){
	            return org.bonitasoft.studio.model.process.diagram.form.providers.ProcessMarkerNavigationProvider.MARKER_TYPE;
	        }
	        return ProcessMarkerNavigationProvider.MARKER_TYPE;
	}

	@Override
	protected String getConstraintId() {
		return "org.bonitasoft.studio.validation.constraints.maxlengthnames";
	}


}
