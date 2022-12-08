/**
 * Copyright (C) 2014 Bonitasoft S.A.
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
package org.bonitasoft.studio.common.diagram.editor;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramEditorInput;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;

/**
 * @author Aurelien Pupier
 */
public class EditorUtil {

    public static Resource getDiagramResource(final ResourceSet resourceSet, final IEditorInput editorInput) {
        Resource diagramResource = null;
        if (editorInput instanceof URIEditorInput) {
            final URI resourceURI = ((URIEditorInput) editorInput).getURI().trimFragment();
            diagramResource = resourceSet.getResource(resourceURI, false);
        } else if (editorInput instanceof FileEditorInput) {
            final URI resourceURI = URI.createPlatformResourceURI(
                    ((FileEditorInput) editorInput).getFile().getFullPath().toString(), true);
            diagramResource = resourceSet.getResource(resourceURI, false);
        } else if (editorInput instanceof IDiagramEditorInput) {
            final Diagram diagram = ((IDiagramEditorInput) editorInput).getDiagram();
            diagramResource = diagram.eResource();
        }
        return diagramResource;
    }

    /**
     * @deprecated use {@link Adapters} instead
     */
    public static IResource retrieveResourceFromEditorInput(final IEditorInput editorInput) {
        return editorInput.getAdapter(IResource.class);
    }
}
