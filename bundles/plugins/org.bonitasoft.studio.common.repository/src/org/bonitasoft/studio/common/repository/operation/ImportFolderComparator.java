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
package org.bonitasoft.studio.common.repository.operation;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IResource;

/**
 * @author Romain Bioteau
 *         Use to have a deterministic order of the imported repository
 */
public class ImportFolderComparator implements Comparator<IResource> {

    private static final String SRC_FOLDER_NAME_PREFIX = "src";

    private static final String LIB_FOLDER_NAME = "lib";

    private static final String DIAGRAMS_FOLDER_NAME = "diagrams";

    private static Map<String, Integer> weight = new HashMap<String, Integer>();
    static{
        weight.put(LIB_FOLDER_NAME, 100);
        weight.put(SRC_FOLDER_NAME_PREFIX, 90);
        weight.put(DIAGRAMS_FOLDER_NAME, 80);
    }

    /*
     * (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(IResource res0, IResource res1) {
        String firstResourceName = res0.getName();
        String secondResourceName = res1.getName();
        return compare(firstResourceName,secondResourceName);
    }

    private int compare(String firstResourceName, String secondResourceName) {
        return weight(firstResourceName) > weight(secondResourceName) ? 1 : -1 ;
    }

    private int weight(String resourceName) {
        if(resourceName.startsWith(SRC_FOLDER_NAME_PREFIX)){
            resourceName = SRC_FOLDER_NAME_PREFIX;
        }
        Integer weigth = weight.get(resourceName);
        if(weigth == null){
            return 0;
        }
        return weigth.intValue();
    }
}
