/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.connectors.ui.wizard;

import java.util.HashSet;
import java.util.Set;

import org.bonitasoft.studio.connector.model.definition.Category;
import org.bonitasoft.studio.connector.model.definition.ConnectorDefinition;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class DeprecatedConnectorViewerFilter extends ViewerFilter {

    private static final Set<String> DEPRECATED_DEFINITIONS = new HashSet<>();
    private static final Set<String> DEPRECATED_CATEGORIES =  new HashSet<>();
    static {
        DEPRECATED_DEFINITIONS.add("scripting-groovy");
        DEPRECATED_DEFINITIONS.add("AlfrescoUploadFileByPath");
        DEPRECATED_DEFINITIONS.add("AlfrescoCreateFolderByPath");
        DEPRECATED_DEFINITIONS.add("AlfrescoDeleteItemById");
        DEPRECATED_DEFINITIONS.add("AlfrescoDeleteFileByPath");
        
        DEPRECATED_CATEGORIES.add("Alfresco");
    }

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        if (element instanceof ConnectorDefinition) {
            return !DEPRECATED_DEFINITIONS.contains(((ConnectorDefinition) element).getId());
        }
        if(element instanceof Category) {
            return !DEPRECATED_CATEGORIES.contains(((Category) element).getId());
        }
        return true;
    }

}
