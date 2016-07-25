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

import org.bonitasoft.studio.diagram.form.custom.editpolicies.AbstractChangeSpanOnSelectionEditPolicy.SpanAction;
import org.bonitasoft.studio.model.form.FormPackage;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.form.WidgetLayoutInfo;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.Transaction;
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
public class ChangeSpanCommand extends AbstractTransactionalCommand {

	protected SpanAction typeChangeSpan;
	protected IGraphicalEditPart target;
	protected IGraphicalEditPart parentEditPart;
	protected CompositeCommand cc;

	/**
	 * @param target
	 * @param typeChangeSpan
	 */
	public ChangeSpanCommand(IGraphicalEditPart target, SpanAction typeChangeSpan) {
		super(target.getEditingDomain(), typeChangeSpan.toString(), getWorkspaceFiles(target.resolveSemanticElement()));
		this.typeChangeSpan = typeChangeSpan;
		this.target = target;
		this.parentEditPart = (IGraphicalEditPart) target.getParent();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
	 */
	@Override
	protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		cc = new CompositeCommand(getLabel());
		executeChangingSpanFor(typeChangeSpan, monitor, info);
		return CommandResult.newOKCommandResult();
	}
	
	/**
	 * @param info 
	 * @param monitor 
	 * @param typeChangeSpan2
	 * @throws ExecutionException 
	 */
	protected void executeChangingSpanFor(SpanAction typeChangeSpan1, IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		Widget widget = (Widget) target.resolveSemanticElement();
		WidgetLayoutInfo wl = widget.getWidgetLayoutInfo();
		if(typeChangeSpan1.equals(SpanAction.ADD_LEFT)){
			SetRequest setRequest1 = new SetRequest(getEditingDomain(), wl, FormPackage.eINSTANCE.getWidgetLayoutInfo_HorizontalSpan(), wl.getHorizontalSpan()+1);
			cc.add(new SetValueCommand(setRequest1));
			SetRequest setRequest2 = new SetRequest(getEditingDomain(), wl, FormPackage.eINSTANCE.getWidgetLayoutInfo_Column(), wl.getColumn()-1);
			cc.add(new SetValueCommand(setRequest2));
		} else if( typeChangeSpan1.equals(SpanAction.ADD_RIGHT)){
			SetRequest setRequest1 = new SetRequest(getEditingDomain(), wl, FormPackage.eINSTANCE.getWidgetLayoutInfo_HorizontalSpan(), wl.getHorizontalSpan()+1);
			cc.add(new SetValueCommand(setRequest1));
		} else if(typeChangeSpan1.equals(SpanAction.ADD_BOTTOM)){
			SetRequest setRequest1 = new SetRequest(getEditingDomain(), wl, FormPackage.eINSTANCE.getWidgetLayoutInfo_VerticalSpan(), wl.getVerticalSpan()+1);
			cc.add(new SetValueCommand(setRequest1));
		}else if(typeChangeSpan1.equals(SpanAction.ADD_TOP)){
			SetRequest setRequest1 = new SetRequest(getEditingDomain(), wl, FormPackage.eINSTANCE.getWidgetLayoutInfo_VerticalSpan(), wl.getVerticalSpan()+1);
			cc.add(new SetValueCommand(setRequest1));
			SetRequest setRequest2 = new SetRequest(getEditingDomain(), wl, FormPackage.eINSTANCE.getWidgetLayoutInfo_Line(), wl.getLine()-1);
			cc.add(new SetValueCommand(setRequest2));
		}else if(typeChangeSpan1.equals(SpanAction.REMOVE_LEFT)){
			composeCommandForRemoveLeft();
		}else if(typeChangeSpan1.equals(SpanAction.REMOVE_RIGHT)){
			SetRequest setRequest1 = new SetRequest(getEditingDomain(), wl, FormPackage.eINSTANCE.getWidgetLayoutInfo_HorizontalSpan(), wl.getHorizontalSpan()-1);
			cc.add(new SetValueCommand(setRequest1));
		}else if(typeChangeSpan1.equals(SpanAction.REMOVE_BOTTOM)){
			SetRequest setRequest1 = new SetRequest(getEditingDomain(), wl, FormPackage.eINSTANCE.getWidgetLayoutInfo_VerticalSpan(), wl.getVerticalSpan()-1);
			cc.add(new SetValueCommand(setRequest1));
		}else if(typeChangeSpan1.equals(SpanAction.REMOVE_TOP)){
			composeCommandForRemoveTop();

		}
		cc.execute(monitor, info);
		target.refresh();
		target.getTopGraphicEditPart().refresh();
	}

	protected void composeCommandForRemoveTop() {
		Widget widget = (Widget) target.resolveSemanticElement();
		WidgetLayoutInfo wl = widget.getWidgetLayoutInfo();
		SetRequest setRequest1 = new SetRequest(getEditingDomain(), wl, FormPackage.eINSTANCE.getWidgetLayoutInfo_VerticalSpan(), wl.getVerticalSpan()-1);
		cc.add(new SetValueCommand(setRequest1));
		SetRequest setRequest2 = new SetRequest(getEditingDomain(), wl, FormPackage.eINSTANCE.getWidgetLayoutInfo_Line(), wl.getLine()+1);
		cc.add(new SetValueCommand(setRequest2));	
	}

	protected void composeCommandForRemoveLeft() {
		Widget widget = (Widget) target.resolveSemanticElement();
		WidgetLayoutInfo wl = widget.getWidgetLayoutInfo();
		SetRequest setRequest1 = new SetRequest(getEditingDomain(), wl, FormPackage.eINSTANCE.getWidgetLayoutInfo_HorizontalSpan(), wl.getHorizontalSpan()-1);
		cc.add(new SetValueCommand(setRequest1));
		SetRequest setRequest2 = new SetRequest(getEditingDomain(), wl, FormPackage.eINSTANCE.getWidgetLayoutInfo_Column(), wl.getColumn()+1);
		cc.add(new SetValueCommand(setRequest2));
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
