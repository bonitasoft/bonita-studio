/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.core.maven;

import java.util.Set;

import org.bonitasoft.studio.maven.CustomPageProjectRepositoryStore;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetypeConfiguration;
import org.bonitasoft.studio.maven.operation.CreateCustomPageProjectOperation;
import org.bonitasoft.studio.rest.api.extension.core.builder.RestAPIBuilder;
import org.codehaus.jdt.groovy.model.GroovyNature;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.m2e.core.project.IProjectConfigurationManager;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;

public class CreateRestAPIExtensionProjectOperation extends CreateCustomPageProjectOperation {

    public CreateRestAPIExtensionProjectOperation(CustomPageProjectRepositoryStore<?> repositoryStore,
            IProjectConfigurationManager projectConfigurationManager,
            ProjectImportConfiguration projectImportConfiguration,
            RestAPIExtensionArchetypeConfiguration archetypeConfiguration) {
        super(repositoryStore, projectConfigurationManager, projectImportConfiguration, archetypeConfiguration);
    }

    @Override
    protected Set<String> projectBuilders(final IProjectDescription description) {
        final Set<String> builders = super.projectBuilders(description);
        builders.add(JavaCore.BUILDER_ID);
        builders.add(RestAPIBuilder.BUILDER_ID);
        return builders;
    }

    @Override
    protected Set<String> projectNatures(final IProjectDescription description) {
        final Set<String> natures = super.projectNatures(description);
        natures.add(GroovyNature.GROOVY_NATURE);
        natures.add(JavaCore.NATURE_ID);
        return natures;
    }

    @Override
    protected IProject doRun(IProgressMonitor monitor) throws CoreException {
        IProject project = super.doRun(monitor);
        //archetype-post-generate.groovy doesn't work with m2e maven-archetype embedded version...
        //so we need to clean irrelevant generated files here
        RestAPIExtensionArchetypeConfiguration archetypeConfiguration = (RestAPIExtensionArchetypeConfiguration) getArchetypeConfiguration();
        if(RestAPIExtensionArchetypeConfiguration.JAVA_LANGUAGE.equals(archetypeConfiguration.getLanguage())){
            cleanKotlinResources(monitor, project);
            cleanGroovyResources(monitor, project);
        }else if(RestAPIExtensionArchetypeConfiguration.GROOVY_LANGUAGE.equals(archetypeConfiguration.getLanguage())) {
            cleanKotlinResources(monitor, project);
            cleanJavaResources(monitor, project);
            project.getFile("groovy-pom.xml").move(Path.fromOSString("pom.xml"), true, monitor);
        }
        return project;
    }

    private void cleanJavaResources(IProgressMonitor monitor, IProject project) throws CoreException {
        project.getFile("pom.xml").delete(true, monitor);
        project.getFolder("src/main/java").delete(true, monitor);
        project.getFolder("src/test/java").delete(true, monitor);
    }

    private void cleanGroovyResources(IProgressMonitor monitor, IProject project) throws CoreException {
        project.getFile("groovy-pom.xml").delete(true, monitor);
        project.getFolder("src/main/groovy").delete(true, monitor);
        project.getFolder("src/test/groovy").delete(true, monitor);
    }

    private void cleanKotlinResources(IProgressMonitor monitor, IProject project) throws CoreException {
        project.getFile("kotlin-pom.xml").delete(true, monitor);
        project.getFolder("src/main/kotlin").delete(true, monitor);
        project.getFolder("src/test/kotlin").delete(true, monitor);
    }

}
