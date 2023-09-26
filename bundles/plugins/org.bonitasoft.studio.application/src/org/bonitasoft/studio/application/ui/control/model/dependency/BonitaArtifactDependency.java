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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.maven.model.Dependency;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.swt.graphics.Image;

public class BonitaArtifactDependency implements Comparable<BonitaArtifactDependency> {

    private String name;
    private String description;
    private String type; // for jackson automatic parsing
    private ArtifactType artifactType;
    private String bonitaMinVersion;
    private String icon;
    private String groupId;
    private String artifactId;
    private String scope;
    private List<BonitaArtifactDependencyVersion> versions = new ArrayList<>();
    private Image iconImage;
    private boolean localDependency;

    private boolean fromMarketplace = true;
    private String scmUrl;
    private MultiStatus status = new MultiStatus(getClass(), 0, "");
	private boolean isProjectExtension = false;

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
        switch (type) {
            case BonitaMarketplace.CONNECTOR_TYPE:
                setArtifactType(ArtifactType.CONNECTOR);
                break;
            case BonitaMarketplace.ACTOR_FILTER_TYPE:
                setArtifactType(ArtifactType.ACTOR_FILTER);
                break;
            case BonitaMarketplace.APPLICATION_TYPE:
                setArtifactType(ArtifactType.APPLICATION);
                break;
            default:
                setArtifactType(ArtifactType.OTHER);
        }
    }

    public void setArtifactType(ArtifactType artifactType) {
        this.artifactType = artifactType;
    }

    public ArtifactType getArtifactType() {
        return artifactType;
    }

    public String getBonitaMinVersion() {
        return bonitaMinVersion;
    }

    public void setBonitaMinVersion(String bonitaMinVersion) {
        this.bonitaMinVersion = bonitaMinVersion;
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

    public void setFromMarketplace(boolean fromMarketplace) {
        this.fromMarketplace = fromMarketplace;
    }

    public boolean isFromMarketplace() {
        return fromMarketplace;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }
    
    public boolean isEnterprise() {
        return getGroupId().startsWith("com.bonitasoft");
    }

    public void setLocalDependency(boolean localDependency) {
        this.localDependency = localDependency;
    }

    public boolean isLocalDependency() {
        return localDependency;
    }

    public Dependency toMavenDependency() {
        var dependency = new Dependency();
        dependency.setGroupId(getGroupId());
        dependency.setArtifactId(getArtifactId());
        String bestVersion = getBestVersion();
        if(bestVersion != null && !bestVersion.isBlank()) {
            dependency.setVersion(bestVersion);
        }
        if (scope != null) {
            dependency.setScope(scope);
        }
        if(getArtifactType() == ArtifactType.APPLICATION) {
            dependency.setClassifier("application");
            dependency.setType("zip");
        }
        return dependency;
    }

    public String getBestVersion() {
        return getLatestCompatibleVersion()
                .map(BonitaArtifactDependencyVersion::getVersion)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("No compatible version found for %s:%s", getGroupId(), getArtifactId())));
    }

    public void setSCMUrl(String scmUrl) {
        this.scmUrl = scmUrl;
    }
    
    public String getScmUrl() {
        return scmUrl;
    }

    public MultiStatus getStatus() {
        return status;
    }
    
    public void setStatus(MultiStatus status) {
        this.status = status;
    }
    
	public boolean isProjectExtension() {
		return isProjectExtension;
	}
	
	public void setProjectExtension(boolean isProjectExtension) {
		this.isProjectExtension = isProjectExtension;
	}
	
    @Override
	public int hashCode() {
		return Objects.hash(artifactId, artifactType, bonitaMinVersion, description, fromMarketplace, groupId, icon,
				iconImage, isProjectExtension, localDependency, name, scmUrl, scope, status, type, versions);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BonitaArtifactDependency other = (BonitaArtifactDependency) obj;
		return Objects.equals(artifactId, other.artifactId) && artifactType == other.artifactType
				&& Objects.equals(bonitaMinVersion, other.bonitaMinVersion)
				&& Objects.equals(description, other.description) && fromMarketplace == other.fromMarketplace
				&& Objects.equals(groupId, other.groupId) && Objects.equals(icon, other.icon)
				&& Objects.equals(iconImage, other.iconImage) && isProjectExtension == other.isProjectExtension
				&& localDependency == other.localDependency && Objects.equals(name, other.name)
				&& Objects.equals(scmUrl, other.scmUrl) && Objects.equals(scope, other.scope)
				&& Objects.equals(status, other.status) && Objects.equals(type, other.type)
				&& Objects.equals(versions, other.versions);
	}

	@Override
    public int compareTo(BonitaArtifactDependency o) {
        if (o instanceof BonitaArtifactDependency) {
            BonitaArtifactDependency otherDep = o;
            int priorityComparaison = getArtifactType().getPriority()
                    - otherDep.getArtifactType().getPriority();
            if (priorityComparaison != 0) {
                return priorityComparaison;
            }
            return getName().compareTo(otherDep.getName());
        }
        return 0;
    }


}
