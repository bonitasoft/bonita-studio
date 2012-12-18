/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.gmf.tools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.ui.internal.parts.PaletteToolTransferDropTargetListener;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramCommandStack;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.widgets.Display;

/**
 * @author Aurelien Pupier
 * Enable Selection of newly created elements on DnD
 */
public class PaletteToolTransferDropTargetListenerWithSelection extends	PaletteToolTransferDropTargetListener {

	public PaletteToolTransferDropTargetListenerWithSelection(EditPartViewer viewer) {
		super(viewer);
	}
	
	/**
	 * Overridden to select the created object.
	 * 
	 * @see org.eclipse.gef.dnd.AbstractTransferDropTargetListener#handleDrop()
	 * 
	 * /!\ don't call super, this method is copied from AbstractTransferDropTargetListener,
	 * and added a method to select added object (method copied from CreationTool)
	 * Please note that intermediate class are also trying to 
	 */
	protected void handleDrop() {
		updateTargetRequest();
		updateTargetEditPart();

		if (getTargetEditPart() != null) {
			Command command = getCommand();
			if (command != null && command.canExecute()){
				getViewer().getEditDomain().getCommandStack().execute(command);
				selectAddedObject(getViewer(),DiagramCommandStack.getReturnValues(command));
			} else {
				getCurrentEvent().detail = DND.DROP_NONE;
			}
		} else {
			getCurrentEvent().detail = DND.DROP_NONE;
		}
		
	}

	/**
	 * Select the newly added shape view by default
	 * @param viewer
	 * @param objects
	 * 
	 * Copied from CreationTool
	 */
	protected void selectAddedObject(EditPartViewer viewer, Collection objects) {
		final List editparts = new ArrayList();
		for (Iterator i = objects.iterator(); i.hasNext();) {
			Object object = i.next();
			if (object instanceof IAdaptable) {
				Object editPart =
					viewer.getEditPartRegistry().get(
						((IAdaptable)object).getAdapter(View.class));
				if (editPart != null)
					editparts.add(editPart);
			}
		}

		if (!editparts.isEmpty()) {
			viewer.setSelection(new StructuredSelection(editparts));
		
			// automatically put the first shape into edit-mode
			Display.getCurrent().asyncExec(new Runnable() {
				public void run(){
					EditPart editPart = (EditPart) editparts.get(0);
					//
					// add active test since test scripts are failing on this
					// basically, the editpart has been deleted when this 
					// code is being executed. (see RATLC00527114)
					if ( editPart.isActive() ) {
						editPart.performRequest(new Request(RequestConstants.REQ_DIRECT_EDIT));
						revealEditPart((EditPart)editparts.get(0));
					}
				}
			});
			
		}
	}
	
	/**
	 * Reveals the newly created editpart
	 * @param editPart
	 * Copied from CreationTool
	 */
	protected void revealEditPart(EditPart editPart){
		if ((editPart != null)&&
				(editPart.getViewer() != null))
				editPart.getViewer().reveal(editPart);
	}

}
