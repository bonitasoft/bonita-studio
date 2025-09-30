
package org.bonitasoft.studio.maven.model.migration;

import static org.bonitasoft.studio.maven.model.ExtensionProjectArchetypes.buildDefaultArchetypeConfiguration;

import java.io.IOException;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Set;

import org.bonitasoft.studio.application.ui.control.model.dependency.ArtifactType;
import org.bonitasoft.studio.common.FileUtil;
import org.bonitasoft.studio.common.RestAPIExtensionNature;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.ProjectDescriptionBuilder;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.common.repository.core.migration.step.ConnectorsModuleMigrationStep;
import org.bonitasoft.studio.common.repository.core.migration.step.UpdateProjectDescriptionMigrationStep;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.bonitasoft.studio.maven.ImportProjectException;
import org.bonitasoft.studio.maven.model.ArchetypeConfigurationWithLanguage;
import org.bonitasoft.studio.maven.model.ExtensionProjectArchetypes;
import org.bonitasoft.studio.maven.operation.ArchetypeGenerator;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.EventTopic;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.m2e.core.internal.IMavenConstants;
import org.osgi.service.event.Event;

import com.google.common.collect.Iterators;

import jakarta.inject.Inject;

/**
 * This add-on registers the operations to create new extension modules from archetypes when migrating a project.
 */
public class ArchetypesRegistration {

    @Inject
    @Optional
    public void applicationStarted(
            @EventTopic(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE) Event event) {
        ConnectorsModuleMigrationStep.registerActorFilterCreationOperation(inputs -> {
            return createExtensionModule(ArtifactType.ACTOR_FILTER, inputs.artifactId(), inputs.useJava(),
                    inputs.projectMetadata(), inputs.extensionsParentProjectLocation());
        });
        ConnectorsModuleMigrationStep.registerConnectorCreationOperation(inputs -> {
            return createExtensionModule(ArtifactType.CONNECTOR, inputs.artifactId(), inputs.useJava(),
                    inputs.projectMetadata(), inputs.extensionsParentProjectLocation());
        });
    }

    /**
     * Create a new extension module project for the given artifact type.
     * 
     * @param artifactType the artifact type to create the extension module for
     * @param artifactId the artifact id of the extension module to create
     * @param useJava whether to use Java instead of Groovy
     * @param projectMetadata the project metadata to use as migrated project
     * @param extensionsParentProjectLocation the location of the "extensions" project hosting the module
     * @return the created project or null if the project could not be created
     */
    private Path createExtensionModule(ArtifactType artifactType, String artifactId, boolean useJava,
            ProjectMetadata projectMetadata, Path extensionsParentProjectLocation) {
        try {
            var config = buildDefaultArchetypeConfiguration(artifactType, projectMetadata);
            config.setJavaPackage("tobedeleted");
            config.setProjectName(artifactId);
            if (useJava && config instanceof ArchetypeConfigurationWithLanguage withLanguage) {
                withLanguage.setLanguage(ArchetypeConfigurationWithLanguage.JAVA_LANGUAGE);
            }

            var mavenProjects = ArchetypeGenerator.getInstance().createArchetypeProjects(
                    IPath.fromPath(extensionsParentProjectLocation),
                    ExtensionProjectArchetypes.getExtensionArchetype(artifactType),
                    config,
                    new NullProgressMonitor());
            var moduleInfo = Iterators.getNext(mavenProjects.iterator(), null);
            if (moduleInfo != null) {
                var moduleProject = moduleInfo.getPomFile().getParentFile().toPath();
                /*
                 * We do not need to import the maven project, but we must configure it as in
                 * org.bonitasoft.studio.maven.operation.CreateExtensionProjectOperation.configure(IProject, IProgressMonitor)
                 * So we create the new descriptor file in the project.
                 */
                var descriptor = moduleProject.resolve(IProjectDescription.DESCRIPTION_FILE_NAME);
                var description = new ProjectDescriptionBuilder()
                        .withProjectName(artifactId)
                        .havingNatures(Set.of(IMavenConstants.NATURE_ID, RestAPIExtensionNature.NATURE_ID))
                        .havingBuilder(IMavenConstants.BUILDER_ID)
                        .build();
                UpdateProjectDescriptionMigrationStep.writeDescriptor(descriptor, description);

                // delete the temporary package and tests
                try {
                    if (useJava) {
                        FileUtil.deleteDir(moduleProject.resolve(Path.of("src", "main", "java", "tobedeleted")));
                        FileUtil.deleteDir(moduleProject.resolve(Path.of("src", "test", "java", "tobedeleted")));
                    } else {
                        FileUtil.deleteDir(moduleProject.resolve(Path.of("src", "main", "groovy", "tobedeleted")));
                        FileUtil.deleteDir(moduleProject.resolve(Path.of("src", "test", "groovy", "tobedeleted")));
                    }
                } catch (IOException e) {
                    // just log, this is not fatal
                    BonitaStudioLog.error(e, RestAPIExtensionActivator.PLUGIN_ID);
                }
                importProject(artifactId);
                return moduleProject;
            } else {
                var error = MessageFormat.format(
                        "Failed to create new {0} extension project named {1}, archetype returned no project.",
                        artifactType.name(), artifactId);
                BonitaStudioLog.error(error, RestAPIExtensionActivator.PLUGIN_ID);
            }
        } catch (CoreException e) {
            var error = MessageFormat.format("Failed to create new {0} extension project named {1}.",
                    artifactType.name(), artifactId);
            BonitaStudioLog.error(error, e, RestAPIExtensionActivator.PLUGIN_ID);
        }
        return null;
    }

    /**
     * Make sure the project is imported in the workspace
     * 
     * @param projectName the name of the project to import
     */
    private void importProject(String projectName) {
        var extStore = RepositoryManager.getInstance().getRepositoryStore(ExtensionRepositoryStore.class);
        var fileStore = extStore.getChild(projectName, true);
        try {
            // the project does not exist when we are running a git migration on temp folder
            if (fileStore != null && !fileStore.getProject().exists()) {
                fileStore.importProject();
            }
        } catch (final ImportProjectException e) {
            BonitaStudioLog.error(e);
        }
    }

}
