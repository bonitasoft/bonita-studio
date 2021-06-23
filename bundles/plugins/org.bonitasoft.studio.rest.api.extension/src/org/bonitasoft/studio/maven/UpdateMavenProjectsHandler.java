/*******************************************************************************
 * Copyright (C) 2020 BonitaSoft S.A.
 * BonitaSoft is a trademark of BonitaSoft SA.
 * This software file is BONITASOFT CONFIDENTIAL. Not For Distribution.
 * For commercial licensing information, contact:
 * BonitaSoft, 32 rue Gustave Eiffel – 38000 Grenoble
 * or BonitaSoft US, 51 Federal Street, Suite 305, San Francisco, CA 94107
 *******************************************************************************/
package org.bonitasoft.studio.maven;

import java.util.ArrayList;
import java.util.List;

import org.bonitasoft.studio.businessobject.maven.UpdateMavenProjectConfiguration;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.RepositoryAccessor;
import org.bonitasoft.studio.common.repository.model.ReadFileStoreException;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionFileStore;
import org.bonitasoft.studio.rest.api.extension.core.repository.RestAPIExtensionRepositoryStore;
import org.eclipse.core.resources.IProject;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.m2e.core.ui.internal.UpdateMavenProjectJob;

public class UpdateMavenProjectsHandler {

    @Execute
    public void execute(RepositoryAccessor repositoryAccessor) {
        final RestAPIExtensionRepositoryStore repositoryStore = repositoryAccessor
                .getRepositoryStore(RestAPIExtensionRepositoryStore.class);
        final List<IProject> projects = new ArrayList<>();
        for (final RestAPIExtensionFileStore fStore : repositoryStore.getChildren()) {
            IProject project = fStore.getProject();
            if (project.exists()) {
                projects.add(project);
            }
        }
        new UpdateMavenProjectJob(projects.toArray(new IProject[projects.size()]),
                UpdateMavenProjectConfiguration.IS_OFFLINE, UpdateMavenProjectConfiguration.FORCE_UPDATE_DEPENDENCIES,
                UpdateMavenProjectConfiguration.UPDATE_CONFIGURATION, UpdateMavenProjectConfiguration.CLEAN_PROJECT,
                UpdateMavenProjectConfiguration.REFRESH_FROM_LOCAL).schedule();
    }

}
