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
package org.bonitasoft.studio.application.views.provider;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class UIDArtifactFilters {

    public static ViewerFilter filterUIDArtifactChildren() {
        return new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                if (element instanceof IResource) {
                    IContainer parent = ((IResource) element).getParent();
                    if (parent != null) {
                        return parent.getParent() == null
                                || (parent.getParent() != null && (!isAUIDFolder(parent.getParent(), "web_page") &&
                                        !isAUIDFolder(parent.getParent(), "web_widgets") &&
                                        !isAUIDFolder(parent.getParent(), "web_fragments")));
                    }
                }
                return true;
            }

        };
    }

    public static boolean isUIDArtifact(Object element) {
        return isUIDArtifactFrom(element, "web_page")
                || isUIDArtifactFrom(element, "web_widgets")
                || isUIDArtifactFrom(element, "web_fragments");
    }

    public static boolean isUIDArtifactFrom(Object element, String folderName) {
        return element instanceof IFolder
                && ((IFolder) element).getParent() != null
                && Path.fromOSString(folderName).equals(((IFolder) element).getParent().getProjectRelativePath())
                && !((IFolder) element).getName().startsWith(".")
                && ((IFolder) element).getFile(((IFolder) element).getName() + ".json").exists();
    }

    private static boolean isAUIDFolder(Object element, String folderName) {
        return element instanceof IFolder
                && Path.fromOSString(folderName).equals(((IFolder) element).getProjectRelativePath());
    }

}
