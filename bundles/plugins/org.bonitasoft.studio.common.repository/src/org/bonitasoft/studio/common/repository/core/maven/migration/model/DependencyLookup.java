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
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.jar.JarFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.maven.model.Dependency;
import org.bonitasoft.studio.common.log.BonitaStudioLog;

public class DependencyLookup {

    public enum Status {
        FOUND, NOT_FOUND
    }

    private final String fileName;
    private final String sha1;
    private final Status status;
    private String groupId;
    private String artifactId;
    private String version;
    private final String repository;
    private String classifier;

    public DependencyLookup(String fileName,
            String sha1,
            Status status,
            String groupId,
            String artifactId,
            String version,
            String classifier,
            String repository) {
        this.fileName = fileName;
        this.sha1 = sha1;
        this.status = status;
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.version = version;
        this.classifier = classifier;
        this.repository = repository;
    }

    public static DependencyLookup fromCSV(String[] csvData) {
        if (csvData.length == 7) {
            return new DependencyLookup(csvData[0],
                    csvData[1],
                    Status.valueOf(enumFormat(csvData)),
                    csvData[3],
                    csvData[4],
                    csvData[5],
                    DependencyLookup.guessClassifier(csvData[0], csvData[3], csvData[4], csvData[5]),
                    csvData[6]);
        } else { //Not found
            String fileName = csvData[0];
            String name = new File(fileName).getName();
            return readPomProperties(fileName)
                    .map(pomProperties -> new DependencyLookup(fileName,
                            csvData[1],
                            Status.valueOf(enumFormat(csvData)),
                            pomProperties.getProperty("groupId"),
                            pomProperties.getProperty("artifactId"),
                            pomProperties.getProperty("version"),
                            null,
                            ""))
                    .orElseGet(() -> new DependencyLookup(fileName,
                            csvData[1],
                            Status.valueOf(enumFormat(csvData)),
                            "com.company",
                            name.replace(".jar", ""),
                            "1.0.0",
                            null,
                            ""));
        }
    }

    private static String guessClassifier(String fileName, String groupId, String artifactId, String version) {
        String name = new File(fileName).getName();
        if(name.equals(String.format("%s-%s.jar", artifactId, version))) {
            // Not classified
            return null;
        }
        Matcher matcher = Pattern
                .compile(String.format("%s-%s-(.*).jar", artifactId,version))
                .matcher(name);
        if(matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private static String enumFormat(String[] csvData) {
        return csvData[2].toUpperCase().replace(" ", "_");
    }

    private static Optional<Properties> readPomProperties(String filePath) {
        File file = new File(filePath);
        String fileName = file.getName();
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
                        return fileNameWithoutExtension.equals(String.format("%s-%s", artifactId, version))
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

    public String getRepository() {
        return repository;
    }

    public Dependency toMavenDependency() {
        Dependency dependency = new Dependency();
        dependency.setArtifactId(artifactId);
        dependency.setVersion(version);
        dependency.setGroupId(groupId);
        dependency.setClassifier(classifier);
        return dependency;
    }

}
