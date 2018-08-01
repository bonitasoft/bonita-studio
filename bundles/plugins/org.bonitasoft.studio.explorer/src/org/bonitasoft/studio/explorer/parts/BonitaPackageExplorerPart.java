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
package org.bonitasoft.studio.explorer.parts;

import java.util.Objects;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.internal.ui.packageview.PackageExplorerPart;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Composite;

@SuppressWarnings("restriction")
public class BonitaPackageExplorerPart extends PackageExplorerPart {

    public static final String VIEW_ID = "org.bonitasoft.studio.explorer";

    @Override
    public void createPartControl(Composite parent) {
        super.createPartControl(parent);
        IProject currentProject = RepositoryManager.getInstance().getCurrentRepository().getProject();

        TreeViewer treeViewer = getTreeViewer();
        treeViewer.addFilter(onlyCurrentProject(currentProject));

        // TODO: filter unwanted folder, add popup menu filter ect
    }

    private ViewerFilter onlyCurrentProject(IProject currentProject) {
        return new ViewerFilter() {

            @Override
            public boolean select(Viewer viewer, Object parentElement, Object element) {
                if (element instanceof IProject) {
                    IProject project = (IProject) element;
                    return Objects.equals(currentProject, project);
                }
                return true;
            }
        };
    }
}
