/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.constraints.process;

import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.MultiInstantiation;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessMarkerNavigationProvider;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;

/**
 * @author Florine Boudin
 *
 */
public class MultiInstanceConstraintInput extends AbstractLiveValidationMarkerConstraint {

	@Override
	protected IStatus performLiveValidation(IValidationContext context) {
		EStructuralFeature feature = context.getFeature();
		if(feature.equals(ProcessPackage.Literals.ACTIVITY)){
			final Activity mi = (Activity) context;
			if(mi.isIsMultiInstance()){
				validateMultiInstantiation(context, mi);
			}
		}	
		return context.createSuccessStatus();
	}

	
	private IStatus validateMultiInstantiation (IValidationContext ctx, Activity act){
		MultiInstantiation mult = act.getMultiInstantiation();
		if(mult!=null && !mult.isUseCardinality() && mult.getInputData() != null && mult.getCollectionDataToMultiInstantiate()==null){
        	return ctx.createFailureStatus(new Object[] {Messages.Validation_MultiInstantiationInputData});
		}
		
		return ctx.createSuccessStatus();
	}
	@Override
	protected IStatus performBatchValidation(IValidationContext context) {
		EObject eObj = context.getTarget();
		if (eObj instanceof Activity) {
			Activity act = (Activity) eObj;
			if(act.isIsMultiInstance()){
				return validateMultiInstantiation(context, act);
			}
		}
		return context.createSuccessStatus();
	}

    @Override
    protected String getMarkerType(DiagramEditor editor) {
        return ProcessMarkerNavigationProvider.MARKER_TYPE;
    }

    @Override
	protected String getConstraintId() {
		return "org.bonitasoft.studio.validation.multiInstanceInput";
	}

}
