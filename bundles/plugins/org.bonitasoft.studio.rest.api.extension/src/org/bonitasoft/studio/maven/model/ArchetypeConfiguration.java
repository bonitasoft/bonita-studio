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

import java.util.Map;

/**
 * Configuration for archetype initialization.
 */
public interface ArchetypeConfiguration {

    /**
     * Set the attribute with the given key to the given value. (for internal use)
     * 
     * @param key attribute key
     * @param value attribute value
     */
    abstract void setAttribute(String key, String value);

    /**
     * Get the attribute with the given key. (for internal use)
     * 
     * @param key attribute key
     * @return attribute value
     */
    abstract String getAttribute(String key);

    /**
     * Collect attributes in a properties in a map to pass for archetype initialization.
     * 
     * @return properties map
     */
    public abstract Map<String, String> toProperties();

    /**
     * Get label to present the artifact in the UI.
     * 
     * @return label
     */
    public abstract String getArtifactLabel();

}
