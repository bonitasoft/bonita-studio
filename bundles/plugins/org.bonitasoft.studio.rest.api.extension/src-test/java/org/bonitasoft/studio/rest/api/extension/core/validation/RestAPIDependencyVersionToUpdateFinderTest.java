package org.bonitasoft.studio.rest.api.extension.core.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bonitasoft.studio.importer.bos.status.BonitaStatusCodeAggregator.REST_API_BONITA_DEPENDENCY_STATUS_CODE;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.apache.maven.project.MavenProject;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.importer.bos.status.BonitaStatusCodeAggregator;
import org.bonitasoft.studio.maven.builder.validator.DependencyToUpdate;
import org.bonitasoft.studio.maven.builder.validator.Location;
import org.bonitasoft.studio.maven.builder.validator.LocationResolver;
import org.eclipse.core.runtime.IStatus;
import org.junit.Test;

public class RestAPIDependencyVersionToUpdateFinderTest {

    @Test
    public void should_compare_versions() {
        RestAPIDependencyVersionToUpdateFinder finder = new RestAPIDependencyVersionToUpdateFinder();

        String oldVersion = "4.0";
        String newVersion = "5.0.0";
        assertThat(finder.artifactVersionToOld(oldVersion, newVersion)).isTrue();
        assertThat(finder.artifactVersionToOld(newVersion, oldVersion)).isFalse();
        assertThat(finder.artifactVersionToOld(newVersion, newVersion)).isFalse();

        oldVersion = "4.0-SNAPSHOT";
        newVersion = "4.0";
        assertThat(finder.artifactVersionToOld(oldVersion, newVersion)).isTrue();
        assertThat(finder.artifactVersionToOld(newVersion, oldVersion)).isFalse();
        assertThat(finder.artifactVersionToOld(newVersion, newVersion)).isFalse();

        oldVersion = "4.0-01";
        newVersion = "4.0-02";
        assertThat(finder.artifactVersionToOld(oldVersion, newVersion)).isTrue();
        assertThat(finder.artifactVersionToOld(newVersion, oldVersion)).isFalse();
        assertThat(finder.artifactVersionToOld(newVersion, newVersion)).isFalse();

        oldVersion = "1.0-groovy-2.4";
        newVersion = "1.2-groovy-2.4";
        assertThat(finder.artifactVersionToOld(oldVersion, newVersion)).isTrue();
        assertThat(finder.artifactVersionToOld(newVersion, oldVersion)).isFalse();
        assertThat(finder.artifactVersionToOld(newVersion, newVersion)).isFalse();

        oldVersion = "1.0-groovy-2.4";
        newVersion = "1.0-groovy-2.5";
        assertThat(finder.artifactVersionToOld(oldVersion, newVersion)).isTrue();
        assertThat(finder.artifactVersionToOld(newVersion, oldVersion)).isFalse();
        assertThat(finder.artifactVersionToOld(newVersion, newVersion)).isFalse();
        
        assertThat(finder.artifactVersionToOld("[7.9,)",  "7.8")).isTrue();
        assertThat(finder.artifactVersionToOld("[7.9,)",  "7.11")).isFalse();
        assertThat(finder.artifactVersionToOld("[7.9,7.11]",  "7.10")).isFalse();
    }

    @Test
    public void should_detect_groovy_all_dependency_to_update() {
        validateDependencyToUpdateDetection(RestAPIDependencyVersionToUpdateFinder.GROOVY_GROUP_ID,
                RestAPIDependencyVersionToUpdateFinder.GROOVY_ALL_ARTIFACT_ID, "2.4.4",
                RestAPIDependencyVersionToUpdateFinder.GROOVY_ALL_MIN_VERSION,
                RestAPIDependencyVersionToUpdateFinder.GROOVY_ALL_MIN_VERSION,
                BonitaStatusCodeAggregator.REST_API_JAVA_11_GROOVY_ALL_STATUS_CODE,
                false);
    }

    @Test
    public void should_detect_groovy_batch_dependency_to_update() {
        validateDependencyToUpdateDetection(RestAPIDependencyVersionToUpdateFinder.GROOVY_GROUP_ID,
                RestAPIDependencyVersionToUpdateFinder.GROOVY_ECLIPSE_BATCH_ARTIFACT_ID, "2.4.3-01",
                RestAPIDependencyVersionToUpdateFinder.GROOVY_ECLIPSE_BATCH_MIN_VERSION,
                RestAPIDependencyVersionToUpdateFinder.GROOVY_ECLIPSE_BATCH_MIN_VERSION,
                BonitaStatusCodeAggregator.REST_API_JAVA_11_GROOVY_BATCH_STATUS_CODE,
                false);
    }

    @Test
    public void should_detect_groovy_compiler_dependency_to_update() {
        validateDependencyToUpdateDetection(RestAPIDependencyVersionToUpdateFinder.GROOVY_GROUP_ID,
                RestAPIDependencyVersionToUpdateFinder.GROOVY_ECLIPSE_COMPILER_ARTIFACT_ID, "2.9.2-01",
                RestAPIDependencyVersionToUpdateFinder.GROOVY_ECLIPSE_COMPILER_MIN_VERSION,
                RestAPIDependencyVersionToUpdateFinder.GROOVY_ECLIPSE_COMPILER_MIN_VERSION,
                BonitaStatusCodeAggregator.REST_API_JAVA_11_GROOVY_COMPILER_STATUS_CODE,
                false);
    }

    @Test
    public void should_detect_maven_compiler_dependency_to_update() {
        validateDependencyToUpdateDetection(RestAPIDependencyVersionToUpdateFinder.APPACHE_MAVEN_PLUGIN_GROUP_ID,
                RestAPIDependencyVersionToUpdateFinder.MAVEN_COMPILER_PLUGIN_ARTIFACT_ID, "3.6.0",
                RestAPIDependencyVersionToUpdateFinder.MAVEN_COMPILER_PLUGIN_MIN_VERSION,
                RestAPIDependencyVersionToUpdateFinder.MAVEN_COMPILER_PLUGIN_RECOMMENDED_VERSION,
                BonitaStatusCodeAggregator.REST_API_JAVA_11_MAVEN_COMPILER_STATUS_CODE,
                true);
    }

    @Test
    public void should_detect_bonita_version_property_to_update() {
        RestAPIDependencyVersionToUpdateFinder finder = spy(new RestAPIDependencyVersionToUpdateFinder());
        doReturn(Optional.of(new Location(0, 0, 0))).when(finder).findVersionLocation(any(), any());

        Properties properties = new Properties();
        properties.put(RestAPIDependencyVersionToUpdateFinder.BONITA_VERSION_PROPERTY, "7.5.0");
        MavenProject mavenProject = mock(MavenProject.class);
        when(mavenProject.getProperties()).thenReturn(properties);

        LocationResolver resolver = mock(LocationResolver.class);
        when(resolver.getLineLocation(anyString())).thenReturn(new Location(10, 10, 10));
        when(resolver.getLocation(anyString())).thenReturn(new Location(10, 10, 10));

        List<DependencyToUpdate> res = new ArrayList<>();
        finder.findMavenPropertiesToUpdate(mavenProject, resolver, res);
        assertThat(res).hasSize(1);
        DependencyToUpdate dependencyToUpdate = res.get(0);
        assertThat(dependencyToUpdate.getCode()).isEqualTo(REST_API_BONITA_DEPENDENCY_STATUS_CODE);

        properties.setProperty(RestAPIDependencyVersionToUpdateFinder.BONITA_VERSION_PROPERTY, ProductVersion.CURRENT_VERSION);
        res.clear();
        finder.findMavenPropertiesToUpdate(mavenProject, resolver, res);
        assertThat(res).isEmpty();
    }

    private void validateDependencyToUpdateDetection(String groupId, String artifactId, String version, String minVersion,
            String recommendedVersion, int code, boolean isPlugin) {
        RestAPIDependencyVersionToUpdateFinder validator = spy(new RestAPIDependencyVersionToUpdateFinder());
        doReturn(Optional.of(new Location(0, 0, 0))).when(validator).findVersionLocation(any(), any());

        String message = "some status message";
        Optional<DependencyToUpdate> optionaDependencyToUpdate = isPlugin
                ? validatePlugin(validator, groupId, artifactId, version, minVersion, message, code, recommendedVersion)
                : validateDependency(validator, groupId, artifactId, version, minVersion, code);

        assertThat(optionaDependencyToUpdate).isPresent();
        DependencyToUpdate dependencyToUpdate = optionaDependencyToUpdate.get();
        assertThat(dependencyToUpdate.getCode()).isEqualTo(code);

        optionaDependencyToUpdate = isPlugin
                ? validatePlugin(validator, groupId, artifactId, minVersion, minVersion, message, code, recommendedVersion)
                : validateDependency(validator, groupId, artifactId, minVersion, minVersion, code);

        assertThat(optionaDependencyToUpdate).isNotPresent();
    }

    private Optional<DependencyToUpdate> validateDependency(RestAPIDependencyVersionToUpdateFinder validator, String groupId,
            String artifactId, String version, String minVersion, int code) {
        org.apache.maven.model.Dependency dependency = mock(org.apache.maven.model.Dependency.class);
        when(dependency.getGroupId()).thenReturn(groupId);
        when(dependency.getArtifactId()).thenReturn(artifactId);
        when(dependency.getVersion()).thenReturn(version);
        return validator.validateDependencyToUpdate(Collections.emptyList(), dependency, groupId, artifactId, minVersion,
                code, IStatus.ERROR);
    }

    private Optional<DependencyToUpdate> validatePlugin(RestAPIDependencyVersionToUpdateFinder validator, String groupId,
            String artifactId, String version, String minVersion, String message, int code, String recommendedVersion) {
        org.apache.maven.model.Plugin plugin = mock(org.apache.maven.model.Plugin.class);
        when(plugin.getGroupId()).thenReturn(groupId);
        when(plugin.getArtifactId()).thenReturn(artifactId);
        when(plugin.getVersion()).thenReturn(version);
        return validator.validatePluginToUpdate(Collections.emptyList(), plugin, minVersion, recommendedVersion, message, code,
                IStatus.ERROR);
    }

}
