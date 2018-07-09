/**
 * Copyright (C) 2010-2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.diagram.custom.handlers;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.handlers.DefaultPasteHandler;
import org.bonitasoft.studio.diagram.custom.clipboard.Clipboard;
import org.bonitasoft.studio.model.process.AbstractProcess;
import org.bonitasoft.studio.model.process.Lane;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.common.ui.action.actions.global.GlobalActionManager;
import org.eclipse.gmf.runtime.common.ui.action.global.GlobalAction;
import org.eclipse.gmf.runtime.common.ui.action.global.GlobalActionId;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeCompartmentEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.ContainerEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author Aurelien Pupier
 */
public class PasteHandler extends DefaultPasteHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();		
		GlobalAction pasteAction = GlobalActionManager.getInstance().createActionHandler(part, GlobalActionId.PASTE);
		if(pasteAction.isHandled() && pasteAction.isRunnable()){
			pasteAction.run() ;
			IStructuredSelection currentSelection = ((IStructuredSelection) part.getSite().getSelectionProvider().getSelection());
			EditPart selectedTargetEditPart = (EditPart) currentSelection.getFirstElement();
			EditPart comp = null ; 
			if(selectedTargetEditPart instanceof ShapeCompartmentEditPart){
				comp = selectedTargetEditPart ;
			}else{
				comp = (EditPart)selectedTargetEditPart.getChildren().get(1);
			}
			final EditPart compartment = comp ;
			EditPolicy ep = compartment.getEditPolicy(EditPolicy.LAYOUT_ROLE) ;
			EditPolicy containerep = compartment.getEditPolicy(EditPolicy.CONTAINER_ROLE) ;
			((ContainerEditPolicy)containerep).getCommand(new Request(RequestConstants.REQ_REFRESH)).execute() ;
			if(ep instanceof XYLayoutEditPolicy){
				ChangeBoundsRequest request = new ChangeBoundsRequest(RequestConstants.REQ_RESIZE_CHILDREN) ;
				List editparts = new ArrayList() ;
				for (Object c : compartment.getChildren()){
					if(c instanceof IGraphicalEditPart){

						editparts.add(c);
					}
				}
				request.setEditParts(editparts) ;
				((XYLayoutEditPolicy)ep).getCommand(request).execute() ;
				selectedTargetEditPart.getRoot().refresh() ;
			}
		}
		return null;
	}

	@Override
	public boolean isEnabled() {
		if (Clipboard.hasThingsToCopy()){
			IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			IStructuredSelection currentSelection = ((IStructuredSelection) part.getSite().getSelectionProvider().getSelection());
			Object selectedElem = currentSelection.getFirstElement();
			if(selectedElem instanceof IGraphicalEditPart){
				if(((IGraphicalEditPart) selectedElem).resolveSemanticElement() instanceof AbstractProcess){
					return true;
				}
				if(((IGraphicalEditPart) selectedElem).resolveSemanticElement() instanceof Lane){
					return true;
				}
			}
			return false;
		} else {
			return false;
		}
	}

}
