/**
 * Copyright (C) 2019 Bonitasoft S.A.
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
package org.bonitasoft.studio.application.operation;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

import org.bonitasoft.engine.session.APISession;
import org.bonitasoft.studio.application.i18n.Messages;
import org.bonitasoft.studio.application.ui.control.model.BuildableArtifact;
import org.bonitasoft.studio.application.ui.control.model.FileStoreArtifact;
import org.bonitasoft.studio.common.core.IRunnableWithStatus;
import org.bonitasoft.studio.common.repository.AbstractRepository;
import org.bonitasoft.studio.engine.export.MavenProjectBuilder;
import org.bonitasoft.studio.ui.util.StatusCollectors;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

public class DeployProjectOperation implements IRunnableWithStatus {

    private static final String APPLICATIONS_STORE_NAME = "applications";
    private static final int MAVEN_BUILD_WORK_UNIT = 1;

    private IStatus status;
    private final APISession session;
    private final Collection<FileStoreArtifact> artifactsToDeploy;
    private final MavenProjectBuilder mavenBuilder = new MavenProjectBuilder();

    public DeployProjectOperation(APISession session, Collection<FileStoreArtifact> artifactsToDeploy) {
        this.session = Objects.requireNonNull(session, "session cannot be null");
        this.artifactsToDeploy = Objects.requireNonNull(artifactsToDeploy, "artifactsToDeploy cannot be null");
    }

    @Override
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
        boolean hasBuildableArtifacts = hasBuildableArtifacts();

        monitor.beginTask(Messages.deployingProject, computeTotalWork(hasBuildableArtifacts));

        try {
            if (hasBuildableArtifacts) {
                buildMavenProject(monitor);
                if (!status.isOK()) {
                    return;
                }
            }

            deployArtifacts(monitor);
        } finally {
            monitor.done();
        }
    }

    private boolean hasBuildableArtifacts() {
        return artifactsToDeploy.stream()
                .filter(artifact -> !APPLICATIONS_STORE_NAME.equals(artifact.getFileStore().getParentStore().getName()))
                .anyMatch(BuildableArtifact.class::isInstance);
    }

    private int computeTotalWork(boolean includeMavenBuild) {
        return artifactsToDeploy.size() + (includeMavenBuild ? MAVEN_BUILD_WORK_UNIT : 0);
    }

    private void buildMavenProject(IProgressMonitor monitor) {
        monitor.setTaskName(Messages.buildingMavenProject);
        status = mavenBuilder.cleanInstall();
        monitor.worked(MAVEN_BUILD_WORK_UNIT);
    }

    private void deployArtifacts(IProgressMonitor monitor) {
        status = artifactsToDeploy.stream()
                .peek(artifact -> monitor.setTaskName(String.format(Messages.deploying, artifact.getName())))
                .map(artifact -> deployArtifact(artifact, monitor))
                .peek(s -> monitor.worked(1))
                .collect(StatusCollectors.toMultiStatus());
    }

    private IStatus deployArtifact(FileStoreArtifact artifact, IProgressMonitor monitor) {
        return !monitor.isCanceled()
                ? artifact.deploy(session, new HashMap<>(), AbstractRepository.NULL_PROGRESS_MONITOR)
                : Status.CANCEL_STATUS;
    }

    @Override
    public IStatus getStatus() {
        return status;
    }

}
