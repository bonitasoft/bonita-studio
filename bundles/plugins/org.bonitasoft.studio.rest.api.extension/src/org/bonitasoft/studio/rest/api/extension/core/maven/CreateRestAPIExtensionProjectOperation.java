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
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Set;
import java.util.jar.JarFile;

import org.apache.maven.archetype.catalog.Archetype;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DefaultArtifact;
import org.apache.maven.artifact.handler.DefaultArtifactHandler;
import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.maven.ProjectDependenciesResolver;
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
import org.eclipse.m2e.core.project.IProjectConfigurationManager;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class CreateRestAPIExtensionProjectOperation extends CreateCustomPageProjectOperation {

    public CreateRestAPIExtensionProjectOperation(ExtensionRepositoryStore repositoryStore,
            IProjectConfigurationManager projectConfigurationManager,
            ProjectImportConfiguration projectImportConfiguration,
            RestAPIExtensionArchetypeConfiguration archetypeConfiguration) {
        super(repositoryStore, projectConfigurationManager, projectImportConfiguration, archetypeConfiguration);
    }

    @Override
    protected void projectCreated(IProject project) throws CoreException {
        //archetype-post-generate.groovy doesn't work with m2e maven-archetype embedded version...
        // TODO fix me after updating m2e to 2.x+
        RestAPIExtensionArchetypeConfiguration archetypeConfiguration = (RestAPIExtensionArchetypeConfiguration) getArchetypeConfiguration();
        var archetypeJarFile = ProjectDependenciesResolver.resolveFile(toArtifact(RestAPIExtensionArchetype.INSTANCE));
        if (archetypeJarFile.exists()) {
            try (var jar = new JarFile(archetypeJarFile)) {
                var scriptEntry = jar.getEntry("META-INF/archetype-post-generate.groovy");
                if (scriptEntry != null) {
                    var groovyShell = newGroovyShell(archetypeConfiguration);
                    var scriptFile = Files.createTempFile("archetype-post-generate", ".groovy");
                    Files.copy(jar.getInputStream(scriptEntry), scriptFile, StandardCopyOption.REPLACE_EXISTING);
                    groovyShell.evaluate(scriptFile.toFile());
                }
            } catch (IOException e) {
                throw new CoreException(Status.error("Failed to execute archetype-post-generate.groovy", e));
            }
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

    private GroovyShell newGroovyShell(RestAPIExtensionArchetypeConfiguration archetypeConfiguration) {
        var variables = new HashMap<String, Object>();
        var request = new HashMap<String, Object>();
        var location = repositoryStore.getResource().getLocation();
        request.put("properties", archetypeConfiguration.toProperties());
        request.put("outputDirectory", location.toFile().getAbsolutePath());
        request.put("artifactId", archetypeConfiguration.getPageName());
        variables.put("request", request);
        var binding = new Binding(variables);
        return new GroovyShell(CreateCustomPageProjectOperation.class.getClassLoader(), binding);
    }

    private Artifact toArtifact(Archetype instance) {
        return new DefaultArtifact(instance.getGroupId(), instance.getArtifactId(), instance.getVersion(), null, "jar",
                null, new DefaultArtifactHandler("jar"));
    }

    /* Dirty string replacement to control the location of the dependency in the dependency list */
    private String insertBdmModelDependency(Dependency bdmModelDep, String content) {
        return content.replace(String.format("    <dependency>%n"
                + "      <groupId>org.slf4j</groupId>%n"
                + "      <artifactId>slf4j-api</artifactId>%n"
                + "      <scope>provided</scope>%n"
                + "    </dependency>"), String.format(
                        "        <dependency>%n"
                                + "      <groupId>org.slf4j</groupId>%n"
                                + "      <artifactId>slf4j-api</artifactId>%n"
                                + "      <scope>provided</scope>%n"
                                + "    </dependency>%n%n"
                                + "    <!-- BDM model dependency -->%n"
                                + "    <dependency>%n"
                                + "      <groupId>${project.groupId}</groupId>%n"
                                + "      <artifactId>%s</artifactId>%n"
                                + "      <version>${project.version}</version>%n"
                                + "      <scope>provided</scope>%n"
                                + "    </dependency>%n",bdmModelDep.getArtifactId()));
    }

    @Override
    protected Set<String> projectBuilders(IProjectDescription description) {
        Set<String> projectBuilders = super.projectBuilders(description);
        projectBuilders.add(RestAPIBuilder.BUILDER_ID);
        return projectBuilders;
    }

    @Override
    protected Archetype getArchetype() {
        return RestAPIExtensionArchetype.INSTANCE;
    }

}
