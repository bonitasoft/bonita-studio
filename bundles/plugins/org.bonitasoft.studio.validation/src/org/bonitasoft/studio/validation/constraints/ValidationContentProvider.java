/**
 * Copyright (C) 2013-2014 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.validation.constraints;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.common.editor.EditorUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.diagram.custom.repository.DiagramFileStore;
import org.bonitasoft.studio.diagram.custom.repository.DiagramRepositoryStore;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.eclipse.core.internal.resources.Marker;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IEditorInput;

/**
 * @author Florine Boudin
 *
 */
public class ValidationContentProvider implements IStructuredContentProvider{

    public ValidationContentProvider() {
    }

    @Override
    public void dispose() {
    }

    @Override
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
    }

    @Override
    public Object[] getElements(final Object inputElement) {
        if(inputElement instanceof DiagramEditor && !(inputElement instanceof FormDiagramEditor)){
            return getElementsForProcessDiagramEditor(inputElement);
        }else if(inputElement instanceof FormDiagramEditor){
            return getElementsForFormDiagramEditor(inputElement);
        }
        return new Object[0];
    }

    protected Object[] getElementsForFormDiagramEditor(final Object inputElement) {
        final IEditorInput input = ((FormDiagramEditor) inputElement).getEditorInput();
        if (input instanceof URIEditorInput) {
            final String file = ((URIEditorInput) input).getURI().toPlatformString(true);
            if (file != null) {
                final int idxSlash = file.lastIndexOf("/");
                final DiagramFileStore dfs = retrieveDiagramFileStore(file, idxSlash);
                if (dfs != null && dfs.getResource().exists()) {
                    try {
                        final List<IMarker> markerList = findMarkersForInput(input, dfs);
                        return markerList.toArray();
                    } catch (final CoreException e) {
                        BonitaStudioLog.error(e);
                        return new Object[0];
                    }
                }
            } else {
                BonitaStudioLog.log("File of FormDiagramEditor input not found." + ((FormDiagramEditor) inputElement).getTitle());
            }

            return new Object[0];
        } else {
            return new Object[0];
        }
    }

    protected Object[] getElementsForProcessDiagramEditor(final Object inputElement) {
        final IEditorInput input = ((DiagramEditor) inputElement).getEditorInput();
        final IResource file = EditorUtil.retrieveResourceFromEditorInput(input);
        if (file.exists()) {
            try {
                return file.findMarkers("org.bonitasoft.studio.diagram.diagnostic", false, IResource.DEPTH_INFINITE);
            } catch (final CoreException e) {
                BonitaStudioLog.error(e);
                return new Object[0];
            }
        } else {
            return new Object[0];
        }
    }

    protected DiagramFileStore retrieveDiagramFileStore(final String file, final int idxSlash) {
        final DiagramRepositoryStore store = RepositoryManager.getInstance().getRepositoryStore(DiagramRepositoryStore.class);
        DiagramFileStore dfs = null;
        if(idxSlash <0){
            dfs = store.getChild(file);
        }else{
            dfs = store.getChild(file.substring(idxSlash+1));
        }
        return dfs;
    }

    protected List<IMarker> findMarkersForInput(final IEditorInput input, final DiagramFileStore dfs) throws CoreException {
        final List<IMarker> markerList = new ArrayList<IMarker>();
        final IMarker[] markerTab = dfs.getResource().findMarkers("org.bonitasoft.studio.diagram.form.diagnostic", false, IResource.DEPTH_INFINITE);
        for(int i=0; i<markerTab.length; i++ ){
            final Marker m = (Marker)markerTab[i];
            final String location = (String) m.getAttribute("location");
            if(location.matches(".*::"+input.getName()+"::.*")){
                markerList.add(m);
            }
        }
        return markerList;
    }

}
