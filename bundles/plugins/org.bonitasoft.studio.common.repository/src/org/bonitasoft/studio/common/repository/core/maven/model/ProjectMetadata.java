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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.maven.model.Model;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.preferences.RepositoryPreferenceConstant;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;

public class ProjectMetadata {

    private static final String DEFAULT_ARTIFACT_ID = "my-project";
    private static final String DEFAULT_VERSION = "0.0.1";

    private String name;
    private String description;
    private String groupId;
    private String artifactId;
    private String version;
    private String bonitaRuntimeVersion;

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
    
    public String getBonitaRuntimeVersion() {
        return bonitaRuntimeVersion;
    }

    public void setBonitaRuntimeVersion(String bonitaRuntimeVersion) {
        this.bonitaRuntimeVersion = bonitaRuntimeVersion;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((artifactId == null) ? 0 : artifactId.hashCode());
        result = prime * result + ((bonitaRuntimeVersion == null) ? 0 : bonitaRuntimeVersion.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ProjectMetadata other = (ProjectMetadata) obj;
        if (artifactId == null) {
            if (other.artifactId != null)
                return false;
        } else if (!artifactId.equals(other.artifactId))
            return false;
        if (bonitaRuntimeVersion == null) {
            if (other.bonitaRuntimeVersion != null)
                return false;
        } else if (!bonitaRuntimeVersion.equals(other.bonitaRuntimeVersion))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (groupId == null) {
            if (other.groupId != null)
                return false;
        } else if (!groupId.equals(other.groupId))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        return true;
    }

    public static ProjectMetadata defaultMetadata() {
        ProjectMetadata metadata = new ProjectMetadata();
        metadata.setName(Messages.defaultRepositoryName);
        metadata.setArtifactId(toArtifactId(Messages.defaultRepositoryName));
        metadata.setGroupId(defaultGroupId());
        metadata.setVersion(DEFAULT_VERSION);
        metadata.setBonitaRuntimeVersion(ProductVersion.BONITA_RUNTIME_VERSION);
        return metadata;
    }

    private static String defaultGroupId() {
        if(CommonRepositoryPlugin.getDefault() == null) {
            return RepositoryPreferenceConstant.DEFAULT_GROUPID_VALUE;
        }
        return CommonRepositoryPlugin.getDefault().getPreferenceStore().getString(RepositoryPreferenceConstant.DEFAULT_GROUPID);
    }

    public static String toArtifactId(String displayName) {
        if(Strings.isNullOrEmpty(displayName)) {
            return displayName;
        }
        String artifactId = Strings.slugify(displayName);
        if (!artifactId.matches("[A-Za-z0-9_\\-.]+")) { // not a valid artifact id
            return DEFAULT_ARTIFACT_ID;
        }
        return artifactId;
    }

    public static ProjectMetadata read(IProject project) {
        try {
            Model model = new MavenProjectHelper().getMavenModel(project);
            if(model == null) {
                return defaultMetadata();
            }
            return read(model);
        } catch (CoreException e) {
            BonitaStudioLog.error(e);
        }
        return null;
    }

    public static ProjectMetadata read(Model model) {
        ProjectMetadata projectMetadata = new ProjectMetadata();
        projectMetadata.setName(model.getName());
        projectMetadata.setDescription(model.getDescription());
        projectMetadata.setGroupId(model.getGroupId());
        projectMetadata.setArtifactId(model.getArtifactId());
        projectMetadata.setVersion(model.getVersion());
        projectMetadata.setBonitaRuntimeVersion(model.getProperties().getProperty(ProjectDefaultConfiguration.BONITA_RUNTIME_VERSION));
        return projectMetadata;
    }
    
    public static ProjectMetadata fromBosFileName(String fileName) {
        ProjectMetadata projectMetadata = defaultMetadata();
        fileName = removeBosExtension(fileName);
        var extractedVersion = extractVersion(fileName);
        if (extractedVersion != null) {
            fileName = fileName.replace(extractedVersion, "");
            if (fileName.endsWith("-")) {
                fileName = fileName.substring(0, fileName.length() - 1);
            }
        }
        String timestamp = extractTimestamp(fileName);
        if (timestamp != null) {
            fileName = fileName.replace(timestamp, "");
            if (fileName.endsWith("_")) {
                fileName = fileName.substring(0, fileName.length() - 1);
            }
        }
        String name = Strings.splitCamelCase(fileName);
        projectMetadata.setName(name);
        projectMetadata.setArtifactId(ProjectMetadata.toArtifactId(name));
        if (extractedVersion != null) {
            projectMetadata.setVersion(extractedVersion);
        }
        return projectMetadata;
    }
    
    static String removeBosExtension(String name) {
        if (name.toLowerCase().endsWith(".bos")) {
            name = name.substring(0, name.lastIndexOf("."));
        }
        return name;
    }

    static String extractTimestamp(String name) {
        Pattern pattern = Pattern.compile("(\\d{8}_\\d{4})$");
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) {
            return matcher.toMatchResult().group();
        }
        return null;
    }

    static String extractVersion(String name) {
        Pattern pattern = Pattern.compile("(?!\\.)(\\d+(\\.\\d+)+)(?:[-.]([A-Z]+))?(?![\\d.])$");
        Matcher matcher = pattern.matcher(name);
        if (matcher.find()) {
            return matcher.toMatchResult().group();
        }
        return null;
    }
 
}
