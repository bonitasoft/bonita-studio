/**
 * Copyright (C) 2013 BonitaSoft S.A.
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
package org.bonitasoft.studio.validation.constraints;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.eclipse.core.internal.resources.Marker;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;

/**
 * @author Florine Boudin
 *
 */
public class ValidationContentProvider implements IStructuredContentProvider{

	
	/**
	 * 
	 */
	public ValidationContentProvider() {
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub
	}

	@Override
	public Object[] getElements(Object inputElement) {
		Object[] noElements = new Object[0];
		if(inputElement instanceof DiagramEditor && !(inputElement instanceof FormDiagramEditor)){
			final IEditorInput input  = ((DiagramEditor) inputElement).getEditorInput();
			if(input instanceof FileEditorInput){
				IFile file = ((FileEditorInput) input).getFile();
				try {
					return file.findMarkers("org.bonitasoft.studio.diagram.diagnostic", false, IResource.DEPTH_INFINITE);
				} catch (CoreException e) {
					BonitaStudioLog.error(e);
					return noElements;
				}
			}
		}else if(inputElement instanceof FormDiagramEditor){
			final IEditorInput input  = ((FormDiagramEditor) inputElement).getEditorInput();
			if(input instanceof URIEditorInput){
				String file = ((URIEditorInput) input).getURI().toPlatformString(true);
				int idxSlash = file.lastIndexOf("/");
				DiagramRepositoryStore store = (DiagramRepositoryStore) RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
				DiagramFileStore dfs = null;
				if(idxSlash <0){
					dfs = store.getChild(file);
				}else{
					dfs = store.getChild(file.substring(idxSlash+1));
				}
				if(dfs!=null){
					try {

						List<IMarker> markerList = new ArrayList<IMarker>();
						IMarker[] markerTab = (IMarker[]) dfs.getResource().findMarkers("org.bonitasoft.studio.diagram.form.diagnostic", false, IResource.DEPTH_INFINITE);
						for(int i=0; i<markerTab.length; i++ ){
							Marker m = (Marker)markerTab[i];
							String location = (String) m.getAttribute("location");
							if(location.matches(".*::"+input.getName()+"::.*")){
								markerList.add(m);
							}
						}
						return markerList.toArray();
					
					} catch (CoreException e) {
						BonitaStudioLog.error(e);
						return noElements;
					}
				}

				return noElements;
			}

		}
		return noElements;
	}

	

}
