/*******************************************************************************
 * Copyright (C) 2025 Bonitasoft S.A.
 * Bonitasoft is a trademark of Bonitasoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * Bonitasoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or Bonitasoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.model;

import java.text.MessageFormat;
import java.util.EnumMap;
import java.util.Map;

import org.bonitasoft.studio.application.ui.control.model.dependency.ArtifactType;
import org.bonitasoft.studio.common.repository.core.maven.model.ProjectMetadata;
import org.bonitasoft.studio.maven.ExtensionRepositoryStore;
import org.bonitasoft.studio.maven.operation.CreateExtensionProjectOperation;
import org.bonitasoft.studio.rest.api.extension.core.maven.CreateRestAPIExtensionProjectOperation;
import org.bonitasoft.studio.theme.CreateThemeProjectOperation;
import org.eclipse.m2e.core.project.IArchetype;
import org.eclipse.m2e.core.project.ProjectImportConfiguration;

/**
 * Gives access to the archetypes used to create extension projects for the different artifact types.
 */
public class ExtensionProjectArchetypes {

    private static final String GROUP_ID = "org.bonitasoft.archetypes";

    private static final Map<ArtifactType, IArchetype> archetypes = new EnumMap<>(ArtifactType.class);

    private ExtensionProjectArchetypes() {
    }

    /**
     * Get the Maven archetype to create an extension project for this particular artifact type.
     * 
     * @param extensionType the artifact type to create extension project.
     * @return the Maven archetype
     * @throws IllegalArgumentException if the artifact type is not supported by the extension mechanism.
     */
    public static IArchetype getExtensionArchetype(ArtifactType extensionType) {
        return archetypes.computeIfAbsent(extensionType, ExtensionProjectArchetypes::doGetArchetype);
    }

    private static IArchetype doGetArchetype(ArtifactType extensionType) {
        switch (extensionType) {
            case CONNECTOR:
                return new Archetype(GROUP_ID, "bonita-connector-archetype", "1.3.0");
            case ACTOR_FILTER:
                return new Archetype(GROUP_ID, "bonita-actorfilter-archetype", "1.2.0");
            case REST_API:
                return new Archetype(GROUP_ID, "bonita-rest-api-extension-archetype", "1.7.2");
            case THEME:
                return new Archetype(GROUP_ID, "bonita-theme-archetype", "1.0.3");
            default:
                throw new IllegalArgumentException(
                        MessageFormat.format("{0} is not an extension type", extensionType.getName()));
        }
    }

    /**
     * Build the default archetype configuration for the given artifact type.
     * 
     * @param artifactType the artifact type
     * @param projectMetadata the project metadata
     * @return the default archetype configuration
     * @throws IllegalArgumentException if the artifact type is not supported by the extension mechanism.
     */
    public static ExtensionProjectArchetypeConfiguration buildDefaultArchetypeConfiguration(ArtifactType artifactType,
            ProjectMetadata projectMetadata) {
        switch (artifactType) {
            case CONNECTOR:
                return ConnectorArchetypeConfiguration.defaultArchetypeConfiguration(projectMetadata);
            case ACTOR_FILTER:
                return ActorFilterArchetypeConfiguration.defaultArchetypeConfiguration(projectMetadata);
            case REST_API:
                return RestAPIExtensionArchetypeConfiguration.defaultArchetypeConfiguration(projectMetadata);
            case THEME:
                return ThemeArchetypeConfiguration.defaultArchetypeConfiguration(projectMetadata);
            default:
                throw new IllegalArgumentException(
                        MessageFormat.format("{0} is not an extension type", artifactType.getName()));
        }
    }

    /**
     * Build the operation to create the new extension project for this particular artifact type.
     * 
     * @param artifactType the artifact type
     * @param repositoryStore the store to hold the extension project
     * @param projectImportConfiguration the import configuration
     * @param configuration the archetype configuration
     * @return the new operation
     */
    public static CreateExtensionProjectOperation buildCreateOperation(ArtifactType artifactType,
            ExtensionRepositoryStore repositoryStore,
            ProjectImportConfiguration projectImportConfiguration,
            ExtensionProjectArchetypeConfiguration configuration) {
        switch (artifactType) {
            case CONNECTOR, ACTOR_FILTER:
                return new CreateExtensionProjectOperation(repositoryStore, projectImportConfiguration,
                        configuration) {

                    @Override
                    protected IArchetype getArchetype() {
                        return getExtensionArchetype(artifactType);
                    }
                };
            case REST_API:
                return new CreateRestAPIExtensionProjectOperation(repositoryStore, projectImportConfiguration,
                        (RestAPIExtensionArchetypeConfiguration) configuration);
            case THEME:
                return new CreateThemeProjectOperation(repositoryStore, projectImportConfiguration,
                        (ThemeArchetypeConfiguration) configuration);
            default:
                throw new IllegalArgumentException(
                        MessageFormat.format("{0} is not an extension type", artifactType.getName()));
        }
    }

}
