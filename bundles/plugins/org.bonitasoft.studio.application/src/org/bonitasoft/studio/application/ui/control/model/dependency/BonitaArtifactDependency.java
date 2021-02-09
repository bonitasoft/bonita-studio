/**
 * Copyright (C) 2021 Bonitasoft S.A.
 * Bonitasoft, 32 rue Gustave Eiffel - 38000 Grenoble
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
package org.bonitasoft.studio.application.ui.control.model.dependency;

import java.util.List;
import java.util.Optional;

import org.eclipse.swt.graphics.Image;

public class BonitaArtifactDependency {

    private String name;
    private String description;
    private String type;
    private String bonitaMinVersion;
    private List<BonitaArtifactConnectorDefinition> definitions;
    private String icon;
    private String groupId;
    private String artifactId;
    private List<BonitaArtifactDependencyVersion> versions;
    private Image iconImage;

    public BonitaArtifactDependency() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBonitaMinVersion() {
        return bonitaMinVersion;
    }

    public void setBonitaMinVersion(String bonitaMinVersion) {
        this.bonitaMinVersion = bonitaMinVersion;
    }

    public List<BonitaArtifactConnectorDefinition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<BonitaArtifactConnectorDefinition> definitions) {
        this.definitions = definitions;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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

    public List<BonitaArtifactDependencyVersion> getVersions() {
        return versions;
    }

    public void setVersions(List<BonitaArtifactDependencyVersion> versions) {
        this.versions = versions;
    }

    public Optional<BonitaArtifactDependencyVersion> getLatestCompatibleVersion() {
        return versions.stream()
                .filter(BonitaArtifactDependencyVersion::isCompatible)
                .sorted()
                .findFirst();
    }

    public void setIconImage(Image iconImage) {
        this.iconImage = iconImage;
    }

    public Image getIconImage() {
        return iconImage;
    }
}
