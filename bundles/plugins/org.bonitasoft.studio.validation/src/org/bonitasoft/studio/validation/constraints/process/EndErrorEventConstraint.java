package org.bonitasoft.studio.validation.constraints.process;

import org.bonitasoft.studio.model.process.EndErrorEvent;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.gmf.runtime.notation.NotationPackage;
import org.eclipse.osgi.util.NLS;

public class EndErrorEventConstraint extends AbstractLiveValidationMarkerConstraint {

    @Override
    protected IStatus performLiveValidation(final IValidationContext ctx) {
        final EStructuralFeature feature = ctx.getFeature();
        if (feature.equals(ProcessPackage.Literals.ERROR_EVENT__ERROR_CODE)) {
            final String errorCode = (String) ctx.getFeatureNewValue();
            if (errorCode == null || errorCode.isEmpty()) {
                return ctx.createFailureStatus(new Object[] { NLS.bind(Messages.validation_errorEvent_codeNotSet, "") });
            }
        } else if (feature.equals(NotationPackage.Literals.VIEW__ELEMENT)) {
            final Object object = ctx.getFeatureNewValue();
            if (object instanceof EndErrorEvent) {
                final String errorCode = ((EndErrorEvent) object).getErrorCode();
                if (errorCode == null || errorCode.isEmpty()) {
                    return ctx.createFailureStatus(new Object[] { NLS.bind(Messages.validation_errorEvent_codeNotSet, "") });
                }
            }
        }

        return ctx.createSuccessStatus();
    }

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final EndErrorEvent endErrorEvent = (EndErrorEvent) ctx.getTarget();
        if (endErrorEvent.getErrorCode() == null || endErrorEvent.getErrorCode().isEmpty()) {
            return ctx.createFailureStatus(new Object[] { NLS.bind(Messages.validation_errorEvent_codeNotSet, endErrorEvent.getName()) });
        }
        return ctx.createSuccessStatus();
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.EndErrorEvent";
    }

}
