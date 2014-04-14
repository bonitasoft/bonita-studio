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

import java.util.List;

import org.bonitasoft.studio.diagram.form.custom.i18n.Messages;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Widget;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.DestroyElementCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;

/**
 * Remove a column of the grid : remove elements that are in this column and up element that are on the right
 * 
 * @author Aurelien Pupier
 * 
 */
public class RemoveColumnCommand extends AbstractTransactionalCommand/*AbstractRemoveColumnCommand*/ {

	protected IGraphicalEditPart editPart;
	protected CompositeCommand cc;
	/*In BOS, this is always a Form but keep it as EObject in order to extends it easily*/
	protected EObject semanticElement;
	protected int column;


	/**
	 * @param editingDomain
	 * @param formEditPart
	 * @param column
	 */
	public RemoveColumnCommand(IGraphicalEditPart editPart, int column) {
		super(editPart.getEditingDomain(), Messages.RemoveColumnCommand, null, getWorkspaceFiles(editPart.resolveSemanticElement()));
		this.editPart = editPart;
		this.column = column;
		this.semanticElement = editPart.resolveSemanticElement();
	}
	
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
				cc = new CompositeCommand(getLabel());
			
				for(Widget widget : getChildren()){
					int oldColumn = widget.getWidgetLayoutInfo().getColumn();
					if (oldColumn == column) {
						if(widget.getWidgetLayoutInfo().getHorizontalSpan() > 1){//if have a "lot" of span, just decrement it.
							decrementSpan(widget);
						} else {//remove element in column
							DestroyElementRequest request = new DestroyElementRequest(getEditingDomain(), widget, false);
							cc.add(new DestroyElementCommand(request));
						}
					} else if (oldColumn > column) {//decrement column
						SetRequest setRequest = new SetRequest(getEditingDomain(), widget.getWidgetLayoutInfo(), FormPackage.eINSTANCE.getWidgetLayoutInfo_Column(), oldColumn-1);			
						cc.add(new SetValueCommand(setRequest));
					} else {/*oldColumn < column*/
						/*Take care of span,
						 * Remove one span if the removed row contains a part of the figure*/
						if(widget.getWidgetLayoutInfo().getHorizontalSpan()+oldColumn > column){
							decrementSpan(widget);
						}
			
					}
				}
				
				decrementColumnEditPart();
				
				cc.execute(monitor, info);
				
				refresh();
				
				return /*cc.getCommandResult();*/CommandResult.newOKCommandResult();
			}
	
	/**
	 * Add the setCommand to cc in order to decrement horizontal span from 1.
	 * @param widget
	 */
	protected void decrementSpan(Widget widget) {
		SetRequest setRequest = new SetRequest(getEditingDomain(), widget.getWidgetLayoutInfo(),  FormPackage.eINSTANCE.getWidgetLayoutInfo_HorizontalSpan(), widget.getWidgetLayoutInfo().getHorizontalSpan()-1);
		cc.add(new SetValueCommand(setRequest));
	}


	protected void refresh() {
		/*need to refresh the element*/
		for(Object obj1 : editPart.getChildren()){
			if(obj1 instanceof GraphicalEditPart){
				((GraphicalEditPart) obj1).refresh();
			}
		}
	}
	
	protected List<Widget> getChildren() {
		return ((Form)semanticElement).getWidgets();
	}

	protected void decrementColumnEditPart() {
		SetRequest setRequest = new SetRequest(getEditingDomain(), semanticElement, FormPackage.eINSTANCE.getForm_NColumn(), ((Form) semanticElement).getNColumn()-1);			
		cc.add(new SetValueCommand(setRequest));	
	}
	
	@Override
	protected void didRedo(Transaction tx) {
		super.didRedo(tx);
		refresh();
	}
	
	@Override
	protected void didUndo(Transaction tx) {
		super.didUndo(tx);
		refresh();
	}
	
	
}
