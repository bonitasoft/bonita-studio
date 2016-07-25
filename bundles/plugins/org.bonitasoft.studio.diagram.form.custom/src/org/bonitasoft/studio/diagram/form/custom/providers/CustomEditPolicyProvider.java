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

package org.bonitasoft.studio.diagram.form.custom.providers;

import org.bonitasoft.studio.diagram.form.custom.editpolicies.AbstractChangeSpanOnSelectionEditPolicy;
import org.bonitasoft.studio.diagram.form.custom.editpolicies.ChangeSpanOnSelectionEditPolicy;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormEditPart;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ITextAwareEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.CreateEditPoliciesOperation;
import org.eclipse.gmf.runtime.diagram.ui.services.editpolicy.IEditPolicyProvider;

/**
 * @author Aurelien Pupier
 * 
 */
public class CustomEditPolicyProvider implements IEditPolicyProvider {

	public void createEditPolicies(EditPart editPart) {
		
		/*install editPolicy to allow modifications of span*/
		if (!(editPart instanceof ITextAwareEditPart)) {// DO NOT INSTALL EDIT
														// POLICIES ON LABELS
			if (((IGraphicalEditPart) editPart).resolveSemanticElement() instanceof Widget) {
				editPart.installEditPolicy(AbstractChangeSpanOnSelectionEditPolicy.CHANGE_SPAN_ON_SELECTION_FEEDBACK_ROLE,
						new ChangeSpanOnSelectionEditPolicy());
			}
		}

		/* Remove unwanted Editpolicy */
		editPart.removeEditPolicy(EditPolicyRoles.CONNECTION_HANDLES_ROLE);
		editPart.removeEditPolicy(EditPolicyRoles.POPUPBAR_ROLE);
	}

	public void addProviderChangeListener(IProviderChangeListener listener) {
	}

	public boolean provides(IOperation operation) {
		if (operation instanceof CreateEditPoliciesOperation && ((CreateEditPoliciesOperation)operation).getEditPart() instanceof GraphicalEditPart) {
			GraphicalEditPart editPart = (GraphicalEditPart) ((CreateEditPoliciesOperation)operation).getEditPart();
			if(editPart.getRoot().getContents() instanceof FormEditPart)
				return editPart.resolveSemanticElement() instanceof Element;
		}
		return false;
	}

	public void removeProviderChangeListener(IProviderChangeListener listener) {
	}

}
