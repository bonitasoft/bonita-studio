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

import java.util.List;
import java.util.stream.Stream;

/**
 * List of the Maven plugins and their version used in the Bonita project.
 * <p>
 * These versions must stay consistent with the ones declared by the
 * {@code org.bonitasoft:bonita-project} parent POM, which is resolved through the
 * {@code bonita-runtime.version} property. The template POMs in
 * {@code org.bonitasoft.studio.local.repository[.sp]/src/template/pom.xml} declare those
 * plugins without a version and inherit them from that parent, so a value declared here
 * that diverges from the parent silently overrides it in generated user projects.
 */
public interface DefaultPluginVersions {

    /** Legacy Groovy 3 coordinates, only used to detect dependencies in poms of projects to migrate. */
    static final String CODEHAUS_GROOVY_GROUPID = "org.codehaus.groovy";
    /** The monolithic Groovy artifact written by 7.13-era Studio, split into modules since. */
    static final String LEGACY_GROOVY_ALL_ARTIFACT_ID = "groovy-all";
    /**
     * Never generated (the runtime bundle does not ship it - a provided dependency would fail
     * with NoClassDefFoundError at execution); interim 12.0 builds briefly wrote it, so the
     * migration removes provided-scoped occurrences.
     */
    static final String GROOVY_ANT_ARTIFACT_ID = "groovy-ant";
    static final String APACHE_GROOVY_GROUPID = "org.apache.groovy";
    /**
     * Groovy modules provided by the Bonita runtime, versions managed by the bonita-project parent.
     * Must stay consistent with the local.repository template poms, and every module listed here
     * MUST be shipped by the runtime bundle: a provided dependency the runtime does not ship
     * compiles and then fails with NoClassDefFoundError at execution.
     */
    static final List<String> GROOVY_MODULES = List.of("groovy", "groovy-json", "groovy-xml",
            "groovy-nio", "groovy-datetime", "groovy-dateutil", "groovy-sql", "groovy-templates", "groovy-jsr223",
            "groovy-jmx", "groovy-yaml");
    /**
     * Frozen historical set: the org.codehaus.groovy modules Studio wrote into user poms up to
     * Bonita 11.x (groovy-ant never was: it only existed in the offline template poms). Only used
     * to detect and migrate legacy dependencies - never edit it when GROOVY_MODULES evolves.
     */
    static final List<String> LEGACY_GROOVY_MODULES = List.of("groovy", "groovy-json", "groovy-xml",
            "groovy-nio", "groovy-datetime", "groovy-dateutil", "groovy-sql", "groovy-templates", "groovy-jsr223",
            "groovy-jmx", "groovy-yaml", "groovy-servlet");

    /**
     * Dependencies provided by the Bonita runtime, declared without version in generated poms.
     * A method rather than a constant: BonitaCommonDependency reads the running product to pick
     * its coordinates, and an eager interface field would latch that on the first access to any
     * non-constant field of this interface. With no product set the Subscription coordinates
     * (bonita-common-sp) are picked - latched early, pom generation (AppProjectConfiguration,
     * ProjectDefaultConfiguration) would write them into Community project poms.
     */
    static List<MavenDependency> providedDependencies() {
        return Stream.concat(
                Stream.of(new BonitaCommonDependency()),
                GROOVY_MODULES.stream().map(module -> new MavenDependency(APACHE_GROOVY_GROUPID, module, null)))
                .toList();
    }

    static final String BONITA_PROJECT_MAVEN_PLUGIN_GROUP_ID = "org.bonitasoft.maven";
    static final String APACHE_MAVEN_PLUGIN_GROUP_ID = "org.apache.maven.plugins";
    static final String CODEHAUS_PLUGIN_GROUP_ID = "org.codehaus.mojo";

    static final String MAVEN_INSTALL_PLUGIN = "maven-install-plugin";
    static final String MAVEN_INSTALL_PLUGIN_VERSION = "3.1.3";

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

    static final String BONITA_PROJECT_MAVEN_PLUGIN_DEFAULT_VERSION = "3.0.0";

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
