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

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class FilesFilter extends ViewerFilter {

    public static final String CLASSPATH_FILE = ".classpath";
    public static final String PROJECT_FILE = ".project";
    public static final String BUILD_PROPERTIES_FILE = "build.properties";

    private List<String> filesToFilter;

    public FilesFilter() {
        filesToFilter = new ArrayList<>();
        filesToFilter.add(CLASSPATH_FILE);
        filesToFilter.add(PROJECT_FILE);
        filesToFilter.add(BUILD_PROPERTIES_FILE);
    }

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        if (element instanceof IFile) {
            return !filesToFilter.contains(((IFile) element).getName());
        }
        return true;
    }

}
