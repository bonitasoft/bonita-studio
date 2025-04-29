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
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetype;
import org.bonitasoft.studio.maven.model.RestAPIExtensionArchetypeConfiguration;
import org.bonitasoft.studio.maven.operation.CreateCustomPageProjectOperation;
import org.bonitasoft.studio.rest.api.extension.core.builder.RestAPIBuilder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.project.IArchetype;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;

public class CreateRestAPIExtensionProjectOperation extends CreateCustomPageProjectOperation {

    public CreateRestAPIExtensionProjectOperation(ExtensionRepositoryStore repositoryStore,
            ProjectImportConfiguration projectImportConfiguration,
            RestAPIExtensionArchetypeConfiguration archetypeConfiguration) {
        super(repositoryStore, projectImportConfiguration, archetypeConfiguration);
    }

    @Override
    protected void projectCreated(IProject project) throws CoreException {
        RestAPIExtensionArchetypeConfiguration archetypeConfiguration = (RestAPIExtensionArchetypeConfiguration) getArchetypeConfiguration();
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
        return content.replaceFirst(String.format("<dependency>\\R?"
                + "\\s*<groupId>org\\.slf4j</groupId>\\R?"
                + "\\s*<artifactId>slf4j-api</artifactId>\\R?"
                + "\\s*<scope>provided</scope>\\R?"
                + "\\s*</dependency>"), String.format(
                        "<dependency>%n"
                                + "      <groupId>org.slf4j</groupId>%n"
                                + "      <artifactId>slf4j-api</artifactId>%n"
                                + "      <scope>provided</scope>%n"
                                + "    </dependency>%n%n"
                                + "    <!-- BDM model dependency -->%n"
                                + "    <dependency>%n"
                                + "      <groupId>\\${project.groupId}</groupId>%n"
                                + "      <artifactId>%s</artifactId>%n"
                                + "      <version>\\${project.version}</version>%n"
                                + "      <scope>provided</scope>%n"
                                + "    </dependency>%n",
                        bdmModelDep.getArtifactId()));
    }

    @Override
    protected Set<String> projectBuilders(IProjectDescription description) {
        Set<String> projectBuilders = super.projectBuilders(description);
        projectBuilders.add(RestAPIBuilder.BUILDER_ID);
        return projectBuilders;
    }

    @Override
    protected IArchetype getArchetype() {
        return RestAPIExtensionArchetype.INSTANCE;
    }

}
