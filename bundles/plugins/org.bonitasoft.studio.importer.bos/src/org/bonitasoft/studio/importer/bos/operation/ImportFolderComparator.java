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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.importer.bos.operation;

import java.util.Comparator;

import org.eclipse.core.resources.IResource;

/**
 * @author Romain Bioteau
 *         Use to have a deterministic order of the imported repository
 */
public class ImportFolderComparator implements Comparator<IResource> {

    private static final String SRC_FOLDER_NAME = "src";

    private static final String LIB_FOLDER_NAME = "lib";

    private static final String DIAGRAMS_FOLDER_NAME = "diagrams";

    /*
     * (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(IResource res0, IResource res1) {
        String firstResourceName = res0.getName();
        String secondResourceName = res1.getName();
        if (firstResourceName.equals(DIAGRAMS_FOLDER_NAME)) { // diagram folder first
            return -1;
        }
        if (secondResourceName.equals(DIAGRAMS_FOLDER_NAME)) {
            return 1;
        }

        if (firstResourceName.equals(LIB_FOLDER_NAME)) { // Then lib folder
            if (secondResourceName.equals(DIAGRAMS_FOLDER_NAME)) {
                return 1;
            } else {
                return -1;
            }
        }
        if (secondResourceName.equals(LIB_FOLDER_NAME)) {
            if (firstResourceName.equals(DIAGRAMS_FOLDER_NAME)) {
                return -1;
            } else {
                return 1;
            }
        }

        if (firstResourceName.contains(SRC_FOLDER_NAME)) { // Then all src folder
            if (secondResourceName.equals(DIAGRAMS_FOLDER_NAME) || secondResourceName.equals(LIB_FOLDER_NAME)) {
                return 1;
            } else {
                return -1;
            }
        }
        if (secondResourceName.contains(SRC_FOLDER_NAME)) {
            if (firstResourceName.equals(DIAGRAMS_FOLDER_NAME) || firstResourceName.equals(LIB_FOLDER_NAME)) {
                return -1;
            } else {
                return 1;
            }
        }
        return 0;
    }
}
