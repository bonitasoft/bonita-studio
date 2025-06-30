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

import org.apache.maven.model.Model;

/**
 * Configuration for maven archetype used for an extension project.
 */
public interface ExtensionProjectArchetypeConfiguration extends ArchetypeConfiguration {

    /** projectName attribute key, for internal use */
    static final String PROJECT_NAME_ATTRIBUTE = "projectName";

    /** groupId attribute key, for internal use */
    static final String GROUP_ID_ATTRIBUTE = "groupId";

    /** version attribute key, for internal use */
    static final String VERSION_ATTRIBUTE = "version";

    /** javaPackage attribute key, for internal use */
    static final String JAVA_PACKAGE_ATTRIBUTE = "javaPackage";

    /**
     * Get the project name or artifact id.
     * 
     * @return project name
     */
    default String getProjectName() {
        return getAttribute(PROJECT_NAME_ATTRIBUTE);
    }

    /**
     * Set the project name or artifact id.
     * 
     * @param projectName project name
     */
    default void setProjectName(String projectName) {
        setAttribute(PROJECT_NAME_ATTRIBUTE, projectName);
    }

    default String getGroupId() {
        return getAttribute(GROUP_ID_ATTRIBUTE);
    }

    default void setGroupId(String groupId) {
        setAttribute(GROUP_ID_ATTRIBUTE, groupId);
    }

    default String getVersion() {
        return getAttribute(VERSION_ATTRIBUTE);
    }

    default void setVersion(String version) {
        setAttribute(VERSION_ATTRIBUTE, version);
    }

    default String getJavaPackage() {
        return getAttribute(JAVA_PACKAGE_ATTRIBUTE);
    }

    default void setJavaPackage(String javaPackage) {
        setAttribute(JAVA_PACKAGE_ATTRIBUTE, javaPackage);
    }

    default Model toModel() {
        final Model model = new Model();
        model.setArtifactId(getProjectName());
        model.setGroupId(getGroupId());
        model.setVersion(getVersion());
        return model;
    }
}
