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

import java.io.File;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.maven.model.Model;
import org.apache.maven.model.Parent;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.Strings;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.maven.MavenProjectHelper;
import org.bonitasoft.studio.common.repository.preferences.RepositoryPreferenceConstant;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

public class ProjectMetadata {

	private static final String DEFAULT_ARTIFACT_ID = "my-project";
	private static final String DEFAULT_VERSION = "0.0.1";
	public static final String BONITA_RUNTIME_VERSION = "bonita.runtime.version";

	private String name;
	private String description;
	private String groupId;
	private String artifactId;
	private String version;
	private String bonitaRuntimeVersion;
	private boolean useSnapshotRepository;
    private boolean includeAdminApp;

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
		if (CommonRepositoryPlugin.getDefault() == null) {
			return RepositoryPreferenceConstant.DEFAULT_GROUPID_VALUE;
		}
		return CommonRepositoryPlugin.getDefault().getPreferenceStore()
				.getString(RepositoryPreferenceConstant.DEFAULT_GROUPID);
	}

	public static String toArtifactId(String displayName) {
		if (Strings.isNullOrEmpty(displayName)) {
			return displayName;
		}
		String artifactId = Strings.slugify(displayName);
		if (!artifactId.matches("[A-Za-z0-9_\\-.]+")) { // not a valid artifact id
			return DEFAULT_ARTIFACT_ID;
		}
		return artifactId;
	}

	public static ProjectMetadata read(IProject project, IProgressMonitor monitor) throws CoreException {
		var projectFacade = org.eclipse.m2e.core.MavenPlugin.getMavenProjectRegistry().create(project,
				new NullProgressMonitor());
		if (projectFacade == null) {
			if (project.getLocation() != null) {
				var pomFile = project.getLocation().toFile().toPath().resolve("pom.xml").toFile();
				if (pomFile.exists()) {
					return read(pomFile);
				}
			}
			return defaultMetadata();
		}
		var projectPom = project.getLocation().toFile().toPath().resolve("pom.xml").toFile();
		var mavenProject = projectFacade.getMavenProject(monitor);
		if (mavenProject == null) {
			return read(projectPom);
		}
		var metadata = read(mavenProject);
		// Ignore bonita-project parent description
		var model = read(projectPom);
		if(model != null) {
			metadata.setDescription(model.getDescription());
		}
		return metadata;
	}

	public static ProjectMetadata read(MavenProject mavenProject) {
		ProjectMetadata projectMetadata = new ProjectMetadata();
		projectMetadata.setName(mavenProject.getName());
		projectMetadata.setDescription(mavenProject.getDescription());
		projectMetadata.setGroupId(mavenProject.getGroupId());
		projectMetadata.setArtifactId(mavenProject.getArtifactId());
		projectMetadata.setVersion(mavenProject.getVersion());
		projectMetadata.setBonitaRuntimeVersion(mavenProject.getProperties().getProperty(BONITA_RUNTIME_VERSION,
				ProductVersion.BONITA_RUNTIME_VERSION));
		return projectMetadata;
	}

	public static ProjectMetadata read(Model model) {
		ProjectMetadata projectMetadata = new ProjectMetadata();
		projectMetadata.setName(model.getName());
		projectMetadata.setDescription(model.getDescription());
		projectMetadata
				.setGroupId(model.getGroupId() == null && model.getParent() != null ? model.getParent().getGroupId()
						: model.getGroupId());
		projectMetadata.setArtifactId(model.getArtifactId());
		projectMetadata
				.setVersion(model.getVersion() == null && model.getParent() != null ? model.getParent().getVersion()
						: model.getVersion());
		var runtimeVersion = getBonitaRuntimeVersion(model);
		projectMetadata.setBonitaRuntimeVersion(
				runtimeVersion != null ? runtimeVersion : ProductVersion.BONITA_RUNTIME_VERSION);
		return projectMetadata;
	}

	public static ProjectMetadata read(File pomFile) {
		try {
			return read(MavenProjectHelper.readModel(pomFile));
		} catch (CoreException e) {
			BonitaStudioLog.error(e);
			return null;
		}
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

	@Override
	public int hashCode() {
		return Objects.hash(artifactId, bonitaRuntimeVersion, description, groupId, name, version,
				useSnapshotRepository);
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
		return Objects.equals(artifactId, other.artifactId)
				&& Objects.equals(bonitaRuntimeVersion, other.bonitaRuntimeVersion)
				&& Objects.equals(description, other.description) && Objects.equals(groupId, other.groupId)
				&& Objects.equals(name, other.name) && Objects.equals(version, other.version)
				&& Objects.equals(useSnapshotRepository, other.useSnapshotRepository);
	}

	public String getProjectId() {
		return artifactId;
	}

	public boolean isUseSnapshotRepository() {
		return useSnapshotRepository;
	}

	public void setUseSnapshotRepository(boolean useSnapshotRepository) {
		this.useSnapshotRepository = useSnapshotRepository;
	}

	public static String getBonitaRuntimeVersion(Model model) {
		if (model.getProperties().containsKey(BONITA_RUNTIME_VERSION)) {
			return model.getProperties().getProperty(BONITA_RUNTIME_VERSION);
		}
		if (model.getParent() != null && isBonitaProjectParent(model.getParent())) {
			return model.getParent().getVersion();
		}
		return null;
	}

	private static boolean isBonitaProjectParent(Parent parent) {
		return Objects.equals(parent.getGroupId(), "org.bonitasoft")
				&& Objects.equals(parent.getArtifactId(), "bonita-project");
	}

    public boolean includeAdminApp() {
        return includeAdminApp;
    }
    
    public void setIncludeAdminApp(boolean includeAdminApp) {
        this.includeAdminApp = includeAdminApp;
    }

}
