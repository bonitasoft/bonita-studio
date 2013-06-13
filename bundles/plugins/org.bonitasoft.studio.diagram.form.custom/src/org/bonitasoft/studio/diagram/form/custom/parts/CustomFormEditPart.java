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
package org.bonitasoft.studio.diagram.form.custom.parts;

import org.bonitasoft.studio.diagram.form.custom.editpolicies.GridLayer;
import org.bonitasoft.studio.diagram.form.custom.editpolicies.GridLayoutCreationEditPolicy;
import org.bonitasoft.studio.diagram.form.custom.editpolicies.GridLayoutEditPolicy;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormEditPart;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @author Aurelien Pupier
 * 
 */
public class CustomFormEditPart extends FormEditPart {

	/**
	 * @param view
	 */
	public CustomFormEditPart(View view) {
		super(view);
	}

	/**
	 * Change policies in order to have feedback and can reorder.
	 * 
	 * @see
	 * org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormEditPart
	 * #createDefaultEditPolicies()
	 */
	protected void createDefaultEditPolicies() {
		super.createDefaultEditPolicies();
		/* feedback on the move and layout */
		removeEditPolicy(EditPolicy.LAYOUT_ROLE);
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new GridLayoutEditPolicy()/*new GridLayerLayoutEditPolicy()*//*new GridLayoutEditPolicy2()*/);
//		installEditPolicy(EditPolicy.LAYOUT_ROLE,
//				new CustomConstrainedToolbarLayoutEditPolicy(
//						FormPackage.Literals.FORM__WIDGETS));
		/* set target feedback on creation by the palette */
		removeEditPolicy(EditPolicyRoles.CREATION_ROLE);
		installEditPolicy(EditPolicyRoles.CREATION_ROLE,
				new GridLayoutCreationEditPolicy());
//		/* allow only vertical move */
//		removeEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE);
//		installEditPolicy(EditPolicyRoles.DRAG_DROP_ROLE, new CustomDiagramDragDropEditPolicy());
	}

	
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart#createFigure()
	 */
	@Override
	protected IFigure createFigure() {		
		/*Try with gmf GridLayout but send clascastexception (rectangle into GridData...???)*/
//		GridLayout manager = new GridLayout();
//		manager.makeColumnsEqualWidth = true;
//		manager.numColumns = 2;
//		GridData gridData = new GridData();
//		manager.setConstraint(figure, gridData);
//		layer.setLayoutManager(manager);
		
		/*Try with gmf GridLayerEx*/
//		GridLayerEx layerEx = new GridLayerEx();
//		layerEx.setSpacing(new Dimension(300,100));
		
		/*Use our custom GridFreeFormLayer*/
		GridLayer layer = new GridLayer(this);
		return layer;
	}
		
	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart#refresh()
	 */
//	@Override
//	public void refresh() {
//		IFigure fig = getFigure();
//		Rectangle bounds = getFigure().getBounds().getCopy();
//		bounds.setSize(800, bounds.height);
//		getFigure().setBounds(bounds);
//		//getFigure().repaint();
//		super.refresh();
//	}
	
}
