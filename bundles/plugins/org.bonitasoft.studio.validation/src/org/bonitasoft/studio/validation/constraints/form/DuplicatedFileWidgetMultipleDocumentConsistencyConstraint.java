/**
 * Copyright (C) 2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.validation.constraints.form;

import java.util.List;

import org.bonitasoft.studio.model.form.FileWidget;
import org.bonitasoft.studio.model.form.ImageWidget;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author aurelie
 */
public class DuplicatedFileWidgetMultipleDocumentConsistencyConstraint extends AbstractLiveValidationMarkerConstraint {

    /*
     * (non-Javadoc)
     * @see
     * org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint#performBatchValidation(org.eclipse.emf.validation.IValidationContext)
     */
    @Override
    protected IStatus performBatchValidation(final IValidationContext context) {
        final EObject target = context.getTarget();
        if (target instanceof FileWidget) {
            final FileWidget widget = (FileWidget) target;
            return getFileWidgetValidationStatus(context, widget);

        } else {
            if (target instanceof ImageWidget) {
                final ImageWidget widget = (ImageWidget) target;
                return getImageWidgetValidationStatus(context, widget);
            }

        }
        return context.createSuccessStatus();
    }

    /**
     * @param context
     * @param widget
     */
    private IStatus getFileWidgetValidationStatus(final IValidationContext context, final FileWidget widget) {
        if (widget.getInputExpression() != null) {
            final String inputExpressionType = widget.getInputExpression().getReturnType();
            if (List.class.getName().equals(inputExpressionType) && !widget.isDuplicate()) {
                return context.createFailureStatus(new Object[] { Messages.bind(Messages.DuplicatedFileWidgetMultipleDocumentConsistencyError,
                        widget.getName(), inputExpressionType) });
            }
            else {
                return context.createSuccessStatus();
            }
        }
        return context.createSuccessStatus();
    }

    private IStatus getImageWidgetValidationStatus(final IValidationContext context, final ImageWidget widget) {
        if (widget.getImgPath() != null) {
            final String inputExpressionType = widget.getImgPath().getReturnType();
            if (List.class.getName().equals(inputExpressionType) && !widget.isDuplicate()) {
                return context.createFailureStatus(new Object[] { Messages.DuplicatedFileWidgetMultipleDocumentConsistencyError });
            }
            else {
                return context.createSuccessStatus();
            }
        }
        return context.createSuccessStatus();
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint#getConstraintId()
     */
    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.DuplicatedFileWidgetMultipleDocumentConsistencyConstraint";
    }

}
