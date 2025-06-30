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

/**
 * {@link ArchetypeConfiguration} with language and className attributes.
 */
public interface ArchetypeConfigurationWithLanguage extends ArchetypeConfiguration {

    /** Language attribute key, for internal use */
    static final String LANGUAGE_ATTRIBUTE = "language";

    public static final String JAVA_LANGUAGE = "java";
    public static final String GROOVY_LANGUAGE = "groovy";
    /*
     * Kotlin language is voluntarily not supported.
     * Support for Kotlin language is not mature nor a functional requirement in Bonita Studio.
     */

    default String getLanguage() {
        return getAttribute(LANGUAGE_ATTRIBUTE);
    }

    default void setLanguage(String language) {
        setAttribute(LANGUAGE_ATTRIBUTE, language);
    }

}
