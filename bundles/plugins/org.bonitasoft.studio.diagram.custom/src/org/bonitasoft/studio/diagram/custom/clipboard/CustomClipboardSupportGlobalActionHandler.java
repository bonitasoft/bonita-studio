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

package org.bonitasoft.studio.diagram.custom.clipboard;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.model.process.Container;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.ui.action.global.GlobalActionId;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionContext;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.diagram.ui.providers.DiagramGlobalActionHandler;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

/**
 * @author Romain Bioteau
 *
 */
public class CustomClipboardSupportGlobalActionHandler extends DiagramGlobalActionHandler{

	public CustomClipboardSupportGlobalActionHandler() {
		super();
	}

	@Override
	public boolean canHandle(IGlobalActionContext cntxt) {

		/* Check if the active part is a IDiagramWorkbenchPart */
		IWorkbenchPart part = cntxt.getActivePart();
		if (!(part instanceof IDiagramWorkbenchPart)) {
			return false;
		}

		/* Check if the selection is a structured selection */
		if (!(cntxt.getSelection() instanceof IStructuredSelection)) {
			return false;
		}

		/* Check the action id */
		String actionId = cntxt.getActionId();
		if (actionId.equals(GlobalActionId.COPY)) {
			return canCopy(cntxt);
		} else if (actionId.equals(GlobalActionId.CUT)) {
			return canCut(cntxt);
		} else if (actionId.equals(GlobalActionId.PASTE)) {
			return canPaste(cntxt);
		}
		return false;
	}


	@Override
	protected org.eclipse.gmf.runtime.common.core.command.ICommand getCopyCommand(IGlobalActionContext cntxt,
			IDiagramWorkbenchPart diagramPart, final boolean isUndoable) {

		return new CustomCopyCommand("Copy", getSelectedEditParts(cntxt));
	}



	protected org.eclipse.gmf.runtime.common.core.command.ICommand getCutCommand(IGlobalActionContext cntxt,
			IDiagramWorkbenchPart diagramPart) {
		return new CustomCutCommand("Cut", getSelectedEditParts(cntxt));
	}


	@Override
	public org.eclipse.gmf.runtime.common.core.command.ICommand getCommand(IGlobalActionContext cntxt) {
		/* Check if the active part is a IDiagramWorkbenchPart */
		IWorkbenchPart part = cntxt.getActivePart();
		if (!(part instanceof IDiagramWorkbenchPart)) {
			return null;
		}

		/* Get the model operation context */
		IDiagramWorkbenchPart diagramPart = (IDiagramWorkbenchPart) part;

		/* Create a command */
		org.eclipse.gmf.runtime.common.core.command.ICommand command = null;

		/* Check the action id */
		String actionId = cntxt.getActionId();
		if (actionId.equals(GlobalActionId.DELETE)) {
			super.getCommand(cntxt);
		} else if (actionId.equals(GlobalActionId.COPY)) {
			command = getCopyCommand(cntxt, diagramPart, false);
		} else if (actionId.equals(GlobalActionId.CUT)) {
			command = getCutCommand(cntxt, diagramPart);
		} else if (actionId.equals(GlobalActionId.OPEN)) {
			super.getCommand(cntxt);
		} else if (actionId.equals(GlobalActionId.PASTE)) {
			command = getPasteCommand(cntxt, diagramPart);
		} else if (actionId.equals(GlobalActionId.SAVE)) {
			super.getCommand(cntxt);
		} else if (actionId.equals(GlobalActionId.PROPERTIES)) {
			super.getCommand(cntxt);
		}

		return command;

	}

	
	private org.eclipse.gmf.runtime.common.core.command.ICommand getPasteCommand(IGlobalActionContext cntxt, IDiagramWorkbenchPart diagramPart) {
		return new CustomPasteCommand("Paste", getSelectedEditParts(cntxt).get(0));

	}

	/**
	 * @return
	 */
	private List<IGraphicalEditPart> getSelectedEditParts(IGlobalActionContext cntxt) {
		List<IGraphicalEditPart> res = new ArrayList<IGraphicalEditPart>();
		IStructuredSelection selection = (IStructuredSelection)cntxt.getSelection();
		for (Object item : selection.toList()) {
			if (item instanceof IGraphicalEditPart) {
				res.add((IGraphicalEditPart)item);
			}
		}
		return res;
	}

	@Override
	protected boolean canPaste(IGlobalActionContext cntxt) {
		/* Check if the clipboard has data for the drawing surface */
		final List<IGraphicalEditPart> selectedEditParts = getSelectedEditParts(cntxt);
		return Clipboard.hasThingsToCopy() && allInSameContainer(selectedEditParts);
	}

	/**
	 * @param selectedEditParts
	 * @return
	 */
	private boolean allInSameContainer(List<IGraphicalEditPart> selectedEditParts) {
		Container commonContainer = null;
		for (IGraphicalEditPart part : selectedEditParts) {
			EObject semantic = part.resolveSemanticElement();
			Container container = getContainer(semantic);
			if (commonContainer == null) {
				commonContainer = container;
			} else if (!commonContainer.equals(container)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param semantic
	 * @return
	 */
	private Container getContainer(EObject semantic) {
		while (semantic != null && ! (semantic instanceof Container)) {
			semantic = semantic.eContainer();
		}
		return (Container)semantic;
	}

}
