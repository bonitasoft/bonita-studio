/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.bonitasoft.studio.common.repository.core.maven.model;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.common.repository.core.maven.migration.model.GAV;

public class MavenDependency {
    
    private final String groupId;
    private final String artifactId;
    private final String version;
    private final String type;

    public MavenDependency(String groupId, String artifactId, String version, String type) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.type = type;
    }
    
    public MavenDependency(String groupId, String artifactId, String version) {
        this(groupId, artifactId, version, "jar");
    }
    
    public Dependency toProvidedDependency() {
        Dependency dependency = new Dependency();
        dependency.setGroupId(groupId);
        dependency.setArtifactId(artifactId);
        dependency.setVersion(version);
        dependency.setType(type);
        dependency.setScope(Artifact.SCOPE_PROVIDED);
        return dependency;
    }
    
    public GAV toGAV() {
        return new GAV(groupId, artifactId, version, null, type, Artifact.SCOPE_PROVIDED);
    }

}
