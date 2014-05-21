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

package org.bonitasoft.studio.diagram.form.custom.editpolicies;

import org.bonitasoft.studio.diagram.form.custom.commands.GridLayoutCreateCommandInForm;
import org.bonitasoft.studio.model.form.ViewForm;
import org.bonitasoft.studio.model.process.diagram.form.providers.ProcessElementTypes;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.CreateCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest.ViewDescriptor;
import org.eclipse.gmf.runtime.notation.View;


/**
 * @author Aurelien Pupier
 * 
 */
public class GridLayoutCreationEditPolicy extends AbstractGridLayoutCreationEditPolicy {

	@Override
	protected AbstractGridLayer getGridLayer(IGraphicalEditPart part) {
		return (AbstractGridLayer) part.getFigure();
	}

	@Override
	protected CreateCommand createGridLayoutCreateCommand(
			TransactionalEditingDomain editingDomain,
			ViewDescriptor descriptor, View view, int line, int column) {
		return new GridLayoutCreateCommandInForm(editingDomain, descriptor, (View) (getHost().getModel()),lineTemp, columnTemp);
	}

	@Override
	protected Command getCreateElementAndViewCommand(
			CreateViewAndElementRequest request) {
		if(ProcessElementTypes.SubmitFormButton_2126.getId().endsWith(request.getViewAndElementDescriptor().getSemanticHint()) ||
				ProcessElementTypes.SubmitFormButton_3116.getId().endsWith(request.getViewAndElementDescriptor().getSemanticHint())){
			if(((IGraphicalEditPart)getHost()).resolveSemanticElement() instanceof ViewForm){
				return UnexecutableCommand.INSTANCE ;
			}
		}
		return super.getCreateElementAndViewCommand(request);
	}

}
