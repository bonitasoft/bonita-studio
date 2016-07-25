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

import static com.google.common.collect.Iterables.filter;
import static com.google.common.collect.Iterables.toArray;
import static com.google.common.collect.Lists.newArrayList;

import org.bonitasoft.studio.common.editor.EditorUtil;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.model.process.diagram.form.part.FormDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessMarkerNavigationProvider;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

import com.google.common.base.Predicate;

public class ValidationMarkerContentProvider extends ArrayContentProvider {

    @Override
    public Object[] getElements(final Object inputElement) {
        try {
            if (inputElement instanceof IEditorPart) {
                final IEditorInput editorInput = ((IEditorPart) inputElement).getEditorInput();
                final IResource resource = EditorUtil.retrieveResourceFromEditorInput(editorInput);
                if (isProcessEditor(inputElement)) {
                    return processMarkers(resource);
                } else if (isFormEditor(inputElement)) {
                    return formsMarkers(editorInput, resource);
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

    private boolean isFormEditor(final Object inputElement) {
        return inputElement instanceof FormDiagramEditor;
    }

    private Object[] formsMarkers(final IEditorInput editorInput, final IResource resource) throws CoreException {
        if (resource != null && resource.exists()) {
            return findFormMarkers(editorInput, resource);
        }
        return new Object[0];
    }

    private Object[] processMarkers(final IResource resource) throws CoreException {
        if (resource != null && resource.exists()) {
            return resource.findMarkers(ProcessMarkerNavigationProvider.MARKER_TYPE, false, IResource.DEPTH_INFINITE);
        }
        return new Object[0];
    }

    private IMarker[] findFormMarkers(final IEditorInput input, final IResource file) throws CoreException {
        final IMarker[] markerTab = file.findMarkers(
                org.bonitasoft.studio.model.process.diagram.form.providers.ProcessMarkerNavigationProvider.MARKER_TYPE, false, IResource.DEPTH_INFINITE);
        return toArray(filter(newArrayList(markerTab), withMarkerLocationMatching(input.getName())), IMarker.class);
    }

    private Predicate<IMarker> withMarkerLocationMatching(final String locationToMatch) {
        return new Predicate<IMarker>() {

            @Override
            public boolean apply(final IMarker marker) {
                try {
                    final String location = (String) marker.getAttribute("location");
                    return location != null && location.matches(".*::" + locationToMatch + "::.*");
                } catch (final CoreException e) {
                    return false;
                }
            }

        };
    }
}
