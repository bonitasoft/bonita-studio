/**
 * Copyright (C) 2025 BonitaSoft S.A.
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
package org.bonitasoft.studio.maven.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Default base implementation for {@link ArchetypeConfiguration}.<br/>
 * Stores attributes in a map to allow easy extension by just extending the interfaces.
 */
public abstract class ArchetypeConfigurationImpl implements ArchetypeConfiguration {

    private Map<String, String> attributes = new HashMap<>();

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.maven.model.ArchetypeConfiguration#getAttribute(java.lang.String)
     */
    @Override
    public String getAttribute(String key) {
        return attributes.get(key);
    }

    /*
     * (non-Javadoc)
     * @see org.bonitasoft.studio.maven.model.ArchetypeConfiguration#setAttribute(java.lang.String, java.lang.String)
     */
    @Override
    public void setAttribute(String key, String value) {
        attributes.put(key, value);
    }

}
