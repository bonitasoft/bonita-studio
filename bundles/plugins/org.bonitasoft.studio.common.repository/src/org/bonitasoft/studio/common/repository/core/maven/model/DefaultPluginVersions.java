/**
 * Copyright (C) 2021 BonitaSoft S.A.
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
package org.bonitasoft.studio.common.repository.core.maven.model;

/**
 * List of the Maven plugins and their version used in the Bonita project
 * Please make sure to keep it consistent with the declared versions in org.bonitasoft.studio.local.repository/src/main/resources/pom.xml
 */
public interface DefaultPluginVersions {

    static final String BONITA_PROJECT_MAVEN_PLUGIN_GROUP_ID = "org.bonitasoft.maven";
    static final String APACHE_MAVEN_PLUGIN_GROUP_ID = "org.apache.maven.plugins";
    static final String CODEHAUS_PLUGIN_GROUP_ID = "org.codehaus.mojo";

    static final String MAVEN_INSTALL_PLUGIN = "maven-install-plugin";
    static final String MAVEN_INSTALL_PLUGIN_VERSION = "2.5.2";

    static final String BONITA_PROJECT_MAVEN_PLUGIN_ARTIFACT_ID = "bonita-project-maven-plugin";
    static final String BONITA_PROJECT_MAVEN_PLUGIN_DEFAULT_VERSION = "0.0.1-SNAPSHOT";

    static final String BUILD_HELPER_MAVEN_PLUGIN = "build-helper-maven-plugin";
    static final String BUILD_HELPER_MAVEN_PLUGIN_VERSION = "3.2.0";

}
