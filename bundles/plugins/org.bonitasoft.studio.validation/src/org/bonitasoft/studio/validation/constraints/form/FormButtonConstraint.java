/**
 * Copyright (C) 2009 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.Group;
import org.bonitasoft.studio.model.form.NextFormButton;
import org.bonitasoft.studio.model.form.SubmitFormButton;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @author Baptiste Mesta
 */
public class FormButtonConstraint extends AbstractLiveValidationMarkerConstraint {

    @Override
    protected IStatus performBatchValidation(final IValidationContext ctx) {
        final EObject eObj = ctx.getTarget();
        if (eObj instanceof Form) {
            final Form f = (Form) eObj;
            final EStructuralFeature eContainingFeature = f.eContainingFeature();
            if (isAnEntryForm(eContainingFeature)) {
                boolean formOk = false;
                for (final Widget w : f.getWidgets()) {
                    if (!formOk && containsOrIsButton(w)) {
                        formOk = true;
                    }
                }
                if (!formOk) {
                    return ctx.createFailureStatus(new Object[] { Messages.Validation_Form_NoButtons });
                }
            }
        }
        if (eObj instanceof SubmitFormButton) {
            final Form f = ModelHelper.getParentForm(eObj);
            final EStructuralFeature eContainingFeature = f.eContainingFeature();
            if (isAViewForm(eContainingFeature)) {
                return ctx.createFailureStatus(Messages.overviewFormCantHaveSubmitButton);
            }
        }
        return ctx.createSuccessStatus();
    }

    private boolean isAViewForm(final EStructuralFeature eContainingFeature) {
        return eContainingFeature != null && eContainingFeature.equals(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM)
                || eContainingFeature.equals(ProcessPackage.Literals.RECAP_FLOW__RECAP_FORMS);
    }

    private boolean isAnEntryForm(final EStructuralFeature eContainingFeature) {
        return eContainingFeature != null && !eContainingFeature.equals(ProcessPackage.Literals.VIEW_PAGE_FLOW__VIEW_FORM)
                && !eContainingFeature.equals(ProcessPackage.Literals.RECAP_FLOW__RECAP_FORMS);
    }

    private boolean containsOrIsButton(final Widget w) {
        if (w instanceof SubmitFormButton || w instanceof NextFormButton) {
            return true;
        } else if (w instanceof Group) {
            boolean formOk = false;
            for (final Widget child : ((Group) w).getWidgets()) {
                if (!formOk && containsOrIsButton(child)) {
                    formOk = true;
                }
            }
            return formOk;
        } else {
            return false;
        }
    }

    @Override
    protected String getConstraintId() {
        return "org.bonitasoft.studio.validation.constraints.SubmitOrNextButtonInAForm";
    }

}
