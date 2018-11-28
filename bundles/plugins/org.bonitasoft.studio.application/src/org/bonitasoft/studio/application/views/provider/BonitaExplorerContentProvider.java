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

import org.eclipse.jdt.internal.ui.navigator.JavaNavigatorContentProvider;

public class BonitaExplorerContentProvider extends JavaNavigatorContentProvider {

    @Override
    public boolean hasChildren(Object element) {
        if (UIDArtifactFilters.isUIDArtifact(element)) {
            return false;
        }
        return super.hasChildren(element);
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (UIDArtifactFilters.isUIDArtifact(parentElement)) {
            return new Object[0];
        }
        return super.getChildren(parentElement);
    }


}
