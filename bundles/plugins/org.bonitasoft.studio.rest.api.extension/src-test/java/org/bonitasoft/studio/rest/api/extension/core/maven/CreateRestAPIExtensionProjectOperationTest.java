/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.core.maven;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.bonitasoft.studio.common.RestAPIExtensionNature;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetypeConfiguration;
import org.bonitasoft.studio.rest.api.extension.core.builder.RestAPIBuilder;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.eclipse.core.internal.resources.ProjectDescription;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.m2e.core.project.IProjectConfigurationManager;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;
import org.junit.Test;
import org.mockito.Mockito;

public class CreateRestAPIExtensionProjectOperationTest {

    @Test
    public void should_create_an_eclipse_project_from_archetype() throws Exception {
        final IProjectConfigurationManager projectConfigurationManager = mock(IProjectConfigurationManager.class);
        final RestAPIExtensionRepositoryStore repository = mock(RestAPIExtensionRepositoryStore.class,
                Mockito.RETURNS_DEEP_STUBS);

        ProjectDescription projectDescription = new ProjectDescription();

        final CreateRestAPIExtensionProjectOperation operation = new CreateRestAPIExtensionProjectOperation(
                repository,
                projectConfigurationManager,
                new ProjectImportConfiguration(),
                RestAPIExtensionArchetypeConfiguration.defaultArchetypeConfiguration());

        assertThat(operation.projectNatures(projectDescription)).contains(JavaCore.NATURE_ID,
                "org.eclipse.jdt.groovy.core.groovyNature",
                "org.eclipse.m2e.core.maven2Nature",
                RestAPIExtensionNature.NATURE_ID);
        assertThat(operation.projectBuilders(projectDescription)).contains(
                "org.eclipse.m2e.core.maven2Builder",
                "org.eclipse.jdt.core.javabuilder",
                RestAPIBuilder.BUILDER_ID);
    }

}
