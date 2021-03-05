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
package org.bonitasoft.studio.common.repository.core.maven.migration.model;

import org.apache.maven.model.Dependency;

public class GAV {

    private String groupId;
    private String artifactId;
    private String version;
    private String classifier;
    private String type = "jar";

    public GAV(String groupId, String artifactId, String version) {
        this(groupId, artifactId, version, null, "jar");
    }

    public GAV(String groupId, String artifactId, String version, String classifier) {
        this(groupId, artifactId, version, classifier, "jar");
    }

    public GAV(String groupId, String artifactId, String version, String classifier, String type) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.classifier = classifier;
        this.type = type;
    }

    public GAV(Dependency mavenDependency) {
        this(mavenDependency.getGroupId(),
                mavenDependency.getArtifactId(),
                mavenDependency.getVersion(),
                mavenDependency.getClassifier(),
                mavenDependency.getType());
    }

    /**
     * Respect maven string coordinate format: <groupId>:<artifactId>[:<extension>[:<classifier>]]:<version>
     */
    @Override
    public String toString() {
        var label = String.format("%s:%s:%s", groupId, artifactId,type);
        if (classifier != null) {
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
        return classifier;
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

}
