/**
 * Copyright (C) 2012 BonitaSoft S.A.
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
package org.bonitasoft.studio.condition.resources;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionStrategy;
import org.eclipse.xtext.util.IResourceScopeCache;

/**
 * @author Romain Bioteau
 */
public class InEditoProcResourceDescription extends ProcResourceDescription
        implements IResourceDescription {

    public InEditoProcResourceDescription(Resource resource, DefaultResourceDescriptionStrategy strategy,
            IResourceScopeCache cache) {
        super(resource, strategy, cache);
    }

    @Override
    public Resource getResource() {
        Resource resource = super.getResource();
        if (resource != null && PlatformUI.isWorkbenchRunning()
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null
                && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null) {
            final IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            if (part != null) {
                if (part instanceof DiagramEditor) {
                    final Resource activeResource = ((DiagramEditor) part).getDiagramEditPart().resolveSemanticElement()
                            .eResource();
                    if (activeResource != null && resource.getURI().equals(activeResource.getURI())) {
                        resource = activeResource;
                    }
                }
            }
        }
        return resource;
    }
}
