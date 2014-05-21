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
package org.bonitasoft.studio.diagram.form.custom.commands;

import org.bonitasoft.studio.diagram.form.custom.i18n.Messages;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.diagram.form.edit.parts.FormEditPart;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;

/**
 * @author Aurelien Pupier
 *
 */
public class AddRowCommand extends AbstractTransactionalCommand {

	private FormEditPart formEditPart;
	private int row;
	protected CompositeCommand cc;
	
	/**
	 * @param formEditPart
	 * @param index
	 */
	public AddRowCommand(FormEditPart formEditPart, int row) {
		super(formEditPart.getEditingDomain(), Messages.addRowCommand, null, getWorkspaceFiles(formEditPart.resolveSemanticElement()));
		this.formEditPart = formEditPart;
		this.row = row;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		cc = new CompositeCommand(getLabel());
		Form form = (Form) formEditPart.resolveSemanticElement();

		//change numColumns
		//form.setNLine(form.getNLine()+1);
		SetRequest setRequest = new SetRequest(getEditingDomain(), form,  FormPackage.eINSTANCE.getForm_NLine(), form.getNLine()+1);
		cc.add(new SetValueCommand(setRequest));
		
		
		for(Object obj : formEditPart.getChildren()){
			if(obj instanceof GraphicalEditPart && ((GraphicalEditPart) obj).resolveSemanticElement() instanceof Widget){
				GraphicalEditPart widgetEditPart = (GraphicalEditPart) obj;
				Widget widget = (Widget) widgetEditPart.resolveSemanticElement();
				int oldRow = widget.getWidgetLayoutInfo().getLine();
				if (oldRow >= row) {//move element from one case to the right
					//widget.getWidgetLayoutInfo().setLine(oldRow+1);
					SetRequest setRequest1 = new SetRequest(getEditingDomain(), widget.getWidgetLayoutInfo(), FormPackage.eINSTANCE.getWidgetLayoutInfo_Line(), oldRow+1);			
					cc.add(new SetValueCommand(setRequest1));
					widgetEditPart.refresh();
				}
			}
		}
		
		cc.execute(monitor, info);
		for(Object obj : formEditPart.getChildren()){
			if(obj instanceof GraphicalEditPart){
				((GraphicalEditPart) obj).refresh();
			}
		}
		return CommandResult.newOKCommandResult();
	}

	
	@Override
	protected void didRedo(Transaction tx) {
		refresh();
		super.didRedo(tx);
	}
	
	@Override
	protected void didUndo(Transaction tx) {
		refresh();
		super.didUndo(tx);
	}
	
	protected void refresh() {
		/*need to refresh the element*/
		for(Object obj1 : formEditPart.getChildren()){
			if(obj1 instanceof GraphicalEditPart){
				((GraphicalEditPart) obj1).refresh();
			}
		}
	}

}
