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

package org.bonitasoft.studio.validation.constraints.process;

import java.util.List;

import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.SubmitFormButton;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.PageFlow;
import org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint;
import org.bonitasoft.studio.validation.i18n.Messages;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.osgi.util.NLS;

/**
 * @author Aurelie Zara
 *
 */
public class EndingPageflowConstraint extends
		AbstractLiveValidationMarkerConstraint {

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint#performLiveValidation(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	protected IStatus performLiveValidation(IValidationContext context) {
		return context.createSuccessStatus();
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint#performBatchValidation(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	protected IStatus performBatchValidation(IValidationContext context) {
		if (context.getTarget() instanceof PageFlow){
			PageFlow flow  = (PageFlow)context.getTarget();
			List<Form> forms = flow.getForm();
			for (Form form :forms){
				List<Widget> widgets =form.getWidgets();
				if (containsSubmitButton(widgets)){
					return context.createSuccessStatus();
				}
			}
			if (!forms.isEmpty()){
				return context.createFailureStatus(new Object[] { NLS.bind(Messages.validation_errorEndingPageflow,"") });
			}
		}
		return context.createSuccessStatus();
	}
	
	public boolean containsSubmitButton(List<Widget> widgets){
		for (Widget widget:widgets){
			if (widget instanceof SubmitFormButton){
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.validation.constraints.AbstractLiveValidationMarkerConstraint#getConstraintId()
	 */
	@Override
	protected String getConstraintId() {
		return "org.bonitasoft.studio.validation.endingPageflowConstraint";
	}

}
