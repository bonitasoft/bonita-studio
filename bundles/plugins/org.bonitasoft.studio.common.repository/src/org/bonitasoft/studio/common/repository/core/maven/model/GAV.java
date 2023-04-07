/**
 * Copyright (C) 2021 BonitaSoft S.A.
 * BonitaSoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.common.repository.core.maven.model;

import java.util.Objects;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DefaultArtifact;
import org.apache.maven.artifact.handler.DefaultArtifactHandler;
import org.apache.maven.artifact.repository.layout.ArtifactRepositoryLayout;
import org.apache.maven.artifact.repository.layout.DefaultRepositoryLayout;
import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.common.Strings;

public class GAV {

    private static final String JAR_TYPE = "jar";
    private String groupId;
    private String artifactId;
    private String version;
    private String classifier;
    private String scope;
    private String type = JAR_TYPE;
    private ArtifactRepositoryLayout repositoryLayout = new DefaultRepositoryLayout();

    public GAV(String groupId, String artifactId, String version) {
        this(groupId, artifactId, version, null, JAR_TYPE, null);
    }

    public GAV(String groupId, String artifactId, String version, String classifier) {
        this(groupId, artifactId, version, classifier, JAR_TYPE, null);
    }

    public GAV(String groupId, String artifactId, String version, String classifier, String type, String scope) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.classifier = classifier;
        this.type = type == null ? JAR_TYPE : type;
        this.scope = scope == null ? Artifact.SCOPE_COMPILE : scope;
    }

    public GAV(Dependency mavenDependency) {
        this(mavenDependency.getGroupId(),
                mavenDependency.getArtifactId(),
                mavenDependency.getVersion(),
                mavenDependency.getClassifier(),
                mavenDependency.getType(),
                mavenDependency.getScope());
    }

    /**
     * Respect maven string coordinate format: <groupId>:<artifactId>[:<extension>[:<classifier>]]:<version>
     */
    @Override
    public String toString() {
        var label = String.format("%s:%s:%s", groupId, artifactId, type);
        if (classifier != null && !classifier.isEmpty()) {
            label = label + ":" + classifier;
        }
        label = label + ":" + version;
        return label;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getClassifier() {
        return Strings.isNullOrEmpty(classifier) ? null : classifier;
    }

    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }

    public boolean isSameAs(GAV gav) {
        if (gav == null) {
            return false;
        }
        return Objects.equals(gav.getArtifactId(), getArtifactId())
                && Objects.equals(gav.getGroupId(), getGroupId())
                && Objects.equals(gav.getClassifier(), getClassifier())
                && Objects.equals(gav.getScope(), getScope())
                && Objects.equals(gav.getType(), getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(artifactId, classifier, groupId, scope, type, version);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GAV other = (GAV) obj;
        return Objects.equals(artifactId, other.artifactId) && Objects.equals(classifier, other.classifier)
                && Objects.equals(groupId, other.groupId) && Objects.equals(scope, other.scope)
                && Objects.equals(type, other.type) && Objects.equals(version, other.version);
    }

    public String toLocalRepositoryPath() {
        return repositoryLayout.pathOf(toArtifact());
    }

    private Artifact toArtifact() {
        return new DefaultArtifact(groupId, artifactId, version, scope, type, classifier,
                new DefaultArtifactHandler(type));
    }

}
