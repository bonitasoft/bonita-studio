/*******************************************************************************
 * Copyright (C) 2019 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.theme.builder;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.model.Plugin;
import org.apache.maven.project.MavenProject;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.importer.bos.status.BonitaStatusCodeAggregator;
import org.bonitasoft.studio.maven.builder.validator.DependencyToUpdate;
import org.bonitasoft.studio.maven.builder.validator.DependencyVersionToUpdateFinder;
import org.bonitasoft.studio.maven.builder.validator.Location;
import org.bonitasoft.studio.maven.builder.validator.LocationResolver;
import org.bonitasoft.studio.maven.i18n.Messages;
import org.bonitasoft.studio.rest.api.extension.RestAPIExtensionActivator;
import org.eclipse.core.runtime.IStatus;

public class ThemeDependencyVersionToUpdateFinder extends DependencyVersionToUpdateFinder {

    private static final String RECOMMENDED_MINIMAL_MAVEN_FRONTEND_PLUGIN_VERSION = "1.10.2";
    private static final String MAVEN_FRONTEND_PLUGIN_GROUP_ID = "com.github.eirslett";
    private static final String MAVEN_FRONTEND_PLUGIN_ARTIFACT_ID = "frontend-maven-plugin";

    public List<DependencyToUpdate> findDependenciesToUpdate(MavenProject mavenProject) {
        List<DependencyToUpdate> dependenciesToUpdate = new ArrayList<>();
        LocationResolver locationResolver = new LocationResolver(toDocument(mavenProject.getFile()));
        List<Location> versionLocations = locationResolver.findLocationsMatching("(<version>)(.)+(</version>)",
                false);
        findMavenPluginDependenciesToUpdate(mavenProject, versionLocations, dependenciesToUpdate);
        return dependenciesToUpdate;
    }

    private void findMavenPluginDependenciesToUpdate(MavenProject mavenProject,
            List<Location> versionLocations, List<DependencyToUpdate> dependenciesToUpdate) {
        Plugin mavenFrontendPlugin = mavenProject
                .getPlugin(toArtifactName(MAVEN_FRONTEND_PLUGIN_GROUP_ID, MAVEN_FRONTEND_PLUGIN_ARTIFACT_ID));
        if (mavenFrontendPlugin != null) {
            String message = String.format(Messages.updateVersionForBCDCompliance,
                    toArtifactName(mavenFrontendPlugin.getGroupId(), mavenFrontendPlugin.getArtifactId()), 
                    RECOMMENDED_MINIMAL_MAVEN_FRONTEND_PLUGIN_VERSION);
            validatePluginToUpdate(versionLocations, mavenFrontendPlugin,
                    RECOMMENDED_MINIMAL_MAVEN_FRONTEND_PLUGIN_VERSION,
                    RECOMMENDED_MINIMAL_MAVEN_FRONTEND_PLUGIN_VERSION,
                    message,
                    BonitaStatusCodeAggregator.THEME_MAVEN_FRONTEND_PLUGIN_STATUS_CODE,
                    IStatus.WARNING).ifPresent(dependenciesToUpdate::add);
        } else {
            BonitaStudioLog.error(String.format("Unable to find plugin %s in the pom.xml",
                    toArtifactName(MAVEN_FRONTEND_PLUGIN_GROUP_ID, MAVEN_FRONTEND_PLUGIN_ARTIFACT_ID)),
                    RestAPIExtensionActivator.PLUGIN_ID);
        }
    }

}
