/**
 * Copyright (C) 2019 BonitaSoft S.A.
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

/**
 * Configuration for a maven archetype that creates a custom page project (Theme or REST API).
 */
public interface CustomPageArchetypeConfiguration extends ExtensionProjectArchetypeConfiguration {

    /** pageDisplayName attribute key, for internal use */
    static final String PAGE_DISPLAY_NAME_ATTRIBUTE = "pageDisplayName";

    /** pageDescription attribute key, for internal use */
    static final String PAGE_DESCRIPTION_ATTRIBUTE = "pageDescription";

    /**
     * Get the page name.
     * 
     * @return page name
     */
    default String getPageName() {
        // same as project name
        return getProjectName();
    }

    default String getPageDisplayName() {
        return getAttribute(PAGE_DISPLAY_NAME_ATTRIBUTE);
    }

    default void setPageDisplayName(String pageDisplayName) {
        setAttribute(PAGE_DISPLAY_NAME_ATTRIBUTE, pageDisplayName);
    }

    default String getPageDescription() {
        return getAttribute(PAGE_DESCRIPTION_ATTRIBUTE);
    }

    default void setPageDescription(String pageDescription) {
        setAttribute(PAGE_DESCRIPTION_ATTRIBUTE, pageDescription);
    }

}
