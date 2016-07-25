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
package org.bonitasoft.studio.properties.sections.appearance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.bonitasoft.studio.common.emf.tools.ModelHelper;
import org.bonitasoft.studio.common.gmf.tools.GMFTools;
import org.bonitasoft.studio.model.process.Activity;
import org.bonitasoft.studio.model.process.Container;
import org.bonitasoft.studio.model.process.Element;
import org.bonitasoft.studio.model.process.MainProcess;
import org.bonitasoft.studio.model.process.ProcessPackage;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * @author Romain Bioteau
 *
 */
public class StyledObjectContentProvider implements IStructuredContentProvider {

	private DiagramEditPart diagramEp;


	public StyledObjectContentProvider(DiagramEditPart diagramEp){
		this.diagramEp = diagramEp ;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {

		if(inputElement instanceof Container){
			MainProcess mainProc = ModelHelper.getMainProcess((EObject) inputElement) ;
			if(mainProc != null){
				List<Container> containers = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.eINSTANCE.getContainer()) ;
				List<Container> toDelete = new ArrayList<Container>();
				for(Container c : containers){
					if(c.equals(inputElement) || c instanceof MainProcess){
						toDelete.add(c) ;
					}
				}
				containers.removeAll(toDelete) ;

				List<IGraphicalEditPart> result = getCorrespondingEditPart(containers) ;
				Collections.sort(result, new Comparator<IGraphicalEditPart>() {

					public int compare(IGraphicalEditPart ep1,
							IGraphicalEditPart ep2) {

						String label = ((Element)ep1.resolveSemanticElement()).getName();
						String label2 = ((Element)ep2.resolveSemanticElement()).getName();
						return (label != null?label:"").compareTo(label2!= null? label2:"");
					}

				}) ;
				return result.toArray(); 
			}
		}else if(inputElement instanceof Activity){
			MainProcess mainProc = ModelHelper.getMainProcess((EObject) inputElement) ;
			if(mainProc != null){
				List<Activity> activities = ModelHelper.getAllItemsOfType(mainProc, ProcessPackage.eINSTANCE.getActivity()) ;
				List<Activity> toDelete = new ArrayList<Activity>();
				for(Activity a : activities){
					if(a.equals(inputElement)){
						toDelete.add(a) ;
					}
				}
				activities.removeAll(toDelete) ;
				List<IGraphicalEditPart> result = getCorrespondingEditPart(activities) ;
				Collections.sort(result, new Comparator<IGraphicalEditPart>() {

					public int compare(IGraphicalEditPart ep1,
							IGraphicalEditPart ep2) {
						return ((Element)ep1.resolveSemanticElement()).getName().compareTo(((Element)ep2.resolveSemanticElement()).getName());
					}

				}) ;
				return result.toArray(); 
			}
		}
		return new Object[]{};
	}

	private List<IGraphicalEditPart> getCorrespondingEditPart(List<? extends EObject> eObjects) {
		List<IGraphicalEditPart> result = new ArrayList<IGraphicalEditPart>();
		for(EObject e : eObjects){
			if(e instanceof Element){
				IGraphicalEditPart ep = GMFTools.findEditPart(diagramEp,(Element) e) ; 
				if(ep != null){
					result.add(ep) ;
				}
			}

		}
		return result;
	}
}
