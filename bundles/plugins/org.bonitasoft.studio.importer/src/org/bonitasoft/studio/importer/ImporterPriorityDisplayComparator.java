/**
 * Copyright (C) 2011 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.importer;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

/**
 * @author Aurelien Pupier
 */
public class ImporterPriorityDisplayComparator extends ViewerComparator {

    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        if (e1 instanceof ImporterFactory && e2 instanceof ImporterFactory) {
            final int priorityDisplay0 = ((ImporterFactory) e1).getPriorityDisplay();
            final int priorityDisplay1 = ((ImporterFactory) e2).getPriorityDisplay();
            if (priorityDisplay0 < priorityDisplay1) {
                return -1;
            } else if (priorityDisplay0 == priorityDisplay1) {
                return 0;
            } else {
                return 1;
            }
        } else {
            return 0;
        }
    }

}
