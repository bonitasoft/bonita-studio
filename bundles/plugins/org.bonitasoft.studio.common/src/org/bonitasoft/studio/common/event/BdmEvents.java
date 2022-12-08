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
package org.bonitasoft.studio.common.event;

public class BdmEvents {

    public static final String BDM_DEPLOYED_TOPIC = "bdm/deployed";
    public static final String BDM_DELETED_TOPIC = "bdm/deleted";
    public static final String DEPENDENCY_PROPERTY = "dependency";
    public static final String MODEL_PROPERTY = "model";
    public static final String FILE_CONTENT_PROPERTY = "fileContent";
    
    private BdmEvents() {
        // private constructor
    }
}
