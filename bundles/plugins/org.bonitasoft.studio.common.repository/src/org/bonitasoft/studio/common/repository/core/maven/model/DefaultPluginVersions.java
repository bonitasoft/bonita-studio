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

    static final String CODEHAUS_GROOVY_GROUPID = "org.codehaus.groovy";
    
    static final String BONITA_PROJECT_MAVEN_PLUGIN_GROUP_ID = "org.bonitasoft.maven";
    static final String APACHE_MAVEN_PLUGIN_GROUP_ID = "org.apache.maven.plugins";
    static final String CODEHAUS_PLUGIN_GROUP_ID = "org.codehaus.mojo";

    
    static final String MAVEN_INSTALL_PLUGIN = "maven-install-plugin";
    static final String MAVEN_INSTALL_PLUGIN_VERSION = "3.1.1";
    
    static final String MAVEN_COMPILER_PLUGIN = "maven-compiler-plugin";
    static final String MAVEN_COMPILER_PLUGIN_VERSION = "3.13.0";
    
    static final String MAVEN_ASSEMBLY_PLUGIN = "maven-assembly-plugin";
    static final String MAVEN_ASSEMBLY_PLUGIN_VERSION = "3.7.1";
    
    static final String MAVEN_SUREFIRE_PLUGIN = "maven-surefire-plugin";
    static final String MAVEN_SUREFIRE_PLUGIN_VERSION = "3.2.5";
    
    static final String GROOVY_MAVEN_PLUGIN_GROUP_ID = "org.codehaus.gmavenplus";
    static final String GROOVY_MAVEN_PLUGIN_ARTIFACT_ID = "gmavenplus-plugin";
    static final String GROOVY_MAVEN_PLUGIN_VERSION = "3.0.2";
    
    static final String MAVEN_FAILSAFE_PLUGIN = "maven-failsafe-plugin";
    static final String MAVEN_FAILSAFE_PLUGIN_VERSION = "3.2.5";
    
    static final String MAVEN_SHADE_PLUGIN = "maven-shade-plugin";
    static final String MAVEN_SHADE_PLUGIN_VERSION = "3.5.3";
    
    static final String MAVEN_SOURCE_PLUGIN = "maven-source-plugin";
    static final String MAVEN_SOURCE_PLUGIN_VERSION = "3.3.1";
    
    static final String MAVEN_JAVADOC_PLUGIN = "maven-javadoc-plugin";
    static final String MAVEN_JAVADOC_PLUGIN_VERSION = "3.6.3";
    
    static final String MAVEN_DEPLOY_PLUGIN = "maven-deploy-plugin";
    static final String MAVEN_DEPLOY_PLUGIN_VERSION = "3.1.2";
    
    static final String MAVEN_CLEAN_PLUGIN = "maven-clean-plugin";
    static final String MAVEN_CLEAN_PLUGIN_VERSION = "3.3.2";
    
    static final String MAVEN_RESOURCES_PLUGIN = "maven-resources-plugin";
    static final String MAVEN_RESOURCES_PLUGIN_VERSION = "3.3.1";
    
    static final String MAVEN_JAR_PLUGIN = "maven-jar-plugin";
    static final String MAVEN_JAR_PLUGIN_VERSION = "3.4.1";
    
    static final String FLATTEN_MAVEN_PLUGIN = "flatten-maven-plugin";
    static final String FLATTEN_MAVEN_PLUGIN_VERSION = "1.6.0";
    
    static final String BONITA_PROJECT_MAVEN_PLUGIN_ARTIFACT_ID = "bonita-project-maven-plugin";

    static final String BONITA_PROJECT_MAVEN_PLUGIN_DEFAULT_VERSION = "1.0.4";

    static final String BUILD_HELPER_MAVEN_PLUGIN = "build-helper-maven-plugin";
    static final String BUILD_HELPER_MAVEN_PLUGIN_VERSION = "3.6.0";
    
    static final String MAVEN_DEPENDENCY_PLUGIN = "maven-dependency-plugin";
    static final String MAVEN_DEPENDENCY_PLUGIN_VERSION = "3.6.1";
    
    static final String RUNTIME_BOM_GROUP_ID = "org.bonitasoft.runtime";
    static final String RUNTIME_BOM_ARTIFACT_ID = "bonita-runtime-bom";
    
    static final String BONITA_PROJECT_GROUP_ID = "org.bonitasoft";
    static final String BONITA_PROJECT_ARTIFACT_ID = "bonita-project";
    
    static final String EXEC_MAVEN_PLUGIN_GROUP_ID = "org.codehaus.mojo";
    static final String EXEC_MAVEN_PLUGIN_ARTIFACT_ID = "exec-maven-plugin";

}
