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
package org.bonitasoft.studio.validation.constraints.process;

import org.bonitasoft.studio.model.process.InclusiveGateway;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessMarkerNavigationProvider;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.NotationPackage;

/**
 * @author Florine Boudin
 * 
 */
public class InclusiveGatewayConstraint extends AbstractLiveValidationMarkerConstraint {


    @Override
    protected IStatus performLiveValidation(IValidationContext ctx) {
        EStructuralFeature feature = ctx.getFeature();
        if(feature.equals(ProcessPackage.Literals.TARGET_ELEMENT__INCOMING)){
            final InclusiveGateway gate = (InclusiveGateway) ctx.getTarget();
            return validateInclusiveIncoming(ctx, gate);
        }else if(feature.equals(NotationPackage.Literals.VIEW__ELEMENT)){
            Object object = ctx.getFeatureNewValue();
            if(object instanceof InclusiveGateway){
                final InclusiveGateway gate = (InclusiveGateway) object;
                return validateInclusiveIncoming(ctx, gate);
            }
        }

        return ctx.createSuccessStatus();
    }

    @Override
    protected IStatus performBatchValidation(IValidationContext ctx) {
        EObject eObj = ctx.getTarget();
        if (eObj instanceof InclusiveGateway) {
        	InclusiveGateway gateway = (InclusiveGateway) eObj;
            return validateInclusiveIncoming(ctx, gateway);
        }

        return ctx.createSuccessStatus();
    }

    private IStatus validateInclusiveIncoming(IValidationContext ctx, InclusiveGateway gateway) {
        if(gateway.getIncoming().size() <= 1) {
            return ctx.createSuccessStatus();
        }else{
            	return ctx.createFailureStatus(new Object[] {Messages.Validation_NoMoreThan2IncomingInInclusiveGateway});
            }
    }

    @Override
    protected String getMarkerType(DiagramEditor editor) {
        return ProcessMarkerNavigationProvider.MARKER_TYPE;
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.inclusiveGateway";
    }



}
