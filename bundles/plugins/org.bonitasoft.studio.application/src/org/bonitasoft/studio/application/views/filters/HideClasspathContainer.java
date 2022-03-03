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
package org.bonitasoft.studio.application.views.filters;

import java.util.Objects;

import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.internal.ui.packageview.ClassPathContainer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class HideClasspathContainer extends ViewerFilter {

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        if (RepositoryManager.getInstance().hasActiveRepository()
                && RepositoryManager.getInstance().getCurrentRepository().isLoaded()) {
            IJavaProject javaProject = RepositoryManager.getInstance().getCurrentRepository().getJavaProject();
            if (element instanceof ClassPathContainer) {
                return !(Objects.equals(((ClassPathContainer) element).getJavaProject(), javaProject));
            }
        }
        return true;
    }

}
