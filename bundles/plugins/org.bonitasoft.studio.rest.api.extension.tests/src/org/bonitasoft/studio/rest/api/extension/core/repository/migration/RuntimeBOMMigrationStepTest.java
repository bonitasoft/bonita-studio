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
package org.bonitasoft.studio.rest.api.extension.core.repository.migration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.io.IOException;
import java.io.InputStream;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.bonitasoft.studio.common.repository.core.maven.model.DefaultPluginVersions;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.junit.Before;
import org.junit.Test;

public class RuntimeBOMMigrationStepTest {

    private MavenXpp3Reader modelReader = new MavenXpp3Reader();
    private static final String GROOVY_4_RUNTIME_VERSION = "12.0.0";
    private static final String PRE_GROOVY_4_RUNTIME_VERSION = "10.2.0";
    private static final String GROOVY_ALL_VERSION_REFERENCE = "${groovy-all.version}";
    private static final String GROOVY_DATEUTIL = "groovy-dateutil";
    private static final String GROOVY_SQL = "groovy-sql";
    private static final String GROOVY_3_VERSION = "3.0.8";

    private RuntimeBOMMigrationStep migrationStep;
    private ProjectMetadata metadata = ProjectMetadata.defaultMetadata();

    @Before
    public void createFixture() throws Exception {
        migrationStep = new RuntimeBOMMigrationStep();
    }

    @Test
    public void should_add_runtime_bom_import() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");

        // When
        migrationStep.migrate(model, metadata);

        // Then
        assertThat(model.getDependencyManagement().getDependencies()).extracting("groupId",
                "artifactId",
                "version",
                "type",
                "scope")
                .contains(tuple("org.bonitasoft.runtime",
                        "bonita-runtime-bom",
                        "${bonita.version}",
                        "pom",
                        "import"));
    }

    @Test
    public void should_remove_version_from_dependencies_in_bom() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");

        // When
        migrationStep.migrate(model, metadata);

        // Then
        assertThat(model.getDependencies()).extracting("groupId",
                "artifactId",
                "version",
                "scope")
                .contains(tuple("com.bonitasoft.web",
                        "bonita-web-extensions-sp",
                        null,
                        "provided"))
                // the legacy Groovy coordinate keeps its version on a 12.0 runtime: that BOM manages
                // org.apache.groovy alone, so handing the version over to it would leave a dependency
                // nothing can resolve. Groovy4MigrationStep owns this coordinate now, and in the real
                // pipeline has already replaced it by the time this step runs
                .contains(tuple(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID,
                        DefaultPluginVersions.LEGACY_GROOVY_ALL_ARTIFACT_ID,
                        "2.4.4",
                        "provided"));
    }

    @Test
    public void should_add_jakarta_servlet_api_dependency() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");

        // When
        migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        // Then
        assertThat(model.getDependencies()).extracting("groupId",
                "artifactId",
                "version",
                "scope")
                .contains(tuple("jakarta.servlet",
                        "jakarta.servlet-api",
                        null,
                        "provided"));
    }

    @Test
    public void should_add_javax_servlet_api_dependency_when_targeting_a_pre_jakarta_runtime() throws Exception {
        // Given
        Model model = loadModel("pom_from_1_0_5.xml");

        // When
        migrationStep.migrate(model, metadataForRuntime(PRE_GROOVY_4_RUNTIME_VERSION));

        // Then
        assertThat(model.getDependencies()).extracting("groupId",
                "artifactId",
                "version",
                "scope")
                .contains(tuple("javax.servlet",
                        "javax.servlet-api",
                        null,
                        "provided"))
                .doesNotContain(tuple("jakarta.servlet",
                        "jakarta.servlet-api",
                        null,
                        "provided"));
    }

    @Test
    public void should_keep_the_version_a_user_pinned_on_a_legacy_groovy_module() throws Exception {
        // Groovy4MigrationStep declines to rewrite a literal version - user intent - and reports it. This
        // step used to null it two steps later, leaving a codehaus coordinate the 12.0 BOM does not manage
        // and breaking the very build the report said was preserved
        Model model = new Model();
        model.getDependencies().add(newDependency(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, GROOVY_DATEUTIL, "3.0.8", "provided"));

        migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getDependencies())
                .filteredOn(dependency -> DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID.equals(dependency.getGroupId()))
                .extracting(Dependency::getArtifactId, Dependency::getVersion)
                .containsExactly(tuple(GROOVY_DATEUTIL, GROOVY_3_VERSION));
    }

    @Test
    public void should_still_let_the_pre_12_bom_manage_the_legacy_groovy_versions() throws Exception {
        // pre-12 the BOM does manage the codehaus coordinates, so nulling the version is correct there
        Model model = new Model();
        model.getDependencies().add(newDependency(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, GROOVY_DATEUTIL, "3.0.8", "provided"));
        model.getDependencies().add(newDependency(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID,
                        DefaultPluginVersions.LEGACY_GROOVY_ALL_ARTIFACT_ID, "3.0.8", "provided"));

        migrationStep.migrate(model, metadataForRuntime(PRE_GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getDependencies())
                .filteredOn(dependency -> DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID.equals(dependency.getGroupId()))
                .extracting(Dependency::getArtifactId, Dependency::getVersion)
                .containsExactly(tuple(GROOVY_DATEUTIL, null), tuple(DefaultPluginVersions.LEGACY_GROOVY_ALL_ARTIFACT_ID, null));
    }

    @Test
    public void should_keep_the_groovy_version_property_a_dependency_still_reads() throws Exception {
        // the property fed versions this step no longer nulls on 12.0: removing it left a
        // ${groovy-all.version} reference resolving to nothing, under a report saying the pin was kept
        Model model = new Model();
        model.getProperties().setProperty(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY, GROOVY_3_VERSION);
        model.getDependencies()
                .add(newDependency(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, GROOVY_SQL, GROOVY_ALL_VERSION_REFERENCE, "compile"));

        migrationStep.migrate(model, metadataForRuntime(GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getProperties()).containsEntry(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY, GROOVY_3_VERSION);
    }

    @Test
    public void should_drop_the_groovy_version_property_it_stopped_feeding_pre_12() throws Exception {
        // groovy-dateutil is one of the two modules this branch nulls, so after it nothing names the
        // property and dropping it is safe
        Model model = new Model();
        model.getProperties().setProperty(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY, GROOVY_3_VERSION);
        model.getDependencies()
                .add(newDependency(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, GROOVY_DATEUTIL, GROOVY_ALL_VERSION_REFERENCE, "provided"));

        migrationStep.migrate(model, metadataForRuntime(PRE_GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getProperties()).doesNotContainKey(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY);
    }

    @Test
    public void should_keep_the_groovy_version_property_a_module_it_does_not_null_still_reads() throws Exception {
        // this branch nulls groovy-all and groovy-dateutil only: groovy-sql keeps its reference, so
        // dropping the property would leave it resolving to nothing
        Model model = new Model();
        model.getProperties().setProperty(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY, GROOVY_3_VERSION);
        model.getDependencies()
                .add(newDependency(DefaultPluginVersions.CODEHAUS_GROOVY_GROUPID, GROOVY_SQL, GROOVY_ALL_VERSION_REFERENCE, "compile"));

        migrationStep.migrate(model, metadataForRuntime(PRE_GROOVY_4_RUNTIME_VERSION));

        assertThat(model.getProperties()).containsEntry(DefaultPluginVersions.GROOVY_ALL_VERSION_PROPERTY, GROOVY_3_VERSION);
    }

    private Dependency newDependency(String groupId, String artifactId, String version, String scope) {
        var dependency = new Dependency();
        dependency.setGroupId(groupId);
        dependency.setArtifactId(artifactId);
        dependency.setVersion(version);
        dependency.setScope(scope);
        return dependency;
    }

    private ProjectMetadata metadataForRuntime(String bonitaRuntimeVersion) {
        var projectMetadata = ProjectMetadata.defaultMetadata();
        projectMetadata.setBonitaRuntimeVersion(bonitaRuntimeVersion);
        return projectMetadata;
    }

    private Model loadModel(String resourceName) throws IOException, XmlPullParserException {
        try (InputStream is = Groovy3MigrationStepTest.class.getResourceAsStream("/migration/" + resourceName)) {
            return modelReader.read(is);
        }
    }
}
