/**
 * Copyright (C) 2022 BonitaSoft S.A.
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

import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

import org.apache.maven.execution.BuildSuccess;
import org.apache.maven.execution.MavenExecutionResult;
import org.bonitasoft.studio.common.repository.BuildScheduler;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.ICallable;
import org.eclipse.m2e.core.embedder.IMaven;
import org.eclipse.m2e.core.embedder.IMavenExecutionContext;

public class CreateBonitaProjectModulePlugin {

    private Path project;
    private String projectId;
    private String goal;
    private static Object lock = new Object();

    public CreateBonitaProjectModulePlugin(Path project, String projectId, String goal) {
        this.project = project;
        this.projectId = projectId;
        this.goal = goal;
    }

    public IStatus execute(IProgressMonitor monitor) throws CoreException {
        synchronized (lock) {
            return BuildScheduler.callWithBuildRule(() -> {
                IMaven maven = maven();
                var ctx = maven.createExecutionContext();
                var request = ctx.getExecutionRequest();
                request.setGoals(List.of("bonita-project:" + goal));
                var properties = new Properties();
                properties.setProperty("bonitaProjectId", projectId);
                request.setUserProperties(properties);
                request.setPom(project.resolve("pom.xml").toFile());
                var executionResult = ctx.execute(new ICallable<MavenExecutionResult>() {

                    @Override
                    public MavenExecutionResult call(IMavenExecutionContext context, IProgressMonitor monitor)
                            throws CoreException {
                        return context.execute(request);
                    }

                }, monitor);

                if (executionResult.getBuildSummary(executionResult.getProject()) instanceof BuildSuccess) {
                    return Status.OK_STATUS;
                } else {
                    throw new CoreException(new Status(IStatus.ERROR, getClass(),
                            "Failed to execute bonita-project-maven-plugin:" + goal,
                            executionResult.hasExceptions() ? executionResult.getExceptions().get(0) : null));
                }
            });
        }
    }

    IMaven maven() {
        return MavenPlugin.getMaven();
    }

}
