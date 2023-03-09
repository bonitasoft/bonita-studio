/**
 * Copyright (C) 2015 Bonitasoft S.A.
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.bonitasoft.studio.model.process.diagram.part.ProcessDiagramEditor;
import org.bonitasoft.studio.model.process.diagram.providers.ProcessMarkerNavigationProvider;
import org.bonitasoft.studio.validation.ModelFileCompatibilityValidator;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.FileEditorInput;
import org.junit.Test;

public class ValidationMarkerContentProviderTest {

    @Test
    public void should_find_no_process_markers_for_null_input() throws Exception {
        final ValidationMarkerContentProvider validationMarkerContentProvider = createContentProvider();

        final Object[] markers = validationMarkerContentProvider.getElements(null);

        assertThat(markers).isEmpty();
    }

    @Test
    public void should_find_process_markers_in_process_diagram_editor_input() throws Exception {
        final ValidationMarkerContentProvider validationMarkerContentProvider = createContentProvider();

        final IMarker errorMarker = mock(IMarker.class);
        final IMarker warningMarker = mock(IMarker.class);
        final Object[] markers = validationMarkerContentProvider.getElements(processDiagramEditorWithMarkers(errorMarker, warningMarker));

        assertThat(markers).containsExactly(errorMarker, warningMarker);
    }


    private IEditorPart processDiagramEditorWithMarkers(final IMarker... markers) throws CoreException {
        final ProcessDiagramEditor processDiagramEditor = mock(ProcessDiagramEditor.class);
        final FileEditorInput editorInput = mock(FileEditorInput.class);
        final IResource processFile = mock(IResource.class);
        when(processFile.exists()).thenReturn(true);
        when(processFile.findMarkers(ProcessMarkerNavigationProvider.MARKER_TYPE, false, IResource.DEPTH_INFINITE)).thenReturn(markers);
        when(processFile.findMarkers(ModelFileCompatibilityValidator.MODEL_VERSION_MARKER_TYPE, false, IResource.DEPTH_INFINITE)).thenReturn(new IMarker[0]);
        when(editorInput.getAdapter(IResource.class)).thenReturn(processFile);
        when(processDiagramEditor.getEditorInput()).thenReturn(editorInput);
        return processDiagramEditor;
    }


    private ValidationMarkerContentProvider createContentProvider() {
        return new ValidationMarkerContentProvider();
    }
}
