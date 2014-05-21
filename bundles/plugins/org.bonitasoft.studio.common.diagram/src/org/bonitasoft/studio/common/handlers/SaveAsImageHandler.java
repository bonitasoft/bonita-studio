/**
 * Copyright (C) 2010 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.model.form.Form;
import org.bonitasoft.studio.model.form.Widget;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.Pool;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ShapeNodeEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.render.actions.CopyToImageAction;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

/**
 * @author Aurelien Pupier
 */
public class SaveAsImageHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		//Remove Selection
		DiagramEditor editor = (DiagramEditor) activePage.getActiveEditor() ;
		editor.getDiagramGraphicalViewer().setSelection(new StructuredSelection()) ;
	
		CopyToImageAction act = new CopyToImageAction(activePage){
			@Override
			protected List createOperationSet() {
				List<?> selection = getSelectedObjects();
				if (selection.isEmpty()
					|| !(selection.get(0) instanceof IGraphicalEditPart))
					return Collections.EMPTY_LIST;
				
				Iterator<?> selectedEPs = selection.iterator();
				List<EditPart> targetedEPs = new ArrayList<EditPart>();
				while (selectedEPs.hasNext()) {
					EditPart selectedEP = (EditPart) selectedEPs.next();
					if(targetedEPs.isEmpty()){
						EObject resolvedSemanticElement = ((IGraphicalEditPart) selectedEP).resolveSemanticElement();
						if(resolvedSemanticElement instanceof Pool
								|| resolvedSemanticElement instanceof MainProcess
								|| resolvedSemanticElement instanceof Form){
							/*We are on the top level element*/
							targetedEPs.add(selectedEP);
						} else {
							Element toSaveAsImage;
							if(resolvedSemanticElement instanceof Widget){// we are in a form diagram
								toSaveAsImage = ModelHelper.getForm((Widget) resolvedSemanticElement);					
							} else {//we are in a process diagram
								toSaveAsImage = ModelHelper.getParentProcess(resolvedSemanticElement) ;
							}
							EditPart parent = selectedEP.getParent() ;
							while(parent != null &&
									(!(parent instanceof ShapeNodeEditPart) 
											|| !((IGraphicalEditPart) parent).resolveSemanticElement().equals(toSaveAsImage))){
								parent = parent.getParent() ;
							}
							if(parent == null){
								targetedEPs.add(selectedEP);
							} else {
								targetedEPs.add(parent);
							}				
						}
					}
				}
				return targetedEPs.isEmpty() ? Collections.EMPTY_LIST
					: targetedEPs;
			}
		};
		act.init();
		act.run();
		return null;
	}

}
