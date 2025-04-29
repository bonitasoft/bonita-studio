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
package org.bonitasoft.studio.common.repository.core.maven.plugin;

import java.util.List;

import org.apache.maven.execution.BuildSuccess;
import org.apache.maven.execution.MavenExecutionResult;
import org.bonitasoft.studio.common.repository.BuildScheduler;
import org.bonitasoft.studio.common.repository.Messages;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.ICallable;
import org.eclipse.m2e.core.embedder.IMaven;
import org.eclipse.m2e.core.embedder.IMavenExecutionContext;

public class InstallLocalDependenciesPlugin {

    private BonitaProject project;

    public InstallLocalDependenciesPlugin(BonitaProject project) {
        this.project = project;
    }

    public IStatus execute(IProgressMonitor monitor) throws CoreException {
        monitor.beginTask(Messages.installLocalDependencies, IProgressMonitor.UNKNOWN);
        return BuildScheduler.callWithBuildRule(() -> {
            var mavenProject = MavenPlugin.getMavenProjectRegistry().getProject(project.getAppProject());
            if (mavenProject == null) {
                return new Status(IStatus.ERROR, getClass(),
                        "An error occured while executing bonita project plugin. Cannot resolve the Maven project.");
            }
            var ctx = mavenProject.createExecutionContext();
            var request = ctx.getExecutionRequest();
            request.setGoals(
                    List.of("org.bonitasoft.maven:bonita-project-maven-plugin:install"));
            request.setPom(mavenProject.getPomFile());
            MavenExecutionResult executionResult = MavenPlugin.getMavenProjectRegistry().execute(mavenProject, new ICallable<MavenExecutionResult>() {

                @Override
                public MavenExecutionResult call(IMavenExecutionContext context, IProgressMonitor monitor)
                        throws CoreException {
                    return context.execute(request);
                }

            }, monitor);
            if (executionResult.getBuildSummary(executionResult.getProject()) instanceof BuildSuccess) {
                return Status.OK_STATUS;
            } else {
                throw new CoreException(
                        new Status(IStatus.ERROR, getClass(), "Failed to execute bonita-project-maven-plugin",
                                executionResult.hasExceptions() ? executionResult.getExceptions().get(0) : null));
            }
        }, monitor);
    }

    IMaven maven() {
        return MavenPlugin.getMaven();
    }

}
