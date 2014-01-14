/**
 * Copyright (C) 2011 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.gmf.tools.tree;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.model.process.Element;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PlatformUI;

/**
 * @author Romain Bioteau
 *
 */
public class BonitaFormTreeViewer extends BonitaTreeViewer implements ISelectionProvider{

	@Override
	protected void handleTreeSelection(Tree tree) {
		TreeItem[] ties = tree.getSelection();
		List<Object> newSelection = new ArrayList<Object>() ;
		for (int i = 0; i < ties.length; i++){
			if(ties[i].getData() instanceof EObject){
				IGraphicalEditPart ep = findEditPartFor((EObject) ties[i].getData());
				if(ep != null){
					newSelection.add(ep) ;
				}
			}
		}

		diagramEditPart.getViewer().deselectAll()  ;
		if(!newSelection.isEmpty()){
			DiagramEditor editor = (DiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor() ;
			editor.getDiagramGraphicalViewer().select(((IGraphicalEditPart)newSelection.get(0))) ;
			BonitaFormTreeSelectionProvider.getInstance().fireSelectionChanged(((IGraphicalEditPart)newSelection.get(0)),(EObject) ties[0].getData()) ;
			scrollDiagram(((IGraphicalEditPart)newSelection.get(0))) ;
		}

	}


	protected void handlTreeDoubleClick() {
		//NOTHING TO DO
	}


	@Override
	protected IGraphicalEditPart findEditPartFor(EObject elem) {
		IGraphicalEditPart ep = null;
		if(elem instanceof Element){
			ep = GMFTools.findEditPart(diagramEditPart, (Element) elem)  ;
		}else{
			while(elem != null && !(elem instanceof Element)){
				elem = elem.eContainer() ;
			}
			if(elem == null){
				return null;
			}else{
				ep = GMFTools.findEditPart(diagramEditPart, (Element) elem)  ;
			}
		}
		while(ep == null) {
			if(elem.eContainer() == null){
				break ;
			}
			elem =elem.eContainer() ;
			if( elem instanceof Element){
				ep = GMFTools.findEditPart(diagramEditPart,(Element) elem) ;
			}
		}
		return ep ;
	}

}
