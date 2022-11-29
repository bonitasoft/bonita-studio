/**
 * Copyright (C) 2022 BonitaSoft S.A.
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
package org.bonitasoft.studio.application.views.filters;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.MultiModuleProject;
import org.eclipse.jface.viewers.Viewer;

public class HideFolderWhenProjectIsShownAsNested
        extends org.eclipse.ui.internal.navigator.resources.nested.HideFolderWhenProjectIsShownAsNested {

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        var currentProject = RepositoryManager.getInstance().getCurrentProject();
        if (currentProject.isPresent()) {
            var project = currentProject.get();
            if (project instanceof MultiModuleProject) {
                var multiModuleProject = (MultiModuleProject) project;
                var appProject = multiModuleProject.getAppProject();
                var bdmFolder = appProject.getFolder("bdm");
                if (bdmFolder.exists() && element.equals(bdmFolder)) {
                    return true;
                }
                // Hide generated submodules in Project explorer
                if(multiModuleProject.getBdmModelProject().equals(element)) {
                    return false;
                }
                if(multiModuleProject.getBdmDaoClientProject().equals(element)) {
                    return false;
                }
            }
        }
        return super.select(viewer, parentElement, element);
    }

}
