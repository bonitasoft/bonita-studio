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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.CommonRepositoryPlugin;
import org.bonitasoft.studio.common.repository.core.InputStreamSupplier;
import org.bonitasoft.studio.common.repository.preferences.RepositoryPreferenceConstant;
import org.bonitasoft.studio.common.repository.store.LocalDependenciesStore;

public class DependencyLookup {

    public enum Status {
        NOT_FOUND, LOCAL, FOUND
    }

    private String fileName;
    private String sha1;
    private Status status;
    private GAV gav;
    private String repository;
    private boolean selected;
    private File tmpFile;
    private boolean isUsed;
    private InputStreamSupplier inputStreamSupplier;
    private ConflictVersion conflictVersion;
    private Set<String> jarNames = new HashSet<>();
    private Artifact artifact;

    public DependencyLookup(String fileName,
            String sha1,
            Status status,
            GAV gav,
            String repository) {
        this.fileName = fileName;
        this.sha1 = sha1;
        this.status = status;
        this.gav = gav;
        this.repository = repository;
        if (status == Status.NOT_FOUND && fileName != null) {
            tmpFile = copy(fileName);
        }
        if (fileName != null) {
            this.fileName = new File(fileName).getName();
            jarNames.add(this.fileName);
        }
    }

    public DependencyLookup(String fileName,
            String sha1,
            Status status,
            GAV gav,
            String repository,
            Artifact artifact) {
        this(fileName, sha1, status, gav, repository);
        this.artifact = artifact;
    }

    public DependencyLookup(InputStreamSupplier inputStreamSupplier,
            Status status,
            GAV gav) {
        this.inputStreamSupplier = inputStreamSupplier;
        this.fileName = inputStreamSupplier.getName();
        this.jarNames.add(fileName);
        this.status = status;
        this.gav = gav;
        tmpFile = inputStreamSupplier.toTempFile();
    }

    private File copy(String fileName) {
        File file = new File(fileName);
        if (file.isFile()) {
            String name = file.getName();
            try {
                Path tempDirectory = Files.createTempDirectory("depLookup");
                return Files.copy(file.toPath(), tempDirectory.resolve(name)).toFile();
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
        }
        return null;
    }

    public void deleteCopy() {
        if (inputStreamSupplier != null) {
            try {
                inputStreamSupplier.close();
            } catch (Exception e) {
                BonitaStudioLog.error(e);
            }
        }
        if (tmpFile != null && tmpFile.exists()) {
            try {
                Files.deleteIfExists(tmpFile.toPath());
                File parentFile = tmpFile.getParentFile();
                if (parentFile != null) {
                    Files.deleteIfExists(parentFile.toPath());
                }
            } catch (IOException e) {
                BonitaStudioLog.error(e);
            }
        }
    }

    public static DependencyLookup fromCSV(String[] csvData) {
        if (csvData.length == 7) {
            GAV gav = new GAV(csvData[3],
                    csvData[4],
                    csvData[5]);
            return new DependencyLookup(csvData[0],
                    csvData[1],
                    Status.valueOf(enumFormat(csvData)),
                    gav,
                    csvData[6]);
        }
        //Not found
        String fileName = csvData[0];
        File file = new File(fileName);
        String name = file.getName();
        GAV defaultGav = new GAV(defaultGroupId(), name.replace(".jar", ""), "1.0.0");
        return readPomProperties(file)
                .map(pomProperties -> new GAV(pomProperties.getProperty("groupId"),
                        pomProperties.getProperty("artifactId"),
                        pomProperties.getProperty("version")))
                .map(gav -> new DependencyLookup(fileName,
                        csvData[1],
                        Status.valueOf(enumFormat(csvData)),
                        gav,
                        ""))
                .orElseGet(() -> new DependencyLookup(fileName,
                        csvData[1],
                        Status.valueOf(enumFormat(csvData)),
                        defaultGav,
                        ""));
    }

    private static String defaultGroupId() {
        return CommonRepositoryPlugin.getDefault().getPreferenceStore().getString(RepositoryPreferenceConstant.DEFAULT_GROUPID);
    }

    public static String guessClassifier(String fileName, GAV gav) {
        String name = new File(fileName).getName();
        if (name.equals(String.format("%s-%s.jar", gav.getArtifactId(), gav.getVersion()))) {
            // Not classified
            return null;
        }
        Matcher matcher = Pattern
                .compile(String.format("%s-%s-(.*).jar", gav.getArtifactId(), gav.getVersion()))
                .matcher(name);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private static String enumFormat(String[] csvData) {
        return csvData[2].toUpperCase().replace(" ", "_");
    }

    public static Optional<Properties> readPomProperties(File file) {
        var fileName = file.getName();
        String fileNameWithoutExtension = fileName.substring(0, fileName.length() - 4);
        try (JarFile jarFile = new JarFile(file)) {
            return jarFile.stream()
                    .filter(entry -> entry.getName()
                            .matches("META-INF/maven/[^/]*/[^/]*/pom.properties"))
                    .map(entry -> {
                        Properties properties = new Properties();
                        try (InputStream is = jarFile.getInputStream(entry)) {
                            properties.load(is);
                            return properties;
                        } catch (IOException e) {
                            BonitaStudioLog.error(e);
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .filter(properties -> {
                        String artifactId = properties.getProperty("artifactId");
                        String version = properties.getProperty("version");
                        return fileNameWithoutExtension.startsWith(String.format("%s-%s", artifactId, version))
                                || fileNameWithoutExtension.equals(artifactId);
                    }).findFirst();

        } catch (IOException e) {
            BonitaStudioLog.error(e);
        }
        return Optional.empty();
    }

    public String getFileName() {
        return fileName;
    }

    public String getSha1() {
        return sha1;
    }

    public Status getStatus() {
        return status;
    }

    public String getGroupId() {
        return gav.getGroupId();
    }

    public void setGroupId(String groupId) {
        gav.setGroupId(groupId);
    }

    public String getArtifactId() {
        return gav.getArtifactId();
    }

    public void setArtifactId(String artifactId) {
        this.gav.setArtifactId(artifactId);
    }

    public String getVersion() {
        return gav.getVersion();
    }

    public void setVersion(String version) {
        gav.setVersion(version);
    }

    public void setType(String type) {
        gav.setType(type);
    }

    public void setClassifier(String classifier) {
        gav.setClassifier(classifier);
    }

    public String getRepository() {
        return repository;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public Dependency toMavenDependency() {
        Dependency dependency = new Dependency();
        dependency.setArtifactId(gav.getArtifactId());
        dependency.setVersion(gav.getVersion());
        if (getConflictVersion() != null) {
            dependency.setVersion(getConflictVersion().getSelectedVersion());
        }
        dependency.setGroupId(gav.getGroupId());
        dependency.setClassifier(gav.getClassifier());
        if (!"jar".equals(gav.getType())) {
            dependency.setType(gav.getType());
        }
        dependency.setScope(gav.getScope());
        return dependency;
    }

    public File getFile() {
        return tmpFile;
    }

    public GAV getGAV() {
        return gav;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setConflict(ConflictVersion conflictVersion) {
        this.conflictVersion = conflictVersion;
    }

    public ConflictVersion getConflictVersion() {
        return conflictVersion;
    }

    public boolean isConflicting() {
        return getConflictVersion() != null && getConflictVersion().getStatus() == ConflictVersion.Status.CONFLICTING;
    }

    public boolean hasFileNameChanged() {
        return jarNames.stream().anyMatch(jarName -> !Objects.equals(jarName, dependencyFileName()));
    }

    public String dependencyFileName() {
        return LocalDependenciesStore.dependencyFileName(toMavenDependency());
    }

    public void addJar(String fileName) {
        this.jarNames.add(fileName);
    }

    public Set<String> getJarNames() {
        return jarNames;
    }

    public Artifact getArtifact() {
        return artifact;
    }

}
