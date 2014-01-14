/**
 * Copyright (C) 2012 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.repository.operation;

import java.util.Comparator;

import org.eclipse.core.resources.IResource;


/**
 * @author Romain Bioteau
 * Use to have a deterministic order of the imported repository
 */
public class ImportFolderComparator<T> implements Comparator<IResource> {

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(IResource res0, IResource res1) {
        if(res0.getName().equals("diagrams")){ //We want to have the diagrams imported first
            return -1;
        }
        if(res1.getName().equals("diagrams")){
            return 1;
        }
        return 0;
    }

}
