package org.bonitasoft.studio.common.diagram.tools;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.SelectionManager;
import org.eclipse.gmf.runtime.diagram.ui.editparts.LabelEditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * @author Mickael Istria
 * This is a {@link SelectionManager} to use in Bonita editors to avoid multiple selection
 */
public class BonitaDiagramSelectionManager extends SelectionManager {
	public void appendSelection(EditPart editpart) {
		if(editpart instanceof LabelEditPart){
			super.appendSelection(editpart) ;
			return ;
		}
		
		//--> disable multiple selection
		deselectAll();
		if (editpart != getFocus())
			getViewer().setFocus(null);
		if (!getSelection().isEmpty()) {
			EditPart primary = (EditPart) ((StructuredSelection) getSelection()).toList().get((((StructuredSelection) getSelection()).size()) - 1);
			primary.setSelected(EditPart.SELECTED);
		}
		// if the editpart is already in the list, re-order it to be the last one
		try{
			((StructuredSelection) getSelection()).toList().remove(editpart);
		}catch (UnsupportedOperationException e) {
			
		}
		setSelection(new StructuredSelection(editpart));
		editpart.setSelected(EditPart.SELECTED_PRIMARY);
		fireSelectionChanged();
	}

	@Override
	public void setSelection(ISelection newSelection) {
		//--> disable multiple selection by selecting an area on diagram
		if (!(newSelection instanceof IStructuredSelection))
			return;
		
		IStructuredSelection selection = (IStructuredSelection)newSelection;
		if (selection.size() > 1) {
			setSelection(new StructuredSelection(selection.getFirstElement()));
		} else {
			super.setSelection(selection);
		}
		
	}
}