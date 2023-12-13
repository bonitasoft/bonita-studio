package org.bonitasoft.studio.maven.model;

import org.eclipse.m2e.core.project.IArchetype;

public record Archetype(String groupId, String artifactId, String version) implements IArchetype{

    @Override
    public String getGroupId() {
        return groupId();
    }

    @Override
    public String getArtifactId() {
        return artifactId();
    }

    @Override
    public String getVersion() {
        return version();
    }
}
