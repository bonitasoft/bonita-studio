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

package org.bonitasoft.studio.diagram.custom.parts;

import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.diagram.custom.editPolicies.CompartmentChildCreationEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.CompartmentEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.CustomMainProcessItemSemanticEditPolicy;
import org.bonitasoft.studio.diagram.custom.editPolicies.CustomSnapFeedbackPolicy;
import org.bonitasoft.studio.diagram.custom.layout.CustomGravityConstrainedFlowLayout;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.MarginBorder;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.SnapToHelper;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.draw2d.ui.figures.GravityConstrainedFlowLayout;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Romain Bioteau
 *
 */
public class CustomMainProcessEditPart extends MainProcessEditPart {

	public CustomMainProcessEditPart(View view) {
		super(view);
	}


	@SuppressWarnings("rawtypes")
	@Override
	public Object getAdapter(Class key) {
		 
		if (key == SnapToHelper.class) {
	            return GMFTools.getSnapHelper(this);
	        }
		 
		return super.getAdapter(key);
	}
	
	/* (non-Javadoc)
	 * @see org.bonitasoft.studio.model.process.diagram.edit.parts.MainProcessEditPart#createDefaultEditPolicies()
	 */
	@Override
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();

		removeEditPolicy(EditPolicyRoles.SEMANTIC_ROLE);
		installEditPolicy(EditPolicyRoles.SEMANTIC_ROLE,
				new CustomMainProcessItemSemanticEditPolicy());
		removeEditPolicy(EditPolicyRoles.SNAP_FEEDBACK_ROLE);
		installEditPolicy(EditPolicyRoles.SNAP_FEEDBACK_ROLE,
				new CustomSnapFeedbackPolicy());
	
		removeEditPolicy(EditPolicy.LAYOUT_ROLE);
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new CompartmentEditPolicy(ProcessPackage.Literals.CONTAINER__ELEMENTS));
		removeEditPolicy(EditPolicyRoles.CREATION_ROLE);
		installEditPolicy(EditPolicyRoles.CREATION_ROLE, new CompartmentChildCreationEditPolicy());
	}

	protected IFigure createFigure() {
		IFigure l = super.createFigure() ;
		l.setBorder(new MarginBorder(30));

		GravityConstrainedFlowLayout lm = new CustomGravityConstrainedFlowLayout();
	
		lm.setVertical(true);
		lm.setStretchMajorAxis(false);
		lm.setStretchMinorAxis(false);

		lm.setMinorAlignment(GravityConstrainedFlowLayout.ALIGN_TOPLEFT);
		lm.setSpacing(20);
		l.setLayoutManager(lm);
		
		return l;
	}

}
