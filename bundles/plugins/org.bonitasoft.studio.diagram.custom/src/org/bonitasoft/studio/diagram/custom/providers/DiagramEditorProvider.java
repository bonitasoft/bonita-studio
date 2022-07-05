/**
 * Copyright (C) 2009-2014 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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

package org.bonitasoft.studio.diagram.custom.providers;

import java.io.IOException;

import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.diagram.custom.parts.CustomProcessDiagramEditor;
import org.bonitasoft.studio.model.process.MainProcess;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.gmf.runtime.common.ui.services.editor.AbstractEditorProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.FileEditorInput;

/**
 * @author Aurelien Pupier
 *         Provider to be able to open form diagram via GMF EditorService.
 */
public class DiagramEditorProvider extends AbstractEditorProvider {

    @Override
    protected boolean canOpen(final IEditorInput editorInput) {
        return true;
    }

    @Override
    protected String getEditorId(final IEditorInput editorInput) {
        final URI uri = retrieveURI(editorInput);
        return isAProcessDiagramURI(uri) ? getID() : null;
    }

    protected String getID() {
        return CustomProcessDiagramEditor.ID;
    }

    protected boolean isAProcessDiagramURI(final URI uri) {
        final String fragment = uri.fragment();
        if (fragment == null) {
            return true;
        } else {
            final EObject eObject = retrieveElementInURIForFragment(uri, fragment);
            if (eObject instanceof Diagram) {
                if (((Diagram) eObject).getElement() instanceof MainProcess) {
                    return true;
                }
            }
        }

        return false;
    }

    private EObject retrieveElementInURIForFragment(final URI uri, final String fragment) {
        final ResourceSet resourceSet = new ResourceSetImpl();
        final Resource resource = resourceSet.getResource(uri, true);
        try {
            resource.load(null);
        } catch (final IOException e) {
            BonitaStudioLog.error(e);
        }
        final EObject eObject = resource.getEObject(fragment);
        return eObject;
    }

    protected URI retrieveURI(final IEditorInput editorInput) {
        if (editorInput instanceof URIEditorInput) {
            return ((URIEditorInput) editorInput).getURI();
        } else if (editorInput instanceof FileEditorInput) {
            return URI.createURI(((FileEditorInput) editorInput).getURI().toString());
        }
        return null;
    }

}
