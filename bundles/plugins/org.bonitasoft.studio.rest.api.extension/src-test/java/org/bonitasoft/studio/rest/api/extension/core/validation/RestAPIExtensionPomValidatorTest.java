/**
 * Copyright (C) 2016 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.rest.api.extension.core.validation;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.util.Collections;
import java.util.List;

import org.apache.maven.execution.DefaultMavenExecutionResult;
import org.apache.maven.execution.MavenExecutionResult;
import org.apache.maven.project.DependencyResolutionResult;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionFileStore;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.graph.DependencyNode;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.text.Document;
import org.junit.Test;

public class RestAPIExtensionPomValidatorTest {

    @Test
    public void should_add_an_error_status_for_missing_dependency() throws Exception {
        final RestAPIExtensionPomValidator validator = spy(new RestAPIExtensionPomValidator());
        final RestAPIDependencyVersionToUpdateFinder finder = spy(new RestAPIDependencyVersionToUpdateFinder());
        final RestAPIExtensionFileStore restApiFileStore = mock(RestAPIExtensionFileStore.class);
        final MavenExecutionResult mavenResult = new DefaultMavenExecutionResult();
        mavenResult.setDependencyResolutionResult(
                expectedResolutionResult(Collections.<Dependency> emptyList(), newArrayList(dependency("g", "a", "1.0.0"))));
        mavenResult.setProject(new MavenProject());
        doReturn(mavenResult).when(validator).build(restApiFileStore);
        doReturn(null).when(finder).currentBDMGroupId();
        validator.dependencyVersionToUpdateFinder = finder;
        doReturn(false).when(finder).isUsingJava11();
        doReturn(new Document()).when(finder).toDocument(any());

        final List<IStatus> result = validator.validate(restApiFileStore);

        assertThat(result).extracting("severity").contains(IStatus.ERROR);
    }

    @Test
    public void should_add_an_info_status_when_web_extension_dependency_is_missing() throws Exception {
        final RestAPIExtensionPomValidator validator = spy(new RestAPIExtensionPomValidator());
        final RestAPIDependencyVersionToUpdateFinder finder = spy(new RestAPIDependencyVersionToUpdateFinder());
        final RestAPIExtensionFileStore restApiFileStore = mock(RestAPIExtensionFileStore.class);
        final MavenExecutionResult mavenResult = new DefaultMavenExecutionResult();
        mavenResult.setDependencyResolutionResult(
                expectedResolutionResult(Collections.<Dependency> emptyList(),
                        newArrayList(dependency("com.bonitasoft.web", "bonita-web-extensions-sp", "1.0.0"))));
        mavenResult.setProject(new MavenProject());
        doReturn(mavenResult).when(validator).build(restApiFileStore);
        doReturn(null).when(finder).currentBDMGroupId();
        validator.dependencyVersionToUpdateFinder = finder;
        doReturn(false).when(finder).isUsingJava11();
        doReturn(new Document()).when(finder).toDocument(any());

        final List<IStatus> result = validator.validate(restApiFileStore);

        assertThat(result).extracting("severity").contains(IStatus.INFO);
    }

    @Test
    public void should_add_a_warning_status_when_web_extension_dependency_is_older() throws Exception {
        final RestAPIExtensionPomValidator validator = spy(new RestAPIExtensionPomValidator());
        final RestAPIDependencyVersionToUpdateFinder finder = spy(new RestAPIDependencyVersionToUpdateFinder());
        final RestAPIExtensionFileStore restApiFileStore = mock(RestAPIExtensionFileStore.class);
        final MavenExecutionResult mavenResult = new DefaultMavenExecutionResult();
        mavenResult.setDependencyResolutionResult(
                expectedResolutionResult(newArrayList(dependency("com.bonitasoft.web", "bonita-web-extensions-sp", "7.2.0")),
                        Collections.<Dependency> emptyList()));
        mavenResult.setProject(new MavenProject());
        doReturn(mavenResult).when(validator).build(restApiFileStore);
        doReturn(null).when(finder).currentBDMGroupId();
        validator.dependencyVersionToUpdateFinder = finder;
        doReturn(false).when(finder).isUsingJava11();
        doReturn(new Document()).when(finder).toDocument(any());

        final List<IStatus> result = validator.validate(restApiFileStore);

        assertThat(result).extracting("severity").contains(IStatus.WARNING);
    }
    
    @Test
    public void should_not_add_a_warning_status_when_web_extension_dependency_is_in_range() throws Exception {
        final RestAPIExtensionPomValidator validator = spy(new RestAPIExtensionPomValidator());
        final RestAPIDependencyVersionToUpdateFinder finder = spy(new RestAPIDependencyVersionToUpdateFinder());
        final RestAPIExtensionFileStore restApiFileStore = mock(RestAPIExtensionFileStore.class);
        final MavenExecutionResult mavenResult = new DefaultMavenExecutionResult();
        mavenResult.setDependencyResolutionResult(
                expectedResolutionResult(newArrayList(dependency("com.bonitasoft.web", "bonita-web-extensions-sp", "[7.2.0,)")),
                        Collections.<Dependency> emptyList()));
        mavenResult.setProject(new MavenProject());
        doReturn(mavenResult).when(validator).build(restApiFileStore);
        doReturn(null).when(finder).currentBDMGroupId();
        validator.dependencyVersionToUpdateFinder = finder;
        doReturn(false).when(finder).isUsingJava11();
        doReturn(new Document()).when(finder).toDocument(any());

        final List<IStatus> result = validator.validate(restApiFileStore);

        assertThat(result).extracting("severity").doesNotContain(IStatus.WARNING);
    }

    private Dependency dependency(String groupId, String artifactId, String version) {
        return new Dependency(new DefaultArtifact(groupId, artifactId, "", version), "compile");
    }

    protected DependencyResolutionResult expectedResolutionResult(final List<Dependency> resolved,
            final List<Dependency> unresolved) {
        return new DependencyResolutionResult() {

            @Override
            public List<Dependency> getUnresolvedDependencies() {
                return unresolved;
            }

            @Override
            public List<Dependency> getResolvedDependencies() {
                return resolved;
            }

            @Override
            public List<Exception> getResolutionErrors(Dependency arg0) {
                return null;
            }

            @Override
            public DependencyNode getDependencyGraph() {
                return null;
            }

            @Override
            public List<Dependency> getDependencies() {
                return null;
            }

            @Override
            public List<Exception> getCollectionErrors() {
                return null;
            }
        };
    }

}
