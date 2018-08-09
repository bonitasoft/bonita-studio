/**
 * Copyright (C) 2018 Bonitasoft S.A.
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
package org.bonitasoft.studio.explorer.filters;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class FoldersFilter extends ViewerFilter {

    public static final String SETTINGS_FOLDER = ".settings";
    public static final String METAINF_FOLDER = "META-INF";
    public static final String TEMPLATE_FOLDER = "template";
    public static final String H2_FOLDER = "h2_database";
    public static final String TARGET_FOLDER = "target";
    public static final String XSD_FOLDER = "xsd";
    public static final String PROCESS_CONF_FOLDER = "process_configurations";
    public static final String DB_CONNECTOR_PROPERTIES_FOLDER = "database_connectors_properties";

    private List<String> foldersToFilter;

    public FoldersFilter() {
        foldersToFilter = new ArrayList<>();
        foldersToFilter.add(METAINF_FOLDER);
        foldersToFilter.add(SETTINGS_FOLDER);
        foldersToFilter.add(TEMPLATE_FOLDER);
        foldersToFilter.add(H2_FOLDER);
        foldersToFilter.add(TARGET_FOLDER);
        foldersToFilter.add(XSD_FOLDER);
        foldersToFilter.add(PROCESS_CONF_FOLDER);
        foldersToFilter.add(DB_CONNECTOR_PROPERTIES_FOLDER);
    }

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        if (element instanceof IFolder) {
            return !foldersToFilter.contains(((IFolder) element).getName());
        }
        return true;
    }

}
