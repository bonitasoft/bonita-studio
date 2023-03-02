/*******************************************************************************
 * Copyright (C) 2015 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.core.maven;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.maven.CustomPageProjectRepositoryStore;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetypeConfiguration;
import org.bonitasoft.studio.maven.operation.CreateCustomPageProjectOperation;
import org.bonitasoft.studio.rest.api.extension.core.builder.RestAPIBuilder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.MavenPlugin;
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
    protected void projectCreated(IProject project) throws CoreException {
        //archetype-post-generate.groovy doesn't work with m2e maven-archetype embedded version...
        //so we need to clean irrelevant generated files here
        RestAPIExtensionArchetypeConfiguration archetypeConfiguration = (RestAPIExtensionArchetypeConfiguration) getArchetypeConfiguration();
        if (RestAPIExtensionArchetypeConfiguration.JAVA_LANGUAGE.equals(archetypeConfiguration.getLanguage())) {
            cleanKotlinResources(AbstractRepository.NULL_PROGRESS_MONITOR, project);
            cleanGroovyResources(AbstractRepository.NULL_PROGRESS_MONITOR, project);
        } else if (RestAPIExtensionArchetypeConfiguration.GROOVY_LANGUAGE
                .equals(archetypeConfiguration.getLanguage())) {
            cleanKotlinResources(AbstractRepository.NULL_PROGRESS_MONITOR, project);
            cleanJavaResources(AbstractRepository.NULL_PROGRESS_MONITOR, project);
            project.getFile("groovy-pom.xml").move(Path.fromOSString("pom.xml"), true,
                    AbstractRepository.NULL_PROGRESS_MONITOR);
        }
        if (archetypeConfiguration.isEnableBDMDependencies()) {
            var bdmStore = RepositoryManager.getInstance().getAccessor()
                    .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
            BusinessObjectModelFileStore bdmFileStore = (BusinessObjectModelFileStore) bdmStore
                    .getChild(BusinessObjectModelFileStore.BOM_FILENAME, false);

            var pomFile = project.getFile("pom.xml").getLocation().toFile();
            var bdmModelDep = bdmFileStore.getModelMavenDependency();
            try {
                var content = Files.readString(pomFile.toPath());
                Files.writeString(pomFile.toPath(), insertBdmModelDependency(bdmModelDep, content));
                project.getFile("pom.xml").refreshLocal(IResource.DEPTH_ZERO, new NullProgressMonitor());
            } catch (IOException e) {
                throw new CoreException(Status.error("Failed to add the BDM model dependency in pom.xml", e));
            }
            MavenPlugin.getProjectConfigurationManager().updateProjectConfiguration(project, new NullProgressMonitor());
        }
        super.projectCreated(project);
    }

    /* Dirty string replacement to control the location of the dependency in the dependency list */
    private String insertBdmModelDependency(Dependency bdmModelDep, String content) {
        return content.replace(String.format("        <dependency>%n"
                + "            <groupId>org.slf4j</groupId>%n"
                + "            <artifactId>slf4j-api</artifactId>%n"
                + "            <scope>provided</scope>%n"
                + "        </dependency>"), String.format(
                        "        <dependency>%n"
                                + "            <groupId>org.slf4j</groupId>%n"
                                + "            <artifactId>slf4j-api</artifactId>%n"
                                + "            <scope>provided</scope>%n"
                                + "        </dependency>%n%n"
                                + "        <!-- BDM model dependency -->%n"
                                + "        <dependency>%n"
                                + "            <groupId>%s</groupId>%n"
                                + "            <artifactId>%s</artifactId>%n"
                                + "            <version>%s</version>%n"
                                + "            <scope>provided</scope>%n"
                                + "        </dependency>%n",
                        bdmModelDep.getGroupId(), bdmModelDep.getArtifactId(), bdmModelDep.getVersion()));
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

    @Override
    protected Set<String> projectBuilders(IProjectDescription description) {
        Set<String> projectBuilders = super.projectBuilders(description);
        projectBuilders.add(RestAPIBuilder.BUILDER_ID);
        return projectBuilders;
    }

}
