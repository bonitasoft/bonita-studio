/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven.builder.validator;


import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.maven.artifact.versioning.ComparableVersion;
import org.apache.maven.artifact.versioning.DefaultArtifactVersion;
import org.apache.maven.artifact.versioning.InvalidVersionSpecificationException;
import org.apache.maven.artifact.versioning.VersionRange;
import org.apache.maven.model.Dependency;
import org.apache.maven.model.InputLocation;
import org.apache.maven.model.Plugin;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jface.text.Document;

import com.google.common.io.Files;

public abstract class DependencyVersionToUpdateFinder implements IDependencyValidator {

    public static final String VERSION_TAG = "version";

    protected String stripSnapshot(String bonitaVersion) {
        return bonitaVersion.endsWith("-SNAPSHOT")
                ? bonitaVersion.substring(0, bonitaVersion.indexOf("-SNAPSHOT"))
                : bonitaVersion;
    }

    public Optional<DependencyToUpdate> validatePluginToUpdate(List<Location> versionLocations, 
            Plugin plugin,
            String minVersion,
            String recommendedVersion, 
            String message,
            int code,
            int severity) {
        if (artifactVersionToOld(plugin.getVersion(), minVersion)) {
            return findVersionLocation(plugin.getLocation(VERSION_TAG), versionLocations)
                    .map(location -> new DependencyToUpdate(location, VERSION_TAG, message,
                            code,
                            recommendedVersion, 
                            severity));
        }
        return Optional.empty();
    }

    public Optional<DependencyToUpdate> validateDependencyToUpdate(List<Location> versionLocations,
            Dependency dependency,
            String groupId, 
            String artifactId, 
            String minVersion, 
            int code, 
            int severity) {
        if (artifactMatch(dependency, groupId, artifactId)
                && artifactVersionToOld(dependency.getVersion(), minVersion)) {
            String artifactName = toArtifactName(groupId, artifactId);
            String message = String.format(Messages.updateVersionForJava11Compliance, artifactName, minVersion);
            return findVersionLocation(dependency.getLocation(VERSION_TAG), versionLocations)
                    .map(location -> new DependencyToUpdate(location, VERSION_TAG, message, code, minVersion,
                            severity));
        }
        return Optional.empty();
    }

    protected String toArtifactName(String groupId, String artifactId) {
        return String.format("%s:%s", groupId, artifactId);
    }

    public boolean artifactVersionToOld(String artifactVersion, String minVersion) {
        ComparableVersion artifactVersionComparable = new ComparableVersion(artifactVersion);
        ComparableVersion minVersionComparable = new ComparableVersion(minVersion);
        if (artifactVersion.startsWith("[") || artifactVersion.startsWith("(")) {
            try {
                VersionRange versionSpec = VersionRange.createFromVersionSpec(artifactVersion);
                return !versionSpec.containsVersion(new DefaultArtifactVersion(minVersion));
            } catch (InvalidVersionSpecificationException e) {
                BonitaStudioLog.error(e);
            }
        }
        return artifactVersionComparable.compareTo(minVersionComparable) < 0;
    }

    protected boolean artifactMatch(Dependency dependency, String groupId, String artifactId) {
        return Objects.equals(dependency.getArtifactId(), artifactId)
                && Objects.equals(dependency.getGroupId(), groupId);
    }

    public boolean isUsingJava11() {
        IVMInstall defaultVMInstall = JavaRuntime.getDefaultVMInstall();
        return JavaRuntime.compareJavaVersions(defaultVMInstall, JavaCore.VERSION_11) >= 0;
    }

    public Document toDocument(File file) {
        final Document document = new Document();
        try {
            document.set(Files.toString(file, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return document;
    }

    public Optional<Location> findVersionLocation(InputLocation inputLocation, List<Location> versionLocations) {
        return versionLocations.stream()
                .filter(l -> Objects.equals(inputLocation.getLineNumber(), l.getLineNumber() + 1))
                .findFirst();
    }

    protected Optional<Location> findGroupIdLocation(InputLocation inputLocation, List<Location> groupIdLocations) {
        return groupIdLocations.stream()
                .filter(l -> Objects.equals(inputLocation.getLineNumber(), l.getLineNumber() + 1))
                .findFirst();
    }

}
