/**
 * Copyright (C) 2020 Bonitasoft S.A.
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
package org.bonitasoft.studio.connector.model.implementation.filter;

import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class CustomConnectorViewerFilter extends ViewerFilter {

    @Override
    public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
        if (element instanceof ConnectorDefinition) {
            final Resource eResource = ((ConnectorDefinition) element).eResource();
            if (eResource != null) {
                final IPath rootPath = ResourcesPlugin.getWorkspace().getRoot().getLocation();
                return rootPath.isPrefixOf(Path.fromOSString(eResource.getURI().toFileString()));
            }
        }
        return false;
    }

}
