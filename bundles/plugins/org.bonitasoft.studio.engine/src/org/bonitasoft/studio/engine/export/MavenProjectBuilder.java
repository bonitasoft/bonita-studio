/**
 * Copyright (C) 2024 BonitaSoft S.A.
 * BonitaSoft, 31 rue Gustave Eiffel - 38000 Grenoble
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2.0 of the License, or
 * (at your option) any later version.
 */
package org.bonitasoft.studio.engine.export;

import java.util.List;

import org.apache.maven.execution.BuildSuccess;
import org.apache.maven.execution.MavenExecutionResult;
import org.bonitasoft.studio.common.log.BonitaStudioLog;
import org.bonitasoft.studio.common.repository.BuildScheduler;
import org.bonitasoft.studio.common.repository.RepositoryManager;
import org.bonitasoft.studio.common.repository.core.BonitaProject;
import org.bonitasoft.studio.engine.EnginePlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.m2e.core.MavenPlugin;
import org.eclipse.m2e.core.embedder.ICallable;
import org.eclipse.m2e.core.embedder.IMavenExecutionContext;
import org.eclipse.m2e.core.project.IMavenProjectFacade;

/**
 * Utility class for building Maven projects in Bonita Studio.
 * <p>
 * This class provides methods to execute Maven builds (clean, install, etc.)
 * on Bonita projects within the Eclipse/OSGi environment using the M2E API.
 * </p>
 * <p>
 * Each instance is stateless and can be safely created multiple times.
 * Maven builds are executed with proper Eclipse workspace scheduling rules
 * to avoid conflicts with other build operations.
 * </p>
 * <p>
 * <b>Usage example:</b>
 * <pre>
 * MavenProjectBuilder builder = new MavenProjectBuilder();
 * IStatus status = builder.cleanInstall();
 * if (!status.isOK()) {
 *     // Handle build failure
 * }
 * </pre>
 * </p>
 *
 * @since 10.3.0
 * @see BarExporter
 * @see BuildScheduler
 */
public class MavenProjectBuilder {

    private static final String PLUGIN_ID = EnginePlugin.PLUGIN_ID;

    /**
     * Builds a Maven project with the specified goals.
     * <p>
     * This method executes Maven goals on the given Bonita project within
     * the Eclipse workspace build rules to ensure proper synchronization.
     * The build output is logged using {@link BonitaStudioLog}.
     * </p>
     *
     * @param project the Bonita project to build, must not be null
     * @param goals the Maven goals to execute (e.g., "clean", "install"), must not be null or empty
     * @return {@link IStatus#OK_STATUS} if build succeeds, error status otherwise
     * @throws IllegalArgumentException if project or goals are null
     */
    public IStatus buildProject(BonitaProject project, List<String> goals) {
        if (!isProjectValid(project)) {
            return createErrorStatus("Cannot build Maven project: No active Bonita project found");
        }

        logBuildStart(project, goals);

        try {
            IStatus buildResult = executeMavenBuild(project, goals);
            if (!buildResult.isOK()) {
                return buildResult;
            }

            return waitForBuildCompletion(project);
        } catch (Exception e) {
            return handleUnexpectedError(project, e);
        }
    }

    /**
     * Convenience method to execute Maven clean install on the current active project.
     * <p>
     * Automatically retrieves the current project from {@link RepositoryManager}.
     * Equivalent to running {@code mvn clean install} from command line.
     * </p>
     *
     * @return {@link IStatus#OK_STATUS} if build succeeds, error status if no active project or build fails
     */
    public IStatus cleanInstall() {
        BonitaProject project = RepositoryManager.getInstance().getCurrentProject().orElse(null);
        return cleanInstall(project);
    }

    /**
     * Convenience method to execute Maven clean install on a specific project.
     * <p>
     * Equivalent to running {@code mvn clean install} from command line.
     * This will:
     * <ul>
     *   <li>Clean the target directory</li>
     *   <li>Compile sources</li>
     *   <li>Run tests</li>
     *   <li>Package artifacts</li>
     *   <li>Install artifacts in local Maven repository</li>
     * </ul>
     * </p>
     *
     * @param project the Bonita project to build, must not be null
     * @return {@link IStatus#OK_STATUS} if build succeeds, error status otherwise
     */
    public IStatus cleanInstall(BonitaProject project) {
        return buildProject(project, List.of("clean", "install"));
    }

    private boolean isProjectValid(BonitaProject project) {
        return project != null;
    }

    private void logBuildStart(BonitaProject project, List<String> goals) {
        String goalsStr = String.join(" ", goals);
        BonitaStudioLog.info(
                String.format("Building project %s (mvn %s)...", project.getDisplayName(), goalsStr),
                PLUGIN_ID);
    }

    private IStatus executeMavenBuild(BonitaProject project, List<String> goals) throws CoreException {
        return BuildScheduler.callWithBuildRule(() -> {
            IMavenProjectFacade mavenProject = resolveMavenProject(project);
            if (mavenProject == null) {
                return createErrorStatus("Cannot resolve Maven project for " + project.getDisplayName());
            }

            return runMavenGoals(mavenProject, goals, project.getDisplayName());
        }, new NullProgressMonitor());
    }

    private IMavenProjectFacade resolveMavenProject(BonitaProject project) {
        return MavenPlugin.getMavenProjectRegistry().getProject(project.getParentProject());
    }

    private IStatus runMavenGoals(IMavenProjectFacade mavenProject, List<String> goals, String projectName)
            throws CoreException {
        var ctx = mavenProject.createExecutionContext();
        var request = ctx.getExecutionRequest();
        request.setGoals(goals);
        request.setPom(mavenProject.getPomFile());

        MavenExecutionResult result = MavenPlugin.getMavenProjectRegistry().execute(
                mavenProject,
                new MavenExecutor(request),
                new NullProgressMonitor());

        return evaluateResult(result, projectName);
    }

    private IStatus evaluateResult(MavenExecutionResult result, String projectName) {
        if (result.getBuildSummary(result.getProject()) instanceof BuildSuccess) {
            BonitaStudioLog.info("Maven build successful for " + projectName, PLUGIN_ID);
            return Status.OK_STATUS;
        } else {
            String errorMsg = "Maven build failed for project " + projectName;
            Throwable cause = result.hasExceptions() ? result.getExceptions().get(0) : null;
            BonitaStudioLog.error(errorMsg, cause);
            return new Status(IStatus.ERROR, PLUGIN_ID, errorMsg, cause);
        }
    }

    private IStatus waitForBuildCompletion(BonitaProject project) {
        try {
            BuildScheduler.joinOnBuildRule();
            return Status.OK_STATUS;
        } catch (IllegalStateException | OperationCanceledException | InterruptedException e) {
            String errorMsg = "Maven build interrupted for " + project.getDisplayName();
            BonitaStudioLog.error(errorMsg, e);
            return new Status(IStatus.ERROR, PLUGIN_ID, errorMsg, e);
        }
    }

    private IStatus handleUnexpectedError(BonitaProject project, Exception e) {
        String errorMsg = "Unexpected error during Maven build for " + project.getDisplayName();
        BonitaStudioLog.error(errorMsg, e);
        return new Status(IStatus.ERROR, PLUGIN_ID, errorMsg, e);
    }

    private IStatus createErrorStatus(String message) {
        BonitaStudioLog.error(message, PLUGIN_ID);
        return new Status(IStatus.ERROR, PLUGIN_ID, message);
    }

    /**
     * Internal class to encapsulate Maven execution callback.
     * <p>
     * This class adapts a Maven execution request to the M2E {@link ICallable}
     * interface, allowing Maven builds to be executed within the Eclipse
     * Maven execution context with proper classloader and session management.
     * </p>
     */
    private static class MavenExecutor implements ICallable<MavenExecutionResult> {

        private final org.apache.maven.execution.MavenExecutionRequest request;

        /**
         * Creates a new Maven executor for the given request.
         *
         * @param request the Maven execution request containing goals, POM file, and settings
         */
        MavenExecutor(org.apache.maven.execution.MavenExecutionRequest request) {
            this.request = request;
        }

        /**
         * Executes the Maven request within the given execution context.
         *
         * @param context the Maven execution context providing classloader and session
         * @param monitor progress monitor (currently unused but required by interface)
         * @return the Maven execution result containing build status and any exceptions
         * @throws CoreException if Maven execution fails with an unrecoverable error
         */
        @Override
        public MavenExecutionResult call(IMavenExecutionContext context, IProgressMonitor monitor)
                throws CoreException {
            return context.execute(request);
        }
    }
}
