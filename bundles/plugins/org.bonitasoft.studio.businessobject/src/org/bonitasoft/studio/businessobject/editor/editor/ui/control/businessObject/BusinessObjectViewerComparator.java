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
package org.bonitasoft.studio.businessobject.editor.editor.ui.control.businessObject;

import org.bonitasoft.studio.businessobject.editor.model.BusinessObject;
import org.bonitasoft.studio.businessobject.editor.model.Package;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

public class BusinessObjectViewerComparator extends ViewerComparator {

    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        if (e1 instanceof Package && e2 instanceof Package) {
            return ((Package) e1).getName().compareTo(((Package) e2).getName());
        }
        if (e1 instanceof BusinessObject && e2 instanceof BusinessObject) {
            return ((BusinessObject) e1).getSimpleName().compareTo(((BusinessObject) e2).getSimpleName());
        }
        return super.compare(viewer, e1, e2);
    }

}
