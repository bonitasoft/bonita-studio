/**
 * Copyright (C) 2013-2015 Bonitasoft S.A.
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
package org.bonitasoft.studio.validation.ui.view;

import org.bonitasoft.studio.common.editor.EditorUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessMarkerNavigationProvider;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

public class ValidationMarkerContentProvider extends ArrayContentProvider {

    @Override
    public Object[] getElements(final Object inputElement) {
        try {
            if (inputElement instanceof IEditorPart) {
                final IEditorInput editorInput = ((IEditorPart) inputElement).getEditorInput();
                final IResource resource = EditorUtil.retrieveResourceFromEditorInput(editorInput);
                if (isProcessEditor(inputElement)) {
                    return processMarkers(resource);
                }
            }
        } catch (final CoreException e) {
            BonitaStudioLog.error("Failed to retrieve validation markers", e);
        }
        return new Object[0];
    }

    private boolean isProcessEditor(final Object inputElement) {
        return inputElement instanceof ProcessDiagramEditor;
    }

    private Object[] processMarkers(final IResource resource) throws CoreException {
        if (resource != null && resource.exists()) {
            return resource.findMarkers(ProcessMarkerNavigationProvider.MARKER_TYPE, false, IResource.DEPTH_INFINITE);
        }
        return new Object[0];
    }


}
