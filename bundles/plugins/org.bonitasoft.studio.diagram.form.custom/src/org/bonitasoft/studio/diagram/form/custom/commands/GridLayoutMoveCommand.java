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
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.form.WidgetLayoutInfo;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.commands.SetValueCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.SetRequest;

/**
 * @author Aurelien Pupier
 *
 */
public class GridLayoutMoveCommand extends AbstractTransactionalCommand/*implements org.eclipse.emf.common.command.Command*/ {

	private IGraphicalEditPart target;
	private IGraphicalEditPart container;
	private Point newPosition;
	private IGraphicalEditPart parentEditPart;
	protected CompositeCommand cc;

	/**
	 * @param domain
	 * @param label
	 * @param affectedFiles
	 */
	public GridLayoutMoveCommand(TransactionalEditingDomain domain, IGraphicalEditPart child, IGraphicalEditPart container, Point newPosition) {
		super(domain, Messages.gridLayoutMoveCommand, getWorkspaceFiles(container.resolveSemanticElement()));		
		this.target = child;
		this.container = container;
		this.newPosition = newPosition;
		this.parentEditPart = (IGraphicalEditPart) target.getParent();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		cc = new CompositeCommand(getLabel());
		WidgetLayoutInfo layoutInfo = ((Widget) target.resolveSemanticElement()).getWidgetLayoutInfo();
		
		SetRequest setRequest1 = new SetRequest(getEditingDomain(), layoutInfo, FormPackage.eINSTANCE.getWidgetLayoutInfo_Column(), newPosition.x);
		cc.add(new SetValueCommand(setRequest1));
		SetRequest setRequest2 = new SetRequest(getEditingDomain(), layoutInfo, FormPackage.eINSTANCE.getWidgetLayoutInfo_Line(), newPosition.y);
		cc.add(new SetValueCommand(setRequest2));
		
		cc.execute(monitor, info);
			
		target.refresh();
		container.refresh();
		return cc.getCommandResult();//CommandResult.newOKCommandResult();
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
	
	protected void refresh() {
		/*need to refresh the element*/
		for(Object obj1 : parentEditPart.getChildren()){
			if(obj1 instanceof GraphicalEditPart){
				((GraphicalEditPart) obj1).refresh();
			}
		}
	}
}
