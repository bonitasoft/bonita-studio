/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.rest.api.extension.core.validation;

import static org.bonitasoft.studio.importer.bos.status.BonitaStatusCodeAggregator.REST_API_BONITA_DEPENDENCY_STATUS_CODE;
import static org.bonitasoft.studio.importer.bos.status.BonitaStatusCodeAggregator.REST_API_JAVA_11_GROOVY_ALL_STATUS_CODE;
import static org.bonitasoft.studio.importer.bos.status.BonitaStatusCodeAggregator.REST_API_JAVA_11_GROOVY_BATCH_STATUS_CODE;
import static org.bonitasoft.studio.importer.bos.status.BonitaStatusCodeAggregator.REST_API_JAVA_11_GROOVY_COMPILER_STATUS_CODE;
import static org.bonitasoft.studio.importer.bos.status.BonitaStatusCodeAggregator.REST_API_JAVA_11_MAVEN_COMPILER_STATUS_CODE;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.maven.model.Plugin;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelFileStore;
import org.bonitasoft.studio.businessobject.core.repository.BusinessObjectModelRepositoryStore;
import org.bonitasoft.studio.common.ProductVersion;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.maven.builder.validator.DependencyToUpdate;
import org.bonitasoft.studio.maven.builder.validator.DependencyVersionToUpdateFinder;
import org.bonitasoft.studio.maven.builder.validator.Location;
import org.bonitasoft.studio.maven.builder.validator.LocationResolver;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;

public class RestAPIDependencyVersionToUpdateFinder extends DependencyVersionToUpdateFinder {

    public static final String BONITA_VERSION_PROPERTY = "bonita.version";
    public static final String VERSION_TAG = "version";

    public static final String GROOVY_GROUP_ID = "org.codehaus.groovy";

    public static final String GROOVY_ALL_ARTIFACT_ID = "groovy-all";
    public static final String GROOVY_ALL_MIN_VERSION = "2.4.16";

    public static final String GROOVY_ECLIPSE_COMPILER_ARTIFACT_ID = "groovy-eclipse-compiler";
    public static final String GROOVY_ECLIPSE_COMPILER_MIN_VERSION = "3.3.0-01";

    public static final String GROOVY_ECLIPSE_BATCH_ARTIFACT_ID = "groovy-eclipse-batch";
    public static final String GROOVY_ECLIPSE_BATCH_MIN_VERSION = "2.4.16-02";

    public static final String APPACHE_MAVEN_PLUGIN_GROUP_ID = "org.apache.maven.plugins";
    public static final String MAVEN_COMPILER_PLUGIN_ARTIFACT_ID = "maven-compiler-plugin";
    public static final String MAVEN_COMPILER_PLUGIN_MIN_VERSION = "3.6.2";
    public static final String MAVEN_COMPILER_PLUGIN_RECOMMENDED_VERSION = "3.8.0";
    private static final String GROUPID_TAG = "groupId";

    public List<DependencyToUpdate> findDependenciesToUpdate(MavenProject mavenProject) {
        LocationResolver locationResolver = new LocationResolver(toDocument(mavenProject.getFile()));
        List<Location> versionLocations = locationResolver.findLocationsMatching("(<version>)(.)+(</version>)",
                false);
        List<Location> groupIdLocations = locationResolver.findLocationsMatching("(<groupId>)(.)+(</groupId>)",
                false);
        List<DependencyToUpdate> dependenciesToUpdate = new ArrayList<>();

        findMavenDependenciesToUpdate(mavenProject, versionLocations, dependenciesToUpdate);
        findMavenPluginDependenciesToUpdate(mavenProject, versionLocations, dependenciesToUpdate);
        findMavenPropertiesToUpdate(mavenProject, locationResolver, dependenciesToUpdate);
        String bdmGroupId = currentBDMGroupId();
        if (bdmGroupId != null) {
            findBDMDependencyToUpdate(mavenProject, groupIdLocations, versionLocations, "bdm-client",
                    dependenciesToUpdate);
            findBDMDependencyToUpdate(mavenProject, groupIdLocations, versionLocations, "bdm-dao",
                    dependenciesToUpdate);
        }

        return dependenciesToUpdate;
    }

    private void findMavenPluginDependenciesToUpdate(MavenProject mavenProject,
            List<Location> versionLocations, List<DependencyToUpdate> dependenciesToUpdate) {
        int java11DependenciesSeverity = isUsingJava11() ? IStatus.ERROR : IStatus.WARNING;
        Plugin mavenComplierPlugin = mavenProject
                .getPlugin(toArtifactName(APPACHE_MAVEN_PLUGIN_GROUP_ID, MAVEN_COMPILER_PLUGIN_ARTIFACT_ID));
        if (mavenComplierPlugin != null) {
            String artifactName = toArtifactName(mavenComplierPlugin.getGroupId(), mavenComplierPlugin.getArtifactId());
            String message = String.format(Messages.updateVersionForJava11Compliance, artifactName, MAVEN_COMPILER_PLUGIN_RECOMMENDED_VERSION);
            validatePluginToUpdate(versionLocations, mavenComplierPlugin,
                    MAVEN_COMPILER_PLUGIN_MIN_VERSION,
                    MAVEN_COMPILER_PLUGIN_RECOMMENDED_VERSION,
                    message,
                    REST_API_JAVA_11_MAVEN_COMPILER_STATUS_CODE,
                    java11DependenciesSeverity).ifPresent(dependenciesToUpdate::add);
            mavenComplierPlugin.getDependencies()
                    .forEach(dependency -> {
                        validateDependencyToUpdate(versionLocations, dependency, GROOVY_GROUP_ID,
                                GROOVY_ECLIPSE_BATCH_ARTIFACT_ID,
                                GROOVY_ECLIPSE_BATCH_MIN_VERSION,
                                REST_API_JAVA_11_GROOVY_BATCH_STATUS_CODE,
                                java11DependenciesSeverity).ifPresent(dependenciesToUpdate::add);
                        validateDependencyToUpdate(versionLocations, dependency, GROOVY_GROUP_ID,
                                GROOVY_ECLIPSE_COMPILER_ARTIFACT_ID,
                                GROOVY_ECLIPSE_COMPILER_MIN_VERSION,
                                REST_API_JAVA_11_GROOVY_COMPILER_STATUS_CODE,
                                java11DependenciesSeverity).ifPresent(dependenciesToUpdate::add);
                    });
        } else {
            BonitaStudioLog.error(String.format("Unable to find plugin %s in the pom.xml",
                    toArtifactName(APPACHE_MAVEN_PLUGIN_GROUP_ID, MAVEN_COMPILER_PLUGIN_ARTIFACT_ID)),
                    RestAPIExtensionActivator.PLUGIN_ID);
        }
    }

    private void findMavenDependenciesToUpdate(MavenProject mavenProject, List<Location> versionLocations,
            List<DependencyToUpdate> dependenciesToUpdate) {
        int java11DependenciesSeverity = isUsingJava11() ? IStatus.ERROR : IStatus.WARNING;
        mavenProject.getDependencies().stream()
                .map(dependency -> validateDependencyToUpdate(versionLocations, dependency,
                        GROOVY_GROUP_ID,
                        GROOVY_ALL_ARTIFACT_ID,
                        GROOVY_ALL_MIN_VERSION,
                        REST_API_JAVA_11_GROOVY_ALL_STATUS_CODE,
                        java11DependenciesSeverity))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .ifPresent(dependenciesToUpdate::add);
    }

    protected void findMavenPropertiesToUpdate(MavenProject mavenProject,
            LocationResolver locationResolver, List<DependencyToUpdate> dependenciesToUpdate) {
        String bonitaVersion = mavenProject.getProperties().getProperty(BONITA_VERSION_PROPERTY);
        if (bonitaVersion != null
                && artifactVersionToOld(stripSnapshot(bonitaVersion), ProductVersion.CURRENT_VERSION)) {
            Location location = locationResolver.getLineLocation(BONITA_VERSION_PROPERTY);
            Location tagLocation = locationResolver.getLocation(BONITA_VERSION_PROPERTY);
            int overflow = tagLocation.getOffset() - location.getOffset() - 1;
            location.setOffset(tagLocation.getOffset() - 1);
            location.setLength(location.getLength() - overflow);
            String message = String.format(Messages.updateBonitaVersionMarkerMessage, ProductVersion.CURRENT_VERSION);
            dependenciesToUpdate.add(new DependencyToUpdate(location, BONITA_VERSION_PROPERTY, message,
                    REST_API_BONITA_DEPENDENCY_STATUS_CODE, ProductVersion.CURRENT_VERSION, IStatus.WARNING));
        }
    }

    private void findBDMDependencyToUpdate(MavenProject mavenProject,
            List<Location> groupIdLocations,
            List<Location> versionLocations,
            String artifactId,
            List<DependencyToUpdate> dependenciesToUpdate) {
        mavenProject.getDependencies().stream()
                .filter(dependency -> Objects.equals(dependency.getArtifactId(), artifactId))
                .findFirst()
                .filter(dependency -> !Objects.equals(dependency.getGroupId(), currentBDMGroupId()))
                .ifPresent(dependency -> findGroupIdLocation(dependency.getLocation(GROUPID_TAG), groupIdLocations)
                        .ifPresent(
                                location -> dependenciesToUpdate.add(createBDMGroupIdToUpdate(location))));
        mavenProject.getDependencies().stream()
                .filter(dependency -> Objects.equals(dependency.getArtifactId(), artifactId))
                .filter(dependency -> Objects.equals(dependency.getGroupId(), currentBDMGroupId()))
                .findFirst()
                .filter(dependency -> !Objects.equals(dependency.getVersion(), currentBDMVersion()))
                .ifPresent(dependency -> findVersionLocation(dependency.getLocation(VERSION_TAG), versionLocations)
                        .ifPresent(location -> dependenciesToUpdate.add(createBDMVersionToUpdate(location))));
    }

    private DependencyToUpdate createBDMVersionToUpdate(Location location) {
        return new DependencyToUpdate(location,
                VERSION_TAG,
                Messages.bdmVersionNotMatching,
                REST_API_BONITA_DEPENDENCY_STATUS_CODE,
                currentBDMVersion(),
                IStatus.WARNING);
    }

    String currentBDMVersion() {
        BusinessObjectModelRepositoryStore repositoryStore = RepositoryManager.getInstance().getCurrentRepository()
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        BusinessObjectModelFileStore bdmFileStore = (BusinessObjectModelFileStore) repositoryStore
                .getChild(BusinessObjectModelFileStore.BOM_FILENAME, false);
        if (bdmFileStore != null) {
            try {
                return bdmFileStore.loadArtifactDescriptor().getVersion();
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        return null;
    }

    private DependencyToUpdate createBDMGroupIdToUpdate(Location location) {
        return new DependencyToUpdate(location,
                GROUPID_TAG,
                Messages.bdmGroupIdNotMatching,
                REST_API_BONITA_DEPENDENCY_STATUS_CODE,
                currentBDMGroupId(),
                IStatus.WARNING);
    }

    String currentBDMGroupId() {
        BusinessObjectModelRepositoryStore repositoryStore = RepositoryManager.getInstance().getCurrentRepository()
                .getRepositoryStore(BusinessObjectModelRepositoryStore.class);
        BusinessObjectModelFileStore bdmFileStore = (BusinessObjectModelFileStore) repositoryStore
                .getChild(BusinessObjectModelFileStore.BOM_FILENAME, false);
        if (bdmFileStore != null) {
            try {
                return bdmFileStore.loadArtifactDescriptor().getGroupId();
            } catch (CoreException e) {
                BonitaStudioLog.error(e);
            }
        }
        return null;
    }

}
